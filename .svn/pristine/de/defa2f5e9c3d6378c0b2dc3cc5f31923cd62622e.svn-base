<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="dict" uri="http://com.bdcor.pip.web.common.tagsupport" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>问卷预览</title>
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
	<ul class="breadcrumb">
		<li class="active">质量管理</li>
		<li class="active">问卷预览</li>
	</ul>
	
	<div id="select">
        <div class="select-main">
              <form id="searchForm" action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label for='pIdLike'>PID:</label></li>
                         <li> 
                         	<input type="text" id="pIdLike" name="pIdLike" style="width:150px" class="form-control input-sm" placeholder="患者ID"/>
                         </li>
                         
                    	 <li class="select-one"> <label for='patientNameLike'>患者姓名:</label></li>
                         <li> 
                         	<input type="text" id="patientNameLike" name="patientNameLike" style="width:150px" class="form-control input-sm" placeholder="患者姓名"/>
                         </li>
                         
                         <li class="select-one"> <label for='idNumberLike'>身份证号:</label></li>
                         <li> 
                         <input type="text" id="idNumberLike" name="idNumberLike" class="form-control input-sm" placeholder="身份证号"/>
                         </li>
                         <li class="select-one"> <label for='lccName'>医院名称:</label></li>
                         <li> 
                         	<input type="hidden" id="lccCodeEq" name="lccCodeEq">
						 	<input type="text" id="lccName" name="lccName"   class="form-control input-sm" placeholder="输入单位简拼或LCCID">
                         </li>
                        <li> <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                </fieldset>
              </form>
        </div>
    </div>
    <div id='dialog-showQnInfo' class="modal fade">
	  <div class="modal-dialog" style="width:750px;">
	    <div class="modal-content">
    		<div class="modal-header" style="border-bottom: 0px;">
       			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
      		</div>
	      	<div class="modal-body">
	        	<p>加载中……</p>
	      	</div>
	      	<div class="modal-footer">
				<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      	</div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
	
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
    
	<script type="text/javascript">
		$(function(){
			$.getJSON("${ctx}/combox/dataLimitLcc",{},function(data) { 
					$('#lccName').autocomplete(data,{
						minChars: 0,
						mustMatch:true,
						width:240,
				        formatItem: function(item,i, max) {
				            return '<table><tr style="align:left"><td width="50px">' + item.LCC_CODE + '</td><td width="180px">' + item.LCC_NAME + '</td></tr></table>';
				        },
				        // 指定 与 输入文字匹配的字段名
				        formatMatch: function(item,i, max) {
				            return item.HELP_CODE + item.LCC_NAME+ item.LCC_CODE;
				        },
				        // 选中 某条记录在输入框里 显示的数据字段
				        formatResult: function(item) {
				            return item.LCC_NAME;
				        }
					}); 
					//选中 某条记录 触发的事件
					$('#lccName').result(function(event, item){
						if(item){
							$("#lccName").val(item.LCC_NAME);
							$("#lccCodeEq").val(item.LCC_CODE);
						}else{
							$("#lccCodeEq").val("");
						}
					});
				});
		
			var option = {
			        url : '${ctx}/quality/previewqn/list',    
			        datatype : 'json',  
			        mtype : 'POST',
			        colNames : [ 'LCCID','医院名称','PID','患者姓名','性别','出生日期','身份证号','问卷预览'],
			        colModel : [ 
			                     {name : 'lccId', index : 'lccId', align:'center' },
			                     {name : 'lccName', index : 'lccName', align:'center',sortable: false},
			                     {name : 'pId', index : 'pId', align:'center' },
			                     {name : 'patientName', index : 'patientName', align:'center' },
			                     {name : 'sex', index : 'sex',formatter : function(cellvalue, option, rowObjects) {
										if(cellvalue=='1')return "<div style='text-align:center'>男</div>";
										if(cellvalue=='2')return "<div style='text-align:center'>女</div>";
										return '';
									}, align:'left'},
								 {name : 'birthday', index : 'birthday', align:'center' },
								 {name : 'idNumber', index : 'idNumber', align:'center',sortable: false},
								 {name : 'pId',index : 'patientId',align : 'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
									return '<button style="line-height:0.8" class="btn btn-primary btn-align-right btn-sm" onclick=\"showQnInfo(\''+cellvalue+'\')\">预览问卷</button>';
								},
								width : '120'
							}],
			        rowNum : 15, 
			        rowList : [ 15, 30, 50,100,150,500 ],      
			        height : "100%",
			        autowidth : true,  
			        pager : '#pager',  
			        sortname : 'lccId,pId',
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
						id : "pId"
					}
			 	};  
				$("#grid").jqGrid(option); 
				$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
				jqgridResponsive("grid",false);
				
				$('#cancel').click(function(){
					$('#dialog-showQnInfo').modal('hide');
				});	
		});
		$("#btnQuery").click(multipleSearch);
		
		//弹出新增对话框			
		function showQnInfo(patientId){
			var timebak = new Date().getTime();
			$("#dialog-showQnInfo").modal({
				 backdrop: 'static'
			});
			$("#dialog-showQnInfo p" ).load("${ctx}/quality/previewqn/openmodalshowQnInfo?patientId="+patientId+"&time="+timebak);
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
	</script>
</body>
</html>