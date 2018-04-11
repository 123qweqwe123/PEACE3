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
		<li class="active">供货厂商管理</li>
	</ul>
	
	<div id="select">
        <div class="select-main">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>供货商名称:</label></li>
                         <li> 
                         <input type="text" id="supplierName" name="supplierName" class="form-control input-sm"/>
                         </li>
                        <li> <button type="button" onclick="searchSupplier();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                    <ol>
                       
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
	<div id="jqgrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>

	<div id='dialog-confirm' class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">供货厂商</h4>
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
		$(function() {

			var option = {
				url : '${ctx}/material/supp/list',
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '', '供货商名称', '供货商分类', '产地类别', '信誉级别', '地址', '邮编','联系人姓名','手机',
						'Email', '电话','拼音简码',//'五笔简码',
						'资质有效日期','状态' ],
				colModel : [
						{
							name : 'supplierCode',
							index : 'SUPPLIER_CODE',
							hidden : true
						},
						{
							name : 'supplierName',
							index : 'SUPPLIER_NAME',
							align : 'left'
						},
						{
							name : 'supplierClass',
							index : 'SUPPLIER_CLASS',
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (1 == cellvalue) {
									result = '<span class="label label-success">生产厂商</span>'
								} else if(2 == cellvalue) {
									result = '<span class="label label-success">供货商</span>';
								} else {
									result = '<span class="label label-success">生产商和供货商</span>';
								}
								return result;
							}
						},
						{
							name : 'localityFlag',
							index : 'LOCALITY_FLAG',
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (1 == cellvalue) {
									result = '<span class="label label-success">国产</span>'
								} else if(2 == cellvalue) {
									result = '<span class="label label-success">合资</span>';
								} else if(3 == cellvalue) {
									result = '<span class="label label-success">进口</span>';
								} else {
									result = '<span class="label label-success">其他</span>';
								}
								return result;
							}
						},
						{
							name : 'creditClass',
							index : 'CREDIT_CLASS'
						}, {
							name : 'address',
							index : 'ADDRESS'
						}, {
							name : 'zipCode',
							index : 'ZIP_CODE'
						}, {
							name : 'contactName',
							index : 'CONTACT_NAME'
						}, {
							name : 'contactTel',
							index : 'CONTACT_TEL'
						}, {
							name : 'contactEmail',
							index : 'CONTACT_EMAIL'
						}, {
							name : 'businessTel',
							index : 'BUSINESS_TEL'
						}, {
							name : 'helpCode',
							index : 'HELP_CODE'
						}, //{
					//		name : 'wangCode',
					//		index : 'WANG_CODE'
					//	},
					{
							name : 'qualificationDisableDate',
							index : 'QUALIFICATION_DISABLE_DATE',
							formatter:yymmddFormatter
						}, {
							name : 'status',
							index : 'STATUS',
							formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (1 == cellvalue) {
									result = '<span class="label label-success">有效</span>'
								} else {
									result = '<span class="label label-default">无效</span>';
								}
								return result;
							}
						}],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'SUPPLIER_NAME',
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
					id : "supplierCode"
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
			.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"});
			//自适应
			jqgridResponsive("grid", false);
			
			$('#cancel').click(function(){
				$('#dialog-confirm').modal('hide');
			});	
			$('#cancel2').click(function(){
				$('#dialog-delete').modal('hide');
			});	

		});
	
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/supp/openmodaladdsupplierinput?time="+timebak);
		}
		function toModify(){
			var supplierCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(supplierCode)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/supp/openmodaladdsupplierinput?supplierCode="+supplierCode+"&time="+timebak);
		}
		$('#do_save').click(function(){
			var myform = $("#dialog-confirm").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			$("#do_save").attr("disabled",true);
			var myform = $("#dialog-confirm").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			$.post("${ctx}/material/supp/checkNameExists",data,function(result){
				if(result.success&&data.supplierCode1 ==""){
					$('#do_save').attr("disabled",false);
					$('#alertform').show().find('strong').text("供货商名称已经存在，请重新输入！");
			    	window.setTimeout(function() {
			    		$('#alertform').slideUp("slow");
			    	}, 2000);
				}else{
					if(data.supplierCode1 ==""){addDate(data);}
					else{updateDate(data);}
				}
				
			},'json');
			
		});
		
		function addDate(data){
			$.post("${ctx}/material/supp/addSupplier",data,function(result){
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
			$.post("${ctx}/material/supp/addSupplier",data,function(result){
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
			var supplierCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(supplierCode)) {
				openError('请选择要删除的数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		$('#do_delete').click(function(){
			
			var supplierCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(supplierCode)) {
				openError('请选择一条数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/material/supp/delete", 
					{supplierCode :supplierCode },
        		function(data){
					$("#grid").trigger("reloadGrid");
					$('#dialog-delete').modal('hide');
					var message = "删除失败！";
        			if(data.success){
        				message = "删除成功！";
        			}else{
        				message = data.msg+"!";
        			}
        			showResult(data.success,message);
        		}, 
        		"json");
		});
		function searchSupplier() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var supplierName =$("#supplierName").val();
		      if(supplierName && ''!=supplierName){
		          mydata.supplierName = supplierName;
		      }else{
		          delete postData.supplierName;
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