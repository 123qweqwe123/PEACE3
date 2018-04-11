<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>样本管理</title> 
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
				if(field.name=='event_code'&&field.value =="请选择"){
					delete data[field.name]; 
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
	  <li class="active">样本管理</li><li class="active" >冻存盒查询</li>   
	</ul>  

	<form class="well bs-bdcor form-inline" id="searchForm">
	<div  class="row"> 
		<div  class="col-lg-1 col-xl-1 col-md-offset-1 col-md-1"> 冻存盒号: </div> 
		<div  class="col-lg-2 col-xl-2  col-md-2"> 
			<input type="text" class="form-control input-sm" name="boxCodes" id="boxCodes" value='' placeholder="输入关键字 模糊查询"/>
		</div>
		
		<div  class="col-lg-1 col-xl-1   col-md-1"> 医院名称:</div> 
		<div  class="col-lg-2 col-xl-2  col-md-2">
			<input  type="text" style="display:none;" class="form-control input-sm" name="lccid" id="lccid" value=''/>    
			<input type="text"   class="form-control input-sm" name="lcc_name" id="lcc_name" value='' placeholder="选择已有单位 双击选择 "/>  
		</div>  
    </div>
    
	<div  class="row" style="margin-top:5px;"> 
		<div  class="col-lg-1 col-xl-1  col-md-offset-1  col-md-1">所属冰箱</div> 
		<div  class="col-lg-2 col-xl-2  col-md-2">
			<input  type="hidden"  class="form-control input-sm" name="fridgeId" id="fridgeId" value=''/>    
			<input type="text"   class="form-control input-sm" name="fridge_name" id="fridge_name" value='' placeholder="选择已有冰箱 双击选择 "/>
		</div>
		
		<div  class="col-lg-1  col-xl-1 col-md-1">类型:</div> 
		<div  class="col-lg-2  col-xl-2  col-md-2">
			<select id="box_type" name="box_type" class="form-control"  >
                 <option value=''>全选</option> 
                 <option value='A'>A</option> 
                 <option value='B'>B</option> 
                 <option value='C'>C</option> 
                 <option value='D'>D</option> 
                 <option value='E'>E</option> 
                 <option value='F'>F</option> 
                 <option value='G'>G</option>      
            </select>               
      </div>          
   
     <div  class="col-lg-2  col-xl-2  col-md-2"> &nbsp;&nbsp;&nbsp;
    	<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
    </div>  
   </div>
</form> 

<div id="jqgrid"">
    <table id="grid"></table>
    <div id="pager"></div>
</div>


<script type="text/javascript">	
$.getJSON("${ctx}/combox/dataLimitLcc",function(data) { 
    $('#lcc_name').autocomplete(data,{
        minChars: 0,
        mustMatch:true, 
        width:260,
       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
        formatItem: function(item,i, max) {
            return '<table><tr><td width="80px;">' + item.LCC_CODE + '</td><td width="180px;">' + item.LCC_NAME + '</td></tr></table>';
            
        },
        // 指定 与 输入文字匹配的字段名
        formatMatch: function(item,i, max) {
            return item.LCC_NAME;
        },
        // 选中 某条记录在输入框里 显示的数据字段
        formatResult: function(item) {
            return item.LCC_NAME;
        }
    }); 
  //选中 某条记录 触发的事件
    $('#lcc_name').result(function(event, item){ 
		if(item) 
		$("#lccid").val(item.LCC_CODE); 
		else 
		$("#lccid").val(''); 
		initFridgeList();
     });
});

function initFridgeList(){
	$('#fridge_name').unautocomplete();
	$("#fridge_name").val(''); 
	$("#fridgeId").val(''); 
	
	$.getJSON("${ctx}/spem/iceBoxReg/fridgeList?lccId="+$("#lccid").val(),function(data) { 
		$('#fridge_name').autocomplete(data,{
		    minChars: 0,
		    mustMatch:true, 
		    width:260,
		   // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		    formatItem: function(item,i, max) {
		        return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="180px;">' + item.name + '</td></tr></table>';
		        
		    },
		    // 指定 与 输入文字匹配的字段名
		    formatMatch: function(item,i, max) {
		        return item.name;
		    },
		    // 选中 某条记录在输入框里 显示的数据字段
		    formatResult: function(item) {
		        return item.name;
		    }
		}); 
		//选中 某条记录 触发的事件
		$('#fridge_name').result(function(event, item){ 
		   if(item){
		   		$("#fridgeId").val(item.id);
		   }else{
		   		$("#fridgeId").val(''); 
		   }
		 });
	});
}


$(function(){
	
	var option = {
        url : '${ctx}/spem/iceBoxReg/boxList',    
        datatype : 'json',  
        mtype : 'POST',
        colNames : [ '项目单位','所属冰箱','冻存盒号','类型','已使用量'], //fridge_name
        colModel : [ 
                      
                     {name : 'lcc_name', index : 'LCC_NAME', align:'left' },
                     {name : 'fridge_name', index : 'FRIDGE_NAME', align:'left',sortable: false },
                     {name : 'boxCode',index : 'BOX_CODE'},
                     {name : 'boxType', index : 'BOX_TYPE', align:'left'}, 
                     {name : 'usage', index : 'TUBE_SUM', align:'left' ,sortable: false }
                   ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'BOX_CODE,LCC_NAME,BOX_TYPE',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "ASC",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        multiselect: false
       /*  ,loadComplete: Gosub */
 	};  
	$("#grid").jqGrid(option); 
	$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
	jqgridResponsive("grid",false); 

	initFridgeList();	
	$("#btnQuery").click(multipleSearch);
});

</script>


</body>
</html>