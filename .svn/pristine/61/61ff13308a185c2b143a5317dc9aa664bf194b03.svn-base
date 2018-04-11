package com.bdcor.pip.web.material.supp.service.impl;

import java.util.Date;
import java.util.List;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.MaterStorehouseMapper;
import com.bdcor.pip.web.material.supp.domain.MaterStorehouse;
import com.bdcor.pip.web.material.supp.filter.MaterStorehouseFilter;
import com.bdcor.pip.web.material.supp.service.MaterStorehouseService;

@Service
public class MaterStorehouseImpl implements MaterStorehouseService {
	
	@Autowired
	MaterStorehouseMapper materStorehouseDao;
	@Override
	public List<MaterStorehouse> getAllMaterStorehouses(
			MaterStorehouseFilter filter) {
		// TODO Auto-generated method stub
		boolean isAdmin=Securitys.isAdmin();
		//管理员能添加所有库房,能查看所有库房
		if(!isAdmin){
			filter.setLccCode(Securitys.getUser().getLccCode());
		}
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return materStorehouseDao.getAllMaterStorehoust(filter);
	}

	@Override
	public MaterStorehouse getMaterStorehouse(MaterStorehouse materStorehouse) {
		// TODO Auto-generated method stub
		return materStorehouseDao.getMaterStorehouse(materStorehouse);
	}

	@Override
	public void addMaterStorehouse(MaterStorehouse materStorehouse) {
		addCommMsg(materStorehouse);
		//生成库房code,库房code编码规则  lccid+001开始
		//由lcccode和projectid查询出stockcode
		String stockCode=materStorehouseDao.selectMaxStockCode(materStorehouse);
		if(stockCode==null){
			materStorehouse.setStockCode(materStorehouse.getLccCode().concat("001"));
		}else{
			materStorehouse.setStockCode(String.valueOf(Long.parseLong(stockCode)+1));
		}
		materStorehouseDao.insert(materStorehouse);
	}

	@Override
	public void updateMaterStorehouse(MaterStorehouse materStorehouse) {
		// TODO Auto-generated method stub
		addCommMsg(materStorehouse);
		materStorehouseDao.update(materStorehouse);
	}

	@Override
	public void delete(MaterStorehouse materStorehouse) {
		// TODO Auto-generated method stub
		addCommMsg(materStorehouse);
		materStorehouseDao.delete(materStorehouse);
	}

	@Override
	public Boolean checkNameExists(MaterStorehouse materStorehouse) {
		addCommMsg(materStorehouse);
		materStorehouse=materStorehouseDao.getMaterStorehouse(materStorehouse);
		if(materStorehouse==null)return true;
		return false;
	}
	
	private void addCommMsg(MaterStorehouse materStorehouse){
		materStorehouse.setCreateBy(Securitys.getUserName());//创建人
		materStorehouse.setCreateDate(new Date());//创建日期
		materStorehouse.setHelpCode(PinyingUtils.getJM(materStorehouse.getStockName()));//简拼
		boolean isAdmin=Securitys.isAdmin();
		materStorehouse.setProjectId(Securitys.getUser().getCurrent_projectId());//项目id
		//管理员能添加库房
		if(!isAdmin){
			materStorehouse.setLccCode(Securitys.getUser().getLccCode());//lcccode
		}
	}

}
