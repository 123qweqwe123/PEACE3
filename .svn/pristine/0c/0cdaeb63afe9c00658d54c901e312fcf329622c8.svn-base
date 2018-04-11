<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>未回复查询</title>
	<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script> 
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
	  <li class="active">未回复查询</li>
	</ul>  
	<div id="select">
		<div class="select-main">
			<form action="${ctx}/msg/noReply/noReplyExport" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row" style="margin-top:5px;"> 
					<div  class="leftLable col-md-3">医院名称:</div> 
					<div  class="right col-md-2"> 
						<input id="lccCode" type="hidden" name="lccCode" />
						<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					
					<div  class="leftLable col-md-1">PID:</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>
					
					<div  class="leftLable col-md-1">患者姓名:</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
					</div> 
					
					<div  class="leftLable col-md-1">所属组别:</div> 
					<div  class="right col-md-2"> 
						<select class="form-control input-sm"  name="group" id="group" >
							<option value="">全部</option>
							<option value="01">非糖尿病实验组</option>
							<option value="02">非糖尿病对照组</option>
							<option value="11">糖尿病实验组</option>
							<option value="12">糖尿病对照组</option>
						</select>
					</div>
				</div>
				<div  class="row" style="margin-top:5px;"> 
					<div  class="leftLable col-md-1">发送日期:</div>
					<div  class="right col-md-2"> 
						<div style="width: 150px" class="input-group date" id="planSStartDateDiv" data-date-format="yyyy-mm-dd">
							<input type="text"  id="sendTime" placeholder="开始日期" name="sendTime" class="form-control input-sm"/>
							<span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
						</div>
					</div>
					<div  class="right col-md-2"> 
						<div style="width: 150px" class="input-group date" id="planSEndDateDiv" data-date-format="yyyy-mm-dd">
							<input type="text"  id="sendTimeEnd" placeholder="结束日期" name="sendTimeEnd" class="form-control input-sm"/>
							<span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
						</div>
					</div>
					<div  class="right col-md-2"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-sm">查询</button>
						<button type="button" id="export" onClick="doExport();" class="btn btn-primary btn-sm">导出</button>
					</div>
				</div>
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
	        <h4 class="modal-title">交互</h4>
	      </div>
	      <div class="modal-body" style="max-height:400px;overflow-y:auto;">
	      		<p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	      	<center>
	        <button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        </center>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  

	<script type="text/javascript">
	$(function(){
		$('#planSStartDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#planSStartDateDiv').datepicker('hide');
		});
		$('#planSEndDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#planSEndDateDiv').datepicker('hide');
		});
		$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) { 
		    $('#lccName').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="180px;">' + item.lccName + '</td></tr></table>';
		            
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.helpCode+item.lccCode;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.lccName;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#lccName').result(function(event, item){ 
				if(item){
					if(item.lccCode != $("#lccCode").val()){
						$("#lccCode").val(item.lccCode);
					}
				}else{
					$("#lccCode").val("");
				}
		     }); 
		});
		
		var option = {
	        url : '${ctx}/msg/noReply/list',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : [ 'LCCID','PID','患者姓名','身份证','手机','所属组别','短信发送内容','发送时间','短信交互', '是否糖尿病'],
	        colModel : [ 
						 {name : 'lccId', index : 'lccId', align:'center',width:"80px"},
						 {name : 'patientId', index : 'patientId', align:'center'},
						 {name : 'patientName', index : 'patientName', align:'center'},
						 {name : 'idNumber', index : 'idNumber', align:'center'},
						 {name : 'mobile', index : 'mobile', align:'center'},
						 {name : 'bGroup', index : 'bGroup', align:'center',formatter : function(cellvalue, option, rowObjects) {
							 if(cellvalue=='01'){
								 return "非糖尿病实验组";
							 }else if(cellvalue=='02'){
								 return "非糖尿病对照组";
							 }else if(cellvalue=='11'){
								 return '糖尿病实验组';
							 }else if( cellvalue=='12' ){
								 return '糖尿病对照组';
							 }
							 return "";

							}
						 },
						 {name : 'msgName', index : 'msgName', align:'left'},
						 {name : 'sendTime', index : 'sendTime', formatter:priorityFullDateFormatter,align:'center'},
						 {name : 'showMsg', index : 'patientId', align:'center',formatter : function(cellvalue, option, rowObjects) {
	                    	 return "<button onclick=\"showAllMsg('"+rowObjects.lccId+"','"+rowObjects.patientId+"')\">查看</button>";
						 }},
						 {name : 'isDiabetes', index : 'isDiabetes', align:'center',formatter : function(cellvalue, option, rowObjects) {
							 if(cellvalue=='1'){
								 return "是";
							 }else if(cellvalue=='2'){
								 return "否";
							 }
							 return "";
							}
						 }
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        autowidth : true,  
	        pager : '#pager',  
	        sortname : 'LCCID',
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
		$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
		jqgridResponsive("grid",false); 
		
		$("#btnQuery").click(multipleSearch);
		
		$('#cancel').click(function(){
		  $('#dialog-confirm').modal('hide');
		}); 
	});
	
	function showAllMsg(lccCode,patientId){
		if(patientId==null || patientId==''){
			showResult(false,"未关联到人员");
			return ;
		}
		var timebak = new Date().getTime();
		openDialog("${ctx}/msg/reply/openmodal/showAllMsg?time="+timebak+"&lccCode="+lccCode+"&patientId="+patientId,"dialog-confirm");
	}
	
	function doExport() {
		$("#searchForm").submit();
	}
	
	//弹出对话框
	function openDialog(url,modalId){
	    $("#"+modalId ).modal({
	         backdrop: 'static'
	    });
	    //使用此方法防止js缓存不加载
	    if(url != null){
	    	$("#"+modalId+" p" ).html("");
	    	$("#"+modalId+" p" ).load(url);
	    }
	}
	
	function showResult(result,message,id){
		  if(id==null)id="message";
		  $("#"+id+"SpanId").text(message);

		  if(result){
		      $("#"+id).addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
		  }else{
		      $("#"+id).addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
		  }
		  window.setTimeout(function() {
		      $("#"+id).slideToggle(1000);
		  }, 2000);
	}
	</script>


</body>
</html>