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
<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">由于上次网络错误，现在系统正在帮您恢复数据，请稍等片刻...</h4>
	      </div>
	      <div class="modal-body">
	        <div class="progress">
			  <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
			    <span class="sr-only">由于上次网络错误，现在系统正在帮您恢复数据，请稍等片刻...</span>
			  </div>
			</div>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

<input type="hidden" id="uqsVersionId_" value="${uqsVersion }" />
<div id="wrap">
	<div id="main-content">
		<div id="left-main"><div id="shadeDiv" style="position: absolute;height:100%;z-index: 300;width:100%;background: #ffffff" ></div>
		<h2 class="stair-title">${paper.paperName}</h2>
		<c:forEach items="${paper.QGroup }" var="qg">
			 <div id="qg_${qg.id}" controlshow="${qg.controlShow}" controlhide="${qg.controlHide}">
			 <h3 class="stair-title" id="g<c:out value="${qg.id}"></c:out>"><c:out value="${qg.displayname}"></c:out></h3>
			 <c:forEach items="${qg.qs }" var="q">
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
														<c:if test="${q.readOnly=='1' || option.readOnly == '1'}">disabled</c:if>
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
														<c:if test="${q.readOnly=='1' || option.readOnly == '1'}">disabled</c:if>
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
												<c:if test="${q.readOnly=='1' || option.readOnly == '1'}">disabled</c:if> 
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
													<c:if test="${q.readOnly=='1' || option.readOnly == '1'}">disabled</c:if>
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
													<c:if test="${q.readOnly == '1' || option.readOnly == '1'}"> disabled</c:if>
													<c:if test="${option.code != null && option.code != ''}">inputAutoCompleted="yes" code="${option.code}"</c:if>
													<c:if test="${option.maxLength != null}">maxlength="${option.maxLength}"</c:if>
													<c:if test="${option.notNull == '1'}">notNull="1"</c:if>
													<c:if test="${option.calculate != null}">calculate="${option.calculate}"</c:if>
													<c:if test="${option.calculateandvalidate != null}">calculateandvalidate="<c:out value='${option.calculateandvalidate}'></c:out>"</c:if>
													<c:if test="${option.idCard != null}">idCard="1"</c:if>
													onkeyup="optionKeyUp(this,'${option.charType}');"
													onblur="blurRule(this,'${option.textType}','${option.format}','${option.scope}','${option.constrainScope}')"
													value="${resultOptionMap[id]}" questionType="FILLBLANK" optionFullId="${qg.id}_${q.id}_${option.id}" name="${qg.id }_${q.id }_${option.id }"><c:if test="${option.notNull == '1'}"><span style="color:red;font-size:20px">*</span></c:if>
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
													value="${resultOptionMap[id]}" questionType="FILLBLANK" optionFullId="${qg.id}_${q.id}_${option.id}" name="${qg.id }_${q.id }_${option.id }"><c:out value="${resultOptionMap[id]}"/></textarea>
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
<div class="btn-wrap noPrint">
<span>
       <button type="button" id="bnt"  onclick="sub()" class="btn-style-default">提交问卷</button></span>
      <!-- <button type="button" onclick="printQn()" class="btn-style-default">打印</button> -->
</div>
               		
			</div>
			<br>
			<br>
			<br>
			<aside id="sidebar">
            	<div class="col-md-2">
				<div class="bs-docs-sidebar hidden-print hidden-xs hidden-sm affix" role="complementary">
					<div style="OVERFLOW-Y: auto; OVERFLOW-X:hidden;max-height:400px;margin-top:-50px;">
						<ul class="nav bs-docs-sidenav">
							<li class="active01"><span>筛查对象条形码:</span><input type="text" class="form-control" name="intervid" id="intervid" readonly value="${patientId}"></li>
							<c:forEach items="${paper.QGroup}" var="qg">
									<li class="active02"><a style="padding:3px;10px;" href="#g<c:out value="${qg.id }"></c:out>"><c:out value="${qg.displayname}"></c:out></a></li>
							</c:forEach>
						</ul>
	                    <br style="clear:both" />
                    </div>
                    <center><button type="button" onclick="tempSub()" id="bnt_zc" class="btn-style-default">暂存</button></center>
				</div>
			  </div>
           </aside>
           
           <br style="clear:both" />
		</div>
	</div>
	<!-- /container -->
	<form action="${ctx}/qn/saveQn" method="post" id="subForm">
	</form>
	
	<c:if test="${uqsCode == '001'}">
	<div id='dialog-planDate' class="modal fade">
	  <div class="modal-dialog" style="width:750px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">随访预约</h4>
	      </div>
	      <div class="modal-body">
	         <div class="row">
				<label class="col-md-3  control-label" for="name">预约日期:</label>
				<div class="col-md-9">
					<input type="text" id="planDate" class="Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:minDate(),maxDate:maxDate(),startDate:startDate(),readOnly:true})"/>
				</div>
			</div>

			<div class="row">
				<label class="col-md-3  control-label" for="name">备注:</label>
				<div class="col-md-9">
					 <textarea rows="8" cols="50" style="overflow-y:auto;resize:none" id="planRemark"></textarea> 
				</div>
			</div>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" onclick="surePlanDate();" class="btn btn-default btn-sm" tabindex="1001">确定</button>
	        <button type="button" onclick="cancelPlanDate();" class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	</c:if>
	
	<c:if test="${uqsCode == '013'}">
	<div id='dialog-planDate' class="modal fade">
	  <div class="modal-dialog" style="width:450px;">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title" id="planDateTitle"></h4>
	      </div>
	      <div class="modal-body">
	      	 <div class="row">
				<label class="col-md-3" style="text-align:right">姓名:</label>
				<div class="col-md-9">
					 ${patientName}
				</div>
			 </div>
			 <div class="row">
				<label class="col-md-3" style="text-align:right">PID:</label>
				<div class="col-md-9">
					  ${patientId} 
				</div>
			 </div>
	         <div class="row">
				<label class="col-md-3" style="text-align:right">预约日期:</label>
				<div class="col-md-9">
					<input type="text" id="planDate" class="Wdate"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:minDate(),maxDate:maxDate(),startDate:startDate(),readOnly:true})"/>
				</div>
			</div>
	      </div>
	      
	      <div class="modal-footer">
	      	<button type="button" onclick="surePlanDate();" class="btn btn-default btn-sm" tabindex="1001">确定</button>
	        <button type="button" onclick="cancelPlanDate();" class="btn btn-default btn-sm" tabindex="1001">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	</c:if>
	<input type="hidden" id="loginName"/>
	<input type="hidden" id="loginPwd"/>


<div id='dialog-reportAfter' class="modal fade">
    <div class="modal-dialog" style="width:800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">补报事件</h4>
            </div>
            <div class="modal-body">
                <p>加载中……</p>
            </div>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" id ='cancel-reportAfter' class="btn btn-default btn-sm" tabindex="1001">取消</button>--%>
            <%--</div>--%>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
var canLeave = false;
var resultMap = ${resultOptionMapJson};
//var timer =window.setInterval(tempSaveLog,900000);
//每隔10秒左右监测一次网络状态 900000
var netTimer =window.setInterval(blankUrl,10000);
function blankUrl(){
	$.ajax({
		   type: "POST",
		   url: "${ctx}/qn/blankUrl",
		   error: function(XMLHttpRequest, textStatus, errorThrown){
			   clearInterval(netTimer);
			   //本地暂存
			   localStore();
			   jAlert("网络发生错误！请重新登陆！");
			   window.setTimeout("window.location.href='${ctx}/login'", 5000 );
		   }
		});
}
//设置各个浏览器兼容
if(!window.indexedDB){
	window.indexedDB = window.mozIndexedDB || window.webkitIndexedDB;
}
var db;  //数据库
var request = indexedDB.open("aqt"+$("#uqsVersionId_").val()+$("#intervid").val(),1.0);
var store;//数据表
var nameIndex;//索引
request.onupgradeneeded = function() {
  // 此数据库此前不存在，进行初始化
  db = request.result;
  store = db.createObjectStore("answerQnTmp", {keyPath: "id",autoIncrement:true});
  nameIndex = store.createIndex("by_name", "name", {unique: true});
};
request.onsuccess = function() {
  db = request.result;
  //监测数据库中是否有数据
  var transaction = db.transaction(["answerQnTmp"],"readonly");
  var objectStore = transaction.objectStore("answerQnTmp");
  var index = objectStore.index("by_name");
  //通过查询一次patientId来确定数据库是否有数据
  var dataP =index.get("patientId");
  dataP.onerror = function(event) {
    // 错误处理!
  };
  dataP.onsuccess = function(event) {
    var data = dataP.result;
    if (data !== undefined) {
        //console.log(data.id, data.name, data.value);
        $('#dialog-confirm').modal({backdrop: 'static', keyboard: false});
    	$('#dialog-confirm').modal('show');
    	var data = {};
    	//查询进行游标
    	var objectArr =objectStore.openCursor();
    	objectArr.onsuccess = function() {
   		  var cursor = objectArr.result;
   		  if (cursor) {
   			data[cursor.value.name]=cursor.value.value;
   		    console.log(cursor.value.name, cursor.value.value);
   		    cursor.continue();
   		  } else {
   			$.post("${ctx}/qn/saveTempQn",data,function(result){
   				if(result.result==1){
   					indexedDB.deleteDatabase("aqt"+$("#uqsVersionId_").val()+$("#intervid").val());
   					$('#dialog-confirm').modal('hide');
   					jAlert("数据恢复成功！");
   					window.setTimeout("toLoginUrl()", 5000 );
   				}
   				else if(result.result==7){
   					jAlert("该患者问卷信息在系统中已经提交过了！");
   				}
   				else{
//   					jAlert('数据恢复失败！');
					console.log('数据恢复失败！'+result.result);
   					$('#dialog-confirm').modal('hide');
   				}
   			},'json');
   			  
   		  }
   		};    	
     } else {
       console.log(null);
     }
  };
};
request.onerror = function(e){
	alert(e);
}
function localStore(){
	$("#subForm").html("<input type=\"hidden\" name=\"patientId\" value=\"${patientId}\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"uqsVersion\" value=\"${uqsVersion}\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"tempSave\" value=\"true\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSBeginTime\" value=\"${UQSBeginTime}\"/>");
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
			var trueValue = $("#dict"+id).val();
			if(trueValue != undefined) value=trueValue;
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
	$("textarea[questionType='FILLBLANK']").each(function(){
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
	var myform = $("#subForm").serializeArray();
	var transaction = db.transaction(["answerQnTmp"], "readwrite");
	// 当所有的数据都被增加到数据库时执行一些操作
	transaction.oncomplete = function(event) {
	};
	transaction.onerror = function(event) {
	  // 不要忘记进行错误处理！
	  alert("some error!");
	};
	var objectStore = transaction.objectStore("answerQnTmp");
	//添加数据
	$.each(myform, function(i, field){
		objectStore.add({name: field.name, value: field.value});
	});
}
function toLoginUrl(){
	var uqsVersion = $("#uqsVersionId_").val();
	var str = uqsVersion.substring(4,7);
	window.location.href="${ctx}/qn/answerQn?patientId="+$("#intervid").val()+"&uqsCode="+str;
}
/* function createDataBase(){
	var dataBase = openDatabase("answerQnTmp", "1.0", "TempInfo", 3000 * 1024, function () { });
	if(!dataBase){
		alert("创建数据库失败！");
	}else{
		alert("创建数据库成功！");
		this.createTable=function() {
			dataBase.transaction(function(tx) { 
				tx.executeSql(
						"create table if not exists answer_t (id REAL UNIQUE, name TEXT)", 
						[], 
						function(tx,result){ 
							alert('创建answer_t表成功');
						}, 
						function(tx, error){ 
							alert('创建answer_t表失败:' + error.message); 
				});
			});
		}
		
	}
} */

//暂存
function tempSaveLog(){
	//调用检查
	$("#subForm").html("<input type=\"hidden\" name=\"patientId\" value=\"${patientId}\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"uqsVersion\" value=\"${uqsVersion}\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"tempSave\" value=\"true\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSBeginTime\" value=\"${UQSBeginTime}\"/>");
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
			var trueValue = $("#dict"+id).val();
			if(trueValue != undefined) value=trueValue;
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
	$("textarea[questionType='FILLBLANK']").each(function(){
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
	var myform = $("#subForm").serializeArray();
	var data = {};
	$.each(myform, function(i, field){
		data[field.name] = field.value;
	});
	$.post("${ctx}/qn/saveTempQn",data,function(result){
			if(result.result==1){
				//删除数据库
				indexedDB.deleteDatabase("aqt"+$("#uqsVersionId_").val()+$("#intervid").val());
			}
			else if(result.result==7){
				jAlert('该患者在系统中已经提交过问卷了！');
			}
			else{
				jAlert('暂存失败！');
			}
	},'json');
}
$(function(){
	if("${uqsVersion}".substr(4,3)=="001"){
		$("#q_1_1").find("input").attr("disabled","disabled");
		$("input[name='2_2_1']").attr("placeholder","010-12345678");
	}
	
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
	
	$("input[inputAutoCompleted='yes']").each(function(){
		$(this).attr("placeholder","请输入首字母进行检索...");
		var obj = $(this)[0];
		inputAutocomplete(obj);
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
	
	if("${uqsVersion}".substr(4,3)=="002"){
		$("#planDateTitle").text("12月随访时间预约");
	}

    $("#shadeDiv").hide(); // 页面加载完DOM元素后js执行控制隐藏方法 出现部分题目闪烁 加遮罩层修饰
});

function inputAutocomplete(obj){
	var code = $(obj).attr("code").trim();
	var prevTrueCode = "";
	if(code == "DISTRICT.02" || code == "DISTRICT.03" || code == "DISTRICT.04" || code == "DISTRICT.05"){
		var objName = $(obj).attr("name");
		var objNameArr = objName.split("_");
		var previousObjName = objNameArr[0]+"_"+objNameArr[1]+"_"+(objNameArr[2]-1);
		prevTrueCode =  $("#dict"+previousObjName).val();
	}
	var url = "${ctx}/combox/getDict?code="+code+"&prevTrueCode="+prevTrueCode;
	$(obj).unautocomplete();
	$(obj).autocomplete(url, {
		max:10,
		minChars:0,
		matchContains: true,
		formatItem: function(row) {
			return row[0];
		}
	}).result(function(event,row,formatted){
		var prveCode = $("#dict"+$(obj).attr("optionFullId")).val();
		if(row==undefined || row == null || row.length<2){
			$("#dict"+$(obj).attr("optionFullId")).val("");
			$("#dictName"+$(obj).attr("optionFullId")).val("");
			
			if($("#o_"+$(obj).attr("optionFullId")).length==1){
				$("#o_"+$(obj).attr("optionFullId")).prop("checked",false);
			}
		}else{
			$("#dict"+$(obj).attr("optionFullId")).val(row[1]);
			$("#dictName"+$(obj).attr("optionFullId")).val(row[0]);
			if($("#o_"+$(obj).attr("optionFullId")).length==1){
				$("#o_"+$(obj).attr("optionFullId")).prop("checked",true);
			}
		}
		var thisCode = $(obj).attr("code").trim();
    	if(thisCode == "DISTRICT.01" || thisCode == "DISTRICT.02"  || thisCode == "DISTRICT.03"  || thisCode == "DISTRICT.04"){
			if(prveCode != row[1]){
				changeCode($(obj).attr("optionFullId"),thisCode);
			}
    	}
	});
}

function changeCode(name,code){
	var nameArr = name.split("_");
	var baseName = nameArr[0]+"_"+nameArr[1]+"_";
	var c=parseInt(code.substr(10));
	for(var i=c+1;i<6;i++){
		$("#dict"+baseName+i).val("");
		$("input[name='"+baseName+i+"']").val("");
		$("input[name='"+baseName+i+"']").unautocomplete();
		inputAutocomplete($("input[name='"+baseName+i+"']")[0]);
	}
}

$.ajaxSetup({
	async: false
});

// 无校验方式提交  针对死亡患者页面跳转
function subWithoutValidate(){
    $("#subForm").html("<input type=\"hidden\" name=\"patientId\" value=\"${patientId}\"/>");
    $("#subForm").append("\n<input type=\"hidden\" name=\"uqsVersion\" value=\"${uqsVersion}\"/>");
    $("#subForm").append("\n<input type=\"hidden\" name=\"UQSBeginTime\" value=\"${UQSBeginTime}\"/>");
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
        $("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
    });

    $("input[questionType='MULTIFILL']:checked:visible").each(function(){
        var id =  $(this).attr("optionFullId");
        var value = "";
        if($("input[name='"+id+"']").val() != undefined){
            value = $("input[name='"+id+"']").val();
            var trueValue = $("#dict"+id).val();
            if(trueValue != undefined) value=trueValue;
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
    $("textarea[questionType='FILLBLANK']:visible").each(function(){
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

//    $("#subForm")[0].submit();
}



function sub(){
	//调用检查
	$("#subForm").html("<input type=\"hidden\" name=\"patientId\" value=\"${patientId}\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"uqsVersion\" value=\"${uqsVersion}\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSBeginTime\" value=\"${UQSBeginTime}\"/>");

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
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION"+id+"\" value=\"\"/>");
	});
	
	$("input[questionType='MULTIFILL']:checked:visible").each(function(){
		var id =  $(this).attr("optionFullId");
		var value = "";
		if($("input[name='"+id+"']").val() != undefined){
			value = $("input[name='"+id+"']").val();
			var trueValue = $("#dict"+id).val();
			if(trueValue != undefined) value=trueValue;
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
	$("textarea[questionType='FILLBLANK']:visible").each(function(){
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
		jAlert("问卷未完整，请完成后提交",null,function(){
			$(nullFocusDiv).find("input:visible:first").focus();
		});
		return;
	}
	if(nullFocusCheckedOption != null){
		jAlert("问卷未完整，请完成后提交",null,function(){
			$(nullFocusCheckedOption).focus();
		});
		return;
	}
	if(nullFocusTr != null){
		jAlert("问卷未完整，请完成后提交",null,function(){
			$(nullFocusTr).find("input:visible:first").focus();
		});
		return;
	}
	if(nullFocusOption != null){
		jAlert("问卷未完整，请完成后提交",null,function(){
			$(nullFocusOption).focus();
		});
		return;
	}
	
	$("input[idCard='1']:checked").each(function(){
		var idCard = $(this).val();
		if(idCard != undefined && !IdCardValidate(idCard.trim())){
			$(this).focus();
			jAlert("身份证无效");
			return;
		}
	});
	
	if("${uqsVersion}".substr(4,3)=="001"){
		//校验采血号
		var bloodNo = $("input[name='OPTION4_3_1']").val();
		var data = {};
		data['bloodNo']=bloodNo;
		if(bloodNo != undefined && $.trim(bloodNo) != ''){
			var back=false;
			$.post("${ctx}/qn/checkBloodNo",data,function(result){
		      if(!result.success){
		    	jAlert(result.msg);
		    	back=true;
		      }
			},'json');
			if(back)return;
		}
		
		if($("input[name='OPTION2_1_7']").length==0 && ($("input[name='OPTION2_1_4']").length==0 || $("input[name='OPTION2_1_5']").length==0 || $("input[name='OPTION2_1_6']").length==0)){
			jAlert("请填写具体地址");
			return;
		}
	}
	
	if("${uqsVersion}".substr(4,3)=="001"){
		 $( "#dialog-planDate" ).modal({
	         backdrop: 'static'
	    });
	}
	
	<%--if("${uqsVersion}".substr(4,3)=="002"){--%>
		 <%--$( "#dialog-planDate" ).modal({--%>
	         <%--backdrop: 'static'--%>
	    <%--});--%>
	<%--}--%>

    if("${uqsVersion}".substr(4,3)=="013"){
        $( "#dialog-planDate" ).modal({
            backdrop: 'static'
        });
    }

	if("${uqsVersion}".substr(4,3)=="006"){
		surePwd();
	}
}




function tempSub(){
	$("#bnt_zc").attr("disabled",true);
	//调用检查
	$("#subForm").html("<input type=\"hidden\" name=\"patientId\" value=\"${patientId}\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"uqsVersion\" value=\"${uqsVersion}\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"tempSave\" value=\"true\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"UQSBeginTime\" value=\"${UQSBeginTime}\"/>");
	
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
			var trueValue = $("#dict"+id).val();
			if(trueValue != undefined) value=trueValue;
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
	$("textarea[questionType='FILLBLANK']").each(function(){
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
	
	var myform = $("#subForm").serializeArray();
	var data = {};
	$.each(myform, function(i, field){
		data[field.name] = field.value;
	});
	
	$.post("${ctx}/qn/saveTempQn",data,function(result){
			if(result.result==1){
				jAlert('保存成功！');
			}else if(result.result==7){
				jAlert('该患者在系统中已经提交过问卷了！');
			}
			else{
				jAlert('保存失败！');
			}
			$("#bnt_zc").attr("disabled",false);
	},'json');
	$("#bnt_zc").attr("disabled",false);
	//$("#subForm")[0].submit();
}


//弹出对话框
function openDialog(url,modalId){
    $("#"+modalId ).modal({
        backdrop: 'static'
    });
    //使用此方法防止js缓存不加载
    if(url != null){
        $("#"+modalId+" p" ).load(url);
    }
}

function clickSelect(obj,rejectother,alertText){

	if( obj.id == "o_7_1_1" ){ //是否死亡选择是
		jConfirm("患者死亡将跳转至死亡事件上报问卷,确定后本问卷将被保存",null,function(r){
			if(r){
                // 保存问卷
                subWithoutValidate();

                $.post("${ctx}/qn/saveQn",$("#subForm").serializeArray(),function(e,a,b){
                    if( a == 'success' ){ //
                        // 跳转到死亡事件上报问卷
                        window.location.href="${ctx}/qn/jumpto006?patientId=${patientId}"
                    }else{
                        tempSub();// 保存失败则先缓存
                        jConfirm("问卷保存失败但已自动暂存,请稍后重试！");
                    }
                });

			}else{
				$("#o_7_1_1").prop('checked',false);
//				return;
			}
		});
	}


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
			var obj = $(this)[0];
			inputAutocomplete(obj);
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
	for(var i=0;i<arr.length;i++){
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
		var b=false;
		try{
			b = eval(convertControlShow);
		}catch(e){

		}
		if(b && $(obj).parent().is(":visible")){
			$(obj).show();
		}else{
			$(obj).hide();
		}
	}
	
	if(controlHide != undefined && controlHide != ""){
		var convertControlHide = convertStr(controlHide);
		var b=false;
		try{
			b = eval(convertControlHide);
		}catch(e){
			
		}
		if(!b && $(obj).parent().is(":visible")){
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
		if($(obj).val().trim()==''){
			$("#dict"+$(obj).attr("optionFullId")).val("");
			$("#dictName"+$(obj).attr("optionFullId")).val("")
		}else{
			$(obj).val($("#dictName"+$(obj).attr("optionFullId")).val());
			if($(obj).val()==""){
				if($("#o_"+$(obj).attr("optionFullId")).length==1){
					$("#o_"+$(obj).attr("optionFullId")).prop("checked",false);
				}
			}
		}
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
		$("#"+id).prop("checked",true);
	}else{
		$("#"+id).prop("checked",false);
	}
}

/**
 * keyUp事件，验证字符输入是否合法
 */
function optionKeyUp(obj,type){
	var v = $(obj).val().trim();
	$(obj).val(v);
	triggerShowOrHide("o_"+$(obj).attr("name"));
	if(v == "")return;
	if(type != ""){
		v = $(obj).val().replace(eval("/[^"+getExpression(type)+"]/g"),'');
		$(obj).val(v);
	}else if($(obj).attr("idCard")=="1"){
		v = $(obj).val().replace(/[^0-9Xx]/g,'');
	}
}

function minDate(){
	var d=new Date();
	d.setDate(d.getDate()+150);
	return d.getFullYear() + "-" + (parseInt(d.getMonth())+1) + "-" + d.getDate();
}

function maxDate(){
	var d=new Date();
	d.setDate(d.getDate()+210);
	return d.getFullYear() + "-" + (parseInt(d.getMonth())+1) + "-" + d.getDate();
}

function startDate(){
	var d=new Date();
	d.setDate(d.getDate()+180);
	return d.getFullYear() + "-" + (parseInt(d.getMonth())+1) + "-" + d.getDate();
}

function surePlanDate(){
	if($.trim($("#planDate").val())==''){
		jAlert("请选择预约时间");
		return;
	}
	$("#subForm").append("\n<input type=\"hidden\" name=\"planDate\" value=\""+$.trim($("#planDate").val())+"\"/>");
	$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION99_99_1\" value=\""+$.trim($("#planDate").val())+"\"/>");
	if("${uqsVersion}".substr(4,3)=="001"){
		$("#subForm").append("\n<input type=\"hidden\" name=\"planRemark\" value=\""+$.trim($("#planRemark").val())+"\"/>");
		$("#subForm").append("\n<input type=\"hidden\" name=\"OPTION99_99_2\" value=\""+$.trim($("#planDate").val())+"\"/>");
	}
//	surePwd();
	$("#subForm").append("\n<input type=\"hidden\" name=\"searchparam\" value=\" ${searchparam} \"/>");
	$("#subForm")[0].submit();
}


function surePwd(){
	var surePwdHtml = "帐号:<input type='text' onblur='cLoginName(this)'/><br/><br/>密码:<input type='password' onblur='cPwd(this)'/>";
	$("#loginName").val("");
	$("#loginPwd").val("");
	jConfirm(surePwdHtml,null,function(r){
		if(r){
			var loginName=$.trim($("#loginName").val());
			var loginPwd=$.trim($("#loginPwd").val());
			if(loginName==""){
				jAlert("帐号不能为空");
				return;
			}
			if(loginPwd==""){
				jAlert("密码不能为空");
				return;
			}
			var data = {};
			data['loginName']=loginName;
			data['loginPwd']=loginPwd;
			$.post("${ctx}/qn/checkPwd",data,function(result){
		    	if(result.success){
		    		// 让定时方法失效
		    		clearInterval(netTimer);
		    		indexedDB.deleteDatabase("aqt"+$("#uqsVersionId_").val()+$("#intervid").val());
		    		$("#subForm")[0].submit();
		    	}else{
					jAlert("帐号或密码错误"); 
				}
			},'json');
		}
	});
}

function cancelPlanDate(){
	$('#dialog-planDate').modal('hide');
}

function cLoginName(obj){
	$("#loginName").val($(obj).val());
}

function cPwd(obj){
	$("#loginPwd").val($(obj).val());
}

function printQn(){
	$("#left-main").css("width","100%");
	$("#left-main").find("div[id^='q']").show();
	$("#left-main").find(".noPrint").hide();
	$("body").html($("#left-main")[0].outerHTML);
	window.print();
	location.reload();
}
</script>

</body>
</html>
