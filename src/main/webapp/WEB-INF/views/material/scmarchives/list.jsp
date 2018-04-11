<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title>单位采血包报损</title>
<style>
	
	.txt{
	    font-size:14px;
	    font-weight:bold;
	}
	
	.percentTxt
		  {
		  	font-size:9px;
		  	font-weight:normal;
		  	color:#333;
		  }
		.upArrow{
        	padding:0px 20px 0px 0px;
        	background-image:url('${ctx}/views/progress/assets/arrowUp1.png');	
        	background-repeat:no-repeat;
        	background-position: 55px 10px;
        }
        
        .downArrow{
        	padding:0px 20px 0px 0px;
        	background-image:url('${ctx}/views/progress/assets/arrowDown1.png');	
        	background-repeat:no-repeat;
        	background-position: 55px 10px;
        }
</style>
</head>
<body>
	<div id="navbar">
    	<ol class="breadcrumb">
          <li class="active">物资管理</li>
          <li class="active">采血包报损</li>
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
               			<fieldset>
                        	<ol>
                            	 <li class="select-one"> <label form=name>默认库房:</label></li>
                                 <li> 
                                 	<%-- <select id="stockCode" style="height: 30px;padding: 5px 10px;font-size: 12px;line-height: 1.5;border-radius: 3px;">
                                 		<c:forEach items="${storehouse}" var="oneStore">
											<option value="${oneStore.stockCode }" >${oneStore.stockName }</option>
										</c:forEach>
                                 	</select> --%>
                                 	<input type="hidden" id="stockCode" name="stockCode">
									<input type="text" id="stockName" name="stockName"   class="form-control input-sm" placeholder="输入单位编码">
                                 </li>
                                 <li class="select-one"> <label form=name>采血箱号:</label></li>
                                 <li> 
					                  <input style="width: 100px" class="form-control input-sm" id="archivesNo" name="stockName" type="text" placeholder="采血箱号"/>
                                  </li>
                                  <li class="select-one"> <label form=name>采血包号:</label></li>
                                 <li> 
					                  <input style="width: 100px" class="form-control input-sm" id="bloodpackageCode" name="stockName" type="text" placeholder="采血包号"/>
                                  </li>
                                  <li class="select-one"> <label form=name>采血包状态:</label></li>
                                 <li> 
                                 	<div class="input-group date"  id="duedate2">
					                  <select id="state" style="height: 30px;padding: 5px 10px;font-size: 12px;line-height: 1.5;border-radius: 3px;">
					                  		<option value="">请选择</option>
					                  		<option value="1">使用</option>
					                  		<option value="2">未使用</option>
					                  		<option value="3">报损</option>
					                  </select>
					               </div>
                                  </li>
                                 <li> <button type="button" onclick="doQuery();" style="width: 50px" class="btn btn-primary btn-sm">查询</button></li>
                            </ol>
                        </fieldset>
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



function doQuery(){
	//111
	var o = {};
	o.packageState = $('#state').val();
	o.bloodpackageCode = $('#bloodpackageCode').val();
	o.archivesNo =$('#archivesNo').val();
	o.stockCode = $('#stockCode').val();
	$("#dataTable").jqGrid()
    .setGridParam({
        postData : o
    })
    .trigger("reloadGrid", [{
                 
            }
        ]);
}
function renameState(cellvalue, option, rowObjects){
	if(cellvalue =='1'){
		return "<span style='color:blue'>使用</span>";
	}
	if(cellvalue =='2'){
		return "<span style='color:green'>未使用</span>";
	}
	if(cellvalue =='3'){
		return "<span style='color:red'>报损</span>";
	}
    if(cellvalue =='4'){
        return "<span style='color:red'>已过期</span>";
    }
}

$(function(){
	//当前用户的库房
	$.ajaxSettings.async = false; 
	$.getJSON("${ctx}/combox/stockInfo?limitLcc=true",function(data) {
		if(data.length >0){
			$('#stockName').val(data[0].STOCKNAME);
			$('#stockCode').val(data[0].STOCKCODE);
		}
	    $('#stockName').autocomplete(data,{
	        minChars: 0,
	        mustMatch:true, 
	        width:260,
	       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	        formatItem: function(item,i, max) {
	            return '<table><tr><td width="80px;">' + item.STOCKCODE + '</td><td width="180px;">' + item.STOCKNAME + '</td></tr></table>';
	            
	        },
	        // 指定 与 输入文字匹配的字段名
	        formatMatch: function(item,i, max) {
	            return item.STOCKCODE+item.STOCKNAME;
	        },
	        // 选中 某条记录在输入框里 显示的数据字段
	        formatResult: function(item) {
	            return item.STOCKNAME;
	        }
	    }); 
	  //选中 某条记录 触发的事件
	    $('#stockName').result(function(event, item){ 
			if(item){
				if(item.STOCKCODE != $("#stockCode").val()){
					$("#stockCode").val(item.STOCKCODE);
				}
			}else{
				$("#stockCode").val("");
			}
	     });
	}); 
	
	
	
	
	
	
	
	
	
	var option = {
			url : '${ctx}/material/scmarchives/list',
			datatype : 'json',
			mtype : 'POST',
			postData :{stockCode:$("#stockCode").val()},
			colNames : [
					'id','项目ID','LCCID','医院名称','库房编码','库房名称','箱号','采血包号','截止有效期','采血包状态','批次号','创建人','创建时间'
			             ],
			colModel : [
					{name : 'id',index : 'id',hidden:true},
					{name : 'projectId',index : 'projectId',align : 'center',hidden:true},
					{name : 'lccCode',index : 'lccName',align : 'center',width: 50},
					{name : 'lccName',index : 'lccName',align : 'left'},
					{name : 'stockCode',index : 'stockCode',align : 'center',width: 80,hidden:true},
					{name : 'stockName',index : 'stockName',align : 'center'},
					{name : 'archivesNo',index : 'archivesNo',align : 'center',width: 80},
					{name : 'bloodpackageCode',index : 'bloodpackageCode',align : 'center',width: 100},
					{name : 'periodValidity',index : 'periodValidity',align : 'center',formatter:yymmddFormatter,width: 100},
					{name : 'packageState',index : 'packageState',align : 'center',width: 100,formatter:renameState},
					{name : 'materlBatch',index : 'materlBatch',align : 'center',width: 70,hidden:true},
					{name : 'createBy',index : 'createBy',align : 'center',width: 80,hidden:true},
					{name : 'createDate',index : 'createDate',align : 'center',formatter:yymmddFormatter,width: 100,hidden:true}],
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],  
	        height : "100%", 
	        autowidth : true,  
	        pager : '#dataTablePager',  
	        sortname : 'id', 
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "desc",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: true 
	 };  
	$("#dataTable").jqGrid(option);
	$("#dataTable").jqGrid('navGrid', '#dataTablePager', {
		edit : false,
		add : false,
		del : false,
		search : false,
		position : 'right'
	}).navButtonAdd('#dataTablePager',{caption:"设置为未使用",buttonicon:"ui-icon-pencil",onClickButton: function(){toDestroy('2')},position:"last"})
	.navButtonAdd('#dataTablePager',{caption:"报损",buttonicon:"ui-icon-pencil",onClickButton: function(){toDestroy('3')},position:"last"});
});
//报损
function toDestroy(state){
	var ids = $("#dataTable").jqGrid('getGridParam','selarrrow');
	if($.isEmptyObject(ids)){
		openError('请选择要报损的记录！',2000);
		return ;
	}
	var idDatas= "";
	var flag = false;
	$.each(ids,function(i,n){
		var rowData = $("#dataTable").jqGrid('getRowData',n);
		if(rowData.packageState =='<span style=\"color:blue\">使用</span>'){
			openError('不能选择采血包状态是使用的记录！',2000);
			flag = true;
		}
		idDatas +=n +",";
	});
	if(flag){
		return ;
	}
	idDatas=idDatas.substr(0,idDatas.length-1);
	$.post("${ctx}/material/scmarchives/changeState",{ids: idDatas, state: state},function(result){
		$("#dataTable").trigger("reloadGrid");
		var message = "操作失败!";
		if(result.success){
			message = "操作成功！";
		}
		showResult(result.success,message);
	},'json');
}
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