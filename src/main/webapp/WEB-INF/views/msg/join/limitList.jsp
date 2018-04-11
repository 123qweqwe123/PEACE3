<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<c:set var="currUserLcc" value="<%=Securitys.getUser().getLccCode()%>"/>
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
		<div class="modal-footer">
			<button type="button" id ='edit_cancel' class="btn btn-default btn-sm" tabindex="1001">
				取消</button>
			<button type="button" id ='edit_save' class="btn btn-primary btn-sm" tabindex="1000">
				提交</button>
		</div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->  
	
	<div id="navbar">
	       <ul class="breadcrumb">
	         <li class="active">入选人员管理</li>
	      </ul>
	</div>
	
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row"> 
					<div  class="leftLable col-md-1" style="text-align: right" >医院名称:</div>
					<div  class="col-md-2"> 
			          	<input id="lccCode" type="hidden" name="lccCode" />
						<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					<div  class="leftLable col-md-1" style="text-align: right" >PID:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>
					
					<div  class="leftLable col-md-1" style="text-align: right" >患者姓名:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="11" value='' placeholder="患者姓名"/>
					</div>

					<c:if test="${currUserLcc  eq '99' }" >
					<div  class="leftLable col-md-1">身份证:</div>
					<div  class="col-md-2">
						<input type="text" class="form-control input-sm" id="idNumber" name="idNumber" value='' placeholder="身份证"/>
					</div>
					</c:if>
			</div>
			<div class="row" style="margin-top: 5px;">
				<div  class="leftLable col-md-1" style="text-align: right" >随访类型:</div>
				<div  class="col-md-2">
					<select class="form-control" id="suifang" name="suifang">
						<option value="">请选择</option>
						<option value="010">糖尿病筛查问卷</option>
						<option value="011">基线问卷</option>
						<option value="012">随机问卷</option>
						<%--<option value="000">干预6月随访问卷</option>--%>
						<option value="014">末次随访问卷</option>
					</select>
				</div>
				<div  class="leftLable col-md-1" style="text-align: right" >随访状态:</div>
				<div  class="col-md-2">
					<select class="form-control" id="suifangtype" name="suifangtype">
						<option value="">请选择</option>
						<option value="0">开始答卷</option>
						<option value="2">继续答卷</option>
						<option value="1">已完成</option>
						<option value="3">已死亡</option>
					</select>
				</div>

					<c:if test="${currUserLcc  eq '99' }" >
					<div  class="leftLable col-md-1">是否糖尿病:</div>
					<div  class="col-md-2">
						<select class="form-control input-sm" id="isdiabetes" name="isdiabetes" placeholder="请选择">
							<option value ="">全部</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</div>

					<div  class="leftLable col-md-1">所属组别:</div>
					<div  class="col-md-2">
						<select class="form-control input-sm" id="belongGroup" name="belongGroup" placeholder="请选择">
							<option value ="">全部</option>
							<option value="01">非糖尿病实验组</option>
							<option value="02">非糖尿病对照组</option>
							<option value="11">糖尿病实验组</option>
							<option value="12">糖尿病对照组</option>
						</select>
					</div>

				</c:if>
				</div>
				<div class="row">
					<c:if test="${currUserLcc  eq '99' }" >
					<div  class="leftLable col-md-2">糖尿病是否与医生判断一致:</div>
						<div  class="col-md-1">
							<select class="form-control input-sm" id="isSystemDiabetes" name="isSystemDiabetes" placeholder="请选择">
								<option value ="">全部</option>
								<option value="2">是</option>
								<option value="1">否</option>
							</select>
						</div>
					</c:if>
					<div  class="col-md-2"></div><div  class="col-md-2"></div><div  class="col-md-2"></div>
					<div  class="col-md-2">
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
        url : '${ctx}/msg/join/list',
        postData:{patientId:'${patientId}','processtypes':'1'},
        datatype : 'json',  
        mtype : 'POST',
		colNames : [ 'LCCID','PID','患者姓名','性别',
			'手机',
			<c:if test="${currUserLcc  eq '99' }" >
			'是否吸烟','是否糖尿病', '糖尿病是否与医生判断一致','所属组别','分组日期',
			</c:if>
			'糖尿病筛查问卷','基线问卷','随机问卷'
//			,'干预6月随访问卷'
			,'末次随访问卷'],
		colModel : [
			{name : 'lccCode',width:'80px', index : 'LCC_CODE', align:'center'},
			{name : 'patientId',width:'80px', index : 'PATIENT_ID', align:'center'},
			{name : 'patientName', width:'80px', index : 'patientName', align:'center',sortable: false  },
			{name : 'sex', index : 'sex',width:'60px', align:'center',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue=='1'){return '男';}return '女';} },
			{name : 'mobile', index : 'mobile',width:'100px', align:'center',sortable: false },
			<c:if test="${currUserLcc  eq '99' }" >
			{name : 'isSmoking', align:'center',width:'80px',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue==1){return '是';}else if(cellvalue ==2)return '否'; else return '';}  },
			{name : 'isDiabetes',  align:'center',width:'80px',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue==1){return '是';}else if(cellvalue ==2)return '否'; else return '';}  },
			{name : 'isSystemDiabetes',  align:'center',width:'160px',sortable: false,formatter:function(cellvalue, option, rowObjects){
				if(cellvalue==1){return '否';}
				else if((rowObjects.state012==1 && cellvalue == null ) || cellvalue == 2) return '是';
				return "";
			}},
			{name : 'belongGroup', index : 'belongGroup',width:'120px', align:'center',sortable: false ,formatter:function(cellvalue, option, rowObjects){
				if(cellvalue=='01'){
					return "非糖尿病实验组";
				}else if(cellvalue=='02'){
					return "非糖尿病对照组";
				}else if(cellvalue=='11'){
					return '糖尿病实验组';
				}else if( cellvalue=='12' ){
					return '糖尿病对照组';
				}
				return "";
			}},
			{name : 'groupDate', index : 'groupDate',width:'100px', align:'center',sortable: false,formatter:yymmddFormatter },
			</c:if>
			{name : 'state010', align:'center',width:'120px',sortable: false,formatter:function (cellvalue,option,rowObjects){

				if(cellvalue ==1) {
					return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','010')\">已完成</button>";
				}else if( rowObjects.isDead == 1 ){
					return "<button class='qnbtn' style='color:red'\">已死亡</button>";
				}else if(cellvalue ==2) {
					return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','010')\">继续答卷</button>";
				}else if(rowObjects.mobile!=null && rowObjects.mobile != "" && cellvalue==null){
					return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','010')\">开始答卷</button>";
				}
				return "";
			} },
			{name : 'state011', align:'center',width:'120px',sortable: false,formatter:function (cellvalue,option,rowObjects){

				if(cellvalue == 1){
					return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','011')\">已完成</button>";
				}else if( rowObjects.isDead == 1 ){
					return "<button class='qnbtn' style='color:red'\">已死亡</button>";
				}else if(cellvalue == 2){
					return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','011')\">继续答卷</button>";
				}else if(cellvalue==null && rowObjects.state010==1){
					return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','011')\">开始答卷</button>";
				}
				return "";
			} },
			{name : 'state012', align:'center',width:'120px',sortable: false,formatter:function (cellvalue,option,rowObjects){

				if(cellvalue == 1){
					return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','012')\">已完成</button>";
				}else if( rowObjects.isDead == 1 ){
					return "<button class='qnbtn' style='color:red'\">已死亡</button>";
				}else if(cellvalue == 2 && rowObjects.isDiabetes!=1){
					return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','012')\">继续答卷</button>";
				}else if(cellvalue==null && rowObjects.state011==1 && rowObjects.isDiabetes!=1){
					return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','012')\">开始答卷</button>";
				}
				return "";
			} },
//			{name:"" },
			{name:"state014", align:'center',width:'120px',sortable: false,
                formatter:function (cellvalue,option,rowObjects){
//                    if(rowObjects.belongGroup != null && rowObjects.belongGroup != ''){ // 已分组的人才可以答末次随访问卷
//                        return "<button class='qnbtn' " +
//                            "onclick=\"javascript:answerQn('"+rowObjects.patientId+"','014')\">开始答卷</button>";
//                    }else{
//                        return "";
//					}
                    if(cellvalue == 1){

                            return "<button class='qnbtn complete' " +
                                "onclick=\"javascript:answerQn('"+rowObjects.patientId+"','014')\">已完成</button>";


                    }else if( rowObjects.isDead == 1 ){
                        return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                    }else if(cellvalue == 2){
                        return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','014')\">继续答卷</button>";
                    }else if(cellvalue==null && rowObjects.belongGroup != null && rowObjects.belongGroup != ''){
        if( rowObjects.betweenDays != null && (rowObjects.betweenDays - 0) >= 150  ) {
            return "<button class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.patientId + "','014')\">开始答卷</button>";
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
        <c:if test="${currUserLcc  eq '99' }" >
        shrinkToFit:false,
        </c:if>
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
    <c:if test="${currUserLcc  eq '99' }" >
    $(".ui-jqgrid .ui-jqgrid-bdiv").css("overflow-x","auto");
    </c:if>
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