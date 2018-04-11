


function popMenu(t1,t2){
	var $anvlfteb=$('.'+t1),
	$posbox=$anvlfteb.find('li.posbox'),
	$seledbox=$("."+t2),
	anvjson={
		config:'<ul class="dropdown-menu" style="margin:15px 0px;z-index:1000" id="menu_21">'+
		'<li>'+
		'<a href="'+ctx+'/protected/sync/dataSyncWithServer.jsp">数据同步(服)</a>'+
		'</li><li>'+
		'<a href="javaScript:initU();">初始化U盘</a>'+
		'</li><li>'+
		'<a href="'+ctx+'/protected/sync/dataSync.jsp">数据同步(U)</a>'+
		'</li><li>'+
		'<a href="'+ctx+'/protected/config/downViews.jsp">下载数据查看</a>'+
		'</li><li>'+
		'<a href="'+ctx+'/protected/config/configList.jsp">配置</a>'+
		'</li><li>'+
		'<a href="'+ctx+'/protected/log/logViews.jsp">日志</a>' +
		'</li></ul>'
		,
    	admin:'<a href="'+ctx+'/protected/changePwd.jsp">修改密码</a>'+
    	'<a href="'+ctx+'/login.jsp?m=out">退出</a>'
	};
	 

	/*$posbox.mouseover(function(){
		var i=$(this).index();
		$(this).addClass("anvh").siblings().removeClass("anvh");
		var selec=$(this).attr("selec");
		if($seledbox.is(":hidden")){
			$seledbox.show().css("left",64*i+1).html("<div>"+anvjson[selec]+"</div>")
		}else{
			$seledbox.stop().animate({left:64*i+1},200,function(){
				$("#seledbox").html("<div>"+anvjson[selec]+"</div>")
			})
		}
	});
	$anvlfteb.mouseleave(function(){
		$seledbox.hide();
		$posbox.removeClass("anvh");
	})*/
}

String.prototype.verhoeffCheck = (function()
        {
    var d = [[0,1,2,3,4,5,6,7,8,9],
            [1,2,3,4,0,6,7,8,9,5],
            [2,3,4,0,1,7,8,9,5,6],
            [3,4,0,1,2,8,9,5,6,7],
            [4,0,1,2,3,9,5,6,7,8],
            [5,9,8,7,6,0,4,3,2,1],
            [6,5,9,8,7,1,0,4,3,2],
            [7,6,5,9,8,2,1,0,4,3],
            [8,7,6,5,9,3,2,1,0,4],
            [9,8,7,6,5,4,3,2,1,0]];
    var p = [[0,1,2,3,4,5,6,7,8,9],
            [1,5,7,6,2,8,3,0,9,4],
            [5,8,0,3,7,9,6,1,4,2],
            [8,9,1,6,0,4,3,5,2,7],
            [9,4,5,3,1,2,6,8,7,0],
            [4,2,8,6,5,7,3,9,0,1],
            [2,7,9,3,8,0,6,4,1,5],
            [7,0,4,6,9,1,3,2,5,8]];
    var j = [0,4,3,2,1,5,6,7,8,9];

    return function()
    {
        var c = 0;
        if(this.length != 9 && this.length != 11)return false;
        var reg = /^\d{9,11}$/;
        if(!reg.test(this))return false;
        this.replace(/\D+/g,"").split("").reverse().join("").replace(/[\d]/g, function(u, i, o){
            c = d[c][p[i&7][parseInt(u,10)]];
        });
        return (c === 0);
    };
})();

window.onload=function(){
	/****************************
	 * 作者：q821424508@sina.com	*
	 * 时间：2012-08-20			*
	 * version：2.1				*
	 * 							*
	 ****************************/
	document.getElementsByTagName("body")[0].onkeydown =function(){
		
		//获取事件对象
		var elem = event.relatedTarget || event.srcElement || event.target ||event.currentTarget; 
		
		if(event.keyCode==8){//判断按键为backSpace键
		
				//获取按键按下时光标做指向的element
				var elem = event.srcElement || event.currentTarget; 
				
				//判断是否需要阻止按下键盘的事件默认传递
				var name = elem.nodeName;
				
				if(name!='INPUT' && name!='TEXTAREA'){
					return _stopIt(event);
				}
				var type_e = elem.type.toUpperCase();
				if(name=='INPUT' && (type_e!='TEXT' && type_e!='TEXTAREA' && type_e!='PASSWORD' && type_e!='FILE')){
						return _stopIt(event);
				}
				if(name=='INPUT' && (elem.readOnly==true || elem.disabled ==true)){
						return _stopIt(event);
				}
			}
		}
	}
function _stopIt(e){
		if(e.returnValue){
			e.returnValue = false ;
		}
		if(e.preventDefault ){
			e.preventDefault();
		}				

		return false;
}
