package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.MaterStorehouse;
import com.bdcor.pip.web.material.supp.filter.MaterStorehouseFilter;

@MyBatisRepository
public interface MaterStorehouseMapper {
	
	int insert(MaterStorehouse record);
	int update(MaterStorehouse record);
	int delete(MaterStorehouse record);
	MaterStorehouse getMaterStorehouse(MaterStorehouse materStorehouse);
	List<MaterStorehouse> getAllMaterStorehoust(MaterStorehouseFilter filter);
	String selectMaxStockCode(MaterStorehouse materStorehouse);
}