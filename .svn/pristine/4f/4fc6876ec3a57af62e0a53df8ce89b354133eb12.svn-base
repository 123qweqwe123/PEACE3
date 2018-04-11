<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<title>样本管理</title> 
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
	  <li class="active">样本管理</li><li class="active" >样本统计</li>   
	</ul>  
	<div id="select">
                <div class="select-main">
                      <form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                             	<li> 
                                    <div class="input-group date" id="duedate1" data-date-format="yyyy-mm-dd" data-date="2014-07-10">
                                        <input id="bg_date" name="startTime" class="form-control input-sm" style="width: 120px;"  required="required" type="text">
                                        <span class="input-group-addon input-sm btn">
                                        <i class="glyphicon glyphicon-calendar "></i>
                                        </span>
                                    </div>
                                </li>
                                <li>
                                   	 到 
                                   	<div class="input-group date" id="duedate2" data-date-format="yyyy-mm-dd" data-date="2014-08-10">
                                        <input id="end_date" name="endTime" class="form-control input-sm" style="width: 120px;" required="required" type="text">
                                        <span class="input-group-addon input-sm btn">
                                        <i class="glyphicon glyphicon-calendar "></i>
                                        </span>
                                    </div>
                                </li>
                                <li>
                                    <select id="area_code" name="areaCode" class="form-control input-sm" onchange='clean_lcc(this)'>
                                        <option value="">请选择省份</option>
                                        <option value="22">吉林</option> 
                                        <option value="21">辽宁</option> 
                                        <option value="33">浙江</option> 
                                        <option value="45">广西</option> 
                                    </select>
                                </li>
                            
                             	<!-- <li>
                             	    <input  type="text" style='display:none;' class="form-control input-sm" name="lcc_code" id="lcc_code" value=''/>   
                            	 	<input  class="form-control input-sm" id="lcc_name" name="lcc_name" type="text" placeholder="请输入项目单位" value=""/>
                                </li> -->
                            
                               <li> <button type="button" id='btnQuery' class="btn btn-primary">查询</button> </li>
                            </ol>
                        </fieldset>
                      </form>
                </div>
            </div>

<div id="jqgrid"">
    <table id="grid"></table>
    <div id="pager"></div>
</div>


<script type="text/javascript">	
var date=new Date();
initDatePicker('#duedate1','#bg_date', '#duedate2','#end_date',  
       function(){
       },
       function(){
       },
       '2015-01-01','');  
$('#duedate1').datetimepicker('setEndDate',  date);
$('#duedate2').datetimepicker('setEndDate',  date); 

$(function(){
	
	var option = {
        url : '${ctx}/spem/scmStatic/list',    
        datatype : 'json',  
        mtype : 'POST',
        colNames : [ '项目单位','总数','中心存放','当地存放','总数','中心存放','当地存放','废管数量'], //fridge_name
        colModel : [ 
                      
                     {name : 'lccName', index : 'lccName', align:'left' },
                     {name : 'boxCount_total', index : 'boxCount_total', align:'center',sortable: false },
                     {name : 'boxCount_center', index : 'boxCount_center', align:'center',sortable: false },
                     {name : 'boxCount_lcc', index : 'boxCount_lcc', align:'center',sortable: false },
                     {name : 'tubeCount_total', index : 'tubeCount_total', align:'center',sortable: false },
                     {name : 'tubeCount_center', index : 'tubeCount_center', align:'center',sortable: false },
                     {name : 'tubeCount_lcc', index : 'tubeCount_lcc', align:'center',sortable: false },
                     {name : 'wasteTubeCount', index : 'wasteTubeCount', align:'center',sortable: false }
                   ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'lccName',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "ASC",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        multiselect: false
       /*  ,loadComplete: Gosub */
 	};  
	$("#grid").jqGrid(option);
	$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
	jqgridResponsive("grid",false); 
	$("#grid").jqGrid('setGroupHeaders', {
        useColSpanStyle : true, 
        groupHeaders : [                        
        {
            startColumnName : 'boxCount_total',
            numberOfColumns : 3,
            titleText : '冻存盒'
        },{
            startColumnName : 'tubeCount_total',
            numberOfColumns : 4,
            titleText : '冻存管'
        }]
    });

	$("#btnQuery").click(multipleSearch);
});

</script>


</body>
</html>