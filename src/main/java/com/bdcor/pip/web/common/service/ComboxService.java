package com.bdcor.pip.web.common.service;

import com.bdcor.pip.client.vo.paper.Paper;

public interface ComboxService {

	String getDict(String code, String prevCode, String q, int limit);

	String getDictName(Paper qn, String questionSetId, String questionId,
			String resultId, String resutlStr);
	String getAreaName(String vCode);

	String getAutoData( String q , String limit , String type );

	String getAutoDoctor( String q , String limit , String lccCode );

	}
