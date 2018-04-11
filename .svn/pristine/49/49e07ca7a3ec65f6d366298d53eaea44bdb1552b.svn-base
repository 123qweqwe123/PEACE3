<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
 <title>采血箱出库</title>
 <script  src="${ctx}/static/echarts/esl/esl.js"></script> 
 	<script type="text/javascript">
		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
	    	if(e && e.keyCode==27){ // 按 Esc 
	        	//要做的事情
	      	}
	    	if(e && e.keyCode==113){ // 按 F2 
	         	//要做的事情
	     	}            
	     	if(e && e.keyCode==13){ // enter 键
	        	 //要做的事情
	        	//multipleSearch();
	    	}
		};

	</script>
	<style>
		.img_mystyle{
			width: 20px;
			height: 20px;
		}
	</style>
</head>
<body>

<div id="navbar">
       <ul class="breadcrumb">
         <li class="active">出货管理</li>
 
      </ul>
</div>
	<div id="select">
		<div class="select-main">
			 <form action="" method="post" class="well-work bs-adp form-inline">
                 <input type="hidden" id="stockCode" name="stockCode">
                 <div class="row">
						 <div  class="leftLable col-md-1" style="text-align: right" >
							 默认库房
						 </div>
						 <div  class="col-md-2">
							<input type="text" id="stockName" name="stockName"
							   class="form-control input-sm" placeholder="输入单位编码">
						</div>

						<div  class="leftLable col-md-1" style="text-align: right" >
                         	出库单号:
                         </div>
						<div  class="col-md-2">
							<input type="text" id="exorderNo" class="form-control input-sm"
							   placeholder="请输入出库单号"/>
						</div>
						<input type="hidden" id="exportLccCode1" name="lccCode">
						<div  class="leftLable col-md-1" style="text-align: right" >
							收货单位:
						</div>
						<div  class="col-md-2">
							<input type="text" id="exportLccName1" name="lccName"
							   class="form-control input-sm" placeholder="输入单位简拼与LCCID">
						</div>
						<div  class="col-md-3"><div></div></div>
                </div>
                 <br>
                 <div class="row" >

                     <div  class="leftLable col-md-1" style="text-align: right" >
                         出库日期:
                     </div>
                     <div  class="col-md-4">
                         <div style="width: 120px;" class="input-group date" id="exportStartDateDiv"
                              data-date-format="yyyy-MM-dd">
                             <input type="text"  id="exportStartDate" placeholder="起始日期"
                                    name="exportStartDate" class="form-control input-sm"/>
                             <span class="input-group-addon input-sm btn">
                                    <i class="glyphicon glyphicon-calendar "></i>
                                    </span>
                         </div>
                         <div style="width: 120px;" class="input-group date" id="exportEndDateDiv"
                              data-date-format="yyyy-MM-dd">
                             <input type="text"  id="exportEndDate" placeholder="结束日期"
                                    name="exportEndDate" class="form-control input-sm"/>
                             <span class="input-group-addon input-sm btn">
                                    <i class="glyphicon glyphicon-calendar "></i>
                                    </span>
                         </div>
                     </div>
                     <div  class="leftLable col-md-1" style="text-align: right" >
                         创建日期:
                     </div>
                     <div class = "col-md-4" >
                         <div style="width: 120px;" class="input-group date" id="orderStartDateDiv"
                              data-date-format="yyyy-MM-dd">
                             <input type="text"  id="orderStartDate" placeholder="起始日期"
                                    class="form-control input-sm"/>
                             <span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
                         </div>
                         <div style="width: 120px;" class="input-group date" id="orderEndDateDiv"
                              data-date-format="yyyy-MM-dd">
                             <input type="text"  id="orderEndDate" placeholder="结束日期"
                                    class="form-control input-sm"/>
                             <span class="input-group-addon input-sm btn">
							<i class="glyphicon glyphicon-calendar "></i>
							</span>
                         </div>
                     </div>

                     <div class = "col-md-2" >
                         <button type="button" id="btnQuery" class="btn btn-primary btn-align-right
                             btn-sm">查询</button>
                     </div>
                 </div>
              </form>
		</div>
	</div>

<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
<div id="jqgrid">
    <table id="grid"></table>
    <div id="pager"></div>
</div>


<div id='dialog-confirm' class="modal fade" >
	  <div class="modal-dialog" style="width:895px;">
	    <div class="modal-content">
	      <div class="modal-header"><!--  style="background-color: #71A7D8" -->
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">添加出库单</h4>
	      </div>
	      <div class="modal-body" >
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<!-- <button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button> -->
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-confirm2' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">预约页面</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_other' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<!-- start -->
    <div id='dialog-confirm-grid' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">箱内信息</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel1' class="btn btn-default btn-sm" data-dismiss="modal" tabindex="1001">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<script type="text/javascript">

$('#orderStartDateDiv').datetimepicker({
	autoTop:true,
	language:'zh-CN',
	minView:'2', 
	autoclose:true,
	format : 'yyyy-mm-dd',
	weekStart : 1
}).on('changeDate', function(ev) {
	$('#orderStartDateDiv').datepicker('hide');
});
$('#orderEndDateDiv').datetimepicker({
	autoTop:true,
	language:'zh-CN',
	minView:'2', 
	autoclose:true,
	format : 'yyyy-mm-dd',
	weekStart : 1
}).on('changeDate', function(ev) {
	$('#orderEndDateDiv').datepicker('hide');
});
$('#exportStartDateDiv').datetimepicker({
	autoTop:true,
	language:'zh-CN',
	minView:'2', 
	autoclose:true,
	format : 'yyyy-mm-dd',
	weekStart : 1
}).on('changeDate', function(ev) {
	$('#exportStartDateDiv').datepicker('hide');
});
$('#exportEndDateDiv').datetimepicker({
	autoTop:true,
	language:'zh-CN',
	minView:'2', 
	autoclose:true,
	format : 'yyyy-mm-dd',
	weekStart : 1
}).on('changeDate', function(ev) {
	$('#exportEndDateDiv').datepicker('hide');
});
//弹出框查看
function toLook(archivesNo){
	var timebak = new Date().getTime();
	openDialogGrid("${ctx}/material/blood/openmodalScmarchives?archivesNo="+archivesNo+"&time="+timebak);
	//material/blood/listDetal
}
//弹出伦理对话框
function openDialogGrid(url,data){
	$( "#dialog-confirm-grid" ).modal({
		 backdrop: 'static'
	});
	//使用此方法防止js缓存不加载
	$("#dialog-confirm-grid p" ).load(url);
}
$(function(){
	$.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) { 
	    $('#exportLccName1').autocomplete(data,{
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
	    $('#exportLccName1').result(function(event, item){ 
			if(item){
				if(item.lccCode != $("#exportLccCode1").val()){
					$("#exportLccCode1").val(item.lccCode);
				}
			}else{
				$("#exportLccCode1").val("");
			}
	     });
	});
	
	
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
	
	
	
	
	
	var multipleSearch = function(){
		 var postData = $("#grid").jqGrid("getGridParam", "postData");  
		 var myform = $("#searchForm").serializeArray(); 
	      var mydata = {};
	      var stockCode= $("#stockCode").val();
			if(stockCode && stockCode!='undefinded' && stockCode.length>0 )
			{
				mydata.stockCode = stockCode;
			}else{
		        delete postData.stockCode;
		    }
			var exorderNo= $("#exorderNo").val();
			if(exorderNo &&  exorderNo!='undefinded' && exorderNo.length>0 )
			{
				mydata.exorderNo = exorderNo;
			}else{
		        delete postData.exorderNo;
		    }
			var exportLccName=$("#exportLccName1").val();
		      if(exportLccName && ''!=exportLccName){
		          mydata.exportLccName = exportLccName;
		      }else{
		          delete postData.exportLccName;
		      }
		      var exportStartDate= $("#exportStartDate").val();
		      if(exportStartDate && ''!=exportStartDate){
		          mydata.exportStartDate = exportStartDate;
		      }else{
		          delete postData.exportStartDate;
		      }
		      var exportEndDate= $("#exportEndDate").val();
		      if(exportEndDate && ''!=exportEndDate){
		          mydata.exportEndDate = exportEndDate;
		      }else{
		          delete postData.exportEndDate;
		      }
		      var orderStartDate =$("#orderStartDate").val();
		      if(orderStartDate && ''!=orderStartDate){
		          mydata.orderStartDate = orderStartDate;
		      }else{
		          delete postData.orderStartDate;
		      }
		      var orderEndDate =$("#orderEndDate").val();
		      if(orderEndDate && ''!=orderEndDate){
		          mydata.orderEndDate = orderEndDate;
		      }else{
		          delete postData.orderEndDate;
		      }
	     $.extend(postData,mydata);
	     $("#grid").jqGrid("setGridParam", {
	         search: true  
	     }).trigger("reloadGrid", [{page:1}]);
		
	};  
	
	var option = {
        url : '${ctx}/material/blood/outorderlist',
        datatype : 'json', 
        postData:{stockCode:$("#stockCode").val()},
        mtype : 'POST',
        colNames : ['出库单号','出库类型','收货LCCID','收货单位','收货库房','出货员','出库日期','出货状态','创建日期'],
        colModel : [ 
                   
					 {name : 'exorderNo', index : 'exorderNo', align:'left' ,sortable: false},
					 {name : 'activeclassName', index : 'exportStockName', align:'left' ,sortable: false},
					 {name : 'exportLccCode', index : 'exportLccCode', align:'center',sortable: false},
					 {name : 'exportLccName', index : 'exportLccName', align:'left',sortable: false},
					 {name : 'exportStockName', index : 'exportStockName',hidden:true, align:'left',sortable: false ,
								formatter:function(cellvalue, option, rowObjects){
									return rowObjects.exportStockCode+"_"+cellvalue;
								} },
                     {name : 'createBy', index : 'createBy', align:'left',sortable: false },
                     {name : 'exportDate', index : 'exportDate', align:'center',sortable: false ,formatter:yymmddFormatter},
                     {name : 'importState', index : 'importState', align:'left',formatter : function(cellvalue, option, rowObjects) {
                      	if(cellvalue==1){
                      		return "已出库";
                      	}
                      	if(cellvalue==2){
                      		return "已接收";
                      	}
						},sortable: false },
						{name : 'createDate', index : 'createDate', align:'center',sortable: false ,formatter:yymmddFormatter}
                   ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'exorderNo',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "ASC",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        multiselect: true,
        jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false,
			cell : "cell",
			id : "exorderNo"
		},/* 开启多级表格 start */
		subGrid: true,
		/* subGridUrl : '${ctx}/material/blood/list1',pipMmsExscmmaster
		   subGridModel : [ 
		                 { 
		                	 name : [ '编号', '项目ID', '入库编码','医院名称' ], 
		                	 width : [ 55, 200, 80 ],
		                	 mapping:['id','projectId','imorderNo','lccName']
		                 } 
		] */
		subGridRowExpanded :function(subgrid_id,row_id){
			//根据subgrid_id定义对应的子表格的table的id
			var subgrid_table_id;  
            subgrid_table_id = subgrid_id + "_t"; 
          	//根据subgrid_id定义对应的子表格的pager的id pipMmsExscmdetal
            var subgrid_pager_id;  
            subgrid_pager_id = subgrid_id + "_pgr";
            //动态添加子报表的table和pager
            $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+subgrid_pager_id+"' class='scroll'></div>"); 
            //创建jqGrid对象 获取选中的行数据
			var rowData = $("#grid").jqGrid('getRowData',row_id);
            $("#" + subgrid_table_id).jqGrid({  
            	url: "${ctx}/material/receive/listDetail?exorderNo="+rowData.exorderNo,
            	datatype: "json",
            	mtype : 'POST',
            	colNames: ['编号','项目编码','出库单号','物资信息编码','箱编号','批次','出库单价','采购价','库存单位','生产厂商','截止有效期','创建日期','查看详情'],
            	colModel: [  
                           {name:"id",index:"id",key:true,hidden:true},  
                           {name:"projectId",index:"projectId",hidden:true},  
                           {name:"exorderNo",index:"exorderNo",align:"center",width:150,hidden:true},  
       					   {name:"materlinfoCode",index:"materlinfoCode",align:"center",width:100,hidden:true},
       					   {name:"archivesNo",index:"archivesNo",align:"center",width:100},
       					   {name:"materlBatch",index:"materlBatch",align:"center",width:60},
       					   {name:"materlPrice",index:"materlPrice",align:"center",width:100},
       					   {name:"wholesalePrice",index:"wholesalePrice",align:"center",width:100},
       					   {name:"storeUnit",index:"storeUnit",align:"center",width:130},
       					   {name:"manufacturerCode",index:"manufacturerCode",width:120,align:"center",width:200},
       					   {name:"periodValidity",index:"periodValidity",width:90,formatter:yymmddFormatter},
       					   {name:'createDate',align:"center",index : 'createDate',width:90,formatter:yymmddFormatter} ,
       					   {name:"look",index:"edit",align:"center",width:80,formatter:look}
                       ],
               pager: subgrid_pager_id,  
               viewrecords: true,  
               rowNum : 15,
			   rowList : [ 15, 30, 50,100,150,500 ],
		       recordpos : 'left',
               height: "100%",
               width:"100%"
            });
            
		}
		/* 开启多级表格 end */
 	};  
	$("#grid").jqGrid(option); 
	$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'}
	).navButtonAdd('#pager',{caption:"添加",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
	.navButtonAdd('#pager',{caption:"打印订单",buttonicon:"ui-icon-pencil",onClickButton: function(){toPoint()},position:"last"});
	//该功能还没有确定。。。
	//.navButtonAdd('#pager',{caption:"作废",buttonicon:"ui-icon-plus",onClickButton: function(){toOther()},position:"last"})
	jqgridResponsive("grid",false); 
	
	//取消按钮操作
	$('#cancel').click(function(){
		$('#dialog-confirm').modal('hide');
		
	});	
		
	function toPoint(){
		var ids = $("#grid").jqGrid('getGridParam','selarrrow');
		if($.isEmptyObject(ids)){
			openError('请选择要接收的记录！',2000);
			return ;
		}
		var idDatas= "";
		$.each(ids,function(i,n){
			idDatas +=n +",";
		});
		idDatas=idDatas.substr(0,idDatas.length-1);
					
		window.open("${ctx}/material/blood/look?ids="+idDatas);
	}
	
	
	$("#btnQuery").click(multipleSearch);
	
	function look(cellvalue, option, rowObjects){
		return "<a href='javascript:void(0)' style='color:green' onclick='toLook(\""+rowObjects.archivesNo+"\")'>详细信息</a>";
	}
	
	function toAdd(){
		
		var timebak = new Date().getTime();
		openDialog('${ctx}/material/blood/openmodaladdneworder?time='+timebak);
	}
	
	function toOther(){
		 var id = $("#grid").jqGrid('getGridParam','selrow');//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
		 var rowData = $("#grid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
       var patientId= rowData.patientId ; //获得制定列的值 （auditStatus 为colModel的name）

       var lccCode= rowData.lccCode ;

		var patientId = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(patientId)) {
			openError('请选择一条数据',2000);
			return;
		}
		$("#do_other").attr("disabled",true);
		var timebak = new Date().getTime();
		openDialog2("${ctx}/reserve/openmodalAddReserveInputorUpdate?patientId="+patientId+'&lccCode='+lccCode+'&time='+timebak);
	}
	
	/**
	   修改的记录入库	
	**/
	$('#do_save').click(function(){
		var data = {};
		var patientId= $("#patientId").val();
		if(patientId=='undefinded' ||patientId.length<1 ) return ;
		data.patientId = patientId;
		var changeComeDate = $("#changeComeDate").val();
		data.changeComeDate =changeComeDate;
		
		//
		var resultCode = $("input[name='resultCode']:checked").val();
		if(resultCode!='' && resultCode!='undedfinded' || result.length>1){
		  data.resultCode =resultCode;
		}
		var remark = $("#remark").val();
		if(remark!='' && remark!='undedfinded' || remark.length>1){
			  data.remark =remark;
			}
		var maxVersion = $("#maxVersion").val();
		if(maxVersion!='' && maxVersion!='undedfinded'){
			  data.maxVersion =maxVersion;
			}
		var maxVTime = $("#maxVTime").val();
		if(maxVTime!='' && maxVTime!='undedfinded'){
			  data.maxVTime =maxVTime;
			}
		var realTime2 = $("#realTime2").val();
		if(realTime2!='' && realTime2!='undedfinded' && realTime2.length>0){
			  data.realTime2 =realTime2;
			}
		
		$("#do_save").attr("disabled",true);
		//alert("patientId"+patientId  +"==="+  "changeComeDate"+changeComeDate + "result"+result );
	       add(data);
		
	});
	
	//新增数据
	function add(data){
		//新增时注释id值
		//data['id'] = null;
		$.post("${ctx}/reserve/saveReserveLog",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm').modal('hide');
			var message = "修改失败！";
			if(result){
				message ='修改成功!';
			}
			$("#do_save").attr("disabled",false);
			showResult(result,message);
		},'json');
	}
	
	
	
	
	//弹出对话框
	function openDialog(url,data){
		$( "#dialog-confirm" ).modal({
			 backdrop: 'static'
		});
		$( "#do_add").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm p" ).load(url);
	}
	
	
	//弹出对话框
	function openDialog2(url,data){
		$( "#dialog-confirm2" ).modal({
			 backdrop: 'static'
		});
		$( "#do_other").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm2 p" ).load(url);
	}
	
	
	$('#planTime2').datepicker({
	 	format: 'yyyy-mm-dd', 
	 	weekStart: 1, 
	 	startDate:new Date(), //开始时间，在这时间之前都不可选
		endDate:'+3',//结束时间，在这时间之后都不可选
	 	autoclose: true, 
	 	todayBtn: 'linked', 	
		language: 'zh-CN'
	 });
	
	function openError(message,delay){
    	$('#alert').show().find('strong').text(message);
    	window.setTimeout(function() {
    		$('#alert').slideUp("slow");
    	}, delay);
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
	
	
	
});

</script>


</body>





</html>