<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<div id="alertForUpload" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
    <form id="examine_form" class="form-horizontal">
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="spec">请选择考核表:</label>
			<div class="col-lg-7 col-md-8">
				<div id="fileQueue"></div> 
				<input type="file" id="uploadify" name="file"  class="form-control">
			</div>
		</div>
		<input id="id" name="id" value="${id }" type="hidden" /> 
		<input id="lccCode" name="lccCode" value="${lccCode }" type="hidden"/>
	</form>
	
<script type="text/javascript">
$(function(){
	
	$("#uploadify").uploadify({
		debug			: false, 

		swf 			: '${ctx}/static/uploadify/uploadify.swf',
		method			: 'POST',	
		uploader		: '${ctx}/quality/examine/uploadExamine', 
		//auto			: false,
		preventCaching	: true,		

		buttonCursor	: 'hand',	
	//	buttonImage		: 'img/.....png',	
		buttonText		: '上  传  考  核  表'	, 
		height			: 30	, 
		width			: 320	, 

		fileObjName		: 'file',	
		fileSizeLimit	: 10000,	//10M	
		fileTypeDesc	: 'any'	,	
		fileTypeExts	: '',		
		formData		: {'id':'${id}', 'lccCode':$("#lccCode").val()} , 
		
		multi			: true ,	
		progressData	: 'speed',		
		queueID			: 'fileQueue',
		queueSizeLimit	: 10,	
		removeCompleted : false,	
		removeTimeout	: 10,	
		requeueErrors	: true,	
		uploadLimit		: 20,	

		successTimeout	: 30,
		overrideEvents : ['onSelectError', 'onUploadError'],
		onSelectError  : function(file, errorCode, errorMsg){
	        var msgText = "上传失败\n";  
	        switch (errorCode) {  
	            case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:  
	                msgText += "每次最多上传 " + this.settings.queueSizeLimit + "个文件";  
	                break;  
	            case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:  
	                msgText += "文件大小超过限制( " + (this.settings.fileSizeLimit / 1000)M + " )";  
	                break;  
	            case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE:  
	                msgText += "文件大小为0";  
	                break;  
	            //case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:  
	            //    msgText += "文件格式不正确，仅限 " + this.settings.fileTypeExts;  
	            //    break;  
	            default:  
	                msgText += "错误代码:" + errorCode + "\n" + errorMsg;
	        }  
	        openErrorForUpload(msgText,2000);
		},
		
		onCancel : function(file) { },
		
		onDialogClose : function (queueData) {},
		
		onDialogOpen : function () {  },
	
		onFallback : function(){ },
		
		onUploadError : function(file, errorCode, errorMsg, errorString){
			if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED  
	                || errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {  
	            return;  
	        }  
	        var msgText = "上传失败\n";  
	        switch (errorCode) {  
	            case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:  
	                msgText += "HTTP 错误\n" + errorMsg;  
	                break;  
	            case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:  
	                msgText += "上传文件丢失，请重新上传";  
	                break;  
	            case SWFUpload.UPLOAD_ERROR.IO_ERROR:  
	                msgText += "IO错误";  
	                break;  
	            case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:  
	                msgText += "安全性错误\n" + errorMsg;  
	                break;  
	            case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:  
	                msgText += "每次最多上传 " + this.settings.uploadLimit + "个";  
	                break;  
	            case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:  
	                msgText += errorMsg;  
	                break;  
	            case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:  
	                msgText += "找不到指定文件，请重新操作";  
	                break;  
	            case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:  
	                msgText += "参数错误";  
	                break;  
	            default:  
	                msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n"  
	                        + errorMsg + "\n" + errorString;  
	        }
	        openErrorForUpload(msgText,2000);
		},
	    onUploadStart : function(file){
	    	var myself = this; 
   			$.post("${ctx}/quality/examine/checkFileNameExists",
   					{id:'${id}', fileName:file.name},
   					function(result){
	    				if(result){
			            	myself.uploadifyCancel(file.id);
			            	openErrorForUpload("请修改文件名，重新上传！",2000);
	    				}
   					},
   			'json');
	    },
        onUploadSuccess : function(file, data, response) {
        	var cancel=$("#"+file.id + " .cancel a");
            if (cancel) {
	            cancel.on('click',function () {
	            	$(this).hide();
	    			$.post("${ctx}/quality/examine/deleteFile",
	    					{examineId:$("#id").val(), fileName:file.name},
	    					function(result){
			    				if(result.success){
			    					
			    				}
	    					},
	    			'json');
	            });
            } 
        }

	});
});
function openErrorForUpload(message,delay){
	$('#alertForUpload').show().find('strong').text(message);
	window.setTimeout(function() {
		$('#alertForUpload').slideUp("slow");
	}, delay);
}
</script>