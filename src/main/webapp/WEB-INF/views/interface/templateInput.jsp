<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 
<html>
<head>
<title>工作台</title>
<script type="text/javascript">
	
</script>
<style type="text/css">
* {margin: 0;padding: 0;}
.portolBtn {float: left;display: block;	width: 15%;cursor: pointer;}
.offset {float: left;display: block;width: 2%;}
</style>
</head>   
<body>
	<form action="uqsTemplate">
		<input name="xmlfile" value="C:\\Users\\dinglin\\Desktop\\utmp\\UQS_001_005_001.xml" type="text" style="width:800px;"/>
		<br>
		<button type="submit">提交</button>
	</form>
</body>
</html>
