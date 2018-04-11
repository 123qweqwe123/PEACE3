<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<form id="user_form" class="form-horizontal" autocomplete="off">
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="loginName"><span style="color: red">*</span>登录名:</label>
            <div class="col-lg-5 col-md-5">
                <input type="text" id="loginName" name="loginName" class="form-control input-sm" placeholder="登录名" required>
            </div>
        </div>
        <!-- 
		<div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="name"><span style="color: red">*</span>用户名:</label>
			<div class="col-lg-5 col-md-5">
				<input type="text" id="name" name="name" class="form-control input-sm" placeholder="用户名" value="${user.name}" required>
			</div>
		</div>
		 
		<div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="lccname"><span style="color: red">*</span>用户:</label>
			<div class="col-lg-5 col-md-5">
				<select id="lccname" name="lccname" class="form-control input-sm" onchange="changeProper()">
					<option value="">请选择用户</option>
					<c:forEach items="${lccUserList }" var="lccUser">
						<option value="${lccUser.userCode };${lccUser.projectId};${lccUser.lccCode}" ${lccUser.userCode == user.userCode ? "selected='selected'" : '' }>${lccUser.name }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		-->
		<div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="lccname"><span style="color: red">*</span>用户名:</label>
			<div class="col-lg-5 col-md-5">
				<input type="text" id="lccname1" name="lccname" value="${user.name }" class="form-control input-sm hhh" ${empty user?'':'disabled'} placeholder="可输入用户名称简码" autocomplete="off" required>
			</div>
		</div>
		
		<input id="name" name="name" value="${user.name }" type="hidden"/>
		<input id="userCode" name="userCode" value="${user.userCode }" type="hidden"/>
		<input id="projectId" name="projectId" value="${user.projectId }" type="hidden"/>
		<input id="lccCode" name="lccCode" value="${user.lccCode }" type="hidden"/>
        <c:if test="${empty user}">
        <input type="text" style="display:none"/>
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="password"><span
                style="color: red">*</span>登录密码:</label>
            <div class="col-lg-5 col-md-5">
                <input type="password" id="password" name="password" class="form-control input-sm" placeholder="登录密码" required onkeydown="return noSpace(event)">
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="password_again"><span style="color: red">*</span>确认密码:</label>
            <div class="col-lg-5 col-md-5">
                <input type="password" id="password_again" name="password_again" class="form-control input-sm" placeholder="确认密码" required onkeydown="return noSpace(event)">
            </div>
        </div>
        </c:if>
        <!--  
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="organizationId"><span style="color: red">*</span>组织机构:</label>
            <div class="col-lg-5 col-md-5">
            
    <input type="hidden" class="form-control input-sm" name="organizationId" id="organizationId"/>
    
    <input type="text" class="form-control input-sm" value="${organization.name}" name="organization" id="organization" onkeydown="return false;"/>
             <%--    <select name="organizationId" class="form-control input-sm" id="organizationId">
                    <option value="">请选择</option>
                    <c:forEach items="${organizations}" var="org">
                    <option value="${org.id}">${org.name}</option>
                    </c:forEach>
                </select> --%>
          <div id="organizationZtree" class="tree-select"> 
		  <div id="organization_ztree" class="ztree" style="height:190px;overflow-y:scroll;" >
		  </div>
		  </div>
          </div>
        </div>-->
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="email">邮箱:</label>
            <div class="col-lg-5 col-md-5">
               <input type="text" id="email" name="email" class="form-control input-sm" placeholder="邮箱" value="${user.email}" >
            </div>
        </div>
        <!-- 
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="status"><span style="color: red">*</span>状态:</label>
            <div class="col-lg-5 col-md-5">
                <select name="status" class="form-control input-sm" id="status">
                    <option value="1">有效</option>
                    <option value="0">无效</option>
                </select>
            </div>
        </div>
    
         <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="isDelete"><span style="color: red">*</span>激活:</label>
            <div class="col-lg-5 col-md-5">
                <select name="isDelete" class="form-control input-sm" id="isDelete">
                      <option value="0">激活</option>
                      <option value="1">失效</option>
                </select>
            </div>
            
        </div> 
         -->
         
		<div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="remark">用户描述:</label>
			<div class="col-lg-5 col-md-5">
				<textarea rows="3" id="remark" class="form-control" name="remark" placeholder="用户描述">${user.remark}</textarea>
			</div>
		</div>
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="roles">角色:</label>
            <div class="col-lg-5 col-md-5">
            
                <c:forEach items="${roles}" var="role">
                <div class="checkbox-inline" style="margin-left:10px;">
                    <input name="roleId" type="checkbox" value="${role.id}"> ${role.name}
                </div>
                </c:forEach>
            </div>
        </div>
		<input type="hidden" name="id" id="id" value="${user.id}">
	</form>

	<script>
		$(function(){
			<c:if test="${empty user}">
			$.getJSON('${ctx}/combox/comboxData?table=PIP_COMM_LCC_USER&cols=HELP_CODE|USER_CODE|PROJECT_ID|LCC_CODE|NAME&where=[{"column":"PROJECT_ID","operate":"=","value":"${projectId}"}]',function(data) { 
				$('#lccname1').autocomplete(data,{
					mustMatch:true,
					minChars:0,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return "<table><tr><td width='40px;'>" + item.LCC_CODE + '</td><td>'+item.NAME + '</td></tr></table>';
			        },

			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.NAME;
			        }
				}); 

				//选中 某条记录 触发的事件
				$('#lccname1').result(function(event, item){ 
				       if(item){
				    	   $("#name").val(item.NAME);
					       $("#userCode").val(item.USER_CODE);
					       $("#projectId").val(item.PROJECT_ID);
					       $("#lccCode").val(item.LCC_CODE);
					      // alert(item.LCC_CODE);
				       }
				    	  
				}); 

			});
			</c:if>
//			var setting = {
//				    async: {
//				        enable: true,
//				        type:"get",
//				        url:"${ctx}/sys/rbac/organization/list",
//				    },
//				    view: {
//				        showIcon: true
//				    },
//				    data: {
//				        simpleData: {
//				            enable: true,
//				            idKey: "id",
//				            pIdKey: "parentId"
//				        },
//				        key:{
//				            name:"name" 
//				        }
//				    } ,
//				    callback: {
//				        onClick:zTreeOnClick
//				    }
//				};
			
//			 function zTreeOnClick(event, treeId, treeNode){
//				// if(treeNode.type=='5'){
//					 var org = treeNode.name;
//				      var id =  treeNode.id;
//				      $("#organizationId").val(id);  
//				      $("#organization").val(org);  
//				      $("#organizationZtree").toggle();
//				// }
//			}
			    
//			$.fn.zTree.init($("#organization_ztree"), setting);
			
			
//			$("#organization").click(function(e){
//			   /*  var ileft = $(e.target).offset().left;
//			    var itop = $(e.target).offset().top;
//			    $("#organizationZtree").css('top',itop-58).css('left', ileft-410 ); */
//			    $("#organizationZtree").css('width',$(this).outerWidth());
//			    $("#organizationZtree").toggle();
			    
//			});
			
			
		   <c:choose>
		     <c:when test="${empty user}">
		     $("#loginName").focus();
		     </c:when>
		     <c:otherwise>
		     $("#lccname").focus();
		     </c:otherwise>
		   </c:choose>
			$("#user_form").validate({
				rules: {
					lccname:{
						required:true
					},
					<c:if test="${empty user}">
					loginName:{
						required:true,
						maxlength:30,
						minlength:2,
						remote:{
							type:"POST",
							url:'${ctx}/sys/rbac/user/checkLoginNameExists',
							dataType:'json',
							data:{
								loginName:function(){
									var l_n = $("#loginName").val();
									return l_n;
								}
							},
							dataFilter: function(data) {
								data= eval("("+data+")");
								if(data){
									if(!data.result){
										return true;
									}
									return false;
								}
								return false;
							}
						}
					},
					password:{
						required:true,
						maxlength:30,
						minlength:6
					},
					password_again:{
						required:true,
						maxlength:30,
						minlength:6,
						equalTo: "#password"
					},
					</c:if>
					//organization:{
					//	required:true
					//},
					email:{
						email:true,
						maxlength:30,
						minlength:4
					},
					//status:{
					//	required:true
					//},
					remark:{
						maxlength:200,
						minlength:4
					}
				},
				messages:{
					lccname:{
						required:'用户名不能为空'
					},
					<c:if test="${empty user}">
					loginName:{
						required:'登录名不能为空',
						maxlength:'不能超过{0}个字符',
						minlength:'不能少于{0}个字符',
						remote:'登录名已存在'
					},
					password:{
						required:'登录密码不能为空',
						maxlength:$.format('不能超过{0}个字符'),
						minlength:$.format('不能少于{0}个字符')
					},
					password_again:{
						required:'确认密码不能为空',
						maxlength:$.format('不能超过{0}个字符'),
						minlength:$.format('不能少于{0}个字符'),
						equalTo: "两次密码输入不一致"
					},
					</c:if>
					//organization:{
					//	required:'请选择组织机构'
					//},
					email:{
						maxlength:$.format('不能超过{0}个字符'),
						minlength:$.format('不能少于{0}个字符')
					},
					//status:{
					//	required:'请选择用户状态'
					//},
					remark:{
						maxlength:'不能超过{0}个字符',
						minlength:'不能少于{0}个字符'
					}
				}
			});
			<c:if test="${not empty user}">
			$("#loginName").val('${user.loginName}');		
			$("#loginName").attr('readonly',true);
			
		//	$("#organization").val('${organization.name}');
//			$("#organizationId").val('${organization.id}');
			
			//$("#status").val('${user.status}');
			//$("#isDelete").val('${user.isDelete}');
			<c:if test="${not empty ownRoles}">
			var ownRoles = [
			<c:forEach items="${ownRoles}" var="role" varStatus="status">
			{id:'${role.id}'}<c:if test="${!status.last}">,</c:if>
			</c:forEach>
			];
		     
			for(var i=0;i<ownRoles.length;i++){
				$('#user_form [name =roleId]').each(function(j){
					   var cv = this.value;
					   
					   if(ownRoles[i].id==cv){
						   this.checked = true;
					   }
				 });
			}
			</c:if>
			</c:if>
		
		});
		
		//密码和确认密码输入框不能输入空格
		function noSpace(e){
			var keynum;
			var keychar;
			var numcheck;
			if(window.event) // IE
			  {
			  keynum = e.keyCode
			  }
			else if(e.which) // Netscape/Firefox/Opera
			  {
			  keynum = e.which
			  }
			keychar = String.fromCharCode(keynum);
			numcheck = /\s/;
			return !numcheck.test(keychar);			
		}
		
		function changeProper(){
			var code = $("#lccname").val();
			var codeArr = code.split(";");
			$("#name").val($("#lccname").find("option:selected").text());
			$("#userCode").val(codeArr[0]);
			$("#projectId").val(codeArr[1]);
			$("#lccCode").val(codeArr[2]);
		}
		

	</script>