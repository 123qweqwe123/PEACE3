package com.bdcor.pip.web.msg.service.impl;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.msg.dao.MsgReplyDao;
import com.bdcor.pip.web.msg.dao.PipMsgSendMapper;
import com.bdcor.pip.web.msg.domain.MsgReplyVo;
import com.bdcor.pip.web.msg.domain.PipMsgSend;
import com.bdcor.pip.web.msg.filter.MsgReplyFilter;
import com.bdcor.pip.web.msg.service.MsgReplyService;
import com.bdcore.webservice.client.MsgClient;
import com.bdcore.webservice.client.RecInvoke;
import com.bdcore.webservice.client.SendStateInvoke;
import com.bdcore.webservice.client.bean.MsgOfRec;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MsgReplyServiceImpl implements MsgReplyService {

    private static final Logger log = LoggerFactory.getLogger(MsgReplyServiceImpl.class);

	@Autowired
	private MsgReplyDao msgReplyDao;
	
	@Value("${project_id}")
	private String projectId;
	
	private static final Object lock = new Object();
	
	@Autowired
	private MobileClient mobileClient;

    @Autowired
    private PipMsgSendMapper pipMsgSendMapper;

    @Value("${sendTime}")
    public String sendTime;

    @Override
	public List<MsgReplyVo> list(MsgReplyFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return msgReplyDao.list(filter);
	}

	@Override
	public Map<String, Object> getPatient(String lccCode, String patientId) {
		
		return msgReplyDao.getPatient(Securitys.getCurrentProject(),lccCode,patientId);
	}

	@Override
	public List<Map<String, Object>> showAllMsg(String lccCode, String patientId) {
		// TODO Auto-generated method stub
		return msgReplyDao.showAllMsg(Securitys.getCurrentProject(),lccCode,patientId);
	}
	
	@PostConstruct
	private void receive(){
		MsgClient client = mobileClient.getClient();
		client.recInvoke(new RecInvoke() {
			@Override
			public void invoke(MsgOfRec rec) {
				try{
					Map<String,Object> pMap = null;
					List<Map<String,Object>> pMapList = msgReplyDao.getPatientByMobile(projectId,rec.getTel());
					Map<String,Object> replyVo = new HashMap<String,Object>();
					if(pMapList != null && pMapList.size()>0){
						if(pMapList.size()==1){
							pMap = pMapList.get(0);
						}else{
							replyVo.put("PATIENT_ID","MOBILE不唯一");
						}
					}
					
					replyVo.put("PROJECT_ID", projectId);
					replyVo.put("MSG_NAME", rec.getContent());
//					Date replyTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rec.getDateTime());
					log.info("回复短信携带的短信服务器时间："+rec.getDateTime()+",当前业务系统时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//					replyVo.put("REPLY_TIME",replyTime);
					replyVo.put("REPLY_TIME", new Date());
					replyVo.put("MOBILE",rec.getTel());
					if(pMap != null){
						replyVo.put("LCC_CODE",pMap.get("LCC_CODE"));
						replyVo.put("PATIENT_ID",pMap.get("PATIENT_ID"));
						if(rec.getContent()!=null){
							if(rec.getContent().trim().toUpperCase().equals("TD")){
								//暂无操作
							}else{
//								Map<String,Object> sendMap = msgReplyDao.getSend(projectId,pMap.get("PATIENT_ID").toString(),replyTime);
//								if(sendMap != null){
//									replyVo.put("SEND_ID",sendMap.get("SEND_ID"));
//								}
							}
							
						}
					}
					synchronized (lock) {//会重复接收
						if(msgReplyDao.checkRepeat(replyVo)==0){
							msgReplyDao.insertReply(replyVo);
							pipMsgSendMapper.setIsreply(rec.getTel()); // 更新短信状态为已回复
						}
					}
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		client.sendStateInvoke(new SendStateInvoke() {
			@Override
			public void invoke(String sendId, String reportCode) {
				msgReplyDao.updateReportCode(sendId,reportCode);
			}
		});
	}

	@Override
	public List<Map<String, Object>> getMaplist(MsgReplyFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return msgReplyDao.getMaplist(filter);
	}



    // 回复信息校验回复短信
    @Transactional
	public void SetReplyMsg(){
        PipMsgSend po = new PipMsgSend();
		List<Map<String,Object>> l = msgReplyDao.getMsgForReply();
        log.info("查询需回复信息数："+l.size());
        if( l == null || l.size() == 0 ){
            return;
        }
        String dbtype="",msg_type="", group = "";
        for( Map<String,Object> m : l ){
            String info = m.get("MSG_NAME") == null ? "" : m.get("MSG_NAME").toString();
            group = m.get("BELONG_GROUP") == null ? "" : m.get("BELONG_GROUP").toString();
            msg_type = m.get("MSG_TYPE_CODE") == null ? "" : m.get("MSG_TYPE_CODE").toString();
            if(StringUtils.isNotBlank(group)
                    && group.length() >=2
                    && "1".equals(group.substring(1,2)))
            { // 实验组人员
                if( msg_type.contains("M002") ){ // 药物类
                    if( "1".equals(info) ){
                        dbtype = "R001";
                    }else if( "2".equals(info) ){
                        dbtype = "R002";
                    }else{
                        dbtype = "R005";
                    }
                }else if(msg_type.contains("M003") || msg_type.contains("M011") ){// 高血压类 血糖类
                    if( "3".equals(info) ){
                        dbtype = "R003";
                    }else if( "4".equals(info) ){
                        dbtype = "R004";
                    }else{
                        dbtype = "R006";
                    }
                }
            }else{
                log.info("查询到的信息不符合条件,手机号："+m.get("MOBILE")+",PID："+m.get("PATIENT_ID"));
                continue;
            }
            // 插入待发送短信
            Map<String,String> msg_m =  msgReplyDao.getReplyMsg(group+"_"+dbtype);
            if( msg_m == null || msg_m.size() == 0 ){
                log.info("获取回复短信失败，程序continue，PID："+
						m.get("PATIENT_ID") == null ? "" : m.get("PATIENT_ID").toString()
                +",group:"+group+","+dbtype);
                continue;
            }

            try {
                Calendar cal = Calendar.getInstance();
                String[] timeArr = sendTime.split(",");
                int length = timeArr.length;
                cal.set(Calendar.MINUTE,0);
                cal.set(Calendar.SECOND,0);
                Date d = cal.getTime();
                d.setHours( Integer.parseInt(timeArr[new Random().nextInt(length)]));
                po.setSendtimePreinstall(d); // 预发送时间
                po.setPatientId(m.get("PATIENT_ID").toString());
                po.setMobile( m.get("MOBILE").toString());
                String sex = m.get("SEX").toString().equals("1") ? "先生," : "女士,";
                sex = m.get("PATIENT_NAME")+sex;
                po.setMsgName(sex+msg_m.get("MSG_NAME"));
                po.setSendtimePreinstall(new Date());
                po.setLccCode(m.get("LCC_CODE").toString());
                po.setProjectId(m.get("PROJECT_ID").toString());
                po.setMsgId(msg_m.get("MSG_ID").toString());
                Long now = System.currentTimeMillis();
                int random = new Random().nextInt(900)+100;
                po.setId(now.toString() + random);
                po.setCreateDate(new Date());
                log.info("短信记录入库,短信ID："+po.getMsgId());
                pipMsgSendMapper.insertSelective(po);

                // 然后更新回复短信的状态
                msgReplyDao.updateReplySate((Date)m.get("REPLY_TIME"), m.get("MOBILE").toString());
            }catch (Exception e){
                log.info("短信回复功能出现异常："+e.getMessage());
                continue;
            }
        }

	}

}
