<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>短信报表</title>
	<script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script> 
	<style type="text/css">
		.leftLable{
			text-align: right;
		}
		.leftLable{
			padding-right:0;
			width:7%;
		}
	</style>
	
	<script type="text/javascript">
		//查询绑定
		var multipleSearch = function(){ 
			var myform = $("#searchForm").serializeArray(); 
			var data = {};
			$.each(myform, function(i, field){
				data[field.name]=null; 
				if(field.value && ''!=field.value){
					data[field.name] = field.value;
				}
			});   
	      
			var postData = $("#grid").jqGrid("getGridParam", "postData");  
			$.extend(postData,data);
	
			$("#grid").jqGrid("setGridParam", {
				search: true  
			}).trigger("reloadGrid", [{page:1}]);
		};  
	
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
</head>
<body>
	<ul class="breadcrumb">
	  <li class="active">短信报表</li>
	</ul>  
	<div id="select">
		<div class="select-main">
			<input type="hidden" id="selLccCode" name="selLccCode" value=""/>
			<form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
				<div  class="row" style="margin-top:5px;"> 
					<div  class="leftLable col-md-1">医院名称:</div> 
					<div  class="right col-md-2"> 
						<input id="lccCode" type="hidden" name="lccCode" />
						<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
					</div>
					
					<div  class="leftLable col-md-1">所属组别:</div> 
					<div  class="right col-md-2"> 
						<select class="form-control input-sm"  name="group" id="group" >
							<option value="">全部</option>
							<option value="01">非糖尿病实验组</option>
							<option value="02">非糖尿病对照组</option>
							<option value="11">糖尿病实验组</option>
							<option value="12">糖尿病对照组</option>
						</select>
					</div>
					<div class="leftLable col-md-1">发送时间:</div> 
					<div  class="right col-md-2"> 
						<input name="startDate" placeholder="开始时间" id="startDate"  class="form-control input-sm" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</div>
					<div  class="right col-md-2"> 
						<input name="endDate" placeholder="结束时间" id="endDate"  class="form-control input-sm" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
					<div  class="right" style="align:right"> 
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<div id="jqgrid" style="overflow:auto;	">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	<!-- 新增窗口 -->
	<div id='dialog-confirm' class="modal fade" style="width:100%">
		<div class="modal-dialog" style="width:70%">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">短信发送失败原因</h4>
				</div>
				<div class="modal-body">
					<p>加载中……</p>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function(){
		$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) { 
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

		var isOnce = true;
		var option = {
	        url : '${ctx}/msg/report/list',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : [ 'LCCID', '医院名称', '已随机人数',//'实际已回复的短信数',
				'发送短信总数', '已发送', '未发送', '发送成功', '发送失败', '空', '应回复短信数',
				'未回复短信数', '连续2次应回复人数', '连续2次未回复人数'],
	        colModel : [ 
						 {name : 'LCC_CODE', align:'center', width:'100', sortable:false,
							 formatter:function(cellvalue, options, rowObject){
							 	//return '<a href="${ctx}/msg/report/failReason?lccCode='+cellvalue+'">'+cellvalue+'</a>';
							 	return '<a href="#" onclick="openEchart('+cellvalue+')">'+cellvalue+'</a>';
							 }
						 },
						 {name : 'LCC_NAME', align:'center', width:'150', sortable:false},
				{name : 'RANDOMCOUNT', align:'center', width:'150', sortable:false},

				{name : 'ZS', align:'center', width:'100', sortable:false},
						 {name : 'YFS', align:'center', width:'100', sortable:false},
						 {name : 'WFS', align:'center', width:'100', sortable:false},
						 {name : 'FSCG', align:'center', width:'100', sortable:false},
						 {name : 'FSSB', align:'center', width:'100', sortable:false},
						 {name : 'FSSB_NULL', align:'center', width:'100', sortable:false},
						 {name : 'XYHF', align:'center', width:'150', sortable:false},
//						 {name : 'SJHF', align:'center', width:'150', sortable:false},
						 {name : 'WHF', align:'center', width:'150', sortable:false},
						 {name : 'FKDXS', align:'center', width:'250', sortable:false},
						 {name : 'TWO_WHF', align:'center', width:'150', sortable:false}
	                   ],         
	        rowNum : 150,
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        autowidth : false,  
//	        pager : '#pager',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "ASC",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
//			footerrow:true,
			multiselect: false,
			gridComplete : function(){

				if($("#grid").getRowData("0").LCC_NAME =='underfined' || $("#grid").getRowData("0").LCC_NAME == null ){
					$("#grid").addRowData(0,
							{"LCC_CODE":"","LCC_NAME":"合计",
							"ZS":$(this).getCol("ZS",false,"sum"),"YFS":$(this).getCol("YFS",false,"sum"),
							"RANDOMCOUNT":$(this).getCol("RANDOMCOUNT",false,"sum"),
							"WFS":$(this).getCol("WFS",false,"sum"),
							"FSCG":$(this).getCol("FSCG",false,"sum"),
							"FSSB":$(this).getCol("FSSB",false,"sum"),
							"FSSB_NULL":$(this).getCol("FSSB_NULL",false,"sum"),
							"XYHF":$(this).getCol("XYHF",false,"sum"),
							"WHF":$(this).getCol("WHF",false,"sum"),
							"FKDXS":$(this).getCol("FKDXS",false,"sum"),
							"TWO_WHF":$(this).getCol("TWO_WHF",false,"sum")
							}
							,"first");
				}else{
					return false;
				}
			}
	 	};
		$("#grid").jqGrid(option);
		$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})
		jqgridResponsive("grid",false);
		$("#btnQuery").click(multipleSearch);
	});


	function openEchart(lccCode){
		$("#selLccCode").val(lccCode);
		openDialog("${ctx}/msg/report/openmodalfailReason", "dialog-confirm");
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
	
	$(".close").click(function(){
		$("#dialog-confirm p" ).html("加载中……");
	});
	</script>


</body>
</html>