<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title>血样查询</title>
</head>
<body>
	<div id="navbar">
    	<ol class="breadcrumb">
          <li></li>
          <li class="active"></li>
        </ol>
    </div>
<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
<div id="alertError" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
<div id="main-content">
    	 <div id="select">  
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">

						  <div  class="row" style="margin-bottom: 10px;">
							  <div  class="leftLable col-md-1" style="text-align:right">盒号:</div>
							  <div  class="col-md-2">
								  <input style="width: 100px" class="form-control input-sm" id="box_code"
										 name="box_code" type="text" placeholder="盒号"/>
							  </div>

							  <div  class="leftLable col-md-1" style="text-align:right">血样编码:</div>
							  <div  class="col-md-2">
								  <input style="width: 100px" class="form-control input-sm" id="answer"
										 name="answer" type="text" placeholder="血样编码"/>
							  </div>

							  <div  class="leftLable col-md-1" style="text-align:right">医院名称:</div>
							  <div  class="col-md-2">
								  <input type="hidden" id="lccCode"  />
								  <input class="form-control input-sm" id="lccName"  name="lccName"
										 type="text" placeholder="输入单位简拼或LCCID" />
							  </div>

							  <div  class="leftLable col-md-1" style="text-align:right">随访类型:</div>
							  <div  class="col-md-2">
								  <select id="sfType" name="sfType"  style=" width:150px;height: 30px;
								  padding: 5px 10px;font-size: 12px;line-height: 1.5;border-radius: 3px;">
									  <option value="">请选择</option>
									  <option value="001">首次随访</option>
                                      <option value="011">6月随访</option>
                                      <option value="014">末次随访</option>
								  </select>
							  </div>
						  </div>
						  <div class="row">
							  <div  class="leftLable col-md-1" style="text-align:right">样本状态:</div>
							  <div  class="col-md-2">
								  <select id="state" name="state" style=" width:150px;height: 30px;
								  padding: 5px 10px;font-size: 12px;line-height: 1.5;border-radius: 3px;">
									  <option value="">请选择</option>
									  <option value="1">已出库</option>
									  <option value="0">未出库</option>
								  </select>
							  </div>

							  <div  class="leftLable col-md-1" style="text-align:right">问卷提交时间:</div>
							  <div  class="col-md-4">
								  <div style="width: 120px" class="input-group date"
									   id="examinationStartDiv" data-date-format="yyyy-mm-dd">
									  <input type="text"  id="examinationStartDate" placeholder="起始日期"
											 class="form-control input-sm"/>
									  <span class="input-group-addon input-sm btn">
										<i class="glyphicon glyphicon-calendar "></i>
										</span>
								  </div>
								  <div style="width: 120px" class="input-group date" id="examinationEndDiv"
									   data-date-format="yyyy-mm-dd">
									  <input type="text"  id="examinationEndDate" placeholder="结束日期"
											 class="form-control input-sm"/>
									  <span class="input-group-addon input-sm btn">
										<i class="glyphicon glyphicon-calendar "></i>
										</span>
								  </div>
							  </div>
							  <div  class="leftLable col-md-2" style="text-align:right">
								  <button type="button" onclick="doQuery();"
										  class="btn btn-primary btn-align-right btn-sm">查询</button>
								  <button type="button" onclick="doExport();"
										  class="btn btn-primary btn-align-right btn-sm">导出</button>
							  </div>
						  </div>
                      </form>
                </div>
            </div>
            <div class="table-responsive">   
             <table  id="dataTable" class="table table-bordered table-striped table-hover">
            	</table>
            	<div id="dataTablePager"></div>
         </div>
        <br style="clear:both;" />
    </div>
<script type="text/javascript">
$('#examinationStartDiv').datetimepicker({
	autoTop:true,
	language:'zh-CN',
	minView:'2', 
	autoclose:true,
	format : 'yyyy-mm-dd',
	weekStart : 1
}).on('changeDate', function(ev) {
	$('#examinationStartDiv').datepicker('hide');
});
$('#examinationEndDiv').datetimepicker({
	autoTop:true,
	language:'zh-CN',
	minView:'2', 
	autoclose:true,
	format : 'yyyy-mm-dd',
	weekStart : 1
}).on('changeDate', function(ev) {
	$('#examinationEndDiv').datepicker('hide');
});


function doExport(){
	window.open('${ctx}/scm/search/export?state='+$('#state').val()+'&answer='+$('#answer').val()
			+"&lccCode="+$('#lccCode').val()+"&sfType="+$('#sfType').val()+"&boxCode="+$('#box_code').val()
		+"&examinationStartDate="+$("#examinationStartDate").val()+"&examinationEndDate="+$("#examinationEndDate").val()
		+"&optionId=1&questionId=3&questionsetId=4");
}
function doQuery(){
	var o = {};
	o.state = $('#state').val();
	o.answer = $('#answer').val();
	o.lccCode = $('#lccCode').val();
	o.sfType = $('#sfType').val();
	o.boxCode = $('#box_code').val();
	o.examinationStartDate= $("#examinationStartDate").val();
	o.examinationEndDate = $("#examinationEndDate").val();
	$("#dataTable").jqGrid()
    .setGridParam({
        postData : o
    })
    .trigger("reloadGrid", [{
            }
        ]);
}
function renameState(cellvalue, option, rowObjects){
	if(cellvalue =='001'){
		return "首次随访";
	}
	if(cellvalue =='011'){
		return "6月随访";
	}
    if(cellvalue =='014'){
        return "末次随访";
    }
	return cellvalue;
}
$(function(){ 
	
	$.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) { 
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
			url : '${ctx}/scm/search/list',
			datatype : 'json',
			mtype : 'POST',
			postData :{},//optionId:'1',questionId:'3',questionsetId:'4'
			colNames : [
					'PROJECT_ID','盒号','血样编码','PID','患者姓名','LCCID','医院名称','问卷提交时间','随访类型','样本状态'
			        ],
			colModel : [
					{name : 'PROJECT_ID',hidden:true},
					{name : 'BOX_CODE',index : 'BOX_CODE',align : 'center',width: 80},
					{name : 'ANSWER',index : 'ANSWER',align : 'center',width: 80},
					{name : 'PATIENT_ID',index : 'PATIENT_ID',align : 'center',width: 80},
					{name : 'PATIENT_NAME',index : 'PATIENT_NAME',align : 'center',width: 80},
					{name : 'LCC_CODE',index : 'LCC_CODE',align : 'center',width: 80},
					{name : 'LCC_NAME',index : 'LCC_NAME',align : 'left'},
					{name : 'END_TIME',index : 'END_TIME',align : 'center',formatter:yymmddFormatter,width: 100},
					{name : 'UQS_VERSION',index : 'UQS_VERSION',align : 'center',width: 100,formatter:renameState},
					{name : 'STATE',index : 'STATE',align : 'center',width: 100}],
	        rowNum : 50, 
	        rowList : [ 15, 30, 50,100,150,500 ],  
	        height : "100%", 
	        autowidth : true,  
	        pager : '#dataTablePager',  
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "desc",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false
	 };  
	$("#dataTable").jqGrid(option);
	$("#dataTable").jqGrid('navGrid', '#dataTablePager', {
		edit : false,
		add : false,
		del : false,
		search : false,
		position : 'right'
	});
});

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
function openError(message,delay){
	$('#alertError').show().find('strong').text(message);
	window.setTimeout(function() {
		$('#alertError').slideUp("slow");
	}, delay);
}
</script>
</body>
</html>