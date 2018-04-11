<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<c:set var="currUserLcc" value="<%=Securitys.getUser().getLccCode()%>"/>
<!DOCTYPE html>
<html>
<head>
 <title>日历管理</title>
 <script  src="${ctx}/static/echarts/esl/esl.js"></script> 
 	<script type="text/javascript">


        //查询绑定
        var multipleSearch = function(){
            var postData = $("#grid").jqGrid("getGridParam", "postData");
//        resultList.setGridParam({datatype:'json',postData:null});
            var myform = $("#searchform").serializeArray();
            var data = {};
            $.each(myform, function(i, field){
                data[field.name]=null;
                if(field.value && ''!=field.value ){
                    data[field.name] = field.value;
                    delete postData[field.name];
                }
            });
            $.extend(postData,data);
            $("#grid").jqGrid("setGridParam", {
                search: true,
                url:"${ctx}/reserve/findList"
//            ,postData:data
            }).trigger("reloadGrid", [{page:1}]);
        };


		document.onkeydown=function(event){
			var e = event || window.event || arguments.callee.caller.arguments[0];
	    	if(e && e.keyCode==27){ // 按 Esc 
	        	//要做的事情
	      	}
	    	if(e && e.keyCode==113){ // 按 F2 
	         	//要做的事情
	     	}            
	     	if(e && e.keyCode==13){ // enter 键
	        	 //要做的事情
	        	//multipleSearch();
	    	}
		};

	</script>
    <script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
    <style>
		.img_mystyle{
			width: 20px;
			height: 20px;
		}
	</style>
</head>
<body>

<div id="navbar">
       <ul class="breadcrumb">
         <li class="active">日历管理</li>
 
      </ul>
</div>

<div id="select">
        <div class="select-main">
            <form id="searchform" action="" method="post" class="well-work bs-adp form-inline">
                <div  class="row">
                    <div  class="leftLable col-md-1" style="text-align:right">随访名称:</div>
                    <div  class="col-md-2">
                            <select id="status" name="suifangType" class="form-control">
                            <option value=''>请选择</option>
                            <option value='1'>6月随访</option>
                            <option value='2'>末次随访</option>
                            </select>
                    </div>

                    <div  class="col-md-1"  style="text-align:right">医院名称:</div>
                    <div  class="col-md-2">
                    <input id="lcccode" type="hidden" name="lcccode" />
                    <input id="lccname"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
                    </div>

                    <div  class="col-md-1"  style="text-align:right">PID:</div>
                    <div  class="col-md-2">
                        <input type="text" id="pid" name="pid" class="form-control input-sm" placeholder="患者ID">
                    </div>
                </div>

                <div class="row" style="margin-top:10px">
                    <div  class="col-md-1"  style="text-align:right">患者姓名:</div>
                    <div  class="col-md-2">
                        <input type="text" id="pname" name="pname" class="form-control input-sm" placeholder="患者姓名">
                    </div>
                    <div  class="col-md-1"  style="text-align:right">完成情况:</div>
                    <div  class="col-md-2">
                            <select id="wcqk" name="wcqk" class="form-control">
                            <%--<option value=''></option>--%>
                            <%--<option value='1'>已完成</option>--%>
                            <option value=''>请选择</option>
                            <option value='2'>继续答卷</option>
                            <option value='3'>未完成</option>
                            <option value='4'>已死亡</option>
                        </select>
                    </div>
                    <%
                        String ismsg = request.getParameter("ismsg");
                        if("1".equals(ismsg)) {
                    %>
                    <div  class="col-md-1"  style="text-align:right;">所属组别:</div>
                    <div  class="col-md-2">
                        <select class="form-control input-sm" id="belongGroup" name="belongGroup" placeholder="请选择">
                            <option value ="">全部</option>
                            <option value="01">非糖尿病实验组</option>
                            <option value="02">非糖尿病对照组</option>
                            <option value="11">糖尿病实验组</option>
                            <option value="12">糖尿病对照组</option>
                        </select>
                    </div>
                    <%}%>

                    <%--<input id="rq" name="rq" value="${rq}" type="hidden" />--%>
                    <input id="ismsg" name="ismsg" value="${ismsg}" type="hidden" />
                    <div  class="col-md-3">
                        <button type="button" id="btnQuery"
                                class="btn btn-primary btn-align-right btn-sm">查询</button>

                        <c:if test="${currUserLcc  eq '99' }" >
                            <button type="button" id="exportExcel" style="margin-left: 10px"
                                class="btn btn-primary btn-align-right btn-sm">导出</button>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
    </div>

<div id="message" class="alert alert-success" hidden>
    	<button data-dismiss="alert" class="close">&times;</button>
    	<span id="messageSpanId"></span>
	</div>
	<div id="alert" class="alert alert-danger" hidden>
		<strong>Warning!</strong>
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
	        <h4 class="modal-title">更新预约时间</h4>
	      </div>
	      <div class="modal-body">
	        <p>


              <form id="" action="" method="post" class="well-work bs-adp"> <!--  form-inline -->
                  <fieldset>

                      <div class="form-group " >
                          <div class="row">
                              <div  class="col-lg-4 col-md-4">
                                  <label form='f_pname'>患者姓名:</label>
                              </div>
                              <div class="col-lg-6 col-md-6">
                                  <input type="text" id="f_pname" readonly name="f_pname" class="form-control
                     input-sm">
                              </div>
                          </div>

                          <br>
                          <div class="row">
                              <div  class="col-lg-4 col-md-4">
                                  <label form='nextview'>随访名称:</label>
                              </div>
                              <div class="col-lg-6 col-md-6">
                                  <input type="text" id="nextview" name="nextview" readonly class="form-control input-sm">
                              </div>
                          </div>
                          <br>
                          <div class="row">
                              <div  class="col-lg-4 col-md-4">
                                  <label form='srcdate'>原定预约时间:</label>
                              </div>
                              <div class="col-lg-6 col-md-6">
                                  <input type="text" id="srcdate" name="srcdate" readonly class="form-control input-sm">
                              </div>
                          </div>
                          <br>
                          <div class="row">
                              <div  class="col-lg-4 col-md-4">
                                  <label form='newdate'>新预约时间:</label></li>
                              </div>
                              <div class="col-lg-6 col-md-6">
                                  <input type="text" id="newdate" name="newdate" class="Wdate"
                                         onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:getDateStr(mindate),maxDate:getDateStr(maxdate),startDate:getDateStr(startdate),readOnly:true});"
                                  />
                              </div>
                          </div>
                      </div>
                  </fieldset>
              <input type="text" id="f_pid" hidden="true">
              </form>

            </p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	
	<div id='dialog-confirm2' class="modal fade">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <h4 class="modal-title">预约页面</h4>
	      </div>
	      <div class="modal-body">
	        <p></p>
	      </div>
	      <div class="modal-footer">
			<button type="button" id ='cancel2' class="btn btn-default btn-sm" tabindex="1001">取消</button>
	        <button type="button" id ='do_other' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
	      </div>
	    </div><!-- /.modal-content -->
	  </div><!-- /.modal-dialog -->
	</div><!-- /.modal -->

<script type="text/javascript">
$(function(){
    $.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) {
        $('#lccname').autocomplete(data,{
            minChars: 0,
            mustMatch:true,
            width:260,
            // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可
            formatItem: function(item,i, max) {
                return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="180px;">' + item.lccName + '</td></tr></table>';
            },
            // 指定 与 输入文字匹配的字段名
            formatMatch: function(item,i, max) {
                return item.helpCode+item.lccCode;
            },
            // 选中 某条记录在输入框里 显示的数据字段
            formatResult: function(item) {
                return item.lccName;
            }
        });
        //选中 某条记录 触发的事件
        $('#lccname').result(function(event, item){
            if(item){
                if(item.lccCode != $("#lcccode").val()){
                    $("#lcccode").val(item.lccCode);
                }
            }else{
                $("#lcccode").val("");
            }
        });
    });

    var ismsg = ${ismsg};
    var option = {};
    if (ismsg == '1') {
        option = {
            url : '${ctx}/reserve/findList?ismsg=${ismsg}',//status=${status}&rq=${rq}&
            datatype : 'json',
            mtype : 'POST',
            colNames : ['随访名称','LCCID',
//            '医院名称',
                'PID','患者姓名','身份证','手机','第一联系人手机','所属组别','理想时间','预约时间','相距天数','完成情况','最后一次随访时间','预约时间'], //,'预约结果','备注'
            colModel : [
                {name : 'VIEWNAME',width:'100px',sortable: false,align:'center'},
                {name : 'LCC_CODE',width:'80px',sortable: false,align:'center'},
//			    {name : 'LCC_NAME', align:'left' ,sortable: false},
                {name : 'PATIENT_ID',sortable: false,align:'center'},
                {name : 'PATIENT_NAME',width:'80px',sortable: false,align:'center'},
                {name : 'ID_NUMBER',sortable: false,width:'200px',align:'center'},
                {name : 'MOBILE',sortable: false,align:'center'},
                {name : 'LINK_MAN1_MOBILE',sortable: false,align:'center'},
                {
                    meme: 'BELONG_GROUP', sortable: false, align: 'center',
                    formatter:function(cellvalue,option,rowObjects){
                        var cellvalue = rowObjects.BELONG_GROUP;
                        if(cellvalue=='01'){
                            return "非糖尿病实验组";
                        }else if(cellvalue=='02'){
                            return "非糖尿病对照组";
                        }else if(cellvalue=='11'){
                            return '糖尿病实验组';
                        }else if( cellvalue=='12' ){
                            return '糖尿病对照组';
                        }
                        return "";
                    }
                },
                {name : 'LXSJ', align:'center',sortable: true,formatter:yymmddFormatter },
                {name : 'PLANDATE', align:'center',sortable: true,formatter:yymmddFormatter },
                {name : 'XJTS',width:'100px',sortable: true,align:'center'},
                {name : 'WCQK2',width:'120px',align:'center',sortable: false,formatter:function(cellvalue,option,rowObjects){
                    if(cellvalue == '已死亡'){
                        return "<span style='color:red'\">已死亡</span>";
                    }else{
                        return cellvalue;
                    }
                }},
                {name : 'LAST_FOLLOWVIEW_DATE' , hidden:true},
                {name : 'PLANDATE' , hidden:true}
            ],
            rowNum : 15,
            rowList : [ 15, 30, 50,100,150,500 ],
            height : "100%",
            autowidth : true,
            pager : '#pager',
            sortname : 'viewname desc,lcc_Code asc,plandate ',
            altRows:true,
            hidegrid : false,
            viewrecords : true,
            recordpos : 'left',
            sortorder : "ASC",
            emptyrecords : "没有可显示记录",
            loadonce : false,
            multiselect: false,
            jsonReader : {
                root : "rows",
                page : "page",
                total : "total",
                records : "records",
                repeatitems : false,
                cell : "cell",
                id : "patientId"
            }
        };
    } else {
        option = {
            url : '${ctx}/reserve/findList?ismsg=${ismsg}',//status=${status}&rq=${rq}&
            datatype : 'json',
            mtype : 'POST',
            colNames : ['随访名称','LCCID',
//            '医院名称',
                'PID','患者姓名','身份证','手机','第一联系人手机','理想时间','预约时间','相距天数','完成情况','最后一次随访时间','预约时间'], //,'预约结果','备注'
            colModel : [
                {name : 'VIEWNAME',width:'100px',sortable: false,align:'center'},
                {name : 'LCC_CODE',width:'80px',sortable: false,align:'center'},
//			    {name : 'LCC_NAME', align:'left' ,sortable: false},
                {name : 'PATIENT_ID',sortable: false,align:'center'},
                {name : 'PATIENT_NAME',width:'80px',sortable: false,align:'center'},
                {name : 'ID_NUMBER',sortable: false,width:'200px',align:'center'},
                {name : 'MOBILE',sortable: false,align:'center'},
                {name : 'LINK_MAN1_MOBILE',sortable: false,align:'center'},
                {name : 'LXSJ', align:'center',sortable: true,formatter:yymmddFormatter },
                {name : 'PLANDATE', align:'center',sortable: true,formatter:yymmddFormatter },
                {name : 'XJTS',width:'100px',sortable: true,align:'center'},
                {name : 'WCQK2',width:'120px',align:'center',sortable: false,formatter:function(cellvalue,option,rowObjects){
                    if(cellvalue == '已死亡'){
                        return "<span style='color:red'\">已死亡</span>";
                    }else{
                        return cellvalue;
                    }
                }},
                {name : 'LAST_FOLLOWVIEW_DATE' , hidden:true},
                {name : 'PLANDATE' , hidden:true}
            ],
            rowNum : 15,
            rowList : [ 15, 30, 50,100,150,500 ],
            height : "100%",
            autowidth : true,
            pager : '#pager',
            sortname : 'viewname desc,lcc_Code asc,plandate ',
            altRows:true,
            hidegrid : false,
            viewrecords : true,
            recordpos : 'left',
            sortorder : "ASC",
            emptyrecords : "没有可显示记录",
            loadonce : false,
            multiselect: false,
            jsonReader : {
                root : "rows",
                page : "page",
                total : "total",
                records : "records",
                repeatitems : false,
                cell : "cell",
                id : "patientId"
            }
        };
    }

//    option.postData = { 'state':$("#state").val(),'rq':$("#rq").val() };
	$("#grid").jqGrid(option); 
	$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'}
	).navButtonAdd('#pager',{caption:"修改预约时间",buttonicon:"ui-icon-pencil",onClickButton: function(){toAdd()},position:"last"});
	jqgridResponsive("grid",false);
	//取消按钮操作
	$('#cancel').click(function(){
		$('#dialog-confirm').modal('hide');
	});	
	//取消按钮操作
	$('#cancel2').click(function(){
		$('#dialog-confirm2').modal('hide');
	});

	$("#btnQuery").click(function(e){
		multipleSearch();
	});

	function addDate(thisDate,dadd){
		if(thisDate==null){
			var a = new Date();
		}else{
			var a = thisDate;
		}
		
		a = a.valueOf();
		a = a + dadd * 24 * 60 * 60 * 1000;
		a = new Date(a);
		return a;
		}
	function getLigth(vTime,planTime,realTime){
		var today = new Date();
		var vTimeDate = new Date(vTime);
		var planTimeDate = new Date(planTime);
		var realTimeDate = new Date(realTime);
		
		
		//如果检查完成时间有 就是绿灯 说明完成了
		// 1 绿灯     2 黄灯   3红灯
		if(realTime!=null &&  realTime !='null' && realTime.length>0){
			return 1;
		}
		//如果    vTime-90 <= 今天  <= vTime+30
		if(addDate(vTimeDate,-90) <planTimeDate  && planTimeDate <= addDate(vTimeDate,30)){
                   if(planTimeDate >= today && today<= addDate(vTimeDate,30) && planTimeDate<= addDate(vTimeDate,30)){
                	   return 1;
                   }
                 
                   if(  planTimeDate <today &&  planTimeDate<= addDate(vTimeDate,30)){
                	   
                       return 3;
                  }
		}
		
		////如果    vTime+30 <= 今天  <= vTime+30
		if(addDate(vTimeDate,30) <planTimeDate && planTimeDate<= addDate(vTimeDate,90)){
            if( planTimeDate > today && planTimeDate<= addDate(vTimeDate,90) ){
         	   return 2;
            }
          
            if(  planTimeDate < today && planTimeDate<= addDate(vTimeDate,90)  ){
                return 3;
           }
	}
	
		
	}

	function toAdd(){
		 var id = $("#grid").jqGrid('getGridParam','selrow');//根据点击行获得点击行的id（id为jsonReader: {id: "id" },）
		 var rowData = $("#grid").jqGrid("getRowData",id);//根据上面的id获得本行的所有数据
		if($.isEmptyObject(id)) {
			openError('请选择一条数据',2000);
			return;
		}
		$("#do_add").attr("disabled",false);

        $("#f_pid").val(rowData.PATIENT_ID);
        $("#f_pname").val(rowData.PATIENT_NAME);
//        $("#f_lccname").val(rowData.LCC_NAME);
        var date1 = new Date(rowData.LAST_FOLLOWVIEW_DATE - 0);
//        $("#f_lastviewtime").val(date1.getFullYear()+"-"+(date1.getMonth()+1)+"-"+date1.getDate());

        $("#nextview").val(rowData.VIEWNAME);

        if( $.isEmptyObject(rowData.PLANDATE) ){
            $("#srcdate").val();
        }else{
            var date2 = new Date(rowData.PLANDATE - 0);
            $("#srcdate").val(date2.getFullYear()+"-"+(date2.getMonth()+1)+"-"+date2.getDate());
        }

        $('#newdate').val(null);

        mindate = addDate(date1,150);
        maxdate = addDate(date1,210);
        startdate = addDate(date1,180);

//        $('#newdate').unbind("click");

//        $("#newdate").click(function(){
////            WdatePicker({dateFmt:'yyyy-MM-dd',minDate:getDateStr(mindate),maxDate:getDateStr(maxdate),startDate:getDateStr(startdate),readOnly:true});
//        });

        $( "#dialog-confirm" ).modal({
            backdrop: 'static'
        });
    }


	/**
	   修改的记录入库	
	**/
	$('#do_save').click(function(){
        $('#dialog-confirm').modal('hide');

        var data = {};
		var patientId= $("#f_pid").val();
        var newDate= $("#newdate").val();
        data.pid = patientId;
        data.newdate = newDate;

        if( $.isEmptyObject(pid) || $.isEmptyObject(newDate)){
            alert("新预约时间不能为空，请重试！");
            return;
        }
//        $("#do_save").attr("disabled",true);

        $.post("${ctx}/reserve/updateviewdate",data,function(e){
            showResult(e.success,e.message);
            $("#grid").trigger("reloadGrid", [{page:1}]);
        });
	});
	//弹出对话框
	function openDialog(url,data){
		$( "#dialog-confirm" ).modal({
			 backdrop: 'static'
		});
		$( "#do_add").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm p" ).load(url);
	}
	
	
	//弹出对话框
	function openDialog2(url,data){
		$( "#dialog-confirm2" ).modal({
			 backdrop: 'static'
		});
		$( "#do_other").attr("disabled",false);
		//使用此方法防止js缓存不加载
		$("#dialog-confirm2 p" ).load(url);
	}
	
	
	$('#planTime2').datepicker({ 
	 	format: 'yyyy-mm-dd', 
	 	weekStart: 1, 
	 	startDate:new Date(), //开始时间，在这时间之前都不可选
		endDate:'+3',//结束时间，在这时间之后都不可选
	 	autoclose: true, 
	 	todayBtn: 'linked', 	
		language: 'zh-CN'
	 });
	
	function openError(message,delay){
    	$('#alert').show().find('strong').text(message);
    	window.setTimeout(function() {
    		$('#alert').slideUp("slow");
    	}, delay);
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
});


var mindate = '';
var maxdate = '';
var startdate = '';
function getDateStr(d){
    var month = (parseInt(d.getMonth())+1) < 10 ? "0"+(parseInt(d.getMonth())+1) : (parseInt(d.getMonth())+1)
    return  d.getFullYear() + "-" +month+ "-" + d.getDate();
}


$('#exportExcel').click(function(){ // 导出
    var myform = $("#searchform").serializeArray();
    var data = {};
    var str = "?1=1";
    $.each(myform, function(i, field){
        data[field.name]=null;
        if(field.value && ''!=field.value){
            data[field.name] = field.value;
            str = str+"&"+field.name+"="+field.value
        }
    });

    window.open("${ctx}/reserve/expoertExcel"+str);
});

</script>
</body>
</html>