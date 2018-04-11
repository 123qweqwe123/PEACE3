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

	<form class="well bs-bdcor form-inline" id="searchForm"> 
		<table class="table table-bordered" > 
		    <tr class="active">
		        <td >文件名称</td>
		        <td >文档类别</td>
		        <td colspan="1"><font class="advanced" >操作</font></td>
		    </tr>  
		    <tr>
		    <td  class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
	            <input type="text" id="adjunctName" name="adjunctName" class="form-control input-sm" placeholder="请输入文件名称"/>    
		    </td>  
		    <td  class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
	            <select id="type" name="type" class="form-control input-sm">
					<option value="-1">请选择类别</option>
					<c:forEach items="${typeList }" var="type">
						<option value="${type.typeCode }">${type.typeName }</option>
					</c:forEach>
				</select>
		     </td>
		     <td colspan="1" class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">    
	            <button type="button" id="btnQuery" class="simpled btn btn-sm btn-primary" onclick="searchAdjunct()">查询</button>
	            <button type="reset" id="reset_btn" class="simpled btn btn-sm btn-primary" >重置</button>
		     </td>   
		    </tr>
		</table>    
	</form>
	
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
						tabindex="1000">上传</button>
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
					colNames : [ '','', '文件名称','文档类别', '上传时间', '下载次数',''],
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
				}).navButtonAdd('#pagerDocument',{caption:"上传",buttonicon:"ui-icon-plus",onClickButton: function(){uploadDocument()},position:"last"})
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
				openError('请选择要删除的文档！',2000,$("#alert"));
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
				openError('请选择要删除的文档！',2000,$("#alert"));
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
			$("#do_save").attr("disabled",true);
			//$("#loading").show();
			
			
			$('#uploadify').uploadify('upload','*');
			
//			$("#adjunctForm").ajaxSubmit({
//				url:"${ctx}/pro/filemgt/uploadDocument", 
//	           	type:"POST",  
//	           	dataType:"json",
//	           	success:function(data){  
//	               	if(data.success){
//	               		$('#dialog-confirm').modal('hide');
//	               		
//	               		$("#documentgrid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
//	               	}else{
//	               		//$("#loading").hide();
//	               		//openError(data.msg,2000,$("#alertForUpload"));
//	               	}
//	             }
//			});
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