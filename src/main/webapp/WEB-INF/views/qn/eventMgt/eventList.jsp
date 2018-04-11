<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>事件列表</title>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
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
			});

			var postData = $("#grid").jqGrid("getGridParam", "postData");

			$.extend(postData,data);

			$("#grid").jqGrid("setGridParam", {
				search: true
			}).trigger("reloadGrid", [{page:1}]);
		};
		
		function exportExcel(){
			var postData = $("#grid").jqGrid("getGridParam", "postData");
			var pram = "";
			$.each(postData, function (k, v) {
				if(v == null)return true;
				if(pram.length==0){
					pram+="?";
				}else{
					pram+="&";
				}
				pram += k + "=" +v;
			});
			window.open('${ctx}/qn/eventMgt/exportexcel'+pram);
		}
		
		
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
	<style>
		.leftLable{
			text-align:right;
		}
		
		.rightDiv{
			text-align:left;
			padding-left:0px;
		}
	</style>
</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">事件列表</li>
      </ul>
</div>
	<div id="message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="messageSpanId"></span>
	</div>
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row"> 
					<div  class="leftLable col-md-1" style="width:100px;">PID:</div>
					<div  class="rightDiv col-md-2"> 
						<input type="text"  class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>
					
					<div  class="leftLable col-md-1" style="width:120px;">患者姓名:</div>
					<div  class="rightDiv col-md-2" style="width:120px;">
						<input type="text" style="width:120px;"  class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
					</div>


					<div  class="leftLable col-md-1" style="width:130px;">是否死亡事件:</div>
					<div  class="rightDiv col-md-2">
						<select style="width:160px;" class="form-control input-sm" name="isDead" id="isDead">
							<option value="">全部</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</div>

					<shiro:hasRole name="超级管理员">
						<div  class="leftLable col-md-1" style="width:100px;">医院名称:</div>
						<div  class="rightDiv col-md-2">
							<input type="hidden" id="lccCode" name="lccCode">
							<input type="text" id="lccName" name="lccName"   class="form-control input-sm" placeholder="输入单位简拼与LCCID">
						</div>
					</shiro:hasRole>

				</div>
				<div class="row" style="margin-top: 10px">
					<div  class="leftLable col-md-1" style="width:100px;">事件分类:</div>
					<div  class="rightDiv col-md-2">
						<select style="width:100%;" class="form-control input-sm" name="eventName"
								id="eventName">
							<option value="">全部</option>
							<c:forEach items="${hisList}" var="his">
								<option value="${his.VNAME}">${his.VNAME}</option>
							</c:forEach>
						</select>
					</div>

					<div  class="leftLable col-md-1" style="width:120px;">是否收集文件:</div>
					<div  class="rightDiv col-md-2"> 
						<select class="form-control input-sm" name="isCallBack" id="isCallBack">
							<option value="">全部</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</div>
                    <div class="col-md-1" style="margin-left:-50px">上报时间:</div>
                    <div  class="col-md-2">
                        <input name="startTime" placeholder="开始时间" id="startTime"
                               class="form-control input-sm"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                    </div>
                    <div  class="col-md-2">
                        <input name="endTime" placeholder="结束时间" id="endTime"
                               class="form-control input-sm"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                    </div>

				</div>
				<div class="row" style="margin-top: 10px">
                    <div  class="col-md-1">门诊类型:</div>
                    <div  class="col-md-2">
                        <select class="form-control input-sm" name="ambtype" id="ambtype">
                            <option value="">全部</option>
                            <option value="1">入选CHAT研究</option>
							<option value="5">CHAT研究补充入选</option>
                            <option value="2">非干预面访</option>
                            <option value="3">空</option>
                            <option value="4">其它</option>
                        </select>
                    </div>
                    <div  class="col-md-6"></div>
                    <div  class="col-md-3">
                        <button type="button" id="btnQuery" class="btn btn-primary btn-align-right
						btn-sm">查询</button>
                        <button type="button" id="btnExport" class="btn btn-primary btn-align-right
						 btn-sm">导出[通过查询条件]</button>
                    </div>
				</div>
			</form>
		</div>
	</div>
	
	<div id="jqgrid">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	
	<script type="text/javascript">
		$(function(){
			$("#btnExport").click(function(){
				exportExcel();
			});
			
			$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) {
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
		        url : '${ctx}/qn/eventMgt/getEventList',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : [ 'LCCID','医院名称','PID','事件ID','患者姓名','事件分类','上报事件时间','入院时间','出院时间','住院医院','是否死亡事件','是否需收集文件','门诊类型'],
		        colModel : [ 
		                    {name : 'LCC_CODE', index : 'LCC_CODE', align:'center'},
		                    {name : 'LCC_NAME', index : 'LCC_NAME', align:'left'},
		                    {name : 'PATIENT_ID', index : 'PATIENT_ID', align:'center'},
					{name:'EVENT_CODE',align:'center'},
					        {name : 'PATIENT_NAME', index : 'PATIENT_NAME', align:'center',sortable: false  },
					        {name : 'EVENT_TYPE', index : 'EVENT_TYPE', align:'left',sortable: false  },
		                    {name : 'EVENT_DATE', index : 'EVENT_DATE', align:'center',sortable: false },
//					","",""
					{name:'IN_HOS_DATE',align:'center'},
					{name:'OUT_HOS_DATE',align:'center'},
					{name:'HOS_NAME',align:'center'},


					{name : 'IS_DEATH', index : 'IS_DEATH', align:'center',sortable: false
//						,formatter : function(cellvalue, option, rowObjects) {
//		                    	 if(cellvalue=="1"){
//		                    	 	return "是";
//		                    	 }
//		                    	 return "否";
//		                    }
					},
		                    {name : 'FILECOUNT', index : 'FILECOUNT', align:'center',sortable: false
//								,formatter : function(cellvalue, option, rowObjects) {
//	                    		 if(cellvalue>0){
//		                    	 	return "是";
//		                    	 }
//		                    	 return "否";
//	                    		}
		                    },
					{name:'BELONGTYPE'}
		                   ],         
		        rowNum : 15, 
		        rowList : [15, 30, 50],      
		        height : "100%",
		        autowidth : true,  
		        pager : '#pager',  
		        sortname : 'LCC_CODE,PATIENT_ID',
		        altRows:true, 
		        hidegrid : false, 
		        viewrecords : true, 
		        recordpos : 'left', 
		        sortorder : "ASC",
		        emptyrecords : "没有可显示记录", 
		        loadonce : false,
		        multiselect: false
		 	};  
			$("#grid").jqGrid(option); 
			$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
			jqgridResponsive("grid",false); 
		
			$("#btnQuery").click(multipleSearch);
			
		});
	</script>
</body>

</html>