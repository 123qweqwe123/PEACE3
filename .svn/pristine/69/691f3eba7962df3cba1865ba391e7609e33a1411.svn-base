<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%
	String lcc = request.getParameter("lcc");
	String type = request.getParameter("type");
	
	String start = request.getParameter("start");
	if ( start == null)
		start = "";
	String end = request.getParameter("end");
	if ( end == null )
		end = "";
	

%>

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
			<li class="active">进度管理</li>
			<li class="active">筛查明细信息</li>
		</ol>
	</div>


	<div id="main-content">
	
		<div id="select">
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                        		<li>
                        		条码:
                        		</li>
                        		<li>
                        			<input id="patient_id" class="form-control input-sm" />&nbsp;&nbsp;&nbsp;&nbsp;姓名:
                        		</li>
                        		<li>
                        			<input id="patient_name" class="form-control input-sm" />
                        		</li>
                                <li> <button type="button" id='btnQuery' class="btn btn-primary">查询</button> </li>
                            </ol>
                        </fieldset>
                      </form>
                </div>
            </div>
	
		<div class="tableBg">
		<div id="jqgrid">
			<table id="grid"></table>
			<div id="pager"></div>
		</div>
		</div>
		<br style="clear: both;" />
	</div>
	<script type="text/javascript">
		$(function() {

			function timestramp2str(num){
				var d = new Date();
				d.setTime(num);
				return DateFormat.format(d, 'yyyy-MM-dd hh:mm:ss');
			}
			
			var option = {
				url : '${ctx}/progress/patientList',
				postData :{lccCode:'<%=lcc %>',start:'<%=start %>',end:'<%=end %>',type:'<%=type %>'},
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '初筛条码', '姓名', '性别', '身份证号', '高危条码', '基本信息登记表',
						'初筛调查表', '高危调查表' , '高危核查表' , '干预调查表' , '随访调查表' ],
				colModel : [
						{
							name : 'PATIENT_ID'
						},
						{
							name : 'PATIENT_NAME',
							sortable : false,
							align : 'center'
						},
						{
							name : 'SEX',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if ('1' == cellvalue) {
									result = '男';
								} else if ('2' == cellvalue){
									result = '女';
								}
								return result;
							}
						},
						{
							name : 'ID_NUMBER',
							sortable : false,
							align : 'center'
						},
						{
							name : 'RISK_CODE',
							sortable : false,
							align : 'center'
						},
						{
							name : 'UQS1',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (cellvalue!=null) {
									result = '<span title="处理时间'+timestramp2str(rowObjects['UQS1_DATE'])+'" class="label label-success">完成</span>'
								} else {
									result = '<span class="label label-danger"></span>';
								}
								return result;
							}
						},
						{
							name : 'UQS2',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (cellvalue!=null) {
									result = '<span title="处理时间'+timestramp2str(rowObjects['UQS2_DATE'])+'" class="label label-success">完成</span>'
								} else {
									result = '<span class="label label-danger"></span>';
								}
								return result;
							}
						},
						{
							name : 'UQS3',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (cellvalue!=null) {
									result = '<span title="处理时间'+timestramp2str(rowObjects['UQS3_DATE'])+'" class="label label-success">完成</span>'
								} else {
									result = '<span class="label label-danger"></span>';
								}
								return result;
							}
						},
						{
							name : 'UQS4',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (cellvalue!=null) {
									result = '<span title="处理时间'+timestramp2str(rowObjects['UQS4_DATE'])+'" class="label label-success">完成</span>'
								} else {
									result = '<span class="label label-danger"></span>';
								}
								return result;
							}
						},
						{
							name : 'UQS5',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (cellvalue!=null) {
									result = '<span title="处理时间'+timestramp2str(rowObjects['UQS5_DATE'])+'" class="label label-success">完成</span>'
								} else {
									result = '<span class="label label-danger"></span>';
								}
								return result;
							}
						},
						{
							name : 'UQS6',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (cellvalue!=null) {
									result = '<span title="处理时间'+timestramp2str(rowObjects['UQS6_DATE'])+'" class="label label-success">完成</span>'
								} else {
									result = '<span class="label label-danger"></span>';
								}
								return result;
							}
						}],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'l.lcc_name',
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
			
			$("#btnQuery").click(function(){
				var o = {lccCode:'<%=lcc %>',start:'<%=start %>',end:'<%=end %>',type:'<%=type %>'};
				
				if ($('#patient_id').val() != '')
					o['patientId'] = $('#patient_id').val();
				
				o['patientName'] = $('#patient_name').val();
				
				$("#grid").jqGrid()
			    .setGridParam({
			        postData : o
			    })
			    .trigger("reloadGrid", [{
			                 
			            }
			        ]);
		    });

		});
	</script>
</body>


</html>