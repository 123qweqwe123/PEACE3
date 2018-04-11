<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<title>项目管理</title>
<style type="text/css">
.ztree li span.button.add {
	margin-left: 2px;
	margin-right: -1px;
	background-position: -144px 0;
	vertical-align: top;
	*vertical-align: middle
}
</style>
</head>
<body>

	<ul class="breadcrumb">
		<li class="active">项目管理</li>
		<li class="active">立项管理</li>
	</ul>
	
	<div id="select">
        <div class="select-main">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>项目名称:</label></li>
                         <li> 
                         <input type="text" id="projectName" name="projectName" class="form-control input-sm"/>
                         </li>
                        <li> <button type="button" onclick="searchProject();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                    <ol>
                       
                    </ol>
                </fieldset>
        </div>
    </div>
	
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
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">项目立项</h4>
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
	        <p>是否确定删除？</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<script type="text/javascript">
		$(function() {

			var option = {
				url : '${ctx}/pro/promgt/list',
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '', '项目名称', '简称', '项目负责人', '开始时间', '结束时间', '', '',
						'项目描述', '是否启动','' ],
				colModel : [
						{
							name : 'projectId',
							index : 'projectId',
							hidden : true
						},
						{
							name : 'projectName',
							index : 'projectName',
							align : 'left'
						},
						{
							name : 'shortName',
							index : 'shortname',
							align : 'left'
						}, {
							name : 'leader',
							index : 'leader'
						},
						{
							name : 'beginTime',
							index : 'beginTime',
							align : 'left',
							formatter:yymmddFormatter
						},
						{
							name : 'endTime',
							index : 'endTime',
							formatter:yymmddFormatter
						},
						{
							name : 'isAutoStart',
							index : 'IS_AUTO_START',
							hidden : true
						},
						{
							name : 'isAutoEnd',
							index : 'IS_AUTO_END',
							hidden : true
						}, {
							name : 'workload',
							index : 'workload'
						},
						{
							name : 'status',
							index : 'status',
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (1 == cellvalue) {
									result = '<span class="label label-success">是</span>'
								} else {
									result = '<span class="label label-default">否</span>';
								}
								return result;
							}
						},{
							name : 'isStarted',
							hidden : true
						} ],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'beginTime',
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
					id : "projectId"
				}
			};
			$("#grid").jqGrid(option);
			$("#grid").jqGrid('navGrid', '#pager', {
				edit : false,
				add : false,
				del : false,
				search : false,
				position : 'right'
			}).navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
			.navButtonAdd('#pager',{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
			.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"})
			.navButtonAdd('#pager',{caption:"启动",buttonicon:"ui-icon-play",onClickButton: function(){toStart('start')},position:"last"})
			.navButtonAdd('#pager',{caption:"停止",buttonicon:"ui-icon-stop",onClickButton: function(){toStart('stop')},position:"last"});
			//自适应
			jqgridResponsive("grid", false);
			
			$('#cancel').click(function(){
				$('#dialog-confirm').modal('hide');
			});	
			$('#cancel2').click(function(){
				$('#dialog-delete').modal('hide');
			});	

		});
	
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/promgt/openmodaladdprojectinput?time="+timebak);
		}
		function toModify(){
			var projectId = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(projectId)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			var rowData = $("#grid").jqGrid('getRowData',projectId);
			if(rowData.status.indexOf('是')>0){
				openError('项目已启动不能再做修改！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/promgt/openmodaladdprojectinput?projectId="+projectId+"&time="+timebak);
		}
		$('#do_save').click(function(){
			var myform = $("#dialog-confirm").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			$("#do_save").attr("disabled",true);
			var myform = $("#dialog-confirm").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			if(data.projectId ==""){addDate(data);}
			else{updateDate(data);}
		});
		
		function addDate(data){
			$.post("${ctx}/pro/promgt/addProject",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "新增失败！";
				if(result.success){
					message ='新增成功!';
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		function updateDate(data){
			$.post("${ctx}/pro/promgt/addProject",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "更新失败！";
				if(result.success){
					message = "更新成功！";
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		function searchProject() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var projectName =$("#projectName").val();
		      if(projectName && ''!=projectName){
		          mydata.projectName = projectName;
		      }else{
		          delete postData.projectName;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
		 function toDelete(){
			 var projectId = $("#grid").jqGrid('getGridParam','selrow');
			 if($.isEmptyObject(projectId)) {
			 	openError('请选择要删除的记录！',2000);
			 	return;
			 }
			 var rowData = $("#grid").jqGrid('getRowData', projectId);
			 if(rowData.status.indexOf('是')>0 || rowData.isStarted == '1'){
				 openError('此项目已经启动过，不可以删除！',2000);
				 return;
			 }
			 $("#do_delete").attr("disabled",false);
			 $("#dialog-delete").modal({
			 	 keyboard: false
			 });
		 }
		$('#do_delete').click(function(){
			
			var projectId = $("#grid").jqGrid('getGridParam','selrow');
			 if($.isEmptyObject(projectId)) {
			 	openError('请选择要删除的记录！',2000);
			 	return;
			 }
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/pro/promgt/delete", 
					{projectId: projectId },
        		function(data){
					$("#grid").trigger("reloadGrid");
					$('#dialog-delete').modal('hide');
					var message = "删除失败！";
        			if(data.success){
        				message = "删除成功！";
        			}else{
        				message = data.msg+"!";
        			}
        			showResult(data.success,message);
        		}, 
        		"json");
		});
		
		function toStart(type){
			var projectId = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(projectId)) {
				if(type=='start') openError('请选择要启动的项目！',2000);
				if(type == 'stop') openError('请选择要结束的项目！', 2000);
				return;
			}
			var rowData = $("#grid").jqGrid('getRowData',projectId);
			if(rowData.status.indexOf('是')>0 && type=='start'){
				openError('项目已启动！',2000);
				return;
			}
			if(rowData.status.indexOf('否')>0 && type=='stop'){
				openError('项目已结束！',2000);
				return;
			}
			var data = {};
			data.type = type;
			data.projectId = projectId;
			doStartStop(data);
		}

		function doStartStop(data){
			$.post("${ctx}/pro/promgt/doStartStop", 
					{projectId: data.projectId, type: data.type },
        		function(result){
					$('#dialog-delete').modal('hide');
					var message = "操作失败！";
        			if(result.success){
        				$("#grid").trigger("reloadGrid");
        				message = "操作成功！";
        			}
        			showResult(result.success,message);
        		}, 
        		"json");
		}
		//弹出对话框
		function openDialog(url,data){
			$( "#dialog-confirm" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm p" ).load(url);
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
	    function openError(message,delay){
	    	$('#alert').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert').slideUp("slow");
	    	}, delay);
	    }
	</script>
</body>
</html>