<%@page import="com.bdcor.pip.data.util.PoiUtil"%>
<%@page import="com.bdcor.pip.data.util.CryptoUtil"%>
<%@page import="com.bdcor.pip.web.data.domain.PipSysNews"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String id = request.getParameter("id");
	String content = "";
	String title = "";
	PipSysNews news = PoiUtil.queryNewsById(id);
	if ( news != null ){
		content = news.getNewsContent();
		title = news.getTitle();
	}
	
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Portal-项目新闻详细页</title>
<link rel="stylesheet" href="css/reset.css" type="text/css" />
<link rel="stylesheet" href="css/main-style.css" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="css/bootstrap-theme.css" type="text/css" />
<link rel="stylesheet" href="css/bootstrap-theme.min.css" type="text/css" />
<script src="js/jquery.min.js" type="text/javascript"></script>
</head>

<body>
<div id="brwrap">
	<!--head-->
    <jsp:include page="./head.jsp"></jsp:include>
    <!--head end-->
    <div id="navbread">
    	<div class="portal_bread">
			<a href="${ctx }/login">首页</a><span>&gt;&nbsp;</span><a href="${ctx }/login">项目新闻</a><span>&gt;</span><span><%=title %></span>
			<%-- <a href="#"><%=title %></a> --%>
		</div>
    </div>
     <div id="mainContent">
    	<!--aside-->
		<div class="portal_leftsider">
			<jsp:include page="news.jsp" ></jsp:include>
			<div class="portal_contact">
				<img src="images/contact.png" width="250" height="72" alt="联系我们" />
			</div>
		</div>
        <!--aside end-->
        <div id="marginRight">
			<div class="portal_newsdetail">
				<!-- <div class="tTitle">
						<h1>心血管病高危人群早期筛查与综合干预项目</h1>
						<div class="time">时间：2014-11-04    |     编辑：王凌霄    </div>
				</div> -->
				<div class="articleBody">
					<div style="overflow:hidden;">
					<%=content %>
					</div>
				</div>
				<!-- <div class="portal_newsadvise">
					<ul>
						<li><a href="#">上一篇：高危筛查项目组IT人员到英国牛津大学CTSU进行学习考察</a></li>
						<li><a href="#">下一篇：心血管病高危人群早期筛查与综合干预项目工作会在阜外医院召开</a></li>
					</ul>
					<a class="back" href="#"><img src="images/back.png" width="66" height="26" alt="返回" /></a>
				</div> -->
			</div>
			<jsp:include page="link.jsp" ></jsp:include>
        </div>
    </div>
    <br style="clear: both;"/>
     <jsp:include page="foot.jsp" ></jsp:include>
</div>
</body>
</html>
