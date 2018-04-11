<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
              
<table style="width:100%">
  <tr>
    <td width="50%" valign="top">
        <form id="role_form">
        	<fieldset>
        		<div class="form-group">
	            	<label for="name"><span style="color: red">*</span>角色名:</label>
			        <input type="text" id="name" name="name" class="form-control input-sm" placeholder="角色名" value="${role.name}" required>
	        	</div>
	        	
	        	<div class="form-group">
		            <label for="remark">角色描述:</label>
			        <textarea rows="3" class="form-control" id="remark" name="remark" placeholder="角色描述">${role.remark}</textarea>
	        	</div>
	        	
	        	<div class="form-group"> 
		            <label class="col-lg-10 col-md-3  control-label" for="deptLevel"><span style="color: red">*</span>角色访问权限:</label>
		            <div class="col-lg-8 col-md-5">
		                <select name="deptLevel" class="form-control input-sm" id="deptLevel">
		                      <option value="1" >全部单位可见</option>
		                      <option value="2" >中心可见</option>
		                </select>
		            </div>
		        </div> 
	        	
	        	 
        	</fieldset>
            <input type="hidden" name="id" id="id" value="${role.id}">
        </form>
    </td>
    <td width="50%" style="border: 1px solid #cccccc; background-color:#F5F5F5;">
        <div id="permissionTree" class="ztree" style="height:350px; overflow-y:auto;overflow-x:hidden;overflow:auto; border:1px solid #E7E7E7;background-color:#F5F5F5"></div>
    </td>
  </tr>
</table>
<script>
	$(function(){
		<c:if test="${not empty role}">
		$("#deptLevel").val('${role.deptLevel}');
		</c:if>
		var setting = {
				async: {
					enable: true,
					type:"post",
					url:"${ctx}/sys/rbac/role/getAllPermission"
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
				try{
					var node = treeObj.getNodeByParam("id", ownPermissions[i].id, null);
					treeObj.checkNode(node, true, false);
				}catch(e){
				}
			}
			</c:if>
			
			$("#name").focus();
			
		}
		
		$.fn.zTree.init($("#permissionTree"), setting);
				
		$("#role_form").validate({
			rules: {
				name:{
					required:true,
					maxlength:20,
					minlength:3,
					remote:{
						type:'POST',
						url:'${ctx}/sys/rbac/role/checkExists',
						dataType:'json',
						data:{
							name:function(){
								var l_n = $("#name").val();
								return l_n;
							},
							id:function(){
								return '${role.id}';
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
					required:'角色名不能为空',
					maxlength:'不能超过{0}个字符',
					minlength:'不能少于{0}个字符',
					remote:'角色名已存在'
				},remark:{
					maxlength:'不能超过{0}个字符',
					minlength:'不能少于{0}个字符'
				}
			}
		});
	});
</script>