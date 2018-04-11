<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<table  style='width:100%'>
  <tr>
  
  <td width="100%"  style="border: 1px solid #cccccc; background-color:#F5F5F5;">
        
        <!-- <div id="organizationZtree" class="tree-select"> -->
               <div id="organization_ztree" class="ztree" style="height: 190px; overflow-y: scroll;"></div>
        <!--   </div>  -->
        
    </td>
   </tr>
</table>

<script type="text/javascript">
<!--

//--> 
$(function(){
var setting = {
		
check: {enable: true,chkStyle: "radio",radioType: "all"},
async: {
    enable: true,
    type:"get",
    url:"${ctx}/sys/rbac/organization/list?type=3",
},
view: {
    showIcon: true
}, 
data: {
    simpleData: {
        enable: true,
        idKey: "id",
        pIdKey: "parentId"
    },
    key:{
        name:"name" 
    }
} ,
callback: {
    onClick:zTreeOnClick
    
}
};


function zTreeOnClick(event, treeId, treeNode){
if(treeNode.type=='5'){
 var org = treeNode.name;
  var id =  treeNode.id;
  $("#organizationId").val(id);  
  $("#organization").val(org);  
  //$("#organizationZtree").toggle();
}
} 

$.fn.zTree.init($("#organization_ztree"), setting);
});
</script>
