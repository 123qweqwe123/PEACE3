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
          <li class="active">进度管理</li>
          <li class="active">项目进度详情</li>
        </ol>
    </div>


<div id="main-content">
    	 <div id="select">
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                            	 <li class="select-one"> <label form=name>开始时间:</label></li>
                                 <li> 
                                 
                                 <div class="input-group date" id="duedate1">
				                 <input class="form-control input-sm"  id="bg_date" name="bg_date" type="text" style="width:100px;" />
				                <span class="input-group-addon input-sm btn">
				                <i class="glyphicon glyphicon-calendar"></i>
				                </span>
				                  </div>
                                 </li>
                                 <li class="select-one"> <label form=name>结束时间:</label></li>
                                 <li> 
                                 	<div class="input-group date"  id="duedate2">
					                  <input class="form-control input-sm" id="end_date" name="end_date" type="text" style="width:100px;" />
					                <span class="input-group-addon input-sm btn">
					                <i class="glyphicon glyphicon-calendar"></i>
					               </span>
					               </div>
                                  </li>
                                 
                                 <li class="select-one">
                                 	<label form=name>协作单位:</label>
                                 </li>
                                 <li>
                                 	<select id="lccCode" name="lccCode" class="form-control input-sm">
										<option value="">请选择协作单位</option>
										<c:forEach items="${lccDictList }" var="lccDict">
											<option value="${lccDict.lccCode }" ${lccUser.lccCode == lccDict.lccCode ? 'selected="selected"' : '' }>${lccDict.lccName }</option>
										</c:forEach>
									</select>
								 </li>
                                 <li> <select id="sel_type" class="form-control input-sm" style="height:30px;width:80px">
                                 			<option value="day">按天</option>
                                 			<option value="week">按周</option>
                                 			<option value="month">按月</option>
                                 		</select> 
                                 </li>
                                <li> <button type="button" onclick="doQuery();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                            </ol>
                            <ol>
                               
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

function formatPercent(v,o,e){
	 if ( v ){
		 return v + '%';
	 }
	 return '0%';
 }

function doQuery(){
	var o = {};
	o.type = $('#sel_type').val();
	o.start = $('#bg_date').val();
	o.end = $('#end_date').val();
	o.lcc = $('#lccCode').val();
	
	$("#dataTable").jqGrid()
    .setGridParam({
        postData : o
    })
    .trigger("reloadGrid", [{
                 
            }
        ]);
}

function getStartDate(str ){
	var year = str.substring(0,4);
	var week = str.substring(5,str.length);
	var d = new Date();
	d.setFullYear(parseInt(year), 0, 1);
	var w = d.getDay();
	//alert(w);
	d.setFullYear(parseInt(year), 0, (w + 10) % 7 );
	return getNewDay( DateFormat.format(d,'yyyy-MM-dd') , parseInt(week) * 7 - 14 )  ;
}
function getEndDate(str ){
	return getNewDay( getStartDate(str) ,  6 ) ;
}

function getNewDay(dateTemp, days) {  
    var dateTemp = dateTemp.split("-");  
    var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-' + dateTemp[0]); //转换为MM-DD-YYYY格式    
    var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);  
    var rDate = new Date(millSeconds);  
    var year = rDate.getFullYear();  
    var month = rDate.getMonth() + 1;  
    if (month < 10) month = "0" + month;  
    var date = rDate.getDate();  
    if (date < 10) date = "0" + date;  
    return (year + "-" + month + "-" + date);  
} 

$(function(){ 
	
	$('#sel_type').change(function(){
		if ( $('#sel_type').val() == 'month' ){
			
		}
	});
	
	
	var date=new Date();
	date.setDate(date.getDate()-2);
	var dayBefore2 = DateFormat.format(date, 'yyyy-MM-dd');
	date.setDate(new Date().getDate()-1);
	var dayBefore1 = DateFormat.format(date,'yyyy-MM-dd');
	//加载日期控件
	initDatePicker(
	        '#duedate1','#bg_date',
	        '#duedate2','#end_date',  
	        function(){
	           $("#submitButton").attr('disabled',false);
	           $('#alert').hide();
	        },
	       function(){
	           $("#submitButton").attr('disabled',true);               
	       },'','');
	
	var option = {
	        url : '${ctx}/progress/getProgressList',   
	        datatype : 'json', 
	        postData :{type:'day',start:$('#bg_date').val(),end:$('#end_date').val(),lcc:$('#lccCode').val()},
	        mtype : 'POST',
	        //colNames : [ '','时间','协作单位','初筛完成人数','初筛完成率','高危检出人数','高危对象检出率','高危调查完成人数','高危检出完成率','随访预约人数','随访完成人数','随访成功率'],
	        colNames : [ '','时间','协作单位','初筛完成人数','高危检出人数','高危调查完成人数','随访预约人数','随访完成人数'],
	        colModel : [ 
	                     {name : 'code',index : 'code',hidden : true},
	                     {name : 'date', index : 'date', align:'left',sortable:false,formatter:function(v,o,e){
	                    	 var txt = '';
	                    	 if ( v ){
	                    		 if ( $('#sel_type').val() == 'day' ){
	                    			 txt = v;
	                    		 }
								if ( $('#sel_type').val() == 'week' ){
									var tt = getStartDate(v) + ' ~ ' + getEndDate(v);
 									
									txt = '<span title="'+tt+'">'+v.replace('-', '年 第') + '周</span>';	                    			 
								}
								if ( $('#sel_type').val() == 'month' ){
									txt = v.replace('-', '年 第') + '月';	  
								}
	                    	 }
	                    	 return txt;
	                     } },
	                     {name : 'name', index : 'name', sortable:false,align:'left' },
	                     {name : '2', index : '2',sortable:false, align:'left' },
	                     //{name : 'csp', index : 'csp', align:'left',sortable:false,formatter:formatPercent}, 
	                     {name : 'risk', index : 'risk', sortable:false,align:'left' },
	                     //{name : 'gwp', index : 'gwp', align:'left',sortable:false,formatter:formatPercent },
	                     {name : '3', index : '3', sortable:false,align:'left' },
	                     //{name : 'gwfp', index : 'gwfp', sortable:false,align:'left',formatter:formatPercent },
	                     {name : 'order', index : 'order', sortable:false,align:'left' },
	                     {name : '4', index : '4', sortable:false,align:'left' }//,
	                     //{name : 'sfp', index : 'sfp', sortable:false,align:'left',formatter:formatPercent }
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