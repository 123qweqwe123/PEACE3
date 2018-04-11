<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="dict" uri="http://com.bdcor.pip.web.common.tagsupport" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<input type="hidden" id="lccCode1" name="lccCode" value="${param.lccCode} ">
<div id="JqLunLiGrid">
	<table id="lunLiGrid"></table>
	<div id="lunLiPager"></div>
</div>
<script type="text/javascript">
var optionlcc = {
		url : '${ctx}/pro/lccmgt/LunliList?lccCode='+$("#lccCode1").val(),
		datatype : 'json',
		mtype : 'POST',
		colNames : [
				'id','所属省份','PID','医院名称','通过伦理类型','通过伦理日期','伦理到期时间','伦理批件代码','创建时间','创建人'
		             ],
		colModel : [
				{
					name : 'id',
					index : 'id',
					hidden:true
				},{
					name : 'areaName',
					index : 'areaName',
					align : 'center',
					width: 80
				},{
					name : 'lccCode',
					index : 'lccCode',
					align : 'center',
					width: 80
				},{
					name : 'lccName',
					index : 'lccName',
					align : 'center',
					width: 80
				},{
					name : 'ethicalType',
					index : 'ethicalType',
					align : 'center' ,
					formatter : function(cellvalue, option, rowObjects) {
						var result = '';
						if (1 == cellvalue) {
							result = '自主过伦理'
						} else {
							result = '同意中心伦理';
						}
						return result;
					},
					width: 100
				},{
					name : 'ethicalEffectiveDate',
					index : 'ethicalEffectiveDate',
					align : 'center',
					formatter:yymmddFormatter,
					width: 80
				},{
					name : 'ethicalDisableDate',
					index : 'ethicalDisableDate',
					align : 'center',
					formatter:yymmddFormatter,
					width: 80
				},{
					name : 'ethicalPaperCode',
					index : 'ethicalPaperCode',
					align : 'center',
					width: 100
				},{
					name : 'createDate',
					index : 'createDate',
					align : 'center',
					width: 80,
					formatter:yymmddFormatter
				},{
					name : 'createBy',
					index : 'createBy',
					align : 'center',
					width: 60
				}],
		rowNum : 15,
		rowList : [ 15, 30, 50,100,500,1000],
		height : "100%",
		autowidth : true,
		pager : '#lunLiPager',
		sortname : 'area_code',
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
			id : "lccCode"
		}
	};
	$("#lunLiGrid").jqGrid(optionlcc);
	$("#lunLiGrid").jqGrid('navGrid', '#lunLiPager', {
		edit : false,
		add : false,
		del : false,
		search : false,
		position : 'right'
	});
	//自适应
	jqgridResponsive("lunLiGrid", false);
</script>

	