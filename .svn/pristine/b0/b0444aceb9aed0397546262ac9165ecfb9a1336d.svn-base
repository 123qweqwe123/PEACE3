<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>  

<!DOCTYPE html>
<html>
<head>
<title>血检报表明细</title>
</head>
<body>
<div id="navbar">
      <ul class="breadcrumb">
         <li class="active">血检报表明细</li>
      </ul>
</div>

<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#src" data-toggle="tab">
            首次随访
        </a>
    </li>
    <li><a href="#new" data-toggle="tab">6月随访</a></li>
    <li><a href="#lastView" data-toggle="tab">末次随访</a></li>

</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="src">

        <div id="alert" class="alert alert-danger" hidden>
            <strong>Warning!</strong>
        </div>
        <div id="message" class="alert alert-success" hidden>
            <button data-dismiss="alert" class="close">&times;</button>
            <span id="messageSpanId"></span>
        </div>
        <div id="select">
            <div class="select-main">
                <form action="" method="post" class="well-work bs-adp form-inline">
                    <fieldset>
                        <ol>
                            <li class="select-one"> <label form=name>医院名称:</label></li>
                            <li>
                                <input type="hidden" id="lccCode"  />
                                <input class="form-control input-sm" id="lccName"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
                            </li>
                            <li class="select-one"> <label form=name>PID:</label></li>
                            <li>
                                <input class="form-control input-sm" id="patientId"  name="patientId" type="text" placeholder="输入患者PID" />
                            </li>
                            <li class="select-one"> <label form=name>距现在未录时间(大于/天):</label></li>
                            <li>
                                <input class="form-control input-sm" id="toNowDay"  name="toNowDay" type="text" placeholder="天数" />
                            </li>
                            <li> <button type="button" onclick="doQuery();" style="width: 50px" class="btn btn-primary btn-sm">查询</button></li>
                        </ol>
                    </fieldset>
                </form>
            </div>
        </div>
        <div id="jqgrid">
            <table id="grid"></table>
            <div id="pager"></div>
        </div>
        <script type="text/javascript">
            function doExport(){
                window.open('${ctx}/qn/uqsAnswerqnLogReportExport');
            }
            function doQuery(){
                var data = {};
                var postData = $("#grid").jqGrid("getGridParam", "postData");
                var lccCode = $("#lccCode").val();
                if(lccCode !=null && lccCode !=''){
                    data.lccCode = lccCode;
                }else{
                    delete postData.lccCode;
                }
                var patientId = $("#patientId").val();
                if(patientId !=null && patientId !=''){
                    data.patientId = patientId;
                }else{
                    delete postData.patientId;
                }
                var toNowDay = $("#toNowDay").val();
                if(toNowDay !=null && toNowDay !=''){
                    data.toNowDay = toNowDay;
                }else{
                    delete postData.toNowDay;
                }
                $.extend(postData,data);
                $("#grid").jqGrid("setGridParam", {
                    search: true
                }).trigger("reloadGrid", [{page:1}]);
            }
            $(function(){
                $.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) {
                    $('#lccName').autocomplete(data,{
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
                    $('#lccName').result(function(event, item){
                        if(item){
                            if(item.lccCode != $("#lccCode").val()){
                                $("#lccCode").val(item.lccCode);
                            }
                        }else{
                            $("#lccCode").val("");
                        }
                    });
                });
                var option = {
                    url : '${ctx}/qn/uqsBloodReportDetailList',
                    datatype : 'json',
                    mtype : 'POST',
                    colNames : [ 'LCCID','医院名称','PID','姓名','完成问卷类型','完成问卷时间','距现在未录时间(天)'],
                    colModel : [
                        {name : 'LCC_CODE', index : 'LCC_CODE', align:'center'},
                        {name : 'LCC_NAME', index : 'LCC_NAME', align:'left',width:200},
                        {name : 'PATIENT_ID', index : 'PATIENT_ID', align:'center',width:100},
                        {name : 'PATIENT_NAME', index : 'PATIENT_NAME', align:'center',width:100},
                        {name : 'UQS_TYPE', index : 'UQS_TYPE',hidden:true, align:'center',width:100},
                        {name : 'END_TIME', index : 'END_TIME', align:'center',width:100,formatter:yymmddFormatter},
                        {name : 'DAYS',sortable:false, index : 'TO_NOW_DAY', align:'center',formatter :reValue}
                    ],
                    rowNum : 15,
                    rowList : [ 15,30,50,100,500 ],
                    height : "100%",
                    autowidth : true,
                    pager : '#pager',
                    altRows:true,
                    hidegrid : false,
                    viewrecords : true,
                    recordpos : 'left',
                    emptyrecords : "没有可显示记录",
                    loadonce : false,
                    multiselect: false,
                    jsonReader : {
                        root : "rows",
                        page : "page",
                        total : "total",
                        records : "records",
                        repeatitems : false,
                        cell : "cell"
                    }
                };
                $("#grid").jqGrid(option);
                $("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'});
                jqgridResponsive("grid",false);
            });
            function reValue(cellvalue, option, rowObjects) {
                if(cellvalue>=14){
                    return "<span style='color:red'>"+cellvalue+"</span>";
                }
                else{
                    return cellvalue;
                }
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

    </div>
<div class="tab-pane fade" id="new">
        <div id="select">
            <div class="select-main">
                <form id="query2" action="" method="post" class="well-work bs-adp form-inline">
                    <fieldset>
                        <ol>
                            <li class="select-one"> <label form=name>医院名称:</label></li>
                            <li>
                                <input type="hidden" id="lccCode2" />
                                <input class="form-control input-sm" id="lccName2"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
                            </li>
                            <li class="select-one"> <label form=name>患者PID:</label></li>
                            <li>
                                <input type="hidden" id="pid" />
                                <input class="form-control input-sm" id="pid2"  name="patientId" type="text" placeholder="输入患者PID" />
                            </li>

                            <li class="select-one"> <label form=name>患者姓名:</label></li>
                            <li>
                                <input type="hidden" id="panme" />
                                <input class="form-control input-sm" id="patientName"  name="patientName" type="text" placeholder="输入患者姓名" />
                            </li>

                            <li class="select-one"> <label form=name>门诊类型:</label></li>
                            <li>
                                <select class="form-control input-sm"  name="aduType" id="aduType" >
                                    <option value="" selected>全部</option>
                                    <option value="干预首次随访">干预首次随访</option>
                                    <option value="非干预首次面访">非干预首次面访</option>
                                </select>
                            </li>

                            <li class="select-one"> <label form=name>采血地点:</label></li>
                            <li>
                                <select style="margin-top: 5px" class="form-control input-sm"  name="bloodType" id="bloodType" >
                                    <option value="" selected>全部</option>
                                    <option value="本院采血">本院采血</option>
                                    <option value="外院采血">外院采血</option>
                                </select>
                            </li>

                            <li> <button type="button" onclick="doQuery2();" style="width: 50px;margin-top:5px" class="btn btn-primary btn-sm">查询</button></li>
                            <li> <button type="button" onclick="doExport2();" style="width: 50px;margin-top:5px" class="btn btn-primary btn-sm">导出</button></li>
                        </ol>
                    </fieldset>
                </form>
            </div>
        </div>

    <div id="jqgrid2">
        <table id="grid2"></table>
        <div id="pager2"></div>
    </div>

    <script type="text/javascript">
        function doExport2(){
            var str = "?";
            str += "lccCode="+$("#lccCode2").val();
            str += "&patientId="+$("#pid2").val();
            str += "&patientName="+$("#patientName").val();
            str += "&aduType="+$("#aduType").val();
            str += "&bloodType="+$("#bloodType").val();
            str = encodeURI(encodeURI(str));
            window.open('${ctx}/qn/uqsAnswerqnBloodReportExport6'+str);
        }
        function doQuery2(){

            var myform = $("#query2").serializeArray();
            var data = {};
            $.each(myform, function(i, field){
                data[field.name]=null;
                if(field.value && ''!=field.value){
                    data[field.name] = field.value;
                }
            });

            var postData = $("#grid2").jqGrid("getGridParam", "postData");

            var lccCode = $("#lccCode2").val();
            if(lccCode !=null && lccCode !=''){
                data.lccCode = lccCode;
            }else{
                delete postData.lccCode;
            }
            $.extend(postData,data);
            $("#grid2").jqGrid("setGridParam", {
                search: true
            }).trigger("reloadGrid", [{page:1}]);
        }
        $(function(){
            $.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) {
                $('#lccName2').autocomplete(data,{
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
                $('#lccName2').result(function(event, item){
                    if(item){
                        if(item.lccCode != $("#lccCode2").val()){
                            $("#lccCode2").val(item.lccCode);
                        }
                    }else{
                        $("#lccCode2").val("");
                    }
                });
            });
            var option2 = {
                url : '${ctx}/qn/getBloodInfo6',
                datatype : 'json',
                mtype : 'POST',
                colNames : [ 'LCCID','医院名称','PID','患者姓名','门诊类型','采血地点','问卷完成时间','距现在未录时间(天)'],
                colModel : [
                    {name : 'LCC_CODE', index : 'LCC_CODE', align:'center'},
                    {name : 'LCC_NAME', index : 'LCC_NAME', align:'center'},
                    {name : 'PATIENT_ID', index : 'PATIENT_ID', align:'center'},
                    {name : 'PATIENT_NAME', index : 'PATIENT_NAME', align:'center'},
                    {name : 'TYPE',align:'center'},
                    {name : 'PLACE', align:'center'},
                    {name : 'END_TIME', index : 'END_TIME', align:'center',formatter:yymmddFormatter},
                    {name : 'DAYS',sortable:false, index : 'TO_NOW_DAY', align:'center',formatter :reValue}
                ],
                rowNum : 15,
                rowList : [ 15,30,50,100,500 ],
                height : "100%",
                autowidth : true,
                pager : '#pager2',
                altRows:true,
                hidegrid : false,
                viewrecords : true,
                recordpos : 'left',
                emptyrecords : "没有可显示记录",
                loadonce : false,
                multiselect: false,
                jsonReader : {
                    root : "rows",
                    page : "page",
                    total : "total",
                    records : "records",
                    repeatitems : false,
                    cell : "cell"
                }
            };
            $("#grid2").jqGrid(option2);
            $("#grid2").jqGrid('navGrid', '#pager2', {edit : false, add : false, del : false, search : false, position : 'right'});
            jqgridResponsive("grid2",false);
        });
        function reValue(cellvalue, option, rowObjects) {
            if(cellvalue>=14){
                return "<span style='color:red'>"+cellvalue+"</span>";
            }
            else{
                return cellvalue;
            }
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
</div>

<!-- 末次随访血检报告明细 -->
    <div class="tab-pane fade" id="lastView">
        <div id="select">
            <div class="select-main">
                <form id="query3" action="" method="post" class="well-work bs-adp form-inline">
                    <fieldset>
                        <ol>
                            <li class="select-one"> <label form=name>医院名称:</label></li>
                            <li>
                                <input type="hidden" id="lccCode3" />
                                <input class="form-control input-sm" id="lccName3"  name="lccName" type="text" placeholder="输入单位简拼或LCCID" />
                            </li>
                            <li class="select-one"> <label form=name>患者PID:</label></li>
                            <li>
                                <input type="hidden" id="pid312" />
                                <input class="form-control input-sm" id="pid3"  name="patientId" type="text" placeholder="输入患者PID" />
                            </li>

                            <li class="select-one"> <label form=name>患者姓名:</label></li>
                            <li>
                                <input type="hidden" id="panme3" />
                                <input class="form-control input-sm" id="patientName3"  name="patientName" type="text" placeholder="输入患者姓名" />
                            </li>

                            <li class="select-one"> <label form=name>门诊类型:</label></li>
                            <li>
                                <select class="form-control input-sm"  name="aduType" id="aduType3" >
                                    <option value="" selected>全部</option>
                                    <option value="干预末次随访">干预末次随访</option>
                                    <option value="非干预末次面访">非干预末次面访</option>
                                </select>
                            </li>

                            <li class="select-one"> <label form=name>采血地点:</label></li>
                            <li>
                                <select style="margin-top: 5px" class="form-control input-sm"  name="bloodType" id="bloodType3" >
                                    <option value="" selected>全部</option>
                                    <option value="本院采血">本院采血</option>
                                    <option value="外院采血">外院采血</option>
                                </select>
                            </li>

                            <li> <button type="button" onclick="doQuery3();" style="width: 50px;margin-top:5px" class="btn btn-primary btn-sm">查询</button></li>
                            <li> <button type="button" onclick="doExport3();" style="width: 50px;margin-top:5px" class="btn btn-primary btn-sm">导出</button></li>
                        </ol>
                    </fieldset>
                </form>
            </div>
        </div>

        <div id="jqgrid3">
            <table id="grid3"></table>
            <div id="pager3"></div>
        </div>

        <script type="text/javascript">
            function doExport3(){
                var str = "?";
                str += "lccCode="+$("#lccCode3").val();
                str += "&patientId="+$("#pid3").val();
                str += "&patientName="+$("#patientName3").val();
                str += "&aduType="+$("#aduType3").val();
                str += "&bloodType="+$("#bloodType3").val();
                str = encodeURI(encodeURI(str));
                window.open('${ctx}/qn/uqsAnswerqnBloodReportExport12'+str);
            }
            function doQuery3(){

                var myform = $("#query3").serializeArray();
                var data = {};
                $.each(myform, function(i, field){
                    data[field.name]=null;
                    if(field.value && ''!=field.value){
                        data[field.name] = field.value;
                    }
                });

                var postData = $("#grid3").jqGrid("getGridParam", "postData");

                var lccCode = $("#lccCode3").val();
                if(lccCode !=null && lccCode !=''){
                    data.lccCode = lccCode;
                }else{
                    delete postData.lccCode;
                }
                $.extend(postData,data);
                $("#grid3").jqGrid("setGridParam", {
                    search: true
                }).trigger("reloadGrid", [{page:1}]);
            }
            $(function(){
                $.getJSON("${ctx}/pro/lccuser/getAllActiveLcc",function(data) {
                    $('#lccName3').autocomplete(data,{
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
                    $('#lccName3').result(function(event, item){
                        if(item){
                            if(item.lccCode != $("#lccCode3").val()){
                                $("#lccCode3").val(item.lccCode);
                            }
                        }else{
                            $("#lccCode3").val("");
                        }
                    });
                });
                var option3 = {
                    url : '${ctx}/qn/getBloodInfo12',
                    datatype : 'json',
                    mtype : 'POST',
                    colNames : [ 'LCCID','医院名称','PID','患者姓名','门诊类型','采血地点','问卷完成时间','距现在未录时间(天)'],
                    colModel : [
                        {name : 'LCC_CODE', index : 'LCC_CODE', align:'center'},
                        {name : 'LCC_NAME', index : 'LCC_NAME', align:'center'},
                        {name : 'PATIENT_ID', index : 'PATIENT_ID', align:'center'},
                        {name : 'PATIENT_NAME', index : 'PATIENT_NAME', align:'center'},
                        {name : 'TYPE',align:'center'},
                        {name : 'PLACE', align:'center'},
                        {name : 'END_TIME', index : 'END_TIME', align:'center',formatter:yymmddFormatter},
                        {name : 'DAYS',sortable:false, index : 'TO_NOW_DAY', align:'center',formatter :reValue}
                    ],
                    rowNum : 15,
                    rowList : [ 15,30,50,100,500 ],
                    height : "100%",
                    autowidth : true,
                    pager : '#pager3',
                    altRows:true,
                    hidegrid : false,
                    viewrecords : true,
                    recordpos : 'left',
                    emptyrecords : "没有可显示记录",
                    loadonce : false,
                    multiselect: false,
                    jsonReader : {
                        root : "rows",
                        page : "page",
                        total : "total",
                        records : "records",
                        repeatitems : false,
                        cell : "cell"
                    }
                };
                $("#grid3").jqGrid(option3);
                $("#grid3").jqGrid('navGrid', '#pager3', {edit : false, add : false, del : false, search : false, position : 'right'});
                jqgridResponsive("grid3",false);
            });
            function reValue(cellvalue, option, rowObjects) {
                if(cellvalue>=14){
                    return "<span style='color:red'>"+cellvalue+"</span>";
                }
                else{
                    return cellvalue;
                }
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
    </div>



</div>


</body>

</html>