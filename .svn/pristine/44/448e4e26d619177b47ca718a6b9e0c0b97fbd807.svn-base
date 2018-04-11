<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>设备管理</title>
</head>
<body>

	<ul class="breadcrumb">
		<li class="active">物资管理</li>
		<li class="active">设备入库明细查询</li>
	</ul>
	<form class="well bs-adp form-inline" id="searchForm">
	       <!-- 	入库单号:
	      <input type="text" id="imorderNo1" class="form-control input-sm"/>
	      	 -->设备编码:
	      <input type="text" id="archivesNo1" name="exorderNo" class="form-control input-sm"/>
	     	 订单生成日期:
			<div style="width: 120px;" class="input-group date" id="orderStartDateDiv" data-date-format="yyyy-MM-dd">
				<input type="text"  id="orderStartDate" placeholder="起始日期"  class="form-control input-sm"/>
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
			</div>
			<div style="width: 120px;" class="input-group date" id="orderEndDateDiv" data-date-format="yyyy-MM-dd">
				<input type="text"  id="orderEndDate" placeholder="结束日期"  class="form-control input-sm"/>
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
			</div>
	      
	      
	      
	      
	    &nbsp;&nbsp;&nbsp;&nbsp; 
	    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm" onclick="searchMaterName()">查询</button>
	</form>
	<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	<div id="jqgrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>

	<div id='dialog-confirm2' class="modal fade"  > 
		<div class="modal-dialog" style="width:90%;">
			<div class="modal-content" style="height:620px">
				<div class="modal-header" style="background-color: #71A7D8">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title"  id="dialog-confirm-title">设备入库管理</h4>
				</div>
				<div class="modal-body">
					<p>加载中……</p>
				</div>
				<div class="modal-footer">
					<button type="button" id='cancel' class="btn btn-default btn-sm"
						tabindex="1001">取消</button>
					<button type="button" id='do_save' class="btn btn-primary btn-sm"
						tabindex="1000">提交</button>
				</div>
			</div>
		</div>
	</div>
	
	
	
	<div id='dialog-delete' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">删除</h4>
	      </div>
	      <div class="modal-body">
	        <p>是否确定删除？</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-delete1' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">删除</h4>
	      </div>
	      <div class="modal-body">
	        <p>是否确定删除？</p>
	      </div>
	      <div class="modal-footer">
	      	<input type="hidden" id="doDelete1">
			<button type="button" id ='cancel3' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_delete1' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<script type="text/javascript">
	$('#orderStartDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#orderStartDateDiv').datepicker('hide');
	});
	$('#orderEndDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#orderEndDateDiv').datepicker('hide');
	});
		$(function() {
			var option = {
				url : '${ctx}/material/devwarehouse/to_detaillist',
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '','入库单号','设备编码', '设备名称','LCCID','医院名称','订单生成日期','批次', '出库单价', '采购价',
				             '入库数量','库存单位','截止有效期','生产厂商','设备状态','是否要计量','下次计量时间',
				             '设备计量单位' ],
				colModel : [
						{
							name : 'id',
							index : 'id',
							hidden : true,
							key:true
						},{
							name : 'imorderNo',
							width : '120px'
						},
						{
							name : 'archivesNo',
							index : 'CREATE_BY',
							width : '80px'
						},
						{
							name : 'materlName',
							index : 'MATERL_NAME',
							align : 'left',
							width : '100%'
						},
						{
							name : 'lccCode',
							index : 'lcc_code',
							width : '60px'
						},
						{
							name : 'lccName',
							index : 'lcc_name',
							align : 'left',
							width : '200'
						},
						{
							name : 'orderDate',
							formatter:yymmddFormatter,
							width : '100%'
						},
						{
							name : 'materlBatch',
							index : 'HELP_CODE',
							width : '60px'
						},
						{
							name : 'materlPrice',
							align : 'left',
							width : '60px'
							
						},
						{
							name : 'wholesalePrice',
							width : '60px'
						},
						{
							name : 'importAmount',
							width : '60px',
							hidden:true
						},
						{
							name : 'storeUnit',
							width : '60px'
						},
						{
							name : 'periodValidity',
							formatter:yymmddFormatter,
							width : '100%'
						},
						{
							name : 'supplierName',
							width : '100%'
						},
						{
							name : 'assetsStatus',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (1 == cellvalue) {
									result = '<span class="label label-success">可用</span>'
								} else if(2 == cellvalue) {
									result = '<span class="label label-danger">不可用</span>';
								}
								return result;
							},
							align : 'center',
							width : '80%'
						},
						{
							name : 'isMeasure',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (1 == cellvalue) {
									result = '<span class="label label-success">需要计量</span>'
								} else if(2 == cellvalue) {
									result = '<span class="label label-danger">不需要计量</span>';
								}
								return result;
							},
							align : 'center',
							width : '80%'
						},
						{
							name : 'nextMeasureDate',
							formatter:yymmddFormatter,
							width : '80%'
						},
						{
							name : 'measurePeriod',
							width : '100%',
							hidden:true
						}],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'id',
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
					id : "id"
				}
			};
			$("#grid").jqGrid(option);
			$("#grid").jqGrid('navGrid', '#pager', {
				edit : false,
				add : false,
				del : false,
				search : false,
				position : 'right'
			});
			/*.navButtonAdd('#pager',{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
			.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"}) */
			//自适应
			
			jqgridResponsive("grid", false);
			
		});
		function searchMaterName() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      /* var imorderNo =$("#imorderNo1").val();
		      if(imorderNo && ''!=imorderNo){
		          mydata.imorderNo = imorderNo;
		      }else{
		          delete postData.imorderNo;
		      } */
		      var archivesNo=$("#archivesNo1").val();
		      if(archivesNo && ''!=archivesNo){
		          mydata.archivesNo = archivesNo;
		      }else{
		          delete postData.archivesNo;
		      }
		      var orderStartDate =$("#orderStartDate").val();
		      if(orderStartDate && ''!=orderStartDate){
		          mydata.orderStartDate = orderStartDate;
		      }else{
		          delete postData.orderStartDate;
		      }
		      var orderEndDate =$("#orderEndDate").val();
		      if(orderEndDate && ''!=orderEndDate){
		          mydata.orderEndDate = orderEndDate;
		      }else{
		          delete postData.orderEndDate;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
		//弹出对话框
		function openDialog(url,data){
			$( "#dialog-confirm2" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm2 p" ).load(url);
		}
		//弹出入库明细对话框
		function openDialog2(url,data){
			$( "#dialog-confirm3" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save1").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm3 p" ).load(url);
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
	</script>
</body>
</html>