<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title>设备出库明细查询</title>
</head>
<body>

	<ul class="breadcrumb">
		<li class="active">设备管理</li>
		<li class="active">出库明细查询</li>
	</ul>
	<form class="well bs-adp form-inline" id="searchForm">
	       	<!-- 出库单号:
	      <input type="text" id="exorderNo1" name="exorderNo" class="form-control input-sm"/>
	      	 -->
	      	 设备编码:
	      <input type="text" id="archivesNo1" name="exorderNo" class="form-control input-sm"/>
	     	 收货LCCID:
	      <input type="text" id="exportLccCode1" class="form-control input-sm"/>
	       	收货单位:
	      	<input id="exportLccName1"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
	      	出库日期:
	      	<div style="width: 120px;" class="input-group date" id="exportStartDateDiv" data-date-format="yyyy-MM-dd">
				<input type="text"  id="exportStartDate" placeholder="起始日期" name="exportStartDate" class="form-control input-sm"/>
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
			</div>
			<div style="width: 120px;" class="input-group date" id="exportEndDateDiv" data-date-format="yyyy-MM-dd">
				<input type="text"  id="exportEndDate" placeholder="结束日期" name="exportEndDate" class="form-control input-sm"/>
				<span class="input-group-addon input-sm btn">
				<i class="glyphicon glyphicon-calendar "></i>
				</span>
			</div>
			<br />
			<br />
			
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
		<div class="modal-dialog modal-lg" style="width:90%;">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #71A7D8">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">设备出库管理</h4>
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
	$('#exportStartDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#exportStartDateDiv').datepicker('hide');
	});
	$('#exportEndDateDiv').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#exportEndDateDiv').datepicker('hide');
	});
		$(function() {
			$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) { 
			    $('#exportLccName1').autocomplete(data,{
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
			    /* $('#exportLccName1').result(function(event, item){ 
					if(item){
						if(item.lccCode != $("#lccCode").val()){
							$("#lccCode").val(item.lccCode);
						}
					}else{
						$("#lccCode").val("");
					}
			     }); */ 
			});
			
			var option = {
				url : '${ctx}/material/devdelivery/to_detaillist',
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '', '订单号','设备编码','设备名称','收货LCCID','收货单位','订单生成日期','出库日期','批次', '出库单价', '采购价','库存单位',
					            '截止有效期','生产厂商' ],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden : true,
								key:true
							},
							{
								name : 'exorderNo',
								align : 'center',
								width : '100'
							},
							{
								name : 'archivesNo',
								align : 'center',
								width : '100'
							},
							{
								name : 'materlName',
								index : 'MATERL_NAME',
								align : 'left',
								width : '100%'
							},
							{
								name : 'exportLccCode',
								align : 'center',
								width : '100%'
							},
							{
								name : 'exportLccName',
								align : 'left',
								width : '200'
							},{
								name : 'orderDate',//截止有效期
								formatter:yymmddFormatter,
								width : '100%'
							},
							{
								name : 'exportDate',//截止有效期
								formatter:yymmddFormatter,
								width : '100%'
							},
							{
								name : 'materlBatch',
								index : 'HELP_CODE',
								width : '100%'
							},
							{
								name : 'materlPrice',
								align : 'left',
								width : '100%'
								
							},
							{
								name : 'wholesalePrice',
								width : '100%'
							},
							{
								name : 'exportUnit',
								width : '100%'
							},
							{
								name : 'periodValidity',//截止有效期
								formatter:yymmddFormatter,
								width : '100%'
							},
							{
								name : 'supplierCode',//生产厂商
								align : 'center',
								width : '200'
							}],
				rowNum : 50,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'ID',
				altRows : true,
				hidegrid : false,
				viewrecords : true,
				recordpos : 'left',
				sortorder : "desc",
				emptyrecords : "没有可显示记录",
				loadonce : false,
				loadComplete : function() {
				},
				jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell",
					id : "ID"
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
			//自适应
			jqgridResponsive("grid", false);
		});
		function toPoint(){
			var ids = $("#grid").jqGrid('getGridParam','selarrrow');
			if($.isEmptyObject(ids)){
				openError('请选择要接受的记录！',2000);
				return ;
			}
			var idDatas= "";
			$.each(ids,function(i,n){
				idDatas +=n +",";
			});
			idDatas=idDatas.substr(0,idDatas.length-1);
						
			window.open("${ctx}/material/devdelivery/look?ids="+idDatas);
		}
		function searchMaterName() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      /* var exorderNo =$("#exorderNo1").val();
		      if(exorderNo && ''!=exorderNo){
		          mydata.exorderNo = exorderNo;
		      }else{
		          delete postData.exorderNo;
		      } */
		      var archivesNo=$("#archivesNo1").val();
		      if(archivesNo && ''!=archivesNo){
		          mydata.archivesNo = archivesNo;
		      }else{
		          delete postData.archivesNo;
		      }
		      var exportLccName=$("#exportLccName1").val();
		      if(exportLccName && ''!=exportLccName){
		          mydata.exportLccName = exportLccName;
		      }else{
		          delete postData.exportLccName;
		      }
		      var exportLccCode=$("#exportLccCode1").val();
		      if(exportLccCode && ''!=exportLccCode){
		          mydata.exportLccCode = exportLccCode;
		      }else{
		          delete postData.exportLccCode;
		      }
		      var exportStartDate= $("#exportStartDate").val();
		      if(exportStartDate && ''!=exportStartDate){
		          mydata.exportStartDate = exportStartDate;
		      }else{
		          delete postData.exportStartDate;
		      }
		      var exportEndDate= $("#exportEndDate").val();
		      if(exportEndDate && ''!=exportEndDate){
		          mydata.exportEndDate = exportEndDate;
		      }else{
		          delete postData.exportEndDate;
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