<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="rp" tagdir="/WEB-INF/tags" %>

<!DOCTYPE html>
<html>
<head>
    <title>事件列表</title>
    <script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/static/bdcor/js/common.js"></script>
    <script type="text/javascript">
        //查询绑定
        var multipleSearch = function () {
            var myform = $("#searchForm").serializeArray();
            var data = {};
            $.each(myform, function (i, field) {
                data[field.name] = null;
                if (field.value && '' != field.value) {
                    data[field.name] = field.value;
                }
            });

            var postData = $("#grid").jqGrid("getGridParam", "postData");

            $.extend(postData, data);

            $("#grid").jqGrid("setGridParam", {
                search: true
            }).trigger("reloadGrid", [{page: 1}]);
        };

        function exportExcel() {
            var postData = $("#grid").jqGrid("getGridParam", "postData");
            var pram = "";
            $.each(postData, function (k, v) {
                if (v == null)return true;
                if (pram.length == 0) {
                    pram += "?";
                } else {
                    pram += "&";
                }
                pram += k + "=" + v;
            });
            window.open('${ctx}/qn/eventMgt/download' + pram);
        }


        document.onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 27) { // 按 Esc
                //要做的事情
            }
            if (e && e.keyCode == 113) { // 按 F2
                //要做的事情
            }
            if (e && e.keyCode == 13) { // enter 键
                //要做的事情
                //multipleSearch();
            }
        };

    </script>
    <style>
        .leftLable {
            text-align: right;
        }

        .rightDiv {
            text-align: left;
            padding-left: 0px;
        }

        /* -------------
 * Selects
 * ------------- */
        .jqTransformSelectWrapper {
            width: 45px;
            position: relative;
            height: 31px;
            float: left;
        }

        .jqTransformSelectWrapper div span {

            font-size: 12px;
            float: none;
            position: absolute;
            white-space: nowrap;
            height: 31px;
            line-height: 15px;
            padding: 8px 0 0 7px;
            overflow: hidden;
            cursor: pointer;
        }

        .jqTransformSelectWrapper a.jqTransformSelectOpen {
            display: block;
            position: absolute;
            right: 0px;
            width: 31px;
            height: 31px;
        }

        .jqTransformSelectWrapper ul {
            position: absolute;
            width: 43px;
            top: 30px;
            left: 0px;
            list-style: none;
            background-color: #FFF;
            border: solid 1px #CCC;
            display: none;
            margin: 0px;
            padding: 0px;
            height: 150px;
            overflow: auto;
            overflow-y: auto;
            z-index: 10;
        }

        .jqTransformSelectWrapper ul a {
            display: block;
            padding: 5px;
            text-decoration: none;
            color: #333;
            background-color: #FFF;
            font-size: 12px;
        }

        .jqTransformSelectWrapper ul a.selected {
            background: #EDEDED;
            color: #333;
        }

        .jqTransformSelectWrapper ul a:hover, .jqTransformSelectWrapper ul a.selected:hover {
            background: #3582c4;
            color: #fff;
        }
    </style>
</head>
<body>
<div id="navbar">
    <ul class="breadcrumb">
        <li class="active">事件列表</li>
    </ul>
</div>
<div id="message" class="alert alert-success" hidden>
    <button data-dismiss="alert" class="close">&times;</button>
    <span id="messageSpanId"></span>
</div>
<div id="select">
    <div class="select-main">
        <form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
            <div class="row">
                <div class="leftLable col-md-1">事件发生日期:</div>
                <div class="col-md-5" style="padding-left: 0px">
                    <input name="inHosDateBegin" placeholder="起始日期" id="inHosDateBegin"
                           class="form-control input-sm"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                    <span style="float: left">&nbsp;-&nbsp;</span>
                    <input name="inHosDateEnd" placeholder="结束日期" id="inHosDateEnd"
                           class="form-control input-sm"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                </div>
                <div class="leftLable col-md-1">事件报告日期:</div>
                <div class="col-md-4" style="padding-left: 0px;">
                    <input name="eventDateBegin" placeholder="起始日期" id="eventDateBegin"
                           class="form-control input-sm"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                    <span style="float: left">&nbsp;-&nbsp;</span>
                    <input name="eventDateEnd" placeholder="结束日期" id="eventDateEnd"
                           class="form-control input-sm"
                           onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"/>
                </div>
            </div>
            <div class="row" style="margin-top: 10px">
                <div class="leftLable col-md-1">PID:
                </div>
                <div class="rightDiv col-md-5">
                    <input type="text" class="form-control input-sm addClose" name="patientId" id="patientId" value=''
                           placeholder="PID"
                    />
                </div>
                <div class="leftLable col-md-1">事件ID:
                </div>
                <div class="rightDiv col-md-4">
                    <input type="text" class="form-control input-sm addClose" name="eventCode" id="eventCode" value=''
                           placeholder="事件ID"/>
                </div>

            </div>
            <div class="row" style="margin-top: 10px">
                <div class="leftLable col-md-1">协作医院:
                </div>
                <div class="rightDiv col-md-5">
                    <input type="hidden" id="lccCode" name="lccCode">
                    <input type="text" id="lccName" name="lccName" class="form-control input-sm"
                           placeholder="输入单位简拼或LCCID">
                </div>
                <div class="leftLable col-md-1">事件结局:
                </div>
                <div class="rightDiv col-md-4">
                    <rp:select type="T001" style="width:200px;" name="eventending" def="true"/>
                </div>
            </div>
            <div class="row" style="margin-top: 10px">
                <div class="leftLable col-md-1">评审状态:
                </div>
                <div class="rightDiv col-md-5">
                    <rp:select type="T002" style="width:200px;" name="reportstatus" def="true"/>
                </div>
                <div class="leftLable col-md-1">终点事件:
                </div>
                <div class="rightDiv col-md-5">
                    <select name="isEnding" class="form-control">
                        <option value="">-请选择-</option>
                        <option value=1>是</option>
                        <option value=2>否</option>
                    </select>
                    <div class="leftLable">
                        <button type="button" id="importEvent" class="btn btn-primary
						btn-sm">导入未审核事件
                        </button>
                        <button type="button" id="btnExport" class="btn btn-primary
						 btn-sm">导出[通过查询条件]
                        </button>
                        <button type="button" id="btnQuery" class="btn btn-primary
						btn-sm">查询
                        </button>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>

<div id="jqgrid">
    <table id="grid"></table>
    <div id="pager"></div>
</div>

<script type="text/javascript">

    ;(function ($) {
        $.fn.addClose = function (options) {
            var opts = $.extend({}, $.fn.addClose.defaults, options);

            return this.each(function () {
                var obj = $(this), width = parseInt(obj.css('width'));
                obj.css(opts.css);
                obj.click(function (e) {
                    var offsetX = e.offsetX;
                    if (offsetX >= width - opts.clickAreaXRight) {
                        opts.callClickFun(obj)
                    }
                })
                obj.mousemove(function (e) {
                    e.stopPropagation()
                    var offsetX = e.offsetX;
                    if (offsetX >= width - opts.clickAreaXRight) {
                        opts.callMousemoveFun(obj, 1)
                    } else {
                        opts.callMousemoveFun(obj, 2)
                    }
                })
            });
        }

        $.fn.addClose.defaults = {
            css: {
                'background-image': 'url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAQAAAC1+jfqAAAABGdBTUEAALGPC/xhBQAAACBjSFJNAAB6JgAAgIQAAPoAAACA6AAAdTAAAOpgAAA6mAAAF3CculE8AAAAAmJLR0QAAKqNIzIAAAAJcEhZcwAAAlgAAAJYAJvGvrMAAAAHdElNRQfeBgoKJRmFEj/KAAAAuElEQVQoz2XRsW7CMBRA0dOWRGn/uK2EggCJKmxsHRn7b9kiCKSRO1hJY+Px3SvL148fnaNKft6cdM50gl6tTHDl4C5oOeoFN5uF8urLILjYUandEmXCvY84KW1mpVDN+FMxXfmv7DWPeKmMRsE1x1DYGwXBYG01jZ9n4WWueFIs5lnY4PcheoGv1rZZNEnYKoku458f5m8psuhaxck9wUul18RlXbxn3aVaH5d11tplu4wva7S+/wAYyWlkkqIbxgAAACV0RVh0ZGF0ZTpjcmVhdGUAMjAxNi0wOS0xN1QxNToyMTowMyswODowMG9GGUwAAAAldEVYdGRhdGU6bW9kaWZ5ADIwMTQtMDYtMTBUMTA6Mzc6MjUrMDg6MDB+ZygYAAAATXRFWHRzb2Z0d2FyZQBJbWFnZU1hZ2ljayA3LjAuMS02IFExNiB4ODZfNjQgMjAxNi0wOS0xNyBodHRwOi8vd3d3LmltYWdlbWFnaWNrLm9yZ93ZpU4AAABjdEVYdHN2Zzpjb21tZW50ACBHZW5lcmF0b3I6IEFkb2JlIElsbHVzdHJhdG9yIDE2LjAuMCwgU1ZHIEV4cG9ydCBQbHVnLUluIC4gU1ZHIFZlcnNpb246IDYuMDAgQnVpbGQgMCkgIHILdZYAAAAYdEVYdFRodW1iOjpEb2N1bWVudDo6UGFnZXMAMaf/uy8AAAAYdEVYdFRodW1iOjpJbWFnZTo6SGVpZ2h0ADI4NcVka+4AAAAXdEVYdFRodW1iOjpJbWFnZTo6V2lkdGgAMjg1VpU7swAAABl0RVh0VGh1bWI6Ok1pbWV0eXBlAGltYWdlL3BuZz+yVk4AAAAXdEVYdFRodW1iOjpNVGltZQAxNDAyMzY3ODQ1NXCZSAAAABJ0RVh0VGh1bWI6OlNpemUAMi45N0tCIetwRgAAAF90RVh0VGh1bWI6OlVSSQBmaWxlOi8vL2hvbWUvd3d3cm9vdC9zaXRlL3d3dy5lYXN5aWNvbi5uZXQvY2RuLWltZy5lYXN5aWNvbi5jbi9zcmMvMTE3MDQvMTE3MDQxMy5wbmeNgrYCAAAAAElFTkSuQmCC)',
                'background-repeat': 'no-repeat',
                'background-attachment': 'scroll',
                'background-size': '8px 8px',
                'background-position': '98% 50%'
            },
            clickAreaXRight: 10,
            callClickFun: function (obj) {  // 默认清空表单
                $(obj).focus().val('')
            },
            callMousemoveFun: function (obj, type) {
                if (type == 1) {
                    $(obj).css('cursor', 'pointer')
                } else {
                    $(obj).css('cursor', 'auto')
                }
            }
        };
        var jqTransformGetLabel = function (objfield) {
            var selfForm = $(objfield.get(0).form);
            var oLabel = objfield.next();
            if (!oLabel.is('label')) {
                oLabel = objfield.prev();
                if (oLabel.is('label')) {
                    var inputname = objfield.attr('id');
                    if (inputname) {
                        oLabel = selfForm.find('label[for="' + inputname + '"]');
                    }
                }
            }
            if (oLabel.is('label')) {
                return oLabel.css('cursor', 'pointer');
            }
            return false;
        };

        /* Hide all open selects */
        var jqTransformHideSelect = function (oTarget) {
            var ulVisible = $('.jqTransformSelectWrapper ul:visible');
            ulVisible.each(function () {
                var oSelect = $(this).parents(".jqTransformSelectWrapper:first").find("select").get(0);
                //do not hide if click on the label object associated to the select
                if (!(oTarget && oSelect.oLabel && oSelect.oLabel.get(0) == oTarget.get(0))) {
                    $(this).hide();
                }
            });
        };

        $.fn.jqTransSelect = function () {
            return this.each(function (index) {
                var $select = $(this);
                var w = $select.width(), h = $select.height(), o = $select.offset();

                if ($select.hasClass('jqTransformHidden')) {
                    return;
                }
                if ($select.attr('multiple')) {
                    return;
                }

                var oLabel = jqTransformGetLabel($select);
                /* First thing we do is Wrap it */
                $select.addClass('jqTransformHidden')
                var $wrapper = $('<div class="jqTransformSelectWrapper"></div>');
                $wrapper.css({
                    position: 'absolute',
                    top: o.top,
                    left: o.left
                })
                /* Now add the html for the select */
                $wrapper.prepend('<div><input type="text" class="form-control" style="width:' + w + 'px"><a href="#" class="jqTransformSelectOpen"></a></div><ul></ul>');
                var $ul = $('ul', $wrapper).css('width', $select.width()).hide();
                /* Now we add the options */
                $('option', this).each(function (i) {
                    var oLi = $('<li><a href="#" index="' + i + '">' + $(this).html() + '</a></li>');
                    $ul.append(oLi);
                });

                /* Add click handler to the a */
                $ul.find('a').click(function () {
                    $('a.selected', $wrapper).removeClass('selected');
                    $(this).addClass('selected');
                    /* Fire the onchange event */
                    if ($select[0].selectedIndex != $(this).attr('index') && $select[0].onchange) {
                        $select[0].selectedIndex = $(this).attr('index');
                        $select[0].onchange();
                    }
                    $select[0].selectedIndex = $(this).attr('index');
                    $('input:eq(0)', $wrapper).val($(this).html());
                    $ul.hide();
                    return false;
                });
                /* Set the default */
                $('a:eq(' + this.selectedIndex + ')', $ul).click();
                $('input:first', $wrapper).click(function () {
                    $("a.jqTransformSelectOpen", $wrapper).trigger('click');
                });
                oLabel && oLabel.click(function () {
                    $("a.jqTransformSelectOpen", $wrapper).trigger('click');
                });
                this.oLabel = oLabel;

                /* Apply the click handler to the Open */
                var oLinkOpen = $('a.jqTransformSelectOpen', $wrapper)
                    .click(function () {
                        //Check if box is already open to still allow toggle, but close all other selects
                        if ($ul.css('display') == 'none') {
                            jqTransformHideSelect();
                        }
                        if ($select.attr('disabled')) {
                            return false;
                        }

                        $ul.slideToggle('fast', function () {
                            var offSet = ($('a.selected', $ul).offset().top - $ul.offset().top);
                            $ul.animate({scrollTop: offSet});
                        });
                        return false;
                    })
                ;

                // Set the new width
                var iSelectWidth = $select.outerWidth();
                var oSpan = $('span:first', $wrapper);
                var newWidth = (iSelectWidth > oSpan.innerWidth()) ? iSelectWidth + oLinkOpen.outerWidth() : $wrapper.width();
                $wrapper.css('width', newWidth);
                $ul.css('width', newWidth - 2);
                oSpan.css({width: iSelectWidth});

                // Calculate the height if necessary, less elements that the default height
                //show the ul to calculate the block, if ul is not displayed li height value is 0
                $ul.css({display: 'block', visibility: 'hidden'});
                var iSelectHeight = ($('li', $ul).length) * ($('li:first', $ul).height());//+1 else bug ff
                (iSelectHeight < $ul.height()) && $ul.css({height: iSelectHeight, 'overflow': 'hidden'});//hidden else bug with ff
                $ul.css({display: 'none', visibility: 'visible'});
                $select.hide()
                $('body').append($wrapper)
            });
        };

    })(jQuery);

    $(function () {

        $("#importEvent").click(function () {
            var options = {
                title: '添加待审核事件',
                htmlText: '<textarea class="form-control" rows="3" id="eventCodeList" placeholder="事件id按照逗号分隔开或者每行一个事件id"></textarea>',
                callback: function () {
                    $.post("${ctx}/qn/eventMgt/updateEventCheckStatus", {eventCodeList: $("#eventCodeList").val()}, function (data) {
                        if (data) {
                            setTimeout(function () {
                                var option1 = {
                                    title: '添加待审核事件',
                                    submitBtnText: '',
                                    htmlText: data
                                }
                                openModalDialog(option1);
                                $("#grid").jqGrid("setGridParam", {
                                    search: true
                                }).trigger("reloadGrid", [{page: 1}]);
                            }, 500)
                        }
                    })
                    return true;
                }
            }
            openModalDialog(options)
        })
        $('.addClose').addClose()

        $.getJSON("${ctx}/pro/lccuser/getAllActiveLcc", function (data) {
            $('#lccName').autocomplete(data, {
                minChars: 0,
                mustMatch: true,
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
            //选中 某条记录 触发的事件
            $('#lccName').result(function (event, item) {
                if (item) {
                    if (item.lccCode != $("#lccCode").val()) {
                        $("#lccCode").val(item.lccCode);
                    }
                } else {
                    $("#lccCode").val("");
                }
            });
        });

        $("#btnExport").click(function () {
            exportExcel();
        });

        var option = {
            url: '${ctx}/qn/eventMgt/getCheckEvents',
            datatype: 'json',
            mtype: 'POST',
            colNames: ['事件ID', 'PID', '病人姓名', 'LCCID', '协作医院', '协作医院ID', '事件发生日期', '事件报告日期', '评审后事件描述', '事件结局', '评审状态', '评审状态'],
            colModel: [
                {
                    name: 'eventCode', index: 'e.EVENT_CODE', align: 'center', formatter: function (c) {
                    var url = "${ctx}/qn/eventMgt/checkEvents/check?eventCode=" + c;
                    return "<a style='color: blue' href='" + url + "' target='_blank'>" + c + "</a>";
                }
                },
                {name: 'patientId', align: 'center', index: 'e.PATIENT_ID'},

                {name: 'patientName', align: 'center', index: "NLSSORT(e.patient_name,'NLS_SORT = SCHINESE_PINYIN_M')"},
                {name: 'lccCode', align: 'center', index: 'e.LCC_CODE'},
                {name: 'lccName', align: 'center'},
                {name: 'lccName', align: 'center', hidden: true},
                {name: 'inHosDate', align: 'center', formatter: yymmddFormatter, index: 'e.IN_HOS_DATE'},
                {name: 'eventDate', align: 'center', formatter: yymmddFormatter, index: 'e.EVENT_DATE'},
                {name: 'currentdescStr', align: 'center'},
                {name: 'eventendingStr', align: 'center'},
                {name: 'reportstatusStr', align: 'center', hidden: true},
                {
                    name: 'checkStatus', formatter: function (v) {
                    if (v == 1) {
                        return "待审核"
                    } else if (v == 2) {
                        return "需再审核"
                    } else if (v == 3) {
                        return "有效"
                    } else if (v == 4) {
                        return "评审后需补充支持性文件"
                    } else if (v == 5) {
                        return "现有资料不足无法补充"
                    } else if (v == 6) {
                        return "无效-非结局事件"
                    } else if (v == 7) {
                        return "无效-重复"
                    } else if (v == 8) {
                        return "需讨论"
                    } else if (v == 10) {
                        return "无任何资料"
                    } else {
                        return ""
                    }
                }
                }
            ],
            rowNum: 15,
            rowList: [15, 30, 50, 100, 500],
            height: "100%",
            autowidth: true,
            pager: '#pager',
            sortname: 'e.LCC_CODE,e.EVENT_CODE',
            altRows: true,
            hidegrid: false,
            viewrecords: true,
            recordpos: 'left',
            sortorder: "ASC",
            emptyrecords: "没有可显示记录",
            loadonce: false,
            multiselect: false
        };
        $("#grid").jqGrid(option);
        $("#grid").jqGrid('navGrid', '#pager', {edit: false, add: false, del: false, search: false, position: 'right'});
        jqgridResponsive("grid", false);

        $("#btnQuery").click(multipleSearch);
    });
</script>
</body>

</html>