<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="examine_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccCode"><span style="color: red">*</span>所属省份:</label>
			<div class="col-lg-7 col-md-8">
				<input type="hidden" id="provinceCode" name="provinceCode" value="${pipExpImplement.provinceCode}">
				<input type="text" id="areaName" name="areaName" value="${pipExpImplement.provinceName}"   class="form-control input-sm" placeholder="输入省份首字母检索">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineLccCode"><span style="color: red">*</span>选择单位:</label>
			<div class="col-lg-7 col-md-8">
				<input type="hidden" id="lccCode1" name="lccCode" value="${pipExpImplement.lccCode }"/>
				<input class="form-control input-sm" id="lccName" name="lccName" value="${pipExpImplement.lccName }" type="text" placeholder="[ 请先选择省份 ] 输入简码选择单位" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="implementStartDate"><span style="color: red">*</span>考核起始日期:</label>
			<div class=" col-lg-7 col-md-8 input-group date" style="width: 200px;left: 15px" id="implementStartDateDiv" data-date-format="yyyy-mm-dd">
				<input type="text"  id="implementStartDate" placeholder="考核起始日期" name="implementStartDate" class="form-control input-sm" value="${pipExpImplement.implementStartDateStr }" />
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="implementEndDate"><span style="color: red">*</span>考核结束日期:</label>
			<div class=" col-lg-7 col-md-8 input-group date" style="width: 200px;left: 15px" id="implementEndDateDiv" data-date-format="yyyy-mm-dd">
				<input type="text"  id="implementEndDate" placeholder="考核结束日期" name="implementEndDate" class="form-control input-sm" value="${pipExpImplement.implementEndDateStr }" />
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineUserCode1"><span style="color: red">*</span>患者数量:</label>
			<div class="col-lg-7 col-md-8">
				<input class="form-control input-sm" id="patientCount" name="patientCount" value="${pipExpImplement.patientCount }" type="text" placeholder="输入患者数量"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineUserCode1"><span style="color: red">*</span>观摩门诊类型:</label>
			<div class="col-lg-7 col-md-8">
				<select class="form-control input-sm" id="watchPatientType" name="watchPatientType" placeholder="选择观摩门诊类型">
					<option value="1"  <c:if test="${pipExpImplement.watchPatientType ==1 }"> selected="selected"</c:if>>基线</option>
					<option value="2" <c:if test="${pipExpImplement.watchPatientType ==2 }"> selected="selected"</c:if>>随访</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="spec">考核备注:</label>
			<div class="col-lg-7 col-md-8">
				<textarea rows="3" id="remark" class="form-control" name="remark" placeholder="考核备注">${pipExpImplement.remark}</textarea>
			</div>
		</div>
		<input id="id" name="id" value="${pipExpImplement.id }" type="hidden" /> 
	</form>
	
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#implementStartDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#implementStartDateDiv').datepicker('hide');
	});
	$('#implementEndDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#implementEndDateDiv').datepicker('hide');
	});
	//所属省份
	$.getJSON("${ctx}/combox/province",function(data) { 
	    $('#areaName').autocomplete(data,{
	        minChars: 0,
	        mustMatch:true, 
	        width:260,
	       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	        formatItem: function(item,i, max) {
	            return '<table><tr><td width="80px;">' + item.PROVINCE_CODE + '</td><td width="180px;">' + item.PROVINCE_NAME + '</td></tr></table>';
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item,i, max) {
	            return item.HELP_CODE+item.PROVINCE_CODE+item.PROVINCE_NAME;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item) {
	            return item.PROVINCE_NAME;
	        }
	    }); 
	  //选中 某条记录 触发的事件
	    $('#areaName').result(function(event, item){ 
			if(item){
				if(item.PROVINCE_CODE != $("#provinceCode").val()){
					$("#provinceCode").val(item.PROVINCE_CODE);
				}
			}else{
				$("#provinceCode").val("");
			}
			$("#lccCode1").val("");
			$("#lccName").val("");
			initRcc();
	     });
	});
	
	
	$("#examine_form").validate({
		rules: {
			lccName:{
				required:true
			},
			areaName:{
				required:true
			},
			implementStartDate:{
				required:true
			},
			implementEndDate:{
				required:true
			},
			patientCount:{
				required:true,
				number:true
			},
			watchPatientType:{
				required:true
			},
			remark:{
				maxlength:1000
			}
		},
		messages:{
			lccName:{
				required:"医院名称不能为空"
			},
			areaName:{
				required:"所属省份不能为空"
			},
			implementStartDate:{
				required:"考核起始日期不能为空"
			},
			implementEndDate:{
				required:"考核结束日期不能为空"
			},
			patientCount:{
				required:"患者数量不能为空",
				number:"只能输入整数"
			},
			watchPatientType:{
				required:"观摩门诊类型不能为空"
			},
			remark:{
				maxlength:"考核备注不能多余1000字"
			}
		}
	});
	
	
});
function initRcc(){
	$('#lccName').unautocomplete();
	var provinceCode1 = $("#provinceCode").val();
	if(provinceCode1 == ""){
		provinceCode1 = "xxx";
	}
	$.getJSON("${ctx}/combox/dataNoLimitLcc?provinceCode="+provinceCode1+"&noDataLimit=true",function(data) { 
	    $('#lccName').autocomplete(data,{
	        minChars: 0,
	        mustMatch:true, 
	        width:260,
	       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	        formatItem: function(item,i, max) {
	            return '<table><tr><td width="80px;">' + item.LCC_CODE + '</td><td width="180px;">' + item.LCC_NAME + '</td></tr></table>';
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item,i, max) {
	            return item.HELP_CODE+ item.LCC_CODE+item.LCC_NAME;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item) {
	            return item.LCC_NAME;
	        }
	    }); 
	  //选中 某条记录 触发的事件
	    $('#lccName').result(function(event, item){ 
			if(item){
				if(item.LCC_CODE != $("#lccCode1").val()){
					$("#lccCode1").val(item.LCC_CODE);
				}
			}else{
				$("#lccCode1").val("");
			}
	     });
	});
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
$.validator.addMethod("number", function(value, element) {
    var tel = /^\+?[0-9][0-9]*$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

</script>