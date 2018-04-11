<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div id="alertForUpload" class="alert alert-danger" hidden>
	<strong>Warning!</strong>
</div>

	<div id="JqGrid">
		<table id="gridexaftp"></table>
		<div id="pagerexaftp"></div>
	</div>
	
	<script type="text/javascript">
$(function(){
	var optionexamineftp = {
		url : '${ctx}/quality/examine/examineFtpNameList',
		datatype : 'json',
		mtype : 'POST',
		colNames : [ '','考核表名称'],
		colModel : [
				{
					name : 'id',
					index : 'id',
					hidden : true
				},{
					name : 'fileName',
					index : 'file_name'
				}],
		rowNum : 15,
		rowList : [ 15, 30, 50,100,150,500 ],
		height : "100%",
		width : 570,
		pager : '#pagerexaftp',
		sortname : 'file_name',
		altRows : true,
		hidegrid : false,
		viewrecords : true,
		recordpos : 'left',
		sortorder : "desc",
		emptyrecords : "没有可显示记录",
		loadonce : false,
		//multiselect : true,
		postData:{examineId:'${examineId}'},
		loadComplete : function() {
		},
		ondblClickRow:function(rowid,iRow,iCol,e){
			showFileName(rowid);
		},
		jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false,
			cell : "cell",
			id : "id"
		}
	};
	$("#gridexaftp").jqGrid(optionexamineftp);
	$("#gridexaftp").jqGrid('navGrid', '#pagerexaftp', {
		edit : false,
		add : false,
		del : false,
		search : false,
		position : 'right'
	});
	//自适应
	jqgridResponsive("gridexaftp", false);
});
</script>
