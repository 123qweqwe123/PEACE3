<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>条码异常数据处理</title>
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
	  <li class="active">质量管理</li><li class="active" >异常数据监测</li><li class="active">条码异常数据处理</li>
	</ul>
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row" style="margin-top:5px;"> 
					<div  class="leftLable col-md-1">异常对象:</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientCode" id="patientCode" value='' placeholder=""/>
					</div>
					
					<div  class="leftLable col-md-1">异常类型:</div> 
					<div  class="right col-md-4" style="width:27%"> 
						<select class="form-control input-sm"  name="errorType" id="errorType">
							<option value=""></option>
							<option value="1">初筛码找不到花名册</option>
							<option value="2">初筛码与花名册绑定重复</option>
							<option value="3">初筛码不符合规则</option>
							<option value="4">高危码未绑定花名册</option>
							<option value="5">对应的初筛码已在花名册中绑定其他的高危码</option>
							<option value="6">高危码已在花名册中并绑定其他初筛码</option>
							<option value="7">高危码不符合规则</option>
							<option value="9">初筛码需要绑定不同的花名册</option>
							<option value="10">初筛码未绑定花名册</option>
							<option value="11">高危码未绑定花名册</option>
						</select>
					</div> 
					
					<div  class="leftLable col-md-1">上报状态:</div> 
					<div  class="right col-md-1"> 
						<select class="form-control input-sm"  name="isReport" id="isReport" >
							<option value=""></option>
							<option value="1">已上报</option>
							<option value="2">未上报</option>
						</select>
					</div> 
					
					<div  class="right col-md-2"> 
						<button type="button" id="btnQuery" style="width:100px" class="btn btn-primary btn-align-right btn-sm">查询</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div id="alert" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div> 
	
	<div id="message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="messageSpanId"></span>
	</div>
	
	<div id="jqgrid"">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	
	<div id='dialog-showReport' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">上报说明</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel1' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  
	
	<div id='dialog-handleInfo' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">处理说明</h4>
	      </div>
	      <div class="modal-body">
	      		<div class="row">
					<label class="col-md-3  control-label" for="name">处理人员:</label>
					<div class="col-md-9" id="dialog-handleInfo_handleBy">
					</div>
				</div>
	      		
	      		<div class="row">
					<label class="col-md-3  control-label" for="name">处理时间:</label>
					<div class="col-md-9" id="dialog-handleInfo_handleDate">
					</div>
				</div>
	      		
				<div class="row">
					<input type="hidden" id="qcId"/>
					<label class="col-md-3  control-label" for="name">处理类型:</label>
					<div class="col-md-9">
						<p id="dialog-handleInfo_handleType"></p>
					</div>
				</div>
	
				<div class="row">
					<label class="col-md-3  control-label" for="name">处理说明:</label>
					<div class="col-md-8" id="dialog-handleInfo_handleInfo">
					</div>
				</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  
	
 	<div id='dialog-addHandle' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">处理记录补充</h4>
	      </div>
	      <div class="modal-body">
	      		<div id="alertForHandle" class="alert alert-danger" hidden>
				  <strong>Warning!</strong>
				</div>
				<div class="row">
					<input type="hidden" id="todoId"/>
					<input type="hidden" id="dialog-addHandle_type"/>
					<label class="col-md-3  control-label" for="name">补充说明记录:</label>
					<div class="col-md-9" id="dialog-addHandle_prev">
						
					</div>
				</div>
	
				<div class="row">
					<label class="col-md-3  control-label" for="name">补充说明:</label>
					<div class="col-md-9">
						 <textarea rows="8" cols="50" style="overflow-y:auto;resize:none" id="dialog-addHandle_addHandleInfo"></textarea> 
					</div>
				</div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel3' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000" onclick="addHandle()">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  
	
	<div id='dialog-showInfoAndTodoList' class="modal fade">
	  <div class="modal-dialog" style="width:940px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">查看详情</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel4' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal --> 
	
	<form id="showQnForm" method="post" target="_blank">
		<input type="hidden" name="patientId" id="patientIdHidden"/>
		<input type="hidden" name="qnVersion" id="qnVersionHidden"/>
	</form>

	<script type="text/javascript">
	$(function(){
		var option = {
	        url : '${ctx}/quality/dataAbnormal/codeAbnormal/errorHandleHisList',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : ['类型', '异常对象','异常类型','异常情况说明','上报状态','操作'],
	        colModel : [ 
						 {name : 'type', index : 'type', hidden:true},
						 {name : 'patientCode', index : 'patientCode', align:'left',sortable: false },
	                     {name : 'errorType', index : 'errorType',formatter : function(cellvalue, option, rowObjects) {
	                    	 if(cellvalue==1){
	                    		 return "初筛码找不到花名册";
	                    	 }
	                    	 if(cellvalue==2){
	                    		 return "初筛码与花名册绑定重复";
	                    	 }
	                    	 if(cellvalue==3){
	                    		 return "初筛码不符合规则";
	                    	 }
	                    	 if(cellvalue==4){
	                    		 return "高危码未绑定花名册";
	                    	 }
	                    	 if(cellvalue==5){
	                    		 return "对应的初筛码已在花名册中绑定其他的高危码";
	                    	 }
	                    	 if(cellvalue==6){
	                    		 return "高危码已在花名册中并绑定其他初筛码";
	                    	 }
	                    	 if(cellvalue==7){
	                    		 return "高危码不符合规则";
	                    	 }
	                    	 if(cellvalue==9){
	                    		 return "初筛码需要绑定不同的花名册";
	                    	 }
	                    	 if(cellvalue==10){
	                    		 return "初筛码未绑定花名册";
	                    	 }
	                    	 if(cellvalue==10){
	                    		 return "初筛码未绑定花名册";
	                    	 }
	                    	 return "";
							}, align:'left',sortable: false },
	                     {name : 'errorMsg', index : 'errorMsg', align:'left',sortable: false},
						 {name : 'isReport', index : 'isReport',formatter : function(cellvalue, option, rowObjects) {
							 if(cellvalue==1){
	                    		 return "已上报";
	                    	 }
							 return "未上报";
						 	}, align:'left',sortable: false},
					 	{name : '', index : 'operate',sortable: false,formatter : function(cellvalue, option, rowObjects) {
					 		if(rowObjects.type=='UQS'){
					 			 if(rowObjects.errorType==1){
					 				return "<button onclick=\"showInfoAndTodoList('"+rowObjects.id+"')\">查看详情</button>";
					 			 }else if(rowObjects.errorType==6 || rowObjects.errorType==7){
					 				return "<button onclick=\"showQn('"+rowObjects.patientCode+"','"+rowObjects.version+"')\">查看问卷</button>";
					 			 }
					 		 }
		                     return "";
							}, align:'center',sortable: false },
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        autowidth : true,  
	        pager : '#pager',  
	        sortname : 'patientCode',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "ASC",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: false
	 	};  
		
		$("#btnQuery").click(multipleSearch);
		
			$("#grid").jqGrid(option); 
			$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})
			.navButtonAdd('#pager',{caption:"查看上报说明",buttonicon:"ui-icon-info",onClickButton: function(){showReport()},position:"last"})
			.navButtonAdd('#pager',{caption:"查看处理记录",buttonicon:"ui-icon-info",onClickButton: function(){showHandle()},position:"last"})
			.navButtonAdd('#pager',{caption:"处理记录补充",buttonicon:"ui-icon-pencil",onClickButton: function(){toAddHandleInfo()},position:"last"});
			jqgridResponsive("grid",false); 
		
		$('#cancel1').click(function(){
			$('#dialog-showReport').modal('hide');
		}); 
		
		$('#cancel2').click(function(){
			$('#dialog-handleInfo').modal('hide');
		}); 
		
		$('#cancel3').click(function(){
			$('#dialog-addHandle').modal('hide');
		}); 
		
		$('#cancel4').click(function(){
			$('#dialog-showInfoAndTodoList').modal('hide');
		}); 
	});
	
	function showInfoAndTodoList(todoId){
	    var timebak = new Date().getTime();
		openDialog("${ctx}/quality/dataAbnormal/uqsAbnormal/openmodalShowInfoAndTodoList?todoId="+todoId+"&time="+timebak,"dialog-showInfoAndTodoList");    
	}
	
	function showQn(patientId,qnVersion){
		$("#patientIdHidden").val(patientId);
		$("#qnVersionHidden").val(qnVersion);
		var form =$("#showQnForm")[0];
		form.action="${ctx}/quality/previewqn/showQnDetail";
		form.submit();
	}
	
	function showReport(){
		var _id = $("#grid").jqGrid('getGridParam','selrow');
	    if($.isEmptyObject(_id)) { 
	        openError('请选择一条数据',2000);
	        return;
	    }
	    var oneData = $("#grid").jqGrid('getRowData',_id); 
	    if(oneData.isReport != '已上报'){
	    	 openError('该数据未上报',2000);
		     return;
	    }
	    var timebak = new Date().getTime();
	    openDialog("${ctx}/quality/dataAbnormal/codeAbnormal/openmodalShowReport?id="+_id+"&type="+oneData.type+"&time="+timebak,"dialog-showReport");    
	}
	
	function showHandle(){
		var _id = $("#grid").jqGrid('getGridParam','selrow');
	    if($.isEmptyObject(_id)) { 
	        openError('请选择一条数据',2000);
	        return;
	    }
	    var oneData = $("#grid").jqGrid('getRowData',_id); 
	    $.ajaxSetup({
	        async: false
	    });
	    $.post("${ctx}/quality/dataAbnormal/codeAbnormal/getFirstDoMap", 
           	{todoId:_id,type:oneData.type},
           	function(data){  
               	$("#dialog-handleInfo_handleBy").text(data.handleBy);
         	    $("#dialog-handleInfo_handleDate").text(data.handleDate);
         	    $("#dialog-handleInfo_handleType").text(data.handleType=='1'?"已处理":"不需要处理");
         	    $("#dialog-handleInfo_handleInfo").html(data.handleInfo);
           	},"Json"
		);
	    
		$( "#dialog-handleInfo" ).modal({
	         backdrop: 'static'
	    });   
	}
	
	function toAddHandleInfo(){
		var _id = $("#grid").jqGrid('getGridParam','selrow');
	    if($.isEmptyObject(_id)) { 
	        openError('请选择一条数据',2000);
	        return;
	    }
	    var oneData = $("#grid").jqGrid('getRowData',_id); 
	    $.ajaxSetup({
	        async: false
	    });
	    var handleInfo = "";
	    $.post("${ctx}/quality/dataAbnormal/codeAbnormal/getDoMapList", 
           	{todoId:_id,type:oneData.type},
           	function(data){  
               	for(var i in data){
               		if(handleInfo.length>0){
               			handleInfo += "<br/>";
               		}
               		handleInfo += data[i].handleOrder+"."+data[i].handleDate+" "+data[i].handleBy+"<br/>"+data[i].handleInfo;
               	}
           	},"Json"
		);
	    
	    $("#dialog-addHandle_prev").html(handleInfo);
	    $("#dialog-addHandle_addHandleInfo").val("");
	    $("#dialog-addHandle_type").val(oneData.type);
	    $("#todoId").val(_id);
	    openDialog(null,"dialog-addHandle");    
	}
	
	function addHandle(){
		if(!confirm('确认提交吗？')){
			return;
		}
		var data = {};
		data['todoId'] = $("#todoId").val();
		data['handleType'] = -1;
		data['handleInfo'] = $("#dialog-addHandle_addHandleInfo").val();
		data['type'] = $("#dialog-addHandle_type").val();
		
		 $.post("${ctx}/quality/dataAbnormal/codeAbnormal/errorHandle",data,function(result){
		      if(result.success){
		    	  $('#dialog-addHandle').modal('hide');
		          showResult(result.success,"补充记录成功");
		          $("#grid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
		      }else{
		    	  openError(result.msg,2000,$("#alertForHandle"));
		      }
		  },'json');
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
	
	function openError(message,delay,msgId){
		if(msgId == null)msgId="#alert";
    	$(msgId).show().find('strong').text(message);
    	window.setTimeout(function() {
    		$(msgId).slideUp("slow");
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
	
	</script>


</body>
</html>