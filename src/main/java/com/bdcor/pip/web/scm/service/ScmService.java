package com.bdcor.pip.web.scm.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.bdcor.pip.web.scm.filter.ScmFilter;

public interface ScmService {

	Map<String, String> getBoxMap(String boxCode);

	List<Map<String, String>> getTubeList(String boxCode);

	void save(HttpServletRequest request)throws Exception;

	List<Map<String, Object>> boxList(ScmFilter filter);

	// 校验管号是否有效可用
	boolean checkTubeCode(String tube_code , String box_code);
}
