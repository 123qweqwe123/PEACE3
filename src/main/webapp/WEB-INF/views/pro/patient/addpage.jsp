<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
   
		<div id="message_1" class="alert alert-success" hidden>
	    	<button data-dismiss="alert" class="close">&times;</button>
	    	<span id="messageSpanId_1"></span>
		</div>
		<div id="alert_1" class="alert alert-danger" hidden>
			<strong>Warning!</strong>
		</div>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="patientId_1"><span style="color: red">*</span>PID:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="patientId_1" name="patientId" value="${patient.patientId }" class="form-control input-sm" placeholder="患者ID" >
			</div>
		</div>
		<%--<div class="form-group">--%>
			<%--<label class="col-lg-3 col-md-1  control-label" for="patientCode"><span style="color: red">*</span>PID:</label>--%>
			<%--<div class="col-lg-7 col-md-8">--%>
				<%--<input type="text" id="patientCode" name="patientCode" value="${patient.patientCode }" class="form-control input-sm" placeholder="PID" >--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="patientName"><span style="color: red">*</span>姓名:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="patientName" name="patientName" value="${patient.patientName }" class="form-control input-sm" placeholder="姓名" >
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
				<input type="text" id="phone" name="phone" value="${patient.phone }" class="form-control input-sm" placeholder="联系电话(与手机号至少选填一个)" >
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="mobile">手机:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="mobile" name="mobile" value="${patient.mobile }" class="form-control input-sm" placeholder="手机(与联系电话至少选填一个)" >
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="lccCode"><span style="color: red">*</span>所属单位:</label>
			<div class="col-lg-7 col-md-8">
				<select id="lccCode" name="lccCode" class="form-control input-sm">
					<option value="">请选择单位</option>
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
				<input type="text" id="email" name="email" value="${patient.email }" class="form-control input-sm" placeholder="Email" >
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
			<label class="col-lg-3 col-md-1  control-label" for="linkMan1Relation_1">第一联系人与本人关系:</label>
			<div class="col-lg-7 col-md-8">
				<select id="linkMan1Relation_1" style="width: 120px" onchange="changeItem()" name="linkMan1Relation" placeholder="第一联系人与本人关系" class="input-sm">
					<option value="">请选择</option>
					<option value="父母" <c:if test="${patient.linkMan1Relation =='父母' }"> selected="selected" </c:if> >父母</option>
					<option  value="配偶"  <c:if test="${patient.linkMan1Relation=='配偶' }"> selected="selected" </c:if> >配偶</option>
					<option value="子女"  <c:if test="${patient.linkMan1Relation =='子女' }"> selected="selected" </c:if>  >子女</option>
					<option value="兄弟姐妹"  <c:if test="${patient.linkMan1Relation =='兄弟姐妹' }"> selected="selected" </c:if> >兄弟姐妹</option>
					<option value="其他"  <c:if test="${not empty patient.linkMan1Relation }"> selected="selected" </c:if> >其他</option>
				</select>
				<input style="width: 150px" type="text" id="linkMan1Relation_other_1"  value="${patient.linkMan1Relation }" class="input-sm" placeholder="第一联系人与本人关系" > 
				<%-- <input type="text" id="linkMan1Relation" name="linkMan1Relation" value="${patient.linkMan1Relation }" class="form-control input-sm" placeholder="第一联系人与本人关系" > --%>
			</div>
		</div>    
	</form>
	<script type="text/javascript">
	function changeItem(){
		   if($("#linkMan1Relation_1").val()!='其他'){
			   $("#linkMan1Relation_other_1").hide();
		   }
		   else if($("#linkMan1Relation_1").val() !=''){
			   $("#linkMan1Relation_other_1").show();
		   }
	   }
		$(function(){
			$("#linkMan1Relation_other_1").hide();
			$("#supplier_form").validate({
				rules: {
					patientId:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/sys/drop/checkIsExist',
							dataType:'json',
							data:{
								patientId:function(){
									return $("#patientId_1").val();
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
					patientCode:{
						required:true
					},
					patientName:{
						required:true,
						maxlength:100
					},
					/* phone:{
						phone:true
					}, */
					mobile:{
						mobile:true
					},
					linkMan1Mobile:{
						mobile:true
					},
					lccCode:{
						required:true
					},
					email:{
						email:true,
						maxlength:30,
						minlength:4
					},
					
				},
				messages:{
					linkMan1Mobile:{
						mobile:'手机格式不正确'
					},
					patientId:{
						required:'请填写患者ID',
						remote:'患者ID已经存在，请重新填写！'
					},
					patientCode:{
						required:'请填写患者编码！'
					},
					patientName:{
						required:'请输入联系人名称！',
						maxlength:$.format('不能超过{0}个字符')
					},
					/* phone:{
						phone:'电话格式不正确！'
					}, */
					lccCode:{
						required:'请选择所属单位'
					},
					mobile:{
						mobile:'手机格式不正确！'
					},
					
					email:{
						maxlength:$.format('不能超过{0}个字符'),
						minlength:$.format('不能少于{0}个字符')
					},
					
				}
			});


		});
		$.validator.addMethod("mobile", function(value, element) {
		    var length = value.length;
		    var mobile =  /^1\d{10}$/
		    return this.optional(element) || (length == 11 && mobile.test(value));
		}, "手机号码格式错误");
		$.validator.addMethod("phone", function(value, element) {
		    //var tel = /^(0[0-9]{2,3})?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
		    var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
		    return this.optional(element) || (tel.test(value));
		}, "电话号码格式错误");
	</script>
