package com.bdcor.pip.web.msg.service.impl;

import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.msg.dao.PipMsgSendMapper;
import com.bdcor.pip.web.msg.domain.PipMsgSend;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Months;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by zhang.rw on 16-8-3.
 */
@Service
public class MsgRuleServiceImpl {

    public static final Logger log = LoggerFactory.getLogger(MsgRuleServiceImpl.class);

    @Autowired
    private PipMsgSendMapper msgMapper;
    @Autowired
	private PipCommPatientMapper patientMapper;

    @Value("${sendTime}")
    public String sendTime;
    // 新增人员的欢迎短信


    public void createMsgByRule(){
        // 实验组短信逻辑
        sendMsgForShiyanZu();
        // 对照组短信逻辑
        sendMsgForDuizhaoZu();
        // 随访短信逻辑
        followViewMsg();
        // 生日祝福短信逻辑
        MsgForBirthday();

    }

    //后缀标签
    private static String[] suffix_notIsD= {"请问您今天按时服药了吗?是请回复1，否请回复2.您的回复对我们很重要,请务必进行回复,谢谢!",
                                 "请问您最近2周测血压了吗？是请回复3，否请回复4。您的回复意见对我们很重要，请务必回复，谢谢！"};
    private static String[] suffix_isD = {"请问您今天按时服药了吗?是请回复1，否请回复2.您的回复对我们很重要，请务必进行回复，谢谢！",
            "请问您最近两周测血糖了吗?是请回复3，否请回复4。您的回复意见对我们很重要，请务必进行回复，谢谢！"};
    private static String MONTHS = "trial_month";
    // 实验组的处理
    public void sendMsgForShiyanZu(){
        log.info("实验组短信逻辑");
        Calendar cal = Calendar.getInstance();
        int weekday = cal.get(Calendar.DAY_OF_WEEK);
        if( weekday == Calendar.MONDAY ){ // 周一不干活
            log.info("当前周一，实验组短信逻辑返回");
            return;
        }
        List<PipCommPatient> list =  msgMapper.getPatientByGroup("1");
        String[] timeArr = sendTime.split(",");
        int length = timeArr.length;
        String dbtype;
        cal.set(Calendar.MINUTE,0);
        cal.set(Calendar.SECOND,0);
        Random rom = new Random();
        for( PipCommPatient po : list ){
            // 获取可发送短信类别
            Map<String,String> m = new HashMap<String, String>();
            m.put("pid",po.getPatientId());
            m.put("ptype",po.getBelongGroup());
            if( "1".equals(po.getBelongGroup().substring(0,1))){
                m.put("smoke",null);
            }else{
                m.put("smoke",po.getIsSmoking());
            }
            dbtype = msgMapper.getMsgDbType(m);
            log.info("针对PID："+po.getPatientId()+"，随机获取的DBTYPE为："+dbtype);
            if(StringUtils.isBlank(dbtype)){
                continue;
            }
            // 随机获取一条该类型短信
            Map<String,String> msg_m = getRandomMsgByDbtype(dbtype,po.getPatientId());
            if( msg_m == null || msg_m.size() == 0 ){
                log.info("实验组获取随机短信失败，获取到DBtype为："+dbtype+",PID："+po.getPatientId());
                continue;
            }
//            msg_m.get("ID");
//            msg_m.get("IS_REPLY");
            PipMsgSend ps = new PipMsgSend();
            ps.setId(getPrimaryId());
            ps.setPatientId(po.getPatientId());
            ps.setCreateDate(new Date());
            ps.setProjectId("004");
            ps.setLccCode(po.getLccCode());
            ps.setMsgId(msg_m.get("ID"));
            ps.setIsDelete(2);
            ps.setMobile(po.getMobile());
            ps.setIsNeedreply(2);
            ps.setState(2);
            if( length > 1 ){
                Date d = cal.getTime();
                d.setHours( Integer.parseInt(timeArr[rom.nextInt(length)]));
                ps.setSendtimePreinstall(d); // 预发送时间
            }else{
                log.info("发送时间点的配置存在问题，则默认的12：00时刻，PID："+po.getPatientId());
                ps.setSendtimePreinstall(new Date()); // 预发送时间
            }
            String pname = "1".equals(po.getSex()) ? po.getPatientName()+"先生" : po.getPatientName()+"女士";
            log.info("查看当前周次："+getWeekMod());
            if( ( getWeekMod() == 2 ) && ( dbtype.contains("M002")))
                { // 每月第一周  药物类 需要进行回复后缀判断
                boolean b = this.isNeedReply(po.getPatientId() , dbtype, 1); // 每月一条需要回复的短信  最后需要发送的条数可以配置...
                    log.info("当前是否发送需要回复的短信："+b+",pid:"+po.getPatientId()+",dntype"+dbtype);
                if(b){
                    // 药物依从性短信
                    if("1".equals(po.getBelongGroup().substring(0,1))){ // 糖尿病
                        ps.setMsgName(suffix_isD[0]);//pname+
                        ps.setIsNeedreply(1);
                    }else{
                        ps.setMsgName(suffix_notIsD[0]); // 非糖尿病 pname+
                        ps.setIsNeedreply(1);
                    }
                }else{
                    String msg_text = msg_m.get("MSG_NAME").replaceAll("patient_nameX",pname);
                    if( msg_text.indexOf(MONTHS) != -1 ){
                        int i = 0;
                        if( po.getGroupDate() != null ){
                            i = getMonths(po.getGroupDate() , new Date());
                        }
                        if( i == 0 ){
                            msg_text = msg_text.replaceAll(MONTHS,"几");
                        }else{
                            msg_text = msg_text.replaceAll(MONTHS,i+"");
                        }
                    }
                    ps.setMsgName(msg_text);//pname+msg_m.get("MSG_NAME")
                    ps.setIsNeedreply(2);
                }
            }else if( getWeekMod() == 0  && (dbtype.contains("M003") || dbtype.contains("M011") )){ // 第三周 /高血压类/血糖类 短信
                boolean b = isNeedReply(po.getPatientId() , dbtype, 1); // 每月/每周 一条需要回复的短信  最后需要发送的条数可以配置...
                if(b){
                    if(dbtype.contains("M003") && "0".equals(po.getBelongGroup().substring(0,1))){ //非糖尿病发送高血压类短信
                        ps.setMsgName(suffix_notIsD[1]); // 非糖尿病 pname+
                        ps.setIsNeedreply(1);
                    }else if( dbtype.contains("M011") && "1".equals(po.getBelongGroup().substring(0,1)) ){ // 糖尿病人群发送血糖类短信
                        ps.setMsgName(suffix_isD[1]); //pname+
                        ps.setIsNeedreply(1);
                    }else{
                        String msg_text = msg_m.get("MSG_NAME").replaceAll("patient_nameX",pname);
                        if( msg_text.indexOf(MONTHS) != -1 ){
                            int i = 0;
                            if( po.getGroupDate() != null ){
                                i = getMonths(po.getGroupDate() , new Date());
                            }
                            if( i == 0 ){
                                msg_text = msg_text.replaceAll(MONTHS,"几");
                            }else{
                                msg_text = msg_text.replaceAll(MONTHS,i+"");
                            }
                        }
                        ps.setMsgName(msg_text);
                        ps.setIsNeedreply(2);
                    }
                }else{
//                    ps.setMsgName(pname+msg_m.get("MSG_NAME"));
                    String msg_text = msg_m.get("MSG_NAME").replaceAll("patient_nameX",pname);
                    if( msg_text.indexOf(MONTHS) != -1 ){
                        int i = 0;
                        if( po.getGroupDate() != null ){
                            i = getMonths(po.getGroupDate() , new Date());
                        }
                        if( i == 0 ){
                            msg_text = msg_text.replaceAll(MONTHS,"几");
                        }else{
                            msg_text = msg_text.replaceAll(MONTHS,i+"");
                        }
                    }
                    ps.setMsgName(msg_text);
                    ps.setIsNeedreply(2);
                }
            }else{
//                ps.setMsgName(pname+msg_m.get("MSG_NAME"));
                String msg_text = msg_m.get("MSG_NAME").replaceAll("patient_nameX",pname);
                if( msg_text.indexOf(MONTHS) != -1 ){
                    int i = 0;
                    if( po.getGroupDate() != null ){
                        i = getMonths(po.getGroupDate() , new Date());
                    }
                    if( i == 0 ){
                        msg_text = msg_text.replaceAll(MONTHS,"几");
                    }else{
                        msg_text = msg_text.replaceAll(MONTHS,i+"");
                    }
                }
                ps.setMsgName(msg_text);
                ps.setIsNeedreply(2);
            }
            try {
                msgMapper.insert(ps);
            }catch (Exception e){
                log.info("实验组短信执行插入操作异常，continue继续循环");
                e.printStackTrace();
            }
        }
    }

    // 对照组的处理（5号 20号）
    public void sendMsgForDuizhaoZu(){
        Calendar cal = Calendar.getInstance();
        int i = cal.get(Calendar.DAY_OF_MONTH);
        log.info("进入对照组短信逻辑，当前是当月的："+i+"号");
        Date d = cal.getTime();
        Random random = new Random();
        String[] timeArr = sendTime.split(",");
        int length = timeArr.length;
        if( length < 2 ){
            timeArr = new String[]{"12"};
        }
        if( i == 5 || i== 20 ){ // 5号  20号发送短信   碰上周一问题已上报待回复
            List<PipCommPatient> list = msgMapper.getPatientByGroup("2"); // 获取对照组病人信息
            log.info("获取的对照组人员数量："+list.size());
            PipMsgSend ps = new PipMsgSend();
            for( PipCommPatient po : list ){
                try {
                    Map<String,String> m = msgMapper.getRandomMsgByType(po.getBelongGroup(),"M013");
                    if( m == null || m.size() == 0 ){
                        log.info("获取对照类短信失败，人员分组为："+po.getBelongGroup()+",短信类型为：M013");
                        continue;
                    }
                    ps.setMsgId(m.get("ID"));
//                    ps.setMsgName(m.get("MSG_NAME"));
                    String pname = "1".equals(po.getSex()) ? po.getPatientName()+"先生" : po.getPatientName()+"女士";
                    String msg_text = m.get("MSG_NAME").replaceAll("patient_nameX",pname);
                    if( msg_text.indexOf(MONTHS) != -1 ){
                        int j = 0;
                        if( po.getGroupDate() != null ){
                            j = getMonths(po.getGroupDate() , new Date());
                        }
                        if( j == 0 ){
                            msg_text = msg_text.replaceAll(MONTHS,"几");
                        }else{
                            msg_text = msg_text.replaceAll(MONTHS,j+"");
                        }
                    }
                    ps.setMsgName(msg_text);
                    log.info("对照组PID："+po.getPatientId()+",随机获取对照组短信ID："+m.get("ID")+",短信内容为："+
                            m.get("MSG_NAME")+",处理之后短信内容："+msg_text);
                    ps.setState(2);
                    ps.setMobile(po.getMobile());
                    ps.setPatientId(po.getPatientId());
                    ps.setLccCode(po.getLccCode());
                    ps.setCreateDate(new Date());
                    ps.setId(getPrimaryId());
                    ps.setIsNeedreply(2);
                    ps.setProjectId(po.getProjectId());
                    d.setHours(Integer.parseInt(timeArr[random.nextInt(timeArr.length)]));
                    ps.setSendtimePreinstall(d);
                    ps.setIsDelete(2);
                    msgMapper.insert(ps);
                }catch (Exception e){
                    log.info("对照组逻辑出现异常，捕获异常后continue循环");
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    // 随访提醒短信逻辑
    public void followViewMsg(){

        List<PipCommPatient> list = msgMapper.getPatientForView();
        if( list == null || list.size() == 0 ){
            log.info("暂无随访提醒短信人员");
            return;
        }
        log.info("随访提醒逻辑获取的人数："+list.size());
        String[] timeArr = sendTime.split(",");
        int length = timeArr.length;
        if( length < 2 ){
            timeArr = new String[]{"12"};
        }
        PipMsgSend ps = new PipMsgSend();
        Date d = Calendar.getInstance().getTime();
        Random random = new Random();
        for( PipCommPatient po : list ){
            try {
                if( po.getBelongGroup() == null || po.getBelongGroup().length() < 2 ){ // 分组字段异常
                    log.info("随访逻辑：患者分组异常，pid:"+po.getPatientId());
                    continue;
                }
                Map<String,String> m = msgMapper.getRandomMsgByType(po.getBelongGroup(),"M007"); // 随机获取随访提醒短信
                if( m == null || m.size() == 0 || StringUtils.isBlank(m.get("MSG_NAME")) ){
                    log.info("随机获取随访短信为空，人员分组为："+po.getBelongGroup()+",短信分类为：M007");
                    continue;
                }
                ps.setMsgId(m.get("ID"));
                String msg_context = m.get("MSG_NAME");
                log.info("随访提醒逻辑源短信："+msg_context);
                if( msg_context.indexOf(MONTHS) != -1 ){
                    int i = 0;
                    if( po.getGroupDate() != null ){
                        i = getMonths(po.getGroupDate() , new Date());
                    }
                    if( i == 0 ){
                        msg_context = msg_context.replaceAll(MONTHS,"几");
                    }else{
                        msg_context = msg_context.replaceAll(MONTHS,i+"");
                    }
                }
                log.info("随访提醒逻辑替换之'"+MONTHS+"'后短信："+msg_context);
                String pname = "1".equals(po.getSex()) ? po.getPatientName()+"先生" : po.getPatientName()+"女士";
                ps.setMsgName(msg_context.replaceAll("patient_nameX",pname));
//                ps.setMsgName(pname+msg_context);
                log.info("随访提醒PID："+po.getPatientId()+",随机获取随访提醒短信ID："+m.get("ID")+",短信内容为："+ps.getMsgName());
                ps.setState(2);
                ps.setMobile(po.getMobile());
                ps.setPatientId(po.getPatientId());
                ps.setLccCode(po.getLccCode());
                ps.setCreateDate(new Date());
                ps.setId(getPrimaryId());
                ps.setIsNeedreply(2);
                ps.setProjectId(po.getProjectId());
                d.setHours(Integer.parseInt(timeArr[random.nextInt(timeArr.length)]));
                ps.setSendtimePreinstall(d);
                ps.setIsDelete(2);
                msgMapper.insert(ps);
            }catch (Exception e){
                log.info("随访提醒逻辑出现异常，捕获异常后continue循环");
                e.printStackTrace();
                continue;
            }
        }
        log.info("随访逻辑结束，离开随访逻辑");
    }

    // 生日祝福短信
    public void MsgForBirthday(){
        List<PipCommPatient> list = msgMapper.getPatientForBirthday();
        if( list == null || list.size() == 0 ){
            log.info("暂无生日祝福短信人员");
            return;
        }
        log.info("生日祝福逻辑获取的人数："+list.size());

        String[] timeArr = sendTime.split(",");
        int length = timeArr.length;
        if( length < 2 ){
            timeArr = new String[]{"12"};
        }
        PipMsgSend ps = new PipMsgSend();
        Date d = Calendar.getInstance().getTime();
        Random random = new Random();
        for( PipCommPatient po : list ){
            try {
                Map<String,String> m = msgMapper.getRandomMsgByType(po.getBelongGroup(),"M008"); // 随机获取随访提醒短信
                if( m == null || m.size() == 0 ){
                    log.info("随机获取生日祝福短信失败,PID："+po.getPatientId()+",人员分组为："+po.getBelongGroup()+",短信分类为：M008");
                    continue;
                }
                ps.setMsgId(m.get("ID"));
                String pname = "1".equals(po.getSex()) ? po.getPatientName()+"先生" : po.getPatientName()+"女士";
                String msgText = m.get("MSG_NAME");
                msgText = msgText.replaceAll("patient_nameX",pname);
                if( msgText.indexOf(MONTHS) != -1 ){
                    int i = 0;
                    if( po.getGroupDate() != null ){
                        i = getMonths(po.getGroupDate() , new Date());
                    }
                    if( i == 0 ){
                        msgText = msgText.replaceAll(MONTHS,"几");
                    }else{
                        msgText = msgText.replaceAll(MONTHS,i+"");
                    }
                }
                ps.setMsgName(msgText);
                log.info("生日祝福PID："+po.getPatientId()+",随机获取生日祝福短信ID："+m.get("ID")+",短信内容为："+m.get("MSG_NAME")+
                "处理之后的短信内容为："+msgText);
                ps.setState(2);
                ps.setMobile(po.getMobile());
                ps.setPatientId(po.getPatientId());
                ps.setLccCode(po.getLccCode());
                ps.setCreateDate(new Date());
                ps.setId(getPrimaryId());
                ps.setIsNeedreply(2);
                ps.setProjectId(po.getProjectId());
                d.setHours(Integer.parseInt(timeArr[random.nextInt(timeArr.length)]));
                ps.setSendtimePreinstall(d);
                ps.setIsDelete(2);
                msgMapper.insert(ps);
            }catch (Exception e){
                log.info("生日祝福逻辑出现异常，捕获异常后continue循环");
                e.printStackTrace();
                continue;
            }
        }
    }

    // 主键生成策略
    public String getPrimaryId(){
        Long now = System.currentTimeMillis();
        int random = new Random().nextInt(900)+100; // 随机数 100-999范围
        return now.toString() + random;
    }

    /**
     * 查询当月需要回复短信数量  判断该条短信是否可以修改为询问短信
     *  pid 这个人 sysdate 这个月里 dbtype类型的 需要回复的 短信 发送了多少条
     *  小于count则返回true 否则是false  用以判断增加回复后缀的依据
     * @param pid
     * @param dbtype
     * @param count
     * @return
     */
    public boolean isNeedReply(String pid , String dbtype , int count){
        try {
            int i = msgMapper.isNeedReply(pid,dbtype) ;
            return count > i ;
        }catch (Exception e){
            log.info("查询需要回复短信数量时抛出异常，PID："+pid+",dbtype:"+dbtype+",count:"+count);
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 计算两个时间点相差月数
     * @param d1
     * @param d2
     * @return
     */
    public  int getMonths(Date d1, Date d2){
        DateTime dt1 = new DateTime(d1);
        DateTime dt2 = new DateTime(d2);
		Months m = Months.monthsBetween(dt1,dt2);
        // 相差月份
        int months = m.getMonths();
		dt1 = dt1.plusMonths(months);
		// 相差天数
		Days d = Days.daysBetween(dt1, dt2);
		int days = d.getDays();

		return (days >= 15) ? months+1 : months;
    }


    /**
     * 实验组获取随机一条短信   只适用实验组  因为涉及180天不重复
     * 如果180天内重复了那就随机再来一条不管时间了
     * @param dbtype
     * @param pid
     * @return
     */
    public Map<String,String> getRandomMsgByDbtype(String dbtype,String pid){
        DateTime dt = new DateTime(Calendar.getInstance());
        dt = dt.minusDays(180);
        Date d = new Date();
        Map<String,String> m = msgMapper.getRandomMsg(dbtype , pid , dt.toDate(),d);
        if( m != null && m.size() > 0 ){
            return m;
        }else{
            for( int i = 0 ; i < 6 ; i++ ){
                dt = dt.plusDays(30);
                log.info("180天不重复过滤失败,i="+i+"，返回随机短信，pid:"+pid+",dbtype:"+dbtype+",不重复短信起止时间："+dt.toString()+",开始时间："
                        +Calendar.getInstance().get(Calendar.YEAR)+"-"+( Calendar.getInstance().get(Calendar.MONTH)+1 )+"-"
                        +Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                m = msgMapper.getRandomMsg(dbtype , pid , dt.toDate(),d);
                if( m != null && m.size() > 0 ){
                    return m;
                }
            }
        }
        return msgMapper.getRandomMsg(dbtype , pid , null , null);
    }

    /**
     *  获取当前时间的周次
     * @return
     */
    public int getWeekMod(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR,8); // 东八区(TimeZone.getDefault().getRawOffset()通用一些，但是依赖环境变量)
        long l = cal.getTimeInMillis() + 3L*3600*24*1000; // 调整计算时间自周一开始
        int i = (int)((l/( 1000L * 3600 * 24 * 7))%4);
        return i;
    }

	/**
	 * 更新patient表中的sendType字段
	 */
	public void updateSendType() {
		patientMapper.updateSendTypeByUQS14();
		// patientMapper.updateSendTypeByUQS11();
	}
}
