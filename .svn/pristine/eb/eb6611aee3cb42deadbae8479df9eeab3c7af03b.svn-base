<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    <div id="alertform" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
    
    <form id="supplier_form" class="form-horizontal">
		<div class="form-group" hidden>
			<label class="col-lg-3 col-md-1  control-label" for="supplierCode"><span style="color: red">*</span>供货商代码:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="supplierCode" name="supplierCode" value="${supplier.supplierCode }" readonly="readonly" class="form-control input-sm" placeholder="供货商代码" required>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="supplierName"><span style="color: red">*</span>供货商名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="supplierName" name="supplierName" value="${supplier.supplierName }" class="form-control input-sm" placeholder="供货商名称">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="supplierClass">供货商分类:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="supplierClass" value="1" checked='checked' />生产厂商 &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="supplierClass" value="2" ${supplier.supplierClass eq 2 ? "checked='checked'" : '' } />供货商&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="supplierClass" value="3" ${supplier.supplierClass eq 3 ? "checked='checked'" : '' } />生产商和供货商 
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="localityFlag">产地类别:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="localityFlag" value="1" checked='checked' />国产 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="localityFlag" value="2" ${supplier.localityFlag eq 2 ? "checked='checked'" : '' } />合资&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="localityFlag" value="3" ${supplier.localityFlag eq 3 ? "checked='checked'" : '' } />进口 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="localityFlag" value="4" ${supplier.localityFlag eq 4 ? "checked='checked'" : '' } />其它
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="creditClass">信誉级别:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="creditClass" name="creditClass" value="${supplier.creditClass }" class="form-control input-sm" placeholder="信誉级别 1～10" number/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="qualificationDisableDate">资质有效日期:</label>
			<div class="col-lg-7 col-md-8">
				<input class="form-control input-sm" id="qualificationDisableDate" name="qualificationDisableDate" value="${project.qualificationDisableDateStr }"  type="text" placeholder="资质有效日期"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="address">地址:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="address" name="address" value="${supplier.address }" class="form-control input-sm" placeholder="地址">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="zipCode">邮编:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="zipCode" name="zipCode" value="${supplier.zipCode }" class="form-control input-sm" placeholder="邮编">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="contactName">联系人姓名:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="contactName" name="contactName" value="${supplier.contactName }" class="form-control input-sm" placeholder="联系人姓名">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="contactTel">手机:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="contactTel" name="contactTel" value="${supplier.contactTel }" class="form-control input-sm" placeholder="联系人电话">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="contactEmail">Email:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="contactEmail" name="contactEmail" value="${supplier.contactEmail }" class="form-control input-sm" placeholder="联系人邮箱">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="businessTel">电话:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="businessTel" name="businessTel" value="${supplier.businessTel }" class="form-control input-sm" placeholder="单位电话">
			</div>
		</div>
		<!-- 
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="status">状态:</label>
			<div class="col-lg-7 col-md-8">
				<input type="radio" name="status" value="1" checked='checked' />有效&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status" value="2" ${supplier.status eq 2 ? "checked='checked'" : '' } />无效
			</div>
		</div>
		 -->
		<input id="supplierCode1" name="supplierCode1" value="${supplier.supplierCode }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
		    function openError(message,delay){
		    	$('#alertQualification').show().find('strong').text(message);
		    	window.setTimeout(function() {
		    		$('#alertQualification').slideUp("slow");
		    	}, delay);
		    }
			//选择开始时间触发
			$('#qualificationDisableDate').datepicker({
				format : 'yyyy-mm-dd',
				weekStart : 1
			}).on('changeDate', function(ev) {
				$('#qualificationDisableDate').datepicker('hide');
			});
			
			$("#supplier_form").validate({
				rules: {
					supplierName:{
						required:true,
						maxlength:250
					},
					qualificationDisableDate:{
						required:true,
					},
					creditClass:{
						number:true,
						digits:true,
						min:1,
						max:10
					},
					zipCode:{
						postcode:true
					},
					contactTel:{
						phone:true
					},
					contactEmail:{
						email:true,
						maxlength:100
					},
					businessTel:{
						phone:true
					}
				},
				messages:{
					supplierName:{
						required:'请输入供应商名称！',
						maxlength:'最大长度不能超过250个字符！'
					},
					qualificationDisableDate:{
						required:'资质有效日期不能为空'
					},
					creditClass:{
						number:'请输入数字',
						digits:'请输入1～10的中的正整数！',
						min:'最小值只能是1',
						max:'最大值只能是10'
					},
					zipCode:{
						postcode:'邮编格式不正确！'
					},
					contactTel:{
						phone:'联系人电话格式不正确！'
					},
					contactEmail:{
						email:'邮箱格式不正确！',
						maxlength:'最大不能超过100个字符！'
					},
					businessTel:{
						phone:'单位电话格式不正确！'
					}
				}
			});

		});
		
		$.validator.addMethod('postcode', function(value, element) {
			if(value == "")return true;
			var zipCode =  value.search(/^[1-9]{1}[0-9]{5}$/);
		    if(zipCode == -1)
		        return false;
		    return true;  
		}, '邮编格式不正确！');
		$.validator.addMethod('phone',function(value, element){
			if(value=="")return true;
			var mobile = /^1[3|5|8]\d{9}$/ , phone = /^0\d{2,3}-?\d{7,8}$/;
			return mobile.test(value) || phone.test(value);
		}, '电话格式不正确');
		
//		$.validator.addMethod('disableDate',function(value, element){
//			if(value=="") return true;
//			var fromDate =  DateFormat.parse(value, 'yyyy-MM-dd'); 
//			if (fromDate.valueOf() < new Date().valueOf()){
//				return false;
//			}
//			return true;
//		}, '不能晚于当前日期！');
	</script>
