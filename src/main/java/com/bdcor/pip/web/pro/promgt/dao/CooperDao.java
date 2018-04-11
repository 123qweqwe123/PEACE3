package com.bdcor.pip.web.pro.promgt.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.pro.promgt.domain.Region;
import com.bdcor.pip.web.pro.promgt.filter.RegionFilter;


@MyBatisRepository
public interface CooperDao {

	List<Region> listRegion(RegionFilter filter);

	void saveRegion(Region region);

	Region getRegionById(String regionId);

	void deleteLcc(String regionId);

	void updateRegion(Region region);


}
