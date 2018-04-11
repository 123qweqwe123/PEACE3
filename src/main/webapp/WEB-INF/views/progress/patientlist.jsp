<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title></title>
<style>
.txt {
	font-size: 14px;
	font-weight: bold;
}

.percentTxt {
	font-size: 9px;
	font-weight: normal;
	color: #333;
}

.upArrow {
	padding: 0px 20px 0px 0px;
	background-image: url('${ctx}/views/progress/assets/arrowUp1.png');
	background-repeat: no-repeat;
	background-position: 55px 10px;
}

.downArrow {
	padding: 0px 20px 0px 0px;
	background-image: url('${ctx}/views/progress/assets/arrowDown1.png');
	background-repeat: no-repeat;
	background-position: 55px 10px;
}
</style>
</head>
<body>
	<div id="navbar">
		<ol class="breadcrumb">
			<li>进度管理</li>
			<li class="active"><c:if test="${type=='2' }">初筛</c:if>
				<c:if test="${type=='3' }">高危</c:if>
				<c:if test="${type=='4' }">预约随访</c:if>列表-${lccName}</li>
		</ol>
	</div>


	<div id="main-content">
		<input type="hidden" id="lccName" value="${lccName }"> <input
			type="hidden" id="type" value="${type }">
		<div id="jqgrid">
			<table id="grid"></table>
			<div id="pager"></div>
		</div>
		<br style="clear: both;" />
	</div>
	<script type="text/javascript">
		$(function() {

			var option = {
				url : '${ctx}/progress/getpatientlist',
				postData :{lccName:$('#lccName').val(),type:$('#type').val()},
				datatype : 'json',
				mtype : 'POST',
				colNames : [ 'pid', 'LCC', '患者姓名', '性别', '出生年月', '电话', '手机',
						'是否高危', '是否预约随访' ],
				colModel : [
						{
							name : 'patientId'
						},
						{
							name : 'lccName'
						},
						{
							name : 'patientName',
							sortable : false,
							align : 'center'
						},
						{
							name : 'sex',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if ('1' == cellvalue) {
									result = '男';
								} else {
									result = '女';
								}
								return result;
							}
						},
						{
							name : 'birthday',
							sortable : false,
							align : 'center',
							formatter : yymmddFormatter
						},
						{
							name : 'phone',
							sortable : false,
							align : 'center'
						},
						{
							name : 'mobile',
							sortable : false,
							align : 'center'
						},
						{
							name : 'riskCode',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (cellvalue!=null) {
									result = '<span class="label label-success">是</span>'
								} else {
									result = '<span class="label label-danger">否</span>';
								}
								return result;
							}
						},
						{
							name : 'isFollowview',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if ('1' == cellvalue) {
									result = '<span class="label label-success">是</span>'
								} else {
									result = '<span class="label label-danger">否</span>';
								}
								return result;
							}
						} ],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'patient_Id',
				altRows : true,
				hidegrid : false,
				viewrecords : true,
				recordpos : 'left',
				sortorder : "asc",
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
	</script>
</body>


</html>