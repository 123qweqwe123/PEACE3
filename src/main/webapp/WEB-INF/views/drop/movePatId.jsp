<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title></title>
 <script type="text/javascript">
 
 
 String.prototype.verhoeffCheck = (function()
	        {
	    var d = [[0,1,2,3,4,5,6,7,8,9],
	            [1,2,3,4,0,6,7,8,9,5],
	            [2,3,4,0,1,7,8,9,5,6],
	            [3,4,0,1,2,8,9,5,6,7],
	            [4,0,1,2,3,9,5,6,7,8],
	            [5,9,8,7,6,0,4,3,2,1],
	            [6,5,9,8,7,1,0,4,3,2],
	            [7,6,5,9,8,2,1,0,4,3],
	            [8,7,6,5,9,3,2,1,0,4],
	            [9,8,7,6,5,4,3,2,1,0]];
	    var p = [[0,1,2,3,4,5,6,7,8,9],
	            [1,5,7,6,2,8,3,0,9,4],
	            [5,8,0,3,7,9,6,1,4,2],
	            [8,9,1,6,0,4,3,5,2,7],
	            [9,4,5,3,1,2,6,8,7,0],
	            [4,2,8,6,5,7,3,9,0,1],
	            [2,7,9,3,8,0,6,4,1,5],
	            [7,0,4,6,9,1,3,2,5,8]];
	    var j = [0,4,3,2,1,5,6,7,8,9];

	    return function()
	    {
	        var c = 0;
	        if(this.length != 9 && this.length != 11)return false;
	        var reg = /^\d{9,11}$/;
	        if(!reg.test(this))return false;
	        this.replace(/\D+/g,"").split("").reverse().join("").replace(/[\d]/g, function(u, i, o){
	            c = d[c][p[i&7][parseInt(u,10)]];
	        });
	        return (c === 0);
	    };
	})();
 
 </script>
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
        
        .select {
		border-radius: 4px;
		background-color: #f5f5f5;
		border: 1px solid #cccccc;
		color: #404040;
		margin-bottom: 15px
		}
</style>
</head>
<body>
	<div id="navbar">
    	<ol class="breadcrumb">
          <li class="active">系统管理</li>
          <li class="active">条码修改</li>
        </ol>
    </div>


	<div id="main-content">
		<div id="select">
			<div class="select-main">
				<span style="color: red; font-weight: bold; font-size: 18px;">&nbsp;&nbsp;&nbsp;条码一经修改，无法撤销！请慎重考虑后再使用此功能！
				</span>
			</div>
		</div>
		<div class="select">
		<div style="padding:15px;">
			<div class="table-responsive">
			<form id="role_form">
	        	<fieldset>
	        		
		        	<div id="div_drop" class="form-group">
			            <label for="remark">修改条件:</label>
				        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        			条码:
		        			<input type="text" id="drop_id" name="type" class="patientId input" value="" checked />
		            	<div class="form-group">
		            		<table style="margin-left:106px;" width="100%">
		        				<tr>
		        					<td style="width:33%;">
		        						姓名:<span class="txt txt_patientName"></span>
		        					</td>
		        					<td style="width:33%;">
		        						性别:<span class="txt txt_sexTxt"></span>
		        					</td>
		        					<td style="width:33%;">
		        						出生日期:<span class="txt txt_birthdayTxt"></span>
		        					</td>
		        				</tr>
		        				<tr>
		        					
		        				</tr>
		        				<tr>
		        					<td>
		        						身份证:<span class="txt txt_idNumber"></span>
		        					</td>
		        					<td>
		        						初筛条码:<span class="txt txt_patientId"></span>
		        					</td>
		        					<td>
		        						高危条码:<span class="txt txt_riskCode"></span>
		        					</td>
		        				</tr>
		        				<tr>
		        					<td colspan=3>
		        						<table id="dataTable"></table>
		        					</td>
		        				</tr>
		        			</table>
		            	 </div>
		            	 <!-- <button id="bt_drop" type="button" >提交</button> -->
		            </div>
		        </fieldset>
	        </form>
	        </div>
	        </div>
		</div>
		<br style="clear:both;" />
		
		<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content" style="width:700px;">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">请输入数据来源条码</h4>
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
		
    </div>
<script type="text/javascript">
	function formatPercent(v, o, e) {
		if (v) {
			return v + '%';
		}
		return '0%';
	}

	function doQuery() {
		var o = {};
		o.type = $('#sel_type').val();
		o.start = $('#bg_date').val();
		o.end = $('#end_date').val();
		o.lcc = $('#lccCode').val();

		$("#dataTable").jqGrid().setGridParam({
			postData : o
		}).trigger("reloadGrid", [ {
			 
		} ]);
	}

	function getStartDate(str) {
		var year = str.substring(0, 4);
		var week = str.substring(5, str.length);
		var d = new Date();
		d.setFullYear(parseInt(year), 0, 1);
		var w = d.getDay();
		//alert(w);
		d.setFullYear(parseInt(year), 0, (w + 10) % 7);
		return getNewDay(DateFormat.format(d, 'yyyy-MM-dd'),
				parseInt(week) * 7 - 14);
	}
	function getEndDate(str) {
		return getNewDay(getStartDate(str), 6);
	}

	function getNewDay(dateTemp, days) {
		var dateTemp = dateTemp.split("-");
		var nDate = new Date(dateTemp[1] + '-' + dateTemp[2] + '-'
				+ dateTemp[0]); //转换为MM-DD-YYYY格式    
		var millSeconds = Math.abs(nDate) + (days * 24 * 60 * 60 * 1000);
		var rDate = new Date(millSeconds);
		var year = rDate.getFullYear();
		var month = rDate.getMonth() + 1;
		if (month < 10)
			month = "0" + month;
		var date = rDate.getDate();
		if (date < 10)
			date = "0" + date;
		return (year + "-" + month + "-" + date);
	}

	$(function() {

		var uu = window.location.href ;
		 
		if ( uu.indexOf('.47/') > 0 ){
			 uu = uu.replace('.47/' , '.46/');
			 var ww = window.open();
			 ww.location = '${ctx}/portal';
			 
			 alert('系统自动切换到数据服务器！');
			 window.location.href = uu;
			 //window.open(uu , 'newwindow', 'height=1024, width=800, top=0, left=0, toolbar=no, menubar=no, scrollbars=no, resizable=yes,location=n o, status=no');
			 
		 }
		$('#sel_type').change(function() {
			if ($('#sel_type').val() == 'month') {

			}
		});

		var date = new Date();
		date.setDate(date.getDate() - 2);
		var dayBefore2 = DateFormat.format(date, 'yyyy-MM-dd');
		date.setDate(new Date().getDate() - 1);
		var dayBefore1 = DateFormat.format(date, 'yyyy-MM-dd');
		//加载日期控件
		initDatePicker('#duedate1', '#bg_date', '#duedate2', '#end_date',
				function() {
					$("#submitButton").attr('disabled', false);
					$('#alert').hide();
				}, function() {
					$("#submitButton").attr('disabled', true);
				}, '', '');

		var option = {
			url : '${ctx}/sys/drop/listUqs2',
			datatype : 'json',
			postData : {
				'patientId':'null'
			},
			mtype : 'POST',
			//colNames : [ '','时间','协作单位','初筛完成人数','初筛完成率','高危检出人数','高危对象检出率','高危调查完成人数','高危检出完成率','随访预约人数','随访完成人数','随访成功率'],
			colNames : [ '', '条码', '问卷名称', '问卷开始时间',
					'问卷结束时间', '上传时间' , '操作员' , '操作','' ],
			colModel : [
					{
						name : 'code',
						index : 'code',
						hidden : true
					},
					{
						name : 'patientId',
						index : 'patientId',
						width: 80,
						sortable : false,
						align : 'left'
					},
					{
						name : 'itemCode',
						index : 'itemCode',
						width: 150,
						align : 'left',
						sortable : false,
						formatter : function(v, o, e) {
							var txt = '';
							if (v) {
								if ( v == '001001' ){
									txt = '基本信息登记表';
								}else if (v == '001002' ){
									txt = '初筛调查表';
								}else if ( v == '001003' ){
									txt = '高危调查表';
								}else if ( v == '001004' ){
									txt = '高危核查表';
								}else if ( v == '001005' ){
									txt = '干预调查表';
								}else if ( v == '001006' ){
									txt = '随访调查表';
								}
							}
							return v + '-' +txt;
						}
					}, 
					{
						name : 'startTime',
						index : 'startTime',
						width: 150,
						sortable : false,
						formatter : datatimeformat,
						align : 'left'
					},
					
					//{name : 'gwp', index : 'gwp', align:'left',sortable:false,formatter:formatPercent },
					{
						name : 'endTime',
						index : 'endTime',
						sortable : false,
						width: 150,
						formatter : datatimeformat,
						align : 'left'
					},
					//{name : 'gwfp', index : 'gwfp', sortable:false,align:'left',formatter:formatPercent },
					{
						name : 'createDate',
						index : 'createDate',
						sortable : false,
						width: 150,
						formatter : datatimeformat,
						align : 'left'
					}, {
						name : 'uqsOperatorName',
						index : 'uqsOperatorName',
						sortable : false,
						width: 100,
						align : 'left'
					},
					{
						name : 'itemCode',
						index : 'itemCode',
						align : 'center',
						title:false,
						width: 150,
						formatter :function(v , o , e ){
							return '<button type="button" class="dropPatientUqs" itemCode="'+v+'" patientId="'+e['patientId']+'">删除</button>&nbsp;&nbsp;&nbsp;<button type="button" class="copyPatientUqs" itemCode="'+v+'" patientId="'+e['patientId']+'"">复制来源</button>'
						}
					},
					{
						name : 'itemCode',
						index : 'itemCode',
						hidden : true
					}//,
			//{name : 'sfp', index : 'sfp', sortable:false,align:'left',formatter:formatPercent }
			],
			rowNum : 15,
			rowList : [ 15, 30, 50,100,150,500 ],
			height : "100%",
			autowidth : false,
			pager : '#dataTablePager',
			sortname : 'id',
			altRows : true,
			hidegrid : false,
			viewrecords : true,
			recordpos : 'left',
			sortorder : "desc",
			emptyrecords : "没有可显示记录",
			loadonce : false,
			multiselect : false,
			gridComplete:function(){
				$('.dropPatientUqs').on( "click" , function(e){
					var itemCode = $(e.target).attr('itemCode');
					var patientId = $(e.target).attr('patientId');
					if ( confirm('确认删除' + patientId + '的问卷？')){
						dropPatientUqs(itemCode , patientId );
					}
				});
				
				$('.copyPatientUqs').on("click" , function(e){
					var itemCode = $(e.target).attr('itemCode');
					var patientId = $(e.target).attr('patientId');
					copyPatientUqs(itemCode , patientId );
				});
			}
		};
		$("#dataTable").jqGrid(option);
		
		var option3 = option;
		$("#dataTable3").jqGrid(option3);
		
		var option2 = option;
		option2.multiselect = true;
		$("#dataTable2").jqGrid(option2);
		
		function datatimeformat(v , o , e ){
			var date = new Date();
			if ( parseInt(v) ){
				date.setTime( parseInt(v));
				return DateFormat.format(date, 'yyyy-MM-dd hh:mm:ss');
			}
			return '';
		}
		
		
		$('.patientId').keyup(queryPatient);
		
		
		function queryPatientById(id){
			var v = id;
			$('.txt').html('');
			$("#dataTable").jqGrid("clearGridData");
			if ( (v.length == 10 && v.substring(0,1) == 'G') ||( (v.length == 11 && v.substring(0,1) != 'G') )){
				//alert('高危');
				if ( v.replace('G','').verhoeffCheck() ){
					$.ajax({
						url : '${ctx}/sys/drop/query',
						type : 'POST',
						dataType : 'json',
						data : {id:v}
					}).done(function (data, textStatus) {
						if ( data ){
							if ( data.msg){
								$(e.target).val('');
								$('.txt').html('');
								alert(data.msg);
								
							}else{
								for ( var i in data){
									$('.txt_' + i ).html(data[i]);
								}
								
								
							}
						}
					}).fail(function () {
						//alert('error');
					});	
					
					var o = {'patientId':v};
					$("#dataTable").jqGrid().setGridParam({
						postData : o
					}).trigger("reloadGrid", [ {
						 
					} ]);
				}else{
					alert('输入条码格式错误！');		
				}
			}
		}
		
		function queryPatient(e){
			var v = $(e.target).val();
			queryPatientById(v);
		}
		
		function dropPatientUqs(itemCode , patientId ){
			$.ajax({
				url : '${ctx}/sys/drop/dropPatientUqs',
				type : 'POST',
				dataType : 'json',
				data : {itemCode:itemCode , patientId:patientId }
			}).done(function (data, textStatus) {
				if ( data ){
					if ( data.msg){
						alert(data.msg);
					}else{
						alert('操作成功！');
						queryPatientById(patientId);
					}
				}
				
				
			}).fail(function () {
				alert('error');
			});	
		}
		
		function copyPatientUqs(itemCode , patientId ){
			openDialog("${ctx}/sys/drop/openmodalMove?itemCode="+ itemCode +"&patientId=" + patientId);
		}
		
		//弹出对话框
		function openDialog(url,data){
			$( "#dialog-confirm" ).modal({
				 backdrop: 'static'
			});
			$( "#do_save").attr("disabled",false);
			//使用此方法防止js缓存不加载
			$("#dialog-confirm p" ).load(url);
		}
		
		//取消按钮操作
		$('#do_save').click(function(){
			
			var qcs = $('#qcTable').jqGrid('getGridParam','selarrrow');
			var drs = $('#dropTable').jqGrid('getGridParam','selarrrow');
			
			if ( qcs.length + drs.length < 1 ){
				alert('请选择一份需要复制的问卷');
				return;
			}else if (qcs.length + drs.length > 1){
				alert('请只选择一份需要复制的问卷。');
				return;
			}
			
			var drop_date = ''
			if ( drs.length == 1 ){
				drop_date = jQuery('#dropTable').jqGrid('getCell',drs[0],'dropDate');
			}
			
			var str= $('#source_id').val();
			var patientId = $('#form_patientId').val();
			var itemCode = $('#form_itemCode').val();
			
			if ( str == patientId){
	    		alert('复制前后条码相同');
	    		return ;
	    	}
			
			if ((str.indexOf('G') == 0 && str.indexOf('G') != 0) || (str.indexOf('G') != 0 && str.indexOf('G') == 0)){
	    		alert('复制前后条码必需为同一类条码');
	    	}
			
			if (str.replace('G','').verhoeffCheck()){
				$.ajax({
					url : '${ctx}/sys/drop/copyPatientUqs',
					type : 'POST',
					dataType : 'json',
					data : {itemCode:itemCode , patientId:patientId , sourceId:str , dropDate:drop_date }
				}).done(function (data, textStatus) {
					if ( data ){
						if ( data.msg){
							alert(data.msg);
						}else{
							alert('操作成功！');
							queryPatientById(patientId);
							$('#dialog-confirm').modal('hide');
						}
					}
					
					
				}).fail(function () {
					//alert('error');
				});	
				
			}
			
			
		});	
		
		$('#cancel').click(function(){
					$('#dialog-confirm').modal('hide');
		});	
		
		function copyPatientUqs2(itemCode , patientId ){
			var str=prompt("请输入数据来源条码");
		    if(str)
		    {
		    	if ( str == patientId){
		    		alert('复制前后条码相同');
		    		return ;
		    	}
		    	
		    	if ((str.indexOf('G') == 0 && str.indexOf('G') != 0) || (str.indexOf('G') != 0 && str.indexOf('G') == 0)){
		    		alert('复制前后条码必需为同一类条码');
		    	}
		    	
		       if (str.replace('G','').verhoeffCheck()){
		    	   $.ajax({
						url : '${ctx}/sys/drop/copyPatientUqs',
						type : 'POST',
						dataType : 'json',
						data : {itemCode:itemCode , patientId:patientId , sourceId:str }
					}).done(function (data, textStatus) {
						if ( data ){
							if ( data.msg){
								alert(data.msg);
							}else{
								alert('操作成功！');
								queryPatientById(patientId);
							}
						}
						
						
					}).fail(function () {
						//alert('error');
					});	
		       }else{
		    	   
		       }
		    }
		}
		
		
	});
	
	
	
</script>
</body>



</html>