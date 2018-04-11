<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@page import="com.bdcor.pip.core.utils.Securitys"%>
<c:set var="currUserLcc" value="<%=Securitys.getUser().getLccCode()%>"/>
<html>
<head>
    <title>干预研究补录</title>

    <style type="text/css">
        .leftLable{
            text-align:right;
        }
    </style>
    <script type="text/javascript">
        //查询绑定
        var multipleSearch = function(){
            var myform = $("#searchForm").serializeArray();
            var data = {};
            $.each(myform, function(i, field){
                data[field.name]=null;
                if(field.value && ''!=field.value){
                    data[field.name] = field.value;
                }
            });

            if( $("div.active")[0].id == "wait" ){
                var postData = $("#grid").jqGrid("getGridParam", "postData");
                $.extend(postData,data);
                $("#grid").jqGrid("setGridParam", {
                    search: true
                }).trigger("reloadGrid", [{page:1}]);
            }else{
                var postData = $("#grid2").jqGrid("getGridParam", "postData");
                $.extend(postData,data);
                $("#grid2").jqGrid("setGridParam", {
                    search: true
                }).trigger("reloadGrid", [{page:1}]);
            }

        };

        document.onkeydown=function(event){
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if(e && e.keyCode==27){ // 按 Esc
            }
            if(e && e.keyCode==113){ // 按 F2
            }
            if(e && e.keyCode==13){ // enter 键
                //multipleSearch();
            }
        };


        $.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) {
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


        $(function(){
            $("#btnQuery").click(multipleSearch);
        });

    </script>

</head>
<body>


<div id="navbar">
    <ul class="breadcrumb">
        <li class="active">干预研究补录</li>
    </ul>
</div>
<form action="${ctx}/msg/join/joinUqs" id="answerQnForm" method="post">
    <input type="hidden" name="patientId" id="answerQnForm_patientId">
    <input type="hidden" name="uqsCode" id="answerQnForm_uqsCode">
</form>
<div id='dialog-confirm' class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">糖尿病患者补充入选问卷</h4>
            </div>
            <div class="modal-body">
                <p></p>
            </div>
            <div class="modal-footer">
                <button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
                <button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div id='dialog-mobile' class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">修改手机号</h4>
            </div>
            <div class="modal-body">
                <p>加载中……</p>
            </div>
            <div class="modal-footer">
                <button type="button" id ='edit_cancel' class="btn btn-default btn-sm" tabindex="1001">
                    取消</button>
                <button type="button" id ='edit_save' class="btn btn-primary btn-sm" tabindex="1000">
                    提交</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div id='dialog-show' class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">糖尿病患者补充入选问卷</h4>
            </div>
            <div class="modal-body" id="modal_body">
                <p></p>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div id="select">
    <div class="select-main">
        <form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
            <div  class="row">
                <div  class="leftLable col-md-1" style="text-align: right" >医院名称:</div>
                <div  class="col-md-2">
                    <input id="lccCode" type="hidden" name="lccCode" />
                    <input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
                </div>
                <div  class="leftLable col-md-1" style="text-align: right" >PID:</div>
                <div  class="col-md-2">
                    <input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
                </div>

                <div  class="leftLable col-md-1" style="text-align: right" >患者姓名:</div>
                <div  class="col-md-2">
                    <input type="text" class="form-control input-sm" name="patientName" id="11" value='' placeholder="患者姓名"/>
                </div>

                <c:if test="${currUserLcc  eq '99' }" >
                    <div  class="leftLable col-md-1">身份证:</div>
                    <div  class="col-md-2">
                        <input type="text" class="form-control input-sm" id="idNumber" name="idNumber" value='' placeholder="身份证"/>
                    </div>
                </c:if>
            </div>
            <div class="row" style="margin-top: 5px;">
                <div  class="leftLable col-md-1" style="text-align: right" >随访类型:</div>
                <div  class="col-md-2">
                    <select class="form-control" id="suifang" name="suifang">
                        <option value="">请选择</option>
                        <option value="010">糖尿病筛查问卷</option>
                        <option value="011">基线问卷</option>
                        <option value="012">随机问卷</option>
                        <%--<option value="000">干预6月随访问卷</option>--%>
                        <option value="014">末次随访问卷</option>
                    </select>
                </div>
                <div  class="leftLable col-md-1" style="text-align: right" >随访状态:</div>
                <div  class="col-md-2">
                    <select class="form-control" id="suifangtype" name="suifangtype">
                        <option value="">请选择</option>
                        <option value="0">开始答卷</option>
                        <option value="2">继续答卷</option>
                        <option value="1">已完成</option>
                        <option value="3">已死亡</option>
                    </select>
                </div>

                <c:if test="${currUserLcc  eq '99' }" >
                    <div  class="leftLable col-md-1">是否糖尿病:</div>
                    <div  class="col-md-2">
                        <select class="form-control input-sm" id="isdiabetes" name="isdiabetes" placeholder="请选择">
                            <option value ="">全部</option>
                            <option value="1">是</option>
                            <option value="2">否</option>
                        </select>
                    </div>

                    <div  class="leftLable col-md-1">所属组别:</div>
                    <div  class="col-md-2">
                        <select class="form-control input-sm" id="belongGroup" name="belongGroup" placeholder="请选择">
                            <option value ="">全部</option>
                            <option value="01">非糖尿病实验组</option>
                            <option value="02">非糖尿病对照组</option>
                            <option value="11">糖尿病实验组</option>
                            <option value="12">糖尿病对照组</option>
                        </select>
                    </div>

                </c:if>
            </div>
            <div class="row">
                <c:if test="${currUserLcc  eq '99' }" >
                    <div  class="leftLable col-md-1">糖尿病判断:</div>
                    <div  class="col-md-1">
                        <select class="form-control input-sm" id="isSystemDiabetes" name="isSystemDiabetes" placeholder="请选择">
                            <option value ="">全部</option>
                            <option value="2">是</option>
                            <option value="1">否</option>
                        </select>
                    </div>
                </c:if>
                <div  class="leftLable col-md-1">是否新增:</div>
                <div  class="col-md-1">
                    <select class="form-control input-sm" id="isNewAdd" name="isNewAdd" placeholder="请选择">
                        <option value ="">全部</option>
                        <option value="1">是</option>
                        <option value="2">否</option>
                    </select>
                </div>
                <div  class="col-md-2"></div><div  class="col-md-2"></div><div  class="col-md-2"></div>
                <div  class="col-md-2">
                    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
                </div>
            </div>
        </form>
    </div>
</div>

<!-- tab页部分 -->
<ul id="myTab" class="nav nav-tabs" style="display: none">
    <li><a href="#wait" data-toggle="tab">待补录人员</a></li>
    <li class="active" ><a href="#info" data-toggle="tab">补录清单</a></li>
    <script type="text/javascript">
        $(function(){
            $('#myTab a[href="#info"]').click(function (e) {
                $("#grid2").trigger("reloadGrid");
                $(this).tab('show')
            })
        });
    </script>
</ul>

<div id="myTabContent" class="tab-content">
    <div id="message" class="alert alert-success" hidden>
        <button data-dismiss="alert" class="close">&times;</button>
        <span id="messageSpanId"></span>
    </div>
    <div id="alert" class="alert alert-danger" hidden>
        <strong>Warning!</strong>
    </div>
    <%--<div class="tab-pane fade" id="wait">--%>

        <%--<div id="jqgrid">--%>
            <%--<table id="grid"></table>--%>
            <%--<div id="pager"></div>--%>
        <%--</div>--%>

<%--<div id='dialog-showform' class="modal fade">--%>
    <%--<div class="modal-dialog">--%>
        <%--<div class="modal-content">--%>
            <%--<p></p>--%>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" id ='change_cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>--%>
                <%--<button type="button" id ='change_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>--%>
            <%--</div>--%>
        <%--</div><!-- /.modal-content -->--%>
    <%--</div><!-- /.modal-dialog -->--%>
<%--</div><!-- /.modal -->--%>

        <%--<script type="application/javascript">--%>
            <%--var option = {--%>
                <%--url : '${ctx}/sys/drop/queryPatientList',--%>
                <%--postData : {--%>
                    <%--"isJoinMsg":2,isRemoved : '0',--%>
                    <%--"processtypes":'2',"idlength":8--%>
                <%--},--%>
                <%--datatype : 'json',--%>
                <%--mtype : 'POST',--%>
                <%--colNames : ['LCCID','医院名称', 'PID','患者姓名','性别',//'身份证','固定电话',--%>
                    <%--'手机号码','第一联系人姓名','与本人关系',--%>
<%--//            '第一联系人手机',--%>
                    <%--'短信干预'//,'6月随访问卷',--%>
                <%--],--%>
                <%--colModel : [--%>
                    <%--{name : 'lccCode',align:'center', index : 'lccCode'}, //,hidden:true--%>
                    <%--{name : 'lccName', index : 'lccName', align:'center',sortable: false },--%>
                    <%--{name : 'patientId',width:'100px', index : 'patientId', align:'center',sortable: false },--%>
                    <%--{name : 'patientName', width:'80px', index : 'patientName', align:'center',sortable: false  },--%>
                    <%--{name : 'sex', index : 'sex',width:'60px', align:'center',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue=='1'){return '男';}return '女';} },--%>
<%--//            {name : 'idNumber', index : 'idNumber', align:'center',sortable: false },--%>
<%--//            {name : 'phone', index : 'phone',width:'100px', align:'center',sortable: false },--%>
                    <%--{name : 'mobile', index : 'mobile', align:'center',sortable: false },--%>
                    <%--{name : 'linkMan1', align:'center',sortable: false },--%>
                    <%--{name : 'linkMan1Relation',  align:'center',sortable: false },--%>
<%--//            {name : 'linkMan1Mobile', align:'center',sortable: false },--%>
                    <%--{name : 'isJoinMsg',  align:'center',sortable: false ,--%>
                        <%--formatter:--%>
                                <%--function(cellvalue, option, rowObjects)--%>
                                <%--{ if(cellvalue=='1')--%>
                                <%--{--%>
                                    <%--return '参加';--%>
                                <%--}else{--%>
                                    <%--<c:choose>--%>
                                    <%--<c:when test="${currUserLcc  eq '99' }">--%>
                                    <%--return "<button class='qnbtn' onclick=\"javascript:showform('"+rowObjects.lccCode+"','" + rowObjects.patientId + "','1')\">不参加</button>";--%>
                                    <%--</c:when>--%>
                                    <%--<c:otherwise>--%>
                                    <%--return '不参加';--%>
                                    <%--</c:otherwise>--%>
                                    <%--</c:choose>--%>
                                <%--}--%>
                                <%--}--%>
                    <%--}--%>
                <%--],--%>
                <%--rowNum : 15,--%>
                <%--rowList : [ 15, 30, 50,100,500 ],--%>
                <%--height : "100%",--%>
                <%--autowidth : true,--%>
                <%--pager : '#pager',--%>
                <%--sortname : 'patient_Id',--%>
                <%--altRows:true,--%>
                <%--hidegrid : false,--%>
                <%--viewrecords : true,--%>
                <%--recordpos : 'left',--%>
                <%--sortorder : "ASC",--%>
                <%--emptyrecords : "没有可显示记录",--%>
                <%--loadonce : false,--%>
                <%--multiselect: false,--%>
                <%--jsonReader : {--%>
                    <%--root : "rows",--%>
                    <%--page : "page",--%>
                    <%--total : "total",--%>
                    <%--records : "records",--%>
                    <%--repeatitems : false,--%>
                    <%--cell : "cell",--%>
                    <%--id : "patientId"--%>
                <%--}--%>
            <%--};--%>
            <%--$("#grid").jqGrid(option);--%>
            <%--$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'}--%>
            <%--).navButtonAdd('#pager',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAdd()},position:"right"})--%>
                    <%--.navButtonAdd('#pager',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toUpdate()},position:"last"});--%>
            <%--jqgridResponsive("grid",false);--%>

            <%--function showform(lccCode , patientId , type){--%>
                <%--openDialog3("${ctx}/sys/drop/openmodalChange?lccCode="+lccCode+"&patientId="--%>
                        <%--+patientId+"&type="+type+"&time="+new Date().getTime());--%>
            <%--}--%>

            <%--//弹出对话框--%>
            <%--function openDialog3(url){--%>
                <%--$( "#dialog-showform" ).modal({--%>
                    <%--backdrop: 'static'--%>
                <%--});--%>
                <%--$( "#change_save").attr("disabled",false);--%>
<%--//使用此方法防止js缓存不加载--%>
                <%--$("#dialog-showform p" ).load(url,{},function(){--%>
                    <%--$("#changeForm").validate({--%>
                        <%--rules: {--%>
                            <%--val:{--%>
                                <%--required:true--%>
                            <%--},--%>
                            <%--person:{--%>
                                <%--required:true,--%>
                                <%--maxlength : 25--%>
                            <%--},--%>
                            <%--changedate:{--%>
                                <%--required:true--%>
                            <%--},--%>
                            <%--bz:{--%>
                                <%--required:true,--%>
                                <%--minlength : 5,--%>
                                <%--maxlength : 512--%>
                            <%--}--%>
                        <%--},--%>
                        <%--messages:{--%>
                            <%--val:{--%>
                                <%--required:'请选择修改的状态'--%>
                            <%--},--%>
                            <%--person:{--%>
                                <%--required:'请填写申请人且长度不超过10个字'--%>
                            <%--},--%>
                            <%--changedate:{--%>
                                <%--required:'请填写申请时间'--%>
                            <%--},--%>
                            <%--bz:{--%>
                                <%--required:'请填写备注信息且长度不少于5个字,不多于512个字'--%>
                            <%--}--%>
                        <%--},--%>
                        <%--errorPlacement : function(error, element) {--%>
                            <%--if (element.is(":radio")){--%>
                                <%--error.appendTo($("#error"));--%>
                            <%--}else{--%>
                                <%--error.appendTo(element.parent());--%>
                            <%--}--%>
                        <%--}--%>
                    <%--});--%>
                    <%--$('#person').unautocomplete();--%>
                    <%--var url = "${ctx}/combox/getautodata?type=person&lccCode="+$("#grid").jqGrid('getRowData',$("#grid").jqGrid('getGridParam','selrow')).lccCode;--%>
                    <%--$('#person').autocomplete(url, {--%>
                        <%--max:10,--%>
                        <%--minChars:0,--%>
                        <%--matchContains: true,--%>
                        <%--formatItem: function(row) {--%>
                            <%--if( row == null || row == '' ){--%>
                                <%--return "";--%>
                            <%--}--%>
                            <%--return row[0]+"|"+row[1];--%>
                        <%--},--%>
                        <%--formatResult: function(row) {--%>
                            <%--return row.join('|');--%>
                        <%--}--%>
                    <%--}).result(function(event,row,formatted){--%>
                        <%--console.log("123");--%>

                    <%--});--%>
                <%--});--%>
            <%--}--%>

            <%--$('#change_cancel').click(function(){--%>
                <%--$('#dialog-showform').modal('hide');--%>
            <%--});--%>
            <%--$('#change_save').click(function(){--%>
                <%--var myform = $("#dialog-showform").find("form").get(0);--%>
                <%--if(!jQuery(myform).validate().form()){ return;}--%>
                <%--var myform = $("#dialog-showform").find("form").serializeArray();--%>
                <%--var data = {};--%>
                <%--$.each(myform, function(i, field){--%>
                    <%--data[field.name] = field.value;--%>
                <%--});--%>

                <%--$("#change_save").attr("disabled",true);--%>
                <%--$.post("${ctx}/sys/drop/savechange",data,function(result){--%>
                    <%--$("#grid").trigger("reloadGrid");--%>
                    <%--$('#dialog-showform').modal('hide');--%>
                    <%--var message = "修改失败！";--%>
                    <%--if(result){--%>
                        <%--message ='修改成功!';--%>
                    <%--}--%>
                    <%--$("#change_save").attr("disabled",false);--%>
                    <%--showResult(result,message);--%>
                <%--},'json');--%>
            <%--});--%>

            <%--function toAdd(){--%>
                <%--var timebak = new Date().getTime();--%>
                <%--openDialog("${ctx}/msg/newjoin/openmodalAddPage?timebak="+timebak);--%>
            <%--}--%>

            <%--//弹出对话框--%>
            <%--function openDialog(url,data){--%>
                <%--$( "#dialog-confirm" ).modal({--%>
                    <%--backdrop: 'static'--%>
                <%--});--%>
                <%--$( "#do_save").attr("disabled",false);--%>
                <%--//使用此方法防止js缓存不加载--%>
                <%--$("#dialog-confirm p" ).load(url);--%>
            <%--}--%>
            <%--//取消按钮操作--%>
            <%--$('#cancel').click(function(){--%>
                <%--$('#dialog-confirm').modal('hide');--%>

            <%--});--%>
            <%--//取消按钮操作--%>
            <%--$('#cancel2').click(function(){--%>
                <%--$('#dialog-confirm2').modal('hide');--%>

            <%--});--%>

            <%--$("#do_save").click(function(){--%>
                <%--var myform = $("#dialog-confirm").find("form").get(0);--%>
                <%--if(!jQuery(myform).validate().form()){ return ;}--%>
                <%--$("#do_save").attr("disabled",true);--%>
                <%--$.post("${ctx}/msg/newjoin/add",{"patientId":$("#patientId").val()},function(data){--%>
                    <%--$("#grid").trigger("reloadGrid");--%>
                    <%--$('#dialog-confirm').modal('hide');--%>
                    <%--showResult(data.success,data.msg,2000);--%>
                <%--});--%>

            <%--});--%>

            <%--function toUpdate(){--%>
                <%--var timebak = new Date().getTime();--%>
                <%--var _ids = $("#grid").jqGrid('getGridParam','selrow');--%>
                <%--if(_ids == null || _ids.length==0){--%>
                    <%--showResult(false,"请选择一条记录");--%>
                    <%--return;--%>
                <%--}--%>
                <%--$( "#dialog-mobile" ).modal({--%>
                    <%--backdrop: 'static'--%>
                <%--});--%>
                <%--$( "#edit_save").attr("disabled",false);--%>
                <%--//使用此方法防止js缓存不加载--%>
                <%--$("#dialog-mobile p" ).load("${ctx}/msg/join/openmodal/toEdit?patientId="+_ids+"&time="+timebak);--%>
            <%--}--%>

            <%--function showResult(result,message,delay){--%>
                <%--$("#messageSpanId").text(message);--%>

                <%--if (!delay || typeof(delay)=="undefined" || typeof(delay)!='number'){--%>
                    <%--delay = 2000;--%>
                <%--}--%>
                <%--if(result){--%>
                    <%--$("#message").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);--%>
                <%--}else{--%>
                    <%--$("#message").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);--%>
                <%--}--%>
                <%--window.setTimeout(function() {--%>
                    <%--$('#message').slideToggle(1000);--%>
                <%--}, delay);--%>
            <%--}--%>

        <%--</script>--%>
    <%--</div>--%>

    <div class="tab-pane fade in active" id="info">

        <div id="jqgrid2" width="100%">
            <table id="grid2" width="100%"></table>
            <div id="pager2"></div>
        </div>



<script type="text/javascript">
            var option = {
                url : '${ctx}/msg/join/list',
                postData:{"processtypes":'2',"isRemoved":0,"idlength":8,"patientId":'${patientId}'},//"isJoinMsg":1,
                datatype : 'json',
                mtype : 'POST',
                colNames : [ 'LCCID','PID','患者姓名','性别',
                    '手机',
                    <c:if test="${currUserLcc  eq '99' }" >
                    '是否吸烟','是否糖尿病', '糖尿病是否与医生判断一致','所属组别','分组日期',
                    </c:if>
                    '糖尿病筛查问卷','基线问卷','随机问卷'
//                    ,'干预6月随访问卷'
                    ,'末次随访问卷'],
                colModel : [
                    {name : 'lccCode',width:'80px', index : 'LCC_CODE', align:'center'},
                    {name : 'patientId',width:'80px', index : 'PATIENT_ID', align:'center'},
                    {name : 'patientName', width:'80px', index : 'patientName', align:'center',sortable: false  },
                    {name : 'sex', index : 'sex',width:'60px', align:'center',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue=='1'){return '男';}return '女';} },
                    {name : 'mobile', index : 'mobile',width:'100px', align:'center',sortable: false },
                    <c:if test="${currUserLcc  eq '99' }" >
                    {name : 'isSmoking', align:'center',width:'80px',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue==1){return '是';}else if(cellvalue ==2)return '否'; else return '';}  },
                    {name : 'isDiabetes',  align:'center',width:'80px',sortable: false,formatter:function(cellvalue, option, rowObjects){ if(cellvalue==1){return '是';}else if(cellvalue ==2)return '否'; else return '';}  },
                    {name : 'isSystemDiabetes',  align:'center',width:'160px',sortable: false,formatter:function(cellvalue, option, rowObjects){
                        if(cellvalue==1){return '否';}
                        else if((rowObjects.state012==1 && cellvalue == null ) || cellvalue == 2) return '是';
                        return "";
                    }},
                    {name : 'belongGroup', index : 'belongGroup',width:'120px', align:'center',sortable: false ,formatter:function(cellvalue, option, rowObjects){
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
                    }},
                    {name : 'groupDate', index : 'groupDate',width:'100px', align:'center',sortable: false,formatter:yymmddFormatter },
                    </c:if>
                    {name : 'state010', align:'center',width:'120px',sortable: false,formatter:function (cellvalue,option,rowObjects){

                        if(cellvalue ==1) {
                            return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','010')\">已完成</button>";
                        }else if( rowObjects.isDead == 1 ){
                            return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                        }else if(cellvalue ==2) {
                            return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','010')\">继续答卷</button>";
                        }else if(rowObjects.mobile!=null && rowObjects.mobile != "" && cellvalue==null){
                            return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','010')\">开始答卷</button>";
                        }
                        return "";
                    } },
                    {name : 'state011', align:'center',width:'120px',sortable: false,formatter:function (cellvalue,option,rowObjects){

                        if(cellvalue == 1){
                            return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','011')\">已完成</button>";
                        }else if( rowObjects.isDead == 1 ){
                            return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                        }else if(cellvalue == 2){
                            return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','011')\">继续答卷</button>";
                        }else if(cellvalue==null && rowObjects.state010==1){
                            return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','011')\">开始答卷</button>";
                        }
                        return "";
                    } },
                    {name : 'state012', align:'center',width:'120px',sortable: false,formatter:function (cellvalue,option,rowObjects){

                        if(cellvalue == 1){
                            return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','012')\">已完成</button>";
                        }else if( rowObjects.isDead == 1 ){
                            return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                        }else if(cellvalue == 2 && rowObjects.isDiabetes!=1){
                            return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','012')\">继续答卷</button>";
                        }else if(cellvalue==null && rowObjects.state011==1 && rowObjects.isDiabetes!=1){
                            return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','012')\">开始答卷</button>";
                        }
                        return "";
                    } },
//                    {name:"" },
                    {name:"state014", align:'center',width:'120px',sortable: false,
                        formatter:function (cellvalue,option,rowObjects){
                            if(cellvalue == 1){
                                return "<button class='qnbtn complete' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','014')\">已完成</button>";
                            }else if( rowObjects.isDead == 1 ){
                                return "<button class='qnbtn' style='color:red'\">已死亡</button>";
                            }else if(cellvalue == 2){
                                return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','014')\">继续答卷</button>";
                            }else if(cellvalue==null && rowObjects.belongGroup != null && rowObjects.belongGroup != ''){
                                if( rowObjects.betweenDays != null && (rowObjects.betweenDays - 0) >= 150  ) {
                                    return "<button class='qnbtn' onclick=\"javascript:answerQn('"+rowObjects.patientId+"','014')\">开始答卷</button>";}else{
                                    return "<button hidden class='qnbtn' style='background:#D5D5D5'" +
                                        ">开始答卷</button>";
                                }
                            }
                            return "";
                        }
                    }
                ],
                rowNum : 15,
                rowList : [ 15, 30, 50,100,500 ],
                height : "100%",
                autowidth : true,
                shrinkToFit:false,
                pager : '#pager2',
                sortname : 'PATIENT_ID',
                altRows:true,
                hidegrid : false,
                viewrecords : true,
                recordpos : 'left',
                sortorder : "ASC",
                emptyrecords : "没有可显示记录",
                loadonce : false,
                multiselect: false,
                <c:if test="${currUserLcc  eq '99' }" >
                shrinkToFit:false,
                </c:if>
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
            $("#grid2").jqGrid(option);
            $("#grid2").jqGrid('navGrid', '#pager2', {edit : false, add : false, del : false, search : false, position : 'right'}
            )
            .navButtonAdd('#pager2',{caption:"新增",buttonicon:"ui-icon-plus",onClickButton: function(){toAddPatient()},position:"last"})
            .navButtonAdd('#pager2',{caption:"修改",buttonicon:"ui-icon-pencil",onClickButton: function(){toModify()},position:"last"})
            .navButtonAdd('#pager2',{caption:"查看",buttonicon:"ui-icon-edit",onClickButton: function(){toShowMsg()},position:"last"});
            ;
            jqgridResponsive("grid2",false);
            $(".ui-jqgrid .ui-jqgrid-bdiv").css("overflow-x","scroll");

            function answerQn(patientId,uqsCode){
                $("#answerQnForm_patientId").val(patientId);
                $("#answerQnForm_uqsCode").val(uqsCode);
                if( uqsCode == '010' ){
                    $("#answerQnForm")[0].action="${ctx}/msg/newjoin/joinUqs";
                }else{
                    $("#answerQnForm")[0].action="${ctx}/msg/join/joinUqs";
                }
                $("#answerQnForm").submit();
            }


            function toAddPatient(){
                openDialog("${ctx}/msg/newjoin/openmodaladdPatient");
            }


            function openDialog(url,data){
                $( "#dialog-confirm" ).modal({
                    backdrop: 'static'
                });
                $( "#do_save").attr("disabled",false);
                //使用此方法防止js缓存不加载
                $("#dialog-confirm p" ).load(url);
            }
            //取消按钮操作
            $('#cancel').click(function(){
                $('#dialog-confirm').modal('hide');
            });

            // 提交保存
            $('#do_save').click(function(){
                var myform = $("#dialog-confirm").find("form").get(0);
                if(!jQuery(myform).validate().form()){
                    jAlert("答案不完整，请完成后提交");
                    return;
                }
                $("#do_save").attr("disabled",true);

                var formData = $("#dialog-confirm").find("form").serializeArray();
                var data = {};
                $.each(formData, function(i, field){
                    data[field.name] = field.value;
                });
                data.hospitaldate = new Date(data.hospitaldate);

                var confirmMsg = "";
                if(  data.isdiabetes != '1' ){
                    confirmMsg += " 第1题";
                }
                if(data.hospitaldate == '' ){
                    confirmMsg += " 第2题";
                }
                if(data.canmsg != '1' ){
                    confirmMsg += " 第3题";
                }
                if(data.requireconsent != '1' ){
                    confirmMsg += " 第4题";
                }
                if(data.canradiomsg != '0' ){
                    confirmMsg += " 第5题";
                }
                if(data.canchat != '0' ){
                    confirmMsg += " 第6题";
                }
                if(data.cantreatment != '0' ){
                    confirmMsg += " 第7题";
                }
                if(data.inotherproj != '0' ){
                    confirmMsg += " 第8题";
                }

//                if( data.isdiabetes == '1' //1
//                    && data.hospitaldate != '' // 2
//                    && data.canmsg == '1' //3
//                    && data.requireconsent == '1' //4
//                    && data.canradiomsg == '0' //5
//                    && data.canchat == '0' //6
//                    && data.cantreatment == '0' //7
//                    && data.inotherproj == '0' //8
//                )
                if(confirmMsg == ""){

                    $.post("${ctx}/msg/newjoin/patientSave",data,function(e){
                        if( e.success ){
                            jAlert("保存成功");

                            $("#patientId").val(e.pid); // 新增患者的pid 查询一次
                            multipleSearch();
                        }else{
                            jAlert("保存失败");
                        }
                    });

                    $('#dialog-confirm').modal('hide');

                }else{
//                    jAlert("该患者不符合入选条件,请确认入选标准题组中"+confirmMsg,function(){
//                        $('#dialog-confirm').modal('hide');
//                    });
                    jConfirm("该患者不符合入选条件，请确认入选标准题组中"+confirmMsg+"的答案",null,function(r){
                            if(r){
                                $('#dialog-confirm').modal('hide');
                            }
                    });
                    $("#popup_cancel").val("返回");
                    $("#popup_cancel")[0].style.marginLeft="20px";
                }
                $("#do_save").attr("disabled",false);
            });

            function toModify(){
                var timebak = new Date().getTime();
                var _ids = $("#grid2").jqGrid('getGridParam','selrow');
                if(_ids == null || _ids.length==0){
                    showResult(false,"请选择一条记录");
                    return;
                }
                openDialog_mobile("${ctx}/msg/join/openmodal/toEdit?patientId="+_ids+"&time="+timebak);
            }

            function openDialog_mobile(url,data){
                $( "#dialog-mobile" ).modal({
                    backdrop: 'static'
                });
                $( "#do_save").attr("disabled",false);
                //使用此方法防止js缓存不加载
                $("#dialog-mobile p" ).load(url);
            }

            $('#edit_save').click(function(){
                var myform = $("#dialog-mobile").find("form").get(0);
                if(!jQuery(myform).validate().form()){ return;}

                $("#edit_save").attr("disabled",true);
                var data = {};
                data.patientId = $('#patientId_').val();
                data.mobile = $('#mobile_').val();

                $.post("${ctx}/msg/newjoin/update",data,function(result){
                    if(result && result !='undefinded' &&result.success==true){
                        $('#dialog-mobile').modal('hide');
                        $("#grid2").trigger("reloadGrid");
                        showResult(true,'保存成功');
                    }else{
                        showResult(false,'保存失败');
                    }
                },'json');
                $("#edit_save").attr("disabled",false);
            });

            $("#edit_cancel").click(function(){
                $('#dialog-mobile').modal('hide');
            });

    //只读展示手动新增患者的基本信息
    function toShowMsg(){
        var timebak = new Date().getTime();
        var _ids = $("#grid2").jqGrid('getGridParam','selrow');
        if(_ids == null || _ids.length==0){
            showResult(false,"请选择一条记录");
            return;
        }
        $.post("${ctx}/msg/newjoin/isHandAdd",{'patientId':_ids},function(e){
            if( e.isHandAdd ){
                openDialog_show("${ctx}/msg/newjoin/openmodaladdPatient?patientId="
                    +_ids+"&time="+timebak);
            }else{
                jAlert("非新增糖尿病患者，无法进行查看操作");
            }
        })
    }

    function openDialog_show(url){
        $( "#dialog-show" ).modal({
            backdrop: 'static'
        });
        $( "#do_save").attr("disabled",false);
        //使用此方法防止js缓存不加载
        $("#dialog-show p" ).load(url);
    }
</script>
    </div>
</div>

</body>
</html>
