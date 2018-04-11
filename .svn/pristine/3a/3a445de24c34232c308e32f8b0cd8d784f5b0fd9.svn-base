<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%--<%@page import="com.bdcor.pip.core.utils.Securitys"%>  --%>

<!DOCTYPE html>
<html>
<head>
 <title>日历管理</title>


	<style>
		.datetimepicker td {
			border: 1px solid gray;
		}
	</style>
</head>
<body>
<script type="text/javascript" src="${ctx}/static/rili/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/static/datepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>


<div id="navbar">
       <ul class="breadcrumb">
         <li class="active"></li>
      </ul>
</div>
					 <!--  <input type="text" class="form-control input-sm" name="planTime2" id="planTime2" value='' placeholder="" data-date-format="yyyy-mm-dd"/>  -->

<%--<div style="text-align:right"><b style="color:#f00">●</b>&nbsp;注解&nbsp;&nbsp;&nbsp;<b style="color:#FFD700">●</b>&nbsp;注解</div>--%>

<div class="input-append date" id="datetimepicker" value="" data-date-format="yyyy-mm-dd" >
</div>


<%--<div class="input-append date" id="test" value="" data-date-format="yyyy-mm-dd" >--%>
<%--</div>--%>

<%--<script type="text/javascript">--%>
    <%--$("#test").datetimepicker({format: 'yyyy-mm-dd'});--%>
<%--</script>--%>

<script type="text/javascript">

var data =${datas};
var  year = ${year};
//var data =[{day:'9_1',value:0,red:0,blue:3},{day:'9_2',value:0,red:0,blue:3}];
$(document).ready(function(){

    $('#datetimepicker').datetimepicker({
		//autoTop:1,
        changeMonth: true,
//        changeYear: true,
		consumerText:true,
		gapWidth:150,
		gapHeight:100,
        weekStart : 1 , // 周一开始
        todayHighlight : true, // 高亮显示今天
		autoclose:false,
		language:'zh-CN',
		format : 'yyyy-mm-dd',
//		bootcssVer: 3 , // 显示上月/下月按钮图标
		consumerClick:function(e){ 
			var id = e.target.id;
			var m = id.split('_');
            (m[1] - 0) < 10 ? m[1] = '0'+m[1] : m[1];
            (m[2] - 0) < 10 ? m[2] = '0'+m[2] : m[2];
			window.location.href="${ctx}/reserve/reservelist?ismsg=${ismsg}&status=0&rq="+year+"-"+m[1]+"-"+m[2];
		},
		consumerRedClick:function(e){ 
			var id = e.target.id;
			var m = id.split('_');
            (m[1] - 0) < 10 ? m[1] = '0'+m[1] : m[1];
            (m[2] - 0) < 10 ? m[2] = '0'+m[2] : m[2];
			window.location.href="${ctx}/reserve/reservelist?ismsg=${ismsg}&status=1&rq="+year+"-"+m[1]+"-"+m[2];
		},
		consumerBlueClick:function(e){
			var id = e.target.id;
			var m = id.split('_');
            (m[1] - 0) < 10 ? m[1] = '0'+m[1] : m[1];
            (m[2] - 0) < 10 ? m[2] = '0'+m[2] : m[2];
			window.location.href="${ctx}/reserve/reservelist?ismsg=${ismsg}&status=2&rq="+year+"-"+m[1]+"-"+m[2];
		},
        OnChangeMonthData : function(e , obj){
            year = e.getFullYear();
            var dateStr = e.getFullYear()+"-"+(e.getMonth()+1);
            $.post("${ctx}/reserve/getdateList",{"date" : dateStr, "ismsg" : ${ismsg}},function(value){
                data = JSON.parse(value);
                obj.fullConsumerText(value);
            });
        }
	});
	
	
	
	$('#datetimepicker').datetimepicker('fullConsumerText',data);
	

	function dateToStr(datetime){

			 var year = datetime.getFullYear();
			 var month = datetime.getMonth()+1;//js从0开始取 
			 var date = datetime.getDate(); 
			 var hour = datetime.getHours(); 
			 var minutes = datetime.getMinutes(); 
			 var second = datetime.getSeconds();
			 
			 if(month<10){
			  month = "0" + month;
			 }
			 if(date<10){
			  date = "0" + date;
			 }
			 if(hour <10){
			  hour = "0" + hour;
			 }
			 if(minutes <10){
			  minutes = "0" + minutes;
			 }
			 if(second <10){
			  second = "0" + second ;
			 }
			 
			 var time = year+"-"+month+"-"+date; //2009-06-12 17:18:05
			// alert(time);
			 return time;
			}
});
</script>
</body>
</html>