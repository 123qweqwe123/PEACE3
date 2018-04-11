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
  <li class="active">样本管理</li><li class="active" >确认样本运送单</li>   
</ul>  



<div id="alert" class="alert alert-danger" hidden>
  <strong>Warning!</strong>
</div> 

<div id="message" class="alert alert-success" hidden>
    <button data-dismiss="alert" class="close">&times;</button>
    <span id="messageSpanId"></span>
</div>

  
  
<div id='dialog-continue' class="modal fade"> 
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">  
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">提示</h4>
      </div>
      <div class="modal-body">
        <p> 确定要提交表单吗？ </p>
      </div>
      <div class="modal-footer">
        <input type="hidden" id="del_id">
        <button type="button"  id ='cancel'  class="btn btn-primary btn-sm" tabindex="1001">取消</button>
        <button type="button"  id ='do_submit' class="btn btn-primary btn-sm" tabindex="1000">提交</button> 
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->  
    
    
<div class='col-lg-10 col-md-offset-1  col-md-10'>


<div class="panel panel-default">
  <div class="panel-heading"> <lable>基本信息: </lable></div>
  <div class="panel-body">
 
<label>运单号:</label> <span>${id}</span>
  </div>
  
  </div>
 
</div>
<br>
<br>
<div  class='col-lg-10 col-md-offset-1  col-md-10'    > 

<div class="panel panel-default">
  <div class="panel-heading">冻存盒列表:</div>
  <div class="panel-body">

<table   class="table table-bordered" >
 <%
 
	 String s[] ={};
     try{
	 String dataStr =   request.getAttribute("boxdata").toString() ;
	 s = dataStr.split(",");
     }catch(Exception e){
    	 e.printStackTrace(); 
     } 
 	 String c[] = {"#ECECFF", "#D2E9FF", "#ECFFFF", "#E8FFF5",
 			"#DFFFDF", "#FCFCFC", "#FFD2D2", "#FFECF5", "#FFE6FF",
 			"#F1E1FF", "#EFFFD7", "#FFFFDF", "#FFF8D7", "#FFEEDD",
 			"#FFE6D9", "#EBD6D6", "#DEDEBE", "#C4E1E1", "#E6E6F2",
 			"#E2C2DE"};  

 	
 	StringBuffer html = new StringBuffer();
 	int color = 0;
 	for (int i = 0; i < s.length; i++) {

 		if (i % 6 == 0) {
 			html.append("<tr class='row' ></tr>");
 			color = (int) (Math.random() * c.length);
 		}

 		html.insert(html.length() - 5,
 				"<td  class='col-md-2 bc1' onclick='createTable(this);'  style='background-color:"
 						+ c[color] + ";float:left'></td>");
 		html.insert(html.length() - 10, s[i]);

 	}

 	out.println(html.toString());
 	
 	
 	
 %>
</table>
</div>
</div>
<!-- </fieldset>  -->
</div>
 
<br>
<br>
<div class='col-lg-10 col-md-offset-1  col-md-10'>
<div class="panel panel-default">
  <div class="panel-heading"> <lable>冻存管列表: </lable></div>
  <div class="panel-body">

<div id="jqgrid">
   <!--  <table id="grid"></table>
    <div id="pager"></div> --> 
</div>
</div>
</div>
</div>
<form  id="createBill" action=""  method="post">  
<div class='col-lg-10 col-md-offset-1  col-md-10'>  
<div class="panel panel-default">  
  <div class="panel-heading"><lable>备注信息: </lable></div>  
  <div class="panel-body">  
 
<textarea   rows="3" id="remark"  readonly=true class="form-control" name="remark" placeholder="备注">${remark}</textarea>
</div>
</div>
</div>
<div class="col-md-offset-7 col-md-5">
 	  <button type="button" id="btnExport" class="btn btn-primary btn-sm">导出excel</button>
      &nbsp;
      <button type="button" id="btnBack" class="btn btn-primary btn-sm">重新选择冻存盒</button>
      &nbsp;
      <button type="button" id="btnCreatSpem" class="btn btn-primary btn-sm">确认提交</button>
  </div>
  
  <input type="hidden"   id='boxCodes' name='boxCodes' value='${boxdata}'  /> 
  <input type="hidden"   id='waybill_no' name='waybill_no' value='${id}'  /> 
  <input type="hidden"   id='boxType' name='boxType' value=''  />  

  <input type="hidden"   id='lccCode' name='lccCode' value='${lcc_code}'  />    
  <input type="hidden" name='type' value='1'  /> 
</form>
<script type="text/javascript">	


//取消按钮操作
$('#cancel').click(function(){
   $("#btnCreatSpem").attr("disabled",false);
   $('#dialog-continue').modal('hide');
});


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




function createTable(obj){
    var id   =  $(obj).html();
    if(!id || id==null || id.length<2)
       return;	
    var boxType = id.substring(id.length-1,id.length);
    var boxId = id.substring(0,id.length-1);
    
    $("#jqgrid").html('<table id="grid"></table><div id="pager"></div>'); 
   var option = {   
        url : '${ctx}/spem/iceBoxReg/microList?boxId='+boxId+"&boxType="+boxType,  
        datatype : 'json', 
        mtype : 'POST',
        colNames : [ '冻存管编号','项目单位','类型','冻存盒内行位置','冻存盒内列位置','冻存盒编号','是否溶血 ','是否脂质血症','是否为空','病人ID','病人姓名','出生日期'],
        colModel : [ {name : 'TUBE_CODE',sortable:false}, 
                     {name : 'LCC_NAME',sortable:false, align:'left' }, 
                     {name : 'TUBE_TYPE',sortable:false, align:'left'}, 
                     {name : 'BOX_COLNO',sortable:false, align:'center'}, 
                     {name : 'BOX_ROWNO',sortable:false},
                     {name : 'BOX_CODE',sortable:false},
                     {name : 'IS_HEMOLYSIS',sortable:false,
                    	 formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if ('1' == cellvalue) {
									result = '<span class="label label-success">是</span>'
								} else if('2' == cellvalue) {
									result = '<span class="label label-danger">否</span>';
								}
								return result;
							} },
                     {name : 'IS_LIPID',sortable:false,
		                    	 formatter : function(cellvalue, option, rowObjects) {
										var result = '';
										if ('1' == cellvalue) {
											result = '<span class="label label-success">是</span>'
										} else if('2' == cellvalue) {
											result = '<span class="label label-danger">否</span>';
										}
										return result;
									} },
                     {name : 'IS_EMPTY',sortable:false,
				                    	 formatter : function(cellvalue, option, rowObjects) {
												var result = '';
												if ('1' == cellvalue) {
													result = '<span class="label label-success">是</span>'
												} else if('2' == cellvalue) {
													result = '<span class="label label-danger">否</span>';
												}
												return result;
											} },
                     {name : 'BLOOD_CODE',sortable:false},
                     {name : 'PATIENTNAME',sortable:false},
                     {name : 'BIRTHDAY',sortable:false,formatter:yymmddFormatter}
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

}

createTable(null);
//自适应
//jqgridResponsive("grid",false); 



$('#btnBack').click(function(){
	history.go(-1);
})


$('#do_continue').click(function(){

	 var url = "${ctx}/spem/iceBoxReg/query";
     window.location.href=url;  
})

$('#btnExport').click(function(){
	$('#createBill').attr('action','${ctx}/spem/waybill/export2excel');
    $('#createBill').submit();  
})

$('#do_eidt').click(function(){
	 var url = "${ctx}/spem/waybill/forQueryBill"
     window.location.href=url;  
})

$('#btnCreatSpem').click(function(){
	$("#btnCreatSpem").attr("disabled",true);
	 /* var myform = $("#createBill");
     
     var myform = myform.serializeArray();
     var data = {};
     $.each(myform, function(i, field){
         data[field.name] = field.value;
     });  */
     
     $("#dialog-continue").modal({
         keyboard: false
     });  
     //createBill(data); 
   /*  $('#createBill').attr('action','${ctx}/spem/waybill/createWayBill');
    $('#createBill').submit();  */
})

	$('#do_submit').click(function(){
		$('#createBill').attr('action','${ctx}/spem/waybill/createWayBill');
	    $('#createBill').submit();  
	})

   
   /*  //新增数据
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
    }  */
    
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