<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
 <title>样本管理</title> 
<style type="text/css">
.bc1{
    cursor:pointer;
    border-right: #F1E1FF 3px ridge !important; 
    border-top: #F1E1FF 3px ridge !important; 
    border-left: #F1E1FF 3px ridge !important; 
    border-bottom: #F1E1FF 3px ridge !important;
   text-align:center;
}

.bc1:hover {
    
    background-color:   #9393FF !important;
    border-right:   #FFFFFF 3px ridge !important; 
    border-top:     #FFFFFF 3px ridge !important; 
    border-left:    #FFFFFF 3px ridge !important; 
    border-bottom:  #FFFFFF 3px ridge !important;
}

</style>

</head>
<body>

<ul class="breadcrumb">
  <li class="active">样本管理</li><li class="active" >运送单明细</li>   
</ul>  



<div id="alert" class="alert alert-danger" hidden>
  <strong>Warning!</strong>
</div> 

<div id="message" class="alert alert-success" hidden>
    <button data-dismiss="alert" class="close">&times;</button>
    <span id="messageSpanId"></span>
</div>

  
  
<div id='dialog-delete' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">  
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body">
        <p> <span id="opMessageSpanId"></span> </p>
      </div>
      <div class="modal-footer">
        <input type="hidden" id="del_id">
        <button type="button"  id ='do_continue'   class="btn btn-primary btn-sm" tabindex="1001">继续生成</button>
        <button type="button"  id ='do_eidt' class="btn btn-primary btn-sm" tabindex="1000">编辑样本运送单</button> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  

 
    
    
    
<div class='col-lg-10 col-md-offset-1  col-md-10'>


<div class="panel panel-default">
  <div class="panel-heading"> <lable>基本信息: </lable></div>
  <div class="panel-body">
      <div class='row'> 
        <div class='col-lg-6   col-md-6' > <label>运单号:</label> <span id='waybill_no' value='${vo.waybill_no}'>${vo.waybill_no}</span>   </div>
        <div class='col-lg-6   col-md-6' > <label>项目号:</label> <span>${vo.projectId}</span>   </div>
      </div> 
      <div class='row'> 
        <div class='col-lg-6   col-md-6' > <label>当前状态:</label> <span>${vo.event_name}</span>   </div>
        <div class='col-lg-6   col-md-6' > <label>项目单位:</label> <span>${vo.lcc_name}</span>   </div>
      </div> 
       <%-- <div class='row'> 
        <div class='col-lg-12   col-md-12' > <label>物流公司单号:</label> <span>${vo.waybill_no}</span>   </div>
         
      </div>  --%> 
  </div>
  
  </div>
 
</div>
<br>
<br>
<div class='col-lg-10 col-md-offset-1  col-md-10'>
<div class="panel panel-default">
  <div class="panel-heading"> <lable>历史状态列表: </lable></div>
  <div class="panel-body">

   <div id="jqgrid2">
       <table id="grid2"></table>
       <div id="pager2"></div> 
   </div>
<script type="text/javascript">
var option = {   
        url : '${ctx}/spem/waybill/bill_detail_evList?waybill_no='+$("#waybill_no").attr('value'),   
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '操作','操作人','操作时间'],
        colModel : [ {name : 'event_name',index : 'event_name',sortable: false}, 
                     {name : 'liableSbName', index : 'liableSbName', align:'left' ,sortable: false}, 
                     {name : 'createDate', index : 'createDate', align:'left',formatter:yyyyMMddhhmmDateFormatter,sortable: false}
                     
                   ],      
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],     
        height : "100%",
        autowidth : true,  
        pager : '#pager2',  
        sortname : 'createDate',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "desc",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        loadComplete: function (){}
 };  
$("#grid2").jqGrid(option); 
$("#grid2").jqGrid('navGrid', '#pager2', {edit : false, add : false, del : false, search : false, position : 'right'})


</script>

</div>
</div>
</div>


<div  class='col-lg-10 col-md-offset-1  col-md-10'    > 

<div class="panel panel-default">
  <div class="panel-heading">冻存盒列表:</div>
  <div class="panel-body">
       <div id="jqgrid3">
	       <table id="grid3"></table>
	       <div id="pager3"></div> 
       </div> 
       
       
<script type="text/javascript">
var option = {   
        url : '${ctx}/spem/waybill/bill_detail_boxList?waybillno='+$("#waybill_no").attr('value'),   
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '冻存盒编码','冻存盒类型','项目单位'],
        colModel : [ 
                     {name : 'id',index : 'id',sortable: false}, 
                     {name : 'boxType', index : 'boxType', align:'left' ,sortable: false}, 
                     /* {name : 'statusCode', index : 'statusCode', align:'left',sortable: false}, */
                    /*  {name : 'iceboxId', index : 'iceboxId', align:'left' },  */
                     {name : 'lcc_name', index : 'lcc_name', align:'left' ,sortable: false}
                   ],       
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],     
        height : "100%",
        autowidth : true,  
        pager : '#pager3',  
        sortname : 'id',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "desc",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        loadComplete: function (){}
 };  
$("#grid3").jqGrid(option); 
$("#grid3").jqGrid('navGrid', '#pager3', {edit : false, add : false, del : false, search : false, position : 'right'})


</script>
    
</div>
</div>

</div>
 
<br>
<br>
<div class='col-lg-10 col-md-offset-1  col-md-10'>
<div class="panel panel-default">
  <div class="panel-heading"> <lable>冻存管列表:  </lable></div>
  <div class="panel-body">

<div id="jqgrid">
   <table id="grid"></table>
    <div id="pager"></div>
</div>

       
<script type="text/javascript">

var option = {   
        url : '${ctx}/spem/waybill/bill_detail_tubeList?waybillno='+$("#waybill_no").attr('value'),   
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '冻存管编号','项目单位','类型','冻存盒内行位置','冻存盒内列位置','冻存盒编号'],
        colModel : [ {name : 'TUBE_CODE',index : 'TUBE_CODE',sortable: false}, 
                     {name : 'LCC_NAME', index : 'LCC_NAME', align:'left' ,sortable: false}, 
                     {name : 'TUBE_TYPE', index : 'TUBE_TYPE', align:'left',sortable: false}, 
                     {name : 'BOX_COLNO', index : 'ROW_NUM', align:'center',sortable: false}, 
                     {name : 'BOX_ROWNO', index : 'COL_NUM',sortable: false},
                     {name : 'BOX_CODE', index : 'BOX_CODE',sortable: false} 
                   ],   
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],     
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'TUBE_CODE',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "desc",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        loadComplete: function (){}
 };  
$("#grid").jqGrid(option); 
$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})
</script>



</div>
</div>
</div>
<form  id="createBill" action=""  method="post">  
<div class='col-lg-10 col-md-offset-1  col-md-10'>  
<div class="panel panel-default">  
  <div class="panel-heading"><lable>备注信息: </lable></div>  
  <div class="panel-body">  
 
<textarea   rows="3" id="remark"  readonly=true class="form-control" name="remark" placeholder="备注">${vo.remark}</textarea>
</div>
</div>
</div>
<div class="col-lg-3 col-md-offset-10 col-md-3">
<button type="button" id="btnExport" class="btn btn-primary btn-sm">导出excel</button>&nbsp;
      <button type="button" id="btnBack" class="btn btn-primary btn-sm">返回</button>
      <!-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" id="btnCreatSpem" class="btn btn-primary btn-sm">确认提交</button> -->
  </div>
  
  <input type="hidden"   id='waybill_no' name='waybill_no' value="${vo.waybill_no}"  /> 
  <input type="hidden" name='type' value='2'  /> 
</form>
<script type="text/javascript">	
 


//弹出删除对话框
function toDelete(){ 
    
    var ids = $("#grid").jqGrid('getGridParam','selarrrow');  
    
    if($.isEmptyObject(ids) || ids.length >1) { 
        openError('请选择一条数据,且仅选择一条数据',2000);
        return;
    }
  
    $("#do_delete").attr("disabled",false);
    $("#dialog-delete").modal({
         keyboard: false
    });   
}




/* function createTable(obj){
    var id   =  $(obj).html();
  
    $("#jqgrid").html('<table id="grid"></table><div id="pager"></div>'); 
   var option = {   
        url : '${ctx}/spem/iceBoxReg/microList?boxId='+id,   
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '冻存管编号','项目单位','类型','冻存盒内行位置','冻存盒内列位置','冻存盒编号','病人ID','病人姓名','出生日期'],
        colModel : [ {name : 'TUBE_CODE',index : 'TUBE_CODE'}, 
                     {name : 'LCC_NAME', index : 'LCC_NAME', align:'left' }, 
                     {name : 'TUBE_TYPE', index : 'TUBE_TYPE', align:'left'}, 
                     {name : 'BOX_COLNO', index : 'ROW_NUM', align:'center'}, 
                     {name : 'BOX_ROWNO', index : 'COL_NUM'},
                     {name : 'BOX_CODE', index : 'BOX_CODE'},
                     {name : 'BOX_ROWNO', index : 'COL_NUM'},
                     {name : 'BOX_ROWNO', index : 'COL_NUM'},
                     {name : 'BOX_ROWNO', index : 'COL_NUM'}
                   ],      
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],     
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'proportion',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "desc",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        loadComplete: function (){}
 };  
$("#grid").jqGrid(option); 
$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})

} */

//自适应
//jqgridResponsive("grid",false); 



$('#btnBack').click(function(){
	history.go(-1);
})

$('#btnCreatSpem').click(function(){
	
	 var myform = $("#createBill");
     $("#btnCreatSpem").attr("disabled",true);
     var myform = myform.serializeArray();
     var data = {};
     $.each(myform, function(i, field){
         data[field.name] = field.value;
     }); 
     createBill(data); 
   
	/* $('#createBill').attr('action','${ctx}/spem/waybill/createWayBill');
    $('#createBill').submit();  */
})   
   
    //新增数据
    function createBill(data){
      
      $.post("${ctx}/spem/waybill/createWayBill",data,function(result){
         
          var message = "操作失败！";
          if(result.success){
              message ='操作成功!';
          }else{
              message = result.msg+'!'; 
          }
          
          
          $("#opMessageSpanId").text(message);
          $("#dialog-delete").modal({
              keyboard: false
          }); 
          //$('#btn_resetpwd').attr("disabled",false);
         // showResult(result.success,message);
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
  $('#btnExport').click(function(){
		$('#createBill').attr('action','${ctx}/spem/waybill/export2excel');
	    $('#createBill').submit();  
	})
</script>


</body>
</html>