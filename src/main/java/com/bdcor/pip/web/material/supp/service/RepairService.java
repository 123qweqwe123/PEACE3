package com.bdcor.pip.web.material.supp.service;

import java.util.List;

import com.bdcor.pip.web.material.supp.domain.Repair;
import com.bdcor.pip.web.material.supp.filter.RepairFilter;

public interface RepairService {

	List<Repair> getAllRepairs(RepairFilter filter);

	Repair getRepairByCode(String archivesNo);

	void addRepair(Repair repair);

	void updateRepair(Repair repair);

	void delete(String id);

	
}
