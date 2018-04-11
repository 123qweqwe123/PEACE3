<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>质量管理</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">质量管理</li>
		<li class="active">现场考核管理</li>
	</ul>

	<div id="select">
        <div class="select-main">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>医院名称:</label></li>
                         <li> 
                         <input id="lccCode" type="hidden" name="lccCode" />
						 <input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
                         </li>
                        <li> <button type="button" onclick="searchExamine();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
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
	<div id="JqGrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>
    
    <div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">现场考核</h4>
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
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1002">取消</button>
	        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
    <div id='dialog-confirm-upload' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">上传考核表</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" id ='cancel3' class="btn btn-default btn-sm" tabindex="1003">确定</button>
			<button type="button" id ='cancel5' class="btn btn-default btn-sm" tabindex="1005">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog_show_filename' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">考核表</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel4' class="btn btn-default btn-sm" tabindex="1004">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
	<script type="text/javascript">
		$(function(){
			$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) { 
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
			var optionlcc = {
					url : '${ctx}/quality/examine/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '','','医院名称','考核员单位','现场考核员','考核起始日期','考核结束日期','考核备注','上传考核表数量'],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden : true
							},{
								name : 'lccCode',
								hidden : true
							},{
								name : 'lccName',
								index : 'lcc_name'
							},
							{
								name : 'examineLccName',
								index : 'EXAMINE_LCC_NAME',
								align : 'left'
							},
							{
								name : 'examineUserName',
								index : 'EXAMINE_USER_NAME'
							},
							{
								name : 'examineDate',
								index : 'EXAMINE_DATE',
								formatter:yymmddFormatter
							},{
								name : 'examineDate2',
								index : 'EXAMINE_DATE2',
								formatter:yymmddFormatter
							},{
								name : 'remark',
								index : 'remark'
							},
							{
								name : 'fileNo',
								index : 'file_no'
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
					ondblClickRow:function(rowid,iRow,iCol,e){
						showFileName(rowid);
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
					.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"})
					.navButtonAdd('#pager',{caption:"上传考核表",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){toUpload()},position:"last"});
				//自适应
				jqgridResponsive("grid", false);
				
				//取消按钮操作
				$('#cancel').click(function(){
					$('#dialog-confirm').modal('hide');
				});	
				$('#cancel2').click(function(){
					$('#dialog-delete').modal('hide');
				});
				$('#cancel3').click(function(){
					$('#dialog-confirm-upload').modal('hide');
					$("#grid").trigger("reloadGrid");
				});
				$('#cancel4').click(function(){
					$('#dialog_show_filename').modal('hide');
					$("#grid").trigger("reloadGrid");
				});
				$('#cancel5').click(function(){
					$('#dialog-confirm-upload').modal('hide');
					$("#grid").trigger("reloadGrid");
				});
				
		});
		//弹出新增对话框			
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/quality/examine/openmodaladdexamineinput?time="+timebak);
		}	
		function toModify(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/quality/examine/openmodaladdexamineinput?id="+id+"&time="+timebak);
		}
		function searchExamine() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var lccName =$("#lccName").val();
		      if(lccName && ''!=lccName){
		          mydata.lccName = lccName;
		      }else{
		          delete postData.lccName;
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
			if(data.id == ""){addDate(data);}
			else{updateDate(data);}
		});
		function addDate(data){
			$.post("${ctx}/quality/examine/addExamine",data,function(result){
				$("#grid").trigger("reloadGrid");
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
			$.post("${ctx}/quality/examine/addExamine",data,function(result){
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
			$.post("${ctx}/quality/examine/delete", 
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
		
		function toUpload(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择一条记录！',2000);
				return;
			}
			var rowData = $("#grid").jqGrid('getRowData', id);
			var timebak = new Date().getTime();
			$( "#dialog-confirm-upload" ).modal({
				 backdrop: 'static'
			});
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-upload p" ).load("${ctx}/quality/examine/openmodaluploadexamineinput?id="+id+"&lccCode="+rowData.lccCode+"&time="+timebak);
		}
		function showFileName(examineId){
			var timebak = new Date().getTime();
			$( "#dialog_show_filename").modal({
				 backdrop: 'static'
			});
			//使用此方法防止js缓存不加载
			$("#dialog_show_filename p").load("${ctx}/quality/examine/openmodalshowfilenameinput?examineId="+examineId+"&time="+timebak);
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
		function openErrorForListPage(message,delay){
	    	$('#alert').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert').slideUp("slow");
	    	}, delay);
	    }
	</script>
</body>
</html>