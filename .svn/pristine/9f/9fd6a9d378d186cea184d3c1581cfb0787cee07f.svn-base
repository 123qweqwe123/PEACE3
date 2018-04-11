/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.service.impl 
 */

package com.bdcor.pip.web.msg.service.impl;

import com.alibaba.fastjson.JSON;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.data.dao.PipCommPatientDateMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.PipCommPatientDate;
import com.bdcor.pip.web.data.domain.PipCommPatientDateKey;
import com.bdcor.pip.web.msg.dao.MsgSendDao;
import com.bdcor.pip.web.msg.dao.PipMsgMsgtmpMapper;
import com.bdcor.pip.web.msg.dao.PipMsgSendMapper;
import com.bdcor.pip.web.msg.dao.PipMsgTempMapper;
import com.bdcor.pip.web.msg.domain.*;
import com.bdcor.pip.web.msg.domain.PipMsgTempExample.Criteria;
import com.bdcor.pip.web.msg.service.MsgSendRuleService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * description:
 * 
 * @author yangfeng 创建时间：2016年5月11日
 */
@Service
public class MsgSendRuleServiceImpl implements MsgSendRuleService
{
	@Value("${msg_map}")
	public String msg_map;

    final Logger log = LoggerFactory.getLogger(this.getClass());


    public static final Date TIME_D[] = new  Date[4];
	public final static String NAME_="XXX";
	public final static String MOUTH_="trial_month";
	// 存放各类短信
	static Map<String , List<PipMsgMsgtmp>> jcjylList  =new HashMap<String , List<PipMsgMsgtmp>>();
	static Map<String , List<PipMsgMsgtmp>> yyyclList = new HashMap<String , List<PipMsgMsgtmp>>();
	static Map<String , List<PipMsgMsgtmp>> gxylList = new HashMap<String , List<PipMsgMsgtmp>>();
	static Map<String , List<PipMsgMsgtmp>> ydlList = new HashMap<String , List<PipMsgMsgtmp>>();
	static Map<String , List<PipMsgMsgtmp>> jylList = new HashMap<String , List<PipMsgMsgtmp>>();
	static Map<String , List<PipMsgMsgtmp>> hycjyjlList = new HashMap<String , List<PipMsgMsgtmp>>();
	static Map<String , List<PipMsgMsgtmp>> sftxlList = new HashMap<String , List<PipMsgMsgtmp>>();
	static Map<String , List<PipMsgMsgtmp>> srzflList = new HashMap<String , List<PipMsgMsgtmp>>();
	static Map<String , List<PipMsgMsgtmp>> dzList = new HashMap<String , List<PipMsgMsgtmp>>();

	//后缀标签
	static String[] suffixFlag= {"您今天吃药了？是请回复1，否请回复2!","最近，您测量血压了吗？是请回复3，否请回复4"};
	
	@Autowired
	private PipMsgMsgtmpMapper pipMsgMsgtmpMapper;
	@Autowired
	private PipMsgSendMapper pipMsgSendMapper;
	@Autowired
	private PipCommPatientMapper pipCommPatientMapper;
	@Autowired
	private PipCommPatientDateMapper pipCommPatientDateMapper;
	@Autowired
	private PipMsgTempMapper pipMsgTempMapper;
    @Autowired
    private MsgSendDao msgSendDao;
    @Autowired
    private PipCommPatientDateMapper patientDateMapper;
	/**
	 * 
	 * description:  初始化当天随机时间的数据
	 * @author yangfeng     
	 * @update 2016年5月16日
	 */
	private void initDate(){
			Date date1 = new Date();
			date1.setHours(9);
			date1.setMinutes(0);
			TIME_D[0]=date1;
			Date date2 = new Date();
			date2.setHours(12);
			date2.setMinutes(0);
			TIME_D[1]=date2;
			Date date3 = new Date();
			date3.setHours(15);
			date3.setMinutes(0);
			TIME_D[2]=date3;
			Date date4 = new Date();
			date4.setHours(20);
			date4.setMinutes(0);
			TIME_D[3]=date4;
	}

	@Override
	public void needSendMsg()
	{
        log.info("获取类别配置信息："+ msg_map);
//        Map<String,String > m = (Map<String,String >)JSON.parse(msg_map);
        //初始化时间
		initDate();

		//对照组里填充数据
		initRandomDate();
		//监测各个患者随访日期是否到达6个月 
		checkAndFunctionPatient();
		// 发送杂类信息   包括 生日  欢迎参加短信
		List<PipCommPatient> patients = pipCommPatientMapper.selectPatientNeedSendMsg();
        createMsgForPid(patients);
	}

    public void createMsgForPid(List<PipCommPatient> patients){
        Random random = new Random();
        for(PipCommPatient temp:patients){
            try
            {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String nowDate = format.format(new Date());
                Date birthday = temp.getBirthday();
                //随访提醒的逻辑
                if(sftxlList.get(temp.getBelongGroup()) != null && sftxlList.get(temp.getBelongGroup()).size()>0){
                    PipCommPatientDateKey key =new PipCommPatientDateKey();
                    key.setPatientId(temp.getPatientId());
                    key.setProjectId("004");
                    PipCommPatientDate pipCommPatientDate = pipCommPatientDateMapper.selectByPrimaryKey(key);
                    if(pipCommPatientDate!=null){
                        log.info("进入随访提醒短信逻辑");
                        randomMsgInsert(random, temp, format, nowDate, pipCommPatientDate);
                    }
                }
                //生日发送短信的逻辑
                if(srzflList.get(temp.getBelongGroup())!= null && srzflList.get(temp.getBelongGroup()).size()>0){
                    if(birthday !=null){
                        log.info("进入生日祝福短信逻辑");
                        birthdayMsgInsert(random, temp, birthday);
                    }
                }
            } catch (Exception e)
            {
                e.printStackTrace();
                log.info("捕获异常之后continue，继续循环。。。");
                continue;
            }
        }

        Calendar nowTime = Calendar.getInstance();

        log.info("查看当天周几：Calendar.get(Calendar.DAY_OF_WEEK)值为："+nowTime.get(Calendar.DAY_OF_WEEK));

        //周一不发送短信
        if (nowTime.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY)
        {
            //初始化短信 为每个人调用一次方法
            log.info("初始化短信逻辑，填充短信到预发送表");
            initMsgForEveryPatient();
            return;
        }

        boolean b;
        for (PipCommPatient patient : patients)
        {
            // 对照组 跟实验组发送短信不一样 1代表对照组，2代表实验组
            // 01:非糖尿病对照组，02:非糖尿病实验组 ,11:糖尿病对照组，12：糖尿病实验组
            if ("2".equals(patient.getBelongGroup().substring(1,2))) // 实验组 02  12
            {
                PipMsgSend record =new PipMsgSend();
                record.setId(getPrimaryId()); //不能用uuid  短信回调有id长度限制
                record.setIsDelete(2);
                record.setCreateBy("system");
                record.setCreateDate(new Date());
                record.setPatientId(patient.getPatientId());
                record.setProjectId("004");
                record.setLccCode(patient.getLccCode());
                if(org.apache.commons.lang3.StringUtils.isBlank(patient.getIsSmoking())){
                    b = setMsgInfo(patient, record,"B");
                }else{
                    //吸烟
                    if(patient.getIsSmoking().equals("1")){
                        b = setMsgInfo(patient, record,"A");
                    }else{
                        //不吸烟
                        b = setMsgInfo(patient, record,"B");
                    }
                }
                if( !b ){
                    continue;
                }
                record.setSendtimePreinstall(TIME_D[random.nextInt(TIME_D.length)]);
                record.setState(2);
                pipMsgSendMapper.insert(record);
            } else{
                SimpleDateFormat sf =new SimpleDateFormat("dd");
                String format = sf.format(new Date());
                if(format.equals("05") ||format.equals("20") ){
                    if(dzList.size() == 0){
                        log.info("整体对照组短信库为空");
                        continue;
                    }
                    PipMsgSend record =new PipMsgSend();
                    int nextInt = 0;
                    if( dzList.get(patient.getBelongGroup()).size() > 0 ){
                        nextInt = random.nextInt(dzList.get(patient.getBelongGroup()).size());
                    }else{
                        log.info("获取到的对照组("+patient.getBelongGroup()+
                                ")短信为空："+dzList.get(patient.getBelongGroup()).size());
                        continue;
                    }
                    record.setId(getPrimaryId()); //不能用uuid  短信回调有id长度限制
                    record.setIsDelete(2);
                    record.setCreateBy("system");
                    record.setCreateDate(new Date());
                    record.setPatientId(patient.getPatientId());
                    record.setProjectId("004");
                    record.setLccCode(patient.getLccCode());
                    record.setMsgId(dzList.get(patient.getBelongGroup()).get(nextInt).getId());
                    record.setMsgName(replacePatient(dzList.get(patient.getBelongGroup()).get(nextInt).getMsgName(),patient));
                    record.setSendtimePreinstall(TIME_D[random.nextInt(TIME_D.length)]);
                    record.setState(2);
                    record.setIsNeedreply(2);
                    pipMsgSendMapper.insert(record);
                }
            }
        }
    }

	/**
	 * 
	 * description:  初始化短信内容 清空一次数据的时候需要填充数据 
	 * @author yangfeng     
	 * @update 2016年5月31日
	 */
	private void initMsgForEveryPatient()
	{
		setIsUsedForEveryOne();
		//这边重新填充一次数据 给所有不同的人 填充一次未来要在一周内发送的短信
		List<PipCommPatient> needMsgPatient = pipCommPatientMapper.selectPatientNeedSendMsg();
		for(PipCommPatient pa:needMsgPatient){
			singleAddMsg(pa);
		}
	}
	private void randomMsgInsert(Random random, PipCommPatient temp, SimpleDateFormat format, String nowDate,
			PipCommPatientDate pipCommPatientDate)
	{
		//首次随访
		if(pipCommPatientDate.getFirstPlanDate()!=null){
			Date firstDate = pipCommPatientDate.getFirstPlanDate();
			firstDate.setDate(firstDate.getDate()-1);
			String firstDateStr = format.format(firstDate);
			if(nowDate.equals(firstDateStr)){
				insertSFMSG(random, temp);
			}
		}
		//六月随访
		if(pipCommPatientDate.getSixPlanDate()!=null){
			Date sixDate = pipCommPatientDate.getSixPlanDate();
			sixDate.setDate(sixDate.getDate()-1);
			String sixDateStr = format.format(sixDate);
			if(nowDate.equals(sixDateStr)){
				insertSFMSG(random, temp);
			}
		}
		//12月随访
		if(pipCommPatientDate.getTwelvePlanDate()!=null){
			Date twelveDate = pipCommPatientDate.getTwelvePlanDate();
			twelveDate.setDate(twelveDate.getDate()-1);
			String twelveDateStr = format.format(twelveDate);
			if(nowDate.equals(twelveDateStr)){
				insertSFMSG(random, temp);
			}
		}
		//18月随访
		if(pipCommPatientDate.getEighteenPlanDate()!=null){
			Date eighteenPlanDate = pipCommPatientDate.getEighteenPlanDate();
			eighteenPlanDate.setDate(eighteenPlanDate.getDate()-1);
			String eighteenPlanDateStr = format.format(eighteenPlanDate);
			if(nowDate.equals(eighteenPlanDateStr)){
				insertSFMSG(random, temp);
			}
		}
		//末次随访
		if(pipCommPatientDate.getEndPlanDate()!=null){
			Date endDate = pipCommPatientDate.getEndPlanDate();
			endDate.setDate(endDate.getDate()-1);
			String endDateStr = format.format(endDate);
			if(nowDate.equals(endDateStr)){
				insertSFMSG(random, temp);
			}
		}
	}
	private void birthdayMsgInsert(Random random, PipCommPatient temp, Date birthday)
	{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
		String format2 = simpleDateFormat.format(new Date());
		String birthdayStr = simpleDateFormat.format(birthday);
		if(birthdayStr.equals(format2)){
            int nextInt = 0 ;
            if( srzflList.get(temp.getBelongGroup()).size() > 0 ){
                nextInt = random.nextInt(srzflList.get(temp.getBelongGroup()).size());
            }else{
                return;
            }
			PipMsgSend record = new PipMsgSend();
			record.setId(getPrimaryId());
			record.setIsDelete(2);
			record.setCreateBy("system");
			record.setCreateDate(new Date());
			record.setPatientId(temp.getPatientId());
			record.setProjectId("004");
			record.setLccCode(temp.getLccCode());
			record.setMsgId(srzflList.get(temp.getBelongGroup()).get(nextInt).getId());
			record.setMsgName(StringUtils.isBlank(temp.getPatientName()) ? "" : temp.getPatientName()+(temp.getSex().equals("1")?"先生":"女士")+","+srzflList.get(temp.getBelongGroup()).get(nextInt).getMsgName());
			record.setState(2);
			record.setSendtimePreinstall(TIME_D[random.nextInt(TIME_D.length)]);
			record.setIsNeedreply(2);
			pipMsgSendMapper.insert(record);
		}
	}

	public void singleAddMsg(PipCommPatient pa)
	{
        log.info("为每个人填充预发送短信，当前pid："+pa.getPatientId());
		List<PipMsgMsgtmp> msg = createMsgForPatient(pa);
        log.info("为PID："+pa.getPatientId()+" 填充短信数量为："+msg.size());
		for(PipMsgMsgtmp msgtmp : msg){
			insertTempMsg(pa, msgtmp);
		}
	}

	/**
	 * 
	 * description:  监测患者随访日期是否过6个月 如果过6个月的话  短信重新发送一遍
	 * @author yangfeng     
	 * @update 2016年5月18日
	 */
	private void checkAndFunctionPatient()
	{
		List<PipCommPatient> patients = pipCommPatientMapper.selectPatientNeedSendMsg();
		for(PipCommPatient patient:patients){
			PipCommPatientDateKey key = new PipCommPatientDateKey();
			key.setPatientId(patient.getPatientId());
			key.setProjectId(patient.getProjectId());
			//获取每个人的随访时间
			PipCommPatientDate visitDate = patientDateMapper.selectByPrimaryKey(key);
			if(null == visitDate){
				continue;
			}
			Date firstDate = null;
			if(null != visitDate.getFirstDate()){
				firstDate = visitDate.getFirstDate();
			}
			Date sixDate = null;
			if(null !=visitDate.getSixRealDate()){
				sixDate= visitDate.getSixRealDate();
			}
			Date twelveDate = null;
			if(null != visitDate.getTwelveRealDate()){
				twelveDate=visitDate.getTwelveRealDate();
			}
			Date eighteenDate = null;
			if(null !=visitDate.getEighteenRealDate()){
				eighteenDate= visitDate.getEighteenRealDate();
			}
			Date endDate = null;
			if(null != visitDate.getEndRealDate()){
				 endDate =visitDate.getEndRealDate();
			}
			if(endDate !=null){
				clearLogic(patient, endDate);
			}
			else if(eighteenDate!=null){
				clearLogic(patient, eighteenDate);
			}
			else if(twelveDate!=null){
				clearLogic(patient, twelveDate);
			}
			else if(sixDate!=null){
				clearLogic(patient, sixDate);
			}
			else if(firstDate!=null){
				clearLogic(patient, firstDate);
			}
			
		}
	}
	/**
	 * 
	 * description:  患者信息数据进行清理操作 
	 * 将已经使用过的患者短信的数据进行删除操作
	 * @author yangfeng  
	 * @param patient
	 * @param firstDate   
	 * @update 2016年5月25日
	 */
	private void clearLogic(PipCommPatient patient, Date firstDate)
	{
		Calendar c1 = Calendar.getInstance(), c2 = Calendar.getInstance();
		c1.setTime(new Date());
		c2.setTime(firstDate);

		int days = (int)((c1.getTimeInMillis() - c2.getTimeInMillis())/1000/3600/24);

		//如果首次随访后 超过6个月进行一次数据清理
		if(days % 180  == 0){ // 每半年清除一次
			PipMsgTempExample example = new PipMsgTempExample();
			Criteria criteria = example.createCriteria();
			criteria.andPatientIdEqualTo(patient.getPatientId());
			criteria.andIsUsedEqualTo("Y");
			pipMsgTempMapper.deleteByExample(example);
		}
	}
	/**
	 * 
	 * description: //将每个人的短信都 置 成 已使用 
	 * @author yangfeng     
	 * @update 2016年5月18日
	 */
	private void setIsUsedForEveryOne()
	{
		PipMsgTemp record = new PipMsgTemp();
		record.setIsUsed("Y");
		PipMsgTempExample example = new PipMsgTempExample();
		pipMsgTempMapper.updateByExampleSelective(record, example);
        log.info("将所有短信预发送数据置为已使用");
	}
	/**
	 * 
	 * description:  插入短信
	 * @author yangfeng  
	 * @param pa
	 * @param smoke   
	 * @update 2016年5月18日
	 */
	private void insertTempMsg(PipCommPatient pa, PipMsgMsgtmp smoke)
	{
		
		PipMsgTemp temp =new PipMsgTemp();
		temp.setId(getPrimaryId());
		if(org.apache.commons.lang3.StringUtils.isBlank(pa.getIsSmoking())){
            temp.setFlag("B"); // 默认不抽烟
        }else{
            if(pa.getIsSmoking().equals("1")){
                temp.setFlag("A");
            }
            else if(pa.getIsSmoking().equals("2")){
                temp.setFlag("B");
            }
        }
		temp.setCreateDate(new Date());
		temp.setPatientId(pa.getPatientId());
		temp.setMsgId(smoke.getId());
		temp.setIsUsed("N");
		temp.setMsgName(smoke.getMsgName());
		if(!StringUtils.isEmpty(smoke.getIsReplay()) && "Y".equals(smoke.getIsReplay())){
			temp.setIsNeedreplay("Y");
		}else{
			temp.setIsNeedreplay("N");
		}
		pipMsgTempMapper.insert(temp);
        log.info("短信入库，进入预发送表");
	}

	// 获取主键  -- 之所以不用UUID是因为短信回调对ID有长度限制
	public String getPrimaryId(){
		Long now = System.currentTimeMillis();
		int random = new Random().nextInt(900)+100; // 随机数 100-999范围
		return now.toString() + random;
	}
	/**
	 * 
	 * description:  
	 * @author yangfeng  
	 * @param patient
	 * @param record
	 * @param isSmoking    A  标识吸烟  B 标识不吸烟
	 * @update 2016年5月18日
	 */
	private boolean setMsgInfo(PipCommPatient patient, PipMsgSend record,String isSmoking)
	{
		Random random= new Random();
		//主要是用来解决 不同的随机短信发送给不同的患者  每个患者在当天收到的短信是随机的 
		PipMsgTempExample example = new PipMsgTempExample();
		com.bdcor.pip.web.msg.domain.PipMsgTempExample.Criteria ccriterias = example.createCriteria();
		ccriterias.andPatientIdEqualTo(patient.getPatientId());
		ccriterias.andIsUsedEqualTo("N");
		ccriterias.andFlagEqualTo(isSmoking);
		List<PipMsgTemp> pspTemp = pipMsgTempMapper.selectByExample(example);
        int nextIntS = 0 ;
        if( pspTemp.size() > 0 ){
            nextIntS = random.nextInt(pspTemp.size());
        }else{
            log.info("PID:"+patient.getPatientId()+",从短信预发送表中未获取到数据");
            return false;
        }
		record.setMsgId(pspTemp.get(nextIntS).getMsgId());
		record.setMsgName(StringUtils.isBlank(patient.getPatientName()) ? "" : patient.getPatientName()+(patient.getSex().equals("1")?"先生":"女士")+","+replacePatient(pspTemp.get(nextIntS).getMsgName(),patient));
		record.setIsNeedreply(pspTemp.get(nextIntS).getIsNeedreplay().equals("Y")? 1 : 2 );
		//将使用过的短信做个标识 更新到数据库里
		pspTemp.get(nextIntS).setIsUsed("Y");
		pipMsgTempMapper.updateByPrimaryKeySelective(pspTemp.get(nextIntS));
        return true;
	}
	/**
	 * 
	 * description: 获取两个日期相差的月数 
	 * @author yangfeng  
	 * @param date1
	 * @param date2
	 * @return
	 * @throws java.text.ParseException   
	 * @update 2016年6月17日
	 */
	public  int getMonthSpace(Date date1, Date date2)throws java.text.ParseException {
		int result = 0;
		int result2 =0;
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(date1);
		c2.setTime(date2);
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		result2 =(c2.get(Calendar.YEAR)- c1.get(Calendar.YEAR))*12;
		return (result+result2) == 0 ? 1 : Math.abs(result+result2);
	}
	//用于添加两条后缀标签的逻辑[两条短信放置在一个月的 第一个星期 和第三个星期的任意一天]
	private static int mark =1;
	//用于区分药物依从类信息 和高血压短信 ，保证两者的互斥现象。即添加了药物依从类信息的后缀标签以后，就不添加高血压短信 ，如果添加了高血压短息
	private static boolean hzflag= false;
	private List<PipMsgMsgtmp> createMsgForPatient(PipCommPatient patient){

        Map<String,String> m = (Map<String,String>)JSON.parse(msg_map);

		if(mark ==5){
			mark =1;
		}
		List<PipMsgMsgtmp> list = new ArrayList<PipMsgMsgtmp>();
		Random random = new Random();
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isBlank(patient.getIsSmoking())){
            map.put("SMOKING", "B");
        }else{
            if( patient.getIsSmoking().equals("1") ){
                map.put("SMOKING", "A");
            }
            else if(patient.getIsSmoking().equals("2")){
                map.put("SMOKING", "B");
            }
        }
		map.put("PATIENT_ID", patient.getPatientId());
		if( patient.getBelongGroup() == null || m.get(patient.getBelongGroup()) == null ){
			return new ArrayList<PipMsgMsgtmp>();
		}
        map.put("MSG_TYPE",m.get(patient.getBelongGroup()));
		List<PipMsgMsgtmp> pipMsgMsgtmps = pipMsgMsgtmpMapper.selectMsgForMap(map);
		// 给各自人员生成自己的短信库
		List<PipMsgMsgtmp> jcjylList  =new ArrayList<PipMsgMsgtmp>();
		List<PipMsgMsgtmp> yyyclList = new ArrayList<PipMsgMsgtmp>();
		List<PipMsgMsgtmp> gxylList = new ArrayList<PipMsgMsgtmp>();
		List<PipMsgMsgtmp> ydlList = new ArrayList<PipMsgMsgtmp>();
		List<PipMsgMsgtmp> jylList = new ArrayList<PipMsgMsgtmp>();
		List<PipMsgMsgtmp> hycjyjlList = new ArrayList<PipMsgMsgtmp>();
		List<PipMsgMsgtmp> sftxlList = new ArrayList<PipMsgMsgtmp>();
		List<PipMsgMsgtmp> srzflList = new ArrayList<PipMsgMsgtmp>();
		// 从短信库中查出短信放到tmpMap
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		//初始化一次数据
		for (PipMsgMsgtmp pipMsgMsgtmp : pipMsgMsgtmps)
		{
			tmpMap.put(pipMsgMsgtmp.getId(), pipMsgMsgtmp);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M001"))
				jcjylList.add(pipMsgMsgtmp);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M002"))
				yyyclList.add(pipMsgMsgtmp);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M003"))
				gxylList.add(pipMsgMsgtmp);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M004"))
				ydlList.add(pipMsgMsgtmp);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M005"))
				jylList.add(pipMsgMsgtmp);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M006"))
				hycjyjlList.add(pipMsgMsgtmp);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M007"))
				sftxlList.add(pipMsgMsgtmp);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M008"))
				srzflList.add(pipMsgMsgtmp);
		}
		if(org.apache.commons.lang3.StringUtils.isBlank(patient.getIsSmoking()) || patient.getIsSmoking().equals("2")){
			//两条高血压短信
			if(gxylList.size()>0){
				int[] randomArr = createRandomCount(gxylList);
				if(mark==1 || mark ==4){
					int nextInt = random.nextInt(2);
					if(nextInt ==0){
						hzflag = false;
						gxylList.get(randomArr[0]).setIsReplay("Y");
						gxylList.get(randomArr[0]).setMsgName(gxylList.get(randomArr[0]).getMsgName()+suffixFlag[1]);
					}else{
						hzflag = true;
						gxylList.get(randomArr[0]).setIsReplay("N");
						gxylList.get(randomArr[0]).setMsgName(gxylList.get(randomArr[0]).getMsgName());
					}
					
				}
				list.add(gxylList.get(randomArr[0]));
				list.add(gxylList.get(randomArr[1]));
			}
			//两条基础类短信
			if(jcjylList.size()>0){
				int[] randomArr = createRandomCount(jcjylList);
				list.add(jcjylList.get(randomArr[0]));
				list.add(jcjylList.get(randomArr[1]));
			}
			//一条药物依从类短信
			if(yyyclList.size()>0){
				int nextInt = random.nextInt(yyyclList.size());
				if(mark==1 || mark ==4){
					if(hzflag){
						yyyclList.get(nextInt).setIsReplay("Y");
						yyyclList.get(nextInt).setMsgName(yyyclList.get(nextInt).getMsgName()+suffixFlag[0]);
					}else{
						yyyclList.get(nextInt).setIsReplay("N");
						yyyclList.get(nextInt).setMsgName(yyyclList.get(nextInt).getMsgName());
					
					}
				}
				list.add(yyyclList.get(nextInt));
			}
			//一条运动类短信
			if(ydlList.size()>0){
				list.add(ydlList.get(random.nextInt(ydlList.size())));
			}
		}
		//吸烟
		if(patient.getIsSmoking().equals("1")){
			//两条高血压短信
			if(gxylList.size()>0){
				int[] randomArr = createRandomCount(gxylList);
				if(mark==1 || mark ==4){
					int nextInt = random.nextInt(2);
					if(nextInt==0){
						hzflag =false;
						gxylList.get(randomArr[0]).setMsgName(gxylList.get(randomArr[0]).getMsgName()+suffixFlag[1]);
						gxylList.get(randomArr[0]).setIsReplay("Y");
					}else{
						hzflag =true;
						gxylList.get(randomArr[0]).setMsgName(gxylList.get(randomArr[0]).getMsgName());
						gxylList.get(randomArr[0]).setIsReplay("N");
					}
				}
				list.add(gxylList.get(randomArr[0]));
				list.add(gxylList.get(randomArr[1]));
			}
			//一条基础类短信
			if(jcjylList.size()>0){
				list.add(jcjylList.get(random.nextInt(jcjylList.size())));
			}
			//一条药物依从类短信
			if(yyyclList.size()>0){
				int nextInt = random.nextInt(yyyclList.size());
				if(mark==1 || mark ==4){
					if(hzflag){
						yyyclList.get(nextInt).setMsgName(yyyclList.get(nextInt).getMsgName()+suffixFlag[0]);
						yyyclList.get(nextInt).setIsReplay("Y");
					}else{
						yyyclList.get(nextInt).setMsgName(yyyclList.get(nextInt).getMsgName());
						yyyclList.get(nextInt).setIsReplay("N");
					}
				}
				list.add(yyyclList.get(nextInt));
			}
			//一条运动类短信
			if(ydlList.size()>0){
				list.add(ydlList.get(random.nextInt(ydlList.size())));
			}
			//一条戒烟类短信
			if(jylList.size()>0){
				list.add(jylList.get(random.nextInt(jylList.size())));
			}
		}
		mark++;
		return list;
	}
	private void initRandomDate()
	{
		// 1代表对照组，2代表实验组
		List<PipMsgMsgtmp> pipMsgMsgtmps = pipMsgMsgtmpMapper.selectByExample(null);
		// 从短信库中查出短信放到tmpMap
		Map<String, Object> tmpMap = new HashMap<String, Object>();
		//清空一次各类短信
		clearData();
		//初始化一次数据
		for (PipMsgMsgtmp pipMsgMsgtmp : pipMsgMsgtmps)
		{
			tmpMap.put(pipMsgMsgtmp.getId(), pipMsgMsgtmp);
            String group = pipMsgMsgtmp.getMsgTypeCode().substring(0,2);
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M001"))
//				jcjylList.add(pipMsgMsgtmp);
            {
                if(jcjylList.get(group) == null){
                    List<PipMsgMsgtmp> l = new ArrayList<PipMsgMsgtmp>();
                    l.add(pipMsgMsgtmp);
                    jcjylList.put(group,l);
                }else{
                    jcjylList.get(group).add(pipMsgMsgtmp);
                }

            }

			if(pipMsgMsgtmp.getMsgTypeCode().contains("M002"))
            {
//				yyyclList.add(pipMsgMsgtmp);
                if(yyyclList.get(group) == null){
                    List<PipMsgMsgtmp> l = new ArrayList<PipMsgMsgtmp>();
                    l.add(pipMsgMsgtmp);
                    yyyclList.put(group,l);
                }else{
                    yyyclList.get(group).add(pipMsgMsgtmp);
                }
            }
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M003"))
//				gxylList.add(pipMsgMsgtmp);
            {
                if(gxylList.get(group) == null){
                    List<PipMsgMsgtmp> l = new ArrayList<PipMsgMsgtmp>();
                    l.add(pipMsgMsgtmp);
                    gxylList.put(group,l);
                }else{
                    gxylList.get(group).add(pipMsgMsgtmp);
                }
            }
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M004"))
//				ydlList.add(pipMsgMsgtmp);
            {
                if(ydlList.get(group) == null){
                    List<PipMsgMsgtmp> l = new ArrayList<PipMsgMsgtmp>();
                    l.add(pipMsgMsgtmp);
                    ydlList.put(group,l);
                }else{
                    ydlList.get(group).add(pipMsgMsgtmp);
                }
            }
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M005"))
//				jylList.add(pipMsgMsgtmp);
            {
                if(jylList.get(group) == null){
                    List<PipMsgMsgtmp> l = new ArrayList<PipMsgMsgtmp>();
                    l.add(pipMsgMsgtmp);
                    jylList.put(group,l);
                }else{
                    jylList.get(group).add(pipMsgMsgtmp);
                }
            }
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M006"))
//				hycjyjlList.add(pipMsgMsgtmp);
            {
                if(hycjyjlList.get(group) == null){
                    List<PipMsgMsgtmp> l = new ArrayList<PipMsgMsgtmp>();
                    l.add(pipMsgMsgtmp);
                    hycjyjlList.put(group,l);
                }else{
                    hycjyjlList.get(group).add(pipMsgMsgtmp);
                }
            }
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M007"))
//				sftxlList.add(pipMsgMsgtmp);
            {
                if(sftxlList.get(group) == null){
                    List<PipMsgMsgtmp> l = new ArrayList<PipMsgMsgtmp>();
                    l.add(pipMsgMsgtmp);
                    sftxlList.put(group,l);
                }else{
                    sftxlList.get(group).add(pipMsgMsgtmp);
                }
            }
			if(pipMsgMsgtmp.getMsgTypeCode().contains("M008"))
//				srzflList.add(pipMsgMsgtmp);
            {
                if(srzflList.get(group) == null){
                    List<PipMsgMsgtmp> l = new ArrayList<PipMsgMsgtmp>();
                    l.add(pipMsgMsgtmp);
                    srzflList.put(group,l);
                }else{
                    srzflList.get(group).add(pipMsgMsgtmp);
                }
            }
		}
        dzList.putAll(sftxlList);
	}
	private void clearData()
	{
		jcjylList.clear();
		yyyclList.clear();
		gxylList.clear();
		ydlList.clear();
		jylList.clear();
		hycjyjlList.clear();
		sftxlList.clear();
		srzflList.clear();
		
	}
	/**
	 * 
	 * description:  对发送的短信进行一次信息过滤   
	 * @author yangfeng  
	 * @param msgName
	 * @param patient
	 * @return   
	 * @update 2016年6月17日
	 */
	public String replacePatient(String msgName, PipCommPatient patient ){
		String result = msgName.replaceAll(NAME_,patient.getPatientName() );
		Date groupDate = patient.getGroupDate();
		if(groupDate!=null && result.indexOf(MOUTH_)!=-1){
			try
			{
				result =result.replaceAll(MOUTH_, getMonthSpace(groupDate, new Date())+"");
			} catch (ParseException e)
			{
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 
	 * description:  插入随访短信
	 * @author yangfeng  
	 * @param random
	 * @param temp   
	 * @update 2016年5月13日
	 */
	private void insertSFMSG(Random random, PipCommPatient temp)
	{
		log.info("随访短信逻辑，随机数大小："+sftxlList.size()+",patient_id:"+temp.getPatientId());
		int nextInt = random.nextInt(sftxlList.size());
		PipMsgSend record = new PipMsgSend();
		record.setId(getPrimaryId());
		record.setIsDelete(2);
		record.setCreateBy("system");
		record.setCreateDate(new Date());
		record.setPatientId(temp.getPatientId());
		record.setProjectId("004");
		record.setLccCode(temp.getLccCode());
		record.setMsgId(sftxlList.get(temp.getBelongGroup()).get(nextInt).getId());
		record.setMsgName(replacePatient(sftxlList.get(temp.getBelongGroup()).get(nextInt).getMsgName(), temp));
		record.setState(2);
		record.setSendtimePreinstall(TIME_D[random.nextInt(TIME_D.length)]);
		record.setIsNeedreply(2);
		pipMsgSendMapper.insert(record);
	}
	/**
	 * 
	 * description:生成两个不重复的随机数  
	 * 传入一个大于0的list集合，通过集合的长度来限定随机数的范围，
	 * 保证随机数不超过list集合的长度，从中随机产生两个不重复的随机数 存放在int[]数组里面
	 * 如果list集合长度是1的话 则会返回两个0存放在int[]数组里面
	 * @author yangfeng  
	 * @param list
	 * @return   
	 * @update 2016年5月12日
	 */
	private int[] createRandomCount(List list){
		if(list.size()<=0)
			throw new RuntimeException("传入的集合为空!");
		int a[] =new int[2];
		Random random =new Random();
		int temp_ = random.nextInt(list.size());
		a[0]=temp_;
		int temp=-1;
		for(;;){
			if(list.size()==1) {
				a[1]=0;
				break;
			}
			temp = random.nextInt(list.size());
			if(temp_ !=temp && temp>=0){
				a[1]=temp;
				break;
			}
		}
		return a;
	}
	@Override
	public void send()
	{	
		initDate();
		welcomeMsg();
	}
	@Override
	public void sendWelcomeMsg4Single(PipCommPatient patient){
		List<PipMsgMsgtmp> welcomeMsg = new ArrayList<PipMsgMsgtmp>();
		addTypeMsg(welcomeMsg,"M006");
		Random random = new Random();
		//参加短信欢迎类的逻辑
		if(welcomeMsg.size()>0){
			welcomeMsgInsert(random, patient,welcomeMsg);
		}
	}

    //  前台调用 发送欢迎短信 按钮触发
    public void welcomeMsg()
	{
		List<PipMsgMsgtmp> welcomeMsg = new ArrayList<PipMsgMsgtmp>();
		addTypeMsg(welcomeMsg,"M006");
		List<PipCommPatient> patients = pipCommPatientMapper.selectPatientNeedSendMsg();
		Random random = new Random();
		for(PipCommPatient temp:patients){
			//参加短信欢迎类的逻辑
			if(welcomeMsg.size()>0){
				Map<String ,Object> map = new HashMap<String ,Object>();
				map.put("PATIENT_ID", temp.getPatientId());
				map.put("MSG_TYPE_CODE", "M006");
				int count=pipMsgSendMapper.selectMsgTypeByPatient(map);
				if(count<=0){
					//欢迎类的短信逻辑
					welcomeMsgInsert(random, temp,welcomeMsg);
				}
			}
		}


	}
	/**
	 * 
	 * description: 添加固定类型的短信 
	 * @author yangfeng  
	 * @param welcomeMsg
	 * @param type   
	 * @update 2016年6月15日
	 */
	private void addTypeMsg(List<PipMsgMsgtmp> welcomeMsg,String type)
	{
		PipMsgMsgtmpExample example = new PipMsgMsgtmpExample();
		List<PipMsgMsgtmp> pipMsgMsgtmps = pipMsgMsgtmpMapper.selectByExample(example);
		for (PipMsgMsgtmp pipMsgMsgtmp : pipMsgMsgtmps)
		{
			if(pipMsgMsgtmp.getMsgTypeCode().equals(type))
				welcomeMsg.add(pipMsgMsgtmp);
		}
	}
	private void welcomeMsgInsert(Random random, PipCommPatient temp,List<PipMsgMsgtmp> welcomeMsg)
	{
		int nextInt = random.nextInt(welcomeMsg.size());
		PipMsgSend record = new PipMsgSend();
		record.setId(getPrimaryId());
		record.setIsDelete(2);
		record.setCreateBy("system");
		record.setCreateDate(new Date());
		record.setPatientId(temp.getPatientId());
		record.setProjectId("004");
		record.setLccCode(temp.getLccCode());
		record.setMsgId(welcomeMsg.get(nextInt).getId());
		record.setMsgName(StringUtils.isBlank(temp.getPatientName()) ? "" : temp.getPatientName()+(temp.getSex().equals("1")?"先生":"女士")+","+welcomeMsg.get(nextInt).getMsgName());
		record.setState(2);
		record.setSendtimePreinstall(new Date());
		record.setIsNeedreply(2);
		record.setMobile(temp.getMobile()); //  插入手机号
		pipMsgSendMapper.insert(record);
	}
	@Override
	public void createWeekMsg(PipCommPatient patient)
	{
		singleAddMsg(patient);
        List<PipCommPatient> l = new ArrayList<PipCommPatient>();
            l.add(patient);
        // 生成当天待发送短信入库
        createMsgForPid(l);
	}


    // 新增患者生成预发送短信 并插入当天需要发送短信
    public void insertMsgForNewPid(){
        List<PipCommPatient> newPidList = msgSendDao.getNewPatient();
        if( newPidList == null || newPidList.size() == 0 ){
            return; // 无新增直接返回
        }
        for(PipCommPatient po : newPidList){
            singleAddMsg(po); // 生成预发送短信并入库
        }
        // 生成当天待发送短信入库
        createMsgForPid(newPidList);

    }

	@Override
	public PipMsgSend addSendMsg(PipCommPatient patient, String msgTypeCode) {
		PipMsgMsgtmp msgtmp = pipMsgMsgtmpMapper.getRandomOneByType(msgTypeCode);
		if(msgtmp==null)return null;
		PipMsgSend record = new PipMsgSend();
		record.setId(getPrimaryId());
		record.setIsDelete(2);
		record.setCreateBy("system");
		record.setCreateDate(new Date());
		record.setPatientId(patient.getPatientId());
		record.setProjectId(Securitys.getCurrentProject());
		record.setLccCode(patient.getLccCode());
		record.setMsgId(msgtmp.getId());
		record.setMsgName(msgtmp.getMsgName().replaceAll("patient_nameX", StringUtils.isBlank(patient.getPatientName()) ? "" : patient.getPatientName()+(patient.getSex().equals("1")?"先生":"女士")));
		record.setState(2);
		record.setSendtimePreinstall(new Date());
		record.setIsNeedreply(2);
		record.setMobile(patient.getMobile()); //  插入手机号
		pipMsgSendMapper.insert(record);
		return record;
	}
}
