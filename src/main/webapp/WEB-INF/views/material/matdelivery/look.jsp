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
        <c:forEach items="${materExports}" var="materExport">
        <hr>
        <div class="header">
            <table class="order" width="1000px">
                <tr>
                    <th width="80" >出库单号:</th>
                    <td width="200">${materExport.exorderNo }</td>
                    <th width="80">出库类别:</th>
                    <td width="200">${materExport.activeclassName }</td>
                    <th width="80">来源库房:</th>
                    <td width="200">${materExport.stockName }</td>
                    <th width="80">出库员:</th>
                    <td>${materExport.userName }</td>
                </tr>
                <tr>
                	<th>出库单位:</th>
                    <td>${materExport.lccName }</td>
                    <th>收货单位:</th>
                    <td>${materExport.exportLccName }</td>
                    <th>收货库房:</th>
                    <td>${materExport.exportStockName }</td>
                    <th>出库日期:</th>
                    <td>${materExport.exportDateStr }</td>
                </tr>
                <tr>
                	<th>创建日期:</th>
                    <td>${materExport.createDateStr }</td>
                    <th></th>
                    <td></td>
                    <th></th>
                    <td></td>
                    <th></th>
                    <td></td>
                </tr>
            </table>
            <br>
            <table class="detail" width="1000">
                <tr class="detail_tr">
                    <th width="109">耗材名称</th>
                    <th width="40">批次</th>
                    <!-- <th width="60">出库单价</th>
                    <th width="50">采购价</th> -->
                    <th width="154">库存单位</th>
                    <th width="50">出库数量</th>
                    <th width="92">截止有效期</th>
                    <!-- <th width="125">生产厂商</th> -->
                </tr>
                <c:forEach var="detail" items="${materExport.materExportDetails }">
                <tr>
                    <td>${detail.materlName }</td>
                    <td>${detail.materlBatch }</td>
                    <%-- <td>${detail.materlPrice }</td>
                    <td>${detail.wholesalePrice }</td> --%>
                    <td>${detail.exportUnit }</td>
                    <td>${detail.exportAmount }</td>
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