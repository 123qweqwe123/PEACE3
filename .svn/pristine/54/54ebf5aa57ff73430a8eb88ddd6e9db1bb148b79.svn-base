<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh"
    uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>CHINAPEACE3项目-<sitemesh:title /></title>  
<%@ include file="/WEB-INF/common/common-js-style.jsp"%>
<%@ include file="/WEB-INF/common/custom-js-style.jsp"%>
<%@ include file="/WEB-INF/common/backtop.jsp"%>
<%@ include file="/WEB-INF/common/less-common-js-style.jsp"%>
<%@ include file="/WEB-INF/common/tree-js-style.jsp"%>
<%@ include file="/WEB-INF/common/table-js-style.jsp"%>  
<%@ include file="/WEB-INF/common/custom-js-style.jsp"%>
<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/bdcor/images/airchina.ico" /> --%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.1.1/css/bootstrap.css" /> 
<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/switch/css/bootstrap-switch.css" />
<link rel="stylesheet" href="${ctx}/static/jquery/css/jquery.alerts.css" type="text/css" />
<script type="text/javascript" src="${ctx}/static/jquery/js/jquery.alerts.js"></script>
 
<script type="text/javascript">  


function changeProject(param){
	alert('hi~');
}
  <shiro:hasPermission name="basic:changepassword"> 
    function modifyPWD(url){
        $( "#dialog-confirm_portal" ).modal({
             backdrop: 'static'
        });
        //使用此方法防止js缓存不加载
        $("#dialog-confirm_portal p" ).load(url);
    }
    
    function submitPWD(){
        $("#updatepwd_form").submit();
    }
    
    function reLogin(){
    
        $("#dialog-confirm_portal button").remove();
        
        var reloginBtn =$('<button class="btn btn-primary btn-sm" onclick="goTo(\''+'${ctx}/logout'+'\')">重新登录</button>');
        
        $("#dialog-confirm_portal .modal-footer").append(reloginBtn);
    }
    
    function modifyPwdFail(){
        var btn = '<button type="button" class="btn" data-dismiss="modal">关闭</button>';
        $("#dialog-confirm_portal .modal-footer button").remove();
        $("#dialog-confirm_portal .modal-footer").append(btn);
        
    }
 </shiro:hasPermission>
    
 function dis(e){
	 window.event.stopPropagation();
	 return false;
 }
 
 $(document).ready(function(){
	 
	 
	 var timebak = new Date().getTime();
     
	    $.getJSON(
	        '<c:url value="/sys/rbac/menu/menuTree?time='+timebak +'"/>',
	        {
	            param : "sanic"
	        },
	        function(data) {

	            var bars = new Array();
	            var downItem = new Array();
	            var menu = $("#dynamicMenu");

	            //此处返回的data已经是json对象   循环 记录 构建 二级菜单
	            //以下其他操作同第一种情况 
	            $.each(
	                    data,
	                    function(idx, item) {

	                        var i = 0;
	                        var parentId = item['PARENT'] ? item['PARENT']
	                                : item['parent'];
	                        mu_name = item['TEXT'] ? item['TEXT']
	                                : item['text'];
	                        mu_id = item['ID'] ? item['ID']
	                                : item['id'];
	                        mu_target = item['TARGET'] ? item['TARGET']
	                                : item['target'];
	                        //如果是模块    则显示到横排菜单中 
	                        if (parentId == 1) {
	                            i++;

	                            this.data = new Object();

	                            var ul = $("<ul class='left-posbox'></ul>");
	                            var li = $(" <li class='dropdown'></li>");

	                            var hrefStr = "<a href='#' class='dropdown-toggle' data-toggle='dropdown'>"
	                                    + mu_name
	                                    + "<b class='caret'></b></a>";
	                            var href = $(hrefStr);

	                            var subulstr = "<ul class='dropdown-menu' style='margin:15px 0px;z-index:1000' id='menu_"+ mu_id +"'></ul>";
	                            var ul_sub = $(subulstr);

	                            ul.append(li.append(href).append(href).append(ul_sub));

	                            menu.append(ul);
	                            return true;//同countinue，返回false同break 
	                        }
	                        menu_it = new Object();
	                        if(mu_name=="日志管理"){
	                            var menuItem = "<li id='menu_item"  + mu_id +  "' ><a href='javascript:void(0);'>"
	                            + mu_name
	                            + "</a></li>";
	                        }else{
	                        	var clickStr = mu_target==null?"onclick='dis(this);'":"onclick='goTo(\"${ctx}"+ mu_target+"\");'";
	                            var menuItem = "<li id='menu_item"  + mu_id +  "' ><a href='javascript:{}' "+clickStr+">"
	                            + mu_name
	                            + "</a></li>";
	                        }


	                        menu_it["li"] = menuItem;
	                        menu_it["pid"] = parentId;

	                        downItem.push(menu_it);

	                    });

	            $.each(downItem,
	                function(idx, item) {
	                    var pid = item.pid;
	                    findStr = "#menu_"
	                            + pid;
	                    var e_li = $(item.li);
	                    menu.find(findStr)
	                            .append(e_li);
	                    
	                    
	                    var curr_sub;
	                    // 三级菜单
	                    $.each(
	                        data,
	                        function(idx, item) {
	                            var pId = item['PARENT'] ? item['PARENT']
	                            : item['parent'];
	                            var m_name = item['TEXT'] ? item['TEXT']
	                                    : item['text'];
	                            var m_id = item['ID'] ? item['ID']
	                                    : item['id'];
	                            var m_target = item['TARGET'] ? item['TARGET']
	                                    : item['target'];
	                            //alert( pId +':' + m_name + ':' + m_id + ':' + m_target );
	                            
	                            if ( e_li.attr("id").replace('menu_item','') == pId && pId != '1'){
	                                //alert(e_li.attr("id").replace('menu_item',''));
	                                //alert(m_name + m_target);
	                                e_li.addClass("dropdown-submenu");
	                                //alert($('#level3_' + pId).length);
	                                if ( $('#level3_' + pId)  && $('#level3_' + pId).length > 0 ){
	                                    //alert(1);
	                                }else{
	                                    //alert(2);
	                                    e_li.append( '<ul id="level3_'+pId+'" class="dropdown-menu"></ul>' );
	                                }
	                                
	                                $('#level3_' + pId ).append('<li id="sub_item'+ m_id 
	                                        + '"><a href="javascript:{}"  onclick="goTo(\'${ctx}'+ m_target+ '\');">'
	                                        + m_name
	                                        + '</a></li>');
	                            
	                            }
	                            
	                        }
	                    );  

	            });
	            
	            
	        });
	    
	    
	

	});
</script>
<style type="text/css">
	ul.right-posbox > li > a{
		padding-top: 10px;
	}
	
	#header{
		margin:25px auto 15px;
	}
</style>

  <sitemesh:head />    
</head>
<body>
	<div id="wrap"  style="background:#FFFFFF;">
		<div id="topnavbar">
			<div id="topnanv">
				<nav style="margin:0 auto;max-width:1350px;">
					<div id="dynamicMenu"  >
						<!-- <ul class="left-posbox"><li class="dropdown"><a href="${ctx}/login" class="" data-toggle="">首页</a></li></ul> -->
						<ul class="left-posbox"><li class="dropdown"><a href="${ctx}/managementIndex" class="" data-toggle="">首页</a></li></ul>
					</div>
					<!-- <ul class="left-posbox">
						<li id="li-1"><a href="#">项目管理</a></li>
						<li id="li-2"><a href="#">质量管理</a></li>
						<li id="li-3"><a href="#">进度管理 </a></li>
						<li id="li-4"><a href="#">样本管理</a></li>
						<li id="li-5"><a href="#">特资管理</a></li>
						<li id="li-6"><a href="#">经旨管理</a></li>
						<li id="li-7"><a href="#">基础数据</a></li>
						<li id="li-8"><a href="#">下载次数</a></li>
					</ul> -->
					
					<ul class="nav navbar-nav pull-right right-posbox">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" style="background-color:#00036a;"> 
                                
                                    <i class="glyphicon glyphicon-user"></i>
                                    <shiro:principal property="name" /> 
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu" style="margin:15px 0px;min-width:100px;">
                                    <shiro:hasPermission name="basic:changepassword">
                                    <li><a href="javascript:{}"  onclick="modifyPWD('${ctx}/sys/rbac/user/openmodalupdatepassword');">修改密码</a></li>
                                    </shiro:hasPermission>
                                    <%-- <shiro:hasPermission name="sys-management">
                                    <li><a href="javascript:{}" onclick="goTo('${ctx}/portal/redirectTo?target=sys-management');">系统管理</a></li>
                                    </shiro:hasPermission> --%> 
                                    <li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">退出系统</a></li>
                                </ul>
                            </li>
                        </ul>
                        
                        <ul class="nav navbar-nav pull-right right-posbox">
                            <li class="dropdown">
                                <a href="#" style="background-color:#00036a;" onclick="goTo('${ctx}/managementIndex');">返回</a>
                            </li>
                        </ul>
				</nav>
			</div>
			
			<br style="clear: both;" />
		</div>
		<div style="background:#fff;height:2px">&nbsp;</div>
		<div id="header">
				<figure class="header-logo">
					<a href="${ctx}/login"> <img src="${ctx}/static/bootstrap/3.1.1/images/logo2.png" width="761" height="107"  /></a>
				</figure>
				<figure class="header-img">
					<br>
					<a href="${ctx}/login"> <img src="${ctx}/static/bootstrap/3.1.1/images/head-image-2.png" width="328" height="107" /></a>
				</figure>
			</div>
		
		<div id="clearFixTop" class="row" style="margin:0px 15px;min-height:410px;">
		<div id="main-content">  
       		<sitemesh:body />  
       	</div>	      
  		</div>

		<footer class="footer" style="text-align: center;clear:both;">   
              Copyright © 2014. 国家心血管病中心 . All rights reserved
        </footer>
        
        <shiro:hasPermission name="basic:changepassword">
    <div id='dialog-confirm_portal' class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
            <h4 class="modal-title">修改密码</h4>
          </div>
          <div class="modal-body">
            <p>加载中……</p>
          </div>
          <div class="modal-footer">
            <button type="button" id ='cancel' data-dismiss="modal" class="btn btn-default btn-sm" tabindex="1001">取消</button>
            <button type="button" id ='do_save' class="btn btn-primary btn-sm" onclick="submitPWD()" tabindex="1000">提交</button>
          </div>
        </div><!-- /.modal-content -->
      </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
    </shiro:hasPermission> 
</div>
	

   <div id="screenWidth" class="container"  style="background:#FFFFFF url(${ctx}/static/bdcor/images/background/tiny_grid.png) repeat center top fixed">
       
        
  
        
        
    
    </div> 
    
</body>
<script type="text/javascript">


</script>
</html> 
