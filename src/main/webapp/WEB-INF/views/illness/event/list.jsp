<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head><title>病例管理</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">病例管理</li>
		<li class="active">事件查看</li>
	</ul>
	<div id="select">
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                            	 <li class="select-one"> <label form='name'>事件分类:</label></li>
                                 <li> 
                                 	<select class="form-control input-sm" name="showEvent_eventName" id="showEvent_eventName">
										<option value="">全部</option>
										<c:forEach items="${hisList}" var="his">
											<option value="${his.VNAME}">${his.VNAME}</option>
										</c:forEach>
									</select>
                                 </li>
                                 <li class="select-one"> <label form='name'>PID:</label></li>
                                 <li>
                                 	<input class="form-control input-sm" name="patientId" id="patientId" />
                                 </li>
                                 <li class="select-one"> <label form='name'>患者姓名:</label></li>
                                 <li>
                                 	<input class="form-control input-sm" name="patientName" id="patientName" />
                                 </li>
                                <li> <button type="button" onclick="showEvent_search()" class="btn btn-primary btn-align-right btn-sm">查询</button>
								</li>
                            </ol>
                        </fieldset>
                      </form>
                </div>
            </div>
<div id="showEvent_jqgrid">
    <table id="showEvent_grid"></table>
    <div id="showEvent_pager"></div>
</div>	
<script type="text/javascript">
	$(function(){
		var showEvent_option = {
	        url : '${ctx}/illness/event/list',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : ['LCCID','PID','姓名','事件Id','事件分类','住院名称','入院时间','出院时间','上报事件时间','是否死亡事件'],
	        colModel : [ 
						 {name : 'LCC_CODE', index : 'LCC_CODE',width:'50px',align:'center' ,sortable: false},
						 {name : 'PATIENT_ID', index : 'PATIENT_ID',width:'80px', align:'center' ,sortable: false},
						 {name : 'PATIENT_NAME', index : 'PATIENT_NAME',width:'80px', align:'center' ,sortable: false},
						 {name : 'EVENT_CODE', index : 'EVENT_CODE',width:'80px', align:'center' ,sortable: false},
						 {name : 'EVENT_TYPE', width:'180px',index : 'EVENT_TYPE', align:'center',sortable: false},
						 {name : 'HOS_NAME',width:'180px', index : 'HOS_NAME', align:'center',sortable: false},
						 {name : 'IN_HOS_DATE', width:'96px',index : 'IN_HOS_DATE', align:'center' ,sortable: false},
						 {name : 'OUT_HOS_DATE', width:'96px',index : 'OUT_HOS_DATE', align:'center' ,sortable: false},
						 {name : 'EVENT_DATE', index : 'EVENT_DATE',align:'center' ,sortable: false},
						 {name : 'IS_DEATH', index : 'IS_DEATH', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
								if(cellvalue == 1){
									return "是";
								}else{
									return "否";
								}	
								}}
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        autowidth : false,  
	        pager : '#showEvent_pager',  
	        sortname : 'EVENT_CODE',
	        altRows:true, 
	        height : "100%",
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "ASC",
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
		data['patientId'] = $("#patientId").val();
		data['patientName'] = $("#patientName").val();
		var postData = $("#showEvent_grid").jqGrid("getGridParam", "postData");  
	 
		$.extend(postData,data);
	
		$("#showEvent_grid").jqGrid("setGridParam", {
			search: true  
		}).trigger("reloadGrid", [{page:1}]);

	}
</script>
</body>
</html>