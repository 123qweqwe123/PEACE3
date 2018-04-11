package com.bdcor.pip.web.msg.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.msg.domain.MsgReplyVo;
import com.bdcor.pip.web.msg.filter.MsgReplyFilter;

@MyBatisRepository
public interface MsgReplyDao {

	List<MsgReplyVo> list(MsgReplyFilter filter);

	Map<String, Object> getPatient(@Param("projectId")String projectId, @Param("lccCode")String lccCode,@Param("patientId")String patientId);

	List<Map<String, Object>> showAllMsg(@Param("projectId")String projectId, @Param("lccCode")String lccCode,@Param("patientId")String patientId);

	List<Map<String, Object>> getPatientByMobile(@Param("projectId")String projectId,@Param("mobile")String mobile);

	Map<String, Object> getSend(@Param("projectId")String projectId,@Param("patientId")String patientId,@Param("replyTime")Date replyTime);

	void insertReply(Map<String, Object> replyVo);

	int checkRepeat(Map<String, Object> replyVo);

	void updateReportCode(@Param("sendId")String sendId,@Param("reportCode")String reportCode);

	// 查询需要回复的短信
	List<Map<String, Object>> getMsgForReply();
    //
	Map<String,String> getReplyMsg(@Param("dbtype")String dbtype);

	int updateReplySate(@Param("replytime")Date a,@Param("mobile") String b);
	List<Map<String, Object>> getMaplist(MsgReplyFilter filter);

}
