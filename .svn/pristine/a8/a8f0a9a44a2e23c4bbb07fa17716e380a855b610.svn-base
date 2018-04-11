<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<title>物资管理</title>
</style>
</head>
<body>

	<ul class="breadcrumb">
		<li class="active">物资管理</li>
		<li class="active">出库管理</li>
	</ul>
	<form class="well bs-adp form-inline" id="searchForm">
	       	出库单号:
	      <input type="text" id="exorderNo1" name="exorderNo" class="form-control input-sm"/>
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
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">出库管理</h4>
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
	
	<div id='dialog-confirm3' class="modal fade"  > 
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">出库明细管理</h4>
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
		$(function() {
			var option = {
				url : '${ctx}/material/delivery/list',
				datatype : 'json',
				mtype : 'POST',
				colNames : ['出库单号', '出库类别','来源库房','收货库房','出库员','出库日期','出库单位','收货单位','创建日期' ],
				colModel : [
						{
							name : 'exorderNo',
							index : 'exorder_no',
							key:true
						},
						{
							name : 'activeclassName',
							index : 'activeclass_code',
							align : 'center'
						},
						{
							name : 'stockName',
							align : 'center'
						},
						{
							name : 'exportStockName',
							align : 'center'
						},
						{
							name : 'userName',
							index : 'user_name',
							align : 'center'
						},
						{
							name : 'exportDate',
							formatter:yymmddFormatter,
							align : 'center'
						},
						{
							name : 'lccName',
							align : 'center'
						},
						{
							name : 'exportLccName',
							align : 'center'
						},{
							name : 'createDate',
							formatter:yymmddFormatter,
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
					id : "exorderNo"
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
					    // we pass two parameters
					    // subgrid_id is a id of the div tag created within a table
					    // the row_id is the id of the row
					    // If we want to pass additional parameters to the url we can use
					    // the method getRowData(row_id) - which returns associative array in type name-value
					    // here we can easy construct the following
					     var subgrid_table_id, pager_id;
							subgrid_table_id = subgrid_id+"_t";
							pager_id = "p_"+subgrid_table_id;
					       $("#"+subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+pager_id+"' class='scroll'></div>");
					       jQuery("#"+subgrid_table_id).jqGrid({
					    	   url : '${ctx}/material/delivery/detaillist?exorderNo='+row_id,
								datatype : 'json',
								mtype : 'POST',
								colNames : [ '', '物资名称', '批次', '出库单价', '采购价','库存单位',
								             '出库数量','截止有效期','生产厂商','物资类别','设备档案号' ],
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
											align : 'left'
										},
										{
											name : 'materlBatch',
											index : 'HELP_CODE'
										},
										{
											name : 'materlPrice',
											align : 'left'
											
										},
										{
											name : 'wholesalePrice'
										},
										{
											name : 'exportUnit'
										},
										{
											name : 'exportAmount'
										},
										{
											name : 'periodValidity',//戒指有效期
											formatter:yymmddFormatter
										},
										{
											name : 'supplierName',//生产厂商
											align : 'center'
										},
										{
											name : 'classCode',
											align : 'center',
											formatter : function(cellvalue, option, rowObjects) {
												var result = '';
												if ('A' == cellvalue) {
													result = '<span class="label label-success">物资</span>'
												} else if('B' == cellvalue) {
													result = '<span class="label label-success">设备</span>';
												}
												return result;
											}
										},
										{
											name : 'archivesNo',
											align : 'center'
										}],
					          height: '100%',
					          rowNum:20,
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
							}).navButtonAdd("#"+pager_id,{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAddDetail(row_id)},position:"left"})
							.navButtonAdd("#"+pager_id,{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModifyDetail(row_id)},position:"left"})
							.navButtonAdd("#"+pager_id,{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDeleteDetail()},position:"left"})
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
			.navButtonAdd('#pager',{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
			.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"})
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
			openDialog("${ctx}/material/delivery/openmodalAddOrUpdate?state=2&time="+timebak);
		}
		function toAddDetail(id){
			var timebak = new Date().getTime();
			openDialog2("${ctx}/material/delivery/openmodalAddOrUpdateDetail?state=2&time="+timebak+"&exorderNo="+id);
		}
		
		function toModify(){
			var exorderNo = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(exorderNo)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/delivery/openmodalAddOrUpdate?state=2&exorderNo="+exorderNo+"&time="+timebak);
		}
		
		function toModifyDetail(id){
			if($.isEmptyObject(subGirdId)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog2("${ctx}/material/delivery/openmodalAddOrUpdateDetail?state=2&id="+subGirdId+"&time="+timebak+"&exorderNo="+id);
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
			$.post("${ctx}/material/delivery/saveOrUpdateMasterExport",data,function(result){
				$("#grid").trigger("reloadGrid");
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
			$.post("${ctx}/material/delivery/saveOrUpdateMasterExportDetail",data,function(result){
				$("#grid").trigger("reloadGrid");
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
			      url: "${ctx}/material/delivery/countMasterExportDetail",
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
			$.post("${ctx}/material/delivery/delete", 
					{exorderNo :exorderNo },
        		function(data){
					$("#grid").trigger("reloadGrid");
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
			$.post("${ctx}/material/delivery/deleteDetail", 
					{id :subGirdId },
        		function(data){
					$("#grid").trigger("reloadGrid");
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