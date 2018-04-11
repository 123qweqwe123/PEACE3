<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<title>组织机构管理</title>
<style type="text/css">
	.ztree li span.button.add {margin-left:2px; margin-right: -1px; background-position:-144px 0; vertical-align:top; *vertical-align:middle}
	
</style> 
</head>
<body>
<ul class="breadcrumb">
  <li class="active">系统管理</li><li class="active" >组织机构管理</li>
</ul>
<div class="row">
	<div class="col-lg-4 col-md-3">
	<ul id="permissionTree" class="ztree well"  style="height:498px; overflow:auto;"></ul>
	</div>
	
	<div class="col-lg-6 col-md-6">
		<div id="message" class="alert alert-success" hidden>
			<button data-dismiss="alert" class="close">&times;</button>
			<span id="messageSpanId"></span>
		</div>
		
		<div id="form_div" hidden>
			<form id="orgForm">
				<fieldset>
					<div class="form-group">
						<label for="parent"><span style="color:red">*</span>父节点:</label>
						<input type="text" class="form-control input-sm" id="parent" name="parent" readonly="readonly">
					</div>
					<div class="form-group">
						<label for="name"><span style="color:red">*</span>名称:</label>
						<input type="text" class="form-control input-sm" id="name" name="name" placeholder="名称" required>
					</div>
					<div class="form-group">
						<label for="type"><span style="color:red">*</span>类型</label>
						<select id="type" name="type" class="form-control input-sm" onchange="showPrincipal()">
							<!-- 
							<option value="1">主管</option>
							<option value="2">公司</option>
							<option value="3">部门</option>
							<option value="4">岗位</option>
							<option value="5">员工</option>
							 -->
							<c:forEach items="${orgTypeList }" var="orgType">
								<option value="${orgType.organType }" >${orgType.typeName }</option>
							</c:forEach> 
						</select>
					</div>
					<div class="form-group" id="selectPrincipal">
						<label for="principal">负责人:</label>
						<input type="text" class="form-control input-sm" name="principal" id="principal" maxlength="100" placeholder="负责人"/>
		    			<!-- 
		    			<input type="text" class="form-control input-sm" name="principalName" id="principalName" onkeydown="return false;" placeholder="负责人"/>
						<input type="hidden" class="form-control input-sm" name="principal" id="principal" />
			            <div id="principalZtree" class="tree-select"> 
					  		<div id="principal_ztree" class="ztree" style="height:190px;overflow-y:scroll;" ></div>
					    </div>
					     -->
					</div>
					<div class="form-group">
	                    <label for="remark">描述:</label>
	                    <textarea rows="3" name="remark" class="form-control" id="remark" placeholder="组织机构描述"></textarea>
	                </div>
				</fieldset>
				
				<input type="hidden" id="parentId" name="parentId">
				<input type="hidden" id="grade" name="Grade">
				<input type="hidden" id="id" value="">
				<input type="hidden" id="t_id">
				<div class="control-group">
					<div class="controls">
						<button type="button" id="btn_cancel" class="btn btn-default btn-sm">取消</button>
						<button type="submit" id="btn_submit" class="btn btn-primary btn-sm">添加</button>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>

<div id='confirmModal' class="modal fade">
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
      	<input type="hidden" id="del_id">
		<button type="button" data-dismiss="modal" class="btn btn-default btn-sm btn-cancel" tabindex="1001">取消</button>
        <button type="button" class="btn btn-primary btn-sm btn-confirm" tabindex="1000">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<script type="text/javascript">
///////////////////////////////////////////////////////负责人
var setting2 = {
	    async: {
	        enable: true,
	        type:"post",
	        url:"${ctx}/sys/rbac/organization/list",
	    },
	    view: {
	        showIcon: true
	    },
	    data: {
	        simpleData: {
	            enable: true,
	            idKey: "id",
	            pIdKey: "parentId"
	        },
	        key:{
	        	url:"_url"
	        }
	    } ,
	    callback: {
	        onClick:zTreeOnClick
	    }
	};

 function zTreeOnClick(event, treeId, treeNode){
	 if(treeNode.type==5){
    	$("#principalName").val(treeNode.name);
    	$("#principal").val(treeNode.id);
    	$("#principalZtree").toggle();
	 }
 }
    
$.fn.zTree.init($("#principal_ztree"), setting2);


$("#principalName").click(function(e){
    $("#principalZtree").css('width',$(this).outerWidth());
    $("#principalZtree").toggle();
});

//====================================================

	var setting = {
		async: {
			enable: true,
			type:"post",
			url:"${ctx}/sys/rbac/organization/list"
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id",
				pIdKey: "parentId"
			}
		} ,
		callback: {
			beforeDrag: beforeDrag,
			onAsyncSuccess: onAsyncSuccess,
			onRemove:onRemove
		},
		view:{
			addHoverDom: addHoverDom,
			removeHoverDom: removeHoverDom,
			expandSpeed: "slow",
			selectedMulti: false
		},
		edit: {
		
			showRemoveBtn:false,
			showRenameBtn: false,
            enable: true
        }
	};
	
	function beforeDrag(treeId, treeNodes) {
		return false;
	}
	
	var newCount = 1;
	function addHoverDom(treeId, treeNode) {
		var sObj = $("#" + treeNode.tId + "_span");
		<shiro:hasPermission name="orgmgt:add">
		
		if ($("#addBtn_"+treeNode.id).length>0) return;
		var addStr = "<span class='button add' id='addBtn_" + treeNode.id
			+ "' title='添加子节点' onfocus='this.blur();'></span>";			
		sObj.append(addStr);
		var btn = $("#addBtn_"+treeNode.id);
		if (btn) btn.bind("click", function(){
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			var newNode = {id:(100 + newCount), pId:treeNode.id, name:"新节点" + (newCount++),grade:(treeNode.grade+1),tId:treeId+'_new'+(newCount++)};
			add(treeId, newNode,treeNode);
		});
		if(treeNode.type == 5){
			$("#addBtn_"+treeNode.id).hide();
		}
		</shiro:hasPermission>
		<shiro:hasPermission name="orgmgt:update">
		var editStr = "<span class='button edit' id='editBtn_" + treeNode.id	+ "' title='修改' onfocus='this.blur();'></span>";
		sObj.append(editStr);
		var ebtn = $("#editBtn_"+treeNode.id);
		if(ebtn) ebtn.bind('click',function(e){
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			zTree.selectNode(treeNode);
			editOnClick(e,treeId,treeNode);
		});
		</shiro:hasPermission>
		
		<shiro:hasPermission name="orgmgt:delete">
		
		if(treeNode.grade!=0){
			var removeStr = "<span class='button remove' id='removeBtn_" + treeNode.id	+ "' title='删除' onfocus='this.blur();'></span>";
			sObj.append(removeStr);
			var rbtn = $("#removeBtn_"+treeNode.id);
			if(rbtn) rbtn.bind('click',function(e){
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				zTree.selectNode(treeNode);
				onRemove(e,treeId,treeNode);
			});
		}
		</shiro:hasPermission>
	};
	
	function removeHoverDom(treeId, treeNode) {
		var addbtn = $("#addBtn_"+treeNode.id);
		if(addbtn) addbtn.unbind().remove();
		var editbtn = $("#editBtn_"+treeNode.id);
		if(editbtn) editbtn.unbind().remove();
		var rbtn = $("#removeBtn_"+treeNode.id);
		if(rbtn) rbtn.unbind().remove();
	};
	
/*	function showRemoveBtn(treeId, treeNode) {
 		if(treeNode.grade==0){
			return false;
		}
		return true;
	} */
	

	function onAsyncSuccess(event, treeId, treeNode, msg) {
		
		var treeObj = $.fn.zTree.getZTreeObj(treeId);
		var node = treeObj.getNodeByParam("grade", 0, null);
		treeObj.expandNode(node,true,false,true);
	}


    /**
     * 删除结束
     * @param e
     * @param treeId
     * @param treeNode
     */
    function onRemove(e, treeId, treeNode) {
    	if(treeNode.grade==0){
    		showResult(false,"不能删除根节点");
    		return;
    	}
    	$("#del_id").val(treeNode.id);
    	$("#confirmModal").modal({ keyboard: false,backdrop:true});
    }
    
    function remove(){
    	$("#confirmModal :button").attr("disabled",true);
    	$('#confirmModal').modal('hide');
    	var id = $("#del_id").val();
    	$.post("${ctx}/sys/rbac/organization/delete", 
        		{ "id": id},
        		function(data){
        			var message = "删除失败！";
        			if(data.success){
        				message = "删除成功！";
        			}else{
        				message = data.msg+'!';
        			}
        			$("#confirmModal :button").attr("disabled",false);
        			showResult(data.success,message);
        		}, 
        		"json");
    }
    
    function onUpdate(){
    	$.post("${ctx}/sys/rbac/organization/update", 
        		{ "id": $("#id").val(),'name':$("#name").val(),remark:$("#remark").val(),type:$("#type").val(), principal:$("#principal").val()},
        		function(data){
        			var message = "更新失败！";
        			if(data.success){
        				message = "更新成功！";
        				$("#form_div").hide();
        			}else{
        				message = data.msg+'!';
        			}
        			showResult(data.success,message);
        		}, 
        		"json");
    }
    
    function editOnClick(event, treeId, treeNode) {
    	//var treeObj = $.fn.zTree.getZTreeObj(treeId);
    	//var node = treeObj.getNodeByParam("id", treeNode.parentId, null);
    	
    	var validator = $("#orgForm").validate();
    	validator.resetForm();

    	$("#form_div").show();
        var treeObj = $.fn.zTree.getZTreeObj(treeId);
    	var node =  treeObj.getNodeByParam("id", treeNode.parentId, null);
    	
    	var node1 = treeObj.getNodeByParam("id", treeNode.principal, null);
    	if(node){
	        $("#parent").val(node.name);
    	}
        $("#btn_submit").text('更新');
    	$("#name").val(treeNode.name);
    	$("#type").val(treeNode.type);
    	if(treeNode.type==4||treeNode.type==5){
    		$("#selectPrincipal").hide();
    	}else{
    		$("#selectPrincipal").show();
    	}
    	$("#principal").val(treeNode.principal);
    	if(node1){
	    	$("#principalName").val(node1.name);
    	}
    	$("#remark").val(treeNode.remark);
    	$("#moduleId").val(treeNode.moduleId);
    	$("#id").val(treeNode.id);
    	var tId = $("#t_id").val();
    	if(''!=tId){
    		var treeObj = $.fn.zTree.getZTreeObj(treeId);
    		var node = treeObj.getNodeByTId(tId);
    		treeObj.removeNode(node);
    		$("#t_id").val('');
    		
    	}
    	
    }
    

	$(function(){
		$("#org_nav").addClass('active');
		
		$("#orgForm").validate();

		$.fn.zTree.init($("#permissionTree"), setting);
		
		$('#confirmModal .btn-confirm').click(remove);
		
		$('#confirmModal .btn-cancel').click(removeForm);
		
		$("#selectPrincipal").show();
	});
	

	$("#orgForm").validate({
		rules:{
			name:{
				required:true,
				maxlength:20,
				minlength:3,
				remote:{
					type:'POST',
					url:'${ctx}/sys/rbac/organization/checkExists',
					dataType:'json',
					data:{
						name:function(){
							var l_n = $("#name").val();
							return l_n;
						},
						id:function(){
							var btn = $("#btn_submit");
							if('更新'==btn.text()){
								var iv = $("#id").val();
								return iv;
							}else{
								return '';
							}
						}
					},dataFilter: function(data) {
						
						if('true'==data){
							return false;
						}
						return true;
					}
				}
			},
			remark:{
				maxlength:200,
				minlength:4
			}
		},
		messages:{
			name:{
				required:'名称不能为空',
				maxlength:$.format('不能超过{0}个字符'),
				minlength:$.format('不能少于{0}个字符'),
				remote:'名称已存在'
			},
			remark:{
				maxlength:$.format('不能超过{0}个字符'),
				minlength:$.format('不能少于{0}个字符')
			}
		},
		submitHandler: function(form) {
			$("#orgForm :button").attr("disabled",true);
			
			var btn = $("#btn_submit");
			if('更新'==btn.text()){
				onUpdate();				
			}else{
				var options = {
			    	 	success:showResponse,
			    	 	url:'${ctx}/sys/rbac/organization/save',       
			            type:'post'
			    	 };
		        $(form).ajaxSubmit(options); 
			}
		}
	});
	
	function showResponse(responseText, statusText, xhr, $form)  {
	    if(responseText.success){
	    	removeForm();
	    	$("#messageSpanId").text('添加成功！');
			$("#message").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
	    }else{
	    	$("#messageSpanId").text('添加失败！');
			$("#message").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
	    }
	    window.setTimeout(function() {
			$('#message').slideToggle(1000);
		}, 2000);
	}
	
	function showResult(result,message){
    	$("#messageSpanId").text(message);
    	$("#orgForm :button").attr("disabled",false);
	    if(result){
			$("#message").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
	    }else{
			$("#message").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
	    }
	    window.setTimeout(function() {
			$('#message').slideToggle(1000);
		}, 2000);
	    
	    var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
	    treeObj.reAsyncChildNodes(null, "refresh");
	}
	
	function add(treeId,treeNode,parent){
		var formstatus = $('#form_div').is(':hidden');
		//if($(!formstatus && "#btn_submit").text()=='添加'){
		//	showResult(false,'请勿重复添加！');
		//	return;
		//}
		var validator = $("#orgForm").validate();
    	validator.resetForm();
		$("#parent").val(parent.name);
		$("#form_div").show();
		$("#btn_submit").text('添加');
		$("#name").val('');
		$("#remark").val('');
		$("#parentId").val(treeNode.pId);
		$("#grade").val(treeNode.grade);
		$("#id").val('');
		$("#t_id").val(treeNode.tId);
		$("#orgForm").attr('action','${ctx}/sys/rbac/organization/save').attr('method','POST');
	}
	
	$("#btn_cancel").click(function(){
		removeForm();
	});
	
	function removeForm(){
		$("#orgForm").resetForm();
		$("#parentId").val('');
		$("#grade").val('');
		$('#id').val('');
		$("#form_div").hide();
		$("#orgForm :button").attr("disabled",false);
		var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
		treeObj.reAsyncChildNodes(null, "refresh");
	}
	
	
	function showPrincipal(){
		if($("#type").val()==4 || $("#type").val()==5){
			$("#selectPrincipal").hide();
		}else{
			$("#selectPrincipal").show();
		}
	}
</script>
</body>
</html>