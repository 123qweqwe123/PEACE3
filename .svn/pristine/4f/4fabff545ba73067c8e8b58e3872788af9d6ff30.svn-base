<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	 <script type="text/javascript" src="${ctx}/static/assets/js/BreakingNews.js"></script>
</head>
<body>
	<!-- <div id="home-page-news">
    	<div class="BreakingNewsController easing" id="breakingnews2">
        	<div class="bn-title"></div>
            <ul>
                <li><a href="#">1. 国家心血管病中心的临床试验项目平台上线了。</a></li>
                <li><a href="#">2. CHINAPEACE3项目正式启动。</a></li>
                
            </ul>
            <div class="bn-arrows"><span class="bn-arrows-left"></span><span class="bn-arrows-right"></span></div>	
            <br style="clear: both;"/>
        </div>
    </div> -->
    <script>
	$(document).ready(function(){
		$("#breakingnews1").BreakingNews();
		
		$("#breakingnews2").BreakingNews({
			background		:'#f2f2f2',
			title			:'最新消息',
			titlecolor		:'#FFF',
			titlebgcolor	:'#137ab6',
			linkcolor		:'#666',
			linkhovercolor	:'#404040',
			fonttextsize	:14,
			isbold			:false,
			border			:'none',
			width			:'100%',
			timer			:2000,
			autoplay		:true,
			effect			:'slide'
				
		});
		
		$('#apply').click(function(e) {
           addValues(); 
        });
		addValues();
  	    });	
	
		function addValues()
		{
			
			var params='Add : jQuery.js\n'+
						'Add : BreakingNews.js\n'+
						'Add : BreakingNews.css\n\n\n'+
						'$("#breakingnews").BreakingNews({\n\n'+
						'	background		:"'+$("#background").val()+'",\n'+
						'	title			:"'+$("#title").val()+'",\n'+
						'	titlecolor		:"'+$("#titlecolor").val()+'",\n'+
						'	titlebgcolor		:"'+$("#titlebgcolor").val()+'",\n'+
						'	linkcolor		:"'+$("#linkcolor").val()+'",\n'+
						'	linkhovercolor		:"'+$("#linkhovercolor").val()+'",\n'+
						'	fonttextsize		:'+parseInt($("#fonttextsize").val())+',\n'+
						'	isbold			:'+($("#isbold").val() == "true" ? true : false)+',\n'+
						'	border			:"'+$("#border").val()+'",\n'+
						'	width			:"'+$("#width").val()+"%"+'",\n'+
						'	timer			:'+parseInt($("#timer").val())+',\n'+
						'	autoplay		:'+($("#autoplay").val() == "true" ? true : false)+',\n'+
						'	effect			:"'+$("#effect").val()+'",\n\n'+
					'});\n';
				
			$('.content pre code').html(params);
		}
			
		</script>
      <!--end news-->
    <!--end navbar-->
  	<div id="main-content">
    	<!-- <h3 class="flowchat">工作流程</h3> --><!-- flowcharImg.png -->
        <div class="flowchatImg">
        	<figure><img src="${ctx}/static/bootstrap/3.1.1/images/funDiagram.png" title="工作流程图" width="1099"/></figure>
        </div>
        <br style="clear:both;" />
    </div>
</body>
</html>