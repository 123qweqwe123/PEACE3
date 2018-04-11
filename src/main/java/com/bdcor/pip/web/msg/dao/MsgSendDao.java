package com.bdcor.pip.web.msg.dao;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.data.domain.PipCommPatient;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.MsgSendVo;
import com.bdcor.pip.web.msg.filter.MsgSendFilter;
import com.bdcor.pip.web.msg.filter.MsgSendNoFilter;

@MyBatisRepository
public interface MsgSendDao {

	List<MsgSendVo> list(MsgSendFilter filter);

	List<Map<String, String>> getPatient(@Param("lccCode")String lccCode);

	List<Map<String, String>> getMsgTypeList();
	/**
	 * 所有短信发送状态码及状态码对应值

	 * Map：
	 * 		report_code	状态码
	 * 		send_reason 状态码值
	 * @return
	 */
	List<Map<String, String>> getReportCode();
	
	List<Map<String, String>> getMsgListByType(@Param("type")String typeCode);

	int insertSend(Map<String, Object> sendMap);

	int updateSend(Map<String, Object> sendMap);

	int sendDelete(@Param("idArr")String[] idArr);
	
	List<Map<String, String>> getSendList(@Param("projectId")String projectId);

	void updateStateById(Map<String, String> msgMap);

	List<Map<String, Object>> getMsgInfo(@Param("status")String status);
	/**
	 * 
	 * description: 更新短信发送的状态 
	 * @author yangfeng  
	 * @param map   
	 * @update 2016年6月3日
	 */
	void updateMsgInfo(Map<String, Object> map);
	/**
	 * 
	 * description:  短信报表
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年6月6日
	 */
	List<Map<String, Object>> report(MsgSendFilter filter);

	List<Map<String, Object>> export(MsgSendNoFilter filter);

	// 获取新增加患者信息
	List<PipCommPatient> getNewPatient();

	List<Map<String, Object>> failReasorList(MsgSendFilter filter);

	int getCountByMobileAndType(@Param("mobile")String mobile,@Param("type")String type);
}
