<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<style type="text/css">
	.row{
		margin-top:8px;
	}
	</style>
	<div class="row">
		<label class="col-md-3  control-label" for="name">上报人员:</label>
		<div class="col-md-9" id="reportInfo">
			${reportInfoMap.reportBy}
		</div>
	</div>
    <div class="row">
		<label class="col-md-3  control-label" for="name">上报时间:</label>
		<div class="col-md-9" id="reportInfo">
			${reportInfoMap.reportDate}
		</div>
	</div>
	<div class="row">
		<label class="col-md-3  control-label" for="name">上报说明:</label>
		<div class="col-md-9">
			<div id="reportInfo">${reportInfoMap.reportInfo}</div>
		</div>
	</div>
	
	<div class="row">
		<label class="col-md-3  control-label" for="name">支持性文件1:</label>
		<div class="col-md-9">
			<span id="fileName1">
				<c:if test="${not empty  reportInfoMap.fileName1}">
					<a href="${ctx}/var/put/${reportInfoMap.sFile1}" target="new">${reportInfoMap.fileName1}</a>
				</c:if>
			</span>
		</div>
	</div>
	
	<div class="row">
		<label class="col-md-3  control-label" for="name">支持性文件2:</label>
		<div class="col-md-9">
			<span id="fileName2">
				<c:if test="${not empty  reportInfoMap.fileName2}">
					<a href="${ctx}/var/put/${reportInfoMap.sFile2}" target="new">${reportInfoMap.fileName2}</a>
				</c:if>
			</span>
		</div>
	</div>
	
	<div class="row">
		<label class="col-md-3  control-label" for="name">支持性文件3:</label>
		<div class="col-md-9">
			<span id="fileName3">
				<c:if test="${not empty  reportInfoMap.fileName3}">	
					<a href="${ctx}/var/put/${reportInfoMap.sFile3}" target="new">${reportInfoMap.fileName3}</a>
				</c:if>
			</span>
		</div>
	</div>
	
	<div class="row">
		<label class="col-md-3  control-label" for="name">支持性文件4:</label>
		<div class="col-md-9">
			<span id="fileName4">
				<c:if test="${not empty  reportInfoMap.fileName4}">	
					<a href="${ctx}/var/put/${reportInfoMap.sFile4}" target="new">${reportInfoMap.fileName4}</a>
				</c:if>
			</span>
		</div>
	</div>
	
	<div class="row">
		<label class="col-md-3  control-label" for="name">支持性文件5:</label>
		<div class="col-md-9">
			<span id="fileName5">
				<c:if test="${not empty  reportInfoMap.fileName5}">		
					<a href="${ctx}/var/put/${reportInfoMap.sFile5}" target="new">${reportInfoMap.fileName5}</a>
				</c:if>
			</span>
		</div>
	</div>
	
