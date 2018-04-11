<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>

<title>物资管理</title>
</style>
</head>
<body>

	<ul class="breadcrumb">
		<li class="active">项目管理</li>
		<li class="active">联系人管理</li>
	</ul>
	
	<div id="select">
        <div class="select-main">
              <form action="" method="post" class="well-work bs-adp form-inline">
       			<fieldset>
                	<ol>
                    	 <li class="select-one"> <label form='name'>联系人姓名:</label></li>
                         <li> 
                         <input type="text" id="username" name="username" class="form-control input-sm" placeholder="联系人姓名"/>    
                         </li>
                         <li class="select-one"> <label form='name'>所属单位:</label></li>
                         <li> 
				            <%-- <select id="lccCode" name="lccCode" class="form-control input-sm">
								<option value="">请选择单位</option>
								<c:forEach items="${lccDictList }" var="lccDict">
									<option value="${lccDict.lccCode }" >${lccDict.lccName }</option>
								</c:forEach>
							</select> --%>
							<input id="lccCode" type="hidden" name="lccCode" />
							<input id="lccName"  class="form-control input-sm"  placeholder="输入单位简拼或LCCID"/>
                         
                         </li>
                        <li> <button type="button" onclick="searchLinkMan();" class="btn btn-primary btn-align-right btn-sm">查询</button></li>
                    </ol>
                    <ol>
                       
                    </ol>
                </fieldset>
              </form>
        </div>
    </div>
	<div id="jqgrid">
		<table id="grid"></table>
		<div id="pager"></div>
	</div>

	<script type="text/javascript">
		$(function() {
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
			            return item.helpCode+item.lccCode;
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
			var option = {
				url : '${ctx}/pro/linkman/list?status=0',
				datatype : 'json',
				mtype : 'POST',
				colNames : [ '','联系人姓名','角色','所属单位','电话','手机','Email','联系人地址','' ],
				colModel : [
						{
							name : 'linkManCode',
							index : 'linkman_code',
							hidden : true
						},
						{
							name : 'username',
							index : 'username'
						},
						{
							name : 'lccRole',
							index : 'lcc_role'
						},
						{
							name : 'lccName',
							index : 'lcc_code'
						},
						{
							name : 'phone',
							index : 'phone',
							align : 'left'
						},
						{
							name : 'mobile',
							index : 'mobile'
						},
						{
							name : 'email',
							index : 'email'
						},{
							name : 'address',
							index : 'address'
						},{
							name : 'status',
							index : 'status',
							hidden : true
						}],
				rowNum : 15,
				rowList : [ 15, 30, 50,100,150,500 ],
				height : "100%",
				autowidth : true,
				pager : '#pager',
				sortname : 'username',
				altRows : true,
				hidegrid : false,
				viewrecords : true,
				recordpos : 'left',
				sortorder : "asc",
				emptyrecords : "没有可显示记录",
				loadonce : false,
				//multiselect : true,
				loadComplete : function() {
				},
				jsonReader : {
					root : "rows",
					page : "page",
					total : "total",
					records : "records",
					repeatitems : false,
					cell : "cell",
					id : "linkManCode"
				}
			};
			$("#grid").jqGrid(option);
			$("#grid").jqGrid('navGrid', '#pager', {
				edit : false,
				add : false,
				del : false,
				search : false,
				position : 'right'
			});
			//自适应
			jqgridResponsive("grid", false);
			
		});
		function searchLinkMan() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var lccCode =$("#lccCode").val();
		      if(lccCode && ''!=lccCode){
		          mydata.lccCode = lccCode;
		      }else{
		          delete postData.lccCode;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
		function searchLinkMan() {  
		      var postData = $("#grid").jqGrid("getGridParam", "postData");  
		      var mydata = {};
		      var username =$("#username").val();
		      var lccCode = $("#lccCode").val();
		      if(username && ''!=username){
		          mydata.username = username;
		      }else{
		          delete postData.username;
		      }
		      if(lccCode && ''!=lccCode){
		          mydata.lccCode = lccCode;
		      }else{
		          delete postData.lccCode;
		      }
		     $.extend(postData,mydata);
		     $("#grid").jqGrid("setGridParam", {
		         search: true  
		     }).trigger("reloadGrid", [{page:1}]);
		 };
	</script>
</body>
</html>