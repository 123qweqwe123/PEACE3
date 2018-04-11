<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
              
<table style="width:100%">
  <tr>
    <td width="90%" style="border: 1px solid #cccccc; background-color:#F5F5F5;">
        <div id="permissionTree" class="ztree" style="height:350px; overflow-y:auto;overflow-x:hidden;overflow:auto; border:1px solid #E7E7E7;background-color:#F5F5F5"></div>
    </td>
    <form>
    	<input type="hidden" value="${userId}" id="userId" name="userId">
    </form>
  </tr>
</table>
<script>
	$(function(){
		var setting = {
				async: {
					enable: true,
					type:"post",
					url:"${ctx}/sys/rbac/user/getAllDataLimit"
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
			
			<c:if test="${not empty datas}">
			var ownPermissions = [
                    <c:forEach items="${datas}" var="data" varStatus="status">
                    {id:'${data}'}<c:if test="${!status.last}">,</c:if>
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
	});
</script>