package com.bdcor.pip.web.material.supp.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.MaterlInfoDao;
import com.bdcor.pip.web.material.supp.domain.MaterlInfo;
import com.bdcor.pip.web.material.supp.filter.MaterlInfoFilter;
import com.bdcor.pip.web.material.supp.service.MaterlInfoService;

@Service
@Transactional
public class MaterlInfoServiceImpl implements MaterlInfoService {

	@Autowired
	private MaterlInfoDao materlInfoDao;

	@Override
	public List<MaterlInfo> getAllMaterInfos(MaterlInfoFilter filter) {
		return materlInfoDao.getAllMaterInfos(filter);
	}

	@Override
	public MaterlInfo getMaterlInfoByCode(String materlInfoCode) {
		MaterlInfo materlInfo = materlInfoDao.getMaterInfoByCode(materlInfoCode);
		//优化时可删除
		String className = materlInfoDao.getClassNameByCode(materlInfo.getClassCode());
		String supplierName = materlInfoDao.getSupplierNameByCode(materlInfo.getSupplierCode());
		materlInfo.setClassName(className);
		materlInfo.setSupplierName(supplierName);
		return materlInfo;
	}

	@Override
	public void addMaterInfo(MaterlInfo materlInfo) {
		try{
			materlInfo.setMaterlInfoCode(GenerateKey.getKey(GenerateKey.PREFIX_MATERIAL));
			materlInfo.setCreateBy(Securitys.getUserName());
			materlInfo.setCreateDate(new Date());
			materlInfo.setIsRemoved(0);
			materlInfo.setHelpCode(PinyingUtils.getJM(materlInfo.getMaterlName()));
			materlInfo.setProjectId(Securitys.getUser().getCurrent_projectId());
			materlInfoDao.addMaterInfo(materlInfo);
		}catch(Exception e){
			throw new ServiceException("添加物资基本信息失败", e);
		}
	}

	@Override
	public void updateMaterInfo(MaterlInfo materlInfo) {
		try{
			materlInfoDao.updateMaterlInfo(materlInfo);
		}catch(Exception e){
			throw new ServiceException("更新物资基本信息失败", e);
		}
	}

	@Override
	public void delete(String materlInfoCode) {
		try{
			materlInfoDao.delete(materlInfoCode);
		}catch(Exception e){
			throw new ServiceException("删除物资基本信息失败", e);
		}
	}

	@Override
	public List<Map<String, String>> materlInfoListByLimit() {
		return materlInfoDao.materlInfoListByLimit(Securitys.getUserId());
	}
}
