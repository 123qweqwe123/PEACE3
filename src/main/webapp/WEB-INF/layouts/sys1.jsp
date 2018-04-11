<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>bdcor-<sitemesh:title /></title> 
<%@ include file="/WEB-INF/common/common-js-style.jsp"%>
<%@ include file="/WEB-INF/common/table-js-style.jsp"%> 
<%@ include file="/WEB-INF/common/less-common-js-style.jsp"%>
<%@ include file="/WEB-INF/common/tree-js-style.jsp"%>
<%@ include file="/WEB-INF/common/custom-js-style.jsp"%> 
<%@ include file="/WEB-INF/common/backtop.jsp"%>
<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/bdcor/images/airchina.ico" /> --%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<sitemesh:head />
</head>
<body> 
	<div id="screenWidth" class="container">
	<div  id="fixTopDiv" class="bs-navbar-fixed-top" >
			<div class="topPic topPicportal"></div>
			<!-- navbar begin -->
			<div class="navbar navbar-default">  
				 		<div class="navbar-header">
						 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
						      <span class="icon-bar"></span>
						      <span class="icon-bar"></span>
						      <span class="icon-bar"></span>
				    	</button> 
				    	</div>
						<div class="collapse navbar-collapse navbar-ex1-collapse" style="position: static;font-weight:bold">
							<ul class="nav navbar-nav">
		
								<shiro:hasPermission name="permissionmgt:administrator">
								<li id="tenant_nav"><a href="javascript:{}" onclick="goTo('${ctx}/sys/dict/grid_init?tableName=COMM_DICT_COUNTRY&dictTypeCode=COUNTRY');">基础字典管理</a></li>
	                            </shiro:hasPermission>
	                            
	                            <shiro:hasPermission name="permissionmgt:administrator">
								<li id="module_nav"><a href="javascript:{}" onclick="goTo('${ctx}/sys/rbac/module');">模块管理</a></li>
								</shiro:hasPermission>
								
								<shiro:hasPermission name="permissionmgt:administrator">
								<li id="permissions_nav"><a href="javascript:{}" onclick="goTo('${ctx}/sys/rbac/permission');">权限管理</a></li>
	                            </shiro:hasPermission>
	                            
	                            <shiro:hasPermission name="sysmgt:orgainzation">
								<li id="org_nav"><a href="javascript:{}" onclick="goTo('${ctx}/sys/rbac/organization');">组织机构管理</a></li>
	                            </shiro:hasPermission>
	                            
	                            <shiro:hasPermission name="sysmgt:role">
								<li id="role_nav"><a href="javascript:{}" onclick="goTo('${ctx}/sys/rbac/role');">角色管理</a></li>
	                            </shiro:hasPermission>
		
	                            <shiro:hasPermission name="sysmgt:user">
								<li id="user_nav"><a href="javascript:{}" onclick="goTo('${ctx}/sys/rbac/user');">用户管理</a></li>
	                            </shiro:hasPermission>
							
					            <shiro:hasPermission name="sysmgt:log">
								<li id="log_nav"><a href="javascript:{}" onclick="goTo('${ctx}/sys/log');">日志管理</a></li>
	                            </shiro:hasPermission>
								
                                <li id="log_nav"><a href="javascript:{}" onclick="goTo('${ctx}/sys/rbac/menu');">菜单管理</a></li>
                                
							</ul>
							<ul class="nav navbar-nav pull-right">
			
							<li id="systemManage_nav"><a href="javascript:{}" onclick="goTo('${ctx}/portal')"><i class="glyphicon glyphicon-home" title="工作台"></i></a></li>
							<li class="dropdown">
									<a href="#" class="dropdown-toggle" data-toggle="dropdown">
										<i class="glyphicon glyphicon-user"></i>
										<shiro:principal property="name" /> 
										<b class="caret"></b>
									</a>
									<ul class="dropdown-menu">
										<li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">退出系统</a></li>
										<li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">测试菜单1</a></li>
                                        <li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">测试菜单2</a></li>
                                        <li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">测试菜单3</a></li>
                                        <li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">测试菜单4</a></li>
                                        <li class="dropdown2">     
                                           <a href="javascript:{}" class="dropdown-toggle2" >测试菜单5    </b></a>
                                          
                                           <ul class="dropdown-menu2" >
                                               <li><a href="javascript:{}" >三级子菜单</a></li>
                                           </ul>
                                        </li>
                                        
                                        
                                        
                                        
									</ul>
								</li>
							</ul>
						</div>
						<!-- /.nav-collapse -->
					</div>  
		</div>
			<!-- navbar end -->
		<div id="clearFixTop">
			<sitemesh:body /> 
		</div> 
		<footer class="footer" style="text-align: center;clear:both;">
		  Copyright © 2012-2014.  Air China.  All rights reserved 
		</footer> 
	</div> 
	
	<script type="text/javascript">
	$(document).ready(function(){
		$(".dropdown-toggle2").mouseover(function (){
			//alert($(this).parent().find(".dropdown-menu2").html())
			$(this).parent().find(".dropdown-menu2").Left='200';
            $(this).parent().find(".dropdown-menu2").Top='60';
			
			$(this).parent().find(".dropdown-menu2").css("display","block");
			
			
			
			//alert(  $(this).position().left  );
			
		}
				
		);  
		
	});
	
	
	</script>
	
	
	
</body>
</html>