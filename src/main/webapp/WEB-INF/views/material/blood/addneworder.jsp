<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
 <title>出货单添加</title>
 <script  src="${ctx}/static/echarts/esl/esl.js"></script> 
 	
	<style>
		.img_mystyle{
			width: 20px;
			height: 20px;
		}
	</style>
</head>
<body>
	 <div id="alert_msg" class="alert alert-danger" hidden>
	  <strong>Warning!</strong>
	</div>

	<div id="select">
		<div class="select-main">
			<fieldset>
                	<ol>
                	       <li class="select-one"> <label form='exorderNo2'><span style="color: red">*</span>出库单号：</label></li>
                           <li> 
	                         <input type="text" style="width: 150px" id="exorderNo2" class="form-control input-sm" value="${exorderNo}"   />
                           </li>
                           <li class="select-one"> <label form='activeclassCode'><span style="color: red">*</span>出库类别：&nbsp;&nbsp;&nbsp;&nbsp;</label></li>
                           <li> 
	                         <select id="activeclassCode" name="activeclassCode" class="form-control input-sm">
										<option value="0001011" >发放出库</option>
										<option value="0001012" >报损出库</option>
										<option value="0001013" >退回出库</option>
							</select>
                           </li>
                           <!-- 
                    	   <li class="select-one"> <label form='exorderDate'>出库日期：</label></li>
                           <li> 
	                         <input type="text" id="exorderDate" class="form-control input-sm" placeholder="出库日期"/>
                           </li>	
                            -->
                           <li class="select-one"> <label form='lccName'><span style="color: red">*</span>当前库房单位：</label></li>
                           <li> 
                           	 <input type="hidden" style="width: 180px" id="lccCode1" class="form-control input-sm"  value="<%=Securitys.getUser().getLccCode()  %>"/>
	                         <input type="text" style="width: 180px" id="lccName1" class="form-control input-sm" readonly="readonly" value="<%=Securitys.getUser().getLccName()  %>"/>
                           </li>
                           </ol>
                           <div style="margin-top: 40px">
	                           <ol style="top: 130px">
	                            <li class="select-one"> <label form='stockCode'><span style="color: red">*</span>当前库房：</label></li>
	                            <li> 
	                             <input type="hidden" id="stockCode1" value="${pmslist.stockCode}" />
		                         <input type="text" style="width: 150px" id="stockName" class="form-control input-sm"   readonly="readonly"  value="${pmslist.stockName}"/>
	                           </li>
                    		
	                          <li class="select-one"> <label form='loginName'><span style="color: red">*</span>出库员名称：</label></li>
	                            <li> 
		                         <input type="text" style="width: 96px;" id="loginName" class="form-control input-sm" value="<%=Securitys.getUser().getLoginName()%>"/>
	                           </li>
	                         
	                           <li class="select-one"> <label form='exportLccCode'><span style="color: red">*</span>出货到单位：&nbsp;&nbsp;&nbsp;&nbsp;</label></li>
		                         <li> 
		                         	<input id="exportLccName" type="text" style="width: 180px" class="form-control input-sm"  placeholder="输入单位简码或者LCCID"/>
						            <input id="exportLccCode" type="hidden" name="exportLccCode" />
		                         </li>
		                         </ol>
                           </div>
	                         <div style="margin-top: 80px">
	                         		<ol>
			                          <li> <label form='exportStockCode'><span style="color: red">*</span>出货到库房：</label></li>
			                          <li> 
				                         <select style="width: 200px" id="exportStockCode" name="exportStockCode" class="form-control input-sm">
							          	 </select>
			                          </li>
		                          </ol>
	                       </div>
                       </fieldset> 
                       </div>
                       </div> 
        <div id="select">
		<div class="select-main">     
                 <div style="width: 400px;height: 30px">
	                 <ol>
	                     <li class="select-one"> <label form='archivesNo'><span style="color: red">*</span>箱号：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label></li>
	                     <li> 
		                  <input type="text" id="archivesNo" class="form-control input-sm" placeholder="输入箱号查询"/>
	                     </li>
	                 </ol>
                 </div>
                 <hr />

      <div style="margin-top: 0px;">
          <ol>
              <!--   <li> <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">列表选择</button></li> -->

              <li class="select-one"> <label form='materlinfoCode'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱编号：</label></li>
              <li>
                  <input type="text" id="archivesNo_1" class="form-control input-sm"  value="箱编号" readonly="readonly"/>
                  <input type="hidden" id="manufacturerCode_1" class="form-control input-sm"  />
              </li>
              <li class="select-one"> <label form='materlBatch'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;批次：</label></li>
              <li>
                  <input type="text" style="width: 100px" id="materlBatch_1" class="form-control input-sm" placeholder="批次" readonly="readonly"/>
              </li>

              <li class="select-one"> <label form='materlPrice'>&nbsp;&nbsp;&nbsp;&nbsp;采购价格：</label></li>
              <li>
                  <input type="text" id="materlPrice_1" class="form-control input-sm" placeholder="物资采购价" readonly="readonly"/>
              </li>
          </ol>
      </div>
      <div style="margin-top: 60px;">
          <ol>
              <li class="select-one"> <label form='wholesalePrice'>&nbsp;&nbsp;&nbsp;&nbsp;出货单价：</label></li>
              <li>
                  <input type="text" id="wholesalePrice_1" class="form-control input-sm" placeholder="出货单价" readonly="readonly"/>
              </li>

              <li class="select-one"> <label form='storeUnit'>&nbsp;&nbsp;库存单位：</label></li>
              <li>
                  <input type="text" style="width: 100px" id="storeUnit_1" class="form-control input-sm" placeholder="库存单位" readonly="readonly"/>
              </li>
              <li class="select-one"> <label form='periodValidity'>&nbsp;&nbsp;&nbsp;&nbsp;有效日期：</label></li>
              <li>
                  <input type="text" id="periodValidity_1" class="form-control input-sm" placeholder="有效日期" readonly="readonly"/>
              </li>
          </ol>
      </div>

		</div>
	</div>
	<li> <button type="button" id="add" class="btn btn-primary btn-align-right btn-sm">添加</button>
	<button type="button"  id="del" class="btn btn-primary btn-align-right btn-sm">删除</button>
	 <button type="button" id="save" class="btn btn-primary btn-align-right btn-sm">保存订单</button></li>

<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
	</div>
<div id="jqgrid2">
    <table id="grid2"></table>
    <div id="pager2"></div>
</div>


<script type="text/javascript">


		$(function(){
     	  
			$.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) { 
			    $('#exportLccName').autocomplete(data,{
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
			     $('#exportLccName').result(function(event, item){ 
					if(item){
						if(item.lccCode != $("#exportLccCode").val()){
							$("#exportLccCode").val(item.lccCode);
							
							var exportLccCode  = $('#exportLccCode').val();
							if(exportLccCode!=null && exportLccCode!='undefinded' && exportLccCode.length>0){
								var data ={};
								data.exportLccCode =exportLccCode;
								 $.post("${ctx}/material/blood/getExportStockCode",data,function(result){
										var message = "查询库房失败！";
										 if(result && result !='undefinded' &&result.success==true){
			                	              $.each(result.data,function(idx,item){
			                	            	  jQuery("#exportStockCode").empty();
			                	            	  jQuery("#exportStockCode").append("<option value='"+item.stockCode+"'>"+item.stockCode+'_'+item.stockName+" </option>"); 
			               	              });
										}else{
											  jQuery("#exportStockCode").empty();
											jQuery("#exportStockCode").append("<option value=''></option>");
										}
									},'json');
							}
							else{
								jQuery("#exportStockCode").empty();
							}
						}
					}else{
						$("#exportLccCode").val("");
					}
			     }); 
			});
			
			
			
			
			
			Date.prototype.format = function(format){ 
        		var o = { 
        		"M+" : this.getMonth()+1, //month 
        		"d+" : this.getDate(), //day 
        		"h+" : this.getHours(), //hour 
        		"m+" : this.getMinutes(), //minute 
        		"s+" : this.getSeconds(), //second 
        		"q+" : Math.floor((this.getMonth()+3)/3), //quarter 
        		"S" : this.getMilliseconds() //millisecond 
        		}; 
        		if(/(y+)/.test(format)) { 
        		format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
        		} 
        		for(var k in o) { 
        		if(new RegExp("("+ k +")").test(format)) { 
        		format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length)); 
        		} 
        		} 
        		return format; 
        		}; 
			//所属省份
			$.getJSON("${ctx}/material/blood/archivesNoAllByLccCode",function(data) { 
			    $('#archivesNo').autocomplete(data,{
			        minChars: 0,
			        mustMatch:true, 
			        listTitle:{data:{archivesNo:'箱号',periodValidity:'有效日期',materlBatch:'批次',materlPrice:'采购价',wholesalePrice:'出货价'},value:'-1'},
			        width:360,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			        	var d = '';
			        	try{
			        		d = new Date(item.periodValidity).format("yyyy-MM-dd");
			        		if ( d == 'NaN-aN-aN')
			        			d = item.periodValidity;
			        	}catch(e){
			        		d = item.periodValidity;
			        	}
			        	
			            return '<table><tr><td width="200px;">' + item.archivesNo + '</td><td width="300px;">' + 
			            d + 
			            '</td>'+
			            '<td width="150px;">' + item.materlBatch + '</td>'+
			            '<td width="200px;">' + item.materlPrice + '</td>'+
			            '<td width="200px;">' + item.wholesalePrice + '</td>'+
			            '</tr></table>';
			            
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.archivesNo;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			        	
			            return item.archivesNo;
			        }
			    }); 
			  //选中 某条记录 触发的事件
			
			    $('#archivesNo').result(function(event, item){ 
			    	$('#materlBatch_1').val(item.materlBatch);
		        	$('#periodValidity_1').val(new Date(item.periodValidity).format("yyyy-MM-dd"));
		        	$('#materlPrice_1').val(item.materlPrice);
		        	$('#wholesalePrice_1').val(item.wholesalePrice);
		        	$('#storeUnit_1').val('箱');
		        	$('#archivesNo_1').val(item.archivesNo);
		        	$('#manufacturerCode_1').val(item.manufacturerCode);
					if(item){
						if(item.archivesNo != $("#archivesNo").val()){
							$("#archivesNo").val(item.archivesNo);
						}
					}else{
						$("#archivesNo").val("");
					}
			     });
			});
			/* $('#exportLccCode').change(function(){ 
				var exportLccCode  = $('#exportLccCode').val();
				if(exportLccCode!=null && exportLccCode!='undefinded' && exportLccCode.length>0){
					var data ={};
					data.exportLccCode =exportLccCode;
					 $.post("${ctx}/material/blood/getExportStockCode",data,function(result){
							var message = "查询库房失败！";
							 if(result && result !='undefinded' &&result.success==true){
							 
                	              $.each(result.data,function(idx,item){
                	            	  jQuery("#exportStockCode").empty();
                	            	  jQuery("#exportStockCode").append("<option value='"+item.stockCode+"'>"+item.stockCode+'_'+item.stockName+" </option>"); 
               	              });
							}else{
								  jQuery("#exportStockCode").empty();
								jQuery("#exportStockCode").append("<option value=''></option>");
							}
						},'json');
				}
				else{
					jQuery("#exportStockCode").empty();
				}
			}) */
			
			
	
	$('#save').click(function(){
	    var data={};
		var str='';
		var ids = $("#grid2").jqGrid('getDataIDs');
		if(ids =='' || ids ==null){
			 openErrorMsg('请添加要出库的箱号！',2000);
			 return ;
		 }
		//var rowData = $('#gridTable').jqGrid('getRowData',ids[0]);
		for(var i=0 ;i<ids.length;i++){
			if(i>0)str+=",";
			var archivesNo = $("#grid2").jqGrid('getRowData',ids[i]).archivesNo;
			str += archivesNo ;
		}
		
	      if(str && str.length>0){
	    	  data.str=str;
	      }else{
	    	  return;
	      }
	      var exportLccCode = $("#exportLccCode").val();
	      if(exportLccCode=='' || exportLccCode=='undefinded'){
	    	 openErrorMsg('出货到单位不能为空！',2000);
	    	 return ;
	      }else{
	    	 data.exportLccCode=exportLccCode; 
	      }
	      var activeclassCode = $("#activeclassCode").val();
	      if(activeclassCode=='' || activeclassCode=='undefinded'){
	    	 openErrorMsg('出货类型不能为空',2000);
	    	 return ;
	      }else{
	    	 data.activeclassCode=activeclassCode; 
	      }
	      
	      var exorderNo2 = $("#exorderNo2").val();
	      if(exorderNo2=='' || exorderNo2=='undefinded'){
	    	 openErrorMsg('出货单号不能为空',2000);
	    	 return ;
	      }else{
	    	 data.exorderNo=exorderNo2; 
	      }
	      var stockCode = $("#stockCode1").val();
	      if(stockCode && stockCode!='undefinded' && stockCode.length>0){
	    	  data.stockCode = stockCode;
	      }
	      else{
	    	  delete data.stockCode;
	      }
	      
	      var exportStockCode = $("#exportStockCode").val();
	      if(exportStockCode=='' || exportStockCode=='undefinded'){
	    	 openErrorMsg('出货到库房不能为空',2000);
	    	 return ;
	      }else{
	    	 data.exportStockCode=exportStockCode; 
	      }
	      
	      var loginName = $("#loginName").val();
	      if(loginName=='' || loginName=='undefinded'){
		    	 openErrorMsg('出库员名称不能为空',2000);
		    	 return ;
	      }else{
	    	 data.loginName=loginName; 
	      }
	      $.post("${ctx}/material/blood/saveExscmmaster",data,function(result){
				$("#grid").trigger("reloadGrid");
				$('#dialog-confirm').modal('hide');
				var message = "出货订单失败！";
				if(result){
					message ='出货订单成功!';
					showResult(true,message,'2000');
					//取消按钮操作
					$('#cancel').click(function(){
						$('#dialog-confirm').modal('hide');
					});
				}
				$("#do_save").attr("disabled",false);
				showResult(result,message,'2000');
			},'json');
	      
	      
		
	});
	
	
	
	$('#del').click(function(){
		var selectedRowIds = $("#grid2").jqGrid("getGridParam","selarrrow");  
		 var ids = jQuery("#grid2").jqGrid('getDataIDs');  
		 if(selectedRowIds =='' || selectedRowIds ==null){
			 openErrorMsg('请选择要删除的数据！',2000);
			 return ;
		 }
		 $("#grid2").jqGrid("delRowData",selectedRowIds);
		 for(var i = selectedRowIds.length-1;i >=0 ;i--) {
			$("#grid2").jqGrid("delRowData", selectedRowIds[i]);  
		  }  
	});
	
	
	 var newrowid =1;
	//取消按钮操作
	$('#add').click(function(){
		//判断出库单号是否唯一
		var exportOrderNo = $.trim($("#exorderNo2").val());
		if(exportOrderNo=='' || exportOrderNo ==null){
			openErrorMsg('出库单不能为空',2000);
			return ;
		}
		/* $.post("${ctx}/material/blood/checkExorderNo", { exorderNo: exportOrderNo},
				function(data){
					if(data.result){
						openErrorMsg('出库单已经存在',2000);
						return ;
					}
				}, 
		"json"); */
		var flag = false ;
		$.ajax({  
	          type : "post",  
	          url : "${ctx}/material/blood/checkExorderNo",  
	          data : {exorderNo: exportOrderNo},  
	          async : false,
	          dataType:'json',
	          success : function(data){  
	        	  if(data.result){
					flag= true;
				 }
	          }  
	     }); 
		if(flag){
			openErrorMsg('出库单已经存在',2000);
			return;
		}
		 var selectedId = $("#grid2").jqGrid("getGridParam", "selrow"); 
		 var ids = jQuery("#grid2").jqGrid('getDataIDs');
		
		 //获得当前最大行号（数据编号）
		 // var rowid = Math.max.apply(Math,ids);
		 //获得新添加行的行号（数据编号）
		 // newrowid = rowid+1;
		 newrowid++;
		 
		 var loginName = $("#loginName").val();
		 if($.trim(loginName) == ''){
			 openErrorMsg('请输入出库员名称',2000);
	    	  return false;
		 }
		 var data = {};
	      var archivesNo = $("#archivesNo").val();
	      if(archivesNo && archivesNo!='undefinded' && archivesNo.length>0 ){
	    	 data.archivesNo = archivesNo;
	      }else{
	    	  openErrorMsg('请选择箱号',2000);
	    	  return false;
	      }
	      var exportLccCode = $("#exportLccCode").val();
	      if(exportLccCode && exportLccCode!='undefinded' && exportLccCode.length>0 ){
	    	  data.exportLccCode = exportLccCode;
	      }else{
	    	  openErrorMsg('出货到单位不能为空',2000);
	    	  return false;
	      }
	      var exportStockCode  = $("#exportStockCode").val();
	      if(exportStockCode && exportStockCode!='undefinded' &exportStockCode.length>0 ){
	    	  data.exportStockCode = exportStockCode;
	      }else{
	    	  openErrorMsg('出货到库房不能为空',2000);
	    	  return false;
	      }	
        var obj = $("#grid2").jqGrid("getRowData");
  	     for(var i = 0;i < obj.length ;i ++) {
			  if(obj[i].archivesNo == archivesNo){
				  openErrorMsg(archivesNo+'箱已经添加',2000);
				  return false;
			  }
		 }
	  	var dataRow = {
	  			  	  lccCode:$("#lccCode1").val(),
	  			  	  lccName:$("#lccName1").val(),
				      stockName:$("#stockName").val(),
				      archivesNo:$('#archivesNo_1').val(),
				      materlBatch:$('#materlBatch_1').val(),
				      wholesalePrice:$('#wholesalePrice_1').val(),
				      stockNum:'1',
				      storeUnit:'箱',
				      periodValidity:$('#periodValidity_1').val(),
				      manufacturerCode:$('#manufacturerCode_1').val()
		}; 
  	   $("#grid2").jqGrid("addRowData", newrowid, dataRow, "first");  
	});	
	
	var multipleSearch2 = function(){
		 var postData2 = $("#grid2").jqGrid("getGridParam", "postData");  
		 //var myform = $("#searchForm2").serializeArray(); 
		 /**
	      var mydata2 = {};
	      var archivesNo = $("#archivesNo").val();
	      if(archivesNo!=null && archivesNo!='undefinded'){
	    	  mydata2.archivesNo = archivesNo;
	      }
	    
	     $.extend(postData2,mydata2);
	     $("#grid2").jqGrid("setGridParam", {
	         search: true  
	     }).trigger("reloadGrid", [{page:1}]);
	      **/
	      
		   //获取表格的初始话model  
		 var colModel = $("#grid2").jqGrid().getGridParam("colModel") ;  
		 var cellLenth = colModel.length ;  
		 //设置所有列可编辑（如果行数据添加后，只有默认的几列是可修改的，这样做吧）  
		 for ( var i = 0; i < cellLenth; i++) {  
		 colModel[i].editable = true ;  
		 }  
		  
		  var newRow = JSON.stringify(colModel);  
		  var ids = $("#grid2").jqGrid('getDataIDs');  
		 //如果jqgrid中没有数据 定义行号为1 ，否则取当前最大行号+1  
		 var rowid = (ids.length ==0 ? 1: Math.max.apply(Math,ids)+1);  
		//获得新添加行的行号（为什么是负数呢,与编辑行差别对待）  
		 var newrowid = (0-rowid);  
		 //设置grid单元格不可编辑 （防止在添加时，用户修改其他非添加行的数据）  
		 $("#grid2").setGridParam({cellEdit:false});  
		 //将新行追加到表格头部  
		$("#grid2").jqGrid("addRowData", newrowid,newRow , "first");  
		//设置grid单元格可编辑（防止追加行后，可编辑列无法编辑）  
		//$('#grid2').jqGrid('editRow', newrowid, false);  
	    
	      
		
	};  
	
	var option2 = {
        url : '${ctx}/material/blood/prelist',
        datatype : 'json',  
        mtype : 'POST',
        colNames : ['选择','LCCID','医院名称','库房名称','箱号','批次','采购价','库存数量','库存单位','截止日期','生产厂商'],
        colModel : [ 
					 {name : 'id', index : 'id', align:'left' ,width: 50,sortable: false,hidden:true},
					 {name : 'lccCode', index : 'lccCode', align:'left' ,width: 50,sortable: false},
					 {name : 'lccName', index : 'lccName', align:'left',width: 100,sortable: false },
                     {name : 'stockName', index : 'stockName', align:'left',width: 120,sortable: false },
                     {name : 'archivesNo', index : 'archivesNo', align:'left',width: 80,sortable: false },
                     {name : 'materlBatch', index : 'materlBatch', align:'left' ,width: 50,sortable: false},
					 {name : 'wholesalePrice', index : 'wholesalePrice', align:'left' ,width: 50,sortable: false},
					// {name : 'exportLccCode', index : 'exportLccCode', align:'left',width: 80,sortable: false },
                     {name : 'stockNum', index : 'stockNum', align:'left',width: 60,sortable: false },
                     {name : 'storeUnit', index : 'storeUnit', align:'left',width: 60,sortable: false },
                     {name : 'periodValidity', index : 'periodValidity', align:'center',width:80,sortable: false },
                     {name : 'manufacturerCode', index : 'manufacturerCode', align:'left',width: 150,sortable: false }
                   ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager2',  
        sortname : 'id',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        multiselect: true, 
        recordpos : 'left', 
        sortorder : "ASC",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
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
	$("#grid2").jqGrid(option2); 
	$("#grid2").jqGrid('navGrid', '#pager2', {edit : false, add : false, del : false, search : false, position : 'right'}
	);//.navButtonAdd('#pager',{caption:"添加",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
	 //.navButtonAdd('#pager',{caption:"作废",buttonicon:"ui-icon-plus",onClickButton: function(){toOther()},position:"last"});
	jqgridResponsive("grid2",true); 
	
 	//$("#add").click(multipleSearch2);
	

	function openErrorMsg(message,delay){
		$('#alert_msg').show().find('strong').text(message);
		window.setTimeout(function() {
			$('#alert_msg').slideUp("slow");
		}, delay);
	}
	
	
});

</script>


</body>
    




</html>