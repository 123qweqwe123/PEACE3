<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
	#div1_left{float:left;width:120px;height:140px;}
	#div1_right{height:140px;margin-left:10px;}
	#qnTable td{border: solid #000 1px;}
</style>
<h3>基本信息</h3>
<hr/>
<div id="div1">
	<div id="div1_left">
		<img src="" width="120px;" height="150px">
	</div>
	<div id="div1_right">
		<table>
			<tr style="height:25px;">
				<td align="right"  width="100px;">PID:&nbsp;</td> <td width="200px;">${pq.pId}</td>
			</tr>
			
			<tr style="height:25px;">
				<td align="right">LCCID:&nbsp;</td>	<td>${pq.lccId}</td>
				<td align="right">医院名称:&nbsp;</td> <td>${pq.lccName}</td> 
			</tr>
			
			<tr style="height:25px;">
				<td align="right">姓名:&nbsp;</td>	<td>${pq.patientName}</td> 		
				<td align="right">性别:&nbsp;</td>	
					<td>
						<c:if test="${pq.sex=='1'}">男</c:if>
						<c:if test="${pq.sex=='2'}">女</c:if>
					</td>
			</tr> 
			
			<tr style="height:25px;">
				<td align="right">出生日期:&nbsp;</td>	<td>${pq.birthday}</td> 		
				<td align="right">身份证号:&nbsp;</td>	<td>${pq.idNumber}</td>
			</tr>
			
			<tr style="height:25px;">
				<td align="right">家庭电话:&nbsp;</td> <td>${pq.phone}</td> 		
				<td align="right">手机:&nbsp;</td> <td>${pq.mobile}</td>
			</tr> 
			
			<tr style="height:20px;"><td align="right">现住地址:&nbsp;</td> <td colspan="3">${pq.address}</td></tr>
		</table>
	</div>
</div>
<br/>
<h3>业务数据</h3>
<hr/>
		本人事件:<button type="button" onclick="showEvent('${pq.pId}')"  class="btn btn-primary btn-align-right btn-sm">查看本人事件</button>
		<div style="height:20px;"></div>
		<table	id="qnTable">
			<tr style="background-color:#f2f2f2"><td width="250px;">问卷类型</td> <td width="200">开始时间</td> <td width="200px;">结束时间</td> <td width="80px;">&nbsp;</td></tr>
			<c:forEach items="${qnList}" var="infoMap">
				<tr>
					<td>${infoMap.PAPERNAME}</td>
					<td>${infoMap.STARTTIME}</td>
					<td>${infoMap.ENDTIME}</td>
					<td align="center">
						<button style="line-height:0.8" class="btn btn-primary btn-align-right btn-sm" onclick="showQnDetail('${infoMap.UQSVERSION}','${pq.pId}','${infoMap.ID}')">预览</button>
					</td>
				</tr>
				
			
			</c:forEach>
		</table>
		
		<form id="subForm" method="post" target="_blank">
			<input type="hidden" name="patientId" id="patientIdHidden"/>
			<input type="hidden" name="qnVersion" id="qnVersionHidden"/>
			<input type="hidden" name="logId" id="logIdHidden"/>
		</form>
<script type="text/javascript">
	function showQnDetail(qnVersion,patientId,logId){
		$("#patientIdHidden").val(patientId);
		$("#qnVersionHidden").val(qnVersion);
		$("#logIdHidden").val(logId);
		var form =$("#subForm")[0];
		form.action="${ctx}/quality/previewqn/showQnDetail";
		form.submit();
	}
	
	function showEvent(patientId){
		var timebak = new Date().getTime();
		$("#dialog-showQnInfo p" ).html("");
		$("#dialog-showQnInfo p" ).load("${ctx}/qn/eventMgt/openmodalShowEvent?patientId="+patientId+"&time="+timebak);
	}
</script>