<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>调查员工作量统计</title> 
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
	  <li class="active">质量管理</li><li class="active" >现场考核</li><li class="active">调查员工作量统计</li>
	</ul>  
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row"> 
					<div  class="leftLable col-md-1">省:</div> 
					<div  class="col-md-2"> 
						<input type="hidden" name="provinceCode" id="provinceCode"/>
						<input type="text" class="form-control input-sm" name="provinceName" id="provinceName" value='' placeholder="请输入名称简拼"/>
					</div>
					
					<div  class="leftLable col-md-1">项目点:</div> 
					<div  class="col-md-2"> 
						<input type="hidden" name="rccCode" id="rccCode"/>
						<input type="text" class="form-control input-sm" name="rccName" id="rccName" value='' placeholder="请输入名称简拼"/>
					</div>

					<div  class="leftLable col-md-1">医院名称:</div> 
					<div  class="right col-md-2"> 
						<input type="hidden" name="lccCode" id="lccCode"/>
						<input type="text" class="form-control input-sm" name="lccName" id="lccName" value='' placeholder="请输入名称简拼"/>
					</div>
					
					<div  class="leftLable col-md-1">调查员代码:</div> 
					<div  class="right col-md-1"> 
						<input type="text" class="form-control input-sm" name="operatorId" id="operatorId" value='' placeholder=""/>
					</div>
				</div>
				
				<div  class="row" style="margin-top:5px;"> 
					<div  class="leftLable col-md-1">调查员姓名:</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="operatorName" id="operatorName" value='' placeholder=""/>
					</div>
					
					<div  class="leftLable col-md-1">起止时间:</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="startDate" id="startDate" value='' placeholder=""/>
					</div> 
					<div  class="col-md-1" style="width:5%">-----</div> 
					<div  class="right col-md-2"> 
						<input type="text" class="form-control input-sm" name="endDate" id="endDate" value='' placeholder=""/>
					</div>
					
					<div  class="right col-md-2"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
					</div>
				</div>
			</form>
		</div>
	</div>

<div id="jqgrid"">
    <table id="grid"></table>
    <div id="pager"></div>
</div>


<script type="text/javascript">
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
initProvince();

$(function(){
	
	var option = {
        url : '${ctx}/quality/inWorkStatistics/list',    
        datatype : 'json',  
        mtype : 'POST',
        colNames : [ '省','项目点编码','LCCID','医院名称','调查员代码','调查员姓名','基础信息问卷','初筛问卷','高危问卷','干预','1月随访','长期随访'], //fridge_name
        colModel : [ 
                      
                     {name : 'provinceName', index : 'provinceName', align:'left' },
                     {name : 'lccCode', index : 'lccCode',formatter : function(cellvalue, option, rowObjects) {
							return cellvalue.substr(0,4);
						}, align:'left',sortable: false },
					 {name : 'lccCode', index : 'lccCode', align:'left',sortable: false },
					 {name : 'lccName', index : 'lccName', align:'left' ,sortable: false},
					 {name : 'operatorId', index : 'operatorId', align:'left' ,sortable: false},
					 {name : 'operatorName', index : 'operatorName', align:'left',sortable: false },
                     {name : 'paper1Count', index : 'paper1Count', align:'center',sortable: false },
                     {name : 'paper2Count', index : 'paper1Count', align:'center',sortable: false },
                     {name : 'paper3Count', index : 'paper1Count', align:'center',sortable: false },
                     {name : 'paper4Count', index : 'paper1Count', align:'center',sortable: false },
                     {name : 'paper5Count', index : 'paper1Count', align:'center',sortable: false },
                     {name : 'paper6Count', index : 'paper1Count', align:'center',sortable: false }
                   ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'provinceName,lccCode',
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
	
	$("#startDate").datepicker('option', 'dateFormat','yy-mm-dd');
	$("#endDate").datepicker('option', 'dateFormat','yy-mm-dd');
});

</script>


</body>
</html>