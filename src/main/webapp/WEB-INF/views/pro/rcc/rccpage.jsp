<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
 <title>RCC管理</title>
 <script  src="${ctx}/static/echarts/esl/esl.js"></script> 
 	<script type="text/javascript">

		
	
	
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
	<style>
		.img_mystyle{
			width: 20px;
			height: 20px;
		}
	</style>
</head>
<body>

<div id="navbar">
       <ul class="breadcrumb">
         <li class="active">RCC管理</li>
 
      </ul>
</div>
<!-- 
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row"> 
					<div  class="leftLable col-md-1">类型:</div> 
					<div  class="col-md-2"> 
						<select id="status" name="status" class="form-control"  >
		                 <option value=''>所有</option> 
		                 <option value='3'>严重</option> 
		                 <option value='2'>中等</option> 
		                 <option value='4'>严重-中等</option> 
		                 <option value='1'>正常</option> 
		                </select>
						
					</div>
					
					<div  class="leftLable col-md-1">项目点:</div> 
					<div  class="col-md-2"> 
						
                      <select id="lccCode" name="lccCode" class="form-control input-sm">
					<option value="">请选择单位</option>
					<c:forEach items="${lccDictList }" var="lccDict">
					<option value="${lccDict.lccCode }" >${lccDict.lccName }</option>
					</c:forEach>
		          	</select>
					</div>

                    <div  class="leftLable col-md-2">预约检查时间:</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="planTime2" id="planTime2" value='' placeholder=""/>
					</div> 
					
                    <div  class="right col-md-2"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
					</div>
				
				</div>
				

			</form>
		</div>
	</div>
   -->
<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
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
	        <h4 class="modal-title">区域中心</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-confirm2' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">预约页面</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_other' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<div id='dialog-delete' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">删除</h4>
	      </div>
	      <div class="modal-body">
	        <p>是否确定删除？</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel3' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">删除</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<script type="text/javascript">


function initProvince(){
	$.getJSON("${ctx}/combox/province",function(data) { 
	    $('#provinceName').autocomplete(data,{
	        minChars: 0,
	        mustMatch:true, 
	        width:260,
	       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	        formatItem: function(item,i, max) {
	            return '<table><tr><td width="80px;">' + item.PROVINCE_CODE + '</td><td width="180px;">' + item.PROVINCE_NAME + '</td></tr></table>';
	            
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item,i, max) {
	            return item.HELP_CODE+item.PROVINCE_CODE+item.PROVINCE_NAME;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item) {
	            return item.PROVINCE_NAME;
	        }
	    }); 
	  //选中 某条记录 触发的事件
	    $('#provinceName').result(function(event, item){ 
			if(item){
				if(item.PROVINCE_CODE != $("#provinceCode").val()){
					$("#provinceCode").val(item.PROVINCE_CODE);
					initRcc();
				}
			}else{
				$("#provinceCode").val("");
				initRcc();
			}
	     });
	});
	initRcc();
}


function initRcc(){
	$('#rccName').unautocomplete();
	$("#rccName").val(''); 
	$("#rccCode").val('');
	
	$.getJSON("${ctx}/combox/rcc?provinceCode="+$("#provinceCode").val(),function(data) { 
	    $('#rccName').autocomplete(data,{
	        minChars: 0,
	        mustMatch:true, 
	        width:260,
	       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	        formatItem: function(item,i, max) {
	            return '<table><tr><td width="80px;">' + item.RCC_CODE + '</td><td width="180px;">' + item.RCC_NAME + '</td></tr></table>';
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item,i, max) {
	            return item.HELP_CODE+ item.RCC_CODE+item.RCC_NAME;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item) {
	            return item.RCC_NAME;
	        }
	    }); 
	  //选中 某条记录 触发的事件
	    $('#rccName').result(function(event, item){ 
			if(item){
				if(item.RCC_CODE != $("#rccCode").val()){
					$("#rccCode").val(item.RCC_CODE);
					initLcc();
				}
			}else{
				$("#rccCode").val("");
				initLcc();
			}
	     });
	});
	initLcc();
}

function initLcc(){
	$('#lccName').unautocomplete();
	$("#lccName").val(''); 
	$("#lccCode").val(''); 
	
	$.getJSON("${ctx}/combox/dataLimitLcc?provinceCode="+$("#provinceCode").val()+"&rccCode="+$("#rccCode").val(),function(data) { 
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
}
//initProvince();

$(function(){

	var multipleSearch = function(){
		 var postData = $("#grid").jqGrid("getGridParam", "postData");  
		var myform = $("#searchForm").serializeArray(); 
		
	      var mydata = {};
	    
	     
	      var lccCode =$("#lccCode").val();
	      if(lccCode && ''!=lccCode){
	          mydata.lccCode = lccCode;
	      }else{
	          delete postData.lccCode;
	      }
	     
	     $.extend(postData,mydata);
	     $("#grid").jqGrid("setGridParam", {
	         search: true  
	     }).trigger("reloadGrid", [{page:1}]);
	
		
	};  
	
	var option = {
	        url : '${ctx}/rcc/queryRccList',
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : [ 'RCC编码','RCC名称','科室','负责人','组织结构代码','上级单位'],
	        colModel : [ 
	                     {name : 'rccCode', index : 'rccCode', align:'center',sortable: false },
	                     {name : 'rccName', index : 'rccName', align:'center',sortable: false },
	                     {name : 'departMent', index : 'departMent', align:'center',sortable: false },
	                     {name : 'manaMan', index : 'manaMan', align:'center',sortable: false },
	                     {name : 'orgCode', index : 'orgCode', align:'center',sortable: false },
	                     {name : 'unit', index : 'unit', align:'center',sortable: false }
	                     
	                   ],     
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'rccCode',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "ASC",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        multiselect: false,
        jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false,
			cell : "cell",
			id : "patientId"
		}
 	};  
	$("#grid").jqGrid(option); 
	$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'}
	)
	.navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
	.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
	.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"});
	jqgridResponsive("grid",false); 
	//取消按钮操作
	$('#cancel').click(function(){
		$('#dialog-confirm').modal('hide');
		
	});	
	
	$("#btnQuery").click(multipleSearch);
	
	//弹出新增对话框			
	function toAdd(){
		var timebak = new Date().getTime();
		openDialog("${ctx}/rcc/openmodalAreainput?time="+timebak);
	}
	//弹出修改对话框
	function toModify(){
		 var id = $("#grid").jqGrid('getGridParam','selrow');//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
		 var rowData = $("#grid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
        var rccCode= rowData.rccCode ;
		var patientId = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(patientId)) {
			openError('请选择一条数据',2000);
			return;
		}
		$("#do_add").attr("disabled",true);
		var timebak = new Date().getTime();
		openDialog("${ctx}/rcc/openmodalAreainput?rccCode="+rccCode+"&time="+timebak);
	}
	//弹出删除对话框
	function toDelete(){
		var lccCode = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(lccCode)) {
			openError('请选择要删除的数据',2000);
			return;
		}
		var rowData = $("#grid").jqGrid('getRowData', lccCode);
		$("#do_delete").attr("disabled",false);
		$("#dialog-delete").modal({
			 keyboard: false
		});
	}
	$('#cancel3').click(function(){
		$('#dialog-delete').modal('hide');
	});
	$('#do_delete').click(function(){
		var id = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(id)) {
			openError('请选择要删除的数据',2000);
			return;
		}
		$("#do_delete").attr("disabled",true);
		var rowData = $("#grid").jqGrid('getRowData', id);
		//开始执行删除动作
		$.post("${ctx}/rcc/deleteByRccCode", 
				{rccCode :rowData.rccCode },
    		function(data){
				$("#grid").trigger("reloadGrid");
				$('#dialog-delete').modal('hide');
				var message = "删除失败！";
    			if(data.success){
    				message = "删除成功！";
    			}
    			showResult(data.success,message);
    		}, 
    		"json");
	});
	
	/**
	   修改的记录入库	
	**/
	$('#do_save').click(function(){
		
		var myform = $("#dialog-confirm").find("form").get(0);
		if(!jQuery(myform).validate().form()){ return ;}
		var myform = $("#dialog-confirm").find("form").serializeArray();
		console.info(myform);
		var data = {};
		$.each(myform, function(i, field){
			data[field.name] = field.value;
		});
		$("#do_save").attr("disabled",true);
		addDate(data);
		
	});
	function addDate(data){
		$.post("${ctx}/rcc/addPipCommRcc",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm').modal('hide');
			var message = "操作失败！";
			if(result.success){
				message ='操作成功!';
			}
			$('#do_save').attr("disabled",false);
			showResult(result.success,message);
		},'json');
	}
	/* //新增数据
	function add(data){
		//新增时注释id值
		//data['id'] = null;
		$.post("${ctx}/reserve/saveReserveLog",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm').modal('hide');
			var message = "修改失败！";
			if(result){
				message ='修改成功!';
			}
			$("#do_save").attr("disabled",false);
			showResult(result,message);
		},'json');
	} */
	
	
	
	
	//弹出对话框
	function openDialog(url,data){
		$( "#dialog-confirm" ).modal({
			 backdrop: 'static'
		});
		$( "#do_add").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm p" ).load(url);
	}
	
	
	
	function openError(message,delay){
    	$('#alert').show().find('strong').text(message);
    	window.setTimeout(function() {
    		$('#alert').slideUp("slow");
    	}, delay);
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
	
	
	
});

</script>


</body>





</html>