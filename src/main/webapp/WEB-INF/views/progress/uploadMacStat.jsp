<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<title></title>
<style>
.txt {
	font-size: 14px;
	font-weight: bold;
}

.percentTxt {
	font-size: 9px;
	font-weight: normal;
	color: #333;
}

.upArrow {
	padding: 0px 20px 0px 0px;
	background-image: url('${ctx}/views/progress/assets/arrowUp1.png');
	background-repeat: no-repeat;
	background-position: 55px 10px;
}

.downArrow {
	padding: 0px 20px 0px 0px;
	background-image: url('${ctx}/views/progress/assets/arrowDown1.png');
	background-repeat: no-repeat;
	background-position: 55px 10px;
}
</style>
</head>
<body>
	<div id="navbar">
		<ol class="breadcrumb">
			<li>质量管理</li>
			<li class="active">上传信息监测</li>
		</ol>
	</div>
	<div id="main-content">
		<div id="select">
        	<div class="select-main">
              <form action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>PID:</label></li>
                         <li> 
                         <input type="text" id="lccCode" name="lccCode" class="form-control input-sm" placeholder="PID"/>
                         </li>
                         <li class="select-one"> <label form='name'>医院名称:</label></li>
                         <li> 
                         <input type="text" id="lccName" name="lccName" class="form-control input-sm" placeholder="医院名称"/>
                         </li>
                         <li class="select-one"> <label form='name'>是否标记:</label></li>
                         <li> 
                          <select id="typeCode" name="typeCode"   class="form-control"> 
                      			<option   value=''  >-请选择-</option>
                      			<option   value='1'  >未标记</option>
                      			<option   value='2'  >已标记</option>
                		 </select>   
                         </li>
                        <li> <button type="button" onclick="searchLcc();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                    <ol>
                       
                    </ol>
                </fieldset>
              </form>
        	</div>
    	</div>
		<div id="jqgrid">
			<table id="grid"></table>
			<div id="pager"></div>
		</div>
		<span style="color:red;"><span style="font-weight:bold;">注:</span>标记为红色的机器近一个月未上传数据,可能为问题机器</span></div>
	</div>
	<script type="text/javascript">
		$(function() {

			var option = {
				url : '${ctx}/progress/getMacStatList',
				postData :{lccName:$('#lccName').val()},
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '医院ID', '医院名称', '上传时间', '机器名称'],
				colModel : [
						{
							name : 'lccCode',
							sortable : false
						},
						{
							name : 'lccName',
							sortable : false
						},
						{
							name : 'uploadDate',
							sortable : false,
							align : 'center',
							formatter : function(cellvalue, option, rowObjects) {
								var timestamp = (new Date()).valueOf(); 
								var _1month=30*24*60*60*1000;//1个月
								var differ=timestamp-cellvalue;
								if(differ>_1month){
									return "<font color='red'>"+getDate(cellvalue)+"</font>";
								}else{
									return getDate(cellvalue);
								}
							}
						},
						{
							name : 'macName',
							sortable : false,
							align : 'center'
						}],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'patient_Id',
				altRows : true,
				hidegrid : false,
				viewrecords : true,
				recordpos : 'left',
				sortorder : "asc",
				emptyrecords : "没有可显示记录",
				loadonce : false,
				//multiselect : true,
				loadComplete : function() {
				},
				jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell",
					id : "patientId"
				}
			};
			$("#grid").jqGrid(option);
			$("#grid").jqGrid('navGrid', '#pager', {
				edit : false,
				add : false,
				del : false,
				search : false,
				position : 'right'
			});
			//自适应
			jqgridResponsive("grid", false);

		});
		function getDate(tm){
			var date = new Date(tm);
			Y = date.getFullYear() + '-';
			M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
			D = date.getDate() + ' ';
			h = date.getHours() + ':';
			m = date.getMinutes() + ':';
			s = date.getSeconds(); 
			return Y+M+D+h+m+s;
		}

		function searchLcc() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var lccName =$("#lccName").val();
		      var lccCode = $("#lccCode").val();
		      var typeCode =$("#typeCode").val();
		      if(lccName && ''!=lccName){
		          mydata.lccName = lccName;
		      }else{
		          delete postData.lccName;
		      }
		      if(lccCode && ''!=lccCode){
		          mydata.lccCode = lccCode;
		      }else{
		          delete postData.lccCode;
		      }
		      if(typeCode && ''!=typeCode){
		          mydata.typeCode = typeCode
		      }else{
		          delete postData.typeCode
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
	</script>
</body>


</html>