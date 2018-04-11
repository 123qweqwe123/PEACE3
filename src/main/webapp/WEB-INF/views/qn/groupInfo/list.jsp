<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
<title>患者入组情况</title>
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
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">患者入组情况</li>
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
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<fieldset>
                	<ol>
                         <li class="select-one"> <label form='lccRole'>医院名称:</label></li>
                         <li> 
                         <input type="hidden" id="lccCode" name="lccCode">
						 <input type="text" id="lccName" name="lccName"   class="form-control input-sm" placeholder="输入简拼或者LCCID">
                         <li> <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button></li>

						<li> <button type="button" id="btnExport" class="btn btn-primary btn-align-right btn-sm">导出</button></li>
                    </ol>
                    <ol>
                    </ol>
                </fieldset>
			</form>
		</div>
	</div>
	
	<div id="jqgrid">
	    <table id="grid"></table>
	    <%--<div id="pager"></div>--%>
	</div>
	
	<script type="text/javascript">
		$(function(){
			var option = {
		        url : '${ctx}/qn/pgroup/list',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : ['LCCID','医院名称','首次面访人数','应随机人数','死亡人数','签署知情同意数','随机率','糖尿病研究人数','非糖尿病研究人数','非干预面访数'],
		        colModel : [ 
		                    {name : 'LCC_CODE', align:'center',sortable: false },
					        {name : 'LCC_NAME', align:'left',sortable: false  },
		                    {name : 'FIRSTVIEW',align:'center',sortable: false},
		                    {name : 'RANDOM',align:'center',sortable: false},
		                    {name : 'DEAD',align:'center',sortable: false},
		                    {name : 'ISKNOW',align:'center',sortable: false},
		                    {name : 'RANDOMRATE', align:'center',sortable: false
//								,formatter : function(cellvalue, option, rowObjects) {
//		                    	if(rowObjects.TOTALCOUNT==0){
//		                    		return "0%";
//		                    	}else{
//		                    		return parseFloat(cellvalue*100).toFixed(0)+"%";
//		                    	}
//
//		                    }
		                    },
		                    {name : 'IS_DIABETES',align:'center',sortable: false},
		                    {name : 'NO_DIABETES',align:'center',sortable: false},
							{name : 'TELFVIEW',align:'center',sortable: false}
		                   ],         
		        rowNum : 150,
		        rowList : [ 15, 30, 50,100,500 ],
		        height : "100%",
		        autowidth : true,  
//		        pager : '#pager',
		        altRows:true, 
		        hidegrid : false, 
		        viewrecords : true, 
		        recordpos : 'left', 
		        sortorder : "ASC",
		        emptyrecords : "没有可显示记录", 
		        loadonce : false,
		        multiselect: false,
		 	};  
			$("#grid").jqGrid(option); 
			$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
			jqgridResponsive("grid",false); 
		
			$("#btnQuery").click(multipleSearch);
			
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
			
		});

		$("#btnExport").click(function(){
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
			window.open('${ctx}/qn/pgroup/export'+pram);
		});

	</script>
</body>

</html>