<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%
       response.setHeader("Pragma","No-Cache");
        response.setHeader("Cache-Control","No-Cache");
       response.setDateHeader("Expires", 0);
 %>
 <!DOCTYPE html>
<html>
<head>
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">
  </head>
 <body>
<style>

 .div_c{
    float: left;
    background-color:#71A7D8;
    border-radius: 4px;
    width: 120px;
    text-align: center;
    height: 60px;
    font-size: 12px;
    color: white;
    margin-right: 2px;
    margin-top:4px;
}
.div_d{
    background-color: red;
}
 .another{ color:red;}
</style>

    <div id="alertform" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
    
    <form id="supplier_form" class="form-horizontal">
		<div class="form-group" >
			<label class="col-lg-3 col-md-1  control-label" for="patientName"><span style="color: red">*</span>姓名:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="patientName" name="patientName" value="${ppk.patientName }" readonly="readonly" class="form-control input-sm" placeholder="姓名" required>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="patientId"><span style="color: red">*</span>PID:</label>
			<div class="col-lg-7 col-md-8">
			  
				<input type="text" id="patientId" name="patientId" value="${ppk.patientId}" readonly="readonly" class="form-control input-sm" placeholder="PID">
			</div>
		</div>
	    <div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="patientId"><span style="color: red">*</span>性别:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="patientId" name="patientId" value="<c:if test="${ppk.sex=='1'}">男</c:if><c:if test="${ppk.sex=='0'}">女</c:if>" readonly="readonly" class="form-control input-sm" placeholder="性别">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="localityFlag">出生日期:</label>
			<div class="col-lg-7 col-md-8">
			    <input type="text" id="patientId" name="patientId"  readonly="readonly" value=<fmt:formatDate value='${ppk.birthday}' pattern='yyyy-M-d' />
				class="form-control input-sm" placeholder="出生日期">
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="address">电话:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="address" name="address" value="${ppk.mobile }" readonly="readonly" class="form-control input-sm" placeholder="电话">
			</div>
		</div>
		
		<div class="form-group"  id="datediv">
			<label class="col-lg-3 col-md-1  control-label" for="changeComeDate">更改来院日期:</label>
			<div class="col-lg-7 col-md-8">
               <input type="text" class="form-control input-sm" name="changeComeDate" id="changeComeDate" value='' placeholder=""/>
			</div>
		</div>
		
		<!--  <c:if test="${pq.sex=='1'}">男</c:if>  -->
	    <!-- 
	    <div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="address">检查对应的次数:</label>
			   <div>
			   <c:forEach items="${listPCR }" var="pcr">
			   </br>
			
				<button type="button" id="version${pcr.version}" onClick="click(${ppk.patientId},${pcr.version})" class="btn btn-primary btn-align-right btn-sm">${pcr.version} 计划时间<fmt:formatDate value='${pcr.planTime}' pattern='yyyy-M-d' /> </button> 
			  </c:forEach>
				</div>
		</div>
		 -->
		  <div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="address">检查对应的次数:</label>
		  
		  
         <!--   
          <fmt:parseDate value="2015-01-02" var="date2"></fmt:parseDate> 
          <fmt:formatDate value="${date2 }" pattern="yyyy-MM-dd"/>  
           -->
		  
		 
		  <!--   <button type="button" id="version${pcr.version}" onClick="click(${ppk.patientId},${pcr.version})" class="btn btn-primary btn-align-right btn-sm">${pcr.version} 计划时间<fmt:formatDate value='${pcr.planTime}' pattern='yyyy-M-d' /> </button> -->
		  <!-- 
		  <div id="reservelist" >
		  <button type="button" id="version1" onClick="click(${ppk.patientId},1)" class="btn btn-primary btn-align-right btn-sm">第一次计划时间</button>
		  <button type="button" id="version2" onClick="click(${ppk.patientId},2)" class="btn btn-primary btn-align-right btn-sm">第二次计划时间</button>
		  <button type="button" id="version3" onClick="click(${ppk.patientId},3)" class="btn btn-primary btn-align-right btn-sm">第三次计划时间</button>
		  <button type="button" id="version4" onClick="click(${ppk.patientId},4)" class="btn btn-primary btn-align-right btn-sm">第四次计划时间</button>
		  <button type="button" id="version5" onClick="click(${ppk.patientId},5)" class="btn btn-primary btn-align-right btn-sm">第五次计划时间</button>
		  <button type="button" id="version6" onClick="click(${ppk.patientId},6)" class="btn btn-primary btn-align-right btn-sm">第六次计划时间</button>
		  </div>
		   -->
		    <div class="row">
			    <div class="div_c" id="version1" onclick="clickVersion(${ppk.patientId},1)">V1计划时间<br></div>
			    <div class="div_c" id="version2" onclick="clickVersion(${ppk.patientId},2)">V2计划时间<br></div>
			    <div class="div_c" id="version3" onclick="clickVersion(${ppk.patientId},3)">V3计划时间<br></div>
			</div>
		  </div>
		   <div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="address"></label>
			<div class="row">
			    <div class="div_c" id="version4" onclick="clickVersion(${ppk.patientId},4)">V4计划时间<br></div>
			    <div class="div_c" id="version5" onclick="clickVersion(${ppk.patientId},5)">V5计划时间<br></div>
			    <div class="div_c" id="version6" onclick="clickVersion(${ppk.patientId},6)">V6计划时间<br></div>
			</div>
			</div>
		
		
		<div class="form-group"  >
		  <label class="col-lg-3 col-md-3  control-label" for="result"><span class='checkhistorylog'>预约历史记录:</span></label>
		    <span class='checkhistorylog  hiddenlog'>点击查看</span>
		  <div class="col-lg-8 col-md-8" id="showHistoryReserveLog" hidden>
		        
		        <c:forEach items="${pcdsList }" var="item">
				
					 <span>  
                                                                      时间<fmt:formatDate value="${item.docTime}" type="both" pattern="yyyy/MM/dd" />
                       &nbsp; &nbsp; &nbsp; 计划第 <span style='background-color:red;'>${item.version }</span>( <fmt:formatDate value="${item.planTime }" type="both" pattern="yyyy/MM/dd" />)次检查  &nbsp; &nbsp;&nbsp; &nbsp;  &nbsp; &nbsp;&nbsp; &nbsp;结果: ${item.result } &nbsp; &nbsp; &nbsp; 备注: ${item.remark }
                     </span>
					 </br>
				</c:forEach>
		  </div>
		  
		
		</div>
		
		
		
		 <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="result">预约结果:</label>
            <div class="col-lg-8 col-md-8">
            
                <div class="checkbox-inline" style="margin-left:5px;">
                    <input name="resultCode" type="radio" class="changeDate" value="1">更改来院时间
                    <input name="resultCode" type="radio" value="2">可按计划日来院
                </div>
             </div>                 
             
        </div>
        
        
        
      
           <div class="form-group">
               
                <label class="col-lg-3 col-md-3  control-label" for="result">未来其他原因:</label>
               <div class="col-lg-8 col-md-8">
              
                <div class="checkbox-inline" style="margin-left:5px;">
                    <input name="resultCode" type="radio" value="3">在外地
                    <input name="resultCode" type="radio" value="4">行动不便
                   
                    
                </div>
                 <div class="checkbox-inline" style="margin-left:5px;">
                    <input name="resultCode" type="radio" value="5">拒绝来访
                    <input name="resultCode" type="radio" value="6">无法取得联系
                    <input name="resultCode" type="radio" value="7">其他情况
                </div>
             </div>
            
             <div class="form-group">
                <label class="col-lg-3 col-md-3  control-label" for="roles">备注:</label>
                 <div class="checkbox-inline" style="margin-left:5px;">
                    <input name="remark"  id="remark" type="text" value="">
                </div>
            </div>
		
		</div>
		<!-- 
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="status">状态:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="status" value="1" checked='checked' />有效&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status" value="2" ${supplier.status eq 2 ? "checked='checked'" : '' } />无效
			</div>
		</div>
		 -->
		<input id="id" name="id" value="" type="hidden" />
		<input id="maxVTime" name="maxVTime" value="${maxVTime}" type="hidden" />
		<input id="oldPlanDate" name="oldPlanDate" value="" type="hidden" />
		<input id="maxVersion" name="maxVersion" value="${maxVersion}" type="hidden" />
		<input id="realTime2" name="realTime2" value="${realTime}" type="hidden" />
		
	</form>
    
	<script type="text/javascript">
		$(function(){
			
			var str = '${listPcrStr}';
			var maxVersion = '${maxVersion}';
			var maxVTime = '${maxVTime}';
            var obj =eval('(' +str +')');
			
            if(obj!=null && obj !='undefinded'){
            	$.each(obj.datas,function(idx,item){
            	  
            		
            		if(item.realTime != "" && item.realTime!='undefinded'){
            			$("#version"+item.version).html("V"+item.version+"</br>计划时间"+item.vTime+"</br>完成时间"+item.realTime);
            		}else{
            			$("#version"+item.version).html("V"+item.version+"</br>计划时间"+item.vTime+"</br>预约时间"+item.planTime);
            		}
            		
            		
            		if(item.version&& item.version==maxVersion ){
        					  $("#version"+maxVersion).addClass("div_d");
        					  
        					  //给更改时间的控件初始化数据
        					  $("#changeComeDate").val(item.planTime);
        					  $("#oldPlanDate").val(item.planTime);
        	     	}
            		
            	});
            }
            
            var thisDate = new Date(maxVTime);
            var startDate=addDate(thisDate,-90);
            var endDate =  addDate(thisDate,90);
            
            // 最佳日期的 区间段
             var start30Date=addDate(thisDate,-30);
             var end30Date =  addDate(thisDate,30);
            
        	$('#changeComeDate').datepicker({
        	 	format: 'yyyy-mm-dd', 
        	 	weekStart: 1, 
        	 	//startDate:new Date(), //开始时间，在这时间之前都不可选
        		//endDate:'+3',//结束时间，在这时间之后都不可选
        	 	autoclose: true, 
        	 	//defaultDate:'-1M',
        	 	todayBtn: 'linked', 	
        		language: 'zh-CN',
        		
        		
        	 }).on('changeDate',function(ev){
        		 var date1 = ev.date.valueOf(); // 日期控件构建 长整数 
        		 var date2 = new Date(date1);   //转换成日期格式  选择后的日期
        		 date2.format("yyyy-MM-dd");    //格式化日期数据
        		 endDate.format("yyyy-MM-dd");
        		 
        		 if(date2>start30Date && date2<end30Date ){
        			 
        		 }else{
        			 alert("最佳预约时间段是"+ dateToStr(start30Date) + "------" +dateToStr(end30Date) );
        		 }
        		 
        		 $("#version"+maxVersion).html( 'V'+maxVersion+'</br>计划时间'+maxVTime+'</br>预约时间'+dateToStr(date2));
        		
        	 });
        	 var today = new Date();
        	 if(today>startDate){
        		 $('#changeComeDate').datetimepicker('setStartDate', dateToStr(today));
        	 }else{
        		 $('#changeComeDate').datetimepicker('setStartDate', dateToStr(startDate));
        	 }
        	
        	 $('#changeComeDate').datetimepicker('setEndDate', dateToStr(endDate));
            
        	$(".checkhistorylog").click(function(){
        		
        		$("#showHistoryReserveLog").show();
        		$(".hiddenlog").hide();
        		
        		
        		
        	})
        	
        	$(".changeDate").click(function(){
        	    $("#changeComeDate").focus(); 
        	})


        	
        	
        	Date.prototype.format = function(format){ 
        		var o = { 
        		"M+" : this.getMonth()+1, //month 
        		"d+" : this.getDate(), //day 
        		"h+" : this.getHours(), //hour 
        		"m+" : this.getMinutes(), //minute 
        		"s+" : this.getSeconds(), //second 
        		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
        		"S" : this.getMilliseconds() //millisecond 
        		}; 
        		if(/(y+)/.test(format)) { 
        		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
        		} 
        		for(var k in o) { 
        		if(new RegExp("("+ k +")").test(format)) { 
        		format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
        		} 
        		} 
        		return format; 
        		}; 


        	
        		

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
        	
        	
        		function addDate(thisDate,dadd){
        			if(thisDate==null){
        				var a = new Date();
        			}else{
        				var a = thisDate;
        			}
        			
        			a = a.valueOf();
        			a = a + dadd * 24 * 60 * 60 * 1000;
        			a = new Date(a);
        			return a;
        			}
			
		})
		
		
		
		
        		function dateToStr2(datetime){

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
        	
		               function clickVersion(patientId , version){
			
                		var data = {};
                		//var patientId= $("#patientId").val();
                		if(patientId=='undefinded' ||patientId.length<1 ) return ;
                		data.patientId = patientId;
                		//var changeComeDate = $("#changeComeDate").val();
                		if(version=='undefinded' ||version.length<1 ) return ;
                		data.version =version;
                		//新增时注释id值
                		//data['id'] = null;
                		$.post("${ctx}/reserve/findPatientVersionList",data,function(result){
                			//$("#grid").trigger("reloadGrid");
                			 $(".hiddenlog").hide();
                			 $("#showHistoryReserveLog").show();
                			 $("#showHistoryReserveLog").empty();
                			 var str  ="";
                             if(result!=null && result !='undefinded'){
                    	              $.each(result.data,function(idx,item){
                    	            	  var date2 = new Date(result.data[idx].docTime);   //转换成日期格式
                    	            	  date2.format("yyyy-MM-dd");    //格式化日期数据
                    	            	   var date3 = new Date(result.data[idx].planTime);   //转换成日期格式
                    	            	   date3.format("yyyy-MM-dd");   
                    	            	 if(result.data[idx].remark ==null || result.data[idx].remark =='null' ||  result.data[idx].remark.length<0){
                    	            		 str  += "时间"+dateToStr2(date2)+" &nbsp; &nbsp; &nbsp; 计划第  <span style='background-color:red;'>"+result.data[idx].version +"</span>("+dateToStr2(date3)+")次检查  ; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 结果:"+ result.data[idx].result  +"    &nbsp; &nbsp; &nbsp; </br>" ;
                    	            	 }else{
                    	            		 str  += "时间"+dateToStr2(date2)+" &nbsp; &nbsp; &nbsp; 计划第  <span style='background-color:red;'>"+result.data[idx].version +"</span>("+dateToStr2(date3)+")次检查  ; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; 结果:"+ result.data[idx].result  +"    &nbsp; &nbsp; &nbsp; 备注:"+ result.data[idx].remark +"</br>" ;
                    	            	 }
                                       
                    	              })
                             }
                             $("#showHistoryReserveLog").html(str);
                		},'json');
                	}
        		

		
		
		  
    	
    	

        	
	</script>
	
	</body>
	</html>
