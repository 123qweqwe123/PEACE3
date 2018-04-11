<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>质量管理</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">质量管理</li>
		<li class="active">随机问卷完成统计表</li>
	</ul>

	<div id="select">
        <div class="select-main">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label for='lccName'>医院名称:</label></li>
                         <li> 
                         	<input type="hidden" id="lccCode" name="lccCode" />
                         	<input type="text" style="width: 200px" id="lccName" class="form-control input-sm" placeholder="请输入LCC ID或医院名称"/>
                         </li>
                        <li> <button type="button" onclick="searchExamine();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                        <li> <button type="button" onclick="doExport();" class="btn btn-primary btn-align-right btn-sm">导出</button></li>
                    </ol>
                </fieldset>
        </div>
    </div>

	<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
	<div id="JqGrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>
    
	<script type="text/javascript">
		$(function(){
			$.getJSON("${ctx}/combox/dataLimitLcc",{},function(data) { 
				$('#lccName').autocomplete(data,{
					minChars: 0,
					mustMatch:true,
					width:240,
			        formatItem: function(item,i, max) {
			            return '<table><tr style="align:left"><td width="50px">' + item.LCC_CODE + '</td><td width="180px">' + item.LCC_NAME + '</td></tr></table>';
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.HELP_CODE + item.LCC_NAME+ item.LCC_CODE;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.LCC_NAME;
			        }
				}); 
				//选中 某条记录 触发的事件
				$('#lccName').result(function(event, item){
					if(item){
						$("#lccName").val(item.LCC_NAME);
						$("#lccCode").val(item.LCC_CODE);
					}else{
						$("#lccCode").val("");
					}
				});
			});
			
			var optionlcc = {
					url : '${ctx}/quality/uqscompletetab/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ 'LCCID','医院名称','PID','患者姓名','问卷状态', '随机延迟时间(天)'],
					colModel : [
							{
								name : 'LCCCODE',
								index : 'LCCCODE',
								align :'center',
							},{
								name : 'LCCNAME',
								align :'center',
								sortable: false
							},
							{
								name : 'PATIENTID',
								align :'center',
								sortable: false
							},{
								name : 'PATIENTNAME',
								align :'center',
								sortable: false
							},{
								name : 'STATE',
								align :'center',
								sortable: false
							},{
								name : 'DAYS',
								index : 'DAYS',
								align :'center',
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pager',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortable:true,
					sortname : 'DAYS',
					sortorder : "desc",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					//multiselect : true,
					loadComplete : function() {
					},
					ondblClickRow:function(rowid,iRow,iCol,e){
						toModify();
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
			
				$("#grid").jqGrid(optionlcc);
				
				$("#grid").jqGrid('navGrid', '#pager', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				});

				/* .navButtonAdd('#pager',{caption:"上传考核表",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){toUpload()},position:"last"}) */
				//自适应
				jqgridResponsive("grid", false);
				
		});
		
		function searchExamine() {  
		    var postData = $("#grid").jqGrid("getGridParam", "postData");  
		    var mydata = {};
		    
		    var lccCode =$("#lccCode").val();
		    if(lccCode && ''!=lccCode){
		        mydata.lccCode = lccCode;
		    }else{
		        delete postData.lccCode;
		    }
		    
			$.extend(postData,mydata);
			$("#grid").jqGrid("setGridParam", {
				search: true  
			}).trigger("reloadGrid", [{page:1}]);
		};
		
		function doExport() {
			var postData = $("#grid").jqGrid("getGridParam", "postData");
			var lccCode = $("#lccCode").val();
			if(lccCode == null) {
				lccCode = "";
			}
			var url = "${ctx}/quality/uqscompletetab/export?lccCode="+lccCode + "&sidx=" + postData.sidx +"&sord=" + postData.sord;
			window.open(url);
		}
	</script>
</body>
</html>