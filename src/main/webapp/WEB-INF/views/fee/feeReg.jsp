<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ page import="com.bdcor.pip.core.utils.Securitys"%> 
<!DOCTYPE html>
<html>
<head>
 <title>经费使用情况</title>

</head>
<body>

<div id="navbar">
        <ul class="breadcrumb">
            <li class="active">经费管理</li><li class="active" >中心运作费用登记</li>
        </ul>
</div>
<div id="main-content">
<div id="select">
                <div class="select-main">
               
<form  class="well-work bs-bdcor form-inline" id="searchForm"> 


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
                                    <label form=name>中心部门:</label>
                                 </li>
                                 <li>
                                     <select id="departId" name="departId"  style="width:110px;"   class="form-control input-sm"> 
							              <option   value=''  selected='selected'>请选择</option>
							                <c:forEach items="${dptList}" var="op" >
							                    <option value="${op.deptCode }"} >${op.deptName}</option>  
							                </c:forEach>        
							         </select>
                                 </li>
                                  <li class="select-one">
                                    <label form=name>登记类型:</label>
                                 </li>
                                 <li>
                                     <select id="reg_type" name="reg_type"  style="width:139px;" class="form-control input-sm"> 
                                          <option   value=''  selected='selected'>请选择</option>
                                                  
                                     </select>
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
        <h4 class="modal-title">费用登记</h4>
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


<script type="text/javascript">

$(function(){  
	
	
	
	var date=new Date();
	var date2=new Date();
	date.setDate(date.getDate()-30);
	var dayBefore2 = DateFormat.format(date, 'yyyy-MM-dd');
	date2.setDate(new Date().getDate());
	var dayBefore1 = DateFormat.format(date2,'yyyy-MM-dd');
	//加载日期控件
	initDatePicker(
	        '#duedate1','#bg_date',
	        '#duedate2','#end_date',
	        function(){
	           $("#submitButton").attr('disabled',false);
	           $('#alert').hide();
	        },
	       function(){
	           $("#submitButton").attr('disabled',true);               
	       },dayBefore2,dayBefore1);
	
	//url 固定  参数 : table 指定的表名，  cols  指定查询的列明  where  目前为空 待扩充
	$.getJSON("${ctx}/combox/comboxData?table=PIP_FEE_REGTYPE&cols=TYPE_CODE|TYPE_NAME&where=",function(data) {
	     $.each(data, function(i, field){
	         var opt =  $("<option value='"+field. TYPE_CODE+"'>"+field.TYPE_NAME+"</option>");
	         $('#reg_type').append(opt);
	      }); 
	});
	
	
   var option = {
        url : '${ctx}/fee/getFeeRegList', 
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '','项目名称','登记类型','中心部门','金额(元)','使用时间'],
        colModel : [ {name : 'id',index : 'FEE_ID',hidden : true},
                     {name : 'projectName', index : 'projectName', align:'left',sortable: false },
                     {name : 'reg_type', index : 'reg_type', align:'left',sortable: false },
                     {name : 'departName', index : 'departName', align:'center',sortable: false},
                     {name : 'amount', index : 'amount' ,sortable: true, formatter:'currency', formatoptions:{decimalSeparator:",", thousandsSeparator: ",", 
                      decimalPlaces:0, prefix: "",defaulValue:0}},
                     {name : 'reg_dateString', index : 'fn.REG_TIME' }],  
        rowNum : 10, 
        rowList : [ 10, 15, 30 ], 
        height : "100%",
        autowidth : true, 
        pager : '#pager', 
        sortname : 'fn.REG_TIME',
        altRows:true, 
        hidegrid : false, 
        viewrecords : true, 
        recordpos : 'left', 
        sortorder : "desc",
        postData : {bg_date:$("#bg_date").val(), end_date:$("#end_date").val(),departId:$("#departId").val(),reg_type:$("#reg_type").val()},
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
    openDialog("${ctx}/fee/openmodal_feeRegForm?time="+timebak);
}   

//弹出修改对话框
function toModify(){
    var ids = $("#grid").jqGrid('getGridParam','selarrrow');
    if($.isEmptyObject(ids)||ids.length >1) {
        openError('请选择一条数据,且仅选择一条数据',2000);
        return;
    }
    var oneData = $("#grid").jqGrid('getRowData',ids[0]); 
    openDialog("${ctx}/fee/openmodal_feeRegForm?id="+oneData.id+"&timestamp="+(new Date()).valueOf()); 
}

//弹出删除对话框
function toDelete(){
    var ids = $("#grid").jqGrid('getGridParam','selarrrow');
     
    if($.isEmptyObject(ids) || ids.length >1) { 
        openError('请选择一条数据,且仅选择一条数据',2000);
        return;
    }     
    for(var i=0; i<ids.length; i++ ){
        var row = $("#grid").jqGrid('getRowData', ids[i]);
        if(row.isAdmin==2 || row.isAdmin ==1){
            openError('不能删除管理员',2000);
            return;
        }
    }   
    $("#do_delete").attr("disabled",false);
    $("#dialog-delete").modal({
         keyboard: false
    });
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



$("#select_btn").click(function(e){
     var timebak = new Date().getTime();
     var url ='${ctx}/fee/openmodalSelectOrgan?time='+timebak; 
    
     
     
     
    }
 );   
 

    
 
var multipleSearch = function() {
     var postData = $("#grid").jqGrid("getGridParam", "postData");  
     var mydata = {};
     var bg_date    =  $("#bg_date").val();
     var end_date   =  $("#end_date").val(); 
     var departId   =  $("#departId").val();  
     
     var reg_type   =  $("#reg_type").val();  
     
     if(bg_date && ''!=bg_date){
         mydata.bg_date = bg_date;
     }else{
         delete postData.bg_date;
     }
     if(end_date && ''!= end_date){
         mydata.end_date = end_date;
     }else{
         delete postData.end_date;
     }
     if(departId && ''!= departId){
         mydata.departId = departId;
     }else{
         delete postData.departId; 
     } 
     if(reg_type && ''!= reg_type){
         mydata.reg_type = reg_type;
     }else{
         delete postData.reg_type; 
     } 
    $.extend(postData,mydata); 
    
    $("#grid").jqGrid("setGridParam", {
        search: true  
    }).trigger("reloadGrid", [{page:1}]); 
};

$("#btnQuery").click(multipleSearch); 

multipleSearch.call();


//取消按钮操作
$('#cancel').click(function(){
    $('#dialog-confirm').modal('hide');
}); 


//新增或修改操作
$('#do_save').click(function(){
    var myform = $("#dialog-confirm").find("form").get(0);
    $(myform).find("#departName").val($(myform).find("#departId option:selected").text() ); 
    
    if(!jQuery(myform).validate().form()){ return ;}
    $("#do_save").attr("disabled",true);
    var myform = $("#dialog-confirm").find("form").serializeArray();
    var data = {};
    $.each(myform, function(i, field){
        data[field.name] = field.value;
     }); 
    
    if( $.trim(data.id) =="")
    {
    	
    	addDate(data);
    }
    else 
    {
    	updateDate(data);
    }    
    
 });

//新增数据
function addDate(data){
    //新增时注释id值
    //data['id'] = null;
    $.post("${ctx}/fee/save",data,function(result){
        $("#grid").trigger("reloadGrid",[{
            page : 1
        }
    ]);
        $('#dialog-confirm').modal('hide');
        var message = "新增失败！";
        if(result.success){
            message ='新增成功!';
        }else{
            message = result.msg+'!';
        }
        //$('#btn_resetpwd').attr("disabled",false);
        showResult(result.success,message);
    },'json');
} 

//修改数据
function updateDate(data){

    $.post("${ctx}/fee/update",data,function(result){
        $("#grid").trigger("reloadGrid");
        $('#dialog-confirm').modal('hide');
        var message = "更新失败！";
        if(result.success){
            message = "更新成功！";
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


//删除一条记录操作
$('#do_delete').click(function(){
    
    var ids = $("#grid").jqGrid('getGridParam','selarrrow');
   // alert(ids[0])
        
    $("#do_delete").attr("disabled",true);   
    //开始执行删除动作
    $.post("${ctx}/fee/delete", 
            {id:ids[0] },      
        function(data){  
            $("#grid").trigger("reloadGrid");
            $('#dialog-delete').modal('hide');
            var message = "删除失败！";
            if(data.success){
                message = "删除成功！";
            }else{
                message = data.msg+"!";
            }
            showResult(data.success,message);
        }, "json");   
});         


function openError(message,delay){
    $('#alert').show().find('strong').text(message);
    window.setTimeout(function() {
        $('#alert').slideUp("slow");
    //window.top.location.reload();
    }, delay);
}

});
</script>

</div>
</body>
</html>