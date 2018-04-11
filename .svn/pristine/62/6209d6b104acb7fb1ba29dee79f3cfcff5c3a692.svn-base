package com.bdcor.pip.web.quality.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.quality.dao.CodeAbnormalDao;
import com.bdcor.pip.web.quality.domain.CodeAbnormalVo;
import com.bdcor.pip.web.quality.filter.CodeAbnormalFilter;
import com.bdcor.pip.web.quality.service.CodeAbnormalService;

@Service
@Transactional
public class CodeAbnormalServiceImpl implements CodeAbnormalService {

	@Autowired
	private CodeAbnormalDao codeAbnormalDao;

	@Override
	public List<CodeAbnormalVo> list(CodeAbnormalFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUser().getId());
		return codeAbnormalDao.list(filter);
	}

	@Override
	public Map<String, String> getReportInfo(Map<String, String> paramMap) {
		paramMap.put("projectId", Securitys.getUser().getCurrent_projectId());
		paramMap.put("userId", Securitys.getUser().getId());
		return codeAbnormalDao.getReportInfo(paramMap);
	}

	@Override
	public int errorHandle(Map<String, String> paramMap) {
		int c = 0;
		try{
			c=codeAbnormalDao.errorHandle(paramMap);
		}catch (Exception e) {
			e.printStackTrace();
			return c;
		}
		return c;
	}
}
