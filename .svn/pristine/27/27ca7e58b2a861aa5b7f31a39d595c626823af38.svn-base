<%@page import="com.bdcor.pip.client.program.transfer.ProgramMsg"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="com.bdcor.pip.client.ClientInfo"%>
<%@page import="com.bdcor.pip.client.file.log.loghandler.c.*" %>
<%@page import="com.bdcor.pip.client.file.log.logfile.*,com.bdcor.pip.client.program.transfer.ProgramMsg" %>
<%@page import="com.bdcor.pip.client.*,com.bdcor.pip.client.program.*" %>


 
<% 
	String webContextName = request.getContextPath();
	request.setAttribute("ctx", webContextName);
	
	//更新程序版本后，是否重启
	boolean programIsUpdate = false;
	
	/*
	//VersionLog provLog = CFileLogHandler.getLog(VersionLog.PROCE);
	if(!provLog.isHandler()){
		programIsUpdate = true;
	}
	*/
	
	if(ProgramInfo.isUpdateProgram){
		programIsUpdate = true;
	}
%>
<script type="text/javascript" src="${ctx}/static/js/jquery.1.10.2.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap.js"></script>
<script type="text/javascript" src="${ctx}/static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.simplePagination.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.alerts.js"></script>
<script type="text/javascript" src="${ctx}/static/datepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/static/datepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/static/datepicker/js/dateformater.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.qtip.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/topanv.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.base64.js"></script>

<script type="text/javascript" src="${ctx}/static/pinyin/MooTools-Core-1.5.1.js"></script>
<script type="text/javascript" src="${ctx}/static/pinyin/pinyin.js"></script>
<script type="text/javascript" src="${ctx}/static/dat/js/dat.js"></script>
<script type="text/javascript">
String.prototype.trim = function() {
    if(this == null || this == undefined){
    	return this;
    }
    return this.replace(/^\s+/g,"").replace(/\s+$/g,"");
}

var lcc = "<%=ClientInfo.LCC_NO %>";

function stop(){ return false; } 
document.oncontextmenu=stop; 
</script>


<link rel="stylesheet" href="${ctx}/static/css/bootstrap.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/bootstrap-theme.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/main-style.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/simplePagination.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/jquery.alerts.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/jquery.qtip.min.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/css/jquery.autocomplete.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/datepicker/css/bootstrap-datetimepicker.css" type="text/css" />

<jsp:include page="/common/component.jsp"></jsp:include>

<script type="text/javascript">


$(function(){
	if(<%=programIsUpdate %>){
		handleProgramUpdate("<%=ProgramMsg.updateVersionMsg((ProgramInfo.info.programRecord()==null?"0":ProgramInfo.info.programRecord().getVersion())) %>");
	}
	
	if(<%=SysVersions.isNeedTip() %>){
		handleProgramUpdate("<%=SysVersions.testSystemTip() %>");
	}
	
	var intervCodeObj2 = new intervCode();
	intervCodeObj2.setIsRistCode(true);
	
	var inputIntervCode = $("#code");
	if(inputIntervCode.length>0){
		$("#code").keydown(function(e){
			intervCodeObj2.checkCode(e);
		});
	}
});


function handleProgramUpdate(msg){
	popWinWithHtmlInfo(msg,'提示',1000,1000);
	$('.close').hide();
	$('#dialog_detail').unbind();
	
	
}
</script>

