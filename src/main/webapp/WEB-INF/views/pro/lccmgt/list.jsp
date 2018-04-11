<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="dict" uri="http://com.bdcor.pip.web.common.tagsupport" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>项目管理</title>
</head>
<body>
	<ul class="breadcrumb">
		<li class="active">项目管理</li>
		<li class="active">单位管理</li>
	</ul>
	<div id="select">
        <div class="select-main">
              <form action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='lccCode'>所属单位:</label></li>
                         <li> 
                         <input type="hidden" id="rccCode" name="rccCode" class="form-control input-sm" placeholder="PID"/>
                         <input type="text" id="rccName" name="rccName" class="form-control input-sm" placeholder="输入简拼"/>  
                         </li>
                         <li class="select-one"> <label form='lccRole'>医院名称:</label></li>
                         <li> 
                         <input type="hidden" id="lccCode" name="lccCode">
						 <input type="text" id="lccName" name="lccName"   class="form-control input-sm" placeholder="输入简拼">
                         <!--  
				            <select id="lccCode" name="lccCode" class="form-control input-sm">
								<option value="">请选择单位</option>
								<c:forEach items="${lccDictList }" var="lccDict">
									<option value="${lccDict.lccCode }" >${lccDict.lccName }</option>
								</c:forEach>
							</select>
							-->
                         </li>
                         <li class="select-one"> <label form='areaCode'>省份:</label></li>
                         <li> 
                         <input type="hidden" id="areaCode" name="areaCode">
						 <input type="text" id="areaName" name="areaName"   class="form-control input-sm" placeholder="输入省份首字母检索">
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
		<table id="grid" style="overflow-x: scoll !important;"></table>
		<div id="pager"></div>
	</div>
    
    
    <!-- 伦理弹框 start -->
    <div id='dialog-confirm-lunli' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content" style="width: 780px">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">伦理信息</h4>
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
    
    
    <!-- 伦理弹框end -->
    <div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">单位信息</h4>
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
			//所属省份
			$.getJSON("${ctx}/combox/province",function(data) { 
			    $('#areaName').autocomplete(data,{
			        minChars: 0,
			        mustMatch:true, 
			        width:260,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="80px;">' + item.PROVINCE_CODE + '</td><td width="180px;">' + item.PROVINCE_NAME + '</td></tr></table>';
			            
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE+item.PROVINCE_CODE+item.PROVINCE_NAME;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.PROVINCE_NAME;
			        }
			    }); 
			  //选中 某条记录 触发的事件
			    $('#areaName').result(function(event, item){ 
					if(item){
						if(item.PROVINCE_CODE != $("#areaCode").val()){
							$("#areaCode").val(item.PROVINCE_CODE);
						}
					}else{
						$("#areaCode").val("");
					}
			     });
			});
			
			var optionlcc = {
					url : '${ctx}/pro/lccmgt/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [
					            'LCCID','医院名称','省份','医院级别','是否签署协议','签署协议日期','是否通过伦理','入选人数','启动时间','项目负责人','所属协调中心',
					            '','','','','',//'地址', '联系人', '联系人电话', '合同签署时间', '批件时间', 
					             '','', '','',//'所属协调单位','是否行政部门','合同是否签署', '是否通过培训', 
					            '','','激活状态'// '伦理是否通过', '单位状态'
					             
					             ],
					colModel : [
							{
								name : 'lccCode',
								index : 'lcc_Code',
								align : 'center',
								width : 100
							},{
								name : 'lccName',
								index : 'lcc_Name',
								align : 'left' 
							},{
								name : 'areaName',
								index : 'areaName',
								align : 'center'
							},{
								name : 'lccRoleType',
								index : 'hospitalType',
								align : 'center',
								width : 100,
								formatter : function(cellvalue, option, rowObjects) {
									if(cellvalue ==1){
										return 'NCC';
									}
									if(cellvalue ==2){
										return 'RCC';
									}
									if(cellvalue ==3){
										return 'LCC';
									}
									//1:NCC 2:RCC 3:LCC
								}
							},{
								name : 'isSignContract',
								index : 'isSignContract',
								align : 'center',
								width : 100,
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								}
							},{
								name : 'contractSignDate',
								index : 'contractSignDate',
								width : 100,
								formatter:yymmddFormatter,
								align : 'center'
							},{
								name:'isEthical',
								index:'isEthical',
								width : 100,
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if ("1" == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								},
								align:'center'
							},{
								name:'totalCount',
								index:'totalCount',
								width : 100,
								align:'center'
							},{
								name : 'expectStartDate',
								index : 'expectStartDate',
								formatter:yymmddFormatter,
								width : 100,
								align : 'center'
							},{
								name : 'projectManager',
								index : 'projectManager',
								width : 100,
								align : 'center'
								
							},{
								name : 'rccName',
								index : 'rccName',
								width : 150,
								align : 'left'
							},
							{
								name : 'lccRole',
								index : 'lccRole',
								hidden:true
							},
							{
								name : 'address',
								index : 'address',
								align : 'center',
								hidden : true
							},
							{
								name : 'contact',
								index : 'contact',
								align : 'center',
								hidden : true
							},
							{
								name : 'tel',
								index : 'tel',
								align : 'center',
								hidden : true
							},
							{
								name : 'contractSignDate',
								index : 'contract_sign_date',
								align : 'center',
								formatter:yymmddFormatter,
								hidden : true
							},
							{
								name : 'higherApproveDate',
								index : 'higher_approve_date',
								align : 'center',
								formatter:yymmddFormatter,
								hidden : true
							},
							{
								name : 'parentName',
								index : 'parent_code',
								align : 'center',
								hidden : true
							},{
								name : 'isAdminDep',
								index : 'is_admin_dep',
								hidden : true,
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								},
								align:'center'
							},
							{
								name : 'isSignContract',
								index : 'is_sign_contract',
								align: 'center',
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								},
								hidden:true
							},
							{
								name : 'isTraining',
								index : 'is_training',
								align: 'center',
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								},
								hidden:true
							},
							{
								name : 'isEthical',
								index : 'is_ethical',
								align: 'center',
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if ("1" == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								},
								hidden:true
							},
							{
								name : 'status',
								index : 'status',
								align: 'center',
								width : 100,
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">激活</span>'
									} else {
										result = '<span class="label label-default">失活</span>';
									}
									return result;
								}
							}
							
							],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,500,1000],
					height : "100%",
					autowidth : true,
					pager : '#pager',
					sortname : 'lcc_code',
					altRows : true,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "asc",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					//multiselect : true,
					loadComplete : function() {
					},
					shrinkToFit:false,
					jsonReader : {
						root : "rows",
						page : "page",
						total : "total",
						records : "records",
						repeatitems : false,
						cell : "cell",
						id : "lccCode"
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
					.navButtonAdd('#pager',{caption:"激活",buttonicon:"ui-icon-disk",onClickButton: function(){updateStatus(1)},position:"last"})
				    .navButtonAdd('#pager',{caption:"失活",buttonicon:"ui-icon-disk",onClickButton: function(){updateStatus(2)},position:"last"})
				    .navButtonAdd('#pager',{caption:"查看伦理",buttonicon:"ui-icon-search",onClickButton: function(){searchLunli()},position:"last"});
				//自适应
				jqgridResponsive("grid", false);
				$(".ui-jqgrid .ui-jqgrid-bdiv").css("overflow-x","auto");
				//取消按钮操作
				$('#cancel').click(function(){
					$('#dialog-confirm').modal('hide');
				});	
				//取消按钮操作
				$('#cancel1').click(function(){
					$('#dialog-confirm-lunli').modal('hide');
				});	
				
				$('#cancel2').click(function(){
					$('#dialog-delete').modal('hide');
				});
		});
		//弹出新增对话框			
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/lccmgt/openmodallccinput?type=add&time="+timebak);
		}	
		function toModify(){
			var lccCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(lccCode)) {
				openErrorForListPage('请选择要编辑的记录！',2000);
				return;
			}
			var rowData = $("#documentgrid").jqGrid('getRowData',lccCode);
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/lccmgt/openmodallccinput?type=edit&lccCode="+lccCode+"&time="+timebak);
		}
		function searchLunli(){
			var lccCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(lccCode)) {
				openErrorForListPage('请选择要查看的伦理的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			var rowData = $("#grid").jqGrid('getRowData',lccCode);
			openDialogLunLi("${ctx}/pro/lccmgt/openmodalLunLiInput?lccCode="+rowData.lccCode+"&time="+timebak);
		}

		function searchLcc() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var lccName =$("#lccName").val();
		      var lccCode = $("#lccCode").val();
		      var areaCode = $("#areaCode").val();
		      var rccCode = $("#rccCode").val();
		      var rccName = $("rccName").val();
		      if(rccCode && ''!=rccCode){
		          mydata.rccCode = rccCode;
		      }else{
		          delete postData.rccCode;
		      }
		      if(rccName && ''!=rccName){
		          mydata.rccName = rccName;
		      }else{
		          delete postData.rccName;
		      }
		      if(lccName && ''!=lccName){
		          mydata.lccName = lccName;
		      }else{
		          delete postData.lccName;
		      }
		      if(lccCode && ''!=lccCode){
		          mydata.lccCode = lccCode;
		      }else{
		          delete postData.lccCode;
		      }
		      if(areaCode && ''!=areaCode){
		          mydata.areaCode = areaCode;
		      }else{
		          delete postData.areaCode;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
		 //弹出伦理对话框
		 function openDialogLunLi(url,data){
			$( "#dialog-confirm-lunli" ).modal({
				 backdrop: 'static'
			});
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-lunli p" ).load(url);
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
			
			var myform = $("#dialog-confirm").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
		
			//非行政部门包括组识结构国家心血管病中心，要求伦理通过并记录伦理批件编号才可以激活状态。
			if(data.organName=="国家心血管病中心" && data.isAdminDep == "2" && data.status=="1"){
				if(data.isEthical=="2"){
					openError("伦理通过单位才可以激活！", 2000);
					return;
				}
				if(data.ethicalPaperCode==""){
					openError("伦理批件编号不为空，单位才可以激活！", 2000);
					return;
				}
			}
			if(data.isAdminDep == "2" && data.organName!="国家心血管病中心"&&data.status=="1"){
				if(data.isEthical=="2"){
					openError("伦理通过单位才可以激活！", 2000);
					return;
				}
				if(data.ethicalPaperCode==""){
					openError("伦理批件编号不为空，单位才可以激活！", 2000);
					return;
				}
				if(data.isSignContract!="1"){
					openError("签署合同后单位才可以激活！", 2000);
					return;
				}
				if(data.contractSignDate==""){
					openError("签署合同日期不为空，单位才可以激活！", 2000);
					return;
				}
			}
			$("#do_save").attr("disabled",true);
			if(data.projectId == ""){addDate(data);}
			else{updateDate(data);}
		});
		function addDate(data){
			$.post("${ctx}/pro/lccmgt/addLcc",data,function(result){
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
			$.post("${ctx}/pro/lccmgt/addLcc",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "更新失败！";
				if(result.success){
					message = "更新成功！";
				}else{
					message = result.msg+'!';
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		//弹出删除对话框
		function toDelete(){
			var lccCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(lccCode)) {
				openErrorForListPage('请选择要删除的数据',2000);
				return;
			}
			var rowData = $("#grid").jqGrid('getRowData', lccCode);
			if(rowData.status.indexOf("激活")>0){
				openErrorForListPage('该单位已激活，不能删除！',2000);
				return;
			}
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		
		
		//激活  失活 状态修改
		function updateStatus(status){
			var lccCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(lccCode)) {
				openErrorForListPage('请选择一行需要操作的数据',2000);
				return;
			}
			var rowData = $("#grid").jqGrid('getRowData', lccCode);
		
			//如果是失活的就不能再去失活
			if(status==2 &&  rowData.status.indexOf("失活")!=-1){
				showResult(true,"已经失活不用再失活");
				return false;
			}
			//如果是激活就不用去再次激活
			if(status==1 &&  rowData.status.indexOf("激活")!=-1){
				showResult(true,"已经是激活不用再激活");
				return false;
			}
			
			//提示失活某条记录
			if(status==2 ){
				if(!confirm("你确信要失活【"+rowData.lccName+"】吗？")) return false;
			}
			    
			
			//开始执行删除动作
			$.post("${ctx}/pro/lccmgt/updateStatus", 
					{lccCode :lccCode ,  status : status },
        		function(data){
					$("#grid").trigger("reloadGrid");
					var message = "修改失败！";
        			if(data.success){
        				message = "修改成功！";
        			}else{
        				message = data.msg;
        			}
        			showResult(data.success,message);
        		}, 
        		"json");
		}
		
		
		$('#do_delete').click(function(){
			
			var lccCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(lccCode)) {
				openErrorForListPage('请选择一条数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/pro/lccmgt/delete", 
					{lccCode :lccCode },
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
		//所属单位
		$.getJSON("${ctx}/combox/rcc",function(data) { 
		    $('#rccName').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.RCC_CODE + '</td><td width="180px;">' + item.RCC_NAME + '</td></tr></table>';
		            
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.HELP_CODE+item.RCC_CODE;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.RCC_NAME;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#rccName').result(function(event, item){ 
				if(item){
					if(item.RCC_CODE != $("#rccCode").val()){
						$("#rccCode").val(item.RCC_CODE);
					}
				}else{
					$("#rccCode").val("");
				}
		     });
		});
		//单位
		$.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) { 
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
	</script>
</body>
</html>