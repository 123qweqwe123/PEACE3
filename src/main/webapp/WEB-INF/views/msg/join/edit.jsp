<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<div id="edit_message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="edit_messageSpanId"></span>
	</div>
	<form id="patientForm" class="form-horizontal" autocomplete="off">
        
        <input type="hidden" id="patientId_" name ="patientId" value='${patientId }' />
         <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="mobile_"><span style="color: red">*</span>手机号:</label>
            <div class="col-lg-5 col-md-5">
                <input id="mobile_" type="text" name="mobile" class="form-control input-sm"  />
            </div>
        </div>

	</form>
	<script>
		$(function(){
			// 自定义验证手机号方法
			jQuery.validator.addMethod("telphoneValid", function(value, element) {
				var tel = /^1\d{10}$/;
				return tel.test(value) || this.optional(element);
			}, "请输入正确的手机号码");

			$("#patientForm").validate({
				rules: {
					mobile:{
						required:true,
						telphoneValid : true
					}
				},
				messages:{
					mobile:{
						required:'手机号码必须填写'

					}
				}
			});
		});

	</script>