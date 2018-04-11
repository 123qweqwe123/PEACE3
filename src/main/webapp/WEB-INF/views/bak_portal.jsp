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
	function infoBox(){$('#noCompleteBox').modal({backdrop : false,keyboard : true});}
	function redirectTo(target){
		if(target){window.location = "${ctx}/portal/redirectTo?target="+target;}
		else{showAlertCk('#error-text','您没有权限,如有疑问请联系管理员。');}
	}
	var ___model_items = 0;
	var ___target = "";
	function initUrl(id, url){
		if(id){$("#"+id).bind("click",url,function(){
			redirectTo(url);
		});
		if(url){
			___model_items++;
			___target=url;
			}
		}
	}
	jQuery(window).load(function() {
		//alert(___target)
		if(___model_items == 1){redirectTo(___target);}
		$(".animate").each(function() {
		}).hover(function() {
			$(this).attr("src","${ctx}/static/bdcor/images/icon/"+$(this).attr("id")+"_active.png"); 
		}, function() {
			$(this).attr("src","${ctx}/static/bdcor/images/icon/"+$(this).attr("id")+"_inactive.png");
		});
	});
</script>
<style type="text/css">
* {margin: 0;padding: 0;}
.portolBtn {float: left;display: block;	width: 15%;cursor: pointer;}
.offset {float: left;display: block;width: 2%;}
</style>
</head>   
<body>
	<div id="myCarousel" class="carousel slide">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
			<li data-target="#myCarousel" data-slide-to="3"></li>
			<li data-target="#myCarousel" data-slide-to="4"></li>
		</ol>
		<%
		 String tenantName = "dalianairlines";//Securitys.getTenantName();
		/*temp*/
		String userName = Securitys.getLoginName();
		if(userName.startsWith("dl_")){
			tenantName = "dalianairlines";
		} 
		/*temp-end*/
		 if(StringUtils.isBlank(tenantName)){
			tenantName = "";
		}else{
			tenantName = tenantName.toLowerCase();
		} 
			
		%>
		<div class="carousel-inner">
		
			<%-- <div class="item active"><img src="${ctx}/static/bdcor/images/tenant/<%=tenantName %>/ahm.jpg" alt=""></div>
			<div class="item"><img src="${ctx}/static/bdcor/images/tenant/<%=tenantName %>/fuel.jpg" alt=""></div>
			<div class="item"><img src="${ctx}/static/bdcor/images/tenant/<%=tenantName %>/ipc.jpg" alt=""></div>
			<div class="item"><img src="${ctx}/static/bdcor/images/tenant/<%=tenantName %>/asset.jpg" alt=""></div>
			<div class="item"><img src="${ctx}/static/bdcor/images/tenant/<%=tenantName %>/gis.jpg" alt=""></div>  --%>
		</div>
		<a class="left carousel-control" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a> 
		<a class="right carousel-control" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
	</div>

	<div class="row" style="margin-left: 0px; margin-right: 0px; margin-top: 20px;">
		<div class="portolBtn">
			<img id="ahm" width="100%" alt="" src="${ctx}/static/bdcor/images/icon/ahm_inactive.png" class="animate">
			<script>initUrl('ahm'<shiro:hasPermission name="airplane-health-management">,'airplane-health-management'</shiro:hasPermission>);</script>
		</div>
		<div class="offset">&nbsp;</div>
		<div class="portolBtn">
			<img id="fuel" width="100%" alt="" src="${ctx}/static/bdcor/images/icon/fuel_inactive.png" class="animate">
			<script>initUrl('fuel'<shiro:hasPermission name="oil-management">,'oil-management'</shiro:hasPermission>);</script>
		</div>
		<div class="offset">&nbsp;</div>
		<div class="portolBtn">
			<img id="ipc" width="100%" alt="" src="${ctx}/static/bdcor/images/icon/ipc_inactive.png" class="animate">
			<script>initUrl('ipc'<shiro:hasPermission name="ipc-management">,'ipc-management'</shiro:hasPermission>);</script>
		</div>
		<div class="offset">&nbsp;</div>
		<div class="portolBtn">
			<img id="asset" width="100%" alt="" src="${ctx}/static/bdcor/images/icon/asset_inactive.png" class="animate">
			<script>initUrl('asset'<shiro:hasPermission name="asset">,'asset'</shiro:hasPermission>);</script>
		</div>
		<div class="offset">&nbsp;</div>
		<div class="portolBtn">
			<img id="map" width="100%" alt="" src="${ctx}/static/bdcor/images/icon/map_inactive.png" class="animate">
			<script>initUrl('map');</script>
		</div>
		<div class="offset">&nbsp;</div>
		<div class="portolBtn">
			<img id="acars" width="100%" alt="" src="${ctx}/static/bdcor/images/icon/acars_inactive.png" class="animate">
			<script>initUrl('acars'<shiro:hasPermission name="acars-management">,'acars-management'</shiro:hasPermission >);</script>
		</div>

	</div>
	<div id="error-text"></div>
</body>
</html>
