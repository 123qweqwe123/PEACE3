<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="examine_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccCode"><span style="color: red">*</span>所属省份:</label>
			<div class="col-lg-7 col-md-8">
				<input type="hidden" id="areaCode" name="provinceCode" value="${plan.provinceCode}">
				<input type="text" id="areaName" name="areaName" value="${plan.provinceName}"   class="form-control input-sm" placeholder="输入省份首字母检索">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineLccCode"><span style="color: red">*</span>选择单位:</label>
			<div class="col-lg-7 col-md-8">
				<input type="hidden" id="lccCode1" name="lccCode" value="${plan.lccCode }"/>
				<input class="form-control input-sm" id="lccName" name="lccName" value="${plan.lccName }" type="text" placeholder="输入简码选择单位" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="planStartDate"><span style="color: red">*</span>考核开始日期:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="planStartDate" name="planStartDate" value="${plan.planStartDateStr }"  class="form-control input-sm" placeholder="考核开始日期">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="planEndDate"><span style="color: red">*</span>考核结束日期:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="planEndDate" name="planEndDate" value="${plan.planEndDateStr }"  class="form-control input-sm" placeholder="考核结束日期">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="examineUserCode1"><span style="color: red">*</span>考核人员姓名:</label>
			<div class="col-lg-7 col-md-8">
				<input class="form-control input-sm" id="expUserName" name="expUserName" value="${plan.expUserName }" type="text" placeholder="输入考核人员姓名"/>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="spec">考核备注:</label>
			<div class="col-lg-7 col-md-8">
				<textarea rows="3" id="remark" class="form-control" name="remark" placeholder="考核备注">${plan.remark}</textarea>
			</div>
		</div>
		<input id="id" name="id" value="${plan.id }" type="hidden" /> 
	</form>
	
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">
$(function(){
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
				if(item.PROVINCE_CODE != $("#areaCode").val()){
					$("#areaCode").val(item.PROVINCE_CODE);
				}
			}else{
				$("#areaCode").val("");
			}
			$("#lccCode1").val("");
			$("#lccName").val("");
			initRcc();
	     });
	});
	$('#planStartDate').datepicker({
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#planStartDate').datepicker('hide');
	});
	$('#planEndDate').datepicker({
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#planEndDate').datepicker('hide');
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
function initRcc(){
	$('#lccName').unautocomplete();
	var areaCode1 = $("#areaCode").val();
	if(areaCode1 == ""){
		areaCode1 = "xxx";
	}
	$.getJSON("${ctx}/combox/dataLimitLcc?provinceCode="+areaCode1+"&noDataLimit=true",function(data) { 
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
</script>