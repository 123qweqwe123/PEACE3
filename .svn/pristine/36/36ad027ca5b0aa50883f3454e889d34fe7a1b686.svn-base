<%--获取缓存 组装成select标签--%>
<%@tag pageEncoding="UTF-8" description="系统参组装缓存" %>
<%@ tag import="com.bdcor.pip.web.common.domain.PipSysParameter" %>
<%@ tag import="com.bdcor.pip.web.common.utils.CacheUtils" %>
<%@ tag import="java.util.List" %>
<%@ attribute name="type" type="java.lang.String" description="cache-type" required="true" %>
<%@ attribute name="name" type="java.lang.String" description="name" %>
<%@ attribute name="value" type="java.lang.String" description="value" %>
<%@ attribute name="style" type="java.lang.String" description="style" %>
<%@ attribute name="cls" type="java.lang.String" description="class" %>
<%@ attribute name="onchange" type="java.lang.String" description="onchange" %>
<%@ attribute name="extAttr" type="java.lang.String" description="onchange" %>
<%@ attribute name="def" type="java.lang.Boolean" description="添加默认选项" %>


<select class="form-control" name="<%=name%>" style="<%=style%>" class="<%=cls%>" onchange="<%=onchange%>" <%=extAttr%>>
    <option value="">-请选择-</option>
    <%
        List<PipSysParameter> parametersByType = CacheUtils.getCacheValue(CacheUtils.CACHE_SYS_PARAMETER, type);
        for (PipSysParameter para : parametersByType) {
    %>
    <option value="<%=para.getCode()%>"
            <%
                if (para.getCode().equals(value)) {%>
            selected
            <% }
            %>
    ><%=para.getValue()%>
    </option>
    <%}%>
</select>
