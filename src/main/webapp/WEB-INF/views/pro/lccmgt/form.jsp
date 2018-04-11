<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib prefix="dict" uri="http://com.bdcor.pip.web.common.tagsupport" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="lcc_form" class="form-horizontal" onsubmit="check()" >
    <!-- 
		<div class="form-group" >
			<label class="col-lg-4 col-md-3  control-label" for="projectName">当前项目:</label>
			<div class="col-lg-7 col-md-10">
				${lcc.projectName }
			</div>
		</div>
	 -->
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccName"><span style="color: red">*</span>医院名称:</label>
			<div class="col-lg-7 col-md-10">
				<input type="text" id="lccName" name="lccName" value="${lcc.lccName }" class="form-control input-sm" placeholder="医院名称">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="areaCode"><span style="color: red">*</span>所属省份:</label>
			<div class="col-lg-7 col-md-10">
				<input type="hidden" id="areaCode1" name="areaCode" value="${lcc.areaCode }">
				<input type="text" id="areaName1" name="areaName" value='<dict:writer dictType="DISTRICT" code="${lcc.areaCode}"/>'  class="form-control input-sm" placeholder="输入省份首字母检索" ${lcc.areaCode!=null ? 'disabled':''}>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="rccName"><span style="color: red">*</span>所属项目点:</label>
			<div class="col-lg-7 col-md-10">
				<input type="hidden" id="rccCode_1" name="rccCode" value="${lcc.rccCode }"/>
				<input class="form-control input-sm" id="rccName_1" name="rccName" value="${lcc.parentName }" type="text" placeholder="输入简码选择所属项目点" ${lcc.lccCode!=null ? 'disabled':''}/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccCode"><span style="color: red">*</span>PID:</label>
			<div class="col-lg-7 col-md-10">
				<input type="text" id="lccCode" name="lccCode" value="${lcc.lccCode }" ${isEdit ? 'readonly=readonly':'' } class="form-control input-sm" placeholder="PID">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="isAdminDep">是否行政部门:</label>
			<div class="col-lg-7 col-md-10">
				<input type="radio" name="isAdminDep" value="1" onclick="isAdminDepFun(this.value)" ${lcc.isAdminDep eq '1' ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="isAdminDep" value="2" onclick="isAdminDepFun(this.value)" ${lcc.isAdminDep eq '2' or !isEdit ? "checked='checked'" : '' }/>否&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="lccRoleType">单位类型:</label>
			<div class="col-lg-7 col-md-10">
				<select class="form-control input-sm" id="lccRoleType" name ="lccRoleType" style="width: 200px">
					<option value="1" <c:if test="${lcc.lccRoleType =='1' }"> selected='selected'</c:if>>NCC</option>
					<option value="2" <c:if test="${lcc.lccRoleType =='2' }"> selected='selected'</c:if>>RCC</option>
					<option value="3" <c:if test="${lcc.lccRoleType =='3' }"> selected='selected'</c:if>>LCC</option>
				</select>
			</div>
		</div>
 <!-- 
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="statuslable">单位状态:</label>
			<div class="col-lg-7 col-md-10">
				<input type="radio" name="status" id="status1" value="1" ${pro != null && pro.status == '1' ? '':'disabled="disabled"' } ${lcc.status eq 1 ? "checked='checked'" : '' } />激活 &nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="status" id="status0" value="2" ${lcc.status eq 2 or !isEdit ? "checked='checked'" : '' } />未激活
			</div>
		</div>
 -->
 		<input type="hidden" name="status" value="${lcc.status}"/>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="contactName">址址:</label>
			<div class="col-lg-7 col-md-10">
				<input type="text" id="address" name="address" value="${lcc.address }" class="form-control input-sm" placeholder="地址">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="contact"><span style="color: red">*</span>项目负责人:</label>
			<div class="col-lg-7 col-md-10">
				<input type="text" id="contact" name="projectManager" value="${lcc.projectManager }" class="form-control input-sm" placeholder="项目负责人">
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="contactTel">项目负责人电话:</label>
			<div class="col-lg-7 col-md-10">
				<input type="text" id="tel" name="tel" value="${lcc.tel }" class="form-control input-sm" placeholder="项目负责人电话">
			</div>
		</div>
		<!-- 
		<div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="organ"><span style="color: red">*</span>组织机构:</label>
			<div class="col-lg-7 col-md-10">
				<select id="organ" name="organ" class="form-control input-sm" onchange="organChange()">
					<option value="-1">请选择组织机构</option>
					<option value="22">国家心血管病中心</option>
				</select>
			</div>
		</div>
		 -->
		<%-- <div class="form-group">
			<label class="col-lg-4 col-md-3  control-label" for="organ"><span style="color: red">*</span>组织机构:</label>
			<div class="col-lg-7 col-md-10">
				<input type="hidden" class="form-control input-sm" name="organ" id="organ" value="${lcc.organ }"/>
		    	<input type="text" class="form-control input-sm" name="organName" id="organName" value="${lcc.organName }" onkeydown="return false;" placeholder="组织机构" required="required" ${lcc.lccCode!=null ? 'disabled':''}/>
		        <div id="organZtree" class="tree-select" > 
					<div id="organ_ztree" class="ztree" style="height:190px;overflow-y:scroll;" ></div>
				</div>
			</div>
		</div> --%>
		<div id="roleandlcc">
			<div class="form-group">
				<label class="col-lg-4 col-md-3  control-label" for="lccRole">单位角色描述:</label>
				<div class="col-lg-7 col-md-10">
					<input class="form-control input-sm" id="lccRole" name="lccRole" value="${lcc.lccRole }" type="text" placeholder="单位角色描述"/>
				</div>
			</div>
		</div>
		<div id="traingandsign" <%-- ${lcc.isAdminDep eq '1' ? 'hidden' : '' } --%>>
			<div class="form-group" id="isSignContractDiv">
				<label class="col-lg-4 col-md-3  control-label" for="isSignContract">合同是否签署:</label>
				<div class="col-lg-7 col-md-10">
					<input type="hidden" id ="isSignContract_input" value="${lcc.isSignContract}"> 
					<input type="radio" name="isSignContract" id="isSignContract1" onclick="isSignContractRadio(this.value)" value="1" ${lcc.isSignContract eq '1' ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="isSignContract" id="isSignContract0" onclick="isSignContractRadio(this.value)" value="2" ${lcc.isSignContract eq '2' or !isEdit ? "checked='checked'" : '' } />否
				</div>
			</div>
			<div class="form-group" id="contractSignDateDiv" <%-- ${lcc.isSignContract eq '1' ? '' : 'hidden' } --%>>
				<label class="col-lg-4 col-md-3  control-label" for="contractSignDate">合同签署日期:</label>
				<div class="col-lg-7 col-md-10">
					<div class="input-group date" id="contractSignDate1" data-date-format="yyyy-mm-dd">
						<input class="form-control input-sm" id="contractSignDate" name="contractSignDate" value="${lcc.contractSignDateStr }" type="text" placeholder="合同签署日期"/>
						<span class="input-group-addon input-sm btn">
						<i class="glyphicon glyphicon-calendar "></i>
						</span>
					</div>
				</div>
			</div>
			<%-- <div class="form-group" id="higherApproveDateDiv" ${lcc.isSignContract eq '1' ? '' : 'hidden' }>
				<label class="col-lg-4 col-md-3  control-label" for="higherApproveDate">合同批件日期:</label>
				<div class="col-lg-7 col-md-10">
					<div class="input-group date" id="higherApproveDate1" data-date-format="yyyy-mm-dd">
						<input class="form-control input-sm" id="higherApproveDate" name="higherApproveDate" value="${lcc.higherApproveDateStr }" type="text" placeholder="合同批件日期"/>
						<span class="input-group-addon input-sm btn">
						<i class="glyphicon glyphicon-calendar "></i>
						</span>
					</div>
				</div>
			</div> --%>
			<div class="form-group">
				<label class="col-lg-4 col-md-3  control-label" for="isEthical">伦理是否通过:</label>
				<div class="col-lg-7 col-md-10">
					<input type="hidden" id ="isEthical_input" value="${lcc.isEthical}">
					<input type="radio" name="isEthical" id="isEthical1" onclick="isEthicalRadio(this.value)" value="1" ${lcc.isEthical eq "1" ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="isEthical" id="isEthical0" onclick="isEthicalRadio(this.value)"  value="2" ${lcc.isEthical eq "2" or !isEdit ? "checked='checked'" : '' } />否
				</div>
			</div>
			<div class="form-group" id="ethicalEffectiveDateDiv" <%-- ${lcc.isEthical eq '1' ? '' : 'hidden' } --%>>
				<label class="col-lg-4 col-md-3  control-label" for="ethicalEffectiveDate">伦理通过日期:</label>
				<div class="col-lg-7 col-md-10">
					<div class="input-group date" id="ethicalEffectiveDate1" data-date-format="yyyy-mm-dd">
						<input class="form-control input-sm" id="ethicalEffectiveDate" name="ethicalEffectiveDate" value="${lcc.ethicalEffectiveDateStr }" type="text" placeholder="伦理通过日期"/>
						<span class="input-group-addon input-sm btn">
						<i class="glyphicon glyphicon-calendar "></i>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group" id="ethicalDisableDateDiv" <%-- ${lcc.isEthical eq '1' ? '' : 'hidden' } --%>>
				<label class="col-lg-4 col-md-3  control-label" for="ethicalDisableDate">伦理批件到期日期:</label>
				<div class="col-lg-7 col-md-10">
					<div class="input-group date" id="ethicalDisableDate1" data-date-format="yyyy-mm-dd">
						<input class="form-control input-sm" id="ethicalDisableDate" name="ethicalDisableDate" value="${lcc.ethicalDisableDateStr }" type="text" placeholder="伦理批件到期日期"/>
						<span class="input-group-addon input-sm btn">
						<i class="glyphicon glyphicon-calendar "></i>
						</span>
					</div>
				</div>
			</div>

			<div class="form-group" id="ethicalPaperCodeDiv" <%-- ${lcc.isEthical eq '1' ? '' : 'hidden' } --%>>
				<label class="col-lg-4 col-md-3  control-label" for="ethicalPaperCode">伦理批件编号:</label>
				<div class="col-lg-7 col-md-10">
					<input type="text" id="ethicalPaperCode" name="ethicalPaperCode" value="${lcc.ethicalPaperCode }" class="form-control input-sm" placeholder="伦理批件编号">
				</div>
			</div>
			<div class="form-group" id="isTrainingDiv">
				<label class="col-lg-4 col-md-3  control-label" for="isTraining">是否通过培训:</label>
				<div class="col-lg-7 col-md-10">
					<input type="hidden" id="isTraining_input" value="${lcc.isTraining}">
					<input type="radio" name="isTraining" id="isTraining1" onclick="isTrainingRadio(this.value)" value="1" ${lcc.isTraining eq '1' ? "checked='checked'" : '' } />是 &nbsp;&nbsp;&nbsp;&nbsp;
					<input type="radio" name="isTraining" id="isTraining0" onclick="isTrainingRadio(this.value)" value="2" ${lcc.isTraining eq '2' or !isEdit ? "checked='checked'" : '' } />否
				</div>
			</div>
			<div class="form-group" id="expectStartDateDiv" ${lcc.isTraining eq '1' ? '' : 'hidden' }>
				<label class="col-lg-4 col-md-3  control-label" for="expectStartDate">目标启动日期:</label>
				<div class="col-lg-7 col-md-10">
					<div class="input-group date" id="expectStartDate1" data-date-format="yyyy-mm-dd">
						<input class="form-control input-sm" id="expectStartDate" name="expectStartDate" value="${lcc.expectStartDateStr }" type="text" placeholder="目标启动日期"/>
						<span class="input-group-addon input-sm btn">
						<i class="glyphicon glyphicon-calendar "></i>
						</span>
					</div>
				</div>
			</div>
		</div>
		<input id="projectId" name="projectId" value="${lcc.projectId }" type="hidden" />
	</form>
	
	<div id="alertErr" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
<script type="text/javascript">

     
 

	$('#contractSignDate1').datetimepicker({
		autoTop:true,
		language:'zh-CN', 
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#contractSignDate1').datepicker('hide');
	});
	
	$('#higherApproveDate1').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#higherApproveDate1').datepicker('hide');
	});
	
	$('#ethicalEffectiveDate1').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#ethicalEffectiveDate1').datepicker('hide');
	});
	
	$('#ethicalDisableDate1').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#ethicalDisableDate1').datepicker('hide');
	});
	
	$('#expectStartDate1').datetimepicker({
		autoTop:true,
		language:'zh-CN',
		minView:'2', 
		autoclose:true,
		format : 'yyyy-mm-dd',
		weekStart : 1
	}).on('changeDate', function(ev) {
		$('#expectStartDate1').datepicker('hide');
	});
//-----------------------organ---------------------
	function initRcc(){
		/* $('#rccName_1').unautocomplete();
		var areaCode1 = $("#areaCode1").val();
		if(areaCode1 == ""){
			areaCode1 = "xxx";
		}
		$.getJSON("${ctx}/combox/rcc?provinceCode="+areaCode1+"&noDataLimit=true",function(data) { 
		    $('#rccName_1').autocomplete(data,{
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
		    $('#rccName_1').result(function(event, item){ 
				if(item){
					if(item.RCC_CODE != $("#rccCode_1").val()){
						$("#rccCode_1").val(item.RCC_CODE);
						generateCode(item.RCC_CODE);
					}
				}else{
					$("#rccCode_1").val("");
					$("#lccCodeform").val('');
				}
		     });
		}); */
		
		
		//所属单位
		$.getJSON("${ctx}/combox/rcc",function(data) { 
		    $('#rccName_1').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.RCC_CODE + '</td><td width="180px;">' + item.RCC_NAME + '</td></tr></table>';
		            
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.HELP_CODE+item.RCC_CODE;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.RCC_NAME;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#rccName_1').result(function(event, item){ 
				if(item){
					if(item.RCC_CODE != $("#rccCode_1").val()){
						$("#rccCode_1").val(item.RCC_CODE);
						//generateCode(item.RCC_CODE);
					}
				}else{
					$("#rccCode_1").val("");
				}
		     });
		});
		
		
		
		
		
		
		
		
	}
	$(function() {
		if($("#isEthical_input").val()!='1'){
			$("#ethicalEffectiveDateDiv").hide();
			$("#ethicalDisableDateDiv").hide();
			$("#ethicalPaperCodeDiv").hide();
		}
		if($("#isSignContract_input").val()!='1'){
			$("#contractSignDateDiv").hide();
			$("#higherApproveDateDiv").hide();
		}
		if($("#isTraining_input").val()!='1'){
			$("#expectStartDateDiv").hide();
		}
		
		//?all=true
		$.getJSON("${ctx}/combox/province",function(data) { 
		    $('#areaName1').autocomplete(data,{
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
		    $('#areaName1').result(function(event, item){ 
		    	if(item){
					if($("#areaCode1").val() != item.PROVINCE_CODE){
						$("#areaCode1").val(item.PROVINCE_CODE);
						$("#rccCode").val('');
						$("#rccName").val('');
						$("#lccCodeform").val('');
						//initRcc();
					}
				}else{
					$("#areaCode1").val('');
					$("#rccCode").val('');
					$("#rccName").val('');
					$("#lccCodeform").val('');
					//initRcc();
				}
		     });
		});
		initRcc();
		var setting = {
			    async: {
			        enable: true,
			        type:"get",
			        url:"${ctx}/sys/rbac/organization/list",
			    },
			    view: {
			        showIcon: true
			    },
			    data: {
			        simpleData: {
			            enable: true,
			            idKey: "id",
			            pIdKey: "parentId"
			        },
			        key:{
			            name:"name" 
			        }
			    } ,
			    callback: {
			        onClick:organzTreeOnClick,
			        onAsyncSuccess:ztreeonAsyncSuccess
			    }
			};
//		function organChange(){
//			var organValue = $("#organ").val();
//			if(organValue == "22"){
//				$("#roleandlcc").hide();
//			}else{
//				$("#roleandlcc").show();
//			}
//		}		
		 function organzTreeOnClick(event, treeId, treeNode){
			 //if(treeNode.type=='5'){
				 var org = treeNode.name;
			      var id =  treeNode.id;
			      $("#organ").val(id);  
			      $("#organName").val(org); 
			      if(org=='国家心血管病中心'){
				      if($('input[name="status"]:checked').val()=="1"&&$('input[name="isAdminDep"]:checked').val()=="2"){
							$("#isSignContractDiv").hide();
							$("#isTrainingDiv").hide();
					  }
			    	  $("#roleandlcc").hide();
			      }else{
			    	  $("#roleandlcc").show();
			      }

			      $("#organZtree").toggle();
			// }
		}
		
		 function ztreeonAsyncSuccess(event, treeId, treeNode, msg){
			 var zTree = $.fn.zTree.getZTreeObj("organ_ztree");
			 zTree.expandAll(true);
		 }
		$.fn.zTree.init($("#organ_ztree"), setting);
		 
		
		$("#organName").click(function(e){
		    $("#organZtree").css('width',$(this).outerWidth());
		    $("#organZtree").toggle();
		});
//----------------------------------------------------------------------------------

		//$("#traingandsign").hide();
		
		$("#lcc_form").validate({
			rules: {
				<c:if test="${empty lcc}">
				lccCode:{
					required:true,
					remote:{
						type:"POST",
						url:'${ctx}/pro/lccmgt/checkLccCodeExists',
						dataType:'json',
						data:{
							lccCode:function(){
								return $("#lccCodeform").val();
							}
						},
						dataFilter: function(data) {
							data= eval("("+data+")");
							if(data){
								if(!data.result){
									return true;
								}
								return false;
							}
							return false;
						}
					}
				},
				</c:if>
				rccName:{
					required:true
				},
				lccName:{
					required:true,
					maxlength:100
				},
				areaName:{
					required:true
				},
				projectManager:{
					required:true
				},
				organName:{
					required:true
				}
			},
			messages:{
				<c:if test="${empty lcc}">
				lccCode:{
					required:'PID不能为空！',
					remote:'PID已经存在，请重新输入！'
				},
				</c:if>
				rccName:{
					required:'请选择所属项目点'
				},
				projectManager:{
					required:'请输入项目负责人',
					maxlength:'最大长度不能超过20个字符！'
				},
				lccName:{
					required:'请输入医院名称',
					maxlength:'最大长度不能超过100个字符！'
				},
				areaName:{
					required:'请选择省份'
				},
				organName:{
					required:'请选择组织机构'
				}
			}
		});
		
		var settingOrgTree = {
			async : {
				enable : true,
				type : "post",
				url : "${ctx}/pro/region/listOrg",
			},
			view : {
				showIcon : true
			},
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "parentId"
				}
			},
			callback : {
				onClick : zTreeOnClick
			}
		};

		function zTreeOnClick(event, treeId, treeNode) {
			$("#organId").val(treeNode.id);
			$("#organName").val(treeNode.name);
			$("#projectId3").val($("#projectId").val());
			$("#principalId").val(treeNode.principal);
			$("#lccZtree").toggle();
		}

		$.fn.zTree.init($("#lcc_ztree"), settingOrgTree);

		$("#organName").click(function(e) {
			$("#lccZtree").css('width', $(this).outerWidth());
			$("#lccZtree").toggle();
		});
	});
	function generateCode(areaCode){
		var code = "";
		$.post("${ctx}/pro/lccmgt/getLccCode",{areaCode:areaCode},function(result){
			if(result.success){
				code = result.code;
				$("#lccCodeform").val(areaCode+code);
			}
		},'json');
	}
	function isAdminDepFun(data){
		if(data=='1'){
			$("#traingandsign").hide();
			$("#roleandlcc").hide();
			$("#status0").removeAttr('checked');
			$("#status1").attr('checked','checked');
		}else{
			$("#traingandsign").show();
			$("#roleandlcc").show();
		}
	}

	function isSignContractRadio(data){ 
		if(data == 1) {
			$("#contractSignDateDiv").show();
			$("#higherApproveDateDiv").show();
		} else {
			$("#contractSignDateDiv").hide();
			$("#higherApproveDateDiv").hide();
		}
	}
	function isEthicalRadio(data){
		if(data == 1) {
			$("#ethicalEffectiveDateDiv").show();
			$("#ethicalDisableDateDiv").show();
			$("#ethicalPaperCodeDiv").show();
		} else {
			$("#ethicalEffectiveDateDiv").hide();
			$("#ethicalDisableDateDiv").hide();
			$("#ethicalPaperCodeDiv").hide();
		}
	}
	function isTrainingRadio(data){
		if(data == 1) {
			$("#expectStartDateDiv").show();
		}else{
			$("#expectStartDateDiv").hide();
		}
	}
</script>