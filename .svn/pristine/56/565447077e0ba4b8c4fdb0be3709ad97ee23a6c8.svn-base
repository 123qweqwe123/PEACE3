<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>血检报告管理</title>
<script type="text/javascript">
		//查询绑定
		var multipleSearch = function(){ 
			var data = {};
			data['lccCode']=$.trim($("#lccCode").val());
			data['patientId']=$.trim($("#patientId").val());
			data['patientName']=$.trim($("#patientName").val());
			var postData = $("#grid").jqGrid("getGridParam", "postData");  
		 
			$.extend(postData,data);
		
			$("#grid").jqGrid("setGridParam", {
				search: true  
			}).trigger("reloadGrid", [{page:1}]);
		};
		
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
</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">血检报告管理</li>
      </ul>
</div>
	<div id="message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="messageSpanId"></span>
	</div>
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row"> 
					<div  class="leftLable col-md-1" style="text-align: right">医院名称:</div>
					<div  class="col-md-2">
						<input id="lccCode" type="hidden" name="lccCode" />
						<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					
					<div  class="leftLable col-md-1" style="text-align: right">PID:</div>
					<div  class="col-md-2">
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>

						<div  class="leftLable col-md-1" style="text-align: right">患者姓名:</div>
						<div  class="col-md-2">
							<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="输入患者姓名或者首字母简拼"/>
						</div>

                    <div  class="col-md-2"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	
	<div id="jqgrid">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	
	<div id='dialog-showBloodTestList' class="modal fade">
	  <div class="modal-dialog" style="width:750px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">查看修改血检问卷</h4>
	      </div>
	      <div class="modal-body">
	      	<div id="alertForShowUsrFile" hidden>
			  <strong>Warning!</strong>
			</div>
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel-showBloodTestList' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-answerQn' class="modal fade">
	  <div class="modal-dialog" style="width:800px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">化验结果</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id ='cancel-answerQn' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-uqsTypeName' class="modal fade">
	  <div class="modal-dialog" style="width:400px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">化验结果类型</h4>
	      </div>
	      <div class="modal-body">
	        <p>
	        	<input type="hidden" id="uqsTypeName_patientId">
	        	<input type="hidden" id="uqsTypeName_uqsCode">
	        	化验结果类型:<select id="uqsTypeName"></select>
	        </p>
	      </div>
	      <div class="modal-footer">
			  <button type="button" id ='cancel-uqsTypeName' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      	<button type="button" id ='sure-uqsTypeName' class="btn btn-primary btn-align-right btn-sm" tabindex="1001">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
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

            var url = "${ctx}/combox/getautodata?type=patient";
            $('#patientName').autocomplete(url, {
                max:10,
                minChars:0,
                matchContains: true,
                formatItem: function(row) {
                	if( row == null || row == '' ){
                		return "";
					}
					return row[0];
                }
            }).result(function(event,row,formatted){
               console.log("123")
            });

			var option = {
		        url : '${ctx}/qn/bloodTestMgt/getBloodTestPatientList',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : [ 'LCCID','医院名称','PID','患者姓名','身份证','化验结果','化验结果管理'],
		        colModel : [ 
							{name : 'lccCode', index : 'lccCode', align:'center'},
							{name : 'lccName', index : 'lccName', align:'left'},
		                    {name : 'patientId', index : 'patientId', align:'center'},
					        {name : 'patientName', index : 'patientName', align:'center',sortable: false  },
		                    {name : 'idNumber', index : 'idNumber', align:'center',sortable: false },
		                    {name : 'patientId', index : '', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    	return "<button class='' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','007',"
		                    			+"'"+rowObjects.bloodTest+"','"+rowObjects.bloodTest6+"','"+rowObjects.bloodTest12+"','"+rowObjects.bloodTest18+"','"+rowObjects.bloodTest24+"')\">上报</button>";
		                    }},
		                    {name : 'patientId', index : '', align:'center',sortable: false,
								formatter : function(cellvalue, option, rowObjects) {
//		            				if( rowObjects.processTypes == 2  ){
//		            				    if( rowObjects.hasDo2==1 ){
//                                            return "<button class='complete' onclick=\"javascript:showBloodTestList('"
//                                                +rowObjects.patientId+"','007')\">查看及修改</button>";
//										}else{
//		            				        return "";
//										}
//									}else if(rowObjects.hasDo1==1){
//										return "<button class='complete' onclick=\"javascript:showBloodTestList('"
//											+rowObjects.patientId+"','007')\">查看及修改</button>";
//									}else{
//										return "";
//									}
									if( rowObjects.hasDo1==1 || rowObjects.hasDo2==1 ){
                                        return "<button class='complete' onclick=\"javascript:showBloodTestList('"
                                                +rowObjects.patientId+"','007')\">查看及修改</button>";
									}else{
									    return "";
									}

		                    	}
		                    }
		                   ],         
		        rowNum : 15, 
		        rowList : [ 15, 30, 50,100,500 ],      
		        height : "100%",
		        autowidth : true,  
		        pager : '#pager',  
		        sortname : 'lccCode,patientId',
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
			$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
			jqgridResponsive("grid",false); 
		
			$("#btnQuery").click(multipleSearch);
			
			$('#cancel-answerQn').click(function(){
				$('#dialog-answerQn').modal('hide');
			});
			
			$('#cancel-uqsTypeName').click(function(){
				$('#dialog-uqsTypeName').modal('hide');
			}); 
			
			$('#cancel-showBloodTestList').click(function(){
				$('#dialog-showBloodTestList').modal('hide');
			});
			
			$('#sure-uqsTypeName').click(function(){
				var timebak = new Date().getTime();
				$('#dialog-uqsTypeName').modal('hide');
				
				var url = "${ctx}/qn/bloodTestMgt/openmodalAnswerQn?patientId="+$("#uqsTypeName_patientId").val()+"&uqsCode="+$("#uqsTypeName_uqsCode").val()+"&uqsTypeName="+$("#uqsTypeName").val()+"&time="+timebak;
				
				setTimeout("openDialog('"+url+"','dialog-answerQn')",500 );
			}); 
			
			
			<c:if test="${'2' eq answerQnResult}">
				showResult(false,"保存失败");
			</c:if>
			
			<c:if test="${'1' eq answerQnResult}">
				showResult(true,"保存成功");
			</c:if>
		});
		
		function showBloodTestList(patientId,uqsCode){
			var timebak = new Date().getTime();
			openDialog("${ctx}/qn/bloodTestMgt/openmodalShowBloodTestList?patientId="+patientId+"&time="+timebak,"dialog-showBloodTestList");
		}
		
		function answerQn(patientId,uqsCode,bloodTest,bloodTest6,bloodTest12,bloodTest18,bloodTest24){
			$("#uqsTypeName_patientId").val(patientId);
			$("#uqsTypeName_uqsCode").val(uqsCode);
			$("#uqsTypeName").html("");
			if(bloodTest!=null && bloodTest!='' && bloodTest!='null'){
				$("#uqsTypeName").append("<option value='1'>首次随访</option>");
			}
			if(bloodTest6!=null && bloodTest6!='' && bloodTest6!='null'){
				$("#uqsTypeName").append("<option value='6'>6月随访</option>");
			}
			if(bloodTest12!=null && bloodTest12!='' && bloodTest12!='null'){
				$("#uqsTypeName").append("<option value='12'>末次随访</option>");
			}
			if(bloodTest18!=null && bloodTest18!='' && bloodTest18!='null'){
				$("#uqsTypeName").append("<option value='18'>18月随访</option>");
			}
			if(bloodTest24!=null && bloodTest24!='' && bloodTest24!='null'){
				$("#uqsTypeName").append("<option value='24'>24月随访</option>");
			}
			openDialog(null,"dialog-uqsTypeName");
		}
		
		
		//弹出对话框
		function openDialog(url,modalId){
		    $( "#"+modalId ).modal({
		         backdrop: 'static'
		    });
		    //使用此方法防止js缓存不加载
		    if(url != null){
		    	$("#"+modalId+" p" ).html("加载中……");
		    	$("#"+modalId+" p" ).load(url);
		    }
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