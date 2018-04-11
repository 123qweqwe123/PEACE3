<%@page import="com.bdcor.pip.data.util.CryptoUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
<script>

$(document).ready(function(){
	
	$('#des').click(function(){
		$('#dtype').val('1');
		$('#form1').submit();
	});
	
	$('#undes').click(function(){
		$('#dtype').val('2');
		$('#form1').submit();
	});
	
});

</script>
</head>
<%
	String srcTxt = "";
	String desTxt = "";
	String lcc = request.getParameter("lcc_code");
	String dtype = request.getParameter("dtype");
	
	if ( lcc != null ){
		lcc = lcc.trim();
		if ( dtype.equals("1")){
			//加密
			srcTxt = request.getParameter("src_txt");
			srcTxt = srcTxt.trim();
			desTxt = CryptoUtil.encrypt(lcc, srcTxt);
			
		}else{
			desTxt = request.getParameter("des_txt");
			desTxt = desTxt.trim();
			srcTxt = CryptoUtil.decrypt(lcc, desTxt);
		}	
	}else{
		srcTxt = "请输入正确的lcc编码";
	}
	
	
	request.setAttribute("lcc", lcc);
	request.setAttribute("srcTxt", srcTxt);
	request.setAttribute("desTxt", desTxt);
	

%>
<body>
<div id="brwrap">
	<!--head-->
    <jsp:include page="./head.jsp"></jsp:include>
    <!--head end-->
    <div id="navbread">
    	<div class="portal_bread">
			<!-- <a href="/">首页</a><span>&gt;</span><a href="/">项目新闻</a><span>&gt;</span><span>新闻详情</span> -->
			<a href="#">项目内容</a>
		</div>
    </div>
     <div id="mainContent">
    	<!--aside-->
		<%-- <div class="portal_leftsider">
			<jsp:include page="news.jsp" ></jsp:include>
			<div class="portal_contact">
				<img src="images/contact.png" width="250" height="72" alt="联系我们" />
			</div>
		</div> --%>
        <!--aside end-->
        <div id="marginRight">
			<div class="portal_newsdetail">
				
				<div class="articleBody">
					<form id="form1" action="destools.jsp" method="post">
					<span>LCC:&nbsp;&nbsp;&nbsp;</span><input id="lcc_code" name="lcc_code" type="text" value="${lcc}"/>
					<input type="hidden" id="dtype" name="dtype"/> 
					<input id="des" type="button" value="加密" ></input>
					<input id="undes" type="button" value="解密"></input>
					<br>
					明文：
					<textarea id="src_txt" name="src_txt" rows="10" cols="100">${ srcTxt }</textarea>
					<br>
					密文：
					<textarea id="des_txt" name="des_txt" rows="10" cols="100">${ desTxt }</textarea>
					</form>
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
