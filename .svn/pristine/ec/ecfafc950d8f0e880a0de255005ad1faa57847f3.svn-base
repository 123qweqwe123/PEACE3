<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bdcor.pip.core.utils.Securitys"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="import_form" class="form-horizontal">
		<div class="form-group" >
			<label class="col-lg-4 col-md-3  control-label" for="projectName">当前项目:</label>
			<div class="col-lg-7 col-md-8">
				${projectName }
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="inorderNo"><span style="color: red">*</span>入库单号:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="imorderNo" name="imorderNo" value="${mat.imorderNo }" class="form-control input-sm" placeholder="入库单号"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="activeclassCode"><span style="color: red">*</span>入库类别:</label>
			<div class="col-lg-7 col-md-8">
				<select id="activeclassCode" name="activeclassCode" class="form-control input-sm">
					<option value="-1">请选择类别</option>
					<c:forEach items="${matList }" var="m">
						<option value="${m.activeclassCode }" ${mat.activeclassCode == m.activeclassCode ? 'selected="selected"' : '' }>${m.activeclassName }</option>
					</c:forEach>
				</select>
				
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccCode">医院名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text"  value="<%=Securitys.getUser().getLccName() %>" class="form-control input-sm" readonly="readonly"/>
				<input type="hidden"  name="lccCode" value="<%=Securitys.getUser().getLccCode() %>"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="userName">入库员名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="userName" name="userName" value="${mat.userName }" class="form-control input-sm" placeholder="选择已有用户 输入名字首字母检索"/>
				<input type="hidden" id="userCode" name="userCode" value="${mat.userCode }" class="form-control input-sm" placeholder="入库员编码"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="stockCode"><span style="color: red">*</span>入到的库房:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="stockName" name="stockName" value="${mat.stockName}" class="form-control input-sm" placeholder="选择已有库房 输入名字首字母检索"/>
				<input id="stockCode" name="stockCode" value="${mat.stockCode }"  type="hidden"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="supplierCode">供货商:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="supplierName" name="supplierName" value="${mat.supplierName}" class="form-control input-sm" placeholder="供货商"/>
			</div>
			<input id="supplierCode" name="supplierCode" value="${mat.supplierCode }" type="hidden"/>
		</div>
		<input id="createDate" name="createDate" value="${mat.createDate }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
			
			$("#import_form").validate({
				rules: {
					<c:if test="${empty mat}">
					imorderNo:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/material/warehouse/checkimorderNoExists',
							dataType:'json',
							data:{
								imorderNo:function(){
									var l_n = $("#imorderNo").val();
									return l_n;
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
					</c:if>
					activeclassCode:{
						required:true,
						min:1
					},
					stockName:{
						required:true
					}
				},
				messages:{
					<c:if test="${empty mat}">
					imorderNo:{
						required:'入库单号不能为空',
						remote:'入库单号已存在'
					},
					</c:if>
					activeclassCode:{
						min:"类别不能为空"
					},
					stockName:{
						required:"库房名字不能为空"
					}
				}
			});
			<c:if test="${not empty mat}">
			$("#imorderNo").attr('readonly',true);
			</c:if>
			//供货商
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_SUPPLIER&cols=SUPPLIER_NAME|SUPPLIER_CODE|HELP_CODE&where=",function(data) { 
				$('#supplierName').autocomplete(data,{
					minChars: 0,
					mustMatch :true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="130px;">' + item.SUPPLIER_CODE + '</td><td>' + item.SUPPLIER_NAME + '</td></tr></table>';
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
				$('#supplierName').result(function(event, item){ 
					if(item)
				       $("#supplierCode").val(item.SUPPLIER_CODE);
				}); 

			});
			
			//库房
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_STOREHOUSE&cols=STOCK_NAME|STOCK_CODE|HELP_CODE&where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22=%22,%22value%22:%22"+<shiro:principal property="lccCode" />+"%22}]",function(data) { 
				$('#stockName').autocomplete(data,{
					minChars: 0,
					mustMatch:true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="130px;">' + item.STOCK_CODE + '</td><td>' + item.STOCK_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.STOCK_NAME;
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#stockName').result(function(event, item){
					if(item)
				     $("#stockCode").val(item.STOCK_CODE);
				}); 
			});
			
			//入库员
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_USER&cols=USER_CODE|USER_NAME|HELP_CODE&where=",function(data) { 
				$('#userName').autocomplete(data,{
					minChars: 0,
					mustMatch:true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="130px;">' + item.USER_CODE + '</td><td>' + item.USER_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.USER_NAME;
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#userName').result(function(event, item){
					if(item){
						$("#userCode").val(item.USER_CODE);
					}
				}); 
			});
		});
	</script>
