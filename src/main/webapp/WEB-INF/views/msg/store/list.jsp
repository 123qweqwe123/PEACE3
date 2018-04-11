<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
<%@ taglib prefix="adp"	uri="http://wwww.gener-tech.com/adp-tags" %> --%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>
<title>短信库</title>
</head>
<body>
<div id='dialog-confirm' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">短信库信息</h4>
	      </div>
	      <div class="modal-body">
	        <p>加载中……</p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel' class="btn btn-default btn-sm" data-dismiss="modal"  tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
<ul class="breadcrumb">
  <li class="active">短信管理</li><li class="active" >短信信息</li>
</ul>
<div id="alert" class="alert alert-danger" hidden>
    <strong>Oh snap!</strong>
</div>
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
<div id="select">
<div class="select-main">
             <form action="" method="post" class="well-work bs-adp form-inline">
      			<fieldset>
               	<ol>
                        <li class="select-one"> <label form=name>短信类别名称:</label></li>
                        <li> 
              				<input class="form-control input-sm" id="msgTypeName1"  name="msgTypeName" type="text" placeholder="选择短信类别名称" />
                        </li>
                        <li class="select-one"> <label form=name>编辑日期:</label></li>
                        <li> 
							<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"  id="startDate" placeholder="起始日期" name="startDate" class="form-control input-sm"/>
							<input type="text" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"  id="endDate" placeholder="结束日期" name="endDateDate" class="form-control input-sm" style="margin-left: 10px"/>
                        </li>
                        <li class="select-one"> <label form=name>编辑人:</label></li>
                        <li> 
              				<input class="form-control input-sm" id="createName"  name="createName" type="text" placeholder="请输入编辑人姓名" />
                        </li>
                        <li> <button type="button" onclick="doQuery();" style="width: 50px" class="btn btn-primary btn-sm">查询</button></li>
                   </ol>
               </fieldset>
             </form>
       </div>
</div>
<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
<div id="jqgrid">
	<table id="grid"></table>
	<div id="pager"></div>
</div> 

<script type="text/javascript">

	$(function(){
		$.getJSON("${ctx}/msg/msgStore/getMsgType",function(data) {
		    $('#msgTypeName1').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return '<table><tr><td width="80px;">' + item.MSG_TYPE_CODE + '</td><td width="180px;">' + item.MSG_TYPE_NAME + '</td></tr></table>';
		            
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.MSG_TYPE_CODE+item.ID;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.MSG_TYPE_NAME;
		        }
		    }); 
		}); 
		var option = {
				url : '${ctx}/msg/msgStore/list', 
				datatype : 'json', 
				mtype : 'POST',
				colNames : ['ID', '短信类别编码','短信类别名称','短信内容','编辑日期','编辑人'],
				colModel : [ 
							{name : 'ID',hidden:true,index : 'ID',align:'center'},
				             {name : 'MSG_TYPE_CODE',width:200,index : 'MSG_TYPE_CODE',align:'center'},
				             {name : 'MSG_TYPE_NAME',width:100, index : 'MSG_TYPE_NAME',align:'center'},
				             {name : 'MSG_NAME',width:590, index : 'MSG_NAME',align:'left'},
				             {name : 'CREATE_DATE',width:100, index : 'CREATE_DATE',align:'center',formatter:yymmddFormatter},
				             {name : 'CREATE_NAME',index : 'CREATE_NAME',align:'center'},
				 ],
	           	rowNum : 15, 
		        rowList : [ 15, 30, 50,100,150,500 ],      
		        autowidth : false,  
		        pager : '#pager',  
		        sortname : 'ID',
		        altRows:true, 
		        height : "100%",
		        width :'100%',
		        hidegrid : false, 
		        viewrecords : true, 
		        recordpos : 'left', 
		        sortorder : "ASC",
		        emptyrecords : "没有可显示记录", 
		        loadonce : false,
		        multiselect: false,
		        jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell",
					id : "ID"
				}
		};
		$("#grid").jqGrid(option);
		$("#grid").jqGrid('navGrid', '#pager', {
			edit : false,
			add : false,
			del : false,
			search : false,
			position : 'right'
		}).navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})
			.navButtonAdd('#pager',{caption:"编辑",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
			.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"});
		//自适应
		jqgridResponsive("grid",false);
	});
	$('#do_delete').click(function(){
		var id = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(id)) {
			openErrorForListPage('请选择一条数据',2000);
			return;
		}
		$("#do_delete").attr("disabled",true);
		//开始执行删除动作
		$.post("${ctx}/msg/msgStore/delete", 
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
	function toModify(){
		var id = $("#grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(id)) {
			openErrorForListPage('请选择要编辑的记录！',2000);
			return;
		}
		var timebak = new Date().getTime();
		openDialog("${ctx}/msg/msgStore/openmodalForm?id="+id+"&time="+timebak);
	}
	//弹出新增对话框			
	function toAdd(){
		var timebak = new Date().getTime();
		openDialog("${ctx}/msg/msgStore/openmodalForm?time="+timebak);
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
		addDate(data);
	});
	function addDate(data){
		$.post("${ctx}/msg/msgStore/addDate",data,function(result){
			$("#grid").trigger("reloadGrid");
			$('#dialog-confirm').modal('hide');
			var message = "操作失败！";
			if(result.success){
				message ='操作成功!';
			}
			$('#do_save').attr("disabled",false);
			showResult(result.success,message);
		},'json');
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
	//查询动作
	function doQuery(){  
	     var postData = $("#grid").jqGrid("getGridParam", "postData");  
	     var mydata = {};
	     var startDate =$("#startDate").val();
	     var endDate =$("#endDate").val();
	     var msgTypeName =$("#msgTypeName1").val();
	     var createName =$("#createName").val();
		 if(startDate && ''!=startDate){
			 mydata.startDate = startDate;
		 }else{
			 delete postData.startDate;
		 }
		 if(endDate && ''!=endDate){
			 mydata.endDate = endDate;
		 }else{
			 delete postData.endDate;
		 }
		 if(msgTypeName && ''!= msgTypeName){
			 mydata.msgTypeName = msgTypeName;
		 }else{
			 delete postData.msgTypeName;
		 }
		 if(createName && ''!= createName){
			 mydata.createName = createName;
		 }else{
			 delete postData.createName;
		 }
	    $.extend(postData,mydata);
	    $("#grid").jqGrid("setGridParam", {
	        search: true  
	    }).trigger("reloadGrid", [{page:1}]);
	};
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