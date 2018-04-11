<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    <form  class="form-horizontal">
   
		<input id="patientId_2" name="patientId" value="${patientId }" type="hidden"/>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="username">首次预约时间:</label>
			<div class="col-lg-7 col-md-8 input-group date" id="startDateDiv_1">
				<input type="text" value="${patientDate.firstPlanDateStr }"  id="startDate" placeholder="预约时间" name="firstPlanDate" class="form-control input-sm"/>
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
			</div>
		</div>
	</form>
	<script>
		$(function(){
			$('#startDateDiv_1').datetimepicker({
				autoTop:true,
				language:'zh-CN',
				minView:'2', 
				autoclose:true,
				format : 'yyyy-mm-dd',
				weekStart : 1
			}).on('changeDate', function(ev) {
				$('#startDateDiv_1').datepicker('hide');
			});
		});
	</script>