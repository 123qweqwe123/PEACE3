<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />


<form id="feereg_form" class="form-horizontal">
	<%-- <div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="loginName">
		<span style="color: red">*</span>项目名:</label>
		<div class="col-lg-5 col-md-5">
			<input type="hidden" id="projectId" name="projectId" value="${vo.projectId}" />
				<input type="text" id="projectName" name="projectName" value="${vo.projectName}"
                class="form-control input-sm" placeholder="项目名称" required  readonly/>
		</div>     
	</div>  --%>   

	<div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="name"><span
			style="color: red">*</span>记账类型:</label>
		<div class="col-lg-5 col-md-5">
			<input type="hidden" id="reg_type" name="reg_type"
				class="form-control input-sm" placeholder="记账类型"
				value="${vo.reg_type}"  required>
				<select id="reg_type" name="reg_type"   class="form-control input-sm"> 
                <c:forEach items="${regtype}" var="rt" >
                    <option value="${rt.typeCode }"  ${rt.typeCode == vo.reg_type ? "selected='selected'" : '' } >${rt.typeName}</option> 
                </c:forEach>        
            </select>   
		</div>
	</div> 

	<div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="departId"><span
			style="color: red">*</span>中心部门:</label>
		<div class="col-lg-5 col-md-5">

			<%-- <input type="hidden" class="form-control input-sm"
				name="departId" id="departId" value="${vo.departId}" />
				<input type="text"   
                class="form-control input-sm" name="organizationFrom" id="organizationFrom" />

            <div id="organizationZtreeF" class="tree-select">
                <div id="organization_ztreeF" class="ztree"
                    style="height: 190px; overflow-y: scroll;"></div>
            </div>
				 --%>
	        <select id="departId" name="departId"   class="form-control input-sm"> 
                <c:forEach items="${ocx}" var="op" >
                    <option value="${op.deptCode }"  ${op.deptCode == vo.departId ? "selected='selected'" : '' } >${op.deptName}</option> 
                </c:forEach>        
            </select>        
			 <input type="hidden" class="form-control input-sm" name="departName" id="departName" value="${vo.departName}" />
		</div>
	</div>

	<div class="form-group"> 
		<label class="col-lg-3 col-md-3  control-label" for="amount"><span
			style="color: red">*</span>金额:</label>
		<div class="col-lg-5 col-md-5">
		<div class="input-group">
			<input type="text" id="amount" name="amount"
				class="form-control input-sm" placeholder="金额" value="${vo.amount}">
		   
          <span class="input-group-addon">元</span>
          </div>
		</div>
	</div> 
    <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="reg_date"><span
            style="color: red">*</span>使用时间:</label>
            <div class="col-lg-5 col-md-1">
                <div class="input-group date" id="reg_dateDiv" data-date-format="yyyy-mm-dd">   
                    <input class="form-control input-sm" id="reg_date" name="reg_date" value="${vo.reg_dateString}" type="text" placeholder="计划用完时间" readonly />
                    <span class="input-group-addon input-sm btn"> 
                    <i class="glyphicon glyphicon-calendar "></i> 
                    </span>
                </div>
            </div>
        </div> 
	<div class="form-group"> 
		<label class="col-lg-3 col-md-3  control-label" for="reasons">事由:</label>
		<div class="col-lg-5 col-md-5">
			<textarea rows="3" id="reasons" class="form-control" name="reasons"
				placeholder="事由">${vo.reasons}</textarea>
		</div>
	</div> 
<input type="hidden" name="id" id="id" value="${vo.id}">  

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
            reasons: {
                maxlength:600
            }      
        },
        messages:{
            amount:{
        		required:"必填字段",
                digits: "必须是数字",    
                range: "数字超出范围"  
            } ,
            reasons: {
            	maxlength:"超出长度范围！"
            }      
         }
      });
	 
	 
	 
	//选择开始时间触发
	    $('#reg_dateDiv').datepicker({
	        format : 'yyyy-mm-dd',
	        weekStart : 1
	    }).on('changeDate', function(ev) {
	        $('#reg_dateDiv').datepicker('hide');
	       // checkDate();
	   }); 
	     
	
	    function checkDate(){
	         
	        return true;
	    } 
});
    
    </script>


