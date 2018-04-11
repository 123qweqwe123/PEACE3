<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<c:set var="currUserLcc" value="<%=Securitys.getUser().getLccCode()%>"/>
<!DOCTYPE html>
<html>
<head>
<title>患者随访</title>
<script type="text/javascript">
		//查询绑定
		var multipleSearch = function(){

            $("#grid").jqGrid('setGridParam',{datatype:'json'});


            var myform = $("#searchForm").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name]=null; 
				if(field.value && ''!=field.value){
					data[field.name] = field.value;
				}
			});   
		  	var suifang = $("#suifang").val();
		  	if(suifang !=null && suifang !=''){
		  		data['suifang'] = suifang;
		  	}
		  	else{
		  		data['suifang']="";
		  	}
		  	var suifangtype=$("#suifangtype").val();
		  	if(suifangtype !=null && suifangtype !=''){
		  		data['suifangtype'] = suifangtype;
		  	}
		  	else{
		  		data['suifangtype']="";
		  	}
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
         <li class="active">患者随访</li>
      </ul>
</div>
	<div id="alert" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
	<div id="message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="messageSpanId"></span>
	</div>
	<div id="select">
		<div class="select-main">
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div class="row">
					<div  class="leftLable col-md-1" style="text-align:right">PID:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
					</div>
					
					<div  class="leftLable col-md-1" style="text-align:right">患者姓名:</div>
					<div  class="col-md-2"> 
						<input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
					</div>
					<div  class="leftLable col-md-1" style="text-align:right">随访类型:</div>
					<div  class="col-md-2">
						<select class="form-control" id="suifang" name="suifang">
							<option value="">请选择</option>
							<option value="001">首次随访</option>
							<option value="013">6月随访</option>
							<option value="003">12月随访</option>
							<option value="004">18月随访</option>
							<option value="005">末次随访</option>
						</select>  
					</div> 
					<div  class="leftLable col-md-1" style="text-align:right">随访状态:</div>
					<div  class="col-md-2">
	                    <select class="form-control" id="suifangtype" name="suifangtype">
							<option value="">请选择</option>
							<option value="0">开始答卷</option>
							<option value="2">继续答卷</option>
							<option value="1">已完成</option>
							<option value="3">已死亡</option>
						</select> 
					</div>
				</div>
				<div class="row">
					<div  class="rightLable col-md-1" style="text-align:right">是否糖尿病:</div>
                    <div  class="col-md-2">
                        <select class="form-control" id="isdiabetes" name="isdiabetes">
                            <option value="">请选择</option>
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>
					<div  class="rightLable col-md-1" style="text-align:right">是否可答6月随访:</div>
					<div  class="col-md-2">
						<select class="form-control" id="is6State" name="is6State">
							<option value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</div>

					<c:if test="${currUserLcc  eq '99' }" >
                        <div  class="rightLable col-md-1" style="text-align:right">医院名称:</div>
                        <div  class="col-md-3">
                                <%-- <select id="lccCode" name="lccCode" class="form-control input-sm">
                                    <option value="">请选择单位</option>
                                    <c:forEach items="${lccDictList }" var="lccDict">
                                        <option value="${lccDict.lccCode }" >${lccDict.lccName }</option>
                                    </c:forEach>
                                  </select> --%>
                            <input id="lccCode" type="hidden" name="lccCode" />
                            <input id="lccName" name = "lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>

                        </div>
                    </c:if>

				</div>
				<div class="row">
					<div  class="leftLable col-md-1" style="text-align:right">用户名:</div>
					<div  class="col-md-2">
						<input type="text" class="form-control input-sm" name="createBy" id="createBy" value='' placeholder="用户名"/>
					</div>
					<div  class="col-md-2" style="float:right;margin-right: 30px;margin-top: 5px">
                        <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
						<button type="button" id="exportExcel" style="margin-left: 10px"
								class="btn btn-primary btn-align-right btn-sm">导出</button>
                    </div>
                </div>
			</form>
		</div>
	</div>
	
	<div id="jqgrid">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	
	<form action="${ctx}/qn/answerQn" id="answerQnForm" method="post">
		<input type="hidden" name="patientId" id="answerQnForm_patientId">
		<input type="hidden" name="uqsCode" id="answerQnForm_uqsCode">
	</form>
	
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
		        url : '${ctx}/qn/getPatientList',
		        datatype : 'json',
                <c:if test="${not empty searchparam}">
                datatype : 'local',
                </c:if>
		        mtype : 'POST',
		        colNames : [ 'LCCID','PID','患者姓名','身份证','手机','是否参加短信项目','是否糖尿病','医院名称','首次随访','6月随访','12月随访','18月随访','末次随访','color'
				,'是否可答6月问卷', '用户名', '完成时间'],
		        colModel : [
							{name : 'LCCCODE', index : 'patientId', align:'center',width:'80px'},
		                    {name : 'ID', index : 'patientId', align:'center',width:'80px'},
					        {name : 'PATIENTNAME', index : 'patientName', align:'center',width:'80px',sortable: false,
                                formatter : function(cellvalue, option, rowObjects){
                                    if( rowObjects.ISJOINMSG != null && rowObjects.ISJOINMSG == '1'){
                                        return "<font style='color:#428bca'>"+cellvalue+"</font>"; // <span style='background-color:#428bca'>"+cellvalue+"</span>
                                    }else{
                                        return cellvalue;
                                    }
                                }
                            },
		                    {name : 'IDNUMBER', index : 'idNumber', align:'center',sortable: false ,width:'140px'},
		                    {name : 'MOBILE', index : 'mobile', align:'center',sortable: false ,width:'100px'},
							{name : 'ISJOINMSG', align:'center',width:'120px',sortable: false,formatter : function(cellvalue, option, rowObjects){
									if( cellvalue != null && cellvalue == '1'){
										return "是";
									}else{
										return "否";
									}
								}
							},
							{name : 'IS_DIABETES', align:'center',width:'80px',sortable: false,formatter : function(cellvalue, option, rowObjects){
									if( cellvalue != null && cellvalue == '1'){
										return "是";
									}else{
										return "否";
									}
								}
							},
		                    {name : 'LCCNAME', index : 'lccName', align:'left',sortable: false,hidden:true},
		                    {name : 'UQS001', index : 'UQS001',width:'80px', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    	if (cellvalue == 1) {
                                	return "<button class='qnbtn complete' onclick=\"javascript:answerQn('" + rowObjects.ID + "','001')\">已完成</button>";
                                }
                                else if(rowObjects.ISDEAD == 1 && rowObjects.UQS002 != 1){
                                	return "<button class='qnbtn' style='color:red' \">已死亡</button>";
                                }
                                else if( rowObjects.ISJOINMSG != null && rowObjects.ISJOINMSG == '1') {
                                    return "<button  disabled class='qnbtn complete' >不可答卷</button>";
                                }else if (cellvalue == 2) {
//                                    return "<button class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','001')\">继续答卷</button>";
									return "";
                                } else {
//                                    return "<button class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','001')\">开始答卷</button>";
                                return "";
                                }
		                    }},
		                    {name : 'UQS002',width:'80px', index : 'UQS002', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
								if( rowObjects.ISDEAD == 1 ){
									if (cellvalue == 1) {
										return "<button class='qnbtn' style='color:red;background-color:#428bca' onclick=\"javascript:answerQn('" + rowObjects.ID + "','013')\">已死亡</button>";
									}else{
										return "<button class='qnbtn' style='color:red'\">已死亡</button>";
									}
								}
		                    	else if (cellvalue == 1) {
                                	return "<button class='qnbtn complete' onclick=\"javascript:answerQn('" + rowObjects.ID + "','013')\">已完成</button>";
                                }else if( rowObjects.ISJOINMSG != null && rowObjects.ISJOINMSG == '1') {
                                    return "<button  disabled class='qnbtn complete' >不可答卷</button>";
                                }else if (cellvalue == 2) {
                                    return "<button class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','013')\">继续答卷</button>";
								} else if(rowObjects.UQS001 != 1 ||(rowObjects.UQS001 == 1 && rowObjects.IS_6STATE == 1) ){
									return "<button class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','013')\">开始答卷</button>";
                                }
		                    	return "";
		                    }},
		                    {name : 'UQS003',width:'80px', index : 'UQS003', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    	if (cellvalue == 1) {
                                	return "<button class='qnbtn complete' onclick=\"javascript:answerQn('" + rowObjects.ID + "','003')\">已完成</button>";
                                }else if(rowObjects.ISDEAD == 1){
                                	return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                                }else if( rowObjects.ISJOINMSG != null && rowObjects.ISJOINMSG == '1') {
                                    return "<button  disabled class='qnbtn complete' >不可答卷</button>";
                                }else if (cellvalue == 2) {
                                    return "<button disabled class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','003')\">继续答卷</button>";
                                } else  if(rowObjects.UQS002==1){
                                    return "<button disabled class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','003')\">开始答卷</button>";
                                }
		                    	return "";
		                    }},
		                    {name : 'UQS004',width:'80px', index : 'UQS004', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    	if (cellvalue == 1) {
                                	return "<button class='qnbtn complete' onclick=\"javascript:answerQn('" + rowObjects.ID + "','004')\">已完成</button>";
                                }else if(rowObjects.ISDEAD == 1){
                                	return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                                }else if( rowObjects.ISJOINMSG != null && rowObjects.ISJOINMSG == '1') {
                                    return "<button  disabled class='qnbtn complete' >不可答卷</button>";
                                }else if (cellvalue == 2) {
                                    return "<button disabled class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','004')\">继续答卷</button>";
                                } else if(rowObjects.UQS003==1){
                                    return "<button disabled class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','004')\">开始答卷</button>";
                                }
		                    	return "";
		                    }},
		                    {name : 'UQS005',width:'80px', index : 'UQS005', align:'center',sortable: false,formatter : function(cellvalue, option, rowObjects) {
		                    	if (cellvalue == 1) {
                                	return "<button class='qnbtn complete' onclick=\"javascript:answerQn('" + rowObjects.ID + "','005')\">已完成</button>";
                                }else if(rowObjects.ISDEAD == 1){
                                	return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                                }else if( rowObjects.ISJOINMSG != null && rowObjects.ISJOINMSG == '1') {
                                    return "<button  disabled class='qnbtn complete' >不可答卷</button>";
                                }else if (cellvalue == 2) {
                                    return "<button disabled class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','005')\">继续答卷</button>";
                                } else if(rowObjects.UQS004==1){
                                    return "<button disabled class='qnbtn' onclick=\"javascript:answerQn('" + rowObjects.ID + "','005')\">开始答卷</button>";
                                }
		                    	return "";
		                    }},
		                    {name : 'ISDEAD', index : 'ISDEAD', hidden:true},
							{name : 'IS_6STATE',width:'120px',  align:'center',index : 'IS_6STATE',formatter : function(cellvalue, option, rowObjects) {
								if( cellvalue == 1 ){
									return "是";
								}else{
									return "否";
								}
							}},
							{name : 'ACCOUNTNAME', index : 'ACCOUNTNAME', align:'center',width:'80px'},
                    		{name : 'FINISHTIME', index : 'FINISHTIME',width:'100px', align:'center',sortable: false,formatter:yymmddFormatter }
		                   ],         
		        rowNum : 15, 
		        rowList : [ 15, 30, 50,100,500 ],      
		        height : "100%",
		        autowidth : true,
				shrinkToFit:false,
				pager : '#pager',
		        sortname : 'lccCode,id',
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
					id : "ID"
				},
				loadComplete:afterCompleteFunction
		 	};  
			$("#grid").jqGrid(option); 
			$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
			jqgridResponsive("grid",false);
			$(".ui-jqgrid .ui-jqgrid-bdiv").css("overflow-x","auto");

			$("#btnQuery").click(multipleSearch);
			
			<c:if test="${'2' eq result}">
				showResult(false,"操作失败");
			</c:if>
			
			<c:if test="${'1' eq result}">
				showResult(true,"保存成功");
			</c:if>

			// 带回来的查询条件 执行查询
			if( "${searchparam}".trim() != "" ){
				var params = "${searchparam}";
				var strArr = params.trim().split("&");
				$.each(strArr,function(i,str){
					var keyValue = str.split("=");
					if( keyValue[1] != null && keyValue[1] != '' ){
						$("#searchForm").find("[name='"+keyValue[0]+"']").val(decodeURI(keyValue[1]));
					}
				});
				multipleSearch();
			}
		});
		
		function answerQn(patientId,uqsCode){
			$("#answerQnForm").append("<input name='searchparam' type='hidden' value='"+
					$("#searchForm").serialize()
//					JSON.stringify($("#searchForm").serializeArray()).replace(/\"/g,"'")
					+"' />");
			$("#answerQnForm_patientId").val(patientId);
			$("#answerQnForm_uqsCode").val(uqsCode);
			$("#answerQnForm").submit();
		}
		
		function afterCompleteFunction(){
			var rowDatas = $("#grid").jqGrid('getRowData');
			for(var i=0;i<rowDatas.length;i++){
				var rowData = rowDatas[i]; 
				if(rowData.ISDEAD ==1){
					var j=0;
					$("#"+rowData.id).find("td").each(function(){
						if(j<5){
							$(this).css("color","red");
						}
					});
				}
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

        $('#exportExcel').click(function(){ // 导出
            var myform = $("#searchForm").serializeArray();
            var data = {};
            var str = "?1=1";
            $.each(myform, function(i, field){
                data[field.name]=null;
                if(field.value && ''!=field.value){
                    data[field.name] = field.value;
                    str = str+"&"+field.name+"="+field.value
                }
            });
debugger;
            window.open("${ctx}/qn/expoertExcel"+str);
        });
	</script>
</body>

</html>