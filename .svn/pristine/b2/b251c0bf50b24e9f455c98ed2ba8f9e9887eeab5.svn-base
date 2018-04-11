package com.bdcor.pip.web.pro.promgt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.filter.ProjectFilter;


@MyBatisRepository
public interface ProjectMgtDao {

	List<Project> getAllProjects(ProjectFilter filter);

	void addProject(Project project);

	void updateProject(Project project);

	Project getProjectById(@Param("projectId") String projectId);

	void delete(@Param("projectId") String projectId);

	void doStartStop(@Param("projectId") String projectId, @Param("type") String type);

	Project checkNameExists(String projectName);

	void updateIsStarted(String projectId);

}
