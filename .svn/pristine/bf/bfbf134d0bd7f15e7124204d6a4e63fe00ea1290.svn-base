<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>采血箱入库</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">采血器具包</li>
		<li class="active">采血包入库</li>
	</ul>

	<div id="select">
        <div class="select-main">
              <form action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form=''>入库单号:</label></li>
                         <li> 
                         <input type="text" id="imorderNo" class="form-control input-sm" placeholder="请输入入库单号"/>
                         </li>
                         <li class="select-one"> <label form=''>入库日期:</label></li>
                         <li> 
	                         <div class="input-group date" id="startDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="startDate" placeholder="入库起始日期" name="startDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                        <!-- <li class="select-one"> <label form=''>入库结束日期:</label></li> -->
                         <li> 
                         	<div class="input-group date" id="endDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="endDate" placeholder="入库结束日期" name="startDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                          <li> <button type="button" onclick="searchAdjunct();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                         <li> <button type="button" onclick="exportExcel()" class="btn btn-primary btn-align-right btn-sm">下载入库模板</button></li>
                        <li> <button type="button" onclick="uploadDocument()" class="btn btn-primary btn-align-right btn-sm">导入入库</button></li>
                    </ol>
                    <ol>
                       
                    </ol>
                </fieldset>
              </form>
        </div>
    </div>
    
    <div id="alert" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
	<div id="documentJqGrid">
		<table id="documentgrid"></table>
		<div id="pagerDocument"></div>
	</div>

	<div id='dialog-confirm' class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">上传文件</h4>
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
	        <p>是否删除？</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- start -->
    <div id='dialog-confirm-grid' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">箱内信息</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel1' class="btn btn-default btn-sm" tabindex="1001">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<script type="text/javascript">
		$(function(){
			//取消按钮操作
			$('#cancel').click(function(){
				$('#dialog-confirm').modal('hide');
			});	
			$('#cancel1').click(function(){
				$('#dialog-confirm-grid').modal('hide');
			});
			$('#cancel2').click(function(){
				$('#dialog-delete').modal('hide');
			});
			//document
			var optionDocument = {
					url : '${ctx}/material/blood/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '主键','项目编码','入库单号',
					             '入库类别','LCCID','医院名称','入到库房名称',
					             '入库员编码','入库员名称','供货商','入库时间',
					             '操作人','单据是否使用 ','入库状态'],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden:true
							},
							{
								name : 'projectId',
								index : 'projectId',
								hidden:true
							},
							{
								name : 'imorderNo',
								index : 'imorderNo',
								align : 'center'
							},{
								name : 'activeclassCode',
								index : 'activeclassCode',
								align : 'center',
								formatter:function(){
									return '导入入库';
								}
							},
							{
								name : 'lccCode',
								index : 'lccCode',
								align : 'center'
							},
							{
								name : 'lccName',
								index : 'lccName',
								align : 'left'
							},{
								name : 'stockName',
								index : 'stockName',
								formatter:function(cellvalue, option, rowObjects){
									return rowObjects.stockCode+"_"+cellvalue;
								},
								hidden:true
							},
							{
								name : 'userCode',
								index : 'userCode',
								align : 'center',
								hidden:true
								/* formatter:yymmddFormatter */
							},
							{
								name : 'userName',
								index : 'userName',
								align : 'center'
							},{
								name : 'supplierCode',
								index : 'supplierCode',
								align : 'center'
							},
							{
								name : 'createDate',
								index : 'createDate',
								align : 'center',
								formatter:yymmddFormatter
							},
							{
								name : 'createBy',
								index : 'createBy'
							},
							{
								name : 'isRemoved',
								index : 'isRemoved',
								hidden:true
							},
							{
								name : 'importState',
								index : 'importState',
								hidden:true
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pagerDocument',
					sortname : 'adjunct_name',
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
						id : "adjunctId"
					},
					/* 开启多级表格 start */
					subGrid: true,
					/* subGridUrl : '${ctx}/material/blood/list1', 
					   subGridModel : [ 
					                 { 
					                	 name : [ '编号', '项目ID', '入库编码','医院名称' ], 
					                	 width : [ 55, 200, 80 ],
					                	 mapping:['id','projectId','imorderNo','lccName']
					                 } 
					] */
					subGridRowExpanded :function(subgrid_id,row_id){
						//根据subgrid_id定义对应的子表格的table的id
						var subgrid_table_id;  
			            subgrid_table_id = subgrid_id + "_t"; 
			          	//根据subgrid_id定义对应的子表格的pager的id 
			            var subgrid_pager_id;  
			            subgrid_pager_id = subgrid_id + "_pgr";
			            //动态添加子报表的table和pager
			            $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+subgrid_pager_id+"' class='scroll'></div>"); 
			            //创建jqGrid对象 获取选中的行数据
						var rowData = $("#documentgrid").jqGrid('getRowData',row_id);
			            $("#" + subgrid_table_id).jqGrid({  
			            	url: "${ctx}/material/blood/listDetal?imorderNo="+rowData.imorderNo,
			            	datatype: "json",
			            	mtype : 'POST',
			            	colNames: ['编号','项目编码','入库单号','物资信息编码','箱编号','批次','出库单价','采购价','收货单位','库存单位','生产厂商','创建时间','查看详情'], 
			            	colModel: [  
			                           {name:"id",index:"id",key:true,hidden:true},  
			                           {name:"projectId",index:"projectId",hidden:true},  
			                           {name:"imorderNo",index:"imorderNo",align:"center",width:150,hidden:true},  
			       					   {name:"materlinfoCode",index:"materlinfoCode",align:"center",width:90,hidden:true},
			       					   {name:"archivesNo",index:"archivesNo",align:"center",width:50},
			       					   {name:"materlBatch",index:"materlBatch",align:"center",width:40},
			       					   {name:"materlPrice",index:"materlPrice",align:"center",width:100},
			       					   {name:"wholesalePrice",index:"wholesalePrice",align:"center",width:100},
			       					   {name:"importUnit",index:"importUnit",align:"center"},
			       					   {name:"storeUnit",index:"storeUnit",align:"center"},
			       					   {name:"manufacturerCode",index:"manufacturerCode",align:"center",width:200},
			       					   {name:'createDate',align:"center",index : 'createDate',width:90,formatter:yymmddFormatter},
			       					   {name:"look",index:"edit",align:"center",width:80,formatter:look}
			                       ],
	                       pager: subgrid_pager_id,  
	                       viewrecords: true,  
	                       rowNum : 15,
						   rowList : [ 15, 30, 50,100,150,500 ],
					       recordpos : 'left',
	                       height: "100%",
	                       width:"100%"   
			            });
			            
					}
					/* 开启多级表格 end */
				};
				$("#documentgrid").jqGrid(optionDocument);
				$("#documentgrid").jqGrid('navGrid', '#pagerDocument', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				});
				//.navButtonAdd('#pagerDocument',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){deleteDocument()},position:"last"})
				//自适应
				jqgridResponsive("documentgrid", false);
		});
		function look(cellvalue, option, rowObjects){
			return "<a href='javascript:void(0)' style='color:green' onclick='toLook(\""+rowObjects.archivesNo+"\")'>详细信息</a>";
		}
		function exportExcel(){
			window.location.href ='${ctx}/material/blood/download';
			//window.location.href ='${ctx}/static/templete/采血箱导入模板.xls';
		}
		//弹出框查看
		function toLook(archivesNo){
			var timebak = new Date().getTime();
			openDialogGrid("${ctx}/material/blood/openmodalScmarchives?archivesNo="+archivesNo+"&time="+timebak);
			//material/blood/listDetal
		}
		//弹出伦理对话框
		 function openDialogGrid(url,data){
			$( "#dialog-confirm-grid" ).modal({
				 backdrop: 'static'
			});
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-grid p" ).load(url);
		}
		$('#startDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#startDateDiv').datepicker('hide');
		});
		$('#endDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#endDateDiv').datepicker('hide');
		});
		function uploadDocument(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/blood/openmodaladdjunctinput?time="+timebak);
		}
		//弹出删除对话框
		function deleteDocument(){
			var documentId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(documentId)) {
				openError('请选择要删除的文件！',2000,$("#alert"));
				return;
			}
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		$('#do_delete').click(function(){
			var documentId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(documentId)) {
				openError('请选择要删除的文件！',200000,$("#alert"));
				return;
			}
			var rowData = $("#documentgrid").jqGrid('getRowData',documentId);
			$.ajax({
	             type: "POST",
	             url: "${ctx}/pro/filemgt/deleteDocument",
	             data: {adjunctId:documentId,adjunctName:rowData.adjunctName,path:rowData.path},
	             dataType: "json",
	             success: function(data){
	            	 $('#dialog-delete').modal('hide');
	                if(data.success){
	                	$("#documentgrid").setGridParam({postData:{page: 1, projectId: $("#projectId").val()}}).trigger("reloadGrid");
	                }else{
	                	openError("删除失败！",2000,$("#alert"));
	                }
	             }
	         });
		});
		//弹出对话框
		function openDialog(url,data){
			$( "#dialog-confirm" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm p" ).load(url);
		}
		
		$('#do_save').click(function(){
			var myform = $("#dialog-confirm").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			
			
			if($("#file").val()==""){
				openError("请选择要上传的文件！",2000,$("#alertForUpload"));
				return;
			}
			if($("#stockCode").val()==""){
				openError("请选择库房信息！",2000,$("#alertForUpload"));
				return;
			}
			
				var data ={};
				var fileName = $("#file").val();
				if(fileName && fileName.length > 1 ){      
		        var ldot = fileName.lastIndexOf(".");
		        var type = fileName.substring(ldot + 1);
		        if(type != "xls"){
		           openError("请选择2003版xls文件上传",2000,$("#alertForUpload"));
		           return false;
		        }
				}else{
					return false;
				}
				
		        var imorderNo = $("#imorderNo").val();
		        if(imorderNo && imorderNo.length > 1 ){    
		        	data['imorderNo']= imorderNo;
		        }
			
			$("#do_save").attr("disabled",true);
			$("#loading").show();
			$("#adjunctForm").ajaxSubmit({
				url:"${ctx}/material/blood/uploadDocument", 
	           	type:"POST",  
	           	dataType:"json",
	           	success:function(data){  
	               	if(data.success){
	               		$('#dialog-confirm').modal('hide');
	               		searchAdjunct();
	               		//$("#documentgrid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
	               	}else{
	               		$("#loading").hide();
	               		openError(data.msg,2000000,$("#alertForUpload"));
	               		$("#do_save").attr("disabled",false);
	               	}
	             }
			});
		});
		function searchAdjunct(){
		      var postData = $("#documentgrid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var imorderNo =$("#imorderNo").val();
		      var startDate =$("#startDate").val();
		      var endDate 	=$("#endDate").val();
		      if(imorderNo && ''!=imorderNo){
		          mydata.imorderNo = imorderNo;
		      }else{
		          delete postData.imorderNo;
		      }
		      if(startDate && ''!=startDate){
		          mydata.startDate = startDate;
		      }else{
		          delete postData.startDate;
		      }
		      if(endDate && ''!=endDate){
		          mydata.endDate = endDate;
		      }else{
		          delete postData.endDate;
		      }
		     $.extend(postData,mydata);
		     $("#documentgrid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		}
	    function openError(message,delay,msgId){
	    	$(msgId).show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$(msgId).slideUp("slow");
	    	}, delay);
	    }
	</script>
</body>
</html>