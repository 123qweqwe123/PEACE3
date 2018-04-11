<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sitemesh"
    uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>bdcor-<sitemesh:title /></title>  
<%@ include file="/WEB-INF/common/common-js-style.jsp"%>
<%@ include file="/WEB-INF/common/custom-js-style.jsp"%>
<%@ include file="/WEB-INF/common/backtop.jsp"%>
<%@ include file="/WEB-INF/common/less-common-js-style.jsp"%>
<%@ include file="/WEB-INF/common/tree-js-style.jsp"%>
<%@ include file="/WEB-INF/common/table-js-style.jsp"%>  

<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/bdcor/images/airchina.ico" /> --%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
  
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
    
</script>

  <sitemesh:head />    
</head>
<body >
    <div id="screenWidth" class="container"  style="background:#FFFFFF url(${ctx}/static/bdcor/images/background/tiny_grid.png) repeat center top fixed">
       <div id="fixTopDiv" class="bs-navbar-fixed-top" >
        <div class="topPic topPicportal"  style="background:#FFFFFF url(${ctx}/static/bdcor/images/nj_top.jpg) no-repeat center top fixed"></div> 
        <div class="navbar navbar-default"  >  
                    <!-- .navbar-toggle is used as the toggle for collapsed navbar content -->
                    <div class="navbar-header">
	                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
	                      <span class="icon-bar"></span>
	                      <span class="icon-bar"></span>
	                      <span class="icon-bar"></span>
	                    </button>  
                    </div>
                    <!-- Place everything within .navbar-collapse to hide it until above 768px -->
                   <div class="collapse navbar-collapse navbar-ex1-collapse"  style='position:static;font-weight:bold'>
                        <div id="dynamicMenu"  >    </div>
                         <ul class="nav navbar-nav pull-right">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="glyphicon glyphicon-user"></i>
                                    <shiro:principal property="name" /> 
                                    <b class="caret"></b>
                                </a>
                                <ul class="dropdown-menu">
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
                        
                        
                  </div>
          </div> 
        </div>
        
  <div id="clearFixTop" class="row" >  
       <sitemesh:body />        
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
    
</body>
<script type="text/javascript">

$(document).ready(function(){
$.getJSON('<c:url value="/sys/rbac/menu/menuTree" />' ,{param:"sanic"},function(data){ 
    
	  var bars     = new Array();
	  var downItem = new Array();
	  var menu     = $("#dynamicMenu");
   
    //此处返回的data已经是json对象   循环 记录 构建 二级菜单
    //以下其他操作同第一种情况 
    $.each(data,function(idx,item){ 
   
	     var i = 0;
	     var parentId =  item['PARENT']?item['PARENT']:item['parent'];
	     mu_name      =  item['TEXT']?item['TEXT']:item['text'];
	     mu_id        =  item['ID']?item['ID']:item['id'];
	     mu_target    =  item['TARGET']?item['TARGET']:item['target'];
	    
	     alert( mu_name   +"===="+ parentId)
	     
        //如果是模块    则显示到横排菜单中 
	    if(parentId==1){
	    	i++;
	    	//arr[i]='父菜单'+item['ID'] ;
	   
	    	this.data = new Object();   
	    	
	    	var ul   =  $("<ul class='nav navbar-nav'></ul>");
	    	var li   =  $(" <li class='dropdown'></li>");
	    	
	    	var hrefStr =  "<a href='#' class='dropdown-toggle' data-toggle='dropdown'>"+mu_name+"<b class='caret'></b></a>";
	    	var href    =  $(hrefStr);
	    	
	    	var subulstr = "<ul class='dropdown-menu' style='z-index:1000' id='menu_"+ mu_id +"'></ul>";
	    	var ul_sub  =  $(subulstr); 
	    	
	    	ul.append(li.append(href).append(href).append(ul_sub));
	    	 
	    	menu.append(ul);
	    	return true;//同countinue，返回false同break 
	    }
	    menu_it = new Object();   
        var menuItem = "<li id='menu_item"  + mu_id +  "' ><a href='javascript:{}' onclick='goTo(\"${ctx}"+mu_target +  "\");'>"  + mu_name +   "</a></li>";
	    
        menu_it["li"]    =  menuItem;
        menu_it["pid"]   =  parentId;   
        
        downItem.push(menu_it);
	    
       }); 
    
	   $.each(downItem,function(idx,item){ 
		   var pid = item.pid;
		   findStr = "#menu_"+pid;
		   var e_li = $(item.li);
		   menu.find(findStr).append(e_li);
		   
	    });    
	  }); 
	  
	  
	  
	  
});


 </script>
</html> 
