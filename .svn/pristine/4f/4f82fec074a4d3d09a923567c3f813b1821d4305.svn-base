<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<title>物资管理</title>
<style type="text/css">
.form-group{
	margin-left: 5px;
	margin-top: 5px;
}
.form-inline>div{
	
}
#delivery_div>fieldset{
	border: 1px solid #ddd;
	border-radius: 4px 4px 0 0;
	margin-top: 5px;
	padding: 5px 5px;
}
#delivery_div>fieldset>form .row .col-md-1{
	padding: 0px;
	text-align: right;
}
#delivery_div>fieldset>form .row .col-md-1 label{
	font-weight: normal;
}
#delivery_div>fieldset>form>div{
	margin-top: 5px;
}
fieldset tbody tr td:last-child{
	cursor: pointer;
}
</style>
</head>
<body>

	<ul class="breadcrumb">
		<li class="active">物资管理</li>
		<li class="active">耗材出库管理</li>
	</ul>
	<form class="well bs-adp form-inline" id="searchForm">
	       	出库单号:
	      <input type="text" id="exorderNo1" name="exorderNo" class="form-control input-sm"/>
	    	收货单位:
	      	<input id="lccCode" type="hidden" name="lccCode" />
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
		<div class="modal-dialog" style="width:90%;">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #71A7D8">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">耗材出库管理</h4>
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
	
	<div id='dialog-confirm4' class="modal fade"  > 
		<div class="modal-dialog modal-lg" style="width:90%;">
			<div class="modal-content">
				<div class="modal-header" style="background-color: #71A7D8">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">耗材出库管理</h4>
				</div>
				<div class="modal-body">
					<p>加载中……</p>
				</div>
				<div class="modal-footer">
					<button type="button" id='cancel4' class="btn btn-default btn-sm"
						tabindex="1001">取消</button>
					<button type="button" id='do_save4' class="btn btn-primary btn-sm"
						tabindex="1000">提交</button>
				</div>
			</div>
		</div>
	</div>
	
	<div id='dialog-confirm3' class="modal fade"  > 
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">耗材出库明细管理</h4>
				</div>
				<div class="modal-body">
					<p>加载中……</p>
				</div>
				<div class="modal-footer">
					<button type="button" id='cancel1' class="btn btn-default btn-sm"
						tabindex="1001">取消</button>
					<button type="button" id='do_save1' class="btn btn-primary btn-sm"
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
			<button type="button" id ='cancel3' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_delete1' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<script type="text/javascript">
	var girdId="";
	var subGirdId="";
	
	
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
			    $('#exportLccName1').result(function(event, item){ 
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
				url : '${ctx}/material/delivery/list',
				datatype : 'json',
				mtype : 'POST',
				colNames : ['出库单号', '出库类别',/* '来源库房','收货库房', */'出库员','出库日期',/* '出库单位', */'收货LCCID','收货单位','创建日期' ],
				colModel : [
						{
							name : 'exorderNo',
							index : 'exorder_no',
							width : '100',
							key:true
						},
						{
							name : 'activeclassName',
							index : 'activeclass_code',
							width : '60',
							align : 'center'
						},
						/* {
							name : 'stockName',
							align : 'left',
							formatter:function(cellvalue, option, rowObjects){
								return rowObjects.stockCode+"_"+cellvalue;
							}
						}, */
						/* {
							name : 'exportStockName',
							align : 'left',
							formatter:function(cellvalue, option, rowObjects){
								return rowObjects.exportStockCode+"_"+cellvalue;
							}
						}, */
						{
							name : 'userName',
							index : 'user_name',
							width : '60',
							align : 'center'
						},
						{
							name : 'exportDate',
							formatter:yymmddFormatter,
							width : '60',
							align : 'center'
						},
						/* {
							name : 'lccName',
							align : 'center',
							formatter:function(cellvalue, option, rowObjects){
								return rowObjects.lccCode+"_"+cellvalue;
							}
						}, */
						{
							name : 'exportLccCode',
							width : '100',
							align : 'center'
						},
						{
							name : 'exportLccName',
							align : 'left'
						},{
							name : 'createDate',
							formatter:yymmddFormatter,
							width : '60',
							align : 'center'
						}],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'exorder_no',
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
					id : "exorderNo"
				},
				subGrid: true,
				onSelectRow:function(id,status){
					girdId=id;
				},
				 subGridOptions: {
					 "plusicon" : "ui-icon-triangle-1-e", "minusicon" : "ui-icon-triangle-1-s", 
					 "openicon" : "ui-icon-arrowreturn-1-e", "reloadOnExpand" : false,"selectOnExpand" : true
					},

				 subGridRowExpanded: function(subgrid_id, row_id) {
					     var subgrid_table_id, pager_id;
							subgrid_table_id = subgrid_id+"_t";
							pager_id = "p_"+subgrid_table_id;
					       $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
					       jQuery("#"+subgrid_table_id).jqGrid({
					    	   url : '${ctx}/material/delivery/detaillist?exorderNo='+row_id,
								datatype : 'json',
								mtype : 'POST',
								colNames : [ '', '耗材名称', '批次', '出库单价', '采购价','库存单位',
								             '出库数量','截止有效期','生产厂商'],
								colModel : [
										{
											name : 'id',
											index : 'id',
											hidden : true,
											key:true
										},
										{
											name : 'materlName',
											index : 'MATERL_NAME',
											align : 'left',
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
											name : 'exportAmount',
											width : '100%'
										},
										{
											name : 'periodValidity',//戒指有效期
											formatter:yymmddFormatter,
											width : '100%'
										},
										{
											name : 'supplierName',//生产厂商
											align : 'center',
											width : '100%'
										}],
					          height: '100%',
					          rowNum:20,
					          rowNum : 15,
							  rowList : [ 15, 30, 50,100,150,500 ],
					          viewrecords : true,
					          recordpos : 'left',
					          sortname: 'ID',
					          sortorder: "asc",
					          pager: pager_id,
					          onSelectRow:function(id,status){
					        	  subGirdId=id;
								}
					       });
					       $("#"+subgrid_table_id).jqGrid('navGrid',"#"+pager_id, {
								edit : false,
								add : false,
								del : false,
								search : false,
								position : 'left'
							})
							/* .navButtonAdd("#"+pager_id,{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAddDetail(row_id)},position:"left"}).navButtonAdd("#"+pager_id,{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModifyDetail(row_id)},position:"left"})
							.navButtonAdd("#"+pager_id,{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDeleteDetail()},position:"left"}) */
					   }
			};
			$("#grid").jqGrid(option);
			$("#grid").jqGrid('navGrid', '#pager', {
				edit : false,
				add : false,
				del : false,
				search : false,
				position : 'right'
			}).navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
			.navButtonAdd('#pager',{caption:"打印订单",buttonicon:"ui-icon-pencil",onClickButton: function(){toPoint()},position:"last"})
			//.navButtonAdd('#pager',{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
			//.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"})
			//自适应
			
			jqgridResponsive("grid", false);
			
			$('#cancel').click(function(){
				$('#dialog-confirm2').modal('hide');
			});	
			$('#cancel1').click(function(){
				$('#dialog-confirm3').modal('hide');
			});	
			$('#cancel2').click(function(){
				$('#dialog-delete').modal('hide');
			});	
			$('#cancel3').click(function(){
				$('#dialog-delete1').modal('hide');
			});	
			$('#cancel4').click(function(){
				$('#dialog-confirm4').modal('hide');
			});
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
						
			window.open("${ctx}/material/matdelivery/look?ids="+idDatas);
		}
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/matdelivery/openmodalAddOrUpdate?state=2&time="+timebak);
		}
		function toAddDetail(id){
			var timebak = new Date().getTime();
			openDialog2("${ctx}/material/matdelivery/openmodalAddOrUpdateDetail?state=2&time="+timebak+"&exorderNo="+id);
		}
		
		function toModify(){
			var exorderNo = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(exorderNo)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/matdelivery/openmodalAddOrUpdate?state=2&exorderNo="+exorderNo+"&time="+timebak);
		}
		
		function toModifyDetail(id){
			if($.isEmptyObject(subGirdId)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog2("${ctx}/material/matdelivery/openmodalAddOrUpdateDetail?state=2&id="+subGirdId+"&time="+timebak+"&exorderNo="+id);
		}
		$('#do_save').click(function(){
			if(!$( "#export_form" ).validate().form()){
				return ;
			}
			$("#do_save").attr("disabled",true);
			var myform = $("#dialog-confirm2").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			if(data.createDate ==""){
				data['type']=1;
				addOrUpdateDate(data,1);}
			else{
				data['type']=2;	
				data['createDate']='';
				addOrUpdateDate(data,2);
			}
		});
		//出库明细保存
		$('#do_save1').click(function(){
			if(!$("#export_form1" ).validate().form()){
				return;
			}
			$("#do_save1").attr("disabled",true);
			var myform = $("#dialog-confirm3").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			if(data.createDate ==""){
				data['type']=1;
				addOrUpdateDateDetail(data,1);}
			else{
				data['type']=2;	
				data['id']=subGirdId;
				data['createDate']='';
				addOrUpdateDateDetail(data,2);
			}
		});
		
		
		function addOrUpdateDate(data,type){
			$.post("${ctx}/material/matdelivery/saveOrUpdateMasterExport",data,function(result){
				$("#grid").trigger("reloadGrid", {page:1 });
				$('#dialog-confirm2').modal('hide');
				var message='';
				if(type=='1'){
					var message = "新增失败！";
					if(result.success){
						message ='新增成功!';
					}
				}else if(type=='2'){
					var message = "更新失败！";
					if(result.success){
						message = "更新成功！";
					}
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		function addOrUpdateDateDetail(data,type){
			$.post("${ctx}/material/matdelivery/saveOrUpdateMasterExportDetail",data,function(result){
				$("#grid").trigger("reloadGrid", {page:1 });
				$('#dialog-confirm3').modal('hide');
				var message='';
				if(type=='1'){
					var message = "新增失败！";
					if(result.success){
						message ='新增成功!';
					}
				}else if(type=='2'){
					var message = "更新失败！";
					if(result.success){
						message = "更新成功！";
					}
				}
				$('#do_save1').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		
		//弹出删除对话框
		function toDelete(){
			var exorderNo = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(exorderNo)) {
				openError('请选择要删除的数据',2000);
				return;
			}
			var bodyContent = $.ajax({
			      url: "${ctx}/material/matdelivery/countMasterExportDetail",
			      global: false,
			      type: "POST",
			      data: ({exorderNo :exorderNo}),
			      dataType: "html",
			      async:false,
			      success: function(msg){
			      }
			   }
			).responseText;
			if(bodyContent>0){
				$("#dialog-delete p").html("该出库单下已关联<B style='color:red'>"+bodyContent+"</B> 条出库明细,是否确定删除?");
			}else{
				$("#dialog-delete p").html("是否确定删除?")
			}
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		//弹出删除对话框
		function toDeleteDetail(){
			if($.isEmptyObject(subGirdId)) {
				openError('请选择要删除的数据',2000);
				return;
			}
			$("#do_delete1").attr("disabled",false);
			$("#dialog-delete1").modal({
				 keyboard: false
			});
		}
		$('#do_delete').click(function(){
			var exorderNo = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(exorderNo)) {
				openError('请选择一条数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/material/matdelivery/delete", 
					{exorderNo :exorderNo },
        		function(data){
					$("#grid").trigger("reloadGrid", {page:1 });
					$('#dialog-delete').modal('hide');
					var message = "删除失败！";
        			if(data.success){
        				message = "删除成功！";
        			}
        			showResult(data.success,message);
        		}, 
        		"json");
		});
		$('#do_delete1').click(function(){
			
			if($.isEmptyObject(subGirdId)) {
				openError('请选择一条数据',2000);
				return;
			}
			$("#do_delete1").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/material/matdelivery/deleteDetail", 
					{id :subGirdId },
        		function(data){
					$("#grid").trigger("reloadGrid", {page:1 });
					$('#dialog-delete1').modal('hide');
					var message = "删除失败！";
        			if(data.success){
        				message = "删除成功！";
        			}
        			showResult(data.success,message);
        		}, 
        		"json");
		});
		function searchMaterName() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var exorderNo =$("#exorderNo1").val();
		      if(exorderNo && ''!=exorderNo){
		          mydata.exorderNo = exorderNo;
		      }else{
		          delete postData.exorderNo;
		      }
		      
		      var exportLccName=$("#exportLccName1").val();
		      if(exportLccName && ''!=exportLccName){
		          mydata.exportLccName = exportLccName;
		      }else{
		          delete postData.exportLccName;
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
			$( "#dialog-confirm4" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm4 p" ).load(url);
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
	    $("#do_save4").click(function(){
	    	if(!$("#export_form" ).validate().form()){
				return;
			}
	    	var export_form = $("#export_form").serializeArray();//出库单详细信息
			var data = {};
			$.each(export_form, function(i, field){
				data[field.name] = field.value;
			});
	    	var detail_list=$("#detail_table_body").find("tr");
	    	if(detail_list.length==0){
	    		openErrorMsg("请添加要出库的耗材!",2000)
	    		return;
	    	}
	    	var detail="";
	    	for(var i=0;i<detail_list.length;i++){
	    		var storeCount=$(detail_list[i]).find("td[title=count]").text();
	    		var storeId=$(detail_list[i]).find("td[title=id]").text();
	    		detail+=(storeId+":"+storeCount+";");
	    	}
	    	
	    	data.detail=detail;
	    	$.post("${ctx}/material/matdelivery/addMasterExport",data,function(result){
				$("#grid").trigger("reloadGrid", {page:1 });
				$('#dialog-confirm4').modal('hide');
				var message='';
				var message = "新增失败！";
				if(result.success){
					message ='新增成功!';
				}
				$('#do_save4').attr("disabled",false);
				showResult(result.success,message);
			},'json');
	    	
	    });
	</script>
</body>
</html>