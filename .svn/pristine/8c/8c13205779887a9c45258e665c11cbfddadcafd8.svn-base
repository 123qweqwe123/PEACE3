<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
	<head>
		<title>样本出库首页</title>
	</head>
	
	<body>
		<div id="navbar">
			<ul class="breadcrumb">
				<li class="active">样本出库首页</li>
			</ul>
		</div>
		<div  class="row">
			<div  class="col-md-1" style="text-align:right;">盒号:</div> 
			<div  class="col-md-2"> 
				<input type="text" class="form-control input-sm" id="boxCode"/>
			</div>
			<div  class="col-md-4"> 
				<button type="button" class="btn btn-primary btn-align-right btn-sm" onclick="toDoSample();">确定</button>
			</div>
		</div>
		<shiro:hasRole name="超级管理员">
			<div  class="row" style="margin-top: 5px">
				<div  class="col-md-1" style="text-align:right;">医院名称:</div> 
				<div  class="col-md-2"> 
					<input id="lccCode" type="hidden" name="lccCode" />
					<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
				</div>
				<div  class="col-md-4"> 
					<button type="button" class="btn btn-primary btn-align-right btn-sm" onclick="search();">查询</button>
				</div>
			</div>
		</shiro:hasRole>
		<div id="jqgrid"">
	    	<table id="grid"></table>
	    	<div id="pager"></div>
		</div>
		
		<form action="${ctx}/scm/toDoSample" id="subForm" method="post">
			<input type="hidden" name="boxCode" id="subForm_boxCode"/>
		</form>
		
		<script type="text/javascript">
		
			function search(){
				var lccCode  = $.trim($("#lccCode").val());
				var data={};
				var postData = $("#grid").jqGrid("getGridParam", "postData");
				if(lccCode !='' && lccCode !=null){
					data.lccCode = lccCode;
				}
				else{
					delete postData.lccCode;
				}
				$.extend(postData,data);
				$("#grid").jqGrid("setGridParam", {
					search: true  
				}).trigger("reloadGrid", [{page:1}]);
			}
			function toDoSample(){
				var boxCode = $.trim($("#boxCode").val());
				if(boxCode==''){
					alert("请扫描冻存盒号");
					return;
				}
				$("#subForm_boxCode").val(boxCode);
				$("#subForm")[0].submit();
			}
			
			function reset(){
				$("#boxCode").val('');
			}
			
			$(function(){
				
				$.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) { 
				    $('#lccName').autocomplete(data,{
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
				  //选中 某条记录 触发的事件
				    $('#lccName').result(function(event, item){ 
						if(item){
							if(item.lccCode != $("#lccCode").val()){
								$("#lccCode").val(item.lccCode);
							}
						}else{
							$("#lccCode").val("");
						}
				     }); 
				});
				var option = {
			        url : '${ctx}/scm/boxList',    
			        datatype : 'json',  
			        mtype : 'POST',
			        colNames : [ 'LCCID','医院名称','盒号','出库日期','数量'],
			        colModel : [ {name : 'LCCCODE', index : 'LCCCODE', align:'center',sortable: false,width:100},
			        			 {name : 'LCCNAME', index : 'LCCNAME', align:'left',sortable: false },
								 {name : 'BOXCODE', index : 'BOXCODE', align:'left',sortable: false },
								 {name : 'LASTTUBEDATE', index : 'LASTTUBEDATE', align:'center',sortable: false },
								 {name : 'COUNTTUBE', index : 'COUNTTUBE', align:'center',sortable: false }
			                   ],         
			        rowNum : 15, 
			        rowList : [ 15, 30, 50,100,150,500 ],      
			        height : "100%",
			        autowidth : true,  
			        pager : '#pager',  
			        sortname : 'lastTubeDate',
			        altRows:true, 
			        hidegrid : false, 
			        viewrecords : true, 
			        recordpos : 'left', 
			        sortorder : "DESC",
			        emptyrecords : "没有可显示记录", 
			        loadonce : false,
			        multiselect: false
			 	};  
				$("#grid").jqGrid(option); 
				$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
				jqgridResponsive("grid",false); 
			});
		</script>
	</body>
</html>