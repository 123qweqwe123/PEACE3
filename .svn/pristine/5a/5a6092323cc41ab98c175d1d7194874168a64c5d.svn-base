package com.bdcor.pip.web.pro.promgt.service;

import java.util.List;

import com.bdcor.pip.web.pro.promgt.domain.Adjunct;
import com.bdcor.pip.web.pro.promgt.domain.AdjunctType;
import com.bdcor.pip.web.pro.promgt.filter.AdjunctFilter;


public interface FileService {

	void saveAdjunct(String projectId, String fileName, String ctxPath, String type, String fileDesc);

	List<Adjunct> listAdjunct(AdjunctFilter filter);

	void updateDownCount(Adjunct adjunct);

	void deleteAdjunct(String adjunctId);

	List<AdjunctType> getAdjunctType();

}
