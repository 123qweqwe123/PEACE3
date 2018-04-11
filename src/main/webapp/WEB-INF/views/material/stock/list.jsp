<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title></title>
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
          <li class="active">库存查询</li>
        </ol>
    </div>


<div id="main-content">
    	 <div id="select">  
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                            	 <li class="select-one"> <label form=name>物资名称:</label></li>
                                 <li> 
                                 
                                 <div class="input-group date" id="duedate1">
				                 <input class="form-control input-sm"  id="materlName" name="materlName" type="text" />
				                  </div>
                                 </li>
                                 <li class="select-one"> <label form=name>库房名称:</label></li>
                                 <li> 
                                 	<div class="input-group date"  id="duedate2">
					                  <input class="form-control input-sm" id="stockName" name="stockName" type="text" />
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
                 
            }
        ]);
}


$(function(){ 
	var option = {
	        url : '${ctx}/material/stock/list',   
	        datatype : 'json', 
	        mtype : 'POST',
	        colNames : [ '','单位','物资名称','库房名称','批次','出库单价','采购价','库存数量','库存单位','截止有效期','生产厂商','物资类别','生产日期',
	                     '设备状态','是否需要计量','下次计量时间'],
	        colModel : [ 
	                     {name : 'id',hidden : true,key:true},
	                     {name : 'lccName',  align:'left' },
	                     {name : 'materlName', align:'left' },
	                     {name : 'stockName',  align:'left' },
	                     {name : 'materlBatch',  align:'left' },
	                     {name : 'materlPrice', align:'left'},
	                     {name : 'wholesalePrice', align:'left' },
	                     {name : 'storeAmount',align:'left'},
	                     {name : 'storeUnit',align:'left' },
	                     {name : 'periodValidity', align:'left',formatter:yymmddFormatter },
	                     {name : 'supplierName',align:'left' },
	                     {name : 'classCode',align:'left',
	                    	 formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if ('A' == cellvalue) {
										result = '<span class="label label-success">物资</span>'
									} else if('B' == cellvalue) {
										result = '<span class="label label-success">设备</span>';
									}
									return result;
								} },
	                     {name : 'produceDate', align:'left',formatter:yymmddFormatter},
	                     {name : 'assetsStatus', align:'left',
	                    	 formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if ('1' == cellvalue) {
										result = '<span class="label label-success">可用</span>'
									} else if('2' == cellvalue) {
										result = '<span class="label label-danger">不可用</span>';
									}
									return result;
								} },
	                     {name : 'isMeasure', align:'left',
	                    	 formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if (1 == cellvalue) {
									result = '<span class="label label-success">需要计量</span>'
								} else if(2 == cellvalue) {
									result = '<span class="label label-danger">不需要计量</span>';
								}
								return result;
							} 
	                     },
	                    {name : 'nextMeasureDate',  align:'left',formatter:yymmddFormatter }
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