<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>样本管理</title> 
	<script type="text/javascript">

		//查询绑定
		var multipleSearch = function(){ 
			var myform = $("#searchForm").serializeArray(); 
			var data = {};
			$.each(myform, function(i, field){
				data[field.name]=null; 
				if(field.value && ''!=field.value){
					data[field.name] = field.value;
				}
				if(field.name=='event_code'&&field.value =="请选择"){
					delete data[field.name]; 
				} 
			});   
	      
			var postData = $("#grid").jqGrid("getGridParam", "postData");  
	     
			$.extend(postData,data);
	
			$("#grid").jqGrid("setGridParam", {
				search: true  
			}).trigger("reloadGrid", [{page:1}]);
		};  
	
	
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
	    	if(e && e.keyCode==27){ // 按 Esc 
	        	//要做的事情
	      	}
	    	if(e && e.keyCode==113){ // 按 F2 
	         	//要做的事情
	     	}            
	     	if(e && e.keyCode==13){ // enter 键
	        	 //要做的事情
	        	//multipleSearch();
	    	}
		};

	</script>
</head>
<body>

	<ul class="breadcrumb">
	  <li class="active">样本管理</li><li class="active" >样本确认单</li><li class="active" >预览</li>      
	</ul>  
	<div style="width: 75%;margin:0 auto;" >
		<p>基本信息</p>
		<div class="row">
			<div class="col-md-5 col-md-offset-2">确认单号:&nbsp;${vo.checkInListNo}</div>
			<div class="col-md-5">项目号:&nbsp;${vo.projectId}</div>
		</div>
		<div class="row">
			<div class="col-md-5 col-md-offset-2">操作人:&nbsp;${vo.createBy}</div>
			<div class="col-md-5">项目单位:&nbsp;${vo.lccName}</div>
		</div>
		<div class="row">
			<div class="col-md-5 col-md-offset-2">样本库经办人:&nbsp;${vo.zyzxOperator}</div>
			<div class="col-md-5">项目单位经办人:&nbsp;${vo.lccOperator}</div>
		</div>
		<div class="row">
			<div class="col-md-5 col-md-offset-2">交付日期:&nbsp;${vo.deliveryDate}</div>
			<div class="col-md-5">收件日期:&nbsp;${vo.receiveDate}</div>
		</div>
		<div class="row">
			<div class="col-md-5 col-md-offset-2">交付时温度:&nbsp;${vo.temperature}</div>
		</div>
		
		<p>冻存盒列表</p>
		<div class="row">
			<div class="col-md-10 col-md-offset-2">
				<div id="jqgrid1">
				    <table id="grid1"></table>
				    <div id="pager1"></div>
				</div>
			</div>
		</div>
		
		<p>冻存管列表</p>
		<div class="row">
			<div class="col-md-10 col-md-offset-2">
				<div id="jqgrid2">
				    <table id="grid2"></table>
				    <div id="pager2"></div>
				</div>
			</div>
		</div>
		
		<p>备注</p>
		<div class="row">
			<div class="col-md-5 col-md-offset-2">${vo.remark}</div>
		</div>
		
		<div class="row">
			<div class="col-md-7 col-md-offset-5">
				<button type="button" id="btnBack" class="btn btn-primary btn-sm">返回修改</button>
      			<button type="button" id="btnSureAndNext" class="btn btn-primary btn-sm">提交</button>
			</div>
		</div>
	</div>
	
	<form action="" id="subForm" method="post">
		<input type="hidden" name="checkInListNo" value="${vo.checkInListNo}">
		<input type="hidden" name="lccCode" value="${vo.lccCode}">
		<input type="hidden" name="fridgeId" value="${vo.fridgeId}">
		<input type="hidden" name="fridgeName" value="${vo.fridgeName}">
		<input type="hidden" name="lccName" value="${vo.lccName}">
		<input type="hidden" name="zyzxOperator" value="${vo.zyzxOperator}">
		<input type="hidden" name="lccOperator" value="${vo.lccOperator}">
		<input type="hidden" name="deliveryDate" value="${vo.deliveryDate}">
		<input type="hidden" name="receiveDate" value="${vo.receiveDate}">
		<input type="hidden" name="temperature" value="${vo.temperature}">
		<input type="hidden" name="boxs" value="${vo.boxs}">
		<input type="hidden" name="boxTypes" value="${vo.boxTypes}">
		<input type="hidden" name="remark" value="${vo.remark}">
	</form>
	

<script type="text/javascript">	

$(function(){
	
	var boxs="${vo.boxs}";
	var option1 = {
        url : '${ctx}/spem/checkInList/previewBoxList',
        postData:{boxs:boxs},
        datatype : 'json',  
        mtype : 'POST',
        colNames : [ '冻存盒编码','冻存盒类型','已使用量'], 
        colModel : [ 
                      
                     {name : 'boxCode', index : 'boxCode', align:'left',sortable: false },
                     {name : 'boxType', index : 'boxType', align:'left',sortable: false },
                     {name : 'useCount',index : 'useCount',sortable: false}
                   ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager1',  
        sortname : 'boxCode,boxType',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "ASC",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        multiselect: false
 	};  
	$("#grid1").jqGrid(option1); 
	$("#grid1").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'}); 
	jqgridResponsive("grid1",false); 
	
	var option2 = {
	        url : '${ctx}/spem/checkInList/previewTubeList',
	        postData:{boxs:boxs},
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : [ '冻存盒编码','冻存管编号','高危码','类型','冻存盒内行位置','冻存盒内列位置'], 
	        colModel : [ 
	                      
	                     {name : 'boxCode', index : 'boxCode', align:'left',sortable: false },
	                     {name : 'tubeCode', index : 'tubeCode', align:'left',sortable: false },
	                     {name : 'bloodCode',index : 'bloodCode',sortable: false},
	                     {name : 'tubeType',index : 'tubeType',sortable: false},
	                     {name : 'rowNo',index : 'rowNo',sortable: false},
	                     {name : 'colNo',index : 'colNo',sortable: false}
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        autowidth : true,  
	        pager : '#pager2',  
	        sortname : 'boxCode,tubeType',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "ASC",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: false
	 	};  
		$("#grid2").jqGrid(option2); 
		$("#grid2").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'}); 
		jqgridResponsive("grid2",false); 

});

$('#btnBack').click(function(){
    var form = $("#subForm")[0];
    form.action="${ctx}/spem/checkInList/backToAdd";
    form.submit();
});

$('#btnSureAndNext').click(function(){
	if(confirm("确认提交吗？")){
		var form = $("#subForm")[0];
		form.action="${ctx}/spem/checkInList/save";
		form.submit();
	}
});

</script>


</body>
</html>