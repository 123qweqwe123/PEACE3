<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException"%>
<%@page import="com.bdcor.pip.data.util.FileUtil"%>
<%@page import="java.util.*"%>
<%@page import="com.bdcor.pip.web.pro.promgt.domain.Adjunct"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
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
			<!-- <a href="/">首页</a><span>&gt;</span><a href="/">项目新闻</a><span>&gt;</span><span>新闻详情</span> -->
			<a href="#">联系我们</a>
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
			<div>
                <h3  class="newTitle">培训资料下载 </h3>
                <div class="clearfix news">
                    <div class="blockquote">
                    <%
							List<Adjunct> list = FileUtil.queryFile();
                    		if ( list != null && list.size() > 0 ){
    							for ( Adjunct file : list ) {
						%>
                   		<p><a class="divout" style="color:#004280;cursor:pointer" 
                   		onclick="downDocument('<%=file.getAdjunctId() %>','<%=file.getPath() %>','<%=file.getAdjunctName() %>','<%=file.getFileDesc() %>')"><%=file.getFileDesc() %></a></p>
                        <% }
						}%>
                    </div>
                </div>
             </div>
             <br style="clear: both;"/>
			<jsp:include page="link.jsp" ></jsp:include>
        </div>
    </div>
    <br style="clear: both;"/>
     <jsp:include page="foot.jsp" ></jsp:include>
</div>
<script>
function downDocument(id,path,name,fileDesc){
	<shiro:authenticated>
	window.location.href="${ctx}/pro/filemgt/downloadDocument?projectId=001&adjunctId="+id+
	"&path="+path+"&adjunctName="+name+"&fileDesc="+fileDesc;
	</shiro:authenticated>
	<shiro:notAuthenticated>
	alert("请登录之后下载!");
	</shiro:notAuthenticated>
	
	
}

</script>
</body>
</html>
