<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
	<li id="noContent1" class="dropdown" style="float: right;">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="background-color:#00036a;">
	    <i class="glyphicon glyphicon-user"></i>
	    <shiro:principal property="name" /> 
	    <b class="caret"></b>
	</a>
	<ul class="dropdown-menu" style="margin:15px 0px;min-width:100px;">
	   <shiro:hasPermission name="basic:changepassword">
	   <li><a href="javascript:{}"  onclick="modifyPWD('${ctx}/sys/rbac/user/openmodalupdatepassword');">修改密码</a></li>
	   </shiro:hasPermission>
	   <li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">退出系统</a></li>
	    </ul>
	</li>
	<li id="noContent2" style="float:right;color:red;"><a href="javascript:{}" onclick="goTo('${ctx}/managementIndex');">返回</a></li>
