<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    <form  class="form-horizontal">
   
		<input id="patientId_1" name="patientId" value="${patientDate.patientId }" type="hidden"/>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="username">预约时间:</label>
			<div class="col-lg-7 col-md-8 input-group date" id="startDateDiv">
				<input type="text" value="${patientDate.sixPlanDateStr }"  id="startDate" placeholder="预约时间" name="sixPlanDate" class="form-control input-sm"/>
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="spec">预约备注:</label>
			<div class="col-lg-7 col-md-8">
				<textarea rows="3" class="form-control" name="nowRemark" placeholder="预约备注">${patientDate.nowRemark}</textarea>
			</div>
		</div>
		<input type="hidden" value="${patientDate.firstStartDateStr }" id="firstStartDateStr" >
		<input type="hidden" value="${patientDate.firstEndDateStr }" id="firstEndDateStr" >
	</form>
	<script>
		$(function(){
			$('#startDateDiv').datetimepicker({
				autoTop:true,
				language:'zh-CN',
				minView:'2', 
				autoclose:true,
				format : 'yyyy-mm-dd',
				startDate:$("#firstStartDateStr").val(),
				endDate : $("#firstEndDateStr").val(),
				weekStart : 1
			}).on('changeDate', function(ev) {
				$('#startDateDiv').datepicker('hide');
			});
		});
	</script>