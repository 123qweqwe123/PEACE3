<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="materlName"><span style="color: red">*</span>物资名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="materlName1" name="materlName" value="${materlInfo.materlName }" class="form-control input-sm hhh" placeholder="可输入物资名称简码" required>
			</div>
		</div>
		<input id="materlCode" name="materlCode" value="${materlInfo.materlCode }" type="hidden"/>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="storeUnit">库存单位:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="storeUnit" name="storeUnit" value="${materlInfo.storeUnit }" class="form-control input-sm" placeholder="库存单位" >
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="materlSpec">规格:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="materlSpec" name="materlSpec" value="${materlInfo.materlSpec }" class="form-control input-sm" placeholder="规格" >
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="supplierName"><span style="color: red">*</span>生产厂家:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="supplierName1" name="supplierName" value="${materlInfo.supplierName }" class="form-control input-sm" placeholder="可输入生产厂家简码" >
			</div>
		</div>
		<input id="supplierCode" name="supplierCode" value="${materlInfo.supplierCode }" type="hidden"/>

		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="className"><span style="color: red">*</span>物资类型名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="className1" name="className" value="${materlInfo.className }" class="form-control input-sm" placeholder="可输入物资类型名称简码" >
			</div>
		</div>
		<input id="classCode" name="classCode" value="${materlInfo.classCode }" type="hidden"/>
		
		<input id="materlInfoCode" name="materlInfoCode" value="${materlInfo.materlInfoCode }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
			$("#supplier_form").validate({
				rules: {
					materlName:{
						required:true
					},
					supplierName:{
						required:true
					},
					className:{
						required: true
					}
				},
				messages:{
					materlName:{
						required:'请输入物资名称！'
					},
					supplierName:{
						required:'请输入生产厂家'
					},
					className:{
						required:'请输入物资类型名称'
					}
				}
			});

			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_NAME&cols=MATERL_NAME|MATERL_CODE|HELP_CODE&where=",function(data) { 
				$('#materlName1').autocomplete(data,{
					mustMatch:true,
					minChars:0,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td>' + item.MATERL_NAME + '</td></tr></table>';
			        },

			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.MATERL_NAME;
			        }
				}); 

				//选中 某条记录 触发的事件
				$('#materlName1').result(function(event, item){ 
				       //$("#materlName1").val(item.MATERL_NAME);
				       if(item)
				    	   $("#materlCode").val(item.MATERL_CODE);
				}); 

			});
			
			$.getJSON('${ctx}/combox/comboxData?table=PIP_MMS_SUPPLIER&cols=SUPPLIER_NAME|SUPPLIER_CODE|HELP_CODE&where=[{"column":"STATUS","operate":"=","value":"1"}]',function(data) { 
				$('#supplierName1').autocomplete(data,{
					mustMatch:true,
					minChars:0,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td>' + item.SUPPLIER_NAME + '</td></tr></table>';
			        },

			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.SUPPLIER_NAME;
			        }
				}); 

				//选中 某条记录 触发的事件
				$('#supplierName1').result(function(event, item){ 
					if(item)
				       $("#supplierCode").val(item.SUPPLIER_CODE);
				}); 

			});
			
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_CLASS&cols=CLASS_NAME|CLASS_CODE|HELP_CODE&where=[{%22column%22:%22PROJECT_ID%22,%22operate%22:%22=%22,%22value%22:%22"+"<shiro:principal property="current_projectId"/>"+"%22}]",function(data) { 
				$('#className1').autocomplete(data,{
					mustMatch:true,
					minChars:0,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td>' + item.CLASS_NAME + '</td></tr></table>';
			        },

			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.CLASS_NAME;
			        }
				}); 

				//选中 某条记录 触发的事件
				$('#className1').result(function(event, item){
					if(item)
				       $("#classCode").val(item.CLASS_CODE);
				}); 

			});
		});
	</script>
