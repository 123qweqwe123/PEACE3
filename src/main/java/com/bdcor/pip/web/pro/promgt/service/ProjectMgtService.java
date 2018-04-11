package com.bdcor.pip.web.pro.promgt.service;

import java.util.List;

import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.filter.ProjectFilter;


public interface ProjectMgtService {

	List<Project> getAllProjects(ProjectFilter filter);

	String addProject(Project project);

	void updateProject(Project project);

	Project getProjectById(String projectId);

	void delete(String projectId);

	void doStartStop(String projectId, String type);

	Boolean checkNameExists(String projectName);

}
