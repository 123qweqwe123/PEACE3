<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>事件管理</title>
<script type="text/javascript">
		//查询绑定
		var multipleSearch = function(){ 
			var data = {};
			if($.trim($("#patientId").val())!=''){
				data['patientId']=$.trim($("#patientId").val());
			}else{
				data['patientId']='';
			}
			if($.trim($("#lccCode").val())!=''){
				data['lccCode']=$.trim($("#lccCode").val());
			}else{
				data['lccCode']='';
			}
			
			if($.trim($("#patientName").val())!=''){
				data['patientName']=$.trim($("#patientName").val());
			}else{
				data['patientName']='';
			}
			if($("#hasEvent")[0].checked){
				data['hasEvent']=1;
			}else{
				data['hasEvent']=0;
			}
			if($("#hasFile")[0].checked){
				data['hasFile']=1;
			}else{
				data['hasFile']=0;
			}
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
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">事件管理</li>
         <li class="active">结局事件</li>
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
					<div  class="leftLable col-md-1" style="text-align: right;">医院名称:</div>
					<div  class="col-md-2"> 
						<input type="hidden" id="lccCode"  />
              			<input class="form-control input-sm" id="lccName"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
						
					</div>
					<div  class="leftLable col-md-1" style="text-align: right;">PID:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>
					<div  class="leftLable col-md-1" style="text-align: right;">患者姓名:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
					</div>
				</div>
				<div class="row" style="margin-top: 10px">
					<div  class="col-md-2"> 
						<input type="checkbox" id="hasEvent" value="1"><label for="hasEvent">只显示有事件的患者</label>
					</div>
					<div  class="col-md-3"> 
						<input type="checkbox" id="hasFile" value="1"><label for="hasFile">只显示需要收集支持性文件的患者</label>
					</div>
                    <div  class="col-md-1"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div id="jqgrid">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	
	<div id='dialog-showEvent' class="modal fade">
	  <div class="modal-dialog" style="width:750px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">事件列表</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel-showEvent' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal --> 
	
	<div id='dialog-showUsrFile' class="modal fade">
	  <div class="modal-dialog" style="width:850px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">支持性材料收集</h4>
	      </div>
	      <div class="modal-body">
	      	<div id="alertForShowUsrFile" hidden>
			  <strong>Warning!</strong>
			</div>
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel-showUsrFile' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal --> 
	
	<div id='dialog-showReportList' class="modal fade">
	  <div class="modal-dialog" style="width:750px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">查看修改住院事件问卷</h4>
	      </div>
	      <div class="modal-body">
	      	<div id="alertForShowUsrFile" hidden>
			  <strong>Warning!</strong>
			</div>
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel-showReportList' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal --> 
	
	<div id='dialog-reportAfter' class="modal fade">
	  <div class="modal-dialog" style="width:800px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">补报事件</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel-reportAfter' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<script type="text/javascript">
		$(function(){
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
		        url : '${ctx}/qn/eventMgt/getEventPatientList',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : [ 'LCCID','PID','患者姓名','身份证','住院事件报告','住院事件管理','事件查看','死亡事件报告','支持性材料收集'],
		        colModel : [ 
							{name : 'lccCode', index : 'lccCode', align:'center'},
		                    {name : 'patientId', index : 'patientId', align:'center',formatter:reStyle},
					        {name : 'patientName', index : 'patientName', align:'center',sortable: false ,formatter:reStyle },
		                    {name : 'idNumber', index : 'idNumber', align:'center',sortable: false },
		                    {name : 'patientId', index : 'a', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    		return "<button class='' onclick=\"javascript:reportAfter('"+rowObjects.patientId+"','008','')\">上报</button>";
		                    }},
		                    {name : 'patientId', index : 'report', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
	                    		return "<button class='' onclick=\"javascript:showReportList('"+rowObjects.patientId+"','008')\">查看及修改</button>";
	                    	}},
		                    {name : 'patientId', index : 'reportList', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    	return "<button class='qnbtn' onclick=\"javascript:showEvent('"+cellvalue+"')\">查看</button>";
		                    }},
		                    {name : 'logId', index : 'logId', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    	if(cellvalue==null||cellvalue==''){
		                    		return "<button class='' onclick=\"javascript:reportAfter('"+rowObjects.patientId+"','006')\">上报</button>";
		                    	}else{
		                    		return "<button class='' onclick=\"javascript:reportAfter('"+rowObjects.patientId+"','006','"+cellvalue+"')\">修改上报</button>";
		                    	}
		                    }},
		                    {name : 'patientId', index : 'showUserFile', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    	return "<button class='qnbtn' onclick=\"javascript:showUsrFile('"+cellvalue+"')\">查看</button>";
		                    }}
		                   ],         
		        rowNum : 15, 
		        rowList : [ 15, 30, 50,100,500 ],      
		        height : "100%",
		        autowidth : true,  
		        pager : '#pager',  
		        sortname : 'lccCode,patientId',
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
					cell : "cell",
					id : "patientId"
				}
		 	};  
			$("#grid").jqGrid(option); 
			$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
			jqgridResponsive("grid",false); 
		
			$("#hasEvent").click(multipleSearch);
			$("#btnQuery").click(multipleSearch);
			$("#hasFile").click(multipleSearch);
			$('#cancel-showEvent').click(function(){
				$('#dialog-showEvent').modal('hide');
			}); 

			$('#cancel-showUsrFile').click(function(){
				$('#dialog-showUsrFile').modal('hide');
			}); 
			
			$('#cancel-showReportList').click(function(){
				$('#dialog-showReportList').modal('hide');
			}); 
			
			$('#cancel-reportAfter').click(function(){
				$('#dialog-reportAfter').modal('hide');
			}); 
			
			
			<c:if test="${'2' eq reportAfterresult}">
				showResult(false,"上报失败");
			</c:if>
			
			<c:if test="${'1' eq reportAfterresult}">
				showResult(true,"上报成功");
			</c:if>
		});
		
		
		
		function showEvent(patientId){
			var timebak = new Date().getTime();
			openDialog("${ctx}/qn/eventMgt/openmodalShowEvent?patientId="+patientId+"&time="+timebak,"dialog-showEvent");
		}
		
		function showReportList(patientId,uqsCode){
			var timebak = new Date().getTime();
			openDialog("${ctx}/qn/eventMgt/openmodalShowReportList?patientId="+patientId+"&time="+timebak,"dialog-showReportList");
		}
		function reStyle(cellvalue, option, rowObjects) {
			if(rowObjects.isEventDeal =='1'){
				return "<span style='color:red'>"+cellvalue+"</span>";
			}
			else{
				return cellvalue;
			}
		}
		function showUsrFile(patientId){
			var timebak = new Date().getTime();
			openDialog("${ctx}/qn/eventMgt/openmodalShowUsrFile?patientId="+patientId+"&time="+timebak,"dialog-showUsrFile");
		}
		
		function reportAfter(patientId,uqsCode,logId){
			var timebak = new Date().getTime();
			if(logId==null||logId=='null')logId="";
			openDialog("${ctx}/qn/eventMgt/openmodalReportAfter?patientId="+patientId+"&uqsCode="+uqsCode+"&logId="+logId+"&time="+timebak,"dialog-reportAfter");
		}
		
		
		//弹出对话框
		function openDialog(url,modalId){
		    $( "#"+modalId ).modal({
		         backdrop: 'static'
		    });
		    //使用此方法防止js缓存不加载
		    if(url != null){
		    	$("#"+modalId+" p" ).html("加载中……");
		    	$("#"+modalId+" p" ).load(url);
		    }
		}
		
		
		function openResultInfo(message,delay,msgId,result){
			if(msgId == null)msgId="#alert";
			if(result == null)result=true;
	    	$(msgId).show().find('strong').text(message);
	    	if(result){
	    		$(msgId).show().find('strong').addClass('alert-success').removeClass('alert-danger');
			}else{
				$(msgId).show().find('strong').addClass('alert-danger').removeClass('alert-success');
			}
	    	window.setTimeout(function() {
	    		$(msgId).slideUp("slow");
	    	}, delay);
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