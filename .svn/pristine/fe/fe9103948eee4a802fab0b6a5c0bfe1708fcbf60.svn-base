<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
	<title>动态血压回收记录</title>
    <script type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript">

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
</head>
<body>
<div id="navbar">
	<ul class="breadcrumb">
		<li class="active">动态血压回收记录</li>
	</ul>
</div>
<div id="message" class="alert alert-success" hidden>
	<button data-dismiss="alert" class="close">&times;</button>
	<span id="messageSpanId"></span>
</div>
<ul id="myTab" class="nav nav-tabs">
    <li ${type=="1"?"class='active'":""} id="type_1">
        <a data-toggle="tab">CHAT研究干预随机门诊</a>
    </li>
    <li ${type=="2"?"class='active'":""} id="type_2">
        <a data-toggle="tab">CHAT干预末次门诊</a>
    </li>
</ul>
<div id="select">
	<div class="select-main">
		<form action="" id="searchForm" method="post" >
			<fieldset>
				<div  class="row">
                    <input type="hidden" id="type" name="type" value="${type=="2"?type:"1"}">
					<div  class="leftLable col-md-1" style="text-align:right">医院名称:</div>
					<div  class="col-md-2">
						<input type="hidden" id="lccCode" name="lccCode" value="${lcc.lccCode}">
						<input type="text" id="lccName" name="lccName" value="${lcc.lccName}"   class="form-control input-sm" placeholder="输入简拼或者LCCID">
					</div>
					<div  class="leftLable col-md-1" style="text-align:right">PID:</div>
					<div  class="col-md-2">
						<input type="text" id="patient_id" name="patientId" class="form-control input-sm" placeholder="输入PID"></li>
					</div>
					<div  class="leftLable col-md-1" style="text-align:right">患者姓名:</div>
					<div  class="col-md-2">
						<input type="text" id="patient_name" name="patientName" class="form-control input-sm" placeholder="输入患者姓名"></li>
					</div>
					<%--<div  class="leftLable col-md-1" style="text-align:right">门诊类型:</div>--%>
					<%--<div  class="col-md-2">--%>
						<%--<select class="form-control input-sm" id="type">--%>
							<%--<option value="1">CHAT研究干预随机门诊</option>--%>
							<%--<option value="2">CHAT干预末次门诊</option>--%>
						<%--</select>--%>
					<%--</div>--%>
                </div>
                <div  class="row">
                    <div  class="leftLable col-md-1" style="text-align:right;margin-top: 5px">是否已回收结果:</div>
                    <div  class="col-md-2" style="margin-top: 5px">
            <%--<input type="text" id="is_getresult" name="isGetResult" class="form-control input-sm" placeholder="输入患者姓名"></li>--%>
                        <select class="form-control input-sm" id="isGetResult" name="isGetResult">
                            <option value="">请选择</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>
                    </div>
					<div  class="col-md-2" style="float:right;margin-right: 30px;margin-top: 5px">
						<button type="button" id="btnQuery" class="btn btn-primary btn-align-right btn-sm">查询</button>
						<button type="button" id="btnExport" class="btn btn-primary btn-align-right btn-sm" style="margin-left:10px;">导出</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>

<div id="jqgrid">
	<table id="grid"></table>
	<div id="pager"></div>
</div>

<script type="text/javascript">
	$(function(){
		var option = {
			url : '${ctx}/qn/report/dbplistdata',
			datatype : 'json',
			postData : {lccCode:$("#lccCode").val(),type:$("#type").val()},
			mtype : 'POST',
			colNames : ['LCCID','PID','患者姓名','门诊类型','随访完成时间','是否预约','是否已回收结果', //'医院名称',
				'完成时间','相距天数','用户名','录入时间','标识是否修改过'],
			colModel : [
				{name : 'LCC_CODE', align:'center'},
				//{name : 'LCC_NAME', align:'center'},
				{name : 'PATIENT_ID', align:'center'},
				{name : 'PATIENT_NAME', align:'center'},
                {name : 'TYPE', align:'center'},
                {name : 'END_DATE', align:'center'},
				{name : 'IS_YY', align:'center'},
				{name : 'IS_GETRESULT', align:'center',
					editable:true,edittype:'select',formatter:'select',
					editoptions:{value:":;0:否;1:是",
						dataEvents: [
							{ type: 'change',          //下拉选择的时候
								fn: function(e) {
                                    var rowId = this.parentElement.parentElement.id;
                                    var dataObj = $("#grid").getRowData(rowId);
                                    if( dataObj.CREATE_DATE != null && dataObj.CREATE_DATE != '' ){ //表明是更新操作
                                        $("#"+rowId+"_IS_CHANGE").val("2");
                                    }else{
                                        $("#"+rowId+"_IS_CHANGE").val("1");
                                    }

                                    if( e.target.value == '1' ){
                                        $("#"+rowId+"_DONE_DATE")[0].disabled = false;
                                    }else{
                                        $("#"+rowId+"_DONE_DATE")[0].disabled = true;
                                        $("#"+rowId+"_DONE_DATE").val("");
                                    }

                                }
							}
						]
					}
				},
				{name : 'DONE_DATE', align:'center',
					editable:true,
					editrules:{required: false},editoptions:{ //date: true,
					dataInit: function(element,e,obj) {
							$(element).click(function(){
								WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});
                                var rowId = element.parentElement.parentElement.id
                                var dataObj = $("#grid").getRowData(rowId);
                                if( dataObj.CREATE_DATE != null && dataObj.CREATE_DATE != '' ){ //表明是更新操作
                                    $("#"+rowId+"_IS_CHANGE").val("2");
                                }else{
                                    $("#"+rowId+"_IS_CHANGE").val("1");
                                }
							});
						}
					}
				},
				{name : 'MINUSDAYS', align:'center',sortable :true},
				{name : 'CREATE_NAME', align:'center'},
				{name : 'CREATE_DATE', align:'center'},
                {name : 'IS_CHANGE', align:'center',hidden:true,editable:true}//标识该行数据是否被编辑需要更新或者插入操作
			],
			rowNum : 15,
			rowList : [ 15, 30, 50,100,500 ],
			height : "100%",
			autowidth : true,
			pager : '#pager',
			altRows:true,
			hidegrid : false,
			viewrecords : true,
			recordpos : 'left',
			sortname : 'MINUSDAYS',
			sortorder : "desc",
			emptyrecords : "没有可显示记录",
			loadonce : false,
			multiselect: false,
            ondblClickRow: function(id){//onSelectRow
                if(id && id !== lastsel){
                    saveDataLocal(lastsel);
                    lastsel = id;
                    var rowData = $("#grid").jqGrid("getRowData", id);
                    $('#grid').jqGrid('restoreRow',lastsel);
                    $('#grid').jqGrid('editRow',id,{
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
                }
			},
            beforeEditCell:function(rowid,cellname,v,iRow,iCol){
                lastrow = iRow;
                lastcell = iCol;
            }
		};
		$("#grid").jqGrid(option);
		$("#grid").jqGrid('navGrid', '#pager', {edit : false, add : false, del : false,search : false, position : 'right'})
			.navButtonAdd('#pager',{caption:"保存", buttonicon:"ui-icon-plus", onClickButton: function(){toSave()},position:"last"})
            .navButtonAdd('#pager',{caption:"重置", buttonicon:"ui-icon-pencil", onClickButton: function(){toReset()},position:"last"})
		;
		jqgridResponsive("grid",false);

        $("#type_1").click(function(){
            $("#type").val("1");
            multipleSearch();
        });
        $("#type_2").click(function(){
            $("#type").val("2");
            multipleSearch();
        });

		$("#btnQuery").click(multipleSearch);

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
					return item.lccCode+item.helpCode+item.lccName;
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

	});

	$("#btnExport").click(function(){
		var postData = $("#grid").jqGrid("getGridParam", "postData");
		var pram = "";
		$.each(postData, function (k, v) {
			if(v == null)return true;
			if(pram.length==0){
				pram+="?";
			}else{
				pram+="&";
			}
			pram += k + "=" +encodeURI(encodeURI(v));
		});
		window.open('${ctx}/qn/report/dbpdetailexport'+pram);
	});


    var lastsel;
    var lastrow;
    var lastcell;

    function saveDataLocal(id){
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
        $("#grid").jqGrid('saveRow',id,  saveparameters);
    }

    function toReset() {
        debugger;
        var id = $("#grid").jqGrid('getGridParam','selrow');
		if (id && confirm("是否确认重置该记录？")) {
			var data = $("#grid").jqGrid("getRowData",id);
			data["clinic_type"] = $("#type").val();
			data["IS_GETRESULT"] = "0";
			$.post("${ctx}/qn/report/resetDbp",{"jsonArr":JSON.stringify(data)},function(result){
				$("#grid").trigger("reloadGrid");
				var message = "操作失败!";
				if(result.success){
					message = "操作成功！";
				}
				showResult(result.success,message);
			},'json');
		}
	}

	function toSave(){
        saveDataLocal(lastsel);
        lastrow = "";
        lastcell = "";
        lastsel = "";
        var data = {};
        data["u"] = new Array();
        data["i"] = new Array();
        var dataArr = $("#grid").getRowData();
        if( dataArr.length == 0 ){
            return;
        }
        // 门诊类型
        var type = $("#type").val();
        for(var i = 0 ; i < dataArr.length ; i++){
            dataArr[i]["clinic_type"] = type;
            if( dataArr[i].IS_CHANGE == '1' ){
                data["i"].push(dataArr[i]);
            }else if(dataArr[i].IS_CHANGE == '2'){
                data["u"].push(dataArr[i])
            }
//            else if(!dataArr[i].IS_GETRESULT){
//                jAlert( "'是否回收结果'为必填项，不可为空");
//                return;
//            }
        }
        if( data["i"].length == 0 && data["u"].length == 0){
            return;
        }

        $.post("${ctx}/qn/report/saveDbpData",{"jsonArr":JSON.stringify(data)},function(result){
            $("#grid").trigger("reloadGrid");
            var message = "操作失败!";
            if(result.success){
                message = "操作成功！";
            }
            showResult(result.success,message);
        },'json');

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
</body>

</html>