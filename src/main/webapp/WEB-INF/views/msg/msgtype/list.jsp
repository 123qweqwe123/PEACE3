<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>短信库</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">短信管理</li>
		<li class="active">短信库</li>
	</ul>

	<div id="select">
        <div class="select-main">
   			<fieldset>
            	<ol>
                    <li class="select-one"><label for='createDate'>编辑日期:</label></li>
                    <li>
                     	<div style="width: 150px" class="input-group date" id="createDateDiv" data-date-format="yyyy-mm-dd">
							<input type="text"  id="createDate" placeholder="编辑日期" name="createDate" class="form-control input-sm"/>
							<span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
						</div>
                    </li>

                    <li>
                    	<button type="button" onclick="javascript:searchAdjunct();" class="btn btn-primary btn-align-right btn-sm">查询</button>
                    </li>
                </ol>
            </fieldset>
        </div>
    </div>

	<div id="message" class="alert alert-success" hidden>
   		<button data-dismiss="alert" class="close">&times;</button>
   		<span id="messageSpanId"></span>
	</div>

    <div id="alert" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
	
	<div id="documentJqGrid">
		<table id="documentgrid"></table>
		<div id="pagerDocument"></div>
	</div>
	<!-- 新增窗口 -->
	<div id='dialog-confirm' class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">短信类别信息</h4>
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
	<!-- 删除窗口 -->
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

	<script type="text/javascript">
		$('#createDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#createDateDiv').datepicker('hide');
		});
		//查询
		function searchAdjunct() {
		      var postData = $("#documentgrid").jqGrid("getGridParam", "postData");
		      var mydata = {};
		      if ($("#stypeCode").val() && $("#stypeCode").val() != '')
		    	  mydata.typeCode = $("#stypeCode").val();
		      else
		    	  delete postData.typeCode;
		      if ($("#createDate").val() && $("#createDate").val() != '')
		    	  mydata.createDate = $("#createDate").val();
		      else
		    	  delete postData.createDate;
		      if ($("#createBy").val() && $("#createBy").val() != '')
		    	  mydata.createBy = $("#createBy").val();
		      else
			      delete postData.createBy;
		     $.extend(postData,mydata);
		     $("#documentgrid").jqGrid("setGridParam", {
		         search:true
		     }).trigger("reloadGrid", [{page:1}]);
		}
		
		$(function(){
			//取消按钮操作
			$('#cancel').click(function(){
				$('#dialog-confirm').modal('hide');
			});
			$('#cancel2').click(function(){
				$('#dialog-delete').modal('hide');
			});
			//document
			var optionDocument = {
					url : '${ctx}/msg/msgtype/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '', '短信类别编码', '短信类别名称', '编辑日期', '编辑人'],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden: true,
								align :'center'
							},
							{
								name : 'typeCode',
								index : 'typeCode',
								align : 'center'
							},
							{
								name : 'typeName',
								index : 'typeName',
								align : 'center'
							},
							{
								name : 'createDate',
								index : 'createDate',
								align : 'center'
							},
							{
								name : 'createBy',
								index : 'createBy',
								align : 'center'
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50 ],
					height : "100%",
					autowidth : true,
					pager : '#pagerDocument',
					sortname : 'typeCode',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "asc",
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
				$("#documentgrid").jqGrid(optionDocument);
				$("#documentgrid").jqGrid('navGrid', '#pagerDocument', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				}).navButtonAdd('#pagerDocument',{caption:"增加",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
					.navButtonAdd('#pagerDocument',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
					.navButtonAdd('#pagerDocument',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"});
				jqgridResponsive("grid",false); 
				//自适应
				jqgridResponsive("documentgrid", false);
		});

		//弹出新增对话框
		function toAdd(){
			openDialog("${ctx}/msg/msgtype/openmodaladdmsgtype");
		}
		//弹出修改对话框
		function toModify(){
			var msgId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(msgId)) {
				openErrorForListPage('请选择要修改的数据',2000);
				return;
			}
			openDialog("${ctx}/msg/msgtype/openmodaladdmsgtype?msgId="+msgId);
		}
		//新增人员操作
		function AddOrModifyMsgtype(data){
			$.post("${ctx}/msg/msgtype/addMsgtype",data,function(result){
				$("#documentgrid").trigger("reloadGrid", {page:1 });
				$('#dialog-confirm').modal('hide');
				var message = "操作失败！";
				if(result.success && result.isAdd){
					message ='新增成功!';
				} else if(result.success && !result.isAdd) {
					message ='修改成功!';
				} else {
					message= result.msg;
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
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
			AddOrModifyMsgtype(data);
		});
		
		//弹出删除对话框
		function toDelete(){
			var msgId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(msgId)) {
				openErrorForListPage('请选择要删除的数据',2000);
				return;
			}
			var rowData = $("#documentgrid").jqGrid('getRowData', msgId);
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		//确认删除数据
		$('#do_delete').click(function(){
			var msgId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(msgId)) {
				openErrorForListPage('请选择一条数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/msg/msgtype/deleteMsgtype", 
				{msgId :msgId },
        		function(data){
					$("#documentgrid").trigger("reloadGrid", {page:1 });
					$('#dialog-delete').modal('hide');
        			showResult(data.flag, data.message);
        		}, 
        	"json");
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
		
	    function openError(message,delay,msgId){
	    	$(msgId).show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$(msgId).slideUp("slow");
	    	}, delay);
	    }
	    function openErrorForListPage(message, delay){
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