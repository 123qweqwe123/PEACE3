package com.bdcor.pip.web.sys.tablecreate.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.sys.tablecreate.domain.Answer;
import com.bdcor.pip.web.sys.tablecreate.domain.QuestionNaire;

@MyBatisRepository
public interface PaperTableCreateDao {

	List<QuestionNaire> getQnList();
	
	void execUpdateSql(String sql);
	
	void execInsertSql(String sql);
	
	int checkHasInsert(@Param("patientId")String patientId,@Param("qnId") String qnId);

	List<String> getPIdList(@Param("qnId")String qnId,@Param("start")int start,@Param("end")int end , @Param("table")String table_name);

	List<Answer> getAnswerList(@Param("qnId")String qnId, @Param("patientId")String patientId);

}
