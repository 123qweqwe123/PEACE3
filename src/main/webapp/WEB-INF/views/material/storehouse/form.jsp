<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="dict" uri="http://com.bdcor.pip.web.common.tagsupport" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="stockName"><span style="color: red">*</span>库房名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="stockName1" name="stockName" value="${materStorehouse.stockName }" class="form-control input-sm" placeholder="库房名称" required>
			</div>
			<shiro:hasPermission name="permissionmgt:administrator">
			<label class="col-lg-3 col-md-1  control-label" for="lccName"><span style="color: red">*</span>协作医院名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="lccName"  class="form-control input-sm" placeholder="输入单位简拼或LCCID" required>
				<input type="hidden" id="lccCode"  name="lccCode">
			</div>
			</shiro:hasPermission>
		</div>

		<input id="stockCode" name="stockCode" value="${materStorehouse.stockCode }" type="hidden" />
	</form>
    
	<script type="text/javascript">
		$(function(){
			
			$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) { 
			    $('#lccName').autocomplete(data,{
			        minChars: 0,
			        mustMatch:true, 
			        width:260,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="180px;">' + item.lccName + '</td></tr></table>';
			            
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.helpCode+item.lccCode;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.lccName;
			        }
			    }); 
			  //选中 某条记录 触发的事件
			    $('#lccName').result(function(event, item){ 
					if(item){
						if(item.lccCode != $("#lccCode").val()){
							$("#lccCode").val(item.lccCode);
						}
					}else{
						$("#lccCode").val("");
					}
			     }); 
			});
			
			
			$("#supplier_form").validate({
				rules: {
					<c:if test="${empty materStorehouse}">
					stockName:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/material/storehouse/checkNameExists',
							dataType:'json',
							data:{
								stockName:function(){
									return $("#stockName1").val();
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
						maxlength:120
					}
					</c:if>
				},
				messages:{
					<c:if test="${empty materStorehouse}">
					stockName:{
						required:'请输入库房名称！',
						remote:'库房名称已经存在！',
						max:'最大长度不能超过120个字符！'
					}
				</c:if>
				}
			});
			
			<shiro:hasPermission name="permissionmgt:administrator">
			//协作医院名称
			$.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_LCC&cols=LCC_CODE|LCC_NAME&where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22!=%22,%22value%22:%22"+<shiro:principal property="lccCode"/>+"%22}]",function(data) { 
				$('#lccName').autocomplete(data,{
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
				
				$('#lccName').result(function(event, item){
					$("#lccCode").val(item.LCC_CODE);
				});
			});
			</shiro:hasPermission>
		});
	</script>
