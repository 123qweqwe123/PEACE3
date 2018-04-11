<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
    <!-- 
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="materlName">当前项目:</label>
			<div class="col-lg-7 col-md-8">
				${linkMan.projectName }
			</div>
		</div>
		 -->
		<input id="projectId" name="projectId" value="${linkMan.projectId }" type="hidden"/>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="username"><span style="color: red">*</span>姓名:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="username" name="username" value="${linkMan.username }" class="form-control input-sm" placeholder="姓名" >
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="materlSpec">联系电话:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="phone" name="phone" value="${linkMan.phone }" class="form-control input-sm" placeholder="联系电话" >
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="mobile">手机:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="mobile" name="mobile" value="${linkMan.mobile }" class="form-control input-sm" placeholder="手机" >
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="parentCode"><span style="color: red">*</span>所属单位:</label>
			<div class="col-lg-7 col-md-8">
				<select id="lccCode" name="lccCode" class="form-control input-sm">
					<option value="">请选择单位</option>
					<c:forEach items="${lccDictList }" var="lccDict">
						<option value="${lccDict.lccCode }" ${linkMan.lccCode == lccDict.lccCode ? 'selected="selected"' : '' }>${lccDict.lccName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="materlSpec">Email:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="email" name="email" value="${linkMan.email }" class="form-control input-sm" placeholder="Email" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="address">联系人地址:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="address" name="address" value="${linkMan.address }" class="form-control input-sm" placeholder="联系人地址" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="department">科室:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="department" name="department" value="${linkMan.department }" class="form-control input-sm" placeholder="科室" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="lccRole">单位角色:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="lccRole" name="lccRole" value="${linkMan.lccRole }" class="form-control input-sm" placeholder="单位角色" >
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="status">显示状态:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="status" value="0" checked='checked' />显示&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status" value="1" ${linkMan.status eq 1 ? "checked='checked'" : '' } />不显示
			</div>
		</div>
		<input id="linkManCode" name="linkManCode" value="${linkMan.linkManCode }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
			$("#supplier_form").validate({
				rules: {
					username:{
						required:true,
						maxlength:100
					},
					phone:{
						phone:true
					},
					/* mobile:{
						mobile:true
					}, */
					email:{
						email:true,
						maxlength:30,
						minlength:4
					},
					lccCode:{
						required:true
					},
					lccRole:{
						maxlength:90
					}
				},
				messages:{
					username:{
						required:'请输入联系人名称！',
						maxlength:$.format('不能超过{0}个字符')
					},
					phone:{
						phone:'电话格式不正确！'
					},
					/* mobile:{
						mobile:'手机格式不正确！'
					}, */
					email:{
						maxlength:$.format('不能超过{0}个字符'),
						minlength:$.format('不能少于{0}个字符')
					},
					lccCode:{
						required:'请选择所属单位！'
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
