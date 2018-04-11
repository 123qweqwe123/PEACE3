<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
<title>事件进度明细报表</title>
</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">事件进度报表</li>
      </ul>
</div>
	<div id="alert" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
	<div id="message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="messageSpanId"></span>
	</div>
	<div id="select">  
          <div class="select-main">
                <form action="" method="post" class="well-work bs-adp form-inline">
         			<fieldset>
                  	<ol>
                           <li class="select-one"> <label form=name>医院名称:</label></li>
                           <li> 
                           	  <input type="hidden" id="lccCode"  />
                 <input class="form-control input-sm" id="lccName"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
                            </li>
                           <li> <button type="button" onclick="doQuery();"  class="btn btn-primary btn-sm">查询</button></li>
                           <li> <button type="button" onclick="doExport();" class="btn btn-primary btn-sm">导出</button></li>
                      </ol>
                  </fieldset>
                </form>
          </div>
      </div>
	<div id="jqgrid">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	<!-- <div id="jqgrid2">
	    <table id="grid2"></table>
	</div> -->
	<script type="text/javascript">
	function doExport(){
		window.open('${ctx}/qn/eventReportExport?lccCode='+$("#lccCode").val());
	}
	function doQuery(){
		var data = {};
		var postData = $("#grid").jqGrid("getGridParam", "postData");  
		var lccCode = $("#lccCode").val();
		if(lccCode !=null && lccCode !=''){
			data.lccCode = lccCode;
		}else{
			delete postData.lccCode;
		}
		$.extend(postData,data);
		$("#grid").jqGrid("setGridParam", {
			search: true  
		}).trigger("reloadGrid", [{page:1}]);
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
		        url : '${ctx}/qn/eventReportList',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : [ 'LCCID','LCC名称','事件数','住院次数'],
		        colModel : [ 
		                    {name : 'LCC_CODE',width:100, index : 'LCC_CODE', align:'center'},
					        {name : 'LCC_NAME',width:200, index : 'LCC_NAME', align:'left'},
		                    {name : 'SJZS',width:100, index : 'SJZS', align:'center'},
		                    {name : 'ZYZS',width:100, index : 'ZYZS', align:'center'}
		                   ],         
		        rowNum : 100, 
		        rowList : [ 15,30,45,60,100,500 ],      
		        height : "100%",
		        autowidth : true,  
		        pager : '#pager',
		        sortname : 'LCC_CODE',
		        altRows:true, 
		        hidegrid : false, 
		        viewrecords : true, 
		        recordpos : 'left', 
		        sortorder : "ASC",
		        emptyrecords : "没有可显示记录", 
		        loadonce : false,
		        multiselect: false,
		        jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell"
				}
		 	};  
			$("#grid").jqGrid(option); 
			$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
			jqgridResponsive("grid",false); 
			/* var option2 = {
			        url : '${ctx}/qn/eventReportListSum',
			        colNames : [ '统计','','事件总数','住院总次数'],
			        colModel : [ 
			                    {name : 'LCC_CODE',width:100, index : 'LCC_CODE', align:'center'},
						        {name : 'LCC_NAME',width:200, index : 'LCC_NAME', align:'left'},
			                    {name : 'SJZS',width:100, index : 'SJZS', align:'center'},
			                    {name : 'ZYZS',width:100, index : 'ZYZS', align:'center'}
			                   ], 
			        datatype : 'json',  
			        mtype : 'POST',
			        height : "100%",
			                
			        autowidth : true,  
			        altRows:true, 
			        hidegrid : false, 
			        viewrecords : true, 
			        recordpos : 'left', 
			        emptyrecords : "没有可显示记录", 
			        loadonce : false,
			        multiselect: false,
			        jsonReader : {
						root : "rows",
						total : "total",
						records : "records",
						repeatitems : false,
						cell : "cell"
					}
			 	};
			$("#grid2").jqGrid(option2);
			jqgridResponsive("grid2",false); */
		});
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
</body>

</html>