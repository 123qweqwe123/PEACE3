<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
 <title>患者管理</title>
 <script  src="${ctx}/static/echarts/esl/esl.js"></script> 
 	<script type="text/javascript">
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
		.img_mystyle{
			width: 20px;
			height: 20px;
		}
	</style>
</head>
<body>
<div id='dialog-delete' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header" style="background-color: #F2DEDE">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">患者预约</h4>
      </div>
      <div class="modal-body">
        <p>该患者尚未预约！</p>
      </div>
      <div class="modal-footer">
		<button type="button"  id ='cancel4' class="btn btn-success btn-sm" tabindex="1001">确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div id="navbar">
       <ul class="breadcrumb">
         <li class="active">患者管理</li>
      </ul>
</div>
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row"> 
					<div  class="leftLable col-md-1">PID:</div> 
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder=""/>
					</div>
					
					<div  class="leftLable col-md-1">患者姓名:</div> 
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder=""/>
					</div>
					
					<div  class="leftLable col-md-1">医院名称:</div> 
					<div  class="col-md-2"> 
                      	<%-- <select id="lccCode" name="lccCode" class="form-control input-sm">
							<option value="">请选择单位</option>
							<c:forEach items="${lccDictList }" var="lccDict">
								<option value="${lccDict.lccCode }" >${lccDict.lccName }</option>
							</c:forEach>
		          		</select> --%>
		          		<input id="lccCode" type="hidden" name="lccCode" />
						<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					<div  class="leftLable col-md-1"></div> 
                    <div  class="col-md-1"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
					</div>
				</div>
				<br>
				<div class="row">
					<div  class="leftLable col-md-2" style="width: 120px">6月预约时间:</div> 
					<div class="col-md-3">
						<div style="width: 120px;" class="input-group date" id="sixStartDateDiv" data-date-format="yyyy-mm-dd">
							<input type="text"  id="sixStartDate" placeholder="起始日期" name="sixStartDate" class="form-control input-sm"/>
							<span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
						</div>
						<div style="width: 120px;" class="input-group date" id="sixEndDateDiv" data-date-format="yyyy-mm-dd">
							<input type="text"  id="sixEndDate" placeholder="结束日期" name="sixEndDate" class="form-control input-sm"/>
							<span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
						</div>
					</div>
					<div  class="leftLable col-md-2" style="width: 120px">首次预约时间:</div> 
					<div class="col-md-3">
						<div style="width: 120px;" class="input-group date" id="firstStartDateDiv" data-date-format="yyyy-mm-dd">
							<input type="text"  id="firstStartDate" placeholder="起始日期" name="firstStartDate" class="form-control input-sm"/>
							<span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
						</div>
						<div style="width: 120px;" class="input-group date" id="firstEndDateDiv" data-date-format="yyyy-mm-dd">
							<input type="text"  id="firstEndDate" placeholder="结束日期" name="firstEndDate" class="form-control input-sm"/>
							<span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
<div id="jqgrid">
    <table id="grid"></table>
    <div id="pager"></div>
</div>
<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">患者新增页面</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 患者首次预约时间 -->
	<div id='dialog-confirm4' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">患者首次预约时间 </h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel4' data-dismiss="modal" aria-hidden="true" class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_update4' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<!-- 患者预约随访时间 -->
	<div id='dialog-confirm3' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">患者预约时间</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel3' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_update2' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-confirm2' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">患者修改页面</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_update' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

<script type="text/javascript">
$('#cancel4').click(function(){
	$('#dialog-delete').modal('hide');
});


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
	
	$('#sixStartDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#sixStartDateDiv').datepicker('hide');
	});
	$('#sixEndDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#sixEndDateDiv').datepicker('hide');
	});
	$('#firstStartDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#firstStartDateDiv').datepicker('hide');
	});
	$('#firstEndDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#firstEndDateDiv').datepicker('hide');
	});
	function initProvince(){
		$.getJSON("${ctx}/combox/province",function(data) { 
		    $('#provinceName').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.PROVINCE_CODE + '</td><td width="180px;">' + item.PROVINCE_NAME + '</td></tr></table>';
		            
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.HELP_CODE+item.PROVINCE_CODE+item.PROVINCE_NAME;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.PROVINCE_NAME;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#provinceName').result(function(event, item){ 
				if(item){
					if(item.PROVINCE_CODE != $("#provinceCode").val()){
						$("#provinceCode").val(item.PROVINCE_CODE);
						initRcc();
					}
				}else{
					$("#provinceCode").val("");
					initRcc();
				}
		     });
		});
		initRcc();
	}


	function initRcc(){
		$('#rccName').unautocomplete();
		$("#rccName").val(''); 
		$("#rccCode").val('');
		
		$.getJSON("${ctx}/combox/rcc?provinceCode="+$("#provinceCode").val(),function(data) { 
		    $('#rccName').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.RCC_CODE + '</td><td width="180px;">' + item.RCC_NAME + '</td></tr></table>';
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.HELP_CODE+ item.RCC_CODE+item.RCC_NAME;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.RCC_NAME;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#rccName').result(function(event, item){ 
				if(item){
					if(item.RCC_CODE != $("#rccCode").val()){
						$("#rccCode").val(item.RCC_CODE);
						initLcc();
					}
				}else{
					$("#rccCode").val("");
					initLcc();
				}
		     });
		});
		initLcc();
	}

	function initLcc(){
		$('#lccName').unautocomplete();
		$("#lccName").val(''); 
		$("#lccCode").val(''); 
		
		$.getJSON("${ctx}/combox/dataLimitLcc?provinceCode="+$("#provinceCode").val()+"&rccCode="+$("#rccCode").val(),function(data) { 
		    $('#lccName').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.LCC_CODE + '</td><td width="180px;">' + item.LCC_NAME + '</td></tr></table>';
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.HELP_CODE+ item.LCC_CODE+item.LCC_NAME;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.LCC_NAME;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#lccName').result(function(event, item){ 
				if(item){
					if(item.LCC_CODE != $("#lccCode").val()){
						$("#lccCode").val(item.LCC_CODE);
					}
				}else{
					$("#lccCode").val("");
				}
		     });
		});
	}

	var multipleSearch = function(){
		 var postData = $("#grid").jqGrid("getGridParam", "postData");  
		 var myform = $("#searchForm").serializeArray(); 
		 var mydata = {};
		 
		 var lccCode =$("#lccCode").val();
	      if(lccCode && ''!=lccCode){
	          mydata.lccCode = lccCode;
	      }else{
	          delete postData.lccCode;
	      }
	      
	      var patientId =$("#patientId").val();
	      if(patientId && ''!=patientId){
	          mydata.patientId = patientId;
	      }else{
	          delete postData.patientId;
	      }
	      
	      var patientName =$("#patientName").val();
	      if(patientName && ''!=patientName){
	          mydata.patientName = patientName;
	      }else{
	          delete postData.patientName;
	      }
	      var firstEndDate = $("#firstEndDate").val();
	      if(firstEndDate && ''!=firstEndDate){
	          mydata.firstEndDate = firstEndDate;
	      }else{
	          delete postData.firstEndDate;
	      }
	      var firstStartDate = $("#firstStartDate").val();
	      if(firstStartDate && ''!=firstStartDate){
	          mydata.firstStartDate = firstStartDate;
	      }else{
	          delete postData.firstStartDate;
	      }
	      var sixEndDate = $("#sixEndDate").val();
	      if(sixEndDate && ''!=sixEndDate){
	          mydata.sixEndDate = sixEndDate;
	      }else{
	          delete postData.sixEndDate;
	      }
	      var sixStartDate = $("#sixStartDate").val();
	      if(sixStartDate && ''!=sixStartDate){
	          mydata.sixStartDate = sixStartDate;
	      }else{
	          delete postData.sixStartDate;
	      }
	     $.extend(postData,mydata);
	     $("#grid").jqGrid("setGridParam", {
	         search: true  
	     }).trigger("reloadGrid", [{page:1}]);
	
		
	};  
	
	var option = {
        url : '${ctx}/sys/drop/queryPatientList',
        datatype : 'json',  
        mtype : 'POST',
        colNames : [ 'PID','患者姓名','身份证','移动电话','医院名称','患者预约时间'],
        colModel : [ 
                    {name : 'patientId', index : 'patientId', align:'center',sortable: false },
 			         {name : 'patientName', index : 'patientName', align:'center',sortable: false  },
                     {name : 'idNumber', index : 'idNumber', align:'center',sortable: false },
                     {name : 'mobile', index : 'mobile', align:'center',sortable: false },
                     {name : 'lccName', index : 'lccName', align:'center',sortable: false },
                     {name : 'followviewDate', index : 'followviewDate',hidden:true, align:'center',sortable: false,formatter:yymmddFormatter }
                   ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'patient_Id',
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
	$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'}
	)/* .navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"right"})
	.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-plus",onClickButton: function(){toUpdate()},position:"last"}) */
	.navButtonAdd('#pager',{caption:"6月预约时间修改",buttonicon:"ui-icon-plus",onClickButton: function(){toAppointment()},position:"right"})
	.navButtonAdd('#pager',{caption:"首次预约时间",buttonicon:"ui-icon-plus",onClickButton: function(){toAppointmentFirst()},position:"right"});
	jqgridResponsive("grid",false); 
	//取消按钮操作
	$('#cancel').click(function(){
		$('#dialog-confirm').modal('hide');
		
	});	
	//取消按钮操作
	$('#cancel2').click(function(){
		$('#dialog-confirm2').modal('hide');
		
	});	
	$("#btnQuery").click(multipleSearch);

	
	
	/**
	       新增的记录入库	
	**/
	$('#do_save').click(function(){
		var data = {};
		
		var myform = $("#dialog-confirm").find("form").get(0);
		if(!jQuery(myform).validate().form()){ return ;}
		$("#do_update").attr("disabled",false);
		var myform = $("#dialog-confirm").find("form").serializeArray();
		var data = {};
		$.each(myform, function(i, field){
			data[field.name] = field.value;
		});
		$("#do_save").attr("disabled",true);
	       add(data);
		
	});
	$("#cancel3").click(function(){
		$('#dialog-confirm3').modal('hide');
	});
	$('#do_update4').click(function(){
		$("#do_update4").attr("disabled",true);
		var data = {};
		var myform = $("#dialog-confirm4").find("form").serializeArray();
		$.each(myform, function(i, field){
			data[field.name] = field.value;
		});
		$.post("${ctx}/pro/pat/updateAppointmentDateFirst",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm4').modal('hide');
			var message = "修改失败！";
			if(result){
				message ='修改成功!';
			}
			$("#do_update4").attr("disabled",false);
			showResult(result,message);
		},'json');
	});
	$('#do_update2').click(function(){
		$("#do_update2").attr("disabled",true);
		var data = {};
		var myform = $("#dialog-confirm3").find("form").serializeArray();
		$.each(myform, function(i, field){
			data[field.name] = field.value;
		});
		$.post("${ctx}/pro/pat/updateAppointmentDate",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm3').modal('hide');
			var message = "修改失败！";
			if(result){
				message ='修改成功!';
			}
			$("#do_update2").attr("disabled",false);
			showResult(result,message);
		},'json');
	});
	/**
	   修改的记录入库	
	**/
	$('#do_update').click(function(){
		//var postData = $("#grid").jqGrid("getGridParam", "postData");  
		//var myform = $("#searchForm").serializeArray(); 
		var data = {};
		//var patientId = $("#patientId").val();
		//alert(patientId);
		//if(patientId!='' && patientId!='undedfinded'){
		//	  data.patientId =patientId;
		//	}else{
		//		delete postData.patientId;
		//	}
		var myform = $("#dialog-confirm2").find("form").get(0);
		if(!jQuery(myform).validate().form()){ return ;}
		$("#do_update").attr("disabled",false);
		var myform = $("#dialog-confirm2").find("form").serializeArray();
		var data = {};
		$.each(myform, function(i, field){
			data[field.name] = field.value;
		});
		
		$("#do_update").attr("disabled",true);
		$.post("${ctx}/sys/drop/openmodalUpdate",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm2').modal('hide');
			var message = "修改失败！";
			if(result){
				message ='修改成功!';
			}
			$("#do_update").attr("disabled",false);
			showResult(result,message);
		},'json');
		
	});
	
	//新增数据
	function add(data){
		//新增时注释id值
		//data['id'] = null;
		$.post("${ctx}/sys/drop/openmodalAdd",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm').modal('hide');
			var message = "添加失败！";
			if(result.success){
				message ='添加成功!';
			}
			else{
				message = result.msg;
			}
			$("#do_save").attr("disabled",false);
			alert(message);
			//showResult_1(result,message);
		},'json');
	}
	//首次预约时间
	function toAppointmentFirst(){
		var timebak = new Date().getTime();
		var patientId = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(patientId)) {
			openError('请选择一条数据',2000);
			return;
		}
		openDialog4("${ctx}/pro/pat/openmodalAppointmentFirst?patientId="+patientId+'&time='+timebak);
	}
	function toAppointment(){
		var timebak = new Date().getTime();
	 	//var rowData = $("#grid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
		var patientId = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(patientId)) {
			openError('请选择一条数据',2000);
			return;
		}
		$.post("${ctx}/pro/pat/checkAppointmentDate",{'patientId':patientId},function(result){
			$('#dialog-confirm').modal('hide');
			if(result.success){
				openDialog3("${ctx}/pro/pat/openmodalAppointment?patientId="+patientId+'&time='+timebak);
			}
			else{
				$('#dialog-delete').modal('show');
			}
			$("#do_save").attr("disabled",false);
		},'json');
	}
	function toUpdate(){

		var patientId = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(patientId)) {
			openError('请选择一条数据',2000);
			return;
		}
		$("#do_update").attr("disabled",true);
		var timebak = new Date().getTime();
		openDialog2("${ctx}/pro/pat/updateAppointmentDate?patientId="+patientId+'&lccCode='+lccCode+'&time='+timebak);
	}
	
	
	
	function toAdd(){
		$("#do_add").attr("disabled",true);
		var timebak = new Date().getTime();
		openDialog("${ctx}/sys/drop/openmodalAddPage?timebak="+timebak);
	}
	
	//弹出对话框
	function openDialog(url,data){
		$( "#dialog-confirm" ).modal({
			 backdrop: 'static'
		});
		$( "#do_add").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm p" ).load(url);
	}
	//弹出对话框
	function openDialog2(url,data){
		$( "#dialog-confirm2" ).modal({
			 backdrop: 'static'
		});
		$( "#do_update").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm2 p" ).load(url);
	}
	//弹出对话框
	function openDialog3(url,data){
		$( "#dialog-confirm3" ).modal({
			 backdrop: 'static'
		});
		$( "#do_update").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm3 p" ).load(url);
	}
	//弹出对话框
	function openDialog4(url,data){
		$( "#dialog-confirm4" ).modal({
			 backdrop: 'static'
		});
		$( "#do_update").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm4 p" ).load(url);
	}
	function openError(message,delay){
    	$('#alert').show().find('strong').text(message);
    	window.setTimeout(function() {
    		$('#alert').slideUp("slow");
    	}, delay);
    }
	
	function showResult_1(result,message,delay){
		$("#messageSpanId_1").text(message);

		if (!delay || typeof(delay)=="undefined" || typeof(delay)!='number'){
			delay = 2000;
		}
	    if(result){
			$("#message_1").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
	    }else{
			$("#message_1").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
	    }
	    window.setTimeout(function() {
			$('#message_1').slideToggle(1000);
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
	
	
	
});

</script>


</body>





</html>