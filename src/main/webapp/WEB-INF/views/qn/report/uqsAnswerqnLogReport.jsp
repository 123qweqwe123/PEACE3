<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
<title>问卷完成情况报表</title>
<script type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">问卷完成情况报表</li>
      </ul>
</div>
	<div id="alert" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>
	<div id="message" class="alert alert-success" hidden>
	    <button data-dismiss="alert" class="close">&times;</button>
	    <span id="messageSpanId"></span>
	</div>
	<div id="select">  
       <div class="select-main">
             <form action="" method="post" class="well-work bs-adp form-inline">
      			<fieldset>
               	<ol>
                        <li class="select-one"> <label form=name>医院名称:</label></li>
                        <li> 
                        	  	<input type="hidden" id="lccCode"  />
              					<input class="form-control input-sm" id="lccName"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
                        </li>
                        <li class="select-one"> <label form=name>查询一段时间的问卷完成情况:</label></li>
                        <li> 
								<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"  id="startDate" placeholder="起始日期" name="startDate" class="form-control input-sm"/>
								<input style="margin-left: 5px;" type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"  id="endDate" placeholder="结束日期" name="endDateDate" class="form-control input-sm"/>
                        </li>
                        <li> <button type="button" onclick="doQuery();" class="btn btn-primary btn-sm">查询</button></li>
                   		<li> <button type="button" onclick="doExport();" class="btn btn-primary btn-sm">导出</button></li>
                   </ol>
               </fieldset>
             </form>
       </div>
	<div id="jqgrid">
	    <table id="grid"></table>
	    <div id="pager"></div>
	</div>
	<!-- <div id="jqgrid2">
	    <table id="grid2"></table>
	</div> -->
	<script type="text/javascript">
	function doExport(){
		var str = "?1=1";
		var lccCode = $("#lccCode").val();
		if(lccCode !=null && lccCode !=''){
			str = str+"&lccCode="+lccCode
		}
		var startDate = $.trim($("#startDate").val());
		if(startDate !=null && startDate !=''){
			str = str + "&startDate="+startDate;
		}
		var endDate = $.trim($("#endDate").val());
		if(endDate !=null && endDate !=''){
			str = str + "&endDate="+endDate;
		}
		window.open('${ctx}/qn/uqsAnswerqnLogReportExport'+str);
	}
	function doQuery(){
		var data = {};
		var postData = $("#grid").jqGrid("getGridParam", "postData");  
		var lccCode = $("#lccCode").val();
		if(lccCode !=null && lccCode !=''){
			data.lccCode = lccCode;
		}else{
			delete postData.lccCode;
		}
		var startDate = $.trim($("#startDate").val());
		if(startDate !=null && startDate !=''){
			data.startDate = startDate;
		}else{
			delete postData.startDate;
		}
		var endDate = $.trim($("#endDate").val());
		if(endDate !=null && endDate !=''){
			data.endDate = endDate;
		}else{
			delete postData.endDate;
		}
		$.extend(postData,data);
		$("#grid").jqGrid("setGridParam", {
			search: true  
		}).trigger("reloadGrid", [{page:1}]);
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
		        url : '${ctx}/qn/uqsAnswerqnLogReportList',
		        datatype : 'json',  
		        mtype : 'POST',
		        colNames : [ 'LCCID','医院名称','应随访人数','死亡人数','首次随访','未随访人数','干预','非干预','电话随访',
					'干预','非干预'],
		        colModel : [
		                    {name : 'LCC_CODE', index : 'LCC_CODE', align:'center'},
					        {name : 'LCC_NAME', index : 'LCC_NAME', align:'left',width:200},
					        {name : 'YSFRS', index : 'YSFRS', align:'center',width:100,formatter :reValue},
					        {name : 'SWRS', index : 'SWRS', align:'center',width:100,formatter :reValue},
		                    {name : 'SCSF', index : 'SCSF', align:'center',width:100,formatter :reValue},
		                    {name : 'SCSFW', index : 'SCSFW',hidden:true, align:'center',width:100,formatter :reValue},
//		                    {name : 'LYSF', index : 'LYSF', align:'center',
//                                formatter:function(value, options, rowObjects){
//		            var row1 = "",row2="";
//                                    var style = "style='height:16px;width:30px;text-align:center;" +
//                                        "border-right-width:0px;'";
//                                            row1 += "<td "+style+">干预</td>";
//                                            row1 += "<td "+style+">非干预</td>";
//                                            row1 += "<td "+style+">电话随访</td>";
//                                            row2 += "<td "+style+">"+rowObjects.GY+"</td>";
//                                            row2 += "<td "+style+">"+rowObjects.FGY+"</td>";
//                                            row2 += "<td "+style+">"+rowObjects.UQSMOBILE+"</td>";
//                                    return "<table><tr>"+row1+"</tr><tr>"+row2+"</tr></table>"
//                                }
//                            },
                    {name : 'GY', index : 'GY', align:'center'},
                    {name : 'FGY', index : 'FGY', align:'center'},
                    {name : 'UQSMOBILE', index : 'UQSMOBILE', align:'center'},

//                    {name : 'MCSF', index : 'MCSF', align:'center',width:100,
//                                formatter:function(value, options, rowObjects){
//                                    var row1 = "",row2="";
//                                    var style = "style='height:16px;width:30px;text-align:center;" +
//                                        "border-right-width:0px;'";
//                                    row1 += "<td "+style+">干预末次</td>";
//                                    row1 += "<td "+style+">非干预末次</td>";
//                                    row2 += "<td "+style+">"+rowObjects.GYMC+"</td>";
//                                    row2 += "<td "+style+">"+rowObjects.FGYMC+"</td>";
//                                    return "<table><tr>"+row1+"</tr><tr>"+row2+"</tr></table>"
//                                }
//                    }

                    {name : 'GYMC', index : 'GYMC', align:'center'},
                    {name : 'FGYMC', index : 'FGYMC', align:'center'}

                ],
		        rowNum : 100, 
		        rowList : [ 100,500 ],      
		        height : "100%",
		        autowidth : true,  
		        pager : '#pager',  
		        sortname : 'LCC_CODE',
		        altRows:true, 
		        hidegrid : false, 
		        viewrecords : true, 
		        recordpos : 'left', 
		        sortorder : "ASC",
		        emptyrecords : "没有可显示记录", 
		        loadonce : false,
		        multiselect: false,
		        jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell"
				}
		 	};  
			$("#grid").jqGrid(option); 
			$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
			jqgridResponsive("grid",false);
            $( "#grid" ).jqGrid( 'setGroupHeaders' , {
                useColSpanStyle :  true ,  // 没有表头的列是否与表头列位置的空单元格合并
                groupHeaders : [
                    {
                    startColumnName:'GY' ,  // 对应colModel中的name
                    numberOfColumns:3,  // 跨越的列数
                    titleText:'6月随访'
                    },
                    {
                        startColumnName:'GYMC',
                        numberOfColumns:2,
                        titleText:'末次随访'
                    }

                ]
            });
		});
		function reValue(cellvalue, option, rowObjects) {
			if(cellvalue==0){
				return '';
			}
			else{
				return cellvalue;
			}
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
	</script>

</body>

</html>