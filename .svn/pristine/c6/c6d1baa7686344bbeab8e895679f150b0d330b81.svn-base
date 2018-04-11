<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<style>
		.ulC li{
			height: 40px;
			line-height: 40px;
		}
	</style>
	<div id="alertErr2" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	<div id="alertInfo2" class="alert alert-info" hidden>
		<strong>Warning!</strong>
	</div>
    <form id="examine_form" class="well-work bs-adp form-inline">
    	<input type="hidden"  value="${implementId }" id="implementId1"/>
		<input type="hidden"  value="${lccCode }" id="lccCodeID1"/>
		<fieldset>
			<ul class="ulC">
				<li>
					<ol>
						<li class="select-one">待解决人员:&nbsp;&nbsp;</li>
						<li class="select-one">
							<input type="hidden" id="userCode3" name="userCode"/>
							<input type="hidden" id="lccCode3" />
							<input type="hidden" id="_id">
							<input class="form-control input-sm" id="userName1" name="userName" value="${pipExpImplement.userName }" type="text" placeholder="选择人员姓名" />
						</li>
						<li class="select-one">&nbsp;&nbsp;问题分类:&nbsp;&nbsp;</li>
						<li class="select-one">
							<select id="problemType"  class="form-control input-sm">
								<option value="1">患者管理</option>
								<option value="2">文件管理</option>
								<option value="3">经费管理</option>
								<option value="4">设备管理</option>	
						    </select>
						</li>
						<li class="select-one">&nbsp;&nbsp;事件状态:&nbsp;&nbsp;</li>
						<li class="select-one">
							<select id="status"  class="form-control input-sm">
								<option value="1">开放</option>
								<option value="2">关闭</option>
						    </select>
						</li>
					</ol>
				</li>
				<li>
					<ol>
						<li class="select-one">问题描述:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
						<li>
							<textarea rows="3" style="width: 300px" id="description" class="form-control" name="description" placeholder="问题描述">${pipExpImplement.description}</textarea>
							<button type="button" class="btn btn-primary btn-sm" onclick='addPatient()' id="addItem2" tabindex="1000">增加</button>
							<button type="button" class="btn btn-primary btn-sm" onclick='alterPatient()' id="alterItem" tabindex="1000">修改</button>
							<button type="button" class="btn btn-primary btn-sm" onclick='deleteS()' id="deleteItem1" tabindex="1000">删除</button>
						</li>
					</ol>
				</li>
			</ul>
			
		</fieldset>
	</form>
	<br>
	<hr />
	<div id="JqGrid2" class="font">
		<table id="problemGrid"></table>
		<div id="problemPager"></div>
	</div>
<script type="text/javascript">
function alterPatient(){
	if($("#_id").val()==""){
		openAlert("请先双击选择一条记录进行修改！",2000);
		return ;
	}
	$( "#alterItem").attr("disabled",true);
	if($.trim($("#userCode3").val())==''){
		openAlert("人员姓名不能为空",2000);
		$( "#alterItem").attr("disabled",false);
		return ;
	}
	var postData={};
	postData.id = $("#_id").val();
	postData.userCode = $("#personType").val();
	postData.lccUserId = $("#userCode3").val();
	postData.lccCode = $("#lccCode3").val();
	postData.description=$("#description").val();
	postData.status =$("#status").val();
	postData.problemType=$("#problemType").val();
	postData.implementId=$("#implementId1").val();
	$.post("${ctx}/quality/examineProblem/update", 
			postData,
		function(data){
			if(data.success){
				$("#problemGrid").trigger("reloadGrid");
				alertInfo2("修改成功!",2000);
			}
			else{
				openAlert(data.msg,2000);
			}
			$( "#alterItem").attr("disabled",false);
		}, 
		"json");
	$("#_id").val("");
	$("#description").val("");
	$("#userCode3").val("");
	$("#userName1").val("");
}
function addPatient(){
	$( "#addItem2").attr("disabled",true);
	if($.trim($("#userCode3").val())==''){
		openAlert("人员姓名不能为空",2000);
		$( "#addItem2").attr("disabled",false);
		return ;
	}
	var postData={};
	postData.userCode = $("#personType").val();
	postData.lccUserId = $("#userCode3").val();
	postData.lccCode = $("#lccCode3").val();
	postData.description=$("#description").val();
	postData.status =$("#status").val();
	postData.problemType=$("#problemType").val();
	postData.implementId=$("#implementId1").val();
	$.post("${ctx}/quality/examineProblem/add", 
			postData,
		function(data){
			if(data.success){
				$("#problemGrid").trigger("reloadGrid");
				alertInfo2("新增成功!",2000);
			}
			else{
				openAlert(data.msg,2000);
			}
			$( "#addItem2").attr("disabled",false);
		}, 
		"json");
}
//单条删除
function deleteS(){
	$( "#deleteItem1").attr("disabled",true);
	var id = $("#problemGrid").jqGrid('getGridParam','selrow');
	if($.isEmptyObject(id)){
		openAlert('请选择要删除的记录！',2000);
		$( "#deleteItem1").attr("disabled",false);
		return ;
	}
	
	$.post("${ctx}/quality/examineProblem/delete",{id: id},function(result){
		$("#problemGrid").jqGrid().trigger("reloadGrid");
		var message = "操作失败!";
		if(result.success){
			message = "操作成功！";
		}
		alertInfo2(message,2000);
		$("#deleteItem1").attr("disabled",false);
	},'json');
}
//批量删除
function deletePatient(){
	$( "#deleteItem1").attr("disabled",true);
	var ids = $("#problemGrid").jqGrid('getGridParam','selarrrow');
	if($.isEmptyObject(ids)){
		openAlert('请选择要删除的记录！',2000);
		$( "#deleteItem1").attr("disabled",false);
		return ;
	}
	var idDatas= "";
	$.each(ids,function(i,n){
		idDatas +=n +",";
	});
	idDatas=idDatas.substr(0,idDatas.length-1);
	$.post("${ctx}/quality/examineProblem/deleteBatch",{ids: idDatas},function(result){
		$("#problemGrid").jqGrid().trigger("reloadGrid");
		var message = "操作失败!";
		if(result.success){
			message = "操作成功！";
		}
		alertInfo2(message,2000);
		$("#deleteItem1").attr("disabled",false);
	},'json');
}
function alertInfo2(message,delay){
	$('#alertInfo2').show().find('strong').text(message);
	window.setTimeout(function() {
		$('#alertInfo2').slideUp("slow");
	}, delay);
}
function openAlert(message,delay){
	$('#alertErr2').show().find('strong').text(message);
	window.setTimeout(function() {
		$('#alertErr2').slideUp("slow");
	}, delay);
}
$(function(){
	//grid start
var optionlcc = {
					url : '${ctx}/quality/examineProblem/list',
					datatype : 'json',
					mtype : 'POST',
					postData:{
							implementId:$("#implementId1").val()
					},
					colNames : [ '','','','待解决人员','问题分类','问题描述','事件状态','增加时间'],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden:true,
								align :'center'
							},{
								name: 'lccUserId',
								index:'lccUserId',
								hidden:true
							},{
								name:'lccCode',
								index:'lccCode',
								hidden: true
							},{
								name : 'lccUserName',
								index : 'lccUserName',
								align :'center'
							},{
								name : 'problemType',
								index : 'problemType',
								width:100,
								align :'center',
								formatter: problemFormatter
							},{
								name : 'description',
								index : 'description',
								align :'center'
							},{
								name : 'status',
								index : 'status',
								formatter:typeFormatter,
								align :'center'
							},{
								name : 'createDate',
								index : 'createDate',
								formatter:yymmddFormatter,
								width:90,
								align :'center'
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#problemPager',
					sortname : 'EXAMINE_DATE',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "desc",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					/* multiselect : true, */
					loadComplete : function() {
					},
					ondblClickRow:function(rowid,iRow,iCol,e){
						var jData = $("#problemGrid").jqGrid('getRowData',rowid);
						$("#problemType option[text='"+jData.problemType+"']").attr("selected",true);
						$("#status option[text='"+jData.status+"']").attr("selected",true);
						$("#description").val(jData.description);
						$("#userCode3").val(jData.lccUserId);
						$("#lccCode3").val(jData.lccCode);
						$("#userName1").val(jData.lccUserName);
						$("#_id").val(jData.id);
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
				$("#problemGrid").jqGrid(optionlcc);
				$("#problemGrid").jqGrid('navGrid', '#problemPager', {
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
	    $('#userName1').autocomplete(data,{
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
	    $('#userName1').result(function(event, item){ 
			if(item){
				if(item.USER_CODE != $("#userCode3").val()){
					$("#userCode3").val(item.USER_CODE);
					$("#lccCode3").val(item.LCC_CODE);
				}
			}else{
				$("#userCode3").val("");
			}
	     });
	});
});
function typeFormatter(cellvalue, option, rowObjects){
	if(cellvalue=='1'){
		return '开放';
	}
	if(cellvalue=='2'){
		return '关闭';
	}
	return '-';
}
function problemFormatter(cellvalue, option, rowObjects){
	if(cellvalue=='1'){
		return '患者管理';
	}
	if(cellvalue=='2'){
		return '文件管理';
	}
	if(cellvalue=='3'){
		return '经费管理';
	}
	if(cellvalue=='4'){
		return '设备管理';
	}
	return '-';
}
$.validator.addMethod("number", function(value, element) {
    var tel = /^\+?[1-9][0-9]*$/;
    return this.optional(element) || (tel.test(value));
}, "电话号码格式错误");

</script>