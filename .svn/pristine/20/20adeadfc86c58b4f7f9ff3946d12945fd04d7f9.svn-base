package com.bdcor.pip.web.msg.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.msg.dao.MsgSendDao;
import com.bdcor.pip.web.msg.domain.MsgSendVo;
import com.bdcor.pip.web.msg.filter.MsgSendFilter;
import com.bdcor.pip.web.msg.filter.MsgSendNoFilter;
import com.bdcor.pip.web.msg.service.MsgSendService;
import com.bdcore.webservice.client.MsgClient;
import com.bdcore.webservice.client.SendResultCallback;
import com.bdcore.webservice.client.SendStateInvoke;
import com.bdcore.webservice.client.bean.MsgOfSend;

@Service
public class MsgSendServiceImpl implements MsgSendService {
	
	@Autowired
	private MsgSendDao msgSendDao;
	
	@Autowired
	private MobileClient mobileClient;

	@Autowired
	private MsgSendRuleServiceImpl sendRuleService;

	@Value("${project_id}")
	private String projectId;
	
	private static final Object sendLock = new Object();
	private static final ReentrantLock LOCK = new ReentrantLock(true);
	
	final Logger logger= LoggerFactory.getLogger(MsgSendServiceImpl.class);

	@Override
	public List<MsgSendVo> list(MsgSendFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		return msgSendDao.list(filter);
	}

	@Override
	public List<Map<String, String>> getPatient(String lccCode) {
		return msgSendDao.getPatient(lccCode);
	}

	@Override
	public List<Map<String, String>> getMsgTypeList() {
		return msgSendDao.getMsgTypeList();
	}

	@Override
	public List<Map<String, String>> getMsgListByType(String typeCode) {
		return msgSendDao.getMsgListByType(typeCode);
	}

	@Override
	public int sendSave(Map<String,Object> sendMap) {
		sendMap.put("PROJECT_ID", Securitys.getCurrentProject());
		if(sendMap.get("ID")==null || sendMap.get("ID").toString().trim().length()==0){
			sendMap.put("CREATE_BY", Securitys.getUserId());
			sendMap.put("CREATE_DATE", new Date());
			return msgSendDao.insertSend(sendMap);
		}else{
			return msgSendDao.updateSend(sendMap);
		}
	}

	@Override
	public int sendDelete(String[] idArr) {
		return msgSendDao.sendDelete(idArr);
	}
	/**
	 * 短信发送
	 * TODO  实现类 
	 * @see com.bdcor.pip.web.msg.service.MsgSendService#send()
	 */
	@Override
	public void send() {
		synchronized (sendLock) {
			MsgClient client = mobileClient.getClient();
			List<Map<String,String>> sendList =  msgSendDao.getSendList(projectId);
			if(sendList != null && sendList.size() > 0){
				for(final Map<String,String> msgMap : sendList){
					MsgOfSend msg = new MsgOfSend();
					msg.setLinkId(msgMap.get("ID"));
					msg.setTel(msgMap.get("MOBILE"));
					msg.setContent(msgMap.get("MSG_NAME"));
					client.send(msg, new SendResultCallback() {
						@Override
						public void callback(int result, String resultMsg, MsgOfSend msg) {
							if(result == 1){ // 调用接口成功
								logger.info("调用短信发送接口成功");
								msgMap.put("STATE","1");
								msgSendDao.updateStateById(msgMap);
							}
							else{
								logger.info("调用短信发送接口失败，调用返回值："+result);
								msgMap.put("STATE","2");
								msgSendDao.updateStateById(msgMap);
							}
						}
					});
				}
			}
			sendRuleService.welcomeMsg();
		}
		
	}
	/**
	 * 对于已经提交发送的信息 接受短信状态
	 * description:  
	 * @author yangfeng     
	 * @update 2016年6月2日
	 */
	@PostConstruct
	private void sendStatusCallback(){
		MsgClient client = mobileClient.getClient();
		client.sendStateInvoke(new SendStateInvoke()
		{
			@Override
			public void invoke(String id, String status)
			{
				final  ReentrantLock loc = LOCK;
				try
				{
					loc.lockInterruptibly();
					//status:"渠道id|satau"
					logger.info("================接受短信发送状态 start");
					logger.info("本地短信id ：" +id +" 短信状态 :" +status);
					logger.info("================接受短信发送状态 end");
					String msg ="";
					String msgReason="";
					String statusF ="";

					if(status.indexOf("|")!=-1){
						String[] split = status.split("\\|");
                        if( split.length > 1 ){
                            statusF = split[1];
                        }else{
                            statusF =status;
                        }
					}
					else{
						statusF =status;
					}

					if(statusF.equals("0")||statusF.equals("DELIVRD")){
						msg="1";
					}
					else{
						msg="2";
					}

                    List <Map<String, Object>> list=msgSendDao.getMsgInfo(statusF);
                    if(list !=null && list.size()>0){
                        msgReason=list.get(0).get("MSG_INFO").toString();
                    }
                    else{
                        msgReason="未知原因";
                    }

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("SEND_RESULT", msg);
					map.put("SEND_REASON", msgReason);
					map.put("ID" , id);
                    map.put("STATE" , msg);
					msgSendDao.updateMsgInfo(map);
				} catch (InterruptedException e){
					e.printStackTrace();
				} finally{
					loc.unlock();
				}
			}
		});
	}
	
	@Override
	public void testSend(String mobile) {
		MsgClient client = mobileClient.getClient();
		MsgOfSend msg = new MsgOfSend();
		msg.setTel(mobile);
		msg.setContent("测试短信");
		
		Date d1 =new Date();
		for(int i =0;i<100;i++){
			client.send(msg, new SendResultCallback() {
				@Override
				public void callback(int result, String resultMsg, MsgOfSend msg) {
					if(result==1){
						System.out.println("发送成功");
					}
				}
			});
		}
		System.out.println(new Date().getTime()-d1.getTime());
	}

	@Override
	public List<Map<String, Object>> report(MsgSendFilter filter)
	{
		return msgSendDao.report(filter);
	}

	@Override
	public List<Map<String, Object>> sendReport(MsgSendNoFilter filter)
	{
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUserId());
		return msgSendDao.export(filter);
	}

	@Override
	public List<Map<String, Object>> failReasorList(MsgSendFilter filter) {
		List<Map<String, Object>> list = msgSendDao.failReasorList(filter);
		return list;
	}

	@Override
	public List<Map<String, String>> getReportCode() {
		return msgSendDao.getReportCode();
	}

}
