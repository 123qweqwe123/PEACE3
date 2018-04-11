<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
 <title>随访人员管理</title>
 <script  src="${ctx}/static/echarts/esl/esl.js"></script> 
	<style>
		.img_mystyle{
			width: 20px;
			height: 20px;
		}
	</style>
</head>
<body>

<div id="navbar">
       <ul class="breadcrumb">
         <li class="active">随访人员管理</li>
 
      </ul>
</div>
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row"> 
					<div  class="leftLable col-md-1" style="text-align: right">医院名称:</div>
					<div  class="leftLable col-md-2"> 
		          	<input id="lccCode" type="hidden" name="lccCode" />
					<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					<div  class="leftLable col-md-1" style="text-align: right">PID:</div>
					<div  class=" leftLable col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>
					
					<div  class="leftLable col-md-1" style="text-align: right">患者姓名:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
					</div>
					<div  class="leftLable col-md-1" style="text-align: right">身份证:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" id="idNumber" name="idNumber" value='' placeholder="身份证"/>
					</div>
					</div>
				<div class="row" style="margin-top: 5px;">
					<div  class="leftLable col-md-1" style="text-align: right">所属组别:</div>
					<div  class="col-md-2"> 
						<select class="form-control input-sm" id="belongGroup" name="belongGroup" placeholder="请选择">
							<option value ="">全部</option>
							<option value="01">非糖尿病实验组</option>
							<option value="02">非糖尿病对照组</option>
							<option value="11">糖尿病实验组</option>
							<option value="12">糖尿病对照组</option>
						</select>
					</div>
					<div  class="leftLable col-md-1"></div> 
                    <div  class="col-md-2"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
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
	      var idNumber =$("#idNumber").val();
	      if(idNumber && ''!=idNumber){
	          mydata.idNumber = idNumber;
	      }else{
	          delete postData.idNumber;
	      }
	      var belongGroup =$("#belongGroup").val();
	      if(belongGroup && ''!=belongGroup){
	          mydata.belongGroup = belongGroup;
	      }else{
	          delete postData.belongGroup;
	      }
	     $.extend(postData,mydata);
	     $("#grid").jqGrid("setGridParam", {
	         search: true  
	     }).trigger("reloadGrid", [{page:1}]);
	
		
	};  
	
	var option = {
        url : '${ctx}/msg/followVisit/list',
        datatype : 'json',  
        mtype : 'POST',
        colNames : [ 'LCCID','PID','患者姓名','性别','身份证','固定电话','手机','第一联系人姓名','与本人关系','第一联系人手机','所属组别','分组日期'],
        colModel : [ 
					 {name : 'lccCode',width:'80px', index : 'lccCode', align:'center',sortable: false },
                     {name : 'patientId',width:'100px', index : 'patientId', align:'center',sortable: false },
 			         {name : 'patientName', width:'60px', index : 'patientName', align:'center',sortable: false  },
 			         {name : 'sex', index : 'sex',width:'60px', align:'center',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue=='1'){return '男';}return '女';} },
 			         {name : 'idNumber',width:'160px', index : 'idNumber', align:'center',sortable: false },
                     {name : 'phone', index : 'phone',width:'100px', align:'center',sortable: false },
                     {name : 'mobile', width:'100px',index : 'mobile', align:'center',sortable: false },
                     {name : 'linkMan1',width:'120px', align:'center',sortable: false },
                     {name : 'linkMan1Relation', width:'80px', align:'center',sortable: false },
                     {name : 'linkMan1Mobile',width:'100px', align:'center',sortable: false },
                     {name : 'belongGroup', width:'100px',index : 'belongGroup', align:'center',sortable: false ,formatter:function(cellvalue, option, rowObjects){
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
					 }},
                     {name : 'groupDate', width:'80px',index : 'groupDate', align:'center',sortable: false,formatter:yymmddFormatter }
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
	);
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
	
	
	function openError(message,delay){
    	$('#alert').show().find('strong').text(message);
    	window.setTimeout(function() {
    		$('#alert').slideUp("slow");
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