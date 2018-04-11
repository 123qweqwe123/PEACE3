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
		
		.checkbox1{
			width:18px;
			height:18px;
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
			            
			            <label for="remark">条码修改:</label>
				        <div class="form-group">
		            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            		身份证号:
		        			<input type="text" id="drop_id" name="type" class="patientId input" value="" checked />
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
		        						项目点编码:<span class="txt txt_lccCode"></span>
		        					</td>
		        					<td colspan="2">
		        						地址:<span class="txt txt_address"></span>
		        					</td>
		        				</tr>
		        				<!-- <tr>
		        					<td colspan=3>
		        						<table id="dataTable"></table>
		        					</td>
		        				</tr> -->
		        			</table>
		        			<div  id="update_div" style="margin-left:106px;">
		            	 	</div>
		            	 </div>
		            	
		            	 <div class="form-group">
		            	 	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		            	 	条码修改或删除:
		            	 	<div id="new_div" style="margin-left:106px;">
		            	 		
		            	 		初筛条码:<input id="new_patient_id" type="text" name="new_patient_id" value=""></input> 【<input type="checkbox" id="del_patient" class="checkbox1" for="new_patient_id">删除初筛条码</input>】<br> <br>
		            	 		高危条码:<input id="new_risk_id" type="text" name="new_risk_id" value=""></input> 【<input type="checkbox" id="del_risk" class="checkbox1" for="new_risk_id">删除高危条码</input>】<br>
		            	 	</div>
		            	 	
		            	 </div>
		            	 <button id="bt_drop" type="button" >提交</button>
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

		$('.checkbox1').click(function(e){
			if ($(e.target).is(':checked')){
				$('#'+$(e.target).attr('for')).val('');
				$('#'+$(e.target).attr('for')).attr("disabled","disabled");
			}else{
				$('#'+$(e.target).attr('for')).val('');
				$('#'+$(e.target).attr('for')).removeAttr("disabled");
			}
		});
		
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
		
		
		$('.patientId').keyup(queryPatient);
		
		function queryPatientById(id){
			var v = id;
			if (  v.length != 15 && v.length != 18 )
				return ;
			$.ajax({
				url : '${ctx}/sys/drop/queryIdCard',
				type : 'POST',
				dataType : 'json',
				data : {idCard:v}
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
						
						$('#update_div').html('');
						var txt = '';
						var patientId = data['patientId'];
						var riskCode = data['riskCode'];
						if ( patientId ){
							var ids = patientId.split('|');
							for ( var i = 0 ; i < ids.length ; i ++ ){
								if ( ids[i] != '' && ids[i] != 'null'){
									txt += '初筛条码:';
									txt += '<span class="txt">';
									txt += ids[i] ;
									txt += '</span>'
									//txt += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="'+ids[i]+'" value="1">保留</input>';
									//txt += '&nbsp;&nbsp;<input type="radio" name="'+ids[i]+'" value="2">清除</input>';
									//txt += '&nbsp;&nbsp;<input type="radio" name="'+ids[i]+'" value="3">替换为:</input>';
									//txt += '&nbsp;&nbsp;<input type="text" name="'+ids[i]+'_input" value=""></input>';
									txt += '<br>';
								}
							}
							
						}
						if ( riskCode ){
							var ids = riskCode.split('|');
							for ( var i = 0 ; i < ids.length ; i ++ ){
								if ( ids[i] != '' && ids[i] != 'null'){
									txt += '高危条码:';
									txt += '<span class="txt">';
									txt += ids[i] ;
									txt += '</span>';
									//txt += '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="'+ids[i]+'" value="1">保留</input>';
									//txt += '&nbsp;&nbsp;<input type="radio" name="'+ids[i]+'" value="2">清除</input>';
									//txt += '&nbsp;&nbsp;<input type="radio" name="'+ids[i]+'" value="3">替换为:</input>';
									//txt += '&nbsp;&nbsp;<input type="text" name="'+ids[i]+'_input" value=""></input>';
									txt += '<br>';
								}
							}
						}
						$('#update_div').html(txt);
					}
				}
				
				
			}).fail(function () {
				//alert('error');
			});	
					
		}
		
		function queryPatient(e){
			var v = $(e.target).val();
			queryPatientById(v);
		}
		
		
		$('#bt_drop').click(function(){
			
			var idcard = $('#drop_id').val();
			var patid =  $('#new_patient_id').val();
			var riskid =  $('#new_risk_id').val();
			var delpat = 0;
			var str = '该受访者的';  //该受访者的初筛条码将更改为xxxx（清除），高危条码将更改为yyyy（清除）。”点击“确认”后提交操作，“取消”后返回。
			if ( $('#del_patient').is(':checked')){
				delpat = 1;
				str += '初筛条将被清除.';
			}else if (patid != ''){
				str += '初筛条将被更改为'+patid+'.';
			}
			var delrisk = 0;
			if ( $('#del_risk').is(':checked')){
				delrisk = 1;
				str += '高危条将被清除.';
			}else if(riskid != ''){
				str += '高危条将被更改为'+riskid+'.';
			}
			if ( idcard == ''){
				alert('身份证必需填写！');
				return ;
			}
			
			if ( delpat == 0 && patid != '' && (! patid.replace('G','').verhoeffCheck() || patid.length != 11 )){
				$('#new_patient_id').val('');
				alert('初筛条码格式错误！');
				return ;
			}
			if ( delrisk == 0 && riskid != '' && (! riskid.replace('G','').verhoeffCheck() || riskid.length != 10 )){
				$('#new_risk_id').val('');
				alert('高危条码格式错误！');
				return ;
			}
			
			if ( str == '该受访者的' ){
				alert('您没有输入任何修改的信息。');
				return ;
			}else{
				str += '\n点击“确认”后提交操作，“取消”后返回。';
			}
			
			if ( confirm(str)){
				$.ajax({
					url : '${ctx}/sys/drop/updatePatient',
					type : 'POST',
					dataType : 'json',
					data : {idNumber:idcard,patientId:patid,riskCode:riskid,delPat:delpat,delRisk:delrisk}
				}).done(function (data, textStatus) {
					if ( data ){
						if ( data.msg ){
							alert(data.msg);
						}else{
							alert(patid+ "操作成功！");
							queryPatientById($('#drop_id').val());
							$('#new_patient_id').removeAttr("disabled");
							$('#new_patient_id').val('');
							$('#new_risk_id').removeAttr("disabled");
							$('#new_risk_id').val('');
							$('#del_patient').attr('checked',false);
							$('#del_risk').attr('checked',false);
						}
					}
				}).fail(function () {
					//alert('error');
				});	
			}
		});
		
		
		
		
	});
</script>
</body>



</html>