<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html>
<head>
 <title>样本管理</title>
 
 <style type="text/css">
 	.row{
 		margin-top:8px;
 	}
 </style>
</head>
<body>

<ul class="breadcrumb">
	<li class="active">样本管理</li><li class="active" >样本确认单</li><li class="active" >新增</li>
</ul>
	<div style="width: 75%;margin:0 auto;" >
		<form action="" id="subForm" method="post">
		<div class="row">
			<div class="col-md-2">所属项目单位:</div>
			<div class="col-md-3">
				<input type="hidden" name="lccCode" id="lccid" value="${vo.lccCode}"/>
				<input type="text" id="lcc_name" name="lccName" value="${vo.lccName}">
			</div>
			<div class="col-md-1">冰箱:</div>
			<div class="col-md-5">
				<input type="hidden" name="fridgeId" id="fridgeId"/>
				<input type="text" id="fridge_name">
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-2">冻存盒类型:</div>
			<div class="col-md-10 top">
				<input onchange="changeProper()" <c:if test="${fn:contains(vo.boxTypes,'A')}">checked</c:if> type="checkbox" name="boxType" value="A" id="boxType-A"><label for="boxType-A">A</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input onchange="changeProper()" <c:if test="${fn:contains(vo.boxTypes,'B')}">checked</c:if> type="checkbox" name="boxType" value="B" id="boxType-B"><label for="boxType-B">B</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input onchange="changeProper()" <c:if test="${fn:contains(vo.boxTypes,'C')}">checked</c:if> type="checkbox" name="boxType" value="C" id="boxType-C"><label for="boxType-C">C</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input onchange="changeProper()" <c:if test="${fn:contains(vo.boxTypes,'D')}">checked</c:if> type="checkbox" name="boxType" value="D" id="boxType-D"><label for="boxType-D">D</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input onchange="changeProper()" <c:if test="${fn:contains(vo.boxTypes,'E')}">checked</c:if> type="checkbox" name="boxType" value="E" id="boxType-E"><label for="boxType-E">E</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input onchange="changeProper()" <c:if test="${fn:contains(vo.boxTypes,'F')}">checked</c:if> type="checkbox" name="boxType" value="F" id="boxType-F"><label for="boxType-F">F</label>&nbsp;&nbsp;&nbsp;&nbsp;
				<input onchange="changeProper()" <c:if test="${fn:contains(vo.boxTypes,'G')}">checked</c:if> type="checkbox" name="boxType" value="G" id="boxType-G"><label for="boxType-G">G</label>&nbsp;&nbsp;&nbsp;&nbsp;
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-2">选择冻存盒:</div>
			<div class="col-md-10">
				<table cellpadding="0" cellspacing="8"  >
		            <tr>
		                <td>可选冻存盒</td>
		                <td>&nbsp;</td>
		                <td><font color='orange'>已选择冻存盒</font></td>
		            </tr>
		            <tr>
		                <td>
			                <div  id='hideOption'  style='display:none' > 
			                  
			                </div> 
		                    <select id="s1" name="s1" class="form-control" style="width:166px; height:220px;" multiple="multiple"> 
		                        
		                        
		                    </select>   
		                </td>
		                <td> 
		                    <br>
		                    <p><input id="b1" type="button" class="s1" value="&gt;" /></p>
		                    <br>
		                    <p><input type="button" id="b2" class="s1" value="&gt;&gt;" /></p>
		                    <br> 
		                    <p><input type="button" id="b3" class="s1" value="&lt;" /></p>
		                    <br>
		                    <p><input type="button" id="b4" class="s1" value="&lt;&lt;" /></p>
		                </td>
		                <td><select id="s2" name="s2" class="form-control" style="width:166px;height:220px;" multiple="multiple">
		                	 <c:forEach items="${boxs}" var="op" >
		                         <option style="color:red" value="${op.boxCode}_${op.boxType}"  boxType='${op.boxType}' fridgeId='${op.fridgeId}'>${op.boxCode}${op.boxType}${op.boxSate==1?"(已满)":"(未满)" }  </option>
		                     </c:forEach> 
		                </select></td>
		            </tr> 
	        	</table>
        	</div>
		</div>
		<form action="" id="subForm" method="post">
		<div class="row">
			<div class="col-md-2">单号:</div>
			<div class="col-md-10"><span></span><input type="text" name="checkInListNo" id="checkInListNo" value="${vo.checkInListNo}" maxlength="30"><span style="color:red">&nbsp;&nbsp;(必填)</span></div>
		</div>
		
		<div class="row">
			<div class="col-md-2">项目单位经办人:</div>
			<div class="col-md-10"><input type="text" name="lccOperator" id="lccOperator" value="${vo.lccOperator}"  maxlength="20"></div>
		</div>
		
		<div class="row">
			<div class="col-md-2">样本库经办人:</div>
			<div class="col-md-10"><input type="text" name="zyzxOperator" id="zyzxOperator"  value="${vo.zyzxOperator}" maxlength="20"></div>
		</div>
		
		<div class="row">
			<div class="col-md-2">收件日期:</div>
			<div class="col-md-10"><input type="text" id="receiveDate" name="receiveDate"  value="${vo.receiveDate}"></div>
		</div>
		
		<div class="row">
			<div class="col-md-2">交付日期:</div>
			<div class="col-md-10"><input type="text" id="deliveryDate" name="deliveryDate" value="${vo.deliveryDate}"></div>
		</div>
		
		<div class="row">
			<div class="col-md-2">交付时温度:</div>
			<div class="col-md-10"><input type="text" id="temperature" name="temperature" maxlength="3" value="${vo.temperature}"></div>
		</div>
		
		<div class="row">
			<div class="col-md-2">备注:</div>
			<div class="col-md-10"><textarea id="remark" name="remark" cols="50" rows="6" maxlength="500"><c:out value="${vo.remark}"></c:out></textarea></div>
		</div>
		<input type="hidden" name="id" value="${vo.id}"/>
		<input type="hidden" name="boxs" id="boxs"/>
		</form>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-10">
				<button type="button" id="btnBack" class="btn btn-primary btn-sm">返回</button>
      			<button type="button" id="btnSureAndNext" class="btn btn-primary btn-sm">确认</button>
			</div>
		</div>
	</div>
<script type="text/javascript">  
$(function(){
	$("#subForm").validate({
		rules: {
			checkInListNo:{
				required:true
			},
			temperature:{
				number:true,
				min:-99,
				max:99
			}
		},
		messages:{
			checkInListNo:{
				required:'请输入单号！'
			},
			temperature:{
				number:'请输入数字',
				min:'最小值只能-99',
				max:'最大值只能是99'
			}
		}});
  	$('#b1').click(function(){
        $obj = $('#s1 option:selected').clone(true);
        if($obj.size() == 0){
            alert("请至少选择一条!");
        }
        // style='color:#FF0000;BACKGROUND-COLOR:#FF0000'
        $($obj).css("color",'#FF0000');
        $('#s2').append($obj);
        $('#s1 option:selected').remove();
    });
    
    $('#b2').click(function(){
        $('#s2').append($('#s1 option').css("color",'#FF0000'));
    });
    
    $('#b3').click(function(){
        $obj = $('#s2 option:selected').clone(true);
        if($obj.size() == 0){
            alert("请至少选择一条!");
        }
        $($obj).css("color",'');
        $('#s1').append($obj);
        $('#s2 option:selected').remove();
    });
    
    $('#b4').click(function(){
        $('#s1').append($('#s2 option').css("color",''));
    });
    
    initFridgeAutoComplete();
    initHiddenBox();
	changeProper();
   
});

var date=new Date();
initDatePicker('#deliveryDate','', '#receiveDate','',  
       function(){
       },
       function(){
       },
       '2015-01-01','');  
$('#deliveryDate').datetimepicker('setEndDate',  date);
$('#receiveDate').datetimepicker('setEndDate',  date);

$.getJSON("${ctx}/combox/dataLimitLcc",function(data) { 
    $('#lcc_name').autocomplete(data,{
        minChars: 0,
        mustMatch:true, 
        width:260,
       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
        formatItem: function(item,i, max) {
            return '<table><tr><td width="80px;">' + item.LCC_CODE + '</td><td width="180px;">' + item.LCC_NAME + '</td></tr></table>';
            
        },
        // 指定 与 输入文字匹配的字段名
        formatMatch: function(item,i, max) {
            return item.LCC_NAME;
        },
        // 选中 某条记录在输入框里 显示的数据字段
        formatResult: function(item) {
            return item.LCC_NAME;
        }
    }); 
  //选中 某条记录 触发的事件
    $('#lcc_name').result(function(event, item){ 
    	if(item){
			if($("#lccid").val() != item.LCC_CODE){
				$("#hideOption").children().remove();
				$("#s1").children().remove();
				$("#s2").children().remove();
				$("#lccid").val(item.LCC_CODE);
				initFridgeList();
			}
		}else{
			$("#lccid").val(''); 
			initFridgeList();
		}
		
     });
});

function initFridgeList(){
	$('#fridge_name').unautocomplete();
	$("#fridge_name").val(''); 
	$("#fridgeId").val(''); 
	
	if($("#lccid").val() == '')return;
	changeProper();
	initFridgeAutoComplete();
}

function initFridgeAutoComplete(){
	$.getJSON("${ctx}/spem/iceBoxReg/fridgeList?lccId="+$("#lccid").val(),function(data) { 
		$('#fridge_name').autocomplete(data,{
		    minChars: 0,
		    mustMatch:true, 
		    width:260,
		   // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		    formatItem: function(item,i, max) {
		        return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="180px;">' + item.name + '</td></tr></table>';
		        
		    },
		    // 指定 与 输入文字匹配的字段名
		    formatMatch: function(item,i, max) {
		        return item.name;
		    },
		    // 选中 某条记录在输入框里 显示的数据字段
		    formatResult: function(item) {
		        return item.name;
		    }
		}); 
		//选中 某条记录 触发的事件
		$('#fridge_name').result(function(event, item){ 
		   if(item){
			   if($("#fridgeId").val() != item.id){
				   $("#fridgeId").val(item.id);
				   initHiddenBox();
				   changeProper();
			   }
		   }else{
		   		$("#fridgeId").val(''); 
		   	 	initHiddenBox();
		   	 	changeProper();
		   }
		  
		 });
	});
}

$('#btnBack').click(function(){
    history.go(-1);
});

$('#btnSureAndNext').click(function(){
	if(!jQuery($("#subForm")[0]).validate().form()){ return ;}
	if(confirm("确认修改吗？")){
		var boxs = "";
		$("#s2 option").each(function(){
			if(boxs.length >0){
				boxs += ",";
			}
			boxs += $(this).val();
		});
		$("#boxs").val(boxs);
		var form = $("#subForm")[0];
		form.action="${ctx}/spem/checkInList/modify";
		form.submit();
	}
});

function initHiddenBox(){
	$("#hideOption").children().remove();
	if($("#fridgeId").val() == "")return;
	$.ajaxSetup({   
        async : false  
    }); 
	$.post("${ctx}/spem/checkInList/getCanSelectBoxList",{fridgeId:$("#fridgeId").val(),checkInListId:''},
		function(data){
			if(data==null || data.length==0){
				return;
			}
			for(var i in data){
				var opStr = "<option value=\""+data[i].boxCode+"_"+data[i].boxType+"\" ";
				opStr += "boxType=\""+data[i].boxType+"\" ";
				opStr += "fridgeId=\""+data[i].fridgeId+"\">";
				opStr += data[i].boxCode+data[i].boxType+(data[i].boxSate==1?"(已满)":"(未满)")+"</option>";
				$("#hideOption").append(opStr);
			}
		},"json");
}

function changeProper(){
    var fridgeId = $("#fridgeId").val();
    var boxTypes = "";
   	$("input[name='boxType']:checked").each(function(){
   		boxTypes += $(this).val();
   	});
   	
    $("#s1").children().remove();
   	$("#hideOption").children().each(function(){
   		if($("#s2").find($("option[value='"+$(this).val()+"']")).length > 0){
   			return true;
   		}
   		
   		if($(this).attr("fridgeId") != fridgeId){
   			return true;
   		}
   		
   		if(boxTypes.indexOf($(this).attr("boxType"))<0){
   			return true;
   		}
   		
   		var obj=$(this).clone();
        $("#s1").append(obj); 
   	});
}
</script>
</body>
</html>


