<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>

<script type="text/javascript">
$(document).ready(function(){   
    $('#addSelect').click(function(){   
        var $unoptions = $('#unusing option:selected');//获取当前选中的项   
        var $options = $('#using option');   
        var count = 0;
        for(var i = 0;i<$unoptions.length;i++){
        	for(var j = 0;j<$options.length;j++){
        		if($unoptions[i].text == $options[j].text){
        			count = count +1;
        		}
        	}
        }
       // alert(count)
        if(!count){
        	var $remove = $unoptions.remove();//删除下拉列表中选中的项   
            $remove.appendTo('#using');//追加给对方
        }else{
        	openError("选择的机尾已存在",1000);
			return;
        }
     /*    var $remove = $options.remove();//删除下拉列表中选中的项   
        $remove.appendTo('#using');//追加给对方    */
    });   
       
    $('#removeSelect').click(function(){   
        var $removeOptions = $('#using option:selected');   
        $removeOptions.appendTo('#unusing');//删除和追加可以用appendTo()直接完成   
    });   
       

       
});   

function openError(message,delay){
	$('#_alert').show().find('strong').text(message);
	window.setTimeout(function() {
		$('#_alert').slideUp("slow");
	//window.top.location.reload();
	}, delay);
}

function save(){ 
	var temp = new Array();
	var $options = $('#using option');
	$options.each(function(){ 
     	temp.push($(this).val());  
     });  
	$('#usings').val(temp);  
	document.all.AircrafttailListForm.submit();
}

</script>
</head>
<body>
<div id="_alert" class="alert alert-danger" hidden  >
		<strong>Oh snap!</strong>
	</div>
<table class="table" >
  <tbody> 
	 <tr class="breadcrumb">
		<td colspan='2'>角色名:${role.name}
		<input type="hidden" id="roleId" value="${role.id}"/>
		</td>
		<td></td>
	</tr>
 <%--    <tr>
	   <td></td>
	   <td>执管单位:</td>
	      <td class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
			<select name="officeId" id="officeId" class="form-control input-sm">
				<option value="">全部</option>				
				<c:forEach items="${enforceOffices }" var="enforce" varStatus="status">  
				<option value="${enforce.id }">${enforce.name }</option>
		   		</c:forEach>
			</select>
		</td>
	</tr> --%>
	
	<tr>
	   <td></td>
	   <td>机型:</td>
	   <td class="col-lg-2 col-md-2" style="vertical-align: middle;padding: 4px 8px;">
		<input type="hidden" id="aircraftTypeId" name ="aircraftTypeId" />
		<input type="text" id="aircraftType" name="aircraftType" maxlength="15"  class="form-control input-sm" onkeydown="return false;" />	 
		</td>
	</tr> 
	<tr>
		<td class="col-lg-5 col-md-5">已选尾号</td>
		<td class="col-lg-1 col-md-1"></td>
		<td class="col-lg-5 col-md-5">未选尾号</td>
	</tr>

	<tr>
		<td >
		<select id="using"  multiple size="15" class="form-control">
			<c:forEach items="${usingTails}" var="usingTail">
				<option value="${usingTail.id}">${usingTail.aircraftNo}</option>
			</c:forEach>
		</select>
		</td>
		<td >
		<br/>
 		<br/>
 		<br/>
 		<br/>
		<button type="button" class="btn btn-small btn-success" id="addSelect">&lt;&lt;添加</button>
		<br/>
 		<br/> 
		<button type="button" class="btn btn-small btn-info" id="removeSelect" >移除&gt;&gt;</button>
		</td>
		
		<td >
		<select id="unusing" multiple size="15" class="form-control">
			 <c:forEach items="${unusingTails}" var="unusingTail">
				<option value="${unusingTail.id}">${unusingTail.aircraftNo}</option>
			</c:forEach> 
		</select>
		</td>
	</tr>
	</tbody>
</table>
</body>

 <script type="text/javascript">	
$(document).ready(function() {
var setting = {	
	async: {
		enable: true,
		type:"get",
		url:"${ctx}/asset/assettype/tail-type-tree"
			
	},
	data: {
		simpleData: {
			enable: true,
			idKey: "id",
			pIdKey: "parentId"
		},
		key:{ name:"code" }
	} ,
	callback: {
		
		onClick: zTreeOnClick
	}
};	
	
$.fn.zTree.init($("#aircraft_type_tree"), setting );			
			
//机型点击全部
$('#aircraft_type_clean').click(function(){
	zTreeOnClick('','',{id:'', code:'' });
});
   
function zTreeOnClick(event, treeId, treeNode){
		var id = treeNode.id;
		var code = treeNode.code;
		var codeText = code ? code :'全部';
		$("#aircraftType").val(codeText );
		$("#aircraftTypeId").val(id);
		$("#aircraft_type_div").toggle('slow');	
		
		var roleId = $("#roleId").val();
		$("#unusing option").remove();
		$.ajax({
			url:"${ctx}/sys/rbac/role/findTailByTypeIdOutRole",
			type: "post",
			dataType: 'json',
			data:{aircraftTypeId: id ,roleId:roleId},
		    success:function(result,status){
		    	for(var i = 0;i<result.tailList.length;i++){
		    		if($('#using').html().indexOf(result.tailList[i].aircraftNo) ==-1 ){
	                    $("#unusing").append('<option value="'+result.tailList[i].id+'">'+result.tailList[i].aircraftNo+'</option>');		    			
		    		}
		    	}	    	
		    }		
	});		
}
	

	
//点击机型事件
$("#aircraftType").click(function(){
	$("#aircraft_type_div").css('top',95).css('left',343);
	showAircraftTypeTree($(this).outerWidth());
});

function showAircraftTypeTree(width){
	$("#aircraft_type_div").css("width",width+5);
	$("#aircraft_type_div").toggle();
}
//机型,发动机类型树 自动隐藏======
$(document).click(function(e){
    var target = e.target;
    if(target.id){
        if(target.id.indexOf('type_tree') != -1 || target.id.indexOf('TypeTree') != -1 || target.id == 'aircraftType' || target.id == 'engineType' || $(target).is('.ztree') ){
            var count = $('.tree-select:visible').size();
            if(count>1){
                $('.tree-select').hide();
                $(target).click();
            }
        }else{
            $('.tree-select').hide();
        }
    }else{
        $('.tree-select').hide();
    } 
});  

});
</script> 
		
<div id="aircraft_type_div" class="tree-select" style="height:220px;">
<span id="aircraft_type_clean" style="cursor: pointer;">全部</span>
<div id= "aircraft_type_tree" class="ztree" style="height:190px;overflow:auto;" >
</div>
</div>

</html>