<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="dict" uri="http://com.bdcor.pip.web.common.tagsupport" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<input type="hidden" id="archivesNo" name="archivesNo" value="${param.archivesNo} ">
<div id="JqNewGrid">
	<table id="newGrid"></table>
	<div id="newPager"></div>
</div>
<script type="text/javascript">
var optionlcc = {
		url : '${ctx}/material/blood/listScmarchives?archivesNo='+$("#archivesNo").val(),
		datatype : 'json',
		mtype : 'POST',
		colNames : [
				'id','项目ID','箱编号','采血包号','采血包状态','批次号','截止有效期','创建人','创建时间'
		             ],
		colModel : [
				{
					name : 'id',
					index : 'id',
					hidden:true
				},{
					name : 'projectId',
					index : 'projectId',
					align : 'center',
					hidden:true
				},{
					name : 'archivesNo',
					index : 'archivesNo',
					align : 'center',
					width: 80
				},{
					name : 'bloodpackageCode',
					index : 'bloodpackageCode',
					align : 'center',
					width: 100
				},{
					name : 'packageState',
					index : 'packageState',
					align : 'center',
					width: 100,
					formatter:function renameState(cellvalue, option, rowObjects){
						if(cellvalue =='1'){
							return "<span style='color:blue'>使用</span>";
						}
						if(cellvalue =='2'){
							return "<span style='color:green'>未使用</span>";
						}
						if(cellvalue =='3'){
							return "<span style='color:red'>报损</span>";
						}
                        if(cellvalue =='4'){
                            return "<span style='color:red'>已过期</span>";
                        }
					}
				},{
					name : 'materlBatch',
					index : 'materlBatch',
					align : 'center',
					width: 70
				},{
					name : 'periodValidity',
					index : 'periodValidity',
					align : 'center',
					formatter:yymmddFormatter,
					width: 100
				},{
					name : 'createBy',
					index : 'createBy',
					align : 'center',
					width: 80
				},{
					name : 'createDate',
					index : 'createDate',
					align : 'center',
					formatter:yymmddFormatter,
					width: 100,
					hidden:true
				}],
		rowNum : 15,
		rowList : [ 15, 30, 50,100,150,500 ],
		height : "100%",
		autowidth : true,
		pager : '#newPager',
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
			id : "id"
		}
	};
	$("#newGrid").jqGrid(optionlcc);
	$("#newGrid").jqGrid('navGrid', '#newPager', {
		edit : false,
		add : false,
		del : false,
		search : false,
		position : 'right'
	});
	//自适应
	jqgridResponsive("newGrid", false);
</script>

	