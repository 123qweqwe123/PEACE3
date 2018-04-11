<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>项目管理</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">系统管理</li>
		<li class="active">条码修改历史</li>
	</ul>

	<div id="select">
        <div class="select-main">
              <!-- <form action="" method="post" class="well-work bs-adp form-inline"> -->
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>身份证号:</label></li>
                         <li> 
                         <input type="text" id="patient_id" name="patient_id" class="form-control input-sm" placeholder="请输入条码名称"/>
                         </li>
                                                 
                        <li> <button type="button" onclick="searchAdjunct();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                    <ol>
                       
                    </ol>
                </fieldset>
              
        </div>
    </div>
    
    <div id="alert" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
	<div id="documentJqGrid">
		<table id="documentgrid"></table>
		<div id="pagerDocument"></div>
	</div>

	<div id='dialog-confirm' class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">上传文件</h4>
				</div>
				<div class="modal-body">
					<p>加载中……</p>
				</div>
				<div class="modal-footer">
					<button type="button" id='cancel' class="btn btn-default btn-sm"
						tabindex="1001">取消</button>
					<button type="button" id='do_save' class="btn btn-primary btn-sm"
						tabindex="1000">提交</button>
				</div>
			</div>
		</div>
	</div>
    	<div id='dialog-delete' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">删除</h4>
	      </div>
	      <div class="modal-body">
	        <p>是否删除？</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<script type="text/javascript">
		$(function(){
			//取消按钮操作
			$('#cancel').click(function(){
				$('#dialog-confirm').modal('hide');
			});	
			$('#cancel2').click(function(){
				$('#dialog-delete').modal('hide');
			});
			//document
			var optionDocument = {
					url : '${ctx}/sys/drop/listPatLog',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '身份证号','修改前初筛条码','初筛条码修改类型','修改后的初筛条码','修改前高危条码','高危条码修改类型','修改后的高危条码','操作人', '操作时间'],
					colModel : [
							{
								name : 'idNumber',
								index : 'idNumber',
								width:70
							},
							{
								name : 'patientIds',
								index : 'patientIds',
								width:50
							},
							{
								name : 'patientIdType',
								index : 'patientIdType',
								align : 'left',
								formatter:function(v , o , e ){
									if ( v == '1'){
										return '删除';
									}else{
										if ( e['newPatientId'] && e['newPatientId'].length > 0 )
											return '修改';
										else
											return '不变';
									}
								},
								width:50
							},
							{
								name : 'newPatientId',
								index : 'newPatientId',
								align : 'left',
								width:50
							},
							{
								name : 'riskCodes',
								index : 'riskCodes',
								align : 'left',
								width:50
							},
							{
								name : 'riskCodeType',
								index : 'riskCodeType',
								align : 'left',
								formatter:function(v , o , e ){
									if ( v == '1'){
										return '删除';
									}else{
										if ( e['newRiskCode'] && e['newRiskCode'].length > 0 )
											return '修改';
										else
											return '不变';
									}
								},
								width:50
							},
							{
								name : 'newRiskCode',
								index : 'newRiskCode',
								align : 'left',
								width:50
							},
							{
								name : 'createBy',
								index : 'createBy',
								width:50
							},
							{
								name : 'createDate',
								index : 'createDate',
								formatter : datatimeformat,
								width:80
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pagerDocument',
					sortname : 'patient_Id',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "desc",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					//multiselect : true,
					loadComplete : function() {
					},
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
				$("#documentgrid").jqGrid(optionDocument);
				/* $("#documentgrid").jqGrid('navGrid', '#pagerDocument', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				}).navButtonAdd('#pagerDocument',{caption:"上传",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){uploadDocument()},position:"last"})
					.navButtonAdd('#pagerDocument',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){deleteDocument()},position:"last"}); */
				//自适应
				jqgridResponsive("documentgrid", false);
		});
		function uploadDocument(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/filemgt/openmodaladdjunctinput?time="+timebak);
		}
		//弹出删除对话框
		function deleteDocument(){
			var documentId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(documentId)) {
				openError('请选择要删除的文件！',2000,$("#alert"));
				return;
			}
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		$('#do_delete').click(function(){
			var documentId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(documentId)) {
				openError('请选择要删除的文件！',2000,$("#alert"));
				return;
			}
			var rowData = $("#documentgrid").jqGrid('getRowData',documentId);
			$.ajax({
	             type: "POST",
	             url: "${ctx}/pro/filemgt/deleteDocument",
	             data: {adjunctId:documentId,adjunctName:rowData.adjunctName,path:rowData.path},
	             dataType: "json",
	             success: function(data){
	            	 $('#dialog-delete').modal('hide');
	                if(data.success){
	                	$("#documentgrid").setGridParam({postData:{page: 1, projectId: $("#projectId").val()}}).trigger("reloadGrid");
	                }else{
	                	openError("删除失败！",2000,$("#alert"));
	                }
	             }
	         });
		});
		//弹出对话框
		function openDialog(url,data){
			$( "#dialog-confirm" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm p" ).load(url);
		}
		$('#do_save').click(function(){
			var myform = $("#dialog-confirm").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			
			if($("#puf").val()==""){
				openError("请选择要上传的文件！",2000,$("#alertForUpload"));
				return;
			}
			
			$("#do_save").attr("disabled",true);
			$("#loading").show();
			$("#adjunctForm").ajaxSubmit({
				url:"${ctx}/pro/filemgt/uploadDocument", 
	           	type:"POST",  
	           	dataType:"json",
	           	success:function(data){  
	               	if(data.success){
	               		$('#dialog-confirm').modal('hide');
	               		
	               		$("#documentgrid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
	               	}else{
	               		$("#loading").hide();
	               		openError(data.msg,2000,$("#alertForUpload"));
	               		$("#do_save").attr("disabled",false);
	               	}
	             }
			});
		});
		
		function searchAdjunct(){
		      var postData = $("#documentgrid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      if ($("#patient_id").val() && $("#patient_id").val() != '')
		    	  mydata.targetId = $("#patient_id").val();
		      else
		    	  delete postData.targetId;
		      
		     $.extend(postData,mydata);
		     //alert(postData.targetId + '-' + mydata.targetId);
		     $("#documentgrid").jqGrid("setGridParam", {
		         search:true
		     }).trigger("reloadGrid", [{page:1}]);
		}
	    function openError(message,delay,msgId){
	    	$(msgId).show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$(msgId).slideUp("slow");
	    	}, delay);
	    }
	    
	    function datatimeformat(v , o , e ){
			var date = new Date();
			date.setTime( parseInt(v));
			return DateFormat.format(date, 'yyyy-MM-dd hh:mm:ss');
		}
	</script>
</body>
</html>