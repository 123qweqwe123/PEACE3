<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>质量管理</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">质量管理</li>
		<li class="active">问卷完成情况查询</li>
	</ul>

	<div id="select">
        <div class="select-main">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>医院名称:</label></li>
                         <li> 
                         	<input type="text" style="width: 200px" id="lccCode" name="lccCode" class="form-control input-sm" placeholder="请输入LCC ID或医院名称"/>
                         </li>
                         <li class="select-one"> <label form='name'>问卷完成时间:</label></li>
                         <li> 
                         	<div style="width: 120px" class="input-group date" id="planSStartDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="StartDate" placeholder="起始日期" name="StartDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                         <li> 
                         	<div style="width: 120px" class="input-group date" id="planSEndDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="EndDate" placeholder="结束日期" name="EndDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                        <li> <button type="button" onclick="searchExamine();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
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
	<div id="JqGrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>
    
	<script type="text/javascript">
		$(function(){
			$.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) { 
			    $('#lccCode').autocomplete(data,{
			        minChars: 0,
			        mustMatch:true, 
			        width:260,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="180px;">' + item.lccName + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.helpCode+item.lccCode;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.lccName;
			        }
			    });
			});
			
			var optionlcc = {
					url : '${ctx}/quality/questionnaireCondition/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '','LCCID','医院名称','PID','姓名','完成问卷类型','开始时间','结束时间','操作人账号','操作人'],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden:true,
								align :'center'
							},{
								name : 'lccCode',
								index : 'lccCode',
								align :'center',
								sortable: false
							},{
								name : 'lccName',
								index : 'lccName',
								align :'center',
								sortable: false
							},
							{
								name : 'patientId',
								index : 'patientId',
								align :'center',
								sortable: false
							},{
								name : 'name',
								index : 'name',
								align :'center',
								sortable: false
							},
							{
								name : 'state',
								index : 'state',
								align :'center',
								sortable: false
							},
							{
								name : 'startTimeStr',
								index : 'startTimeStr',
								align :'center',
								sortable: false
							},{
								name : 'endTimeStr',
								index : 'endTimeStr',
								align :'center',
								sortable: false
							},
							{
								name : 'createAccount',
								index : 'createAccount',
								align :'center',
								sortable: false
							},
							{
								name : 'createName',
								index : 'createName',
								align :'center',
								sortable: false
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pager',
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
					ondblClickRow:function(rowid,iRow,iCol,e){
						toModify();
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
			
				$("#grid").jqGrid(optionlcc);
				
				$("#grid").jqGrid('navGrid', '#pager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				});

				/* .navButtonAdd('#pager',{caption:"上传考核表",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){toUpload()},position:"last"}) */
				//自适应
				jqgridResponsive("grid", false);
				
		});
		
		$('#planSStartDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#planSStartDateDiv').datepicker('hide');
		});
		$('#planSEndDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#planSEndDateDiv').datepicker('hide');
		});
		
		function searchExamine() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var lccCode =$("#lccCode").val();
		      if(lccCode && ''!=lccCode){
		          mydata.lccCode = lccCode;
		      }else{
		          delete postData.lccCode;
		      }
		      var StartDate = $("#StartDate").val();
		      if(StartDate && ''!=StartDate){
		          mydata.startDate = StartDate;
		      }else{
		          delete postData.startDate;
		      }
		      var EndDate = $("#EndDate").val();
		      if(EndDate && ''!=EndDate){
		          mydata.endDate = EndDate;
		      }else{
		          delete postData.endDate;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
		
	</script>
</body>
</html>