<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
<%@ taglib prefix="adp"	uri="http://wwww.gener-tech.com/adp-tags" %> --%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<title>日志管理</title>
</head>
<body>
<ul class="breadcrumb">
  <li class="active">系统管理</li><li class="active" >日志管理</li>
</ul>
<div id="alert" class="alert alert-danger" hidden>
    <strong>Oh snap!</strong>
</div>
<form class="form-table" id="searchForm">
	<table class="table table-bordered" >
		<tr class="active">
		<!-- 
			<td>日志类型</td>
			<td>日志等级</td>
			<td>模块</td>
			 -->
			<td>开始时间</td>
			<td>截止时间</td>
			<td></td>
		</tr>
		<tr>
		<!-- 
			<td  class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
				<adp:select id="logType" name="logType" typename="LogType" 
					defaultname="全部" defaultvalue="" valuelist="1,3" 
					styleclass="form-control input-sm" >
				</adp:select>
			</td>
			<td  class="col-lg-1 col-md-1" style="vertical-align: middle;padding: 4px 8px;">
				<adp:select id="logLevel" name="logLevel" typename="LogLevel" 
					defaultname="全部" defaultvalue="" valuelist="0,1,2,3" 
					styleclass="form-control input-sm" >
				</adp:select>
			</td>
			<td  class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
				<adp:select id="model" name="model" typename="SystemModel" 
					defaultname="全部" defaultvalue="" 
					styleclass="form-control input-sm" >
				</adp:select>
			</td>
			 -->
			<td class="col-lg-2 col-md-2">				    
			    <div class="input-group date" id="duedate1" data-date-format="yyyy-mm-dd">
					<input class="form-control input-sm" id="dateFrom" name="dateFrom" required="required" type="text" />
					<span class="input-group-addon input-sm btn">
					<i class="glyphicon glyphicon-calendar "></i>
					</span>
				</div>
			</td>
			<td class="col-lg-2 col-md-2">
				<div class="input-group date" id="duedate2" data-date-format="yyyy-mm-dd">
				<input class="form-control input-sm" id="dateTo" name="dateTo" required="required" type="text" />
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
				</div>
			</td>
			<td  class="col-lg-1 col-md-1" style="vertical-align: middle;padding: 4px 8px;">
				<button type="button" id="btnQuery" class="btn btn-primary btn-sm">查询</button>
			</td>	
		</tr>
	</table>
</form>

<div id="jqgrid">
	<table id="grid"></table>
	<div id="pager"></div>
</div> 

<script type="text/javascript">

	$(function(){
		$("#log_nav").addClass('active');
		
		//日期控件		
		//初始化开始时间值
		var fday=new Date();
		fday.setMonth(fday.getMonth()-1);
		var fdate = DateFormat.format(fday, 'yyyy-MM-dd');
		$('#duedate1').attr('data-date', fdate);
		$('#dateFrom').val(fdate);
		//选择开始时间触发
		$('#duedate1').datepicker({
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#duedate1').datepicker('hide');
			checkDate();
		});
		//初始化结束时间值
		var today = DateFormat.format(new Date(), 'yyyy-MM-dd');
		$('#duedate2').attr('data-date', today);
		$('#dateTo').val(today);
		//选择结束时间触发
		$('#duedate2').datepicker({
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#duedate2').datepicker('hide');
			checkDate();
		});
		
		function checkDate(){
			var flag=true;
			var fromDate =  DateFormat.parse($('#dateFrom').val(), 'yyyy-MM-dd'); 
			var endDate = DateFormat.parse($("#dateTo").val(), 'yyyy-MM-dd');
			
			if (fromDate.valueOf() > new Date().valueOf()){
				$('#alert').show().find('strong').text('起始时间不能晚于当前时间！');
				flag=false;
			}
			if (endDate.valueOf() > new Date().valueOf()){
				$('#alert').show().find('strong').text('结束时间不能晚于当前时间！');
				flag=false;
			}
			if(fromDate.valueOf() > endDate.valueOf()){
				$('#alert').show().find('strong').text('起始时间不能大于结束时间！');
				flag=false;
			}	
			if(!flag){
				$("#btnQuery").attr("disabled",true);
			}else {
				$("#btnQuery").attr("disabled",false);
				$('#alert').hide();
			}
		}	

		var option = {
				url : '${ctx}/sys/log/list', 
				datatype : 'json', 
				mtype : 'POST',
				postData:{
					startDate:$("#dateFrom").val()+" 00:00:00",
					endDate:$("#dateTo").val()+" 23:59:59"
				},
				colNames : [ '','时间','用户','登录名','IP','模块','日志类型','日志等级','信息'],
				colModel : [ {name : 'id',index : 'id',hidden : true},
				             {name : 'logDatetime', index : 'logDatetime',width:'35%', align:'center',formatter:priorityFullDateFormatter},
				             {name : 'userName', index : 'userName',width:"20%"},
				             {name : 'loginName', index : 'loginName',width:"20%"},
				             {name : 'userIp', index : 'userIp',width:"25%"},
				             {name : 'model', width:"20%",index : 'model',formatter:function(cellvalue,option,rowObject){
				            	 var result = '';
				            	 if(cellvalue){
				            		 if(1==cellvalue){
				            			 result ='健康监控';
				            		 }else if(2==cellvalue){
				            			 result ='资产管理';
				            		 }else if(3==cellvalue){
				            			 result ='燃油管理';
				            		 }else if(4==cellvalue){
				            			 result ='集成监控';
				            		 }else if(5==cellvalue){
				            			 result ='构型管理';
				            		 }else if(6==cellvalue){
				            			 result ='系统管理';
				            		 }else if(7==cellvalue){
				            			 result ='基础模块';
				            		 }
				            	 }
				            	 return result;
				             }},
				             {name : 'logType', index : 'logType',width:"20%"},
				             {name : 'logLevel', index : 'logLevel',align:'center',width:"20%",formatter:function(cellvalue,option,rowObject){
				            	 var result ='';
				            	 if(0==cellvalue){
				            		 result = '<span class="label label-success">信息</span>';
				            	 }else if(1==cellvalue){
				            		 result ='<span class="label label-warning">警告</span>';
				            	 }else if(2==cellvalue){
				            		 result ='<span class="label label-danger">错误</span>';
				            	 }else if(3==cellvalue){
				            		 result ='<span class="label label-info">严重</span>';
				            	 }else if(4==cellvalue){
				            		 result ='<span class="label label-info">瘫痪</span>';
				            	 }
				            	 return result;
				             }},
				             {name : 'logInfo', index : 'logInfo'}],
				rowNum : 15, 
				rowList : [ 15, 30, 50,100,150,500 ], 
				height : "100%",
				autowidth : true, 
				pager : '#pager', 
				sortname : 'logDatetime',
				altRows:true,
				hidegrid : false, 
				viewrecords : true, 
				recordpos : 'left', 
				sortorder : "desc",
				emptyrecords : "没有可显示记录", 
				loadonce : false,
				loadComplete : function() {},
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
		$("#grid").jqGrid(option);
		
		//自适应
		jqgridResponsive("grid",false);
		
		$("#btnQuery").click(multipleSearch);
	});
	
	//查询动作
	var multipleSearch = function() {  
	     var postData = $("#grid").jqGrid("getGridParam", "postData");  
	     var mydata = {};
	     var logType =$("#logType").val();
	     var logLevel =$("#logLevel").val();
	     var model =$("#model").val();
	     var dateFrom =$("#dateFrom").val();
	     var dateTo =$("#dateTo").val();
		 if(logType && ''!=logType){
			 mydata.logType = logType;
		 }else{
			 delete postData.logType;
		 }
		 if(logLevel && ''!=logLevel){
			 mydata.logLevel = logLevel;
		 }else{
			 delete postData.logLevel;
		 }
		 if(model && ''!= model){
			 mydata.model = model;
		 }else{
			 delete postData.model;
		 }
		 if(dateFrom && ''!= dateFrom){
			 
			 mydata.startDate = dateFrom+" 00:00:00";
		 }else{
			 delete postData.startDate;
		 }
		 if(dateTo && ''!= dateTo){
			 mydata.endDate = dateTo+" 23:59:59";
		 }else {
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