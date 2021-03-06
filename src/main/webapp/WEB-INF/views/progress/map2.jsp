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
          <li class="active">项目点进度</li>
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
                                    <p class="figure"><span id="result_PATIENT" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p >上报花名册人数</p>
                                </li>
                                <li class="data02" title="已完成初筛的人数">
                                    <p class="figure"><span id="result_UQS2" class="txt">0</span><span class="txtunit">人</span></p>
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
                                    <p class="figure"><span id="result_UQS34" class="txt">0</span><span class="txtunit">人</span></p>
                                    <p>完成高危人数</p>
                                </li>
                            </ul>
                            </td>
                            <td colspan="3">
                            <ul class="data">
                            	<li class="data03" title="检出的高危对象人数占所有已完成初筛人数的百分比">
                                    <p class="figure"><span id="result_UQS5" class="txt">0</span></p>
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
                                    <p class="figure"><span id="result_FOLLOWVIEW" class="txt">0</span><span class="txtunit">人</span></p>
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

var lccCache = {};
var mc ;

function flushLcc(lcc){
	$('.txt').html('0');
	$('#txt_mapType').html(lcc);
	if ( lccCache && lccCache[lcc] ){ 
		for ( var i in lccCache[lcc] ){//alert(i + ':' + lccCache[lcc][i]);
			$('#result_' + i ).html(lccCache[lcc][i]);
		}
	}else{
		//$('#txt_mapType').html("全国");
		//flushNum('china');
	}
}

$(function(){
	
	flushNum('china');
	
	
	$('#tx_prd_weight').html(Math.round(Math.random()*10000));
    $('#tx_inflow_weight').html(Math.round(Math.random()*100));
    $('#tx_outflow_weight').html(Math.round(Math.random()*1000));
    $('#tx_net_inflow_weight').html(Math.round(Math.random()*100));
    
    //$('#screenWidth').css({background:''});
});


var option = {
    	title : {
            text: 'CHINAPEACE3项目项目点初筛完成人数',
            subtext: '单位:人',
            x:'center'
        },
       /*  tooltip : {
            trigger: 'item',
            formatter: function(v) {
                return v[1] + '<br>已完成:&nbsp;' + v[2] + '人';
            }
        }, */
        tooltip : {
            trigger: 'item',
            formatter: function(v) {
               /*  for (var i in v[5] ){
                	alert( i  + ":" + v[5][i]);
                } */
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
            min : 0, 
            max : 20000,
            calculable : true,
            color: ['lightgreen','yellow','orange','red']
        },
        series : [
            {
                name: 'CHINAPEACE3项目项目点初筛完成人数',
                type: 'map',
                mapType: 'china',
                hoverable: false,
                roam:true,
                data:[],
                markPoint : {
                    symbolSize: 5,       // 标注大小，半宽（半径）参数，当图形为方向或菱形则总宽度为symbolSize * 2
                    itemStyle: {
                        normal: {
                            borderColor: '#87cefa',
                            borderWidth: 1,            // 标注边线线宽，单位px，默认为1
                            label: {
                                show: false
                            }
                        },
                        emphasis: {
                            borderColor: '#1e90ff',
                            borderWidth: 5,
                            label: {
                                show: false
                            }
                        }
                    },
                    data : [
                        
                       /*  {name: "丽水市中心医院", value: 312},
                        {name: "辽源市第二人民医院", value: 231},
                        {name: "诸暨市人民医院", value: 433},
                        {name: "宽甸满族自治县中心医院", value: 235},
                        {name: "南宁", value: 437},
                        {name: "灯塔市中心医院", value: 337},
                        {name: "盘锦市中心医院", value: 240},
                        {name: "金州新区医院", value: 447},
                        {name: "辽宁省人民医院", value: 250},
                       
                        {name: "吉林大学第一医院", value: 351},
                        {name: "延吉市中医院", value: 454},
                        {name: "柳州", value: 254},
                        {name: "吉林市中心医院", value: 356},
                        {name: "桂林", value: 359},
                       
                        {name: "通化县医院", value: 371},
                        {name: "昌化人民医院", value: 384},
                        {name: "玉环县人民医院", value: 32},
                        {name: "义乌市北苑医院", value: 457} */
                    ]
                },
                geoCoord: {
                	"南宁市城区":[108.20,22.50],
                	"柳州市城区-柳钢":[109.24,24.20],	
                	"柳州市柳江县":[109.19,24.15],		
                	"南宁市横县":[109.12,22.41],		
                	"南宁市武鸣县":[108.16,23.19],		
                	
                	"沈阳市沈河区":[123.38,41.8],
                	"大连市经济技术开发区":[121.62,38.92],
                	"盘锦市":[122.070714,41.119997],
                	"丹东市宽甸满族自治县":[124.37,40.13],	
                	"辽阳市灯塔市":[123.34,41.43],
                		
                    "丽水市庆元县":[119.92,28.45],
                    "绍兴市诸暨市":[120.23,29.71],
                    "杭州市临安市昌化县":[120.19,30.26],
                    "台州市玉环县":[121.420757,28.656386],
                    "金华市义乌市":[120.06,29.32],
                	
                    "延边朝鲜族自治州延吉市":[129.52,42.93],
                    "辽源市":[125.15,42.97],
                    "通化市通化县":[125.92,41.49],
                    "长春市":[125.35,43.88],
                    "吉林市":[126.57,43.87],
                	
                	"广西医科大学第一附属医院":[108.20,22.50],		
                	"柳州市钢铁(集团)公司职工医院":[109.24,24.20],		
                	"柳江县人民医院":[109.19,24.15],		
                	"横县人民医院":[109.12,22.41],		
                	"武鸣县人民医院":[108.16,23.19],		
                	
                	
                	 "延吉市中医院":[129.52,42.93],
                	 "国家卫生计生委疾病预防控制局":[116.23,39.54],
                	 "辽源市第二人民医院":[125.15,42.97],
                	 "通化县医院":[125.92,41.49],		
                    "海门":[121.15,31.89],
                    "鄂尔多斯":[109.781327,39.608266],
                    "招远":[120.38,37.35],
                    "舟山":[122.207216,29.985295],
                    "齐齐哈尔":[123.97,47.33],
                    "盐城":[120.13,33.38],
                    "赤峰":[118.87,42.28],
                    "青岛":[120.33,36.07],
                    "乳山":[121.52,36.89],
                    "金昌":[102.188043,38.520089],
                    "泉州":[118.58,24.93],
                    "莱西":[120.53,36.86],
                    "日照":[119.46,35.42],
                    "胶南":[119.97,35.88],
                    "南通":[121.05,32.08],
                    "拉萨":[91.11,29.97],
                    "云浮":[112.02,22.93],
                    "梅州":[116.1,24.55],
                    "文登":[122.05,37.2],
                    "上海":[121.48,31.22],
                    "攀枝花":[101.718637,26.582347],
                    "威海":[122.1,37.5],
                    "承德":[117.93,40.97],
                    "厦门":[118.1,24.46],
                    "汕尾":[115.375279,22.786211],
                    "潮州":[116.63,23.68],
                    "宽甸满族自治县中心医院":[124.37,40.13],
                    "太仓":[121.1,31.45],
                    "曲靖":[103.79,25.51],
                    "烟台":[121.39,37.52],
                    "福州":[119.3,26.08],
                    "瓦房店":[121.979603,39.627114],
                    "即墨":[120.45,36.38],
                    "抚顺":[123.97,41.97],
                    "玉溪":[102.52,24.35],
                    "张家口":[114.87,40.82],
                    "阳泉":[113.57,37.85],
                    "莱州":[119.942327,37.177017],
                    "湖州":[120.1,30.86],
                    "汕头":[116.69,23.39],
                    "昆山":[120.95,31.39],
                    "宁波":[121.56,29.86],
                    "湛江":[110.359377,21.270708],
                    "揭阳":[116.35,23.55],
                    "荣成":[122.41,37.16],
                    "连云港":[119.16,34.59],
                    "葫芦岛":[120.836932,40.711052],
                    "常熟":[120.74,31.64],
                    "东莞":[113.75,23.04],
                    "河源":[114.68,23.73],
                    "淮安":[119.15,33.5],
                    "泰州":[119.9,32.49],
                    "南宁":[108.33,22.84],
                    "营口":[122.18,40.65],
                    "惠州":[114.4,23.09],
                    "江阴":[120.26,31.91],
                    "蓬莱":[120.75,37.8],
                    "韶关":[113.62,24.84],
                    "嘉峪关":[98.289152,39.77313],
                    "广州":[113.23,23.16],
                    "延安":[109.47,36.6],
                    "太原":[112.53,37.87],
                    "清远":[113.01,23.7],
                    "中山":[113.38,22.52],
                    "昆明":[102.73,25.04],
                    "寿光":[118.73,36.86],
                    "盘锦市中心医院":[122.070714,41.119997],
                    "长治":[113.08,36.18],
                    "深圳":[114.07,22.62],
                    "珠海":[113.52,22.3],
                    "宿迁":[118.3,33.96],
                    "咸阳":[108.72,34.36],
                    "铜川":[109.11,35.09],
                    "平度":[119.97,36.77],
                    "佛山":[113.11,23.05],
                    "海口":[110.35,20.02],
                    "江门":[113.06,22.61],
                    "章丘":[117.53,36.72],
                    "肇庆":[112.44,23.05],
                    "金州新区医院":[121.62,38.92],
                    "临汾":[111.5,36.08],
                    "吴江":[120.63,31.16],
                    "石嘴山":[106.39,39.04],
                    "辽宁省人民医院":[123.38,41.8],
                    "灯塔市中医医院":[123.34,41.43],
                    "苏州":[120.62,31.32],
                    "茂名":[110.88,21.68],
                    "嘉兴":[120.76,30.77],
                    "吉林大学第一医院":[125.35,43.88],
                    "胶州":[120.03336,36.264622],
                    "银川":[106.27,38.47],
                    "张家港":[120.555821,31.875428],
                    "三门峡":[111.19,34.76],
                    "锦州":[121.15,41.13],
                    "南昌":[115.89,28.68],
                    "柳州":[109.4,24.33],
                    "三亚":[109.511909,18.252847],
                    "自贡":[104.778442,29.33903],
                    "吉林市中心医院":[126.57,43.87],
                    "阳江":[111.95,21.85],
                    "泸州":[105.39,28.91],
                    "西宁":[101.74,36.56],
                    "宜宾":[104.56,29.77],
                    "呼和浩特":[111.65,40.82],
                    "成都":[104.06,30.67],
                    "大同":[113.3,40.12],
                    "镇江":[119.44,32.2],
                    "桂林":[110.28,25.29],
                    "张家界":[110.479191,29.117096],
                    "宜兴":[119.82,31.36],
                    "北海":[109.12,21.49],
                    "西安":[108.95,34.27],
                    "金坛":[119.56,31.74],
                    "东营":[118.49,37.46],
                    "牡丹江":[129.58,44.6],
                    "遵义":[106.9,27.7],
                    "绍兴":[120.58,30.01],
                    "扬州":[119.42,32.39],
                    "常州":[119.95,31.79],
                    "潍坊":[119.1,36.62],
                    "重庆":[106.54,29.59],
                    "玉环县人民医院":[121.420757,28.656386],
                    "南京":[118.78,32.04],
                    "滨州":[118.03,37.36],
                    "贵阳":[106.71,26.57],
                    "无锡":[120.29,31.59],
                    "本溪":[123.73,41.3],
                    "克拉玛依":[84.77,45.59],
                    "渭南":[109.5,34.52],
                    "马鞍山":[118.48,31.56],
                    "宝鸡":[107.15,34.38],
                    "焦作":[113.21,35.24],
                    "句容":[119.16,31.95],
                    "北京":[116.46,39.92],
                    "徐州":[117.2,34.26],
                    "衡水":[115.72,37.72],
                    "包头":[110,40.58],
                    "绵阳":[104.73,31.48],
                    "乌鲁木齐":[87.68,43.77],
                    "枣庄":[117.57,34.86],
                    "昌化人民医院":[120.19,30.26],
                    "淄博":[118.05,36.78],
                    "鞍山":[122.85,41.12],
                    "溧阳":[119.48,31.43],
                    "库尔勒":[86.06,41.68],
                    "安阳":[114.35,36.1],
                    "开封":[114.35,34.79],
                    "济南":[117,36.65],
                    "德阳":[104.37,31.13],
                    "温州":[120.65,28.01],
                    "九江":[115.97,29.71],
                    "邯郸":[114.47,36.6],
                    "临安":[119.72,30.23],
                    "兰州":[103.73,36.03],
                    "沧州":[116.83,38.33],
                    "临沂":[118.35,35.05],
                    "南充":[106.110698,30.837793],
                    "天津":[117.2,39.13],
                    "富阳":[119.95,30.07],
                    "泰安":[117.13,36.18],
                    "诸暨市人民医院":[120.23,29.71],
                    "郑州":[113.65,34.76],
                    "哈尔滨":[126.63,45.75],
                    "聊城":[115.97,36.45],
                    "芜湖":[118.38,31.33],
                    "唐山":[118.02,39.63],
                    "平顶山":[113.29,33.75],
                    "邢台":[114.48,37.05],
                    "德州":[116.29,37.45],
                    "济宁":[116.59,35.38],
                    "荆州":[112.239741,30.335165],
                    "宜昌":[111.3,30.7],
                    "义乌市北苑医院":[120.06,29.32],
                    "丽水市中心医院":[119.92,28.45],
                    "洛阳":[112.44,34.7],
                    "秦皇岛":[119.57,39.95],
                    "株洲":[113.16,27.83],
                    "石家庄":[114.48,38.03],
                    "莱芜":[117.67,36.19],
                    "常德":[111.69,29.05],
                    "保定":[115.48,38.85],
                    "湘潭":[112.91,27.87],
                    "金华":[119.64,29.12],
                    "岳阳":[113.09,29.37],
                    "长沙":[113,28.21],
                    "衢州":[118.88,28.97],
                    "廊坊":[116.7,39.53],
                    "菏泽":[115.480656,35.23375],
                    "合肥":[117.27,31.86],
                    "武汉":[114.31,30.52],
                    "大庆":[125.03,46.58]
                }
            },
            
            {
                name: '进度落后',
                type: 'map',
                mapType: 'china',
                data:[],
                markPoint : {
                    symbol:'emptyCircle',
                    symbolSize : function (v){
                        return 10 + v/100
                    },
                    effect : {
                        show: true,
                        shadowBlur : 0
                    },
                    itemStyle:{
                        normal:{
                            label:{show:false}
                        }
                    },
                    data : [
                        //{name: "玉环县人民医院", value: 32}
                    ]
                }
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
'广东', '青海', '四川', '海南', '陕西', 
'甘肃', '云南', '湖南', '湖北', '黑龙江',
'贵州', '山东', '江西', '河南', '河北',
'山西', '安徽', '福建', '浙江', '江苏', 
'吉林', '辽宁', '台湾',
// 5个自治区
'新疆', '广西', '宁夏', '内蒙古', '西藏', 
// 4个直辖市
'北京', '天津', '上海', '重庆',
// 2个特别行政区
'香港', '澳门'
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
myChart2.setOption(option, true);
var ecConfig = require('echarts/config');

mc = myChart2;

$.ajax({
	url : '${ctx}/progress/getLccDetails2',
	type : 'POST',
	dataType : 'json',
	data : {}
}).done(function (data, textStatus) {
	
	if ( data ){
		lccCache = data;
		var arr = new Array();
		for ( var n in data ){
			arr.push(data[n]);
		}
		option.series[0].markPoint.data = arr;
		myChart2.setOption(option, true);
	}
	
	
}).fail(function () {
	//alert('error');
});	


myChart2.on(ecConfig.EVENT.CLICK, function (param){
	
    //var len = mapType.length;
    
    flushLcc(param['name']);
    for ( var ii in param ){
    	//alert(ii + ':' + param[ii]);
    }
    
    //var mt = mapType[curIndx % len];
    /* if (mt == 'china') {
        // 全国选择时指定到选中的省份
        var selected = param.selected;
        for (var i in selected) {
        	
            if (selected[i]) {
            	
                mt = i;
                while (len--) {
                    if (mapType[len] == mt) {
                        curIndx = len;
                    }
                }
                break;
            }
        }
        
    }
    else {
        curIndx = 0;
        mt = 'china';
        $('#txt_mapType').html('全国');
    }
    option.series[0].mapType = mt;
    
    myChart2.setOption(option, true);
    mmt = mt; */
    
    
    //-----------------------
    /* $('#tx_prd_weight').html(Math.round(Math.random()*10000));
    $('#tx_inflow_weight').html(Math.round(Math.random()*100));
    $('#tx_outflow_weight').html(Math.round(Math.random()*1000));
    $('#tx_net_inflow_weight').html(Math.round(Math.random()*100)); */
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

<script type="text/javascript">
	


	


     
</script>

</body>



</html>