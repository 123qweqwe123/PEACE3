<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title></title>
<script type="text/javascript" src="${ctx}/static/echarts/esl/esl.js"></script>
<script type="text/javascript" src="${ctx}/static/echarts/echarts.js" ></script>
<style>
	
	.txt{
	    font-size:14px;
	    font-weight:bold;
	}
	
	.percentTxt
		  {
		  	font-size:9px;
		  	font-weight:normal;
		  	color:#333;
		  }
		.upArrow{
        	padding:0px 20px 0px 0px;
        	background-image:url('${ctx}/views/progress/assets/arrowUp1.png');	
        	background-repeat:no-repeat;
        	background-position: 55px 10px;
        }
        
        .downArrow{
        	padding:0px 20px 0px 0px;
        	background-image:url('${ctx}/views/progress/assets/arrowDown1.png');	
        	background-repeat:no-repeat;
        	background-position: 55px 10px;
        }
</style>
</head>
<body>
	<div id="navbar">
    	<ol class="breadcrumb">
          <li class="active">样本管理</li>
          <li class="active">冻存管使用详情</li>
          <%-- <li class="active"><c:if test="${type=='2' }">初筛</c:if><c:if test="${type=='3' }">高危</c:if><c:if test="${type=='4' }">预约随访</c:if>列表-${lccName}</li> --%>
        </ol>
    </div>


	<div id="mainContent">
    	<!--aside-->
    	<!-- <div class="slide">
        	<ul style="margin-left: 0">
            	<li class="active"><a href="#">进度管理</a></li>
                <li><a val='001001'  showname='登记' href="javascript:void(0);" onclick="flushLine(this);">登记进度</a></li>
                <li><a href="#">初筛进度</a>
                	<ul style="display: block;margin-left: 0;">
                    	<li val='001002'  showname='快速血脂'  onclick="flushLine(this);">快速血脂进度</li>
  						<li val='001002'  showname='体格检查' onclick="flushLine(this);">体格检查进度</li>
  						<li val='001002'  showname='初筛调查表' onclick="flushLine(this);">初筛调查表进度</li>
                    </ul>
                </li>
                <li><a val='num5'  showname='高危检出' href="javascript:void(0);" onclick="flushLine(this);">高危检出人数</a></li>
                <li><a href="#">高危进度</a>
                	<ul style="display: block;margin-left: 0;">
                    	<li val='num6'  showname='样本采集' onclick="flushLine(this);">样本采集进度</li>
  						<li val='num7'  showname='心电检查' onclick="flushLine(this);">心电检查进度</li>
  						<li val='num8'  showname='颈动脉超声' onclick="flushLine(this);">颈动脉超声进度</li>
                        <li val='num9'  showname='心脏超声' onclick="flushLine(this);">心脏超声进度</li>
                        <li val='001003,001004'  showname='高危调查表' onclick="flushLine(this);">高危调查表进度</li>
                    </ul>
                </li>
                <li ><a  val='001005'  showname='干预'   href="javascript:void(0);" onclick="flushLine(this);" >干预进度</a></li>  
                <li><a val='001006'  showname= '随访'  href="javascript:void(0);" onclick="flushLine(this);">随访进度</a></li>   
            </ul>      
        </div> -->
        <!--aside end--> 
        <div id="margin Right">
        	<div id="select">
                <div class="select-main">
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
                        	<ol>
                             	<li> 
                                    <div class="input-group date" id="duedate1" data-date-format="yyyy-mm-dd" data-date="2014-07-10">
                                        <input id="bg_date" class="form-control input-sm" style="width: 120px;"  required="required" type="text">
                                        <span class="input-group-addon input-sm btn">
                                        <i class="glyphicon glyphicon-calendar "></i>
                                        </span>
                                    </div>
                                </li>
                                <li>
                                    到 <div class="input-group date" id="duedate2" data-date-format="yyyy-mm-dd" data-date="2014-08-10">
                                        <input id="end_date" class="form-control input-sm" style="width: 120px;" required="required" type="text">
                                        <span class="input-group-addon input-sm btn">
                                        <i class="glyphicon glyphicon-calendar "></i>
                                        </span>
                                    </div>
                                </li>
                                <li>
                                    <select id="area_code" name="area_code" class="form-control input-sm" onchange='clean_lcc(this)'>
                                        <option value="">请选择省份</option>
                                        <option value="22">吉林</option> 
                                        <option value="21">辽宁</option> 
                                        <option value="33">浙江</option> 
                                        <option value="45">广西</option> 
                                    </select>
                                </li>
                            
                             	<li>
                             	    <input  type="text" style='display:none;' class="form-control input-sm" name="lcc_code" id="lcc_code" value=''/>   
                            	 	<input  class="form-control input-sm" id="lcc_name" name="lcc_name" type="text" placeholder="请输入项目单位" value=""/>
                                </li>
                            
                               <li> <button type="button" id='btnQuery' class="btn btn-primary">查询</button> </li>
                            </ol>
                        </fieldset>
                      </form>
                </div>
            </div>
            
         <div class="tableBg" style="min-height:600px;">
         
         	<div id="line" style="height:300px;"></div>
         	
         	<div class="table-responsive" style="padding: 0 80px;">   
             	<table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr class="table-top-nav">
                        <td>时间</td>
                        <td>当天人数(单位:人)</td>
                        <td><span id="txt_type"></span>人数(单位:人)</td>
                    </tr>
                </thead>
               <tbody id="tbody">
               </tbody>
              </table>  
         	</div>
            			
         </div>
        </div>
        
        <br style="clear:both;" />
    </div>
<script type="text/javascript">

(function($) {
    $.fn.menu = function(b) {
        var c,
        item,
        httpAdress;
        b = jQuery.extend({
            Speed: 220,
            autostart: 1,
            autohide: 1
        },
        b);
        c = $(this);
        item = c.children("ul").parent("li").children("a");
        httpAdress = window.location;
        item.addClass("inactive");
        function _item() {
            var a = $(this);
            if (b.autohide) {
                a.parent().parent().find(".active").parent("li").children("ul").slideUp(b.Speed / 1.2, 
                function() {
                    $(this).parent("li").children("a").removeAttr("class");
                    $(this).parent("li").children("a").attr("class", "inactive")
                })
            }
            if (a.attr("class") == "inactive") {
                a.parent("li").children("ul").slideDown(b.Speed, 
                function() {
                    a.removeAttr("class");
                    a.addClass("active")
                })
            }
            if (a.attr("class") == "active") {
                a.removeAttr("class");
                a.addClass("inactive");
                a.parent("li").children("ul").slideUp(b.Speed)
            }
        }
        item.unbind('click').click(_item);
        if (b.autostart) {
            c.children("a").each(function() {
                if (this.href == httpAdress) {
                    $(this).parent("li").parent("ul").slideDown(b.Speed, 
                    function() {
                        $(this).parent("li").children(".inactive").removeAttr("class");
                        $(this).parent("li").children("a").addClass("active")
                    })
                }
            })
        }
    }   
})(jQuery);

	function clean_lcc(obj){
	    var area_code  =  $(obj).val();
	    if(area_code!='')
	    {
	    	$("#lcc_code").val(''); 
	    	$("#lcc_name").val(''); 
	    }
	} 
	
	
  $.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_LCC&cols=LCC_NAME|LCC_CODE|ENGLISH_NAME&where=",function(data) { 
	$('#lcc_name').autocomplete(data,{
	    minChars: 0,
	    mustMatch:true, 
	    width:280,
	   // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
	    formatItem: function(item,i, max) {
	        return '<table><tr><td width="60px;">' + item.LCC_CODE + '</td><td width="220px;">' + item.LCC_NAME + '</td></tr></table>';
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
		// flushLine(ctype);
	    if(item) 
	    {
	    	  $("#lcc_code").val(item.LCC_CODE); 
	    	  $("#area_code option:first").prop("selected", 'selected');
	    }
	    else 
	    $("#lcc_code").val('');   
	  }); 
	});  
  var date=new Date();
  var date2=new Date();
  date.setDate(date.getDate()-7);
  var dayBefore2 = DateFormat.format(date, 'yyyy-MM-dd');
  date2.setDate(new Date().getDate()-1);
  var dayBefore1 = DateFormat.format(date2,'yyyy-MM-dd');
  initDatePicker( '#duedate1','#bg_date', '#duedate2','#end_date',  
         function(){
             $("#submitButton").attr('disabled',false);
             $('#alert').hide();
         },
         function(){
             $("#submitButton").attr('disabled',true);               
         },dayBefore2,dayBefore1);  
  
  $('#duedate1').datetimepicker('setEndDate',  date2);
  $('#duedate2').datetimepicker('setEndDate',  date2); 
 

         
         
/* 
$(function(){ 
	
	$(".slide ul li").menu();
	var option = {
	        url : '${ctx}/progress/getpatientlist',   
	        datatype : 'json', 
	        postData :{lccName:$('#lccName').val(),type:$('#type').val()},
	        mtype : 'POST',
	        colNames : [ 'pid','LCC','患者姓名','性别','出生年月','电话','手机','是否高危','是否预约随访'],
	        colModel : [ 
	                     {name : 'patientId'},
	                     {name : 'lccName'},
	                     {name : 'patientName', sortable:false,align:'center' },
	                     {name : 'sex',sortable:false, align:'center' ,
	                    	 formatter : function(cellvalue, option, rowObjects) {
									var result = '';
									if ('1' == cellvalue) {
										result ='男';
									} else{
										result = '女';
									}
									return result;
								} },
	                     {name : 'birthday', sortable:false,align:'center',formatter:yymmddFormatter},
	                     {name : 'phone', sortable:false,align:'center' },
	                     {name : 'mobile', sortable:false,align:'center' },
	                     {name : 'isRisk', sortable:false,align:'center',
	                    	 formatter : function(cellvalue, option, rowObjects) {
								var result = '';
								if ('1' == cellvalue) {
									result = '<span class="label label-danger">是</span>'
								} else{
									result = '<span class="label label-success">否</span>';
								}
								return result;
							}},
	                     {name : 'isFollowview', sortable:false,align:'center',
		                    	 formatter : function(cellvalue, option, rowObjects) {
										var result = '';
										if ('1' == cellvalue) {
											result = '<span class="label label-success">是</span>'
										} else{
											result = '<span class="label label-fanger">否</span>';
										}
										return result;
									} }
	                   ],  
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],  
	        height : "100%", 
	        autowidth : true,  
	        pager : '#dataTablePager',  
	        sortname : 'patient_Id',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "desc",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: false 
	 };  
	$("#dataTable").jqGrid(option);
	$("#dataTable").jqGrid('navGrid', '#dataTablePager', {
		edit : false,
		add : false,
		del : false,
		search : false,
		position : 'right'
	})
});
 */

var option2 = {
	    title : {
	        text: '',
	        subtext: '  单位:人'
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    toolbox: {
	        show : false,
	        feature : {
	        	mark : {show: true},
                dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    
	    xAxis : [
	        {
	            type : 'category',
	            
	            boundaryGap : false,
	            //data : ['2014-10-12','2014-10-13','2014-10-14','2014-10-15','2014-10-16']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'人数',
	            type:'line',
	            smooth:false,
	            //data:[0, 30, 30 ]
	        } 
	    ]
	};
    require.config({
	paths:{ 
	echarts:'${ctx}/static/echarts/echarts',
	'echarts/chart/bar' : '.${ctx}/static/echarts/echarts',
	'echarts/chart/line': '${ctx}/static/echarts/echarts'
	}
	});

	require(
	[
	'echarts',
	'echarts/chart/bar',
	'echarts/chart/line'
	],
	function (ec) {
	
	myChart3 = ec.init(document.getElementById('line'));
      getData();
	  //flushLine('登记');
	}
	);
	
	var myChart3;
	var ctype = '冻存管';
	var cvalue = 'tube';
	function flushLine(obj){
		//ctype= type;
		
		
		
		cvalue = $(obj).attr('val');
		ctype  = $(obj).attr('showname');
		
		 
	   
	
	   getData();    
	   /*
	   var dat = [0,0,0,0,0];
		if ( $('#lcc_name').val() == '' || $('#lcc_name').val() == '辽宁省人民医院' ){
			if ( type == '登记'){
				dat = [0,32,32,32,32];
			}
			if ( type == '初筛调查表'){
				dat = [0,30,30,30,30];
			}
			if ( type == '高危检出'){
				dat = [0,3,3,3,3];
			}
			if ( type == '高危调查表'){
				dat = [0,0,9,9,9];
			}
			if ( type == '干预'){
				dat = [0,0,13,13,13];
			}
		}
		
		option2.title.text = type + '进度';
		option2.series[0].data = dat;
		
		myChart3.setOption(option2, true);
		
		$('#tbody').html('');
		$('#tbody').append('<tr><td>2014-10-12</td><td>'+dat[0]+'</td></tr>');
		$('#tbody').append('<tr><td>2014-10-13</td><td>'+dat[1]+'</td></tr>');
		$('#tbody').append('<tr><td>2014-10-14</td><td>'+dat[2]+'</td></tr>');
		$('#tbody').append('<tr><td>2014-10-15</td><td>'+dat[3]+'</td></tr>');
		$('#tbody').append('<tr><td>2014-10-16</td><td>'+dat[4]+'</td></tr>');
		*/
	} 
	function getData(){
		$('#txt_type').html(ctype+'累计');
        $.getJSON("${ctx}/progress/getProgressByCondit",
                {
                 start:$("#bg_date").val(),
                 end:$("#end_date").val(),
                 lccCode:$("#lcc_code").val(),
                 area_code:$("#area_code").val(),
                 type:cvalue  
                },
                function(data) { 
                  //for(var i in data){
                  //alert(data.days); 
                  //}
                   option2.title.text = ctype + '进度';
                   option2.series[0].data = data.valus;
                   option2.xAxis[0].data = data.days;
                   myChart3.setOption(option2, true);
                   $('#tbody').html('');
                   for(var i=0;i<data.days.length;i++){
                	   $('#tbody').append('<tr><td>'+data.days[i]+'</td><td>'+data.nums[i]+'</td><td>'+data.valus[i]+'</td></tr>');
                   }
                   
               }   
        );  
   }
    
    //type
    $("#btnQuery").click(function(){
        getData()
    });
	
</script>
</body>
</html>