<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhang.rw
  Date: 16-7-6
  Time: 上午5:02
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>问卷内容修改</title>
</head>
<body>
  <!-- 菜单 -->
    <ul class="breadcrumb">
        <li class="active"></li>
    </ul>
  <!-- 查询框 -->
    <div id="select">
        <div class="select-main">
            <form action="" id="searchForm" method="post" class="well-work bs-adp form-inline">
                <fieldset>
                <div  class="row" style="margin-top:5px;">
                        <div  class="col-md-1" style="text-align: right">PID:</div>
                        <div  class="col-md-2">
                            <input id="pid"  name="pid" class="form-control input-sm"  placeholder="患者ID"/>
                        </div>

                    <button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>

                </div>
                </fieldset>
            </form>
        </div>
    </div>

<!-- 基本信息表单 -->
  <div id="select"> 个人信息
      <div class="select-main">
          <form action="" id="" method="post" class="well-work bs-adp form-inline">
              <div  class="row" style="margin-top:5px;">
                  <div class="col-md-3 column">
                      <label>姓名 :</label>
                      <text id="info_name"></text>
                  </div>
                  <div class="col-md-3 column">
                      <label>性别 :</label>
                          <text id="info_sex"></text>
                  </div>
                  <div class="col-md-3 column">
                      <label>出生日期 :</label>
                      <text id="info_birday"></text>
                  </div>
                  <div class="col-md-3 column">
                      <label>身份证号 :</label>
                      <text id="info_idnum"></text>
                  </div>
              </div>
              <div class="row"  style="margin-top:5px;">
                  <div class="col-md-3 column">
                      <label>PID :</label>
                      <text id="info_pid"></text>
                  </div>
                  <div class="col-md-3 column">
                      <label>医院名称 :</label>
                      <text id="info_lcc"></text>
                  </div>
              </div>
          </form>
      </div>
  </div>

<!-- 问卷列表 -->

  <div id="jqgrid"> <!-- style="overflow:auto;" -->
      <table id="grid"></table>
      <div id="pager"></div>
  </div>



<script type="text/javascript" >
    $(function(){
        var option = {
            url : '${ctx}/changeans/list',
            datatype : 'json',
            mtype : 'POST',
            colNames : [ 'LCCID','PID','问卷编号','问卷名称','问卷开始时间','问卷结束时间','操作员' , '操作' ,'UQS_FILE', 'UQS_VERSION'],
            colModel : [
                {name : 'LCC_CODE',  align:'center',width:'80',sortable: false },
                {name : 'PATIENT_ID',  align:'center',width:'80',sortable: false },
                {name : 'QUESTIONNAIRE_ID',  align:'center',width:'80',sortable: false },
                {name : 'DISPLAY_NAME',  align:'center',width:'80',sortable: false },

                {name : 'START_TIME',  align:'center',width:'80',sortable: false},
                {name : 'END_TIME',  align:'center',width:'80',sortable: false },
                {name : 'NAME',  align:'center',width:'80',sortable: false },
                {name : '', index : 'operate',sortable: false,formatter : function(cellvalue, option, rowObjects) {
                            return "<button onclick=\"changeQn('"+rowObjects.PATIENT_ID+"','"+rowObjects.UQS_VERSION+"','"+rowObjects.UQS_FILE+"')\">修改问卷</button>";
                }, align:'center',sortable: false },

                {name : 'UQS_FILE',  align:'left',width:'80',sortable: false , hidden:true },
                {name : 'UQS_VERSION',  align:'left',width:'80',sortable: false , hidden:true }

            ],
            rowNum : 15,
            rowList : [ 15, 30, 50],
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
            loadonce : false
//            ,multiselect: true
        };
        $("#grid").jqGrid(option);
        $("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false, search : false, position : 'right'})
        jqgridResponsive("grid",false);
    });

    //查询
    $("#btnQuery").click(function(){


        $.post("${ctx}/changeans/getpinfo",{"pid" : $("#pid").val()} , function(e){
            if( e != null && e != 'null' && e != ''){
                $("#info_name")[0].innerText = e.PATIENT_NAME;
                if( e.SEX ){
                    $("#info_sex")[0].innerText = e.SEX == "1" ? "男" : "女";
                }
                $("#info_birday")[0].innerText = e.BIRTHDAY;
                $("#info_idnum")[0].innerText = e.ID_NUMBER;
                $("#info_pid")[0].innerText = e.PATIENT_ID;
                $("#info_lcc")[0].innerText = e.LCC_NAME;

            }else{
                $("#info_name")[0].innerText ="";
                $("#info_sex")[0].innerText = "";
                $("#info_birday")[0].innerText = "";
                $("#info_idnum")[0].innerText = "";
                $("#info_pid")[0].innerText = "";
                $("#info_lcc")[0].innerText = "";
            }
        })

        var postData = $("#grid").jqGrid("getGridParam", "postData");
        $.extend(postData,{"pid" : $("#pid").val()});

        $("#grid").jqGrid("setGridParam", {
            search: true
        }).trigger("reloadGrid", [{page:1}]);

    });

    function changeQn(pid , uqsversion , uqsfile){
        //$.post("${ctx}/changeans/tochange",{"pid":pid ,"qnid":uqsversion },function(e){
        //});
        window.location.href = "${ctx}/changeans/tochange?pid="+pid+"&qnid="+uqsversion+"&uqsfile="+uqsfile;
    }
</script>
</body>
</html>
