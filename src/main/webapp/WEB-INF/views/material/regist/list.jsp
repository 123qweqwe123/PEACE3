<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title>物资管理</title>
<style>
	
	.txt{
	    font-size:14px;
	    font-weight:bold;
	}
	
	.percentTxt
		  {
		  	font-size:9px;
		  	font-weight:normal;
		  	color:#333;
		  }
		.upArrow{
        	padding:0px 20px 0px 0px;
        	background-image:url('${ctx}/views/progress/assets/arrowUp1.png');	
        	background-repeat:no-repeat;
        	background-position: 55px 10px;
        }
        
        .downArrow{
        	padding:0px 20px 0px 0px;
        	background-image:url('${ctx}/views/progress/assets/arrowDown1.png');	
        	background-repeat:no-repeat;
        	background-position: 55px 10px;
        }
</style>
</head>
<body>
	<ul class="breadcrumb">
		<li class="active">物资管理</li>
		<li class="active">设备登记入库管理</li>
	</ul>
	<form class="well bs-adp form-inline" id="searchForm">
	       	入库单号:
	        <input type="text" id="imorderNo" name="imorderNo" class="form-control input-sm"/>
	    &nbsp;&nbsp;&nbsp;&nbsp; 
	    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm" onclick="search()">查询</button>
	</form>
	<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	<div id="JqGrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>
<script type="text/javascript">

	$(function() {
		var option = {
			url : '${ctx}/material/regist/list',
			datatype : 'json',
			mtype : 'POST',
			colNames : ['入库单号','','入库类别','入库状态', '医院名称','入库员名称','库房名称','供货商','创建人','创建日期' ],
			colModel : [
					{
						name : 'imorderNo',
						index : 'imorder_no',
						key:true
					},
					{
						name : 'activeclassCode',
						hidden:true
					},
					{
						name : 'activeclassName',
						index : 'activeclass_code',
						align : 'center'
					},
					{
						name : 'importState',
						index : 'import_state',
						align: 'center',
						formatter : function(cellvalue, option, rowObjects) {
							var result = '';
							if (1 == cellvalue) {
								result = '<span class="label label-success">审核通过</span>'
							} else if(0 == cellvalue){
								result = '<span class="label label-warning">未审核</span>'
							} else if(2 == cellvalue){
								result = '<span class="label label-danger">审核失败</span>'
							}
							return result;
						}
					},
					{
						name : 'lccName',
						index : 'lcc_code',
						align : 'center',
						formatter:function(cellvalue, option, rowObjects){
							return rowObjects.lccCode+"_"+cellvalue;
						}
					},
					{
						name : 'userName',
						index : 'user_name',
						align : 'center'
					},
					{
						name : 'stockName',
						index : 'stock_code',
						align : 'center',
						formatter:function(cellvalue, option, rowObjects){
							return rowObjects.stockCode+"_"+cellvalue;
						}
					},
					{
						name : 'supplierName',
						index : 'supplier_code',
						align : 'center'
					},
					{
						name : 'createBy',
						index : 'create_by',
						align : 'center'
					},
					{
						name : 'createDate',
						index : 'create_date',
						formatter:yymmddFormatter,
						align : 'center'
					}],
			rowNum : 15,
			rowList : [ 15, 30, 50,100,150,500 ],
			height : "100%",
			autowidth : true,
			pager : '#pager',
			sortname : 'imorder_no',
			altRows : true,
			hidegrid : false,
			viewrecords : true,
			recordpos : 'left',
			sortorder : "desc",
			emptyrecords : "没有可显示记录",
			loadonce : false,
			//multiselect : true,
			loadComplete : function() {
			},
			jsonReader : {
				root : "rows",
				page : "page",
				total : "total",
				records : "records",
				repeatitems : false,
				cell : "cell",
				id : "imorderNo"
			}
		};
		
		$("#grid").jqGrid(option);
		$("#grid").jqGrid('navGrid', '#pager', {
			edit : false,
			add : false,
			del : false,
			search : false,
			position : 'right'
		}).navButtonAdd('#pager',{caption:"通过",buttonicon:"ui-icon-pencil",onClickButton: function(){updateState('1')},position:"last"})
		.navButtonAdd('#pager',{caption:"退回",buttonicon:"ui-icon-pencil",onClickButton: function(){updateState('2')},position:"last"})
		//自适应
		$("#grid").jqGrid(option);
		jqgridResponsive("grid", false);
	});
	
	function updateState(data) {
		var imorderNo = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(imorderNo)) {
			openError('请选择要更改的记录！',2000);
			return;
		}
		var rowData = $("#grid").jqGrid('getRowData',imorderNo);
		if(rowData.importState.indexOf('审核通过')>0&&data=='1'){
			openError('该入库单已经是审核通过状态！',2000);
			return;
		}
		if(data == '2' && rowData.importState.indexOf('审核失败')>0){
			openError('该入库单已经是审核失败状态！',2000);
			return;
		}
		if(data == '2' && rowData.importState.indexOf('审核通过')>0){
			openError('已审核通过不能退回！',2000);
			return;
		}
		var activeclassCode=rowData.activeclassCode;
		$.post("${ctx}/material/regist/updateState",{imorderNo: imorderNo,importState:data,activeclassCode:activeclassCode},function(result){
			var message = "操作成功！";
			if(result.success){
				$("#grid").trigger("reloadGrid", {page:1 });
			}else{
				message = "操作失败！";
			}
			showResult(result.success,message);
		},'json');
		
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
	
	function openError(message,delay){
    	$('#alert').show().find('strong').text(message);
    	window.setTimeout(function() {
    		$('#alert').slideUp("slow");
    	}, delay);
    }
	
	function search() {  
	      var postData = $("#grid").jqGrid("getGridParam", "postData");  
	      var mydata = {};
	      var imorderNo =$("#imorderNo").val();
	      if(imorderNo && ''!=imorderNo){
	          mydata.imorderNo = imorderNo;
	      }else{
	          delete postData.imorderNo;
	      }
	     $.extend(postData,mydata);
	     $("#grid").jqGrid("setGridParam", {
	         search: true  
	     }).trigger("reloadGrid", [{page:1}]);
	 };
</script>
</body>



</html>