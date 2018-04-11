<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="adp"	uri="http://wwww.gener-tech.com/adp-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:url var="saveHelpDoc" value="/sys/helpdoc/save-help-doc"></c:url>
<c:url var="getHelpDoc" value="/sys/helpdoc/get-doc-bypid"></c:url>


<!DOCTYPE html>
<html>
<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/adp/images/airchina.ico" /> --%>
<head>

<title>帮助文档管理</title>
</head>
<body>
<ul class="breadcrumb">
  <li class="active">系统管理</li><li class="active" >帮助文档管理</li>
</ul>

<div class="row">
    <div class="col-lg-3 col-md-3">
    <ul id="permissionTree" class="ztree well" style="height:500px;overflow:auto;"></ul>
    </div>
    
    <div class="col-lg-9 col-md-9"> 
		<script id="container" name="content" type="text/plain"   style="height:475px;" >
                    这里写你的初始化内容
	   </script>
    </div>
</div>
       
<div id="errordiv" ></div>
        
    <div class="breadcrumb"  style="text-align:center;margin-top:0px;padding:10px;">
        <button type="button" class="btn btn-info" id="to_preview" >预览</button>
        <button type="button" class="btn btn-primary" id="to_add" >确定</button>
    </div>  


<!-- 配置文件 -->
<script type="text/javascript" src="${ctx}/static/ueditor1.3.6/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${ctx}/static/ueditor1.3.6/ueditor.all.js"></script>
<!-- 语言包文件(建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败) -->
<script type="text/javascript" src="${ctx}/static/ueditor1.3.6/lang/zh-cn/zh-cn.js"></script>

<script type="text/javascript">
$(function() {
    $("#help_nav").addClass('active');
	
    var setting = {
        async: {
            enable: true,
            type:"post",
            url:"${ctx}/sys/rbac/permission/getAllPermission"
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

//加载ueditor
var editor = UE.getEditor('container');


//当ztree加载成功时 展开
function onAsyncSuccess(event, treeId, treeNode, msg) {    
    var treeObj = $.fn.zTree.getZTreeObj(treeId);
    var node = treeObj.getNodeByParam("grade", 0, null);
    treeObj.expandNode(node,true,false,true);
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
              editor.setContent(data.content, false);
            }else{
              //清空ueditor
            	editor.execCommand('cleardoc');
            }
        }
    });
    
};


//加载权限数
$.fn.zTree.init($("#permissionTree"), setting );

//确定操作======================
$(document).on('click','#to_add',function(){
	var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
	var nodes = treeObj.getSelectedNodes();
	if(!nodes[0]){
		showAlertCk("#errordiv", "请选择一个权限节点！");
		return false;
	}
	//通过editor API获取编辑内容
	var content = editor.getContent(); 
	//console.log(content);
	if(!content){
        showAlertCk("#errordiv", "编辑框内容为空！");
        return false;
    }
	
	//提交============
	$.post("${saveHelpDoc}", 
	    { content: content, permissionId: nodes[0].id },
	   function(data){
	     if(data.permissionId){
	    	 showAlertCk("#errordiv", "保存成功！", true );
	     }
    });

});

//预览=========================
$('#to_preview').click(function(){
	//触发保存
	$('#to_add').click();
	//显示预览页面
	
    var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
    var nodes = treeObj.getSelectedNodes();
   //打开帮助文档页面
   showHelpDoc(nodes[0].code);

});



});
</script>

</body>
</html>