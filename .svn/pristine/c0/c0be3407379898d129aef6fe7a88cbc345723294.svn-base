<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@page import="com.bdcor.pip.data.util.PoiUtil"%>
<%@page import="java.util.*"%>
<%@page import="com.bdcor.pip.web.data.domain.PipSysNews"%>
<%
	List<PipSysNews> list = PoiUtil.queryNews("1");

%>
<div class="portal_latestnews">
				<h2>最新新闻</h2>
				<ul>
					<% if ( list != null && list.size() > 0 ){
						for ( PipSysNews news : list ) { %>
					<li><a class="divout" href="newsdetail.jsp?id=<%=news.getId() %>"><%=news.getTitle() %></a></li>
					<% }
					}%>
				</ul>			
			</div>