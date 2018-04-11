package com.bdcor.pip.web.spem.utils;

import com.bdcor.pip.core.utils.SpringContextHolder;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;

public class SysCodeToNameUtil {
	
	public static String getProjectNameByCode(String code){
		
		
		return SpringContextHolder.getBean(ProjectMgtService.class).getProjectById(code).getProjectName();
		
	}
	
	
    public static String getLccNameByCode(String code){
		
		
		return SpringContextHolder.getBean(LccService.class).getLcc(code).getLccName();
		
	}
	
}

