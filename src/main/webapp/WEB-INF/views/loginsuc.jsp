<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.LockedAccountException"%>
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
<link rel="stylesheet" href="${ctx }/static/bootstrap/3.1.1/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="${ctx }/static/bootstrap/3.1.1/css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="${ctx }/static/bootstrap/3.1.1/css/bootstrap-theme.css" type="text/css" />
<link rel="stylesheet" href="${ctx }/static/bootstrap/3.1.1/css/bootstrap-theme.min.css" type="text/css" />
<script type="text/javascript" src="${ctx}/static/jquery/js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.js" ></script>
<script type="text/javascript" src="${ctx}/static/jquery-validation/1.11.1/messages_zh.js" ></script>
    <script>
    $(document).ready(function(){
        
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

<body>
<div id="brwrap">
	<!--head-->
    <div id="brHead">
    	<div class="logo">
        	<ul>
            	<li class="name"><a href="${ctx}/login"><img src="${ctx }/static/login/images/hospitalhead.png" width="670" height="74" alt="心血管病高危人群早期筛查与综合干预项目
" /></a></li>
				<li class="hospital-logo"><a href="${ctx}/login"><img src="${ctx }/static/login/images/hospitallogo.png" width="254" height="78"alt="心血管病高危人群早期筛查与综合干预项目
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
                <li><a href="./portals/content.jsp">项目内容</a></li>
                <li><a href="#">项目进展</a></li>
                <li><a href="#">项目考核</a></li>
                <li><a href="#">项目文件</a></li>
                <li><a href="#">联系我们</a></li>
                <li><a href="${ctx}/portal">管理端入口</a></li>
            </ol>
       </nav>
    </div>
    <!--head end-->
    <div id="news">
    <marquee id="newquee" behavior = "scroll" direction = "left" height="40" behavior = "scroll" onMouseOut="this.start()" 
    onMouseOver="this.stop()" scrollamount="3" scrolldelay="100" >
  	<p>心血管病高危人群早期筛查与综合干预项目启动暨培训会议于2014年9月29日召开！</p>
  	</marquee>
    </div>
     <div id="mainContent">
    	<!--aside-->
    	<div class="aside">
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
                                    <button  type="button" onClick="location.href='${ctx}/portal'" class="btn-style-default entrance">管理端</button>
                                 </li>
                                 <li>
                                    <button type="button" onClick="location.href='${ctx}/logout'" class="btn-style-default logout">注销</button>
                                 </li>
                    </ol>
                  </div>
            </div>
            <div class="column">
           		<header>
                	<h4>项目参加单位</h4>
                </header> 
                <div class="clearfix project list">
                  	<ol>
                     	<h5>辽宁省</h5>
                        <li>辽宁省人民医院</li>
                        <li>大连经济技术开发区医院</li>
                        <li>盘锦市中心医院</li>
                        <li>宽甸县中心医院</li>
                        <li class="last">灯塔市中医院</li>
                    </ol>
                    <ol>
                     	<h5>吉林省</h5>
                        <li>吉林大学第一医院</li>
                        <li>吉林市中心医院</li>
                        <li>延吉市中医院</li>
                        <li>辽源市第二人民医院</li>
                        <li class="last">通化县医院</li>
                    </ol>
                    <ol>
                     	<h5>浙江省</h5>
                        <li>丽水市中心医院</li>
                        <li>诸暨市第二人民医院</li>
                        <li>昌化人民医院</li>
                        <li>玉环县人民医院</li>
                        <li class="last">义乌市北苑医院</li>
                    </ol>
                    <ol>
                     	<h5>广西壮族自治区</h5>
                        <li>广西医科大学第一附属医院</li>
                        <li>柳州市钢铁(集团)公司职工医院</li>
                        <li> 柳江县人民医院</li>
                        <li>横县人民医院</li>
                        <li class="last"> 武鸣县人民医院</li>
                    </ol>
                </div>
            </div>
           
        </div>
        <!--aside end-->
        <div id="marginRight">
        	<div>
                <h3  class="newTitle">项目新闻 </h3>
                <div class="clearfix news">
                    <div class="blockquote">
                   		<p>高危筛查项目组IT人员到英国牛津大学CTSU进行学习考察</p>
                        <p>国家心血管病中心项目工作人员到中国慢性病前瞻性研究现场观摩学习 </p> 
                        <p>国家心血管病中心项目工作人员到辽宁省进行现场调研</p> 
                        <p>心血管病高危人群早期筛查与综合干预项目工作会在阜外医院召开</p> 
                        <p>国家心血管病中心项目工作人员到辽宁省进行现场调研</p>
                        <p>心血管病高危人群早期筛查与综合干预项目工作会在阜外医院召开</p> 
                        <p>国家心血管病中心项目工作人员到辽宁省进行现场调研</p> 
                    </div>
                    <figure class="imgRight"><img src="${ctx }/static/login/images/hospitalimg02.jpg" alt=""/></figure>
                </div>
             </div>
             <br style="clear: both;"/>
             <div class="moduleone">
                <h3 class="newTitle">心血管疾病可防可控，掌握疾病知识最关键</h3>
                <div class="modulContent">
                    <p class="img"><img src="${ctx }/static/login/images/hospitalimg01.png" width="147" height="103" alt=""  /></p>
                    <ol>
                        
                        <li>血脂异常是冠心病的独立危险因素</li>
                        <li>体力活动与冠心病风险的剂量-反应关系</li>
                        <li>社区心血管病二级预防应加强危险因素控制</li>
                        <li>心血管病的主要危险因素及其预防</li> 
                        <li>社区心血管病二级预防应加强危险因素控制</li>
                        <li><a href="#" class="more">更多</a></li>
                    </ol>
                </div>
                <br style="clear: both;"/>
            </div>
            <div class="moduMain">
            	<div class="moduleLeft">
                	<h3 class="newTitle">项目进度</h3>
                    <div class="modulOne">
                     	<p class="img"><img src="${ctx }/static/login/images/icon-img01.png" width="53" height="43" alt=""  /></p>
                        <ol>
                            <li>全国月度项目进展分析报告</li>
                            <li>各省初筛进展报告</li>
                            <li>各省高危对象调查进展报告</li>
                            <li><a href="#" class="more">更多</a></li>
                        </ol>
                    </div>
                    <div class="clearfix"></div>
                </div>
                <div class="moduleRight">
                	<h3 class="newTitle">质量监控</h3>
                    <div class="modulOne">
                     	<p class="img"><img src="${ctx }/static/login/images/icon-img02.png" width="53" height="43" alt=""  /></p>
                        <ol>
                            <li>项目质量控制方案</li>
                            <li>中心性数据监测</li>
                            <li>现场考核</li>
                            <li><a href="#" class="more">更多</a></li>
                        </ol>
                    </div>
                    <div class="clearfix"></div>
                </div>
                 <br style="clear: both;"/>
            </div>
            <div class="moduMain">
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
            </div>
            <br style="clear: both;"/>
            <div class="link">
                <p>友情链接:
                 <a href="http://www.nhfpc.gov.cn">中华人民共和国国家卫生和计划生育委员会</a> 
                 <a href="http://www.chinacdc.cn">中国疾病预防控制中心</a> 
                 <a href="http://www.fuwaihospital.org/">中国医学科学院阜外医院</a> 
                </p>
            </div>
        </div>
    </div>
    <br style="clear: both;"/>
     <div id="footer">
     	<p>Copyright © 2014. 国家心血管病中心 . All rights reserved</p>
     </div>
</div>
</body>
</html>

