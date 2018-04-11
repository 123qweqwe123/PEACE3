<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<%@ page import="com.bdcor.pip.core.utils.Securitys"%>  
<!DOCTYPE html>
<form id="feereg_form" class="form-horizontal"> 
	<%-- <div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="projectId">
		<span style="color: red">*</span>当前项目名:</label>
		<div class="col-lg-5 col-md-5">
			<input type="hidden" id="projectid" name="projectid"
				class="form-control input-sm" placeholder="项目名称" required  value="${ibr.projectid}">
		    <input type="text" id="projectName" name="projectName"
                class="form-control input-sm" placeholder="项目名称" required  value="${ibr.projectName}">
		 
		</div>  
	</div> --%>
    <div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="organization"><span
			style="color: red">*</span>医院名称:</label>
		<div class="col-lg-5 col-md-5">
			
				
				
		    <input type="hidden"  name="lccid" id="lccid" value="${ibr.lccid}"/>  
            <%-- <input type="text"   class="form-control input-sm" name="lcc_name" id="lcc_name" placeholder="项目单位" value="${ibr.lcc_name}"/> --%>
            <label class="control-label">${ibr.lcc_name}</label>
           
            <!-- <div id="organizationZtree" class="tree-select">
                <div id="organization_ztree" class="ztree"
                    style="height: 190px; overflow-y: scroll;"></div>
            </div> -->
		</div>
	</div> 

	<div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="actualCost"><span
			style="color: red">*</span>冰箱名称:</label>
		<div class="col-lg-5 col-md-5"> 
        <input type="text"   class="form-control input-sm" name="iceboxName" id="iceboxName" placeholder="冰箱名称" required  value="${ibr.iceboxName}"/>
    </div>  
	</div>
	
	
	    <div class="form-group">  
            <label class="col-lg-3 col-md-3  control-label" for="fridge_brand">品牌:</label>
            <div class="col-lg-5 col-md-1">
                <input type="text"   class="form-control input-sm" name="fridge_brand" id="fridge_brand" placeholder="品牌"  value="${ibr.fridge_brand}"/>
            </div>  
        </div>  
        
        <div class="form-group">  
            <label class="col-lg-3 col-md-3  control-label" for="fridge_spc">规格:</label>
            <div class="col-lg-5 col-md-1">
                <input type="text"   class="form-control input-sm" name="fridge_spc" id="fridge_spc" placeholder="规格"   value="${ibr.fridge_spc}"/>
            </div>
        </div>  

	
    <div class="form-group">  
            <label class="col-lg-3 col-md-3  control-label" for="capacity"><span
            style="color: red">*</span>冰箱容量:</label>
            <div class="col-lg-5 col-md-1">
                <input type="text"   class="form-control input-sm" name="capacity" id="capacity" placeholder="冰箱容量"  required value="${ibr.capacity}"/>
            </div> 
        </div>  
        
        <div class="form-group">  
            <label class="col-lg-3 col-md-3  control-label" for="capacity"><span
            style="color: red">*</span>冻存盒类型:</label>
            <div class="col-lg-5 col-md-1"> 
                
                <select id="spemType" name="spemType" class="form-control input-sm"  value='${ibr.spemType}' >
                    <option value="A、B、C、D、E、F、G">全部类型</option>
                    <option value="A、C、E、G">A、C、E、G</option>
                    <option value="B、D、F">B、D、F</option>
                </select>
                
           </div>
        </div>
        
        
        
  
    <input type="hidden" name="id" id="id" value="${ibr.id}">   
</form>
<script type="text/javascript">
$(function(){
	
	 $("#spemType").find("option[value='${ibr.spemType}']").attr("selected",true);
	
	
	
	$("#feereg_form").validate({  
	    rules: {  
	    	iceboxName: {
	    		required: true,     
	    		maxlength: 64
	        },
	        fridge_brand: {
                maxlength: 64
            },
            fridge_spc: {
                maxlength: 64
            },
            capacity: {
            	number: true,
            	range: [1, 99999999]  
            }  
	    },
	    messages:{
	        iceboxName:{
	    		required: "必填字段",    
	    		maxlength: "长度超出范围"   
	        },
	        fridge_brand: {
	        	maxlength: "长度超出范围"   
            },
            fridge_spc: {
            	maxlength: "长度超出范围"   
            },
            capacity: {
            	number: "必须是数字",
                range:  "超出范围"  
            }
	    }   
	  });  
});

</script>


