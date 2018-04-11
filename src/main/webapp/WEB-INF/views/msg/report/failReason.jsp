<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
	<head>
		<title>短信发送失败原因</title>
		<script src="${ctx}/static/echarts/esl/esl.js"></script> 
	</head>
	
	<body>
		<div id="main-content">
			<div class='row'>
				<div class="col-lg-4 col-md-4">
					<div id="jqgridReason"></div>
				</div> 
				<div class="col-lg-8 col-md-8" style="padding-left:100px;">
			       <div id="main" style='width:500px;height:400px'></div> 
				</div>  
			</div>
		</div>
		
<script type="text/javascript">
$(function (){ 	
<!------------------------------ 引入echarts 开始 ------------------------------------------->
	require.config({
	    paths:{ 
	        'echarts' : '${ctx}/static/echarts/echarts',
	        'echarts/chart/pie' : '${ctx}/static/echarts/echarts'
	    }  
	}); 

	function makePie(xmlobj) {
		var cols = xmlobj.list;
		var cdata = [];
		var vdata = [];
		for(var i in cols){
	         name  = cols[i].SEND_REASON;
	         value = cols[i].SUM;
	         cdata.push(name);
	         obj = new Object() ;
	         obj.value= value;
	         obj.name = name;
	         vdata.push(obj);
	     } 
		
		option = {
       		title : {
                   text: '短信发送失败原因',
                   x:'center'
               },
       	    tooltip : {
       	        trigger: 'item',
       	        formatter: "{a} <br/>{b} : {c} ({d}%)"
       	    },
       	    legend: {
                   orient : 'vertical',
                   x : 'left',
                   data:cdata
               },
       	    series : [
       	        {
       	            name: '短信发送失败原因',
       	            type: 'pie',
       	            radius : '55%',
       	            center: ['50%', '60%'],
       	            data:vdata
       	        }
       	    ]
       	};
		
		// 使用
		require(
		    [
		        'echarts',
		        'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
		    ], 
		    function (ec) {
		        // 基于准备好的dom，初始化echarts图表
		        var myChart = ec.init(document.getElementById('main')); 
		          
		        // 为echarts对象加载数据 
		        myChart.setOption(option); 
		    }
		);
	}
<!----------------------------------------- 引入echarts 结束 ---------------------------->    	 

<!----------------------------------jqgrid---------------------------------------------------->
	function makeTable(xmlobj){    
		$("#jqgridReason").html('<table id="gridReason"></table>');
    
    	var option = {
   			data: xmlobj.list, 
            datatype: "local" , 
            colNames : [ '错误原因','错误个数'],
            colModel : [ 
						{name : 'SEND_REASON', align:'center' , sortable:false, width:'200px'},
                        {name : 'SUM', align:'center' , sortable:false, width:'100px'}
                      ],
            altRows:true,
            hidegrid : false, 
            viewrecords : true, 
            recordpos : 'left', 
            sortorder : "desc",
            emptyrecords : "没有可显示记录", 
            loadonce : false
            
    	};
	    $("#gridReason").jqGrid(option);
	    //jQuery("#gridReason").jqGrid('addRowData', 1, xmlobj);
	    //自适应
	    jqgridResponsive("grid",true);
	}
<!--------------------------------jqgrid------------------------------------------------------->

function pageLoader(){
	var lccCode = $('#selLccCode').val();
	var group = $('#group').val();
	var startDate =  $('#startDate').val();
	var endDate = $('#endDate').val();
	
	$.ajax({ 

	    type: "get", 

	    url:'${ctx}/msg/report/getFailList',  
	    
	    dataType: "json",
	    
	    data: "lccCode="+lccCode+"&group="+group+"&startDate="+startDate+"&endDate="+endDate,

	    cache:false, 

	    async:false, 
	    
	    success: function(xmlobj){ 
	       if(! (xmlobj ))
	           return;
	       makeTable(xmlobj)
	       makePie(xmlobj);
	    },
	        
	    failure:function (result) {  
	         alert('Failed');  
	    }
	 }); 
} 

pageLoader(); 
// debugger;	
// var data = '${xmlobj}'.replace(/=/g,':');
// var xmlobj = eval('(' + data + ')');

// makeTable(xmlobj);
// makePie(xmlobj);
});

     
</script>
</body>
</html>