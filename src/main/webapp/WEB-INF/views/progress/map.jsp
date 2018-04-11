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
<script type="text/javascript" src="${ctx}/static/echarts/echarts-map.js" ></script>
<style>
	
	.txt{
	    /* font-size:14px;
	    font-weight:bold; */
	}
	
	.txtunit{
			font-size:10px;
			color:#222;
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
        
        .mapType{
        
        	background: #058eb2;
			padding: 6px;
			font-size: 14px;
			color: #FFF;
			margin: 10px;
			text-align: center;
			font-size:24px;
        }
        
        .data {
			background: #fff;
			padding: 0px;
			height: auto;
			overflow: hidden;
			float: left;
			margin-right: 0px;
			/* box-shadow: -2px 0 2px #dadada,
			 2px 0 2px #dadada,
			 0 -2px 2px #dadada,
			 0 2px 2px #dadada; */
			margin-top: 15px;
			margin-left: 0px;
		}
</style>
</head>
<body>
	<div id="navbar">
    	<ol class="breadcrumb">
          <li class="active">进度管理</li>
          <li class="active">项目整体进度</li>
        </ol>
    </div>


<div class='map' style="padding:25px 0px;">
	
	 <div id="graphic" class="col-md-7">
                <div id="main" class="main" style="height: 400px;width:650px;border:none;"></div>
     </div>
     <table class="">
        				<tr>
        					<td colspan="6"><span id="txt_mapType" class="mapType">全国</span></td>
        				</tr>
        				<tr>
        					<td width="130px">&nbsp;</td>
        					<td width="130px"></td>
        					<td width="10px"></td>
        					<td width="100px">&nbsp;</td>
        					<td width="100px"></td>
        					<td width="10px"></td>
        				</tr>
        				<tr>
        					<td colspan="3">
        					<ul class="data">
        						<li class="data01" title="上报花名册人数">
                                    <p class="figure"><span style="cursor: pointer;" id="result_PATIENT" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p >上报花名册人数</p>
                                </li>
                                <li class="data02" title="已完成初筛的人数">
                                    <p class="figure"><span onclick="selPatient(2)" style="cursor: pointer;" id="result_UQS2" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p >初筛人数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                </li>
                                <!-- <li class="data02" title="成功完成初筛的人数占目标人数的百分比">
                                    <p class="figure"><span id="result_csp" class="txt">0</span><span class="txtunit">%</span></p>
                                    <p >初筛完成率</p>
                                </li> -->
                            </ul>
                            </td>
                            <td colspan="3">
                            <ul class="data">
                                <li class="data03" title="初筛确定的高危对象人数">
                                    <p class="figure"><span id="result_RISK" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p>高危检出人数</p>
                                </li>
                                <!-- <li class="data04" title="检出的高危对象人数占所有已完成初筛人数的百分比">
                                    <p class="figure"><span id="result_gwp" class="txt">0</span><span class="txtunit">%</span></p>
                                    <p>高危检出率</p>
                                </li> -->
                            </ul>
                        	    
                            </td>
                        </tr>
        				<!-- <tr style="" >
        					<td><span class="txttitle">高危对象检出人数</span></td>
        					<td><span id="result_risk" class="txt">0</span><span class="txtunit">人</span></td>
        					<td align="right" class="upArrow"><div  class="percentTxt">环比<br><span class="txt" id="tx_last_outflow_trend">0</span>%</div></td>
        				
        					<td><span class="txttitle">高危对象检出率</span>&nbsp;&nbsp;</td>
        					<td><span id="result_gwp" class="txt">0</span><span class="txtunit">%</span></td>
        					<td align="right" class="upArrow"><div class="percentTxt">环比<br><span class="txt" id="tx_last_net_inflow_trend">0</span>%</div></td>
        				</tr> -->
        				<tr>
        					<td>&nbsp;</td>
        					<td></td>
        					<td></td>
        					<td>&nbsp;</td>
        					<td></td>
        					<td></td>
        				</tr>
        				<tr>
        					<td colspan="3">
        					<ul class="data">
                               
                                <li class="data01" title="检出的高危对象人数占所有已完成初筛人数的百分比">
                                    <p class="figure"><span id="result_RISK_PER" class="txt">0</span><span class="txtunit">%</span></p>
                                    <p>高危检出率&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
                                </li>
                                 <li class="data02" title="已完成全部高危调查项目并上传数据完整的人数">
                                    <p class="figure"><span id="result_UQS34" onclick="selPatient(3)" style="cursor: pointer;" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p>完成高危人数</p>
                                </li>
                            </ul>
                            </td>
                            <td colspan="3">
                            <ul class="data">
                            	<li class="data03" title="检出的高危对象人数占所有已完成初筛人数的百分比">
                                    <p class="figure"><span id="result_UQS5" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p>完成干预人数</p>
                                </li>
                                <!-- <li class="data03" title="在干预之后，成功预约随访（即同意进行随访）的高危对象人数">
                                    <p class="figure"><span id="result_order" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p>随访预约人数</p>
                                </li>
                                <li class="data04" title="成功随访的高危对象占成功预约人的百分比">
                                    <p class="figure"><span id="result_sfp" class="txt">0</span><span class="txtunit">%</span></p>
                                    <p>随访成功率</p>
                                </li> -->
                            </ul>
                        	    
                            </td>
                        </tr>
        				<tr>
        					<td>&nbsp;</td>
        					<td></td>
        					<td></td>
        					<td>&nbsp;</td>
        					<td></td>
        					<td></td>
        				</tr>
        				<tr>
        					<td colspan="3">
        					<ul class="data">
                                <li class="data01" title="在干预之后，成功预约随访（即同意进行随访）的高危对象人数">
                                    <p class="figure"><span id="result_FOLLOWVIEW" onclick="selPatient(4)" style="cursor: pointer;" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p>随访预约人数&nbsp;&nbsp;&nbsp;</p>
                                </li>
                                <li class="data02" title="成功随访的高危对象占成功预约人的百分比">
                                    <p class="figure"><span id="result_FOLLOWVIEW_PER" class="txt">0</span><span class="txtunit">%</span></p>
                                    <p>随访成功率</p>
                                </li>
                            </ul>
                            </td>
                            <td colspan="3">
                            <ul class="data">
                            	<li class="data03" title="完成随访的高危对象人数">
                                    <p class="figure"><span id="result_UQS6" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p>随访完成人数</p>
                                </li>
                                <!-- <li class="data03" title="在干预之后，成功预约随访（即同意进行随访）的高危对象人数">
                                    <p class="figure"><span id="result_order" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p>随访预约人数</p>
                                </li>
                                <li class="data04" title="成功随访的高危对象占成功预约人的百分比">
                                    <p class="figure"><span id="result_sfp" class="txt">0</span><span class="txtunit">%</span></p>
                                    <p>随访成功率</p>
                                </li> -->
                            </ul>
                        	    
                            </td>
                        </tr>
        				<!-- 
        				<tr>
        					<td><span class="txttitle">随访完成人数</span></td>
        					<td><span id="result_4" class="txt">0</span><span class="txtunit">人</span></td>
        					<td align="right" class="upArrow"><div  class="percentTxt">环比<br><span class="txt" id="tx_last_outflow_trend">0</span>%</div></td>
        				
        					<td>&nbsp;</td>
        					<td></td>
        					<td></td>
        				</tr> -->
        			</table>
        			
        			
        			       
            
</div>
<script type="text/javascript">

$(function(){
	$('#tx_prd_weight').html(Math.round(Math.random()*10000));
    $('#tx_inflow_weight').html(Math.round(Math.random()*100));
    $('#tx_outflow_weight').html(Math.round(Math.random()*1000));
    $('#tx_net_inflow_weight').html(Math.round(Math.random()*100));
    
    //$('#screenWidth').css({background:''});
});


var mc ;
var sttype = '2';
var type = '1';

function flushMap(){
	//alert(1);
	$.ajax({
		url : '${ctx}/progress/getProgressData2',
		type : 'POST',
		dataType : 'json',
		data : {stType:sttype,type:type}
	}).done(function (data, textStatus) {
		
		option.series[0].data = data ;
		
		mc.setOption(option, true);
	}).fail(function () {
		//alert('error');
	});	
}

function selPatient(type){
	var lccName=$('#txt_mapType').html();
	var url = "${ctx}/progress/topatientlistpage?type="+type+"&lccName="+lccName; 
    window.location.href=url;  
}

function flushNum(mt){
	$('.txt').html('0');
	$.ajax({
		url : '${ctx}/progress/getLccData2',
		type : 'POST',
		dataType : 'json',
		data : {mt:mt}
	}).done(function (data, textStatus) {
		
		if ( data ){
			for ( var i in data){
				$('#result_' + i ).html(data[i]);
			}
		}
		
		//option.series[0].data = data ;
		
		//mc.setOption(option, true);
	}).fail(function () {
		//alert('error');
	});	
}



var option = {
    	title : {
            text: 'CHINAPEACE3项目整体进度',
            subtext: '单位:人',
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: function(v) {
               /*  for (var i in v[5] ){
                	alert( i  + ":" + v[5][i]);
                } */
                if ( v[1] != '浙江' && v[1] != '吉林' && v[1] != '广西' && v[1] != '辽宁' )
                	return '非项目开展地区';
                
                var cs =  v[2];
                if ( !cs || cs == '-')
                	cs = 0;
                var gw = v[5]['UQS34'];
                if (!gw )
                	gw = 0 ;
                var sf = v[5]['UQS6'];
                if ( !sf )
                	sf = 0;
                return v[1] + '<br>初筛完成人数:&nbsp;' + cs + '人<br>高危完成人数:&nbsp;' + gw + '人<br>随访完成人数:&nbsp;' +sf + '人' ;
            }
        },
        dataRange: {
            min: 0,
            max: 100000,
            color: ['lightgreen','red'],
            calculable : false
        },
        series : [
            {
                name: 'CHINAPEACE3项目整体进度',
                type: 'map',
                mapType: 'china',
                selectedMode : 'single',
                itemStyle:{
                    normal:{label:{show:true}},
                    emphasis:{label:{show:true}}
                },
                data:[


						
                     ]
            }
        ]
    };

var option2 = {
	    title : {
	        text: '',
	        subtext: ''
	    },
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
	        data:['初筛人数','高危人数']
	    },
	    toolbox: {
	        show : false,
	        feature : {
	            mark : {show: true},
	            dataView : {show: true, readOnly: false},
	            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
	            restore : {show: true},
	            saveAsImage : {show: true}
	        }
	    },
	    calculable : true,
	    xAxis : [
	        {
	            type : 'category',
	            boundaryGap : false,
	            data : ['1周','2周','3周','4周','5周','6周','7周']
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'初筛人数',
	            type:'line',
	            smooth:true,
	            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[200, 240, 350, 500, 650 , 740 , 810]
	        },
	        {
	            name:'高危人数',
	            type:'line',
	            smooth:true,
	            itemStyle: {normal: {areaStyle: {type: 'default'}}},
	            data:[30, 56, 80, 151, 220, 230, 300]
	        }
	    ]
	};
    
//var zrEvent = require('zrender/tool/event');
var curIndx = 0;
var mapType = [
'china',
// 23个省
//'广东', '青海', '四川', '海南', '陕西', 
//'甘肃', '云南', '湖南', '湖北', '黑龙江',
'贵州', '山东', '江西', '河南', '河北',
'山西', '安徽', '福建', '浙江', '江苏', 
'吉林', '辽宁', '台湾'
// 5个自治区
//'新疆', '广西', '宁夏', '内蒙古', '西藏', 
// 4个直辖市
//'北京', '天津', '上海', '重庆',
// 2个特别行政区
//'香港', '澳门'
];


require.config({
paths:{ 
echarts:'${ctx}/static/echarts/echarts',
'echarts/chart/bar' : '.${ctx}/static/echarts/echarts',
'echarts/chart/line': '${ctx}/static/echarts/echarts',
'echarts/chart/map' : './js/echarts-map'
}
});

require(
[
'echarts',
'echarts/chart/bar',
'echarts/chart/line',
'echarts/chart/map'
],
function (ec) {
// --- 地图 ---
var myChart2 = ec.init(document.getElementById('main'));
mc = myChart2;
myChart2.setOption(option, true);

flushMap();
flushNum('china');

var ecConfig = require('echarts/config');
myChart2.on(ecConfig.EVENT.HOVER, function (param){
	for ( var i in param ){
		//alert( i + ':' + param [i]);
	}
	
});

myChart2.on(ecConfig.EVENT.MAP_SELECTED, function (param){
	
    var len = mapType.length;
    var mt = mapType[curIndx % len];
    //if (mt == 'china') {
        // 全国选择时指定到选中的省份
        var selected = param.selected;
        for (var i in selected) {
        	
            if (selected[i] && ( i == '浙江' || i == '吉林' || i == '广西' || i == '辽宁' )) {
            	$('#txt_mapType').html(i);
                mt = i;
                while (len--) {
                    if (mapType[len] == mt) {
                        curIndx = len;
                    }
                }
                break;
            }else{
            	mt="china";
            	$('#txt_mapType').html('全国');
            }
        }
        
    //}
    /* else {
        curIndx = 0;
        mt = 'china';
        $('#txt_mapType').html('全国');
    } */
   
    //option.series[0].mapType = mt;
    //myChart2.setOption(option, true);
    mmt = mt;
    flushNum(mt);
    
    //-----------------------
    $('#tx_prd_weight').html(Math.round(Math.random()*10000));
    $('#tx_inflow_weight').html(Math.round(Math.random()*100));
    $('#tx_outflow_weight').html(Math.round(Math.random()*1000));
    $('#tx_net_inflow_weight').html(Math.round(Math.random()*100));
});
/*
myChart2.on(ecConfig.EVENT.CLICK, function (param){
				
				if ( param['name'] )
        getMmDetail(param['name']);
});
*/
//mapChart = myChart2;

//-----------------line


//var myChart3 = ec.init(document.getElementById('line'));
//myChart3.setOption(option2, true);

// -----------------end line

}
);

</script>


</body>



</html>