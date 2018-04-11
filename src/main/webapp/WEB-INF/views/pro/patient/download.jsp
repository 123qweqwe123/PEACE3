<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
 <title></title>
<script type="text/javascript" src="${ctx}/static/echarts/esl/esl.js"></script>
<script type="text/javascript" src="${ctx}/static/echarts/echarts.js" ></script>
<style>
	
	.txt{
	    font-size:14px;
	    font-weight:bold;
	}
	
	.percentTxt
		  {
		  	font-size:9px;
		  	font-weight:normal;
		  	color:#333;
		  }
		.upArrow{
        	padding:0px 20px 0px 0px;
        	background-image:url('${ctx}/views/progress/assets/arrowUp1.png');	
        	background-repeat:no-repeat;
        	background-position: 55px 10px;
        }
        
        .downArrow{
        	padding:0px 20px 0px 0px;
        	background-image:url('${ctx}/views/progress/assets/arrowDown1.png');	
        	background-repeat:no-repeat;
        	background-position: 55px 10px;
        }
</style>
</head>
<body>
	<div id="navbar">
    	<ol class="breadcrumb">
          <li class="active">系统管理</li>
          <li class="active">花名册模板下载</li>
          <%-- <li class="active"><c:if test="${type=='2' }">初筛</c:if><c:if test="${type=='3' }">高危</c:if><c:if test="${type=='4' }">预约随访</c:if>列表-${lccName}</li> --%>
        </ol>
    </div>



	<div id="mainContent">
    	
        <!-- <div id="marginRight"> -->
        	<div id="select">
                <div class="select-main">
                	<div class="selectList">
                		请先选择上传花名册的行政区划:
				    </div>
                		
                		<br>
                      <form action="" method="post" class="well-work bs-adp form-inline">
               			<fieldset>
               				<ol>
               					<li>省:
               					</li>
               					<li>
               						<select id="province" class="province">
							            <option value="">请选择</option>
							        </select>
               					</li>
               					<li>市:
               					</li>
               					<li>
               						<select id="city" class="city">
							            <option value="">请选择</option>
							        </select>
               					</li>
               					<li>县:
               					</li>
               					<li>
               						<select id="district" class="district">
							            <option value="">请选择</option>
							        </select>
               					</li>
               					<li>乡:
               					</li>
               					<li>
               						<select id="town" class="town">
							            <option value="">请选择</option>
							        </select>
               					</li>
               					<li>村:
               					</li>
               					<li>
               						<select id="vili" class="vili">
							            <option value="">请选择</option>
							        </select>
               					</li>
               				</ol>
               			</fieldset>
               			<br><br><br><br>
               			<fieldset>	
                        	<ol>
                             	<!-- <li> 
                                    <div class="input-group date" id="duedate1" data-date-format="yyyy-mm-dd" data-date="2014-07-10">
                                        <input id="bg_date" class="form-control input-sm" style="width: 120px;"  required="required" type="text">
                                        <span class="input-group-addon input-sm btn">
                                        <i class="glyphicon glyphicon-calendar "></i>
                                        </span>
                                    </div>
                                </li>
                                <li>
                                    到 <div class="input-group date" id="duedate2" data-date-format="yyyy-mm-dd" data-date="2014-08-10">
                                        <input id="end_date" class="form-control input-sm" style="width: 120px;" required="required" type="text">
                                        <span class="input-group-addon input-sm btn">
                                        <i class="glyphicon glyphicon-calendar "></i>
                                        </span>
                                    </div>
                                </li> -->
                                <li>
                                </li>
                                
                            
                             	<!-- <li>
                             	    <input  type="text" style='display:none;' class="form-control input-sm" name="lcc_code" id="lcc_code" value=''/>   
                            	 	<input  class="form-control input-sm" id="lcc_name" name="lcc_name" type="text" placeholder="请输入项目单位" value=""/>
                                </li> -->
                            
                               	<li> <button type="button" id='idcard_bt' class="btn btn-primary">身份证花名册模版下载</button> </li>
                           		<li></li>
                           		<li> <button type="button" id='other_bt' class="btn btn-primary">其它证件花名册模版下载</button> </li>
                            	<li></li>
                            	<li></li>
                            	<li></li>
                           
                            </ol>
                        </fieldset>
                      </form>
                </div>
            </div>
            
         <div class="table Bg" style="min-height:100px;">
         	<div style="color:#f00;font-size:12px;">&nbsp;&nbsp;*注:上报花名册说明</div>
         	<div style="color:#f00;font-size:12px;">&nbsp;&nbsp;第一步:选择上传花名册的行政区划，根据上报数据类型选择指定的花名册模版。</div>
         	<div style="color:#f00;font-size:12px;">&nbsp;&nbsp;第二步:按照花名册模版要求填写花名册，列标题带*的为必填数据。</div>
         </div>
        </div>
        
        <br style="clear:both;" />
    </div>
<script>
$(function(){
	
	var areaJson;
	
	$.getJSON('${ctx}/dict/areaData',function(data){
        areaJson = data;
        province();
    });
	
	
	function province(){
		var txt = '<option value="">请选择</option>';
		$.each(areaJson,function(i,province){
	        txt +="<option value='"+province.VCODE+"'>"+province.VNAME+"</option>";
	    });
		$('#province').html(txt);
		city();
	}

	$('#province').change(function(e){
		city();
		$('#district').html('<option value="">请选择</option>');
		$('#town').html('<option value="">请选择</option>');
		$('#vili').html('<option value="">请选择</option>');
	});
	
	function city(){
		var n = $('#province').get(0).selectedIndex;
		var txt = '<option value="">请选择</option>';
		
		if ( n > 0 ){
			$.each(areaJson[n - 1].cp,function(i,city){
	            txt +="<option value='"+city.VCODE+"'>"+city.VNAME+"</option>";
	        });
	        $('#city').html(txt);
		}
        
        district();
	}
	
	$('#city').change(function(e){
		district();
		$('#town').html('<option value="">请选择</option>');
		$('#vili').html('<option value="">请选择</option>');
	});
	
	function district(){
		var n = $('#province').get(0).selectedIndex;
		var m = $('#city').get(0).selectedIndex;
		var txt = '<option value="">请选择</option>';
		if ( m > 0 ){
			//alert(areaJson[n - 1].cp[m-1].cc);
			$.each(areaJson[n - 1].cp[m -1 ].cc,function(i,dis){
	            txt +="<option value='"+dis.VCODE+"'>"+dis.VNAME+"</option>";
	        });
	        $('#district').html(txt);
		}
	}
	
	$('#district').change(function(e){
		town();
		$('#vili').html('<option value="">请选择</option>');
	});
	
	function town(){
		var d = $('#district').val();
		var type = '4';
		
		var txt = '<option value="">请选择</option>';
		$.getJSON('${ctx}/dict/areaData?type='+type+'&pid='+d,function(data){
	        $.each(data,function(i,d){
	        	txt +="<option value='"+d.VCODE+"'>"+d.VNAME+"</option>";
	        });
	        $('#town').html(txt);
	    });
		
		
	}
	

	$('#town').change(function(e){
		vili();
	});
	
	function vili(){
		var d = $('#town').val();
		var type = '5';
		
		var txt = '<option value="">请选择</option>';
		$.getJSON('${ctx}/dict/areaData?type='+type+'&pid='+d,function(data){
	        $.each(data,function(i,d){
	        	txt +="<option value='"+d.VCODE+"'>"+d.VNAME+"</option>";
	        });
	        $('#vili').html(txt);
	    });
		
		
	}
	
	
	
	
	
	
	$("#idcard_bt").click(function(){
		downloadExcel("idcard.xls");
	});
	
	
	$('#other_bt').click(function(){
		downloadExcel("other.xls");
	});
	
	function downloadExcel(excelName){

		var p = $('#province').val();
		var pValue = $('#province').find("option:selected").text();
		
		var c = $('#city').val();
		var cValue = $('#city').find("option:selected").text();
		
		var d = $('#district').val();
		var dValue = $('#district').find("option:selected").text();
		
		var town = $('#town').val();
		var townValue = $('#town').find("option:selected").text();
		
		var vili = $('#vili').val();
		var viliValue = $('#vili').find("option:selected").text();
		
		
		if ( p && p != '' && c && c != '' && d && d != '' ){
			window.open('${ctx}/pro/pat/downloadTemplate?templateName='+excelName+
					'&province='+p+'&provinceValue='+pValue+
					'&city='+c+'&cityValue='+cValue+
					'&district='+d+'&districtValue='+dValue+
					'&town='+town+'&townValue='+townValue+
					'&vili='+vili+'&viliValue='+viliValue
					,'_self');
		}else{
			alert('请先选择花名册模版的行政区划！');
		}
		
	}
	
});



</script> 
</body>
</html>