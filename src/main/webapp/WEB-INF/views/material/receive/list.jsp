<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head><title>单位采血箱接收</title></head>
<body>
	<ul class="breadcrumb">
		<li class="active">采血器具包</li>
		<li class="active">单位采血箱接收</li>
	</ul>
	<div id="alertError" class="alert alert-danger" hidden>
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
                		<li class="select-one"> <label form=name>默认库房:</label></li>
                        <li> 
                             	<%-- <select id="stockCode" style="height: 30px;padding: 5px 10px;font-size: 12px;line-height: 1.5;border-radius: 3px;">
                             		<c:forEach items="${storehouse}" var="oneStore">
										<option value="${oneStore.stockCode }" >${oneStore.stockName }</option>
									</c:forEach>
                             	</select> --%>
                             	<input type="hidden" id="stockCode" name="stockCode">
								<input type="text" id="stockName" name="stockName"   class="form-control input-sm" placeholder="输入单位编码">
                         </li>
                    	 <li class="select-one"> <label form=''>出库单号:</label></li>
                         <li> 
                         	<input type="text" id="exorderNo" class="form-control input-sm" placeholder="请输入入库单号"/>
                         </li>
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
	
	<div id='dialog-confirm' class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="dialog-confirm-title">上传文件</h4>
				</div>
				<div class="modal-body">
					<p>加载中……</p>
				</div>
				<div class="modal-footer">
					<button type="button" id='cancel' class="btn btn-default btn-sm"
						tabindex="1001">取消</button>
					<button type="button" id='do_save' class="btn btn-primary btn-sm"
						tabindex="1000">提交</button>
				</div>
			</div>
		</div>
	</div>
    	<div id='dialog-delete' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">删除</h4>
	      </div>
	      <div class="modal-body">
	        <p>是否删除？</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<!-- start -->
    <div id='dialog-confirm-grid' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">箱内信息</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel1' class="btn btn-default btn-sm" tabindex="1001">关闭</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	<script type="text/javascript">
		$(function(){
			
			//当前用户的库房
			$.ajaxSettings.async = false; 
			$.getJSON("${ctx}/combox/stockInfo?limitLcc=true",function(data) {
				if(data.length >0){
					$('#stockName').val(data[0].STOCKNAME);
					$('#stockCode').val(data[0].STOCKCODE);
				}
			    $('#stockName').autocomplete(data,{
			        minChars: 0,
			        mustMatch:true, 
			        width:260,
			       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
			        formatItem: function(item,i, max) {
			            return '<table><tr><td width="80px;">' + item.STOCKCODE + '</td><td width="180px;">' + item.STOCKNAME + '</td></tr></table>';
			            
			        },
			        // 指定 与 输入文字匹配的字段名
			        formatMatch: function(item,i, max) {
			            return item.STOCKCODE+item.STOCKNAME;
			        },
			        // 选中 某条记录在输入框里 显示的数据字段
			        formatResult: function(item) {
			            return item.STOCKNAME;
			        }
			    }); 
			  //选中 某条记录 触发的事件
			    $('#stockName').result(function(event, item){ 
					if(item){
						if(item.STOCKCODE != $("#stockCode").val()){
							$("#stockCode").val(item.STOCKCODE);
						}
					}else{
						$("#stockCode").val("");
					}
			     });
			}); 
			
			
			
			
			
			//取消按钮操作
			$('#cancel').click(function(){
				$('#dialog-confirm').modal('hide');
			});	
			$('#cancel1').click(function(){
				$('#dialog-confirm-grid').modal('hide');
			});
			$('#cancel2').click(function(){
				$('#dialog-delete').modal('hide');
			});
			//document
			var optionDocument = {
					url : '${ctx}/material/receive/list',
					datatype : 'json',
					mtype : 'POST',
					colNames : [ '主键','出库单号',
					             '出库类别','来源LCCID','来源单位','来源库房','收货单位','收货库房',
					             '出库员','出库时间','出库状态'],
					postData :{stockCode:$("#stockCode").val()},
					colModel : [
							{
								name : 'id',
								index : 'id',
								hidden:true
							},
							{
								name : 'exorderNo',
								index : 'exorderNo',
								align : 'center'
							},
							{
								name : 'activeclassCode',
								index : 'activeclassCode',
								align : 'center'
							},{
								name : 'lccCode',
								index : 'lccCode',
								align : 'center'
							},{
								name : 'lccName',
								index : 'lccName',
								align : 'center'/* ,
								formatter:function(cellvalue, option, rowObjects){
									return rowObjects.lccCode+"_"+cellvalue;
								}  */
							},
							{
								name : 'stockName',
								index : 'stockName',
								align : 'center'/* ,
								formatter:function(cellvalue, option, rowObjects){
									return rowObjects.lccCode+"_"+cellvalue;
								}  */
							},
							{
								name : 'exportLccName',
								index : 'stockName',
								align : 'center'/* ,
								formatter:function(cellvalue, option, rowObjects){
									return rowObjects.stockCode+"_"+cellvalue;
								} */ 
							},
							{
								name : 'exportStockName',
								index : 'stockName',
								align : 'center'/* ,
								formatter:function(cellvalue, option, rowObjects){
									return rowObjects.stockCode+"_"+cellvalue;
								} */ 
							},{
								name : 'userName',
								index : 'userName',
								align : 'center'
							},
							{
								name : 'exportDate',
								index : 'exportDate',
								align : 'center',
								formatter:yymmddFormatter
							},
							{
								name : 'importState',
								index : 'importState',
								align : 'center',
								formatter:renameState
							}],
					rowNum : 15,
					rowList : [ 15, 30, 50,100,150,500 ],
					height : "100%",
					autowidth : true,
					pager : '#pagerDocument',
					altRows : true,
					hidegrid : false,
					viewrecords : true,
					recordpos : 'left',
					sortorder : "desc",
					emptyrecords : "没有可显示记录",
					loadonce : false,
					multiselect : true,
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
					},
					/* 开启多级表格 start */
					subGrid: true,
					/* subGridUrl : '${ctx}/material/blood/list1',pipMmsExscmmaster
					   subGridModel : [ 
					                 { 
					                	 name : [ '编号', '项目ID', '入库编码','医院名称' ], 
					                	 width : [ 55, 200, 80 ],
					                	 mapping:['id','projectId','imorderNo','lccName']
					                 } 
					] */
					subGridRowExpanded :function(subgrid_id,row_id){
						//根据subgrid_id定义对应的子表格的table的id
						var subgrid_table_id;  
			            subgrid_table_id = subgrid_id + "_t"; 
			          	//根据subgrid_id定义对应的子表格的pager的id pipMmsExscmdetal
			            var subgrid_pager_id;  
			            subgrid_pager_id = subgrid_id + "_pgr";
			            //动态添加子报表的table和pager
			            $("#" + subgrid_id).html("<table id='"+subgrid_table_id+"' class='scroll'></table><div id='"+subgrid_pager_id+"' class='scroll'></div>"); 
			            //创建jqGrid对象 获取选中的行数据
						var rowData = $("#documentgrid").jqGrid('getRowData',row_id);
			            $("#" + subgrid_table_id).jqGrid({  
			            	url: "${ctx}/material/receive/listDetail?exorderNo="+rowData.exorderNo,
			            	datatype: "json",
			            	mtype : 'POST',
			            	colNames: ['编号','项目编码','出库单号','物资信息编码','箱编号','批次','出库单价','采购价','库存单位','生产厂商','截止有效期','创建时间','查看详情'], 
			            	colModel: [  
			                           {name:"id",index:"id",key:true,hidden:true},  
			                           {name:"projectId",index:"projectId",hidden:true},  
			                           {name:"exorderNo",index:"exorderNo",align:"center",width:150},  
			       					   {name:"materlinfoCode",index:"materlinfoCode",align:"center",width:100,hidden:true},
			       					   {name:"archivesNo",index:"archivesNo",align:"center",width:100},
			       					   {name:"materlBatch",index:"materlBatch",align:"center",width:60},
			       					   {name:"materlPrice",index:"materlPrice",align:"center",width:60},
			       					   {name:"wholesalePrice",index:"wholesalePrice",align:"center",width:100},
			       					   {name:"storeUnit",index:"storeUnit",align:"center"},
			       					   {name:"manufacturerCode",index:"manufacturerCode",width:200,align:"center"},
			       					   {name:"periodValidity",index:"periodValidity",width:90,formatter:yymmddFormatter},
			       					   {name:'createDate',align:"center",index : 'createDate',width:90,formatter:yymmddFormatter},
			       					   {name:"look",index:"edit",align:"center",width:80,formatter:look}
			                       ],
	                       pager: subgrid_pager_id,  
	                       viewrecords: true,  
	                       height: "100%",
	                       width:"100%",
	                       rowNum : 15,
						  rowList : [ 15, 30, 50,100,150,500 ],
				          recordpos : 'left'  
			            });
			            
					}
					/* 开启多级表格 end */
				};
				$("#documentgrid").jqGrid(optionDocument);
				$("#documentgrid").jqGrid('navGrid', '#pagerDocument', {
					edit : false,
					add : false,
					del : false,
					search : false,
					position : 'right'
				}).navButtonAdd('#pagerDocument',{caption:"接收",buttonicon:"ui-icon-edit",onClickButton: function(){receiveDocument(2)},position:"last"});
				//.navButtonAdd('#pagerDocument',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){deleteDocument()},position:"last"})
				//自适应
				jqgridResponsive("documentgrid", false);
		});
		function look(cellvalue, option, rowObjects){
			return "<a href='javascript:void(0)' style='color:green' onclick='toLook(\""+rowObjects.archivesNo+"\")'>详细信息</a>";
		}
		function renameState(cellvalue, option, rowObjects){
			if(cellvalue =='1'){
				return "<span style='color:blue'>出库</span>";
			}
			else if(cellvalue =='2'){
				return "<span style='color:green'>已接收</span>";
			}
			else{
				return "-";
			}
		}
		
		//弹出框查看
		function toLook(archivesNo){
			var timebak = new Date().getTime();
			openDialogGrid("${ctx}/material/blood/openmodalScmarchives?archivesNo="+archivesNo+"&time="+timebak);
			//material/blood/listDetal
		}
		//弹出伦理对话框
		 function openDialogGrid(url,data){
			$( "#dialog-confirm-grid" ).modal({
				 backdrop: 'static'
			});
			//使用此方法防止js缓存不加载
			$("#dialog-confirm-grid p" ).load(url);
		}
		
		$('#startDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#startDateDiv').datepicker('hide');
		});
		$('#endDateDiv').datetimepicker({
			autoTop:true,
			language:'zh-CN',
			minView:'2', 
			autoclose:true,
			format : 'yyyy-mm-dd',
			weekStart : 1
		}).on('changeDate', function(ev) {
			$('#endDateDiv').datepicker('hide');
		});
		function uploadDocument(){
			var timebak = new Date().getTime();
			openDialog("${ctx}/material/blood/openmodaladdjunctinput?time="+timebak);
		}
		//接收
		function receiveDocument(state){
			
			var ids = $("#documentgrid").jqGrid('getGridParam','selarrrow');
			if($.isEmptyObject(ids)){
				openError('请选择要接收的记录！',2000);
				return ;
			}
			var idDatas= "";
			$.each(ids,function(i,n){
				idDatas +=n +",";
			});
			idDatas=idDatas.substr(0,idDatas.length-1);
			$.post("${ctx}/material/receive/changeState",{ids: idDatas, state: state},function(result){
				$("#documentgrid").jqGrid().trigger("reloadGrid");
//				var message = "操作失败!";
//				if(result.success){
//					message = "操作成功！";
//				}
				showResult(result.success,result.message);
			},'json');
			
			
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
		$('#do_delete').click(function(){
			var documentId = $("#documentgrid").jqGrid('getGridParam','selrow');
			if($.isEmptyObject(documentId)) {
				openError('请选择要删除的文件！',200000,$("#alert"));
				return;
			}
			var rowData = $("#documentgrid").jqGrid('getRowData',documentId);
			$.ajax({
	             type: "POST",
	             url: "${ctx}/pro/filemgt/deleteDocument",
	             data: {adjunctId:documentId,adjunctName:rowData.adjunctName,path:rowData.path},
	             dataType: "json",
	             success: function(data){
	            	 $('#dialog-delete').modal('hide');
	                if(data.success){
	                	$("#documentgrid").setGridParam({postData:{page: 1, projectId: $("#projectId").val()}}).trigger("reloadGrid");
	                }else{
	                	openError("删除失败！",2000,$("#alert"));
	                }
	             }
	         });
		});
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
			
			
			if($("#file").val()==""){
				openError("请选择要上传的文件！",2000,$("#alertForUpload"));
				return;
			}
			
				var data ={};
				var fileName = $("#file").val();
				if(fileName && fileName.length > 1 ){      
		        var ldot = fileName.lastIndexOf(".");
		        var type = fileName.substring(ldot + 1);
		        if(type != "xls"){
		           alert('请选择2003版xml文件上传');
		           return false;
		        }
				}else{
					return false;
				}
				
		        var imorderNo = $("#imorderNo").val();
		        if(imorderNo && imorderNo.length > 1 ){    
		        	data['imorderNo']= imorderNo;
		        }
			
			$("#do_save").attr("disabled",true);
			$("#loading").show();
			$("#adjunctForm").ajaxSubmit({
				url:"${ctx}/material/blood/uploadDocument", 
	           	type:"POST",  
	           	dataType:"json",
	           	success:function(data){  
	               	if(data.success){
	               		$('#dialog-confirm').modal('hide');
	               		searchAdjunct();
	               		//$("#documentgrid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
	               	}else{
	               		$("#loading").hide();
	               		openError(data.msg,2000000,$("#alertForUpload"));
	               		$("#do_save").attr("disabled",false);
	               	}
	             }
			});
		});
		function searchAdjunct(){
		      var postData = $("#documentgrid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var exorderNo =$("#exorderNo").val();
		      var stockCode =$("#stockCode").val();
		      if(exorderNo && ''!=exorderNo){
		          mydata.exorderNo = exorderNo;
		      }else{
		          delete postData.exorderNo;
		      }
		      if(stockCode && ''!=stockCode){
		          mydata.stockCode = stockCode;
		      }else{
		          delete postData.stockCode;
		      }
		     $.extend(postData,mydata);
		     $("#documentgrid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		}
		function openError(message,delay){
			$('#alertError').show().find('strong').text(message);
			window.setTimeout(function() {
				$('#alertError').slideUp("slow");
			}, delay);
		}
	</script>
</body>
</html>