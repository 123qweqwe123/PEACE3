<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bdcor.pip.core.utils.Securitys"  %>
<%@ page import="java.text.SimpleDateFormat,com.bdcor.pip.web.material.supp.domain.MasterImportDetail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
	<div id="alert_msg" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
    <form id="import_form" class="form-inline"> <!-- form-horizontal -->  
	<div class="col-md-4">
	    <div class="form-group">
			<label class=" control-label" for="inorderNo"><span style="color: red">*</span>入库单号:</label>
			<div class="form-group">
				<input type="text" id="imorderNo" name="imorderNo" value="${orderNo }" class="form-control input-sm" placeholder="入库单号"/>
			</div>
		</div>
	</div>	
	<div class="col-md-4">	
		 <div class="form-group">
	        <label  for="activeclassCode"><span style="color: red">*</span>入 库 类 别:</label>
	    </div>
	    <div class="form-group">
			<select id="activeclassCode" name="activeclassCode" class="form-control input-sm">
					<c:forEach items="${matList }" var="m">
						<option value="${m.activeclassCode }" ${mat.activeclassCode == m.activeclassCode ? 'selected="selected"' : '' }>${m.activeclassName }</option>
					</c:forEach>
				</select>
	    </div>
	</div>				
		<br />
		<br />

	<div class="col-md-4">	
		<div class="form-group">
			<label class="control-label" for="lccCode"><span style="color: red">*</span>入库到单位:</label> <!--  医院名称 -->
			<div class="form-group">
				<input type="text"  value="<%=Securitys.getUser().getLccName() %>" class="form-control input-sm" readonly="readonly"/>
				<input type="hidden"  name="lccCode" value="<%=Securitys.getUser().getLccCode() %>"/>
			</div>
		</div>
	</div>	
	
	<div class="col-md-4">	
		<div class="form-group">
			<label class="control-label" for="userName"><span style="color: red">*</span>入库员名称:</label>
			<div class="form-group">
				<input type="text" id="userName" name="userName" value="${mat.userName }" class="form-control input-sm" placeholder="选择已有用户 "/>
				<input type="hidden" id="userCode" name="userCode" value="${mat.userCode }" class="form-control input-sm" placeholder="入库员编码"/>
			</div>
		</div>
	</div>
	<div class="col-md-4">		
		<div class="form-group">
			<label class="control-label" for="stockCode"><span style="color: red">*</span>入到的库房:</label>
			<div class="form-group">
				<input type="text" id="stockName" name="stockName" value="${mat.stockName}" class="form-control input-sm" placeholder="选择已有库房"/>
				<input id="stockCode" name="stockCode" value="${mat.stockCode }"  type="hidden"/>
			</div>
		</div>
	</div>
	<input id="createDate" name="createDate" value="${mat.createDate }" type="hidden" />
</form>
	</br>
    <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%" />
    
    <form   class="form-inline" id="import_form2">
	  		<div class="col-md-12">
	  				<div class="form-group">
							<label class="col-lg-4 col-md-3  control-label" for="materlName"><span style="color: red">*</span>设备名称:</label>
							<div class="col-lg-8 col-md-5">
								<input type="text" style="width: 240px" id="materlName" name="materlName" value="${mat.materlName }" class="form-control input-sm " placeholder="请选择已有物资(输入设备名称首字母)"/>
								<input type="hidden" id="materlCode" name="materlCode" value="${mat.materlCode }" />
								<input type="hidden" id="materlinfoCode" name="materlinfoCode" value="${mat.materlinfoCode }" />
							</div>
					</div>
	  		</div>
  		<div class="col-md-4" style="margin-top: 10px">
  					<div class="form-group">
						<label class="form-group control-label" for="materlBatch"><span style="color: red"> * </span> 批           次     : </label>
						<div class="form-group">
							<input type="text" id="materlBatch" name="materlBatch" value="${mat.materlBatch }" class="form-control input-sm" placeholder="批次"/>
						</div>
					</div>
  		</div>
  		<div class="col-md-4" style="margin-top: 10px">
  			<div class="form-group">
				<label class="form-group control-label" for="wholesalePrice"><span style="color: red">*</span>采 购 价 :</label>
				<div class="form-group">
					<input type="text" name="wholesalePrice" id="wholesalePrice" value="${mat.wholesalePrice }" class="form-control input-sm" placeholder="采购价"/>
				</div>
			</div>
  		</div>
  		<div class="col-md-4" style="margin-top: 10px">
			<div class="form-group">
				<label class="form-group control-label" for="materlPrice"><span style="color: red"> * </span>出库单价:</label>
				<div class="form-group">
					<input type="text" name="materlPrice" id="materlPrice" value="${mat.materlPrice }" class="form-control input-sm" placeholder="出库单价"/>
				</div>
			</div>
  		</div>
  		<!-- 设备入库 数量默认1台 -->
  		<div class="col-md-4" style="margin-top: 10px">
  			<div class="form-group">
  			<input type="hidden" id="importAmount" name="importAmount"  value="1"/>
				<label class="form-group control-label" for="storeUnit"><span style="color: red"> * </span>库存单位:</label>
				<div class="form-group">
					<c:if test="${not empty mat }"><input type="text" readonly="readonly" id="storeUnit" name="storeUnit" readonly="readonly" value="${mat.storeUnit}" class="form-control input-sm" placeholder="库存单位"/></c:if>
					<c:if test="${empty mat }"><input type="text" readonly="readonly" id="storeUnit" name="storeUnit"  class="form-control input-sm" placeholder="库存单位"/></c:if>
				</div>
			</div>
  		</div>
  		<br><br>
  		
  		<div class="col-md-4" style="margin-top: 10px">
  			<div class="form-group">
				<label class="form-group control-label" for="periodValidity"><span style="color: red"> * </span>截止有效日期:</label>
				<div class="form-group">
				<c:if test="${not empty mat }"><input class="form-control input-sm" id="periodValidity" name="periodValidity" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(((MasterImportDetail)request.getAttribute("mat")).getPeriodValidity()) %>"  type="text" placeholder="截止有效期"/></c:if>
				<c:if test="${empty mat }"><input style="width: 100px" class="form-control input-sm" id="periodValidity" name="periodValidity"   type="text" placeholder="截止有效期"/></c:if>
				</div>
			</div>
  		</div> 
  		<div class="col-md-4" style="margin-top: 10px">
	  		<div class="form-group">
				<label class="form-group control-label" for="supplierName">生产厂商:</label>
				<div class="form-group">
					<input type="text" id="supplierName1" readonly="readonly" name="supplierName" value="" class="form-control input-sm" placeholder="生产厂商"/>
					<input type="hidden" id="supplierCode1" name="supplierCode" value=""/>
				</div>
			</div>
  		</div>
  		
  		<div class="col-md-4" style="margin-top: 10px">
  			<div class="form-group" >
			<label class="form-group control-label" for=coefficientChange >设备状态:</label>
			<div class="form-group">
				<select id="assetsStatus" name="assetsStatus" class="form-control input-sm">
					<option value="1" <c:if test="${mat.assetsStatus=='1'}">selected="selected"</c:if>>可用</option>
					<option value="2" <c:if test="${mat.assetsStatus=='2'}">selected="selected"</c:if>>不可用</option>
				</select>
			</div>
		</div>
  		</div>
  		<div class="col-md-4" style="margin-top: 10px">
  			<div class="form-group" >
				<label class="form-group control-label" for="isMeasure">是否要计量:</label>
				<div class="form-group">
				<select id="isMeasure" onchange="isMeasureChange()" name="isMeasure" class="form-control input-sm">
						<option value="1" <c:if test="${mat.isMeasure=='1'}">selected="selected"</c:if>>需要</option>
						<option value="2" <c:if test="${mat.isMeasure=='2'}">selected="selected"</c:if>>不需要</option>
					</select>
				</div>
			</div>
  		</div>
  		<br>
  		<br>
  		<div class="col-md-4" style="margin-top: 10px">
  			<div class="form-group" id="nextM">
				<label class="form-group  control-label" for="nextMeasureDate"><span style="color: red"> * </span>下次计量时间:</label>
				<div class="form-group">
				<c:if test="${mat.nextMeasureDate!=null }"><input style="width: 100px"  type="text" id="nextMeasureDate" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(((MasterImportDetail)request.getAttribute("mat")).getNextMeasureDate()) %>" name="nextMeasureDate"  class="form-control input-sm" placeholder="下次计量时间"/></c:if>
				<c:if test="${mat.nextMeasureDate==null }"><input style="width: 100px"  type="text" id="nextMeasureDate" name="nextMeasureDate"  class="form-control input-sm" placeholder="下次计量时间"/></c:if>
				</div>
			</div>
  		</div>
  		<div class="col-md-4" style="margin-top: 10px">
  			<div class="form-group">
				<label class="form-group  control-label" for="measurePeriod"><span style="color: red"> * </span>设备计量单位:</label>
				<div class="form-group ">
					<input type="text" id="measurePeriod" name="measurePeriod" value="1" class="form-control input-sm" placeholder="设备计量单位"/>
				</div>
			</div>
  		</div>
  		<div class="col-md-4" style="margin-top: 10px">
  			<div class="form-group">
				<label class="form-group  control-label" for="archivesNo"><span style="color: red"> * </span>设备编号:</label>
				<div class="form-group ">
					<input type="text" id="archivesNo" name="archivesNo"  class="form-control input-sm" placeholder="设备编号"/>
				</div>
			</div>
  		</div>
<!-- 	</div> -->
		<input id="classCode" name=classCode value="${mat.classCode }" type="hidden" />
		<input id="createDate" name="createDate" value="${mat.createDate }" type="hidden" />
    </form>
    <button style="margin-top: 10px" type="button" id ='toJqgrid' class="btn btn-primary btn-sm " onclick = "addtoGrid()" style="float:right;">增加</button>
    <button style="margin-top: 10px" type="button" id ='delete' class="btn btn-primary btn-sm " onclick = "deleteData()" style="float:right;">删除</button>
    <br>
   
        <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%" />
   	<div id="Tempjqgrid" >
		<table id="tempgrid"></table>
		<div id="temppager"></div>
	</div>
	<script type="text/javascript">
	var i =0;
		function addtoGrid(){
			if(!$("#import_form2" ).validate().form()){
				return;
			}
			var archivesNo = $.trim($("#archivesNo").val());
			if($.trim(archivesNo) ==''){
				openErrorMsg('亲:设备编号必填，且是唯一的 ！',2000);
				return ;
			}
			var measurePeriod =  $.trim($("#measurePeriod").val());
			var number = /^\d*$/;
			if(!number.test(measurePeriod)){
				openErrorMsg('设备计量单位必须是数字',2000);
				return ;
			}

			//记录行数
			var obj_arr = $("#tempgrid").jqGrid("getRowData");
			for(var data= 0; data <obj_arr.length; data++){
				if(obj_arr[data].archivesNo==archivesNo){
					openErrorMsg('亲:设备编号跟第'+(data+1)+'行的设备编号重复了！',2000);
					return ;
				}
			}
			
			$.post("${ctx}/material/devwarehouse/checkArchivesNo",
					{"archivesNo":archivesNo}
					,function(result){
					 if(result.success){
						var myform = $("#import_form2").serializeArray();
						var data = {};
						$.each(myform, function(i, field){
							data[field.name] = field.value;
						});
						$("#tempgrid").jqGrid('addRowData',i++,data);
					 }
					 else{
						 openErrorMsg(result.msg,2000);
					 }
			},'json');
		}
		function deleteData(){
	    	var ids=$("#tempgrid").jqGrid("getGridParam","selrow");
			$("#tempgrid").jqGrid("delRowData", ids); 
	    }
		function isMeasureChange(){
			if($("#isMeasure").val()=='2' || $("#isMeasure").val()==2){
				$("#nextM").hide();
			}
			else{
				$("#nextM").show();
			}
		}
		$(function(){
			
			
			$("#import_form").validate({
				rules: {
					imorderNo:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/material/devwarehouse/checkImportNo',
							dataType:'json',
							data:{
								imorderNo:function(){
									var l_n = $.trim($("#imorderNo").val());
									return l_n;
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
					activeclassCode:{
						required:true,
						min:1
					},
					stockName:{
						required:true
					},
					userName:{
						required:true
					}
				},
				messages:{
					imorderNo:{
						required:'入库单号不能为空',
						remote:'入库单号已存在'
					},
					activeclassCode:{
						min:"类别不能为空"
					},
					stockName:{
						required:"库房名字不能为空"
					},
					userName:{
						required:'出库员名称不能为空'
					}
				}
			});
			//供货商
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_SUPPLIER&cols=SUPPLIER_NAME|SUPPLIER_CODE|HELP_CODE&where=",function(data) { 
				$('#supplierName').autocomplete(data,{
					minChars: 0,
					mustMatch :true,
					width:'230px',
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="130px;">' + item.SUPPLIER_CODE + '</td><td>' + item.SUPPLIER_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.SUPPLIER_NAME;
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#supplierName').result(function(event, item){ 
					if(item)
				       $("#supplierCode").val(item.SUPPLIER_CODE);
				}); 

			});
			//库房
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_STOREHOUSE&cols=STOCK_NAME|STOCK_CODE|HELP_CODE&where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22=%22,%22value%22:%22"+<shiro:principal property="lccCode" />+"%22}]",function(data) { 
				$('#stockName').autocomplete(data,{
					minChars: 0,
					width:'230px',
					mustMatch:true,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="130px;">' + item.STOCK_CODE + '</td><td>' + item.STOCK_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.STOCK_NAME;
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#stockName').result(function(event, item){
					if(item)
				     $("#stockCode").val(item.STOCK_CODE);
				}); 
			});
			
			//入库员
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_USER&cols=USER_CODE|USER_NAME|HELP_CODE&where=",function(data) { 
				$('#userName').autocomplete(data,{
					minChars: 0,
					mustMatch:true,
					width:'230px',
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="130px;">' + item.USER_CODE + '</td><td>' + item.USER_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.USER_NAME;
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#userName').result(function(event, item){
					if(item){
						$("#userCode").val(item.USER_CODE);
					}
				}); 
			});
			
			
			
			var option = {
					datatype : 'local', 
					datatype : 'json',
// 					mtype : 'POST',
					colNames : [ '','设备code','设备编号','设备名称', '批次', '出库单价', '采购价',
								 '入库数量','库存单位','截止有效期','生产厂商','设备状态','是否要计量','下次计量时间',
								'设备计量单位','archivesNo' ],
					colModel : [{
									name : 'id',
									index : 'id',
									hidden : true,
									key:true
								},{
									name : 'materlinfoCode',
									index : 'MATERL_CODE',
									align : 'left',
									width : '100',
									hidden:true
								},{
									name:'archivesNo',
									align : 'left',
									width : '100px'
								},{
									name : 'materlName',
									align : 'left',
									width : '100px'
								},{
									name : 'materlBatch',
									index : 'HELP_CODE',
									width : '50px'
								},{
									name : 'materlPrice',
									align : 'left',
									width : '80px'
									
								},{
									name : 'wholesalePrice',
									width : '70'
								},{
									name : 'importAmount',
									width : '70'
								},{
									name : 'storeUnit',
									width : '80px'
								},{
									name : 'periodValidity',
									formatter:yymmddFormatter,
									width : '80'
								},{
									name : 'supplierName',
									width : '100%'
								},{
									name : 'assetsStatus',
									formatter : function(cellvalue, option, rowObjects) {
										var result = '';
										if (1 == cellvalue) {
											result = '可用'
										} else if(2 == cellvalue) {
											result = '不可用';
										}
										return result;
									},
									align : 'center',
									width : '80'
								},{
									name : 'isMeasure',
									formatter : function(cellvalue, option, rowObjects) {
										var result = '';
										if (1 == cellvalue) {
											result = '需要计量'
										} else if(2 == cellvalue) {
											result = '不需要计量';
										}
										return result;
									},
									align : 'center',
									width : '80'
								},{
									name : 'nextMeasureDate',
									formatter:yymmddFormatter,
									width : '80'
								},{
									name : 'measurePeriod',
									width : '100px'
								},{
									name:'archivesNo',
									hidden : true
								}],
					rowNum : 1500,
// 					rowList : [ 150,100,150,500 ],
					height : "80px",
// 					width:"900px",
					autowidth : true,
// 					pager : '#temppager',
					sortname : 'imorder_no',
// 					altRows : true,
// 					hidegrid : false,
					viewrecords : true,
// 					recordpos : 'left',
					sortorder : "desc",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					//multiselect : true,
					loadComplete : function() {
					}
					
				};
				$("#tempgrid").jqGrid(option);
				
				
				$("#import_form2").validate({
					rules: {
						materlName:{
							required:true
						},
						materlPrice:{
							required:true,
							decimalsValue:true
						},
						wholesalePrice:{
							required:true,
							decimalsValue:true
						},
						/* importAmount:{
							number:true
						}, */
						/* measurePeriod:{
							number:true
						}, */
						materlBatch:{
							required:true
						}
						,periodValidity:{
							required:true
						},coefficientChange:{
							validateUnit:true
						},importUnit:{
							required:true
						},
						nextMeasureDate:{
							required:true
						},
						measurePeriod:{
							required:true
						},
						archivesNo:{
							required:true
						},
						importAmount:{
							required:true
						}},
					messages:{
						materlName:{
							required:'设备名称不能为空'
						},
						materlPrice:{
							required:'出库单价不能为空',
							decimalsValue:"金额必须大于0并且只能精确到分"
						},
						wholesalePrice:{
							decimalsValue:"金额必须大于0并且只能精确到分"
						},
						/* importAmount:{
							number:"请输入数字"
						},
						measurePeriod:{
							number:"请输入数字"
						}, */
						materlBatch:{
							required:"批次不能为空"
						}
						,periodValidity:{
							required:"有效期不能为空"
						},coefficientChange:{
							validateUnit:"入库和库存单位不一致时,必须填写"
						},importUnit:{
							required:"入库单位不能为空"
						},
						nextMeasureDate:{
							required:'下次计量时间'
						},
						measurePeriod:{
							required:'设备计量单位不能为空'
						},
						archivesNo:{
							required:'设备编码不能为空'
						},
						importAmount:{
							required:'入库数量不能为空'
						}
					}
				});
				//选择开始时间触发
				$('#periodValidity').datepicker({
					format : 'yyyy-mm-dd',
					weekStart : 1
				}).on('changeDate', function(ev) {
					$('#periodValidity').datepicker('hide');
				});
				$('#nextMeasureDate').datepicker({
					format : 'yyyy-mm-dd',
					weekStart : 1
				}).on('changeDate', function(ev) {
					$('#nextMeasureDate').datepicker('hide');
				});
				//金额验证
				jQuery.validator.addMethod("decimalsValue",function(value, element) {
		                var decimalsValue =/^(?!0+(?:\.0+)?$)(?:[1-9]\d*|0)(?:\.\d{1,2})?$/ ;
		                return this.optional(element) || (decimalsValue.test(value));
		            }, "金额必须大于0并且只能精确到分");
				//入库单位校验
				jQuery.validator.addMethod("validateUnit",function(value, element) {
						var storeUnit=$("#storeUnit").val();//库存单位
						var importUnit=$("#importUnit").val();//入库单位
						var coefficientChange=$("#coefficientChange").val();//拆零系数
						//库存单位和入库单位不一致时,拆零系数必须填写
						if(storeUnit!=importUnit&&coefficientChange==""){
							return false;
						}else if(storeUnit==importUnit&&coefficientChange==""){
							$("#coefficientChange").val("1");//入库单位和库存单位一致时,将拆零系数置1
						}
		               return true;
		            }, "拆零系数必须填写");
				
				//设备名称
				$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_INFO&cols=MATERLINFO_CODE|MATERL_NAME|MATERL_CODE|HELP_CODE|STORE_UNIT|CLASS_CODE|SUPPLIER_CODE&where=[{%22column%22:%22IS_REMOVED%22,%22operate%22:%22=%22,%22value%22:%220%22},{%22column%22:%22CLASS_CODE%22,%22operate%22:%22=%22,%22value%22:%22B%22}]",function(data) { 
					$('#materlName').autocomplete(data,{
						width:"25%",     //提示的宽度，溢出隐藏
						minChars: 0,
						mustMatch:true,
						listTitle:{data:{MATERLINFO_CODE:'物资编码',MATERL_NAME:'物资名称'},value:'-1'},
						//scrollHeight: 300,   //提示的高度，溢出显示滚动条
				       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
				        formatItem: function(item,i, max) {
				        	if(item){
				        		 return '<table><tr><td width="180px">' + item.MATERLINFO_CODE + '</td><td >' + item.MATERL_NAME + '</td></tr></table>';	
				        	} 
				        },
				        // 指定 与 输入文字匹配的字段名
				        formatMatch: function(item,i, max) {
				        	if(item){
				        		 return item.HELP_CODE;
				        	}
				        },
				        // 选中 某条记录在输入框里 显示的数据字段
				        formatResult: function(item) {
				        	if(item){
				        		 return item.MATERL_NAME;
				        	}
				        }
					}); 
					
					
					//选中 某条记录 触发的事件
					$('#materlName').result(function(event, item){
						if(!item)return;
						$("#materlCode").val(item.MATERL_CODE);
						$("#materlinfoCode").val(item.MATERLINFO_CODE);
						//得到生产厂商的名字
						$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_SUPPLIER&cols=SUPPLIER_NAME|SUPPLIER_CODE|HELP_CODE&where=[{%22column%22:%22SUPPLIER_CODE%22,%22operate%22:%22=%22,%22value%22:%22"+item.SUPPLIER_CODE+"%22}]",function(data) {
							$("#supplierName1").val(data[0].SUPPLIER_NAME);
							$("#supplierCode1").val(data[0].SUPPLIER_CODE);
							$("#supplierName1").attr('readonly',true);
						});
						//STORE_UNIT得到库存单位 storeUnit
						$("#storeUnit").val(item.STORE_UNIT);
						//得到物资分类CLASS_CODE 物资需要拆零 设备需要显示计量信息 A耗材 B设备
						$("#importAmount").attr("readonly",false);
					}); 
					
			});
				
		});
		function openErrorMsg(message,delay){
	    	$('#alert_msg').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert_msg').slideUp("slow");
	    	}, delay);
	    }
	</script>
