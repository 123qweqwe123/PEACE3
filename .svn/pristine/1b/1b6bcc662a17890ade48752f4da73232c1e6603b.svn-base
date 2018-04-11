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
		<li class="active">单位用户管理</li>
	</ul>
	
	<div id="select">
        <div class="select-main">
              <form action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>研究人员:</label></li>
                         <li> 
                         <input type="text" id="name" name="name" class="form-control input-sm" placeholder="研究人员"/>
                         </li>
                         <li class="select-one"> <label form='lccRole'>角色:</label></li>
                         <li> 
		                   <select id="lccRole" name="lccRole" class="form-control input-sm">
							<option value="-1">请选择</option>
							<option value="课题负责人">课题负责人</option>
							<option value="研究医生">研究医生</option>
							<option value="研究助理">研究助理</option>
						   </select>
                         </li>
                         <li class="select-one"> <label form='lccRole'>医院名称:</label></li>
                         <li> 
                         <input type="hidden" id="lccCode" name="lccCode">
						 <input type="text" id="lccName" name="lccName"   class="form-control input-sm" placeholder="输入单位简拼与LCCID">
                         <!--  
				            <select id="lccCode" name="lccCode" class="form-control input-sm">
								<option value="">请选择单位</option>
								<c:forEach items="${lccDictList }" var="lccDict">
									<option value="${lccDict.lccCode }" >${lccDict.lccName }</option>
								</c:forEach>
							</select>
							-->
                         </li>
                        <li> <button type="button" onclick="searchLccUser();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                        <li> <button type="button" onclick="outExcelLccUser();" class="btn btn-primary btn-align-right btn-sm">EXCEL导出</button></li>
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
    
    <div class="prediv">
    <div id='dialog-confirm' class="modal fade ">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">单位用户信息</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel' class="btn btn-default btn-sm" data-dismiss="modal" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	</div>
	

	<div id='dialog-confirm2' class="modal fade "  >   <!-- data-backdrop="static" -->
	  <div class="modal-dialog" >
	    <div class="modal-content">
	      <div class="modal-header">
	       <button type="button" class="close removeclose" data-dismiss="modal" aria-label="close" aria-hidden="true">&times;</button> 
	        <h4 class="modal-title">单位用户信息</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel_user' class="btn btn-default btn-sm " data-dismiss="modal" tabindex="1001">取消</button>
	        <button type="button" id ='do_save_user' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
			<input id="hiddenLccCode" name="hiddenLccCode" value="" type="hidden" />
		<input id="hiddenLccName" name="hiddenLccName" value="" type="hidden" />
	
	<script type="text/javascript">
		$(function(){
			
			$(".removeclose").on("click", function() {
			     parent.location.reload();
				// $("div").removeClass("in");
			});
			

			var option = {
					url : '${ctx}/pro/lccuser/rcclist',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '','LCCID','医院名称','研究人员','联系电话','手机','Email','电子签名表','简历','培训', '激活','角色描述','参研','',''],
					colModel : [
							{
								name : 'userCode',
								index : 'USER_CODE',
								hidden:true
							},{
								name : 'lccCode',
								index : 'lcc_code',
								align : 'left'
							},
							{
								name : 'lccName',
								index : 'lcc_code',
								width: 250,
								align : 'left'
							},
							{
								name : 'name',
								index : 'name',
								align : 'left'
							},
							{
								name : 'phone',
								index : 'phone',
								align : 'left'
							},
							{
								name : 'mobile',
								index : 'mobile',
								width: 110,
								align : 'left'
							},
							{
								name : 'email',
								index : 'email',
								width: 220,
							},
							{
								name : 'isSignSigna',
								index : 'is_sign_signa',
								align: 'center',
								width: 100,
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if ("1" == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								}
							},
							{
								name : 'isGetResume',
								index : 'is_get_resume',
								align: 'center',
								width: 100,
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								}
							},
							{
								name : 'isJoinTraining',
								index : 'is_join_training',
								align: 'center',
								width: 100,
								formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if (1 == cellvalue) {
										result = '<span class="label label-success">是</span>'
									} else {
										result = '<span class="label label-default">否</span>';
									}
									return result;
								}
							},
							{
								name : 'status',
								index : 'status',
								align: 'center',
								width: 100,
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
								name:'lccRole',
								index:'lcc_role',
								width: 100
								
							},{
								name:'researchStatus',
								index:'research_status',
								width: 100,
									formatter : function(cellvalue, option, rowObjects) {
										var result = '';
										if (1 == cellvalue) {
											result = '<span class="label label-success">参研</span>';
										} else {
											result = '<span class="label label-default">离研</span>';
										}
										return result;
									}
							},{
								name:'projectId',
								index:'project_id',
								hidden:true
							},{
								name:'lccCode',
								index:'lcc_code',
								hidden:true
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pager',
					sortname : 'lcc_code',
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
						id : "userCode"
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
					.navButtonAdd('#pager',{caption:"激活",buttonicon:"ui-icon-plus",onClickButton: function(){toChangeStatus('1')},position:"last"})
					.navButtonAdd('#pager',{caption:"失活",buttonicon:"ui-icon-pencil",onClickButton: function(){toChangeStatus('2')},position:"last"});
				//自适应
				jqgridResponsive("grid", false);
				
				//取消按钮操作
				$('#cancel').click(function(){
					 // parent.location.reload();
					//$('#dialog-confirm').modal('hide');
					// $("div").removeClass("modal-backdrop fade in");
					$('#dialog-confirm').remove();
					 $(".in").remove();
					
					
				});	
				//取消按钮操作
				$('#cancel_user').click(function(){
					$('#dialog-confirm2').modal('hide');
					// parent.location.reload();
					 $(".in").remove();
					
				});	
				$('#cancel2').click(function(){
					$('#dialog-delete').modal('hide');
				});
		});
		
		
		//保存人员js
		//新增或修改操作
		$('#do_save_user').click(function(){
			var myform = $("#dialog-confirm2").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			$("#do_save_user").attr("disabled",false);
			var myform = $("#dialog-confirm2").find("form").serializeArray();
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
			addDateUser(data);
			
		});
		//新增数据
		function addDateUser(data){
			//新增时注释id值
			$.post("${ctx}/sys/rbac/user/save",data,function(result){
				//$("#grid").trigger("reloadGrid");
				$('#dialog-confirm2').modal('hide');
				 
				var message = "新增失败！";
				if(result.success){
					message ='新增成功!';
					
				}else{
					message = result.msg+'!';
				}
				 $('#do_save_user').attr("disabled",false);
				// $("div").removeClass("in");
				 if(typeof($(".modal-backdrop fade in"))!="undefined"){
						//$(".modal-backdrop fade in").remove();
					 $(".in").remove();
					}
				showResult(result.success,message);
				//parent.location.reload(); 
				
			},'json');
		}
		
		//弹出新增对话框			
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/lccuser/openmodallccuserinput?lccRoleType=2&time="+timebak);
		}	
		function toModify(){
			var userCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(userCode)) {
				openError('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/pro/lccuser/openmodallccuserinput?lccRoleType=2&userCode="+userCode+"&time="+timebak);
		}
		function searchLccUser() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var name =$("#name").val();
		      var lccCode = $("#lccCode").val();
		      var lccRole = $("#lccRole").val();
		      if(name && ''!=name){
		          mydata.name = name;
		      }else{
		          delete postData.name;
		      }
		      if(lccCode && ''!=lccCode){
		    	  mydata.lccCode = lccCode;
		      }else{
		    	  delete postData.lccCode;
		      }
		      if(lccRole && ''!=lccRole){
		    	  mydata.lccRole = lccRole;
		      }else{
		    	  delete postData.lccRole;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid");
		 };
		function toChangeStatus(data) {
			var userCode = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(userCode)) {
				if(data == '1')	openError('请选择要激活的记录！',2000);
				if(data == '2')	openError('请选择要失活的记录！',2000);
				return;
			}
			var rowData = $("#grid").jqGrid('getRowData',userCode);
			if(rowData.status.indexOf('否')>0&&data=='2'){
				openError('该用户已经是未激活状态！',2000);
				return;
			}

			if(data == '1' && rowData.status.indexOf('是')>0){
				openError('该用户已经是激活状态！',2000);
				return;
			}
			
			$.post("${ctx}/pro/lccuser/changeStatus",{userCode: userCode, status: data, projectId: rowData.projectId,lccCode:rowData.lccCode},function(result){
				var message = "操作失败!";
				if(!result.isLccActive){
					message = "医院名称未激活，该用户不能被激活！";
				}
				 if(result.msg && result.msg.length>0){
					message = result.msg;
			     } 
                if(result.success){
					$("#grid").trigger("reloadGrid");
					message = "操作成功！";
				
				}
				//$("#grid").trigger("reloadGrid");
				showResult(result.success,message);
			},'json');
			
		}
		//弹出对话框
		function openDialog(url,data){
			if(typeof($("#dialog-confirm").html())=="undefined"){
				$(".prediv").html( 
						
						'<div id="dialog-confirm" class="modal fade ">'
						+'  <div class="modal-dialog"> '
						+'  <div class="modal-content"> '
						+'      <div class="modal-header"> '
						+'        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button> '
						+'        <h4 class="modal-title">单位用户信息</h4> '
						+'      </div> '
						+'      <div class="modal-body"> '
						+'        <p>加载中……</p> '
						+'      </div> '
						+'     <div class="modal-footer"> '
						+'		<button type="button" id ="cancel" class="btn btn-default btn-sm" data-dismiss="modal" tabindex="1001">取消</button> '
						+'        <button type="button" id ="do_save" class="btn btn-primary btn-sm" onClick="do_save()" tabindex="1000">提交</button> '
						+'      </div> '
						+'    </div> '
						+'  </div> '
						+' </div> '
						
				);
				$( "#do_save").attr("disabled",false);
			}
			
			
			$( "#dialog-confirm" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm p" ).load(url);
			
			
		}
		//弹出对话框
		function openDialog2(url,data){
			
			$( "#dialog-confirm2" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save_user").attr("disabled",false);
			    //使用此方法防止js缓存不加载
		    $("#dialog-confirm2 p" ).load(url);
		   
		}
		$('#do_save').click(function(){
			
			var myform = $("#dialog-confirm").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			$("#do_save").attr("disabled",false);
			var myform = $("#dialog-confirm").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			if(data.userCode == ""){addDate(data);}
			else{updateDate(data);}
		});
         function do_save(){
			var myform = $("#dialog-confirm").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			$("#do_save").attr("disabled",false);
			var myform = $("#dialog-confirm").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			if(data.userCode == ""){addDate(data);}
			else{updateDate(data);}
         }
		
		
		
		function addDate(data){
			$.post("${ctx}/pro/lccuser/addLccUser",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "新增失败！";
				if(result.success){
					message ='新增成功!';
					//模拟触发
					$( "#do_save").attr("disabled",false);
					$('#dialog-confirm').remove();
					$('#dialog-confirm2').modal('show');
					var timebak = new Date().getTime();
					 var hiddenLccCode = $('#hiddenLccCode').val();
					 var hiddenLccName =$('#hiddenLccName').val();
					openDialog2("${ctx}/pro/lccuser/openmodaluserinputfromlccuser?time="+timebak+"&lccCode="+hiddenLccCode+"&userCode="+result.userCode+"&lccName="+encodeURI(encodeURI(hiddenLccName))+"&name="+encodeURI(encodeURI(data.name)));
					//openDialog2("${ctx}/pro/lccuser/openmodaluserinputfromlccuser?time="+timebak);
				}
				showResult(result.success,message);
			},'json');
		}
		function updateDate(data){
			$.post("${ctx}/pro/lccuser/addLccUser",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "更新失败！";
				if(result.success){
					message = "更新成功！";
				}else{
					message = result.msg+'!';
				}
				showResult(result.success,message);
			},'json');
		}
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
		
		function outExcelLccUser(){
			
			window.location.href ='${ctx}/pro/lccuser/outExcelLccUser?lccRoleType=2';
		}
		
		
			//所属省份
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