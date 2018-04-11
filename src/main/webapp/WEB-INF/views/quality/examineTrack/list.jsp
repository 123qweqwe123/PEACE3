<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>考核实施</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">质量管理</li>
		<li class="active">考核追踪</li>
	</ul>

	<div id="select">
        <div class="select-main">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>LCCID:</label></li>
                         <li> 
                         	<input style="width: 140px" type="text" id="lccCode" name="lccCode" class="form-control input-sm"/>
                         </li>
                         <li class="select-one"> <label form='name'>考核起始日期:</label></li>
                         <li> 
                         	<div style="width: 130px" class="input-group date" id="startSDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="startSDate" placeholder="起始日期" name="startDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                         <li> 
                         	<div  style="width: 130px" class="input-group date" id="endSDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="endSDate" placeholder="结束日期" name="startDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                         <li class="select-one"> <label form='name'>考核结束日期:</label></li>
                         <li> 
                         	<div style="width: 130px" class="input-group date" id="startEDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="startEDate" placeholder="起始日期" name="startDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                         <li> 
                         	<div  style="width: 130px" class="input-group date" id="endEDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="endEDate" placeholder="结束日期" name="startDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                         <div style="clear: both; margin-top: 10px"></div>
                         <li class="select-one">事件状态:</li>
                         <li> 
                         	<select id="status"  style="height: 30px;width: 100px;padding: 5px 10px;font-size: 12px;line-height: 1.5;border-radius: 3px;">
                         		<option value="">全部</option>
                         		<option value="1">开放</option>
                         		<option value="2">关闭</option>
                         	</select>
                         </li>
                        <li> <button type="button" onclick="searchExamine();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
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
			var optionlcc = {
					url : '${ctx}/quality/examineImplementTrack/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '','','','所属省份','LCCID','医院名称',
					             '考核起始日期','考核结束日期','观摩门诊类型','患者数量','待解决人员','问题分类','问题描述','事件状态','增加时间'],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden:true,
								align :'center'
							},{
								name : 'lccCode',
								index : 'lccCode',
								hidden:true
							},{
								name: 'lccUserId',
								index:'lccUserId',
								hidden:true
							},{
								name : 'provinceName',
								index : 'provinceName',
								align :'center'
							},{
								name : 'lccCode',
								index : 'lccCode',
								align :'center'
							},{
								name : 'lccName',
								index : 'lccName',
								align :'center'
							},{
								name : 'implementStartDate',
								index : 'implementStartDate',
								formatter:yymmddFormatter,
								align :'center'
							},{
								name : 'implementEndDate',
								index : 'implementEndDate',
								formatter:yymmddFormatter,
								align :'center'
							},{
								name : 'watchPatientType',
								index : 'watchPatientType',
								align :'center',
								formatter: ptypeFormatter
							},{
								name : 'patientCount',
								index : 'patientCount',
								align :'center'
							},{
								name : 'lccUserName',
								index : 'lccUserName',
								align :'center'
							},{
								name : 'problemType',
								index : 'problemType',
								width:100,
								align :'center',
								formatter: problemFormatter
							},{
								name : 'description',
								index : 'description',
								align :'center'
							},{
								name : 'status',
								index : 'status',
								formatter:typeFormatter,
								align :'center'
							},{
								name : 'createDate',
								index : 'createDate',
								formatter:yymmddFormatter,
								width:90,
								align :'center',
								hidden:true
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pager',
					sortname : 'EXAMINE_DATE',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "desc",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					/* multiselect : true, */
					loadComplete : function() {
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
				}).navButtonAdd('#pager',{caption:"开放事件状态",buttonicon:"ui-icon-ok",onClickButton: function(){toUpate("1")},position:"last"})
					.navButtonAdd('#pager',{caption:"关闭事件状态",buttonicon:"ui-icon-cancel",onClickButton: function(){toUpate("2")},position:"last"});
				//自适应
				jqgridResponsive("grid", false);
		});
		$('#startEDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#startEDateDiv').datepicker('hide');
		});
		$('#startSDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#startSDateDiv').datepicker('hide');
		});
		function ptypeFormatter(cellvalue, option, rowObjects){
			if(cellvalue="1"){
				return "基线";
			}
			if(cellvalue="2"){
				return "随访";
			}
			return "-";
		}
		function problemFormatter(cellvalue, option, rowObjects){
			if(cellvalue=='1'){
				return '患者管理';
			}
			if(cellvalue=='2'){
				return '文件管理';
			}
			if(cellvalue=='3'){
				return '经费管理';
			}
			if(cellvalue=='4'){
				return '设备管理';
			}
			return '-';
		}
		function typeFormatter(cellvalue, option, rowObjects){
			if(cellvalue=='1'){
				return '开放';
			}
			if(cellvalue=='2'){
				return '关闭';
			}
			return '-';
		}
		$('#endSDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#endSDateDiv').datepicker('hide');
		});
		$('#endEDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#endEDateDiv').datepicker('hide');
		});
		function toModify(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/quality/examineImplement/openmodaladdinput?id="+id+"&time="+timebak);
		}
		function toUpate(status){
			var data={};
			data.status = status;
			var id = $("#grid").jqGrid('getGridParam','selrow');
			data.id = id;
			$.post("${ctx}/quality/examineImplementTrack/updateStatus",data,function(result){
				$("#grid").trigger("reloadGrid");
				var message = "事件状态修改失败！";
				if(result.success){
					message = "事件状态修改成功！";
				}
				showResult(result.success,message);
			},'json');
		}
		function searchExamine() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var lccCode =$("#lccCode").val();
		      if(lccCode && ''!=lccCode){
		          mydata.lccCode = lccCode;
		      }else{
		          delete postData.lccCode;
		      }
		      var startSDate = $("#startSDate").val();
		      if(startSDate && ''!=startSDate){
		          mydata.startSDate = startSDate;
		      }else{
		          delete postData.startSDate;
		      }
		      var endSDate = $("#endSDate").val();
		      if(endSDate && ''!=endSDate){
		          mydata.endSDate = endSDate;
		      }else{
		          delete postData.endSDate;
		      }
		      var startEDate = $("#startEDate").val();
		      if(startEDate && ''!=startEDate){
		          mydata.startEDate = startEDate;
		      }else{
		          delete postData.startEDate;
		      }
		      var endEDate = $("#endEDate").val();
		      if(endEDate && ''!=endEDate){
		          mydata.endEDate = endEDate;
		      }else{
		          delete postData.endEDate;
		      }
		      var status = $("#status").val();
		      if(status && ''!=status){
		          mydata.status = status;
		      }else{
		          delete postData.status;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
		//弹出对话框
		function openDialog(url,data){
			$( "#dialog-confirm" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm p" ).load(url);
		}
		$('#do_save').click(function(){
			var myform = $("#dialog-confirm").find("form").get(0);
			if(!jQuery(myform).validate().form()){ return ;}
			
			var myform = $("#dialog-confirm").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			$("#do_save").attr("disabled",true);
			if(data.id == ""){addDate(data);}
			else{updateDate(data);}
		});
		function updateDate(data){
			$.post("${ctx}/quality/examineImplement/update",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "更新失败！";
				if(result.success){
					message = "更新成功！";
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		function showFileName(examineId){
			var timebak = new Date().getTime();
			$( "#dialog_show_filename").modal({
				 backdrop: 'static'
			});
			//使用此方法防止js缓存不加载
			$("#dialog_show_filename p").load("${ctx}/quality/examine/openmodalshowfilenameinput?examineId="+examineId+"&time="+timebak);
		}
	    function openError(message,delay){
	    	$('#alert').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert').slideUp("slow");
	    	}, delay);
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
		function openErrorForListPage(message,delay){
	    	$('#alert').show().find('strong').text(message);
	    	window.setTimeout(function() {
	    		$('#alert').slideUp("slow");
	    	}, delay);
	    }
	</script>
</body>
</html>