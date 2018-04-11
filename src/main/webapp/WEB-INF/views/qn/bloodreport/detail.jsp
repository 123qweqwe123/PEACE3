<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<title>未采集中心血患者名单</title>
	<script type="text/javascript">

		var multipleSearch = function(){
			var myform = $("#searchForm").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name]=null;
				if(field.value && ''!=field.value){
					data[field.name] = field.value;
				}
			});

			var postData = $("#grid").jqGrid("getGridParam", "postData");

			$.extend(postData,data);

			$("#grid").jqGrid("setGridParam", {
				search: true
			}).trigger("reloadGrid", [{page:1}]);
		};

		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
			if(e && e.keyCode==27){ // 按 Esc
				//要做的事情
			}
			if(e && e.keyCode==113){ // 按 F2
				//要做的事情
			}
			if(e && e.keyCode==13){ // enter 键
				//要做的事情
				//multipleSearch();
			}
		};

	</script>
</head>
<body>
<div id="navbar">
	<ul class="breadcrumb">
		<li class="active">未采集中心血患者名单</li>
	</ul>
</div>
<div id="message" class="alert alert-success" hidden>
	<button data-dismiss="alert" class="close">&times;</button>
	<span id="messageSpanId"></span>
</div>
<div id="select">
	<div class="select-main">
		<form action="" id="searchForm" method="post" >
			<fieldset>
				<div  class="row">
					<div  class="leftLable col-md-1" style="text-align:right">医院名称:</div>
					<div  class="col-md-2">
						<input type="hidden" id="lccCode" name="lccCode" value="${lcc.lccCode}">
						<input type="text" id="lccName" name="lccName" value="${lcc.lccName}"   class="form-control input-sm" placeholder="输入简拼或者LCCID">
					</div>
					<div  class="leftLable col-md-1" style="text-align:right">PID:</div>
					<div  class="col-md-2">
						<input type="text" id="patient_id" name="patientId" class="form-control input-sm" placeholder="输入PID"></li>
					</div>
					<div  class="leftLable col-md-1" style="text-align:right">患者姓名:</div>
					<div  class="col-md-2">
						<input type="text" id="patient_name" name="patientName" class="form-control input-sm" placeholder="输入患者姓名"></li>

					</div>
					<div  class="leftLable col-md-1" style="text-align:right">门诊类型:</div>
					<div  class="col-md-2">
						<select class="form-control input-sm" id="type">
							<option value="1">CHAT研究干预随机门诊</option>
							<option value="2">CHAT干预末次门诊</option>
						</select>
					</div>

					<div  class="col-md-2" style="float:right;margin-right: 30px;margin-top: 5px">
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
						<button type="button" id="btnExport" class="btn btn-primary btn-align-right btn-sm" style="margin-left:10px;">导出</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>

<div id="jqgrid">
	<table id="grid"></table>
	<div id="pager"></div>
</div>

<script type="text/javascript">
	$(function(){
		var option = {
			url : '${ctx}/qn/report/noCenterBloodlist',
			datatype : 'json',
			postData : {lccCode:$("#lccCode").val()},
			mtype : 'POST',
			colNames : ['LCCID','医院名称','PID','患者姓名','门诊类型'],
			colModel : [
				{name : 'LCC_CODE', align:'center'},
				{name : 'LCC_NAME', align:'center'},
				{name : 'PATIENT_ID', align:'center'},
				{name : 'PATIENT_NAME', align:'center'},
				{name : 'TYPE', align:'left'}
			],
			rowNum : 15,
			rowList : [ 15, 30, 50,100,500 ],
			height : "100%",
			autowidth : true,
			pager : '#pager',
			altRows:true,
			hidegrid : false,
			viewrecords : true,
			recordpos : 'left',
			sortname : 'LCC_CODE',
			sortorder : "ASC",
			emptyrecords : "没有可显示记录",
			loadonce : false,
			multiselect: false,
		};
		$("#grid").jqGrid(option);
		$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
		jqgridResponsive("grid",false);

		$("#btnQuery").click(multipleSearch);

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
					return item.lccCode+item.helpCode+item.lccName;
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

	});

	$("#btnExport").click(function(){
		var postData = $("#grid").jqGrid("getGridParam", "postData");
		var pram = "";
		$.each(postData, function (k, v) {
			if(v == null)return true;
			if(pram.length==0){
				pram+="?";
			}else{
				pram+="&";
			}
			pram += k + "=" +encodeURI(encodeURI(v));
		});
		window.open('${ctx}/qn/report/blooddetailexport'+pram);
	});
</script>
</body>

</html>