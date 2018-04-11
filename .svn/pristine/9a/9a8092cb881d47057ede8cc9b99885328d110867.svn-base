<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="rp" tagdir="/WEB-INF/tags" %>

<div>
	<table width="100%">
		<c:if test="${code=='A0101' || code == 'A0102' || code == 'A0103' || code == 'A0104' || code == 'A0105' || code == 'A0106' || code == 'A0107'}">
			<tr>
				<td style="border:0"><font color="red">*</font>心肌生物标志物</td>
			</tr>

			<tr id="">
				<td style="border:0"><span>是否有心肌生物标志物:</span>
					<rp:select type="T005" style="width:150px;display:inline;" name="xjbzw" extAttr="required" onchange="changexjbzw(this)" value="${er.xjbzw}"/>
				</td>
			</tr>
			<tr id="xj2">
				<td style="border:0">心肌标志物类型:
					<rp:select type="T006" style="width:150px;display:inline;" name="xjbzwtype" extAttr="required" value="${er.xjbzwtype}"/>
				</td>
			</tr>
			<tr id="xj3">
				<td style="border:0">峰值检测结果:
					<input class="form-control" style="width: 150px;display: inline;"  name="fzresult"  type="text" value="${er.fzresult}" required/>
					<rp:select type="T007" style="width:100px;display:inline;" name="fzunit" extAttr="required" value="${er.fzunit}"/>
				</td>
			</tr>
			<tr id="xj4">
				<td style="border:0">检测值参考上限:
					<input class="form-control" style="width: 150px;display: inline;" type="text" name="fzuplimit" value="${er.fzuplimit}" required/>
					<rp:select type="T007" style="width:100px;display:inline;" name="fzuplimitunit" extAttr="required" value="${er.fzuplimitunit}"/>
				</td>
			</tr>
			<tr>
				<td style="border:0"><font color="red">*</font>是否有心电图:
					<rp:select type="T005" style="width:150px;display:inline;" name="isxdt" extAttr="required" onchange="changexdtgb(this)" value="${er.isxdt}"/>
			</tr>
			<tr id="xdtgb1">
				<td style="border:0"><font color="red">*</font>心电图改变:
					<rp:select type="T008" style="width:150px;display:inline;" name="xdtverity" extAttr="required" value="${er.xdtverity}"/>
				</td>
			</tr>
			<tr>
				<td style="border:0"><font color="red">*</font>Killip分级:
					<rp:select type="T009" style="width:150px;display:inline;" name="killiprank" extAttr="required" value="${er.killiprank}"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${code=='A0401' || code=='A0402' ||code=='A0403' ||code=='A0404'}">
			<tr>
				<td style="border:0"><font color="red">*</font>冠脉血管重建分类:
					<rp:select type="T010" style="width:150px;display:inline;" name="gzxgcj" extAttr="required" value="${er.gzxgcj}"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${code=='A0701'||code=='A0702'||code=='A0703'||code=='A0705'||code=='A0704'}">
			<tr>
				<td style="border:0"><font color="red">*</font>致残程度:
					<rp:select type="T011" style="width:150px;display:inline;" name="zccd" extAttr="required" value="${er.zccd}"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${code=='A0701'||code=='A0704'}">
			<tr>
				<td style="border:0"><font color="red">*</font>梗死部位:
					<rp:select type="T012" style="width:150px;display:inline;" name="gsbw" extAttr="required" value="${er.gsbw}"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${code=='A1101' || code=='A1102' || code=='A1103'}">
			<tr>
				<td style="border:0"><font color="red">*</font>NYHA分级:
					<rp:select type="T013" style="width:150px;display:inline;" name="nyhatype" extAttr="required" value="${er.nyhatype}"/>
				</td>
			</tr>
		</c:if>
		<c:if test="${code=='A12'}">
			<tr>
				<td style="border:0"><font color="red">*</font>China PEACE出血分类:
					<rp:select type="T014" style="width:150px;display:inline;" name="cxtype" extAttr="required" value="${er.cxtype}"/>
				</td>
			</tr>
			<tr>
				<td style="border:0"><font color="red">*</font>出血部位:
					<rp:select type="T015" style="width:150px;display:inline;" name="cxplace" extAttr="required" value="${er.cxplace}"/>
				</td>
			</tr>
		</c:if>
	</table>
</div>