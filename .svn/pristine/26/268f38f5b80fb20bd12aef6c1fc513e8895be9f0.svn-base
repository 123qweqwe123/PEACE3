package com.bdcor.pip.web.quality.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.quality.domain.Examine;
import com.bdcor.pip.web.quality.domain.ExamineFtp;
import com.bdcor.pip.web.quality.filter.ExamineFilter;
import com.bdcor.pip.web.quality.filter.ExamineFtpFilter;

@MyBatisRepository
public interface ExamineDao {

	List<Examine> getAllExamines(ExamineFilter filter);

	Examine getExamineById(@Param(value = "id") String id, @Param(value="projectId") String projectId);

	void addExamine(Examine examine);

	void updateExamine(Examine examine);

	void delete(@Param(value = "id") String id, @Param(value="projectId") String projectId);

	void addExamineFtp(ExamineFtp exFtp);

	Integer getExamineFileNo(@Param(value = "projectId") String projectId, @Param(value = "examineId") String examineId);

	void updateExamineFileNo(@Param(value = "fileNo") String fileNo, @Param(value = "examineId") String examineId, @Param(value = "projectId") String projectId);

	ExamineFtp getExamineFtp(@Param(value = "projectId") String projectId, @Param(value = "examineFtpId") String examineFtpId,
			@Param(value = "fileName") String fileName);

	void deleteFile(@Param(value = "projectId") String projectId,@Param(value = "examineFtpId") String examineFtpId, @Param(value = "fileName") String fileName);

	void updateExamineFileNoForDelete(@Param(value = "projectId") String projectId, @Param(value = "examineId") String examineId);

	//List<ExamineFtp> getExamineFtps(@Param(value = "projectId") String projectId, @Param(value = "examineId") String examineId);

	List<ExamineFtp> getAllExamineFtps(ExamineFtpFilter filter);

}
