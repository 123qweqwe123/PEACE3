<%--缓存--%>
<%@tag pageEncoding="UTF-8" description="缓存" %>
<%@ tag import="com.bdcor.pip.web.common.utils.CacheUtils" %>
<%@ attribute name="key" type="java.lang.Object" required="true" description="cache key" %>
<%@ attribute name="code" type="java.lang.String" required="false" description="cache value的属性名称" %>
<%=CacheUtils.getSysParameterCacheValueByTypeAndCode(key, code, null) != null ? CacheUtils.getSysParameterCacheValueByTypeAndCode(key, code, null) : ""%>
