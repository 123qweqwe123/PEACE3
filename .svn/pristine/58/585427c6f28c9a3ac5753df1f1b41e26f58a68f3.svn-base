<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript" src="${ctx }/static/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">



</script>


 <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
	        <c:if test="${type eq '1'}"><h4 class="modal-title">参加短信干预</h4></c:if>
	        <c:if test="${type eq '2'}"><h4 class="modal-title">6月随访问卷</h4></c:if>
	      </div>
	      <div class="modal-body">
	        <p>
	        <span style="color:red;text-align:center;">
	        <c:if test="${type eq '1'}">修改状态至参加短信干预后，不可修改为不参加短信干预，请核实信息后慎重填写。
	        </c:if>
            <c:if test="${type eq '2'}">
	        			请核实信息后慎重填写。
	        </c:if>
	        
	        </span>
	        <form id="changeForm" class="form-horizontal">
	        	<c:if test="${type eq '1'}">
	        	<div class="row">
	        		<div class="col-md-4" style="text-align:right">
                        <span style="color: red">*</span>参加短信干预:
	        		</div>
					<div class="col-md-3" >
					<input name="val" value="1" type="radio">参加
					</div>
					<div class="col-md-3" >
					<input name="val" value="2" type="radio">不参加
					</div>
	        	</div>
	       		</c:if>
	       		<c:if test="${type eq '2'}">
		        	<div class="row">
		        		<div class="col-md-4" style="text-align:right">
                            <span style="color: red">*</span>6月随访问卷:
		        		</div>
						<div class="col-md-3" >
						<input name="val" value="1" type="radio">可填写
						</div>
						<div class="col-md-3" >
						<input name="val" value="2" type="radio">不可填写
						</div>
		        	</div>
	       		</c:if>
              <div class="row">
                  <div class="col-md-4" style="text-align:right">
                  </div>
                  <div class="col-md-8" id="error" >

                  </div>
              </div>
	       		<div class="row" style="margin-top:10px">
	       			<div class="col-md-4" style="text-align:right">
                        <span style="color: red">*</span>协作单位申请人:
	       			</div>
	       			<div class="col-md-4">
	       				<input name="person" id="person" class="form-control input-sm"/>
	       			</div>
	       		</div>
    			<div class="row" style="margin-top:10px">
    				
    				<div class="col-md-4" style="text-align:right">
                        <span style="color: red">*</span>协作单位申请日期:
    				</div>
    				<div class="col-md-3"><input type="text" class="form-control input-sm" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'});"
    					id="changedate" name="changedate"></div>
    			</div>
    			<div class="row" style="margin-top:10px">
 					<div class="col-md-4" style="text-align:right">
                        <span style="color: red">*</span>备注:
                    </div>
 					<div  class="col-md-8"><textarea style="width:100%" rows="5" id="bz" name="bz"></textarea></div>
    			</div>
    			<input name="lccCode" value="${lccCode}" hidden />
                <input name="type" value="${type}" hidden/>
				<input name="patientId" value="${patientId}" hidden/>
			</form>
	        </p>
	      </div>