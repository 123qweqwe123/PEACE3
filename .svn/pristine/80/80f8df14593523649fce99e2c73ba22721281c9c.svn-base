<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<title>项目管理</title>
</head>
<body>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	<form class="well bs-adp form-inline" id="searchForm">
	       	项目名称:
	        <input type="text" id="projectName" name="projectName" class="form-control input-sm"/>
	    &nbsp;&nbsp;&nbsp;&nbsp; 
	    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm" onclick="searchProject()">查询</button>
	</form>
	<div id="jqgrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>

	<script type="text/javascript">
		$(function() {

			var option = {
			    width : 300,
				url : '${ctx}/pro/promgt/list',
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '', '项目名称', '开始时间', '结束时间', '是否有效' ],
				colModel : [
						{
							name : 'projectId',
							index : 'projectId',
							hidden : true
						},
						{
							name : 'projectName',
							index : 'projectName',
							align : 'left',
							width : 140,
							align : 'center'
						},
						{
							name : 'beginTime',
							index : 'beginTime',
							align : 'center',
							width : 140,
							formatter:yymmddFormatter
						},
						{
							name : 'endTime',
							index : 'endTime',
							align : 'center',
							width : 140,
							formatter:yymmddFormatter
						},
						{
							name : 'isDelete',
							index : 'isDelete',
							align : 'center',
							width : 140,
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (0 == cellvalue) {
									result = '<span class="label label-success">是</span>'
								} else {
									result = '<span class="label label-default">否</span>';
								}
								return result;
							}
						} ],
				pager : '#pager',
				rowNum : 5,
				rowList : [ 5, 8, 10 ],
				shrinkToFit:false,
				height : "100%",
				autowidth : true,
				sortname : 'projectName',
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
			});
			//自适应
			jqgridResponsive("grid", false);
			
		});
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
	</script>
</body>
</html>