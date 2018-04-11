<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
	<style type="text/css">
		.row{
			margin-top:6px;
		}
	</style>
		<div id="alertForUpload" class="alert alert-danger" hidden>
			<strong>Warning!</strong>
		</div>
		<form id="subForm"  enctype="multipart/form-data" method="post">
	      	<input type="hidden" name="todoId" value="${todoId}"/>
	      	<input type="hidden" name="reportId" value="${reportInfoMap.id}"/>
			<div class="row">
				<label class="col-md-3  control-label" for="name"><p style="color: red;float:left">*</p>上报说明:</label>
				<div class="col-md-9">
					<textarea rows="8" cols="50" style="overflow-y:auto;resize:none" name="reportInfo" id="reportInfo">${reportInfoMap.reportInfo}</textarea>
				</div>
			</div>
			
			<div class="row">
				<label class="col-md-3  control-label" for="name">支持性文件1:</label>
				<div class="col-md-9">
					<div hidden><input type="file" name="file" onchange="changeFile(1);" id="file1" accept="image/*"></div>
					<button type="button" style="height:25px;" class="btn btn-primary btn-align-right btn-sm" onclick="selectFile('1')">请选择文件</button>
					<span id="fileName1">
						<c:if test="${not empty  reportInfoMap.fileName1}">
							<a href="${ctx}/var/put/${reportInfoMap.sFile1}" target="new">${reportInfoMap.fileName1}</a>
							&nbsp;<a href="#" onclick="deleteFile(1)" style="color:red">X</a>
						</c:if>
					</span>
				</div>
			</div>
			
			<div class="row">
				<label class="col-md-3  control-label" for="name">支持性文件2:</label>
				<div class="col-md-9">
					<div hidden><input type="file" name="file" onchange="changeFile(2);" id="file2" accept="image/*"></div>
					<button type="button" style="height:25px;" class="btn btn-primary btn-align-right btn-sm" onclick="selectFile('2')">请选择文件</button>
					<span id="fileName2">
						<c:if test="${not empty  reportInfoMap.fileName2}">
							<a href="${ctx}/var/put/${reportInfoMap.sFile2}" target="new">${reportInfoMap.fileName2}</a>
							&nbsp;<a href="#" onclick="deleteFile(2)" style="color:red">X</a>
						</c:if>
					</span>
				</div>
			</div>
			
			<div class="row">
				<label class="col-md-3  control-label" for="name">支持性文件3:</label>
				<div class="col-md-9">
					<div hidden><input type="file" name="file" onchange="changeFile(3);" id="file3" accept="image/*"></div>
					<button type="button" style="height:25px;" class="btn btn-primary btn-align-right btn-sm" onclick="selectFile('3')">请选择文件</button>
					<span id="fileName3">
						<c:if test="${not empty  reportInfoMap.fileName3}">	
							<a href="${ctx}/var/put/${reportInfoMap.sFile3}" target="new">${reportInfoMap.fileName3}</a>
							&nbsp;<a href="#" onclick="deleteFile(3)" style="color:red">X</a>
						</c:if>
					</span>
				</div>
			</div>
			
			<div class="row">
				<label class="col-md-3  control-label" for="name">支持性文件4:</label>
				<div class="col-md-9">
					<div hidden><input type="file" name="file" onchange="changeFile(4);" id="file4" accept="image/*"></div>
					<button type="button" style="height:25px;" class="btn btn-primary btn-align-right btn-sm" onclick="selectFile('4')">请选择文件</button>
					<span id="fileName4">
						<c:if test="${not empty  reportInfoMap.fileName4}">	
							<a href="${ctx}/var/put/${reportInfoMap.sFile4}" target="new">${reportInfoMap.fileName4}</a>
							&nbsp;<a href="#" onclick="deleteFile(4)" style="color:red">X</a>
						</c:if>
					</span>
				</div>
			</div>
			
			<div class="row">
				<label class="col-md-3  control-label" for="name">支持性文件5:</label>
				<div class="col-md-9">
					<div hidden><input type="file" name="file" onchange="changeFile(5);" id="file5" accept="image/*"></div>
					<button type="button" style="height:25px;" class="btn btn-primary btn-align-right btn-sm" onclick="selectFile('5')">请选择文件</button>
					<span id="fileName5">
						<c:if test="${not empty  reportInfoMap.fileName5}">		
							<a href="${ctx}/var/put/${reportInfoMap.sFile5}" target="new">${reportInfoMap.fileName5}</a>
							&nbsp;<a href="#" onclick="deleteFile(5)" style="color:red">X</a>
						</c:if>
					</span>
				</div>
			</div>
			<input type="hidden" id="fileLocations" name="fileLocations"/>
			<input type="hidden" id="changeIds" name="changeIds"/>
		</form>
		
	<script type="text/javascript">
		function selectFile(i){
			$("#file"+i).click();
		}
		var changeIds="";		
		function changeFile(i){
			var fullName = $("#file"+i).val();
			$("#fileName" + i).html(fullName.substr(fullName.lastIndexOf("\\") + 1)+"&nbsp;<a href=\"#\" onclick=\"deleteFile("+i+")\" style=\"color:red\">X</a>");
		}
		
		function deleteFile(i){
			if(changeIds.indexOf(i)==-1){
				changeIds += i;
			}
			$("#fileName" + i).html("");
			$("#file"+i)[0].outerHTML=$("#file"+i)[0].outerHTML;
		}
	</script>
		
	
