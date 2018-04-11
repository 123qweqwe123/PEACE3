<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<c:url var="getHelpDoc" value="/sys/helpdoc/get-doc-bypid"></c:url>
<!DOCTYPE html>
<head> 
<title>帮助文档</title>
<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/adp/images/airchina.ico" /> --%>
<script type="text/javascript">
$(function() {
    
	var setting = {
	        async: {
	            enable: true,
	            type:"post",
	            url:"${ctx}/sys/rbac/permission/getByUserIdCatHelp"
	        },
	        data: {
	            simpleData: {
	                enable: true,
	                idKey: "id",
	                pIdKey: "parentId"
	            }
	        } ,
	        callback: {
	           // beforeDrag: beforeDrag,
	           onAsyncSuccess: onAsyncSuccess,
	           onClick: zTreeOnClick,
	           // onRemove: onRemove
	        },
	        view:{
	            //addHoverDom: addHoverDom,
	           // removeHoverDom: removeHoverDom,
	            expandSpeed: "slow",
	            selectedMulti: false
	        },
	        edit: {
	            showRemoveBtn : false,
	            showRenameBtn:false,
	            enable: true
	        }
	    };


	//当ztree加载成功时 展开
	function onAsyncSuccess(event, treeId, treeNode, msg) {    
	    var treeObj = $.fn.zTree.getZTreeObj(treeId);
	    //获取到当前页面的 权限节点
	    var node = treeObj.getNodeByParam("id", '${vo.permissionId}', null);
	    //获取它的父节点
	    //var parentNode = node.getParentNode();
	    //展开其父节点
	    //treeObj.expandNode(parentNode,true,false,true);
	    treeObj.selectNode(node);
	}
	//当点击树节点时事件
	function zTreeOnClick(event, treeId, treeNode) {
	    //alert(treeNode.id + ", " + treeNode.code);
	    $.ajax({
	        url:"${getHelpDoc}",
	        data : { permissionId: treeNode.id } ,
	        success:function(data,status){  
	            if(data){
	              //重新覆盖ueditor 编辑框内容  
	              $('#allcontent').html(data.content);
	            }else{
	              //清空ueditor
	              $('#allcontent').html('');
	            }
	        }
	    });
	    
	};


	//加载权限数
	$.fn.zTree.init($("#permissionTree"), setting );
	

	
});
</script> 
	 
</head>
<body >

<div class="breadcrumb">
            <label class="active">帮助文档</label>
  </div>
<div class="row">
    <div class="col-lg-3 col-md-3">
    <div id="myAffix" data-spy="affix" style="width:280px;">    
     <div id="permissionTree" class="ztree" style="height:500px; border-radius:5px;border:1px solid #E7E7E7;background-color:#F5F5F5"></div>
    </div>
    </div>
    <div class="col-lg-9 col-md-9"> 
	  <div id="allcontent" class="well">   
		${vo.content}
	  </div>

    </div>
</div>

</body>
</html>
