<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
<title>事件进度报表</title>
</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">事件进度明细报表</li>
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
                           <li class="select-one"> <label form=name>PID:</label></li>
                           <li> 
                 				<input class="form-control input-sm" id="patientId"  name="patientId" type="text" placeholder="患者ID" />
                           </li>
                           <li class="select-one"> <label form=name>患者姓名:</label></li>
                           <li> 
                 				<input class="form-control input-sm" id="patientName"  name="patientName" type="text" placeholder="患者姓名" />
                           </li>
                           <li> <button type="button" onclick="doQuery();" class="btn btn-primary btn-sm">查询</button></li>
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
	<script type="text/javascript">
	function doExport(){
		window.open('${ctx}/qn/eventDetailReportExport?lccCode='+$("#lccCode").val()+
                "&patientId="+$("#patientId").val()+"&patientName="+encodeURI(encodeURI($("#patientName").val())));
	}
	function doQuery(){
		var data = {};
		var postData = $("#grid").jqGrid("getGridParam", "postData");  
		var lccCode = $("#lccCode").val();
		var patientId = $("#patientId").val();
		var patientName = $("#patientName").val();
		if(lccCode !=null && lccCode !=''){
			data.lccCode = lccCode;
		}else{
			delete postData.lccCode;
		}
		if(patientId !=null && patientId !=''){
			data.patientId = patientId;
		}else{
			delete postData.patientId;
		}
		if(patientName !=null && patientName !=''){
			data.patientName = patientName;
		}else{
			delete postData.patientName;
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
		        url : '${ctx}/qn/eventDetailReportList',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : [ 'LCCID','医院名称','患者姓名','PID','事件ID','事件名称','入院时间','出院时间','住院医院名称'],
		        colModel : [ 
		                    {name : 'LCC_CODE', index : 'LCC_CODE', align:'center'},
					        {name : 'LCC_NAME', index : 'LCC_NAME', align:'left'},
		                    {name : 'PATIENT_NAME', index : 'PATIENT_NAME', align:'center'},
		                    {name : 'PATIENT_ID', index : 'PATIENT_ID', align:'center'},
		                    {name : 'EVENT_CODE', index : 'EVENT_CODE', align:'center'},
		                    {name : 'EVENT_NAME', index : 'EVENT_NAME', align:'center'},
		                    {name : 'IN_HOS_DATE', index : 'IN_HOS_DATE', align:'center',formatter:yymmddFormatter },
		                    {name : 'OUT_HOS_DATE', index : 'OUT_HOS_DATE', align:'center',formatter:yymmddFormatter },
 		                    {name : 'HOS_NAME', index : 'HOS_NAME', align:'left'}
		                   ],         
		        rowNum : 15, 
		        rowList : [ 15, 30, 50,100,500 ],      
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