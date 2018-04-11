<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,com.bdcor.pip.web.material.supp.domain.MasterImportDetail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="import_form1" class="form-horizontal">
		<input type="hidden" id="imorderNo" name="imorderNo" value="${imorderNo }"/>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="materlName"><span style="color: red">*</span>物资名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="materlName" name="materlName" value="${mat.materlName }" class="form-control input-sm" placeholder="请选择已有物资(输入物资名称首字母)"/>
				<input type="hidden" id="materlCode" name="materlCode" value="${mat.materlCode }" />
				<input type="hidden" id="materlinfoCode" name="materlinfoCode" value="${mat.materlinfoCode }" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="materlBatch"><span style="color: red">*</span>批次:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="materlBatch" name="materlBatch" value="${mat.materlBatch }" class="form-control input-sm" placeholder="批次"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="wholesalePrice"><span style="color: red">*</span>采购价:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" name="wholesalePrice" value="${mat.wholesalePrice }" class="form-control input-sm" placeholder="采购价"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="materlPrice">出库单价:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" name="materlPrice" value="${mat.materlPrice }" class="form-control input-sm" placeholder="出库单价"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="importUnit"><span style="color: red">*</span>入库单位:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="importUnit" name="importUnit" value="${mat.importUnit }" class="form-control input-sm" placeholder="入库单位"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="importAmount"><span style="color: red">*</span>入库数量:</label>
			<div class="col-lg-7 col-md-8">
				<input type="number" id="importAmount" name="importAmount"  value="${mat.importAmount}" class="form-control input-sm" placeholder="入库数量"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="storeUnit">库存单位:</label>
			<div class="col-lg-7 col-md-8">
				<c:if test="${not empty mat }"><input type="text" id="storeUnit" name="storeUnit" readonly="readonly" value="${mat.storeUnit}" class="form-control input-sm" placeholder="库存单位"/></c:if>
				<c:if test="${empty mat }"><input type="text" id="storeUnit" name="storeUnit"  class="form-control input-sm" placeholder="库存单位"/></c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="periodValidity"><span style="color: red">*</span>截止有效期:</label>
			<div class="col-lg-7 col-md-8">
				<c:if test="${not empty mat }"><input class="form-control input-sm" id="periodValidity" name="periodValidity" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(((MasterImportDetail)request.getAttribute("mat")).getPeriodValidity()) %>"  type="text" placeholder="截止有效期"/></c:if>
				<c:if test="${empty mat }"><input class="form-control input-sm" id="periodValidity" name="periodValidity"   type="text" placeholder="截止有效期"/></c:if>
			</div>
			
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="supplierName">生产厂商:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="supplierName1" name="supplierName" value="${mat.supplierName}" class="form-control input-sm" placeholder="生产厂商"/>
				<input type="hidden" id="supplierCode1" name="supplierCode" value="${mat.supplierName}"/>
			</div>
		</div>
		<div class="form-group" id="classA" <c:if test="${mat.classCode eq 'B'}">hidden="true"</c:if>>
			<label class="col-lg-4 col-md-3  control-label" for=coefficientChange >拆零系数:</label>
			<div class="col-lg-7 col-md-8">
				<input  type="text" id="coefficientChange" name="coefficientChange" value="${mat.coefficientChange}" class="form-control input-sm" placeholder="拆零系数(默认为1)*入库单位=库存数量"/>
			</div>
		</div>
		<input id="createDate" name="createDate" value="${mat.createDate }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
			//选择开始时间触发
			$('#periodValidity').datepicker({
				format : 'yyyy-mm-dd',
				weekStart : 1
			}).on('changeDate', function(ev) {
				$('#periodValidity').datepicker('hide');
			});
			$('#nextMeasureDate').datepicker({
				format : 'yyyy-mm-dd',
				weekStart : 1
			}).on('changeDate', function(ev) {
				$('#nextMeasureDate').datepicker('hide');
			});
			//金额验证
			jQuery.validator.addMethod("decimalsValue",function(value, element) {
	                var decimalsValue =/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/ ;
	                return this.optional(element) || (decimalsValue.test(value));
	            }, "金额必须大于0并且只能精确到分");
			//入库单位校验
			jQuery.validator.addMethod("validateUnit",function(value, element) {
					var storeUnit=$("#storeUnit").val();//库存单位
					var importUnit=$("#importUnit").val();//入库单位
					var coefficientChange=$("#coefficientChange").val();//拆零系数
					//库存单位和入库单位不一致时,拆零系数必须填写
					if(storeUnit!=importUnit&&coefficientChange==""){
						return false;
					}else if(storeUnit==importUnit&&coefficientChange==""){
						$("#coefficientChange").val("1");//入库单位和库存单位一致时,将拆零系数置1
					}
	               return true;
	            }, "拆零系数必须填写");
			
			
			$("#import_form1").validate({
				rules: {
					materlName:{
						required:true
					},
					materlPrice:{
						decimalsValue:true
					},
					wholesalePrice:{
						required:true,
						decimalsValue:true
					},
					importAmount:{
						number:true
					},
					measurePeriod:{
						number:true
					},
					materlBatch:{
						required:true
					}
					,periodValidity:{
						required:true
					},coefficientChange:{
						validateUnit:true
					},importUnit:{
						required:true
					},
					importAmount:{
						required:true
					}
					},
				messages:{
					materlName:{
						required:'物资名称不能为空'
					},
					materlPrice:{
						decimalsValue:"金额必须大于0并且只能精确到分"
					},
					wholesalePrice:{
						decimalsValue:"金额必须大于0并且只能精确到分"
					},
					importAmount:{
						number:"请输入数字"
					},
					measurePeriod:{
						number:"请输入数字"
					},
					materlBatch:{
						required:"批次不能为空"
					}
					,periodValidity:{
						required:"有效期不能为空"
					},coefficientChange:{
						validateUnit:"入库和库存单位不一致时,必须填写"
					},importUnit:{
						required:"入库单位不能为空"
					},
					importAmount:{
						required:'入库数量不能为空'
					}
				}
			});
			
			//物资名称
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_INFO&cols=MATERLINFO_CODE|MATERL_NAME|MATERL_CODE|HELP_CODE|STORE_UNIT|SUPPLIER_CODE&where=[{%22column%22:%22CLASS_CODE%22,%22operate%22:%22=%22,%22value%22:%22A%22}]",function(data) { 
				$('#materlName').autocomplete(data,{
					minChars: 0,
					mustMatch:true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			        	if(item){
			        		 return '<table><tr><td width="130px;">' + item.MATERL_CODE + '</td><td>' + item.MATERL_NAME + '</td></tr></table>';	
			        	} 
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			        	if(item){
			        		 return item.HELP_CODE;
			        	}
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			        	if(item){
			        		 return item.MATERL_NAME;
			        	}
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#materlName').result(function(event, item){ 
					if(!item)return;
					$("#materlCode").val(item.MATERL_CODE);
					$("#materlinfoCode").val(item.MATERLINFO_CODE);
					//得到生产厂商的名字
					$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_SUPPLIER&cols=SUPPLIER_NAME|SUPPLIER_CODE|HELP_CODE&where=[{%22column%22:%22SUPPLIER_CODE%22,%22operate%22:%22=%22,%22value%22:%22"+item.SUPPLIER_CODE+"%22}]",function(data) {
						$("#supplierName1").val(data[0].SUPPLIER_NAME);
						$("#supplierCode1").val(data[0].SUPPLIER_CODE);
						$("#supplierName1").attr('readonly',true);
					});
					//STORE_UNIT得到库存单位 storeUnit
					$("#storeUnit").val(item.STORE_UNIT);
					//得到物资分类CLASS_CODE 物资需要拆零 设备需要显示计量信息 A耗材 B设备
					$("#importAmount").attr("readonly",false);
				}); 

			});
			
			
		});
	</script>
