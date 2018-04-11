package com.bdcor.pip.web.pro.promgt.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.pro.promgt.domain.Adjunct;
import com.bdcor.pip.web.pro.promgt.domain.AdjunctType;
import com.bdcor.pip.web.pro.promgt.filter.AdjunctFilter;


@MyBatisRepository
public interface FileDao {

	void saveAdjunct(Adjunct adjunct);

	List<Adjunct> listAdjunct(AdjunctFilter filter);

	void updateDownCount(Adjunct adjunct);

	void deleteAdjunct(String adjunctId);

	List<AdjunctType> getAdjunctType();

}
