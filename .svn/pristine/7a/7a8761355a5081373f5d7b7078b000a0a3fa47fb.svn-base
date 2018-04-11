<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page import="com.bdcor.pip.core.utils.Securitys"%> 
<!DOCTYPE html>
<html>
<head>
 <title>经费管理</title> 

</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
        <li class="active">经费管理</li><li class="active" >详细条目记录</li>
      </ul>
</div>
  
<div id="main-content">
<div id="select">
                <div class="select-main">
<form class="well-work bs-bdcor form-inline" id="searchForm"> 
<fieldset>
                        <ol>
                            <li class="select-one"> <label form=name>开始时间:</label></li>
                            <li> 
                                <div class="input-group date" id="duedate1">
                                <input class="form-control input-sm"  id="bg_date" name="bg_date" type="text" style="width:86px;" readonly />
                                <span class="input-group-addon input-sm btn">
                                <i class="glyphicon glyphicon-calendar"></i>
                                </span>
                                  </div>
                                 </li>
                                 <li class="select-one"> <label form=name>结束时间:</label></li>
                                 <li> 
                                   <div class="input-group date"  id="duedate2">
                                   <input class="form-control input-sm" id="end_date" name="end_date" type="text" style="width:86px;" readonly />
                                    <span class="input-group-addon input-sm btn"> 
                                    <i class="glyphicon glyphicon-calendar"></i>
                                   </span> 
                                   </div>
                                  </li>
                                 
                                  <li class="select-one">
                                    <label form=name>选择项目单位:</label>
                                 </li>
                                 <li> 
                                   <input type="text"  style="display:none;"  class="form-control input-sm" name="lcc_code" id="lcc_code" value=''/>    
                                   <input type="text"   class="form-control input-sm" name="lcc_name" id="lcc_name" value='' placeholder="选择已有单位 双击选择 "/>               
                  
                                 </li> 
                                 <li> <button type="button" id="btnQuery" class="simpled btn btn-sm btn-primary" >查询</button></li>
                            </ol>


      </fieldset>   

</form>
</div>
</div>
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
        <h4 class="modal-title">实际费用登记</h4>
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

</div>

<script type="text/javascript"> 
 
//url 固定  参数 : table 指定的表名，  cols  指定查询的列明  where  目前为空 待扩充
$.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_LCC&cols=LCC_NAME|LCC_CODE|ENGLISH_NAME&where=",function(data) { 
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
        url : '${ctx}/fee/LccReg/getLccRegList',   
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '','lccid','项目单位','费用类型','实际费用(元)','花费日期'], 
        colModel : [ 
                     {name : 'id',index : 'id',hidden : true},
                     {name : 'lccid', index : 'lccid', align:'left',hidden : true },
                     {name : 'lcc_name', index : 'lcc_name', align:'left', sortable:false },
                     {name : 'fee_type', index : 'fee_type', align:'left', sortable:false },
                     {name : 'actualCost', index : 'ACTUAL_COST', align:'left', formatter:'currency', formatoptions:{decimalSeparator:",", thousandsSeparator: ",", 
                         decimalPlaces:0, prefix: "",defaulValue:0} },
                     {name : 'costDate', index : 'COST_DATE', align:'center',formatter:yymmddFormatter},
                   ],  
        rowNum : 15, 
        rowList : [ 15, 30, 50,100,150,500 ],  
        height : "100%", 
        autowidth : true,  
        pager : '#pager',  
        sortname : 'lccid,r.COST_DATE',  
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "desc",
        emptyrecords : "没有可显示记录", 
        loadonce : false,
        multiselect: true 
 };  
$("#grid").jqGrid(option);

$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})

.navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})  
 
.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})  

.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"}) 
    
//自适应
jqgridResponsive("grid",false);  

//弹出新增对话框           
function toAdd(){
    var timebak = new Date().getTime();
    openDialog("${ctx}/fee/LccReg/openmodal_feePlanForm?time="+timebak);   
}      
//弹出修改对话框
function toModify(){
    var ids = $("#grid").jqGrid('getGridParam','selarrrow');//'selarrrow');
    if($.isEmptyObject(ids)||ids.length >1) {
        openError('请选择一条数据,且仅选择一条数据',2000);
        return;
    }
    var oneData = $("#grid").jqGrid('getRowData',ids[0]); 
    openDialog("${ctx}/fee/LccReg/openmodal_feePlanForm?id="+oneData.id+"&timestamp="+(new Date()).valueOf());
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


//删除一条记录操作
$('#do_delete').click(function(){
    
    var _ids = $("#grid").jqGrid('getGridParam','selrow');
    if($.isEmptyObject(_ids)) { 
        openError('请选择一条数据',2000);
        return;
    }
    
    $("#do_delete").attr("disabled",true);
    
  //开始执行删除动作
    $.post("${ctx}/fee/LccReg/delete",  
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
     var data={};
     var myform = $("#searchForm").serializeArray(); 
     $.each(myform, function(i, field){
         data[field.name]=null; 
         
         if(field.value && ''!=field.value){
            data[field.name] = field.value;
         }else{
        	 delete data[field.name]; 
        	 delete postData[field.name]; 
         }
      }); 
     
    $.extend(postData,data);

    $("#grid").jqGrid("setGridParam", {
        search: true  
    }).trigger("reloadGrid", [{page:1}]);
};

$("#btnQuery").click(multipleSearch);

$("#select_btn").click(function(e){
     var timebak = new Date().getTime();
     var url ='${ctx}/fee/LccReg/openmodalSelectOrgan?time='+timebak; 
    } 
 );   

var date=new Date();
date.setDate(date.getDate()-20);
var dayBefore2 = DateFormat.format(date, 'yyyy-MM-dd');
date.setDate(new Date().getDate());
var dayBefore1 = DateFormat.format(new Date(),'yyyy-MM-dd'); 
//加载日期控件
initDatePicker(
        '#duedate1','#bg_date',
        '#duedate2','#end_date',
        function(){
           //$("#submitButton").attr('disabled',false);
           $('#alert').hide();
        },
       function(){
           //$("#submitButton").attr('disabled',true);               
       },dayBefore2,dayBefore1); 
  });   






//取消按钮操作
$('#cancel').click(function(){
    $('#dialog-confirm').modal('hide');
});   


//新增或修改操作
$('#do_save').click(function(){
    var myform = $("#dialog-confirm").find("form").get(0);
    if(!jQuery(myform).validate( ).form()){ return ;}
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
    $.post("${ctx}/fee/LccReg/save",data,function(result){
        $("#grid").trigger("reloadGrid",[{
            page : 1
        }
    ]);
        $('#dialog-confirm').modal('hide');
        var message = "操作失败！";
        if(result.success){
            message ='操作成功!';
        }else{
            message = result.msg+'!'; 
        }
       
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


</script>


</body>
</html>