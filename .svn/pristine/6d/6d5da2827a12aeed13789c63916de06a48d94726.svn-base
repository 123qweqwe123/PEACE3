<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
	<title>入选人员管理</title>
	<style type="text/css">
		.leftLable{
			text-align:right;
		}
	</style>
	<script type="text/javascript">
		//查询绑定
		var multipleSearch = function(){ 
			var myform = $("#searchForm").serializeArray(); 
			var data = {};
			$.each(myform, function(i, field){
				data[field.name]=null; 
				if(field.value && ''!=field.value){
					data[field.name] = field.value;
				}
			});   
			var postData = $("#grid").jqGrid("getGridParam", "postData");  
	     
			$.extend(postData,data);
	
			$("#grid").jqGrid("setGridParam", {
				search: true  
			}).trigger("reloadGrid", [{page:1}]);
		};  
	
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
	    	if(e && e.keyCode==27){ // 按 Esc 
	      	}
	    	if(e && e.keyCode==113){ // 按 F2 
	     	}            
	     	if(e && e.keyCode==13){ // enter 键
	        	//multipleSearch();
	    	}
		};
	</script>
</head>
<body>
<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">修改手机号</h4>
	      </div>
	      <div class="modal-body">
	      		<p>加载中……</p>
	      </div>
			<div class="form-group">
				<center>
					<button type="button" id ='edit_save' class="btn btn-default btn-sm" tabindex="1000">提交</button>
					<button type="button" id ='edit_cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
				</center>
			</div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  
	
	<div id="navbar">
	       <ul class="breadcrumb">
	         <li class="active">非干预面访问卷</li>
	      </ul>
	</div>
	
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row"> 
					<div  class="leftLable col-md-1">医院名称:</div> 
					<div  class="col-md-2"> 
			          	<input id="lccCode" type="hidden" name="lccCode" />
						<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					<div  class="leftLable col-md-1">PID:</div> 
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='${patientId}' placeholder="患者ID"/>
					</div>
					
					<div  class="leftLable col-md-1">患者姓名:</div> 
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
					</div>


				</div>
				<div class="row" style="margin-top: 5px;">
						<div  class="leftLable col-md-1">随访类型:</div>
						<div  class="col-md-2">
							<select class="form-control" id="suifang" name="suifang">
								<option value="">请选择</option>
								<option value="002002">首次非干预面访</option>
								<option value="015">末次非干预面访</option>
							</select>
						</div>
						<div  class="leftLable col-md-1">随访状态:</div>
						<div  class="col-md-2">
							<select class="form-control" id="suifangtype" name="suifangtype">
								<option value="">请选择</option>
								<option value="0">开始答卷</option>
								<option value="2">继续答卷</option>
								<option value="1">已完成</option>
								<option value="3">已死亡</option>
							</select>
						</div>
					<div  class="col-md-3"></div>

					<div  class="col-md-3">
                        <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
                    </div>
                </div>
			</form>
		</div>
	</div>
	<form action="${ctx}/msg/join/joinUqs" id="answerQnForm" method="post">
		<input type="hidden" name="patientId" id="answerQnForm_patientId">
		<input type="hidden" name="uqsCode" id="answerQnForm_uqsCode">
	</form>
	
	<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	
	<div id="jqgrid">
    	<table id="grid"></table>
    	<div id="pager"></div>
	</div>
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
	
	var option = {
        url : '${ctx}/msg/noJoin/list',
        postData:{patientId:'${patientId}','processtypes':'1'},
        datatype : 'json',  
        mtype : 'POST',
        colNames : [ 'LCCID','PID','患者姓名','性别','固定电话','手机','是否参加短信项目','非干预首次面访',
//			'非干预6月面访',
			'非干预末次面访'], // '分组日期',
        colModel : [ 
					 {name : 'lccCode',width:'100px', index : 'LCC_CODE', align:'center'},
                     {name : 'patientId',width:'100px', index : 'PATIENT_ID', align:'center'},
 			         {name : 'patientName', width:'80px', index : 'patientName', align:'center',sortable: false  },
 			         {name : 'sex', index : 'sex',width:'60px', align:'center',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue=='1'){return '男';}return '女';} },
                     {name : 'phone',hidden:true,  index : 'phone',width:'100px', align:'center',sortable: false },
                     {name : 'mobile', index : 'mobile', align:'center',sortable: false },
//                     {name : 'groupDate', index : 'groupDate',width:'100px', align:'center',sortable: false,formatter:yymmddFormatter },
                     {name : 'isJoinMsg', align:'center',sortable: false,formatter:function (cellvalue,option,rowObjects){
                    	 if(cellvalue ==1) {
                    		 return "是";
                    	 }else{ // if(cellvalue ==2)
                    		  return "否";
                    	 }
                     } },
                     {name : 'state002002', align:'center',sortable: false,formatter:function (cellvalue,option,rowObjects){
                    	 if(cellvalue == 1){
                    		 return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','002')\">已完成</button>";
                    	 }else if( rowObjects.isDead == 1 ){
                             return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                         }else if(cellvalue == 2){
                    		 return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','002')\">继续答卷</button>";
                    	 }else if(cellvalue==null){
                    		 return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','002')\">开始答卷</button>";
                    	 }
                    	 return "";
                     } }
//					,{name:''}
					, {name:"state015", align:'center',width:'120px',sortable: false,
							formatter:function (cellvalue,option,rowObjects){
//								if(rowObjects.state002002 == '1'){
//									return "<button class='qnbtn' " +
//										"onclick=\"javascript:answerQn('"+rowObjects.patientId+"','014')\">开始答卷</button>";
//								}else{
//									return "";
//								}

                                if(cellvalue == 1){
                                    return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','015')\">已完成</button>";
                                }else if( rowObjects.isDead == 1 ){
                                    return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                                }else if(cellvalue == 2){
                                    return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','015')\">继续答卷</button>";
                                }else if(cellvalue==null && rowObjects.state002002 =='1'){

                                    if( rowObjects.betweenDays != null && (rowObjects.betweenDays - 0) >= 150  ) {

                                        return "<button class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.patientId + "','015')\">开始答卷</button>";
                                    }else{
                                            return "<button hidden class='qnbtn' style='background:#D5D5D5'" +
                                                ">开始答卷</button>";
									}
                                }
                                return "";

							}
						}
                    ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'PATIENT_ID',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "ASC",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        multiselect: false,
        jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false,
			cell : "cell",
			id : "patientId"
		}
 	};  
	$("#grid").jqGrid(option); 
	$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'}
	).navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"});
	jqgridResponsive("grid",false); 
	
	//取消按钮操作
	$('#cancel').click(function(){
		$('#dialog-confirm').modal('hide');
		
	});	

	$("#btnQuery").click(multipleSearch);
	
	if("${errorMsg}" != ""){
		showResult(false,"${errorMsg}");
	}
	
});
	function answerQn(patientId,uqsCode){
		$("#answerQnForm_patientId").val(patientId);
		$("#answerQnForm_uqsCode").val(uqsCode);
		$("#answerQnForm").submit();
	}

	function toModify(){
		var timebak = new Date().getTime();
		var _ids = $("#grid").jqGrid('getGridParam','selrow');
		if(_ids.length==0){
			showResult(false,"请选择一条记录");
			return;
		}
		openDialog("${ctx}/msg/join/openmodal/toEdit?patientId="+_ids+"&time="+timebak,"dialog-confirm");
	}
	
	function openDialog(url,data){
		$( "#dialog-confirm" ).modal({
			 backdrop: 'static'
		});
		$( "#do_add").attr("disabled",false);
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


	$('#edit_save').click(function(){
		var myform = $("#dialog-confirm").find("form").get(0);
		if(!jQuery(myform).validate().form()){ return;}

		$("#edit_save").attr("disabled",true);
		var data = {};
		data.patientId = $('#patientId_').val();
		data.mobile = $('#mobile_').val();

		$.post("${ctx}/msg/join/update",data,function(result){
			if(result && result !='undefinded' &&result.success==true){
				$('#dialog-confirm').modal('hide');
				$("#grid").trigger('reloadGrid');
				showResult(true,'保存成功');
			}else{
				showResult(false,'保存失败');
			}
		},'json');
		$("#edit_save").attr("disabled",false);
	});

	$("#edit_cancel").click(function(){
		$('#dialog-confirm').modal('hide');
	});

</script>


</body>





</html>