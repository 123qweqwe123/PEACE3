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
		<li class="active">考核实施</li>
	</ul>

	<div id="select">
        <div class="select-main">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>LCCID:</label></li>
                         <li> 
                         	<input type="text" style="width: 100px" id="lccCode" name="lccCode" class="form-control input-sm"/>
                         </li>
                         <li class="select-one"> <label form='name'>考核起始日期:</label></li>
                         <li> 
                         	<div style="width: 130px" class="input-group date" id="startSDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="startSDate" placeholder="起始日期" name="startSDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                         <li> 
                         	<div  style="width: 130px" class="input-group date" id="endSDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="endSDate" placeholder="结束日期" name="endSDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                         <li class="select-one"> <label form='name'>考核结束日期:</label></li>
                         <li> 
                         	<div style="width: 130px" class="input-group date" id="startEDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="startEDate" placeholder="起始日期" name="startEDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
                         </li>
                         <li> 
                         	<div  style="width: 130px" class="input-group date" id="endEDateDiv" data-date-format="yyyy-mm-dd">
								<input type="text"  id="endEDate" placeholder="结束日期" name="endEDate" class="form-control input-sm"/>
								<span class="input-group-addon input-sm btn">
								<i class="glyphicon glyphicon-calendar "></i>
								</span>
							</div>
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
    
    <div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">考核实施</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<div id='dialog-confirm-wt' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content" style="width: 700px">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">待处理问题</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-confirm-hc' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content" style="width: 700px">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">研究文件核查</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id="ztqkconcel1"  class="btn btn-default btn-sm" >取消</button>
	        <button type="button" id="ztqksubmit1" onclick="ztqksubmit1()" class="btn btn-primary btn-sm" >提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<div id='dialog-confirm-qk' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content" style="width: 700px">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">总体情况</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id="ztqkconcel"  class="btn btn-default btn-sm" >取消</button>
	        <button type="button" id="ztqksubmit" onclick="ztqksubmit()" class="btn btn-primary btn-sm" >提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-confirm-xg' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content" style="width: 700px">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">相关人员</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-delete' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">删除</h4>
	      </div>
	      <div class="modal-body">
	        <p>是否确定删除？</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1002">取消</button>
	        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
    <div id='dialog-confirm-upload' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h4 class="modal-title">上传考核表</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
	      	<button type="button" id ='cancel3' class="btn btn-default btn-sm" tabindex="1003">确定</button>
			<button type="button" id ='cancel5' class="btn btn-default btn-sm" tabindex="1005">取消</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog_show_filename' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">考核表</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel4' class="btn btn-default btn-sm" tabindex="1004">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
    
	<script type="text/javascript">
		function ztqksubmit(){
			var myform = $("#dialog-confirm-qk").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			$("#ztqksubmit").attr("disabled",true);
			$.post("${ctx}/quality/examineImplement/updateZtqk",data,function(result){
				$('#dialog-confirm-qk').modal('hide');
				var message = "操作失败！";
				if(result.success){
					message ='操作成功!';
				}
				$('#ztqksubmit').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		function ztqksubmit1(){
			var myform = $("#dialog-confirm-hc").find("form").serializeArray();
			var data = {};
			$.each(myform, function(i, field){
				data[field.name] = field.value;
			});
			$("#ztqksubmit1").attr("disabled",true);
			$.post("${ctx}/quality/examineImplement/updateZtqk",data,function(result){
				$('#dialog-confirm-hc').modal('hide');
				var message = "操作失败！";
				if(result.success){
					message ='操作成功!';
				}
				$('#ztqksubmit1').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
		$(function(){
			var optionlcc = {
					url : '${ctx}/quality/examineImplement/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '','所属省份','LCCID','医院名称',
					             '考核起始日期','考核结束日期','观摩门诊类型','患者数量',
					             '相关人员','总体情况','研究文件核查','待处理问题','备注'],
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden:true,
								align :'center'
							},{
								name : 'provinceName',
								index : 'provinceName',
								sortable: false,
								align :'center'
							},{
								name : 'lccCode',
								index : 'lcc_Code',
								align :'center'
							},{
								name : 'lccName',
								index : 'lcc_Name',
								align :'left'
							},{
								name : 'implementStartDate',
								index : 'implement_Start_Date',
								formatter:yymmddFormatter,
								align :'center'
							},{
								name : 'implementEndDate',
								index : 'implement_End_Date',
								formatter:yymmddFormatter,
								align :'center'
							},{
								name : 'watchPatientType',
								index : 'watch_Patient_Type',
								align :'center',
								formatter: typeFormatter
							},{
								name : 'patientCount',
								index : 'patient_Count',
								align :'center'
							},{
								name : '',
								index : '',
								align :'center',
								sortable: false,
								formatter: patientFormatter
							},{
								name : '',
								index : '',
								sortable: false,
								formatter:totalQKFormatter,
								align :'center'
							},{
								name : '',
								index : '',
								sortable: false,
								formatter:researchWJFormatter,
								align :'center'
							},{
								name : '',
								index : '',
								sortable: false,
								formatter:todealWTFormatter,
								align :'center'
							},{
								name : 'remark',
								index : 'remark',
								align :'center'
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pager',
					sortname : 'ID',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
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
				}).navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
					.navButtonAdd('#pager',{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
					.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"})
					.navButtonAdd('#pager',{caption:"查看",buttonicon:"ui-icon-search",onClickButton: function(){toLook()},position:"last"})
					.navButtonAdd('#pager',{caption:"打印",buttonicon:"ui-icon-search",onClickButton: function(){toPoint()},position:"last"});

				/* .navButtonAdd('#pager',{caption:"上传考核表",buttonicon:"ui-icon-arrowthickstop-1-n",onClickButton: function(){toUpload()},position:"last"}) */
				//自适应
				jqgridResponsive("grid", false);
				
				//取消按钮操作
				$('#cancel').click(function(){
					$('#dialog-confirm').modal('hide');
				});
				$('#ztqkconcel').click(function(){
					$('#dialog-confirm-qk').modal('hide');
				});
				$('#ztqkconcel1').click(function(){
					$('#dialog-confirm-hc').modal('hide');
				});
				$('#cancel2').click(function(){
					$('#dialog-delete').modal('hide');
				});
				$('#cancel3').click(function(){
					$('#dialog-confirm-upload').modal('hide');
					$("#grid").trigger("reloadGrid");
				});
				$('#cancel4').click(function(){
					$('#dialog_show_filename').modal('hide');
					$("#grid").trigger("reloadGrid");
				});
				$('#cancel5').click(function(){
					$('#dialog-confirm-upload').modal('hide');
					$("#grid").trigger("reloadGrid");
				});
				
		});
		function toLook(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择要操作的记录',2000);
				return;
			}
			window.open("${ctx}/quality/examineImplement/look?id="+id);
			//window.showModalDialog("ceshi.html",null,"dialogHeight=100%,dialogWidth=100%,resizable=no"); 
		}
		function toPoint(){
			toLook();
		}
		function typeFormatter(cellvalue, option, rowObjects){
			if(cellvalue=="1"){
				return "基线";
			}
			if(cellvalue=="2"){
				return "随访";
			}
			return "-";
		}
		//相关人员
		function patientFormatter(cellvalue, option, rowObjects){
			return "<button style='line-height:0.8' class='btn btn-primary btn-align-right btn-sm' onclick='toPatient(\""+rowObjects.id+"\",\""+rowObjects.lccCode+"\")'> 操作</button>";
		}
		function toPatient (id,lccCode){
			var timebak = new Date().getTime();
			openDialogxg("${ctx}/quality/examineImplement/openmodalpatientinput?id="+id+"&lccCode="+lccCode+"&time="+timebak);
		}
		function totalQKFormatter(cellvalue, option, rowObjects){
			return "<button style='line-height:0.8' class='btn btn-primary btn-align-right btn-sm' onclick='toTotalSituation(\""+rowObjects.id+"\",\""+rowObjects.lccCode+"\")'>操作</button>";
		}
		function toTotalSituation(id,lccCode){
			var timebak = new Date().getTime();
			openDialogQK("${ctx}/quality/examineImplement/openmodalsituationinput?id="+id+"&lccCode="+lccCode+"&time="+timebak);
		}
		function researchWJFormatter(cellvalue, option, rowObjects){
			return "<button style='line-height:0.8' class='btn btn-primary btn-align-right btn-sm' onclick='toResearchWJ(\""+rowObjects.id+"\",\""+rowObjects.lccCode+"\")'>操作</button>";
		}
		function toResearchWJ(id,lccCode){
			var timebak = new Date().getTime();
			openDialogHC("${ctx}/quality/examineImplement/openmodalResearchWJinput?id="+id+"&lccCode="+lccCode+"&time="+timebak);
		}
		function todealWTFormatter(cellvalue, option, rowObjects){
			return "<button style='line-height:0.8' class='btn btn-primary btn-align-right btn-sm' onclick='toProblem(\""+rowObjects.id+"\",\""+rowObjects.lccCode+"\")'>操作</button>";
		}
		function toProblem(id,lccCode){
			var timebak = new Date().getTime();
			openDialogWT("${ctx}/quality/examineImplement/openmodalProbleminput?id="+id+"&lccCode="+lccCode+"&time="+timebak);
		}
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
		//弹出新增对话框			
		function toAdd(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/quality/examineImplement/openmodaladdinput?time="+timebak);
		}	
		function toModify(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择要编辑的记录！',2000);
				return;
			}
			var timebak = new Date().getTime();
			openDialog("${ctx}/quality/examineImplement/openmodaladdinput?id="+id+"&time="+timebak);
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
		//相关人员
		function openDialogxg(url,data){
			$( "#dialog-confirm-xg" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-xg p" ).load(url);
		}
		//总体情况
		function openDialogQK(url,data){
			$( "#dialog-confirm-qk" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-qk p" ).load(url);
		}
		//研究文件核查
		function openDialogHC(url,data){
			$( "#dialog-confirm-hc" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-hc p" ).load(url);
		}
		//研究文件核查
		function openDialogWT(url,data){
			$( "#dialog-confirm-wt" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-wt p" ).load(url);
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
		function addDate(data){
			$.post("${ctx}/quality/examineImplement/add",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "新增失败！";
				if(result.success){
					message ='新增成功!';
				}
				$('#do_save').attr("disabled",false);
				showResult(result.success,message);
			},'json');
		}
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
		//弹出删除对话框
		function toDelete(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择要删除的数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",false);
			$("#dialog-delete").modal({
				 keyboard: false
			});
		}
		$('#do_delete').click(function(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择一条数据',2000);
				return;
			}
			$("#do_delete").attr("disabled",true);
			//开始执行删除动作
			$.post("${ctx}/quality/examineImplement/delete", 
					{id :id },
        		function(data){
					$("#grid").trigger("reloadGrid");
					$('#dialog-delete').modal('hide');
					var message = "删除失败！";
        			if(data.success){
        				message = "删除成功！";
        			}
        			showResult(data.success,message);
        		}, 
        		"json");
		});
		
		function toUpload(){
			var id = $("#grid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(id)) {
				openErrorForListPage('请选择一条记录！',2000);
				return;
			}
			var rowData = $("#grid").jqGrid('getRowData', id);
			var timebak = new Date().getTime();
			$( "#dialog-confirm-upload" ).modal({
				 backdrop: 'static'
			});
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-upload p" ).load("${ctx}/quality/examine/openmodaluploadexamineinput?id="+id+"&lccCode="+rowData.lccCode+"&time="+timebak);
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