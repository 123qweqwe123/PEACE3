<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<title></title>
<script type="text/javascript" src="${ctx}/static/echarts/esl/esl.js"></script>
<script type="text/javascript" src="${ctx}/static/echarts/echarts.js" ></script>
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
          <li class="active">系统管理</li>
          <li class="active">花名册管理</li>
          <%-- <li class="active"><c:if test="${type=='2' }">初筛</c:if><c:if test="${type=='3' }">高危</c:if><c:if test="${type=='4' }">预约随访</c:if>列表-${lccName}</li> --%>
        </ol>
    </div>


	<div id="mainContent">
    	<!-- <div id="marginRight"> -->
        	<div id="select">
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                           		<li> <button type="button" id='uploadExcel' class="btn btn-primary">上传</button> </li>
                            </ol>
                        </fieldset>
                      </form>
                </div>
            </div>
            
        <!-- <div id="marginRight"> -->
        	<div id="select">
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                             	<!-- <li> 
                                    <div class="input-group date" id="duedate1" data-date-format="yyyy-mm-dd" data-date="2014-07-10">
                                        <input id="bg_date" class="form-control input-sm" style="width: 120px;"  required="required" type="text">
                                        <span class="input-group-addon input-sm btn">
                                        <i class="glyphicon glyphicon-calendar "></i>
                                        </span>
                                    </div>
                                </li>
                                <li>
                                    到 <div class="input-group date" id="duedate2" data-date-format="yyyy-mm-dd" data-date="2014-08-10">
                                        <input id="end_date" class="form-control input-sm" style="width: 120px;" required="required" type="text">
                                        <span class="input-group-addon input-sm btn">
                                        <i class="glyphicon glyphicon-calendar "></i>
                                        </span>
                                    </div>
                                </li> -->
                                <li>
                                </li>
                                <li>
                                    <input type="text" class="form-control input-sm" id="file_name" placeholder="文件名称"/>
                                </li>
                            
                             	<!-- <li>
                             	    <input  type="text" style='display:none;' class="form-control input-sm" name="lcc_code" id="lcc_code" value=''/>   
                            	 	<input  class="form-control input-sm" id="lcc_name" name="lcc_name" type="text" placeholder="请输入项目单位" value=""/>
                                </li> -->
                            
                               <li> <button type="button" id='btnQuery' class="btn btn-primary">查询</button> </li>
                           	<!-- 
                           		<li> <button type="button" id='exportQuery' class="btn btn-primary">上传</button> </li>
                             -->
                           
                            </ol>
                        </fieldset>
                      </form>
                </div>
            </div>
            
         <div class="tableBg" style="min-height:600px;">
         
         	<table id="grid"></table>
         	<div id="pager"></div>
         	<!-- <div class="table-responsive" style="padding: 0 80px;">   
             	<table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr class="table-top-nav">
                        <td>时间</td>
                        <td><span id="txt_type"></span>人数(单位:人)</td>
                    </tr>
                </thead>
               <tbody id="tbody">
               </tbody>
              </table>  
         	</div> -->
         	<!-- <div style="color:#f00;font-size:12px;">&nbsp;&nbsp;*注:点击数字可以查看数据的明细&nbsp;(合计的数字不提供查询明细功能)</div>
         	<div style="color:#f00;font-size:12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;“详表导出”将导出全部的数据，与查询条件设定的时间无关，2万条数据平均需要大约10分钟，请耐心等待！</div>			
         	 -->	
         </div>
        </div>
        
        <br style="clear:both;" />
    </div>
    
       	<!-- 弹出框 -->
	    <div id='dialog-showQnInfo' class="modal fade">
		  <div class="modal-dialog">
		    <div class="modal-content">
	    		<div class="modal-header" style="border-bottom: 0px;">
	    			<!-- 
	       			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	      			-->
	      		</div>
		      	<div class="modal-body">
		        	<p>加载中……</p>
		      	</div>
		      	<div class="modal-footer">
					<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
		      	</div>
		    </div><!-- /.modal-content -->
		  </div><!-- /.modal-dialog -->
		</div><!-- /.modal -->
<script>

var o = {};

if ( $('#bg_date').val() != '')
	o.start = $('#bg_date').val();
if ( $('#end_date').val() != '')
	o.end = $('#end_date').val();

if ( $('#file_name').val() != '')
	o.fileName = $('#file_name').val();

var option = {
		url : '${ctx}/pro/pat/historylist',
		postData :o ,
		datatype : 'json',
		mtype : 'POST',
		colNames : [ '文件名', '上传时间', '成功导入数据' ,'操作人', '操作',''],
		colModel : [
			{
				name : 'fileName'
			},
			{
				name : 'createDate',
				formatter:function(cellvalue, option, rowObjects) {
					if ( cellvalue ){
						try{
							var d = new Date();
							d.setTime(parseInt(cellvalue));
							return DateFormat.format(d, 'yyyy-MM-dd hh:mm:ss');
						}catch(e){};
					}
					return '';
				}
			},
			{
				name : 'rowNum'
			},
			{
				name : 'createrName'
			},
			{
				name : 'id',
				sortable : false,
				align : 'center',
				formatter : function(cellvalue, option, rowObjects) {
					return '<a target="_blank" href="./pat/downloadHistory?id='+cellvalue+'">下载文件</a>';
				}
			},{
				name:'id',
				hidden:true
			}
				],
		rowNum : 15,
		rowList : [ 15, 30, 50,100,150,500 ],
		height : "100%",
		autowidth : true,
		pager : '#pager',
		altRows : true,
		hidegrid : false,
		viewrecords : true,
		recordpos : 'left',
		sortname : 'ID',
		sortorder:'desc',
		emptyrecords : "没有可显示记录",
		loadonce : false,
		//multiselect : true,
		loadComplete : function() {
			//var ids = jQuery("#grid").jqGrid('getDataIDs');
		},
		jsonReader : {
			root : "rows",
			page : "page",
			total : "total",
			records : "records",
			repeatitems : false,
			cell : "cell",
			id : "id"
		}
	};

$(function() {
		$("#grid").jqGrid(option);
		//自适应
		jqgridResponsive("grid", false);
		
});

$("#btnQuery").click(function(){
	var vo = {};
	if ( $('#bg_date').val() != '')
		vo.start = $('#bg_date').val();
	if ( $('#end_date').val() != '')
		vo.end = $('#end_date').val();
	
	if ( $('#file_name').val() != '')
		vo.fileName = $('#file_name').val();
	
	//alert(o.end  + o.fileName);
	//option.postData = o ;
	
	$("#grid").jqGrid("setGridParam", {
         postData:vo 
     }).trigger("reloadGrid", [{page:1}]);
	    
	
});


$('#exportQuery').click(function(){
	window.open('./pat/toupload','_self');
});



//excel上传
$("#uploadExcel").click(function(){
	//window.open('./downloadTemplate');
	var timebak = new Date().getTime();
	$("#dialog-showQnInfo").modal({
		 backdrop: 'static'
	});
	$("#dialog-showQnInfo p" ).load("${ctx}/pro/pat/openmodal/toupload?time="+timebak);
});
//关闭excel下载 弹出页面
$('#cancel').click(function(){
	$('#dialog-showQnInfo').modal('hide');
	
	$("#dialog-showQnInfo p").text('加载中……');
});	

</script> 
<script type="text/javascript">



</script>   
</body>
</html>