package com.bdcor.pip.web.msg.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.MsgMsgtype;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtypeFilter;

/**  
 * description:
 * @author huang cz 创建时间：2016年5月10日         
 */
@MyBatisRepository
public interface PipMsgMsgtypeDao {
	/**
	 * @param filter
	 * @return
	 */
	List<MsgMsgtype> msgTypeList(PipMsgMsgtypeFilter filter);
	
	/**
	 * @param msgTmp
	 * @return
	 */
	int addMsgType(MsgMsgtype msgType);
	
	/**
	 * @param msgTmp
	 * @return
	 */
	int updateMsgType(MsgMsgtype msgType);
	
	/**
	 * @param msgId
	 * @return
	 */
	int deleteMsgType(String id);

	/**
	 * @param msgId
	 * @return
	 */
	MsgMsgtype getMsgTypeById(String id);

	/**
	 * @param typeCode
	 * @return
	 */
	MsgMsgtype getMsgtypeByCode(String typeCode);
}