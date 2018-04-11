/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.msg.service 
 */

package com.bdcor.pip.web.msg.service;

import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.msg.domain.PipMsgSend;

/**  
 * description:  
 * @author yangfeng 创建时间：2016年5月11日         
 */
public interface MsgSendRuleService
{
	public  void needSendMsg();
	/**
	 * 
	 * description:生成一周的短信  
	 * @author yangfeng  
	 * @param pa   
	 * @update 2016年6月17日
	 */
	public void singleAddMsg(PipCommPatient pa);
	public void send();
	/**
	 * 
	 * description:  发送欢迎类信息
	 * @author yangfeng  
	 * @param patient   
	 * @update 2016年6月15日
	 */
	public void sendWelcomeMsg4Single(PipCommPatient patient);
	public void createWeekMsg(PipCommPatient patient);
	public PipMsgSend addSendMsg(PipCommPatient patient, String string);
}

