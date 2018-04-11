<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>

<html>
<head>
 <title>样本管理</title>

</head>
<body>

<ul class="breadcrumb">
  <li class="active">样本管理</li><li class="active" >生成样本运送单</li>
</ul>
<form id="feereg_form"  class="well bs-bdcor form-inline"  >
<div class='row'>
     <div class="col-lg-4 col-md-offset-1 col-md-4">
              冻存盒类型:
	   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  
		<select id="spemType" name="spemType" class="form-control input-sm"  onchange="changeProper()" >
             <option value="A、C、E、G">A、C、E、G</option>
             <option value="B、D、F">B、D、F</option>
        </select>
      </div>
     
     <div class="col-lg-5  col-md-5">
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" id="export" class="btn btn-primary btn-sm">导出冻存盒列表</button>
      <button type="button" id="btnCreatSpem" class="btn btn-primary btn-sm">生成样本运送单</button>
      <button type="button" id="btnBack" class="btn btn-primary btn-sm">返回</button>
      </div>
     
      </div>
 </form>	  

 <div class='row'>

 <div class="col-lg-5  col-md-5">
   <div id="d3" style="padding-left:20px">
        <table cellpadding="0" cellspacing="8"  >
            <tr>
                <td>可选冻存盒</td>
                <td>&nbsp;</td>
                <td><font color='orange'>已选择冻存盒</font></td>
            </tr>
            <tr>
                <td>
	                <div  id='hideOption'  style='display:none' > 
	                   <c:forEach items="${option }" var="op" >
                           <option value="${op.id }"  opType='${op.boxType}' lcc_code='${op.lcc_code}'   >${op.id}${op.boxType}${op.statusCode==1?"(已满)":"(未满)" }  </option>
                        </c:forEach> 
	                </div> 
                    <select id="s1" name="s1" class="form-control" style="width:166px; height:220px;" multiple="multiple" onchange='createTable(this)'> 
                        
                        
                    </select>   
                </td>
                <td> 
                    <br>
                    <p><input id="b1" type="button" class="s1" value="&gt;" /></p>
                    <br>
                    <p><input type="button" id="b2" class="s1" value="&gt;&gt;" /></p>
                    <br> 
                    <p><input type="button" id="b3" class="s1" value="&lt;" /></p>
                    <br>
                    <p><input type="button" id="b4" class="s1" value="&lt;&lt;" /></p>
                </td>
                <td><select id="s2" name="s2" class="form-control" style="width:166px;height:220px;" multiple="multiple" onchange='createTable(this)'></select></td>
            </tr> 
        </table>
    </div>
   </div> 
 
     <div class="col-lg-6 col-md-6">
      <div id="jqgrid">
        <table id="grid"></table>
        <div id="pager"></div> 
     </div>
</div>    
 
</div>

<form id="forCreateWayBill_form"  method="post" action   >
    <div class='col-lg-12   col-md-12' >  
           备注信息:
            <textarea rows="3" id="remark"  class="form-control" name="remark" placeholder="备注">${remark}</textarea>
            <input type="hidden"   id='boxdata' name='boxdata' value=''  /> 
            <input type="hidden"   id='showboxdata' name='showboxdata' value=''  /> 
            
            <input type="hidden"   id='boxType' name='boxType' value=''  /> 
            <input type="hidden"   id='lcc_code' name='lcc_code' value=''  /> 
            <input type="hidden"   id='boxCodes' name='boxCodes' value=''  /> 
            <input type="hidden"    name='type' value='1'  /> 
  </div>
   
</form>

<script type="text/javascript">          
$(function(){
	
  $('#b1').click(function(){
        $obj = $('#s1 option:selected').clone(true);
        if($obj.size() == 0){
            alert("请至少选择一条!");
        }
        // style='color:#FF0000;BACKGROUND-COLOR:#FF0000'
        $($obj).css("color",'#FF0000');
        $('#s2').append($obj);
        $('#s1 option:selected').remove();
    });
    
    $('#b2').click(function(){
        $('#s2').append($('#s1 option').css("color",'#FF0000'));
    });
    
    $('#b3').click(function(){
        $obj = $('#s2 option:selected').clone(true);
        if($obj.size() == 0){
            alert("请至少选择一条!");
        }
        $($obj).css("color",'');
        $('#s1').append($obj);
        $('#s2 option:selected').remove();
    });
    
    $('#b4').click(function(){
        $('#s1').append($('#s2 option').css("color",''));
    });
    /* $('option').dblclick(function(){
        var flag = $(this).parent().attr('id');
        if(flag == "s1"){
            var $obj = $(this).clone(true);
            $('#s2').append($obj);
            $(this).remove();
        } else {
            var $obj = $(this).clone(true);
            $('#s1').append($obj);
            $(this).remove();
        }
    }); */
   
    changeProper();  
    
});

$('#btnBack').click(function(){
    history.go(-1);
})

$('#btnCreatSpem').click(function(){
	  $obj = $('#s2 option');
	 
      if($obj.size() == 0){
          alert("请选择要运回的冻存盒!");
          return false;
      }
      var  opts = '';
      var  showopts = '';

      for(var i = 0 ;i<$obj.size() ;i++){
    	  opts += $obj[i].value+$($obj[i]).attr('opType')+",";
    	  //showopts += $obj[i].value+$($obj[i]).attr('opType')+",";
      } 
      
      
     // $('#showboxdata').val(showopts); 
      $('#boxdata').val(opts); 
      $('#boxType').val($("#spemType option:selected").val()); 
     
      $('#lcc_code').val($($obj[0]).attr('lcc_code')); 
      
      
      
      //alert( $('#boxType').val()  )
      $('#forCreateWayBill_form').attr('action','${ctx}/spem/waybill/forCreate');
      $('#forCreateWayBill_form').submit(); 
      //var url = "${ctx}/spem/waybill/forCreate?data="+opts+"&timestamp="+(new Date()).valueOf(); 
     // window.location.href=url;  
}); 

$('#export').click(function(){
	  $obj = $('#s2 option');
	 
    if($obj.size() == 0){
        alert("请选择要导出的冻存盒!");
        return false;
    }
    var  opts = '';
    var  showopts = '';

    for(var i = 0 ;i<$obj.size() ;i++){
  	  opts += $obj[i].value+$($obj[i]).attr('opType')+",";
    } 
   // $('#showboxdata').val(showopts); 
    $('#boxCodes').val(opts); 
    $('#lcc_code').val($($obj[0]).attr('lcc_code')); 
    //alert( $('#boxType').val()  )
    $('#forCreateWayBill_form').attr('action','${ctx}/spem/waybill/export2excel');
    $('#forCreateWayBill_form').submit(); 
    //var url = "${ctx}/spem/waybill/forCreate?data="+opts+"&timestamp="+(new Date()).valueOf(); 
   // window.location.href=url;  
}); 


function createTable(obj){
	var id    =  $(obj).val();
	var opType   =  $(obj).find("option:selected").attr('opType');
	
	if(id.length>1){
		return; 
	}  
	
	$("#jqgrid").html('<table id="grid"></table><div id="pager"></div>'); 
    
    var option = {  
            url : '${ctx}/spem/iceBoxReg/microList?boxId='+id+"&boxType="+opType,   
            datatype : 'json', 
            mtype : 'POST',
            colNames : [ '冻存管编号','项目单位','类型','冻存盒内行位置','冻存盒内列位置','冻存盒编号'],
            colModel : [ {name : 'TUBE_CODE',index : 'TUBE_CODE'}, 
                         {name : 'LCC_NAME', index : 'LCC_NAME', align:'left' }, 
                         {name : 'TUBE_TYPE', index : 'TUBE_TYPE', align:'left'}, 
                         {name : 'BOX_COLNO', index : 'ROW_NUM', align:'center'}, 
                         {name : 'BOX_ROWNO', index : 'COL_NUM'},
                         {name : 'BOX_CODE', index : 'BOX_CODE'}
                        ],  
            rowNum : 5, 
            rowList : [ 5, 15, 50,100,150,500 ],   
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
            multiselect: false 
     };  
    $("#grid").jqGrid(option);
  
    //自适应
    jqgridResponsive("grid",true);  
}

function changeProper(){
    var code = $("#spemType").val();
    var codeArr = code.split("、");
    $("#s1").children().each(function(){$(this).remove();});
    $("#s2").children().each(function(){$(this).remove();});
    $("#hideOption").children().each(
         function(){
        	 var type = $(this).attr('opType');
        	 
        	 if(!!type && code.indexOf(type)>=0)
        	 {
        		 var obj=$(this).clone();
        		 $("#s1").append(obj); 	 
             }  
         }      
    )
}
</script>
</body>
</html>


