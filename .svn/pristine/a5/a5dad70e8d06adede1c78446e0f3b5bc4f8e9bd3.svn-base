<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="repair_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for=""><span style="color: red">*</span>设备档案号:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="archivesNoform" name="archivesNo" value="${repair.archivesNo }" class="form-control input-sm" placeholder="设备档案号">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccName">医院名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="lccName" name="lccName" value="${repair.lccName }" readonly="readonly" class="form-control input-sm" placeholder="所属单位">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="">设备名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="materlName1" name="materlName" value="${repair.materlName }" readonly="readonly" class="form-control input-sm" placeholder="设备名称">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="storeUnit">库存单位:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="storeUnit" name="storeUnit" value="${repair.storeUnit }" readonly="readonly" class="form-control input-sm" placeholder="库存单位">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="spec">规格:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="spec" name="spec" value="${repair.spec }" readonly="readonly" class="form-control input-sm" placeholder="规格">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="supplierName">生产厂家:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="supplierName" name="supplierName" value="${repair.supplierName }" readonly="readonly" class="form-control input-sm" placeholder="生产厂家">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="contactTel">维修计量状态:</label>
			<div class="col-lg-7 col-md-8">
			<!--  
			<select id="repairState" name="repairState" class="form-control input-sm">
					<option value="">请选择维修计量状态</option>
					<option value="1">维修</option>
					<option value="2">计量</option>
				</select>
			-->
				<input id="repairState1" name="repairState" value="1" type="radio" checked='checked'/>维修 &nbsp;&nbsp;&nbsp;&nbsp;
				<input id="repairState2" name="repairState" value="2" type="radio" ${repair.isMeasure == 2 ? "disabled='disabled'" : ""}/>计量
				
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="repairDate">维修计量时间:</label>
			<div class="col-lg-7 col-md-8">
				<input class="form-control input-sm" id="repairDate" name="repairDate" value="${repair.repairDateStr }"  type="text" placeholder="维修计量时间"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="repairOrganization">维修计量机构:</label>
			<div class="col-lg-7 col-md-8">
				<input class="form-control input-sm" id="repairOrganization" name="repairOrganization" value="${repair.repairOrganization }" type="text" placeholder="维修计量机构"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="repairUser">维修计量人员:</label>
			<div class="col-lg-7 col-md-8">
				<input class="form-control input-sm" id="repairUser" name="repairUser" value="${repair.repairUser }" type="text" placeholder="维修计量人员"/>
			</div>
		</div>
		<input id="id" name="id" value="${repair.id }" type="hidden" />
		<input id="projectId" name="projectId" value="${repair.projectId }" type="hidden" /> 
		<input id="lccCode" name="lccCode" value="${repair.lccCode }" type="hidden" />
		<input id="materlInfoCode" name="materlInfoCode" value="${repair.materlInfoCode }" type="hidden" />
	</form>
	
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">
$(function(){
	$('#repairDate').datepicker({
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#repairDate').datepicker('hide');
	});
	
	$("#repair_form").validate({
		rules: {
			archivesNo:{
				required:true
			},
			repairOrganization:{
				maxlength:120
			},
			repairUser:{
				maxlength:120
			}
		},
		messages:{
			archivesNo:{
				required:'请选择设备档案号！'
			},
			repairOrganization:{
				maxlength:'维修计量机构长度不能超过120个字符！'
			},
			repairUser:{
				maxlength:'维修计量人员长度不能超过120个字符！'
			}
		}
	});
});
$.getJSON("${ctx}/combox/comboxData?table=REPAIRVIEW&cols=ARCHIVES_NO|LCC_NAME|LCC_CODE|MATERLINFO_CODE|MATERL_NAME|STORE_UNIT|MATERL_SPEC|SUPPLIER_NAME|IS_MEASURE&where=",function(data) { 
	$('#archivesNoform').autocomplete(data,{
		mustMatch:true,
		minChars:0,
       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
        formatItem: function(item,i, max) {
            return '<table><tr><td width="50%">' + item.ARCHIVES_NO + '</td><td>' + item.MATERL_NAME + '</td></tr></table>';
        },

        // 指定 与 输入文字匹配的字段名
        formatMatch: function(item,i, max) {
            return item.ARCHIVES_NO;
        },
        // 选中 某条记录在输入框里 显示的数据字段
        formatResult: function(item) {
        	if(item){
        		return item.ARCHIVES_NO;
        	}
        }
	}); 

	//选中 某条记录 触发的事件
	$('#archivesNoform').result(function(event, item){ 
	       $("#archivesNoform").val(item.ARCHIVES_NO);
	       $("#lccName").val(item.LCC_NAME);
	       $("#materlName1").val(item.MATERL_NAME);
	       $("#storeUnit").val(item.STORE_UNIT);
	       $("#spec").val(item.MATERL_SPEC);
	       $("#supplierName").val(item.SUPPLIER_NAME);
	       if(item.IS_MEASURE == 2){
		       $("#repairState2").attr("disabled", "disabled");
	       }
	       $("#lccCode").val(item.LCC_CODE);
	       $("#materlInfoCode").val(item.MATERLINFO_CODE);
	}); 

});
</script>