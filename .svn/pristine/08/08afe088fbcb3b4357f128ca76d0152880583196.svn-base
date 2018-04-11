package com.bdcor.pip.web.msg.dao;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.msg.domain.PipMsgSend;
import com.bdcor.pip.web.msg.domain.PipMsgSendExample;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
@MyBatisRepository
public interface PipMsgSendMapper {
    int countByExample(PipMsgSendExample example);

    int deleteByExample(PipMsgSendExample example);

    int insert(PipMsgSend record);

    int insertSelective(PipMsgSend record);

    List<PipMsgSend> selectByExample(PipMsgSendExample example);

    int updateByExampleSelective(@Param("record") PipMsgSend record, @Param("example") PipMsgSendExample example);

    int updateByExample(@Param("record") PipMsgSend record, @Param("example") PipMsgSendExample example);
    /**
     * 
     * description: 查找短信是否存在已经发送了且未删除的欢迎类的信息 
     * @author yangfeng  
     * @param map
     * @return   
     * @update 2016年5月12日
     */
	int selectMsgTypeByPatient(Map<String, Object> map);

    /**
     * @param m  * @Param("pid")String patient_id,
                 * @Param("ptype") String belonggroup,
                 * @Param("is_smoke")String is_smoke
     * @return
     */
    String getMsgDbType(Map<String,String> m);

    /**
     * 随机获取一条短信
     * @param dbtype
     * @param pid
     * @param startdate
     * @param endDate
     * @return
     */
    Map<String,String> getRandomMsg(@Param("dbtype") String dbtype , @Param("pid") String pid ,
                                    @Param("startdate") Date startdate , @Param("enddate") Date endDate);

    List<PipCommPatient> getPatientByGroup(@Param("group") String group);

    Map<String,String> getRandomMsgByType(@Param("group") String group ,@Param("mtype") String mtype );

    List<PipCommPatient> getPatientForView();

    List<PipCommPatient> getPatientForBirthday();

    int isNeedReply(@Param("pid") String pid,@Param("dbtype") String dbtype);

    int setIsreply(@Param("mobile")String mobile);
}