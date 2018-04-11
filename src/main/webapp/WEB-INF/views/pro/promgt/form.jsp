<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    <div id="alertProject" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
    
    <form id="project_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="projectName"><span style="color: red">*</span>项目名称:</label>
			<div class="col-lg-5 col-md-5">
				<input type="text" id="projectNameForId" name="projectName" value="${project.projectName }"  class="form-control input-sm" placeholder="项目名称" required>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="shortName"><span style="color: red">*</span>简称:</label>
			<div class="col-lg-5 col-md-5">
				<input type="text" id="shortName" name="shortName" value="${project.shortName }"  class="form-control input-sm" placeholder="简称" required>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="leader">项目负责人:</label>
			<div class="col-lg-5 col-md-5">
				<input type="text" id="leader" name="leader" value="${project.leader }" class="form-control input-sm" placeholder="项目负责人">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="beginTime"><span style="color: red">*</span>开始时间:</label>
			<div class="col-lg-5 col-md-5">
				<input class="form-control input-sm" id="beginTime" name="beginTime" value="${project.beginTimeString }"  required="required" type="text" placeholder="选择开始时间"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="endTime"><span style="color: red">*</span>结束时间:</label>
			<div class="col-lg-5 col-md-5">
				<input class="form-control input-sm" id="endTime" name="endTime" value="${project.endTimeString }"  required="required" type="text" placeholder="选择结束时间"/>
			</div>
		</div>
		<!-- 
		<div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="isAutoStart">是否自动开始项目:</label>
			<div class="col-lg-5 col-md-5">
				<input type="radio" name="isAutoStart" value="1" ${project.isAutoStart eq 1 ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="isAutoStart" value="0" ${project.isAutoStart eq 0 or !edit ? "checked='checked'" : '' } />否
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="isAutoEnd">是否自动结束项目:</label>
			<div class="col-lg-5 col-md-5">
				<input type="radio" name="isAutoEnd" value="1" ${project.isAutoEnd eq 1 ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="isAutoEnd" value="0" ${project.isAutoEnd eq 0 or !edit ? "checked='checked'" : '' } />否
			</div>
		</div>
		 -->
		<div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="workload">项目描述:</label>
			<div class="col-lg-5 col-md-5">
				<input type="text" id="workload" name="workload" value="${project.workload }" class="form-control input-sm" placeholder="对项目的描述"  >
			</div>
		</div>
		 <div class="form-group">
			<label class="col-lg-4 col-md-4  control-label" for="status">是否开始:</label>
			<div class="col-lg-5 col-md-5">
				<input type="radio" name="status" value="1" ${project.status eq 1 ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status" value="0" ${project.status eq 0 or !edit ? "checked='checked'" : '' } />否
			</div>
		</div>
		<input type="hidden" name="projectId" id="projectId" value="${project.projectId}">
	</form>
    
	<script type="text/javascript">
		$(function(){

				$('#beginTime').datepicker({
					format : 'yyyy-mm-dd',
					weekStart : 1
				}).on('changeDate', function(ev) {
					$('#beginTime').datepicker('hide');
				});

				$('#endTime').datepicker({
					format : 'yyyy-mm-dd',
					weekStart : 1
				}).on('changeDate', function(ev) {
					$('#endTime').datepicker('hide');
				});
			
			$("#project_form").validate({
				rules: {
					<c:if test="${empty project}">
					projectName:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/pro/promgt/checkNameExists',
							dataType:'json',
							data:{
								//projectName:$("#projectName").val()
								projectName:function(){
									var proName = $("#projectNameForId").val();
									return proName;
								}
							},
							dataFilter: function(data) {
								data= eval("("+data+")");
								if(data){
									if(!data.result){
										return true;
									}
									return false;
								}
								return false;
							}
						},
						maxlength:70
					},
					</c:if>
					shortName:{
						required: true
					},
					beginTime:{
						required:true
					},
					endTime:{
						required:true,
						notlatercurrentdate:true,
						startdatenotlaterenddate:true
					},
					leader:{
						maxlength:20
					},
					workload:{
						maxlength:100
					}
				},
				messages:{
					<c:if test="${empty project}">
					projectName:{
						required:'项目名称不能为空',
						remote:'项目名称已经存在！',
						maxlength:'项目名称长度不能超过70个字符！'
					},
					</c:if>
					shortName:{
						required:'简称不能为空！'
					},
					beginTime:{
						required:'开始时间不能为空'
					},
					endTime:{
						required:'结束时间不能为空',
						notlatercurrentdate:'结束时间必须晚于当前日期！',
						startdatenotlaterenddate:'结束时间不能早于开始日期！'
					},
					leader:{
						maxlength:'项目负责人长度不能超过20个字符！'
					},
					workload:{
						maxlength:'工作量长度不能超过100个字符！'
					}
				}
			});

		});
		$.validator.addMethod('notlatercurrentdate',function(value, element){
			if(value=="") return true;
			var fromDate =  DateFormat.parse(value, 'yyyy-MM-dd'); 
			if (fromDate.valueOf() < new Date().valueOf()){
				return false;
			}
			return true;
		}, '不能晚于当前日期！');
		
		$.validator.addMethod('startdatenotlaterenddate',function(value, element){
			var startTime = $("#beginTime").val();
			var endTime = $("#endTime").val();
			if(startTime > endTime){
				return false;
			}
			return true;
		},'结束时间不能早于开始时间！');
	</script>
