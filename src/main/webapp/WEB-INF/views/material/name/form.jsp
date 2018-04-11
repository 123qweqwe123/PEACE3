<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="forMaterlName"><span style="color: red">*</span>物资名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="materlNameforform" name="materlName" value="${materName.materlName }" class="form-control input-sm" placeholder="物资名称" required>
			</div>
		</div>

		<input id="materlCode" name="materlCode" value="${materName.materlCode }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
			$("#supplier_form").validate({
				rules: {
					<c:if test="${empty materName}">
					materlName:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/material/name/checkNameExists',
							dataType:'json',
							data:{
								materlName:function(){
									return $("#materlNameforform").val();
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
						maxlength:120
					}
					</c:if>
				},
				
				messages:{
					<c:if test="${empty materName}">
					materlName:{
						required:'请输入物资名称！',
						remote:'物资名称已经存在！',
						max:'最大长度不能超过120个字符！'
					}
					</c:if>
				}
			});

		});
	</script>
