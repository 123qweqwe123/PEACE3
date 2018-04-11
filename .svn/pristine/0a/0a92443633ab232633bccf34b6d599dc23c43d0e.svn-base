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
          <li>物资管理</li>
          <li class="active">耗材库存查询</li>
        </ol>
    </div>


<div id="main-content">
    	 <div id="select">  
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                            	 <li class="select-one"> <label form=name>耗材名称:</label></li>
                                 <li> 
                                 
                                 <div class="input-group date" id="duedate1">
				                 <input class="form-control input-sm"  id="materlName" name="materlName" type="text" />
				                  </div>
                                 </li>
                                 <li class="select-one"> <label form=name>库房名称:</label></li>
                                 <li> 
                                 	<div class="input-group date"  id="duedate2">
					               	   <input type="hidden" id="stockCode" name="stockCode">
									   <input type="text" id="stockName" name="stockName"   class="form-control input-sm" placeholder="输入单位编码">
					               </div>
                                  </li>
                                 <li> <button type="button" onclick="doQuery();" class="btn-style-default">查询</button></li> 
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
	var o = {};
	o.materlName = $('#materlName').val();
	o.stockName = $('#stockName').val();
	
	$("#dataTable").jqGrid()
    .setGridParam({
        postData : o
    })
    .trigger("reloadGrid", [{
                page : 1
            }
        ]);
}


$(function(){ 
	$.getJSON("${ctx}/combox/stockInfo",function(data) {
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
	        url : '${ctx}/material/matstock/list',   
	        datatype : 'json', 
	        mtype : 'POST',
	        colNames : [ '','医院名称','耗材名称','库房名称','批次','出库单价','采购价','库存数量','库存单位','截止有效期','生产厂商'/* ,'生产日期' */],
	        colModel : [ 
	                     {name : 'id',hidden : true,key:true},
	                     {name : 'lccName',  align:'left',width:'220px'},
	                     {name : 'materlName', align:'left'},
	                     {name : 'stockName',  align:'left',width:'220px',
								formatter:function(cellvalue, option, rowObjects){
									return rowObjects.stockCode+"_"+cellvalue;
								}},
	                     {name : 'materlBatch',  align:'left',width:'80px' },
	                     {name : 'materlPrice', align:'left',width:'80px'},
	                     {name : 'wholesalePrice', align:'left',width:'80px'},
	                     {name : 'storeAmount',align:'left',width:'80px'},
	                     {name : 'storeUnit',align:'left',width:'80px' },
	                     {name : 'periodValidity', align:'left',formatter:yymmddFormatter },
	                     {name : 'supplierName',align:'left',width:'220px' }/* ,
	                     {name : 'produceDate', align:'left',formatter:yymmddFormatter} */
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

</script>
</body>



</html>