<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="rp" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>事件审定</title>
    <script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
        ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
            color:   #1024e0!important;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color:   #1024e0!important;
            opacity:  1;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color:    #1024e0!important;
            opacity:  1;
        }
        :-ms-input-placeholder { /* Internet Explorer 10-11 */
            color:    #1024e0!important;
        }
    </style>
</head>
<body>
<div id='dialog-event-desc' class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 780px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">当前事件描述</h4>
            </div>
            <div class="modal-body">
                <ul id="permissionTree" class="ztree well" style="height:498px; overflow:auto;"></ul>
            </div>
            <div class="modal-footer">
                <button type="button" id='cancel' class="btn btn-default btn-sm" tabindex="1001">取消</button>
                <button type="button" id='do_save' class="btn btn-primary btn-sm" tabindex="1000">提交</button>
            </div>
        </div>
    </div>
</div>

<div id='dialog-event-add' class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content" style="width: 780px">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">添加审核员</h4>
            </div>
            <div class="modal-body">
                当前用户没有审核权限，是否将当前用户加入审核员？
            </div>
            <div class="modal-footer">
                <button type="button" id='cancel1' class="btn btn-default btn-sm" tabindex="1001">否</button>
                <button type="button" id='do_save1' class="btn btn-primary btn-sm" tabindex="1000">是</button>
            </div>
        </div>
    </div>
</div>

<div id="navbar">
    <ul class="breadcrumb">
        <li class="active">事件审定</li>
    </ul>
</div>
<div class="panel panel-default" style="margin-bottom: 1px;">
    <div class="panel-heading">基本信息</div>
    <div class="panel-body">
        <b>姓名:</b>:</b>${event.patientName}&nbsp;&nbsp;&nbsp;<b>事件ID:</b>${event.eventCode}&nbsp;&nbsp;&nbsp;<b>PID:</b>${event.patientId}&nbsp;&nbsp;&nbsp;<b>协作医院:</b>${lccCode}:${lccName}
    </div>
</div>
<div class="panel panel-default" style="margin-bottom: 1px;">
    <div class="panel-heading" style="cursor: pointer" onclick="showOrHideHistory()" id="historyHead">历史事件↓</div>
    <div class="panel-body" id="historyBody" style="display: none">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th style="width: 10%">事件ID</th>
                <th style="width: 15%">发生日期</th>
                <th style="width: 20%">评审前事件描述</th>
                <th style="width: 20%">评审后事件描述</th>
                <th style="width: 15%">事件结局</th>
                <th style="width: 15%">评审状态</th>
                <th style="width: 5%">新增事件</th>
            </tr>
            </thead>
            <c:forEach items="${eventList}" var="e">
                <tr>
                    <td>
                        <a href="${ctx}/qn/eventMgt/checkEvents/check?eventCode=${e.eventCode}"
                           target="_blank">${e.eventCode}</a>
                    </td>
                    <td><fmt:formatDate value="${e.inHosDate}" pattern="yyyy-MM-dd"/></td>
                    <td>${e.srcdescStr}</td>
                    <td>${e.currentdescStr}</td>
                    <td>${e.eventendingStr}</td>
                    <td>${e.reportstatusStr}</td>
                    <td>
                        <c:if test="${!e.addingEvent}">
                            <a style="cursor: pointer" target="_blank" onclick="addEvent('${e.eventCode}')">新增</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<div class="panel panel-default">
    <div class="panel-heading">评审信息&nbsp;&nbsp;&nbsp;&nbsp;<span style="color: #1024e0;">(未填写的选项将以蓝色标识)</span></div>
    <div class="panel-body">
        <form id="checkForm">
            <table class="table table-bordered">
                <thead>
                <tr>
                    <th style="width: 20%">
                        <input type="hidden" name="eventCode" value="${event.eventCode}">
                        <input type="hidden" name="pid" value="${event.patientId}">
                    </th>
                    <th style="width: 40%">评审前</th>
                    <th style="width: 40%">评审后</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row">事件描述</th>
                    <td>${originDesc} <input type="hidden" name="originDesc" value="${originDesc}"></td>
                    <td><input type="text" class="form-control" id="currentDesc" placeholder="点击选择"
                               value="<rp:cache key="S001" code="${check.currentDesc}"/>"
                               style="width: 200px;" required>
                        <input type="hidden" name="currentDesc" value="${check.currentDesc}">
                        <textarea required placeholder="请输入其他事件描述..." class="form-control" rows="3" name="currentRemark"
                                <c:if
                                        test="${check.currentDesc != 'A15'}"> style="display: none;" </c:if>>${check.currentRemark}</textarea>
                    </td>
                </tr>
                <tr>
                    <th scope="row">额外信息</th>
                    <td>N/A</td>
                    <td id="er">
                    </td>
                </tr>
                <tr>
                    <th scope="row">事件严重程度</th>
                    <td>N/A</td>
                    <td><rp:select type="T004" style="width:200px;" name="isDead" value="${check.isDead}"
                                   extAttr="required" onchange="changeIsDead()"/></td>
                </tr>
                <tr>
                    <th scope="row">事件发生日期</th>
                    <td>
                        <fmt:formatDate value="${event.inHosDate}" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>
                        <input name="occurDate" id="occurDate" placeholder="事件发生日期" style="width:200px;float: left"
                               class="form-control"
                               value="<fmt:formatDate value="${check.occurDate}" pattern="yyyy-MM-dd"/>"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" required/>
                        <rp:select type="T003" style="width:150px;float:left;margin-left:5px;" name="occurDateType"
                                   value="${check.occurDateType}" extAttr="required"/>
                    </td>
                </tr>
                <tr>
                    <th scope="row">事件结束日期</th>
                    <td>
                        <c:if test="${event.isDeath != 1}"><!--非死亡事件才显示事件结束日期-->
                        <fmt:formatDate value="${event.outHosDate}" pattern="yyyy-MM-dd"/>
                        </c:if>
                        <c:if test="${event.isDeath == 1}">
                            --
                        </c:if>

                    </td>
                    <td>
                        <input name="endingDate" id="endingDate" placeholder="事件结束日期" style="width: 200px;float: left"
                               class="form-control"
                               value="<fmt:formatDate value="${check.endingDate}" pattern="yyyy-MM-dd"/>"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                        <rp:select type="T003" style="width:150px;float:left;margin-left:5px;" name="endingDateType"
                                   value="${check.endingDateType}"/>
                    </td>
                </tr>
                <tr>
                    <th scope="row">死亡日期</th>
                    <td>${deathDate}
                        <input name="originDeadDate" type="hidden" value="${deathDate}">
                    </td>
                    <td>
                        <input name="deadDate" placeholder="死亡日期" style="width: 200px;float:left;"
                               class="form-control"
                               value="<fmt:formatDate value="${check.deadDate}" pattern="yyyy-MM-dd"/>"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                        <rp:select type="T003" style="width:150px;float:left;margin-left:5px;" name="deadDateType"
                                   value="${check.deadDateType}"/>
                    </td>
                </tr>
                <tr>
                    <th scope="row">事件诊疗地点</th>
                    <td>N/A</td>
                    <td><rp:select type="T016" style="width:200px;" name="eventPlace" value="${check.eventPlace}"
                                   onchange="changeEventPlace(this)" extAttr="required"/></td>
                </tr>
                <tr>
                    <th scope="row">住院医院名称(事件发生地点)</th>
                    <td id="hospitalnameBefore">${event.hosName}</td>
                    <td><input type="text" class="form-control" id="hospitalName" name="hospitalName"
                               value="${check.hospitalName}"
                               placeholder="点击选择"
                               style="width: 150px;">
                    </td>
                </tr>
                <tr id="zyts">
                    <th scope="row">住院天数(天)</th>
                    <td>N/A</td>
                    <td><input type="text" class="form-control input-sm" name="inHospitalDay" placeholder="住院天数"
                               required value="${check.inHospitalDay}"
                               style="width: 200px;"></td>
                </tr>
                <tr>
                    <th scope="row">事件结局</th>
                    <td>
                        ${eventending}
                        <input type="hidden" name="originEventEnding" value="${eventending}">
                    </td>
                    <td><rp:select type="T001" style="width:200px;" name="eventEnding"
                                   value="${check.eventEnding}" extAttr="required" onchange="changeIsDead()"/></td>
                </tr>
                <tr>
                    <th scope="row">评审状态</th>
                    <td>N/A</td>
                    <td>
                        <rp:select type="T002" style="width:200px;" name="reportStatus" value="${check.reportStatus}"
                                   onchange="changeReportStatus(this)" extAttr="required"/>
                        <textarea class="form-control" rows="3" name="reportRemark" placeholder="请输入需要补充的支持性文件"
                                  <c:if test="${check.reportStatus != 4}">style="display: none"</c:if>
                                  required>${check.reportRemark}</textarea>
                    </td>
                </tr>
                <tr>
                    <th scope="row">医生备注</th>
                    <td colspan="2">
                        <textarea class="form-control" rows="3" name="remark">${check.remark}</textarea>
                    </td>
                </tr>
                <tr>
                    <th scope="row">报告日期/评审日期</th>
                    <td><fmt:formatDate value="${event.eventDate}" pattern="yyyy-MM-dd"/></td>
                    <td><input name="reportDate" placeholder="评审日期" style="width: 200px;"
                               class="form-control input-sm"
                               value="<fmt:formatDate value="${check.reportDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                               onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',maxDate:'%y-%M-%d'})" required/></td>
                </tr>
                <tr>
                    <th scope="row">协作医生/评审人</th>
                    <td><shiro:principal property="name"/></td>
                    <td><input type="text" class="form-control input-sm" name="reportBy" placeholder="评审人"
                               value="${check.reportBy}"
                               style="width: 200px;" required>
                    </td>
                </tr>
                <tr>
                    <th scope="row">密码:</th>
                    <td colspan="2"><input type="password" class="form-control" id="password" style="width:300px;"
                                           placeholder="提交前请输入当前登录用户密码确认操作"></td>
                </tr>
                <tr id="sButton" <c:if test="${!isAuditUser}"> style="display: none" </c:if>>
                    <td colspan="3" style="text-align: center">
                        <button class="btn btn-default" type="button" onclick="returnMain()">返回</button>
                        &nbsp;&nbsp;
                        <button class="btn btn-default" type="button" onclick="submitForm()">提交</button>
                        <span id="submitMsg" style="color: red"></span>
                    </td>
                </tr>

                </tbody>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">


    String.prototype.replaceAll = function (search, replacement) {
        var target = this;
        return target.replace(new RegExp(search, 'g'), replacement);
    };

    function changeEventPlace(v) {
        if ($(v).val() == 1) { //显示住院天数
            var iDay = $('input[name=occurDate]').val();
            var oDay = $('input[name=endingdDate]').val();
            if (iDay && oDay) {
                $("input[name=inHospitalDay]").attr("disabled", false).val(DateDiff(oDay, iDay))
            }
            $("#zyts").show();
            $("input[name=inHospitalDay]").attr("disabled", false)
        } else {
            $("input[name=inHospitalDay]").attr("disabled", true)
            $("#zyts").hide();
        }

        var sel = $(v).val();
        var hosName =  $("#hospitalName").val();
        if(!hosName) {
            hosName = '${event.hosName}';
        }
        if (sel == 1 || sel == 2) {
            $("#hospitalName").val(hosName).attr('required', true);
        } else if (sel == 3 || sel == 5) {
            $("#hospitalName").val(hosName).attr('required', false);
        } else if (sel == 4) {
            $("#hospitalName").val("").attr('required', true);
        }

        // 病人家中住院医院名称不可用
        if (sel == 3) {
            $('#hospitalName').attr('disabled', true).val("")
        } else {
            $('#hospitalName').attr('disabled', false)
        }
    }


    $(function () {

        jQuery.validator.addMethod("dateEquals", function (value, element) {
            if (!$("#occurDate").val()) {
                return true;
            }
            if (!value) {
                return true;
            }
            var occurDate = DateFormat.parse($("#occurDate").val(), 'yyyy-MM-dd')
            var currDate = DateFormat.parse(value, 'yyyy-MM-dd')
            return currDate >= occurDate
        }, "事件结束日期不小于发生日期");

        $("#checkForm").validate({
            rules: {
                endingDate: {
                    dateEquals: true
                },
                deadDate: {
                    dateEquals: true
                }
            },
            messages: {
                endingDate: {},
                deadDate: {
                    dateEquals: '死亡日期不小于事件发生日期'
                }
            }
        });

        $.getJSON("${ctx}/pro/lccuser/getAllActiveLcc", function (data) {
            $('#hospitalName').autocomplete(data, {
                minChars: 0,
                mustMatch: false,
                width: 260,
                // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可
                formatItem: function (item, i, max) {
                    return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="180px;">' + item.lccName + '</td></tr></table>';

                },
                // 指定 与 输入文字匹配的字段名
                formatMatch: function (item, i, max) {
                    return item.helpCode + item.lccCode;
                },
                // 选中 某条记录在输入框里 显示的数据字段
                formatResult: function (item) {
                    return item.lccName;
                }
            });
        });

        // $("#checkForm").validate().form() // form 校验
        // 选择时间诊疗地点，对应改变住院医院名称
        $("select[name=eventPlace]").change(function () {
            changeEventPlace($(this))
        })

        $("#currentDesc").click(function () {
            $("#dialog-event-desc").modal({
                backdrop: 'static'
            });
            $("#do_save").attr("disabled", false);
        })

        $('#cancel').click(function () {
            $('#dialog-event-desc').modal('hide');
        });
        $('#cancel1').click(function () {
            $('#dialog-event-add').modal('hide');
        });

        function onAsyncSuccess(event, treeId, treeNode, msg) {
            var treeObj = $.fn.zTree.getZTreeObj(treeId);
            var node = treeObj.getNodeByParam("grade", 0, null);
            treeObj.expandNode(node, true, true, true);
        }

        var setting = {
            async: {
                enable: true,
                type: "post",
                url: "${ctx}/qn/eventMgt/currdesctree"
            },
            data: {
                key: {
                    name: "value"
                },
                simpleData: {
                    enable: true,
                    idKey: "code",
                    pIdKey: "parentCode"
                }
            },
            callback: {
                onAsyncSuccess: onAsyncSuccess,
                //onDblClick: zTreeOnDblClick
            },
            view: {
                expandSpeed: "fast",
                selectedMulti: false
            },
            edit: {
                showRemoveBtn: false,
                showRenameBtn: false,
                enable: true
            }
        };

        $.fn.zTree.init($("#permissionTree"), setting);

        $("#do_save").click(function () {
            var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
            var treeNode = treeObj.getSelectedNodes()[0];
            if (treeNode == null) {
                alert('请选择');
                return;
            }
            if (treeNode.isParent) {
                alert('您没有选择值!');
                return;
            }
            var code = treeNode.code;
            $("#er").load("${ctx}/qn/eventMgt/openmodalcurrdescer?code=" + code)

            // 如果选择其他
            if (code == 'A15') {
                $("textarea[name=currentRemark]").show()
            } else {
                $("textarea[name=currentRemark]").hide().val("")
            }
            // 如果选择死亡，则
            var parentCode = treeNode.parentCode;
            $("input[name=currentDesc]").val(code);
            $("#currentDesc").val(treeNode.value)
            $('#dialog-event-desc').modal('hide');
            changeIsDead();
        })


        //initHospitalName();// 初始化医院名称
        changeReportStatus($('select[name=reportStatus]'));// 初始化评审状态
        initEr();// 初始化额外事件
        initDate();// 评审后日期默认等于评审前的
        changeIsDead();
        changeEventPlace($('select[name=eventPlace]'))

        setTimeout(function () {
            changexjbzw('select[name=xjbzw]');
            changexdtgb();
        }, 1000)

        // 判断当前用户是否是审核员
        <c:if test="${!isAuditUser}">
        $("#dialog-event-add").modal({
            backdrop: 'static'
        });
        $("#do_save1").attr("disabled", false);
        </c:if>

        // 添加审核员
        $("#do_save1").click(function () {
            $.post("${ctx}/qn/eventMgt/checkEvents1/addAuditUser", function (data) {
                if (data) {
                    $('#dialog-event-add').modal('hide');
                    $("#sButton").show();
                } else {
                    alert("添加审核员失败");
                }
            })
        })

        // 将所有的select默认字体 // font-style: italic;
        //color: #b1a6a6;

        $("select").change(function () {
            var obj = $(this)
            if (obj.val()) {
                obj.css({'color': '#555'})
            } else {
                obj.css({'color': '#1024e0'})
            }
        })

        $("select").each(function () {
            var obj = $(this)
            if (obj.val()) {
                obj.css({'color': '#555'})
            } else {
                obj.css({'color': '#1024e0'})
            }
        })
    })
    // 返回到列表页面
    function returnMain() {
        window.location.href = '${ctx}/qn/eventMgt/checkEvents1'
    }

    // 表单提交
    function submitForm() {
        // 表单校验
        if (!$("#checkForm").validate().form()) {
            return;
        }
        // 密码校验
        var password = $("#password").val();
        if (password != null) {
            $.post("${ctx}/sys/rbac/user/checkConfirmOldPassword", {password: password}, function (data) {
                if (data.result) {
                    var myform = $("#checkForm").serializeArray();
                    var data = {};
                    $.each(myform, function (i, field) {
                        data[field.name] = null;
                        if (field.value && '' != field.value) {
                            data[field.name] = field.value;
                        }
                    });

                    if (data.originDesc) {
                        data.originDesc = data.originDesc.replaceAll('<br/>', '')
                    }
                    if (data.originDeadDate == 'N/A') {
                        delete data.originDeadDate
                    }
                    $.post("${ctx}/qn/eventMgt/checkEvents1/saveEvent", data, function (d) {
                        if (d == "success") {
                            $("#submitMsg").text("保存成功!")
                            setTimeout(function () {
                                window.location.reload()
                            }, 1000)
                        } else {
                            $("#submitMsg").text("保存失败!")
                        }
                    })
                } else {
                    $("#submitMsg").text("密码错误!")
                }
            })
        }

    }

    function changexjbzw(v) {
        if ($(v).val() == 1) {
            $("select[name=xjbzwtype]").attr("disabled", false)
            $("#xj2").show();
            $("#xj3").show();
            $("#xj4").show();
        } else {
            $("select[name=xjbzwtype]").attr("disabled", true)
            $("#xj2").hide();
            $("#xj3").hide();
            $("#xj4").hide();
        }
    }

    function changexdtgb() {
        var val = $("select[name=isxdt]").val()
        if(val == 1) {
            $("#xdtverity").show();
        }else if(val == 2) {
            $("#xdtverity").hide();
        }
    }

    function changeReportStatus(v) {
        if ($(v).val() == 4) {   // 评审后需要补充支持性文件
            $('textarea[name=reportRemark]').show()
        } else {
            $('textarea[name=reportRemark]').hide().val("")
        }
    }

    function initEr() {
        var code = $("input[name=currentDesc]").val();
        var eventCode = $("input[name=eventCode]").val();
        if (code != "") {
            $("#er").load("${ctx}/qn/eventMgt/checkEvents1/openmodalcurrdescer?code=" + code + "&eventCode=" + eventCode)
        }
    }

    function changexdtgb(v) {//
        if ($(v).val() == 1) {
            $("select[name=xdtverity]").attr("disabled", false)
            $("#xdtgb1").show();
        } else {
            $("select[name=xdtverity]").attr("disabled", true)
            $("#xdtgb1").hide();
        }
    }

    function addEvent(eventCode) {
        $.post("${ctx}/qn/eventMgt/checkEvents1/addEvent?eventCode=" + eventCode, function (data) {
            if (data.success) {
                window.open("${ctx}/qn/eventMgt/checkEvents1/check?eventCode=" + data.eventCode)
            } else {

            }
        })
    }

    function DateDiff(sDate1, sDate2) {
        var aDate, oDate1, oDate2, iDays
        aDate = sDate1.split("-")
        oDate1 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])    //转换为12-18-2006格式
        aDate = sDate2.split("-")
        oDate2 = new Date(aDate[1] + '-' + aDate[2] + '-' + aDate[0])
        iDays = parseInt(Math.abs(oDate1 - oDate2) / 1000 / 60 / 60 / 24)    //把相差的毫秒数转换为天数
        return iDays
    }

    $("input[name=inHospitalDay]").blur(function () {
        getInHospitalDay();
    })

    $("input[name=occurDate]").blur(function () {
        getInHospitalDay();
    })

    $("input[name=endingDate]").blur(function () {
        getInHospitalDay();
    })

    $("input[name=deadDate]").blur(function () {
        getInHospitalDay();
    })

    $("select[name=isDead]").change(function () {
        getInHospitalDay();
    })

    // 事件严重程度
    function changeIsDead() {
        // 选择
        var isDead = $("select[name=isDead]").val();
        var chooseDead = $('input[name=currentDesc]').val().indexOf('A10') != -1; //  时间描述是否选择死亡
        var eventEnding = $('select[name=eventEnding]').val() == 2; //事件结局选择死亡
        if (isDead == 1 || chooseDead || eventEnding) {   // 死亡
            $("input[name=endingDate]").attr("required", false).attr("disabled", true).val("")
            $("select[name=endingDateType]").attr("required", false).attr("disabled", true).val("")
            $("input[name=deadDate]").attr("required", true).attr("disabled", false)
            $("select[name=deadDateType]").attr("required", true).attr("disabled", false)
        } else {
            $("input[name=endingDate]").attr("required", true).attr("disabled", false)
            $("select[name=endingDateType]").attr("required", true).attr("disabled", false)
            $("input[name=deadDate]").attr("required", false).attr("disabled", true).val("")
            $("select[name=deadDateType]").attr("required", false).attr("disabled", true).val("")
        }
        getInHospitalDay();
    }

    // 死亡事件，住院天数

    function getInHospitalDay() {
        var v = $('input[name=inHospitalDay]');
        if (!v.val()) {
            return;
        }

        var iDay = $('input[name=occurDate]').val();
        var oDay = $('input[name=endingDate]').val();
        var dDay = $('input[name=deadDate]').val();

        var isDead = $("select[name=isDead]").val() == 1;
        var chooseDead = $('input[name=currentDesc]').val().indexOf('A10') != -1; //  时间描述是否选择死亡
        var eventEnding = $('select[name=eventEnding]').val() == 2; //事件结局选择死亡

        if (isDead || chooseDead || eventEnding) {
            if (iDay && dDay) {
                var d = DateDiff(dDay, iDay);
                if (v.val() > (d + 1 ) || v.val() < (d - 1) || v.val() < 0) {
                    alert("输入住院天数异常");
                    v.val("");
                }
            }
        } else {
            if (iDay && oDay) {
                var d = DateDiff(oDay, iDay);
                if (v.val() > (d + 1 ) || v.val() < (d - 1) || v.val() < 0) {
                    alert("输入住院天数异常");
                    v.val("");
                }
            }
        }
    }

    function initDate() {
        var occurDate = $("input[name=occurDate]").val();   // 事件发生日期
        if (!occurDate) {
            $("input[name=occurDate]").val('<fmt:formatDate value="${event.inHosDate}" pattern="yyyy-MM-dd"/>')
        }
        <c:if test="${event.isDeath != 1}">
        var endingDate = $("input[name=endingDate]").val();   // 事件结束日期
        if (!endingDate) {
            $("input[name=endingDate]").val('<fmt:formatDate value="${event.outHosDate}" pattern="yyyy-MM-dd"/>')
        }
        </c:if>

        var deadDate = $("input[name=deadDate]").val();   // 死亡日期
        if (!deadDate && '${deathDate}' != 'N/A') {
            $("input[name=deadDate]").val('${deathDate}')
        }
    }

    function showOrHideHistory() {
        if ($('#historyBody').is(':hidden')) {
            $('#historyBody').show()
            $("#historyHead").text('历史事件↑')
        } else {
            $('#historyBody').hide()
            $("#historyHead").text('历史事件↓')
        }
    }

</script>
</body>
</html>