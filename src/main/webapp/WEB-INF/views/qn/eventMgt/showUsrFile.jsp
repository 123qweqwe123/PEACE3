<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<div>
	<div class="select-main">
		<div  class="row"> 
			<div  class="leftLable col-md-2" style="text-align: right">支持性材料:</div>
			<div  class="col-md-3"> 
				<input type="hidden" id="showUsrFile_dictFileId">
				<input type="text" class="form-control input-sm" id="showUsrFile_dictFileName" value='' placeholder="输入支持性材料简拼"/>
			</div>
			<div  class="col-md-2"> 
				<button type="button" onclick="showUsrFile_search()" class="btn btn-primary btn-align-right btn-sm">查询</button>
			</div>
		</div>
	</div>
</div>
<div id="showUsrFile_jqgrid">
    <table id="showUsrFile_grid"></table>
    <div id="showUsrFile_pager"></div>
</div>	
<script type="text/javascript">
	$(function(){
		$.getJSON("${ctx}/qn/eventMgt/dictFiles",function(data) { 
		    $('#showUsrFile_dictFileName').autocomplete(data,{
		        minChars: 0,
		        mustMatch:true, 
		        width:260,
		       // 下拉列表显示的字段 ，如果多个用表格格式化，如果单个 直接  return item.COUNTRY_NAME; 即可 
		        formatItem: function(item,i, max) {
		            return item.FILE_INFO;
		            
		        },
		        // 指定 与 输入文字匹配的字段名
		        formatMatch: function(item,i, max) {
		            return item.FILE_INFO+item.HELP_CODE;
		        },
		        // 选中 某条记录在输入框里 显示的数据字段
		        formatResult: function(item) {
		            return item.FILE_INFO;
		        }
		    }); 
		  //选中 某条记录 触发的事件
		    $('#showUsrFile_dictFileName').result(function(event, item){ 
				if(item){
					$("#showUsrFile_dictFileId").val(item.ID);
				}else{
					$("#showUsrFile_dictFileId").val("");
				}
		     });
		});
		
		var showEvent_option = {
	        url : '${ctx}/qn/eventMgt/showUsrFileList?patientId=${patientId}',    
	        datatype : 'json',  
	        mtype : 'POST',
	        colNames : ['LOG_MIN_ID','LCCID','PID','姓名','住院名称','入院时间','出院时间','支持性材料','是否完成','操作时间','操作人'],
	        colModel : [ 
						 {name : 'LOG_MIN_ID', index : 'LOG_MIN_ID',hidden:true},
						 {name : 'LCC_CODE', index : 'LCC_CODE',width:'50px',align:'center' ,sortable: false},
						 {name : 'PATIENT_ID', index : 'PATIENT_ID',width:'60px', align:'center' ,sortable: false},
						 {name : 'PATIENT_NAME', index : 'PATIENT_NAME',width:'60px', align:'center' ,sortable: false},
						 {name : 'HOS_NAME', index : 'HOS_NAME',width:'80px', align:'center' ,sortable: false},
						 {name : 'IN_HOS_DATE', index : 'IN_HOS_DATE',width:'80px', align:'center' ,sortable: false},
						 {name : 'OUT_HOS_DATE', index : 'OUT_HOS_DATE',width:'80px', align:'center' ,sortable: false},
						 {name : 'FILE_INFO', index : 'FILE_INFO',width:'150px', align:'left' ,sortable: false},
						 {name : 'COMPLETED', index : 'COMPLETED',width:'60px', align:'center' ,sortable: false,formatter : function(cellvalue, option, rowObjects) {
								if(cellvalue == 1){
									return "是";
								}else{
									return "否";
								}	
								}},
						 {name : 'CREATE_DATE', index : 'CREATE_DATE',width:'120px', align:'center' ,sortable: false},
						 {name : 'CREATE_NAME', index : 'CREATE_NAME',width:'60px', align:'center' ,sortable: false}
	                   ],         
	        rowNum : 15, 
	        rowList : [ 15, 30, 50,100,150,500 ],      
	        height : "100%",
	        width:"650px",
	        autowidth : false,  
	        pager : '#showUsrFile_pager',  
	        sortname : 'LOG_MIN_ID',
	        altRows:true, 
	        hidegrid : false, 
	        viewrecords : true, 
	        recordpos : 'left', 
	        sortorder : "",
	        emptyrecords : "没有可显示记录", 
	        loadonce : false,
	        multiselect: false
	 	};  
		$("#showUsrFile_grid").jqGrid(showEvent_option); 
		$("#showUsrFile_grid").jqGrid('navGrid', '#showUsrFile_pager', {edit : false, add : false, del : false, search : false, position : 'right'})
		.navButtonAdd('#showUsrFile_pager',{caption:"完成收集",buttonicon:"ui-icon-pencil",onClickButton: function(){toCompleteUsrFile()},position:"last"});
		jqgridResponsive("showUsrFile_grid",false); 
	});
	
	function toCompleteUsrFile(){
		var _id = $("#showUsrFile_grid").jqGrid('getGridParam','selrow');
		if($.isEmptyObject(_id)) { 
			openResultInfo('请选择一条数据',2000,"#alertForShowUsrFile",false);
	        return;
	    }
		
	    var oneData = $("#showUsrFile_grid").jqGrid('getRowData',_id); 
	    
	    if(oneData.COMPLETED=='是'){
			openResultInfo('该文件已经收集',2000,"#alertForShowUsrFile",false);
	        return;
		}
	    if(!confirm('确认提交吗？')){
			return;
		}
	    var data = {};
		data['patientId'] = $.trim(oneData.PATIENT_ID);
		data['fileInfo'] = $.trim(oneData.FILE_INFO);
		data['logMinId'] = $.trim(oneData.LOG_MIN_ID);
		data['inHosDate'] = $.trim(oneData.IN_HOS_DATE);
		data['outHosDate'] = $.trim(oneData.OUT_HOS_DATE);
		data['hosName'] = $.trim(oneData.HOS_NAME);
		if(data['logMinId']==null || data['logMinId']=='' || data['logMinId']=='null'){
			data['logMinId'] = "-1";
		}
	    $.post("${ctx}/qn/eventMgt/collectDictFile",data,function(result){
		      if(result.success){
		    	  openResultInfo("处理成功",2000,"#alertForShowUsrFile");
		          $("#showUsrFile_grid").setGridParam({postData:{page: 1}}).trigger("reloadGrid");
		      }else{
		    	  openResultInfo(result.msg,2000,"#alertForShowUsrFile",false);
		      }
		  },'json');
	    
	}
	
	function showUsrFile_search(){
		var data = {};
		data['fileInfo'] = $("#showUsrFile_dictFileName").val();
		var postData = $("#showUsrFile_grid").jqGrid("getGridParam", "postData");  
	 
		$.extend(postData,data);
	
		$("#showUsrFile_grid").jqGrid("setGridParam", {
			search: true  
		}).trigger("reloadGrid", [{page:1}]);
	}
</script>
