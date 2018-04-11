<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title>经费使用情况</title>
 <script type="text/javascript" src="${ctx}/static/echarts/esl/esl.js"></script>

</head>
<body>

<script >



$(function (){ 
	
	
});

</script>





<ul class="breadcrumb">
  <li class="active">经费管理</li><li class="active" >中心运作费用查询</li>
</ul>

<form class="well bs-bdcor form-inline" id="searchForm">
    
    <!-- <table class="table table-bordered" > 
        <tr class="active">
           <td colspan="1">
             <div class="col-lg-3 col-md-3" >开始时间:
             <div class="input-group date" id="duedate1">
                 <input class="form-control input-sm"  id="dateFrom" name="dateFrom" type="text" readonly />
                <span class="input-group-addon input-sm btn">
                <i class="glyphicon glyphicon-calendar"></i>
                </span>
             </div>
            </div>
         </td> 
           <td colspan="1">
              <div class="col-lg-2 col-md-2" >结束时间:
                 <div class="input-group date"  id="duedate2">
                   <input class="form-control input-sm" id="dateTo" name="dateTo" type="text" readonly />
                     <span class="input-group-addon input-sm btn">
                       <i class="glyphicon glyphicon-calendar"></i>
                     </span>
                  </div>
             </div>         
           </td>  
           <td colspan="1">
                          记账类型:
                  <select>
                      <option>全部</option>
                  </select>  
           </td>  
        </tr>   
         
         <tr>  
            <td  colspan="2"> 中心部门
	               <select>
	                  <option>全部</option>
	               </select>
              
            </td>
            
             <td colspan="1"   >
                <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button> 
             </td>     
            
        </tr>  
    </table> -->
    
    <!--  <div  class="row"> 
        <div  class="col-lg-1 col-xl-1  col-md-1">  col-md-offset-1
                  统计类型:
        </div> 
        <div  class="col-lg-3 col-xl-3  col-md-3"> 
              <select id="qType" name="qType" class="form-control"  >
                 <option value='b'>按部门</option>  
                 <option value='t'>按类型</option>    
            </select>    
        </div> 
       <span  class="col-lg-1 col-xl-1   col-md-1">
                   记账类型:       
       </span> 
       
       <div  class="col-lg-2 col-xl-2  col-md-2">
                <select id="feeType" name="feeType" class="form-control"  >
                 <option value=''>请选择</option>     
            </select> 
       </div>  
    </div>  
    <hr>   -->
    <div  class="row" > 
	    <div  class="col-lg-1 col-xl-1  col-md-1">  <!-- col-md-offset-1 -->
	            时间点:  
	    </div> 
     
     <div  class="col-lg-3 col-xl-3  col-md-3">
             <div class="input-group date"  id="duedate2">
                  <input class="form-control input-sm" id="dateTo" name="dateTo" type="text" readonly />
                  <span class="input-group-addon input-sm btn">
                          <i class="glyphicon glyphicon-calendar"></i>
                  </span>
              </div>
           
     </div> 
    
          
   
     <div  class="col-lg-3  col-xl-3  col-md-3"> 
     &nbsp;&nbsp;&nbsp;
    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
    <!-- <button type="reset" id="reset_btn" class="simpled btn btn-sm btn-primary" >重置</button>  -->
    </div>  
   </div>
    
    
   <%
  /*  //String s   = "['2014-07-01','2014-07-02','2014-07-03','2014-07-04','2014-07-15']";
   java.util.List l = new java.util.ArrayList();
   java.util.Map m =  new java.util.HashMap();
      m.put("name", "差旅费");
      m.put("type", "line");  // type:'line',
      
      
     
  
      
      java.util.List l2 = new java.util.ArrayList();
      l2.add(8000);
      l2.add(5000);
      l2.add(3000);
      l2.add(2000);
      l2.add(7000);
      l2.add(9000);
      m.put("data", l2);
      
	l.add(m);

	 String s = com.bdcor.pip.core.mapper.JsonMapper.nonEmptyMapper().toJson(l);
	
	 */
%>  
   
   
</form>
<div id="feebyDepart" style="height:400px"> </div>

<hr>
<div id="main" style="height:400px"></div>




<script type="text/javascript"> 



$(function (){ 
	
<!------------------------------ 引入echarts 开始 ------------------------------------------->
  require.config({   
	    paths:{ 
	        'echarts' : '${ctx}/static/echarts/echarts',
	        'echarts/chart/bar' : '${ctx}/static/echarts/echarts'
	    }   
  }); 
  
function feeStatics(){
	
 
  $.getJSON("${ctx}/combox/comboxData?table=PIP_FEE_REGTYPE&cols=TYPE_CODE|TYPE_NAME&where=",function(d) {
	  var feeTypes = []; 
	  
	  var xaxis = [];
	  
	  var series = [];   
	   $.each(d, function(i, field){
	          feeTypes.push(field.TYPE_NAME);
	      }); 
	   
	      var dateTo =  $("#dateTo").val();
      
	      $.getJSON("${ctx}/fee/getFeeStatisticsByCategory?dateTo="+dateTo, {dateTo:dateTo},function(data) {
	    	  if(!!data.xAxis)
	               xaxis  = data.xAxis;
	    	  if(!!data.series)
	             series = data.series;  
	          makePie();
	       });
	      
	      
	      function makePie(){
	    	     
	    	    require(
	    	        [
	    	            'echarts',
	    	            'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
	    	        ], 
	    	        function (ec) { 
	    	            // 基于准备好的dom，初始化echarts图表
	    	            var myChart = ec.init(document.getElementById('main')); 
	    	           
	    	            var option = {
	    	                    title : {  
	    	                        text: '费用类型统计',
	    	                        subtext: '按费用类型'
	    	                    },
	    	                    tooltip : {
	    	                        trigger: 'axis'
	    	                    },
	    	                    legend: {
	    	                        orient:'vertical',
	    	                        x:'right',
	    	                        y:'60px',
	    	                        data:feeTypes
	    	                    },
	    	                    grid:{
	    	                    	
	    	                    	x2:128
	    	                    },
	    	                    toolbox: {
	    	                    	show : false,
	    	                        feature : {
	    	                            mark : {show: true},
	    	                            dataView : {show: true, readOnly: false},
	    	                            magicType : {show: true, type: ['bar','line']},
	    	                            restore : {show: true},
	    	                            saveAsImage : {show: true}
	    	                        }
	    	                    },
	    	                    xAxis : [
	    	                        {
	    	                            type : 'category',
	    	                            splitNumber: 7,
	    	                            boundaryGap: false,
	    	                            data : xaxis // ['一月','二月','三月','四月','五月','六月']
	    	                        }   
	    	                    ],
	    	                    yAxis : [
	    	                        {
	    	                            type : 'value',
	    	                            axisLabel : {
	    	                                formatter: '{value} 元'
	    	                            }
	    	                        }
	    	                    ],
	    	                    series : series 
	    	                };
	    	            // 为echarts对象加载数据 
	    	            myChart.setOption(option); 
	    	        }   
	    	      ); 
	  } 
	      
});
  
}   
  
feeStatics();  

  

	

 

function feeStaticsbyDepart(){


$.getJSON("${ctx}/combox/comboxData?table=PIP_FEE_DEPT&cols=DEPT_CODE|DEPT_NAME&where=",function(d) {
    var departName = []; 
    
    var xaxis = [];
    
    var series = [];   
     $.each(d, function(i, field){
            departName.push(field.DEPT_NAME);
        }); 
     
        var dateTo =  $("#dateTo").val();
    
        $.getJSON("${ctx}/fee/getFeeStatisticsByDepart?dateTo="+dateTo, {dateTo:dateTo},function(data) {
            if(!!data.xAxis)
                 xaxis  = data.xAxis;
            if(!!data.series)
               series = data.series;  
            makeLinebyDepart();
         });
        
        
        
        function makeLinebyDepart(){
            // 使用
            require(
                [
                    'echarts',
                    'echarts/chart/bar' // 使用柱状图就加载bar模块，按需加载
                ], 
                function (ec) {
                    // 基于准备好的dom，初始化echarts图表
                    var myChart = ec.init(document.getElementById('feebyDepart')); 
                   
                    var option = {
                            title : {  
                                text: '部门费用统计',
                                subtext: '按部门统计'
                            },
                            tooltip : {
                                trigger: 'axis'
                            },
                            grid:{
                            	x:80,
                            	x2:128
                            },
                            legend: {
                                orient:'vertical',
                                x:'right',
                                y:'60px',
                                data:departName
                            },
                            toolbox: {
                            	show : false,
                                feature : {
                                    mark : {show: true},
                                    dataView : {show: true, readOnly: false},
                                    magicType : {show: true, type: ['line', 'bar']},
                                    restore : {show: true},
                                    saveAsImage : {show: true}
                                }
                            },
                           xAxis : [
                                {
                                    type : 'category',
                                    data : xaxis
                                }   
                            ],
                            yAxis : [
                                {
                                    type : 'value',
                                    axisLabel : {
                                        formatter: '{value} 元'
                                    }
                                }
                            ],
                            series :series 
                           
                        };
                    // 为echarts对象加载数据 
                    myChart.clear();
                    myChart.setOption(option); 
                    myChart.refresh();
                    myChart.resize();
                }   
              ); 
          }    
  
  
        
});
} 
  
feeStaticsbyDepart();
  




var multipleSearch = function() {
    
    feeStatics();
    feeStaticsbyDepart();
 
};





$("#btnQuery").click(multipleSearch);   
 
  
<!----------------------------------------- 引入echarts 结束 ---------------------------->    	   

var date=new Date();  
date.setDate(date.getDate()-2);
var dayBefore2 = DateFormat.format(date, 'yyyy-MM-dd'); 
date.setDate(new Date().getDate()-1);
var dayBefore1 = DateFormat.format(date,'yyyy-MM-dd');

initDatePicker(
        '#duedate1','#dateFrom',
        '#duedate2','#dateTo',
       function(){
           $("#submitButton").attr('disabled',false);
           $('#alert').hide();
       },
       function(){
           $("#submitButton").attr('disabled',true);               
       },dayBefore2,dayBefore1);        
   });  
</script> 

</body>
</html>