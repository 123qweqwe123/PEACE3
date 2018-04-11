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
			<label class="col-lg-4 col-md-3  control-label" for="storeUnit">库存单位:</label>
			<div class="col-lg-7 col-md-8">
				<c:if test="${not empty mat }"><input type="text" id="storeUnit" name="storeUnit" readonly="readonly" value="${mat.storeUnit}" class="form-control input-sm" placeholder="库存单位"/></c:if>
				<c:if test="${empty mat }"><input type="text" id="storeUnit" name="storeUnit"  class="form-control input-sm" placeholder="库存单位"/></c:if>
				<!-- 设备入库 数量默认1台 -->
				<input type="hidden" id="importAmount" name="importAmount"  value="1"/>
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
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="assetsStatus">设备状态:</label>
			<div class="col-lg-7 col-md-8">
				<select id="assetsStatus" name="assetsStatus" class="form-control input-sm">
					<option value="-1">请选择类别</option>
					<option value="1" <c:if test="${mat.assetsStatus=='1'}">selected="selected"</c:if>>可用</option>
					<option value="2" <c:if test="${mat.assetsStatus=='2'}">selected="selected"</c:if>>不可用</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="isMeasure">是否要计量:</label>
			<div class="col-lg-7 col-md-8">
			<select id="isMeasure" name="isMeasure" class="form-control input-sm">
					<option value="-1">请选择类别</option>
					<option value="1" <c:if test="${mat.isMeasure=='1'}">selected="selected"</c:if>>需要</option>
					<option value="2" <c:if test="${mat.isMeasure=='2'}">selected="selected"</c:if>>不需要</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="nextMeasureDate">下次计量时间:</label>
			<div class="col-lg-7 col-md-8">
			<c:if test="${mat.nextMeasureDate!=null }"><input type="text" id="nextMeasureDate" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(((MasterImportDetail)request.getAttribute("mat")).getNextMeasureDate()) %>" name="nextMeasureDate"  class="form-control input-sm" placeholder="下次计量时间"/></c:if>
			<c:if test="${mat.nextMeasureDate==null }"><input type="text" id="nextMeasureDate" name="nextMeasureDate"  class="form-control input-sm" placeholder="下次计量时间"/></c:if>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="measurePeriod">设备计量单位:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="measurePeriod" name="measurePeriod" value="${mat.measurePeriod}" class="form-control input-sm" placeholder="设备计量单位"/>
			</div>
		</div>
		<input id="classCode" name=classCode value="${mat.classCode }" type="hidden" />
		<input id="createDate" name="createDate" value="${mat.createDate }" type="hidden" />
		<input id="archivesNo" name="archivesNo" value="${mat.archivesNo }" type="hidden" />
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
					}
				}
			});
			
			//物资名称
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_INFO&cols=MATERLINFO_CODE|MATERL_NAME|MATERL_CODE|HELP_CODE|STORE_UNIT|CLASS_CODE|SUPPLIER_CODE&where=[{%22column%22:%22CLASS_CODE%22,%22operate%22:%22=%22,%22value%22:%22B%22}]",function(data) { 
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
					//得到物资分类CLASS_CODE 物资需要拆零 设备需要显示计量信息 A物资 B设备
					var classCode=item.CLASS_CODE;
					$("#classCode").val(classCode);
					//设备数量只能是一台
					$("#importAmount").val(1);
					$("#importAmount").attr("readonly",true);
				}); 

			});
			
			
		});
	</script>
