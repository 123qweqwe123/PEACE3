<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div>
	<div class="select-main">
		<fieldset>
			<form action="" class="well-work bs-adp form-inline">
	       	<ol style="margin-left:-10px;">
            	<li> 
            		问卷答卷时间:
                   <div class="input-group date" id="duedate1" data-date-format="yyyy-mm-dd">
                       <input id="bg_date" name="startTime" class="form-control input-sm" style="width: 120px;"  required="required" type="text">
                       <span class="input-group-addon input-sm btn">
                       <i class="glyphicon glyphicon-calendar "></i>
                       </span>
                   </div>
               </li>
               <li>
                  	 ---&nbsp;&nbsp;
                  	<div class="input-group date" id="duedate2" data-date-format="yyyy-mm-dd">
                       <input id="end_date" name="endTime" class="form-control input-sm" style="width: 120px;" required="required" type="text">
                       <span class="input-group-addon input-sm btn">
                       <i class="glyphicon glyphicon-calendar "></i>
                       </span>
                   </div>
               </li>
               <li> <button type="button" onclick="showReportList_search()" class="btn btn-primary  btn-align-right btn-sm">查询</button> </li>
            </ol>
            </form>
         </fieldset>
	</div>
</div>
<div id="showReportList_jqgrid">
    <table id="showReportList_grid"></table>
    <div id="showReportList_pager"></div>
</div>	
<script type="text/javascript">
	$(function(){
		var date=new Date();
		initDatePicker('#duedate1','#bg_date', '#duedate2','#end_date',  
		       function(){
		       },
		       function(){
		       },
		       '2015-01-01','');  
		$('#duedate1').datetimepicker('setEndDate',  date);
		$('#duedate2').datetimepicker('setEndDate',  date);
		
		var showReportList_option = {
	        url : '${ctx}/qn/eventMgt/showReportList?patientId=${patientId}&uqsCode=${uqsCode}',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : ['','LCCID','PID','姓名','问卷开始时间','问卷结束时间','操作人','问卷查看','问卷修改'],
	        colModel : [ 
	                     {name : 'ID', index : 'ID',hidden:true},
						 {name : 'LCC_CODE', index : 'LCC_CODE',width:'50px',align:'center' ,sortable: false},
						 {name : 'PATIENT_ID', index : 'PATIENT_ID',width:'80px', align:'center' ,sortable: false},
						 {name : 'PATIENT_NAME', index : 'PATIENT_NAME',width:'60px', align:'center' ,sortable: false},
						 {name : 'START_TIME', index : 'START_TIME',width:'160px', align:'center' ,sortable: false},
						 {name : 'END_TIME', index : 'END_TIME',width:'160px', align:'center' ,sortable: false},
						 {name : 'CREATE_BY', index : 'CREATE_BY',width:'60px', align:'center' ,sortable: false},
						 {name : '', index : '',width:'60px', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
							 return "<button class='' onclick=\"javascript:showReport('"+rowObjects.PATIENT_ID+"','008','"+rowObjects.ID+"')\">查看</button>";
						 }},
						 {name : '', index : '',width:'60px', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
							 return "<button class='' onclick=\"javascript:reportAfter('"+rowObjects.PATIENT_ID+"','008','"+rowObjects.ID+"')\">修改</button>";
						 }}
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        width:"650px",
	        autowidth : false,  
	        pager : '#showReportList_pager',  
	        sortname : 'ID',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: false
	 	};  
		$("#showReportList_grid").jqGrid(showReportList_option); 
		$("#showReportList_grid").jqGrid('navGrid', '#showReportList_pager', {edit : false, add : false, del : false, search : false, position : 'right'});
		jqgridResponsive("showReportList_grid",false); 
	});
	
	
	function showReportList_search(){
		var data = {};
		data['startTime'] = $("#bg_date").val();
		data['endTime'] = $("#end_date").val();
		var postData = $("#showReportList_grid").jqGrid("getGridParam", "postData");  
	 
		$.extend(postData,data);
	
		$("#showReportList_grid").jqGrid("setGridParam", {
			search: true  
		}).trigger("reloadGrid", [{page:1}]);
	}
	
	function showReport(patientId,uqsCode,logId){
		var timebak = new Date().getTime();
		if(logId==null||logId=='null')logId="";
		openDialog("${ctx}/qn/eventMgt/openmodalReportAfter?onlyShow=true&patientId="+patientId+"&uqsCode="+uqsCode+"&logId="+logId+"&time="+timebak,"dialog-reportAfter");
	}
</script>
