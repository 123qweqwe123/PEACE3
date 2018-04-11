<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<title>用户管理</title>
</head>
<body>
<ul class="breadcrumb">
<li class="active">系统管理</li><li class="active" >账户管理</li>
</ul>

<form class="well bs-bdcor form-inline" id="searchForm" method="post"> 
	
		登录名:
		<input type="text" id="s_loginName" name="s_loginName" placeholder="登录名" class="form-control input-sm"/>
	    &nbsp;&nbsp;&nbsp;
	        用户名:
		<input type="text" id="s_userName" name="s_userName" placeholder="用户名" class="form-control input-sm" />
	    &nbsp;&nbsp;&nbsp;
	    所属单位:
		<input id="s_lccName" type="text"  name="s_lccName" placeholder="所属单位" class="form-control input-sm" />
	    &nbsp;&nbsp;&nbsp;
	    
	    <button type="button" id="btnQuery" class="btn btn-primary btn-sm">查询</button>
	    &nbsp;&nbsp;&nbsp;
	    <button type="button" id="btnExport" class="btn btn-primary btn-sm">导出</button>
</form>

<div id="alert" class="alert alert-danger" hidden>
  <strong>Warning!</strong>
</div>


<div id="message" class="alert alert-success" hidden>
    <button data-dismiss="alert" class="close">&times;</button>
    <span id="messageSpanId"></span>
</div>
 
	<div id="jqgrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div> 

<div id='dialog-confirm' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">账户信息</h4>
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
<div id='dialog-resetpwd' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">重置密码</h4>
      </div>
      <div class="modal-body">
        <form id="repwd_form" class="form-horizontal">
            <div class="form-group">
                <label class="col-lg-3 col-md-3 control-label" for="r_loginName">登录名:</label>
                <div class="col-lg-5 col-md-5">
                    <input type="text" id="r_loginName" name="r_loginName" class="form-control input-sm" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 col-md-3 control-label" for="r_name">用户名:</label>
                <div class="col-lg-5 col-md-5">
                    <input type="text" id="r_name" name="r_name" class="form-control input-sm" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 col-md-3 control-label" for="r_password"><span style="color: red">*</span>重置密码:</label>
                <div class="col-lg-5 col-md-5">
                    <input type="text" id="r_password" name="r_password" class="form-control input-sm" placeholder="重置密码密钥" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 col-md-3 control-label" for="r_password_again"><span style="color: red">*</span>确认密码:</label>
                <div class="col-lg-5 col-md-5">
                    <input type="password" id="r_password_again" name="r_password_again" class="form-control input-sm" placeholder="确认密码" required>
                </div>
            </div>
            <input type="hidden" id="r_id" name="r_id">
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" data-dismiss="modal" id="cancel3" class="btn btn-default btn-sm">取消</button>
		<button type="button" id="btn_resetpwd" class="btn btn-primary btn-sm">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- <div id='dialog-resetpwd' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">重置密码密钥</h4>
      </div>
      <div class="modal-body">
        <form id="repwd_form" class="form-horizontal">
            <div class="form-group">
                <label class="col-lg-3 col-md-3 control-label" for="r_loginName">登录名:</label>
                <div class="col-lg-5 col-md-5">
                    <input type="text" id="r_loginName" name="r_loginName" class="form-control input-sm" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 col-md-3 control-label" for="r_name">用户名:</label>
                <div class="col-lg-5 col-md-5">
                    <input type="text" id="r_name" name="r_name" class="form-control input-sm" readonly="readonly">
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 col-md-3 control-label" for="r_password"><span style="color: red">*</span>重置密码密钥:</label>
                <div class="col-lg-5 col-md-5">
                    <input type="text" id="r_password" name="r_password" class="form-control input-sm" placeholder="重置密码密钥" required>
                </div>
            </div>
            <div class="form-group">
                <label class="col-lg-3 col-md-3 control-label" for="r_password_again"><span style="color: red">*</span>确认密码:</label>
                <div class="col-lg-5 col-md-5">
                    <input type="password" id="r_password_again" name="r_password_again" class="form-control input-sm" placeholder="确认密码" required>
                </div>
            </div> 
            <input type="hidden" id="r_id" name="r_id">
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" data-dismiss="modal" id="cancel3" class="btn btn-default btn-sm">取消</button>
		<button type="button" id="btn_resetpwd" class="btn btn-primary btn-sm">提交</button>
      </div>
    </div>/.modal-content
  </div>/.modal-dialog
</div>/.modal -->

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

<div id="data-limit" class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">数据权限</h4>
      </div>
      <div class="modal-body">
        <p>加载中</p>
      </div>
      <div class="modal-footer">
		<button type="button" id ='cancel4' class="btn btn-default btn-sm" tabindex="1001">取消</button>
        <button type="button" id ='do_save4' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">

	$(function(){
		$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) { 
		    $('#s_lccName').autocomplete(data,{
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
		   /*  $('#lccName').result(function(event, item){ 
				if(item){
					if(item.lccCode != $("#lccCode").val()){
						$("#lccCode").val(item.lccCode);
					}
				}else{
					$("#lccCode").val("");
				}
		     }); */
		});
		
		
		$("#user_nav").addClass('active');
		var option = {
				url : '${ctx}/sys/rbac/user/list', 
				datatype : 'json', 
				mtype : 'POST',
				colNames : [ '','登录名','用户名','所属单位','状态',//'组织机构',
				             '角色','邮箱','','','',''],
				colModel : [ {name : 'id',index : 'id',hidden : true},
				             {name : 'loginName', index : 'loginName', align:'left' },
				             {name : 'name', index : 'name', align:'left' },
				             {name : 'lccName', index : 'lcc_code', align:'left' },
				             {name : 'status', index : 'status', align:'center',formatter:function(cellvalue,option,rowObjects){
				            	 var result = '';
				            	 if(1==cellvalue){result='<span class="label label-success">有效</span>'}
			            		 else{result='<span class="label label-default">无效</span>';}
				            	 return result;
				             }},
				             
				            // {name : 'organizationName', index : 'organizationName'},
				             {name : 'roleName', index : 'roleName'},
				             {name : 'email', index : 'email',hidden:true},
				             {name : 'isAdmin', index : 'isAdmin',hidden:true},
				            
				             {name : 'userCode', index : 'userCode',hidden:true},
				             {name : 'lccCode', index : 'lccCode',hidden:true},
				             {name : 'projectId', index : 'projectId',hidden:true}],
				rowNum : 15, 
				rowList : [ 15, 30, 50,100,150,500 ], 
				height : "100%",
				autowidth : true, 
				pager : '#pager', 
				sortname : 'id',
				altRows:true,
				hidegrid : false, 
				viewrecords : true, 
				recordpos : 'left', 
				sortorder : "desc",
				emptyrecords : "没有可显示记录", 
				loadonce : false,
				multiselect:true,
				loadComplete : function() {},
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
		$("#grid").jqGrid(option);
		$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false,	position : 'right'})
		<shiro:hasPermission name="user:add">
		.navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
		</shiro:hasPermission>
		<shiro:hasPermission name="user:update">
		.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
		</shiro:hasPermission>
		<shiro:hasPermission name="user:delete">
		.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"})
		</shiro:hasPermission>
		<shiro:hasPermission name="user:resetpassword">
		.navButtonAdd('#pager',{caption:"重置密码",buttonicon:"ui-icon-disk",onClickButton: function(){toReSetPwd()},position:"last"})
		/* .navButtonAdd('#pager',{caption:"重置密码密钥",buttonicon:"ui-icon-disk",onClickButton: function(){toReSetPwd()},position:"last"}) */
		</shiro:hasPermission>
		.navButtonAdd('#pager',{caption:"数据权限",buttonicon:"ui-icon-disk",onClickButton: function(){toDataLimit()},position:"last"});
		//自适应
		jqgridResponsive("grid",false);
		
		
		//取消按钮操作
		$('#cancel').click(function(){
			$('#dialog-confirm').modal('hide');
		});	
		
		$('#cancel2').click(function(){
			$('#dialog-delete').modal('hide');
		});
		
		$("#cancel3").click(function(){
			$("#dialog-resetpwd").modal('hide');
		});
		
		$("#cancel4").click(function(){
			$("#data-limit").modal('hide');
		});
		
		//新增或修改操作
		$('#do_save').click(function(){
			var myform = $("#dialog-confirm").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			$("#do_save").attr("disabled",true);
			var myform = $("#dialog-confirm").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			var myrIds = "";
			$("#user_form [name=roleId]").each(function(){
				if($(this).is(":checked")){
					var id_ = $(this).val();
					myrIds += id_+",";
	   			}
			});
			if(''!=myrIds){
				myrIds=myrIds.substring(0,myrIds.length-1);
    			data.rIds = myrIds;
			}
			if(data.id ==""){addDate(data);}
			else{updateDate(data);}
		});
		
		$('#do_save4').click(function(){
			$("#do_save4").attr("disabled",true);
			var data = {};
			data.userId=$("#userId").val();
			var mypIds = "";
			var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
			var nodes = treeObj.getCheckedNodes(true);
			$.each(nodes, function(i, n){
				if(!n.isParent)
					mypIds+=n.id+",";
			});
			if('' != mypIds){
				mypIds = mypIds.substring(0,mypIds.length-1);
				data.destLcc = mypIds;
			}
			$.post("${ctx}/sys/rbac/user/updateDataLimit",data,function(result){
				$("#grid").trigger("reloadGrid", {page:1 });
				$('#data-limit').modal('hide');
				var message = "更新失败！";
				if(result.success){
					message = "更新成功！";
				}else{
					message = result.msg+'!';
				}
				$('#btn_resetpwd').attr("disabled",false);
				showResult(result.success,message);
			},'json');
			
		});
		
		//删除一条记录操作
		$('#do_delete').click(function(){
			
			var _ids = $("#grid").jqGrid('getGridParam','selarrrow');
			if($.isEmptyObject(_ids)) {
				openError('请选择一条数据',2000);
				return;
			}
			
			$("#do_delete").attr("disabled",true);
			
			var str='';
			for(var i=0; i<_ids.length; i++ ){
				if(str!=''){
					str +=','+_ids[i];
				}else{
					str +=_ids[i];
				}
			}
			//开始执行删除动作
			$.post("${ctx}/sys/rbac/user/delete", 
					{ids :str },
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
		
		$("#repwd_form").validate({
			rules:{
				r_password:{
					required:true,
					maxlength:30,
					minlength:6
				},
				r_password_again:{
					required:true,
					maxlength:30,
					minlength:6,
					equalTo: "#r_password"
				}
			},
			messages:{
				r_password:{
					required:'登录密码不能为空',
					maxlength:$.format('不能超过{0}个字符'),
					minlength:$.format('不能少于{0}个字符')
				},
				r_password_again:{
					required:'确认密码不能为空',
					maxlength:$.format('不能超过{0}个字符'),
					minlength:$.format('不能少于{0}个字符'),
					equalTo: "两次密码输入不一致"
				}
			},
			submitHandler:function(form) {
				resetpwd();
			}
		});
		
		$("#btn_resetpwd").click(function(){
			if(!$("#repwd_form" ).validate().form()){
				return;
			}
			resetpwd();
			//$('#repwd_form').submit();
		});
		$("#btnQuery").click(multipleSearch);
		
		$('#btnExport').click(function(){
			window.open('${ctx}/sys/rbac/user/exportexcel');
		});
		
	});
	
	
	//弹出新增对话框			
	function toAdd(){
		var timebak = new Date().getTime();
		openDialog("${ctx}/sys/rbac/user/openmodaluserinput?time="+timebak);
	}	

	//弹出修改对话框
	function toModify(){
		var ids = $("#grid").jqGrid('getGridParam','selarrrow');
		if($.isEmptyObject(ids)||ids.length >1) {
			openError('请选择一条数据,且仅选择一条数据',2000);
			return;
		}
		var oneData = $("#grid").jqGrid('getRowData',ids[0]); 
		openDialog("${ctx}/sys/rbac/user/openmodaluserinput?id="+oneData.id+"&timestamp="+(new Date()).valueOf());
	}

	//弹出删除对话框
	function toDelete(){
		var ids = $("#grid").jqGrid('getGridParam','selarrrow');
		if($.isEmptyObject(ids)) {
			openError('请选择要删除的数据',2000);
			return;
		}
		for(var i=0; i<ids.length; i++ ){
			var row = $("#grid").jqGrid('getRowData', ids[i]);
			if(row.isAdmin==2 || row.isAdmin ==1){
				openError('不能删除管理员',2000);
				return;
			}
		}
		$("#do_delete").attr("disabled",false);
		$("#dialog-delete").modal({
			 keyboard: false
		});
	}
	
	//弹出重置密码对话框
	function toReSetPwd(){
		var ids = $("#grid").jqGrid('getGridParam','selarrrow');
		if($.isEmptyObject(ids)||ids.length >1) {
			openError('请选择一条数据,且仅选择一条数据',2000);
			return;
		}
		var oneData = $("#grid").jqGrid('getRowData',ids[0]); 
		
		$( "#dialog-resetpwd").modal({
			 keyboard: false
		});
		$( "#btn_resetpwd").attr("disabled",false);
		$("#r_id").val(oneData.id);
		$("#r_name").val(oneData.name);
		$("#r_loginName").val(oneData.loginName);
		$.ajax({
            type: "post",
            async:false,
            url: "${ctx}/sys/rbac/user/resetKey",
            data: {lcc:oneData.lccCode,user: oneData.name},
            dataType: "json",
            success: function(data){
            	if ( data && data.code == '0' ){
            		$("#r_password").val(data.key);
            	} else {
            		alert(data.msg);
            	}          	
            }
        });
		
		//$("#r_password_again").val('');
		
	}
	function toDataLimit(){
		var ids = $("#grid").jqGrid('getGridParam','selarrrow');
		if($.isEmptyObject(ids)||ids.length >1) {
			openError('请选择一条数据,且仅选择一条数据',2000);
			return;
		}
		var oneData = $("#grid").jqGrid('getRowData',ids[0]);
		var timebak = new Date().getTime();
		$( "#data-limit" ).modal({
			 backdrop: 'static'
		});
		$( "#do_save4").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#data-limit p" ).load("${ctx}/sys/rbac/user/openmodaldatalimit?time="+timebak+"&userId="+oneData.id);
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
	
	//新增数据
	function addDate(data){
		//新增时注释id值
		//data['id'] = null;
		$.post("${ctx}/sys/rbac/user/save",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm').modal('hide');
			var message = "新增失败！";
			if(result.success){
				message ='新增成功!';
			}else{
				message = result.msg+'!';
			}
			$('#btn_resetpwd').attr("disabled",false);
			showResult(result.success,message);
		},'json');
	}
	
	//修改数据
	function updateDate(data){
	
		$.post("${ctx}/sys/rbac/user/update",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm').modal('hide');
			var message = "更新失败！";
			if(result.success){
				message = "更新成功！";
			}else{
				message = result.msg+'!';
			}
			$('#btn_resetpwd').attr("disabled",false);
			showResult(result.success,message);
		},'json');
	}
	
	//重置密码
	function resetpwd(){
		$("#btn_resetpwd").attr("disabled",true);
		
		$.post("${ctx}/sys/rbac/user/resetPassword", 
        		{ "id": $("#r_id").val(),'newPassword':$("#r_password").val()},
        		function(data){
        			$('#dialog-resetpwd').modal('hide');
        			$("#grid").trigger("reloadGrid");
        			var message = "更新失败！";
        			if(data.success){
        				message = "更新成功！";
        			}else{
        				messge = data.msg+'!';
        			}
        			$('#btn_resetpwd').attr("disabled",false);
        			showResult(data.success,message);
        		}, 
        		"json");
	}
	
    function openError(message,delay){
    	$('#alert').show().find('strong').text(message);
    	window.setTimeout(function() {
    		$('#alert').slideUp("slow");
    	//window.top.location.reload();
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
	
	//查询绑定
	var multipleSearch = function() {
	     var postData = $("#grid").jqGrid("getGridParam", "postData");  
	     var mydata = {};
	     var loginName =$("#s_loginName").val();
		 var userName=$("#s_userName").val();
		 
		 var lccName =$("#s_lccName").val();
		 if(loginName && ''!=loginName){
			 mydata.loginName = loginName;
		 }else{
			 delete postData.loginName;
		 }
	     if(userName && ''!= userName){
	    	 mydata.name = userName;
	     }else{
	    	 delete postData.name;
	     }
	     
	     if(lccName && ''!= lccName){
	    	 mydata.lccName = lccName;
	     }else{
	    	 delete postData.lccName;
	     }	
	    $.extend(postData,mydata);
	    $("#grid").jqGrid("setGridParam", {
	        search: true  
	    }).trigger("reloadGrid", [{page:1}]);
	};

	function hideError(){
		$('#alert').hide();
	}

	$(document).click(function(){
		hideError();
	});
</script>
</body>
</html>