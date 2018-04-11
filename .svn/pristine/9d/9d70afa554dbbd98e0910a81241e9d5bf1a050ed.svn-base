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
<%@ include file="/WEB-INF/common/custom-js-style.jsp"%>
<%-- <link rel="shortcut icon" type="image/x-icon" href="${ctx}/static/bdcor/images/airchina.ico" /> --%>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
 <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.1.1/css/bootstrap.css" /> 
 <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/switch/css/bootstrap-switch.css" />
 
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
	
 	$(document).ready(function(){
		$(".leaveCheck").each(function(){
			$(this).attr("init-value",$(this).val());
		});
	});
 	
 	function valIsChange(obj){
 		if($(obj).attr("init-value")== undefined || $(obj).val() == $(obj).attr("init-value"))return false;
 		return true;
 	}
 	
 	window.onbeforeunload = function(){
 		var canLeave = true;
 		$(".leaveCheck").each(function(){
			if(valIsChange($(this)[0])){
				canLeave = false;
				return false;
			}
		});
 		if(canLeave)return;
 		return "页面数据未保存，确认离开吗?";
 	}
</script>

<sitemesh:head />    
</head>
<body >
	<div id="wrap" style="background:#FFFFFF;">
		<div style="background:#fff;height:2px">&nbsp;</div>
		<div id="header">
				<figure class="header-logo">
					<a href="${ctx}/login"> <img src="${ctx}/static/bootstrap/3.1.1/images/logo2.png" width="761" height="107"  /></a>
				</figure>
				<figure class="header-img">
				<br>
				<a href="${ctx}/login">	<img src="${ctx}/static/bootstrap/3.1.1/images/head-image-2.png" width="328" height="107" /></a>
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
