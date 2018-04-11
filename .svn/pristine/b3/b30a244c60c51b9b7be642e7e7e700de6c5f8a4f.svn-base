<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>发送短信管理</title>
	<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script> 
	<style type="text/css">
		.leftLable{
			text-align: right;
		}
		.leftLable{
			padding-right:0;
			width:7%;
		}
	</style>
	
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
	  <li class="active">发送短信管理</li>
	</ul>  
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row" style="margin-top:5px;"> 
					<div  class="leftLable col-md-1">医院名称:</div> 
					<div  class="right col-md-2"> 
						<input id="lccCode" type="hidden" name="lccCode" />
						<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					
					<div  class="leftLable col-md-1">PID:</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>
					
					<div  class="leftLable col-md-1">患者姓名:</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
					</div> 
					
					<div  class="leftLable col-md-1">所属组别:</div> 
					<div  class="right col-md-2"> 
						<select class="form-control input-sm"  name="group" id="group" >
							<option value="">全部</option>

							<option value="01">非糖尿病实验组</option>
							<option value="02">非糖尿病对照组</option>
							<option value="11">糖尿病实验组</option>
							<option value="12">糖尿病对照组</option>
						</select>
					</div>
				</div>
				<div  class="row" style="margin-top:5px;"> 
					<div  class="leftLable col-md-1">预约发送时间:</div> 
					<div  class="right col-md-2"> 
						<input name="preSendTime" id="preSendTime"  class="form-control input-sm" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					
					<div  class="leftLable col-md-1">发送时间:</div> 
					<div  class="right col-md-1"> 
						<input name="realSendTimeStart" id="realSendTimeStart" placeholder="开始" style="width:90px;" class="form-control input-sm" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					<div  class="right col-md-1">
						<input name="realSendTimeEnd" id="realSendTimeEnd" placeholder="结束" style="width:90px;" class="form-control input-sm" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					<div  class="leftLable col-md-1">是否发送:</div> 
					<div  class="right col-md-2"> 
						<select class="form-control input-sm"  name="state" id="state" >
							<option value="">全部</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</div>
					
					<%--<div  class="leftLable col-md-1">是否删除:</div> --%>
					<%--<div  class="right col-md-2"> --%>
						<%--<select class="form-control input-sm"  name="isDelete" id="isDelete" >--%>
							<%--<option value="">全部</option>--%>
							<%--<option value="1">是</option>--%>
							<%--<option value="2">否</option>--%>
						<%--</select>--%>
					<%--</div>--%>
					<div  class="leftLable col-md-1">发送状态:</div>
					<div  class="right col-md-2">
						<select class="form-control input-sm"  name="sendResult" id="sendResult" >
							<option value="">全部</option>
                            <option value="0">空</option>
							<option value="1">成功</option>
							<option value="2">失败</option>
						</select>
					</div>
				</div>
				<div  class="row" style="margin-right: 50px;text-align: right">
					

					<%--<div  class="leftLable col-md-1">回执信息:</div> --%>
					<%--<div  class="right col-md-2">--%>
						<%--<select class="form-control input-sm"  name="reportCode" id="reportCode" >--%>
							<%----%>
						<%--</select>--%>
					<%--</div>--%>
				
					<%--<div  class="col-md-3" style="text-align:right">--%>
						<button type="button" id="btnQuery" class="btn btn-primary btn-sm">查询</button>
						<%--<button type="button" id="btnSend" style="width:100px" class="btn btn-primary btn-sm">发送欢迎信息</button>--%>
						<button type="button" id="btnExport" class="btn btn-primary btn-sm">导出</button>
					<%--</div>--%>
				</div>
			</form>
		</div>
	</div>
	
	<div id="message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="messageSpanId"></span>
	</div>
	
	<div id="jqgrid" style="overflow:auto;">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	

	<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 id="dia_title" class="modal-title">编辑</h4>
	      </div>
	      <div class="modal-body">
	      		<p>加载中……</p>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  

	<script type="text/javascript">
	$(function(){
		$("#btnExport").click(function(){
			
			
			window.open('${ctx}/msg/send/export?lccCode='+$("#lccCode").val()+'&patientId='+$("#patientId").val()+'&patientName='+$("#patientName").val()+
					'&group='+$("#group").val()+'&preSendTime='+$("#preSendTime").val()+'&realSendTimeStart='+$("#realSendTimeStart").val()
					+'&realSendTimeEnd='+$("#realSendTimeEnd").val()+'&state='+$("#state").val()
					//+'&reportCode='+$("#reportCode").val()+'&isDelete='+$("#isDelete").val()+'&sendResult='+$("#sendResult").val()
					);
		});
		$("#btnSend").click(function(){
			$.post("${ctx}/msg/rule/send",function(result){
				if(result.SUCCESS==true){
					showResult(true,"调用成功");
				}
				else{
					showResult(false,"调用失败");
				}
			},'json');
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
		    //发送回执的状态码
		    $.getJSON("${ctx}/msg/send/getReportCode",function(data) { 
		    	//console.log(data);
		    	var options = "<option value='all'>全部</option><option value=''>为空</option>";
		    	$.each(data,function(index,d){
		    		if(d && d.REPORT_CODE){
		    			options += "<option value="+d.REPORT_CODE+">"+d.SEND_REASON+"</option>"
		    		}
		    	});
		    	$("#reportCode").html(options);
		    	
		    	//console.log(data);
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
	        url : '${ctx}/msg/send/list',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : [ 'LCCID','PID','患者姓名',//'身份证',
				'手机','所属组别','短信内容','是否需要回复','预设发送时间','发送时间','是否发送','发送状态','回执信息','发送次数'
				//,'是否删除','创建时间','创建人'
			],
	        colModel : [ 
						 {name : 'lccId', index : 'lccId', align:'center',width:'60'},
						 {name : 'patientId', index : 'patientId', align:'center',width:'80'},
						 {name : 'patientName', index : 'patientName', align:'center',width:'80'},
//						 {name : 'idNumber', index : 'idNumber', align:'center',width:'140'},
						 {name : 'mobile', index : 'mobile', align:'center',width:'100'},
						 {name : 'bGroup', index : 'bGroup', align:'center',width:'100',formatter : function(cellvalue, option, rowObjects) {
							 if(cellvalue=='01'){
								 return "非糖尿病实验组";
							 }else if(cellvalue=='02'){
								 return "非糖尿病对照组";
							 }else if(cellvalue=='11'){
								 return '糖尿病实验组';
							 }else if( cellvalue=='12' ){
								 return '糖尿病对照组';
							 }
							 return "";
							}
						 },
						 {name : 'msgName', index : 'msgName', align:'left',width:'350'},
						 {name : 'isNeedReply', index : 'isNeedReply', align:'center',width:'100',formatter : function(cellvalue, option, rowObjects) {
							 if(cellvalue==1){
	                    		 return "是";
	                    	 }else{
	                    		 return "否";
	                    	 }
							}
						 },
						 {name : 'preSendTime', index : 'preSendTime', formatter:priorityFullDateFormatter,align:'center',width:'120'},
						 {name : 'realSendTime', index : 'realSendTime', formatter:priorityFullDateFormatter,align:'center',width:'120'},
						 {name : 'state', index : 'state', align:'center',width:'60',formatter : function(cellvalue, option, rowObjects) {
	                    	 if(cellvalue==1){
	                    		 return "是";
	                    	 }else  if(cellvalue==2){
	                    		 return "否";
	                    	 }
	                    	 return "";
							}
						 },
						 {name : 'sendResult', index : 'sendResult', align:'center',width:'60',formatter : function(cellvalue, option, rowObjects) {
	                    	 if(cellvalue==1){
	                    		 return "成功";
	                    	 }else  if(cellvalue==2){
	                    		 return "失败";
	                    	 }
	                    	 return "";
							}
						 },
						 {name : 'sendReason', index : 'sendReason',align:'center',width:'120'},
						 {name : 'sendCount', index : 'sendCount',align:'center',width:'60'}
//				,{name : 'isDelete', index : 'isDelete', align:'center',width:'60',formatter : function(cellvalue, option, rowObjects) {
//	                    	 if(cellvalue==1){
//	                    		 return "是";
//	                    	 }else  if(cellvalue==2){
//	                    		 return "否";
//	                    	 }
//	                    	 return "";
//							}
//						  },
//						 {name : 'createTime', index : 'createTime', align:'center',width:'120',formatter:yymmddFormatter},
//						 {name : 'creatorName', index : 'creatorName',width:'80', align:'center'},
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        autowidth : false,  
	        pager : '#pager',  
	        sortname : 'LCCID',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "ASC",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: true
	 	};  
		$("#grid").jqGrid(option); 
		$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})
		.navButtonAdd('#pager',{caption:"增加",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
		.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
		.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"});
		jqgridResponsive("grid",false); 
		
		$("#btnQuery").click(multipleSearch);
	});
	
	function toAdd(){
		var timebak = new Date().getTime();
		$("#dia_title")[0].innerText = "新增";
		openDialog("${ctx}/msg/send/openmodal/toEdit?time="+timebak,"dialog-confirm");
	}
	
	function toModify(){
		$("#dia_title")[0].innerText = "编辑";
		var timebak = new Date().getTime();
		var _ids = $("#grid").jqGrid('getGridParam','selarrrow');
		if(_ids.length==0){
			showResult(false,"请选择一条记录");
			return;
		}
		
		if(_ids.length>1){
			showResult(false,"只能选择一条记录");
			return;
		}
		
		var rowData = $("#grid").jqGrid('getRowData',_ids[0]);
		if(rowData.isDelete == '是'){
			showResult(false,"已删除数据不可修改");
			return ;
		}
		if(rowData.state == '是'){
			showResult(false,"已发送数据不可修改");
			return ;
		}
		openDialog("${ctx}/msg/send/openmodal/toEdit?id="+_ids[0]+"&time="+timebak,"dialog-confirm");
	}
	
	function toDelete(){
		var _ids = $("#grid").jqGrid('getGridParam','selarrrow');
	    if(_ids==null || _ids.length==0) { 
	    	showResult(false,'请选择要删除的数据');
	        return;
	    }
	    var ids="";
	    for(var i in _ids){
	    	if(ids.length>0){
	    		ids+=",";
	    	}
	    	var rowData = $("#grid").jqGrid('getRowData',_ids[i]);
			if(rowData.isDelete == '是'){
				showResult(false,"包含已删除数据");
				return ;
			}
			if(rowData.state == '是'){
				showResult(false,"已发送数据不可删除");
				return ;
			}
	    	ids+=_ids[i];
	    }
	    jConfirm("确认删除这"+_ids.length+"条数据吗?",null,function(r){
	    	if(r){
	    		$.post("${ctx}/msg/send/sendDelete?ids="+ids,null,function(result){
					if(result && result !='undefinded' &&result.success==true){
						$("#grid").trigger('reloadGrid');
	        	    	showResult(true,'删除成功');
					}else{
						showResult(false,'删除失败');
					}
				},'json');
	    	}
	    });
	}
	
	//弹出对话框
	function openDialog(url,modalId){
	    $("#"+modalId ).modal({
	         backdrop: 'static'
	    });
	    //使用此方法防止js缓存不加载
	    if(url != null){
	    	$("#"+modalId+" p" ).html("");
	    	$("#"+modalId+" p" ).load(url);
	    }
	}
	
	function openError(message,delay,msgId){
		if(msgId == null)msgId="#alert";
    	$(msgId).show().find('strong').text(message);
    	window.setTimeout(function() {
    		$(msgId).slideUp("slow");
    	}, delay);
    }
	
	function showResult(result,message,id){
		  if(id==null)id="message";
		  $("#"+id+"SpanId").text(message);

		  if(result){
		      $("#"+id).addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
		  }else{
		      $("#"+id).addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
		  }
		  window.setTimeout(function() {
		      $("#"+id).slideToggle(1000);
		  }, 2000);
	}
	</script>


</body>
</html>