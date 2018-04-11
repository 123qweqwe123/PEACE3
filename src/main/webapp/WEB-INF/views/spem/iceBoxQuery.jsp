<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title>样本管理</title> 

</head>
<body>

<ul class="breadcrumb">
  <li class="active">样本管理</li><li class="active" >冰箱容量查询</li>   
</ul>  

<form class="well bs-bdcor form-inline" id="searchForm">

	    项目单位:
	     <input  type="text"  style="display:none;"   class="form-control input-sm" name="lcc_code" id="lcc_code" value=''/>    
         <input type="text"   class="form-control input-sm" name="lcc_name" id="lcc_name" value='' placeholder="选择已有单位 双击选择 "/>               
             
    &nbsp;&nbsp;&nbsp;
    
    &nbsp;&nbsp;&nbsp;
    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
    <!-- <button type="reset" id="reset_btn" class="simpled btn btn-sm btn-primary" >重置</button>-->
</form> 

<div id="alert" class="alert alert-danger" hidden>
  <strong>Warning!</strong>
</div> 

<div id="message" class="alert alert-success" hidden>
    <button data-dismiss="alert" class="close">&times;</button>
    <span id="messageSpanId"></span>
</div>

<div id="jqgrid">
    <table id="grid"></table>
    <div id="pager"></div>
</div>


<div id='dialog-confirm' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">冰箱容量登记</h4>
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


<div id='select-confirm' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">选择冻存盒</h4>
      </div>
      <div class="modal-body">
        <p>加载中……</p>
      </div>
      <div class="modal-footer">
        <button type="button" id ='cancel1' class="btn btn-default btn-sm" tabindex="1001">取消</button>
        <button type="button" id ='do_submit' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
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
        <input type="hidden" id="del_id">
        <button type="button" data-dismiss="modal" class="btn btn-default btn-sm btn-cancel" tabindex="1001">取消</button>
        <button type="button" id ='do_delete' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal --> 

<form id="exportForm"  method="post"> 
	<input type="hidden" name="fridgeIDs" id="fridgeIDs"/>
</form>


<script type="text/javascript">	



//url 固定  参数 : table 指定的表名，  cols  指定查询的列明  where  目前为空 待扩充
$.getJSON("${ctx}/combox/dataLimitLcc",function(data) { 
  $('#lcc_name').autocomplete(data,{
      minChars: 0,
      mustMatch:true, 
      width:260,
     // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
      formatItem: function(item,i, max) {
    	    return '<table><tr><td width="80px;">' + item.LCC_CODE + '</td><td width="180px;">' + item.LCC_NAME + '</td></tr></table>';
      },
      // 指定 与 输入文字匹配的字段名
      formatMatch: function(item,i, max) {
          return item.LCC_NAME;
      },
      // 选中 某条记录在输入框里 显示的数据字段
      formatResult: function(item) {
          return item.LCC_NAME;
      }
  }); 
  //选中 某条记录 触发的事件
   $('#lcc_name').result(function(event, item){ 
	  if(item) 
	  $("#lcc_code").val(item.LCC_CODE); 
	  else 
	  $("#lcc_code").val(''); 
	}); 
}); 
 


$(function(){
	
var option = {
        url : '${ctx}/spem/iceBoxReg/qlist',     
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '','','医院名称','冰箱名称','容量','冻存盒的数量','百分比'], 
        colModel : [ 
                     {name : 'id',index : 'id',hidden : true},  
                     {name : 'lccid', index : 'lccid', align:'left',hidden : true }, 
                     {name : 'lcc_name', index : 'lcc_name', align:'left',sortable: false }, 
                     {name : 'iceboxName', index : 'iceboxName', align:'left' ,sortable: false},
                     {name : 'capacity', index : 'FRIDGE_CAPACITY', align:'right'},
                     {name : 'stock', index : 'CAPACITY_BOX', align:'right'},
                     {name : 'proportion', index : 'PROPORTION',width:'70',align:'right',formatter:function(v){
                    	 try{
                    		 return Math.floor(parseInt(v * 100 )) + '%';
                    	 }catch(Exception){}
                    	 return v;
                     }} 
                   ],         
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],        
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'PROPORTION',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "desc",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        multiselect: true ,
        loadComplete: Gosub
 };  
$("#grid").jqGrid(option); 

$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})

/* 
 * 
 .navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})  
  
.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})  

.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"}) 
 */  
.navButtonAdd('#pager',{caption:"导出样本转运计划",buttonicon:"ui-icon-plus",onClickButton: function(){exportPlan()},position:"last"})   
//自适应
jqgridResponsive("grid",false); 




//提示 预警
function  Gosub(){
	
	
        var ids = $("#grid").getDataIDs();
        for(var id in ids)
        {
        	var str = $("#grid").getCell( ids[id] , 'proportion' );
        	//capacity  stock
        	var capacity =  $("#grid").getCell( ids[id] , 'capacity');
        	
        	var stock    =  $("#grid").getCell( ids[id] , 'stock');
        	
        	var iceboxName    =  $("#grid").getCell( ids[id] , 'iceboxName');
        	
        	var iceboxStatus   =  $("#grid").getCell( ids[id] , 'isExsitWayBill');
        	var intValue = parseInt(iceboxStatus);
        	if( intValue<1 )
        		$("#grid").setCell(ids[id],'isExsitWayBill', "未生成运单",'');
        	if( intValue>=1 )
                $("#grid").setCell(ids[id],'isExsitWayBill', "已生成运单",{background:'#66B3FF'});
        	
        	var floatValue = parseFloat(str);
        	
        	if(floatValue>=50 && intValue<1 )
        	{
        		//$("#grid").setCell(ids[id],'capacity', capacity,{color:'red'});
        		//$("#grid").setCell(ids[id],'stock', stock,{color:'red'});
        		//$("#grid").setCell(ids[id],'iceboxName', iceboxName,{color:'red'});
        		$("#grid").setCell(ids[id],'isExsitWayBill', "未生成运单",{color:'red',background:'#FFFF6F'});
        	}      
        }  
    
}
//弹出新增对话框           
function toAdd(){
    var timebak = new Date().getTime();
    openDialog("${ctx}/spem/iceBoxReg/openmodal?time="+timebak);    
}      
//弹出修改对话框
function toModify(){
    var ids = $("#grid").jqGrid('getGridParam','selarrrow');//'selarrrow');
    if($.isEmptyObject(ids)||ids.length >1) {
        openError('请选择一条数据,且仅选择一条数据',2000);
        return;
    }
    var oneData = $("#grid").jqGrid('getRowData',ids[0]); 
    openDialog("${ctx}/spem/iceBoxReg/openmodal?id="+oneData.id+"&timestamp="+(new Date()).valueOf());
} 

   
   	//导出转运计划
	function exportPlan(){
	 
		var ids = $("#grid").jqGrid('getGridParam','selarrrow');  
	  
		if($.isEmptyObject(ids)) { 
			openError('请选择至少一个冰箱！',2000);
			return; 
		} 
		$('#fridgeIDs').val(ids); 
	    $('#exportForm').attr('action','${ctx}/spem/iceBoxReg/exportPlan?time='+new Date().getTime());
	    $('#exportForm').submit(); 
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

//查询绑定
var multipleSearch = function() {
    
     var postData = $("#grid").jqGrid("getGridParam", "postData");  
     var mydata = {};
     
    
     var lcc_code =$("#lcc_code").val();
     
    
     if(lcc_code && ''!=lcc_code){
         mydata.lcc_code = lcc_code;
     }else{
         delete postData.lcc_code; 
     }  
     
    $.extend(postData,mydata);

    $("#grid").jqGrid("setGridParam", {
        search: true  
    }).trigger("reloadGrid", [{page:1}]);
};

$("#btnQuery").click(multipleSearch);

  


//取消按钮操作
$('#cancel').click(function(){
  $('#dialog-confirm').modal('hide');
}); 
//取消按钮操作
$('#cancel1').click(function(){
  $('#select-confirm').modal('hide');
});


//新增或修改操作
$('#do_save').click(function(){
  var myform = $("#dialog-confirm").find("form").get(0);
  if(!jQuery(myform).validate().form()){ return ;}
  $("#do_save").attr("disabled",true);
  var myform = $("#dialog-confirm").find("form").serializeArray();
  var data = {};
  $.each(myform, function(i, field){
      data[field.name] = field.value;
      
  }); 
  addDate(data); 
});

//新增数据
function addDate(data){
  //新增时注释id值
  //data['id'] = null;
  $.post("${ctx}/spem/iceBoxReg/save",data,function(result){
      $("#grid").trigger("reloadGrid");
      $('#dialog-confirm').modal('hide');
      var message = "操作失败！";
      if(result.success){
          message ='操作成功!';
      }else{
          message = result.msg+'!'; 
      }
      //$('#btn_resetpwd').attr("disabled",false);
      showResult(result.success,message);
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





function openError(message,delay){
  $('#alert').show().find('strong').text(message);
  window.setTimeout(function() {
      $('#alert').slideUp("slow");
  //window.top.location.reload();
  }, delay);
}




    
});  






</script>


</body>
</html>