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
		<li class="active">项目管理</li>
		<li class="active">文件管理</li>
	</ul>

	<div id="select">
        <div class="select-main">
              <form action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <!-- <li class="select-one"> <label form='name'>源文件名:</label></li>
                         <li> 
                         <input type="text" id="adjunctName" name="adjunctName" class="form-control input-sm" placeholder="请输入文件名称"/>
                         </li> -->
                         <li class="select-one"> <label form='name'>文件名:</label></li>
                         <li> 
                         <input type="text" id="fileDesc" name="fileDesc" class="form-control input-sm" placeholder="请输入文件名称"/>
                         </li>
                         <li class="select-one"> <label form='name'>文件类别:</label></li>
                         <li> 
			                <select id="type" name="type" class="form-control input-sm">
								<option value="-1">请选择类别</option>
								<c:forEach items="${typeList }" var="type">
									<option value="${type.typeCode }">${type.typeName }</option>
								</c:forEach>
							</select>
                         </li>
                        <li> <button type="button" onclick="searchAdjunct();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                    <ol>
                       
                    </ol>
                </fieldset>
              </form>
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
					url : '${ctx}/pro/filemgt/listAdjunct',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '','', '源文件名','文件名称','文件类别', '上传时间', '下载次数',''],
					colModel : [
							{
								name : 'adjunctId',
								index : 'adjunct_id',
								hidden : true
							},
							{
								name : 'projectId',
								index : 'project_id',
								hidden : true
							},
							{
								name : 'adjunctName',
								index : 'adjunct_name',
								align : 'left',
								hidden : true
							},{
								name : 'fileDesc',
								index : 'file_desc',
								align : 'left'
							},
							{
								name : 'typeName',
								index : 'type',
								align : 'left'
							},
							{
								name : 'creatTime',
								index : 'creatTime',
								align : 'left',
								formatter:yymmddFormatter
							},
							{
								name : 'downCount',
								index : 'down_count'
							},
							{
								name : 'path',
								index : 'path',
								hidden : true
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pagerDocument',
					sortname : 'adjunct_name',
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
						id : "adjunctId"
					}
				};
				$("#documentgrid").jqGrid(optionDocument);
				$("#documentgrid").jqGrid('navGrid', '#pagerDocument', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				}).navButtonAdd('#pagerDocument',{caption:"上传",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){uploadDocument()},position:"last"})
					.navButtonAdd('#pagerDocument',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){deleteDocument()},position:"last"});
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
	           		$("#documentgrid").trigger("reloadGrid");
	               	if(data.success){
	               		$('#dialog-confirm').modal('hide');
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
		      var adjunctName =$("#adjunctName").val();
		      var type = $("#type").val();
		      if(adjunctName && ''!=adjunctName){
		          mydata.adjunctName = adjunctName;
		      }else{
		          delete postData.adjunctName;
		      }
		      var fileDesc = $("#fileDesc").val();
		      if(fileDesc && ''!=fileDesc){
		          mydata.fileDesc = fileDesc;
		      }else{
		          delete postData.fileDesc;
		      }
		      if(type!='-1'){
		          mydata.type = type;
		      }else{
		          delete postData.type;
		      }
		     $.extend(postData,mydata);
		     $("#documentgrid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		}
	    function openError(message,delay,msgId){
	    	$(msgId).show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$(msgId).slideUp("slow");
	    	}, delay);
	    }
	</script>
</body>
</html>