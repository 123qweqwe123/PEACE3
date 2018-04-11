<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div>
	<div class="select-main">
		<div  class="row"> 
			<div  class="leftLable col-md-2" style="text-align: right">事件分类:</div>
			<div  class="col-md-3"> 
				<select class="form-control input-sm" name="showEvent_eventName" id="showEvent_eventName">
					<option value="">全部</option>
					<c:forEach items="${hisList}" var="his">
						<option value="${his.VNAME}">${his.VNAME}</option>
					</c:forEach>
				</select>
			</div>
			
			<div  class="col-md-3"> 
				<button type="button" onclick="showEvent_search()" class="btn btn-primary btn-align-right btn-sm">查询</button>
			</div>
		</div>
	</div>
</div>
<div id="showEvent_jqgrid">
    <table id="showEvent_grid"></table>
    <div id="showEvent_pager"></div>
</div>	
<script type="text/javascript">
	$(function(){
		var showEvent_option = {
	        url : '${ctx}/qn/eventMgt/showEventList?patientId=${patientId}',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : ['LCCID','PID','姓名','事件Id','事件分类','入院时间','出院时间','上报事件时间','是否死亡事件'],
	        colModel : [ 
						 {name : 'LCC_CODE', index : 'LCC_CODE',width:'50px',align:'center' ,sortable: false},
						 {name : 'PATIENT_ID', index : 'PATIENT_ID',width:'60px', align:'center' ,sortable: false},
						 {name : 'PATIENT_NAME', index : 'PATIENT_NAME',width:'60px', align:'center' ,sortable: false},
						 {name : 'EVENT_CODE', index : 'EVENT_CODE',width:'60px', align:'center' ,sortable: false},
						 {name : 'EVENT_TYPE', index : 'EVENT_TYPE',width:'120px', align:'center',sortable: false},
						 {name : 'IN_HOS_DATE', index : 'IN_HOS_DATE',width:'80px', align:'center' ,sortable: false},
						 {name : 'OUT_HOS_DATE', index : 'OUT_HOS_DATE',width:'80px', align:'center' ,sortable: false},
						 {name : 'EVENT_DATE', index : 'EVENT_DATE',width:'120px', align:'center' ,sortable: false},
						 {name : 'IS_DEATH', index : 'IS_DEATH',width:'80px', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
								if(cellvalue == 1){
									return "是";
								}else{
									return "否";
								}	
								}}
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        width:"650px",
	        autowidth : false,  
	        pager : '#showEvent_pager',  
	        sortname : 'EVENT_CODE',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: false
	 	};  
		$("#showEvent_grid").jqGrid(showEvent_option); 
		$("#showEvent_grid").jqGrid('navGrid', '#showEvent_pager', {edit : false, add : false, del : false, search : false, position : 'right'});
		jqgridResponsive("showEvent_grid",false); 
	});
	
	function showEvent_search(){
		var data = {};
		data['eventName'] = $("#showEvent_eventName").val();
		var postData = $("#showEvent_grid").jqGrid("getGridParam", "postData");  
	 
		$.extend(postData,data);
	
		$("#showEvent_grid").jqGrid("setGridParam", {
			search: true  
		}).trigger("reloadGrid", [{page:1}]);

	}
</script>
