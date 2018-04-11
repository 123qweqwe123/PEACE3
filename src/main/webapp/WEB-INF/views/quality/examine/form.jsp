<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="examine_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccCodeforexamine"><span style="color: red">*</span>医院名称:</label>
			<div class="col-lg-7 col-md-8">
				<select id="lccCodeforexamine" name="lccCode" class="form-control input-sm" onchange="changeLccCode()">
					<option value="-1">请选择</option>
					<c:forEach items="${lccList }" var="exaLcc">
						<option value="${exaLcc.lccCode }" ${examine.lccCode == exaLcc.lccCode ? 'selected="selected"' : '' }>${exaLcc.lccName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<input type="hidden" name="lccName" id="lccNameforexamine" value="${examine.lccName }"/>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineLccCode"><span style="color: red">*</span>考核员单位:</label>
			<div class="col-lg-7 col-md-8">
				<select id="examineLccCode" name="examineLccCode" class="form-control input-sm" onchange="setExamLccCode()">
					<option value="-1">请选择</option>
					<c:forEach items="${exaUserLccList }" var="exaUserLcc">
						<option value="${exaUserLcc.lccCode }" ${examine.examineLccCode == exaUserLcc.lccCode ? 'selected="selected"' : '' }>${exaUserLcc.lccName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<input type="hidden" name="examineLccName" id="examineLccName" value="${examine.examineLccName }"/>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineUserCode"><span style="color: red">*</span>现场考核员:</label>
			<div class="col-lg-7 col-md-8">
				<select id="examineUserCode" name="examineUserCode" class="form-control input-sm" onchange="setExamUserCode()" >
					<option value="-1">请选择</option>
					<c:forEach items="${exaLccUserList }" var="exaLccUser">
						<option value="${exaLccUser.userCode }" ${examine.examineUserCode == exaLccUser.userCode ? 'selected="selected"' : '' }>${exaLccUser.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<input type="hidden" name="examineUserName" id="examineUserNameForm" value="${examine.examineUserName }"/>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineDate">考核起始日期:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="examineDate" name="examineDate" value="${examine.examineDateStr }"  class="form-control input-sm" placeholder="考核起始日期">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineDate">考核结束日期:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="examineDate2" name="examineDate2" value="${examine.examineDate2Str }"  class="form-control input-sm" placeholder="考核结束日期">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="remark">考核备注:</label>
			<div class="col-lg-7 col-md-8">
				<textarea rows="3" id="remark" class="form-control" name="remark" placeholder="考核备注">${examine.remark}</textarea>
			</div>
		</div>
		<input id="id" name="id" value="${examine.id }" type="hidden" /> 
	</form>
	
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#examineDate').datepicker({
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#examineDate').datepicker('hide');
	});
	$('#examineDate2').datepicker({
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#examineDate2').datepicker('hide');
	});
	
	$("#examine_form").validate({
		rules: {
			lccCode:{
				min:0
			},
			examineLccCode:{
				min:0
			},
			examineUserCode:{
				min:0
			},
			spec:{
				maxlength:1000
			},
			remark:{
				maxlength:1000
			}
		},
		messages:{
			lccCode:{
				min:'请选择项目承担医院！'
			},
			examineLccCode:{
				min:'请选择考核员单位！'
			},
			examineUserCode:{
				min:'请选择现场考核员！'
			},
			spec:{
				maxlength:'最大长度不能超过1000个字符'
			},
			remark:{
				maxlength:'最大长度不能超过1000个字符'
			}
		}
	});
	
	
});
function changeLccCode(){
    var lccName = $("#lccCodeforexamine").find("option:selected").text()
	$("#lccNameforexamine").val(lccName);
}
function setExamLccCode(){
	$("#examineLccName").val($("#examineLccCode").find("option:selected").text());
	if($("#examineLccCode").val() == '-1') return;
	$.post("${ctx}/quality/examine/getLccUserByLccCode",{lccCode:$("#examineLccCode").val()},function(result){
		if(result.success){
			
			$("#examineUserCode").empty();
			
			$("#examineUserCode").append("<option value='-1'>请选择</option>");
			$.each(result.lccUserList,function(idx,item){
				var userCode = item['userCode'];
				var name = item['name'];
				$("#examineUserCode").append("<option value='"+userCode+"'>"+name+"</option>");
			});
			
		}
	},'json');
}
function setExamUserCode(){
	var text = $("#examineUserCode").find("option:selected").text();
	$("#examineUserNameForm").val(text);
}
</script>