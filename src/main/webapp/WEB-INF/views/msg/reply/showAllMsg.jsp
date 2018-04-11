<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style>
	.msgTable{ 
        border: 1px solid #b0b0b0;  
        width: 100%;  
        border-collapse:collapse;  
    }  
    
    .msgTable th,.msgTable td{
    	text-align:center;
        border: 1px solid #b0b0b0;
        vertical-align:middle;
    }  
</style>
		<div class="row">
				<div class="col-md-2"><label>姓名:</label>${patientVo.PATIENT_NAME}</div>
				<div class="col-md-2"><label>性别:</label>${patientVo.SEX==1?'男':'女'}</div>
				<div class="col-md-8"><label>身份证号:</label>${patientVo.ID_NUMBER}</div>
		</div>
		<div class="row" style="margin-top: 5px;">
				<div class="col-md-4"><label>手机号:</label>${patientVo.MOBILE}</div>
				<div class="col-md-8">
						<label>所属组别:</label>${patientVo.BELONG_GROUP=='01'?
						'非糖尿病实验组':patientVo.BELONG_GROUP=='02'?
						'非糖尿病对照组':patientVo.BELONG_GROUP == '11' ?
						'糖尿病实验组':patientVo.BELONG_GROUP == '12'? '糖尿病对照组': '' }
				</div>
		</div>

<table class="msgTable">
<tr>
	<th style="width:15%">类别</th>
	<th style="width:30%">时间</th>
	<th style="width:53%">内容</th>
</tr>
<c:forEach items="${msgList}" var="m">
	<tr>
		<td>${m.TYPE==1?'发送':'回复'}</td>
		<td><fmt:formatDate value='${m.STIME}' pattern='yyyy-MM-dd HH:mm:ss' /></td>
		<td style="text-align:left;">${m.MSG_NAME}</td>
	</tr>
</c:forEach>
</table>