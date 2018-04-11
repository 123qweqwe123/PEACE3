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
				class="form-control input-sm" placeholder="项目名称" required  value="<%=Securitys.getUser().getCurrent_projectId ()%>">
		    <input type="text" id="project_name" name="project_name" 
                class="form-control input-sm" placeholder="项目名称" required  value="<%=Securitys.getUser().getCurrent_projectName()%>">
		 
		</div>  
	</div> --%>
    <div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="organization"><span
			style="color: red">*</span>项目单位:</label>
		<div class="col-lg-5 col-md-5">
			<label class="control-label" > <%=Securitys.getUser().getLccName()%> </label> 
        </div>
	</div> 

     <div class="form-group">
        <label class="col-lg-3 col-md-3  control-label" for="organization"><span
            style="color: red">*</span>费用类型:</label>
        <div class="col-lg-5 col-md-5">
             <select class="form-control" name='fee_type' id ='fee_type' >
                   <option value=''>请选择</option>
                   
             </select>
        </div> 
    </div> 


	<div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="actualCost"><span
			style="color: red">*</span>实际费用:</label>
		<div class="col-lg-5 col-md-5"> 
        <input type="text"    
         class="form-control input-sm" name="actualCost" id="actualCost" placeholder="实际费用"  value="${ar.actualCost}"/>
    </div>  
	</div>  

	<!-- <div class="form-group">
		<label class="col-lg-3 col-md-3  control-label" for="costDate"><span
			style="color: red">*</span>花费日期:</label>
		<div class="col-lg-5 col-md-5">
			<div class="input-group date" id="duedate1">
                 <input class="form-control input-sm"  id="costDate" name="costDate" type="text" />
                <span class="input-group-addon input-sm btn">
                <i class="glyphicon glyphicon-calendar"></i>
                </span>
                  </div>
		</div>
	</div> -->
    <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="costDate"> <span
            style="color: red">*</span>花费日期:</label>
            <div class="col-lg-5 col-md-5">
             
                    <input class="form-control " id="costDate" name="costDate" value="${ar.costDateStr}" required="required" readonly="readonly" type="text" placeholder="花费日期"/>
                   <!--  <span class="input-group-addon input-sm btn"> 
                    <i class="glyphicon glyphicon-calendar "></i>
                    </span> -->
                
            </div>
        </div>
  
    <input type="hidden" name="id" id="id" value="${ar.id}">   
</form>
<script type="text/javascript">       
    
$(function(){  
	$.getJSON("${ctx}/combox/comboxData?table=PIP_FEE_ACTUAL_TYPE&cols=ID|NAME&where=",function(data) {
	     $.each(data, function(i, field){
	         var opt =  $("<option value='"+field.ID+"'>"+field.NAME+"</option>");
	         $('#fee_type').append(opt);
	      }); 
	});   
	
	
	$("#feereg_form").validate({  
		  rules: {  
			  actualCost: {
				  digits:true,      
		          range: [1, 10000000]  
		    },
		    fee_type: {
		    	required:true 
            } 
		  },
		  messages:{
              actualCost:{
				  digits: "必须是数字",    
	              range: "数字超出范围"  
              },
              fee_type: {
                  required:"必填字段"
              } 
          } 
		});  
	
	
	/* var date=new Date("${ar.costDate}");    
	date.setDate(date.getDate()-2);
	var dayBefore2 = DateFormat.format(date, 'yyyy-MM-dd');
	date.setDate(new Date().getDate()+30);
	var dayBefore1 = DateFormat.format(date,'yyyy-MM-dd');
	//加载日期控件
	initDatePicker(
	        '#duedate1','#costDate',
	        '#duedate2','#plan_complete_time',
	        function(){
	           //$("#submitButton").attr('disabled',false);
	           $('#alert').hide(); 
	        },
	       function(){
	          // $("#submitButton").attr('disabled',true);               
	       },dayBefore2,dayBefore1);
	        //},'2014-03-01','2014-03-31'); */
	//选择开始时间触发
    $('#costDate').datepicker({
        format : 'yyyy-mm-dd',
        weekStart : 1
    }).on('changeDate', function(ev) {
        $('#costDate').datepicker('hide');
        checkDate();
    }); 
	        
    function checkDate(){
        /* var fromDate =  DateFormat.parse($('#costDate').val(), 'yyyy-MM-dd'); 
        
        if (fromDate.valueOf() < new Date().valueOf()){
            openError("资质有效日期不能晚于当前日期！",2000);
            $("#costDate").val("");
        } */
          
        return true;
    }   
	
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
              $("#lccid").val(id);  
              $("#organization").val(org);  
              $("#organizationZtree").toggle();
         } 
    } 
        
         $.fn.zTree.init($("#organization_ztree"), setting);
     
    
    $("#organization").click(function(e){
       
        $("#organizationZtree").css('width',$(this).outerWidth());
        $("#organizationZtree").toggle();
         
    });    
    
    });
    
    </script>


