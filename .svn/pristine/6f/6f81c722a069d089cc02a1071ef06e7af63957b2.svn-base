<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>项目管理</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">项目管理</li>
		<li class="active">文件下载</li>
	</ul>
	<div id="select">
        <div class="select-main">
              <form action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>文件名称:</label></li>
                         <li> 
                         <input type="text" id="fileDesc" name="fileDesc" class="form-control input-sm" placeholder="请输入文件名称"/>
                         </li>
                         <!-- <li class="select-one"> <label form='name'>源文件名:</label></li>
                         <li> 
                         <input type="text" id="adjunctName" name="adjunctName" class="form-control input-sm" placeholder="请输入文件名称"/>
                         </li> -->
                         <shiro:hasRole name="超级管理员">
                         <li class="select-one"> <label form='name'>文件类型:</label></li>
                         <li> 
			                <select id="type" name="type" class="form-control input-sm">
								<option value="-1">请选择类别</option>
								<c:forEach items="${typeList }" var="type">
									<option value="${type.typeCode }" <c:if test="${type.typeCode =='1' }"> selected="selected"</c:if> >${type.typeName }</option>
								</c:forEach>
							</select>
                         </li> 
                         </shiro:hasRole>
                        <li> <button type="button" onclick="searchAdjunct();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                    <ol>
                       
                    </ol>
                </fieldset>
              </form>
        </div>
    </div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	<div id="documentJqGrid">
		<table id="documentgrid"></table>
		<div id="pagerDocument"></div>
	</div>

	<script type="text/javascript">
		$(function(){
		
			//document
			var optionDocument = {
					url : '${ctx}/pro/filemgt/listAdjunct',
					datatype : 'json',
					mtype : 'POST',
					postData:{type:'1'},
					colNames : [ '','', '源文件名', '文件名称','文件类型', '上传时间', '下载次数',''],
					colModel : [{
								name : 'adjunctId',
								index : 'adjunct_id',
								hidden : true
							},{
								name : 'projectId',
								index : 'project_id',
								hidden : true
							},{
								name : 'adjunctName',
								index : 'adjunct_name',
								align : 'left',
								hidden : true
							},{
								name:'fileDesc',
								index:'file_desc',
								align:'left'
							},{
								name : 'typeName',
								index : 'type',
								align : 'left'
							},{
								name : 'creatTime',
								index : 'creatTime',
								align : 'left',
								formatter:yymmddFormatter
							},{
								name : 'downCount',
								index : 'down_count'
							},{
								name : 'path',
								index : 'path',
								hidden : true
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pagerDocument',
					sortname : 'adjunct_name',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "desc",
					emptyrecords : "没有可显示记录",
					loadtext:'',
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
						id : "adjunctId"
					}
				};
				$("#documentgrid").jqGrid(optionDocument);
				$("#documentgrid").jqGrid('navGrid', '#pagerDocument', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				}).navButtonAdd('#pagerDocument',{caption:"下载",buttonicon:"ui-icon-arrowthickstop-1-s",onClickButton: function(){downDocument()},position:"last"});
				//自适应
				jqgridResponsive("documentgrid", false);
		});
		var timer;
		function downDocument(){
			var documentId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(documentId)) {
				openError('请选择要下载的文件！',2000);
				return;
			}
			var rowData = $("#documentgrid").jqGrid('getRowData',documentId);
			
			$.post("${ctx}/pro/filemgt/checkFileExists",{projectId:rowData.projectId,adjunctId:rowData.adjunctId,path:rowData.path,adjunctName:rowData.adjunctName},function(result){
				if(result.success){
					location.href="${ctx}/pro/filemgt/downloadDocument?projectId="+rowData.projectId+"&adjunctId="+rowData.adjunctId+
					"&path="+rowData.path+"&adjunctName="+rowData.adjunctName+"&fileDesc="+rowData.fileDesc;
				}else{
					openError('文件已不存在！',2000);
				}
				
			},'json');
			
			//location.href="${ctx}/pro/filemgt/downloadDocument?projectId="+rowData.projectId+"&adjunctId="+rowData.adjunctId+
			//"&path="+rowData.path+"&adjunctName="+rowData.adjunctName;
			
			
			timer =  window.setInterval("flushGrid()",1000);
		}
		function flushGrid(){
			clearInterval(timer);
			for(var i=0;i<6; i++){
				window.setTimeout(function(){
					$("#documentgrid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
				}, 500);
			}
			
		}
		function searchAdjunct(){
		      var postData = $("#documentgrid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var adjunctName =$("#adjunctName").val();
		      var fileDesc =$("#fileDesc").val();
		      var type = $("#type").val();
		      if(fileDesc && ''!=fileDesc){
		          mydata.fileDesc = fileDesc;
		      }else{
		          delete postData.fileDesc;
		      }
		      if(adjunctName && ''!=adjunctName){
		          mydata.adjunctName = adjunctName;
		      }else{
		          delete postData.adjunctName;
		      }
		      if(type!=-1){
		          mydata.type = type;
		      }else{
		          delete postData.type;
		      }
		     $.extend(postData,mydata);
		     $("#documentgrid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		}
		function openError(message,delay){
	    	$('#alert').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert').slideUp("slow");
	    	}, delay);
	    }
	</script>
</body>
</html>