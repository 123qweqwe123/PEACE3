<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="${ctx}/static/echarts/build/dist/echarts-all.js"></script>
		<title>随机分组进度</title>
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
	</head>
	<body>
		<div id="navbar">
	    	<ul class="breadcrumb">
	        	<li class="active">随机对照分组</li>
	    	</ul>
		</div>
		
		<div id="jqgrid">
		    <table id="grid"></table>
		    <div id="pager"></div>
		</div>
	
		<div class="tab-content">
			<div role="tabpanel" class="tab-pane active" id="home">
				<div id="mainContent">
			        <div class="tableBg">
				        <div id="point" style="height:400px;"></div>
			        </div>
			        <br style="clear:both;" />
			    </div>
			</div>
	    </div>
	
	<script type="text/javascript">
		$(function() {
			var option = {
		        url : '${ctx}/random/group/getRanomGroupList',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : ['分组','总数','男','女','年龄1','年龄2','年龄3','心梗1','心梗2','教育1','教育2','教育3','医保1','医保2','医保3'],
		        colModel : [ 
		                    {name : 'R_GROUP', index : 'R_GROUP', width:'300', align:'center',sortable: false ,formatter : function(cellvalue, option, rowObjects) {
		                    	if(cellvalue=='01'){
		                    		return "非糖尿病实验组";
		                    	}else if(cellvalue=='02'){
		                    		return "非糖尿病对照组";
		                    	}else if(cellvalue=='11'){
		                    		return "糖尿病实验组";
		                    	}else if(cellvalue=='12'){
		                    		return "糖尿病对照组";
		                    	}
		                    }},
		                    
		                    {name : 'R_SUM', index : 'R_SUM', align:'center',sortable: false},
		                    
					        {name : 'SEX1', index : 'SEX1', align:'center',sortable: false},
					        {name : 'SEX2', index : 'SEX2', align:'center',sortable: false},
					        
					        {name : 'AGE_GRADE_1', index : 'AGE_GRADE_1', align:'center',sortable: false},
					        {name : 'AGE_GRADE_2', index : 'AGE_GRADE_2', align:'center',sortable: false},
					        {name : 'AGE_GRADE_3', index : 'AGE_GRADE_3', align:'center',sortable: false},
		                    
		                    {name : 'MEDICALHIS_MI_1', index : 'MEDICALHIS_MI_1', align:'center',sortable: false},
		                    {name : 'MEDICALHIS_MI_2', index : 'MEDICALHIS_MI_2', align:'center',sortable: false},
		                    
		                    {name : 'EDU_LEVEL_1', index : 'EDU_LEVEL_1', align:'center',sortable: false},
					        {name : 'EDU_LEVEL_2', index : 'EDU_LEVEL_2', align:'center',sortable: false},
					        {name : 'EDU_LEVEL_3', index : 'EDU_LEVEL_3', align:'center',sortable: false},
		                    
					        {name : 'MEDICALINSYRANCE_1', index : 'MEDICALINSYRANCE_1', align:'center',sortable: false},
					        {name : 'MEDICALINSYRANCE_2', index : 'MEDICALINSYRANCE_2', align:'center',sortable: false},
					        {name : 'MEDICALINSYRANCE_3', index : 'MEDICALINSYRANCE_3', align:'center',sortable: false}
		                    
		                   ],         
		        rowNum : 15, 
		        rowList : [ 15, 30, 50,100,500 ],      
		        height : "100%",
		        autowidth : true,  
		        pager : '#pager',  
		        altRows:true,
		        hidegrid : false, 
		        viewrecords : true, 
		        recordpos : 'left', 
		        sortorder : "ASC",
		        emptyrecords : "没有可显示记录", 
		        loadonce : true,
		        multiselect: false,
		 	};
			
			$("#grid").jqGrid(option); 
			// 设置出现水平滚动条
			$("#grid").closest(".ui-jqgrid-bdiv").css({ 'overflow-x' : 'scroll' });
			jqgridResponsive("grid",false); 
			
		});
		
		graphOption = {
		    tooltip : {
		        trigger: 'axis',
		        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
		            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
		        }
		    },
		    legend: {
		    	 data:['非糖尿病对照组','非糖尿病实验组','糖尿病对照组','糖尿病实验组']
		    },
		    grid: {
		        left: '3%',
		        right: '4%',
		        bottom: '3%',
		        containLabel: true
		    },
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value',
		            axisLabel : {
		                formatter: '{value} 人'
		            }
		        }
		    ],
		    series : [
		        {
		            name:'非糖尿病实验组',
		            type:'line',
		        },
		        {
		            name:'非糖尿病对照组',
		            type:'line',
		        },
		        {
		            name:'糖尿病实验组',
		            type:'line',
		        },
		        {
		        	name:'糖尿病对照组',
		            type:'line',
		        }
		    ]
		};
		
		function getPoint() {
			$.getJSON("${ctx}/random/group/getGroupSum",
		    function(data) {
		    	var chart = document.getElementById('point');
		        var echart = echarts.init(chart, 'macarons');
		        
		        echart.showLoading({  
		            text: '正在努力加载中...'
		        });
		        
		        graphOption.xAxis[0].data = data.dayList;
		        
		        graphOption.series[0].data = data.nonControlGroupList;
		        graphOption.series[1].data = data.nonTestGroupList;
		        graphOption.series[2].data = data.diabetesControlGroupList;
		        graphOption.series[3].data = data.diabetesTestGroupList;
		        
		        echart.setOption(graphOption, true);
		        
		        echart.hideLoading(); 
		    }); 
		}
		getPoint(); 
	</script>
</body>

</html>