<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<style>
	.ulC li{
		height: 30px;
		line-height: 30px;
	}
	.ulC_span{
		display:inline-block;
		width: 200px;
	}	
</style>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="hju" class="well-work bs-adp form-inline">
    	<input type="hidden" name="id"  value="${implementId }" id="implementId"/>
		<input type="hidden"  value="${lccCode }" id="lccCodeID"/>
		<ul class="ulC">
			<li>1、研究人员是否充分理解研究方案</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk1" <c:if test="${pipExpImplement.isztqk1 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk1" <c:if test="${pipExpImplement.isztqk1 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk1" <c:if test="${pipExpImplement.isztqk1 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>2、知情同意是否充分</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk2" <c:if test="${pipExpImplement.isztqk2 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk2" <c:if test="${pipExpImplement.isztqk2 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk2" <c:if test="${pipExpImplement.isztqk2 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>3、研究人员理解研究并掌握电脑使用方法</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk3" <c:if test="${pipExpImplement.isztqk3 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk3" <c:if test="${pipExpImplement.isztqk3 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk3" <c:if test="${pipExpImplement.isztqk3 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>4、LCC研究人员需要更多的培训</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk4" <c:if test="${pipExpImplement.isztqk4 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk4" <c:if test="${pipExpImplement.isztqk4 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk4" <c:if test="${pipExpImplement.isztqk4 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>5、受试者被安排预约随访</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk5" <c:if test="${pipExpImplement.isztqk5 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk5" <c:if test="${pipExpImplement.isztqk5 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk5" <c:if test="${pipExpImplement.isztqk5 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>6、研究材料及设备，包括电脑及文件，均存储在安全环境下</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk6" <c:if test="${pipExpImplement.isztqk6 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk6" <c:if test="${pipExpImplement.isztqk6 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk6" <c:if test="${pipExpImplement.isztqk6 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>7、LCC采血包是否充足，且采血包已被销毁</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk7" <c:if test="${pipExpImplement.isztqk7 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk7" <c:if test="${pipExpImplement.isztqk7 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk7" <c:if test="${pipExpImplement.isztqk7 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>8、中心校验血被安全存储于冰箱、冰柜中，定期记录冰箱、冰柜温度</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk8" <c:if test="${pipExpImplement.isztqk8 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk8" <c:if test="${pipExpImplement.isztqk8 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk8" <c:if test="${pipExpImplement.isztqk8 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>9、了解终点事件支持性文件的要求</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk9" <c:if test="${pipExpImplement.isztqk9 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk9" <c:if test="${pipExpImplement.isztqk9 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk9" <c:if test="${pipExpImplement.isztqk9 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>10、掌握如何正确记录不良事件</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk10" <c:if test="${pipExpImplement.isztqk10 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk10" <c:if test="${pipExpImplement.isztqk10 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk10" <c:if test="${pipExpImplement.isztqk10 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		<ul class="ulC">
			<li>11、研究经费按规定支出</li>
			<li>
				<span class="ulC_span"><input type="radio" name="isztqk11" <c:if test="${pipExpImplement.isztqk11 =='1' }">checked="checked" </c:if> value='1'>是</span>
				<span class="ulC_span"><input type="radio" name="isztqk11" <c:if test="${pipExpImplement.isztqk11 =='2' }">checked="checked" </c:if> value='2'>否</span>
				<span class="ulC_span"><input type="radio" name="isztqk11" <c:if test="${pipExpImplement.isztqk11 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
			</li>
		</ul>
		
	</form>
<script type="text/javascript">

</script>