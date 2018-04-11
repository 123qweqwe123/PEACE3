package com.bdcor.pip.web.pro.promgt.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.dao.LccDao;
import com.bdcor.pip.web.pro.promgt.dao.LccUserDao;
import com.bdcor.pip.web.pro.promgt.dao.ProjectMgtDao;
import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.filter.ProjectFilter;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;

@Service
@Transactional
public class ProjectMgtServiceImpl implements ProjectMgtService {
	
	@Autowired
	private ProjectMgtDao projectMgtDao;
	@Autowired
	private LccDao lccDao;
	@Autowired
	private LccUserDao lccUserDao;

	@Override
	public List<Project> getAllProjects(ProjectFilter filter) {
		filter.setCreator(Securitys.getUserId());
		return projectMgtDao.getAllProjects(filter);
	}

	@Override
	public String addProject(Project project) {
		try{
			//String projectId = Identities.uuid();
			//String projectId = GenerateKey.getKey(GenerateKey.PREFIX_PROJECT);
			String projectId = GenerateKey.getKey(GenerateKey.PREFIX_PROJECT_LIXIANG);
			project.setProjectId(projectId);
			project.setCreator(Securitys.getUserId());
			projectMgtDao.addProject(project);
			return projectId;
		}catch(Exception e){
			throw new ServiceException("保存项目信息失败", e);
		}
	}

	@Override
	public void updateProject(Project project) {
		try{
			projectMgtDao.updateProject(project);
			if(project.getStatus()==1){
				lccDao.updateStatusForLcc(project.getProjectId(), String.valueOf(1));
				lccUserDao.updateStatusForLccUser(project.getProjectId(), String.valueOf(1));
			}else{
				lccDao.updateStatusForLcc(project.getProjectId(), String.valueOf(2));
				lccUserDao.updateStatusForLccUser(project.getProjectId(), String.valueOf(2));
			}
		}catch(Exception e){
			throw new ServiceException("更新项目信息失败！", e);
		}
	}

	@Override
	@CacheEvict(value = "sysCache", key = "#projectId+'getProjectById'")
	public Project getProjectById(String projectId) {
		SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
		Project project = projectMgtDao.getProjectById(projectId);
		if(project!=null){
			project.setBeginTimeString(ff.format(project.getBeginTime()));
			project.setEndTimeString(ff.format(project.getEndTime()));
		}
		return project;
	}

	@Override
	public void delete(String projectId) {
		try{
			projectMgtDao.delete(projectId);
		}catch(Exception e){
			throw new ServiceException("删除失败！", e);
		}
	}

	@Override
	public void doStartStop(String projectId, String type) {
		try{
			projectMgtDao.doStartStop(projectId, type);
			if("start".equals(type)){
				lccDao.updateStatusForLcc(projectId, String.valueOf(1));
				lccUserDao.updateStatusForLccUser(projectId, String.valueOf(1));
				projectMgtDao.updateIsStarted(projectId);
			}else{
				lccDao.updateStatusForLcc(projectId, String.valueOf(2));
				lccUserDao.updateStatusForLccUser(projectId, String.valueOf(2));
			}
		}catch(Exception e){
			throw new ServiceException("", e);
		}
	}

	@Override
	public Boolean checkNameExists(String projectName) {
		Project project = projectMgtDao.checkNameExists(projectName);
		if(project==null) return false;
		return true;
	}

}
