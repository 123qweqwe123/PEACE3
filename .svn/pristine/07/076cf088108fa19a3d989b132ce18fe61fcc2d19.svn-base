package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.PipMmsStorehouse;

@MyBatisRepository
public interface PipMmsStorehouseMapper {
	
	public List<PipMmsStorehouse> getAllPipMmsStorehouse(PipMmsStorehouse pms);
	

	public List<PipMmsStorehouse> getExportStockCode(@Param("lccCode")String lccCode);
	

}