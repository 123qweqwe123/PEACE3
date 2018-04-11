<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
   
		<input id="patientId" name="patientId" value="${patient.patientId }" type="hidden"/>
		<input id="sex" name="sex" value="${patient.sex }" type="hidden"/>
		<input id="patientCode" name="patientCode" value="${patient.patientCode }" type="hidden"/>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="patientName"><span style="color: red">*</span>姓名:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="patientName" name="patientName" readonly="readonly" value="${patient.patientName }" class="form-control input-sm" placeholder="姓名" >
			</div>
		</div>

		
		 <div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="sex"><span style="color: red">*</span>性别:</label>
			<div class="col-lg-7 col-md-8">
				<!-- <input type="text" id="sex" name="sex" value="${patient.sex }" class="form-control input-sm" placeholder="性别" > -->
				<select id="sex" name="sex" class="form-control input-sm">
						<option value="1" ${patient.sex ==1 ? 'selected="selected"' : '' }>男</option>
						<option value="2" ${patient.sex ==2 ? 'selected="selected"' : '' }>女</option>
				</select>
			</div>
			
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="phone">联系电话:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="phone" name="phone" value="${patient.phone }" class="form-control input-sm" placeholder="联系电话" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="mobile">手机:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="mobile" name="mobile" value="${patient.mobile }" class="form-control input-sm" placeholder="手机" >
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="lccCode">所属单位:</label>
			<div class="col-lg-7 col-md-8">
				<select id="lccCode" name="lccCode" class="form-control input-sm">
					<c:forEach items="${lccDictList }" var="lccDict">
						<option value="${lccDict.lccCode }" ${patient.lccCode == lccDict.lccCode ? 'selected="selected"' : '' }>${lccDict.lccName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="idNumber">身份证:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="idNumber" name="idNumber" value="${patient.idNumber}" class="form-control input-sm" placeholder="身份证号" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="email">Email:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="email" name="email" value="${patient.email }" class="form-control input-sm" placeholder="邮箱地址" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="linkMan1">第一联系人:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="linkMan1" name="linkMan1" value="${patient.linkMan1 }" class="form-control input-sm" placeholder="第一联系人" >
			</div>
		</div>
	    <div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="linkMan1Mobile">第一联系人手机:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="linkMan1Mobile" name="linkMan1Mobile" value="${patient.linkMan1Mobile }" class="form-control input-sm" placeholder="第一联系人手机" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="linkMan1Relation">第一联系人与本人关系:</label>
			<div class="col-lg-7 col-md-8">
				<select id="linkMan1Relation" style="width: 120px" onchange="changeItem()" name="linkMan1Relation" placeholder="第一联系人与本人关系" class="input-sm">
					<option value="">请选择</option>
					<option value="父母" <c:if test="${patient.linkMan1Relation =='父母' }"> selected="selected" </c:if> >父母</option>
					<option value="配偶"  <c:if test="${patient.linkMan1Relation=='配偶' }"> selected="selected" </c:if> >配偶</option>
					<option value="子女"  <c:if test="${patient.linkMan1Relation =='子女' }"> selected="selected" </c:if>  >子女</option>
					<option value="兄弟姐妹"  <c:if test="${patient.linkMan1Relation =='兄弟姐妹' }"> selected="selected" </c:if> >兄弟姐妹</option>
					<option value="其他"  <c:if test="${ not empty patient.linkMan1Relation and patient.linkMan1Relation !='父母' and patient.linkMan1Relation != '兄弟姐妹' and patient.linkMan1Relation != '子女' and patient.linkMan1Relation != '配偶'}"> selected="selected" </c:if> >其他</option>
				</select>
				<input style="width: 150px" type="text" id="linkMan1Relation_other"  value="${patient.linkMan1Relation }" class="input-sm" placeholder="第一联系人与本人关系" > 
				<%-- 
				<input type="text" id="linkMan1Relation" name="linkMan1Relation" value="${patient.linkMan1Relation }" class="form-control input-sm" placeholder="第一联系人与本人关系" > --%>
			</div>
		</div>


<!-- 		<div class="form-group" -->

<%-- 			 ${patient.isJoinMsg == "1" ? 'hidden="true"' : ''} --%>
<!-- 		> -->
<!-- 			<label class="col-lg-3 col-md-1  control-label" for="is6State">是否可答6月问卷:</label> -->
<!-- 			<div class="col-lg-7 col-md-8"> -->
<!-- 				<select id="is6State" name="is6State" class="form-control input-sm"> -->
<%-- 					<option value="1" ${patient.is6State ==1 ? 'selected="selected"' : '' }>是</option> --%>
<%-- 					<option value="2" ${patient.is6State ==2 ? 'selected="selected"' : '' }>否</option> --%>
<!-- 				</select> -->
<!-- 			</div> -->

<!-- 		</div> -->
		
	</form>
    
	<script type="text/javascript">
	   function changeItem(){
		   if($("#linkMan1Relation").val()!='其他'){
			   $("#linkMan1Relation_other").hide();
		   }
		   else if($("#linkMan1Relation").val() !=''){
			   $("#linkMan1Relation_other").val('');
			   $("#linkMan1Relation_other").show();
		   }
	   }
	   function initRelation(){
		   if($("#linkMan1Relation_other").val()==''
				   ||$("#linkMan1Relation_other").val()=='父母'
				   || $("#linkMan1Relation_other").val()=='配偶'
				   || $("#linkMan1Relation_other").val()=='兄弟姐妹'
				   || $("#linkMan1Relation_other").val()=='子女'){
			   $("#linkMan1Relation_other").hide();
		   }
	   }
		$(function(){
			initRelation();
			$("#supplier_form").validate({
				rules: {
					username:{
						required:true,
						maxlength:100
					},
					/* phone:{
						phone:true
					}, */
					mobile:{
						mobile:true
					},
					email:{
						email:true,
						maxlength:30,
						minlength:4
					},
					linkMan1Mobile:{
						mobile:true
					},
					lccRole:{
						maxlength:90
					}
				},
				messages:{
					linkMan1Mobile:{
						mobile:'手机格式不正确'
					},
					username:{
						required:'请输入联系人名称！',
						maxlength:$.format('不能超过{0}个字符')
					},
					/* phone:{
						phone:'电话格式不正确！'
					}, */
					mobile:{
						mobile:'手机格式不正确！'
					},
					email:{
						maxlength:$.format('不能超过{0}个字符'),
						minlength:$.format('不能少于{0}个字符')
					},
					lccRole:{
						maxlength:$.format('不能超过{0}个字符')
					}
				}
			});


		});
		$.validator.addMethod("mobile", function(value, element) {
		    var length = value.length;
		    var mobile =  /^1\d{10}$/
		    return this.optional(element) || (length == 11 && mobile.test(value));
		}, "手机号码格式错误");
		$.validator.addMethod("phone", function(value, element) {
		    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
		    return this.optional(element) || (tel.test(value));
		}, "电话号码格式错误");
	</script>
