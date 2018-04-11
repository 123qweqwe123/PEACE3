<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.bdcor.pip.core.utils.Securitys"  %>
<%@ page import="java.text.SimpleDateFormat,com.bdcor.pip.web.material.supp.domain.MasterImportDetail"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

    <form id="import_form" class="form-inline"> <!-- form-horizontal -->  
       
		<%-- <div class="form-group" style="display:none">
			<label class="col-lg-5 col-md-3  control-label" for="projectName">当前项目:</label>
			<div class="col-lg-9 col-md-8">
				${projectName }
			</div>
		</div> --%>
	<div id="alert_msg" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>	
	<div class="col-md-4">
	    <div class="form-group">
	        <label  for="inorderNo"><span style="color: red">*</span> 入库单号 : </label>
	    </div>
	    <div class="form-group">
			<input type="text" id="imorderNo" name="imorderNo" value="${orderNo }" class="form-control input-sm" placeholder="入库单号"/>
<%-- 			${mat.imorderNo } --%>
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
		</br>
		</br>

	<div class="col-md-4">	
		<div class="form-group">
			<label class="control-label" for="lccCode">入库到单位:</label> <!--  医院名称 -->
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
				<input type="text" id="userName" name="userName" value="${mat.userName }" class="form-control input-sm" placeholder="选择已有用户"/>
				<input type="hidden" id="userCode" name="userCode" value="${mat.userCode }" class="form-control input-sm" placeholder="入库员编码"/>
			</div>
		</div>
	</div>
	<div class="col-md-4">		
		<div class="form-group">
			<label class="control-label" for="stockCode"><span style="color: red">*</span>入到的库房:</label>
			<div class="form-group">
				<input type="text" id="stockName" name="stockName" value="${mat.stockName}" class="form-control input-sm" placeholder="选择已有库房 "/>
				<input id="stockCode" name="stockCode" value="${mat.stockCode }"  type="hidden"/>
			</div>
		</div>
	</div>

				<input id="createDate" name="createDate" value="${mat.createDate }" type="hidden" />

</form>
	
	<br />
    <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%" />
    
    
    
    
    
    
    <form   class="form-inline" id="import_form2">
<!--     	<div class="row"> -->
	  		<div class="col-md-4">
	  					<div class="form-group">
							<label class="form-group control-label" for="materlName"><span style="color: red">*</span>物资名称:</label>
						<div class="form-group">
							<input type="text" id="materlName" name="materlName" value="${mat.materlName }" class="form-control input-sm " placeholder="请选择已有物资"/>
							<input type="hidden" id="materlCode" name="materlCode" value="${mat.materlCode }" />
							<input type="hidden" id="materlinfoCode" name="materlinfoCode" value="${mat.materlinfoCode }" />
						</div>
				</div>
	  		</div>
  		<br>
  		<br>
	  		<div class="col-md-4">
	  					<div class="form-group">
							<label class="form-group control-label" for="materlBatch"><span style="color: red"> * </span> 批           次     : </label>
							<div class="form-group">
								<input type="text" id="materlBatch" name="materlBatch" value="${mat.materlBatch }" class="form-control input-sm" placeholder="批次"/>
							</div>
						</div>
	  		</div>
	  		<div class="col-md-4">
	  			<div class="form-group">
					<label class="form-group control-label" for="wholesalePrice"><span style="color: red">*</span>采 购 价 :</label>
					<div class="form-group">
						<input type="text" name="wholesalePrice" id="wholesalePrice" value="${mat.wholesalePrice }" class="form-control input-sm" placeholder="采购价"/>
					</div>
				</div>
	  		</div>
	  		<div class="col-md-4">
 					<div class="form-group">
					<label class="form-group control-label" for="materlPrice"><span style="color: red"> * </span>出库单价:</label>
					<div class="form-group">
						<input type="text" name="materlPrice" id="materlPrice" value="${mat.materlPrice }" class="form-control input-sm" placeholder="出库单价"/>
					</div>
				</div>
	  		</div>
  		<br><br>
	  		<div class="col-md-4">
	  		
	  			<div class="form-group">
					<label class="form-group  control-label" for="importUnit"><span style="color: red">*</span>入库单位:</label>
					<div class="form-group">
						<input class="form-control input-sm" id="importUnit" name="importUnit"   type="text" placeholder="入库单位"/>
					</div>
					
				</div>
	  		
	  		</div>
	  		<div class="col-md-4">
	  			<div class="form-group">
					<label class="form-group control-label" for="importAmount"><span style="color: red"> * </span>入库数量:</label>
					<div class="form-group">
						<input type="text" name="importAmount" id="importAmount" value="${mat.importAmount }" class="form-control input-sm" placeholder="入库数量"/>
					</div>
				</div>
	  		</div>
	  		<div class="col-md-4">
	  			<div class="form-group">
					<label class="form-group control-label" for="storeUnit"><span style="color: red"> * </span>库存单位:</label>
					<div class="form-group">
						<c:if test="${not empty mat }"><input type="text" id="storeUnit" name="storeUnit" readonly="readonly" value="${mat.storeUnit}" class="form-control input-sm" placeholder="库存单位"/></c:if>
						<c:if test="${empty mat }"><input type="text" id="storeUnit" name="storeUnit"  class="form-control input-sm" placeholder="库存单位"/></c:if>
					</div>
				</div>
	  		</div>
  		<br><br>
  		
  		<div class="col-md-4">
  		
  			<div class="form-group">
				<label class="form-group control-label" for="periodValidity"><span style="color: red"> * </span>截止有效日期:</label>
				<div class="form-group">
				<c:if test="${not empty mat }"><input class="form-control input-sm" id="periodValidity" name="periodValidity" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(((MasterImportDetail)request.getAttribute("mat")).getPeriodValidity()) %>"  type="text" placeholder="截止有效期"/></c:if>
				<c:if test="${empty mat }"><input class="form-control input-sm" id="periodValidity" name="periodValidity"   type="text" placeholder="截止有效期"/></c:if>
				</div>
			</div>
  		
  		</div>
  		
  		
  		<div class="col-md-4">
	  		<div class="form-group">
				<label class="form-group control-label" for="supplierName">生产厂商:</label>
				<div class="form-group">
					<input type="text" readonly="readonly" id="supplierName1" name="supplierName" value="" class="form-control input-sm" placeholder="生产厂商"/>
					<input type="hidden" id="supplierCode1" name="supplierCode" value=""/>
				</div>
			</div>
  		</div>
  		
  		<div class="col-md-4">
  			
  			<div class="form-group" id="classA">
			<label class="form-group control-label" for=coefficientChange ><span style="color: red"> * </span>拆零系数:</label>
			<div class="form-group">
				<input  style="width: 180px" type="text" id="coefficientChange" name="coefficientChange" class="form-control input-sm" placeholder="拆零系数*入库数量=库存数量"/>
			</div>
		</div>
  		</div>
  		<div class="col-md-12" style="margin-bottom: 10px">
  			<button type="button" id ='toJqgrid' class="btn btn-primary btn-sm " onclick = "addtoGrid()" style="float:right; margin-right: 5px">增加</button>
    		&nbsp;
    		<button  type="button" id ='delete' class="btn btn-primary btn-sm " onclick = "deleteData()" style="float:right;margin-right: 5px">删除</button>
  			&nbsp;
  		</div>
<!-- 	</div> -->
			<input id="createDate" name="createDate" value="${mat.createDate }" type="hidden" />
    </form>
    	<br />
    	<div style="clear: both; margin-bottom: 10px">
        <hr style="height:1px;border:none;border-top:1px solid #555555;width:100%" />
    	</div>
   	<div id="Tempjqgrid" >
		<table id="tempgrid"></table>
		<div id="temppager"></div>
	</div>
    
	<script type="text/javascript">
		$(function(){
			$("#import_form").validate({
				rules: {
					<c:if test="${empty mat}">
					imorderNo:{
						required:true,
						remote:{
							type:"POST",
							url:'${ctx}/material/matwarehouse/checkimorderNoExists',
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
					</c:if>
					userName:{
						required:true
					},
					activeclassCode:{
						required:true,
						min:1
					},
					stockName:{
						required:true
					}
				},
				messages:{
					<c:if test="${empty mat}">
					imorderNo:{
						required:'入库单号不能为空',
						remote:'入库单号已存在'
					},
					</c:if>
					userName:{
						required:'入库员名称不能为空'
					},
					activeclassCode:{
						min:"类别不能为空"
					},
					stockName:{
						required:"库房名字不能为空"
					}
				}
			});
			<c:if test="${not empty mat}">
			$("#imorderNo").attr('readonly',true);
			</c:if>
			//供货商
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_SUPPLIER&cols=SUPPLIER_NAME|SUPPLIER_CODE|HELP_CODE&where=",function(data) { 
				$('#supplierName').autocomplete(data,{
					minChars: 0,
					mustMatch :true,
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
					mustMatch:true,
					width :'250',
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="60px;">' + item.STOCK_CODE + '</td><td>' + item.STOCK_NAME + '</td></tr></table>';
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
					width:'200',
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="100px;">' + item.USER_CODE + '</td><td>' + item.USER_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE + item.USER_CODE;
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
// 					url : '${ctx}/material/matwarehouse/list',
					datatype : 'local', 
					datatype : 'json',
// 					mtype : 'POST',
					colNames : [ '', '物资名称', '批次', '出库单价', '采购价','入库单位',
					             '入库数量','库存单位','拆零系数','截止有效期','生产厂商','','','','' ],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden : true,
								key:true,
								key:true
							},
							{
								name : 'materlName',
								index : 'MATERL_NAME',
								width : '100%'
							},
							{
								name : 'materlBatch',
								index : 'HELP_CODE',
								width : '100%'
							},
							{
								name : 'materlPrice',
								align : 'left',
								width : '100%'
								
							},
							{
								name : 'wholesalePrice',
								width : '100%'
							},
							{
								name : 'importUnit',
								width : '100%'
							},
							{
								name : 'importAmount',
								width : '100%'
							},
							{
								name : 'storeUnit',
								width : '100%'
							},
							{
								name : 'coefficientChange',
								width : '100%'
							},
							{
								name : 'periodValidity',
								formatter:yymmddFormatter,
								width : '100%'
							},
							{
								name : 'supplierName',
								width : '100%'
							},
							{
								name:'createDate',
								width:'100%',
								hidden:true
							},
							{
								name:'materlCode',
								width:'100%',
								hidden:true
							},
							{
								name:'materlinfoCode',
								width:'100%',
								hidden:true
							},
							{
								name:'supplierCode',
								width:'100%',
								hidden:true
							}
							
							],
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
						importAmount:{
							number:true
						},
						measurePeriod:{
							number:true
						},
						materlBatch:{
							required:true
						}
						,periodValidity:{
							required:true
						},coefficientChange:{
							required:true,
							validateUnit:true
						},importUnit:{
							required:true
						},importAmount:{
							required:true
						},storeUnit:{
							required:true
						}},
					messages:{
						materlName:{
							required:'物资名称不能为空'
						},
						materlPrice:{
							required:'出库单价不能为空',
							decimalsValue:"金额必须大于0并且只能精确到分"
						},
						wholesalePrice:{
							decimalsValue:"金额必须大于0并且只能精确到分"
						},
						importAmount:{
							number:"请输入数字"
						},
						measurePeriod:{
							number:"请输入数字"
						},
						materlBatch:{
							required:"批次不能为空"
						}
						,periodValidity:{
							required:"有效期不能为空"
						},coefficientChange:{
							required:"拆零系数不能为空",
							validateUnit:"入库和库存单位不一致时,必须填写"
						},importUnit:{
							required:"入库单位不能为空"
						},
						importAmount:{
							required:'入库数量不能为空'
						},storeUnit:{
							required:'库存单位不能为空'
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
				
				//物资名称
				$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_INFO&cols=MATERLINFO_CODE|MATERL_NAME|MATERL_CODE|HELP_CODE|STORE_UNIT|SUPPLIER_CODE&where=[{%22column%22:%22IS_REMOVED%22,%22operate%22:%22=%22,%22value%22:%220%22},{%22column%22:%22CLASS_CODE%22,%22operate%22:%22=%22,%22value%22:%22A%22}]",function(data) { 
					$('#materlName').autocomplete(data,{
						width:"300",     //提示的宽度，溢出隐藏
						minChars: 0,
						mustMatch:true,
						//scrollHeight: 300,   //提示的高度，溢出显示滚动条
				       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
				        formatItem: function(item,i, max) {
				        	if(item){
				        		 return '<table><tr><td width="180px">' + item.MATERLINFO_CODE + '</td><td >' + item.MATERL_NAME + '     </td></tr></table>';	
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
	
	</script>
