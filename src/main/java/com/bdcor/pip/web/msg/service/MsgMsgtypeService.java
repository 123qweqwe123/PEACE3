package com.bdcor.pip.web.msg.service;

import java.util.List;

import com.bdcor.pip.web.msg.domain.MsgMsgtype;
import com.bdcor.pip.web.msg.filter.PipMsgMsgtypeFilter;

/**  
 * description:
 * @author huang cz 创建时间：2016年5月10日         
 */
public interface MsgMsgtypeService {
	/**
	 * 
	 * description:  查询列表
	 * @author huang cz
	 * @param filter
	 * @return   
	 */
	List<MsgMsgtype> msgTypeList(PipMsgMsgtypeFilter filter);

	/**
	 * 
	 * description:  新增
	 * @author huang cz
	 * @param msgType
	 * @return   
	 */
	int addMsgType(MsgMsgtype msgType);

	/**
	 * 
	 * description:  修改
	 * @author huang cz
	 * @param msgType
	 * @return   
	 */
	int updateMsgType(MsgMsgtype msgType);

	/**
	 * 
	 * description:  删除
	 * @author huang cz
	 * @param msgId
	 * @return   
	 */
	int deleteMsgType(String msgId);

	/**
	 * 
	 * description:  根据id查询
	 * @author huang cz
	 * @param msgId
	 * @return   
	 */
	MsgMsgtype getMsgTypeById(String msgId);
	
	/**
	 * 
	 * description:  根据类别编码查询
	 * @author huang cz
	 * @param msgId
	 * @return   
	 */
	MsgMsgtype getMsgtypeByCode(String typeCode);
}
