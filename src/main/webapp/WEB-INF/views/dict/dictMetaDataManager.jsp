<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>字典管理</title></head>
<body>
<div id="navbar">
	<ul class="breadcrumb">
	  <li class="active">基础数据</li><li class="active" >字典字段维护</li>
	</ul>
</div> 
	
<form class="well bs-bdcor form-inline" id="searchForm">
       	 列名:
        <input type="text" id="colName" name="colName" class="form-control input-sm"/>
    &nbsp;&nbsp;&nbsp;&nbsp; 
    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
</form>
 <div id="message" class="alert alert-success" hidden>
            <button data-dismiss="alert" class="close">&times;</button>
                <span id="messageSpanId"></span>
            </div>
<div>
	<table id="metaDataGrid"></table>
	<div id="pager"></div>
</div>	

	<script type="text/javascript">

	$(function(){  
   		 $("#metaDataGrid").jqGrid({
	   			url:'${ctx}/meta/metadatalist',
	            datatype : 'json',
	            mtype : 'get', 
	            cmTemplate :{sortable:false},
	            editurl : '${ctx}/meta/updateMetaData',
	            colNames:['id','字段名称','表名称','字典类型','列长度','是否必填','是否可修改','是否显示','显示名称','conditionType','conditionShowType'],
			   	colModel:[
			   		{name:'id',index:'id', width: 50,hidden:true},
			   		{name:'colName',index:'colName',editable:false,width:150},
			   		{name:'tableName',index:'tableName',editable:false,width:200},
			   		{name:'dictType',index:'dictType',editable:false, width:100},
			   		{name:'colLength',index:'colLength',editable:false,width:100},		
			   		{name:'isRequired',index:'isRequired',editable:true,width:100},		
			   		{name:'isUpdate',index:'isUpdate',editable:true,width:100},
			   		{name:'isShow',index:'isShow',editable:true,width:100},
			   		{name:'showName',index:'showName',editable:true},
			   		{name:'conditionType',index:'conditionType',editable:true,hidden:true},
			   		{name:'conditionShowType',index:'conditionShowType',editable:true,hidden:true}
			   	],
	            height : "100%",
	            autowidth : true,
	            altRows:true,
	            hidegrid : false,
	            pager : '#pager',
	            rowNum : 10,
	            rowList : [10,20,30],   
	            viewrecords : true,
	            recordpos : 'left',
	            emptyrecords : "没有可显示记录",
	            loadonce : false, 
	            loadComplete : function(){
	               
	            },
	            gridComplete: function(){
	              
	            },
	            //postData : {tableName: '${tableName}' },
	            jsonReader : {
	                root : "rows",
	                page : "page",
	                total : "total",
	                records : "records",
	                repeatitems : false,
	                cell : "cell",
	                id : "id"
	            }
	        }); 
		    $("#metaDataGrid").jqGrid('setFrozenColumns');   
		    //$("#metaDataGrid").jqGrid('searchGrid', {multipleSearch:true});
		    
		    
		    var edit_options={
             recreateForm:true,
             width:400,
             height:250,
             left:20,
             top:30,
             reloadAfterSubmit:true,
             jqModal:false,
             editCaption: "修改字典字段信息",
             bSubmit: "保存",
             bCancel: "关闭",
             closeAfterEdit:true,
             
             afterSubmit : function(response, postdata) 
             { 
                 var result;
                 var msg;
                 try{
                   result = eval('(' + response.responseText + ')');
                   msg = result.errMsg;
                   
                 }catch(e){
                   showResult(result,'操作失败'); 
                   return false;
                 }
                 
                 showResult(result,msg); 
                 
                return true;
             }
           }; 
	       
	   	    $("#metaDataGrid").jqGrid('navGrid', '#pager', {
	            edit : true,
	            add : false,
	            del : true,
	            search : false,
	            position : 'right'
	        },edit_options,{closeAfterEdit:true,closeOnEscape:true});
	   	 jqgridResponsive("metaDataGrid", false);
	   	 
	  	 $("#btnQuery").click(multipleSearch); 
	   });
	   //makeTable();
 
	   
	   var multipleSearch = function() {  
	         var postData = $("#metaDataGrid").jqGrid("getGridParam", "postData");  
	         var mydata = {};
	         var colName =$("#colName").val();
	         if(colName && ''!=colName){
	             mydata.colName = colName;
	         }else{
	             delete postData.colName;
	         }
	        $.extend(postData,mydata);
	        $("#metaDataGrid").jqGrid("setGridParam", {
	            search: true  
	        }).trigger("reloadGrid", [{page:1}]);
	    };
	    
	    
	    function showResult(result,message,delay){
	         $("#messageSpanId").text(message);

	         if (!delay || typeof(delay)=="undefined" || typeof(delay)!='number'){
	             delay = 2000;
	         }
	         if(result){
	             $("#message").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
	         }else{
	             $("#message").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
	         }
	         window.setTimeout(function() {
	             $('#message').slideToggle(1000);
	         }, delay);
	     }
	     
	</script>
