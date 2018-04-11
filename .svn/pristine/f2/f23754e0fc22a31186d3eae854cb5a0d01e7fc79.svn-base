package com.bdcor.pip.web.material.supp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.MaterNameDao;
import com.bdcor.pip.web.material.supp.domain.MaterName;
import com.bdcor.pip.web.material.supp.filter.MaterNameFilter;
import com.bdcor.pip.web.material.supp.service.MaterNameService;

@Service
@Transactional
public class MaterNameServiceImpl implements MaterNameService {
	
	@Autowired
	private MaterNameDao materNameDao;

	@Override
	public List<MaterName> getAllMaterNames(MaterNameFilter filter) {
		return materNameDao.getAllMaterNames(filter);
	}

	@Override
	public MaterName getMaterlNameByCode(String materlCode) {
		return materNameDao.getMaterlNameByCode(materlCode);
	}

	@Override
	public void addMaterNameService(MaterName materName) {
		try{
			materName.setMaterlCode(GenerateKey.getKey(GenerateKey.PREFIX_MATERIAL));
			materName.setCreateBy(Securitys.getUserName());
			materName.setCreateDate(new Date());
			materName.setHelpCode(PinyingUtils.getJM(materName.getMaterlName()));
			materNameDao.addMaterNameService(materName);
		}catch(Exception e){
			throw new ServiceException("添加物资名称失败！", e);
		}
	}

	@Override
	public void updateMaterNameService(MaterName materName) {
		try{
			materName.setHelpCode(PinyingUtils.getJM(materName.getMaterlName()));
			materNameDao.updateMaterName(materName);
		}catch(Exception e){
			throw new ServiceException("更新物资名称失败！", e);
		}
	}

	@Override
	public void delete(String materlCode) {
		try{
			materNameDao.delete(materlCode);
		}catch(Exception e){
			throw new ServiceException("删除物资名称失败！", e);
		}
	}

	@Override
	public Boolean checkNameExists(String materlName) {
		MaterName materName = materNameDao.checkNameExists(materlName);
		if(materName==null) return false;
		return true;
	}
	
}
