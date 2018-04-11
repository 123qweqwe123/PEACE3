<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.bdcor.pip.client.vo.paper.*,com.bdcor.pip.client.vo.*,java.util.*,java.io.*"%>
<%@page import="java.text.SimpleDateFormat"%>



<jsp:useBean id="paperBean" class="com.bdcor.pip.client.vo.paper.PaperService"></jsp:useBean>


<%
	String paperId = request.getParameter("paperId");
	String intervId = request.getParameter("intervid");
	String showbar = request.getParameter("showbar");
	//Paper paper = paperBean.getPage(paperId);
	//String paperCode = paper.getPaperVersion().split("\\.")[1];
	//request.setAttribute("paper", paper);
	SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
	String nowDateStr = sf1.format(new Date());
	request.setAttribute("nowDate",nowDateStr);
	Date nowDateAdd30 = new Date();
	nowDateAdd30.setDate(new Date().getDate()+30);
	request.setAttribute("nowDateAdd30", sf1.format(nowDateAdd30));
	SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String UQSBeginTime = sf2.format(new Date());
	String thisBeginTime = UQSBeginTime;
	String UQSIsHold = "2";
	String UQSRemark = "";
	Map<String,String> map = new HashMap<String,String>();
	String filePath = request.getParameter("filePath");
	/**
	if(filePath != null){
		File file = new  File(filePath);
		if(file.exists()){
			Result result = ResultService.instence().getResult(new File(filePath));
			UQSBeginTime = result.getUQSBeginTime()==null?"":result.getUQSBeginTime().trim();
			UQSIsHold = "1";
			UQSRemark = result.getUQSRemark()==null?"":result.getUQSRemark().trim();
			if(result.getOptions() != null){
				for(ResultOption ro:result.getOptions()){
					String dictName = ResultService.getDictName(paper,ro.getQuestionSetId(),ro.getQuestionId(),ro.getResultId(),ro.getResutlStr());
					if(dictName == null){
						map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
					}else{
						map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),dictName);
						map.put("dict"+ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
					}
				}
			}
		}
	}else{
		
	}
	**/
	request.setAttribute("resultOptionMap", map);
	//request.setAttribute("resultOptionMapJson",JsonMapper.objectToJson(map));;
%>

<!DOCTYPE html>
<html>
<head>
<title  id='title'>试卷- <c:out value="${paper.paperName }"></c:out></title>
<script type="text/javascript" src="${ctx}/static/datepicker/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${ctx}/static/datepicker/js/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${ctx}/static/datepicker/js/dateformater.js"></script>
<link rel="stylesheet" href="${ctx}/static/jquery/css/paper.css" type="text/css" />

<script type="text/javascript" src="${ctx}/static/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/static/jquery/js/exam.js"></script>
</head>
<body>
<div id="wrap">

	<div id="main-content">
		<div id="left-main">
		<div id="navbar">
    	</div>
    	<% if(showbar == null ){ %>
    	<div class="flow_steps" style="width:100%;max-width: 1140px;min-width:800px;">
			<ul style="overflow:hidden;width:1004px;">
				<li class="done">1.问卷列表</li>
				<li class="current_prev">2.扫描条码</li>
				<li class="current">3.回答问卷</li>
				<li class="">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
			</ul>
		</div>
		<%} %>
    	
		<h2 class="stair-title">${paper.paperName}  ${paper.paperId}</h2>
		
		
		<c:forEach items="${paper.QGroup }" var="qg">
			 <div id="qg_${qg.id}" controlshow="${qg.controlShow}" controlhide="${qg.controlHide}">
			 <h3 class="stair-title" id="g<c:out value="${qg.id}"></c:out>"><c:out value="${qg.displayname}"></c:out></h3>
			 <c:forEach items="${qg.qs }" var="q">
				<c:choose>
					<c:when test="${q.type eq 'Q_TYPE_SHOWTEXT'}">
						<div id="q_${qg.id}_${q.id}" controlshow="${q.controlShow}" controlhide="${q.controlHide}"
							<c:if test="${q.setHidden eq '1'}">style="display:none"</c:if>>
							<h2  style="color: red">&nbsp;&nbsp;<c:out value="${q.displayname}"/></h2>
						</div>
					</c:when>
					<c:otherwise>
						<div thisTitle="${q.helpText}" displayname="${q.displayname}"
							class="panel panel-default"  id="q_${qg.id}_${q.id}" controlshow="${q.controlShow}" controlhide="${q.controlHide}" qType='${q.type}'
							<c:if test="${q.notNull == '1'}">notNull="1"</c:if>
							<c:if test="${q.setHidden eq '1'}">style="display:none"</c:if>>
							<div class="panel-heading">
								<span><c:out value="${q.displayname}"></c:out></span><c:if test="${q.notNull == '1'}"><span style="color: red">&nbsp;&nbsp;(必填)</span></c:if>
							</div>

							<c:choose>
							<c:when test="${q.type eq 'Q_TYPE_SINGLE'}">
							<!-- 单选题 -->
								<div class="panel-body">
									<div class="form-horizontal">
										<div class="form-group">
										<c:forEach items="${q.options}" var="option">
											<div class="col-sm-6" controlshow="${option.controlShow}" controlhide="${option.controlHide}">
												<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
												<label for="o_${qg.id}_${q.id}_${option.id}">
													<input 
														id="o_${qg.id }_${q.id }_${option.id }" name="${qg.id }_${q.id}" value="<c:out value="${qg.id}_${q.id}_${option.id}"></c:out>"
														questionType="SINGLE" optionFullId="${qg.id}_${q.id}_${option.id}"  type="radio"
														<c:if test="${resultOptionMap[id] != null || option.isdefaultchosen=='1'}">checked</c:if>
														<c:if test="${option.readOnly == '1'}">readOnly</c:if>
														<c:if test="${option.allEnd == '1'}"> allEnd='1' </c:if>
														
														ondblclick="cancleSingleCheck(this);"  onclick="clickSelect(this,'${option.rejectother}','${option.alert}')" >
														
													<span>${option.displayname}</span>
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
												<div class="col-sm-12" controlshow="${option.controlShow}" controlhide="${option.controlHide}">
													<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
													<input 
														id="o_${qg.id }_${q.id }_${option.id }" name="${qg.id }_${q.id }" value="<c:out value="${option.id }"></c:out>"
														questionType="SINGLEFILL" optionFullId="${qg.id}_${q.id}_${option.id}" type="radio"
														<c:if test="${resultOptionMap[id] != null || option.isdefaultchosen=='1'}">checked</c:if> 
														<c:if test="${option.readOnly == '1'}">readOnly</c:if>
														<c:if test="${option.allEnd == '1'}">allEnd = '1'</c:if>
														ondblclick="cancleSingleCheck(this);" onclick="clickSelect(this,'${option.rejectother}','${option.alert}')" >
													
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
															onkeyup="optionKeyUp(this,'${option.charType}');changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
															optionFullId="${qg.id}_${q.id}_${option.id}"
															<c:if test="${option.datatype eq 'DATE'}"> readOnly class="Wdate"
																onclick="WdatePicker({dateFmt:'yyyy-MM-dd'
																<c:if test="${option.minDate != null && option.minDate != ''}">
																,minDate:'${option.minDate}'
																</c:if>
																<c:if test="${option.maxDate != null && option.maxDate != ''}">
																,maxDate:'${option.maxDate}'
																</c:if>
																});"
																onfocus="changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
															</c:if>
															<c:if test="${option.datatype eq 'DATETIME'}"> readOnly class="Wdate"
																onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'
																<c:if test="${option.minDate != null && option.minDate != ''}">
																,minDate:'${option.minDate}'
																</c:if>
																<c:if test="${option.maxDate != null && option.maxDate != ''}">
																,maxDate:'${option.maxDate}'
																</c:if>
																});"
															</c:if>
															<c:if test="${option.code != null && option.code != ''}">inputAutoCompleted="yes" code="${option.code}"</c:if>
															<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
															<c:if test="${option.isChosenNull != null}">isChosenNull="1"</c:if>
															<c:if test="${option.calculate != null}">calculate="${option.calculate}"</c:if>
															<c:if test="${option.calculateandvalidate != null}">calculateandvalidate="<c:out value='${option.calculateandvalidate}'></c:out>"</c:if>
															onblur="blurRule(this,'${option.textType}','${option.format}','${option.scope}','${option.constrainScope}')"
														    type="text" value="${resultOptionMap[id]}" questionName="${q.displayname}" name="${qg.id}_${q.id}_${option.id}" >
														    <c:out value="${option.afterDisplayName}"></c:out>
													         
															<c:if test="${option.code != null && option.code != ''}">
																<input type="text" id="dictName${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
																<c:set var="id" value="dict${qg.id}_${q.id}_${option.id}"></c:set>
																<input type="text" onchange="changeCode('${qg.id}_${q.id}_${option.id}',${option.code}')" id="dict${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
															</c:if>
													</c:if>
													
													<c:if test="${option.datastyle eq 'Q_TYPE_TEXTAREA'}">
														<label for="o_${qg.id }_${q.id }_${option.id }">
															<c:out value="${option.beforeDisplayName}"></c:out>
														</label>
														<textarea rows="4" cols="80"
															onkeyup="optionKeyUp(this,'${option.charType}');changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
															optionFullId="${qg.id}_${q.id}_${option.id}"
															<c:if test="${option.code != null && option.code != ''}">inputAutoCompleted="yes" code="${option.code}"</c:if>
															<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
															<c:if test="${option.isChosenNull != null}">isChosenNull="1"</c:if>
															<c:if test="${option.calculate != null}">calculate="${option.calculate}"</c:if>
															<c:if test="${option.calculateandvalidate != null}">calculateandvalidate="<c:out value='${option.calculateandvalidate}'></c:out>"</c:if>
															onblur="blurRule(this,'${option.textType}','${option.format}','${option.scope}','${option.constrainScope}')"
														    type="text" value="${resultOptionMap[id]}" questionName="${q.displayname}" name="${qg.id}_${q.id}_${option.id}"></textarea><c:out value="${option.afterDisplayName}"></c:out>
													
															<c:if test="${option.code != null && option.code != ''}">
																<input type="text" id="dictName${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
																<c:set var="id" value="dict${qg.id}_${q.id}_${option.id}"></c:set>
																<input type="text" onchange="changeCode('${qg.id}_${q.id}_${option.id}',${option.code}')" id="dict${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
															</c:if>
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
											<div class="col-sm-6"  controlshow="${option.controlShow}" controlhide="${option.controlHide}">
												<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
												<input 
													 id="o_${qg.id }_${q.id }_${option.id }" name="${qg.id }_${q.id }" value="<c:out value="${option.id }"></c:out>"
													 questionType="MULTI" optionFullId="${qg.id}_${q.id}_${option.id}" type="checkbox"
												<c:if test="${resultOptionMap[id] != null || option.isdefaultchosen=='1'}">checked</c:if> 
												<c:if test="${option.readOnly == '1'}">readOnly</c:if> 
												<c:if test="${option.allEnd == '1'}">allEnd = '1'</c:if>
												onclick="clickSelect(this,'${option.rejectother}','${option.alert}')" >
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
												<input 
													 id="o_${qg.id }_${q.id }_${option.id }" name="${qg.id }_${q.id }" value="<c:out value="${option.id }"></c:out>"
													 questionType="MULTIFILL" optionFullId="${qg.id}_${q.id}_${option.id}" type="checkbox" 
													<c:if test="${resultOptionMap[id] != null || option.isdefaultchosen=='1'}">checked</c:if>
													<c:if test="${option.readOnly == '1'}">readOnly</c:if>
													<c:if test="${option.allEnd == '1'}">allEnd = '1'</c:if>
													onclick="clickSelect(this,'${option.rejectother}','${option.alert}')" >
													
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
														onkeyup="optionKeyUp(this,'${option.charType}');changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
														<c:if test="${option.datatype eq 'DATE'}"> readOnly class="Wdate"
															onclick="WdatePicker({dateFmt:'yyyy-MM-dd'
															<c:if test="${option.minDate != null && option.minDate != ''}">
																,minDate:'${option.minDate}'
																</c:if>
																<c:if test="${option.maxDate != null && option.maxDate != ''}">
																,maxDate:'${option.maxDate}'
																</c:if>
																});"
															onfocus="changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
														</c:if>
														<c:if test="${option.datatype eq 'DATETIME'}"> readOnly class="Wdate"
															onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'
															<c:if test="${option.minDate != null && option.minDate != ''}">
																,minDate:'${option.minDate}'
																</c:if>
																<c:if test="${option.maxDate != null && option.maxDate != ''}">
																,maxDate:'${option.maxDate}'
																</c:if>
																});"
														</c:if>
														<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
														<c:if test="${option.isChosenNull != null}">isChosenNull="1"</c:if>
														<c:if test="${option.calculate != null}">calculate="${option.calculate}"</c:if>
														<c:if test="${option.calculateandvalidate != null}">calculateandvalidate="<c:out value='${option.calculateandvalidate}'></c:out>"</c:if>
														onblur="blurRule(this,'${option.textType}','${option.format}','${option.scope}','${option.constrainScope}')"
													    type="text" value="${resultOptionMap[id]}" questionName="${q.displayname}" name="${qg.id}_${q.id}_${option.id}"><c:out value="${option.afterDisplayName}"></c:out>
												</c:if>
												
												<c:if test="${option.datastyle eq 'Q_TYPE_TEXTAREA'}">
													<label for="o_${qg.id }_${q.id }_${option.id }">
													<c:out value="${option.beforeDisplayName}"></c:out>
													</label>
													<textarea rows="4" cols="80"
														onkeyup="optionKeyUp(this,'${option.charType}');changeMultiFull('o_${qg.id }_${q.id }_${option.id }',this);"
														<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
														<c:if test="${option.isChosenNull != null}">isChosenNull="1"</c:if>
														<c:if test="${option.calculate != null}">calculate="${option.calculate}"</c:if>
														<c:if test="${option.calculateandvalidate != null}">calculateandvalidate="<c:out value='${option.calculateandvalidate}'></c:out>"</c:if>
														onblur="blurRule(this,'${option.textType}','${option.format}','${option.scope}','${option.constrainScope}')"
													    type="text" value="${resultOptionMap[id]}" questionName="${q.displayname}" name="${qg.id}_${q.id}_${option.id}"></textarea><c:out value="${option.afterDisplayName}"></c:out>
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
											<c:set var="id" value="${qg.id}_${q.id}_${option.id}"></c:set>
											<c:out value="${option.beforeDisplayName}"></c:out>
											 
											<c:if test="${option.datastyle eq 'Q_TYPE_FILL_BLANK'}">
												<input type="text" 
													<c:if test="${option.readOnly != null && option.readOnly != ''}"> readOnly</c:if>
													<c:if test="${option.datatype eq 'DATE'}"> readOnly class="Wdate"
														onclick="WdatePicker({dateFmt:'yyyy-MM-dd'
														<c:if test="${option.minDate != null && option.minDate != ''}">
																,minDate:'${option.minDate}'
																</c:if>
																<c:if test="${option.maxDate != null && option.maxDate != ''}">
																,maxDate:'${option.maxDate}'
																</c:if>
																});"
													</c:if>
													<c:if test="${option.datatype eq 'DATETIME'}"> readOnly class="Wdate"
														onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'
														<c:if test="${option.minDate != null && option.minDate != ''}">
																,minDate:'${option.minDate}'
																</c:if>
																<c:if test="${option.maxDate != null && option.maxDate != ''}">
																,maxDate:'${option.maxDate}'
																</c:if>
																});"
													</c:if>
													<c:if test="${option.code != null && option.code != ''}">inputAutoCompleted="yes" code="${option.code}"</c:if>
													<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
													<c:if test="${option.notNull == '1'}">notNull="1"</c:if>
													<c:if test="${option.calculate != null}">calculate="${option.calculate}"</c:if>
													<c:if test="${option.calculateandvalidate != null}">calculateandvalidate="<c:out value='${option.calculateandvalidate}'></c:out>"</c:if>
													<c:if test="${option.idCard != null}">idCard="1"</c:if>
													onkeyup="optionKeyUp(this,'${option.charType}');"
													onblur="blurRule(this,'${option.textType}','${option.format}','${option.scope}','${option.constrainScope}')"
													width="40px;" value="${resultOptionMap[id]}" questionType="FILLBLANK" optionFullId="${qg.id}_${q.id}_${option.id}" name="${qg.id }_${q.id }_${option.id }" value="">
												<c:out value="${option.afterDisplayName}"></c:out>
												<c:if test="${option.code != null && option.code != ''}">
													<input type="hidden" id="dictName${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
													<c:set var="id" value="dict${qg.id}_${q.id}_${option.id}"></c:set>
													<input type="hidden" id="dict${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
												</c:if>
											</c:if>
										
											
											<c:if test="${option.datastyle eq 'Q_TYPE_TEXTAREA'}">
												<textarea type="text" rows="6" cols="100"
													<c:if test="${option.readOnly != null && option.readOnly != ''}"> readOnly</c:if>
													<c:if test="${option.code != null && option.code != ''}">inputAutoCompleted="yes" code="${option.code}"</c:if>
													<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
													<c:if test="${option.notNull == '1'}">notNull="1"</c:if>
													<c:if test="${option.calculate != null}">calculate="${option.calculate}"</c:if>
													<c:if test="${option.calculateandvalidate != null}">calculateandvalidate="<c:out value='${option.calculateandvalidate}'></c:out>"</c:if>
													<c:if test="${option.idCard != null}">idCard="1"</c:if>
													onkeyup="optionKeyUp(this,'${option.charType}');"
													onblur="blurRule(this,'${option.textType}','${option.format}','${option.scope}','${option.constrainScope}')"
													width="40px;" value="${resultOptionMap[id]}" questionType="FILLBLANK" optionFullId="${qg.id}_${q.id}_${option.id}" name="${qg.id }_${q.id }_${option.id }"><c:out value="${resultOptionMap[id]}"/></textarea>
												<c:out value="${option.afterDisplayName}"></c:out>
												<c:if test="${option.code != null && option.code != ''}">
													<input type="hidden" id="dictName${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
													<c:set var="id" value="dict${qg.id}_${q.id}_${option.id}"></c:set>
													<input type="hidden" id="dict${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
												</c:if>
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
															&nbsp;
														</td>
													</c:forEach>
												</tr>
												<c:forEach items="${q.talbe.tableTr}" var="tableTr">
													<tr notNull="1" id="${qg.id }_${q.id }_${tableTr.rownum}">
														<td style="width:40%;">${tableTr.displayname}</td>
														<c:forEach items="${tableTr.tableTrTd}" var="thisTd">
															<c:if test="${tableTd.optionValidates != null}">
																<c:forEach items="${thisTd.optionValidates}" var="optionValidate">
																	<input type="hidden" questionName="${q.displayname}" validate="yes" controlOption="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}" validateType="${optionValidate.type}" value="${optionValidate.value}">
																</c:forEach>
															</c:if>
															
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
																	<c:if test="${thisTd.notNull == '1'}">notNull="1"</c:if>
																	<c:if test="${thisTd.maxLength != null}">maxlength="${thisTd.maxLength}"</c:if>
																	name="${qg.id}_${q.id}_${tableTr.rownum}-${thisTd.id}"
																	onkeyup="optionKeyUp(this,'${thisTd.charType}');"
																	onblur="blurRule(this,'${thisTd.textType}','${thisTd.format}','${thisTd.scope}','${thisTd.constrainScope}')"
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

</c:otherwise>
</c:choose> 	
</div>
					</c:otherwise>
				</c:choose>								
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
				<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix" role="complementary" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;max-height:500px;">
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

	</div>
	<!-- /container -->

</form>
<script type="text/javascript">


$(function(){
	
	
	
	
})
</script>

</body>
</html>
