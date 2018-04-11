<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<title>冻存出库</title>
<style type="text/css">
 
	 input::-webkit-input-placeholder {
	    font-size:12px;
	    text-align: right;
	 }
 
	.leftDiv1{
		width: 40%;
		float: left;
		margin-left: 10px;
	}
	.leftDiv1 h4{
		max-width: 120px;
		background-color: #00a1cb;
		border-radius: 4px 4px 0 0;	
		color: #FFF;
		padding: 8px;
		margin: 0;
		font-size: 14px;
		text-align: center;
	}
	.leftDiv1 div{
		border: #ddd 1px solid;
		padding: 20px;
		padding-bottom: 10px;
		height:150px;
	}
	
	
	.leftDiv2{
		width: 20%;
		float: left;
		margin-left: 10px;
	}
	.leftDiv2 h4{
		max-width: 120px;
		background-color: #00a1cb;
		border-radius: 4px 4px 0 0;	
		color: #FFF;
		padding: 8px;
		margin: 0;
		font-size: 14px;
		text-align: center;
	}
	.leftDiv2 div{
		border: #ddd 1px solid;
		padding: 20px;
		padding-bottom: 10px;
		height:150px;
	}
	
	
	.leftDiv3{
		width: 36%;
		float: left;
		margin-left: 10px;
	}
	.leftDiv3 h4{
		max-width: 120px;
		background-color: #00a1cb;
		border-radius: 4px 4px 0 0;	
		color: #FFF;
		padding: 8px;
		margin: 0;
		font-size: 14px;
		text-align: center;
	}
	.leftDiv3 div{
		border: #ddd 1px solid;
		padding: 20px;
		padding-bottom: 10px;
		height:150px;
	}
	
	
	
	.frozenMain{
		margin-top:20px;
		float: left;
		width:100%;
	}
	
	.frozenMain table{
		border: #ddd 1px solid;
		width:100%;
	}
	
	.frozenMain table td{ 
		border:#ddd 1px solid;
	}
	
	.frozenMain table td input{
		width:100%;
		border:none;
		line-height:30px;
		height:30px;
		background-color:#C3C3C3;
	}
	
	.frozenMain table td input.in{
		background-color:#d7e9f7;
	}
	
	.frozenMain table td input:focus{
		background-color:#9af13a;
	}
</style>
</head>
<body>
	<div id="wrap">
		<div id="navbar">
	    	<ol class="breadcrumb">
	          <li class="active">样本出库</li>
	        </ol>
    	</div>
		<div id="main-content">
			<div class="leftDiv1">
				<h4>录入示例</h4>
				<div style="background:none;padding:5px;">
					<img style="margin:20px 80px;width:200px;" src="${ctx}/static/images/shili.png"/>
				</div>
			</div>
			
			<div class="leftDiv2">
				<h4>冻存盒备注</h4>
				<div style="background:none;padding-top:10px">
					当前盒号:<span>${boxCode}</span><br/>
					<button onclick="changeBoxRemark();">备注</button>
				</div>
			</div>
			
			<div class="leftDiv3">
				<h4>样本冻存管备注</h4>
				<div style="background:none;padding-top:10px">
					当前冻存管号:<span id="tubeRemark_tubeCode"></span><br/>
					<button onclick="changeTubeRemark();">备注</button>
				</div>
				</div>
			</div>
			<div class="frozenMain">
				<table border="0" cellspacing="3" style="background-color:#a0c6e5">
					<c:forEach var="i" begin="1" end="10">
						<c:set var="codeKey" value="tubeCode${i}"></c:set>
						<tr style="line-height:30px">
							<c:forEach var="j" begin="1" end="10">
								<td>
									<input type="text" maxlength=11 value="${tubeCodeMap[codeKey]}" rowno="${i}" colno="${j}" placeholder="${(i-1)*10+j}"
										onfocus="inputFocus(this);" onblur="inputBlur(this);"
										<c:if test="${j==1}">class="in"</c:if>
										<c:if test="${j>1}">readOnly</c:if>
									/>
								</td>
							</c:forEach>
						</tr>
					</c:forEach>
           		</table>
          		<br/>
          		<center>
          			<input id="subBtn" style="width:90px;" type="button" value="返回" onClick="back();"/>
          			<input id="subBtn" style="width:90px;" type="button" value="提交出库" onClick="sub();"/>
          		</center>
            </div>
	</div>
    <br style="clear:both;" />
        	
	<form id="subForm" method="post" action="${ctx}/scm/save">
		<input type="hidden" id="boxCode" name="boxCode" value="${boxCode}"/>
		<input type="hidden" id="boxRemark" name="boxRemark" value="${boxMap.REMARK}"/>
		
		<div id="tubeRemarkDiv" style="display: none">
			<c:forEach var="tubeRemarkMap" items="${tubeRemarkList}">
				<input type="hidden" name="tubeRemark${tubeRemarkMap.rowno}_${tubeRemarkMap.colno}"  value="${tubeRemarkMap.remark}"/>
			</c:forEach>
		</div>
		<div id="tubeDiv" style="display: none">
		</div>
	</form>
	
	<div style="text-align:center;">
		<div id="changeBoxRemarkModal" class="modal fade" tabindex="1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="dialog_title"></h4>
					</div>
					<div class="modal-body">
						<p id="type_context1">
							<div style="width: 100%;height: 100%;">
								<label id="changeBoxRemarkModal_showDes">冻存盒${boxCode}备注</label><br>
								<textarea id="changeBoxRemarkModal_remark" rows="8" cols="75"></textarea>
								<input type="button" value="提交" onclick="subBoxRemark();">
								<input type="button" value="取消" onclick="hideModal('changeBoxRemarkModal')">
							</div>
						</p>
					</div>
				</div>
			</div>
		</div>
		
		<div id="changeTubeRemarkModal" class="modal fade" tabindex="1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">&times;</button>
						<h4 class="modal-title" id="dialog_title"></h4>
					</div>
					<div class="modal-body">
						<p id="type_context1">
							<div style="width: 100%;height: 100%;">
								<label id="changeTubeRemarkModal_showDes"></label><br>
								<textarea id="changeTubeRemarkModal_remark" rows="8" cols="75"></textarea>
								<input type="hidden" id="changeTubeRemarkModal_tubeId"/>
								<input type="button" value="提交" onclick="subTubeRemark();">
								<input type="button" value="取消" onclick="hideModal('changeTubeRemarkModal')">
							</div>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function changeBoxRemark(){
			$("#changeBoxRemarkModal_remark").val($("#boxRemark").val());
			$("#changeBoxRemarkModal").modal("show");
		}
		function subBoxRemark(){
			var value = $("#changeBoxRemarkModal_remark").val().trim();
			if(value.length > 500){
				alert("备注不能超过500字，请修改。");
				return;
			}else{
				$("#boxRemark").val(value);
				$("#changeBoxRemarkModal").modal("hide");
			}
		}
		
		var edit_rowno="";
		var edit_colno="";
		function inputBlur(obj){
			$(obj).val($.trim($(obj).val()));
			if($(obj).val()!=""){
				var pageCodeRepeat = false;
				$("input[colno='1'][rowno!='"+$(obj).attr("rowno")+"']").each(function(){
					if($(this).val() == $(obj).val()){
						alert("冻存管条码重复,请检查");
						pageCodeRepeat=true;
						return false;
					}
				});
				if(pageCodeRepeat){
					$(obj).focus();
					return;
				}

				$.ajax({
					type:"POST",
					url:"${ctx}/scm/checktubecode",
					data : {"tubecode":$(obj).val(),"boxcode":$("#boxCode").val()},
					cache:false,
					async:false,
					success : function(e){
						if(e != null && e != '' ){
							if( !e.canuse){
								alert("录入的冻存管号不存在或未使用或已出库，请查验录入");
								$(obj).val(null);
								return;
							}
						}else{
							alert("录入的冻存管号不存在或未使用或已出库，请查验录入");
							$(obj).val(null);
							return;
						}
					}
				}
				);
			$("input[rowno='"+$(obj).attr("rowno")+"']").val($(obj).val());
			$("#tubeRemark_tubeCode").text($(obj).val());
			}
		}
		
		function inputFocus(obj){
			edit_rowno = $(obj).attr("rowno");
			edit_colno = $(obj).attr("colno");
			$("#tubeRemark_tubeCode").text($(obj).val());
		}
		
		function changeTubeRemark(){
			if(edit_rowno == "" || edit_colno==""){
				alert("请点击需要添加备注的冻存管位置");
				return;
			}
			var tubeCode = $("input[rowno='"+edit_rowno+"'][colno='"+edit_colno+"']").val();
			if($.trim(tubeCode)==""){
				alert("请点扫描冻存管");
				$("input[rowno='"+edit_rowno+"'][colno='1']").focus();
				return;
			}
			var tubeRemark = $("input[name='tubeRemark"+edit_rowno+"_"+edit_colno+"']").val();
			if(tubeRemark == undefined)tubeRemark="";
			
			$("#changeTubeRemarkModal_showDes").text("冻存管"+tubeCode+"备注:");
			$("#changeTubeRemarkModal_tubeId").val("tubeRemark"+edit_rowno+"_"+edit_colno);
			$("#changeTubeRemarkModal_remark").val(tubeRemark);
			$("#changeTubeRemarkModal").modal("show");
		}
		
		function subTubeRemark(){
			var value = $("#changeTubeRemarkModal_remark").val().trim();
			if(value.length > 500){
				alert("备注不能超过500字，请修改。");
				return;
			}
			var id = $("#changeTubeRemarkModal_tubeId").val();
			if($("input[name='"+id+"']").val() == undefined){
				$("#tubeRemarkDiv").append("\n");
				$("#tubeRemarkDiv").append("<input type=\"hidden\" name=\""+id+"\" value=\""+value+"\"/>");
			}else{
				$("input[name='"+id+"']").val(value);
			}
			$("#changeTubeRemarkModal").modal("hide");
			$("input[rowno='"+edit_rowno+"'][colno='"+edit_colno+"']").focus();
		}
		
		function hideModal(id){
			$("#"+id).modal("hide");
		}
		
		function sub(){

			$("#subBtn").attr("disabled","disabled");
			var data = {};
			data['boxCode'] = $("#boxCode").val();
			data['boxRemark'] = $("#boxRemark").val();
			
			$("#tubeRemarkDiv").find("input").each(function(){
				data[$(this).attr("name")]=$(this).val();
			});
			
			var c=0;
			$(".frozenMain").find("input[colno='1']").each(function(){
				if($(this).val()!=''){
					c++;
					data['tube_'+$(this).attr('rowno')]=$(this).val();
				}
			});
			if(c==0){
				alert("没有添加冻存管,不能提交");
				return;
			}
			
			if(!confirm("确认提交吗?")){
				return;
			}
			
			 $.post("${ctx}/scm/save",data,function(result){
			      if(result.success){
			    	 back();
			      }else{
			    	  alert("保存失败");;
			      }
			      $("#subBtn").removeAttr("disabled");
			  },'json');
		}
		
		function back(){
			 location.replace("${ctx}/scm/index?t="+new Date().getTime());
		}
	</script>
</body>
</html>
