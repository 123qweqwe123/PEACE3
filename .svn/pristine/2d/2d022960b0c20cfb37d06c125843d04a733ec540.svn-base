<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>BDCOR</title> 
<link type="text/css" rel="stylesheet" href="${ctx}/static/bdcor/css/login.css" />

<link type="text/css" rel="stylesheet" href="${ctx}/static/jquery-validation/1.11.1/validate.css"  />   
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.1.1/css/login-main.css" />

<%-- <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.1.1/css/main-style.css" /> --%>
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.1.1/css/reset.css" />  
<link type="text/css" rel="stylesheet" href="${ctx}/static/jquery/css/jquery-ui-1.10.3.custom.css" />  


<style type="">

	select option{line-height:30px;height:30px;margin:5px auto;}
</style>
<!--[if lt IE 9]>  
<span class="versionRemind">您的IE浏览器版本较低，建议使用<a href="http://windows.microsoft.com/zh-cn/internet-explorer/download-ie" target="_blank">IE9.0及以上版本浏览器</a>或者<a href="${ctx}/downloadTool/rar/firefox_install" target="_blank">Firefox4.0及以上版本浏览器</a>访问本系统。</span> 
<![endif]-->  
<script type="text/javascript" src="${ctx}/static/jquery/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.js" ></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/messages_zh.js" ></script>
<script type="text/javascript" src="${ctx}/static/jquery/js/jquery-ui-1.10.3.custom.min.js" ></script>

<script type="text/javascript">
// 	var browser = {};//定义浏览器json数据对象
// 	var ua = navigator.userAgent.toLowerCase();
// 	var s;
// 	(s = ua.match(/msie ([\d.]+)/)) ? browser.ie = s[1] :
// 	(s = ua.match(/firefox\/([\d.]+)/)) ? browser.firefox = s[1] :
// 	(s = ua.match(/chrome\/([\d.]+)/)) ? browser.chrome = s[1] :
// 	(s = ua.match(/opera.([\d.]+)/)) ? browser.opera = s[1] :
// 	(s = ua.match(/version\/([\d.]+).*safari/)) ? browser.safari = s[1] : 0;
// 	//以下进行测试,实际运用中可能并不需要。  
// 	if (browser.ie) document.write('您使用的浏览器是IE: ' + browser.ie+'建议使用IE9.0及以上浏览器访问系统');  
// 	if (browser.firefox) document.write('Firefox: ' + browser.firefox);
// 	if (browser.chrome) document.write('Chrome: ' + browser.chrome);
// 	if (browser.opera) document.write('Opera: ' + browser.opera);
// 	if (browser.safari) document.write('Safari: ' + browser.safari);
 	
 	$(document).ready(function() {
 		$("#username").focus();
 		$("#loginForm").validate({
 			rules:{
 				username:{
 					required:true,
 					minlength:3,
 					maxlength:30
 				},
 				password:{
 					required:true,
 					maxlength:30,
 					minlength:6
 				},
 				project:{
 					required:true
 				}
 			},
 			messages:{
	 			username:{
	 				required:'请输入登录名',
	 				maxlength:$.format('不能超过{0}个字符'),
					minlength:$.format('不能少于{0}个字符')
	 			},password:{
					required:'请输入密码',
					maxlength:$.format('不能超过{0}个字符'),
					minlength:$.format('不能少于{0}个字符')
	 			},
	 			project:{
 					required:'请选择账户所在项目'
 				}
 			}
 		});
 		
 		
 		$('#username').change(function(){
 			$.ajax({
 				url : '${ctx}/sys/rbac/user/getProject',
 				type : 'POST',
 				dataType : 'json',
 				data : {loginName:$('#username').val()}
 			}).done(function (data, textStatus) {
 				$('#project').empty();
 				if ( data && data.length > 0 ){
 					for ( var i in data ){
 						$('#project').append('<option value="'+ data[i]['projectId'] +'">'+ data[i]['shortName'] +'</option>');
 					}
 				}
 			}).fail(function () {
 				//alert('error');
 			});	
 		});
 		
 		
 	});
	</script>  
</head>
<body class="login-body">
	<div id="wrap">
	<div id="DAT-login">
	<form id="loginForm" action="${ctx}/login" method="post">
        <!--main-bar-->
        <div id="main-center" class="clearfix">
            <div class="content">
            	<h2>临床试验项目平台<br><span style="font-size:10px;"><br/></>&nbsp;&nbsp;&nbsp;CLINICAL TRIAL PROJECT PLATFORM</span>
                </h2>
            	<div class="login_area_new">
                	<div class="login_img"></div>
                </div>
            	</div>
            <!--login-->
         	<div class="login-wrap">
                <div class="login">
                   <p>登录</p>
                   <form action="" method="get">
                   		<fieldset>
                            <ol>
                                <li>
                                    <label for=name>账&nbsp;号:</label>
                                    <input id="username" name="username" class="username" type="text" placeholder="请输入账号" autofocus required="required" />
                                </li>
                                <li>
                                    <label for=name>密&nbsp;码:</label>
                                    <input id="password" name="password" class="passward" type="password" placeholder="请输入密码" autofocus required/>
                                </li>
                                <li>
                                    <label class="ppp" for=project>项&nbsp;目:</label>
                                    <select id="project" name="project" class="ppp form-control" style="width:245px;height:35px;font-size:14px;">
                                    	<!-- <option value="">高危筛查项目</option>
                                    	<option>冠心病医疗改善研究</option> -->
                                    </select>
                                </li>
                                <div id="u4_rtf2">
                                    <p style="text-align: center;">
                                    	<%
											String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
											if(null == error){
												error = (String) request.getAttribute("errorMessage");
											}
											if (error != null) {
										%>
                                        <span  id ="error">
                                        	<%
											if (error.contains("DisabledAccountException")) {
												out.print("用户已被屏蔽,请登录其他用户");
											}else if(error.contains("UnknownAccountException")){
												out.println("用户不存在");
											}else if(error.contains("AuthenticationException")){
												out.println("登录名或密码错误");
											} else {
												out.print("登录失败，请重试");
											}
										%>
                                        </span>
                                    	<%}%>
                                    </p>
                               </div>
        
                                <li class="login-button">
                                    <input type="submit" id="submit" class="button" value="登录" />
                                    <input type="reset" id="reset" class="button02" value="重置" />
                                </li>
                             
                            </ol>
                         </fieldset>
                    </form>
                </div>
             <div class="bottom-bg"></div>
             </div>
        </div>
        <!--footer-->
        <div id="footer">Copyright © 2014. 国家心血管病中心 . All rights reserved</div>
  </form>
  </div>
</div>
</body>
</html>