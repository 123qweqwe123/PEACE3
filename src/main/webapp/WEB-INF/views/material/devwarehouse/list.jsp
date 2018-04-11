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
		<li class="active">设备入库管理</li>
	</ul>
	<form class="well bs-adp form-inline" id="searchForm">
	       	入库单号:
	      <input type="text" id="imorderNo1" class="form-control input-sm"/>
	      	设备编码:
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
	
	<!-- <div id='dialog-confirm3' class="modal fade"  > 
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">设备入库明细管理</h4>
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
	</div> -->
	
	
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
				url : '${ctx}/material/devwarehouse/list',
				datatype : 'json',
				mtype : 'POST',
				colNames : ['入库单号', '入库类别','', '医院名称','入库员名称','库房名称','供货商','创建人','创建日期' ],
				colModel : [
						{
							name : 'imorderNo',
							index : 'imorder_no',
							width : '100px',
							key:true
						},
						{
							name : 'activeclassName',
							index : 'activeclass_code',
							width : '80px',
							align : 'center'
						},
						{
							name : 'importState',
							hidden:true
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
							width : '80px',
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
							width : '100px',
							align : 'center'
						},
						{
							name : 'createDate',
							index : 'create_date',
							formatter:yymmddFormatter,
							width : '80px',
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
				},
				subGrid: true,
				onSelectRow:function(id,status){
					girdId=id;
				},
				 subGridOptions: {
				        "plusicon"  : "ui-icon-triangle-1-e",
				        //"minusicon" : "ui-icon-triangle-1-s",
				        "openicon"  : "ui-icon-arrowreturn-1-e"
						//expand all rows on load
					//	"expandOnLoad" : true
					},

				 subGridRowExpanded: function(subgrid_id, row_id) {
					     var subgrid_table_id, pager_id;
							subgrid_table_id = subgrid_id+"_t";
							pager_id = "p_"+subgrid_table_id;
					       $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
					       jQuery("#"+subgrid_table_id).jqGrid({
					    	   	url : '${ctx}/material/devwarehouse/detaillist?imorderNo='+row_id,
								datatype : 'json',
								mtype : 'POST',
								colNames : [ '', '设备名称', '批次', '出库单价', '采购价',
								             '入库数量','库存单位','截止有效期','生产厂商','设备状态','是否要计量','下次计量时间',
								             '设备计量单位','设备档案号' ],
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
											width : '50%'
										},
										{
											name : 'materlPrice',
											align : 'left',
											width : '70%'
											
										},
										{
											name : 'wholesalePrice',
											width : '70%'
										},
										{
											name : 'importAmount',
											width : '70%'
										},
										{
											name : 'storeUnit',
											width : '100%'
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
											width : '100%'
										},
										{
											name : 'archivesNo',
											index : 'CREATE_BY',
											width : '100%'
										}],
					          height: '100%',
					          sortname: 'ID',
					          sortorder: "asc",
					          pager: pager_id,
					          rowNum : 15,
							  rowList : [ 15, 30, 50,100,150,500 ],
					          viewrecords : true,
					          recordpos : 'left',
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
							})/* .navButtonAdd("#"+pager_id,{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAddDetail(row_id,subgrid_id)},position:"left"})
							.navButtonAdd("#"+pager_id,{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModifyDetail(row_id,subgrid_id)},position:"left"})
							.navButtonAdd("#"+pager_id,{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDeleteDetail(row_id,subgrid_id)},position:"left"}) */
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
			/*.navButtonAdd('#pager',{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
			.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"}) */
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

		});
	
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/devwarehouse/openmodalAddOrUpdate?state=1&time="+timebak);
		}
		function toAddDetail(id,s_id){
			s_id = s_id.substr('5',s_id.length);
			//登记入库审核不通过的不能进行新增
			var rowData = $("#grid").jqGrid('getRowData',s_id);
			var importState=rowData.importState;
			/* if(importState=='1'){
				openError('该入库单已经审核通过,不能进行新增操作！',2000);
				return;
			} */
			var timebak = new Date().getTime();
			openDialog2("${ctx}/material/devwarehouse/openmodalAddOrUpdateDetail?state=1&time="+timebak+"&imorderNo="+id+"&importState="+importState);
		}
		
		function toModify(){
			var imorderNo = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(imorderNo)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			//登记入库审核不通过的不能进行修改
			var rowData = $("#grid").jqGrid('getRowData',imorderNo);
			var importState=rowData.importState;
			if(importState=='1'){
				openError('亲 :该入库单已经审核通过,不能进行修改操作！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/devwarehouse/openmodalAddOrUpdate?state=1&imorderNo="+imorderNo+"&time="+timebak);
		}
		
		function getSubGridName(id){
			var subGridId="";
			subGridId="grid_"+id+"_t";
			return subGridId;
		}
		
		function toModifyDetail(id,s_id){
			//得到当前 p_grid_X123456_t
			var subGridId = $("#"+getSubGridName(id)).jqGrid('getGridParam','selrow');
			if($.isEmptyObject(subGridId)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			s_id = s_id.substr('5',s_id.length);
			//登记入库审核不通过的不能进行新增
			var rowData = $("#grid").jqGrid('getRowData',s_id);
			var importState=rowData.importState;
			if(importState=='1'){
				openError('亲 :该入库单已经审核通过,不能进行修改操作！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog2("${ctx}/material/devwarehouse/openmodalAddOrUpdateDetail?state=2&id="+subGirdId+"&time="+timebak);
		}
		$('#do_save').click(function(){
			if(!$( "#import_form" ).validate().form()){
				return ;
			}
			// 获取数据准备插入操作
			var obj_arr = $("#tempgrid").jqGrid("getRowData");
			if(obj_arr.length<=0){
				openErrorMsg('请添加要入库的设备',2000);
				return ;
			}
			$("#do_save").attr("disabled",true);
			var myform = $("#dialog-confirm2").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			if( obj_arr != "underfined" ){
				for( var i = 0 ;i <obj_arr.length ; i++ ){
					var imorderNo = $("#imorderNo").val();
					obj_arr[i].imorderNo = imorderNo;
					obj_arr[i]['type']=1;
// 					addOrUpdateDateDetail(obj_arr[i],1);
				}
			}
			//修改的逻辑 start
			data['type']=1;
			$.post("${ctx}/material/devwarehouse/saveOrUpdateMasterImport",
					{"m":JSON.stringify(data),"list":JSON.stringify(obj_arr)},
				function(result){
					$("#grid").trigger("reloadGrid", {page:1 });
					$('#dialog-confirm2').modal('hide');
					var message='';
					if(result.success){
						message = "新增成功！";
					}
					else{
						message= result.msg;
					}
					/* if(type=='1'){
						var message = "新增失败！";
						if(result.success){
							message ='新增成功!';
						}
					}else if(type=='2'){
						var message = "更新失败！";
						if(result.success){
							message = "更新成功！";
						}
					} */
					$('#do_save').attr("disabled",false);
					showResult(result.success,message);
			},'json');
			/* if(data.createDate ==""){
				data['type']=1;
				addOrUpdateDate(data,1);}
			else{
				data['type']=2;	
				data['createDate']='';
				addOrUpdateDate(data,2);
			} */
		});
		//入库明细保存
		$('#do_save1').click(function(){
			if(!$("#import_form1" ).validate().form()){
				return;
			}
			$("#do_save1").attr("disabled",true);
			var myform = $("#dialog-confirm3").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			//修改的逻辑 start
			data['type']=1;
			$.post("${ctx}/material/devwarehouse/saveOrUpdateMasterImport",
					{"m":JSON.stringify(data),"list":JSON.stringify(obj_arr)},
				function(result){
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
			//修改的逻辑end
			/* if(data.createDate ==""){
				data['type']=1;
				addOrUpdateDateDetail(data,1);}
			else{
				data['type']=2;	
				data['id']=subGirdId;
				data['createDate']='';
				addOrUpdateDateDetail(data,2);
			} */
		});
		function addOrUpdateDate(data,type){
			$.post("${ctx}/material/devwarehouse/saveOrUpdateMasterImport",data,function(result){
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
			$.post("${ctx}/material/devwarehouse/saveOrUpdateMasterImportDetail",data,function(result){
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
					}else if(!result.success&&result.msg!=null){
						message = result.msg;
					}
				}
				$('#do_save1').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		//弹出删除对话框
		function toDelete(){
			var imorderNo = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(imorderNo)) {
				openError('请选择要删除的数据',2000);
				return;
			}
			//登记入库审核不通过的不能进行修改
			var rowData = $("#grid").jqGrid('getRowData',imorderNo);
			var importState=rowData.importState;
			if(importState=='1'){
				openError('亲 :该入库单已经审核通过,不能进行删除操作！',2000);
				return;
			}
			var bodyContent = $.ajax({
			      url: "${ctx}/material/devwarehouse/countMasterImportDetail",
			      global: false,
			      type: "POST",
			      data: ({imorderNo :imorderNo}),
			      dataType: "html",
			      async:false,
			      success: function(msg){
			      }
			   }
			).responseText;
			if(bodyContent>0){
				$("#dialog-delete p").html("该入库单下已关联<B style='color:red'>"+bodyContent+"</B> 条入库明细,是否确定删除?");
			}else{
				$("#dialog-delete p").html("是否确定删除?")
			}
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		//弹出删除对话框
		function toDeleteDetail(id,s_id){
			var subGridId = $("#"+getSubGridName(id)).jqGrid('getGridParam','selrow');
			if($.isEmptyObject(subGridId)) {
				openError('请选择要删除的数据',2000);
				return;
			}
			s_id = s_id.substr('5',s_id.length);
			//登记入库审核不通过的不能进行新增
			var rowData = $("#grid").jqGrid('getRowData',s_id);
			var importState=rowData.importState;
			if(importState=='1'){
				openError('亲 :该入库单已经审核通过,不能进行删除操作！',2000);
				return;
			}
			$("#doDelete1").val(subGridId);
			$("#do_delete1").attr("disabled",false);
			$("#dialog-delete1").modal({
				 keyboard: false
			});
		}
		$('#do_delete').click(function(){
			var imorderNo = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(imorderNo)) {
				openError('请选择一条数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/material/devwarehouse/delete", 
					{imorderNo :imorderNo },
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
			var subGridId=$("#doDelete1").val();
			$("#do_delete1").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/material/devwarehouse/deleteDetail", 
					{id :subGirdId },
        		function(data){
					$("#grid").trigger("reloadGrid", {page:1 });
					$('#dialog-delete1').modal('hide');
					var message = "删除失败！";
        			if(data.success){
        				message = "删除成功！";
        			}else if(!data.success&&data.msg!=null){
						message = data.msg;
					}
        			showResult(data.success,message);
        		}, 
        		"json");
		});
		function searchMaterName() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var imorderNo =$("#imorderNo1").val();
		      if(imorderNo && ''!=imorderNo){
		          mydata.imorderNo = imorderNo;
		      }else{
		          delete postData.imorderNo;
		      }
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
	    function openError(message,delay){
	    	$('#alert').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert').slideUp("slow");
	    	}, delay);
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
</body>
</html>