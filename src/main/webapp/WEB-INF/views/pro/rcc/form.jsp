<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
    
    
    <form id="supplier_form" class="form-horizontal">
    	<!-- RCC编码 -->
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="rccCode"><span style="color: red">*</span>RCC编码:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" id="rccCode1" name="rccCode" value="${pipCommRcc.rccCode }" ${pipCommRcc.rccCode!=null ? 'disabled':''} class="form-control input-sm" placeholder="RCC编码" required>
				<c:if test="${!empty pipCommRcc}">
					<input type="hidden" name="rccCode" value="${pipCommRcc.rccCode }">
				</c:if>
			</div>
		</div>
		<input type="hidden" name="projectId" value="${pipCommRcc.projectId }">
		<!-- RCC名称  -->
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="rccName"><span style="color: red">*</span>RCC名称:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" name="rccName" value="${pipCommRcc.rccName }" class="form-control input-sm" placeholder="RCC名称" required>
			</div>
		</div>
			<!-- 科室 -->
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="departMent"><span style="color: red">*</span>科室:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" name="departMent" value="${pipCommRcc.departMent }" class="form-control input-sm" placeholder="科室" required>
			</div>
		</div>	<!-- 负责人 -->
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="manaMan"><span style="color: red">*</span>负责人:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" name="manaMan" value="${pipCommRcc.manaMan }" class="form-control input-sm" placeholder="负责人" required>
			</div>
		</div>
	
		<!-- 组织结构代码  -->
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="orgCode"><span style="color: red">*</span>组织结构代码:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" name="orgCode" value="${pipCommRcc.orgCode }" class="form-control input-sm" placeholder="组织结构代码" required>
			</div>
		</div>
			<!-- 所属机构 -->
		<div class="form-group">
			<label class="col-lg-3 col-md-1  control-label" for="unit"><span style="color: red">*</span>所属结构:</label>
			<div class="col-lg-7 col-md-8">
				<input type="text" name="unit" value="${pipCommRcc.unit }" class="form-control input-sm" placeholder="所属机构" required>
			</div>
		</div>
		
	</form>
    
	<script type="text/javascript">
		$(function(){
			$("#supplier_form").validate({
				rules: {
					
					rccCode:{
						required:true,
						<c:if test="${empty pipCommRcc}">
						remote:{
							type:"POST",
							url:'${ctx}/rcc/checkRccCodeExists',
							dataType:'json',
							data:{
								rccCode:function(){
									return $("#rccCode1").val();
								}
							},
							dataFilter: function(data) {
								data= eval("("+data+")");
								if(data){
									if(!data.result){
										return true;
									}
									return false;
								}
								return false;
							}
						},
						</c:if>
						maxlength:50
					},
					
					rccName:{
						required:true,
						maxlength:150
					},
					orgCode:{
						required:true,
						maxlength:100
					},
					manaMan:{
						required:true,
						maxlength:100
					}
					
				},
				
				messages:{
					
					rccCode:{
						required:'请输入RCC编码！',
						<c:if test="${empty pipCommRcc}">
						remote:'RCC编码已经存在！',
						</c:if>
						maxlength:'最大长度不能超过50个字符！'
					},
					
					rccName:{
						required:'请输入RCC名称！',
						maxlength:'最大长度不能超过150个字符！'
					},
					orgCode:{
						required:'请输入组织机构代码！',
						maxlength:'最大长度不能超过100个字符！'
					},
					manaMan:{
						required:'请输入负责人！',
						maxlength:'最大长度不能超过100个字符！'
					}
				}
			});

		});
	</script>
