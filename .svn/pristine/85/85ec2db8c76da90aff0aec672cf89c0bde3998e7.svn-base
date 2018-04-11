	String.prototype.trim = function() {
	    if(this == null || this == undefined){
	    	return this;
	    }
	    return this.replace(/^\s+/g,"").replace(/\s+$/g,"");
	}

	Date.prototype.Format = function(fmt){
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} 

	function checkScope(v,type,vScope,format){
		var arr = vScope.split(",");
		arr[0]  = arr[0].trim();
		arr[1]  = arr[1].trim();
		var min = arr[0].substr(1);
		var minChar = arr[0].substr(0,1);
		
		var max = arr[1].substr(0,arr[1].length-1).trim();
		var maxChar = arr[1].substr(arr[1].length-1);
		if(type == "number"){
			if(isNaN(v)){
				return false;
			}
			if(min!=""){
				if(minChar == "("){
					if(parseFloat(v) <= parseFloat(min))return false;
				}else{
					if(parseFloat(v) < parseFloat(min))return false;
				}
			}
			if(max!=""){
				if(maxChar == ")"){
					if(parseFloat(v) >= parseFloat(max))return false;
				}else{
					if(parseFloat(v) > parseFloat(max))return false;
				}
			}
		}
		
		
		if(type == "datenum"){
			if(min!=""){
				if(minChar == "("){
					if(v <= formatDateNum(min,format))return false;
				}else{
					if(v < formatDateNum(min,format))return false;
				}
			}
			if(max!=""){
				if(maxChar == ")"){
					if(v >= formatDateNum(max,format))return false;
				}else{
					if(v > formatDateNum(max,format))return false;
				}
			}
		}
		return true;
	}
	/**
	 * 将T+3y4M2d 格式化
	 */
	function formatDateNum(v,format){
		if(v.indexOf("T")==-1){
			return v;
		}
		var nowDate = new Date();
		if(v.indexOf("+")>-1){
			var arr = dateSplit(v.substr(v.indexOf("+")+1).trim());
			for(var i in arr){
				var num = parseInt(arr[i].substr(0,arr[i].length-1));
				var label = arr[i].substr(arr[i].length-1);
				if(label=="y"){
					nowDate.setFullYear(nowDate.getFullYear()+num); 
				}else if(label=="M"){
					nowDate.setMonth(nowDate.getMonth()+num); 
				}else if(label=="d"){
					nowDate.setDate(nowDate.getDate()+num); 
				}
			}
		}else if(v.indexOf("-")>-1){
			var arr = dateSplit(v.substr(v.indexOf("-")+1).trim());
			for(var i in arr){
				var num = parseInt(arr[i].substr(0,arr[i].length-1));
				var label = arr[i].substr(arr[i].length-1);
				if(label=="y"){
					nowDate.setFullYear(nowDate.getFullYear()-num); 
				}else if(label=="M"){
					nowDate.setMonth(nowDate.getMonth()-num); 
				}else if(label=="d"){
					nowDate.setDate(nowDate.getDate()-num); 
				}
			}
		}
		return nowDate.Format(format);
	}

	/**
	 * 将 3y4M2d拆分成 数组
	 */
	function dateSplit(v){
		var arr = new Array();
		var yInt = v.indexOf("y");
		if(yInt>-1){
			arr[arr.length]=v.substr(0,yInt+1);
			v=v.substr(yInt+1);
		}
		
		var MInt = v.indexOf("M");
		if(MInt>-1){
			arr[arr.length]=v.substr(0,MInt+1);
			v=v.substr(MInt+1);
		}
		
		var dInt = v.indexOf("d");
		if(dInt>-1){
			arr[arr.length]=v.substr(0,dInt+1);
			v=v.substr(dInt+1);
		}
		return arr;
	}
	
	
	/**
	 * 根据字符得到正则表达式
	 */
	function getExpression(type){
		var ch = "\\u4e00-\\u9fa5";
		var num = "0-9";
		var en = "A-Za-z";
		var sp = "\^\\%&\*~'\?\/\<\>\|\"`";//用到时再进行验证
		
		var returnStr = "";
		if(type.indexOf("ch")>-1){
			returnStr += ch;
		}
		if(type.indexOf("en")>-1){
			returnStr += en;
		}
		if(type.indexOf("num")>-1){
			returnStr += num;
		}
		if(type.indexOf("sp")>-1){
			returnStr += sp;
		}
		return returnStr;
	}