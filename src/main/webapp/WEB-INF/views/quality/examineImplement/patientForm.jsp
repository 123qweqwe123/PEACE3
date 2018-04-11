<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="examine_form" class="well-work bs-adp form-inline">
    	<input type="hidden"  value="${implementId }" id="implementId"/>
		<input type="hidden"  value="${lccCode }" id="lccCodeID"/>
		<fieldset>
			<ol>
				<li class="select-one"> 人员类型:&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li class="select-one">
					<select id="personType"  class="form-control input-sm">
						<option value="1">考核人员</option>
						<option value="2">LCC研究人员</option>
						<option value="3">其他人员</option>	
				    </select>
				</li>
				<li class="select-one">&nbsp;&nbsp;&nbsp;&nbsp;人员姓名:&nbsp;&nbsp;&nbsp;&nbsp;</li>
				<li class="select-one">
					<input type="hidden" id="userCode1" name="userCode"/>
					<input type="hidden" id="lccCode1" />
					<input class="form-control input-sm" id="userName" name="userName" value="${pipExpImplement.userName }" type="text" placeholder="选择人员姓名" />
				</li>
				<li class="select-one">&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-sm" onclick='addPatient()' id="addItem" tabindex="1000">增加</button></li>
				<li class="select-one">&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn btn-primary btn-sm" onclick='deletePatient()' id="deleteItem" tabindex="1000">删除</button></li>
			</ol>
		</fieldset>
	</form>
	<br>
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	<div id="alertInfo" class="alert alert-info" hidden>
		<strong>Warning!</strong>
	</div>
	<hr />
	<div id="JqGrid1" class="font">
		<table id="patientGrid"></table>
		<div id="patientPager"></div>
	</div>
<script type="text/javascript">

function addPatient(){
	$( "#addItem").attr("disabled",true);
	if($.trim($("#userCode1").val())==''){
		openAlert("人员姓名不能为空",2000);
		$( "#addItem").attr("disabled",false);
		return ;
	}
	var postData={};
	postData.personType = $("#personType").val();
	postData.lccUserId = $("#userCode1").val();
	postData.lccCode = $("#lccCode1").val();
	postData.implementId=$("#implementId").val();
	$.post("${ctx}/quality/examineImplementPerson/add", 
			postData,
		function(data){
			if(data.success){
				$("#patientGrid").trigger("reloadGrid");
				alertInfo("新增成功!",2000);
			}
			else{
				openAlert(data.msg,2000);
			}
			$( "#addItem").attr("disabled",false);
		}, 
		"json");
}
//删除
function deletePatient(){
	$( "#deleteItem").attr("disabled",true);
	var ids = $("#patientGrid").jqGrid('getGridParam','selarrrow');
	if($.isEmptyObject(ids)){
		openAlert('请选择要删除的记录！',2000);
		$( "#deleteItem").attr("disabled",false);
		return ;
	}
	var idDatas= "";
	$.each(ids,function(i,n){
		idDatas +=n +",";
	});
	idDatas=idDatas.substr(0,idDatas.length-1);
	$.post("${ctx}/quality/examineImplementPerson/deleteBatch",{ids: idDatas},function(result){
		$("#patientGrid").jqGrid().trigger("reloadGrid");
		var message = "操作失败!";
		if(result.success){
			message = "操作成功！";
		}
		alertInfo(message,2000);
		$("#deleteItem").attr("disabled",false);
	},'json');
}
function alertInfo(message,delay){
	$('#alertInfo').show().find('strong').text(message);
	window.setTimeout(function() {
		$('#alertInfo').slideUp("slow");
	}, delay);
}
function openAlert(message,delay){
	$('#alertErr').show().find('strong').text(message);
	window.setTimeout(function() {
		$('#alertErr').slideUp("slow");
	}, delay);
}
$(function(){
	//grid start
var optionlcc = {
					url : '${ctx}/quality/examineImplementPerson/list',
					datatype : 'json',
					mtype : 'POST',
					postData:{
							implementId:$("#implementId").val()
					},
					colNames : [ '','人员类型','人员名称','增加时间'],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden:true,
								align :'center'
							},{
								name : 'personType',
								index : 'personType',
								width : 210,
								align :'center',
								formatter: typeFormatter
							},{
								name : 'lccUserName',
								index : 'lccUserName',
								width : 210,
								align :'center'
							},{
								name : 'createDate',
								index : 'createDate',
								formatter:yymmddFormatter,
								width : 210,
								align :'center'
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#patientPager',
					sortname : 'EXAMINE_DATE',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "desc",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					multiselect : true,
					loadComplete : function() {
					},
					jsonReader : {
						root : "rows",
						page : "page",
						total : "total",
						records : "records",
						repeatitems : false,
						cell : "cell",
						id : "id"
					}
				};
				$("#patientGrid").jqGrid(optionlcc);
				$("#patientGrid").jqGrid('navGrid', '#patientPager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				});
				/* .navButtonAdd('#pager',{caption:"上传考核表",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){toUpload()},position:"last"}) */
				//自适应
				jqgridResponsive("grid", false);	
	//grid end
	$.getJSON("${ctx}/combox/user",function(data) { 
	    $('#userName').autocomplete(data,{
	        minChars: 0,
	        mustMatch:true, 
	        width:260,
	       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	        formatItem: function(item,i, max) {
	            return '<table><tr><td width="80px;">(' + item.USER_CODE + ')</td><td width="180px;">' + item.USER_NAME + '</td></tr></table>';
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item,i, max) {
	            return item.HELP_CODE+item.USER_CODE+item.USER_NAME;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item) {
	            return item.USER_NAME;
	        }
	    }); 
	  //选中 某条记录 触发的事件
	    $('#userName').result(function(event, item){ 
			if(item){
				if(item.USER_CODE != $("#userCode1").val()){
					$("#userCode1").val(item.USER_CODE);
					$("#lccCode1").val(item.LCC_CODE);
				}
			}else{
				$("#userCode1").val("");
			}
	     });
	});
});
function typeFormatter(cellvalue, option, rowObjects){
	if(cellvalue=='1'){
		return '考核人员';
	}
	if(cellvalue=='2'){
		return 'LCC研究人员';
	}
	if(cellvalue=='3'){
		return '其他人员';
	}
	return '-';
}
$.validator.addMethod("number", function(value, element) {
    var tel = /^\+?[1-9][0-9]*$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

</script>