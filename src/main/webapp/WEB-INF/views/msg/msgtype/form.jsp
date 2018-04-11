<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<form id="type_form" class="form-horizontal">
	<div class="form-group">
		<label class="col-lg-3 col-md-2  control-label" for="typeCode"><span style="color: red">*</span> 类别编码:</label>
		<div class="col-lg-7 col-md-8">
			<input type="text" id="typeCode" name="typeCode" value="${msgType.typeCode}" class="form-control input-sm" placeholder="类别编码"/>
		</div>
	</div>
	
	<div class="form-group">
		<label class="col-lg-3 col-md-2  control-label" for="typeName"><span style="color: red">*</span> 类别名称:</label>
		<div class="col-lg-7 col-md-8">
			<input type="text" id="typeName" name="typeName" value="${msgType.typeName}" class="form-control input-sm" placeholder="类别名称"/>
		</div>
	</div>
	<input id="id" name="id" value="${msgType.id }" type="hidden" />
</form>

<div id="alertErr" class="alert alert-danger" hidden>
	<strong>Warning!</strong>
</div>

<script type="text/javascript">
$(function(){
	$("#type_form").validate({
		rules: {
			typeCode: {
				<c:if test="${empty msgType}">
				remote: {
					type: "POST",
					url: '${ctx}/msg/msgtype/getMsgtypeByCode',
					dataType: 'json',
					data: {
						typeCode: function(){
							return $("#typeCode").val();
						}
					},
					dataFilter: function(data) {
						data = eval("("+data+")");
						if(data){
							if(!data.success){
								return true;
							}
							return false;
						}
						return false;
					}
				},
				</c:if>
				required: true
			},
			typeName: {
				required: true
			}
		},
		messages: {
			typeCode: {
				<c:if test="${empty msgType}">
					remote: '该类别编码已存在，请重新输入',
				</c:if>
				required: '请输入类别编码'
			},
			typeName: {
				required: '请输入类别名称'
			}
		}
	});
});

</script>