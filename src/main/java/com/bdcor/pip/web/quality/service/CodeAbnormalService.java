package com.bdcor.pip.web.quality.service;

import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.quality.domain.CodeAbnormalVo;
import com.bdcor.pip.web.quality.filter.CodeAbnormalFilter;


public interface CodeAbnormalService {

	List<CodeAbnormalVo> list(CodeAbnormalFilter filter);

	Map<String, String> getReportInfo(Map<String, String> paramMap);

	int errorHandle(Map<String, String> paramMap);


}
