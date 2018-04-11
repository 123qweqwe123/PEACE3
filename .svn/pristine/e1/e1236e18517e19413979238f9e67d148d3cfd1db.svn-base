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
		<li class="active">系统管理</li>
		<li class="active">新闻管理</li>
	</ul>
	<div id="select">
        <div class="select-main">
              <form action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>标题:</label></li>
                         <li> 
                         <input type="text" id="title" name="title" class="form-control input-sm" placeholder="请输入文件名称"/>
                         </li>
                         <li class="select-one"> <label form='name'>频道:</label></li>
                         <li> 
			                <select id="channel" name="channel" class="form-control input-sm">
								<option value="">请选择类别</option>
								<option value="1">新闻</option>
								<option value="2">疾病知识</option>
								<option value="3">项目进度</option>
								<option value="4">质量控制</option>
								<option value="5">工作流程</option>
								<option value="6">经验交流</option>
								
							</select>
                         </li>
                        <li> <button type="button" onclick="search();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
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
	
	
	<script type="text/javascript">
		$(function(){
			$('#cancel').click(function(){
				$('#dialog-confirm').modal('hide');
			});	
			//document
			var optionDocument = {
					url : '${ctx}/pro/news/listNews',
					datatype : 'json',
					mtype : 'POST',
					colNames : ['','标题','名称','作者', '类型','频道' , '文件名', '创建时间','操作'],
					colModel : [
							{
								sortable:false,
								name : 'id',
								index : 'id',
								hidden :true
							},
							{
								sortable:false,
								name : 'title',
								index : 'title',
								align : 'left'
							},
							{
								sortable:false,
								hidden:true,
								name : 'name',
								index : 'name',
								align : 'left'
							},
							{
								sortable:false,
								name : 'author',
								index : 'author',
								align : 'left'
							},
							{
								sortable:false,
								hidden:true,
								name : 'type',
								index : 'type',
								align : 'left',
								formatter:function(v){
									if ( v ){
										if ( v == '1')
											return '新闻';
										if (v == '2')
											return '视频';
											
									}
									return v;
								}
							},
							{
								sortable:false,
								name : 'channel',
								index : 'channel',
								align : 'left',
								formatter:function(v){
									if (v){
										if ( v == '1')
											return '新闻';
										if ( v == '2' )
											return '疾病知识';
										if ( v == '3')
											return '项目进度';
										if ( v == '4')
											return '质量控制';
										if ( v == '5')
											return '工作流程';
										if ( v == '6')
											return '经验交流';
									}
									return '';
								}
							},
							{
								sortable:false,
								name : 'fileName',
								index : 'fileName',
								align : 'left'
							},
							{
								hidden:true,
								name : 'createDate',
								index : 'createDate',
								align : 'left'
							},{
								sortable:false,
								align : 'center',
								name : 'id',
								index : 'id',
								formatter:function(v){
									return '<a href="javascript:deleteRow('+v+');"><spen style="color:red">删除</span></a>'
								}
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pagerDocument',
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
				$("#documentgrid").jqGrid(optionDocument);
				$("#documentgrid").jqGrid('navGrid', '#pagerDocument', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				}).navButtonAdd('#pagerDocument',{caption:"上传",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){uploadDocument()},position:"last"});
				//自适应
				jqgridResponsive("documentgrid", false);
		});
		function uploadDocument(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/news/openmodaluploadPage?time="+timebak);
		}
		
		function deleteRow(id){
			$.ajax({
				url:"${ctx}/pro/news/deleteNews?id=" + id, 
	           	type:"POST",  
	           	dataType:"json",
	           	success:function(data){
	           	 
	               	if(data.success){
	               		$('#dialog-confirm').modal('hide');
	               		$("#documentgrid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
	               	}else{
	               		$("#loading").hide();
	               		openError(data.msg,2000,$("#alertForUpload"));
	               		$("#do_save").attr("disabled",false);
	               	}
	             }
			});
			
		}
		
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
			
			$("#newsForm").ajaxSubmit({
				url:"${ctx}/pro/news/upload", 
	           	type:"POST",  
	           	dataType:"json",
	           	success:function(data){
	           	 
	               	if(data.success){
	               		$('#dialog-confirm').modal('hide');
	               		$("#documentgrid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
	               	}else{
	               		$("#loading").hide();
	               		openError(data.msg,2000,$("#alertForUpload"));
	               		$("#do_save").attr("disabled",false);
	               	}
	             }
			});
		});
		
		function search(){
		      var postData = $("#documentgrid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var name =$("#name").val();
		      var title = $("#title").val();
		      var channel = $("#channel").val();
		      if(name && ''!=name){
		          mydata.name = name;
		      }else{
		          delete postData.name;
		      }
		      if(title && ''!=title){
		          mydata.title = title;
		      }else{
		          delete postData.title;
		      }
		      if(channel && ''!=channel){
		          mydata.channel = channel;
		      }else{
		          delete postData.channel;
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