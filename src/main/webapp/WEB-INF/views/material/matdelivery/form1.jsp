<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat,com.bdcor.pip.web.material.supp.domain.MaterExport"%>
<%@ page import="com.bdcor.pip.core.utils.Securitys"  %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>
<div id="delivery_div">

<div id="alert_msg" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
</div>
<fieldset >
<legend>出库单</legend>
<form id="export_form" class="form-inline">
		<div class="row">
			<div class="col-md-1" ><label for="exorderNo"><span style="color: red">*</span>出库单号:</label></div>
			<div class="col-md-3"><input type="text" class="form-control input-sm" id="exorderNo" name="exorderNo" value="${orderNo }"></div>
			<div class="col-md-1" ><label for="activeclassCode"><span style="color: red">*</span>出库类别:</label></div>
			<div class="col-md-3">
				<select id="activeclassCode" name="activeclassCode" class="form-control input-sm">
					<c:forEach items="${matList }" var="m">
						<option value="${m.activeclassCode }" ${mat.activeclassCode == m.activeclassCode ? 'selected="selected"' : '' }>${m.activeclassName }</option>
					</c:forEach>
				</select>
			</div>
			<div class="col-md-1" ><label for="exportDate"><span style="color: red">*</span>出库日期:</label></div>
			<div class="col-md-3"><input type="text" class="form-control input-sm" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" id="exportDate" name="exportDate" placeholder="选择日期"></div>
		</div>
		<div class="row">
			<div class="col-md-1" ><label for="userName"><span style="color: red">*</span>出库员名称:</label></div>
			<div class="col-md-3">
				<input type="text" id="userName" name="userName"  class="form-control input-sm" placeholder="选择已有用户 "/>
				<input type="hidden" id="userCode" name="userCode" class="form-control input-sm" />
				
			</div>
			<div class="col-md-1" ><label for="exampleInputName2">当前库房单位:</label></div>
			<div class="col-md-3">
				<input type="text"  value="<%=Securitys.getUser().getLccName() %>" class="form-control input-sm" readonly="readonly"/>
                <input type="hidden"  name="lccCode" value="<%=Securitys.getUser().getLccCode() %>"/>
			</div>
			<div class="col-md-1" ><label for="stockName"><span style="color: red">*</span>当前库房:</label></div>
			<div class="col-md-3">
			<input type="text" class="form-control input-sm" name ="stockName" id="stockName" placeholder="选择已有库房 ">
			<input id="stockCode" name="stockCode"  type="hidden"/>
			</div>
		</div>
		<div class="row">
			<div class="col-md-1" ><label for="exorderNo"><span style="color: red">*</span>出到的单位:</label></div>
			<div class="col-md-3">
				<input type="text" id="exportLccName" name="exportLccName"  class="form-control input-sm" placeholder="选择已有单位 "/>
                <input id="exportLccCode" name="exportLccCode"  type="hidden"/>
			</div>
			<div class="col-md-1" ><label for="exorderNo"><span style="color: red">*</span>出到的库房:</label></div>
			<div class="col-md-3">
				<input type="text" id="exportStockName" name="exportStockName"  class="form-control input-sm" placeholder="选择已有库房"/>
                <input id="exportStockCode" name="exportStockCode"   type="hidden"/>
			</div>
			<div class="col-md-1" ><span style="color: red">*</span><label for="supplierName">供货商:</label></div>
			<div class="col-md-3">
				 <input type="text" id="supplierName" name="supplierName"  class="form-control input-sm" placeholder="选择已有供应商" />
			</div>
		</div>
		<div class="row">
			<div class="col-md-1" ><label for="remark">备注:</label></div>
			<div class="col-md-10"><textarea id="remark" class="form-control" style="width: 80%"></textarea></div>
		</div>
	</form>
</fieldset>
<fieldset>
	<legend>出库明细</legend>
	<form class="form-inline" id="detail_form">
		<div class="row">
			<div class="col-md-1" ><label for="materlName"> <span style="color: red">*</span>物资名称:</label></div>
			<div class="col-md-3">
				<input type="text" class="form-control input-sm" id="materlName">
				<input type="hidden" id="materlinfoCode" name="materlinfoCode" />
				<input type="hidden" id="storeId" />
				<input type="hidden" id="materlinfoId" />
			</div>
			<div class="col-md-1" ><label for="materlBatch">批次:</label></div>
			<div class="col-md-3"><input type="text" class="form-control input-sm" id="materlBatch" readonly="readonly"></div>
			<div class="col-md-1" ><label for="wholesalePrice">采购价:</label></div>
			<div class="col-md-3"><input type="text" class="form-control input-sm" id="wholesalePrice" readonly="readonly"></div>
		</div>
		<div class="row">
			<div class="col-md-1" ><label for="periodValidity">截止有效期:</label></div>
			<div class="col-md-3"><input type="text" class="form-control input-sm" id="periodValidity" readonly="readonly"></div>
			<div class="col-md-1" ><label for="exportUnit">库存单位:</label></div>
			<div class="col-md-3"><input type="text" class="form-control input-sm" id="exportUnit" readonly="readonly"></div>
			<div class="col-md-1" ><label for="storeAmount">库存数量:</label></div>
			<div class="col-md-3"><input type="text" class="form-control input-sm" id="storeAmount" readonly="readonly"></div>
		</div>
		<div class="row">
			<div class="col-md-1" ><label for="materlPrice">出库单价:</label></div>
			<div class="col-md-3"><input type="text" readonly="readonly" class="form-control input-sm" id="materlPrice"></div>
			<div class="col-md-1" ><label for="exportAmount"><span style="color: red">*</span>出库数量:</label></div>
			<div class="col-md-3"><input type="number" class="form-control input-sm" id="exportAmount"></div>
			<div class="col-md-3"><input type="button" class="btn btn-primary btn-sm" value="添加" id="addDetail"></div>
		</div>
	</form>
</fieldset>
<!-- <fieldset>
<legend>出库组合</legend>
<form class="">
		<div class="row">
			<div class="col-md-1" ><label for="exportUnit"><label for="storeAmount">出库组合:</label></label></div>
			<div class="col-md-3">
				<input type="text" class="form-control input-sm" id="comb">
				<input type="hidden"  id="combid">
			</div>
			<div class="col-md-1" ><label for="exportUnit"><label for="storeAmount">人数:</label></label></div>
			<div class="col-md-3"><input type="text" class="form-control input-sm" id="countPeople"></div>
			<div class="col-md-3"><input type="button" class="btn btn-success btn-sm" value="添加" id="addGroup"></div>
		</div>
</form>
</fieldset> -->
<fieldset >
	<legend>已添加列表</legend>
	<table class="table table-bordered">
	<thead>
        <tr>
          <th style="display:none">物资明细ID</th>
          <th>耗材名称</th>
          <th>批次</th>
          <th>出库单价</th>
          <th>采购价</th>
          <th>库存单位</th>
          <th>出库数量</th>
          <th>截止有效期</th>
          <th>操作</th>
        </tr>
      </thead>
      <tbody id="detail_table_body">
      </tbody>
	</table>
</fieldset>
</div>
<script>
$("#detail_table_body").delegate("tr td:last-child","click",function(){
	$(this).parent("tr").remove();
});
//添加出库明细
var i=0;
function openErrorMsg(message,delay){
	$('#alert_msg').show().find('strong').text(message);
	window.setTimeout(function() {
		$('#alert_msg').slideUp("slow");
	}, delay);
}
$("#addDetail").click(function(){
	var storeId=$("#storeId").val();
	var exportAmount=$.trim($("#exportAmount").val());
	var storeAmount=$.trim($("#storeAmount").val());
	var storeCode = $.trim($("#stockCode").val());
	if(storeCode ==''){
		openErrorMsg("请选择当前库房",2000);
		return;
	}
	var materlName = $.trim($("#materlName").val());
	if(materlName ==''){
		openErrorMsg("物资名称不能为空!",2000);
		return;
	}
	if(exportAmount==""){
		openErrorMsg("出库数量不能为空!",2000);
		return;
	}
	if(parseInt(exportAmount)<=0){
		openErrorMsg("出库数量不能为零或者小于零!",2000);
		return;
	}
	if(parseFloat(exportAmount)>parseFloat(storeAmount)){
		openErrorMsg("出库数量不能大于库存!",2000);
		return;
	}
	
	var flag = false;
	$(".materlinfoId").each(function(){
		if($.trim($("#materlinfoId").val())==$(this).val()){
			flag=true;
		}
	});
	if(flag){
		openErrorMsg("不能重复添加物资[如果添加物资出错，可以点击删除，重新添加物资信息]",2000);
		return;
	}
	var tr="<tr><td style='display:none' title='id'>"+storeId+"</td><td><input type='hidden' class='materlinfoId' value='"+$("#materlinfoId").val()+"'>"+$("#materlName").val()+"</td><td>"+$("#materlBatch").val()+"</td><td>"+$("#materlPrice").val()+
	"</td> <td>"+$("#wholesalePrice").val()+"</td> <td>"+$("#exportUnit").val()+"</td><td title='count'>"+$("#exportAmount").val()+"</td><td>"+$("#periodValidity").val()+"</td><td>删除</td></tr>";
	$("#detail_table_body").append(tr);
	
})
//出库组合点击事件
$("#addGroup").click(function(){
	var combid=$("#combid").val();
	var countPeople=$("#countPeople").val();
	if(combid==""){
		alert("请选择出库组合!");
		return;
	}
	if(countPeople==""){
		alert("请选择人数!");
		return;
	}
	$.getJSON("${ctx}/material/matdelivery/comboxData?combId="+combid,function(data) { 
		for(var i in data){
			var record=data[i];
			//计算出库数量
			var exportAmount=countPeople/record.ratio;
			var tr="<tr><td style='display:none' title='id'>"+record.id+"</td><td>"+record.materlName+"</td><td>"+record.materlBatch+"</td><td>"+record.materlPrice+
			"</td> <td>"+record.wholesalePrice+"</td> <td >"+record.storeUnit+"</td><td title='count'>"+exportAmount+"</td><td>"+record.periodValidityStr+"</td><td>删除</td></tr>";
			$("#detail_table_body").append(tr);
		}
	});
	
})


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

//来源库房 $.getJSON("${ctx}/combox/stockInfo?limitLcc=true",function(data) { 
$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_STOREHOUSE&cols=STOCK_NAME|STOCK_CODE|HELP_CODE&where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22=%22,%22value%22:%22"+<shiro:principal property="lccCode" />+"%22}]",function(data) { 
		$('#stockName').autocomplete(data,{
		minChars: 0,
		mustMatch:true,
		width:"200px",
       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
        formatItem: function(item1,i, max) {
            return '<table><tr><td width="100px;">' + item1.STOCK_CODE + '</td><td>' + item1.STOCK_NAME + '</td></tr></table>';
        },
        // 指定 与 输入文字匹配的字段名
        formatMatch: function(item1,i, max) {
            return item1.STOCK_CODE + item1.STOCK_NAME;
        },
        // 选中 某条记录在输入框里 显示的数据字段
        formatResult: function(item1) {
            return item1.STOCK_NAME;
        }
	}); 
	//选中 某条记录 触发的事件
	$('#stockName').result(function(event, item1){
		if(item1){
	     $("#stockCode").val(item1.STOCK_CODE);
	   //物资名称
	     $.getJSON("${ctx}/material/matdelivery/comboxData?stockCode="+$("#stockCode").val(),function(data) { 
	     	$('#materlName').autocomplete(data,{
	     		minChars: 0,
	     		width:"350px",
	     		mustMatch:true,
	     		listTitle:{data:{id:'标识',materlName:'物资名称',materlBatch:'批次',wholesalePrice:'采购价',periodValidityStr:'截止有效期'},value:'-1'},
	            // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	             formatItem: function(item,i, max) {
	             	if(item){
	             		 return '<table><tr><td width="80px;">' + item.id+'</td><td width="80px;">' + item.materlName+'</td><td width="50px;">'+item.materlBatch+
	             		 '</td><td width="50px;">'+item.wholesalePrice+
	             		 '</td><td width="80px;">' + item.periodValidityStr + '</td></tr></table>';	
	             	}
	             },
	             // 指定 与 输入文字匹配的字段名
	             formatMatch: function(item,i, max) {
	             	if(item){
	             		 return item.helpCode;
	             	}
	             },
	             // 选中 某条记录在输入框里 显示的数据字段
	             formatResult: function(item) {
	             	if(item){
	             		 return item.materlName;
	             	}
	             }
	     	}); 
	     });

	     $('#materlName').result(function(event, item){ 
	     	$("#materlBatch").val(item.materlBatch);
	     	$("#wholesalePrice").val(item.wholesalePrice);
	     	$("#periodValidity").val(item.periodValidityStr);
	     	$("#exportUnit").val(item.storeUnit);
	     	$("#storeAmount").val(item.storeAmount);
	     	$("#supplierCode").val(item.supplierCode);
	     	$("#classCode").val(item.classCode);
	     	$("#materlinfoCode").val(item.materlinfoCode);
	     	$("#materlinfoId").val(item.id);
	     	$("#produceDate").val(item.produceDateStr);
	     	$("#materlPrice").val(item.materlPrice);
	     	$("#storeId").val(item.id);
	     }); 
		
		}
	}); 
});


//入库员
$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATERL_USER&cols=USER_CODE|USER_NAME|HELP_CODE&where=",function(data) { 
	$('#userName').autocomplete(data,{
		minChars: 0,
		width:'250px',
		mustMatch:true,
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

//出到的单位
$.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_LCC&cols=LCC_CODE|LCC_NAME&where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22!=%22,%22value%22:%22"+<shiro:principal property="lccCode"/>+"%22}]",function(data) { 
	$('#exportLccName').autocomplete(data,{
		minChars: 0,
		mustMatch:true,
		width:'300px',
       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
        formatItem: function(item,i, max) {
            return '<table><tr><td width="130px;">' + item.LCC_CODE + '</td><td>' + item.LCC_NAME + '</td></tr></table>';
        },
        // 指定 与 输入文字匹配的字段名
        formatMatch: function(item,i, max) {
            return item.LCC_CODE;
        },
        // 选中 某条记录在输入框里 显示的数据字段
        formatResult: function(item) {
            return item.LCC_NAME;
        }
	}); 
	//选中 某条记录 触发的事件
	$('#exportLccName').result(function(event, item){
		if(item){
			$("#exportStockName").val('');
			$("#exportStockCode").val('');
			$("#exportLccCode").val(item.LCC_CODE);
			//出到的库房
			$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_STOREHOUSE&cols=STOCK_NAME|STOCK_CODE|HELP_CODE&where=[{%22column%22:%22LCC_CODE%22,%22operate%22:%22=%22,%22value%22:%22"+item.LCC_CODE+"%22}]",function(data) { 
				if ( $('#exportStockName').unautocomplete ){
					$('#exportStockName').unautocomplete();
				}
				$('#exportStockName').autocomplete(data,{//收货库房
				minChars: 0,
				width:'250px',
				mustMatch:true,
	      		// 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	      		formatItem: function(item2,i, max) {
	            return '<table><tr><td width="130px;">' + item2.STOCK_CODE + '</td><td>' + item2.STOCK_NAME + '</td></tr></table>';
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item2,i, max) {
	            return item2.HELP_CODE;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item2) {
	            return item2.STOCK_NAME;
	        }
		}); 
		//选中 某条记录 触发的事件
		$('#exportStockName').result(function(event, item2){
			if(item2){
				$("#exportStockName").val(item2.STOCK_NAME);
				$("#exportStockCode").val(item2.STOCK_CODE);
			}
		}); 
	});		
			
		}
	}); 
	
});

//出库组合
$.getJSON("${ctx}/combox/comboxData?table=PIP_MMS_MATEXPORT_COMBI&cols=ID|NAME&where=",function(data) { 
	$('#comb').autocomplete(data,{
		minChars: 0,
		mustMatch :true,
     // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
      formatItem: function(item,i, max) {
          return '<table><tr><td width="130px;">' + item.NAME + '</td></tr></table>';
      },
      // 指定 与 输入文字匹配的字段名
      formatMatch: function(item,i, max) {
          return item.NAME;
      },
      // 选中 某条记录在输入框里 显示的数据字段
      formatResult: function(item) {
          return item.NAME;
      }
	}); 
	//选中 某条记录 触发的事件
	$('#comb').result(function(event, item){ 
		if(item)
	       $("#combid").val(item.ID);
	}); 

});
$("#export_form").validate({
	rules: {
		exorderNo:{
			required:true,
			remote:{
				type:"POST",
				url:'${ctx}/material/matdelivery/checkexorderNoExists',
				dataType:'json',
				data:{
					exorderNo:function(){
						var l_n = $("#exorderNo").val();
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
			},
		},
		exportLccName:{
			required:true
		},
		activeclassCode:{
			required:true,
			min:1
		},
		stockName:{
			required:true,
			notEqual:true
		},
		exportStockName:{
			required:true
		},
		userName:{
			required:true
		},
		stockName:{
			required:true
		},
		supplierName:{
			required:true
		},
		exportDate:{
			required:true
		}
	},
	messages:{
		exorderNo:{
			required:'出库单号不能为空',
			remote:'出库单号已存在'
		},
		exportLccName:{
			required:'出到的单位不能为空'
		},
		activeclassCode:{
			min:"类别不能为空"
		},
		stockName:{
			required:"库房名字不能为空",
			notEqual:'来源库房和出到的库房不能相同'
		},
		exportStockName:{
			required:"库房名字不能为空"
		},
		userName:{
			required:'出库员名称不能为空！'
		},
		stockName:{
			required:'当前库房不能为空'
		},
		supplierName:{
			required:'供应商不能为空'
		},
		exportDate:{
			required:"出库日期不能为空"
		}
	}
});

$("#detail_form").validate({
	rules: {
		materlName:{
			required:true
		}
		,exportAmount:{
			required:true,
			number:true,
			min:1,
			validateUnit:true
		}
		},
	messages:{
		materlName:{
			required:'物资名称不能为空'
		},
		exportAmount:{
			required:"出库数量不能为空",
			number:"请输入数字",
			min:'数量不能小于1',
			validateUnit:'出库数量不能大于库存数量'
		}
	}
});
</script>