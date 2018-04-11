<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page import="com.bdcor.pip.core.utils.Securitys"%>
<%
	String projectId = Securitys.getCurrentProject();
	String lcc = Securitys.getUser().getLccCode();
	if ( lcc == null )
		lcc = "1101";
	
	String lccName =  Securitys.getUser().getLccName();
	if (lccName.equals("获取当前单位失败!"))
		lccName = "系统管理员";
%>
<!DOCTYPE html>
<html>
<head>
 <title>项目点通知交互</title>
 <script type="text/javascript" src="${ctx}/static/echarts/esl/esl.js"></script>
 <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.1.1/css/bootstrap.css" /> 
 <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/switch/css/bootstrap-switch.css" />
 <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.1.1/css/bootstrap-combined.min.css" /> 
 <style type="text/css">
 		.breadcrumb1 {
		  padding: 8px 15px;
		  margin-bottom: 20px;
		  list-style: none;
		  background-color: #0080C0;
		  border-radius: 4px;
		  margin-left: 0em;
		}
		.breadcrumb1 > li {
		  display: inline-block;
		}
		.breadcrumb1 > li + li:before {
		  padding: 0 5px;
		  color: #E80000;
		  content: "/\00a0";
		}
		.breadcrumb1 > .active1 {
		  color: #E80000;
		  font-weight:bold;
		}
 </style>
</head>
<body>
	


	<div class="container-fluid">
	
		<ul class="breadcrumb1">
			<li class="active1">项目管理</li>
			<li class="active1">项目点通知交互</li>
		</ul>
		<div id="main-content">
	    	<div class="interactive">
	        	<h4>项目点通知交互</h4>
	            <div class="chatMain">
	            	<div class="chatLeft">
	                	<ul id="lccList" style="margin: 0 0 5px 5px;">
	                    	<!-- <li class="item"><a href="#">国家心血管病中心<span>0</span></a></li>
	                        <li class="item"><a href="#">辽宁省人民医院<span>0</span></a></li> 
	                        <li class="item"><a href="#">昌化人民医院<span>10</span></a> </li> 
	                        <li class="item"><a href="#">测试单位<span>10</span></a> </li> 
	                        <li class="item"><a href="#">测试测试测试协作试测 <span>99+</span> </a></li>
	                        <li class="item"><a href="#">浙江省人民医院<span>99+</span> </a></li> 
	                        <li class="item"><a href="#">测试单位测试单位<span>99+</span></a> </li>    -->   
	                    </ul>
	                </div>
	                 <div id="convo" data-from="Sonu Joshi">  
	                    <div id="lccMsgList" class="chat-thread">
	                      	<!-- <ul>
	                        	<li class="hospital">国家心血管病中心:</li>
	                            <li class="chatContent">您好您好您好您好您好您好您好您好您好<i></i></li>
	                        </ul>
	                        <br style="clear:both;" />
	                        <ul>
	                        	<li class="hospital">辽宁省人民医院:</li>
	                            <li class="chatContentEven">您好您好您好您好您好您好您好您好您好<i></i></li>
	                        </ul>
	                        <br style="clear:both;" />
	                         <ul>
	                        	<li class="hospital">辽宁省人民医院:</li>
	                            <li class="chatContentEven">您好您好您好您好您好您好您好您好您好<i></i></li>
	                        </ul>
	                        <br style="clear:both;" />
	                        <ul>
	                        	<li class="hospital">国家心血管病中心:</li>
	                            <li class="chatContent">您好您好您好您好您好您好您好您好您好<i></i></li>
	                        </ul>
	                        <ul>
	                        	<li class="hospital">国家心血管病中心:</li>
	                            <li class="chatContent">您好您好您好您好您好您好您好您好您好<i></i></li>
	                        </ul>
	                        <ul>
	                        	<li class="hospital">国家心血管病中心:</li>
	                            <li class="chatContent">您好您好您好您好您好您好您好您好您好<i></i></li>
	                        </ul>
	                        <ul>
	                        	<li class="hospital">国家心血管病中心:</li>
	                            <li class="chatContent">您好您好您好您好您好您好您好您好您好<i></i></li>
	                        </ul> -->
	                     
	                    </div>
	                    <div class="inputchat">
	                     <form id="sendFrom" name="sendFrom"  >
							<input id="sendProjectId" name="projectId" type="hidden" value="<%=projectId %>">
							<input id="sendFromLcc" name="toLcc" type="hidden">
							<input id="sendToLcc" name="fromLcc" type="hidden" value="<%=lcc %>">
	                        <fieldset>
	                            <ol>
	                                <li><input id="sendMsg" name="msg" type="text" style="height:30px;" value=""></li>
	                                <li> <button type="submit" class="btn-style-default">发送</button></li>
	                                <li> <button type="button" onclick="$('#txt_' + fromlccCode ).html('');" class="btn-style-default black">清屏</button></li>
	                            </ol>
	                        </fieldset>
	                    </form>
	                    </div>
	                 </div>
	                
	            </div>
	        </div>
	         
	        <br style="clear:both;" />
	    </div>
		<%-- <div class="row-fluid">
			
			<div class="span12">
				<div class="tabbable tabs-left" id="tabs-735840">
					<ul id="lccList" style="height:420px;overflow-y:auto;overflow-x:hidden;" class="nav nav-tabs">
						
					</ul>
					<form id="sendFrom" name="sendFrom"  >
					<input id="sendProjectId" name="projectId" type="hidden" value="<%=projectId %>">
					<input id="sendFromLcc" name="toLcc" type="hidden">
					<input id="sendToLcc" name="fromLcc" type="hidden" value="<%=lcc %>">
					<input id="sendMsg" name="msg" type="text" style="height:30px;" value="">
					<button type="submit" class="btn btn-primary btn-align-right btn-sm">发送</button>
					<button type="button" onclick="$('#txt_' + fromlccCode ).html('');" class="btn btn-primary btn-align-right btn-sm">清屏</button>
					
					</form>
					<div id="lccMsgList" class="tab-content">
						
					</div>
				</div>
			</div>
		</div> --%>
	</div>
  <script type="text/javascript">
  
  var fromlccCode;
  var lccMap = {};
  function clickLcc(lcc){
	  fromlccCode = lcc ;
	  $('#sendFromLcc').val(fromlccCode);
	  	$('.lccItem').removeClass('active');
	  	$('#a_' + lcc).addClass('active');
		$('.tab-pane').hide();
		$('#panelpanel_' + lcc ).show();
		flushMsgNum();
		flushMsg();
	}
  
  window.setInterval(flushMsgNum , 10000); 
  
  window.setInterval(flushMsg , 5000); 
  
  function flushMsgNum(){ 
	$.getJSON("${ctx}/websocket/getLccMsgNum?projectId=<%=projectId %>&toLccCode=<%=lcc %>&dd" + new Date() ,function(data) { 
	  	for ( var n in lccMap){
	  		$('#badge_' + n ).html('0');
	  	}
		if ( data && data.length > 0 ){
	  		for ( var i in data ){
		  		//alert(data[i]['LCC_CODE']);
		  		if ( parseInt( data[i]['MSGNUM'] ) > 99  ){
		  			$('#badge_' + data[i]['FROM_LCC_CODE']).html("99+");
		  		} else
		  			$('#badge_' + data[i]['FROM_LCC_CODE']).html(data[i]['MSGNUM']);
		  	}	
	  	}
	});
  }
  
  /* function send(){
	  if ( $('#sendMsg').val() == '' ){
		  return ;
	  }
	  
	  $("#sendFrom").ajaxSubmit({  
             type:"post",  //提交方式  
             url: "${ctx}/websocket/send" ,
             dataType: "json",
             success:function(data){ //提交成功的回调函数  
                 //alert(data.result);
            	 $('#sendMsg').val('');
             }  
         });  
	  $('#sendMsg').val('');
      return false; //不刷新页面  
      
  } */
  
  function flushMsg(){   
	  //alert('<%=lcc %>:' + fromlccCode);
	  $.getJSON("${ctx}/websocket/getLccMsg?projectId=<%=projectId %>&toLcc=<%=lcc %>&fromLcc=" + fromlccCode  ,function(data) { 
		  	if ( data && data.length > 0 ){
		  		//alert(data.length);
		  		for ( var i in data ){
		  			//if ( data[i]['fromLccCode'] == fromlccCode ){
		  				//$('#txt_' + fromlccCode ).append('<div>' + lccMap[data[i]['fromLccCode']] + '['+ data[i]['createDateTxt'] +']:' + data[i]['message']  + '</div>');
		  			$('#txt_' + fromlccCode ).append('<ul><li class="hospital">' + lccMap[data[i]['fromLccCode']] + ':</li><li class="chatContent">' + data[i]['message']  + '<i></i></li></ul><br style="clear:both;" />');
		  
		  				//}else{
		  			//	$('#txt_' + fromlccCode ).append('<div>' + lccMap[data[i]['toLccCode']] + '['+ data[i]['createDateTxt'] +']:' + data[i]['message']  + '</div>');	
		  			//}
		  		}
		  	}
		  	flushMsgNum();
		});
  }
  
  function clear(){ alert(fromlccCode);
	  
  }
  
  $(function(){
	  
	  $("#sendFrom").submit(function() {  
		  if ( $('#sendMsg').val() == '' ){
			  return false;
		  }
		  $(this).ajaxSubmit({  
	          type:"post",  //提交方式  
	          url: "${ctx}/websocket/send" ,
	          dataType: "json",
	          success:function(data){ //提交成功的回调函数  
	             //alert(data.result);
	         	 
	          }  
	      });
		  /* <ul>
	      	<li class="hospital">辽宁省人民医院:</li>
	          <li class="chatContentEven">您好您好您好您好您好您好您好您好您好<i></i></li>
	      </ul>
	      <br style="clear:both;" /> */
		  
		  $('#txt_' + fromlccCode ).append('<ul><li class="hospital"><%=lccName %>:</li><li class="chatContentEven">' + $('#sendMsg').val()  + '<i></i></li></ul><br style="clear:both;" />');
		  $('#sendMsg').val('');
		  return false;
	  });
	  
	 
	$.getJSON("${ctx}/websocket/getLcc?projectId=<%=projectId %>&lccCode=<%=lcc %>" ,function(data) {
		
		if ( data && data.length > 0 ){ 
			for ( var i = 0 ; i < data.length ; i ++ ){
				lccMap[data[i]['lccCode']] = data[i]['lccName'];
				/*
				<li class="active">
					<a href="#panel-765158" data-toggle="tab">第一部分<span class="badge">1</span></a>
				</li> 
				*/
				var txt = '<li id="li_'+ data[i]['lccCode'] +'" class="">' ;
				if ( i == 0 ){
					txt = '<li id="li_'+ data[i]['lccCode'] +'" class="active">' ;
					fromlccCode = 	data[i]['lccCode'];
					$('#sendFromLcc').val(fromlccCode);
				}
				txt += '<a id="a_'+ data[i]['lccCode'] +'" class="lccItem" href="#" onclick="clickLcc(\''+ data[i]['lccCode'] +'\');" data-toggle="tab" style="overflow:hidden;"><span id="badge_'+ data[i]['lccCode'] +'" class="badge">0</span>' + data[i]['lccName'] + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a> </li>' ;
				
				$('#lccList').append(txt);
				/* 
				<div class="tab-pane active" id="panel-765158">
					<p>
						第一部分内容.
					</p>
				</div> */
				txt = '<div class="tab-pane active" id="panelpanel_'+ data[i]['lccCode'] +'"><p id="txt_'+ data[i]['lccCode'] +'">'+ '' +'</p></div>';
				
				$('#lccMsgList').append(txt);
			}
			$('.tab-pane').hide();
			$('#panelpanel_' + fromlccCode ).show();
			flushMsgNum();
			flushMsg();
		}  
	 });
	
	
	
	  
	  
	  
	  /* var optionlcc = {
				url : '${ctx}/pro/lccmgt/list',
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '协作PID', '医院名称', '消息'],
				colModel : [
						{
							name : 'lccCode',
							index : 'lccCode',
							align : 'left'
						},{
							name : 'lccName',
							index : 'lccName',
							align : 'left'
						},
						{
							name : 'address',
							index : 'address',
							align : 'left'
						}],
				rowNum : 30,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'id',
				altRows : true,
				hidegrid : false,
				viewrecords : true,
				recordpos : 'left',
				sortorder : "desc",
				emptyrecords : "没有可显示记录",
				loadonce : false,
				//multiselect : true,
				loadComplete : function() {
				},
				jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell",
					id : "lccCode"
				}
			};
			$("#grid").jqGrid(optionlcc);
			//自适应
			jqgridResponsive("grid", false); */
	});
  
  </script>
  
<script type='text/javascript'>
    /* var webSocket = new WebSocket('ws://127.0.0.1:8777' );
    
   // alert(webSocket);
      
    webSocket.onerror = function(event) {
      onError(event);
    }; 
    
    webSocket.onclose = function(event) {
        alert(event);
    };
 
    webSocket.onopen = function(event) {
      onOpen(event);
    };
 
    webSocket.onmessage = function(event) {
      onMessage(event);
    };
 
    //webSocket.send('hello:' + document.getElementById("msg").value );
    
    
    function onMessage(event) {
      document.getElementById('messages').innerHTML
        += '<br />' + event.data;
    }
 
    function onOpen(event) {
      document.getElementById('messages').innerHTML
        = 'Connection established';
    }
 
    function onError(event) {
    	alert('error:'+event);
      for ( var i in event ){
    		alert(i + ' : '  + event[i]);  
      }
    }
 
    function start() {
    	var txt = 'hello ' + document.getElementById("msg").value;
    	try{
    		
    		document.getElementById('messages').innerHTML
            += '<br />' + webSocket.readyState  ;
    		webSocket.send( 'aa' );
    	     	
    	}catch(e){alert(e.message);}
    	
    } */
  </script>

</body>



</html>