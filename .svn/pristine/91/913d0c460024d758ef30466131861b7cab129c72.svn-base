package com.bdcor.pip.web.quality.service;

import java.util.List;

import com.bdcor.pip.web.quality.domain.Examine;
import com.bdcor.pip.web.quality.domain.ExamineFtp;
import com.bdcor.pip.web.quality.filter.ExamineFilter;
import com.bdcor.pip.web.quality.filter.ExamineFtpFilter;

public interface ExamineService {

	List<Examine> getAllExamines(ExamineFilter filter);

	Examine getExamineById(String id);

	void addExamine(Examine examine);

	void updateExamine(Examine examine);

	void delete(String id);

	String addExamineFtp(String oldFileName, String fileName, String ctxPath, String examineId);

	ExamineFtp getExamineFtp(String examineFtpId, String fileName);

	Boolean deleteFile(String examineFtpId, String fileName, String examineId);

	Boolean checkFileNameExists(String id, String fileName);

	//List<ExamineFtp> getExamineFtps(String examineId);

	List<ExamineFtp> getAllExamineFtps(ExamineFtpFilter filter);

}
