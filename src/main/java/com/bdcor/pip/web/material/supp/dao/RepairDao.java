package com.bdcor.pip.web.material.supp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
import com.bdcor.pip.web.material.supp.domain.Repair;
import com.bdcor.pip.web.material.supp.filter.RepairFilter;

@MyBatisRepository
public interface RepairDao {

	List<Repair> getAllRepairs(RepairFilter filter);

	Repair getRepairByCode(@Param("id") String id);

	void addRepair(Repair repair);

	void updateRepair(Repair repair);

	void delete(@Param("id") String id);

}
