<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title>库存查询</title>
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
    		<li class="active">项目管理</li>
			<li class="active">文件管理</li>
        </ol>
    </div>
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
			<button type="button" id ='cancel1' data-dismiss="modal" aria-hidden="true" class="btn btn-default btn-sm" tabindex="1001">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

<div id="main-content">
    	 <div id="select">  
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                            	 <li class="select-one"> <label form=name>箱号:</label></li>
                                 <li> 
                                 
                                 <div class="input-group date" id="duedate1">
				                 <input class="form-control input-sm"  id="archivesNo_2" name="archivesNo"
										type="text" placeholder="箱号"/>
				                  </div>
                                 </li>
                                 <li class="select-one"> <label form=name>库房名称:</label></li>
                                 <li> 
                                 	<div class="input-group date"  id="duedate2">
					               	  <input type="hidden" id="stockCode_2" name="stockCode">
									  <input type="text" id="stockName_2" name="stockName"   class="form-control input-sm" placeholder="输入单位编码">
					               </div>
                                  </li>
                                  <li> <input  type="checkbox" id="isZero"/></li>
                                  <li> 是否显示零库存 </li>
                                 <li> <button type="button" onclick="doQuery();" class="btn btn-primary btn-sm">查询</button></li>
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
	var isZero =$("#isZero").is(':checked');
	var o = {};
	o.archivesNo = $('#archivesNo_2').val();
	o.stockName = $('#stockName_2').val();
	if(isZero){
		o.isZero =2;
	}
	else{
		o.isZero =1;
	}
	$("#dataTable").jqGrid()
    .setGridParam({
        postData : o
    })
    .trigger("reloadGrid", [{
                 
            }
        ]);
}


$(function(){ 
	$.getJSON("${ctx}/combox/stockInfo?limitLcc=true",function(data) {
	    $('#stockName_2').autocomplete(data,{
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
	    $('#stockName_2').result(function(event, item){ 
			if(item){
				if(item.STOCKCODE != $("#stockCode_2").val()){
					$("#stockCode_2").val(item.STOCKCODE);
				}
			}else{
				$("#stockCode_2").val("");
			}
	     });
	}); 
	var option = {
	        url : '${ctx}/material/matstock/list4cxb',   
	        datatype : 'json', 
	        mtype : 'POST',
	        colNames : [ '','LCCID','医院名称','库房名称','箱号','物资名称','批次','采购价','库存单位','库存数量','截止有效期','生产厂商','生产日期','详细信息'],
	        colModel : [ 
	                     {name : 'id',hidden : true,key:true},
	                     {name : 'lccCode',  align:'center',width:80},
	                     {name : 'lccName',  align:'center',width:130},
	                     {name : 'stockName', align:'center',
								formatter:function(cellvalue, option, rowObjects){
									return rowObjects.stockCode+"_"+cellvalue;
								} },
	                     {name : 'archivesNo', align:'center'},
	                     {name : 'materlName',  align:'center',width:'120px',hidden:true},
	                     {name : 'materlBatch',  align:'center',width:70 },
	                     {name : 'wholesalePrice', align:'center',width:100,hidden:true},
	                     {name : 'storeUnit',align:'center',width:70,formatter:function(){return '箱';}  },
	                     {name : 'stockNum',align:'center',width:70 },
	                     {name : 'periodValidity', align:'center',formatter:yymmddFormatter ,width:90 },
	                     {name : 'manufacturerCode',align:'center' },
	                     {name : 'createDate', align:'center',formatter:yymmddFormatter,hidden:true},
	                     {name:"look",index:"edit",align:"center",width:80,formatter:look}
	                   ],  
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
	        multiselect: false 
	 };  
	$("#dataTable").jqGrid(option);
});
function look(cellvalue, option, rowObjects){
	return "<a href='javascript:void(0)' style='color:green' onclick='toLook(\""+rowObjects.archivesNo+"\")'>详细信息</a>";
}
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
</script>
</body>

</html>