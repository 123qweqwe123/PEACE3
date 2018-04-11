<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<style>
<!--
.fileload {
    border-radius: 3px;
    font-size: 12px;
    height: 30px;
    line-height: 1.5;
    padding: 5px 10px;
    width: 81%;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    box-shadow: 0 1px 1px rgba(0, 0, 0, 0.075) inset;
    color: #555;
    
}
-->
</style>
    <div id="alertForUpload" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
    <form id="adjunctForm" method="POST" enctype="multipart/form-data" class="form-horizontal">
		<!-- 
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="type"><span style="color: red">*</span>文件类别:</label>
			<div class="col-lg-7 col-md-8">
				<select id="type" name="type" class="form-control input-sm">
					<option value="-1">请选择类别</option>
					<c:forEach items="${typeList }" var="type">
						<option value="${type.typeCode }">${type.typeName }</option>
					</c:forEach>
				</select>
			</div>
		</div>
		
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="imorderNo"><span style="color: red">*</span>订单号:</label>
			<div class="col-lg-7 col-md-8">
				<input id="imorderNo" name="imorderNo" class="form-control input-sm" placeholder="订单号"/>
			</div>
		</div>
		 -->
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="materlName"><span style="color: red">*</span>选择文件:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" name="fileName" id="puf" class="fileload" placeholder="请点击浏览选择文件"/>
				<input type="button" value="浏览..." style="height:30px;" onclick="javascript:$('#file').click();" />
				<input type="file" name="file" id="file" style="display:none" onchange="javascript:$('#puf').val($('#file').val());" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label"><span style="color: red">*</span>请选择存放库房:</label>
			<div class="col-lg-7 col-md-8">
				<input type="hidden" id="stockCode" name="stockCode">
				<input type="text" id="stockName" name="stockName"   class="form-control input-sm" placeholder="输入存放库房名称或者编码">
			</div>
		</div>
		<div class="form-group" id="loading" hidden>
			<label class="col-lg-5 col-md-1  control-label" for="materlName">&nbsp;</label>
			<div class="col-lg-7 col-md-5">
				<img src="${ctx }/static/file-upload/img/loading.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/> 
			</div>
		</div>
		
	</form>
	
<script type="text/javascript">

	$(function(){
		//当前用户的库房
		$.getJSON("${ctx}/combox/stockInfo",function(data) { 
		    $('#stockName').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.STOCKCODE + '</td><td width="180px;">' + item.STOCKNAME + '</td></tr></table>';
		            
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.STOCKCODE+item.STOCKNAME;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.STOCKNAME;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#stockName').result(function(event, item){ 
				if(item){
					if(item.STOCKCODE != $("#stockCode").val()){
						$("#stockCode").val(item.STOCKCODE);
					}
				}else{
					$("#stockCode").val("");
				}
		     });
		});
		$("#adjunctForm").validate({
			rules: {
				file:{
					required:true
				},
				stockCode:{
					required:true
				}
			},
			messages:{
				
				file:{
					required:'请选择上传excle文件！',
					maxlength:'文件名称最大长度不能超过400个字符'
				},stockCode:{
					required:"请选择库房"
				}
			
			}
		});
	});
</script>