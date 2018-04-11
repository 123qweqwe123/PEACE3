<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>项目管理</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">物资管理</li>
		<li class="active">计量维修记录</li>
	</ul>
	<form class="well bs-bdcor form-inline" id="searchForm"> 
		<table class="table table-bordered" > 
		    <tr class="active">
		        <td >设备档案号</td>
		        <td >维修计量状态</td>
		        <td >设备名称</td>
		        <td colspan="1"><font class="advanced" >操作</font></td>
		    </tr>  
		    <tr>
		    <td  class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
	            <input type="text" id="archivesNo" name="archivesNo" class="form-control input-sm" placeholder="请双击或输入设备档案号..."/>    
		    </td>  
		    <td  class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
	            <select id="repairState" name="repairState" class="form-control input-sm">
					<option value="">请选择类别</option>
					<option value="1">维修</option>
					<option value="2">计量</option>
				</select>
		     </td>
		    <td  class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
	            <input type="text" id="materlName" name="materlName" class="form-control input-sm" placeholder=""/>    
		    </td>       
		     <td colspan="1" class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">    
	            <button type="button" id="btnQuery" class="simpled btn btn-sm btn-primary" onclick="searchRepair()">查询</button>
	            <!-- 
	            <button type="reset" id="reset_btn" class="simpled btn btn-sm btn-primary" >重置</button>
	             -->
		     </td>   
		    </tr>
		</table>    
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
    
    <div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">计量维修记录登记</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
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
    
	<script type="text/javascript">
		$(function(){
			var optionlcc = {
					url : '${ctx}/material/repair/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '','','','','设备档案号','维修计量状态','所属单位','设备名称','库存单位','规格','生产厂家','维修计量时间','维修计量机构','维修计量人员',''],
					colModel : [
					        {
					        	name:'id',
					        	hidden:true,
					        	key:true
					        },
							{
								name : 'projectId',
								index : 'project_id',
								hidden : true
							},{
								name : 'materlInfoCode',
								index : 'materlinfo_code',
								hidden : true
							},
							{
								name : 'lccCode',
								index : 'lcc_code',
								hidden : true
							},
							{
								name : 'archivesNo',
								index : 'archives_no',
								align : 'left'
							},
							{
								name : 'repairState',
								index : 'repair_state',
								align: 'center',
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">维修</span>'
									} else {
										result = '<span class="label label-default">计量</span>';
									}
									return result;
								}
							},
							{
								name : 'lccName',
								index : '',
								align : 'left'
							},
							{
								name : 'materlName',
								index : '',
								align : 'left'
							},
							{
								name : 'storeUnit',
								index : '',
								align: 'center'
							},
							{
								name : 'spec',
								index : '',
								align : 'left'
							},
							{
								name : 'supplierName',
								index : 'supplier_name',
								align : 'left'
							},
							{
								name : 'repairDate',
								index : 'repair_date',
								formatter:yymmddFormatter,
								align: 'center'
							},
							{
								name : 'repairOrganization',
								index : 'repair_organization',
								align: 'center'
							},
							{
								name : 'repairUser',
								index : 'repair_user',
								align: 'center'
							},{
								name : 'isMeasure',
								hidden : true
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pager',
					sortname : 'archives_no',
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
				$("#grid").jqGrid(optionlcc);
				$("#grid").jqGrid('navGrid', '#pager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				}).navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
					.navButtonAdd('#pager',{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
					.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"});
				//自适应
				jqgridResponsive("grid", false);
				
				//取消按钮操作
				$('#cancel').click(function(){
					$('#dialog-confirm').modal('hide');
				});	
				$('#cancel2').click(function(){
					$('#dialog-delete').modal('hide');
				});
		});
		//弹出新增对话框			
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/repair/openmodaladdrepairinput?time="+timebak);
		}	
		function toModify(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(archivesNo)) {
				openErrorForListPage('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/repair/openmodaladdrepairinput?id="+id+"&time="+timebak);
		}
		function searchRepair() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var archivesNo =$("#archivesNo").val();
		      var repairState = $("#repairState").val();
		      var materlName = $("#materlName").val();
		      if(archivesNo && ''!=archivesNo){
		          mydata.archivesNo = archivesNo;
		      }else{
		          delete postData.archivesNo;
		      }
		      if(repairState && ''!=repairState){
		    	  mydata.repairState = repairState;
		      }else{
		    	  delete postData.repairState;
		      }
		      if(materlName && ''!=materlName){
		    	  mydata.materlName = materlName;
		      }else{
		    	  delete postData.materlName;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
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
			
			var myform = $("#dialog-confirm").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			$("#do_save").attr("disabled",true);
			if(data.archivesNo == ""){addDate(data);}
			else{updateDate(data);}
		});
		function addDate(data){
			$.post("${ctx}/material/repair/addRepair",data,function(result){
				$("#grid").trigger("reloadGrid",[{
	                page : 1
	            }
	        ]);
				$('#dialog-confirm').modal('hide');
				var message = "新增失败！";
				if(result.success){
					message ='新增成功!';
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		function updateDate(data){
			$.post("${ctx}/material/repair/addRepair",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "更新失败！";
				if(result.success){
					message = "更新成功！";
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		//弹出删除对话框
		function toDelete(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择要删除的数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		$('#do_delete').click(function(){
			
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择一条数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/material/repair/delete", 
					{id :id },
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
	    function openError(message,delay){
	    	$('#alertErr').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alertErr').slideUp("slow");
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
		function openErrorForListPage(message,delay){
	    	$('#alert').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert').slideUp("slow");
	    	}, delay);
	    }
		$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_DEVSTORE&cols=ARCHIVES_NO&where=",function(data) { 
			$('#archivesNo').autocomplete(data,{
				mustMatch:true,
				minChars: 0,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td >' + item.ARCHIVES_NO + '</td></tr></table>';
		        },

		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.ARCHIVES_NO;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.ARCHIVES_NO;
		        }
			}); 

			//选中 某条记录 触发的事件
			$('#archivesNo').result(function(event, item){
			       $("#archivesNo").val(item.ARCHIVES_NO);
			}); 

		});
	</script>
</body>
</html>