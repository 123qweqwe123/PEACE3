<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="lcc_form" class="form-horizontal">
    <!-- 
		<div class="form-group" >
			<label class="col-lg-4 col-md-3  control-label" for="projectName">当前项目:</label>
			<div class="col-lg-7 col-md-8">
				${lccUser.projectName }
			</div>
		</div>
		 -->
		 <input id="lccRoleType" type="hidden" value="${lccRoleType }" />
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="name"><span style="color: red">*</span>用户姓名:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="name" name="name" value="${lccUser.name }" class="form-control input-sm" placeholder="姓名">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccCode"><span style="color: red">*</span>所属单位:</label>
			<div class="col-lg-7 col-md-8">
				<!-- 
				<select id="lccCode" name="lccCode" class="form-control input-sm" >
					<option value="-1">请选择</option>
					<option value="11">山东省</option>
				</select>
				 -->
				<%-- <select id="lccCode_1" name="lccCode" class="form-control input-sm" onchange="changeLccUserStatus(this)" 
				<c:if test="${lccUser.projectId != null}">disabled</c:if>>
					<option value="-1">请选择单位</option>
					<c:forEach items="${lccDictList }" var="lccDict">
						<option value="${lccDict.lccCode }" ${lccUser.lccCode == lccDict.lccCode ? 'selected="selected"' : '' }>${lccDict.lccName }</option>
					</c:forEach>
				</select> --%>
				<input type="hidden" id="lccCode_1" name="lccCode" value="${lccUser.lccCode }"/>
				<input type="text" <%-- <c:if test='${lccUser.projectId != null}'> readonly="readonly"</c:if>  --%> id="lccName_1" name="lccName" value="${lccUser.lccName }" placeholder="请输入医院简拼或者LCCID" class="form-control input-sm"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="phone">联系电话:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="phone" name="phone" value="${lccUser.phone }" class="form-control input-sm" placeholder="联系电话">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="mobile">手机:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="mobile" name="mobile" value="${lccUser.mobile }" class="form-control input-sm" placeholder="手机">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="email">Email:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="email" name="email" value="${lccUser.email }" class="form-control input-sm" placeholder="Email">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccRole">单位中角色描述:</label>
			<div class="col-lg-7 col-md-8">
				<%-- <input type="text" id="lccRole" name="lccRole" value="${lccUser.lccRole }" class="form-control input-sm" placeholder="单位中角色描述"> --%>
				
				<select id="lccRole" name="lccRole" class="form-control input-sm">
					<option value="">请选择</option>
					<option value="课题负责人" ${lccUser.lccRole == '课题负责人' ? 'selected="selected"' : '' }>课题负责人</option>
					<option value="研究医生" ${lccUser.lccRole == '研究医生' ? 'selected="selected"' : '' }>研究医生</option>
					<option value="研究助理" ${lccUser.lccRole == '研究助理' ? 'selected="selected"' : '' }>研究助理</option>
				</select>
			</div>
		</div>
			<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="researchStatus"><span style="color: red">*</span>参研状态:</label>
			<div class="col-lg-7 col-md-10">
			   <select id="researchStatus" style="width: 200px" name="researchStatus" class="form-control input-sm">
				  <option value="1"  ${lccUser.researchStatus == 1 ? 'selected="selected"' : '' }>参研</option>
				  <option value="0"  ${lccUser.researchStatus == 0 ? 'selected="selected"' : '' }>离研</option>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="isSignSigna">电子签名表:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="isSignSigna" id="isSignSigna1" value="1" ${lccUser.isSignSigna eq '1' ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="isSignSigna" id="isSignSigna0" value="2" ${lccUser.isSignSigna eq '2' or !isEdit ? "checked='checked'" : '' } />否
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="isGetResume">简历:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="isGetResume" id="isGetResume1" value="1" ${lccUser.isGetResume eq '1' ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="isGetResume" id="isGetResume0" value="2" ${lccUser.isGetResume eq '2' or !isEdit ? "checked='checked'" : '' } />否
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="isJoinTraining">培训:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="isJoinTraining" id="isJoinTraining1" value="1" ${lccUser.isJoinTraining eq '1' ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="isJoinTraining" id="isJoinTraining0" value="2" ${lccUser.isJoinTraining eq '2' or !isEdit ? "checked='checked'" : '' } />否
			</div>
		</div>
		<!-- 
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="status">用户状态:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="status" id="status1" value="1" ${lccUser != null && lccUser.status == '1' ? '':'disabled="disabled"' } ${lccUser.status eq '1' ? "checked='checked'" : '' } />激活 &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status" id="status0" value="2" ${lccUser.status eq '2' or !isEdit ? "checked='checked'" : '' } />未激活
			</div>
		</div>
		 -->
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="addToLinkMan">是否添加到联系人:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="addToLinkMan" id="addToLinkMan1" value="1" ${lccUser.addToLinkMan eq '1' ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="addToLinkMan" id="addToLinkMan0" value="2" ${lccUser.addToLinkMan eq '2' or !isEdit ? "checked='checked'" : '' } />否
			</div>
		</div>
		 <input id="projectId" name="projectId" value="${lccUser.projectId }" type="hidden"/>
		<input id="userCode" name="userCode" value="${lccUser.userCode }" type="hidden" />
		<input id="linkManCode" name="linkManCode" value="${lccUser.linkManCode }" type="hidden" />

	</form>
	
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">
	$(function() {
		$.getJSON("${ctx}/pro/lccuser/getLccByType?lccRoleType="+$("#lccRoleType").val(),function(data) { 
			$('#lccName_1').autocomplete(data,{
				minChars: 0,
				mustMatch :true,
				width:"250px",
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="120px;">' + item.lccName + '</td></tr></table>';
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.helpCode+item.lccCode;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.lccName;
		        }
			}); 
			//选中 某条记录 触发的事件
			$('#lccName_1').result(function(event, item){ 
				if(item){
					if(item.lccCode != $("#lccCode_1").val()){
						$("#lccCode_1").val(item.lccCode);
						 parent.$('#hiddenLccCode').val(item.lccCode);
					     parent.$('#hiddenLccName').val(item.lccName);
					}
				}
				else{
					$("#lccCode_1").val("");
					parent.$('#hiddenLccCode').val("");
				    parent.$('#hiddenLccName').val("");
				}
			}); 
		});
		$("#lcc_form").validate({
			rules: {
				name:{
					required:true
				},
				lccCode:{
					min:0
				},
				email:{
					email:true
				},
				phone:{
					phone:true
				}/* ,
				mobile:{
					mobile:true
				} */
			},
			messages:{
				name:{
					required:'请输入姓名'
				},
				lccCode:{
					min:'请选择单位'
				},
				email:{
					email:'邮箱格式不正确'
				},
				phone:{
					phone:'电话格式不正确！'
				}/* ,
				mobile:{
					mobile:'手机格式不正确！'
				} */
			}
		});
	});
  
/**
	$("#LccCode").change(function () {
	 var aaa = $("#LccCode  option:selected").text();  
	alert(aaa);  
	});
	 $("#LccCode").trigger("change");  
	**/ 
	 
	function changeLccUserStatus(tis){
		
		parent.$('#hiddenLccCode').val(tis.value);
	     var txt=tis.options[tis.options.selectedIndex].text;
	     parent.$('#hiddenLccName').val(txt);
	     

		/**
		$.post("${ctx}/pro/lccuser/getLccStatus",{lccCode:data},function(result){
			if(result.success){
				$("#status1").removeAttr("disabled");
			}else{
				$("#status1").attr("disabled",true);
			}
		},'json');
		
		**/
	}


	$.validator.addMethod("mobile", function(value, element) {
	    var length = value.length;
	    var mobile =  /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/
	    return this.optional(element) || (length == 11 && mobile.test(value));
	}, "手机号码格式错误");
	$.validator.addMethod("phone", function(value, element) {
	    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
	    return this.optional(element) || (tel.test(value));
	}, "电话号码格式错误");
</script>