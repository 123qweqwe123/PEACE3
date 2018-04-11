<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
<title>支持性文件收集报表</title>
</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">支持性文件收集报表</li>
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
                <div class="row">
                    <div class="col-md-1" style="text-align: right">医院名称:</div>
                    <div class="col-md-2">
                        <input type="hidden" id="lccCode"  />
                        <input class="form-control input-sm" style="width: 150px" id="lccName"  name="lccName"
                               type="text" placeholder="输入单位简拼或LCCID" />
                    </div>

                    <div class="col-md-1" style="text-align: right">PID:</div>
                    <div class="col-md-2">
                        <input class="form-control input-sm" id="patientId"  name="patientId" type="text"
                               placeholder="患者ID"/>
                    </div>

                    <div class="col-md-1" style="text-align: right">患者姓名:</div>
                    <div class="col-md-2">
                        <input class="form-control input-sm" id="patientName"  name="patientName" type="text"
                               placeholder="患者姓名"/>
                    </div>

                    <div class="col-md-1" style="text-align: right">住院名称:</div>
                    <div class="col-md-2">
                        <input class="form-control input-sm" id="hosName"  name="hosName" type="text"
                               placeholder="住院名称"/>
                    </div>
                </div>
                <div class="row" style="margin-top: 10px">
                    <div class="col-md-1" style="text-align: right">支持性材料:</div>
                    <div class="col-md-2">
                        <input type="hidden" id="showUsrFile_dictFileId">
                        <input type="text" style="width: 150px" class="form-control input-sm"
                               id="showUsrFile_dictFileName" value='' placeholder="输入支持性材料简拼"/>
                    </div>
                    <div class="col-md-1" style="text-align: right">是否完成:</div>
                    <div class="col-md-2">
                        <select  class="form-control input-sm" id="state" placeholder="输入支持性材料简拼">
                            <option value="" >全部</option>
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>
                    <%--<div class="col-md-1" style="text-align: right"></div>--%>
                    <div class="col-md-2">
                        <button type="button" onclick="doQuery();"
                                class="btn btn-primary btn-sm">查询</button>
                    </div>
                </div>
               </fieldset>
             </form>
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
		window.open('${ctx}/qn/uqsAnswerqnLogReportExport');
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
		var patientId = $("#patientId").val();
		if(patientId !=null && patientId !=''){
			data.patientId = patientId;
		}else{
			delete postData.patientId;
		}
		var patientName = $("#patientName").val();
		if(patientName !=null && patientName !=''){
			data.patientName = patientName;
		}else{
			delete postData.patientName;
		}
		var hosName = $("#hosName").val();
		if(hosName !=null && hosName !=''){
			data.hosName = hosName;
		}else{
			delete postData.hosName;
		}
		var state = $("#state").val();
		if(state !=null && state !=''){
			data.state = state;
		}else{
			delete postData.state;
		}
		var fileInfo = $("#showUsrFile_dictFileName").val();
		if(fileInfo !=null && fileInfo !=''){
			data.fileInfo = fileInfo;
		}else{
			delete postData.fileInfo;
		}
		$.extend(postData,data);
		$("#grid").jqGrid("setGridParam", {
			search: true  
		}).trigger("reloadGrid", [{page:1}]);
	}
		$(function(){
			$.getJSON("${ctx}/qn/eventMgt/dictFiles",function(data) { 
			    $('#showUsrFile_dictFileName').autocomplete(data,{
			        minChars: 0,
			        mustMatch:true, 
			        width:260,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return item.FILE_INFO;
			            
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.FILE_INFO+item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.FILE_INFO;
			        }
			    }); 
			  //选中 某条记录 触发的事件
			    $('#showUsrFile_dictFileName').result(function(event, item){ 
					if(item){
						$("#showUsrFile_dictFileId").val(item.ID);
					}else{
						$("#showUsrFile_dictFileId").val("");
					}
			     });
			});
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
		        url : '${ctx}/qn/eventMgt/showAllUsrFileList',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : ['LOG_MIN_ID','LCCID','PID','患者姓名','住院名称','入院时间','出院时间','支持性材料','是否完成','操作时间','操作人'],
		        colModel : [ 
							 {name : 'LOG_MIN_ID', index : 'LOG_MIN_ID',hidden:true},
							 {name : 'LCC_CODE', index : 'LCC_CODE',width:'50px',align:'center' ,sortable: false},
							 {name : 'PATIENT_ID', index : 'PATIENT_ID',width:'60px', align:'center' ,sortable: false},
							 {name : 'PATIENT_NAME', index : 'PATIENT_NAME',width:'60px', align:'center' ,sortable: false},
							 {name : 'HOS_NAME', index : 'HOS_NAME',width:'80px', align:'center' ,sortable: false},
							 {name : 'IN_HOS_DATE', index : 'IN_HOS_DATE',width:'80px', align:'center' ,sortable: false},
							 {name : 'OUT_HOS_DATE', index : 'OUT_HOS_DATE',width:'80px', align:'center' ,sortable: false},
							 {name : 'FILE_INFO', index : 'FILE_INFO',width:'150px', align:'left' ,sortable: false},
							 {name : 'COMPLETED', index : 'COMPLETED',width:'60px', align:'center' ,sortable: false,formatter : function(cellvalue, option, rowObjects) {
									if(cellvalue == 1){
										return "是";
									}else{
										return "否";
									}	
									}},
							 {name : 'CREATE_DATE', index : 'CREATE_DATE',width:'120px', align:'center' ,sortable: false},
							 {name : 'CREATE_NAME', index : 'CREATE_NAME',width:'60px', align:'center' ,sortable: false}
		                   ],         
		        rowNum : 15, 
		   		rowList : [ 15,30,50,100,500 ],      
		        height : "100%",
		        autowidth : true,  
		        pager : '#pager',  
		        altRows:true, 
		        hidegrid : false, 
		        viewrecords : true, 
		        recordpos : 'left', 
		        emptyrecords : "没有可显示记录", 
		        loadonce : false,
		        multiselect: false,
                sortname : 'LCC_CODE',
                sortorder : "ASC",
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
		function reValue(cellvalue, option, rowObjects) {
			if(cellvalue>=14){
				return "<span style='color:red'>"+cellvalue+"</span>";
			}
			else{
				return cellvalue;
			}
		}
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