package com.bdcor.pip.web.reserve.service.impl;

import com.alibaba.druid.wall.WallProvider;
import com.alibaba.druid.wall.spi.MySqlWallProvider;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.PipCommPatientKey;
import com.bdcor.pip.web.reserve.controller.ReserveController;
import com.bdcor.pip.web.reserve.dao.PipCommReserveDetailMapper;
import com.bdcor.pip.web.reserve.dao.PipCommReserveMapper;
import com.bdcor.pip.web.reserve.domain.PipCommReserve;
import com.bdcor.pip.web.reserve.domain.PipCommReserveDetail;
import com.bdcor.pip.web.reserve.domain.ReserveResult;
import com.bdcor.pip.web.reserve.filter.PipCommReserveDetailFilter;
import com.bdcor.pip.web.reserve.filter.PipCommReserveFilter;
import com.bdcor.pip.web.reserve.service.PipCommReserveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class PipCommReserveServiceImpl implements PipCommReserveService {
	public static final Logger log = LoggerFactory.getLogger(PipCommReserveServiceImpl.class);

	@Autowired
	PipCommReserveMapper pipCommReserveMapper;
	@Autowired
	PipCommReserveDetailMapper pipCommReserveDetailMapper;
	@Autowired
	PipCommPatientMapper pipCommPatientMapper;

	private String status="1";
	//患者的最近一次预约列表
		public List<PipCommReserve> getPipCommReserveList(PipCommReserveFilter filter){
			return pipCommReserveMapper.getPipCommReserveList(filter);
		}

	//患者的详细信息
	   public PipCommPatient selectByPrimaryKey(PipCommPatientKey key){
		return  pipCommPatientMapper.selectByPrimaryKey(key);
	   }

	//某一个患者的所有记录
	   public List<PipCommReserve> selectPipCommReserveListForPatientId(PipCommReserve pcr){
			return  pipCommReserveMapper.selectPipCommReserveListForPatientId(pcr);
       }

		public void  updatePipCommReserve(PipCommReserve pcr){

			  pipCommReserveMapper.updatePipCommReserve(pcr);
		}

		public Boolean  saveReserveDetailUpdateReserve(PipCommReserveDetailFilter pcdf){
			Boolean bl = false;
			Map<String, Object> res = new HashMap<String, Object>();
			try {
				PipCommReserveDetail pcrd = new PipCommReserveDetail();
				pcrd.setPatientId(pcdf.getPatientId());
				pcrd.setVersion(pcdf.getMaxVersion());
				pcrd.setChangeComeDate(pcdf.getChangeComeDate());

				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				// formatter.format(new Date(changeComeDate));
				pcrd.setDocTime(new Date());
				res.put("success", true);
				pcrd.setPlanTime(formatter.parse(pcdf.getChangeComeDate()));
				pcrd.setResultCode(pcdf.getResultCode());
				if(pcdf.getResultCode()!=null && !"".equals(pcdf.getResultCode())){
				pcrd.setResult(ReserveController.getResult(Integer.parseInt(pcdf.getResultCode())));
				}else{
					pcrd.setResult("其他");
				}
				pcrd.setRemark(pcdf.getRemark());
				pipCommReserveDetailMapper.savePipCommReserveDetail(pcrd);

				//修改一下 新的计划日期
				PipCommReserve pcr = new PipCommReserve();
				pcr.setVersion(pcdf.getMaxVersion());
				pcr.setPatientId(pcdf.getPatientId());
				pcr.setPlanTime(formatter.parse(pcdf.getChangeComeDate()));
				pcr.setResult(pcrd.getResult() );
				pcr.setvTime(formatter.parse(pcdf.getMaxVTime()));
				pcr.setRemark( pcdf.getRemark());
				if(pcdf.getRealTime2() != null && !"".equals(pcdf.getRealTime2())){
					pcr.setRealTime(formatter.parse(pcdf.getRealTime2()));
				}else{
					pcr.setRealTime(null);
				}
				getStatus(pcr.getPlanTime(),pcr.getvTime(),pcr.getRealTime());
				pcr.setStatus(status);
				pipCommReserveMapper.updatePipCommReserve(pcr);

				bl = true;
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return bl;
		}

		public void getStatus(Date planTime, Date vTime,Date realTime) throws ParseException{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Calendar today = Calendar.getInstance();

		     if(realTime!=null ){
		    	 status="1";
		    	 return ;
		     }

		     Calendar vTimeDate = Calendar.getInstance();
		     Calendar vTime30Date = Calendar.getInstance();
		     Calendar vTime90Date = Calendar.getInstance();  //负方向 -90天
		     Calendar vTimez90Date = Calendar.getInstance(); //正方向90天
		     vTimeDate.setTime(vTime);

		     vTime30Date.setTime(vTime);
		     vTime30Date.add(Calendar.DATE, 30);

		     vTime90Date.setTime(vTime);
		     vTime90Date.add(Calendar.DATE, -90);

		     vTimez90Date.setTime(vTime);
		     vTimez90Date.add(Calendar.DATE, 90);


		     Calendar planTimeDate = Calendar.getInstance();
		     planTimeDate.setTime(planTime);

		     Calendar todayTimeDate = Calendar.getInstance();

		     todayTimeDate.setTime(formatter.parse( new SimpleDateFormat("yyyy-MM-dd").format(new Date()) ) ) ;

		     //计划时间在       vTime的 -90 至 30天之间时
		     if(planTimeDate.getTimeInMillis()-vTime90Date.getTimeInMillis() >=0 && vTime30Date.getTimeInMillis()- planTimeDate.getTimeInMillis()>=0 ){

		    	 if( planTimeDate.getTimeInMillis() - todayTimeDate.getTimeInMillis() >=0 &&   vTime30Date.getTimeInMillis()- todayTimeDate.getTimeInMillis() >=0 &&  vTime30Date.getTimeInMillis()- planTimeDate.getTimeInMillis() >=0  ){
		    		 status="1";
			    	 return ;
		    	 }

		    	 if( todayTimeDate.getTimeInMillis()-planTimeDate.getTimeInMillis()  >0 &&   vTime30Date.getTimeInMillis()- planTimeDate.getTimeInMillis() >=0  ){
		    		 status="3";
			    	 return ;
		    	 }

		     }

		   //计划时间在       vTime的 30 至 90天之间时
              if(planTimeDate.getTimeInMillis()-vTime30Date.getTimeInMillis() >=0 &&  vTimez90Date.getTimeInMillis() -planTimeDate.getTimeInMillis()>=0 ){

		    	if(planTimeDate.getTimeInMillis()-todayTimeDate.getTimeInMillis() >0 && vTimez90Date.getTimeInMillis()-planTimeDate.getTimeInMillis()>=0 ){
		    		 status="2";
			    	 return ;
		    	}

		    	if(todayTimeDate.getTimeInMillis()-planTimeDate.getTimeInMillis() >0 && vTimez90Date.getTimeInMillis()-planTimeDate.getTimeInMillis()>=0 ){
		    		 status="3";
			    	 return ;
		    	}

		     }
		}

		public void outDay(){
			pipCommReserveMapper.updatePipCommReserveForQuartzYellow();
			pipCommReserveMapper.updatePipCommReserveForQuartzRed();
			//检查大于本次计划日期了  实际检查日期为空     就去新生成一条记录
			pipCommReserveMapper.creatPipCommReserveForQuartzRed();


		}

		public  List<ReserveResult> reserveResult(Map<String, String> map){

			return pipCommReserveMapper.reserveResult(map);
		}


// 以下内容 edit by zhang.rw  20160726
	public  List<Map<String,String>> getGroupData(String year, String month,String ismsg  ){
        String[] strArr = getDateArr(year,month);
        return getGroupdata(strArr,ismsg );
    }

    public List<Map<String,String>> getGroupData(int year, int month ,String ismsg  ){
        String[] strArr = getDateArr(year,month);
        return getGroupdata(strArr,ismsg );
    }

    public  List<Map<String,String>> getGroupdata(String[] strArr ,String ismsg ){
        //   设置查询参数
        Map<String,String> map = new HashMap<String, String>();
        map.put("startdate",strArr[0]);
        map.put("enddate",strArr[1]);
        map.put("userid",Securitys.getUserId());
        map.put("ismsg",ismsg);
        List<Map<String,String>> list = pipCommReserveMapper.getGroupData(map);
        return list;
    }


    public String[] getDateArr( String year , String month ){
        int y = Integer.parseInt(year);
        int m = Integer.parseInt(month);
        return getDateArr(y,m);
    }

    public String[] getDateArr( int y , int m ){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR , y);
        cal.set(Calendar.MONTH, m -1);
        cal.set(Calendar.DAY_OF_MONTH,1);
        cal.setFirstDayOfWeek(Calendar.MONDAY); // 前后台之间要一致 设置周一为一周第一天
        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek()); // 周一
//        cal.get(Calendar.YEAR);
//        cal.get(Calendar.MONTH)+1;
//        cal.get(Calendar.DAY_OF_MONTH)
        cal.add(Calendar.DATE , -6);
        log.info("获取到日历开始时间为："+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)
                +"-"+cal.get(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startdate = sdf.format(new Date(cal.getTimeInMillis()));

        // 当月最后一天所在周到周末一天的日期
//        cal.set(Calendar.YEAR , y);
//        cal.set(Calendar.MONTH, m -1);
//        cal.set(Calendar.DAY_OF_MONTH , cal.getActualMaximum(Calendar.DAY_OF_MONTH));
//        cal.set(Calendar.WEEK_OF_YEAR ,cal.get(Calendar.WEEK_OF_YEAR));
//        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 周一
//        cal.add(Calendar.DATE , 6);

        // 日历第一天+42天
        cal.set(Calendar.DATE ,( cal.get(Calendar.DATE)+48));
        String enddate = sdf.format(new Date(cal.getTimeInMillis()));
        log.info("获取到日历结束时间为："+cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.MONTH)+1)
                +"-"+cal.get(Calendar.DAY_OF_MONTH));
        return new String[]{startdate,enddate};
    }


    public List<Map<String,String>> getDataInfo( PipCommReserveFilter filter){
        return pipCommReserveMapper.getDataInfo(filter);
    }

    public int updateViewDate(String pid ,String newdate){
        try {
            Map<String,String> column = pipCommReserveMapper.getViewColumn(pid);
            WallProvider provider = new MySqlWallProvider();
            provider.getConfig().setStrictSyntaxCheck(false);
            int i = 0 ;
            i = pipCommReserveMapper.updatePlandate(column.get("VIEWNAME") , newdate , pid );
            provider.getConfig().setStrictSyntaxCheck(true);
            return i;
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            return 0;
        }
    }
}
