<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<title></title>
<%@ include file="/WEB-INF/common/common-js-style.jsp"%>
<%@ include file="/WEB-INF/common/custom-js-style.jsp"%>
<style>
	@media print{ 
		.print {display:block;} 
		.notPrint {display:none;} 
		} 
        *{
            font-family: "宋体";
            margin: 0px;
            padding: 0px;
            font-size: 15px;
        }
        h4{
        	width: 200px;
        	height: 20px;
        }
        h1{
            font-size: 24px;
        }
        .noprint{visibility:hidden} 
        .header{
            margin: auto auto;
            padding-top: 10px;
            text-align: center;
        }
        .width{
            width: 100%;
            height: 40px;
        }
        .divButtom{
            width: 400px;
            height: 30px;
            float: right;
        }
        .classSpan{
            color: #0044cc;
        }
        .claUl{
            margin-top: 40px;
        }
        .claUl li{
            float: left;
            list-style-type: none;
            margin-left: 20px;
        }
        ul{
            list-style-type: none;
        }
        .claUl1 li{
            float: left;
            margin-left: 20px;
        }
        .div_line{
            clear: both;
            height: 30px;
            background-color:#009DD9;
            border: 1px solid blue;
            color: white;
            line-height: 30px;
            padding-left: 10px;
            padding-top:5px;
            margin-top: 20px;
            width: 1100px;
        }
        table tr{
            height: 30px;
        }
        .ulC li{
            height: 20px;
            line-height: 20px;
            margin-left: 30px;
            margin-top: 10px;
        }
        .ulC_span{
            display:inline-block;
            width: 200px;
        }
        .tableTr{
            margin: auto auto;
            width: 800px;
        }
        .tableTr{
            border-top: 1px solid #C1C1BF;
            border-left: 1px solid #C1C1BF;
        }
        .tableTr td{
            border-bottom: 1px solid #C1C1BF;
            border-right: 1px solid #C1C1BF;
        }
        .tableTr tr{
       	 	height: 24px;
            line-height: 24px;
            font-size: 20px;
            font-family: "微软雅黑, Arial, Helvetica, sans-serif"
        }
    </style>
    <script type="text/javascript">
    	$(function(){
    		$("input[type='radio']").attr("disabled","disabled");;
    	});
    </script>
</head>

<body>

<div class="print" style="width: 1100px; background-color:#E5E5E5;margin: 0 auto">
        <div class="header">
            <div class="header width">
                <h1>考核实施</h1>
            </div>
            <div class="header divButtom">
                考核日期: <span class="classSpan">
                <fmt:formatDate value="${pipExpImplement.implementStartDate}" pattern="yyyy年MM月dd日"/> 到 
                <fmt:formatDate value="${pipExpImplement.implementEndDate}" pattern="yyyy年MM月dd日"/> 
              </span>
            </div>
        </div>
        <div class="content">
            <div style="width: 100%;height: 40px;">
                <ul class="claUl" style="width: 100%;height: 20px;">
                    <li>所属省份:</li>
                    <li><span class="classSpan">${pipExpImplement.provinceName }</span></li>
                    <li>医院名称:</li>
                    <li><span class="classSpan">${pipExpImplement.lccName }</span></li>
                    <li>观摩门诊类型:</li>
                    <li><span class="classSpan">${pipExpImplement.watchPatientType =='1' ? '基线' :'随访'}</span></li>
                    <li>患者数量:</li>
                    <li><span class="classSpan">${pipExpImplement.patientCount }人</span></li>
                </ul>
                <div style="clear: both;height: 1px" ></div>
                <div>
                    <ul class="claUl1" style="width: 100%;height: 20px;">
                        <li>备注:</li>
                        <li><span class="classSpan">${pipExpImplement.remark }</span></li>
                    </ul>
                </div>

            </div>
            <div class="div_line"  style="margin-top: 20px;margin-bottom: 10px">
                <h4>相关人员</h4>
            </div>
            <table>
                <tr>
                    <td style="text-align: right;width: 200px">考核人员:</td>
                    <td><span class="classSpan">${khStrings }</span></td>
                </tr>
                <tr>
                    <td style="text-align: right;width: 200px">LCC研究者人员:</td>
                    <td><span class="classSpan">${yjStrings }</span></td>
                </tr>
                <tr>
                    <td style="text-align: right;width: 200px">其他人员:</td>
                    <td><span class="classSpan">${otherStrings }</span></td>
                </tr>
            </table>
            <div class="div_line" style="margin-top: 0px">
                <h4>总体情况</h4>
            </div>
            <ul class="ulC">
				<li>1、研究人员是否充分理解研究方案</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk1" <c:if test="${pipExpImplement.isztqk1 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk1" <c:if test="${pipExpImplement.isztqk1 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk1" <c:if test="${pipExpImplement.isztqk1 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>2、知情同意是否充分</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk2" <c:if test="${pipExpImplement.isztqk2 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk2" <c:if test="${pipExpImplement.isztqk2 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk2" <c:if test="${pipExpImplement.isztqk2 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>3、研究人员理解研究并掌握电脑使用方法</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk3" <c:if test="${pipExpImplement.isztqk3 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk3" <c:if test="${pipExpImplement.isztqk3 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk3" <c:if test="${pipExpImplement.isztqk3 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>4、LCC研究人员需要更多的培训</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk4" <c:if test="${pipExpImplement.isztqk4 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk4" <c:if test="${pipExpImplement.isztqk4 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk4" <c:if test="${pipExpImplement.isztqk4 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>5、受试者被安排预约随访</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk5" <c:if test="${pipExpImplement.isztqk5 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk5" <c:if test="${pipExpImplement.isztqk5 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk5" <c:if test="${pipExpImplement.isztqk5 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>6、研究材料及设备，包括电脑及文件，均存储在安全环境下</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk6" <c:if test="${pipExpImplement.isztqk6 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk6" <c:if test="${pipExpImplement.isztqk6 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk6" <c:if test="${pipExpImplement.isztqk6 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>7、LCC采血包是否充足，且采血包已被销毁</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk7" <c:if test="${pipExpImplement.isztqk7 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk7" <c:if test="${pipExpImplement.isztqk7 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk7" <c:if test="${pipExpImplement.isztqk7 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>8、中心校验血被安全存储于冰箱、冰柜中，定期记录冰箱、冰柜温度</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk8" <c:if test="${pipExpImplement.isztqk8 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk8" <c:if test="${pipExpImplement.isztqk8 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk8" <c:if test="${pipExpImplement.isztqk8 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>9、了解终点事件支持性文件的要求</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk9" <c:if test="${pipExpImplement.isztqk9 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk9" <c:if test="${pipExpImplement.isztqk9 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk9" <c:if test="${pipExpImplement.isztqk9 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>10、掌握如何正确记录不良事件</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk10" <c:if test="${pipExpImplement.isztqk10 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk10" <c:if test="${pipExpImplement.isztqk10 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk10" <c:if test="${pipExpImplement.isztqk10 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>11、研究经费按规定支出</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isztqk11" <c:if test="${pipExpImplement.isztqk11 =='1' }">checked="checked" </c:if> value='1'>是</span>
					<span class="ulC_span"><input type="radio" name="isztqk11" <c:if test="${pipExpImplement.isztqk11 =='2' }">checked="checked" </c:if> value='2'>否</span>
					<span class="ulC_span"><input type="radio" name="isztqk11" <c:if test="${pipExpImplement.isztqk11 =='3' }">checked="checked" </c:if> value='3' >不清楚</span>
				</li>
			</ul>
            <div class="div_line">
                <h4>研究文件核查</h4>
            </div>
            <ul class="ulC">
				<li>1、研究方案/操作手册（单独成册）</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isyjwjhc1" <c:if test="${pipExpImplement.isyjwjhc1 =='1' }">checked="checked" </c:if> value='1'>有，及时更新</span>
					<span class="ulC_span"><input type="radio" name="isyjwjhc1" <c:if test="${pipExpImplement.isyjwjhc1 =='2' }">checked="checked" </c:if> value='2'>否，待补充</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>2、第1册:合同/伦理</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isyjwjhc2" <c:if test="${pipExpImplement.isyjwjhc2 =='1' }">checked="checked" </c:if> value='1'>有，及时更新</span>
					<span class="ulC_span"><input type="radio" name="isyjwjhc2" <c:if test="${pipExpImplement.isyjwjhc2 =='2' }">checked="checked" </c:if> value='2'>否，待补充</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>3、第2册:知情同意书</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isyjwjhc3" <c:if test="${pipExpImplement.isyjwjhc3 =='1' }">checked="checked" </c:if> value='1'>有，及时更新</span>
					<span class="ulC_span"><input type="radio" name="isyjwjhc3" <c:if test="${pipExpImplement.isyjwjhc3 =='2' }">checked="checked" </c:if> value='2'>否，待补充</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>4、第3册:研究人员相关:简历/职责授权表/培训文件/考核报告</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isyjwjhc4" <c:if test="${pipExpImplement.isyjwjhc4 =='1' }">checked="checked" </c:if> value='1'>有，及时更新</span>
					<span class="ulC_span"><input type="radio" name="isyjwjhc4" <c:if test="${pipExpImplement.isyjwjhc4 =='2' }">checked="checked" </c:if> value='2'>否，待补充</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>5、第4册:报告:通知/简报/软件升级报告</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isyjwjhc5" <c:if test="${pipExpImplement.isyjwjhc5 =='1' }">checked="checked" </c:if> value='1'>有，及时更新</span>
					<span class="ulC_span"><input type="radio" name="isyjwjhc5" <c:if test="${pipExpImplement.isyjwjhc5 =='2' }">checked="checked" </c:if> value='2'>否，待补充</span>
				</li>
			</ul>
			<ul class="ulC">
				<li>6、第5册:终点事件支持性文件</li>
				<li>
					<span class="ulC_span"><input type="radio" name="isyjwjhc6" <c:if test="${pipExpImplement.isyjwjhc6 =='1' }">checked="checked" </c:if> value='1'>有，及时更新</span>
					<span class="ulC_span"><input type="radio" name="isyjwjhc6" <c:if test="${pipExpImplement.isyjwjhc6 =='2' }">checked="checked" </c:if> value='2'>否，待补充</span>
				</li>
			</ul>

            <div class="div_line">
                <h4>待处理问题</h4>
            </div>
            <table class="tableTr" style="margin-top: 10px">
                <tr >
                    <td style="text-align: center;width: 200px">待解决人员:</td>
                    <td style="text-align: center;width: 200px">问题分类:</td>
                    <td style="text-align: center;width: 200px">问题描述:</td>
                    <td style="text-align: center;width: 200px">事件状态:</td>
                    <td style="text-align: center;width: 200px">增加时间:</td>
                </tr>
                <c:forEach items="${problems }" var="problem" >
	                <tr>
	                    <td style="text-align: center;">${problem.lccUserName }</td>
	                    <td style="text-align: center;">
	                    	<c:if test="${problem.problemType=='1' }">患者管理</c:if>
	                    	<c:if test="${problem.problemType=='2' }">文件管理</c:if>
	                    	<c:if test="${problem.problemType=='3' }">经费管理</c:if>
	                    	<c:if test="${problem.problemType=='4' }">设备管理</c:if>
	                    </td>
	                    <td style="text-align: center;">${problem.description }</td>
	                    <td style="text-align: center;">${problem.status=='1' ? "开放" :"关闭" }</td>
	                    <td style="text-align: center;"><fmt:formatDate value="${problem.createDate}" pattern="yyyy-MM-dd"/></td>
	                </tr>
                </c:forEach>
            </table>
        </div>
        <div id="notPrint" class="notPrint" style="width: 400px; margin:0 auto; height: 30px; text-align: center; line-height: 30px">
            <input style='line-height:0.8' class='btn btn-primary btn-align-right btn-sm' id="btnClose" type="button" value="关闭" onClick="custom_close()" />
            <button style='line-height:0.8' class='btn btn-primary btn-align-right btn-sm' onClick="javascrīpt:window.print()">打印</button>
        </div>
    </div>
	<script language="javascript">
		// 这个脚本是 ie6和ie7 通用的脚本
		function custom_close(){
			if(confirm("您确定要关闭本页吗？")){
				window.opener=null;
				window.open('','_self');
				window.close();
			}
			else{}
		}
	</script>
</body>
</html>