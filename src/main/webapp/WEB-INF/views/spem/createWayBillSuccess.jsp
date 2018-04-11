<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
 <title>样本管理</title> 
<style type="text/css">
.bc1{
    cursor:pointer;
    border-right: #F1E1FF 3px ridge !important; 
    border-top: #F1E1FF 3px ridge !important; 
    border-left: #F1E1FF 3px ridge !important; 
    border-bottom: #F1E1FF 3px ridge !important;
   text-align:center;
   
}

.bc1:hover {
    
    background-color:   #9393FF !important;
    border-right:   #FFFFFF 3px ridge !important; 
    border-top:     #FFFFFF 3px ridge !important; 
    border-left:    #FFFFFF 3px ridge !important; 
    border-bottom:  #FFFFFF 3px ridge !important;
}

</style>

</head>
<body>

  <h1> 创建运送单成功了！！</h1>

  <div class="col-lg-10 col-md-offset-1 col-md-10"> 
      <button type="button" id="do_continue" class="btn btn-primary btn-sm">继续生成新运送单</button>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <button type="button" id="do_eidt" class="btn btn-primary btn-sm">查看运送单</button>
  </div> 


<script type="text/javascript">


$('#do_continue').click(function(){

     var url = "${ctx}/spem/iceBoxReg/query";
     window.location.href=url;  
})

$('#do_eidt').click(function(){
     var url = "${ctx}/spem/waybill/forQueryBill"
     window.location.href=url;  
})

</script>


</body>
</html>