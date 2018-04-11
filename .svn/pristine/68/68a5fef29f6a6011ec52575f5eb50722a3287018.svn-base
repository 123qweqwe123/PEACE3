<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
 <title>经费使用情况</title>
 <script  src="${ctx}/static/echarts/esl/esl.js"></script> 
</head>
<body>

<div id="navbar">
       <ul class="breadcrumb">
         <li class="active">经费管理</li><li class="active" >使用进度记录</li>  
      </ul>
</div>


<div id="main-content">

<div id='dialog-confirm' class="modal fade">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        <h4 class="modal-title">选择组织</h4>
      </div>
      <div class="modal-body">
        <p>加载中……</p>
      </div>
      
      <div class="modal-footer">
        <button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
      </div>
    </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
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
                                    <label form=name>参加项目单位:</label>
                                 </li>
                                 <li>
                                    <input  type="text" style="display:none;" class="form-control input-sm" name="lcc_code" id="lcc_code" value='<%=Securitys.getUser().getLccCode() %>'/>    
                                    <input  type="text"   class="form-control input-sm" name="lcc_name" id="lcc_name" value='<%=Securitys.getUser().getLccName() %>' placeholder="选择已有单位 双击选择 "/>   
                                 </li>
                                 <li> <button type="button" id="btnQuery" class="simpled btn btn-sm btn-primary" >查询</button></li>
                            </ol>
                           
 </fieldset>
</form>

</div>
</div>
<div class='row'>

 <div class="col-lg-4 col-md-4">
    <div id="jqgrid"></div>
 </div> 
 
 <div class="col-lg-8 col-md-8">
       <div id="main" style='height:400px'></div> 
 </div>  
 
</div>

<script type="text/javascript">



//url 固定  参数 : table 指定的表名，  cols  指定查询的列明  where  目前为空 待扩充
$.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_LCC&cols=LCC_CODE|LCC_NAME|HELP_CODE|ENGLISH_NAME&where=",function(data) { 
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
        return item.HELP_CODE;
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



$(function (){ 

	
	
	
	
	var date=new Date();
	date.setDate(date.getDate()-20);
	var dayBefore2 = DateFormat.format(date, 'yyyy-MM-dd');
	date.setDate(new Date().getDate()-1);
	var date2 = new Date();
	date2.setDate(new Date().getDate()-1);
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
	
<!------------------------------ 引入echarts 开始 ------------------------------------------->
require.config({
    paths:{ 
        'echarts' : '${ctx}/static/echarts/echarts',
        'echarts/chart/pie' : '${ctx}/static/echarts/echarts'
    }  
}); 

function makePie(xmlobj){
    
    var organName = $("#organization").val();
    
    var cols = xmlobj.list;
     
     var cdata = [];
     
     var vdata = [];
     
     var v_sum = 0;
     
     var v_total = 0;
     
       if(xmlobj.LCC_SUM)
    	  v_total = xmlobj.LCC_SUM; 
     
     var obj;
     for(var i in cols){
         name  = cols[i].st_name;
         value = cols[i].lcc_fee_total;
         cdata.push(name);
         v_sum += parseInt(value,10);
         obj= new Object() ;
         obj.value= parseInt(value,10);
         obj.name = name;
         vdata.push(obj);
     }         
     obj = new Object() ;
     if(v_total!=0){
    	 var y_total = v_total - v_sum;
         
         obj.value= parseInt(y_total,10);
    	 cdata.push('剩余');
    	 obj.name = '剩余';
    	 vdata.push(obj);    
     }
      
    
// 使用
require(
    [
        'echarts',
        'echarts/chart/pie' // 使用柱状图就加载bar模块，按需加载
    ], 
    function (ec) {
        // 基于准备好的dom，初始化echarts图表
        var myChart = ec.init(document.getElementById('main')); 
       
          
        var option = {
                title : {
                    text: '项目经费使用情况',
                    subtext: organName,
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                },
                legend: {
                    orient : 'vertical',
                    x : 'left',
                    data:cdata
                },
                toolbox: {
                    show : false,
                    feature : {
                        mark : {show: true},
                        dataView : {show: true, readOnly: false},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name:'费用占比',
                        type:'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:vdata
                    }
                ]
            };
        
        // 为echarts对象加载数据 
        myChart.setOption(option); 
    }
);
}
<!----------------------------------------- 引入echarts 结束 ---------------------------->    	 

<!----------------------------------jqgrid---------------------------------------------------->
function makeTable(xmlobj){    
	
	$("#jqgrid").html('<table id="grid"></table>');
    
    var option = { 
            data: xmlobj.list, 
            datatype: "local" , 
            colNames : [ '类别','金额(元)'],
            colModel : [ 
                         {name : 'st_name', index : 'st_name', align:'left' },
                         {name : 'lcc_fee_total', index : 'lcc_fee_total', align:'left',formatter:'currency', formatoptions:{decimalSeparator:",", thousandsSeparator: ",", 
                             decimalPlaces:0, prefix: "",defaulValue:0} }
                       ], 
          /*   rowNum : 150, 
            height : "100%",
            autowidth : false, 
            sortname : 'st_name', */
            altRows:true,
            hidegrid : false, 
            viewrecords : true, 
            recordpos : 'left', 
            sortorder : "desc",
            emptyrecords : "没有可显示记录", 
            loadonce : false,
            footerrow:true,             //加这个标识
            gridComplete:function(){
                var count = parseInt($("#grid").getCol("lcc_fee_total",false,"sum"),10);
                if(!xmlobj.LCC_SUM )
                {	
                	$(".ui-jqgrid-sdiv").hide();
                	return;
                }
                var total = parseInt(xmlobj.LCC_SUM,10);
                var surplus = total - count;
                
                var rowNum=parseInt($(this).getGridParam("records"),10);
                if(rowNum>0){
                    $(".ui-jqgrid-sdiv").show();
                    
                    $(this).footerData("set",{"st_name":"剩余","lcc_fee_total":surplus});                               //将合计值显示出来
                }else{
                    $(".ui-jqgrid-sdiv").hide();
                }
            } 
            
    };
    $("#grid").jqGrid(option);
    //自适应
    jqgridResponsive("grid",true);
     
    //$("#grid").trigger("reloadGrid");
}


<!--------------------------------jqgrid------------------------------------------------------->
 
function pageLoader(){
	var bg_date  = $('#bg_date').val();
	var end_date = $('#end_date').val(); 
	
	var myform =   $("#searchForm"); 
	if(!jQuery(myform).validate().form()){ return ;}
	
	$.ajax({ 

	    type: "get", 

	    url:'${ctx}/fee/getFeeList',  
	    
	    dataType: "json",
	    
	    data: "lcc_code="+$('#lcc_code').val()+"&bg_date="+$('#bg_date').val()+"&end_date="+$('#end_date').val(),

	    cache:false, 

	    async:false, 
	    
	    success: function(xmlobj){ 
	       if(! (xmlobj ))
	           return;
	       
	         makeTable(xmlobj)
	         makePie(xmlobj);
	       },
	        
	       failure:function (result) {  

	         alert('Failed');  
	       }
	 }); 
} 


pageLoader(); 

//取消按钮操作
$('#cancel').click(function(){
    $('#dialog-confirm').modal('hide');
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
    var myrIds = "";
    $("#user_form [name=roleId]").each(function(){
        if($(this).is(":checked")){
            var id_ = $(this).val();
            myrIds += id_+",";
        }
    });
    if(''!=myrIds){
        myrIds=myrIds.substring(0,myrIds.length-1);
        data.rIds = myrIds;
    }
    if(data.id ==""){addDate(data);}
    else{updateDate(data);}
});


//查询绑定
var multipleSearch = function() {
    pageLoader(); 
    
    
     /* var postData = $("#grid").jqGrid("getGridParam", "postData");  
     var mydata = {};
     var bg_date =  $("#bg_date").val();
     var end_date   =  $("#end_date").val(); 
     var lcc_code   =  $("#lcc_code").val();  
     
     if(bg_date && ''!=bg_date){
         mydata.bg_date = bg_date;
     }else{
         delete postData.loginName;
     }
     if(end_date && ''!= end_date){
         mydata.end_date = end_date;
     }else{
         delete postData.end_date;
     }
     if(lcc_code && ''!= lcc_code){
         mydata.lcc_code = end_date;
     }else{
         delete postData.lcc_code;
     } 
     
    $.extend(postData,mydata); 
    
    $("#grid").jqGrid("setGridParam", {
        search: true  
    }).trigger("reloadGrid", [{page:1}]); */ 
};

$("#btnQuery").click(multipleSearch); 



 




       
        
    
            
          
          
         $("#select_btn").click(function(e){
              var timebak = new Date().getTime();
              var url ='${ctx}/fee/openmodalSelectOrgan?time='+timebak; 
              openDialog(url);
             }
          );   
        
           
               $("#organization").click(function(e){
                   /*  var ileft = $(e.target).offset().left;
                    var itop = $(e.target).offset().top;
                    $("#organizationZtree").css('top',itop-58).css('left', ileft-410 ); */
                    $("#organizationZtree").css('width',$(this).outerWidth());
                    //$("#organizationZtree").toggle();
                    
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
                
      });

     
</script>
</div>
</body>



</html>