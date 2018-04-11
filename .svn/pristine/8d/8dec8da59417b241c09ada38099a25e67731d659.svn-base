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
	        		<div class="form-group">
		            	<label for="name">修改方式:</label>
		            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        			<input type="radio" id="radio_1" name="type" class="type input" value="1" checked />清空 
			        		
			        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        		<input type="radio" id="radio_2" name="type" class="type input" value="2"  />替换
		            		
		            </div>
		        	<div id="div_drop" class="form-group">
			            <label for="remark">修改条件:</label>
				        
		        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        			需要清空的条码:
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
		            	 <button id="bt_drop" type="button" >提交</button>
		            </div>
		            
		            <div  id="div_change" class="form-group">
			            <label for="remark">修改条件:</label>
				        
		        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        			需要修改的条码:
		        			<input type="text" id="source_id" name="type" class="patientId input" value="" checked />
		        		<div class="form-group">
		            	 	<table style="margin-left:106px;" width="100%">
		        				<tr>
		        					<td>
		        						姓名:<span class="txt txt_patientName"></span>
		        					</td>
		        					<td>
		        						性别:<span class="txt txt_sexTxt"></span>
		        					</td>
		        					<td>
		        						出生日期:<span class="txt txt_birthdayTxt"></span>
		        					</td>
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
		        						<table id="dataTable3"></table>
		        					</td>
		        				</tr>
		        			</table>
		        			
		            	 </div>
		            	 <div class="form-group">
		        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        			修改后的条码:&nbsp;&nbsp;&nbsp;&nbsp;
		        			<input type="text" id="target_id" name="type" class=" input" value="" checked />
		        		</div>	
		            	 <div class="form-group">
		        			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        			是否从原条码复制:
		        			<input type="radio" id="radio_3" name="isnew" class="isnew input" value="1" checked />是 
			        		
			        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			        		<input type="radio" id="radio_4" name="isnew" class="isnew input" value="2"  />否
			        	</div>
			        	<div id="div_other" class="form-group">
			        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        			复制内容来源条码:
		        			<input type="text" id="other_id" name="type" class=" input" value="" checked />
			        	</div>
			        	<div class="other form-group">
		            	 	<table style="margin-left:106px;" width="100%">
		        				<tr>
		        					<td>
		        						姓名:<span class="txt txt_patientName"></span>
		        					</td>
		        					<td>
		        						性别:<span class="txt txt_sexTxt"></span>
		        					</td>
		        					<td>
		        						出生日期:<span class="txt txt_birthdayTxt"></span>
		        					</td>
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
		        						<table id="dataTable2"></table>
		        					</td>
		        				</tr>
		        			</table>
		        			
		            	 </div>
		            	<button id="bt_change" type="button">提交</button>
		            </div>
	        	</fieldset>
	        </form>
	        </div>
	        </div>
		</div>
		<br style="clear:both;" />
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
			url : '${ctx}/sys/drop/listUqs',
			datatype : 'json',
			postData : {
				'patientId':'null'
			},
			mtype : 'POST',
			//colNames : [ '','时间','协作单位','初筛完成人数','初筛完成率','高危检出人数','高危对象检出率','高危调查完成人数','高危检出完成率','随访预约人数','随访完成人数','随访成功率'],
			colNames : [ '', '条码', '问卷名称', '问卷开始时间',
					'问卷结束时间', '上传时间' , '操作员' , '' ],
			colModel : [
					{
						name : 'code',
						index : 'code',
						hidden : true
					},
					{
						name : 'patientId',
						index : 'patientId',
						sortable : false,
						align : 'left'
					},
					{
						name : 'itemCode',
						index : 'itemCode',
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
					}, {
						name : 'startTime',
						index : 'startTime',
						sortable : false,
						formatter : datatimeformat,
						align : 'left'
					},
					
					//{name : 'gwp', index : 'gwp', align:'left',sortable:false,formatter:formatPercent },
					{
						name : 'endTime',
						index : 'endTime',
						sortable : false,
						formatter : datatimeformat,
						align : 'left'
					},
					//{name : 'gwfp', index : 'gwfp', sortable:false,align:'left',formatter:formatPercent },
					{
						name : 'createDate',
						index : 'createDate',
						sortable : false,
						formatter : datatimeformat,
						align : 'left'
					}, {
						name : 'uqsOperatorName',
						index : 'uqsOperatorName',
						sortable : false,
						align : 'left'
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
			multiselect : false
		};
		$("#dataTable").jqGrid(option);
		
		var option3 = option;
		$("#dataTable3").jqGrid(option3);
		
		var option2 = option;
		option2.multiselect = true;
		$("#dataTable2").jqGrid(option2);
		
		function datatimeformat(v , o , e ){
			var date = new Date();
			date.setTime( parseInt(v));
			return DateFormat.format(date, 'yyyy-MM-dd hh:mm:ss');
		}
		
		$('.type').click(function(e){
			//alert( e.target.id );
			if ( e.target.id == 'radio_1' ){
				$('#div_drop').show();
				$('#div_change').hide();
			}else{
				$('#div_drop').hide();
				$('#div_change').show();
			}
		});
		
		$('.isnew').click(function(e){
			//alert( e.target.id );
			if ( e.target.id == 'radio_4' ){
				$('#div_other').show();
				
			}else{
				$('#div_other').hide();
				$('#other_id').val('');
			}
		});
		
		$('#div_drop').show();
		$('#div_change').hide();
		$('#div_other').hide();
		
		
		$('.patientId').keyup(queryPatient);
		//$('.patientId').change(queryPatient);
		
		$('#other_id').keyup(queryPatient2);
		
		function queryPatient(e){
			var v = $(e.target).val();
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
								
								var o = {'patientId':v};
								
								$("#dataTable").jqGrid().setGridParam({
									postData : o
								}).trigger("reloadGrid", [ {
									 
								} ]);
								
								//alert($("#radio_4").is(':checked'));
								if ( $("#radio_3").is(':checked') ){
									$("#dataTable2").jqGrid().setGridParam({
										postData : o
									}).trigger("reloadGrid", [ {
										 
									} ]);
								}else{
									queryPatient3($('#other_id').val());
								}
								
								$("#dataTable3").jqGrid().setGridParam({
									postData : o
								}).trigger("reloadGrid", [ {
									 
								} ]);	
							
								
							}
						}
						
						
					}).fail(function () {
						//alert('error');
					});	
				}else{
					alert('输入条码格式错误！');		
				}
			}
		}
		
		function queryPatient3(id){
			var v = id;
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
									$('.other .txt_' + i ).html(data[i]);
								}
								
								var o = {'patientId':v};
								
								$("#dataTable2").jqGrid().setGridParam({
									postData : o
								}).trigger("reloadGrid", [ {
									 
								} ]);
							}
						}
						
						
					}).fail(function () {
						//alert('error');
					});	
				}else{
					alert('输入条码格式错误！');		
				}
			}
		}
		
		function queryPatient2(e){
			var v = $(e.target).val();
			queryPatient3(v);
		}
		
		$('#bt_drop').click(function(){
			var patid =  $('#drop_id').val();
			if ( patid == ''){
				alert('请输入需要清空的条码');
				return ;
			}
			
			var c = confirm('需要修改的条码'+patid+'将清空，是否继续？');
			if ( c == false )
				return ;
			
			if ( patid.replace('G','').verhoeffCheck()){
				$.ajax({
					url : '${ctx}/sys/drop/dropId',
					type : 'POST',
					dataType : 'json',
					data : {patientId:patid}
				}).done(function (data, textStatus) {
					if ( data ){
						if ( data.msg ){
							alert(data.msg);
						}else{
							alert(patid+ "条码已经清空成功！");
							window.location.href = '${ctx}/sys/drop';
						}
					}
				}).fail(function () {
					//alert('error');
				});	
			}else{
				alert('条码格式错误！');
			}	
		});
		
		$('#radio_3').click(function(){
			var v = $('#source_id').val();
			$.ajax({
				url : '${ctx}/sys/drop/query',
				type : 'POST',
				dataType : 'json',
				data : {id:v}
			}).done(function (data, textStatus) {
				if ( data ){
					if ( data.msg){
						//$(e.target).val('');
						$('.txt').html('');
						alert(data.msg);
						
					}else{
						for ( var i in data){
							$('.other .txt_' + i ).html(data[i]);
						}
						
						var o = {'patientId':v};
						
						$("#dataTable2").jqGrid().setGridParam({
							postData : o
						}).trigger("reloadGrid", [ {
							 
						} ]);
					}
				}
				
				
			}).fail(function () {
				//alert('error');
			});	
		});
		
		$('#bt_change').click(function(){
			
			var patid =  $('#source_id').val();
			if ( patid == ''){
				alert('请输入需要修改的条码');
				$('#source_id').focus();
				return ;
			}
			
			if ( !patid.replace('G','').verhoeffCheck() ){
				alert('条码格式错误！');
				$('#source_id').val('');
				$('#source_id').focus();
				return ;
			}
			
			var target =  $('#target_id').val();
			if ( target == ''){
				alert('请输入修改后的条码');
				$('#target_id').focus();
				return ;
			}
			
			if ( !target.replace('G','').verhoeffCheck() ){
				alert('条码格式错误！');
				$('#target_id').val('');
				$('#target_id').focus();
				return ;
			}
			
			if ( patid ==  target ){
				alert('修改前后的条码相同！');
				return ;
			}
			
			var other = '';	
			//alert($("#radio_4").is(':checked'));
			if ( $("#radio_4").is(':checked') ){
				other = $('#other_id').val();
				if ( other == '' ){
					alert('请输入复制内容来源条码');
					$('#other_id').focus();
					return ;
				}
				
				if ( !other.replace('G','').verhoeffCheck() ){
					alert('条码格式错误！');
					$('#other_id').val('');
					$('#other_id').focus();
					return ;
				}
				
				if ( patid ==  other ){
					alert('修改前后的条码相同！');
					return ;
				}
			}
			
			var rowData = jQuery('#dataTable2').jqGrid('getGridParam','selarrrow');
			//jQuery('#dataTable2').jqGrid('getCell',rowData[i],'name');
			if ( rowData.length == 0 ){
				var c = confirm('您没有选择任何需要复制的问卷，是否提交？');
				if ( c == false )
					return ;
			}
			
			var items = ',';
			for ( var i = 0 ; i < rowData.length ; i ++ ){
				var c = jQuery('#dataTable2').jqGrid('getCell',rowData[i],'itemCode');
				items += (c + ',');
			}
			
			if ( other && other.length > 0 && patid.length != other.length ){
				alert("数据来源条码必需是同一类型的条码");
				return;
			}
			
			if ( patid.length != target.length ){
				alert("修改前后的条码必需是同一类型的条码");
				return;
			}
			
			
			var cc = false;
			if ( other && other.length > 0 )	
				cc = confirm('条码'+patid+'将替换为'+ target +'，并且从'+other +'问卷"'+items+'"，是否继续？');
			else
				cc = confirm('条码'+patid+'将替换为'+ target +'，问卷"'+items+'"，是否继续？');
			if ( cc == false )
				return ;
			
			$.ajax({
				url : '${ctx}/sys/drop/exchangeId',
				type : 'POST',
				dataType : 'json',
				data : {patientId:patid,targetId:target,sourceId:other,items:items}
			}).done(function (data, textStatus) {
				if ( data ){
					if ( data.msg ){
						alert(data.msg);
					}else{
						alert(patid + "条码已经修改成功！");
						window.location.href = '${ctx}/sys/drop';
					}
				}
			}).fail(function () {
				//alert('error');
			});	
		});
		
		
	});
</script>
</body>



</html>