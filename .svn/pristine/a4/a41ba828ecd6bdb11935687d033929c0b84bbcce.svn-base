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
		<li class="active">项目管理</li>
		<li class="active">单位评价管理</li>
	</ul>

	<div id="select">
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                            	 <li class="select-one"> <label form='name'>医院名称:</label></li>
                                 <li> 
                                 <input type="text" id="lccName" name="lccName" class="form-control input-sm"/>
                                 </li>
                                <li> <button type="button" onclick="searchLcc();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                            </ol>
                            <ol>
                               
                            </ol>
                        </fieldset>
                      </form>
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
	        <h4 class="modal-title">单位评价</h4>
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
		$(function(){
			var optionlcc = {
					url : '${ctx}/pro/eval/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '','LCCID','医院名称','评价','评价等级','备注',''],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden: true
							},{
								name : 'lccCode',
								index : 'lcc_code',
								align : 'left'
							},{
								name : 'lccName',
								index : 'lcc_code',
								align : 'left'
							},
							{
								name : 'evaluation',
								index : 'evaluation',
								align : 'left'
							},
							{
								name : 'evaluationLevel',
								index : 'evaluation_level',
								align : 'left'
							},
							{
								name : 'remark',
								index : 'remark',
								align : 'left'
							},
							{
								name : 'isRemoved',
								index : 'is_removed',
								align: 'center',
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">正常</span>'
									} else {
										result = '<span class="label label-default">删除</span>';
									}
									return result;
								},
								hidden:true
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pager',
					sortname : 'evaluation_level',
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
			openDialog("${ctx}/pro/eval/openmodalevalinput?time="+timebak);
		}	
		function toModify(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/eval/openmodalevalinput?id="+id+"&time="+timebak);
		}
		function searchLcc() {  
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
			$.post("${ctx}/pro/eval/addEvaluation",data,function(result){
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
			$.post("${ctx}/pro/eval/addEvaluation",data,function(result){
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
				openError('请选择要删除的数据',2000);
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
				openError('请选择一条数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/pro/eval/delete", 
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
		function openError(message,delay){
	    	$('#alert').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert').slideUp("slow");
	    	}, delay);
	    }
	</script>
</body>
</html>