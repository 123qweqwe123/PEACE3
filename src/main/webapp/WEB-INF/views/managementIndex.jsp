<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>管理端首页</title>
	<script type="text/javascript">
		function showL3Div(id){
			$(".l3Div").hide();
		   	$("#l3Div_"+id).show();
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
		                        var parentId = item['PARENT'] ? item['PARENT'] : item['parent'];
		                        mu_name = item['TEXT'] ? item['TEXT'] : item['text'];
		                        mu_id = item['ID'] ? item['ID'] : item['id'];
		                        mu_target = item['TARGET'] ? item['TARGET'] : item['target'];

		                        //如果是模块    则显示到横排菜单中 
		                        if (parentId == 1) {
		                            this.data = new Object();
		                            var li = $(" <li></li>");
									var div= "<div class='p_qtitle'>"+mu_name+"</div>";
									var dl="<dl id='menu_"+mu_id+"'></dl>"
									li.append($(div));
									li.append($(dl));
									menu.append(li);
									return true;
		                        }
		                        
		                        //将二级菜单和三级菜单放到数组中
		                        menu_it = new Object();
		                        menu_it["pid"] = parentId;
		                        menu_it["name"] = mu_name;
		                        menu_it["id"] = mu_id;
		                        menu_it["target"] = mu_target;
		                       
		                        downItem.push(menu_it);

		                    });
						
		            	//加入二级菜单
			            $.each(downItem,function(idx, item) {
			                    var pid = item.pid;
			                    findStr = "#menu_" + pid;
			                    var e_li = "";
			                    if(item.target==null || item.target==""){
			                    	e_li = $("<dd  id='l2menu" + item.id +  "' ><a href='javascript:{}' onclick=\"javascript:showL3Div(\'"+item.id+"\')\">"
					                        + item.name
					                        + "</a>"
				                    		+ "<div id='l3Div_"+item.id+"' class='l3Div'></div></dd>");
			                    }else{
			                    	e_li = $("<dd  id='l2menu" + item.id +  "' ><a href='javascript:{}' onclick=\"javascript:goTo(\'${ctx}"+item.target+"\')\">"
					                        + item.name
					                        + "</a></dd>");
			                    }
			                    	
			                    menu.find(findStr).append(e_li);
			            });
		            	
		            	//加入三级菜单
		            	$.each(downItem,function(idx, item) {
			                    var pid = item.pid;
			                    findStr = "#l3Div_" + pid;
			                    var div_a = $("<a href='javascript:{}' onclick='goTo(\"${ctx}"
			                            + item.target
			                            + "\");'>"
			                            + item.name
			                            + "</a><br/>");
			                    menu.find(findStr).append(div_a);
			            });
		            	
		            	$(".l3Div").each(function(){
		            		$(this).css("left",parseInt($(this).parent().find("a").css("width"))+22+'px');
		            		$(this).css("width","200px");
		            		$(this).css("width",getMaxWidth($(this)[0]));
		            	});
			    });
		    
		  //  $(".dropdown-toggle").removeAttr("style");
		    $(".dropdown-toggle").attr({"style":""}); 
		        
		}).click (function (e){
	        e = e || window.event;
	        if(e.target != $ ('.l3Div:visible')[0] && e.target != "javascript:{}"){
	            $ ('.l3Div').hide ();
	        }
	    });
		
		function getMaxWidth(obj){
			var returnWidth = 0;
			var mul = 16;
			$(obj).find("a").each(function(){
				var thisWidth = $(this).text().length;
				if(thisWidth > returnWidth)returnWidth=thisWidth;
			});
			return returnWidth*mul + 8 + "px";
		}
	</script>
	<style type="text/css">
		.p_qtitle{
			background-color: white;
			font-family:"黑体" !important;
			color: red;
		}
		.managementIndex_main{
			font-family:"黑体";
		}
		.managementIndex_main ul li dl dd a{
			font-family:"黑体";
		}
	</style>
</head>
<body>
	<ul class="breadcrumb" id="breadcrumb">
		<li class="active" id="topTitle">首页</li>
		<li id="noContent1" class="dropdown" style="float: right;">
			<a href="#" class="dropdown-toggle" data-toggle="dropdown" style="background-color:#00036a;">
			    <i class="glyphicon glyphicon-user"></i>
			    <shiro:principal property="name" /> 
			    <b class="caret"></b>
			</a>
			<ul class="dropdown-menu" style="margin:15px 0px;min-width:100px;">
			   <shiro:hasPermission name="basic:changepassword">
			   <li><a href="javascript:{}"  onclick="modifyPWD('${ctx}/sys/rbac/user/openmodalupdatepassword');">修改密码</a></li>
			   </shiro:hasPermission>
			   <li><a href="javascript:{}" onclick="goTo('${ctx}/logout');">退出系统</a></li>
			    </ul>
		</li>
	</ul>
	<div class="managementIndex_main">
		<ul id="dynamicMenu">
		</ul>
	</div>
</body>