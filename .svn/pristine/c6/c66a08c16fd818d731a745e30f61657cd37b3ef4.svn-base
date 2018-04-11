<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<form id="updatepwd_form" class="form-horizontal">
    <div class="form-group">
        <label class="col-lg-3 col-md-3 control-label" for="r_loginName">登录名:</label>
        <div class="col-lg-6 col-md-6">
            <input type="text" id="loginName" value="${user.loginName}" name="loginName" class="form-control input-sm" readonly="readonly">
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-3 control-label" for="name">用户名:</label>
        <div class="col-lg-6 col-md-6">
            <input type="text" id="name" name="name" class="form-control input-sm" value="${user.name}" readonly="readonly">
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-3 control-label" for="currentPassword"><span style="color: red">*</span>当前密码:</label>
        <div class="col-lg-6 col-md-6">
            <input type="password" id="currentPassword" name="currentPassword" class="form-control input-sm" placeholder="当前密码" required>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-3 control-label" for="newPassword"><span style="color: red">*</span>新密码:</label>
        <div class="col-lg-6 col-md-6">
            <input type="password" id="newPassword" name="newPassword" class="form-control input-sm" placeholder="新密码" required>
        	<span style="color: red">密码至少6位，且必须是数字和字母的组合</span>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-3 control-label" for="newPassword_again"><span
            style="color: red">*</span>确认密码:</label>
        <div class="col-lg-6 col-md-6">
            <input type="password" id="newPassword_again" name="newPassword_again" class="form-control input-sm" placeholder="确认新密码" required>
        </div>
    </div>
    <input type="hidden" id="id" name="id" value="${user.id}">
</form>


<script>
	$(function(){
		
		$("#updatepwd_form").validate({
			rules: {
				currentPassword:{
					required:true,
					maxlength:30,
					minlength:6,
					remote:{
						type:"POST",
						url:'${ctx}/sys/rbac/user/checkConfirmOldPassword',
						dataType:'json',
						data:{
							password:function(){
								return $("#currentPassword").val();
							}
						},
						dataFilter: function(data) {
							data= eval("("+data+")");
							if(data){
								if(data.result){
									return true;
								}
								return false;
							}
							return false;
						}
					}
				},
				newPassword:{
					required:true,
					maxlength:30,
					minlength:6,
					same:true,
					passwordA:true
				},
				newPassword_again:{
					required:true,
					maxlength:30,
					minlength:6,
					equalTo: "#newPassword"
				}
			},
			messages:{
				currentPassword:{
					required:'当前密码不能为空',
					maxlength:$.format('不能超过{0}个字符'),
					minlength:$.format('不能少于{0}个字符'),
					remote:'当前密码不正确'
				},
				newPassword:{
					required:'新密码不能为空',
					maxlength:$.format('不能超过{0}个字符'),
					minlength:$.format('不能少于{0}个字符'),
					same:'新密码不能与当前密码重复',
					passwordA:'密码必须是数字或者字母的组合！'
				},
				newPassword_again:{
					required:'确认新密码不能为空',
					maxlength:$.format('不能超过{0}个字符'),
					minlength:$.format('不能少于{0}个字符'),
					equalTo: "两次密码输入不一致"
				}					
			},
			submitHandler:function(form){
				
				$.post("${ctx}/sys/rbac/user/modifyPassword", 
		        		{ "id": $("#id").val(),'currentPassword':$("#currentPassword").val(),newPassword:$("#newPassword").val()},
		        		function(data){
		        			var alert =$('<div class="alert" hidden></div>')
		        		    var msgSpan = $('<span></span>');
		        		    alert.append(msgSpan);
		        		    $("#updatepwd_form").parent().empty().append(alert);
		        		    var message = "密码修改失败！";
		        		    
		        			if(data.success){
		        				message = "密码修改成功！";
		        				msgSpan.text(message);
		        				alert.addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
		        				reLogin();
		        			}else{
		        				message = data.msg+'!';
		        				msgSpan.text(message);
		        				alert.addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
		        				modifyPwdFail();
		        			}
		        		}, 
		        		"json");
			}
		});
		<c:if test="${not empty user}">
		$("#loginName").attr('readonly',true).val('${user.loginName}');
		$("#name").attr('readonly',true).val('${user.name}');
		$("#id").val('${user.id}');
		</c:if>
		
		jQuery.validator.addMethod("same", function(value, element) {  
            return this.optional(element) || same(value);  
        }, "新密码不能与当前密码重复");
		jQuery.validator.addMethod("passwordA", function(value, element) {  
			var reg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,30}$/;
            return this.optional(element) || reg.test(value);  
        }, "新密码不能与当前密码重复");
        function same(pwd) {  
            var oldPwd = $("#currentPassword").val();  
            if (oldPwd == pwd)  
                return false;  
            else  
                return true;  
        } 
	});
</script>