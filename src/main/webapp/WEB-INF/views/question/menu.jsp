<%@page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.bdcor.pip.client.ClientInfo"%>
<%@page import="com.bdcor.pip.client.type.ClientType,com.bdcor.pip.client.vo.dict.handler.DictFactory"%>
<%@page import="com.bdcor.pip.client.program.ProgramInfo,com.bdcor.pip.client.service.CurrentLoginUser"%>

<%@include file="/common/internineingizine.jsp" %>
<%@page import="java.util.List"%>
<%@page import="com.bdcor.pip.client.authority.AuthorityHandler"%>
<%@page import="com.bdcor.pip.client.authority.menu.Menu"%>





<script>
$(function(){
	$.each($("a[id^='menu_']"), function(i,n){
		$(this).click(function(){
			$('.dropdown-menu').hide();
			$(this).parent().children(".dropdown-menu").toggle();
		});
	});
	
	$('body').click(function(e){
		var executors_name_val = $(e.target).attr('id') || '';
		if (executors_name_val.indexOf('menu_') != 0){
			$('.dropdown-menu').hide();
		}
	});
	

	
})
	var ctx = "${ctx}";
	var leaveCheck=false;
	var MACHINE_NAME = '<%=ClientInfo.terminal.getTerminalname() %>';
	var isAdmin = <%="admin".equals(CurrentLoginUser.getUser().LOGINNAME)?"true":"false" %>;
	
	function jumpJsp(path){
		if(leaveCheck){
			jConfirm("页面数据未保存，离开页面数据会丢失，确认离开吗？",null,function(r){
				if(r){
					location.replace(path);
				}
			});
		}else{
			location.replace(path);
		}
	}
	
	function checkFridge(){
		<%if(DictFactory.getLccHandler().getLccInfo() == null ||DictFactory.getLccHandler().getLccInfo().fridges == null || DictFactory.getLccHandler().getLccInfo().fridges.size()==0){%>
			jAlert("未从服务端获取到冰箱信息");
		<%}else{%>
			jumpJsp('${ctx}/protected/scm/frozenStorageIndex.jsp');
		<%}%>
	}
	
	function initU(){
		$.post('/datclient/protected/initU/initU.jsp','{}',function(info){popWinWithHtmlInfo(info,'选择要初始化的U盘',480,150);})
	}
	function initUSub(){
		var uLength = $("input[name='selectU']:checked").length;
		if(uLength == 0){
			jAlert("请选择U盘");
			return;
		}
		var u = $("input[name='selectU']:checked").val().trim().substr(0,1);
		jConfirm("您选择的U盘是可移动磁盘"+u+",初始化会清除U盘上的全部数据，并进行格式化。确定初始化吗？",null,function(r){
			if(r){
				$("#messageSpan").text("正在初始化，请耐心等待...");
				$("#initUSubButton").attr("disabled","disabled");
				$.post("${ctx}/protected/initU/initUJava.jsp",{selectU:u},function(info){
					jAlert("初始化完成");
					$('#dialog_detail').modal('hide');
				});
			}
		});
		
	}
	
	function about(){
	    $.post('/datclient/protected/help/aboutPop.jsp','{}',function(info){popWinWithHtmlInfo(info,'关于',580,200);});
	}
	
	function sysState(){
	    $.post('/datclient/protected/help/systemState.jsp','{}',function(info){popWinWithHtmlInfo(info,'系统状态',580,200);});
	}
	
	
	//修改机器名称
	function changeMaName(){
		var url = "/datclient/protected/config/changeMachineName.jsp";
		$.get(url,"{}",function(info){
			popWinWithHtmlInfo(info,"更改机器名称",580,500);
		});
	}
	
	//修改机器名称
	function changeIsOnline(){
		var url = "/datclient/protected/config/changeIsOnline.jsp";
		$.get(url,"{}",function(info){
			popWinWithHtmlInfo(info,"在线状态修改",580,500);
		});
	}
</script>
<<style>
<!--

#machineName2{
overflow:hidden; 
text-overflow:ellipsis;
white-space: nowrap;
width:80px;}


-->
</style>  
<%
List<Menu> menus = AuthorityHandler.instance().getMenus();
request.setAttribute("menus", menus);
%>
	<div id="topnavbar">
	  <div id="topnanv">
		  <nav>
            <span id='machineName' id='tab_scan' title='<%=ClientInfo.terminal.getTerminalname() %>'><a href="#" onclick="changeMaName();"><b style="color:#eee;">(<%=ClientInfo.terminal.getTerminalShortName(8) %>)</b></a></span>
            <ul class="left-posbox">
            	<c:forEach items="${menus }" var="menu">
            		<c:if test="${menu.hPosition=='left'}">
            			<c:if test="${empty menu.childs }">
            				<li><a href="javascript:
            				<c:if test="${menu.onclick!=''}">${menu.onclick}</c:if>
            				<c:if test="${menu.onclick==''}">jumpJsp('${ctx}/${menu.url}');</c:if>
            				">
            				${menu.showText}</a></li>
            			</c:if>
            			<c:if test="${!empty menu.childs }">
            				<li class="dropdown" >
            					<a href="#" id="${menu.id}"><fmt:message key="menu.${menu.clientCode}" bundle="${userBundle}"/>&nbsp;&nbsp;<i></i></a>
            					<ul class="dropdown-menu" style="margin:1px 0px;z-index:1000" >
            						<c:forEach items="${menu.childs }" var="cmenu">
	            						<li style="height:26px !important;">
	            							<a href="javascript:
	            							<c:if test="${cmenu.onclick!=''}">${cmenu.onclick}</c:if>
            								<c:if test="${cmenu.onclick==''}">jumpJsp('${ctx}/${cmenu.url}');</c:if>
	            							">
	            							${cmenu.showText}</a>
	            						</li>
            						</c:forEach>
            					</ul>
            				</li>
            			</c:if>
            		</c:if>
            	</c:forEach>
            </ul>
            
            <ul class="right-posbox" >
            	<c:forEach items="${menus }" var="menu">
            		<c:if test="${menu.hPosition=='right'}">
            			<c:if test="${empty menu.childs }">
            				<li><a href="javascript:jumpJsp('${ctx}/${menu.url}');">${menu.showText}</a></li>
            			</c:if>
            			<c:if test="${!empty menu.childs }">
            				<li class="dropdown" >
            					<a href="#" id="${menu.id}">${menu.showText}&nbsp;&nbsp;<i></i></a>
            					<ul class="dropdown-menu" style="margin:1px 0px;z-index:1000" >
            						<c:forEach items="${menu.childs }" var="cmenu">
	            						<li style="height:26px !important;">
	            							
	            							
	            							<a href="javascript:
					            				<c:if test="${cmenu.onclick!=''}">${cmenu.onclick}</c:if>
					            				<c:if test="${cmenu.onclick==''}">jumpJsp('${ctx}/${cmenu.url}');</c:if>
					            				">
					            				${cmenu.showText}</a>
	            							
	            							
	            						</li>
            						</c:forEach>
            					</ul>
            				</li>
            			</c:if>
            		</c:if>
            	</c:forEach>
            </ul>
		 </nav>
	   </div>
    </div>
	<!--end head-->
