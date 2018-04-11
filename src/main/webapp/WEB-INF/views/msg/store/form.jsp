<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="msgTmp_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="msgTmp"><span style="color: red">*</span>短信类别:</label>
			<div class="col-lg-7 col-md-8">
				<input type="hidden" id="msgTypeCode" name="msgTypeCode" value="${msgTmp.msgTypeCode }"   class="form-control input-sm">
				<input type="text" id="msgTypeName" placeholder="请输入选择短信分类" name="msgTypeName" value="${msgTmp.msgTypeName }"   class="form-control input-sm">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="msgTmpText"><span style="color: red">*</span>短信内容:</label>
			<div class="col-lg-7 col-md-8">
				<textarea rows="10" id="msgName" class="form-control" name="msgName" placeholder="请输入短信内容[最多输入300个字符]">${msgTmp.msgName}</textarea>
			</div>
		</div>
		<input type="hidden" name ="id" value="${msgTmp.id }"/>
	</form>
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	
<script type="text/javascript">
$(function(){
	$.getJSON("${ctx}/msg/msgStore/getMsgType",function(data) {
	    $('#msgTypeName').autocomplete(data,{
	        minChars: 0,
	        mustMatch:true, 
	        width:260,
	       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	        formatItem: function(item,i, max) {
	            return '<table><tr><td width="80px;">' + item.MSG_TYPE_CODE + '</td><td width="180px;">' + item.MSG_TYPE_NAME + '</td></tr></table>';
	            
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item,i, max) {
	            return item.MSG_TYPE_CODE+item.ID;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item) {
	            return item.MSG_TYPE_NAME;
	        }
	    }); 
	  //选中 某条记录 触发的事件
	    $('#msgTypeName').result(function(event, item){ 
			if(item){
				if(item.MSG_TYPE_CODE != $("#msgTypeCode").val()){
					$("#msgTypeCode").val(item.MSG_TYPE_CODE);
				}
			}else{
				$("#msgTypeCode").val("");
			}
	     });
	}); 
	$("#msgTmp_form").validate({
		rules: {
			msgTypeName:{
				required:true
			},
			msgName:{
				required:true,
				maxlength:300
			}
		},
		messages:{
			msgTypeName:{
				required:'请选择短信类别'
			},
			msgName:{
				required:'请输入短信内容',
				maxlength:'不能超过{0}个字符'
				
			}
		}
	});
});
</script>