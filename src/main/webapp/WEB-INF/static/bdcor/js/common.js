/**
 * 定义了目前系统里面的公用js
 * 主要包含 表单提交校验js/模态框/级联自动补全表单/输入框输入数字
 * Created by rp on 2015/12/14.
 */

/**
 *定义公共的js
 *require之后,可以直接调用
 */

/****************************************************jqgrid的日期格式化**************************************************/
var priorityDateFormatter = function (cellvalue, options, rowObject) {
    var udate = "";
    if (cellvalue) {
        var date = new Date(cellvalue);
        udate = DateFormat.format(date, 'MM-dd hh:mm');
    }
    return udate;
};

var priorityFullDateFormatter = function (cellvalue, options, rowObject) {
    var udate = "";
    if (cellvalue) {
        var date = new Date(cellvalue);
        udate = DateFormat.format(date, 'yyyy-MM-dd hh:mm:ss');
    }
    return udate;
};


var yyyyMMddhhmmDateFormatter = function (cellvalue, options, rowObject) {
    var udate = "";
    if (cellvalue) {
        var date = new Date(cellvalue);
        udate = DateFormat.format(date, 'yyyy-MM-dd hh:mm');
    }
    return udate;
};

var mmddFormatter = function (cellvalue, options, rowObject) {
    var udate = '';
    if (cellvalue) {
        var date = new Date(cellvalue);
        udate = DateFormat.format(date, 'MM-dd');
    }
    return udate;
};

var yymmddFormatter = function (cellvalue, options, rowObject) {
    var udate = '';
    if (cellvalue) {
        var date = new Date(cellvalue);
        udate = DateFormat.format(date, 'yyyy-MM-dd');
    }
    return udate;
};

var hhmmssFormatter = function (cellvalue, options, rowObject) {
    var udate = '';
    if (cellvalue) {
        var date = new Date(cellvalue);
        udate = DateFormat.format(date, 'hh:mm:ss');
    }
    return udate;
};

var hhmmFormatter = function (cellvalue, options, rowObject) {
    var etadate = '';
    if (cellvalue) {
        var date = new Date(cellvalue);
        etadate = DateFormat.format(date, 'hh:mm');
    }
    return etadate;
};

var idCardFormatter = function (cellvalue, options, rowObject) {
    if (cellvalue) {
        var v = cellvalue.trim();
        var reg = new RegExp(v.slice(6, 14));
        return v.replace(reg, '******');
    }
    return '无';
}

/********************************************************DateFormat******************************************************/
DateFormat = (function () {

    var SIGN_REGEXP = /([yMdhsm])(\1*)/g;
    var DEFAULT_PATTERN = 'yyyy-MM-dd';

    function padding(s, len) {
        len = len - (s + '').length;
        for (var i = 0; i < len; i++) {
            s = '0' + s;
        }
        return s;
    };
    return ({
        format: function (date, pattern) {
            pattern = pattern || DEFAULT_PATTERN;
            return pattern.replace(SIGN_REGEXP, function ($0) {
                switch ($0.charAt(0)) {
                    case 'y' :
                        return padding(date.getFullYear(), $0.length);
                    case 'M' :
                        return padding(date.getMonth() + 1, $0.length);
                    case 'd' :
                        return padding(date.getDate(), $0.length);
                    case 'w' :
                        return date.getDay() + 1;
                    case 'h' :
                        return padding(date.getHours(), $0.length);
                    case 'm' :
                        return padding(date.getMinutes(), $0.length);
                    case 's' :
                        return padding(date.getSeconds(), $0.length);
                }
            });
        },

        parse: function (dateString, pattern) {
            var matchs1 = pattern.match(SIGN_REGEXP);
            var matchs2 = dateString.match(/(\d)+/g);
            if (matchs1.length == matchs2.length) {
                var _date = new Date(1970, 0, 1);
                for (var i = 0; i < matchs1.length; i++) {
                    var _int = Number(matchs2[i]);
                    var sign = matchs1[i];
                    switch (sign.charAt(0)) {
                        case 'y' :
                            _date.setFullYear(_int);
                            break;
                        case 'M' :
                            _date.setMonth(_int - 1);
                            break;
                        case 'd' :
                            _date.setDate(_int);
                            break;
                        case 'h' :
                            _date.setHours(_int);
                            break;
                        case 'm' :
                            _date.setMinutes(_int);
                            break;
                        case 's' :
                            _date.setSeconds(_int);
                            break;
                    }
                }
                return _date;
            }
            return null;
        },
        parseck: function (c_date) {
            if (!c_date)
                return "";
            var tempArray = c_date.split("-");
            if (tempArray.length != 3) {
                return 0;
            }
            var dateArr = c_date.split(" ");
            var date = null;
            if (dateArr.length == 2) {
                var yymmdd = dateArr[0].split("-");
                var hhmmss = dateArr[1].split(":");
                date = new Date(yymmdd[0], yymmdd[1] - 1, yymmdd[2], hhmmss[0], hhmmss[1], hhmmss[2]);
            } else {
                date = new Date(tempArray[0], tempArray[1] - 1, tempArray[2], 00, 00, 00);
            }
            return date;
        }
    });
})();

function changeDateFormat(cellval) {
    var date = new Date(parseInt(cellval) * 1000);
    var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
    var currentDate = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
    var hour = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
    var mints = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
    var sec = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
    return date.getFullYear() + "-" + month + "-" + currentDate + " " + hour + ":" + mints + ":" + sec;
}

/******************************************************校验 START**************************************************/
/**
 * 身份证号校验
 * @param code
 * @returns {boolean}
 * @constructor
 */
function IdentityCodeValid(code) {
    var city = {
        11: "北京",
        12: "天津",
        13: "河北",
        14: "山西",
        15: "内蒙古",
        21: "辽宁",
        22: "吉林",
        23: "黑龙江 ",
        31: "上海",
        32: "江苏",
        33: "浙江",
        34: "安徽",
        35: "福建",
        36: "江西",
        37: "山东",
        41: "河南",
        42: "湖北 ",
        43: "湖南",
        44: "广东",
        45: "广西",
        46: "海南",
        50: "重庆",
        51: "四川",
        52: "贵州",
        53: "云南",
        54: "西藏 ",
        61: "陕西",
        62: "甘肃",
        63: "青海",
        64: "宁夏",
        65: "新疆",
        71: "台湾",
        81: "香港",
        82: "澳门",
        91: "国外 "
    };
    var tip = "";
    var pass = true;
    if (!code || !/^[1-9][0-9]{5}(19[0-9]{2}|200[0-9]|2010)(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])[0-9]{3}[0-9xX]$/i.test(code)) {
        tip = "身份证号格式错误";
        pass = false;
    }

    else if (!city[code.substr(0, 2)]) {
        tip = "地址编码错误";
        pass = false;
    }
    else {
        //18位身份证需要验证最后一位校验位
        if (code.length == 18) {
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
            //校验位
            var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++) {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if (parity[sum % 11] != code[17]) {
                tip = "校验位错误";
                pass = false;
            }
        }
    }
    //if (!pass) alert(tip);
    return pass;
}
function EmailValid(code) {
    var emailReg = /^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/
    return emailReg.test(code);
}
function PhoneValid(code) {
    var phoneReg = /^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
    return phoneReg.test(code);
}
/******************************************************动态modal START**************************************************/
//bootstrap样式的model窗口 text_url, _w, _h, _title, callback, _p
function openModalDialog(options) {
    var defaults = {
        closeBtnText: '关闭',
        submitBtnText: '确定',
        height: '',
        width: '650px',//默认值600px
        title: '',
        requestUrl: '',
        requestParams: '',
        htmlText: '',
        showFooter: true,
        otherBtn: [],
        /*其他按钮组
         otherBtn:{
         text:'',//按钮名字
         style:'',
         title:'',
         callback:function(){}//按钮绑定事件
         },
         */
        callback: function () {
        }//执行提交操作
    };
    options = $.extend({}, defaults, options);
    var dialogId = "tempxx-dialog" + parseInt(Math.random() * 1000000);
    var dialogHtml = '<div id="' + dialogId + '" class="modal fade" >'
        + '<div class="modal-dialog" style="width:' + options.width + '; ">'
        + '<div class="modal-content">'
        + '<div class="modal-header">'
        + '<button type="button" class="close" data-dismiss="modal" aria-hidden="true" >&times;</button>'
        + '<h4 class="modal-title">' + options.title + '</h4>'
        + '</div>'
        + '<div class="modal-body" style="height:' + options.height + '; overflow:auto;overflow-x: hidden;">'
        + '<!-- 放置html --><div style="width:100%;text-align:center;"><i class="fa fa-spinner fa-spin"></i></div>'
        + '</div>'
    ;
    if (options.showFooter) {
        var otherBtnText = "";
        if (options.otherBtn) {
            for (var i in options.otherBtn) {
                var _btnText = options.otherBtn[i]['text'],
                    _btnStyle = options.otherBtn[i]['style'],
                    _btnId = dialogId + "_" + i,
                    _btnTitle = options.otherBtn[i]['title'];
                otherBtnText += '<button type="button" class="' + _btnStyle + '" id="' + _btnId + '-btn">' + _btnText + '</button>';
            }
        }
        var submitButton = "";
        if (options.submitBtnText) {//显示确定按钮
            submitButton = '<button type="submit" class="btn btn-primary" id="' + dialogId + '-btn">' + options.submitBtnText + '</button>';
        }
        dialogHtml += ('<div class="modal-footer">'
        + '<button type="button" class="btn btn-default" data-dismiss="modal">' + options.closeBtnText + '</button>'
        + otherBtnText
        + submitButton
        + '</div>');
    }
    dialogHtml += '</div><!-- /.modal-content -->'
        + '</div><!-- /.modal-dialog -->'
        + '</div>';

    //=====构造model对话框=====
    if ($('#' + dialogId)[0]) {
        $('#' + dialogId).remove();
    }
    $('body').append(dialogHtml);

    //=====填充内容=====
    if (options.requestUrl && options.requestUrl.indexOf("/") != -1) {//判断是url还是text
        var timeFlag = new Date().getTime();
        var eventName = 'temp-event' + timeFlag;
        var param = {eventName: eventName};
        if (options.requestParams != '') $.extend(param, options.requestParams);
        $('#' + dialogId + ' .modal-body').load(options.requestUrl + '?' + $.param(param));
        //========绑定确定事件============
        $(document).bind(eventName, function (e, text) {
            options.callback(e, text);
            $('#' + dialogId).modal('hide');
        });
    } else {
        $('#' + dialogId + ' .modal-body').html(options.htmlText);
    }
    //=========初始化对话框==========
    $("#" + dialogId).modal({
        backdrop: 'static',
        show: true
    });

    //========band窗口关闭时销毁dom========
    $(document).on('hidden.bs.modal', function (e) {
        $('#' + dialogId).remove();
    });

    //========当确认时销毁dom========
    if (options.submitBtnText) {
        $(document).off('click', '#' + dialogId + '-btn').on('click', '#' + dialogId + '-btn', function () {
            $("#" + dialogId + "-btn").prop("disabled", true);
            $.ajaxSetup({async: false});
            if (options.callback()) {//
                $('#' + dialogId).modal('hide');
            } else {
                $("#" + dialogId + "-btn").prop("disabled", false);
            }
        });
    }
    if (options.otherBtn) {
        for (var i in options.otherBtn) {
            var btnId = dialogId + "_" + i,
                _callback = _btnStyle = options.otherBtn[i]['callback'];
            $(document).off('click', '#' + btnId + '-btn').on('click', '#' + btnId + '-btn', function () {
                $("#" + btnId + "-btn").prop("disabled", true);
                if (_callback()) {//
                    $('#' + dialogId).modal('hide');
                } else {
                    $("#" + dialogId + "-btn").prop("disabled", false);
                }
            });
        }
    }
}

/******************************************************jqGrid START**************************************************/
/**
 * 拓展grid方法,当页面变化时jqgrid自适应
 * 使用方法:$("#grid").jqGrid(option).jqgridResize();
 * @param option .
 * author:rp
 */
$.fn.jqgridResize = function (option) {
    option = option || {};
    var _me = this;
    if (!_me.jqGrid) {
        throw new Error("非jqgrid对象");
    }
    var _wrapDiv = $("body").find("#gbox_" + _me.attr("id")).parent("div");
    var _wrapWidth = _wrapDiv.width();//新页面的grid外层包装div的宽度
    var _resize = function () {
        if (_wrapWidth != _wrapDiv.width()) {
            _wrapWidth = _wrapDiv.width();
            if (option.groupFlag) {
                _me.jqGrid('destroyGroupHeader');
                _me.find("table").setGridWidth(mediawidth);
                _me.find("table").jqGrid('setGroupHeaders', {
                    useColSpanStyle: true,
                    groupHeaders: option.groupHeaders
                });
            }
            else {
                _me.setGridWidth(_wrapWidth);
            }
        }
    };
    $(window).resize(function () {//resize方法会执行两次,当前页面和新页面分别执行
        _resize();
    });
    //点击页面窗口切换按钮
    $("a.sidebar-toggle").click(function () {
        setTimeout(_resize, 300);
    });
    return _me;
};
/**
 *
 * @param querySelector
 */
$.fn.buildGridQuery = function (querySelector) {
    var _me = this;
    var postData = _me.jqGrid("getGridParam", "postData");
    $(querySelector).find("input").each(function () {
        var name = this.name, val = this.value;
        if (val) {
            postData[name] = val;
        } else {
            delete postData[name];
        }
    });
    _me.jqGrid("setGridParam", {
        search: true
    }).trigger("reloadGrid", [{page: 1}]);
};

/**
 * 加密combox请求地址
 * obj={url:..,table:..,columns:..,where:..}
 */
function encodeURI4Combox(obj) {
    var _url = obj.url + "?";
    _url += "table=" + obj.table;
    if (obj.columns == null) {
        _url += "&columns=*"
    } else {
        _url += ("&columns=" + encodeURIComponent(obj.columns));
    }
    if (obj.where != null) {
        _url += ("&where=" + encodeURIComponent(obj.where));
    }
    return _url;
}
/**
 * 对autocomplete插件进一步包装
 * 支持多级级联
 * @param option 参数
 * var option={requestParam:'请求参数',
 *             match:'查询条件',
 *             items:{//列表显示字段和字段的样式
 *              itemId:style
 *             }
 *             result:'显示结果',//选中某条输入框显示的结果
 *             dependencys:{//多条结果
 *              selector:itemName
 *             },
 *             defaults:{},//autocomplete的默认配置参数
 *             cascade:{
 *                casSelector:'',//选择器
 *                casParam:{paramKey:itemFieldName},//url拼接参数
 *                casOption:''
 *             }//定义级联
 *           }
 */
$.fn.warpAutocomplete = function (option) {
    var _me = this;

    $.getJSON(encodeURI4Combox(option.requestParam), function (data) {
        var _options = {
            // position: 'fixed',
            formatItem: function (item, i, max) {
                var _items = option.items;
                var itemBody = '<table><tr>';
                for (var _item in _items) {
                    itemBody += '<td style="' + _items[_item] + '">' + item[_item] + '</td>'
                }
                itemBody += '</tr></table>';
                return itemBody;
            },
            formatMatch: function (item, i, max) {
                return item[option.match];
            },
            formatResult: function (item) {
                return item[option.result];
            }
        };
        _options = $.extend(true, {}, _options, option.defaults);
        _me.autocomplete(data, _options);
        //获取结果
        _me.result(function (event, item) {
            var _dep = option.dependencys, _cas = option.cascade;
            if (item && _dep) {
                for (var selector in _dep) {
                    $(selector).val(item[_dep[selector]]);
                }
            }
            //清空表单数据时会执行
            if (!item) {
                for (var selector in _dep) {
                    $(selector).val("");
                }
            }
            if (item && _cas) {//定义级联插件
                $(_cas.casSelector).unautocomplete();
                var _casParam = _cas.casParam;
                var _casParam2Append = "";//追加到原requestParam后面的参数
                for (var _paramKey in _casParam) {
                    var _itemKey = _casParam[_paramKey];
                    var _paramValye = item[_itemKey];
                    _casParam2Append += " and " + _paramKey + "=" + _paramValye;
                }
                var _casOption = $.extend(true, {}, _cas.casOption);//复制一份where条件,避免污染源对象,必须deepCopy,否则依然会污染源对象
                _casOption.requestParam.where += _casParam2Append;
                $(_cas.casSelector).warpAutocomplete(_casOption);
            }
        });
    });
}

var calculateFunctionValue = function (func, args, defaultValue) {
    if (typeof func === 'string') {
        // support obj.func1.func2
        var fs = func.split('.');

        if (fs.length > 1) {
            func = window;
            $.each(fs, function (i, f) {
                func = func[f];
            });
        } else {
            func = window[func];
        }
    }
    if (typeof func === 'function') {
        return func.apply(null, args);
    }
    return defaultValue;
};
/******************************************************表单验证 START**************************************************/
/**表单验证函数 Start*/
function required(val) {
    if (val == null || val == "")
        return "不能为空";
}
function mobile(val) {
    if (!val) {
        return;
    }
    if (!PhoneValid(val)) {
        return "格式错误";
    }
}
function length(val, min, max) {
    if (!val)
        return;
    var len = val.length;
    if (min && len < parseInt(min)) {
        return "长度不能小于" + min;
    }
    if (max && len > parseInt(max)) {
        return "长度不能大于" + max;
    }
}
function confirmPwd(val, selector) {
    var valConfirm = $(selector).val();
    if (val != valConfirm) {
        return '两次输入不一致';
    }
}
/**
 * 简单的表单验证
 * 验证
 * {required fnname(arg1,args)}多个校验规则按照空格分开,如 fn(a1,a2)
 * requried(必填项)
 * @param option
 * option:{msg:'直接弹出错误消息',hideMsg:'true/false 隐藏显示消息框',msgType:1}//1:错误 2:成功 3:
 * @return true|false 校验成功|失败
 */
$.fn.formvalidate = function (option) {
    option = option || {};
    var _me = this, _classReg = /{(.*)}/;
    var _inputarr = _me.find("input");
    _inputarr.push(_me.find("select"));//添加select标签
    $(".msg-wrap").remove();
    var _msgWrap = '<div class="msg-wrap"><div class="msg-error"><i class="fa fa-minus-circle" style="cursor: pointer"></i>{msg}</div></div>';

    function addMsgWrap() {
        _me.append(_msgWrap);
        $(".msg-wrap").delegate("i", "click", function () {
            $(".msg-wrap").fadeOut('slow', function () {
                $(".msg-wrap").remove();
            });
        })
    }

    if (option.hideMsg) {
        $(".msg-wrap").remove();
        return false;
    }
    if (option.msg) {//直接弹出错误
        _msgWrap = _msgWrap.replace(/{msg}/, option.msg);
        if (option.msgType == 2) {
            _msgWrap = _msgWrap.replace("msg-error", "msg-success");
        }
        addMsgWrap();
        return false;
    }
    for (var i = 0; i < _inputarr.length; i++) {
        var _curr = $(_inputarr[i]);
        var _class = _curr.attr("class"), _v = _curr.val(), _id = _curr.attr("id");
        var result = _classReg.exec(_class);
        var errorMsg = "", err = false;
        //获取表单标题
        var _labelText = $("label[for=" + _id + "]").text();
        var reg = /[^:：*]+/;
        var _text = reg.exec(_labelText);
        if (_text) {
            errorMsg += _text[0];
        }
        if (result) {
            var _validateArr = result[1].trim().split(" ");//校验串
            for (var a in _validateArr) {
                var fnStr = _validateArr[a];
                var _fnStr = fnStr.match(/([^\(]+)(\((.+)\))?/);//校验参数按照逗号分隔
                var fnName = _fnStr[1], argStr = _fnStr[3], args;
                if (argStr) {
                    args = "'" + _v + "','" + (argStr.split(",").join("','")) + "'";
                } else {
                    args = "'" + _v + "'";
                }
                //多个参数
                //校验开始
                try {
                    var msg = eval(fnName + ".call(null," + args + ")");
                    if (!$.isEmptyObject(msg)) {
                        errorMsg += msg;
                        _msgWrap = _msgWrap.replace(/{msg}/, errorMsg);
                        addMsgWrap();
                        return false;
                    }
                } catch (e) {
                    throw new Error(fnName + "未定义");
                }
            }
        }
    }
    return true;
}

/**表单序列化
 * jquery自带序列化方法无法序列化disabled的表单
 *
 * */
$.fn.formserialize = function (option) {
    option = option || {};
    var _default = {
        seriDisabled: true//disabled的表单是否序列化
    }
    option = $.extend({}, _default, option);
    var _me = this;
    var seriStr = _me.serialize();
    if (option.seriDisabled) {
        _me.find("input:disabled,select:disabled").each(function () {
            seriStr += ("&" + $(this).attr("name") + "=" + $(this).val());
        })
    }
    return seriStr;
}
/******************************************************页面消息弹出**************************************************/
function openError(message, delay) {
    $('#alert').show().find('strong').text(message);
    window.setTimeout(function () {
        $('#alert').slideUp("slow");
    }, delay);
}

function showResult(result, message, delay) {
    $("#messageSpanId").text(message);
    if (!delay || typeof(delay) == "undefined" || typeof(delay) != 'number') {
        delay = 2000;
    }
    if (result) {
        $("#message").addClass('alert-success').removeClass('alert-danger').slideToggle(1000);
    } else {
        $("#message").addClass('alert-danger').removeClass('alert-success').slideToggle(1000);
    }
    window.setTimeout(function () {
        $('#message').slideToggle(1000);
    }, delay);
}

function isNotNull(v) {
    if (v == null)
        return false;
    if (v.trim() == "")
        return false;
    return true;
}

function getScrollBarWidth() {
    if (document.documentElement.scrollHeight <= document.documentElement.clientHeight) {
        return 0;
    }
    var inner = document.createElement('p');
    inner.style.width = '100%';
    inner.style.height = '200px';

    var outer = document.createElement('div');
    outer.style.position = 'absolute';
    outer.style.top = '0px';
    outer.style.left = '0px';
    outer.style.visibility = 'hidden';
    outer.style.width = '200px';
    outer.style.height = '150px';
    outer.style.overflow = 'hidden';
    outer.appendChild(inner);

    document.body.appendChild(outer);
    var w1 = inner.offsetWidth;
    outer.style.overflow = 'scroll';
    var w2 = inner.offsetWidth;
    if (w1 === w2) w2 = outer.clientWidth;

    document.body.removeChild(outer);

    return w1 - w2;
}


function initLccQuery(params) {
    var lccQueryObj = {
        areaCode: '',
        areaName: '',
        rccCode: '',
        rccName: '',
        lccCode: '',
        lccName: '',
        url: ''
    };
    $.extend(lccQueryObj, params)

    if (lccQueryObj.areaName) {
        initProvince(lccQueryObj)
    }
    if (lccQueryObj.rccName) {
        initRcc(lccQueryObj)
    }
    if (lccQueryObj.lccName) {
        initLcc(lccQueryObj)
    }
}

function initProvince(lccQueryObj) {
    $("#" + lccQueryObj.areaName).unautocomplete();
    $.getJSON(lccQueryObj.url + "/combox/province", function (data) {
        $('#' + lccQueryObj.areaName).autocomplete(data, {
            minChars: 0,
            mustMatch: true,
            width: 260,
            sortName: 'PROVINCE_CODE',
            formatItem: function (item, i, max) {
                return '<table><tr><td width="80px;">' + item.PROVINCE_CODE + '</td><td width="180px;">' + item.PROVINCE_NAME + '</td></tr></table>';

            },
            formatMatch: function (item, i, max) {
                return item.HELP_CODE + item.PROVINCE_CODE + item.PROVINCE_NAME;
            },
            formatResult: function (item) {
                return item.PROVINCE_NAME;
            }
        });
        $('#' + lccQueryObj.areaName).result(function (event, item) {
            if (item) {
                if (item.PROVINCE_CODE != $("#" + lccQueryObj.areaCode).val()) {
                    $("#" + lccQueryObj.areaCode).val(item.PROVINCE_CODE);
                    if (lccQueryObj.rccName) {
                        initRcc(lccQueryObj)
                    }
                    if (lccQueryObj.lccName) {
                        initLcc(lccQueryObj)
                    }
                }
            } else {
                $("#" + lccQueryObj.areaCode).val("");
                if (lccQueryObj.rccName) {
                    initRcc(lccQueryObj)
                }
                if (lccQueryObj.lccName) {
                    initLcc(lccQueryObj)
                }
            }
        });
    });
}

function initRcc(lccQueryObj) {
    $("#" + lccQueryObj.rccName).unautocomplete();
    $("#" + lccQueryObj.rccCode).val('');
    $("#" + lccQueryObj.rccName).val('');
    var areaCode = "";
    if(lccQueryObj.areaCode) {
        areaCode = $("#" + lccQueryObj.areaCode).val()
    }
    $.getJSON(lccQueryObj.url + "/combox/rcc?provinceCode=" + areaCode, function (data) {
        $('#' + lccQueryObj.rccName).autocomplete(data, {
            minChars: 0,
            mustMatch: true,
            width: 260,
            sortName: 'RCC_CODE',
            // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可
            formatItem: function (item, i, max) {
                return '<table><tr><td width="80px;">' + item.RCC_CODE + '</td><td width="180px;">' + item.RCC_NAME + '</td></tr></table>';
            },
            // 指定 与 输入文字匹配的字段名
            formatMatch: function (item, i, max) {
                return item.HELP_CODE + item.RCC_CODE + item.RCC_NAME;
            },
            // 选中 某条记录在输入框里 显示的数据字段
            formatResult: function (item) {
                return item.RCC_NAME;
            }
        });
        //选中 某条记录 触发的事件
        $('#' + lccQueryObj.rccName).result(function (event, item) {
            if (item) {
                if (item.RCC_CODE != $("#" + lccQueryObj.rccCode).val()) {
                    $("#" + lccQueryObj.rccCode).val(item.RCC_CODE);
                    if (lccQueryObj.lccName) {
                        initLcc(lccQueryObj)
                    }
                }
            } else {
                $("#" + lccQueryObj.rccCode).val("");
                if (lccQueryObj.lccName) {
                    initLcc(lccQueryObj)
                }
            }
        });
    });
}

function initLcc(lccQueryObj) {
    $("#" + lccQueryObj.lccName).unautocomplete();
    $("#" + lccQueryObj.lccName).val('');
    $("#" + lccQueryObj.lccCode).val('');
    $.getJSON(lccQueryObj.url + "/combox/lcc?provinceCode=" + $("#" + lccQueryObj.areaCode).val() + "&rccCode=" + $("#" + lccQueryObj.rccCode).val(), function (data) {
        $('#' + lccQueryObj.lccName).autocomplete(data, {
            minChars: 0,
            mustMatch: true,
            width: 260,
            sortName: 'LCC_CODE',
            // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可
            formatItem: function (item, i, max) {
                return '<table><tr><td width="80px;">' + item.LCC_CODE + '</td><td width="180px;">' + item.LCC_NAME + '</td></tr></table>';

            },
            // 指定 与 输入文字匹配的字段名
            formatMatch: function (item, i, max) {
                return item.HELP_CODE + item.LCC_CODE + item.LCC_NAME;
            },
            // 选中 某条记录在输入框里 显示的数据字段
            formatResult: function (item) {
                return item.LCC_NAME;
            }
        });
        //选中 某条记录 触发的事件
        $('#' + lccQueryObj.lccName).result(function (event, item) {
            if (item) {
                if (item.LCC_CODE != $("#" + lccQueryObj.lccCode).val()) {
                    $("#lccCode").val(item.LCC_CODE);
                }
            } else {
                $("#" + lccQueryObj.lccCode).val("");
            }
        });
    });
}



