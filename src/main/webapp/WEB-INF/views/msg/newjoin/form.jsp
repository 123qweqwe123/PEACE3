<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>新增待补录人员</title>
</head>
<body>

<form id="supplier_form" class="form-horizontal">
    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="patientId"><span style="color: red">*</span>PID:</label>
        <div class="col-lg-7 col-md-8">
            <input type="text" id="patientId" name="patientId" value="" class="form-control input-sm" placeholder="PID" >
        </div>
    </div>

    <%--<input id="patientId" name="patientId" value="" type="hidden"/>--%>
    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="patientName"><span style="color: red">*</span>姓名:</label>
        <div class="col-lg-7 col-md-8">
            <input type="text" id="patientName" name="patientName" readonly="readonly" value="" class="form-control input-sm" placeholder="姓名" >
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="idNumber"><span style="color: red">*</span>身份证号:</label>
        <div class="col-lg-7 col-md-8">
            <input type="text" id="idNumber" name="idNumber" readonly="readonly" value="" class="form-control input-sm" placeholder="身份证号" >
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="sex"><span style="color: red">*</span>性别:</label>
        <div class="col-lg-7 col-md-8"  readonly="readonly">
            <input type="text" id="sex" name="sex" readonly="readonly" value="" class="form-control input-sm">

            <%--<select id="sex" name="sex" class="form-control input-sm"  readonly="readonly" disabled>--%>
                <%--<option value="" ></option>--%>
                <%--<option value="1" >男</option>--%>
                <%--<option value="2" >女</option>--%>
            <%--</select>--%>
        </div>

    </div>

    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="mobile">手机:</label>
        <div class="col-lg-7 col-md-8">
            <input type="text" id="mobile" name="mobile" value="" class="form-control input-sm" placeholder="手机"  readonly="readonly">
        </div>
    </div>

    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="lccName">所属单位:</label>
        <div class="col-lg-7 col-md-8">
            <input type="text" id="lccName" name="lccName" value="" class="form-control input-sm" placeholder="所属单位" readonly="readonly">
            <input type="text" id="lccCode" hidden/>
        </div>
    </div>


    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="mobile">心肌梗死病史:</label> <!-- <span style="color: red">*</span> -->
        <div class="col-lg-7 col-md-8">
            <input type="text" id="medicalHis_MI" name="medicalHis_MI" value="" class="form-control input-sm" readonly="readonly">
            <%--<select id="medicalHis_MI" name="medicalHis_MI" class="form-control input-sm"  readonly="readonly" disabled>--%>
                <%--<option value="" ></option>--%>
                <%--<option value="1" >有</option>--%>
                <%--<option value="2" >无</option>--%>
            <%--</select>--%>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="mobile"><span style="color: red">*</span>医保类型:</label>
        <div class="col-lg-7 col-md-8">
            <input type="text" id="medicalInsurance" name="medicalInsurance" value="" class="form-control input-sm" readonly="readonly">
            <%--<select id="medicalInsurance" name="medicalInsurance" class="form-control input-sm"  readonly="readonly" disabled>--%>
                <%--<option value="" ></option>--%>
                <%--<option value="1" >城镇居民/职工医疗保险/公费医疗 </option>--%>
                <%--<option value="2" >农村合作医疗</option>--%>
                <%--<option value="3" >完全自费</option>--%>
            <%--</select>--%>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="age_grade"><span style="color: red">*</span>年龄段:</label>
        <div class="col-lg-7 col-md-8">
            <input type="text" id="age_grade" name="age_grade" value="" class="form-control input-sm" readonly="readonly">
            <%--<select id="age_grade" name="age_grade" class="form-control input-sm"  readonly="readonly" disabled>--%>
                <%--<option value="" ></option>--%>
                <%--<option value="1" >55以下</option>--%>
                <%--<option value="2" >55-64</option>--%>
                <%--<option value="3" >65及以上</option>--%>
            <%--</select>--%>
        </div>
    </div>
    <div class="form-group">
        <label class="col-lg-3 col-md-1  control-label" for="edu_level"><span style="color: red">*</span>教育程度:</label>
        <div class="col-lg-7 col-md-8">
            <input type="text" id="edu_level" name="edu_level" value="" class="form-control input-sm" readonly="readonly">
            <%--<select id="edu_level" name="edu_level" class="form-control input-sm"  readonly="readonly" disabled>--%>
                <%--<option value="" ></option>--%>
                <%--<option value="1" >初中及以下</option>--%>
                <%--<option value="2" >初中到高中</option>--%>
                <%--<option value="3" >高中及以上</option>--%>
            <%--</select>--%>
        </div>
    </div>

</form>

<script type="text/javascript">

    <%--$.getJSON("${ctx}/pro/lccuser/getAllActiveLccByAuthority",function(data) {--%>
        <%--$('#lccName').autocomplete(data,{--%>
            <%--minChars: 0,--%>
            <%--mustMatch:true,--%>
            <%--width:260,--%>
            <%--// 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可--%>
            <%--formatItem: function(item,i, max) {--%>
                <%--return '<table><tr><td width="80px;">' + item.lccCode + '</td><td width="180px;">' + item.lccName + '</td></tr></table>';--%>

            <%--},--%>
            <%--// 指定 与 输入文字匹配的字段名--%>
            <%--formatMatch: function(item,i, max) {--%>
                <%--return item.helpCode+item.lccCode;--%>
            <%--},--%>
            <%--// 选中 某条记录在输入框里 显示的数据字段--%>
            <%--formatResult: function(item) {--%>
                <%--return item.lccName;--%>
            <%--}--%>
        <%--});--%>
        <%--//选中 某条记录 触发的事件--%>
        <%--$('#lccName').result(function(event, item){--%>
            <%--if(item){--%>
                <%--if(item.lccCode != $("#lccCode").val()){--%>
                    <%--$("#lccCode").val(item.lccCode);--%>
                <%--}--%>
            <%--}else{--%>
                <%--$("#lccCode").val("");--%>
            <%--}--%>
        <%--});--%>
    <%--});--%>

    $(function(){


        var url = "${ctx}/msg/newjoin/getautodata?type=person&lccCode="+$("#grid").jqGrid('getRowData',$("#grid").jqGrid('getGridParam','selrow')).lccCode;
        $('#patientId').autocomplete(url, {
            limit:10,
            max:10,
            minChars:0,
            matchContains: true,
//            parse: function(data) { // 格式化后台返回数据
//                if( data != null && data != '' ){
//                    return null;
//                }
//                var arr = data.split("\n");
//                var arr2 = new Array();
//                for( var i = 0 ; i < arr.length ; i++ ){
//                    arr2[i] = JSON.parse(arr[i])
//                }
//                return arr2;
//            },
            formatItem: function(row) {
                if( row == null || row == '' ){
                    return "";
                }
                var obj = JSON.parse(row);
                return  obj.PATIENT_ID + "  " + obj.PATIENT_NAME//row[0]+"|"+row[1];
            },
            formatResult: function(row) {
                var obj = JSON.parse(row);
                return  obj.PATIENT_ID;
            }
        }).result(function(event,row,formatted){
            //点击下拉框中数据触发事件;
            var obj = JSON.parse(row);

            if( row ){
                $("#patient_id").val(obj.PATIENT_ID);
                $("#idNumber").val(obj.ID_NUMBER);
                if(obj.SEX == null || obj.SEX == ""){
                    $("#sex").val("");
                }else if( obj.SEX == "1" ){
                    $("#sex").val("男");
                }else{
                    $("#sex").val("女");
                }

                $("#patientName").val(obj.PATIENT_NAME);
                $("#mobile").val(obj.MOBILE);
                $("#lccName").val(obj.LCC_NAME);
                $("#lccCode").val(obj.LCC_CODE);


//                $("#medicalHis_MI").val(obj.MEDICALHIS_MI);
                if(obj.MEDICALHIS_MI == null || obj.MEDICALHIS_MI == ""){
                    $("#medicalHis_MI").val("");
                }else if( obj.MEDICALHIS_MI == "1" ){
                    $("#medicalHis_MI").val("有");
                }else{
                    $("#medicalHis_MI").val("无");
                }

//                $("#medicalInsurance").val(obj.MEDICALINSURANCE);
                if(obj.MEDICALINSURANCE == null || obj.MEDICALINSURANCE == ""){
                    $("#medicalInsurance").val("");
                }else if( obj.MEDICALINSURANCE == "1" ){
                    $("#medicalInsurance").val("城镇居民/职工医疗保险/公费医疗");
                }else if( obj.MEDICALINSURANCE == "2" ){
                    $("#medicalInsurance").val("农村合作医疗");
                }else if( obj.MEDICALINSURANCE == "3" ){
                    $("#medicalInsurance").val("完全自费");

                }

//                $("#age_grade").val(obj.AGE_GRADE);
                if( obj.AGE_GRADE == null || obj.AGE_GRADE =='' ){
                    $("#age_grade").val('');
                }else if(obj.AGE_GRADE == '1'){
                    $("#age_grade").val('55以下');
                }else if(obj.AGE_GRADE == '2'){
                    $("#age_grade").val('55-64');
                }else if(obj.AGE_GRADE == '3'){
                    $("#age_grade").val('65及以上');
                }

//                $("#edu_level").val(obj.EDU_LEVEL);
                <%--<option value="1" >初中及以下</option>--%>
                <%--<option value="2" >初中到高中</option>--%>
                <%--<option value="3" >高中及以上</option>--%>
                if( obj.EDU_LEVEL == null || obj.EDU_LEVEL == '' ){
                    $("#edu_level").val('');
                }else if( obj.EDU_LEVEL == '1' ){
                    $("#edu_level").val('初中及以下');
                }else if( obj.EDU_LEVEL == '2' ){
                    $("#edu_level").val('初中到高中');
                }else if( obj.EDU_LEVEL == '3' ){
                    $("#edu_level").val('高中及以上');
                }


            }else{
                $("#patient_id").val("");
                $("#sex").val("");
                $("#patientName").val("");
                $("#mobile").val("");
                $("#lccName").val("");
                $("#lccCode").val("");
                $("#medicalHis_MI").val("");
                $("#medicalInsurance").val("");
                $("#age_grade").val("");
                $("#edu_level").val("");
                $("#idNumber").val("");

            }

            jQuery($("#supplier_form")[0]).validate().form()
        });

        $("#supplier_form").validate({
            rules: {
                patientId:{
                    required:true,
                    maxlength:100
                },
                patientName:{
                    required:true,
                    minlength:2
                },
                idNumber:{
                    required:true
                },
//                medicalHis_MI:{
//                    required:true
//                },
                age_grade:{
                    required:true
                },
                edu_level:{
                    required:true
                },
                medicalInsurance:{
                    required:true
                }
            },
            messages:{
                patientId:{
                    required:'PID不能为空'
                },
                patientName:{
                    required:'请输入有效PID后选择数据！',
                    maxlength:$.format('不能少于{0}个字符')
                },
                idNumber:{
                    required:'身份证号不能为空，请先到患者管理/患者信息页面维护患者身份证号！'
                },
                medicalHis_MI:{
                    required:'分组信息缺失,如需增加请按照中心数据修改流程执行'
                },
                age_grade:{
                    required:'年龄信息缺失,如需增加请按照中心数据修改流程执行'
                },
                edu_level:{
                    required:'教育程度信息缺失,如需增加请按照中心数据修改流程执行'
                },
                medicalInsurance:{
                    required:'医保类型信息缺失,如需增加请按照中心数据修改流程执行'
                }
            }
        });
    });
//    $.validator.addMethod("mobile", function(value, element) {
//        var length = value.length;
//        var mobile =  /^1\d{10}$/
//        return this.optional(element) || (length == 11 && mobile.test(value));
//    }, "手机号码格式错误");
//    $.validator.addMethod("phone", function(value, element) {
//        var tel = /^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/;
//        return this.optional(element) || (tel.test(value));
//    }, "电话号码格式错误");
</script>


</body>
</html>
