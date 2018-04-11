var rootPath = 'http://'+location.host;
rootPath += location.pathname.substring(0, location.pathname.indexOf('/', 1));
//alert(rootPath);
var ge ;

//设置参数==========
function setOptions(ge){
	  ge.getWindow().setVisibility(true);
	  
	//导航控件      ge.VISIBILITY_SHOW    ge.VISIBILITY_HIDE    ge.VISIBILITY_AUTO
	  ge.getNavigationControl().setVisibility(ge.VISIBILITY_AUTO);
	  // add some layers
	  //ge.getLayerRoot().enableLayerById(ge.LAYER_TERRAIN, true);
	  //ge.getLayerRoot().enableLayerById(ge.LAYER_BORDERS, true);
	  ge.getLayerRoot().enableLayerById(ge.LAYER_ROADS, true);


}

//设置视角
function setViewFace(ge,lat,long,alt,tilt,heading,range){
	// Set the placemark's location. 
	var la = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);  
	la.setLatitude(lat); // latitude纬度
	la.setLongitude(long); // longitude经度
	la.setAltitude(alt);// altitude高度
	la.setTilt(tilt); //仰视倾斜度
	la.setHeading( heading);//左右倾斜度
	la.setRange(range); //default is 0.0可视细度
	ge.getView().setAbstractView(la);
	
  // look at the placemark we created
  /* var la = ge.createLookAt('');
  la.set(
  31, // latitude纬度
  121,// longitude经度
  0, // altitude
  ge.ALTITUDE_RELATIVE_TO_GROUND,
  0, // heading
  0, // straight-down tilt
  500000 // range(inverse of zoom)
  );
  ge.getView().setAbstractView(la);
  */	
}
//视觉缩进
function inViewFace(ge, limits){
	// Set the placemark's location. 
	var la = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);
	//la.setHeading( heading);//左右倾斜度
	var viewRange = la.getRange();
	if(limits && viewRange> limits )	la.setRange(viewRange-limits); //default is 0.0可视距离
	ge.getView().setAbstractView(la);
	
}

//视觉缩进
function outViewFace(ge,limits){
	// Set the placemark's location. 
	var la = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);  
	//la.setHeading( heading);//左右倾斜度
	var viewRange = la.getRange();
	if(limits )	la.setRange(viewRange+limits); //default is 0.0可视距离
	ge.getView().setAbstractView(la);
	
}

//视觉向上
function upViewFace(ge,c){
	// Set the placemark's location. 
	var la = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);  
	//la.setHeading( heading);//左右倾斜度
	var tilt = la.getTilt();
	if(tilt<90 && c)	la.setTilt((tilt+ c)< 90 ? (tilt+ c): 90 );  
	ge.getView().setAbstractView(la);
	
}

//视觉向下
function downViewFace(ge,c){
	// Set the placemark's location. 
	var la = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);  
	//la.setHeading( heading);//左右倾斜度
	var tilt = la.getTilt();
	if(tilt>0 && c)	la.setTilt((tilt- c)>0 ? (tilt- c): 0 ); 
	ge.getView().setAbstractView(la);
	
}

//视觉顺时针
function clockWiseViewFace(ge,h){
	// Set the placemark's location. 
	var la = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);
	var heading = la.getHeading();
	if(heading< 180 && h)	la.setHeading((heading+ h)< 180 ? (heading+ h): 180 );
	ge.getView().setAbstractView(la);
	
}

//视觉逆时针
function clockReverseViewFace(ge,h){
	// Set the placemark's location. 
	var la = ge.getView().copyAsLookAt(ge.ALTITUDE_RELATIVE_TO_GROUND);  
	var heading = la.getHeading();
	if(heading > -180 && h) la.setHeading((heading- h)> -180 ? (heading- h): -180 ); 
	ge.getView().setAbstractView(la);
	
}

//添加一个位置
function addPlaceMark(ge,name, lat,long, alt){
	  // create the placemark
	  var  placemark = ge.createPlacemark('');
	  placemark.setName(name);

	// Define a custom icon.
	  var icon = ge.createIcon('');
	  //icon.setHref('http://maps.google.com/mapfiles/kml/paddle/red-circle.png');
	  icon.setHref(rootPath +'/static/adp/images/plane2.png');
	  
	  var style = ge.createStyle('');
	  style.getIconStyle().setIcon(icon);
	  style.getIconStyle().setScale(1.2);   //大小
	  style.getLabelStyle().setScale(0.6);  //地标名字大小
	  placemark.setStyleSelector(style);
	  
	  var point = ge.createPoint('');
	  point.setAltitudeMode(ge.ALTITUDE_ABSOLUTE);
	  point.setLatitude(lat);
	  point.setLongitude(long);
	  if(alt){
		  point.setAltitude(alt);
	  }
	  placemark.setGeometry(point);
	  // add the placemark to the earth DOM
	  ge.getFeatures().appendChild(placemark);
	
	  return placemark;
}

function movePlaceMark(placemark,lat,long, alt){
	  // create the placemark
	  
	  var point =  placemark.getGeometry();
	  point.setLatitude(lat);
	  point.setLongitude(long);
	  if(alt){
		  point.setAltitude(alt);
	  }
	
	  return placemark;
}

//添加一个点
function addPoint(ge, lat,long, alt){
	  // create the placemark
	  var  placemark = ge.createPlacemark('');
	  var point = ge.createPoint('');
	  point.setAltitudeMode(ge.ALTITUDE_ABSOLUTE);
	  point.setLatitude(lat);
	  point.setLongitude(long);
	  if(alt){
		  point.setAltitude(alt);
	  }
	  placemark.setGeometry(point);
	  // add the placemark to the earth DOM
	  ge.getFeatures().appendChild(placemark);
	
	  return placemark;
}
	
//添加一个点有样式
function addPointWithStyle(ge, lat,long, alt){
	  // create the placemark
	  var  placemark = ge.createPlacemark('');
	// Define a custom icon.
	  var icon = ge.createIcon('');
	  icon.setHref('http://maps.google.com/mapfiles/kml/paddle/red-circle.png');
	  var style = ge.createStyle('');
	  style.getIconStyle().setIcon(icon);
	  //style.getIconStyle().setScale(5.0);   //大小
	  //style.getLabelStyle().setScale(0.5);  //地标名字大小
	  placemark.setStyleSelector(style);
	  
	  var point = ge.createPoint('');
	  point.setAltitudeMode(ge.ALTITUDE_ABSOLUTE);
	  point.setLatitude(lat);
	  point.setLongitude(long);
	  if(alt){
		  point.setAltitude(alt);
	  }
	  placemark.setGeometry(point);
	  // add the placemark to the earth DOM
	  ge.getFeatures().appendChild(placemark);
	
	  return placemark;
}	


	
function makeBalloonContent(ge,lat,long,alt,tilt){
	var contentHTML = '';
	var ul='<ul>'
	+'<li><strong class="text-error">纬度</strong>：<span class="label label-success">'+lat+'</span></li>'
	+'<li><strong class="text-error">经度</strong>：<span class="label label-warning">'+long+'</span></li>'
	+'<li><strong class="text-error">高度</strong>：<span class="label label-info">'+alt+'</span></li>'
	+'</ul>' ;
   var detailButton = '<button class="btn btn-small btn-inverse" type="button" onclick="inViewFace(ge,2000000)"><i class="icon-zoom-in icon-white"></i></button>';
   var overButton = '<button class="btn btn-small btn-inverse" type="button" onclick="outViewFace(ge,2000000)"><i class="icon-zoom-out icon-white"></i></button>';
   var upButton = '<button class="btn btn-small btn-inverse" type="button" onclick="upViewFace(ge,30)"><i class="icon-arrow-up icon-white"></i></button>';
   var downButton = '<button class="btn btn-small btn-inverse" type="button" onclick="downViewFace(ge,30)"><i class="icon-arrow-down icon-white"></i></button>';
   var clockWise  = '<button class="btn btn-small btn-inverse" type="button" onclick="clockWiseViewFace(ge,30)"><i class="icon-arrow-left icon-white"></i></button>';
   var clockReverse = '<button class="btn btn-small btn-inverse" type="button" onclick="clockReverseViewFace(ge,30)"><i class="icon-arrow-right icon-white"></i></button>';
   
   
   contentHTML +=ul+detailButton+overButton +upButton+downButton +clockWise+ clockReverse;
   return contentHTML;
}


//新建一个气泡
function addBalloon(ge,name, lat, long, alt, contentHTML){
		
		var placemark = addPlaceMark(ge,name, lat,long, alt);

		bandBalloon(ge, placemark, contentHTML);
	
}

//绑定一个气泡
function bandBalloon(ge, placemark, contentHTML ){
	//地标mousedown、mouseover的缺省处理是弹出球（balloon）
	
	google.earth.addEventListener(placemark, 'mouseover', function(event) {  
		event.preventDefault();   //屏蔽缺省处理方式
		var balloon = ge.createHtmlStringBalloon('');
		balloon.setMinWidth(400);
		balloon.setMaxHeight(400);
		balloon.setBackgroundColor('#cccccc');
		balloon.setCloseButtonEnabled(true);
		balloon.setContentString(contentHTML);
		balloon.setFeature(placemark);
	 	ge.setBalloon(balloon); 	
	});
	
	//google.earth.addEventListener(placemark, 'mouseout', function(event) {  
	//	event.preventDefault();   //屏蔽缺省处理方式 
	// 	ge.setBalloon(null); 	
	//});	
	
		
}



//给点添加一个样式
function putStyleToPlaceMark(ge, placemark){
	// Create a style map.
	var styleMap = ge.createStyleMap('');

	// Create normal style for style map.
	var normalStyle = ge.createStyle('');
	var normalIcon = ge.createIcon('');
	normalIcon.setHref('http://maps.google.com/mapfiles/kml/paddle/red-circle.png');
	normalStyle.getIconStyle().setIcon(normalIcon);

	// Create highlight style for style map.
	var highlightStyle = ge.createStyle('');
	var highlightIcon = ge.createIcon('');
	highlightIcon.setHref('http://google-maps-icons.googlecode.com/files/girlfriend.png');

	highlightStyle.getIconStyle().setIcon(highlightIcon);
	highlightStyle.getIconStyle().setScale(5.0);

	styleMap.setNormalStyle(normalStyle);
	styleMap.setHighlightStyle(highlightStyle);

	// Apply stylemap to a placemark.
	placemark.setStyleSelector(styleMap);

}


//画线==========
function addLine(ge){
	var place_line_mark = ge.createPlacemark('');
	
	var lineString = ge.createLineString('');
	place_line_mark.setGeometry(lineString);
	lineString.setExtrude(false);
	lineString.setAltitudeMode(ge.ALTITUDE_RELATIVE_TO_GROUND);			
	// Add LineString points
	addPointsToLineString(ge,lineString,31.584207, 121.754322, 33, -100, 40); 
    
	// Create a style and set width and color of line
	place_line_mark.setStyleSelector(ge.createStyle(''));
	var lineStyle = place_line_mark.getStyleSelector().getLineStyle();
	lineStyle.setWidth(2);
	//lineStyle.getColor().set('9900ffff');
	lineStyle.getColor().set('ffffffff'); 
	
	// Add the feature to Earth
	ge.getFeatures().appendChild(place_line_mark);
}

//画线==========
function addPointsToLineString(ge,lineString,lat1,lang1,lat2,lang2,steps){
    var i;
    var lat;
    var lang;
    var height;
    for(i=0;i<steps;i++){    	
      lat = lat1+ i*(lat2-lat1)/steps;
      lang = lang1+ i*(lang2-lang1)/steps;
      if (i==0)
            height = 0;
      else if(i==1 || i==steps-1)
            height = 8130.76624025246;
      else if(i==2 || i==steps-2)
            height = 15640.4403479026;
			else if(i==3 || i==steps-3)
            height = 22453.9881416349;
			else if(i==4 || i==steps-4)
            height = 28503.3309041539;
			else if(i==5 || i==steps-5)
            height = 33728.0256022233;
			else if(i==6 || i==steps-6)
            height = 38075.8688134745;
			else if(i==7 || i==steps-7)
            height = 41503.4183257466;
			else if(i==8 || i==steps-8)
            height = 43976.4271973102;
			else if(i==9 || i==steps-9)
			height = 45470.1859409886 ;
			else if(i==10){	 						
            height = 45969.769413186;
            
   		}
    lineString.getCoordinates().pushLatLngAlt(lat,lang, height);     
    var contentHTML = makeBalloonContent(ge,lat, lang, height,45,0,500000);
    addBalloon(ge,'坐标', lat, lang, height, contentHTML);
    	
     }
     lineString.getCoordinates().pushLatLngAlt(lat2,lang2, 0);

} 



//画线==========
function addLine2(ge){
	var place_line_mark = ge.createPlacemark('');
	
	var lineString = ge.createLineString('');
	place_line_mark.setGeometry(lineString);
	lineString.setExtrude(false);
	lineString.setAltitudeMode(ge.ALTITUDE_RELATIVE_TO_GROUND);			
    
	// Create a style and set width and color of line
	place_line_mark.setStyleSelector(ge.createStyle(''));
	var lineStyle = place_line_mark.getStyleSelector().getLineStyle();
	lineStyle.setWidth(2);
	lineStyle.getColor().set('ffffffff'); 

	// Add LineString points
	//addPointsToLineString2(lineString,31.584, 121, 10); 

	var placeMark= addPlaceMark(ge, '',40.138,116.053,21703); 
	
    window.setTimeout(function(){														   	
    	lineString.getCoordinates().pushLatLngAlt(40.138,116.053,21703);            
    	       }, 1000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(39.537,115.154,32098);	
    	movePlaceMark(placeMark,39.537,115.154,32098);	                 
    	       }, 2000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(38.672,114.681,32096);
    	movePlaceMark(placeMark,38.672,114.681,32096);                   
    	       }, 3000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(37.622,114.802,38090);
    	movePlaceMark(placeMark,37.622,114.802,38090);                   
    	       }, 4000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(36.491,114.928,38095);  
    	movePlaceMark(placeMark,36.491,114.928,38095);                   
    	       }, 5000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(35.403,114.744,38099);
    	movePlaceMark(placeMark,35.403,114.744,38099);                   
    	       }, 6000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(34.266,114.635,38103);
    	movePlaceMark(placeMark,34.266,114.635,38103);                   
    	       }, 7000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(33.160,114.530,38100);
    	movePlaceMark(placeMark,33.160,114.530,38100);                   
    	       }, 8000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(32.920,113.513,38099);   
    	movePlaceMark(placeMark,32.920,113.513,38099);                   
    	       }, 9000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(32.196,114.492,38104);
    	movePlaceMark(placeMark,32.196,114.492,38104);                   
    	       }, 10000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(31.105,114.229,38103);
    	movePlaceMark(placeMark,31.105,114.229,38103);                   
    	       }, 11000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(30.786,113.243,36920);
    	movePlaceMark(placeMark,30.786,113.243,36920);                   
    	       }, 12000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(30.653, 113.052, 34099);
    	movePlaceMark(placeMark,30.653,113.052,34099);                   
    	       }, 13000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(29.925,113.240,34102);
    	movePlaceMark(placeMark,29.925,113.240,34102);                   
    	       }, 14000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(28.856,113.633,34094);
    	movePlaceMark(placeMark,28.856,113.633,34094);                   
    	       }, 15000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(27.801,113.554,34102);
    	movePlaceMark(placeMark,27.801,113.554,34102);                   
    	       }, 16000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(27.568,113.780,36096);
    	movePlaceMark(placeMark,27.568,113.780,36096);                   
    	       }, 17000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(26.527,113.848,34100);
    	movePlaceMark(placeMark,26.527,113.848,34100);                   
    	       }, 18000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(26.412,113.828,34098);
    	movePlaceMark(placeMark,26.412,113.828,34098);                   
    	       }, 19000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(26.299,113.809,34104);
    	movePlaceMark(placeMark,26.299,113.809,34104);                   
    	       }, 20000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(26.183,113.790,34099);
    	movePlaceMark(placeMark,26.183,113.790,34099);                   
    	       }, 21000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(26.067,113.771,34102);
    	movePlaceMark(placeMark,26.067,113.771,34102);                   
    	       }, 22000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.951,113.755,34094);
    	movePlaceMark(placeMark,25.951,113.755,34094);                   
    	       }, 23000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.833,113.744,34104);  
    	movePlaceMark(placeMark,25.833,113.744,34104);                   
    	       }, 24000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.713,113.734,34098);
    	movePlaceMark(placeMark,25.713,113.734,34098);                   
    	       }, 25000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.594,113.732,34104);
    	movePlaceMark(placeMark,25.594,113.732,34104);                   
    	       }, 26000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.476,113.733,34114);
    	movePlaceMark(placeMark,25.476,113.733,34114);                   
    	       }, 27000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.360,113.739,34102);
    	movePlaceMark(placeMark,25.360,113.739,34102);                   
    	       }, 28000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.240,113.764,32442);
    	movePlaceMark(placeMark,25.240,113.764,32442);                   
    	       }, 29000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.125,113.790,30533);
    	movePlaceMark(placeMark,25.125,113.790,30533);                   
    	       }, 30000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(25.026,113.866,28369);
    	movePlaceMark(placeMark,25.026,113.866,28369);                   
    	       }, 31000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.959,113.984,27011);
    	movePlaceMark(placeMark,24.959,113.984,27011);                   
    	       }, 32000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.894,114.098,25422);
    	movePlaceMark(placeMark,24.894,114.098,25422);                   
    	       }, 33000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.833,114.205,23907);
    	movePlaceMark(placeMark,24.833,114.205,23907);                   
    	       }, 34000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.750,114.286,23597);
    	movePlaceMark(placeMark,24.750,114.286,23597);                   
    	       }, 35000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.651,114.267,23600);
    	movePlaceMark(placeMark,24.651,114.267,23600);                   
    	       }, 36000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.559,114.226,23543);
    	movePlaceMark(placeMark,24.559,114.226,23543);                   
    	       }, 37000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.471,114.188,21990);
    	movePlaceMark(placeMark,24.471,114.188,21990);                   
    	       }, 38000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.382,114.149,20871);
    	movePlaceMark(placeMark,24.382,114.149,20871);                   
    	       }, 39000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.302,114.100,19645);
    	movePlaceMark(placeMark,24.302,114.100,19645);                   
    	       }, 40000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.225,114.048,18253);
    	movePlaceMark(placeMark,24.225,114.048,18253);                   
    	       }, 41000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.149,113.996,17691);
    	movePlaceMark(placeMark,24.149,113.996,17691);                   
    	       }, 42000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.077,113.936,17704);
    	movePlaceMark(placeMark,24.077,113.936,17704);                   
    	       }, 43000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(24.010,113.870,17701);
    	movePlaceMark(placeMark,24.010,113.870,17701);                   
    	       },44000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.944,113.805,17709);
    	movePlaceMark(placeMark,23.944,113.805,17709);                   
    	       }, 45000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.878,113.740,17702);
    	movePlaceMark(placeMark,23.878,113.740,17702);                   
    	       }, 46000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.811,113.674,17702);
    	movePlaceMark(placeMark,23.811,113.674,17702);                   
    	       },47000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.746,113.611,17705);
    	movePlaceMark(placeMark,23.746,113.611,17705);                   
    	       }, 48000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.679,113.545,17698);
    	movePlaceMark(placeMark,23.679,113.545,17698);                   
    	       }, 49000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.613,113.482,17701);
    	movePlaceMark(placeMark,23.613,113.482,17701);                   
    	       }, 50000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.550,113.413,17704);
    	movePlaceMark(placeMark,23.550,113.413,17704);                   
    	       }, 51000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.496,113.340,17697);
    	movePlaceMark(placeMark,23.496,113.340,17697);                   
    	       }, 52000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.443,113.265,17699);
    	movePlaceMark(placeMark,23.443,113.265,17699);                   
    	       }, 53000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.371,113.210,17699);
    	movePlaceMark(placeMark,23.371,113.210,17699);                   
    	       }, 54000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.295,113.157,17704);
    	movePlaceMark(placeMark,23.295,113.157,17704);                   
    	       }, 55000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.213,113.119,17702);
    	movePlaceMark(placeMark,23.213,113.119,17702);                   
    	       }, 56000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.121,113.124,17513);
    	movePlaceMark(placeMark,23.121,113.124,17513);                   
    	       }, 57000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(23.049,113.192,16173);
    	movePlaceMark(placeMark,23.049,113.192,16173);                   
    	       }, 58000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.996,113.278,14694);
    	movePlaceMark(placeMark,22.996,113.278,14694);                   
    	       }, 59000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.952,113.370,13635);
    	movePlaceMark(placeMark,22.952,113.370,13635);                   
    	       }, 60000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.909,113.461,12594);
    	movePlaceMark(placeMark,22.909,113.461,12594);                   
    	       }, 61000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.846,113.522,11348);
    	movePlaceMark(placeMark,22.846,113.522,11348);                   
    	       }, 62000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.764,113.540,10097);
    	movePlaceMark(placeMark,22.764,113.540,10097);                   
    	       }, 63000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.684,113.556,9071 );
    	movePlaceMark(placeMark,22.684,113.556,9071 );                   
    	       }, 64000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.604,113.573,8028 );
    	movePlaceMark(placeMark,22.604,113.573,8028 );                   
    	       }, 65000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.532,113.600,6591 );
    	movePlaceMark(placeMark,22.532,113.600,6591 );                   
    	       }, 66000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.500,113.682,5035 );
    	movePlaceMark(placeMark,22.500,113.682,5035 );                   
    	       }, 67000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.470,113.760,3913 );
    	movePlaceMark(placeMark,22.470,113.760,3913 );                   
    	       }, 68000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.458,113.834,3412 );
    	movePlaceMark(placeMark,22.458,113.834,3412 );                   
    	       }, 69000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.487,113.891,2719 );
    	movePlaceMark(placeMark,22.487,113.891,2719 );                   
    	       }, 70000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.537,113.877,2035 );
    	movePlaceMark(placeMark,22.537,113.877,2035 );                   
    	       }, 71000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.577,113.855,1159 );
    	movePlaceMark(placeMark,22.577,113.855,1159 );                   
    	       }, 72000)	;  
    	    window.setTimeout(function(){                              
    	lineString.getCoordinates().pushLatLngAlt(22.614,113.835,428  );
    	movePlaceMark(placeMark,22.614,113.835,428  );                   
    	       }, 73000)	;
 
    
    
    
	// Add the feature to Earth
	ge.getFeatures().appendChild(place_line_mark);
}

//画线==========
function addPointsToLineString2(lineString,lat,lang,steps){
    for(var i=0;i<steps;i++){ 
    	
    //setTimeout("sss("+lineString+","+lat+","+lang-(i*10)+")", 1000*(i+1));
    	setTimeout("var lin="+lineString+"; alert({lin})", 1000*(i+1));
     }

} 

function sss(lineString,lat,lang){
    lineString.getCoordinates().pushLatLngAlt(lat, lang, 15640);    
}



