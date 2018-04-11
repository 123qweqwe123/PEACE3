<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="dict" uri="http://com.bdcor.pip.web.common.tagsupport" %>
<%
	String itemCode = request.getParameter("itemCode");
	String patientId = request.getParameter("patientId");

%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="lcc_form" class="form-horizontal">
    	
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="zipCode"><span style="color: red">*</span>条码:</label>
			<div class="col-lg-7 col-md-10">
				<input type="text" id="source_id" name="sourceId" class="form-control input-sm" value="" placeholder="">
			</div>
		</div>
		<input id="form_patientId" type="hidden" value="<%=patientId %>">
		<input id="form_itemCode" type="hidden" value="<%=itemCode %>">
		
		<div class="form-group">
			正常的问卷:
			<table id="qcTable"></table>
		</div>
		<div class="form-group">
			<br>
			作废的问卷:
			<table id="dropTable"></table>
		</div>
	</form>
	
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">
$(function() {
	
	var optionqc = {
			url : '${ctx}/sys/drop/listUqs',
			datatype : 'json',
			postData : {
				itemCode:'<%=itemCode %>',
				'patientId':'null'
			},
			mtype : 'POST',
			//colNames : [ '','时间','协作单位','初筛完成人数','初筛完成率','高危检出人数','高危对象检出率','高危调查完成人数','高危检出完成率','随访预约人数','随访完成人数','随访成功率'],
			colNames : [ '', '条码', '问卷开始时间',
					'问卷结束时间', '上传时间' , '操作员' , '' ],
			colModel : [
					{
						name : 'code',
						index : 'code',
						hidden : true
					},
					{
						name : 'patientId',
						index : 'patientId',
						width: 80,
						sortable : false,
						align : 'left'
					},
					{
						name : 'startTime',
						index : 'startTime',
						width: 150,
						sortable : false,
						formatter : datatimeformat,
						align : 'left'
					},
					
					//{name : 'gwp', index : 'gwp', align:'left',sortable:false,formatter:formatPercent },
					{
						name : 'endTime',
						index : 'endTime',
						sortable : false,
						width: 150,
						formatter : datatimeformat,
						align : 'left'
					},
					//{name : 'gwfp', index : 'gwfp', sortable:false,align:'left',formatter:formatPercent },
					{
						name : 'createDate',
						index : 'createDate',
						sortable : false,
						width: 150,
						formatter : datatimeformat,
						align : 'left'
					}, {
						name : 'uqsOperatorName',
						index : 'uqsOperatorName',
						sortable : false,
						width: 100,
						align : 'left'
					},
					{
						name : 'itemCode',
						index : 'itemCode',
						hidden : true
					}//,
			//{name : 'sfp', index : 'sfp', sortable:false,align:'left',formatter:formatPercent }
			],
			rowNum : 15,
			rowList : [ 15, 30, 50,100,150,500 ],
			height : "100%",
			autowidth : false,
			pager : '#dataTablePager',
			sortname : 'id',
			altRows : true,
			hidegrid : false,
			viewrecords : true,
			recordpos : 'left',
			sortorder : "desc",
			emptyrecords : "没有可显示记录",
			loadonce : false,
			multiselect : true,
			gridComplete:function(){
				
			}
		};
	
		function datatimeformat(v , o , e ){
			var date = new Date();
			if ( parseInt(v) ){
				date.setTime( parseInt(v));
				return DateFormat.format(date, 'yyyy-MM-dd hh:mm:ss');
			}
			return '';
		}
	
		$("#qcTable").jqGrid(optionqc);
		
		
		var optiondrop = {
				url : '${ctx}/sys/drop/listUqsDrop',
				datatype : 'json',
				postData : {
					itemCode:'<%=itemCode %>',
					'patientId':'null'
				},
				mtype : 'POST',
				//colNames : [ '','时间','协作单位','初筛完成人数','初筛完成率','高危检出人数','高危对象检出率','高危调查完成人数','高危检出完成率','随访预约人数','随访完成人数','随访成功率'],
				colNames : [ '', '条码', '问卷开始时间',
						'问卷结束时间', '上传时间' , '操作员' ,'作废时间', '' ],
				colModel : [
						{
							name : 'dropDate',
							index : 'dropDate',
							hidden : true
						},
						{
							name : 'patientId',
							index : 'patientId',
							width: 80,
							sortable : false,
							align : 'left'
						},
						{
							name : 'startTime',
							index : 'startTime',
							width: 120,
							sortable : false,
							formatter : datatimeformat,
							align : 'left'
						},
						
						//{name : 'gwp', index : 'gwp', align:'left',sortable:false,formatter:formatPercent },
						{
							name : 'endTime',
							index : 'endTime',
							sortable : false,
							width: 120,
							formatter : datatimeformat,
							align : 'left'
						},
						//{name : 'gwfp', index : 'gwfp', sortable:false,align:'left',formatter:formatPercent },
						{
							name : 'createDate',
							index : 'createDate',
							sortable : false,
							width: 120,
							formatter : datatimeformat,
							align : 'left'
						}, {
							name : 'uqsOperatorName',
							index : 'uqsOperatorName',
							sortable : false,
							width: 80,
							align : 'left'
						},
						{
							name : 'dropDate',
							index : 'dropDate',
							sortable : false,
							width: 120,
							formatter : datatimeformat,
							align : 'left'
						},
						{
							name : 'itemCode',
							index : 'itemCode',
							hidden : true
						}//,
				//{name : 'sfp', index : 'sfp', sortable:false,align:'left',formatter:formatPercent }
				],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : false,
				pager : '#dataTablePager',
				sortname : 'id',
				altRows : true,
				hidegrid : false,
				viewrecords : true,
				recordpos : 'left',
				sortorder : "desc",
				emptyrecords : "没有可显示记录",
				loadonce : false,
				multiselect : true,
				gridComplete:function(){
					
				}
			};
		
		$("#dropTable").jqGrid(optiondrop);
		
	
		$('#source_id').keyup(function(e){
			var o = {'patientId':$('#source_id').val(),itemCode:'<%=itemCode %>'};
			
			$("#qcTable").jqGrid().setGridParam({
				postData : o
			}).trigger("reloadGrid", [ {
				 
			} ]);
			
			$("#dropTable").jqGrid().setGridParam({
				postData : o
			}).trigger("reloadGrid", [ {
				 
			} ]);
		});
		
});
	
</script>