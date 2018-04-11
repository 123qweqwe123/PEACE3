<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>患者姓名修改</title>
	<style type="text/css">
		.leftLable{
			text-align: right;
		}
		.leftLable{
			padding-right:0;
			width:7%;
		}
	</style>
	
	<script type="text/javascript">

		//查询绑定
		var multipleSearch = function(){ 
			var myform = $("#searchForm").serializeArray(); 
			var data = {};
			$.each(myform, function(i, field){
				data[field.name]=null; 
				if(field.value && ''!=field.value){
					data[field.name] = field.value;
				}
			});   
	      
			var postData = $("#grid").jqGrid("getGridParam", "postData");  
			$.extend(postData,data);
	
			$("#grid").jqGrid("setGridParam", {
				search: true  
			}).trigger("reloadGrid", [{page:1}]);
		};  
	
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
	    	if(e && e.keyCode==27){ // 按 Esc 
	        	//要做的事情
	      	}
	    	if(e && e.keyCode==113){ // 按 F2 
	         	//要做的事情
	     	}            
	     	if(e && e.keyCode==13){ // enter 键
	        	 //要做的事情
	        	//multipleSearch();
	    	}
		};
	</script>
</head>
<body>
	<ul class="breadcrumb">
		<li class="active">数据管理</li><li class="active" >患者姓名修改</li>
	</ul>  
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form=''>PID:</label></li>
                         <li> 
                         	<input  type="text" class="form-control input-sm" name="patientId" value='' placeholder="患者ID">
                         </li>
                         <li class="select-one"> <label form=''>患者姓名:</label></li>
                         <li> 
                         	<input  type="text" class="form-control input-sm" name="patientName" value='' placeholder="患者姓名">
                         </li>
                         <li class="select-one"> <label form=''>医院名称:</label></li>
                         <li> 
                         	<input  type="hidden" name="lccCode" id="lccCode">
                         	<input  type="text" class="form-control input-sm" name="lccName" id="lccName" value='' placeholder="输入单位简拼或LCCID">
                         </li>
                         <li> <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                </fieldset>
			</form>
		</div>
	</div>
	
	<div id="message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="messageSpanId"></span>
	</div>
	
	<div id="jqgrid">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	

	<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">姓名修改</h4>
	      </div>
	      <div class="modal-body">
	      	<input type="hidden" id="modify_patientId"/>
	      	<div class="row">
	      		<label class="col-md-4" style="text-align:right">原始姓名:</label>
	      		<div class="col-md-6" style="padding-left:0px">
	      			<input  type="text" class="form-control input-sm" id="modify_oldPatientName" value='' placeholder="" readOnly>
	      		</div>
	      	</div>
	      	<div class="row" style="margin-top:6px;">
	      		<label class="col-md-4" style="text-align:right">修改姓名:</label>
	      		<div class="col-md-6" style="padding-left:0px">
	      			<input maxlength="10" type="text" class="form-control input-sm" id="modify_newPatientName" value='' placeholder="">
	      		</div>
	      	</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000" onclick="save();">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  

	<script type="text/javascript">
	$(function(){
		$.getJSON("${ctx}/combox/dataLimitLcc",function(data) { 
	    $('#lccName').autocomplete(data,{
	        minChars: 0,
	        mustMatch:true, 
	        width:260,
	       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	        formatItem: function(item,i, max) {
	            return '<table><tr><td width="80px;">' + item.LCC_CODE + '</td><td width="180px;">' + item.LCC_NAME + '</td></tr></table>';
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item,i, max) {
	            return item.HELP_CODE+ item.LCC_CODE+item.LCC_NAME;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item) {
	            return item.LCC_NAME;
	        }
	    }); 
	  //选中 某条记录 触发的事件
	    $('#lccName').result(function(event, item){ 
			if(item){
				if(item.LCC_CODE != $("#lccCode").val()){
					$("#lccCode").val(item.LCC_CODE);
				}
			}else{
				$("#lccCode").val("");
			}
	     });
	});
		var option = {
	        url : '${ctx}/dataMgt/patientNameModify/list',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : [ 'LCCID','医院名称','PID','患者姓名','身份证','出生日期','手机','地址'],
	        colModel : [ 
						 {name : 'LCC_CODE', index : 'LCC_CODE', align:'center'},
						 {name : 'LCC_NAME', index : 'LCC_NAME', align:'center'},
						 {name : 'PATIENT_ID', index : 'PATIENT_ID', align:'center'},
						 {name : 'PATIENT_NAME', index : 'PATIENT_NAME', align:'center'},
						 {name : 'ID_NUMBER', index : 'ID_NUMBER', align:'center'},
						 {name : 'BIRTHDAY_STR', index : 'BIRTHDAY_STR', align:'center'},
						 {name : 'MOBILE', index : 'MOBILE', align:'center'},
						 {name : 'ADDRESS', index : 'ADDRESS', align:'left'}
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        autowidth : true,  
	        pager : '#pager',  
	        sortname : 'LCC_CODE',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "ASC",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: false
	 	};  
		$("#grid").jqGrid(option); 
		$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})
		.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){toModify()},position:"last"});
		jqgridResponsive("grid",false); 
		
		$("#btnQuery").click(multipleSearch);
		
		$('#cancel').click(function(){
		  $('#dialog-confirm').modal('hide');
		}); 
	});
	
	function toModify(){
		var _id = $("#grid").jqGrid('getGridParam','selrow');
	    if($.isEmptyObject(_id)) { 
	        showResult(false,'请选择一条数据',2000);
	        return;
	    }
	    var rowData = $("#grid").jqGrid('getRowData',_id);
	    $("#modify_patientId").val(rowData.PATIENT_ID);
	    $("#modify_oldPatientName").val(rowData.PATIENT_NAME);
	    $("#modify_newPatientName").val("");
		openDialog(null,"dialog-confirm");
	}
	
	function save(){
		var oldName = $("#modify_oldPatientName").val();
		var newName = $.trim($("#modify_newPatientName").val());
		if(newName == ""){
			jAlert("修改姓名不能为空");
			return;
		}
		if(oldName == newName){
			jAlert("修改姓名和原始姓名一致，不需要修改");
			return;
		}
		
		var reg = /^[\u4e00-\u9fa5]+$/;
		if(!reg.test(newName)){
			jAlert("修改姓名不符合规则");
			return;
		}
		
		var patientId = $.trim($("#modify_patientId").val());
		
		jConfirm("确认修改姓名吗?",null,function(r){
			if(r){
				$.post("${ctx}/dataMgt/patientNameModify/save",{patientId:patientId,oldPatientName:oldName,newPatientName:newName},function(result){
					if(result!=null && result !='undefinded'){
						if(result.success){
							$('#dialog-confirm').modal('hide');
                  			$("#grid").trigger('reloadGrid');
                  			showResult(true,"姓名修改成功");
                		}else{
                			jAlert(result.msg);
                		}
					}
				},'json');	
			}
		});
	}
	
	//弹出对话框
	function openDialog(url,modalId){
	    $( "#"+modalId ).modal({
	         backdrop: 'static'
	    });
	    //使用此方法防止js缓存不加载
	    if(url != null){
	    	$("#"+modalId+" p" ).load(url);
	    }
	}
	
	function showResult(result,message,delay){
		  $("#messageSpanId").text(message);

		  if (!delay || typeof(delay)=="undefined" || typeof(delay)!='number'){
		      delay = 2000;
		  }
		  if(result){
		      $("#message").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
		  }else{
		      $("#message").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
		  }
		  window.setTimeout(function() {
		      $('#message').slideToggle(1000);
		  }, delay);
	}
	</script>


</body>
</html>