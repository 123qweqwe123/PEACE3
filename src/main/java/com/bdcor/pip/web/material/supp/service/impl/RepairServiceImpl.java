package com.bdcor.pip.web.material.supp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.RepairDao;
import com.bdcor.pip.web.material.supp.domain.Repair;
import com.bdcor.pip.web.material.supp.filter.RepairFilter;
import com.bdcor.pip.web.material.supp.service.RepairService;

@Service
@Transactional
public class RepairServiceImpl implements RepairService {

	@Autowired
	private RepairDao repairDao;

	@Override
	public List<Repair> getAllRepairs(RepairFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return repairDao.getAllRepairs(filter);
	}

	@Override
	public Repair getRepairByCode(String id) {
		Repair repair = repairDao.getRepairByCode(id);
		repair.setRepairDateStr(DateUtil.dateToString(repair.getRepairDate(), "yyyy-MM-dd"));
		return repair;
	}

	@Override
	public void addRepair(Repair repair) {
		try{
			repair.setCreateBy(Securitys.getUserId());
			repair.setCreateDate(new Date());
			repair.setProjectId(Securitys.getUser().getCurrent_projectId());
			repairDao.addRepair(repair);
		}catch(Exception e){
			throw new ServiceException("添加维修计量信息失败！",e);
		}
	}

	@Override
	public void updateRepair(Repair repair) {
		try{
			repair.setUpdateBy(Securitys.getUserId());
			repair.setUpdateDate(new Date());
			repairDao.updateRepair(repair);
		}catch(Exception e){
			throw new ServiceException("修改维修计量信息失败！",e);
		}
	}

	@Override
	public void delete(String id) {
		try{
			repairDao.delete(id);
		}catch(Exception e){
			throw new ServiceException("删除维修计量信息失败！",e);
		}
	}


}
