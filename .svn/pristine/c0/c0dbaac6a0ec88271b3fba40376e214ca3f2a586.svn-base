<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
<title>血检报表</title>
</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">血检报表</li>
      </ul>
</div>

<ul id="myTab" class="nav nav-tabs">
	<li class="active">
		<a href="#src" data-toggle="tab">
			首次随访
		</a>
	</li>
	<li><a href="#new" data-toggle="tab">6月随访</a></li>
	<li><a href="#lastView" data-toggle="tab">末次随访</a></li>

</ul>
<div id="myTabContent" class="tab-content">
	<div class="tab-pane fade in active" id="src">


		<div id="alert" class="alert alert-danger" hidden>
			<strong>Warning!</strong>
		</div>
		<div id="message" class="alert alert-success" hidden>
			<button data-dismiss="alert" class="close">&times;</button>
			<span id="messageSpanId"></span>
		</div>
		<div id="select">
			<div class="select-main">
				<form action="" method="post" class="well-work bs-adp form-inline">
					<div class="row">
						<div class="col-md-1" style="text-align: right">医院名称:</div>
						<div class="col-md-2">
							<input type="hidden" id="lccCode"  />
							<input class="form-control input-sm" id="lccName"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
						</div>

						<div class="col-md-1" style="text-align: right">所属组别:</div>
						<div class="col-md-3">
							<select name="group" id="group" class="form-control input-sm">
								<option value="">全部</option>
								<option value="11">糖尿病实验组</option>
								<option value="01">非糖尿病实验组</option>
							</select>
						</div>
						<button type="button" onclick="doQuery();" class="btn btn-primary btn-sm">查询</button>
						<button type="button" onclick="doExport();" class="btn btn-primary btn-sm">导出</button>
					</div>
				</form>
			</div>
		</div>
		<div id="jqgrid">
			<table id="grid"></table>
			<div id="pager"></div>
		</div>
		<!-- <div id="jqgrid2">
            <table id="grid2"></table>
        </div> -->

		<script type="text/javascript">
			function doExport(){
				window.open('${ctx}/qn/uqsAnswerqnBloodReportExport');
			}
			function doQuery(){
				var data = {};
				var postData = $("#grid").jqGrid("getGridParam", "postData");
				var lccCode = $("#lccCode").val();
				if(lccCode !=null && lccCode !=''){
					data.lccCode = lccCode;
				}else{
					delete postData.lccCode;
				}
				$.extend(postData,data);
				$("#grid").jqGrid("setGridParam", {
					search: true
				}).trigger("reloadGrid", [{page:1}]);
			}
			$(function(){
				$.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) {
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
				var option = {
					url : '${ctx}/qn/uqsAnswerqnBloodReportList',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ 'LCCID','医院名称','本院采血','外院采血','已录入','未录入'],
					colModel : [
						{name : 'LCC_CODE', index : 'LCC_CODE', align:'center'},
						{name : 'LCC_NAME', index : 'LCC_NAME',width:200, align:'left'},
						{name : 'BYCYCOUNT', index : 'BYCYCOUNT', width:100,align:'center',formatter :reValue },
						{name : 'FBYSYCOUNT', index : 'FBYSYCOUNT',width:100, align:'center',formatter :reValue },
						{name : 'YLRCOUNT', index : 'YLRCOUNT',width:100, align:'center',formatter :reValue},
						{name : 'WLR', index : 'WLR',width:100, align:'center',formatter :reValue}
					],
					rowNum : 100,
					rowList : [ 100,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pager',
					sortname : 'LCC_CODE',
					altRows:true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "ASC",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					multiselect: false,
					jsonReader : {
						root : "rows",
						page : "page",
						total : "total",
						records : "records",
						repeatitems : false,
						cell : "cell"
					}
				};
				$("#grid").jqGrid(option);
				$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
				jqgridResponsive("grid",false);
			});
			function reValue(cellvalue, option, rowObjects) {
				if(cellvalue==0){
					return '';
				}
				else{
					return cellvalue;
				}
			}
			function showResult(result,message,delay){
				$("#messageSpanId").text(message);

				if (!delay || typeof(delay)=="undefined" || typeof(delay)!='number'){
					delay = 2000;
				}
				if(result){
					$("#message").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
				}else{
					$("#message").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
				}
				window.setTimeout(function() {
					$('#message').slideToggle(1000);
				}, delay);
			}
		</script>
	</div>
<div class="tab-pane fade" id="new">
	<div id="select">
		<div class="select-main">
			<form action="" method="post" class="well-work bs-adp form-inline">
				<div class="row">
					<div class="col-md-1" style="text-align: right">医院名称:</div>
					<div class="col-md-2">
						<input type="hidden" id="lccCode2"  />
						<input class="form-control input-sm" id="lccName2"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
					</div>

					<div class="col-md-1" style="text-align: right">所属组别:</div>
					<div class="col-md-3">
						<select name="group" id="group2" class="form-control input-sm">
							<option value="">全部</option>
							<option value="1">非干预面访</option>
							<option value="0">入选CHAT研究</option>
						</select>
					</div>
					<button type="button" onclick="doQuery2();" class="btn btn-primary btn-sm">查询</button>
					<button type="button" onclick="doExport2();" class="btn btn-primary btn-sm">导出</button>
				</div>
			</form>
		</div>
	</div>
		<div id="jqgrid2">
			<table id="grid2"></table>
			<%--<div id="pager"></div>--%>
		</div>
<script type="text/javascript">
	$(function(){
		$.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) {
			$('#lccName2').autocomplete(data,{
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
			$('#lccName2').result(function(event, item){
				if(item){
					if(item.lccCode != $("#lccCode2").val()){
						$("#lccCode2").val(item.lccCode);
					}
				}else{
					$("#lccCode2").val("");
				}
			});
		});
		var option2 = {
			url : '${ctx}/qn/reportBlood2',
			datatype : 'json',
			mtype : 'POST',
			colNames : [ 'LCCID','医院名称','已随访人数','本院采血','外院采血','未采血','已录入','未录入'],
			colModel : [
				{name : 'LCC_CODE', align:'center'},
				{name : 'LCC_NAME',width:'200px',align:'left'},
				{name : 'YSFS',align:'center'},
				{name : 'BYCX',align:'center'},
				{name : 'WYCX',align:'center'},
				{name : 'WCX',align:'center'},
				{name : 'YLR',width:'90px',align:'center'},
				{name : 'WLR',width:'90px',align:'center'}
			],
			rowNum : 100,
			rowList : [ 100,500 ],
			height : "100%",
			autowidth : true,
//			pager : '#pager',
			sortname : 'LCC_CODE',
			altRows:true,
			hidegrid : false,
			viewrecords : true,
			recordpos : 'left',
			sortorder : "ASC",
			emptyrecords : "没有可显示记录",
			loadonce : false,
			multiselect: false,
			footerrow:true,
			jsonReader : {
				root : "rows",
				page : "page",
				total : "total",
				records : "records",
				repeatitems : false,
				cell : "cell"
			}
			,gridComplete:function(){
				jqgridResponsive("grid2",false);
				$("#grid2").footerData('set',
						{
							'LCC_CODE':'合计',
							'LCC_NAME':'',
							'YSFS':$(this).getCol('YSFS', false, 'sum'),
							'BYCX':$(this).getCol('BYCX', false, 'sum'),
							'WYCX':$(this).getCol('WYCX', false, 'sum'),
							'WCX': $(this).getCol('WCX', false, 'sum'),
							'YLR': $(this).getCol('YLR', false, 'sum'),
							'WLR': $(this).getCol('WLR', false, 'sum'),
						}
				);
			}
		};
		$("#grid2").jqGrid(option2);
	});
	function doExport2(){
		var str="?";
		var lccCode = $("#lccCode2").val();
		if(lccCode !=null && lccCode !=''){
			str = str+"lccCode="+lccCode;
		}
		var group = $("#group2").val();
		if(group !=null && group !=''){
			str = str+"group="+group;
		}
		window.open('${ctx}/qn/uqsAnswerqnBloodReportExport2'+str);
	}
	function doQuery2(){
		var data = {};
		var postData = $("#grid2").jqGrid("getGridParam", "postData");
		var lccCode = $("#lccCode2").val();
		if(lccCode !=null && lccCode !=''){
			data.lccCode = lccCode;
		}else{
			delete postData.lccCode;
		}
		var group = $("#group2").val();
		if(group !=null && group !=''){
			data.group = group;
		}else{
			delete postData.group;
		}
		$.extend(postData,data);
		$("#grid2").jqGrid("setGridParam", {
			search: true
		}).trigger("reloadGrid", [{page:1}]);
	}
</script>




</div>

	<!-- 末次随访 -->
	<div class="tab-pane fade" id="lastView">
		<div id="select">
			<div class="select-main">
				<form action="" method="post" class="well-work bs-adp form-inline">
					<div class="row">
						<div class="col-md-1" style="text-align: right">医院名称:</div>
						<div class="col-md-2">
							<input type="hidden" id="lccCode3"  />
							<input class="form-control input-sm" id="lccName3"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
						</div>

						<div class="col-md-1" style="text-align: right">所属组别:</div>
						<div class="col-md-3">
							<select name="group" id="group3" class="form-control input-sm">
								<option value="">全部</option>
								<option value="0">非干预末次面访</option>
								<option value="1">干预末次随访</option>
							</select>
						</div>
						<button type="button" onclick="doQuery3();" class="btn btn-primary btn-sm">查询</button>
						<button type="button" onclick="doExport3();" class="btn btn-primary btn-sm">导出</button>
					</div>
				</form>
			</div>
		</div>
		<div id="jqgrid3">
			<table id="grid3"></table>
			<%--<div id="pager"></div>--%>
		</div>
		<script type="text/javascript">
            $(function(){
                $.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) {
                    $('#lccName3').autocomplete(data,{
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
                    $('#lccName3').result(function(event, item){
                        if(item){
                            if(item.lccCode != $("#lccCode3").val()){
                                $("#lccCode3").val(item.lccCode);
                            }
                        }else{
                            $("#lccCode3").val("");
                        }
                    });
                });
                var option3 = {
                    url : '${ctx}/qn/reportBlood12',
                    datatype : 'json',
                    mtype : 'POST',
                    colNames : [ 'LCCID','医院名称','已随访人数','本院采血','外院采血','未采血','已录入','未录入'],
                    colModel : [
                        {name : 'LCC_CODE', align:'center'},
                        {name : 'LCC_NAME',width:'200px',align:'left'},
                        {name : 'YSFS',align:'center'},
                        {name : 'BYCX',align:'center'},
                        {name : 'WYCX',align:'center'},
                        {name : 'WCX',align:'center'},
                        {name : 'YLR',width:'90px',align:'center'},
                        {name : 'WLR',width:'90px',align:'center'}
                    ],
                    rowNum : 100,
                    rowList : [ 100,500 ],
                    height : "100%",
                    autowidth : true,
//			pager : '#pager',
                    sortname : 'LCC_CODE',
                    altRows:true,
                    hidegrid : false,
                    viewrecords : true,
                    recordpos : 'left',
                    sortorder : "ASC",
                    emptyrecords : "没有可显示记录",
                    loadonce : false,
                    multiselect: false,
                    footerrow:true,
                    jsonReader : {
                        root : "rows",
                        page : "page",
                        total : "total",
                        records : "records",
                        repeatitems : false,
                        cell : "cell"
                    }
                    ,gridComplete:function(){
                        jqgridResponsive("grid3",false);
                        $("#grid3").footerData('set',
                            {
                                'LCC_CODE':'合计',
                                'LCC_NAME':'',
                                'YSFS':$(this).getCol('YSFS', false, 'sum'),
                                'BYCX':$(this).getCol('BYCX', false, 'sum'),
                                'WYCX':$(this).getCol('WYCX', false, 'sum'),
                                'WCX': $(this).getCol('WCX', false, 'sum'),
                                'YLR': $(this).getCol('YLR', false, 'sum'),
                                'WLR': $(this).getCol('WLR', false, 'sum'),
                            }
                        );
                    }
                };
                $("#grid3").jqGrid(option3);
            });
            function doExport3(){
                var str="?";
                var lccCode = $("#lccCode3").val();
                if(lccCode !=null && lccCode !=''){
                    str = str+"lccCode="+lccCode;
                }
                var group = $("#group3").val();
                if(group !=null && group !=''){
                    str = str+"group="+group;
                }
                window.open('${ctx}/qn/BloodReportExport12'+str);
            }
            function doQuery3(){
                var data = {};
                var postData = $("#grid3").jqGrid("getGridParam", "postData");
                var lccCode = $("#lccCode3").val();
                if(lccCode !=null && lccCode !=''){
                    data.lccCode = lccCode;
                }else{
                    delete postData.lccCode;
                }
                var group = $("#group3").val();
                if(group !=null && group !=''){
                    data.group = group;
                }else{
                    delete postData.group;
                }
                $.extend(postData,data);
                $("#grid3").jqGrid("setGridParam", {
                    search: true
                }).trigger("reloadGrid", [{page:1}]);
            }
		</script>
	</div>
</div>

</body>

</html>