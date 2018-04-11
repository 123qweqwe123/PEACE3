<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 16-9-20
  Time: 下午3:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>未回复短信监控</title>
    <script type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>
</head>
<body>

<ul class="breadcrumb">
    <li class="active">未回复短信监控</li>
</ul>
    <div id="select">
        <div class="select-main">
            <form id="searchForm" method="post" class="well-work bs-adp form-inline">
                <div class="row">
                    <div  class="col-md-1" style="text-align: right">医院名称:</div>
                    <div  class="right col-md-2">
                        <input id="lccCode" type="hidden" name="lccCode" />
                        <input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
                    </div>
                    <div  class="col-md-1"  style="text-align: right">PID:</div>
                    <div  class="right col-md-2">
                        <input type="text" class="form-control input-sm" name="patientId" id="patientId" value='' placeholder="患者ID"/>
                    </div>
                </div>
                <div class="row" style="margin-top: 5px;">

                    <div  class="col-md-1" style="text-align: right">所属组别:</div>
                    <div  class="right col-md-2">
                        <select class="form-control input-sm"  name="group" id="group" >
                            <option value="">全部</option>
                            <option value="11">糖尿病实验组</option>
                            <option value="01">非糖尿病实验组</option>
                        </select>
                    </div>

                    <div  class="col-md-1" style="text-align: right">患者姓名:</div>
                    <div  class="right col-md-2">
                        <input type="text" class="form-control input-sm" name="patientName" id="patientName" value='' placeholder="患者姓名"/>
                    </div>

                    <div  class="col-md-4" style="text-align: right">
                        <button type="button" id="btnQuery" class="btn btn-primary btn-sm">查询</button>
                        <button type="button" id="btnExport" class="btn btn-primary btn-sm">导出</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

<div id="jqgrid" style="overflow:auto;">
    <table id="grid"></table>
    <div id="pager"></div>
</div>
<input id="patientId_wait" hidden value="">
<script type="text/javascript">
    $(function(){
        $.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) {
            $('#lccName').autocomplete(data,{
                minChars: 0,
                mustMatch:true,
                width:260,
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
            url : '${ctx}/msg/noReply/watchlist',
            datatype : 'json',
            mtype : 'POST',
            colNames : [ 'LCCID','PID','患者姓名','手机','所属组别','最近连续未回复时间','历史致电次数'
                ,'未处理短信数'
                ,'详情'],
            colModel : [
                {name : 'LCC_CODE', index : 'lccId', align:'center',width:"80px"},
                {name : 'PATIENT_ID', index : 'patientId', align:'center'},
                {name : 'PATIENT_NAME', index : 'patientName', align:'center'},
                {name : 'MOBILE', index : 'mobile', align:'center'},
                {name : 'BELONG_GROUP', index : 'bGroup', align:'center'},
                {name : 'SENDTIME_REAL',align:'center'},//,datatype:'DATE'
                {name : 'CON', index : 'allcount',  align:'center'},
                {name : 'COU', index : 'allcount',  align:'center'},
                {name : '', index : '', align:'center',
                    formatter : function(cellvalue, option, rowObjects){
                    return "<button onclick=\"showInfo('"+rowObjects.LCC_CODE+"','"+rowObjects.PATIENT_ID+"')\">查看</button>";
                }}
            ],
            rowNum : 15,
            rowList : [ 15, 30, 50,100,150,500 ],
            height : "100%",
            autowidth : true,
            pager : '#pager',
            sortname : 'LCCID',
            altRows:true,
            hidegrid : false,
            viewrecords : true,
            recordpos : 'left',
            sortorder : "ASC",
            emptyrecords : "没有可显示记录",
            loadonce : false,
            multiselect: false
        };
        $("#grid").jqGrid(option);
        jqgridResponsive("grid",false);

        $("#btnQuery").click(multipleSearch);

        $('#btnExport').click(function(){ // 导出
            var myform = $("#searchForm").serializeArray();
            var data = {};
            var str = "?1=1";
            $.each(myform, function(i, field){
                data[field.name]=null;
                if(field.value && ''!=field.value){
                    data[field.name] = field.value;
                    str = str+"&"+field.name+"="+field.value
                }
            });

            window.open("${ctx}/msg/noReply/watchExport"+str);
        });

        <%--$('#patientName').unautocomplete();--%>
        <%--var url = "${ctx}/combox/getautodata?type=person&lccCode="+$("#lccCode").val(); // lcc条件难附加--%>
        <%--$('#patientName').autocomplete(url, {--%>
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

    });

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

        var postData = $("#grid").jqGrid("getGridParam", "postData");
        $.extend(postData,data);

        $("#grid").jqGrid("setGridParam", {
            search: true
        }).trigger("reloadGrid", [{page:1}]);
    };
    function showInfo(lccCode , patientId){
        $("#patientId_wait").val(patientId);
        openDialog("${ctx}/msg/noReply/openmodalShowInfo?patientId="+patientId+"&lccCode="+lccCode);
    }

    //弹出对话框
    function openDialog(url){
        $( "#dialog-showinfo" ).modal({
            backdrop: 'static'
        });
        $( "#do_add").attr("disabled",false);
        //使用此方法防止js缓存不加载
        $("#dialog-showinfo p" ).load(url,{},function(){
            $(function(){
                var optionx = {
                    url : '${ctx}/msg/noReply/watiHandlerlist?patientId='+$("#patientId_wait").val(),
                    editurl : '${ctx}/meta/updateMetaData',
                    datatype : 'json',
                    mtype : 'POST',
                    colNames : [ '短信内容','','是否致电','是否打通','致电时间','是否收到短信','未回复原因','未回复原因','','','',''],
                    colModel : [
                        {name : 'MSG_NAME', index : 'lccId', align:'center',width:"80px"},
                        {name : 'SENDTIME_REAL', hidden:true},
                        {name : 'isHandler', index : 'patientId', align:'center',editable:true,edittype:"select",
                            formatter:'select', editoptions:{value:"0:否;1:是",
                            dataEvents: [
                            { type: 'change',          //下拉选择的时候
                                fn: function(e) {
                                    var rowId = this.parentElement.parentElement.id;
                                   if(e.target.value == '1'){
                                       $("#"+rowId+"_isPass")[0].disabled = false;
                                       $("#"+rowId+"_handlerDate")[0].disabled = false;
                                       $("#"+rowId+"_isGetmsg")[0].disabled = false;
                                       $("#"+rowId+"_noreplyReason")[0].disabled = false;
                                       $(this.parentElement.parentElement).find("button")[0].disabled = false;
                                       $(this.parentElement.parentElement).find("button")[1].disabled = false;

                                   }else{
//                                       if(e.target.value == '0'){
                                           $("#"+rowId+"_isPass")[0].disabled = true;
                                           $("#"+rowId+"_handlerDate")[0].disabled = true;
                                           $("#"+rowId+"_isGetmsg")[0].disabled = true;
                                           $("#"+rowId+"_noreplyReason")[0].disabled = true;
                                           $(this.parentElement.parentElement).find("button")[0].disabled = true;
                                           $(this.parentElement.parentElement).find("button")[1].disabled = true;
                                       $("#"+rowId+"_isPass").find("option[text='未致电']").attr("selected",true);
                                       $("#"+rowId+"_isPass").find("option")[0].selected = true;
//                                       $("#"+rowId+"_isGetmsg").find("option[text='未致电']").attr("selected",true);
                                       $("#"+rowId+"_isGetmsg").find("option")[0].selected = true;
                                       $("#"+rowId+"_handlerDate").val("");
                                       $("#"+rowId+"_noreplyReason").val("");
                                   }
                                }
                            }
                        ]
                        }

                        },
                        {name : 'isPass', index : 'patientName', align:'center',editable:true,edittype:'select',
                            formatter:'select', editoptions:{value:":未致电;0:否;1:是",
                            dataEvents: [
                                { type: 'change',          //下拉选择的时候
                                    fn: function(e) {
                                        var rowId = this.parentElement.parentElement.id;
                                        if(e.target.value != '1'){
                                            $("#"+rowId+"_isGetmsg").find("option")[1].selected = true;
                                            $("#"+rowId+"_handlerDate")[0].disabled = true;
                                            $("#"+rowId+"_isGetmsg")[0].disabled = true;
                                            $("#"+rowId+"_isGetmsg").find("option")[0].selected = true;
                                            $(this.parentElement.parentElement).find("button")[0].disabled = true;
                                            $(this.parentElement.parentElement).find("button")[1].disabled = true;
                                        }
                                        else{
                                            $("#"+rowId+"_handlerDate")[0].disabled = false;
                                            $("#"+rowId+"_isGetmsg")[0].disabled = false;
                                            $(this.parentElement.parentElement).find("button")[0].disabled = false;
                                            $(this.parentElement.parentElement).find("button")[1].disabled = false;
                                        }
                                    }
                                }
                            ]} },
                        {name : 'handlerDate', index : 'mobile', align:'center',editable:true,
                            editrules:{required: false},editoptions:{ //date: true,
                            dataInit: function(element,e,obj) {
//                                $(element).datepicker({dateFormat: 'yy-mm-dd'});
//                                $(element).datepicker('option', 'maxDate','2016-09-10');
                                $(element).click(function(){
                                    var datemin =new Date(new Date($("#grid1").getRowData(element.parentElement.parentElement.id).SENDTIME_REAL).getTime() + 3*24*3600*1000);
                                    var dateStr = datemin.getFullYear()+"-"+
                                            (datemin.getMonth() - 0 +1)+"-"+
                                            datemin.getDate();
                                    WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:dateStr,maxDate:'%y-%M-%d'});
                                });
                                }
                            }
                        },
                        {name : 'isGetmsg', align:'center',editable:true,edittype:'select',
                            formatter:'select', editoptions:{value:":未致电;0:否;1:是;2:拒绝回答"}
                        },
                        {name : 'noreplyReason', index : '',editable:true, align:'center',
                            edittype:"text", editrules:{required: false},hidden:true
//                            , editoptions: {rows:"3",cols:"10"}
//                            formatter : function(cellvalue, option, rowObjects){
//                                return "<button onclick=\"showInfo('"+rowObjects.LCCID+"','"+rowObjects.patientId+"')\">填写</button>";
//                            }
                        },
//
                        {name:"reason" , align:'center',
                            formatter : function(cellvalue, option, rowObjects){
                                return "<button onclick=\"showReason('"+option.rowId+"')\">填写</button>";
                            }},
                        {name : 'saveBtn', index : '', align:'center',
                            formatter : function(cellvalue, option, rowObjects){
                                return "<button onclick=\"saveEdit('"+option.rowId+"','"+rowObjects.PATIENT_ID+"')\">提交</button>";
                            }
                        },
                        {name:"MOBILE",hidden:true},{name:"PATIENT_ID",hidden:true},{name:"MSGSEND_ID",hidden:true}
                    ],
                    rowNum : 15,
                    rowList : [ 15, 30, 50,100,150,500 ],
                    height : "400px",
                    autowidth : true,
//                    shrinkToFit:false,
//                    autoScroll: true,
                    pager : '#pager1',
                    sortname : 'LCCID',
                    altRows:true,
                    hidegrid : false,
                    viewrecords : true,
                    recordpos : 'left',
                    sortorder : "ASC",
                    emptyrecords : "没有可显示记录",
                    loadonce : false,
                    multiselect: false,
                    onSelectRow: function(id){
                        if(id && id !== lastsel){
//                            $("#1_isHandler").change(function(){alert(1)});
                            saveDataLocal(lastsel,'');
                            lastsel = id;
                            var rowData = $("#grid1").jqGrid("getRowData", id);
                            $('#grid1').jqGrid('restoreRow',lastsel);
                            $('#grid1').jqGrid('editRow',id,{
                                keys : false,        //这里按[enter]保存
                                url: "clientArray", // 不会向server端触发请求，可在后期手动调用修改
                                mtype : "POST",
                                restoreAfterError: true,
                                extraparam: {
                                },
                                oneditfunc: function(rowid){
                                    console.log(rowid);
                                },
                                succesfunc: function(response){
                                    alert("save success");
                                    return true;
                                },
                                errorfunc: function(rowid, res){
                                    console.log(rowid);
                                    console.log(res);
                                }
                            });
                            $("#"+id+"_isHandler").trigger("change");
                        }
                    },
                    beforeEditCell:function(rowid,cellname,v,iRow,iCol){
                        lastrow = iRow;
                        lastcell = iCol;
                    }
//                    ,afterEditCell : function(rowid, cellname, value, iRow, iCol) {
//                        var cellId = iRow + "_" + cellname;
//                        //判断当前是否为开始日期或者结束日期
//                        if(cellname == "handlerDate" || cellname=="handlerDate") {
//                            $("#" + cellId).live("click", function(){
//                                WdatePicker({dateFmt:'MM-dd',onpicked:function(){
//                                    $('#grid1').jqGrid('saveCell',iRow, iCol);;}
//                                });
//
//                            });
//                        }else{//保存修改的jqgrid列表
//                            $("#" + iRow + "_" + cellname, "#make_up_list").one('blur',
//                                    function() {
//                                        $('#grid1').saveCell(iRow, iCol);
//                                    });
//                        }
//                    }

                };

                $("#grid1").jqGrid(optionx);
                jqgridResponsive("grid1",false);





                var option2 = {
                    url : '${ctx}/msg/noReply/hasHandlerlist?patientId='+$("#patientId_wait").val(),
                    datatype : 'json',
                    mtype : 'POST',
                    colNames : [ '短信内容','是否致电','是否打通','致电时间','是否收到短信','未回复原因','未回复原因','处理人'],
                    colModel : [
                        {name : 'msgName', index : 'lccId', align:'center',width:"80px"},
                        {name : 'isHandler', index : 'patientId', align:'center',formatter : function(cellvalue, option, rowObjects){
                            if(cellvalue=='1'){
                                return "是";
                            }else if(cellvalue=='0'){
                                return "否";
                            }
                            return "";
                        }},
                        {name : 'isPass', index : 'patientName', align:'center',formatter : function(cellvalue, option, rowObjects){
                            if(cellvalue=='1'){
                                return "是";
                            }else if(cellvalue=='0'){
                                return "否";
                            }
                            return "";
                        }},
                        {name : 'handlerDate', index : 'mobile', align:'center',formatter:function(cellvalue, option, rowObjects){
                            var now = new Date(parseInt(cellvalue));
                            var   year=now.getFullYear();
                            var   month=now.getMonth()+1;
                            var   date=now.getDate();
                            var   hour=now.getHours();
                            var   minute=now.getMinutes();
                            var   second=now.getSeconds();
                            return   year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;
                        }},
                        {name : 'isGetmsg', align:'center',formatter : function(cellvalue, option, rowObjects){
                            if(cellvalue=='1'){
                                return "是";
                            }else if(cellvalue=='0'){
                                return "否";
                            }else if(cellvalue=='2'){
                                return '拒绝回答';
                            }
                            return "";
                        }
                        },
                        {name : 'reason', index : '', align:'center',
                            formatter : function(cellvalue, option, rowObjects){
                            return "<button onclick=\"showReasonOnly('"+option.rowId+"','"+rowObjects.noreplyReason+"')\">查看</button>";
                            }
                        },
                        {name : 'noreplyReason', index : '', align:'center',hidden:true},
                        {name : 'createName', index : '', align:'center'}
                    ],
                    rowNum : 15,
                    rowList : [ 15, 30, 50,100,150,500 ],
                    height : "400px",
                    autowidth : true,
                    pager : '#pager2',
                    sortname : 'LCCID',
                    altRows:true,
                    hidegrid : false,
                    viewrecords : true,
                    recordpos : 'left',
                    sortorder : "ASC",
                    emptyrecords : "没有可显示记录",
                    loadonce : false,
                    multiselect: false
                };
                $("#grid2").jqGrid(option2);

                $(".ui-jqgrid .ui-jqgrid-bdiv").css("cssText","height: 150px!important;");
            });
        });
    }
    var lastsel;
    var lastrow;
    var lastcell;
    function saveDataLocal(id,pid){
        saveparameters = {
            "successfunc" : null,
            "url" : "clientArray",
            "extraparam" : {},
            "aftersavefunc" : null,
            "errorfunc": null,
            "afterrestorefunc" : null,
            "restoreAfterError" : true,
            "mtype" : "POST"
        };
        $("#grid1").jqGrid('saveRow',id,  saveparameters);
    }
    function saveEdit(id , pid){
        lastrow = "";
        lastcell = "";
        lastsel = "";
        saveparameters = {
            "successfunc" : null,
            "url" : "clientArray",
            "extraparam" : {},
            "aftersavefunc" : null,
            "errorfunc": null,
            "afterrestorefunc" : null,
            "restoreAfterError" : true,
            "mtype" : "POST"
        };
        $("#grid1").jqGrid('saveRow',id,  saveparameters);
        var obj = $("#grid1").jqGrid("getRowData", id);
        obj.saveBtn ="";

        if( obj.isHandler != '1' ){
            jAlert("未致电时不能提交处理信息！")
            return;
        }else{
           if( obj.isPass != "1" || obj.isGetmsg == "" || obj.noreplyReason == "" || obj.handlerDate == ""){
               jAlert("是否打通选择'是' 是否收到短信选择'是'或者'否' 致电时间,未回复原因不能为空！");
               return ;
           }
        }
        if( obj.noreplyReason.length > 500){
            jAlert("未回复原因描述不能超过500字，请修正！")
            return;
        }

        $.post("${ctx}/msg/noReply/saveHandler",
                obj,
                function(e){
                    $("#grid1").trigger("reloadGrid");
                    $("#grid2").trigger("reloadGrid");
                });
    }

    $(function ()
    {
        $('#dialog-showinfo').on('hide.bs.modal', function (e) {
            if( "dialog-showinfo" == e.target.id ){
                lastrow = "";
                lastcell = "";
                lastsel = "";
                $("#grid").trigger("reloadGrid");
                $(".ui-jqgrid .ui-jqgrid-bdiv").css("cssText","");

            }

        });
    });
</script>
<div id='dialog-showinfo' class="modal fade">
    <div class="modal-dialog" style="width:900px;text-align: center">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">未回复短信致电详情</h4>
            </div>
            <div class="modal-body">
                <p></p>
            </div>
            <%--<div class="modal-footer">--%>
                <%--<button type="button" id ='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>--%>
                <%--<button type="button" id ='do_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>--%>
            <%--</div>--%>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<div id='dialog-reason' class="modal fade">
    <div class="modal-dialog" style="width:900px;text-align: center">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">未回复原因</h4>
            </div>
            <div class="modal-body">
                <p>
                <form id="reason_form" class="form-horizontal">
                <div class="form-group">
                    <label class="col-lg-3 col-md-3  control-label" for="reason_select">请选择:</label>
                    <div class="col-lg-5 col-md-5">
                    <select id="reason_select" class="form-control input-sm" onchange="reason_selectChange()">
                        <option value="患者不愿回复">患者不愿回复</option>
                        <option value="忘记回复">忘记回复</option>
                        <option value="不会回复">不会回复</option>
                        <option value="其它">其它</option>
                    </select>
                        </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 col-md-3  control-label" for="reason_select">请输入具体原因:</label>
                    <div class="col-lg-5 col-md-5" >
                        <div class="col-md-8">
                            <textarea id="reason_text" cols="50" rows="10"></textarea>
                        </div>
                    </div>
                </div>

                </form>
                </p>
            </div>
            <div class="modal-footer" id="modal_foot">
            <button type="button" id ='reason_cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
            <button type="button" id ='reason_save' class="btn btn-primary btn-sm" tabindex="1000">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    $("#reason_cancel").click(function(){
        $( "#dialog-reason" ).modal('hide');
    });
    $("#reason_save").click(function(){
        $("#"+m_row_id+"_noreplyReason").val($("#reason_select").val()+'|'+$("#reason_text").val());
        $( "#dialog-reason" ).modal('hide');
    });
    var m_row_id = "";
    function showReason(rowid){
        m_row_id = rowid;

        if( $("#"+m_row_id+"_isHandler").val() != '1' ){
            return;
        }


        if($("#"+rowid+"_noreplyReason").val() != null && $("#"+rowid+"_noreplyReason").val() !=''){
            var text_arr = $("#"+rowid+"_noreplyReason").val().split("|");
            $("#reason_select").val(text_arr[0]);
            $("#reason_text").val(text_arr[1])
        }else{
            $("#reason_select").val('患者不愿回复');
            $("#reason_text").val('');
            $("#reason_text")[0].disabled = true;
        }
        $("#modal_foot").css("display","block");
        $( "#dialog-reason" ).modal({
            backdrop: 'show'
        });
    }

    function showReasonOnly(id , text){
        var arr = text.split("|");
        $("#reason_select").val(arr[0]);
        $("#reason_text").val(arr[1]);
        $("#modal_foot").css("display","none");
        $( "#dialog-reason" ).modal({
            backdrop: 'show'
        });

    }
    function reason_selectChange(e){
        if( $("#reason_select").val() == "其它" ){
            $("#reason_text")[0].disabled = false;
        }else{
            $("#reason_text")[0].disabled = true;
            $("#reason_text").val('');
        }
    }
</script>
</body>
</html>
