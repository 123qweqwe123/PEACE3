<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="perm" uri="http://www.genertech.com/tags/shiro"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
 <title>字典查询</title>
  
</head>
<body>
<div id="navbar">
	<ul class="breadcrumb">
	  <li class="active">基础数据</li><li class="active" >字典管理</li>
	</ul>
</div> 

<script type="text/javascript">
var kw ='';
var tm = 0;
function p_next(){
	    
        sv = $('#searchstr').val();  // 要查询的值
        wrap = $("#type_menu");      
        //alert(wrap.find('li').html() );
        wrap.find("a").css("background-color", "");
        var lis = '';
        if(kw!=sv )
        {
            kw  = sv;
            lis = wrap.find('li');
            lis.removeClass("after").removeClass("bf");
            lis.children().css("color", ""); 
        }else if(kw !='' ){
            wrap.find('.bf'); 
            lis =  wrap.find('.after');
            if(!lis[0])
            {
               wrap.removeClass("after").removeClass("bf"); 
               lis = wrap.find('li');
            }
        }    
        
        if(!lis[0])
        {
           alert("未发现检索目标");
           return;
        }  
       
         if(kw !=''){
           var patt=new RegExp(kw,'i'); // .children().text()
           var i=0;
           
           o = lis.each(function(){
               var c_text = $(this).text();
               var isEq = !!patt.test(c_text);
               
            if(isEq)
               {   
                   if(i>0){
                      $(this).addClass("after");
                   } else if(i==0){
                       $(this).removeClass("after"); 
                       $(this).addClass("bf");
                       $(this).children().css("color", "red"); 
                       $(this).find('a').css("background-color", "yellow");  
                       hei = $(this).position().top;   //相对于 父元素
                       heiW = $(this).offset().top;    //相对于 窗口
                       sctp = wrap.scrollTop(); 
                       var elem = wrap.scrollTop(sctp + hei - $("#mySearchstr") .height()-37 )[0];  
                    }
                  i++;
               }  
           }
       ); 
    }	
}

/* document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if(e && e.keyCode==27){ // 按 Esc 
        //要做的事情
      }
    if(e && e.keyCode==113){ // 按 F2 
         //要做的事情
     }            
     if(e && e.keyCode==13){ // enter 键
         //要做的事情
        //var a =  p_next();    
    }
}; */
</script>

<div id="main-content">
 <div class="row" >
      
    <div class="col-lg-3 col-md-3" >
           
            <div class="input-group row" >
                 <div class="input-group-addon">字典类型:</div>
                 <select id="typeCode" name="typeCode"   class="form-control"> 
                      <option   value=''  >请选择字典类型</option>
                 </select>     
            </div> 
            <ul style="height:400px; overflow:auto;"  id='type_menu' class="ztree well row" style='position:relative'  >
              <div  id='mySearchstr' style='position:absolute;white-space:nowrap;z-index:99;margin-left:-16px;margin-top:-19px;background:#f5f5f5'>
                   <div class="input-group" style='width:269px'>
				      <input type="text" id='searchstr' class="form-control" >
				      <span class="input-group-btn">
				        <button class="btn btn-default" type="button"     id='bsearch'>下一个</button>
				      </span>
				   </div>
              </div>  
              <br>
               
             <%--  <c:forEach items="${dictList}" var="dict">
                    <li><a href="grid_init?tableName=${dict.TNAME}&dictTypeCode=${dict.TYPE_CODE}">${dict.CNNAME}</a></li>
              </c:forEach>  --%>
              <div id='dictList'> 
              
              
              </div>
           </ul> 
         </div> 
        
		<div class="col-lg-8 col-md-8"   style='margin-left:15px;'>
		    <div class="row placeholders">
		    <div id="message" class="alert alert-success" hidden>
            <button data-dismiss="alert" class="close">&times;</button>
                <span id="messageSpanId"></span>
            </div>
			  <div id="jqgrid">
                        <table id='grid'></table>
                        <div id='pager'></div>
              </div>  
			</div> 
		</div>
</div>
</div>


<script type="text/javascript">

 /*url 固定  参数 : table 指定的表名，  cols  指定查询的列明  where  目前为空 待扩充 */

$.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_DICT_SUMMARY&cols=TYPE_CODE|TYPE_NAME&where=",function(data) {
   $.each(data, function(i, field){
	   var opt =  $("<option value='"+field.TYPE_CODE+"'>"+field.TYPE_NAME+"</option>");
       $('#typeCode').append(opt);
    });  
}); 
 

function dict_list(typeCode){
	$.getJSON("${ctx}/sys/dict/dict_list?typeCode="+typeCode,function(data) {
		$('#dictList').html('');
	    $.each(data, function(i, field){
	    	  var a="<li><a href='#' onclick='showDict(this)'  tableName='"+field.TNAME+"' dictTypeCode='"+field.TYPE_CODE+"'>"+field.CNNAME+"</a></li>";
	    	  $('#dictList').append(a);
	    });  
	});
}
dict_list('');

 
$(document).ready(function(){
	   
     $('#typeCode').change(function(){ 
         var p1=$(this).children('option:selected').val();//这就是selected的值 
         dict_list(p1);
     })  
     
     
     $("#bsearch").click(function(){
         p_next();
     });
     $("#mySearchstr").keydown(function(e){ 
        
         var curKey = e.which; 
         if(curKey == 13){ 
             
             $("#bsearch").click(); 
             return false; 
         } 
     });      
})   



function showDict(obj){
	var tableName    = $(obj).attr("tableName");
	var dictTypeCode = $(obj).attr("dictTypeCode");
	
	$(obj).parent().parent().find("a").css("background-color", "");
	
	//document.write(($(obj).parent().parent().html()))
	$(obj).css("background-color", "yellow");  
	$("#jqgrid").html('<table id="grid"></table><div id="pager"></div>');
	
	var colNames = null;
    var colModels ;
     $.ajax({ 

        type: "get", 

        url:'${ctx}/sys/dict/getColumn?tableName='+tableName, 
        
        dataType: "json",

        cache:false, 

        async:false, 
        
        success: function(xmlobj){ 
           if(! (xmlobj && xmlobj.colNames) )
               return;
            colNames  = xmlobj.colNames;
            
            colModels = xmlobj.colModles;
            
            },
         failure:function (result) {  

             alert('Failed');  

          }
     }); 
	

     function makeTable(){  
          if(!(colNames && colModels))
              return;
          $("#grid").jqGrid({
              url : "${ctx}/sys/dict/getData",
              datatype : 'json',
              mtype : 'post', 
              cmTemplate :{sortable:false},
              colNames :colNames,
              colModel :colModels,
              height : "100%",
              autowidth : true,
              shrinkToFit:false,
              altRows:true,
              hidegrid : false,
              pager : '#pager',
              rowNum : 10,
              rowList : [10,20,30],   
              viewrecords : true,
              recordpos : 'right',
              emptyrecords : "没有可显示记录",
              
              loadonce : false,                   
              loadComplete : function(){
                 
              },
              gridComplete: function(){
                
              },
              postData : {tableName: tableName ,dictTypeCode:dictTypeCode,ceshi:encodeURIComponent('测试')},
              jsonReader : {
                  root : "rows",
                  page : "page",
                  total : "total",
                  records : "records",
                  repeatitems : false,
                  cell : "cell",
                  id : "id"
              },
              editurl: '${ctx}/sys/dict/edit?tableName='+tableName 
               
          });
         jQuery("#grid").jqGrid('setFrozenColumns'); 
         
         
         function addoption(formid,e){
             var isdelet = $( "#tr_IS_REMOVED" );
             var dtd = $( "td.DataTD" );
             
             
              //var ids = $("#grid").getDataIDs();
            
              var _id = $("#grid").jqGrid('getGridParam','selrow');
             
              if('add'==e){
                  var input_dict = $( "#tr_DICT_CODE" );
                  
                  //var str = $("#grid").getCell( _id , 'DICT_CODE' );
                  
                  formid.find(input_dict).find("#DICT_CODE").val(dictTypeCode).attr('readonly',true);;
                  //formid.find(input_dict).find("#DICT_CODE").attr('readonly',true);
                  formid.find(isdelet).find(dtd).html(
                 "<select role='select' id='IS_REMOVED' name='IS_REMOVED' class='FormElement ui-widget-content ui-corner-all'><option value ='0' selected>有效</option> <option value ='1'>无效</option></select> "      
                 )
              }else if('edit'==e){
            	  
            	  $("#Act_Buttons").find(".navButton").html('');   
                  
                  var str = $("#grid").getCell( _id , 'IS_REMOVED' );
                  var is_select='';
                  if(str.indexOf('无效')>0) 
                  {
                      is_select = 'selected';
                  } 
                  formid.find(isdelet).find(dtd).html(
                          "<select role='select' id='IS_REMOVED' name='IS_REMOVED' class='FormElement ui-widget-content ui-corner-all'><option value ='0' >有效</option> <option value ='1' "+is_select+" >无效</option></select> "       
                  )     
              }   
          }
         
         var add_options={
                  reloadAfterSubmit:true,
                  jqModal:false,
                  closeAfterAdd:true,
                  afterShowForm:addoption ,
                  recreateForm:true,
                  afterSubmit : function(response, postdata) 
                  { 
                      var result;
                      var msg;
                      try{
                        result = eval('(' + response.responseText + ')');
                        msg = result.errMsg;
                      
                      }catch(e){
                        showResult(result,'操作失败'); 
                        return false;
                      }
                      
                      showResult(result,msg); 
                      
                     return true;
                  },
                  beforeSubmit:function(postdata, formid) {
                	  var msg ='';
                	  $.each(postdata, function(i, field){
                		  /* alert(i + "=="+ field +"!!" +field.length)
                		  
                		  if(field.length>20){
                			 formid.find("#"+i).focus();   
                		     msg='超出范围！';
                		  } */
                		 })
                	  
                	  return[true,msg]; 
                  } 
             }; 
             var edit_options={
                 recreateForm:true,
                 width:400,
                 height:600,
                 left:20,
                 top:30,
                 reloadAfterSubmit:true,
                 jqModal:false,
                 editCaption: "修改字典信息",
                 bSubmit: "保存",
                 bCancel: "关闭",
                 closeAfterEdit:true,
                 afterShowForm:addoption,
                 afterSubmit : function(response, postdata) 
                 { 
                     var result;
                     var msg;
                     try{
                       result = eval('(' + response.responseText + ')');
                       msg = result.errMsg;
                     
                     }catch(e){
                       showResult(result,'操作失败'); 
                       return false;
                     }
                     
                     showResult(result,msg); 
                     
                    return true;
                 }
               };  
             
          $("#grid").jqGrid('navGrid','#pager',{search:true,edit:true,add:true,del:false,view:false},edit_options,add_options,{},{multipleSearch:true});
      }   
     
     makeTable();
}




 //将负数处理成红色显示
 var negativeFormatter = function(cellvalue, options, rowObject){
      var result = '';
      if(0==cellvalue){result='<span class="label label-success">有效</span>'}
      else{result='<span class="label label-default">无效</span>';}
      return result;
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
