<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="dict" uri="http://com.bdcor.pip.web.common.tagsupport" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="userName"><span style="color: red">*</span>用户名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="userName1" name="userName" value="${materUser.userName }" class="form-control input-sm" placeholder="用户名称" required>
			</div>
			<shiro:hasPermission name="permissionmgt:administrator">
			<label class="col-lg-3 col-md-1  control-label" for="lccName"><span style="color: red">*</span>协作医院名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="lccName"  value='<dict:writer dictType="LCC" code="${materUser.lccCode}"/>' class="form-control input-sm" placeholder="协作医院名称" required>
				<input type="hidden" id="lccCode"  name="lccCode" value="${materUser.lccCode}">
			</div>
			</shiro:hasPermission>
		</div>

		<input id="userCode" name="userCode" value="${materUser.userCode }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
			$("#supplier_form").validate({
				rules: {
					<c:if test="${not empty mat}">
					userName:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/material/user/checkNameExists',
							dataType:'json',
							data:{
								userName:function(){
									return $("#userName1").val();
								}
							},
							dataFilter: function(data) {
								data= eval("("+data+")");
								if(data){
									if(data.result){
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
					<c:if test="${not empty mat}">
					userName:{
						required:'请输入库管员名称！',
						remote:'库管员名称已经存在！',
						max:'最大长度不能超过120个字符！'
					}
					</c:if>
				}
			});
			<shiro:hasPermission name="permissionmgt:administrator">
			//协作医院名称where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22!=%22,%22value%22:%22"+<shiro:principal property="lccCode"/>+"%22}]
			$.getJSON("${ctx}/combox/dataLimitLcc",function(data) { 
				$('#lccName').autocomplete(data,{
					minChars: 0,
					mustMatch:true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="130px;">' + item.LCC_CODE + '</td><td>' + item.LCC_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE+item.LCC_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.LCC_NAME;
			        }
				}); 
				
				$('#lccName').result(function(event, item){
					if(item){
						$("#lccCode").val(item.LCC_CODE);						
					}
				});
			});
			</shiro:hasPermission>
		});
	</script>
