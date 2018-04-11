


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="perm" uri="http://www.genertech.com/tags/shiro"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<style type="text/css">
#gview_grid table.ui-jqgrid-htable th[role='columnheader'][rowspan='2']>div {
    height:auto;
}


/*
 * Base structure
 */

/* Move down content because we have a fixed navbar that is 50px tall */
body {
  padding-top: 50px;
}


/*
 * Global add-ons
 */

.sub-header {
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}

/*
 * Top navigation
 * Hide default border to remove 1px line.
 */
.navbar-fixed-top {
  border: 0;
}

/*
 * Sidebar
 */

/* Hide for mobile, show later */
.sidebar {
  display: none;
}
@media (min-width: 768px) {
  .sidebar {
    position: fixed;
    top: 51px;
    bottom: 0;
    left: 0;
    z-index: 1000;
    display: block;
    padding: 20px;
    overflow-x: hidden;
    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
    background-color: #f5f5f5;
    border-right: 1px solid #eee;
  }
}

/* Sidebar navigation */
.nav-sidebar {
  margin-right: -21px; /* 20px padding + 1px border */
  margin-bottom: 20px;
  margin-left: -20px;
}
.nav-sidebar > li > a {
  padding-right: 20px;
  padding-left: 20px;
}
.nav-sidebar > .active > a,
.nav-sidebar > .active > a:hover,
.nav-sidebar > .active > a:focus {
  color: #fff;
  background-color: #71A7D8;
}


/*
 * Main content
 */

.main {
  padding: 20px;
}
@media (min-width: 768px) {
  .main {
    padding-right: 40px;
    padding-left: 40px;
  }
}
.main .page-header {
  margin-top: 0;
}


/*
 * Placeholder dashboard ideas   //white-space:nowrap;
 */

.placeholders {
  margin-bottom: 30px;
  text-align: center;
}
.placeholders h4 {
  margin-bottom: 0;
}
.placeholder {
  margin-bottom: 20px;
}
.placeholder img {
  display: inline-block;
  border-radius: 50%;
}
</style>

<!--  <div class="container-fluid"> -->
      <div class="row" >
        <div class="col-sm-3 col-md-2 sidebar" id='type_menu'  style='margin-bottom:0px;' >
             <div class="input-append"  id='mySearchstr'   >
                <input  type="text" id='searchstr'  class="span2 search-query" style='width:120px' ></input>
                <button type="submit"  id='bsearch' class="btn"  >下一个</button>
              </div> 
          <ul class="nav nav-sidebar" style='margin-bottom:0px;margin-top:0px'  >
              
              
           
              
                
              <br>
               
              <c:forEach items="${ dictList }" var="dict">
                    <li><a href="grid_init?tableName=${dict.TNAME}&dictTypeCode=${dict.TYPE_CODE}">${dict.CNNAME}</a></li>
              </c:forEach>
          </ul> 
        </div>
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
<h1 class="page-header"></h1>

<div class="row placeholders">
          
<form id="conditionform" > 
   <%--   --%>
       
     
</form>

<table id="grid"></table>
<div id="pager"></div>
</div>
</div>
</div>
<!-- </div> -->
<script type="text/javascript">

    
    var colNames = null;
    var colModels ;
    $.ajax({ 

       type: "get", 

       url:'<c:url value="../dict/getColumn"/>?tableName=${tableName}', 
       
       dataType: "json",

       cache:false, 

       async:false, 
       
       success: function(xmlobj){ 
           colNames  = xmlobj.colNames;
           
           colModels = xmlobj.colModles;
           //alert( colModels[2].name )
        },
        failure:function (result) {  

            alert('Failed');  

         }
    }); 
    
      
    
    
    
    function makeTable(){  
         $("#grid").jqGrid({
             url : '<c:url value="../dict/getData" />',
             datatype : 'json',
             mtype : 'get', 
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
             postData : {tableName: '${tableName}' ,dictTypeCode:'${dictTypeCode}'},
             jsonReader : {
                 root : "rows",
                 page : "page",
                 total : "total",
                 records : "records",
                 repeatitems : false,
                 cell : "cell",
                 id : "id"
             },
             editurl: '<c:url value="../dict/saveOrUpdate" />'
              
         });
        jQuery("#grid").jqGrid('setFrozenColumns'); 
        
        
        var add_options={
                width:400,
                height:300,
                left:20,
                top:30,
                reloadAfterSubmit:false,
                jqModal:false,
                addCaption: "添加字典信息",
                bSubmit: "保存",
                bCancel: "关闭",
                closeAfterAdd:true
               
            };
            var edit_options={
                width:400,
                height:290,
                left:20,
                top:30,
                reloadAfterSubmit:false,
                jqModal:false,
                editCaption: "修改字典信息",
                bSubmit: "保存",
                bCancel: "关闭",
                closeAfterEdit:true
              };   
            
        $("#grid").jqGrid('navGrid','#pager',{search:true,edit:true,add:true,del:true,view:true},edit_options,add_options,{},{multipleSearch:true});
    }   
    
    makeTable(); 
    
    
  /*   $("#btnQuery").click(function() {
        var postData = $("#grid").jqGrid("getGridParam", "postData");
        var mydata = $("#conditionform").serializeObject();  
        
        $.extend(postData, mydata);
        $("#grid").jqGrid("setGridParam", {
            search : true
        }).trigger("reloadGrid", [ {
             
        } ]);
 
    });  */
    
 $(document).ready(function (){
	     var kw ='';
	     var tm = 0;
	    $("#bsearch").click(function() {
	         sv = $('#searchstr').val();
	         wrap = $("#type_menu");
	         
	         var lis = '';
	         if(kw!=sv )
	         {
	             kw  = sv;
	             lis = wrap.find('li');
	             lis.removeClass("after").removeClass("bf").css("background-color", "");
	             lis.children().css("color", ""); 
	         }else if(kw !='' ){
	             wrap.find('.bf').css("background-color", ""); 
	             lis =  wrap.find('.after');
	             if(!lis[0])
	             {
	                wrap.removeClass("after").removeClass("bf").css("background-color", ""); 
	                lis = wrap.find('li');
	             }
	         }
	         
	         if(!lis[0])
	         {
	                  alert("未发现检索目标");
	                  return;
	         } 
	        
	          if(kw !=''){
	           
	        
	            var patt=new RegExp(kw,'g'); // .children().text()
	            var i=0;
	            o = lis.each(function(){
	                if(patt.test($(this).text()))
	                {   
	                    $(this).removeClass("after"); 
	                    if(i>0){
	                       $(this).addClass("after");
	                    } if(i==0){
	                        $(this).addClass("bf");
	                        $(this).children().css("color", "red"); 
	                        $(this).css("background-color", "yellow");  
	                        hei = $(this).position().top;
	                        
	                        wrap.scrollTop(hei -  $("#mySearchstr") .height());  
	                        
	                        heiW = $(this).offset().top;
	                        if(hei < 0)
	                            hei =0;
	                        if(tm ==0)
	                         tm =  $("#type_menu").offset().top;
	                       
	                        $("#mySearchstr").css("top" , tm );
	                       
	                    }
	                    i++;
	                }
	            }
	        ); 
	     }
	});
 });
    
</script>
