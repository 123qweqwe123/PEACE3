<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<c:set var="currUserLcc" value="<%=Securitys.getUser().getLccCode()%>"/>
<!DOCTYPE html>
<html>
<head>
 <title>患者管理</title>
 <script  src="${ctx}/static/echarts/esl/esl.js"></script> 
 	<script type="text/javascript">

		
	
	
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
	    	if(e && e.keyCode==27){ // 按 Esc 
	        	//要做的事情
	      	}
	    	if(e && e.keyCode==113){ // 按 F2 
	         	//要做的事情
	     	}            
	     	if(e && e.keyCode==13){ // enter 键
	        	 //要做的事情
	        	//multipleSearch();
	    	}
		};

	</script>
	<style>
		.img_mystyle{
			width: 20px;
			height: 20px;
		}
	</style>
</head>
<body>

<div id="navbar">
       <ul class="breadcrumb">
         <li class="active">患者管理</li>
 
      </ul>
</div>
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row">

					<div  class="leftLable col-md-1" style="text-align: right">医院名称:</div>
					<div  class="col-md-2">
						<%--  <select id="lccCode" name="lccCode" class="form-control input-sm">
                       <option value="">请选择单位</option>
                       <c:forEach items="${lccDictList }" var="lccDict">
                       <option value="${lccDict.lccCode }" >${lccDict.lccName }</option>
                       </c:forEach>
                         </select> --%>
						<input id="lccCode" type="hidden" name="lccCode" />
						<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					<div  class="leftLable col-md-1" style="text-align: right">PID:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>
					
					<div  class="leftLable col-md-1" style="text-align: right">患者姓名:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
					</div>

                   <!--
                    <div  class="leftLable col-md-2">预约检查时间:</div>
					<div  class="right col-md-2">
						<input type="text" class="form-control input-sm" name="planTime2" id="planTime2" value='' placeholder=""/>
					</div>
					 -->
					<div  class="leftLable col-md-1"></div> 
                    <div  class="col-md-2"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
					</div>
				
				</div>
				
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
<div id="jqgrid">
    <table id="grid"></table>
    <div id="pager"></div>
</div>


<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">患者新增页面</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-confirm2' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">患者修改页面</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_update' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

<script type="text/javascript">

$('#cancel3').click(function(){
	$('#dialog-delete').modal('hide');
});

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
	
	
	function initProvince(){
		$.getJSON("${ctx}/combox/province",function(data) { 
		    $('#provinceName').autocomplete(data,{
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
		    $('#provinceName').result(function(event, item){ 
				if(item){
					if(item.PROVINCE_CODE != $("#provinceCode").val()){
						$("#provinceCode").val(item.PROVINCE_CODE);
						initRcc();
					}
				}else{
					$("#provinceCode").val("");
					initRcc();
				}
		     });
		});
		initRcc();
	}


	function initRcc(){
		$('#rccName').unautocomplete();
		$("#rccName").val(''); 
		$("#rccCode").val('');
		
		$.getJSON("${ctx}/combox/rcc?provinceCode="+$("#provinceCode").val(),function(data) { 
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
		            return item.HELP_CODE+ item.RCC_CODE+item.RCC_NAME;
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
						initLcc();
					}
				}else{
					$("#rccCode").val("");
					initLcc();
				}
		     });
		});
		initLcc();
	}

	function initLcc(){
		$('#lccName').unautocomplete();
		$("#lccName").val(''); 
		$("#lccCode").val(''); 
		
		$.getJSON("${ctx}/combox/dataLimitLcc?provinceCode="+$("#provinceCode").val()+"&rccCode="+$("#rccCode").val(),function(data) { 
		    $('#lccName').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.LCC_CODE + '</td><td width="180px;">' + item.LCC_NAME + '</td></tr></table>';
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.HELP_CODE+ item.LCC_CODE+item.LCC_NAME;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.LCC_NAME;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#lccName').result(function(event, item){ 
				if(item){
					if(item.LCC_CODE != $("#lccCode").val()){
						$("#lccCode").val(item.LCC_CODE);
					}
				}else{
					$("#lccCode").val("");
				}
		     });
		});
	}

	var multipleSearch = function(){
		 var postData = $("#grid").jqGrid("getGridParam", "postData");  
		 var myform = $("#searchForm").serializeArray(); 
		 var mydata = {};
		 
		 var lccCode =$("#lccCode").val();
	      if(lccCode && ''!=lccCode){
	          mydata.lccCode = lccCode;
	      }else{
	          delete postData.lccCode;
	      }
	      
	      var patientId =$("#patientId").val();
	      if(patientId && ''!=patientId){
	          mydata.patientId = patientId;
	      }else{
	          delete postData.patientId;
	      }
	      
	      var patientName =$("#patientName").val();
	      if(patientName && ''!=patientName){
	          mydata.patientName = patientName;
	      }else{
	          delete postData.patientName;
	      }
	      
	      
	      
	   
	     $.extend(postData,mydata);
	     $("#grid").jqGrid("setGridParam", {
	         search: true  
	     }).trigger("reloadGrid", [{page:1}]);
	
		
	};  
	
	var option = {
        url : '${ctx}/sys/drop/queryPatientList',
        datatype : 'json',  
        mtype : 'POST',
        colNames : [ 'PID','患者姓名','性别','身份证','固定电话','手机号码','第一联系人姓名','与本人关系','第一联系人手机','短信干预','6月随访问卷','医院名称',''],
        colModel : [ 
                     {name : 'patientId',width:'100px', index : 'patientId', align:'center',sortable: false },
 			         {name : 'patientName', width:'80px', index : 'patientName', align:'center',sortable: false  },
 			         {name : 'sex', index : 'sex',width:'60px', align:'center',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue=='1'){return '男';}return '女';} },
 			         {name : 'idNumber', index : 'idNumber', align:'center',sortable: false },
                     {name : 'phone', index : 'phone',width:'100px', align:'center',sortable: false },
                     {name : 'mobile', index : 'mobile', align:'center',sortable: false },
                     {name : 'linkMan1', align:'center',sortable: false },
                     {name : 'linkMan1Relation',  align:'center',sortable: false },
                     {name : 'linkMan1Mobile', align:'center',sortable: false },
					{name : 'isJoinMsg',  align:'center',sortable: false ,
						formatter:
							function(cellvalue, option, rowObjects)
							{ if(cellvalue=='1')
							   {
								return '参加';
							   }else{
                                <c:choose>
                                    <c:when test="${currUserLcc  eq '99' }">
                                        return "<button class='qnbtn' onclick=\"javascript:showform('"+rowObjects.lccCode+"','" + rowObjects.patientId + "','1')\">不参加</button>";
                                    </c:when>
                                    <c:otherwise>
                                        return '不参加';
                                    </c:otherwise>
                                </c:choose>
							   }
							}
					},
					{name : 'is6State',   align:'center',sortable: false ,
						formatter:
								function(cellvalue, option, rowObjects)
								{ if(cellvalue=='1')
								{
									return '可填写';
								}else{
                                    <c:choose>
                                    <c:when test="${currUserLcc  eq '99' }">
                                    return "<button class='qnbtn' onclick=\"javascript:showform('"+rowObjects.lccCode+"','" + rowObjects.patientId + "','2')\">不可填写</button>";
                                    </c:when>
                                    <c:otherwise>
                                    return '不可填写';
                                    </c:otherwise>
                                    </c:choose>

								}
								}
					},
					{name : 'lccName', index : 'lccName', align:'center',sortable: false },
					{name : 'lccCode', index : 'lccCode',hidden:true }

		],
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'patient_Id',
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
	).navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"right"})
	.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toUpdate()},position:"last"});
	jqgridResponsive("grid",false); 
	//取消按钮操作
	$('#cancel').click(function(){
		$('#dialog-confirm').modal('hide');
		
	});	
	//取消按钮操作
	$('#cancel2').click(function(){
		$('#dialog-confirm2').modal('hide');
		
	});	
	$("#btnQuery").click(multipleSearch);

	
	
	/**
	       新增的记录入库	
	**/
	$('#do_save').click(function(){
		var data = {};
		var myform = $("#dialog-confirm").find("form").get(0);
		if(!jQuery(myform).validate().form()){ return ;}
		$("#do_update").attr("disabled",false);
		var myform = $("#dialog-confirm").find("form").serializeArray();
		var data = {};
		$.each(myform, function(i, field){
			data[field.name] = field.value;
			if(field.name=='linkMan1Relation'){
				if(field.value=='其他'){
					data[field.name] = $("#linkMan1Relation_other_1").val();
				}
			}
			console.info(field);
		});
		$("#do_save").attr("disabled",true);
	       add(data);
		
	});
	
	/**
	   修改的记录入库	
	**/
	$('#do_update').click(function(){
		//var postData = $("#grid").jqGrid("getGridParam", "postData");  
		//var myform = $("#searchForm").serializeArray(); 
		var data = {};
		//var patientId = $("#patientId").val();
		//alert(patientId);
		//if(patientId!='' && patientId!='undedfinded'){
		//	  data.patientId =patientId;
		//	}else{
		//		delete postData.patientId;
		//	}
		var myform = $("#dialog-confirm2").find("form").get(0);
		if(!jQuery(myform).validate().form()){ return ;}
		$("#do_update").attr("disabled",false);
		var myform = $("#dialog-confirm2").find("form").serializeArray();
		var data = {};
		$.each(myform, function(i, field){
			data[field.name] = field.value;
			if(field.name=='linkMan1Relation'){
				if(field.value=='其他'){
					data[field.name] = $("#linkMan1Relation_other").val();
				}
			}
		});
		
		$("#do_update").attr("disabled",true);
		$.post("${ctx}/sys/drop/openmodalUpdate",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm2').modal('hide');
			var message = "修改失败！";
			if(result){
				message ='修改成功!';
			}
			$("#do_update").attr("disabled",false);
			showResult(result,message);
		},'json');
		
	});
	
	//新增数据
	function add(data){
		//新增时注释id值
		//data['id'] = null;
		$.post("${ctx}/sys/drop/openmodalAdd",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm').modal('hide');
			var message = "添加失败！";
			if(result.success){
				message ='添加成功!';
			}
			else{
				message = result.msg;
			}
			$("#do_save").attr("disabled",false);
			showResult(result,message);
		},'json');
	}
	
	
	function toUpdate(){
		
	  var id = $("#grid").jqGrid('getGridParam','selrow');//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
	  var rowData = $("#grid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
      var patientId= rowData.patientId ; 

      var lccCode= rowData.lccCode ;

		var patientId = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(patientId)) {
			openError('请选择一条数据',2000);
			return;
		}
		$("#do_update").attr("disabled",true);
		var timebak = new Date().getTime();
		openDialog2("${ctx}/sys/drop/openmodalUpdatePage?patientId="+patientId+'&lccCode='+lccCode+'&time='+timebak);
	}
	
	
	
	function toAdd(){
		$("#do_add").attr("disabled",true);
		var timebak = new Date().getTime();
		openDialog("${ctx}/sys/drop/openmodalAddPage?timebak="+timebak);
	}
	
	//弹出对话框
	function openDialog(url,data){
		$( "#dialog-confirm" ).modal({
			 backdrop: 'static'
		});
		$( "#do_add").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm p" ).load(url);
	}
	//弹出对话框
	function openDialog2(url,data){
		$( "#dialog-confirm2" ).modal({
			 backdrop: 'static'
		});
		$( "#do_update").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm2 p" ).load(url);
	}
	
	
	function openError(message,delay){
    	$('#alert').show().find('strong').text(message);
    	window.setTimeout(function() {
    		$('#alert').slideUp("slow");
    	}, delay);
    }
	
	function showResult_1(result,message,delay){
		$("#messageSpanId_1").text(message);

		if (!delay || typeof(delay)=="undefined" || typeof(delay)!='number'){
			delay = 2000;
		}
	    if(result){
			$("#message_1").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
	    }else{
			$("#message_1").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
	    }
	    window.setTimeout(function() {
			$('#message_1').slideToggle(1000);
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

	$('#change_cancel').click(function(){
		$('#dialog-showform').modal('hide');
	});
	$('#change_save').click(function(){
		var myform = $("#dialog-showform").find("form").get(0);
		if(!jQuery(myform).validate().form()){ return;}
		var myform = $("#dialog-showform").find("form").serializeArray();
		var data = {};
		$.each(myform, function(i, field){
			data[field.name] = field.value;
		});
		
		$("#change_save").attr("disabled",true);
		$.post("${ctx}/sys/drop/savechange",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-showform').modal('hide');
			var message = "修改失败！";
			if(result){
				message ='修改成功!';
			}
			$("#change_save").attr("disabled",false);
			showResult(result,message);
		},'json');
	});
});
function showform(lccCode , patientId , type){
	openDialog3("${ctx}/sys/drop/openmodalChange?lccCode="+lccCode+"&patientId="
			+patientId+"&type="+type+"&time="+new Date().getTime());
}
//弹出对话框
function openDialog3(url){
$( "#dialog-showform" ).modal({
	 backdrop: 'static'
});
$( "#change_save").attr("disabled",false);
//使用此方法防止js缓存不加载
$("#dialog-showform p" ).load(url,{},function(){
	$("#changeForm").validate({
		rules: {
			val:{
				required:true
			},
			person:{
				required:true,
                maxlength : 25
            },
			changedate:{
				required:true
			},
			bz:{
				required:true,
				minlength : 5,
                maxlength : 512
			}
		},
		messages:{
			val:{
				required:'请选择修改的状态'
			},
			person:{
				required:'请填写申请人且长度不超过10个字',
				maxlength:'最大长度不能超过{0}个汉字'
			},
			changedate:{
				required:'请填写申请时间'
			},
			bz:{
				required:'请填写备注信息且长度不少于5个字,不多于512个字'
			}
		},
        errorPlacement : function(error, element) {
            if (element.is(":radio")){
//                $("<br>").appendTo(element.parent().parent());
//                error.appendTo(element.parent().parent());
                error.appendTo($("#error"));
            }else{
                error.appendTo(element.parent());
            }
//            else if (element.is(":checkbox"))
//                error.appendTo(element.parent().parent());
//            else
//                error.appendTo(element.parent().next());
        }
	});
	$('#person').unautocomplete();
	var url = "${ctx}/combox/getautodata?type=person&lccCode="+$("#grid").jqGrid('getRowData',$("#grid").jqGrid('getGridParam','selrow')).lccCode;
	$('#person').autocomplete(url, {
		max:10,
		minChars:0,
		matchContains: true,
		formatItem: function(row) {
			if( row == null || row == '' ){
				return "";
			}
			return row[0]+"|"+row[1];
		},
        formatResult: function(row) {
            return row.join('|');
        }
	}).result(function(event,row,formatted){
		console.log("123");

	});
});
}
</script>

<div id='dialog-showform' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	     <p></p>
	      <div class="modal-footer">
			<button type="button" id ='change_cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='change_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
</body>





</html>