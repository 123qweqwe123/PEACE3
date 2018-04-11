<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="version_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="version"><span style="color: red">*</span>版本编号:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="version" name="version" value="${versionCode==null? version.version : versionCode}"   class="form-control input-sm" readonly="readonly">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="versionText"><span style="color: red">*</span>版本内容:</label>
			<div class="col-lg-7 col-md-8">
				<textarea rows="10" id="versionText" class="form-control" name="versionText" placeholder="请输入版本内容[最多输入300个字符]">${version.versionText}</textarea>
			</div>
		</div>
		<input type="hidden" name ="edit" value="${edit }"/>
	</form>
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">
$(function(){
	$("#version_form").validate({
		rules: {
			versionText:{
				required:true,
				maxlength:300
			}
		},
		messages:{
			versionText:{
				required:'请输入版本内容',
				maxlength:'不能超过{0}个字符'
				
			}
		}
	});
});
</script>