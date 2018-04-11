<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>糖尿病患者补充入选</title>
    <script type="text/javascript" src="${ctx}/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/idCardCheck.js"></script>

</head>
<body>

<form id="supplier_form" class="form-horizontal">
    <div id="wrap" >
        <div >
            <h3>A患者基本信息</h3>
            <div thistitle="如发现姓名错误，请联系NCC相关人员更改" displayname="姓名：" class="panel panel-default"
                 id="q_1_1" data-hasqtip="0">
                <div class="panel-heading">
                    <span>姓名：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                  <!-- 填空 -->
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6" style="height:30px;">
                                <input type="text" maxlength="16" style="width: 200px;" name = "patientName"
                                value="${po.patientName}">
                            </div>
                        </div>
                    </div>
                </div>
        </div>

            <div thistitle="" displayname="性别：" class="panel panel-default" id="q_1_2" >
                <div class="panel-heading">
                    <span>性别：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <!-- 单选题 -->
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="o_1_2_1">
                                    <input id="o_1_2_1" name="sex" value="1" type="radio"
                                    <c:if test='${po.sex eq "1"}'>checked</c:if>
                                    >
                                    <span>男</span>
                                </label>
                            </div>
                            <div class="col-sm-6">

                                <label for="o_1_2_2">
                                    <input id="o_1_2_2" name="sex" value="2" type="radio"
                                    <c:if test='${po.sex eq "2"}'>checked</c:if>
                                    >
                                    <span>女</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div thistitle="" displayname="身份证号：" class="panel panel-default" id="q_1_4">
                <div class="panel-heading">
                    <span>身份证号：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <!-- 填空 -->
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6" style="height:30px;">
                                <input type="text" maxlength="21" id="idNumber"
                                       value="${po.idnumber}" name="idnumber" style="width: 200px;">
                            </div>
                            <div class="col-sm-6" style="height:30px;" id="textMsg">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div displayname="您的教育程度："
                 class="panel panel-default" id="q_1_5">
                <div class="panel-heading">
                    <span>您的教育程度：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <!-- 单选填空  -->
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input id="o_1_5_1" name="eduLevel" value="1" type="radio"
                                <c:if test='${ po.eduLevel eq "1" }' >checked</c:if>
                                >
                                <label for="o_1_5_1">
                                    初中及以下
                                </label>
                            </div>
                            <div class="col-sm-12">
                                <input id="o_1_5_2" name="eduLevel" value="2" type="radio"
                                <c:if test='${ po.eduLevel eq "2" }' >checked</c:if>
                                >
                                <label for="o_1_5_2">
                                    初中到高中
                                </label>
                </div>
                            <div class="col-sm-12">
                                <input id="o_1_5_3" name="eduLevel" value="3"type="radio"
                                <c:if test='${ po.eduLevel eq "3" }' >checked</c:if>
                                >
                                <label for="o_1_5_3">
                                    高中及以上
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div displayname="您的外语水平怎么样：" title="询问患者调查时当前的外语水平，可结合“教育程度”分步尝试诱导（应努力减小对患者的干扰）。例如，对于文化程度在初中及以下的患者，第一步请患者区分“是否学过外语”，如果学过，再询问“现在的外语水平怎么样”；如果没有学过，则不再询问；对于文化程度在高中及以上的患者，可逐步询问“外语水平怎么样，能否简单交流，掌握几种外语”"
                 class="panel panel-default" id="q_test">
                <div class="panel-heading">
                    <span>您的外语水平怎么样：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <!-- 单选填空  -->
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-12">
                                <input id="t_1_5_1" name="fLanguage" value="1" type="radio"
                                <c:if test='${ po.fLanguage eq "1" }' >checked</c:if>
                                >
                                <label for="t_1_5_1">
                                    我可以用1种以上的外语简单交流
                                </label>
                            </div>
                            <div class="col-sm-12">
                                <input id="t_1_5_2" name="fLanguage" value="2" type="radio"
                                <c:if test='${ po.fLanguage eq "2" }' >checked</c:if>
                                >
                                <label for="t_1_5_2">
                                    我可以用1种外语简单交流
                                </label>
                            </div>
                            <div class="col-sm-12">
                                <input id="t_1_5_3" name="fLanguage" value="3" type="radio"
                                <c:if test='${ po.fLanguage eq "3" }' >checked</c:if>
                                >
                                <label for="t_1_5_3">
                                    我了解一些外语,但不足以进行简单交流
                                </label>
                            </div>
                            <div class="col-sm-12">
                                <input id="t_1_5_4" name="fLanguage" value="4" type="radio"
                                <c:if test='${ po.fLanguage eq "4" }' >checked</c:if>
                                >
                                <label for="t_1_5_4">
                                    我学过外语,但现在基本不记得了
                                </label>
                            </div>
                            <div class="col-sm-12">
                                <input id="t_1_5_5" name="fLanguage" value="5" type="radio"
                                <c:if test='${ po.fLanguage eq "5" }' >checked</c:if>
                                >
                                <label for="t_1_5_5">
                                    我从来没有学过外语
                                </label>
                            </div>
                            <div class="col-sm-12">
                                <input id="t_1_5_6" name="fLanguage" value="6" type="radio"
                                <c:if test='${ po.fLanguage eq "6" }' >checked</c:if>
                                >
                                <label for="t_1_5_6">
                                    不清楚
                                </label>
                            </div>
                            <div class="col-sm-12">
                                <input id="t_1_5_7" name="fLanguage" value="7" type="radio"
                                <c:if test='${ po.fLanguage eq "7" }' >checked</c:if>
                                >
                                <label for="t_1_5_7">
                                    拒绝回答
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div thistitle="" displayname="您的父亲和同胞兄弟中,是否有人在55岁前患心梗,或接受支架治疗、搭桥治疗?" class="panel panel-default" id="t_1_1"  qtype="Q_TYPE_SINGLE" notnull="1">
                <div class="panel-heading">
                    <span>您的父亲和同胞兄弟中,是否有人在65岁前患心梗，或接受支架治疗、搭桥治疗？</span>
                    <span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">

                            <div class="col-sm-6">
                                <label for="o_1_1_1">
                                    <input id="o_1_1_1" name="manrhis" value="0" type="radio"
                                    <c:if test='${ po.manrhis eq "0" }' >checked</c:if>
                                    >
                                    <span>否</span>
                                </label>
                            </div>

                            <div class="col-sm-6" >
                                <label for="o_1_1_2">
                                    <input id="o_1_1_2" name="manrhis" value="1" type="radio"
                                    <c:if test='${ po.manrhis eq "1" }' >checked</c:if>
                                    >
                                    <span>是</span>
                                </label>
                            </div>

                            <div class="col-sm-6" >
                                <label for="o_1_1_3">
                                    <input id="o_1_1_3" name="manrhis" value="2" type="radio"
                                    <c:if test='${ po.manrhis eq "2" }' >checked</c:if>
                                        >
                                    <span>不清楚</span>
                                </label>
                            </div>

                            <div class="col-sm-6" >
                                <label for="o_1_1_4">
                                    <input id="o_1_1_4" name="manrhis" value="3" type="radio"
                                    <c:if test='${ po.manrhis eq "3" }' >checked</c:if>
                                        >
                                    <span>拒绝回答</span>
                                </label>
                            </div>

                        </div>
                    </div>
                </div>
            </div>

            <div thistitle="" displayname="您的母亲和同胞兄弟中，是否有人在65岁前患心梗,或接受支架治疗、搭桥治疗？" class="panel panel-default" id="mt_1_1"  qtype="Q_TYPE_SINGLE" notnull="1">
                <div class="panel-heading">
                    <span>您的母亲和同胞兄弟中,是否有人在65岁前患心梗,或接受支架治疗、搭桥治疗?</span>
                    <span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">

                            <div class="col-sm-6">
                                <label for="mo_1_1_1">
                                    <input id="mo_1_1_1" name="womanrhis" value="0" type="radio"
                                    <c:if test='${ po.womanrhis eq "0" }' >checked</c:if>
                                        >
                                    <span>否</span>
                                </label>
                            </div>

                            <div class="col-sm-6" >
                                <label for="mo_1_1_2">
                                    <input id="mo_1_1_2" name="womanrhis" value="1" type="radio"
                                    <c:if test='${ po.womanrhis eq "1" }' >checked</c:if>
                                        >
                                    <span>是</span>
                                </label>
                            </div>

                            <div class="col-sm-6" >
                                <label for="mo_1_1_3">
                                    <input id="mo_1_1_3" name="womanrhis" value="2" type="radio"
                                    <c:if test='${ po.womanrhis eq "2" }' >checked</c:if>
                                        >
                                    <span>不清楚</span>
                                </label>
                            </div>

                            <div class="col-sm-6" >
                                <label for="mo_1_1_4">
                                    <input id="mo_1_1_4" name="womanrhis" value="3" type="radio"
                                        <c:if test='${ po.womanrhis eq "3" }' >checked</c:if>
                                        >
                                    <span>拒绝回答</span>
                                </label>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div thistitle="" displayname="参加干预研究的患者本人手机号码：" class="panel panel-default" id="tq_1_4">
                <div class="panel-heading">
                    <span>参加干预研究的患者本人手机号码：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6" style="height:30px;">
                                <input type="text" maxlength="11" idcard="1"
                                       value="${po.mobile}" name="mobile" style="width: 200px;">
                            </div>
                        </div>
                    </div>
                </div>
            </div>



            <h3>B入选标准</h3>
            <span style="color: red">&nbsp;&nbsp;入选标准题组内1-4题均选“是”并且 5-8题均选“否”方纳入研究，1-4题任意一题选“否”或者5-8题任意一题选“是”则不符合入选条件，该患者录入的信息将不做保存。</span>
            <div thistitle="" displayname="近3年内因冠心病而住院，出院诊断为急性心肌梗死或在住院期间接受冠状动脉介入治疗，同时明确记录合并有糖尿病，并可获得该次住院病历：" class="panel panel-default" id="tq_1_2" >
                <div class="panel-heading">
                    <span>1. 近3年内因冠心病而住院，出院诊断为急性心肌梗死或在住院期间接受冠状动脉介入治疗，同时明确记录合并有糖尿病，并可获得该次住院病历：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <!-- 单选题 -->
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="to_1_2_1">
                                    <input id="to_1_2_1" name="isdiabetes" value="1" type="radio"
                                        <c:if test='${ po.isdiabetes eq "1" }' >checked</c:if>
                                        >
                                    <span>是</span>
                                </label>
                            </div>
                            <div class="col-sm-6">
                                <label for="to_1_2_2">
                                    <input id="to_1_2_2" name="isdiabetes" value="2" type="radio"
                                        <c:if test='${ po.isdiabetes eq "2" }' >checked</c:if>
                                        >
                                    <span>否</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div thistitle="" displayname="该次住院时间为：" class="panel panel-default" id="mtq_1_4">
                <div class="panel-heading">
                    <span>2. 该次住院时间为：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6" style="height:30px;">
                                <input type="text"
                                       value='<fmt:formatDate value="${po.hospitaldate}" pattern="yyyy-MM-dd"/>'
                                       <%--type="date" dateStyle="default"/>--%>
                                       id="hospitaldate" name="hospitaldate" style="width: 200px;"
                                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d',onpicked:function(){
//                                   var myform = $('#supplier_form');
//                                jQuery(myform).validate().form();
                                        $('#supplier_form').validate().element($('#hospitaldate'));
                                       },
                                       oncleared:function(){
                                            $('#supplier_form').validate().element($('#hospitaldate'));
                                       }
                                       });"  readonly="readonly"
                                >
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div thistitle="" displayname="具备阅读和发送短信能力：" class="panel panel-default" id="mq_1_2" >
                <div class="panel-heading">
                    <span>3. 具备阅读和发送短信能力：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <!-- 单选题 -->
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="mo_1_2_1">
                                    <input id="mo_1_2_1" name="canmsg" value="1" type="radio"
                                        <c:if test='${ po.canmsg eq "1" }' >checked</c:if>
                                        >
                                    <span>是</span>
                                </label>
                            </div>
                            <div class="col-sm-6">

                                <label for="mo_1_2_2">
                                    <input id="mo_1_2_2" name="canmsg" value="0" type="radio"
                                    <c:if test='${ po.canmsg eq "0" }' >checked</c:if>
                                    >
                                    <span>否</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div thistitle="" displayname="书面同意参加本项研究：" class="panel panel-default" id="pmq_1_2" >
                <div class="panel-heading">
                    <span>4. 书面同意参加本项研究：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="pmo_1_2_1">
                                    <input id="pmo_1_2_1" name="requireconsent" value="1" type="radio"
                                    <c:if test='${ po.requireconsent eq "1" }' >checked</c:if>
                                    >
                                    <span>是</span>
                                </label>
                            </div>
                            <div class="col-sm-6">
                                <label for="pmo_1_2_2">
                                    <input id="pmo_1_2_2" name="requireconsent" value="0" type="radio"
                                    <c:if test='${ po.requireconsent eq "0" }' >checked</c:if>
                                    >
                                    <span>否</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div thistitle="" displayname="文盲或视力、听力障碍等，导致难以阅读手机短信或收听电话语音信息：" class="panel panel-default" id="tpmq_1_2" >
                <div class="panel-heading">
                    <span>5. 文盲或视力、听力障碍等，导致难以阅读手机短信或收听电话语音信息：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="tpmo_1_2_1">
                                    <input id="tpmo_1_2_1" name="canradiomsg" value="1" type="radio"
                                    <c:if test='${ po.canradiomsg eq "1" }' >checked</c:if>
                                    >
                                    <span>是</span>
                                </label>
                            </div>
                            <div class="col-sm-6">
                                <label for="tpmo_1_2_2">
                                    <input id="tpmo_1_2_2" name="canradiomsg" value="0" type="radio"
                                    <c:if test='${ po.canradiomsg eq "0" }' >checked</c:if>
                                    >
                                    <span>否</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div thistitle="" displayname="存在认知、交流等功能障碍：" class="panel panel-default" id="ctpmq_1_2" >
                <div class="panel-heading">
                    <span>6. 存在认知、交流等功能障碍：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="ctpmo_1_2_1">
                                    <input id="ctpmo_1_2_1" name="canchat" value="1" type="radio"
                                    <c:if test='${ po.canchat eq "1" }' >checked</c:if>
                                    >
                                    <span>是</span>
                                </label>
                            </div>
                            <div class="col-sm-6">
                                <label for="ctpmo_1_2_2">
                                    <input id="ctpmo_1_2_2" name="canchat" value="0" type="radio"
                                    <c:if test='${ po.canchat eq "0" }' >checked</c:if>
                                    >
                                    <span>否</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div thistitle="" displayname="已知对研究随访和短信干预依从性差：" class="panel panel-default" id="yctpmq_1_2" >
                <div class="panel-heading">
                    <span>7. 已知对研究随访和短信干预依从性差：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="yctpmo_1_2_1">
                                    <input id="yctpmo_1_2_1" name="cantreatment" value="1" type="radio"
                                   <c:if test='${ po.cantreatment eq "1" }' >checked</c:if>
                                    >
                                    <span>是</span>
                                </label>
                            </div>
                            <div class="col-sm-6">
                                <label for="yctpmo_1_2_2">
                                    <input id="yctpmo_1_2_2" name="cantreatment" value="0" type="radio"
                                   <c:if test='${ po.cantreatment eq "0" }' >checked</c:if>>
                                    <span>否</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div thistitle="" displayname="目前正在参加未上市药物或设备的临床试验：" class="panel panel-default" id="myctpmq_1_2" >
                <div class="panel-heading">
                    <span>8. 目前正在参加未上市药物或设备的临床试验：</span><span style="color: red">&nbsp;&nbsp;(必填)</span>
                </div>
                <div class="panel-body">
                    <div class="form-horizontal">
                        <div class="form-group">
                            <div class="col-sm-6">
                                <label for="myctpmo_1_2_1">
                                    <input id="myctpmo_1_2_1" name="inotherproj" value="1" type="radio"
                               <c:if test='${ po.inotherproj eq "1" }' >checked</c:if>
                                    >

                                    <span>是</span>
                                </label>
                            </div>
                            <div class="col-sm-6">
                                <label for="myctpmo_1_2_2">
                                    <input id="myctpmo_1_2_2" name="inotherproj" value="0" type="radio"
                                   <c:if test='${ po.inotherproj eq "0" }' >checked</c:if>>

                                    <span>否</span>
                                </label>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        <%--<input name="age" hidden/>--%>
        <input name="birthday" id="birthday" hidden />
        <input name="agegrade"id="ageGrade" hidden />

        </div>
    </div>
</form>

<script type="text/javascript">
    $(function(){
        if( "${po.patientId}" != "" ){
            $('#supplier_form input').attr("disabled",true)//查看时将input元素设置为disabled
        }else{
            $('#supplier_form input').attr("disabled",false)//新增时去除input元素的disabled属性
        }

        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^1\d{10}$/
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
        $.validator.addMethod("idCardNumber", function(code, element) {
            {
                return IdCardValidate(code);
            }
        }, "身份证号码错误");

        $("#supplier_form").validate({
            // 实时校验
            onfocusout: function(element) {
                if( $(element).valid() && element.name && element.name == 'idnumber' ){
                    var idnumber =  element.value;
                    var year,month,days;
                    if( idnumber && idnumber.length == 15 ){
                        year = "19"+idnumber.substr(6,2);
                        month = idnumber.substr(8,2);
                        days = idnumber.substr(10,2);
                    }else{
                        year = idnumber.substr(6,4);
                        month = idnumber.substr(10,2);
                        days = idnumber.substr(12,2);
                    }
                    $("#birthday").val(year+"-"+month+"-"+days);
                    var age = new Date() - new Date(year+"-"+month+"-"+days);
                    // 去尾法获取年龄
                    age = Math.floor(age/365/24/3600/1000);
                    var text2 = "";
                    if( age != NaN && typeof(age) == 'number'){
                        if( age <= 18 ){
                            $("#idNumber").val();
                            jAlert("患者年龄不能小于或者等于18岁");
                        }else if( age < 55 ){
                            $("#ageGrade").val("1");
                            text2 = "55岁以下";
                        }else if( age <= 64 ){
                            $("#ageGrade").val("2");
                            text2 = "55岁(含)至64岁(含)";
                        }else{
                            $("#ageGrade").val("3");
                            text2 = "65岁及以上";
                        }
                        $("#textMsg")[0].innerHTML="<label>患者年龄"+age+"岁<br>所属年龄段为："+text2+"</label>";
                    }

                }
            } ,
            errorElement: 'span',
            errorPlacement: function(error, element) {
                if( element[0].type == 'radio' ){
                    error.appendTo(element.parent().parent().parent().parent());
                }else{
                    error.appendTo(element.parent().parent().parent());
                }
            },
            rules: {
                patientName:{
                    required:true,
                    minlength:2
                },
                idnumber:{
                    required:true,
                    idCardNumber : true
                },
                sex:{
                    required:true
                },
                manrhis:{
                    required:true
                },
                womanrhis:{
                    required:true
                },
                eduLevel:{
                    required:true
                },
                mobile:{
                    required:true,
                    mobile:true
                },
                fLanguage:{
                    required:true,
                },
                isdiabetes:{
                    required:true
                },
                hospitaldate:{
                    required:true
                },
                canmsg:{
                    required:true
                },
                requireconsent:{
                    required:true
                },
                canradiomsg:{
                    required:true
                },
                canchat:{
                    required:true
                },
                cantreatment:{
                    required:true
                },
                inotherproj:{
                    required:true
                }
            },
            messages:{
                patientName:{
                    required:'患者姓名不能为空！',
                    maxlength: '请输入长度最少是{0}的字符'  //$.format('<br>不能少于{0}个字符')
                },
                idnumber:{
                    required:'身份证号不能为空'
                },
//                eduLevel:{
//                    required:'<br>教育程度信息'
//                },
                mobile:{
                    required:'手机号不能为空',
                    mobile:'手机号输入错误,请确认后重新输入'
                },
                hospitaldate:{
                    required:'必填字段'
                }
            }
        });
    });
</script>



</body>
</html>
