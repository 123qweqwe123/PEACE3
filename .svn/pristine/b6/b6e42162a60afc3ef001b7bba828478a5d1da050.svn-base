<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

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
        
        .msg {
		border-radius: 4px;
		background-color: #f5f5f5;
		border: 1px solid #cccccc;
		color: #404040;
		margin-bottom: 15px;
		padding:10px;
		}
		.modal-dialog{
			width:700px;
			height:300px;
		}
</style>


	
	<div id="mainContent">
	<div class="msg" id="loading" style="display:none;width:100%;max-width: 1140px;height:60px;position:absolute;z-index:10000;">
		
		<div class="col-lg-7 col-md-5">
			<label class="col-lg-5 col-md-1  control-label" style="float:right;vertical-align:middle;" for="materlName">导入时间较长，请耐心等待！！</label>
			<img src="${ctx }/static/file-upload/img/loading.gif" width="32" height="32" style="margin-right:8px;float:right;vertical-align:middle;"/> 
		</div>
	</div>
    	
        <!-- <div id="marginRight"> -->
        	<div id="select">
                <div class="select-main">
                      <form id="newsForm" action="" method="post" class="well-work bs-adp form-inline">
                      <fieldset>
                        	<ol>
                             	<li> 
                                    <div class="form-group" style="float:left;">
                      					<!-- <label class="col-lg-3 col-md-10  control-label" for="materlName"><span style="color: red">*</span>选择文件:</label> -->
										<div class="col-lg-7 col-md-10" style="float:left;width:300px;">
											<input type="text" name="fileName" id="puf" class="form-control input-sm" placeholder="请点击浏览选择文件"/><input type="button" value="浏览" style="height:30px;" onclick="javascript:$('#file').click();" />
											<input type="file" accept=".xls" name="file" id="file" style="display:none" onchange="javascript:$('#puf').val($('#file').val());$('#errorDiv').hide();$('#successDiv').hide();" />
										</div>
									 </div>
                                </li>
                                
                                <!-- <li>
                             	    <input  type="text" style='display:none;' class="form-control input-sm" name="lcc_code" id="lcc_code" value=''/>   
                            	 	<input  class="form-control input-sm" id="lcc_name" name="lcc_name" type="text" placeholder="请输入项目单位" value=""/>
                                </li> -->
                            
                               <li> <button type="button" id='btnCheck' class="btn btn-primary">上传校验</button> </li>
                           	<!-- 
                           	 <li> <button type="button" id='btnTemplate' class="btn btn-primary">下载模板</button> </li>
                            -->
                            </ol>
                        </fieldset>
                        
                        
                      </form>
                </div>
            </div>
            
         <div class="msg" style="min-height:300px;">
         	<div id="errorDiv">
         		<p id="errorMsg" style="font-size:10px;">
         			
         		</p>
         		<div style="float:right;">
         			<button type="button" id='btndownload' class="btn btn-primary">下载校验结果</button>
         		</div>
         	</div>
         	<div id="successDiv">
         		<p style="font-size:10px;">
         			花名册数据导入成功！
         		</p>
         	</div>
        
        	<br style="clear:both;" />
    	</div>
 
<script>

$(function() {
	

		
		//验证后的excel文件下载
		$("#btndownload").click(function(){
			window.open('${ctx}/pro/pat/downloadError?fn='+fn+"&logid="+logid);
	    });
		
		$("#errorDiv").hide();
		$("#successDiv").hide();
		
	
		
		var fn = '';
		var logid = '';
		//excel文件上传
		$('#btnCheck').click(function(){
			
			if ( $('#file').val() == '' ){
				alert('请选择需要上传的文件!');
				return;
			}
			$("#loading").show();
			fn = $('#file').val();
			
			fn = fn.replace('C:\\fakepath\\', '');
			
			$("#newsForm").ajaxSubmit({
				url:"${ctx}/pro/pat/upload", 
	           	type:"POST",  
	           	dataType:"json",
	           	error:function(data){
	           		$('#file').val('');
	           		$('#puf').val('');
	           		$("#loading").hide();
	           		alert('系统错误！');
	           	},
	           	success:function(data){
	           		$('#file').val('');
	           		$('#puf').val('');
	           		$("#loading").hide();
	               	if(data.success){
	               		//$('#dialog-confirm').modal('hide');
	               		//$("#documentgrid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
	               		$("#successDiv").show();
	               		
	               	}else{
	               		fn = data.fileName;
	               		logid = data.logId;
               			$("#errorDiv").show();
	               		$("#errorMsg").html(data.msg);
               			$('#btndownload').show();
               			
               			
	               		/*
	               		//alert(data.num);
	               		if ( data.num ){
	               			fn = data.fileName;
	               			$("#errorDiv").show();
		               		$("#errorMsg").html(data.msg);
	               			$('#btndownload').show();
	               		}else{
	               			//$('#btndownload').hide();
	               			alert(data.msg);
	               		}
	               		*/
	               		//openError(data.msg,2000,$("#alertForUpload"));
	               		//$("#do_save").attr("disabled",false);
	               	}
	             }
			});
		});
	
});

</script>    
    
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
                        $(this).parent("li").children("a").addClass("active");
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
	
	
  /* $.getJSON("${ctx}/combox/comboxData?table=PIP_COMM_LCC&cols=LCC_NAME|LCC_CODE|ENGLISH_NAME&where=",function(data) { 
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
	});  */
	//选中 某条记录 触发的事件
	 $('#lcc_name').result(function(event, item){ 
		// flushLine(ctype);
	    if(item) 
	    {
	    	  $("#lcc_code").val(item.LCC_CODE); 
	    	  $("#area_code option:first").prop("selected", 'selected');
	    	  $("#area_code").val('');
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
         },'2014-11-01',dayBefore1);  
  
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
	
	//myChart3 = ec.init(document.getElementById('line'));
      //getData();
	  //flushLine('登记');
	}
	);
	
	var myChart3;
	var ctype = '登记';
	var cvalue = '001001';
	function flushLine(obj){
		//ctype= type;
		
		
		
		cvalue = $(obj).attr('val');
		ctype  = $(obj).attr('showname');
		
		 
	   
	
	   //getData();    
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
                	   $('#tbody').append('<tr><td>'+data.days[i]+'</td><td>'+data.valus[i]+'</td></tr>');
                   }
                   
               }   
        );  
   }
    
   
    
	
</script>
