package com.bdcor.pip.web.quality.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.dao.ExamineDao;
import com.bdcor.pip.web.quality.domain.Examine;
import com.bdcor.pip.web.quality.domain.ExamineFtp;
import com.bdcor.pip.web.quality.filter.ExamineFilter;
import com.bdcor.pip.web.quality.filter.ExamineFtpFilter;
import com.bdcor.pip.web.quality.service.ExamineService;

@Service
@Transactional
public class ExamineServiceImpl implements ExamineService {

	@Autowired
	private ExamineDao examineDao;

	@Override
	public List<Examine> getAllExamines(ExamineFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return examineDao.getAllExamines(filter);
	}

	@Override
	public Examine getExamineById(String id) {
		Examine examine = examineDao.getExamineById(id, Securitys.getUser().getCurrent_projectId());
		examine.setExamineDateStr(DateUtil.dateToString(examine.getExamineDate(), "yyyy-MM-dd"));
		examine.setExamineDate2Str(DateUtil.dateToString(examine.getExamineDate2(), "yyyy-MM-dd"));
		return examine;
	}

	@Override
	public void addExamine(Examine examine) {
		try{
			examine.setId(GenerateKey.getKey(GenerateKey.PREFIX_EXAMINE));
			examine.setProjectId(Securitys.getUser().getCurrent_projectId());
			examineDao.addExamine(examine);
		}catch(Exception e){
			throw new ServiceException("添加现场考核失败！", e);
		}
	}

	@Override
	public void updateExamine(Examine examine) {
		try{
			examine.setProjectId(Securitys.getUser().getCurrent_projectId());
			examineDao.updateExamine(examine);
		}catch(Exception e){
			throw new ServiceException("更新现场考核失败！", e);
		}
	}

	@Override
	public void delete(String id) {
		try{
			examineDao.delete(id, Securitys.getUser().getCurrent_projectId());
		}catch(Exception e){
			throw new ServiceException("删除现场考核失败！", e);
		}
	}

	@Override
	public String addExamineFtp(String oldFileName, String fileName,
			String ctxPath, String examineId) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		ExamineFtp exFtp = new ExamineFtp();
		exFtp.setId(GenerateKey.getKey(GenerateKey.PREFIX_EXAMINE));
		exFtp.setProjectId(projectId);
		exFtp.setCreateBy(Securitys.getUserId());
		exFtp.setCreateDate(new Date());
		exFtp.setExamineId(examineId);
		exFtp.setFileName(fileName);
		exFtp.setOldFileName(oldFileName);
		exFtp.setFilePath(ctxPath);
		
		try{
			examineDao.addExamineFtp(exFtp);
			
			Integer fileNo = examineDao.getExamineFileNo(projectId, examineId);
			if(fileNo==null) fileNo = 0;
			//更新Examine文件数
			//examineDao.updateExamineFileNo(String.valueOf(fileNo + 1), examineId, projectId);
			examineDao.updateExamineFileNo(String.valueOf(fileNo), examineId, projectId);
		}catch(Exception e){
			throw new ServiceException("删除现场考核失败！", e);
		}
		return exFtp.getId();
	}

	@Override
	public ExamineFtp getExamineFtp(String examineFtpId, String fileName) {
		return examineDao.getExamineFtp(Securitys.getUser().getCurrent_projectId(), examineFtpId, fileName);
	}

	@Override
	public Boolean deleteFile(String examineFtpId, String fileName, String examineId) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		try{
			examineDao.deleteFile(projectId, examineFtpId, fileName);
			examineDao.updateExamineFileNoForDelete(projectId, examineId);
		}catch(Exception e){
			throw new ServiceException("更新文件数量失败！", e);
		}
		return true;
	}

	@Override
	public Boolean checkFileNameExists(String id, String fileName) {
		ExamineFtp exFtp = examineDao.getExamineFtp(Securitys.getUser().getCurrent_projectId(), id, fileName);
		if(exFtp!=null) return true;
		return false;
	}

	@Override
	public List<ExamineFtp> getAllExamineFtps(ExamineFtpFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return examineDao.getAllExamineFtps(filter);
	}

//	@Override
//	public List<ExamineFtp> getExamineFtps(String examineId) {
//		return examineDao.getExamineFtps(Securitys.getUser().getCurrent_projectId(), examineId);
//	}
	
}
