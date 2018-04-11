<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,com.bdcor.pip.web.material.supp.domain.MaterExport"%>
<%@ page import="com.bdcor.pip.core.utils.Securitys"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="export_form" class="form-horizontal">
		<div class="form-group" >
			<label class="col-lg-4 col-md-3  control-label" for="projectName">当前项目:</label>
			<div class="col-lg-7 col-md-8">
				${projectName }
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="inorderNo"><span style="color: red">*</span>出库单号:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="exorderNo" name="exorderNo" value="${mat.exorderNo }" class="form-control input-sm" placeholder="出库单号"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="activeclassCode"><span style="color: red">*</span>出库类别:</label>
			<div class="col-lg-7 col-md-8">
				<select id="activeclassCode" name="activeclassCode" class="form-control input-sm">
					<c:forEach items="${matList }" var="m">
						<option value="${m.activeclassCode }" ${mat.activeclassCode == m.activeclassCode ? 'selected="selected"' : '' }>${m.activeclassName }</option>
					</c:forEach>
				</select>
				
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="exportDate">出库日期:</label>
			<div class="col-lg-7 col-md-8">
				<c:if test="${mat.exportDate!=null }"><input type="text" id="exportDate" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(((MaterExport)request.getAttribute("mat")).getExportDate()) %>" name="exportDate"  class="form-control input-sm" placeholder="出库日期"/></c:if>
				<c:if test="${mat.exportDate==null }"><input type="text" id="exportDate" name="exportDate"  class="form-control input-sm" placeholder="出库日期"/></c:if>
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
			<label class="col-lg-4 col-md-3  control-label" for="userName">出库员名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="userName" name="userName" value="${mat.userName }" class="form-control input-sm" placeholder="选择已有用户 输入名字首字母检索"/>
				<input type="hidden" id="userCode" name="userCode" value="${mat.userCode }" class="form-control input-sm" placeholder="入库员编码"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="exportLccName"><span style="color: red">*</span>出到的单位:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="exportLccName" name="exportLccName" value="${mat.exportLccName}" class="form-control input-sm" placeholder="选择已有单位 输入 LCCID检索"/>
				<input id="exportLccCode" name="exportLccCode" value="${mat.exportLccCode }"  type="hidden"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="exportStockName"><span style="color: red">*</span>出到的库房:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="exportStockName" name="exportStockName" value="${mat.exportStockName}" class="form-control input-sm" placeholder="选择已有库房 输入名字首字母检索"/>
				<input id="exportStockCode" name="exportStockCode" value="${mat.exportStockCode }"  type="hidden"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="stockCode"><span style="color: red">*</span>来源库房:</label>
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
			//选择开始时间触发
			$('#exportDate').datepicker({
				format : 'yyyy-mm-dd',
				weekStart : 1
			}).on('changeDate', function(ev) {
				$('#exportDate').datepicker('hide');
			});
			$.validator.addMethod("notEqual", function(value, element) { 
				var stockName=$("#stockName").val();
				var exportStockName=$("#exportStockName").val();
				return stockName!=exportStockName;
				}, "值不能相同"); 
			$("#export_form").validate({
				rules: {
					<c:if test="${empty mat}">
					exorderNo:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/material/matdelivery/checkexorderNoExists',
							dataType:'json',
							data:{
								exorderNo:function(){
									var l_n = $.trim($("#exorderNo").val());
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
						},
					},
					</c:if>
					exportLccName:{
						required:true
					},
					activeclassCode:{
						required:true,
						min:1
					},
					stockName:{
						required:true,
						notEqual:true
					},
					exportStockName:{
						required:true
					},
					exportDate:{
						required:true
					}
				},
				messages:{
					<c:if test="${empty mat}">
					exorderNo:{
						required:'出库单号不能为空',
						remote:'出库单号已存在'
					},
					</c:if>
					exportLccName:{
						required:'出到的单位不能为空'
					},
					activeclassCode:{
						min:"类别不能为空"
					},
					stockName:{
						required:"库房名字不能为空",
						notEqual:'来源库房和出到的库房不能相同'
					},
					exportStockName:{
						required:"库房名字不能为空"
					},
					exportDate:{
						required:"出库日期不能为空"
					}
				}
			});
			<c:if test="${not empty mat}">
			$("#exorderNo").attr('readonly',true);
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
			
			
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_STOREHOUSE&cols=STOCK_NAME|STOCK_CODE|HELP_CODE&where=",function(data) { 
				$('#stockName').autocomplete(data,{//来源库房
					minChars: 0,
					mustMatch:true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item1,i, max) {
			            return '<table><tr><td width="130px;">' + item1.STOCK_CODE + '</td><td>' + item1.STOCK_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item1,i, max) {
			            return item1.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item1) {
			            return item1.STOCK_NAME;
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#stockName').result(function(event, item1){
					if(item1)
				     $("#stockCode").val(item1.STOCK_CODE);
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
			
			//出到的单位
			$.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_LCC&cols=LCC_CODE|LCC_NAME&where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22!=%22,%22value%22:%22"+<shiro:principal property="lccCode"/>+"%22}]",function(data) { 
				$('#exportLccName').autocomplete(data,{
					minChars: 0,
					mustMatch:true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="130px;">' + item.LCC_CODE + '</td><td>' + item.LCC_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.LCC_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.LCC_NAME;
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#exportLccName').result(function(event, item){
					if(item){
						$("#exportStockName").val('');
						$("#exportStockCode").val('');
						$("#exportLccCode").val(item.LCC_CODE);
						//出到的库房
						$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_STOREHOUSE&cols=STOCK_NAME|STOCK_CODE|HELP_CODE&where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22=%22,%22value%22:%22"+item.LCC_CODE+"%22}]",function(data) { 
							if ( $('#exportStockName').unautocomplete ){
								$('#exportStockName').unautocomplete();
							}
							$('#exportStockName').autocomplete(data,{//收货库房
							minChars: 0,
							mustMatch:true,
				      		// 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
				      		formatItem: function(item2,i, max) {
				            return '<table><tr><td width="130px;">' + item2.STOCK_CODE + '</td><td>' + item2.STOCK_NAME + '</td></tr></table>';
				        },
				        // 指定 与 输入文字匹配的字段名
				        formatMatch: function(item2,i, max) {
				            return item2.HELP_CODE;
				        },
				        // 选中 某条记录在输入框里 显示的数据字段
				        formatResult: function(item2) {
				            return item2.STOCK_NAME;
				        }
					}); 
					//选中 某条记录 触发的事件
					$('#exportStockName').result(function(event, item2){
						if(item2){
							$("#exportStockName").val(item2.STOCK_NAME);
							$("#exportStockCode").val(item2.STOCK_CODE);
						}
					}); 
				});		
						
					}
				}); 
				
			});
		});
	</script>
