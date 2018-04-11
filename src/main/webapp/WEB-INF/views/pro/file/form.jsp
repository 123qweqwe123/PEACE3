<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
		<input id="id" name="id" value="${pipCommEventDictfile.id }" type="hidden"/>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label"><span style="color: red">*</span>事件分类:</label>
			<div class="col-lg-7 col-md-8">
				<input type="hidden" id="eventDictCode1" name="eventDictCode"  value="${pipCommEventDictfile.eventDictCode }" class="form-control input-sm" placeholder="选择事件分类"/>    
                <input type="text" id="eventName1" name="eventName"  value="${pipCommEventDictfile.eventName }"  class="form-control input-sm" placeholder="输入事件分类编码或者名称">
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="materlSpec"><span style="color: red">*</span>收集材料:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="fileInfo" name="fileInfo" value="${pipCommEventDictfile.fileInfo }" class="form-control input-sm" placeholder="收集材料" >
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="mobile">是否在用</label>
			<div class="col-lg-7 col-md-8">
				<select id="isUsed" name="isUsed">
					<option value="1" <c:if test="${pipCommEventDictfile.isUsed=='1' }"> selected='selected' </c:if>>是</option>
					<option value="0" <c:if test="${pipCommEventDictfile.isUsed=='0' }"> selected='selected' </c:if>>否</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="mobile">备注</label>
			<div class="col-lg-7 col-md-8">
				<textarea rows="3" id="remark" class="form-control" name="remark" placeholder="备注">${pipCommEventDictfile.remark}</textarea>
			</div>
		</div>
	</form>
    
	<script type="text/javascript">
		$(function(){
			//事件分类 start
			$.getJSON("${ctx}/combox/eventInfo?dictCode=HIS.003",function(data) { 
			    $('#eventName1').autocomplete(data,{
			        minChars: 0,
			        mustMatch:true, 
			        width:260,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="80px;">' + item.VCODE + '</td><td width="180px;">' + item.VNAME + '</td></tr></table>';
			            
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.VCODE+item.VNAME+item.HELPCODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.VNAME;
			        }
			    }); 
			  //选中 某条记录 触发的事件
			    $('#eventName1').result(function(event, item){ 
					if(item){
						if(item.STOCKCODE != $("#eventDictCode1").val()){
							$("#eventDictCode1").val(item.VCODE);
						}
					}else{
						$("#eventDictCode1").val("");
					}
			     });
			});
			//事件分类end
			
			$("#supplier_form").validate({
				rules: {
					eventName:{
						required:true
					},
					fileInfo:{
						required:true
					}
				},
				messages:{
					eventName:{
						required:'选择事件分类！'
					},
					fileInfo:{
						required:'请填写收集材料'
					}
				}
			});


		});
	</script>
