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
  <li class="active">样本管理</li><li class="active" >冰箱容量登记</li>   
</ul>  

<!-- <form class="well bs-bdcor form-inline" id="searchForm">

	    项目单位:
	     <input type="hidden"   class="form-control input-sm" name="lccid" id="lccid" value=''/>    
         <input type="text"   class="form-control input-sm" name="lcc_name" id="lcc_name" value=''/>               
             
    &nbsp;&nbsp;&nbsp;
    
    &nbsp;&nbsp;&nbsp;
    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
    <button type="reset" id="reset_btn" class="simpled btn btn-sm btn-primary" >重置</button>   

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


$(function(){
	
var option = {
        url : '${ctx}/spem/iceBoxReg/rlist',   
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '','','医院名称','冰箱名称','品牌','规格','容量','操作人','操作时间',''],
        colModel : [ 
                     {name : 'id',index : 'id',hidden : true},  
                     {name : 'lccid', index : 'lccid', align:'left',hidden : true },  
                     {name : 'lcc_name', index : 'lcc_name', align:'left' }, 
                     {name : 'iceboxName', index : 'iceboxName', align:'left' },
                     
                     {name : 'fridge_brand', index : 'fridge_brand', align:'left' },
                     {name : 'fridge_spc', index : 'fridge_spc', align:'left' },
                     
                     {name : 'capacity', index : 'capacity', align:'center'},
                     {name : 'user_name', index : 'user_name', align:'center'},
                     /* {name : 'stock', index : 'stock', align:'center',hidden : true}, */
                     {name : 'regTimeStr', index : 'regTimeStr', align:'center',formatter:yymmddFormatter},
                     {name : 'proportion', index : 'proportion', hidden:true}      
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
        loadonce : true,    
        multiselect: false ,  
        loadComplete: Gosub
 };  
function ct(){
$("#grid").jqGrid(option); 

$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})

.navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"last"})  
  
.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})  

.navButtonAdd('#pager',{caption:"删除",buttonicon:"ui-icon-trash",onClickButton: function(){toDelete()},position:"last"}) 

//自适应
jqgridResponsive("grid",false); 

}

ct();

//提示 预警
function  Gosub(){
	
	
        /* var ids = $("#grid").getDataIDs();
        for(var id in ids)
        {
        	var str = $("#grid").getCell( ids[id] , 'proportion' );
        	//capacity  stock
        	var capacity =  $("#grid").getCell( ids[id] , 'capacity');
        	
        	var stock    =  $("#grid").getCell( ids[id] , 'stock');
        	
        	var iceboxName    =  $("#grid").getCell( ids[id] , 'iceboxName');
        	
        	var floatValue = parseFloat(str);
        	if(floatValue>0.5)
        	{
        		$("#grid").setCell(ids[id],'capacity', capacity,{color:'red'});
        		$("#grid").setCell(ids[id],'stock', stock,{color:'red',background:'yellow'});
        		$("#grid").setCell(ids[id],'iceboxName', iceboxName,{color:'red'});
        	}      
        } */  
    
}
//弹出新增对话框           
function toAdd(){
    var timebak = new Date().getTime();
    openDialog("${ctx}/spem/iceBoxReg/openmodal?time="+timebak);    
}      
//弹出修改对话框
function toModify(){
    var id = $("#grid").jqGrid('getGridParam','selrow');//'selarrrow');
    if($.isEmptyObject(id)) {
        openError('请选择一条数据',2000);
        return;
    }
    var oneData = $("#grid").jqGrid('getRowData',id); 
    openDialog("${ctx}/spem/iceBoxReg/openmodal?id="+oneData.id+"&timestamp="+(new Date()).valueOf());
} 

//弹出删除对话框
function toDelete(){ 
	
    var id = $("#grid").jqGrid('getGridParam','selrow');  
	
    if($.isEmptyObject(id)) { 
        openError('请选择一条数据',2000);
        return;
    }
  
    $("#do_delete").attr("disabled",false);
    $("#dialog-delete").modal({
         keyboard: false
    });   
} 

   function goSelectBox(){
	 var id = $("#grid").jqGrid('getGridParam','selrow');  
	    
	    if($.isEmptyObject(id)) { 
	        openError('请选择一条数据,且仅选择一条数据',2000);
	        return; 
	    } 
	    var oneData = $("#grid").jqGrid('getRowData',id); 
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
            	 $("#jqgrid").html('<table id="grid"></table><div id="pager"></div>');
                 
                 ct(); 
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
         
          
          $("#jqgrid").html('<table id="grid"></table><div id="pager"></div>');
          
          ct();
          
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