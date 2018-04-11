<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh"
    uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html> 
<head>
<title>bdcor-<sitemesh:title /></title>  
<%@ include file="/WEB-INF/common/common-js-style.jsp"%>
<%@ include file="/WEB-INF/common/custom-js-style.jsp"%>
<%@ include file="/WEB-INF/common/backtop.jsp"%>
<%@ include file="/WEB-INF/common/less-common-js-style.jsp"%>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" /> 
<script type="text/javascript">  
    
    <shiro:hasPermission name="basic:changepassword"> 
    function modifyPWD(url){
        $( "#dialog-confirm" ).modal({
             backdrop: 'static'
        });
        //使用此方法防止js缓存不加载
        $("#dialog-confirm p" ).load(url);
    }
    
    function submitPWD(){
        $("#updatepwd_form").submit();
    }
    
    function reLogin(){
    
        $("#dialog-confirm button").remove();
        
        var reloginBtn =$('<button class="btn btn-primary btn-sm" onclick="goTo(\''+'${ctx}/logout'+'\')">重新登录</button>');
        
        $("#dialog-confirm .modal-footer").append(reloginBtn);
    }
    
    function modifyPwdFail(){
        var btn = '<button type="button" class="btn" data-dismiss="modal">关闭</button>';
        $("#dialog-confirm .modal-footer button").remove();
        $("#dialog-confirm .modal-footer").append(btn);
        
    }
    </shiro:hasPermission>
    
</script>
 
<sitemesh:head />    
</head>
<body data-offset="50" data-target=".subnav" data-spy="scroll">
    <div id="screenWidth" class="container"  style="background:#FFFFFF url(${ctx}/static/bdcor/images/background/tiny_grid.png) repeat center top fixed">
        <div class="topPic topPicportal"></div> 
        <div class="navbar navbar-default"> 
                    <!-- .navbar-toggle is used as the toggle for collapsed navbar content -->
                    <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>  
                    </div>
                    <!-- Place everything within .navbar-collapse to hide it until above 768px -->
                    <div class="collapse navbar-collapse navbar-ex1-collapse" style="position: static;font-weight:bold">
<!--                        <ul class="nav navbar-nav">  -->
<!--                            <li class="active"><a href="javascript:{}" class="active">我的工作台</a></li>  -->
<!--                        </ul> -->
                        <ul class="nav navbar-nav pull-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="glyphicon glyphicon-user"></i>
                                    <shiro:principal property="name" /> 
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
                                    <shiro:hasPermission name="basic:changepassword">
                                    <li><a href="javascript:{}"  onclick="modifyPWD('${ctx}/sys/rbac/user/openmodalupdatepassword');">修改密码</a></li>
                                    </shiro:hasPermission>
                                    <shiro:hasPermission name="sys-management">
                                    <li><a href="javascript:{}" onclick="goTo('${ctx}/portal/redirectTo?target=sys-management');">系统管理</a></li>
                                    </shiro:hasPermission>
                                    <li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">退出系统</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <!-- /.nav-collapse -->
        </div> 
            <sitemesh:body />     
        <footer class="footer" style="text-align: center;clear:both;">   
              Copyright © 2012-2014.  Air China.  All rights reserved 
        </footer>
    <shiro:hasPermission name="basic:changepassword">
    <div id='dialog-confirm' class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">修改密码</h4>
          </div>
          <div class="modal-body">
            <p>加载中……</p>
          </div>
          <div class="modal-footer">
            <button type="button" id ='cancel' data-dismiss="modal" class="btn btn-default btn-sm" tabindex="1001">取消</button>
            <button type="button" id ='do_save' class="btn btn-primary btn-sm" onclick="submitPWD()" tabindex="1000">提交</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    </shiro:hasPermission> 
    </div> 
</body>
</html>