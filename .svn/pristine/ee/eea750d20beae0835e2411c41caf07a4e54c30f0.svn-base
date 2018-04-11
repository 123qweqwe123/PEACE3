<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>条码异常数据监测</title>
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
	  <li class="active">质量管理</li><li class="active" >异常数据监测</li><li class="active">条码异常数据监测</li>
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
	

	<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">上报</h4>
	      </div>
	      <div class="modal-body" style="height:400px;">
	      		<p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000" onclick="reportSubmit();">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  

	<script type="text/javascript">
	$(function(){
		var option = {
	        url : '${ctx}/quality/dataAbnormal/codeAbnormal/list',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : [ '异常对象','异常类型','异常情况说明','上报状态'],
	        colModel : [ 
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
						 	}, align:'left',sortable: false}
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
		$("#grid").jqGrid(option); 
		$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})
		.navButtonAdd('#pager',{caption:"上报",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){toReport()},position:"last"});
		jqgridResponsive("grid",false); 
		
		$("#btnQuery").click(multipleSearch);
		
		$('#cancel').click(function(){
		  $('#dialog-confirm').modal('hide');
		}); 
	});
	
	function toReport(){
		var _id = $("#grid").jqGrid('getGridParam','selrow');
	    if($.isEmptyObject(_id)) { 
	        openError('请选择一条数据',2000);
	        return;
	    }
	    var timebak = new Date().getTime();
		openDialog("${ctx}/quality/dataAbnormal/codeAbnormal/openmodalToReport?id="+_id+"&time="+timebak,"dialog-confirm");
		$("#do_save").attr("disabled",false);
	}
	
	function reportSubmit(){
		if($.trim($("#reportInfo").val())==""){
			openError("上报信息不能为空",2000,$("#alertForUpload"));
			return;
		}
		if(!confirm('确认提交吗？')){
			return;
		}
		var form = $("#subForm")[0];
		
		var locations = "";
		$("#subForm").find("input[type='file']").each(function(){
			if($(this).val()=="")return true;
			if(locations.length>0)locations+=",";
			locations += $(this).attr("id").substr(4);
		});
		$("#fileLocations").val(locations);
		$("#changeIds").val(changeIds);
		
		form.action="${ctx}/quality/dataAbnormal/codeAbnormal/report";

		$("#do_save").attr("disabled",true);
		
		var url="${ctx}/quality/dataAbnormal/codeAbnormal/report";
		if(locations==""){
			url = "${ctx}/quality/dataAbnormal/codeAbnormal/reportNoFile";
		}
		$("#subForm").ajaxSubmit({
			url:url, 
           	type:"POST",  
           	dataType:"json",
           	success:function(data){  
               	if(data.success){
               	 	$('#dialog-confirm').modal('hide');
               	 	showResult(data.success,"上报成功");
               		$("#grid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
               	}else{
               		openError(data.msg,2000,$("#alertForUpload"));
               		$("#do_save").attr("disabled",false);
               	}
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