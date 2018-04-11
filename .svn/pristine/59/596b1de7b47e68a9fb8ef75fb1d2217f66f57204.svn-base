<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException"%>
<%@page import="com.bdcor.pip.data.util.PoiUtil"%>
<%@page import="java.util.*"%>
<%@page import="com.bdcor.pip.web.data.domain.PipSysNews"%>
<%@page import="com.bdcor.pip.web.data.domain.PipSysNewsMarquee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Portal-index</title>
<link rel="stylesheet" href="${ctx }/static/login/css/reset.css" type="text/css" />
<link rel="stylesheet" href="${ctx }/static/login/css/main-style.css" type="text/css" />
<link rel="stylesheet" href="${ctx }/static/bootstrap/3.1.1/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="${ctx }/static/bootstrap/3.1.1/css/bootstrap-theme.min.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/zTree/3.5.15/css/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/static/jquery/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.js" ></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/messages_zh.js" ></script>
<script type="text/javascript" src="${ctx}/static/jquery/js/jquery.lksMenu.js"></script>
<script type="text/javascript" src="${ctx}/static/zTree/3.5.15/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery/js/flux.min.js"></script>
<script type="text/javascript" src="${ctx}/static/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<style>
	li.level1{
		border-bottom:1px #D8D1D6 solid;
	}
	ul.level0 li{
		width: 240px;
		word-wrap: break-word !important;
		word-break: normal !important;
	}
	.ztree li span.button.switch {
		width: 16px;
	}
</style>
    <script>
    var setting = {
    		async: {
    			enable: true,
    			type:"post",
    			url:"${ctx}/news/lccTree"
    		},
    		data: {
    			simpleData: {
    				enable: true,
    				idKey: "id",
    				pIdKey: "parentId",
    				rootPId: 0
    			}
    		},
    		view:{
    			showLine: false,
    			showIcon: false
    		}
    	};
    $(function(){
    	if($("#needPassword").val()!="false" && $("#secret_A").val()=="A"){
			modifyPWD_1('${ctx}/sys/rbac/user/openmodalupdatepassword');
		} 
    	
    });
    function modifyPWD_1(url){
        $( "#dialog-confirm_portal_1" ).modal({
             backdrop: 'static'
        });
        //使用此方法防止js缓存不加载
        $("#dialog-confirm_portal_1 p" ).load(url);
    }
    function submitPWD(){
        $("#updatepwd_form").submit();
    }
  //跳转到想要去的页面
	function goTo(href,flag){
		var f = 0;
		if(flag)
			f = flag;
		if(f==0)
			window.location.href = href;
		else
			window.open(href);
	} 
    function reLogin(){
        
        $("#dialog-confirm_portal_1 button").remove();
        
        var reloginBtn =$('<button class="btn btn-primary btn-sm" onclick="goTo(\''+'${ctx}/logout'+'\')">重新登录</button>');
        
        $("#dialog-confirm_portal_1 .modal-footer").append(reloginBtn);
    }
    $(document).ready(function(){
    	
    	//轮播效果start
    	if (!flux.browser.supportsTransitions)
             alert("该浏览器不支持特殊效果，推荐使用项目规定的Chrome浏览器！");
         window.f = new flux.slider('#slider', {
             pagination: true,
             transitions: ['dissolve']
         });
    	//end
    	$.fn.zTree.init($("#lccTree"), setting);
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
}); 
</script>
</head>

<body id="body_">
<!-- 强制修改密码 -->
		    <div id='dialog-confirm_portal_1' class="modal fade">
		      <div class="modal-dialog">
		        <div class="modal-content">
		          <div class="modal-header">
		            <h4 class="modal-title">修改密码</h4>
		          </div>
		          <div class="modal-body">
		            <p>加载中……</p>
		          </div>
		          <div class="modal-footer">
		            <button type="button"  class="btn btn-primary btn-sm" onclick="submitPWD()">提交</button>
		          </div>
		        </div><!-- /.modal-content -->
		      </div><!-- /.modal-dialog -->
		    </div><!-- /.modal -->
<input  type="hidden" id="needPassword" value="${neetAlterPassword }" />
<input  type="hidden" id="secret_A" value="${secret_A }" />  
<div id="brwrap">
	<!--head-->
    <div id="brHead">
    	<div class="logo">
        	<ul>
            	<li class="name"> <a href="${ctx}/login"><img src="${ctx }/static/login/images/hospitalhead.png" width="740" height="100" alt="心血管病高危人群早期筛查与综合干预项目
" /> </a></li>
				<li class="hospital-logo"> <a href="${ctx}/login"><img src="${ctx }/static/login/images/hospitallogo.png" width="270" height="80"alt="心血管病高危人群早期筛查与综合干预项目
" /></a> </li>
            </ul>
            <br style="clear:both" />
        </div>
       
    </div>
    <div id="nav">
      <nav class="nav">
            <ol>
            	<li  class="home"><a href="./login" >首页</a></li>
                <li><a href="./portals/project.jsp">项目介绍</a></li>
                <li><a href="./portals/organization.jsp">组织机构</a></li>
                <!-- <li><a href="./portals/content.jsp">项目内容</a></li> -->
                <li><a href="#">项目进展</a></li>
                <li><a href="#">项目考核</a></li>
                <li><a href="./portals/file.jsp">项目文件</a></li>
                <li><a href="./portals/contact.jsp">联系我们</a></li>
            </ol>
       </nav>
    </div>
    <!--head end-->
    <!-- 滚动条 start -->
    <%-- <div id="news">
    <marquee id="newquee" behavior = "scroll" direction = "left" height="40" behavior = "scroll" onMouseOut="this.start()" 
    onMouseOver="this.stop()" scrollamount="3" scrolldelay="100" >
    <p>
    					<%
							List<PipSysNewsMarquee> marquees = PoiUtil.queryMarqueeNews();
                    		if ( marquees != null && marquees.size() > 0 ){
    							for ( PipSysNewsMarquee news : marquees ) {
						%>
                   		<%= news.getDisplayText()%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <% }
						}%>
						</p>
  	</marquee>
    </div> --%>
    <!-- 滚动条 start -->
     <div id="mainContent">
    	<!--aside-->
    	<div class="aside">
    	<shiro:authenticated>
    	<div class="column">
           		<header>
                	<h4>登录用户信息</h4>
                </header> 
                <div class="clearfix loginSuc">
                   <ol>
                   		<li>登录名:<span class="first"><shiro:principal property="loginName" /></span> </li>
                        <li>姓名: <span><shiro:principal property="name" /></span></li>
                        <li>PID: <span><shiro:principal property="lccCode" /></span></li>
                        <li>单位: <span><shiro:principal property="lccName" /></span></li>
                    </ol>
                    <ol>
                    	  <li>
                                    <button  type="button" onClick="location.href='${ctx}/managementIndex'" class="btn-style-default entrance">管理端</button>
                                 </li>
                                 <li>
                                    <button type="button" onClick="location.href='${ctx}/logout'" class="btn-style-default logout">注销</button>
                                 </li>
                    </ol>
                  </div>
            </div>
            </shiro:authenticated>
            <shiro:notAuthenticated>
        	<div class="column">
           		<header>
                	<h4>用户登录</h4>
                </header> 
                <div class="clearfix leftLogin">
                    <form id="loginForm" action="${ctx}/login" method="post">
                        <fieldset>
                            <ol>
                                  <li>
                                    <input id="username" name="username" class="username" type="text" placeholder="请输入用户名" autofocus required />
                                 </li>
                                 <li>
                                    <input id="password" name="password" class="password"type="password" placeholder="请输入密码" required  />
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
                                        <span  id ="error" style="color: #ff0000">
                                            <%
                                            if (error.contains("DisabledAccountException")) {
                                                out.print("该账户无效");
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
                            </ol>
                            <ol>
                                 <li>
                                    <button  type="submit" id="submit" class="btn-style-default login">登录</button>
                                 </li>
                                 <li>
                                    <button type="reset" id="reset"  class="btn-style-default cacel last">取消</button>
                                 </li>
                            </ol>
                            
                            
                            
                        </fieldset>
                    </form>
                  </div>
            </div>
            </shiro:notAuthenticated>
            <div class="column">
           		<header>
                	<h4>项目参加单位</h4>
                </header> 
                <div id="lcc" class="">
					<ul id="lccTree" class="ztree" style="border: 1px solid #d1d1d1;">
						
					</ul>
                </div>
            </div>
           
        </div>
        <!--aside end-->
        <div id="marginRight">
        	<div>
                <h3  class="newTitle">项目新闻 </h3>
                <br />
                <div class="clearfix news">
                    <div class="blockquote">
                    	<%
							List<PipSysNews> list = PoiUtil.queryNews("1");
                    		if ( list != null && list.size() > 0 ){
    							for ( PipSysNews news : list ) {
						%>
                   		<p><a class="divout" style="color:#004280;" href="./portals/newsdetail.jsp?id=<%=news.getId() %>"><%=news.getTitle() %></a></p>
                        <% }
						}%>
                    </div>
                    <figure class="imgRight">
                    	<div id="slider">
					        <%-- <img src="${ctx }/static/login/images/hospitalimg02.jpg" alt="" /> --%>
					    </div>
                    </figure>
                </div>
             </div>
             <br style="clear: both;"/>
             <div class="moduleone">
            	<div class="moduleLeft">
                	<h3 class="newTitle">项目通知</h3>
                	<br />
                    <div class="modulOne">
                     	<p class="img"><img src="${ctx }/static/login/images/icon-img04.png" width="53" height="43" alt=""  /></p>
                        <!-- <ol>
                            <li>全国月度项目进展分析报告</li>
                            <li>各省初筛进展报告</li>
                            <li>各省高危对象调查进展报告</li>
                            <li><a href="#" class="more">更多</a></li>
                        </ol> -->
                        <ol>
                            <li>.</li>
                            <li>.</li>
                            <li>.</li>
                            <li>.</li>
                            <li>.</li>
                            <li><a href="#" class="more">更多</a></li>
                        </ol> 
                    </div>
                    <div class="clearfix"></div>
                </div>
                <!-- class="moduleRight" -->
                <div class="moduleRight">
                <h3 class="newTitle">项目简报</h3>
                <br />
                    <div class="modulOne">
                     	<p class="img"><img src="${ctx }/static/login/images/document.png" width="53" height="43" alt=""  /></p>
                        <!-- <ol>
                            <li>项目质量控制方案</li>
                            <li>中心性数据监测</li>
                            <li>现场考核</li>
                            <li><a href="#" class="more">更多</a></li>
                        </ol> -->
                        <ol>
                            <li>.</li>
                            <li>.</li>
                            <li>.</li>
                            <li>.</li>
                            <li>.</li>
                            <li><a href="#" class="more">更多</a></li>
                        </ol> 
                    </div>
                    <div class="clearfix"></div>
                </div>
                 <br style="clear: both;"/>
            </div>
             <%-- <div class="moduMain">
                <h3 class="newTitle">心血管疾病可防可控，掌握疾病知识最关键</h3>
                <div class="modulContent">
                    <p class="img"><img src="${ctx }/static/login/images/hospitalimg01.png" width="147" height="103" alt=""  /></p>
                    <ol>
                        <%
							List<PipSysNews> list2 = PoiUtil.queryNews("2");
                    		if ( list2 != null && list2.size() > 0 ){
    							for ( PipSysNews news : list2 ) {
						%>
                   		<li><a class="divout" style="color:#004280;" href="./portals/newsdetail.jsp?id=<%=news.getId() %>"><%=news.getTitle() %></a></li>
                        <% }
						}%>
                        <li><a href="#" class="more">更多</a></li>
                    </ol>
                </div>
                <br style="clear: both;"/>
            </div> --%>
            
            <%-- <div class="moduMain">
            	<div class="moduleLeft">
                	<h3 class="newTitle">工作流程</h3>
                    <div class="modulOne">
                     	<p class="img"><img src="${ctx }/static/login/images/icon-img03.png" width="53" height="43" alt=""  /></p>
                        <ol>
                            <li>筛查登记</li>
                            <li>初筛</li>
                            <li>高危对象调查</li>
                            <li><a href="#" class="more">更多</a></li>
                        </ol>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="moduleRight">
                	<h3 class="newTitle">经验交流</h3>
                    <div class="modulOne">
                     	<p class="img"><img src="${ctx }/static/login/images/icon-img04.png" width="53" height="43" alt=""  /></p>
                        <ol>
                            <li>国家协调中心与中国慢性病前瞻性<br />研究人员召开现场工作交流会议</li>
                            <li><a href="#" class="more">更多</a></li>
                        </ol>
                    </div>
                </div>
            </div> --%>
            <br style="clear: both;"/>
            <%-- <jsp:include page="/portals/link.jsp" ></jsp:include> --%>
        </div>
    </div>
    <br style="clear: both;"/>
     <div id="footer">
     	<p>Copyright © 2014. 国家心血管病中心 . All rights reserved</p>
     </div>
</div>
</body>
</html>

