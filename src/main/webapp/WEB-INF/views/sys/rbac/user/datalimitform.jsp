<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
              
<table style="width:100%">
  <tr>
  	<td width="50%" align="center" colspan="2">未关联LCC</td>
  	<td width="50%" align="center" colspan="2">已关联LCC</td>
  </tr>
  <tr>
  	<td width="10%"></td>
    <td width="38%" style="border: 1px solid #cccccc; background-color:#F5F5F5;">
        <div id="sourceLccTree" class="ztree" style="height:350px;width:250px; overflow-y:auto;overflow-x:auto;overflow:auto;border:1px solid #E7E7E7;background-color:#F5F5F5"></div>
    </td>
    <td width="4%" align="center" valign="middle">
   		<button id="btnadd" onclick="addRole()">&gt;&gt;</button><br><br><br>
    	<button id="bendel" onclick="delRole()">&lt;&lt;</button>
    </td>
    <td width="38%" style="border: 1px solid #cccccc; background-color:#F5F5F5;">
        <div id="destLccTree" class="ztree" style="height:350px;width:250px; overflow-y:auto;overflow-x:auto;overflow:auto;border:1px solid #E7E7E7;background-color:#F5F5F5"></div>
    </td>
    <td width="10%"></td>
  </tr>
  <tr>
  	<td>
  	<input type="hidden" value="${userId}" id="userId">
  	</td>
  </tr>
</table>
<script>
//数据右移动
var Tree1, Tree2;
function addRole() {
		//移动方法
		//右移时Tree1 在第一个参数,Tree2第二个参数
		//表示Tree1移动致Tree2
		moveTreeNode(Tree1, Tree2);
		
}

//数据左移动
function delRole() {
	//移动方法 参数相反
	moveTreeNode(Tree2, Tree1);
}

function moveTreeNode(zTree1, zTree2){
	var nodes = zTree1.getCheckedNodes();	//获取选中需要移动的数据
	for(var i=0;i<nodes.length;i++){			//把选中的数据从根开始一条一条往右添加
		var node = nodes[i];
		var strs={};			//新建一个JSON 格式数据,表示为一个节点,可以是根也可以是叶
		strs.id =node.id;
		strs.name=node.name;
		//调用添加方法
		//strs : json 格式..拼装成树的一个节点
		//zTree2: 表示需要添加节点的树
		zTreeDataAddNode(strs,zTree2);
		zTreeDataDelete(nodes[i],zTree1);
		}
		//把选中状态改为未选择
		zTree2.checkAllNodes(false);	
		zTree1.checkAllNodes(false);
}

//树数据移动方法
function zTreeDataAddNode(strs,zTree2){
	//树没任何数据时,第一个被加进来的元素
	zTree2.addNodes(null,strs);
}

function zTreeOnClick(event, treeId, treeNode){
	var treeObj = $.fn.zTree.getZTreeObj(treeId); 
		if(treeNode.checked){
			treeObj.checkNode(treeNode, false, true);
		}else{
			treeObj.checkNode(treeNode, true, true);
		}
}
//数据移除zTreeDataDelete(strs,zTree1);
function zTreeDataDelete(node,zTree1){
	//不是就直接移除
	zTree1.removeNode(node);
}
	$(function(){
		var userId=$("#userId").val();
		var settingSource = {
				async: {
					enable: true,
					type:"post",
					url:"${ctx}/sys/rbac/user/getAllLcc?type=1&userId="+userId
				},
				check:{
					enable:true
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId"
					}
				},
				view:{
					expandSpeed: "slow",
					selectedMulti: false
				},callback: {
					onClick: zTreeOnClick
				}
			};
		var settingDest = {
				async: {
					enable: true,
					type:"post",
					url:"${ctx}/sys/rbac/user/getAllLcc?type=2&userId="+userId
				},
				check:{
					enable:true
				},
				data: {
					simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "parentId"
					}
				},
				view:{
					expandSpeed: "slow",
					selectedMulti: false
				},callback: {
					onClick: zTreeOnClick
				}
			};
		
		Tree1=$.fn.zTree.init($("#sourceLccTree"), settingSource);
		Tree2=$.fn.zTree.init($("#destLccTree"), settingDest);
		
	});
</script>