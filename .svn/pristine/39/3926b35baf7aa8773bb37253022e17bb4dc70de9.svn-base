<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="lcc_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="parentCode"><span style="color: red">*</span>医院名称:</label>
			<c:if test="${empty eval }">
				<div class="col-lg-7 col-md-8">
					<select id="lccName" name="lccName" class="form-control input-sm" onchange="chooseLccCode(this.value)" >
						<option value="-1">请选择单位</option>
						<c:forEach items="${lccDictList }" var="lccDict">
							<option value="${lccDict.lccCode }" ${eval.lccCode == lccDict.lccCode ? 'selected="selected"' : '' }>${lccDict.lccName }</option>
						</c:forEach>
					</select>
				</div>
			</c:if>
			<c:if test="${!empty eval }">
				<div class="col-lg-7 col-md-8">
					<select id="lccName" name="lccName" class="form-control input-sm" onchange="chooseLccCode(this.value)" >
						<c:forEach items="${lccDictList }" var="lccDict">
							<c:if test="${eval.lccCode == lccDict.lccCode  }">
								<option value="${lccDict.lccCode }" ${eval.lccCode == lccDict.lccCode ? 'selected="selected"' : '' }>${lccDict.lccName }</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
			</c:if>
		</div>
		<input type="hidden" name="lccCode" value="${eval.lccCode }" id="lccCode">
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="evaluation"><span style="color: red">*</span>评价:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="evaluation" name="evaluation" value="${eval.evaluation }" class="form-control input-sm" placeholder="评价" required="required">
			</div>
		</div>
				<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="evaluationLevel"><span style="color: red">*</span>评价级别:</label>
			<div class="col-lg-7 col-md-8">
			<!-- 
				<input type="text" id="evaluationLevel" name="evaluationLevel" value="${eval.evaluationLevel }" class="form-control input-sm" placeholder="评价级别" number>
				 -->
				<select id="evaluationLevel" name="evaluationLevel" class="form-control input-sm">
					<option value="-1">请选择评价级别</option>
					<c:forEach items="${evaluationLevelList }" var="evalLevel">
						<option value="${evalLevel }" ${evalLevel == eval.evaluationLevel ? 'selected="selected"' : '' }>${evalLevel }</option>
					</c:forEach>
				</select>
			</div>
		</div>
				<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="remark">备注:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="remark" name="remark" value="${eval.remark }" class="form-control input-sm" placeholder="描述">
			</div>
		</div>
		<input id="id" name="id" value="${eval.id }" type="hidden" />
	</form>
	
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">

	function chooseLccCode(data){ 
		$("#lccCode").val(data);
	}
		
	$("#lcc_form").validate({
		rules: {
			<c:if test="${empty eval}">
			lccName:{
					min:0,
					remote:{
						type:"POST",
						url:'${ctx}/pro/eval/checkLccNameExists',
						dataType:'json',
						data:{
							lccCode:function(){
								return $("#lccCode").val();
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
					}
				},
				</c:if>
				evaluation:{
					required:true,
					maxlength:1024
				},
				evaluationLevel:{
					min:0
				},
			remark:{
				maxlength:120
			}
		},
		messages:{
			<c:if test="${empty project}">
			lccName:{
				min:'请选择要评价的单位！',
				remote:'该单位已经评价！'
			},
			</c:if>
			evaluation:{
				required:'请输入评价内容！',
				maxlength:'最大不能超过1024个字符'
			},
			evaluationLevel:{
				min:'请选择评价级别'
			},
			remark:{
				maxlength:'最大不能超过120个字符！'
			}
		}
	});
</script>