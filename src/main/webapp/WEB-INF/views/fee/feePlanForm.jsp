<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<form id="feereg_form" class="form-horizontal">
	<%-- <div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="projectId">
		<span style="color: red">*</span>当前项目名:</label>
		<div class="col-lg-5 col-md-5">
			<input type="text" id="projectId" name="projectId"
				class="form-control input-sm" readonly value="${pv.projectId}" required>
		 
		</div>  
	</div> --%>

	<div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="organization"><span
			style="color: red">*</span>项目单位:</label>
		<div class="col-lg-5 col-md-5">
		
		<label class=" control-label"  > ${pv.organId}</label> 
		<%--  <input type="hidden" class="form-control input-sm"
                name="organId" id="organId"  value="${pv.organId}"/> 
    
            <input type="text"  class="form-control input-sm" readonly name="organization" id="organization" value="${pv.organId}" placeholder="项目单位" />
 --%>
           <!--  <div id="organizationZtree" class="tree-select">
                <div id="organization_ztree" class="ztree"
                    style="height: 190px; overflow-y: scroll;"></div> 
            </div> -->
		</div>
	</div>

	<div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="amount"><span
			style="color: red">*</span>到位金额:</label>
		<div class="col-lg-5 col-md-5"> 
        <input type="text"   class="form-control input-sm" name="amount" id="amount" value="${pv.amount}" placeholder="到账金额" />
    </div> 
	</div>

	<!-- <div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="activat_time"><span
			style="color: red">*</span>到账时间:</label>
		<div class="col-lg-5 col-md-5">
			<div class="input-group date" id="duedate1">
                 <input class="form-control input-sm"  id="activat_time" name="activat_time" type="text" />
                <span class="input-group-addon input-sm btn">
                <i class="glyphicon glyphicon-calendar"></i>
                </span>
                  </div>
		</div>
	</div>


	<div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="plan_complete_time">计划用完时间:</label>
		<div class="col-lg-5 col-md-5">
			<div class="input-group date" id="duedate2">
                 <input class="form-control input-sm"  id="plan_complete_time" name="plan_complete_time" type="text" />
                <span class="input-group-addon input-sm btn">
                <i class="glyphicon glyphicon-calendar"></i> 
                </span>
                  </div>
		</div>
	</div>  --> 
	
	
	 <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="activat_time"><span
            style="color: red">*</span>到位时间:</label>
            <div class="col-lg-5 col-md-1">
                <div class="input-group date" id="activat_timeDiv" data-date-format="yyyy-mm-dd">   
                    <input class="form-control input-sm" id="activat_time" name="activat_time" value="${pv.activat_timeString}" type="text" placeholder="到账时间" readonly />
                    <span class="input-group-addon input-sm btn"> 
                    <i class="glyphicon glyphicon-calendar "></i>
                    </span>
                </div>
            </div>
        </div>
        
        
         <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="plan_complete_time"><span
            style="color: red">*</span>执行时限:</label>
            <div class="col-lg-5 col-md-1">
                <div class="input-group date" id="plan_complete_timeDiv" data-date-format="yyyy-mm-dd">   
                    <input class="form-control input-sm" id="plan_complete_time" name="plan_complete_time" value="${pv.plan_complete_timeString}" type="text" placeholder="计划用完时间" readonly />
                    <span class="input-group-addon input-sm btn"> 
                    <i class="glyphicon glyphicon-calendar "></i>
                    </span>
                </div>
            </div>
        </div> 




</form>

<script type="text/javascript"> 
    
$(function(){  
	
	
	$("#feereg_form").validate({  
        rules: {  
            amount: {
                required:true,
                digits:true,      
                range: [1, 10000000]  
            },
            activat_time: {
                required:true,
                date:true 
            },
            plan_complete_time: {
                required:true,
                date:true 
            }
        },
        messages:{
            amount:{
                required:"必填字段",
                digits: "必须是数字",    
                range: "数字超出范围"  
            },
            activat_time: {
            	 required:"必填字段",
            	 date: "日期格式不对"
            },
            plan_complete_time: {
            	 required:"必填字段",
                 date: "日期格式不对"
            }
         }
      }); 
	
	
	
	
	
	
	
	
	//选择开始时间触发
    $('#activat_timeDiv').datepicker({
        format : 'yyyy-mm-dd',
        weekStart : 1
    }).on('changeDate', function(ev) {
        $('#activat_timeDiv').datepicker('hide');
        checkDate1();
    }); 
            
    function checkDate1(){
        /* var fromDate =  DateFormat.parse($('#costDate').val(), 'yyyy-MM-dd'); 
        
        if (fromDate.valueOf() < new Date().valueOf()){
            openError("资质有效日期不能晚于当前日期！",2000);
            $("#costDate").val("");
        } */
          
        return true;
    } 
    
  //选择开始时间触发
    $('#plan_complete_timeDiv').datepicker({
        format : 'yyyy-mm-dd',
        weekStart : 1
    }).on('changeDate', function(ev) {
        $('#plan_complete_timeDiv').datepicker('hide');
       // checkDate();
    }); 
            
    function checkDate(){
         var actDate =  DateFormat.parse($('#activat_time').val(), 'yyyy-MM-dd');
         
         var comDate =  DateFormat.parse($('#plan_complete_time').val(), 'yyyy-MM-dd');
         
        
        if (comDate.valueOf() < actDate.valueOf()){
            openError("用完时间不能在到账日期前!",2000);
            $("#costDate").val("");
        } 
          
        return true;
    } 
	 /* 
	 var setting = {
            async: {
                enable: true,
                type:"get",
                url:"${ctx}/sys/rbac/organization/list",
            },
            view: {
                showIcon: true
            },
            data: {
                simpleData: {
                    enable: true,
                    idKey: "id",
                    pIdKey: "parentId"
                },
                key:{
                    name:"name" 
                }
            } ,
            callback: {
                onClick:zTreeOnClick
            }
        };
    
     function zTreeOnClick(event, treeId, treeNode){
         if(treeNode.type=='5'){
             var org = treeNode.name;
              var id =  treeNode.id;
              $("#organId").val(id);  
              $("#organization").val(org);  
              $("#organizationZtree").toggle();
         } 
    } 
        
         $.fn.zTree.init($("#organization_ztree"), setting);
     
    
    $("#organization").click(function(e){
       
        $("#organizationZtree").css('width',$(this).outerWidth());
        $("#organizationZtree").toggle();
         
    });     */ 
    
    });
    
    </script>


