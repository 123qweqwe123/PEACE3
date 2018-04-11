<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>订单打印</title>
	<link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/3.1.1/css/bootstrap.css" /> 
	<style>
		@media print{ 
		.print {display:block;} 
		.notPrint {display:none;} 
		} 
        .header{
            margin-left: 0px;
            margin-right: 0px;
            margin-top: 5px;
            margin-bottom: 20px;
            width: 1000px;
            background-color:#EAEAEA;
            padding-top: 10px;
            font-size: 14px;
            padding-bottom: 20px;
        }
        .div_con{
            width: 1000px;
            margin: 0 auto;
        }
        .order td{
            text-align: left;
        }
        .order th{
            text-align: right;
        }
        .detail_tr{
            background-color: #C1C1C1;
        }
        .detail td{
            text-align: center;
        }
        .detail th{
        	text-align: center;
        }
    </style>
</head>
<body>
<div class="div_con">
        <div style="height: 60px;line-height:60px;background-color: #B4B4B4">
        	<center><h1 style="height: 60px;line-height:60px;">订单打印</h1></center>
        </div>
        <br>
        <c:forEach items="${masterImports}" var="masterImport">
        <hr>
        <div class="header">
            <table class="order" width="1000px">
                <tr>
                    <th width="110" >入库单号:</th>
                    <td width="180">${masterImport.imorderNo }</td>
                    <th width="90">入库类别:</th>
                    <td width="90">${masterImport.activeclassName }</td>
                    <th width="90">医院名称:</th>
                    <td width="200">${masterImport.lccName }</td>
                    <th width="90">创建人:</th>
                    <td>${masterImport.createBy }</td>
                </tr>
                <tr>
                	<th>供应商:</th>
                    <td>${masterImport.supplierName }</td>
                    <th>入库员名称:</th>
                    <td>${masterImport.userName }</td>
                    <th>库房名称:</th>
                    <td>${masterImport.stockName }</td>
                    <th>创建日期:</th>
                    <td>${masterImport.createDateStr }</td>
                </tr>
            </table>
            <br>
            <table class="detail" width="1000">
                <tr class="detail_tr">
                    <th width="109">耗材名称</th>
                    <th width="40">批次</th>
                    <!-- <th width="60">出库单价</th>
                    <th width="50">采购价</th> -->
                    <th width="162">入库单位</th>
                    <th width="81">入库数量</th>
                    <th width="154">库存单位</th>
                    <th width="83">拆零系数</th>
                    <th width="92">截止有效期</th>
                    <!-- <th width="200">生产厂商</th> -->
                </tr>
                <c:forEach var="detail" items="${masterImport.masterImportDetails }">
                <tr>
                    <td>${detail.materlName }</td>
                    <td>${detail.materlBatch }</td>
                    <%-- <td>${detail.materlPrice }</td>
                    <td>${detail.wholesalePrice }</td> --%>
                    <td>${detail.importUnit }</td>
                    <td>${detail.importAmount }</td>
                    <td>${detail.storeUnit }</td>
                    <td>${detail.coefficientChange }</td>
                    <td>${detail.periodValidityStr }</td>
                    <%-- <td>${detail.supplierName }</td> --%>
                </tr>
                </c:forEach>
            </table>
        </div>
		</c:forEach>
		<hr>
		<div id="notPrint" class="notPrint" style="float: right; height: 50px">
			<button onClick="javascrīpt:window.print()" class="btn btn-success btn-sm">打印</button>
		</div>
		
    </div>
</body>
</html>