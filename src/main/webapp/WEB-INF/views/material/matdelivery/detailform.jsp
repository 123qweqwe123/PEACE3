<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,com.bdcor.pip.web.material.supp.domain.MasterImportDetail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="export_form1" class="form-horizontal">
		<div class="form-group" >
			<label class="col-lg-4 col-md-3  control-label" for="projectName">当前项目:</label>
			<div class="col-lg-7 col-md-8">
				${projectName }
				<c:if test="${not empty mat}"><input type="hidden" name="classCode" id="classCode" value="${mat.classCode}"></c:if>
				<c:if test="${empty mat}"><input type="hidden" name="classCode" id="classCode"></c:if>
			</div>
		</div>
		<input type="hidden" id="exorderNo" name="exorderNo" value="${exorderNo }"/>
		<input type="hidden" id="stockCode1" name="stockCode" value="${stockCode }"/>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="materlName"><span style="color: red">*</span>物资名称:</label>
			<div class="col-lg-7 col-md-8">
				<c:if test="${empty mat}"><input type="text" id="materlName" name="materlName"  class="form-control input-sm" placeholder="请选择已入库物资(名字/批次/出库单价/有效期  )"/></c:if>
				<c:if test="${not empty mat}"><input type="text" readonly="readonly" id="materlName" name="materlName" value="${mat.materlName }" class="form-control input-sm" /></c:if>
				<input type="hidden" id="materlinfoCode" name="materlinfoCode" value="${mat.materlinfoCode }" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="materlBatch"><span style="color: red">*</span>批次:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="materlBatch" readonly="readonly" name="materlBatch" value="${mat.materlBatch }" class="form-control input-sm" placeholder="批次"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="wholesalePrice"><span style="color: red">*</span>采购价:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="wholesalePrice" name="wholesalePrice" readonly="readonly" value="${mat.wholesalePrice }" class="form-control input-sm" placeholder="采购价"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="periodValidity"><span style="color: red">*</span>截止有效期:</label>
			<div class="col-lg-7 col-md-8">
				<input class="form-control input-sm" value="${mat.periodValidityStr }" readonly="readonly" id="periodValidity" name="periodValidity" type="text" placeholder="截止有效期"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="storeUnit">库存单位:</label>
			<div class="col-lg-7 col-md-8">
				<input readonly="readonly" type="text" value="${ms.storeUnit}" id="exportUnit" name="exportUnit"  class="form-control input-sm" placeholder="库存单位"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="storeAmount">库存数量:</label>
			<div class="col-lg-7 col-md-8">
				<input readonly="readonly" type="text" value="${ms.storeAmount }" id="storeAmount" name="storeAmount"  class="form-control input-sm" placeholder="库存数量"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="materlPrice">出库单价:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="materlPrice" name="materlPrice" value="${mat.materlPrice }" class="form-control input-sm" placeholder="出库单价"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="exportAmount"><span style="color: red">*</span>出库数量:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="exportAmount" name="exportAmount"  value="${mat.exportAmount}" class="form-control input-sm" placeholder="出库数量"/>
			</div>
		</div> 
		<input id="supplierCode" name="supplierCode" type="hidden">
		<input id="createDate" name="createDate" value="${mat.createDate }" type="hidden" />
		<input id="archivesNo" name="archivesNo" value="${mat.archivesNo }" type="hidden" />
		<input id="classCode" name="classCode" value="${mat.classCode }" type="hidden" />
		<input id="produceDate" name="produceDate"  type="hidden" />
		<input id="id" name="id" value="${mat.id }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
			//金额验证
			jQuery.validator.addMethod("decimalsValue",function(value, element) {
	                var decimalsValue =/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/ ;
	                return this.optional(element) || (decimalsValue.test(value));
	            }, "金额必须大于0并且只能精确到分");
			//入库单位校验
			jQuery.validator.addMethod("validateUnit",function(value, element) {
					var storeAmount=parseInt($("#storeAmount").val());//库存数量
					var exportAmount=parseInt($("#exportAmount").val());//出库数量
					if(exportAmount>storeAmount){
						return false;
					}
	               return true;
	            });
			
			
			$("#export_form1").validate({
				rules: {
					materlName:{
						required:true
					}
					,exportAmount:{
						required:true,
						number:true,
						min:1,
						validateUnit:true
					},
					materlPrice:{
						decimalsValue:true
					}
					},
				messages:{
					materlName:{
						required:'物资名称不能为空'
					},
					exportAmount:{
						required:"出库数量不能为空",
						number:"请输入数字",
						min:'数量不能小于1',
						validateUnit:'出库数量不能大于库存数量'
					},
					materlPrice:{
						decimalsValue:"金额必须大于0并且只能精确到分"
					}
				}
			});
			var stockCode=$("#stockCode1").val();
			//物资名称
			$.getJSON("${ctx}/material/matdelivery/comboxData?stockCode="+stockCode,function(data) { 
				$('#materlName').autocomplete(data,{
					minChars: 0,
					mustMatch:true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			        	if(item){
			        		 return '<table><tr><td width="80px;">' + item.materlName+'/</td><td width="50px;">'+item.materlBatch+
			        		 '/</td><td width="60px;">'+item.wholesalePrice+
			        		 '/</td><td width="50px;">' + item.periodValidityStr + '</td></tr></table>';	
			        	}
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			        	if(item){
			        		 return item.helpCode;
			        	}
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			        	if(item){
			        		 return item.materlName;
			        	}
			        }
				}); 
			});
			
			$('#materlName').result(function(event, item){ 
				$("#materlBatch").val(item.materlBatch);
				$("#wholesalePrice").val(item.wholesalePrice);
				$("#periodValidity").val(item.periodValidityStr);
				$("#exportUnit").val(item.storeUnit);
				$("#storeAmount").val(item.storeAmount);
				$("#supplierCode").val(item.supplierCode);
				$("#classCode").val(item.classCode);
				$("#materlinfoCode").val(item.materlinfoCode);
				$("#produceDate").val(item.produceDateStr);
				$("#materlPrice").val(item.materlPrice);
			}); 
		});
	</script>
