<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<table  style="width:100%">
  <tr>
    <td width="50%" valign="top">
		<form id="module_form">
			<fieldset>
				<div class="form-group">
					<label for="name"><span style="color: red">*</span>模块名:</label>
					<input type="text" id="name" name="name" class="form-control input-sm" placeholder="模块名" value="${module.name}" required>
				</div>
				<div class="form-group">
					<label for="remark">模块描述:</label>
					<textarea rows="3" id="remark" name="remark" class="form-control" placeholder="模块描述">${module.remark}</textarea>
				</div>
				<input type="hidden" name="id" id="id" value="${module.id}">
			</fieldset>
		</form>
	</td>
    <td width="50%"  style="border: 1px solid #cccccc; background-color:#F5F5F5;">
    	<div id="permissionTree" class="ztree" style="height:350px; overflow-y:auto;overflow-x:hidden;overflow:auto; border:1px solid #E7E7E7;background-color:#F5F5F5"></div>
	</td>
  </tr>
</table>

<script>
	$(function(){
		var setting = {
				async: {
					enable: true,
					type:"post",
					url:"${ctx}/sys/rbac/module/getAllPermissions"
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId"
					},
					key:{
						url:"_url"
					}
				} ,
				check: {
					enable: true,
					chkStyle: "checkbox",
					autoCheckTrigger: true,
					chkboxType: { "Y": "ps", "N": "s" }
				},
				view:{
					expandSpeed: "slow",
					selectedMulti: false
				},callback: {
					onAsyncSuccess: onAsyncSuccess
				}
			};
		
		function onAsyncSuccess(event, treeId, treeNode, msg) {
			
			var treeObj = $.fn.zTree.getZTreeObj(treeId);
			
			treeObj.expandAll(true);
			
			<c:if test="${not empty ownPermissions}">
			var ownPermissions = [
                    <c:forEach items="${ownPermissions}" var="permission" varStatus="status">
                    {id:'${permission.id}'}<c:if test="${!status.last}">,</c:if>
                    </c:forEach>
			        ];

			for (var i=0;i<ownPermissions.length; i++) {
				var node = treeObj.getNodeByParam("id", ownPermissions[i].id, null);
				treeObj.checkNode(node, true, false);
			}
			</c:if>
			
			$("#name").focus();
		}
		
		
		$.fn.zTree.init($("#permissionTree"), setting);
		
		$("#module_form").validate({
			rules: {
				name:{
					required:true,
					maxlength:20,
					minlength:3,
					remote:{
						type:'POST',
						url:'${ctx}/sys/rbac/module/checkExists',
						dataType:'json',
						data:{
							name:function(){
								var l_n = $("#name").val();
								return l_n;
							},
							id:function(){
								return '${module.id}';							
							}				
						},dataFilter: function(data) {
							
							if('true'==data){
								return false;
							}
							return true;
						}
					}
				},
				remark:{
					maxlength:200,
					minlength:4
				}
			},
			messages:{
				name:{
					required:'模块名不能为空',
					maxlength:'不能超过{0}个字符',
					minlength:'不能少于{0}个字符',
					remote:'模块名已存在'
				},remark:{
					maxlength:'不能超过{0}个字符',
					minlength:'不能少于{0}个字符'
				}
			}
		});
	});
</script>