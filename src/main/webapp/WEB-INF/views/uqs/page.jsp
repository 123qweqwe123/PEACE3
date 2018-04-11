<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.bdcor.pip.client.vo.paper.*,com.bdcor.pip.client.vo.*,java.util.*,java.io.*,com.bdcor.pip.core.utils.Securitys,java.text.SimpleDateFormat"%>
<%-- <%@page import="java.text.SimpleDateFormat,com.bdcor.pip.client.db.dao.DaoFactory,com.bdcor.pip.client.service.CurrentLoginUser"%>
 --%><%-- <jsp:useBean id="paperBean" class="com.bdcor.pip.client.service.PaperService"></jsp:useBean>
<jsp:useBean id="interview" class="com.bdcor.pip.client.service.InterviewService"></jsp:useBean>
 --%>
<%

String intervId = request.getParameter("patientId");
SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
String UQSBeginTime = sf2.format(new Date());
String thisBeginTime = UQSBeginTime;
String UQSIsHold = "2";
String UQSRemark = "";
Paper paper = (Paper)request.getAttribute("paper");
String paperCode = paper.getPaperVersion().split("\\.")[1];
String showbar = request.getParameter("showbar");
SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
String nowDateStr = sf1.format(new Date());
request.setAttribute("nowDate",nowDateStr);
Date nowDateAdd30 = new Date();
nowDateAdd30.setDate(new Date().getDate()+30);
request.setAttribute("nowDateAdd30", sf1.format(nowDateAdd30));

%>

<!DOCTYPE html>
<html>
<head>
<title  id='title'>试卷- <c:out value="${paper.paperName }"></c:out></title>
<%-- <jsp:include page="/common/header.jsp"></jsp:include> --%>
<script type="text/javascript" src="${ctx}/static/js/who.js"></script>
<script type="text/javascript" src="${ctx}/static/js/idCardCheck.js"></script>
    <style type="text/css">
        .table_border td
        {
            border-top:1px #DDD solid;
            border-right:1px #DDD solid;
            border-color:rgb(128,158,182);
        }
        .table_border
        {
         margin-left:10px;
         border-bottom:1px #DDD solid;
         border-left:1px #DDD solid;
         border-color:rgb(128,158,182);
         width:97%
        }
    </style>
</head>
<body>
<div id="wrap">
	<%-- <jsp:include page="/common/component.jsp"></jsp:include>
	<jsp:include page="/common/menu.jsp"></jsp:include>
	 --%>
	<div id="main-content">
		<div id="left-main">
		<div id="navbar">
    	</div>
    	<%-- <% if ( showbar == null ){ %>
    	<div class="flow_steps" style="width:100%;max-width: 1140px;min-width:800px;">
			<ul style="overflow:hidden;width:1004px;">
				<li class="done">1.问卷列表</li>
				<li class="current_prev">2.扫描条码</li>
				<li class="current">3.回答问卷</li>
				<li class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
		</div>
		<%} %> --%>
    	
		<h2 class="stair-title">${paper.paperName}</h2>
		
		
		<c:forEach items="${paper.QGroup }" var="qg">
			<div id="qg_${qg.id}">
			 <h3 class="stair-title" id="g<c:out value="${qg.id}"></c:out>"><c:out value="${qg.displayname}"></c:out></h3>
			 <c:forEach items="${qg.qs }" var="q">
			 
			 		<!-- 个性化设置 -->
			 		 <%if(paperCode.equals("005")){%>
			 			<c:if test="${qg.id == '2'}">
			 				<c:if test="${q.id == '1'}"><h2  style="color: red">&nbsp;&nbsp;高血压</h2></c:if>
			 				<c:if test="${q.id == '26'}"><h2 style="color: red">&nbsp;&nbsp;血脂异常</h2></c:if>
			 				<c:if test="${q.id == '51'}"><h2 style="color: red">&nbsp;&nbsp;血糖异常</h2></c:if>
			 			</c:if>
				 	 <%}else if(paperCode.equals("006")){%>
			 			<c:if test="${qg.id == '5'}">
			 				<c:if test="${q.id == '2'}"><h2 id="before5_2" style="color: red;display: none">&nbsp;&nbsp;住院记录 1</h2></c:if>
			 				<c:if test="${q.id == '8'}"><h2 id="before5_8" style="color: red;display: none">&nbsp;&nbsp;住院记录 2</h2></c:if>
			 				<c:if test="${q.id == '14'}"><h2 id="before5_14" style="color: red;display: none">&nbsp;&nbsp;住院记录 3</h2></c:if>
			 			</c:if>
				 	 <%}%>
				 	 
				 	 
				 	 
					<div thisTitle="${q.helptext}" displayname="${q.displayname }"
						<c:if test="${q.notNull == '1'}">notNull="1"</c:if>
						<c:if test="${q.sethidden eq 'SH_HIDDEN'}">style="display:none"</c:if>
							class="panel panel-default"  id="q_${qg.id}_${q.id}" controlshow="${q.controlshow}" controlhide="${q.controlhide}" >
						<div class="panel-heading">
							<span><c:out value="${q.displayname }"></c:out></span><c:if test="${q.notNull == '1'}"><span style="color: red">&nbsp;&nbsp;(必填)</span></c:if>
						</div>
<c:choose>
<c:when test="${q.type eq 'Q_TYPE_SINGLE'}">
<!-- 单选题 -->
			<div class="panel-body">
				<div class="form-horizontal">
					<div class="form-group">
					<c:forEach items="${q.options }" var="option">
						<div class="col-sm-6">
							<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
							<label for="o_${qg.id }_${q.id }_${option.id }">
							<input 
							<c:if test="${resultOptionMap[id] != null}">checked</c:if>
							ondblclick="cancleSingleCheck(this);" questionType="SINGLE" optionFullId="${qg.id}_${q.id}_${option.id}" type="radio" onclick="clickSelect(this,'${option.controlOther}','${option.rejectother}','${qg.id}_${q.id}_');"  id="o_${qg.id }_${q.id }_${option.id }" name="${qg.id }_${q.id}" value="<c:out value="${qg.id}_${q.id}_${option.id}"></c:out>">
								<span><c:out value="${option.displayname }"></c:out></span>
							</label>
							</div>
					</c:forEach>
					</div>
				</div>
			</div>
</c:when> 
<c:when test="${q.type eq 'Q_TYPE_SINGLE_FILL'}">
<!-- 单选填空  -->
		<div class="panel-body">
			<div class="form-horizontal">
				<div class="form-group">
					<c:forEach items="${q.options }" var="option">
						<div class="col-sm-12" >
							<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
							<input 
							<c:if test="${resultOptionMap[id] != null}">checked</c:if> 
							type="radio" ondblclick="cancleSingleCheck(this);" questionType="SINGLEFILL" optionFullId="${qg.id}_${q.id}_${option.id}" onclick="clickSelect(this,'${option.controlOther}','${option.rejectother}','${qg.id}_${q.id}_');" id="o_${qg.id }_${q.id }_${option.id }" name="${qg.id }_${q.id }" value="<c:out value="${option.id }"></c:out>">
							<c:forEach items="${option.optionValidates}" var="optionValidate">
								<input type="hidden" validate="yes" controlOption="${qg.id}_${q.id}_${option.id}" validateType="${optionValidate.type}" value="${optionValidate.value}">
							</c:forEach>
							
							<c:if test="${not empty option.displayname}">
								<label for="o_${qg.id }_${q.id }_${option.id }">
									<c:out value="${option.displayname}"></c:out>
								</label>
							</c:if>
								<c:if test="${option.datastyle eq 'Q_TYPE_FILL_BLANK'}">
									<label for="o_${qg.id }_${q.id }_${option.id }">
									<c:out value="${option.beforeDisplayName}"></c:out>
									</label>
									<input 
										onkeyup="optionKeyUp(this,'${option.format}','${option.validateobjecttype}','${option.scope}');changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
										<c:if test="${option.datatype eq 'DATE'}"> readOnly class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'<%=nowDateStr%>'});"
											onfocus="changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
										</c:if>
										<c:if test="${option.datatype eq 'DATETIME'}"> readOnly class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
										</c:if>
										<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
										<c:if test="${option.isChosenNull != null}">isChosenNull="1"</c:if>
										onblur="blurRule(this,'${option.format}','${option.validateobjecttype}','${option.scope}')"
									type="text" value="${resultOptionMap[id]}" questionName="${q.displayname}" name="${qg.id}_${q.id}_${option.id}"><c:out value="${option.afterDisplayName}"></c:out>
								</c:if>
								<c:if test="${option.coption != null and option.coption.datastyle == 'Q_TYPE_FILL_BLANK'}">
									<c:out value="${option.coption.beforeDisplayName}"></c:out><input type="text" value="${resultOptionMap[id]}" name="${qg.id}_${q.id}_${option.id}"><c:out value="${option.coption.afterDisplayName}"></c:out>
								</c:if>
							</div>
					</c:forEach>
				</div>
			</div>
		</div>
</c:when>
<c:when test="${q.type eq 'Q_TYPE_MULTI'}">
<!-- 多选  -->
		<div class="panel-body">
			<div class="form-horizontal">
				<div class="form-group">
					<c:forEach items="${q.options }" var="option">
						<div class="col-sm-6">
							<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
							<input 
							<c:if test="${resultOptionMap[id] != null}">checked</c:if> 
							<c:if test="${option.rejectother != null}">rejectother="1"</c:if> 
							type="checkbox" questionType="MULTI" optionFullId="${qg.id}_${q.id}_${option.id}" onclick="clickSelect(this,'${option.controlOther}','${option.rejectother}','${qg.id}_${q.id}_');"  id="o_${qg.id }_${q.id }_${option.id }" name="${qg.id }_${q.id }" value="<c:out value="${option.id }"></c:out>">
							<label for="o_${qg.id }_${q.id }_${option.id }">
								<c:out value="${option.displayname }"></c:out>
							</label>
							</div>
					</c:forEach>
				</div>
			</div>
		</div>
</c:when>
<c:when test="${q.type eq 'Q_TYPE_MULTI_FILL'}">
<!-- 多选填空  -->
		<div class="panel-body">
			<div class="form-horizontal">
				<div class="form-group">
					<c:forEach items="${q.options }" var="option">
						<div class="col-sm-12">
							<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
							<c:forEach items="${option.optionValidates}" var="optionValidate">
								<input type="hidden" questionName="${q.displayname}" validate="yes" controlOption="${qg.id}_${q.id}_${option.id}" validateType="${optionValidate.type}" value="${optionValidate.value}">
							</c:forEach>
							
							<input 
							<c:if test="${resultOptionMap[id] != null}">checked</c:if>
							<c:if test="${option.rejectother != null}">rejectother="1"</c:if> 
							type="checkbox" questionType="MULTIFILL" optionFullId="${qg.id}_${q.id}_${option.id}" onclick="clickSelect(this,'${option.controlOther}','${option.rejectother}','${qg.id}_${q.id}_');"  id="o_${qg.id }_${q.id }_${option.id }" name="${qg.id }_${q.id }" value="<c:out value="${option.id }"></c:out>">
								
								<c:if test="${not empty option.displayname}">
								<label for="o_${qg.id }_${q.id }_${option.id }">
									<c:out value="${option.displayname}"></c:out>
								</label>
								</c:if>
								<c:if test="${option.datastyle eq 'Q_TYPE_FILL_BLANK'}">
									<label for="o_${qg.id }_${q.id }_${option.id }">
									<c:out value="${option.beforeDisplayName}"></c:out>
									</label>
									<input 
										onkeyup="optionKeyUp(this,'${option.format}','${option.validateobjecttype}','${option.scope}');changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
										<c:if test="${option.datatype eq 'DATE'}"> readOnly class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'<%=nowDateStr%>'});"
										</c:if>
										<c:if test="${option.datatype eq 'DATETIME'}"> readOnly class="Wdate"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
										</c:if>
										<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
										<c:if test="${option.isChosenNull != null}">isChosenNull="1"</c:if>
										onblur="blurRule(this,'${option.format}','${option.validateobjecttype}','${option.scope}')"
									type="text" value="${resultOptionMap[id]}" name="${qg.id}_${q.id}_${option.id}"><c:out value="${option.afterDisplayName}"></c:out>
								</c:if>
								<c:if test="${option.coption != null and option.coption.datastyle == 'Q_TYPE_FILL_BLANK'}">
									<c:out value="${option.coption.beforeDisplayName}"></c:out><input type="text" value="${resultOptionMap[id]}" name="${qg.id}_${q.id}_${option.id}"><c:out value="${option.coption.afterDisplayName}"></c:out>
								</c:if>
								
							</div>
					</c:forEach>
				</div>
			</div>
		</div>
</c:when>
<c:when test="${q.type eq 'Q_TYPE_FILL_BLANK'}">
<!-- 填空 -->
		<div class="panel-body">
			<div class="form-horizontal">
				<div class="form-group">
				<c:forEach items="${q.options }" var="option">
					<c:forEach items="${option.optionValidates}" var="optionValidate">
						<input type="hidden" questionName="${q.displayname}" validate="yes" controlOption="${qg.id}_${q.id}_${option.id}" validateType="${optionValidate.type}" value="${optionValidate.value}">
					</c:forEach>
						<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
							<c:out value="${option.beforeDisplayName}"></c:out>
							<input type="text" 
								<c:if test="${option.readOnly != null && option.readOnly != ''}"> readOnly</c:if>
								<c:if test="${option.datatype eq 'DATE'}"> readOnly class="Wdate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'<%=nowDateStr%>'});"
								</c:if>
								<c:if test="${option.datatype eq 'DATETIME'}"> readOnly class="Wdate"
									onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});"
								</c:if>
								<c:if test="${option.code != null && option.code != ''}">inputAutoCompleted="yes" code="${option.code}"</c:if>
								<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
								<c:if test="${option.notNull == '1'}">notNull="1"</c:if>
								onkeyup="optionKeyUp(this,'${option.format}','${option.validateobjecttype}','${option.scope}')"
								onblur="blurRule(this,'${option.format}','${option.validateobjecttype}','${option.scope}')"
								width="40px;" value="${resultOptionMap[id]}" questionType="FILLBLANK" optionFullId="${qg.id}_${q.id}_${option.id}" name="${qg.id }_${q.id }_${option.id }" value="">
							<c:out value="${option.afterDisplayName}"></c:out>
							<c:if test="${option.code != null && option.code != ''}">
								<input type="hidden" id="dictName${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
								<c:set var="id" value="dict${qg.id}_${q.id}_${option.id}"></c:set>
								<input type="hidden" onchange="changeCode('${qg.id}_${q.id}_${option.id}',${option.code}')" id="dict${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
							</c:if>
				</c:forEach>
				</div>
			</div>
		</div>
</c:when>
<c:when test="${q.type eq 'Q_TYPE_TABLE'}">
<!-- 表格题 -->
		<div class="panel-body">
			<div class="form-horizontal">
				<div class="form-group">
					<table class = "table_border">
						<tr>
							<td style="min-width: 30%;text-align:center;">${q.talbe.displayname}</td>
							<c:forEach items="${q.talbe.tableTr[0].tableTrTd}" var="tableTd">
								<td <c:if test="${tableTd.extendcolumn != null}">colspan="${tableTd.extendcolumn}"</c:if>>
									 <%if(paperCode.equals("006")){%>
			 							<!--<c:if test="${qg.id == '4' && q.id == '1'}">-->
			 								${tableTd.displayname}
			 							<!--</c:if>-->
			 						 <%} %>
									&nbsp;
								</td>
							</c:forEach>
						</tr>
						<c:forEach items="${q.talbe.tableTr}" var="tableTr">
							<tr notNull="1" id="${qg.id }_${q.id }_${tableTr.rownum}">
								<td style="width:40%;">${tableTr.displayname}</td>
								<c:forEach items="${tableTr.tableTrTd}" var="thisTd">
									<c:forEach items="${thisTd.optionValidates}" var="optionValidate">
										<input type="hidden" questionName="${q.displayname}" validate="yes" controlOption="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}" validateType="${optionValidate.type}" value="${optionValidate.value}">
									</c:forEach>
									
									<c:set var="id" value="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}"></c:set>
									<td <c:if test="${thisTd.extendcolumn != null}">colspan="${thisTd.extendcolumn}"</c:if>>
										<c:if test="${thisTd.datastyle == 'Q_TYPE_TABLE_SINGLE'}">
											<input 
											id="o_${qg.id }_${q.id }_${tableTr.rownum}-${thisTd.id}"
											<c:if test="${resultOptionMap[id] != null}">checked</c:if>
											onclick="clickTableSingle(this);"
											ondblclick="cancleSingleCheck(this);" type="radio" questionType="TABLESINGLE" optionFullId="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}" name="td_${qg.id }_${q.id }_${tableTr.rownum}">
											<label for="o_${qg.id }_${q.id }_${tableTr.rownum}-${thisTd.id}">
												<c:out value="${thisTd.displayname }"></c:out>
											</label>
										</c:if>
										<c:if test="${thisTd.datastyle == 'Q_TYPE_TABLE_MULTI'}">
											<input 
											<c:if test="${resultOptionMap[id] != null}">checked</c:if> 
											type="checkBox" questionType="TABLEMULTI" optionFullId="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}">
										</c:if>
										<c:if test="${thisTd.datastyle == 'Q_TYPE_TABLE_BLANK'}">
											${thisTd.beforeDisplayName}
											<input 
											<c:if test="${option.notNull == '1'}">notNull="1"</c:if>
											<c:if test="${thisTd.maxLength != null}">maxlength="${thisTd.maxLength}"</c:if>
											name="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}"
											onkeyup="optionKeyUp(this,'${option.format}','${option.validateobjecttype}','${option.scope}')"
											onblur="blurRule(this,'${thisTd.format}','${thisTd.validateobjecttype}','${thisTd.scope}')"
											value="${resultOptionMap[id]}" type="text" questionType="TABLEBLANK" optionFullId="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}">
											${thisTd.afterDisplayName}
										</c:if>
										<c:if test="${thisTd.datastyle == 'Q_TYPE_TABLE_SELECT'}">
											<select questionType="TABLESELECT" optionFullId="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}">
												<option>全部</option>
											</select>
										</c:if>
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
</c:when>

<c:otherwise>
user.generous and user.stingy are false.
</c:otherwise>
</c:choose> 									
</div>
</c:forEach>
</div>
</c:forEach>
<br style="clear:both" />
<div class="btn-wrap">
       <button type="button" id="bnt"  onclick="sub()" class="btn-style-default">提交问卷</button>
</div>
               		
			</div>
			<br>
			<br>
			<br>
			<aside id="sidebar">
            	<div class="col-md-2">
				<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix" role="complementary">
					<ul class="nav bs-docs-sidenav">
						<li class="active01"><span>筛查对象条形码:</span><input type="text" class="form-control" name="intervid" id="intervid" readonly value="<%=intervId%>"></li>
						<c:forEach items="${paper.QGroup}" var="qg">
								<li class="active02"><a style="padding:3px;10px;" href="#g<c:out value="${qg.id }"></c:out>"><c:out value="${qg.displayname }"></c:out></a></li>
						</c:forEach>
						<li class="active"><button type="button" onclick="tempSub()" id="bnt" class="btn-style-default">暂存</button></li>
					</ul>
                     <br style="clear:both" />
				</div>
			  </div>
           </aside>
           
           <br style="clear:both" />
		</div>
		  <%-- <jsp:include page="/common/foot.jsp"></jsp:include> --%>
	</div>
	<!-- /container -->
<form action="4examSave.jsp" method="post" id="subForm">
</form>
<script type="text/javascript">
var canLeave = false;


$(function(){
	//光标定位到第一个输入框中
	$('.form-horizontal input:first').focus();
	
	//初始化题目的显示隐藏
	<c:forEach items="${paper.QGroup }" var="qg">
		<c:forEach items="${qg.qs }" var="q">
		showOrHide('${qg.id}_${q.id}');
		</c:forEach>
	</c:forEach>
	leaveCheck=true;
	
	var paperCode = "<%=paperCode%>";
	if(paperCode == "002"){
		$("input[optionFullId^='13_1_']").attr("disabled","disabled");
		$("input[optionFullId^='14_1_']").attr("disabled","disabled");
	}
	if(paperCode == "003"){
		$("input[optionFullId^='9_1_']").attr("disabled","disabled");
	}
	
	$("div[id^='q']").each(function(){
		var title= $(this).attr("thisTitle");
		if(title == null || title.trim() == "")return true;
		$(this).qtip({
			  content: title,
			  position: {
			      my: 'bottom left',
			      at: 'top left'
			   }
		});
	});
	
	if(paperCode == "006"){
		var v = $("input[name='5_1_1']").val().trim();
		if(v == "" || v == "0"){
			$("div[id^='q_5_']").each(function(){
				var intId = $(this).attr("id").substr(4);
				if(intId > 1){
					$(this).hide();
				}
			});
			$("#before5_2").hide();
			$("#before5_8").hide();
			$("#before5_14").hide();
		}else if(v == "1"){
			$("div[id^='q_5_']").each(function(){
				var intId = $(this).attr("id").substr(4);
				if(intId > 7){
					$(this).hide();
				}else{
					$(this).show();
				}
			});
			$("#before5_2").show();
			$("#before5_8").hide();
			$("#before5_14").hide();
		}else if(v == "2"){
			$("div[id^='q_5_']").each(function(){
				var intId = $(this).attr("id").substr(4);
				if(intId > 13){
					$(this).hide();
				}else{
					$(this).show();
				}
			});
			$("#before5_2").show();
			$("#before5_8").show();
			$("#before5_14").hide();
		}else{
			$("div[id^='q_5_']").each(function(){
				var intId = $(this).attr("id").substr(4);
				$(this).show();
			});
			$("#before5_2").show();
			$("#before5_8").show();
			$("#before5_14").show();
		}
	}
	
	$("input[inputAutoCompleted='yes']").each(function(){
		$(this).attr("placeholder","请输入首字母进行检索...");
		var obj = $(this)[0];
		
		inputAutocomplete(obj);
	});
	
	function inputAutocomplete(obj){
		var code = $(obj).attr("code").trim();
		var prevTrueCode = "";
		if(code == "DISTRICT.02" || code == "DISTRICT.03"){
    		var objName = $(obj).attr("name");
    		var objNameArr = objName.split("_");
    		var previousObjName = objNameArr[0]+"_"+objNameArr[1]+"_"+(objNameArr[2]-1);
    		prevTrueCode =  $("#dict"+previousObjName).val();
    	}
		var url = "examJava.jsp?selectDict=true&code="+code+"&prevTrueCode="+prevTrueCode;
		$(obj).autocomplete(url, {
			max:10,
			minChars:0,
			matchContains: true,
			formatItem: function(row) {
				return row[0];
			}
		}).result(function(event,row,formatted){
			if(row==undefined || row == null || row.length<2)return;
			var prveCode = $("#dict"+$(obj).attr("optionFullId")).val();
			$("#dict"+$(obj).attr("optionFullId")).val(row[1]);
			$("#dictName"+$(obj).attr("optionFullId")).val(row[0]);
			var thisCode = $(obj).attr("code").trim();
        	if(thisCode == "DISTRICT.01" || thisCode == "DISTRICT.02"){
				if(prveCode != row[1]){
					changeCode($(obj).attr("optionFullId"),thisCode);
				}
        	}
		});
	}
	
	function changeCode(name,code){
		if(code == 'DISTRICT.01'){
			var nameArr = name.split("_");
			var nextName = nameArr[0]+"_"+nameArr[1]+"_"+(parseInt(nameArr[2])+1);
			var nextnextName = nameArr[0]+"_"+nameArr[1]+"_"+(parseInt(nameArr[2])+2);
			$("#dict"+nextName).val("");
			$("#dict"+nextnextName).val("");
			$("input[name='"+nextName+"']").val("");
			$("input[name='"+nextnextName+"']").val("");
			$("input[name='"+nextName+"']").unautocomplete();
			$("input[name='"+nextnextName+"']").unautocomplete();
			inputAutocomplete($("input[name='"+nextName+"']")[0]);
			inputAutocomplete($("input[name='"+nextnextName+"']")[0]);
		}
		
		if(code == 'DISTRICT.02'){
			var nameArr = name.split("_");
			var nextName = nameArr[0]+"_"+nameArr[1]+"_"+(parseInt(nameArr[2])+1);
			$("#dict"+nextName).val("");
			$("input[name='"+nextName+"']").val("");
			$("input[name='"+nextName+"']").unautocomplete();
			inputAutocomplete($("input[name='"+nextName+"']")[0]);
		}
	}
	
	<%-- 
	function inputAutocomplete(obj , info ){
		$(obj).autocomplete(info,
				{
				max:9999,
				minChars:0,
				mustMatch:true,
		        formatItem: function(item,i, max) {
		        	var thisCode = $(obj).attr("code").trim();
		        	if(thisCode == "DISTRICT.02" || thisCode == "DISTRICT.03"){
		        		var objName = $(obj).attr("name");
		        		var objNameArr = objName.split("_");
		        		var previousObjName = objNameArr[0]+"_"+objNameArr[1]+"_"+(objNameArr[2]-1);
		        		var prevCode =  $("#dict"+previousObjName).val();
		        		if(prevCode == ""){
		        			return false;
		        		}
		        		if(item.trueCode.substr(0,prevCode.length) == prevCode){
		        			return item.name;
		        		}
		        		return false;
		        	}
		        	return item.name;
		        },
				
			 	formatMatch: function(item,i, max) {
			 		return item.code;
		        },
				
		  		// 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		        	return item.name;
		        }
		}).result(function(event,row,formatted){
			var thisCode = $(obj).attr("code").trim();
        	if(thisCode == "DISTRICT.01" || thisCode == "DISTRICT.02"){
				var prveCode = $("#dict"+$(obj).attr("optionFullId")).val();
				if(prveCode != row.trueCode){
					changeCode($(obj).attr("optionFullId"),thisCode);
				}
        	}
			$("#dict"+$(obj).attr("optionFullId")).val(row.trueCode);
		});
	}
	
	
	var dictCache = {};
	var dictcodes = new Array();
	var dicttmp = {};
	var dictcode ;
	$("input[autoCompleted='yes']").each(function(){
		var obj = $(this)[0];
		if (! dicttmp[$(this).attr("code").trim().replace('.','')] ){
			dictcodes.push($(this).attr("code").trim());
			dicttmp[$(this).attr("code").trim().replace('.','')] = $(this).attr("code").trim();
		}
	});
	
	setTimeout(function(){
		initDict();
	},300);
	
	function initDict(){
		
		dictcode = dictcodes.pop();
		$.post("examJava.jsp",{code:dictcode,selectDict:true},function(info){
			//jAlert('result: ' + info);
			info = eval(info.trim());
			dictCache[dictcode.replace('.','')] = info;
			if ( dictcodes.length > 0 ){
				initDict();
			}else{
				initAuto();
			}
		});
	}
	
	function initAuto(){
		$("input[autoCompleted='yes']").each(function(){
			var obj = $(this)[0];
			dictcode = $(this).attr("code").trim().replace('.','');
			var dictinfo = dictCache[dictcode];
			//jAlert(dictinfo);
			if ( dictinfo ){
				//jAlert('Cache : ' + info);
				inputAutocomplete(obj , dictinfo )
			}else{
				$.post("examJava.jsp",{code:$(this).attr("code").trim(),selectDict:true},function(info){
					//jAlert('result: ' + info);
					info = eval(info.trim());
					inputAutocomplete(obj , info );
				});
			}
		});
	}
	--%>
	
});

function sub(){
	var intervid = $("#intervid").val().trim();
	var paperCode = "<%=paperCode%>";
	if(intervid == ""){
		jAlert("请输入筛查对象条形码");
		return;
	}
	var paperid = '${paper.paperId}';
	//调用检查
	$("#subForm").html("<input type=\"hidden\" name=\"intervid\" value=\""+intervid+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"paperid\" value=\""+paperid+"\"/>");
	var UQSBeginTime = "<%=UQSBeginTime%>";
	var thisBeginTime = "<%=thisBeginTime%>";
	var UQSIsHold = "<%=UQSIsHold%>";
	var UQSRemark = "<%=UQSRemark%>";
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSBeginTime\" value=\""+UQSBeginTime+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"thisBeginTime\" value=\""+thisBeginTime+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSIsHold\" value=\""+UQSIsHold+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSRemark\" value=\""+UQSRemark+"\"/>");
	<% if ( showbar != null ){ %>
		$("#subForm").append("\n<input type=\"hidden\" name=\"showbar\" value=\"false\"/>");
	<%} %>

	$("input[questionType='SINGLE']:checked:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='SINGLEFILL']:checked:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = "";
		if($("input[name='"+id+"']").val() != undefined){
			value = $("input[name='"+id+"']").val();
		}
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+value+"\"/>");
	});
	
	$("input[questionType='MULTI']:checked:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		if(paperCode == "002" && id.substr(0,5)=="13_1_")return true;
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='MULTIFILL']:checked:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = "";
		if($("input[name='"+id+"']").val() != undefined){
			value = $("input[name='"+id+"']").val();
		}
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+value+"\"/>");
	});
	
	$("input[questionType='FILLBLANK']:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = $(this).val().trim();
		var trueValue = $("#dict"+id).val();
		if(trueValue == undefined)trueValue = value;
		if(value != ""){
			$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+trueValue+"\"/>");
		}
	});
	
	$("input[questionType='TABLESINGLE']:checked:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='TABLEMULTI']:checked:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='TABLEBLANK']:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = $(this).val().trim();
		if(value != ""){
			$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+value+"\"/>");
		}
	});
	
	$("input[questionType='TABLESELECT']:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = $(this).val();
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+value+"\"/>");
	});
	//校验非空 针对question 查看题中有没有值
	var nullFocusDiv = null;
	var nullFocusTr = null;
	var nullFocusCheckedOption = null;
	var nullFocusOption = null;
	
	$("div[notNull='1']:visible").each(function(){
		var qId = $(this).attr("id").substr(2)+"_";
		if($("input[name^='OPTION"+qId+"']").length == 0){
			if(nullFocusDiv==null)nullFocusDiv=$(this)[0];
		}
	});
	$("tr[notNull='1']:visible").each(function(){
		var trId = $(this).attr("id")+"-";
		if($("input[name^='OPTION"+trId+"']").length == 0){
			if(nullFocusTr==null)nullFocusTr=$(this)[0];
		}
	});
	$("input[isChosenNull='1']:visible").each(function(){
		var optionName = $(this).attr("name");
		if($("input[name='OPTION"+optionName+"']").val() == ""){
			if(nullFocusCheckedOption==null)nullFocusCheckedOption=$(this)[0];
		}
	});
	$("input[notNull='1']:visible").each(function(){
		var optionName = $(this).attr("name");
		if($("input[name='OPTION"+optionName+"']").length==0||$("input[name='OPTION"+optionName+"']").val() == ""){
			if(nullFocusOption==null)nullFocusOption=$(this)[0];
		}
	});
	
	if(nullFocusDiv != null){
		//jAlert(notNullAlertStr);
		jAlert("问卷未完整，请完成后提交",null,function(){
			$(nullFocusDiv).find("input:visible:first").focus();
		});
		return;
	}
	if(nullFocusCheckedOption != null){
		//jAlert(notNullAlertStr);
		jAlert("问卷未完整，请完成后提交",null,function(){
			$(nullFocusCheckedOption).focus();
		});
		return;
	}
	if(nullFocusTr != null){
		//jAlert(notNullAlertStr);
		jAlert("问卷未完整，请完成后提交",null,function(){
			$(nullFocusTr).find("input:visible:first").focus();
		});
		return;
	}
	if(nullFocusOption != null){
		//jAlert(notNullAlertStr);
		jAlert("问卷未完整，请完成后提交",null,function(){
			$(nullFocusOption).focus();
		});
		return;
	}
	
	//针对各问卷写死的情况
	if(paperCode == "001"){
		//身份证校验
		var idCard = $("input[name='OPTION1_5_1']").val();
		if(idCard != undefined && !IdCardValidate(idCard.trim())){
			$("input[optionFullId='1_5_1']").focus();
			jAlert("身份证无效");
			return;
		}
		//联系方式至少填写一个
		if($("input[name='OPTION2_2_2']").length == 0 && $("input[name='OPTION2_3_1']").length == 0){
			$("input[optionFullId='2_2_1']").focus();
			jAlert("家庭电话 和 手机 至少要填写一个");
			return;
		}
		
		if($("input[name='OPTION1_10_8']").length != 0 && $("input[name='OPTION1_5_1']").length == 0 && $("input[name='OPTION1_6_1']").length == 0){
			$("input[optionFullId='1_5_1']").focus();
			jAlert("筛查对象职业为军人，身份证号 和 其他证件号 至少要填写一个");
			return;
		}
	}
	jConfirm("<h2 style=\"color:red\" class=\"stair-title\">提交后不能更改，请确认！</h2>",null,function(r){
		if(r){
			var surePwdHtml = "帐号:<input type=\"text\" id=\"userName\" value='<%=Securitys.getUserName() %>'/><br/>"+
			"密码:<input type=\"password\" id=\"userPwd\"/><br/>"+
			"<input type=\"button\" onclick=\"validateUser();\" value=\"确定\"/>";
			$("input[id^=o_13_1_]").removeAttr("checked","");
			if(paperCode == "002"){
				if(isSpecial()){
					$("#subForm").append("\n<input type=\"hidden\" name=\"isSpecial\" value=\"1\"/>");
				}
				var highdiskValue=highdisk();
				if(highdiskValue.length > 0){
					var nowDate="${nowDate}";
					var highdiskHtml = "<label>高危条码:</label><input type=\"text\" id=\"highdiskCode\"><br/>"+
						"<label>预约时间:</label><input type=\"text\" id=\"highdiskDate\" readOnly onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'"+nowDate+"'});\"><br/>"+
								"<input type=\"button\" onclick=\"sureHighdiskDate();\" value=\"确定\"/>";		
					if(highdiskValue.indexOf("1")>-1){
						$("#o_13_1_1").attr("checked","checked");
						$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION13_1_1\" value=\"\"/>");
					}else if(highdiskValue.indexOf("2")>-1){
						$("#o_13_1_2").attr("checked","checked");
						$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION13_1_2\" value=\"\"/>");
					}else if(highdiskValue.indexOf("3")>-1){
						$("#o_13_1_3").attr("checked","checked");
						$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION13_1_3\" value=\"\"/>");
					}else if(highdiskValue.indexOf("4")>-1){
						$("#o_13_1_4").attr("checked","checked");
						$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION13_1_4\" value=\"\"/>");
					}
					
					jConfirm("<p style=\"font-family:simhei,Arial;\">请回收筛查对象知情同意书第1联，<br/><br/>"+
							"请将以下四项结果记录于“初筛体检结果报告”上:<br/><br/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;平均收缩压:&nbsp;&nbsp;"+$("input[optionFullId='1_7_1']").val()+"mmHg<br/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;平均舒张压:&nbsp;&nbsp;"+$("input[optionFullId='1_8_1']").val()+"mmHg<br/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;平均心率:&nbsp;&nbsp;"+$("input[optionFullId='1_9_1']").val()+"次/分钟<br/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;BMI:&nbsp;&nbsp;"+$("input[optionFullId='3_3_1']").val()+"kg/m<sub>2</sub><br/><br/>"+
							"此对象为高危，请核实问卷准确无误，预约高危对象调查时间。请筛查对象携带资料，前往健康咨询处。",null,function(r){
								if(r)
						popWinWithHtmlInfo(highdiskHtml,"请分配高危条码并预约高危时间",600,400);
						var intervCodeObj = new intervCode();
						intervCodeObj.setPaperCode("004");
						
						var inputIntervCode = $("#highdiskCode");
						if(inputIntervCode.length>0){
							$("#highdiskCode").keydown(function(e){
								intervCodeObj.checkCode(e);
							});
						}
					});
				}else{
					$("#o_13_1_5").attr("checked","checked");
					$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION13_1_5\" value=\"\"/>");
					jConfirm("<p style=\"font-family:simhei,Arial;\">请回收筛查对象知情同意书第1联，<br/><br/>"+
							"请将以下四项结果记录于“初筛体检结果报告”上:<br/><br/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;平均收缩压:&nbsp;&nbsp;"+$("input[optionFullId='1_7_1']").val()+"mmHg<br/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;平均舒张压:&nbsp;&nbsp;"+$("input[optionFullId='1_8_1']").val()+"mmHg<br/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;平均心率:&nbsp;&nbsp;"+$("input[optionFullId='1_9_1']").val()+"次/分钟<br/>"+
							"&nbsp;&nbsp;&nbsp;&nbsp;BMI:&nbsp;&nbsp;"+$("input[optionFullId='3_3_1']").val()+"kg/m<sub>2</sub><br/><br/>"+
							"此对象为非高危，请核实问卷准确无误。请筛查对象携带资料，前往健康咨询处。",null,function(r){
						if(r)popWinWithHtmlInfo(surePwdHtml,"请重新输入账号密码:",600,400);
					});
				}
			}else if(paperCode == "005"){
				var followUpHtml = "<input type=\"text\" id=\"followUpDate\" readOnly value=\"${nowDateAdd30}\" onclick=\"WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'"+nowDate+"'});\"><br/>"+
				"<input type=\"button\" onclick=\"sureFollowUpDate();\" value=\"确定\"/>";		
					popWinWithHtmlInfo(followUpHtml,"请选择随访预约时间",600,400);
			}else{
				//重新输入帐号密码
				popWinWithHtmlInfo(surePwdHtml,"请重新输入账号密码:",600,400);
			}
		}
	});
}

function sureHighdiskDate(){
	var date = $("#highdiskDate").val();
	var highdiskCode = $("#highdiskCode").val();
	if(highdiskCode==''){
	 	jAlert('高危编码不能为空', '提示信息',function(r) {
			$("#highdiskCode").focus();
		});
		return;
	}
	if(date == ""){
		jAlert("高危预约时间不能为空",null,function(){
			$("#highdiskDate").focus();
		});
		return;
	}
	
	if(highdiskCode.substr(0,1) != "G" || !highdiskCode.substr(1).verhoeffCheck()){
		jAlert("输入的高危编码不正确.","提示信息",function(r){
			$("#highdiskCode").val('');
			$("#highdiskCode").focus();
		});
		return;
	}
	
	$.post("examJava.jsp",{checkHighdiskCodeRepeat:true,highdiskCode:highdiskCode},function(info){
		info = info.trim();
		if(info == "1"){
			jAlert("该高危编码已经被使用.","提示信息",function(r){
				$("#highdiskCode").val('');
				$("#highdiskCode").focus();
			});
			return;
		}else{
			$("#subForm").append("\n<input type=\"hidden\" name=\"highdiskCode\" value=\""+highdiskCode+"\"/>");
			$("#subForm").append("\n<input type=\"hidden\" name=\"highdiskDate\" value=\""+date+"\"/>");
			var surePwdHtml = "帐号:<input type=\"text\" id=\"userName\" value='<%=Securitys.getUserName() %>'/><br/>"+
			"密码:<input type=\"password\" id=\"userPwd\"/><br/>"+
			"<input type=\"button\" onclick=\"validateUser();\" value=\"确定\"/>";
			//重新输入帐号密码
			popWinWithHtmlInfo(surePwdHtml,"请重新输入账号密码:",600,400);
		}
	});
	
	
}

function sureFollowUpDate(){
	var date = $("#followUpDate").val();
	if(date == ""){
		jAlert("随访预约时间不能为空",null,function(){
			$("#followUpDate").focus();
		});
		return;
	}
	$("#subForm").append("\n<input type=\"hidden\" name=\"followUpDate\" value=\""+date+"\"/>");
	var surePwdHtml = "帐号:<input type=\"text\" id=\"userName\" value='<%=Securitys.getUserName() %>'/><br/>"+
	"密码:<input type=\"password\" id=\"userPwd\"/><br/>"+
	"<input type=\"button\" onclick=\"validateUser();\" value=\"确定\"/>";
	//重新输入帐号密码
	popWinWithHtmlInfo(surePwdHtml,"请重新输入账号密码:",600,400);
}

function highdisk(){
	var returnValue = "";
	//判断疾病史 7_1_3 || 7_1_4 || 7_1_5 || 7_1_6
	if($("input[name='OPTION7_1_3']").length>0 || $("input[name='OPTION7_1_4']").length>0 ||$("input[name='OPTION7_1_5']").length>0 ||$("input[name='OPTION7_1_6']").length>0){
		returnValue +="1";
	}
	//判断血压血脂1_7_1 >=160 || 1_8_1 >= 100|| 2_2_1<0.78mmol/L || 2_4_1>=4.14mmol/L
	if($("input[name='OPTION1_7_1']").val()>=160 || $("input[name='OPTION1_8_1']").val()>=100){
		returnValue +="2";
	}
	if($("input[name='OPTION2_2_1']").val()<0.78 || ($("input[name='OPTION2_4_1']").val()>=4.14 && $("input[name='OPTION2_4_1']").val() < 99)){
		returnValue +="3";
	}
	//WHO指南？？？？？？？？？？？
	
	//7_1_2(糖尿病,多选)
	try{
		var diabetes = $("input[name='OPTION7_1_2']").length > 0;
		var sex = $("input[name='OPTION4_2_1']").length > 0?1:2;
		var smoking = $("input[name='OPTION5_1_1']").length > 0;
		var age = parseInt($("input[name='OPTION4_1_1']").val());
		var bloodPressure = parseInt($("input[name='OPTION1_7_1']").val());
		var cholesterol = parseFloat($("input[name='OPTION2_1_1']").val());
		if(isHighDisk(diabetes,sex,smoking,age,bloodPressure,cholesterol)){
			returnValue +="4";
		}
	}catch(e){}
	
	return returnValue;
}

function isSpecial(){
	var b913 = $("input[name='OPTION9_1_3']").length>0;
	var b923 = $("input[name='OPTION9_2_3']").length>0;
	var b933 = $("input[name='OPTION9_3_3']").length>0;
	var b943 = $("input[name='OPTION9_4_3']").length>0;
	var b953 = $("input[name='OPTION9_5_3']").length>0;
	var b954 = $("input[name='OPTION9_5_4']").length>0;
	var b963 = $("input[name='OPTION9_6_3']").length>0;
	
	return b913||b923||b933||b943||b953||b954||b963;
}

function tempSub(){
	var intervid = $("#intervid").val().trim();
	
	if(intervid == ""){
		jAlert("请输入筛查对象条形码");
		return;
	}
	var paperid = '${paper.paperId}';
	//调用检查
	$("#subForm").html("<input type=\"hidden\" name=\"intervid\" value=\""+intervid+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"paperid\" value=\""+paperid+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"tempSave\" value=\"true\"/>");
	var UQSBeginTime = "<%=UQSBeginTime%>";
	var thisBeginTime = "<%=thisBeginTime%>";
	var UQSRemark = "<%=UQSRemark%>";
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSBeginTime\" value=\""+UQSBeginTime+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"thisBeginTime\" value=\""+thisBeginTime+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSIsHold\" value=\"1\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSRemark\" value=\""+UQSRemark+"\"/>");

	<% if ( showbar != null ){ %>
		$("#subForm").append("\n<input type=\"hidden\" name=\"showbar\" value=\"false\"/>");
		
	<%} %>
	
	$("input[questionType='SINGLE']:checked").each(function(){
		var id =  $(this).attr("optionFullId");
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='SINGLEFILL']:checked").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = "";
		if($("input[name='"+id+"']").val() != undefined){
			value = $("input[name='"+id+"']").val();
		}
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+value+"\"/>");
	});
	
	$("input[questionType='MULTI']:checked").each(function(){
		var id =  $(this).attr("optionFullId");
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='MULTIFILL']:checked").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = "";
		if($("input[name='"+id+"']").val() != undefined){
			value = $("input[name='"+id+"']").val();
		}
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+value+"\"/>");
	});
	
	$("input[questionType='FILLBLANK']").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = $(this).val().trim();
		var trueValue = $("#dict"+id).val();
		if(trueValue == undefined)trueValue = value;
		if(value != ""){
			$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+trueValue+"\"/>");
		}
	});
	
	$("input[questionType='TABLESINGLE']:checked").each(function(){
		var id =  $(this).attr("optionFullId");
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='TABLEMULTI']:checked").each(function(){
		var id =  $(this).attr("optionFullId");
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='TABLEBLANK']").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = $(this).val().trim();
		if(value != ""){
			$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+value+"\"/>");
		}
	});
	
	$("input[questionType='TABLESELECT']").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = $(this).val();
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\""+value+"\"/>");
	});
	
	$("#subForm")[0].submit();
}

function clickSelect(obj,ids,rejectother,nameStart){
	if($(obj).next().text().trim() == "片/粒"){
		jAlert("请尽量使用g、mg等计量单位");
	}

	if(rejectother != undefined && rejectother != ""){
		if(obj.checked){
			$("input[id^='o_"+nameStart+"']").each(function(){
				if($(this).attr("id") != $(obj).attr("id")){
					$(this).removeAttr("checked");
					$(this).attr("disabled","disabled");
				}
			});
			$(obj).parent().parent().find("input[type='text']").each(function(){
				if("o_"+$(this).attr("name") != $(obj).attr("id")){
					$(this).val("");
					$(this).attr("disabled","disabled");
				}
			});
		}else{
			$("input[id^='o_"+nameStart+"']").each(function(){
				if($(this).attr("id") != $(obj).attr("id")){
					$(this).removeAttr("disabled");
				}
			});
			$(obj).parent().parent().find("input[type='text']").removeAttr("disabled");
		}
		<c:forEach items="${paper.QGroup }" var="qg">
			<c:forEach items="${qg.qs }" var="q">
				showOrHide('${qg.id}_${q.id}');
			</c:forEach>
		</c:forEach>
	}
	
	
	//以下为个性化设置 start
	var paperid = '${paper.paperId}';
	var id=$(obj).attr("id");
	var paperCode = "<%=paperCode%>";
	if(paperCode == "002"){
		if(id.substr(0,6) == "o_5_1_"){
			if($("#o_5_1_1")[0].checked){
				$("#o_14_1_4").attr("checked","checked");
			}else{
				$("#o_14_1_4").removeAttr("checked");
			}
		}
		if(id.substr(0,6) == "o_6_1_"){
			if($("#o_6_1_2")[0].checked || $("#o_6_1_3")[0].checked || $("#o_6_1_4")[0].checked || $("#o_6_1_5")[0].checked){
				$("#o_14_1_5").attr("checked","checked");
			}else{
				$("#o_14_1_5").removeAttr("checked");
			}
		}
	}
	if(paperCode == "003"){
		if(id.substr(0,6) == "o_1_1_"){
			if($("#o_1_1_3")[0].checked || $("#o_1_1_4")[0].checked){
				$("#o_9_1_4").attr("checked","checked");
			}else{
				$("#o_9_1_4").removeAttr("checked");
			}
		}
		if(id.substr(0,6) == "o_2_1_"){
			if($("#o_2_1_2")[0].checked || $("#o_2_1_3")[0].checked || $("#o_2_1_4")[0].checked || $("#o_2_1_5")[0].checked){
				$("#o_9_1_5").attr("checked","checked");
			}else{
				$("#o_9_1_5").removeAttr("checked");
			}
		}
	}
	
	if(paperCode == "006"){
		if(id.substr(0,6) == "o_1_2_"){
			if($("#o_1_2_2")[0].checked){
				$("div[id='qg_4']").hide();
				$("div[id='qg_11']").hide();
			}else{
				$("div[id='qg_4']").show();
				$("div[id='qg_11']").show();
			}
		}
		if(id.substr(0,6) == "o_2_1_"){
			if($("#o_2_1_1")[0].checked){
				$("div[id^='qg_']").each(function(){
					var intId = $(this).attr("id").substr(3);
					if(intId > 2){
						$(this).hide();
					}
				});
			}else{
				$("div[id^='qg_']").each(function(){
					var intId = $(this).attr("id").substr(3);
					if(intId > 2){
						$(this).show();
					}
				});
			}
		}
	}
	var optionFullIdArr = $(obj).attr("optionFullId").split("_");
	$("#q_"+optionFullIdArr[0]+"_"+optionFullIdArr[1]).find("input[type='text']").each(function(){
		if(!$("input[optionFullId='"+$(this).attr("name")+"']")[0].checked){
			$(this).val("");
		}
	});
	
	//以上为个性化设置 start
	
	if(ids == "")return;
	var arr = ids.split(",");
	for(var i in arr){
		showOrHide(arr[i]);
	}
}

function clickTableSingle(obj){
	var paperCode = "<%=paperCode%>";
	var id=$(obj).attr("id");
	if(paperCode == "003"){
		if(id.substr(0,6)=="o_5_1_"){
			var tempPartId = id.substr(6,1); 
			if($("#o_5_1_"+tempPartId+"-1")[0].checked || $("#o_5_1_"+tempPartId+"-3")[0].checked){
				$("input[name='5_1_"+tempPartId+"-2']").val("");
				$("input[name='5_1_"+tempPartId+"-2']").attr("disabled","disabled");
			}else{
				$("input[name='5_1_"+tempPartId+"-2']").removeAttr("disabled");
			}
		}
	}
}

function showOrHide(questionId){
	//优先级 ，> |
	var controlShow = $("#q_"+questionId).attr("controlshow");
	var controlHide = $("#q_"+questionId).attr("controlhide");
	var sureShow = false;
	if(controlShow != undefined && controlShow != ""){
		var arr1 = controlShow.split("|");
		var length = arr1.length;
		for(var i = 0;i<arr1.length;i++){
			var show = true;
			var arr2 = arr1[i].split(",");
			for(var j = 0;j<arr2.length;j++){
				if(!checkShowOrHide(arr2[j])){
					show = false;
					break;
				}
			}
			if(show){
				sureShow = true;
				break;
			}
		}
		if(sureShow){
			$("#q_"+questionId).show();
		}else{
			$("#q_"+questionId).hide();
		}
	}
	
	if(controlHide != undefined && controlHide != ""){
		var arr1 = controlHide.split("|");
		var length = arr1.length;
		var sureHide = false;
		for(var i = 0;i<arr1.length;i++){
			var arr2 = arr1[i].split(",");
			var hide = true;
			for(var j = 0;j<arr2.length;j++){
				if(!checkShowOrHide(arr2[j])){
					hide = false;
					break;
				}
			}
			if(hide){
				sureHide = true;
				break;
			}
		}
		if(sureHide){
			$("#q_"+questionId).hide();
			//$("#q_"+questionId).find("input[type='radio']").removeAttr("checked");
			//$("#q_"+questionId).find("input[type='checkBox']").removeAttr("checked");
		}else{
			$("#q_"+questionId).show();
		}
	}
}

function checkShowOrHide(rule){
	var m = rule.substr(0,1);
	var oId = rule.substr(1);
	if(m == "S" && $("#o_"+oId)[0].checked){
		return true;
	}
	
	if(m == "U" && !$("#o_"+oId)[0].checked){
		return true;
	}
	return false;
}

function cancleSingleCheck(obj){
	$(obj)[0].checked=false;
	
	var paperCode = "<%=paperCode%>";
	var id=$(obj).attr("id");
	if(paperCode == "003"){
		if(id.substr(0,6)=="o_5_1_"){
			var tempPartId = id.substr(6,1); 
			if($("#o_5_1_"+tempPartId+"-1")[0].checked || $("#o_5_1_"+tempPartId+"-3")[0].checked){
				$("input[name='5_1_"+tempPartId+"-2']").val("");
				$("input[name='5_1_"+tempPartId+"-2']").attr("disabled","disabled");
			}else{
				$("input[name='5_1_"+tempPartId+"-2']").removeAttr("disabled");
			}
		}
	}
	
	if(paperCode == "006"){
		if(id.substr(0,6) == "o_1_2_"){
			if($("#o_1_2_2")[0].checked){
				$("div[id='qg_4']").hide();
				$("div[id='qg_11']").hide();
			}else{
				$("div[id='qg_4']").show();
				$("div[id='qg_11']").show();
			}
		}
		if(id.substr(0,6) == "o_2_1_"){
			if($("#o_2_1_1")[0].checked){
				$("div[id^='qg_']").each(function(){
					var intId = $(this).attr("id").substr(3);
					if(intId > 2){
						$(this).hide();
					}
				});
			}else{
				$("div[id^='qg_']").each(function(){
					var intId = $(this).attr("id").substr(3);
					if(intId > 2){
						$(this).show();
					}
				});
			}
		}
	}
}

function formatNumber(obj,n){
	var v = $(obj).val().trim();
	if(v == "")return;
	if(isNaN(v)){
	//	jAlert("必须是数字",null,function(){
	//		$(obj).focus();
	//	});
		return;
	}
	$(obj).val(parseFloat(v).toFixed(parseInt(n)));
}

function checkScope(v,type,vScope){
	if(type == "number"){
		if(isNaN(v)){
			return false;
		}
		var arr = vScope.split(",");
		arr[0]  = arr[0].trim();
		arr[1]  = arr[1].trim();
		var min = arr[0].substr(1);
		var minChar = arr[0].substr(0,1);
		
		var max = arr[1].substr(0,arr[1].length-1).trim();
		var maxChar = arr[1].substr(arr[1].length-1);
		if(min!=""){
			if(minChar == "("){
				if(parseFloat(v) <= parseFloat(min))return false;
			}else{
				if(parseFloat(v) < parseFloat(min))return false;
			}
		}
		if(max!=""){
			if(maxChar == ")"){
				if(parseFloat(v) >= parseFloat(max))return false;
			}else{
				if(parseFloat(v) > parseFloat(max))return false;
			}
		}
	}
	if(type == "date"){
	}
	return true;
}

function validateUser(){
	var userName = $("#userName").val().trim();
	var userPwd = $("#userPwd").val().trim();
	if(userName == ""){
		jAlert("帐号不能为空",null,function(){
			$("#userName").focus();
		});
		return false;
	}
	if(userPwd == ""){
		jAlert("密码不能为空",null,function(){
			$("#password").focus();
		});
		return false;
	}
	$.post("examJava.jsp",{userName:userName,userPwd:userPwd,validateUser:true},function(info){
		info = info.trim();
		if(info == "1"){
			$("#subForm")[0].submit();
		}else{
			jAlert("帐号或密码不正确",null,function(){
				$("#userName").focus();
			});
		}
		
	});
}

function blurRule(obj,format,type,scope){
	if($(obj).attr("inputAutoCompleted")=="yes"){
		$(obj).val($("#dictName"+$(obj).attr("optionFullId")).val());
		return;
	}
	
	
	var v = $(obj).val().trim();
	$(obj).val(v);
	if(v == "")return;
	if(type == "number" || scope != ""){
		if(isNaN(v)){
			jAlert("录入结果必须是数字",null,function(){
				$(obj).focus();
			});
			return;
		}
		if(type == "number" && format == ""){
			 $(obj).val(parseInt(v));
		}
	}
	
	var paperid = '${paper.paperId}';
	var paperCode = "<%=paperCode%>";
	
	if(format != null && format != "" && !isNaN(v)){
		$(obj).val(parseFloat(v).toFixed(parseInt(format)));
	}
	
	//处理需要计算的题
	var confirmList = [];
	var r = false;
	var qg_q_o = $(obj).attr("name").trim();
	
	if(paperCode == "003"){
		if(qg_q_o == "3_7_3-1" || qg_q_o == "3_7_4-1" ){
			if($(obj).val() != "" && (parseInt($(obj).val())<0 || parseInt($(obj).val())>24)){
				jAlert("请录入正确的数据",null,function(){
					$(obj).val("");
					$(obj).focus();
				});
				return;
			}
			var v1 = $("input[name='3_7_3-1']").val();
			var v2 = $("input[name='3_7_4-1']").val();
			if(v1 != "" && v2 != "" && parseInt(v1) < parseInt(v2)){
				jAlert("请录入正确的数据",null,function(){
					$(obj).val("");
					$(obj).focus();
				});
				return;
			}
		}
	}
	if(paperCode == "006"){
		if(qg_q_o == "10_6_1"){
			if($(obj).val() != "" && (parseInt($(obj).val())<0 || parseInt($(obj).val())>100)){
				jAlert("请录入正确的数据",null,function(){
					$(obj).val("");
					$(obj).focus();
				});
				return;
			}
		}
	}
	
	$("input[validate='yes']").each(function(){
		try{
		var validateVal = $(this).val();
		if(validateVal.indexOf(qg_q_o.substr(0,5)) == -1)return true;
		
		var arr=validateVal.split(/-|<|\/|>|\(|\)|\+|&&|\*|=/);
		if($(this).attr("validateType") == "calculate"){
			for(var i in arr){
				if(arr[i].trim().length == 0 || arr[i].trim().substr(0,1)!="#")continue;
				var this_qg_q_o = arr[i].trim().substr(1).replace("d","-");
				var v = $("input[name='"+this_qg_q_o+"']").val().trim();
				if(v == "" || isNaN(v))break;
				validateVal = validateVal.replace(/d/g,"-").replace(eval("/#"+this_qg_q_o+"/g"),v);
			}
			if(validateVal.indexOf("#")==-1)$("input[name='"+$(this).attr("controlOption")+"']").val(eval(validateVal).toFixed(1));
		}
		if($(this).attr("validateType") == "calculateandvalidate"){
			for(var i in arr){
				if(arr[i].trim().length == 0 || arr[i].trim().substr(0,1)!="#")continue;
				var this_qg_q_o = arr[i].trim().substr(1).replace("d","-");
				var v = $("input[name='"+this_qg_q_o+"']").val();
				if(v==undefined ||v.trim() == "" || isNaN(v))continue;
				validateVal = validateVal.replace(/d/g,"-").replace(eval("/#"+this_qg_q_o+"/g"),v);
			}
			if(validateVal.indexOf("#")==-1 && !eval(validateVal)){
				//jConfirm("请确认录入结果是否无误",null,function(){
				//	$(obj).focus();
				//});
				if(validateVal.indexOf("=")>-1 && validateVal.indexOf("<")==-1 && validateVal.indexOf(">")==-1){
					r=true;
					jAlert("两次输入结果不一致，请修改",null,function(){
						$(obj).val("");
						$(obj).focus();
					});
				}else{
					confirmList.push("请确认录入结果是否无误");
				}
				
			}
		}
		}catch(e){}
	});
	if(r){
		return;
	}
	
	if(scope != null && scope != ""){
		
		if(!checkScope(v,type,scope)){
			confirmList[0] = "请确认录入结果是否无误";
		}	
	}
	
	if(paperCode == "002"){
		if(qg_q_o == "7_1_1" ||qg_q_o == "7_1_2" ||qg_q_o == "7_1_3" ||qg_q_o == "7_1_4" || qg_q_o == "7_1_5"||qg_q_o == "7_1_6"){
			var nowYear = new Date().getFullYear();
			if(isNaN(v) || parseInt(v)+""=="NaN" || parseInt(v)<1934 || parseInt(v)>nowYear){
				jAlert("请输入正确的年份",null,function(){
					$(obj).focus();
				});
				return;
			}
			var age = $("input[name='4_1_1']").val();
			if(age != "" && nowYear - parseInt(v) > parseInt(age)){
				jAlert("请输入正确的年份",null,function(){
					$(obj).focus();
				});
				return;
			}
		}
	}
	if(paperCode == "003"){
		if(qg_q_o == "5_1_1-2"||qg_q_o == "5_1_2-2"||qg_q_o == "5_1_3-2"||qg_q_o == "5_1_4-2"||qg_q_o == "5_1_5-2"||
				qg_q_o == "5_1_6-2"||qg_q_o == "5_1_7-2"||qg_q_o == "5_1_8-2"||qg_q_o == "5_1_9-2"){
			var nowYear = new Date().getFullYear();
			if(isNaN(v) || parseInt(v)+""=="NaN" || parseInt(v)<1934 || parseInt(v)>nowYear){
				jAlert("请输入正确的年份",null,function(){
					$(obj).focus();
				});
				return;
			}
			<%--  
			var age = $("input[name='4_1_1']").val();
			if(age != "" && nowYear - parseInt(v) > parseInt(age)){
				jAlert("请输入正确的年份",null,function(){
					$(obj).val("");
					$(obj).focus();
				});
				return;
			}
			--%>
		}
	}
	if(confirmList.length > 0){
		jConfirm(confirmList[0],null,function(r){
			if(r){
				jumpSomeQuestion(paperCode,qg_q_o);
			}else{
				$(obj).focus();
				return;
			}
		});
	}else{
		jumpSomeQuestion(paperCode,qg_q_o);
	}
}

//002 -跳过需要计算的题
function jumpSomeQuestion(paperCode,qg_q_o){
	if(paperCode == "002"){
		if(qg_q_o == "1_6_1" ){
			$("input[name='2_1_1']").focus();
			return;
		}
		if(qg_q_o == "3_2_1" ){
			$("input[name='4_1_1']").focus();
			return;
		}
		if(qg_q_o == "10_6_1" ){
			$("input[name='11_1_1']").focus();
			return;
		}
	}
	
	if(paperCode == "006"){
		if(qg_q_o == "4_1_1-2" ){
			$("input[name='4_1_2-1']").focus();
			return;
		}
		if(qg_q_o == "4_1_2-2" ){
			$("input[name='4_1_3-1']").focus();
			return;
		}
		if(qg_q_o == "4_1_3-2" ){
			$("input[name='4_1_4-1']").focus();
			return;
		}
		
		if(qg_q_o == "11_1_1-2" ){
			$("input[name='11_1_2-1']").focus();
			return;
		}
		if(qg_q_o == "11_1_2-2" ){
			$("input[name='11_1_3-1']").focus();
			return;
		}
		if(qg_q_o == "11_1_3-2" ){
			$("input[name='11_1_4-1']").focus();
			return;
		}
	}
}

function showConfirmList(i,list,obj){
	if(i == list.length)return;
	jConfirm(list[i],null,function(r){
		if(r){
			showConfirmList(i+1,list);
		}else{
			$(obj).focus();
			return;
		}
	});
}

function changeMultiFull(id,obj){
	var v = $(obj).val().trim();
	if(v != ""){
		$("#"+id).attr("checked","checked");
	}else{
		$("#"+id).removeAttr("checked");
	}
}

function optionKeyUp(obj,format,type,scope){
	var v = $(obj).val().trim();
	if(v == "")return;
	if(type == "number"){
		if(format == ""){
			 v = parseInt(v);
			 if(v.toString() == "NaN"){
				 $(obj).val("");
			 }else{
				 $(obj).val(v);
			 }
		}else{
			if(v=="."){
				 $(obj).val("0.");
				 return;
			}
			v = v.replace(/[^\d.]/g,"");
			$(obj).val(v);
		}
	}
	
	v = $(obj).val().trim();
	var paperCode = "<%=paperCode%>";
	if(paperCode == "001"){
		if($(obj).attr("name") == "2_2_1"){
			v = $(obj).val().replace(/\D/g,'');
			$(obj).val(v);
		}
	}
	if(paperCode == "006"){
		if($(obj).attr("name") == "5_1_1"){
			if(v == "" || v == "0"){
				$("div[id^='q_5_']").each(function(){
					var intId = $(this).attr("id").substr(4);
					if(intId > 1){
						$(this).hide();
					}
				});
				$("#before5_2").hide();
				$("#before5_8").hide();
				$("#before5_14").hide();
			}else if(v == "1"){
				$("div[id^='q_5_']").each(function(){
					var intId = $(this).attr("id").substr(4);
					if(intId > 7){
						$(this).hide();
					}else{
						$(this).show();
					}
				});
				$("#before5_2").show();
				$("#before5_8").hide();
				$("#before5_14").hide();
			}else if(v == "2"){
				$("div[id^='q_5_']").each(function(){
					var intId = $(this).attr("id").substr(4);
					if(intId > 13){
						$(this).hide();
					}else{
						$(this).show();
					}
				});
				$("#before5_2").show();
				$("#before5_8").show();
				$("#before5_14").hide();
			}else{
				$("div[id^='q_5_']").each(function(){
					var intId = $(this).attr("id").substr(4);
					$(this).show();
				});
				$("#before5_2").show();
				$("#before5_8").show();
				$("#before5_14").show();
			}
			
		}
	}
}


</script>

</body>
</html>
