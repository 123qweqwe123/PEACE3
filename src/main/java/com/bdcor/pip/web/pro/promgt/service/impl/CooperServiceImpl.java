package com.bdcor.pip.web.pro.promgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.web.pro.promgt.dao.CooperDao;
import com.bdcor.pip.web.pro.promgt.domain.Region;
import com.bdcor.pip.web.pro.promgt.filter.RegionFilter;
import com.bdcor.pip.web.pro.promgt.service.CooperService;

@Service
@Transactional
public class CooperServiceImpl implements CooperService {

	@Autowired
	private CooperDao cooperDao;

	@Override
	public List<Region> listRegion(RegionFilter filter) {
		filter.setProjectId(filter.getProjectId()==null?"":filter.getProjectId());
		return cooperDao.listRegion(filter);
	}

	@Override
	public void saveRegion(Region region) {
		try{
			//region.setRegionId(Identities.uuid());
			region.setRegionId(GenerateKey.getKey(GenerateKey.PREFIX_PROJECT));
			cooperDao.saveRegion(region);
		}catch(Exception e){
			throw new ServiceException("保存区域中心失败！", e);
		}
	}

	@Override
	public Region getRegionById(String regionId) {
		return cooperDao.getRegionById(regionId);
	}

	@Override
	public void deleteLcc(String regionId) {
		try{
			cooperDao.deleteLcc(regionId);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("删除区域中心失败！", e);
		}
	}

	@Override
	public void updateRegion(Region region) {
		try{
			cooperDao.updateRegion(region);
		}catch(Exception e){
			throw new ServiceException("更新区域中心失败！", e);
		}
	}
	
}
