<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title>样本管理</title> 
<style type="text/css">

#grid  td{
   vertical-align:middle;
   height:35;
}
</style>
</head>
<body>

<ul class="breadcrumb">
  <li class="active">样本管理</li><li class="active" >项目单位接收通知管理</li>   
</ul>  

<!-- <form class="well bs-bdcor form-inline" id="searchForm">
       <div  class="row"> 
        <div  class="col-lg-2 col-xl-1 col-md-offset-1 col-md-2"> 
            单号:<input type="text"   class="form-control input-sm" name="waybill_no" id="waybill_no" value=''/>
        </div> 
        <div  class="col-lg-2  col-md-2">
      冻存盒号:<input type="text"   class="form-control input-sm" name="boxCodes" id="boxCodes" value=''/>
       </div> 
    </div> 
    <div  class="row" > 
    <div  class="col-lg-2 col-xl-1 col-md-offset-1 col-md-2">
           状态:  <select id="state" name="state" class="form-control"  >
                 <option>请选择</option>     
            </select>   
     </div> 
    
      <div  class="col-lg-2  col-xl-2  col-md-2">
	    项目单位:
	     <input type="hidden"   class="form-control input-sm" name="lccid" id="lccid" value=''/>    
         <input type="text"   class="form-control input-sm" name="lcc_name" id="lcc_name" value=''/>               
       </div>      
   
     <div  class="col-lg-2  col-xl-2  col-md-2">
    &nbsp;&nbsp;&nbsp;
    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
    <button type="reset" id="reset_btn" class="simpled btn btn-sm btn-primary" >重置</button> 
    </div>  
   </div>
</form>  -->

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


<div id='dialog-submit' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">  
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body">
        <p>继续提交将完成运单的本环节处理，是否继续？</p> 
      </div>
      <div class="modal-footer">
        <input type="hidden" id="op_id">
        <button type="button" id ='submit_cancel' class="btn btn-default btn-sm btn-cancel" tabindex="1001">取消</button>
        <button type="button" id ='do_continue' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  

<script type="text/javascript">	




//url 固定  参数 : table 指定的表名，  cols  指定查询的列明  where  目前为空 待扩充
$.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_LCC&cols=LCC_NAME|LCC_CODE|ENGLISH_NAME&where=",function(data) { 
    $('#lcc_name').autocomplete(data,{

        // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
        formatItem: function(item,i, max) {
            return '<table><tr><td width="100">' + item.LCC_CODE + '</td><td>' + item.LCC_NAME + '</td></tr></table>';
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
        $('#lccid').val(item.LCC_CODE);
  });  
}); 



//url 固定  参数 : table 指定的表名，  cols  指定查询的列明  where  目前为空 待扩充
$.getJSON("${ctx}/combox/comboxData?table=PIP_SCM_EVENTDICT&cols=EVENT_CODE|EVENT_NAME&where=",function(data) {
	 $.each(data, function(i, field){
	     var opt =  $("<option value='"+field. EVENT_CODE+"'>"+field.EVENT_NAME+"</option>");
	     $('#state').append(opt);
	  }); 
});


$(function(){
	
var option = {
        url : '${ctx}/spem/waybill/list',    
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '项目单位','运送单号','','冻存盒','申请人','申请时间','上一经手人','上一经手时间','物流公司单号','当前状态','','操作'],
        colModel : [ 
                     {name : 'lcc_name', index : 'l.lcc_name', align:'left',sortable: false  },  
                     {name : 'waybill_no',index : 'sw.WAYBILL_NO'}, 
                     {name : 'projectId', index : 'projectId', align:'left',hidden : true}, 
                     {name : 'boxCodes', index : 'boxCodes', align:'left',sortable: false,hidden : true  }, 
                     {name : 'applyer_name', index : 'applyer_name', align:'center',sortable: false }, 
                     {name : 'applyDate', index : 'sw.APPLY_DATE', align:'center',formatter:yymmddFormatter}, 
                     {name : 'liable_sb', index : 'liable_sb', align:'center',sortable: false },
                     {name : 'event_date', index : 'event_date', align:'center',formatter:yymmddFormatter,sortable: false } ,
                     {name : 'projectId', index : 'projectId', align:'left',hidden : true }, 
                     {name : 'event_name', index : 'event_name',sortable: false },
                     {name : 'event_code', index : 'event_code',hidden:true} ,
                     {name : 'op', index : 'op',sortable: false } 
                   ],          
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],      
        height : "100%",
        autowidth : true,  
        pager : '#pager',  
        sortname : 'sw.APPLY_DATE,l.lcc_name,sw.WAYBILL_NO', 
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
.navButtonAdd('#pager',{caption:"查看详细",buttonicon:"ui-icon-info",onClickButton: function(){view()},position:"last"}) 


/* .navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"}) 
 */

//自适应
jqgridResponsive("grid",false); 




//提示 预警
function  Gosub(){
	    var ids = $("#grid").getDataIDs();
        for(var id in ids)
        {
        	var str = $("#grid").getCell( ids[id] , 'event_code' );
        	var value = parseInt(str);
        	
        	if(value==1){
        		//var btn = "<button type='button' onclick='completeCurrentStatus("+value+")' class='btn btn-primary btn-xs disabled' >等待处理</button>";
                
        		var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs disabled' >等待处理</button>"; 
        		$("#grid").setCell(ids[id],'op',btn, {height:35} ); 
        	}else if(value==2){ 
        		var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs' >确认请求</button>"; 
                $("#grid").setCell(ids[id],'op',btn, {height:35} ); 
                
            }else if(value==3){ 
            	//var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs' >派遣车辆</button>";
                var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs disabled' >等待处理</button>";
                $("#grid").setCell(ids[id],'op',btn, {height:35} );
                
            }else if(value==4){
            	var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs' >交付样本</button>"; 
                //var btn = "<button type='button' onclick='completeCurrentStatus("+value+")' class='btn btn-primary btn-xs disabled' >等待处理</button>";
                
            	$("#grid").setCell(ids[id],'op',btn, {height:35} );
                
            }else if(value==5){
            	//var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs' >接收</button>";
            	var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs disabled' >等待处理</button>";
                $("#grid").setCell(ids[id],'op',btn, {height:35} );
                
            }else if(value==6){
            	//var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs' >发起请求</button>";
                var btn = "<button type='button' onclick='completeCurrentStatus("+ids[id]+")' class='btn btn-primary btn-xs disabled' >完成</button> ";
                $("#grid").setCell(ids[id],'op',btn, {height:35} );
            } 
        }  
 } 
 
 
//弹出删除对话框
function view(){ 
    var ids = $("#grid").jqGrid('getGridParam','selarrrow');  
    
    if($.isEmptyObject(ids) || ids.length >1) { 
        openError('请选择一条数据,且仅选择一条数据',2000);
        return;
    }  
    var oneData = $("#grid").jqGrid('getRowData',ids[0]); 
    var url = "${ctx}/spem/waybill/bill_detail?waybill_no="+oneData.waybill_no+"&timestamp="+(new Date()).valueOf(); 
    window.location.href=url;  
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

   function goSelectBox(){
	 var ids = $("#grid").jqGrid('getGridParam','selarrrow');  
	    
	    if($.isEmptyObject(ids) || ids.length >1) { 
	        openError('请选择一条数据,且仅选择一条数据',2000);
	        return; 
	    } 
	    var oneData = $("#grid").jqGrid('getRowData',ids[0]); 
	    var url = "${ctx}/spem/iceBoxReg/selectBox?id="+oneData.id+"&timestamp="+(new Date()).valueOf(); 
	    window.location.href=url;  
	    
	   /*  $( "#select-confirm" ).modal({
	         backdrop: 'static'
	    });  */  
	    //$( "#do_save").attr("disabled",false);
	    //使用此方法防止js缓存不加载
	   // $("#select-confirm p" ).load(url); 
   }       


//删除一条记录操作
$('#do_delete').click(function(){
    
    var _ids = $("#grid").jqGrid('getGridParam','selrow');
    if($.isEmptyObject(_ids)) { 
        openError('请选择一条数据',2000);
        return;
    }
    
    $("#do_delete").attr("disabled",true);
    
  //开始执行删除动作
    $.post("${ctx}/spem/iceBoxReg/delete",  
            {id :_ids }, 
        function(data){
            $("#grid").trigger("reloadGrid");
            $('#dialog-delete').modal('hide');
            var message = "删除失败！";
            if(data){ 
                message = "删除成功！";
            }
            showResult(data,message);
        },  
        "json");
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

//查询绑定
var multipleSearch = function() {
    
     var postData = $("#grid").jqGrid("getGridParam", "postData");  
     var mydata = {};
     
     var lccid =$("#lccid").val();
     
     if(lccid && ''!=lccid){
         mydata.lccid = lccid;
     }else{
         delete postData.lccid; 
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


//取消按钮操作
$('#submit_cancel').click(function(){
   $('#dialog-submit').modal('hide');
}); 


function completeCurrentStatus(id){
    $("#op_id").val(id);
    $("#dialog-submit").modal({
        keyboard: false
    }); 
}

$('#dialog-submit').on('hide.bs.modal', function (e) {
    $("#op_id").val('');
})

$('#do_continue').click(function(){
  var id = $("#op_id").val( );
  $('#dialog-submit').modal('hide');
  
  continueProcess(id);
  
}); 


 
function continueProcess(id){ 
    var current_event_code =  $("#grid").getCell( id , 'event_code'); 
    var waybill_no           =  $("#grid").getCell( id , 'waybill_no'); 
    var data = {}; 
    data['current_event_code'] = current_event_code; 
    data['waybill_no'] = waybill_no; 
    
    
    $.post("${ctx}/spem/waybill/processBill",data,function(result){
        $("#grid").trigger("reloadGrid");
        //$('#dialog-confirm').modal('hide');
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





</script>


</body>
</html>