<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<div id="edit_message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="edit_messageSpanId"></span>
	</div>
	<form id="sendForm" class="form-horizontal" autocomplete="off">
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="edit_lccName"><span style="color: red">*</span>LCCID:</label>
            <div class="col-lg-5 col-md-5">
                <input id="edit_lccCode" type="hidden" value="${sendVo.lccId}"/>
				<input id="edit_lccName" name="edit_lccName" value="${sendVo.lccName}"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="edit_patientName"><span style="color: red">*</span>患者姓名:</label>
            <div class="col-lg-5 col-md-5">
                <input id="edit_patientId" type="hidden" value="${sendVo.patientId}"/>
				<input id="edit_patientName" name="edit_patientName" value="${sendVo.patientName}"  class="form-control input-sm"  placeholder="请输入PID"/>
            </div>
        </div>
        
        <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="edit_typeCode"><span style="color: red">*</span>短信类型:</label>
            <div class="col-lg-5 col-md-5">
                <select id="edit_typeCode" name="edit_typeCode" class="form-control input-sm" onChange="changeTypeCode()">
                	<option value="">全部</option>
                	<c:forEach items="${msgTypeList}" var="t">
                		<option value="${t.MSG_TYPE_CODE}" <c:if test="${t.MSG_TYPE_CODE==sendVo.typeCode}">selected</c:if>>${t.MSG_TYPE_NAME}</option>
                	</c:forEach>
                </select>
            </div>
        </div>
        
         <div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="edit_msgId"><span style="color: red">*</span>默认短信内容:</label>
            <div class="col-lg-5 col-md-5">
                <select id="edit_msgId" name="edit_msgId" class="form-control input-sm" onChange="changeMsgId()">
                	<option value=""></option>
                </select>
            </div>
        </div>
        
        <div class="form-group">
			<label class="col-lg-3 col-md-3  control-label" for="edit_msgName"><span style="color: red">*</span>短信内容:</label>
			<div class="col-lg-5 col-md-5">
				<textarea rows="4" cols="50" name="edit_msgName" id="edit_msgName">${sendVo.msgName}</textarea>
			</div>
		</div>
		
		<div class="form-group">
            <label class="col-lg-3 col-md-3  control-label" for="edit_preSendTime"><span style="color: red">*</span>发送时间:</label>
            <div class="col-lg-5 col-md-5">
				<input id="edit_preSendTime" name="edit_preSendTime" value="<fmt:formatDate value='${sendVo.preSendTime}' pattern='yyyy-MM-dd HH:mm:ss' />"  class="form-control input-sm"  placeholder="请选择时间" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',minDate:'%y-%M-%d %h:%m:%s',readOnly:true})"/>
            </div>
        </div>
        
        <%--<div class="form-group">--%>
        	<%--<center>--%>
        		<%--<button type="button" id ='edit_save' class="btn btn-default btn-sm" tabindex="1000">提交</button>--%>
            	<%--<button type="button" id ='edit_cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>--%>
            <%--</center>--%>
        <%--</div>--%>
        <div class="modal-footer">
            <button type="button" id ='edit_cancel' class="btn btn-default btn-sm" tabindex="1002">取消</button>
            <button type="button" id ='edit_save' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
        </div>
	</form>
<input id="trial_month" type="hidden" value=""/>

	<script>
		function initPatient(){
			$('#edit_patientName').unautocomplete();
			$("#edit_patientId").val("");
			$('#edit_patientName').val("");
			$.getJSON("${ctx}/msg/send/getPatient?lccCode="+$("#edit_lccCode").val(),function(data) { 
			    $('#edit_patientName').autocomplete(data,{
			        minChars: 0,
			        mustMatch:true, 
			        width:260,
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="80px;">' + item.PATIENT_ID + '</td><td width="180px;">' + item.PATIENT_NAME + '</td></tr></table>';
			            
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.PATIENT_ID+item.PATIENT_NAME;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.PATIENT_NAME;
			        }
			    }); 
			  //选中 某条记录 触发的事件
			    $('#edit_patientName').result(function(event, item){ debugger
					if(item){
						if(item.PATIENT_ID != $("#edit_patientId").val()){
							$("#edit_patientId").val(item.PATIENT_ID);
                            $("#trial_month").val(item.TRIAL_MONTH);
						}
					}else{
						$("#edit_patientId").val("");
                        $("#trial_month").val("");
					}
			     }); 
			});
		}
		
		function changeTypeCode(){
			jQuery("#edit_msgId").empty();
			jQuery("#edit_msgId").append("<option value=''></option>");
			if($.trim($("#edit_typeCode").val())=="")return;
			$.post("${ctx}/msg/send/getMsgListByType?typeCode="+$.trim($("#edit_typeCode").val()),null,function(result){
				if(result && result !='undefinded' &&result.success==true){
        	        $.each(result.data,function(idx,item){
        	            var s = '';
        	            if("${sendVo.msgId}"==item.ID){
        	              s = 'selected';
        	            }
        	            jQuery("#edit_msgId").append("<option style='width:200px;' value='"+item.ID+"' "+s+">"+item.MSG_NAME+" </option>"); 
       	            });
				}
			},'json');
		}
		
		function changeMsgId(){
			$("#edit_msgName").val("");
			if($("#edit_msgId").val()=="")return;
            var msgText = $("#edit_msgId option:selected").text().replace("patient_nameX",$("#edit_patientName").val());
            msgText = msgText.replace("trial_month",$("#trial_month").val());
			$("#edit_msgName").val(msgText);
		}
	
		$(function(){
			$("#edit_cancel").click(function(){
				$('#dialog-confirm').modal('hide');
			});	
			
			$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) { 
			    $('#edit_lccName').autocomplete(data,{
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
			    $('#edit_lccName').result(function(event, item){ 
					if(item){
						if(item.lccCode != $("#edit_lccCode").val()){
							$("#edit_lccCode").val(item.lccCode);
							initPatient();
						}
					}else{
						$("#edit_lccCode").val("");
						initPatient();
					}
			     }); 
			});
			
			$("#sendForm").validate({
				rules: {
					edit_lccName:{
						required:true
					},
					edit_patientName:{
						required:true
					},
					edit_typeCode:{
						required:true
					},
					edit_msgId:{
						required:true
					},
					edit_msgName:{
						required:true
					},
					edit_preSendTime:{
						required:true
					}
				},
				messages:{
					edit_lccName:{
						required:'请选择医院'
					},
					edit_patientName:{
						required:'请选择患者'
					},
					edit_typeCode:{
						required:'请选择短信类型'
					},
					edit_msgId:{
						required:'请选择默认短信内容'
					},
					edit_msgName:{
						required:'请填写短信内容'
					},
					edit_preSendTime:{
						required:'请选择发送时间'
					}
				}
			});
			
			$('#edit_save').click(function(){
				var myform = $("#dialog-confirm").find("form").get(0);
				if(!jQuery(myform).validate().form()){ return;}
				$("#edit_save").attr("disabled",true);
				var data = {};
				data.ID = "${sendVo.id}";
				data.LCC_CODE = $("#edit_lccCode").val();
				data.PATIENT_ID = $("#edit_patientId").val();
				data.SENDTIME_PREINSTALL = $("#edit_preSendTime").val();
				data.MSG_ID = $("#edit_msgId").val();
				data.MSG_NAME = $("#edit_msgName").val();
				
				$.post("${ctx}/msg/send/sendSave",data,function(result){
					if(result && result !='undefinded' &&result.success==true){
						$('#dialog-confirm').modal('hide');
						$("#grid").trigger('reloadGrid');
	        	    	showResult(true,'保存成功');
					}else{
						showResult(false,'保存失败','edit_message');
					}
				},'json');
				$("#edit_save").attr("disabled",false);
			});
			
			changeTypeCode();
		
		});
		
	</script>