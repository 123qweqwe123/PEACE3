<%@page pageEncoding="UTF-8"%>
<!-- 弹出框组件
调用方法:
			$('.modal-dialog').css("width","520");
			$('.modal-dialog').css("height","500");
			$('#dialog_title').text("答卷导出");
			
		    $("#dialog_detail #type_context" ).load('xxxx.jsp', {});
		    
		    $('#dialog_detail').modal('show');
		    
menu.jsp 中有调用实例

-->

<script type="text/javascript">
<!--
function popWinWithUrl(url,title,width,hight){
	$('.modal-dialog').css("width",width);
	$('.modal-dialog').css("height",hight);
	$('#dialog_title').text(title);
	
    $("#dialog_detail #type_context" ).load(url, {});
    
    $('#dialog_detail').modal('show');
}

function popWinWithHtmlInfo(htmlInfo,title,width,hight){
	$('.modal-dialog').css("width",width);
	$('.modal-dialog').css("height",hight);
	$('#dialog_title').text(title);
	
    $("#dialog_detail #type_context" ).html(htmlInfo);
    
    $('#dialog_detail').modal('show');
}
//-->
</script>
<div id="dialog_detail" class="modal fade" tabindex="1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="dialog_title"></h4>
			</div>

			<!-- <div class="modal-body" style="max-height: 240px;"> -->
			<div class="modal-body">
				<!-- <p id="type_context" style="height: 240px; overflow: auto;"></p> -->
				<p id="type_context">
				
				
				
				</p>
			</div>
			<!-- 
			<div class="modal-footer"
				style="text-align: right; margin-top: 0px; padding: 0 15px 0 0;">
				<button type="button" class="btn" data-dismiss="modal" id="popWinCloseBt">关闭</button>
			</div>
			 -->
		</div>
	</div>
</div>



<div id="headerAndBodyPopWin" class="modal fade" tabindex="1">
	<div class="modal-dialog">
		<div class="modal-content" id="headerAndBody">

		</div>
	</div>
</div>


<!-- 不包括标题和底部按钮 -->
<div id="dialog_detail-nobt" class="modal fade" tabindex="1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-body">
				<p id="type_context-nobt">
				
				
				
				</p>
			</div>
		</div>
	</div>
</div>


    	
