<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" /> 

<!DOCTYPE html>
<html>
<head>
<title  id='title'>试卷- <c:out value="${paper.paperName }"></c:out></title>
<link rel="stylesheet" href="${ctx}/static/jquery/css/jquery.qtip.min.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/jquery/css/paper.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/jquery/css/jquery.alerts.css" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/jquery.qtip.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/exam.js"></script>
<script type="text/javascript" src="${ctx}/static/js/jquery.alerts.js"></script>
<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div id="wrap">
	<div id="main-content">
		<div id="left-main">
		<h2 class="stair-title">${paper.paperName}</h2>
		
		
		<c:forEach items="${paper.QGroup }" var="qg">
			 <div id="qg_${qg.id}" controlshow="${qg.controlShow}" controlhide="${qg.controlHide}">
			 <h3 class="stair-title" id="g<c:out value="${qg.id}"></c:out>"><c:out value="${qg.displayname}"></c:out></h3>
			 <c:forEach items="${qg.qs}" var="q">
				<c:choose>
					<c:when test="${q.type eq 'Q_TYPE_SHOWTEXT'}">
						<div id="q_${qg.id}_${q.id}" controlshow="<c:out value='${q.controlShow}'/>" controlhide="<c:out value='${q.controlHide}'/>"
							<c:if test="${q.setHidden eq '1'}">style="display:none"</c:if>>
							<p  style="color:${q.color};font-size:${q.fontSize}px">&nbsp;&nbsp;<c:out value="${q.displayname}"/></p>
						</div>
					</c:when>
					<c:otherwise>
						<div thisTitle="${q.helpText}" displayname="${q.displayname}"
							class="panel panel-default"  id="q_${qg.id}_${q.id}" controlshow="<c:out value='${q.controlShow}'/>" controlhide="<c:out value='${q.controlHide}'/>" qType='${q.type}'
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
														<c:if test="${qg.iscycle=='1' && option.cycle == '1'}"> cycle='1' </c:if>
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
														    type="text" value="${resultOptionMap[id]}" questionName="${q.displayname}" name="${qg.id}_${q.id}_${option.id}"><c:out value="${option.afterDisplayName}"></c:out>
													
															<c:if test="${option.code != null && option.code != ''}">
																<input type="hidden" id="dictName${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
																<c:set var="id" value="dict${qg.id}_${q.id}_${option.id}"></c:set>
																<input type="hidden" onchange="changeCode('${qg.id}_${q.id}_${option.id}',${option.code}')" id="dict${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
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
																<input type="hidden" id="dictName${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
																<c:set var="id" value="dict${qg.id}_${q.id}_${option.id}"></c:set>
																<input type="hidden" onchange="changeCode('${qg.id}_${q.id}_${option.id}',${option.code}')" id="dict${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
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
														<c:if test="${option.code != null && option.code != ''}">inputAutoCompleted="yes" code="${option.code}"</c:if>
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
													    type="text" value="${resultOptionMap[id]}" questionName="${q.displayname}" optionFullId="${qg.id}_${q.id}_${option.id}" name="${qg.id}_${q.id}_${option.id}"><c:out value="${option.afterDisplayName}"></c:out>
													    <c:if test="${option.code != null && option.code != ''}">
															<input type="hidden" id="dictName${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
															<c:set var="id" value="dict${qg.id}_${q.id}_${option.id}"></c:set>
															<input type="hidden" onchange="changeCode('${qg.id}_${q.id}_${option.id}',${option.code}')" id="dict${qg.id}_${q.id}_${option.id}" value="${resultOptionMap[id]}"/>
														</c:if>
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
											<div class="col-sm-6" style="height:30px;">
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
													width="40px;" value="${resultOptionMap[id]}" questionType="FILLBLANK" optionFullId="${qg.id}_${q.id}_${option.id}" name="${qg.id }_${q.id }_${option.id }"><c:if test="${option.notNull == '1'}"><span style="color:red;font-size:20px">*</span></c:if>
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
											</div>
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
													<td style="min-width: 20%;text-align:center;">${q.talbe.displayname}</td>
													<c:forEach items="${q.talbe.tableTr[0].tableTrTd}" var="tableTd">
														<td <c:if test="${tableTd.extendcolumn != null}">colspan="${tableTd.extendcolumn}"</c:if>>
															&nbsp;
														</td>
													</c:forEach>
												</tr>
												<c:forEach items="${q.talbe.tableTr}" var="tableTr">
													<tr notNull="1" id="${qg.id }_${q.id }_${tableTr.rownum}">
														<td style="width:25%;">${tableTr.displayname}</td>
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
</div>
               		
			</div>
			<br>
			<br>
			<br>
			<aside id="sidebar">
            	<div class="col-md-2">
				<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix" role="complementary" style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;max-height:400px;margin-top:-50px;">
					<ul class="nav bs-docs-sidenav">
						<li class="active01"><span>筛查对象条形码:</span><input type="text" class="form-control" name="intervid" id="intervid" readonly value="${patientId}"></li>
						<c:forEach items="${paper.QGroup}" var="qg">
								<li class="active02"><a style="padding:3px;10px;" href="#g<c:out value="${qg.id }"></c:out>"><c:out value="${qg.displayname}"></c:out></a></li>
						</c:forEach>
					</ul>
                     <br style="clear:both" />
				</div>
			  </div>
           </aside>
           
           <br style="clear:both" />
		</div>
	</div>
	<!-- /container -->
<form action="${ctx}/qn/saveQn" method="post" id="subForm">
</form>
<script type="text/javascript">
var resultMap = ${resultOptionMapJson};
$(function(){
	divMask("left-main");
	//显示图片
	$("#left-main").find("img").each(function(){
		$(this).before("<br/>");
		$(this).after("<br/>");
		$(this).attr("src","${ctx}/static/examImg/"+$(this).attr("src"))
	});
	//光标定位到第一个输入框中
	$('.form-horizontal input:first').focus();

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
	
	$("input[type='text']").each(function(){
		if($(this).attr("maxlength")==undefined)return true;
		var ml = parseInt($(this).attr("maxlength"));
		if(ml>10)$(this).css("width","200px");
		if(ml>21)$(this).css("width","300px");
		if(ml>32)$(this).css("width","400px");
		if(ml>42)$(this).css("width","500px");
	});
	
	$("input[calculate*='_']").attr("readOnly","readOnly");
	
	$("input[id^='o_']").each(function(){
		if($(this).is(":visible")){
			triggerShowOrHide($(this).attr("id"));
		}
	});
	
	$("input[optionFullId*='_'][type='text']").each(function(){
		if($(this).is(":visible") && $(this).val()!=''){
			triggerShowOrHide($(this).attr("name"));
		}
	});

	$("input[cycle='1']:checked").each(function(){
		triggerCycle($(this));
	});
});



function clickSelect(obj,rejectother,alertText){
	
	if(alertText != ""){
		jAlert(alertText);
	}
	var fullId=$(obj).attr("id");
	
	var i = fullId.lastIndexOf("_");
	var partId=fullId.substr(0,i+1);
	if(rejectother == "1"){
		if(obj.checked){
			$("input[id^='"+partId+"']").each(function(){
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
			$("input[id^='"+partId+"']").each(function(){
				if($(this).attr("id") != $(obj).attr("id")){
					$(this).removeAttr("disabled");
				}
			});
			$(obj).parent().parent().find("input[type='text']").removeAttr("disabled");
		}
	}
	
	//控制循环题
	triggerCycle(obj);
	triggerShowOrHide($(obj).attr("id")); 
	
}


/**
 * 点击选择框或者离开文本框时触发 跳转规则
 * optionInputId 被点击的选择框Id 或离开的文本框
 */
function triggerShowOrHide(optionInputId){
	var i = optionInputId.lastIndexOf("_");
	var partId=optionInputId.substr(2,i-1);
	
	//allEnd
	var triggerAllEnd = false;
	var isAllEnd = false;
	$("input[id^='"+optionInputId.substr(0,i+1)+"'][allEnd='1']").each(function(){
		triggerAllEnd = true;
		if($(this)[0].checked){
			isAllEnd = true;
			return false;
		}
	});
	if(triggerAllEnd){
		allEnd(optionInputId,isAllEnd);
	}
	
	
	//题组隐藏
	$("div[controlShow*='"+partId+"'][id^='qg_']").each(function(){
		showOrHide($(this)[0]);
	});
	$("div[controlHide*='"+partId+"'][id^='qg_']").each(function(){
		showOrHide($(this)[0]);
	});
	//题隐藏
	$("div[controlShow*='"+partId+"'][id^='q_']").each(function(){
		showOrHide($(this)[0]);
	});
	$("div[controlHide*='"+partId+"'][id^='q_']").each(function(){
		showOrHide($(this)[0]);
	});
	//选项隐藏
	$("div[controlShow*='"+partId+"'][id='']").each(function(){
		showOrHide($(this)[0]);
	});
	$("div[controlHide*='"+partId+"'][id='']").each(function(){
		showOrHide($(this)[0]);
	});
}

//
function triggerCycle(obj){
	var thisOptionId = $(obj).attr("id");
	var n = thisOptionId.lastIndexOf("_");
	if($("input[id^='"+thisOptionId.substr(0,n+1)+"'][cycle='1']").length==0)return;
	
	var thisOptionIdArr = thisOptionId.split("_");
	var qgDiv = $("div[id='qg_"+thisOptionIdArr[1]+"']")[0];
	var qIdAdd = thisOptionIdArr[2];
	if($("input[id^='"+thisOptionId.substr(0,n+1)+"'][cycle='1']").is(":checked")){
		if($("div[id='q_"+thisOptionIdArr[1]+"_"+getQId(qIdAdd,qIdAdd)+"']").length>0)return;
		var appendHtml = "";
		$(qgDiv).find("div[id^='q_'][id!='q_"+thisOptionIdArr[1]+"_1']").each(function(){
			var qDivCopy = getQDivCopy($(this),qIdAdd);
			if(qDivCopy == "")return true;
			appendHtml += $(qDivCopy).prop("outerHTML");
			if($(this).find("input[cycle='1']").length>0){
				return false;
			}
		});
		if(appendHtml == "")return;
		$(qgDiv).append(appendHtml);
		$(qgDiv).find("div[id^='q_']").each(function(){
			showOrHide($(this)[0]);
		});
		$(qgDiv).find("input[inputAutoCompleted='yes']").each(function(){
			$(this).attr("placeholder","请输入首字母进行检索...");
		});
		$(qgDiv).find("div[id^='q_']:last").find("input[cycle='1']:checked").each(function(){
			triggerCycle($(this));
		});
	}else{
		if(qIdAdd.length > 3){
			qIdAdd = parseInt(qIdAdd.substr(qIdAdd.length-3,3));
		}else{
			qIdAdd = 0;
		}
		$(qgDiv).find("div[id^='q_']").each(function(){
			var thisIdArr = $(this).attr("id").split("_");
			if(thisIdArr[2].length <=3)return true;
			if(parseInt(thisIdArr[2].substr(thisIdArr[2].length-3,3)) > parseInt(qIdAdd)){
				$(this).remove();
			}
		});
	}
}

function cycleShowOrHideReplace(controlStr,idArr,qIdAdd){
	if(controlStr=="")return "";
	var arr=controlStr.split(/\|\||&&/);
	for(var i in arr){
		var cArr=arr[i].toString().split("_");
		if(cArr[0].substr(1)==idArr[1] && cArr[1]>1){
			var replaceTo="";
			if(qIdAdd.length<3){
				replaceTo = cArr[0]+"_"+(parseInt(cArr[1])*1000+1)+"_"+cArr[2];
			}else{
				replaceTo = cArr[0]+"_"+(parseInt(cArr[1])*1000+parseInt(qIdAdd.substr(qIdAdd.length-3,3))+1)+"_"+cArr[2];
			}
			controlStr = controlStr.replace(arr[i],replaceTo);
		}
	}
	return controlStr;
}

function getQDivCopy(obj,qIdAdd){
	var divCopy = $(obj).clone();
	var divCopyIdArr = divCopy.attr("id").split("_");
	$(divCopy).attr("id",divCopyIdArr[0]+"_"+divCopyIdArr[1]+"_"+getQId(divCopyIdArr[2],qIdAdd));
	$(divCopy).attr("controlshow",cycleShowOrHideReplace($(divCopy).attr("controlshow"),divCopyIdArr,qIdAdd));
	$(divCopy).attr("controlhide",cycleShowOrHideReplace($(divCopy).attr("controlhide"),divCopyIdArr,qIdAdd));
	var qType = $(divCopy).attr("qType");
	if(qType == 'Q_TYPE_SINGLE' || qType == 'Q_TYPE_MULTI' || qType == 'Q_TYPE_SINGLE_FILL' || qType == 'Q_TYPE_MULTI_FILL'){//单选框 和 多选框
		$(divCopy).find("label[for^='o_']").each(function(){
			var thisFor = $(this).attr("for").split("_");
			$(this).attr("for","o_"+thisFor[1]+"_"+getQId(thisFor[2],qIdAdd)+"_"+thisFor[3]);
		});
		$(divCopy).find("input[type='radio'],input[type='checkbox']").each(function(){
			var thisIdArr = $(this).attr("id").split("_");
			var thisNameArr = $(this).attr("name").split("_");
			var fullId = thisIdArr[1]+"_"+getQId(thisIdArr[2],qIdAdd)+"_"+thisIdArr[3];
			$(this).attr("id","o_"+fullId);
			$(this).attr("optionFullId",fullId);
			$(this).attr("name",thisNameArr[0]+"_"+getQId(thisNameArr[1],qIdAdd));
			if(resultMap[fullId] == undefined){
				$(this).removeAttr("checked");
			}else{
				$(this).attr("checked","checked");
			}
		});
	}
	
	if(qType == 'Q_TYPE_FILL_BLANK'  || qType == 'Q_TYPE_SINGLE_FILL' || qType == 'Q_TYPE_MULTI_FILL'){ //填空题
		$(divCopy).find("input[type='hidden'][id^='dict']").each(function(){
			var thisIdArr = $(this).attr("id").split("_");
			$(this).attr("id",thisIdArr[0]+"_"+getQId(thisIdArr[1],qIdAdd)+"_"+thisIdArr[2]);
			var fullId = "";
			if(thisIdArr[0].substr(0,8) == 'dictName'){
				fullId = thisIdArr[0].substr(8)+"_"+getQId(thisIdArr[1],qIdAdd)+"_"+thisIdArr[2];
				$(this).attr("value",resultMap[fullId]==undefined?"":resultMap[fullId]);
			}else{
				fullId = thisIdArr[0].substr(4)+"_"+getQId(thisIdArr[1],qIdAdd)+"_"+thisIdArr[2];
				$(this).attr("value",resultMap['dict'+fullId]==undefined?"":resultMap['dict'+fullId]);
			}
			
		});
		
		$(divCopy).find("input[type='text']").each(function(){
			var thisOptionFullIdArr = $(this).attr("optionFullId").split("_");
			var fullId = thisOptionFullIdArr[0]+"_"+getQId(thisOptionFullIdArr[1],qIdAdd)+"_"+thisOptionFullIdArr[2];
			$(this).attr("optionFullId",fullId);
			$(this).attr("name",fullId);
			$(this).attr("value",resultMap[fullId]==undefined?"":resultMap[fullId]);
			
		});
	}
	
	return divCopy;
}

function getQId(prevQId,currMaxQId){
	if(currMaxQId.length <= 3){
		return parseInt(prevQId)*1000+1;
	}else{
		return parseInt(prevQId.substr(0,prevQId.length%3))*1000+parseInt(currMaxQId.substr(currMaxQId.length-3,3))+1;
	}
}

//该题以后的题全部显示或隐藏
function allEnd(oId,isAllEnd){
	var idArr = oId.split("_");
	$("div[id^='qg_']").each(function(){
		if(parseInt($(this).attr("id").substr(3)) > parseInt(idArr[1])){
			if(isAllEnd){
				$(this).hide();
			}else{
				$(this).show();
			}
			
		}
	});
	$("div[id^='q_"+idArr[1]+"_']").each(function(){
		if(parseInt($(this).attr("id").substr(idArr[1].length+3)) > parseInt(idArr[2])){
			if(isAllEnd){
				$(this).hide();
			}else{
				$(this).show();
			}
		}
	});
}

/*
 * 判断某元素隐藏或显示
 */
function showOrHide(obj){
	var controlShow = $(obj).attr("controlshow");
	var controlHide = $(obj).attr("controlhide");
	
	if(controlShow != undefined && controlShow != ""){
		var convertControlShow = convertStr(controlShow);
		if(convertControlShow.indexOf("#")>-1)return;
		if(eval(convertControlShow) && $(obj).parent().is(":visible")){
			$(obj).show();
		}else{
			$(obj).hide();
		}
	}
	
	if(controlHide != undefined && controlHide != ""){
		var convertControlHide = convertStr(controlHide);
		if(convertControlHide.indexOf("#")>-1)return;
		
		if(!eval(convertControlHide) && $(obj).parent().is(":visible")){
			$(obj).show();
		}else{
			$(obj).hide();
		}
	}
}

/**
 * 将字符串转换成  表达式
 */
function convertStr(str){
	var returnStr = str;
	var reg = /[#SU]\d{1,2}_\d{1,2}_\d{1,2}(d\d{1,2})?/g;
	var arr=str.match(reg);
	for(var i in arr){
		var this_qg_q_o = arr[i].trim().substr(1).replace("d","-");
		var label = arr[i].trim().substr(0,1);
		var v = "";
		if(label=="#"){
			v=$("input[name='"+this_qg_q_o+"']").val().trim();
			if(v == "")break;
		}else{
			v=($("#o_"+this_qg_q_o)[0].checked && label=="S") || (!$("#o_"+this_qg_q_o)[0].checked && label=="U") ;
		}
		returnStr = returnStr.replace(eval("/"+arr[i]+"/g"),v);
	}
	return returnStr;
}

function cancleSingleCheck(obj){
	$(obj)[0].checked=false;
	triggerCycle(obj);
	triggerShowOrHide($(obj).attr("id")); 
}

function formatNumber(obj,n){
	var v = $(obj).val().trim();
	if(v == "")return;
	if(isNaN(v)){
		return;
	}
	$(obj).val(parseFloat(v).toFixed(parseInt(n)));
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

/**
 * 根据format得到小数位数
 */
function getDecimalDigits(format){
	var i = format.indexOf(".");
	if(i==-1)return 0;
	return parseInt(format.substr(i+1));
}

function blurRule(obj,textType,format,scope,constrainScope){
	if($(obj).attr("inputAutoCompleted")=="yes"){
		$(obj).val($("#dictName"+$(obj).attr("optionFullId")).val());
		return;
	}
	
	var v = $(obj).val().trim();
	$(obj).val(v);
	if(v == "" || v == "9999")return;
	var confirmStr = "";
	if(textType == "number"){
		if(isNaN(v)){
			jAlert("录入结果必须是数字",null,function(){
				$(obj).focus();
			});
			return;
		}
		if(format != ""){
			var decimalDigits = getDecimalDigits(format);
			v = parseFloat(v).toFixed(decimalDigits)
			$(obj).val(v);
		}
	}	
	
	if(textType == "datenum"){
		if(format != ""){
			if(v.length != format.length){
				jAlert("请录入正确的数据",null,function(){
					$(obj).focus();
				});
				return;
			}
		}
	}	
	
	if(constrainScope != "" && !checkScope(v,textType,constrainScope,format)){
		jAlert("请录入正确的数据",null,function(){
			$(obj).focus();
		});
		return;
	}
	if(scope != "" && !checkScope(v,textType,scope,format)){
		confirmStr = "请确认录入结果是否无误";
	}
	var isRight = true;
	$("input[calculateandvalidate*='"+$(obj).attr("name").replace("-","d")+"']").each(function(){
		var converResult = convertStr($(this).attr("calculateandvalidate"));
		if(converResult.indexOf("#")==-1 && !eval(converResult)){
			jAlert("录入结果不符合要求",null,function(){
				$(obj).focus();
			});
			isRight = false;
			return false;
		}
	});
	if(!isRight)return;
	$("input[calculate*='"+$(obj).attr("name").replace("-","d")+"']").each(function(){
		var converResult = convertStr($(this).attr("calculate"));
		if(converResult.indexOf("#")==-1){
			$(this).val(eval(converResult));
			$(this).blur();
		}
	});
	
	if(confirmStr.length > 0){
		jConfirm(confirmStr,null,function(r){
			if(r){
				return;
			}else{
				$(obj).focus();
				return;
			}
		});
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

/**
 * keyUp事件，验证字符输入是否合法
 */
function optionKeyUp(obj,type){
	var v = $(obj).val().trim();
	if(v == "")return;
	if(type != ""){
		v = $(obj).val().replace(eval("/[^"+getExpression(type)+"]/g"),'');
		$(obj).val(v);
	}else if($(obj).attr("idCard")=="1"){
		v = $(obj).val().replace(/[^0-9Xx]/g,'');
	}
	$(obj).val(v);
	triggerShowOrHide("o_"+$(obj).attr("name"));
}

/**
 * 给div加透明遮罩
 * 参数:div Id
 */
 function divMask(divId){
	var jId = "#"+divId;
	  $("<div id='shade'></div>").css({
    	  position:'absolute',
    	  top:$(jId)[0].offsetTop,
    	  left:$(jId)[0].offsetLeft,
    	  backgroundColor:"#000",
    	  opacity:0,
    	  zIndex:300
    	  }).height($(jId).height()).width($(jId).width()).appendTo(jId); 
}
</script>

</body>
</html>
