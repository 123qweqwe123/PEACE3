package com.bdcor.pip.web.material.supp.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.MaterUserDao;
import com.bdcor.pip.web.material.supp.domain.MaterStorehouse;
import com.bdcor.pip.web.material.supp.domain.MaterUser;
import com.bdcor.pip.web.material.supp.filter.MaterUserFilter;
import com.bdcor.pip.web.material.supp.service.MaterUserService;

@Service
public class MaterUserImpl implements MaterUserService {
	
	@Autowired
	MaterUserDao materUserDao;
	@Override
	public List<MaterUser> getAllMaterUser(
			MaterUserFilter filter) {
		// TODO Auto-generated method stub
		boolean isAdmin=Securitys.isAdmin();
		//管理员能添加所有库房,能查看所有库房
		if(!isAdmin){
			filter.setLccCode(Securitys.getUser().getLccCode());
		}
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return materUserDao.getAllMaterUser(filter);
	}

	@Override
	public MaterUser getMaterUser(MaterUser MaterUser) {
		// TODO Auto-generated method stub
		return materUserDao.getMaterUser(MaterUser);
	}

	@Override
	public void addMaterUser(MaterUser materUser) {
		addCommMsg(materUser);
		//生成库房code,库房code编码规则  lccid+001开始
		//由lcccode和projectid查询出stockcode
		String stockCode=materUserDao.selectMaxUserCode(materUser);
		if(stockCode==null){
			materUser.setUserCode(materUser.getLccCode().concat("001"));
		}else{
			materUser.setUserCode(String.valueOf(Long.parseLong(stockCode)+1));
		}
		materUserDao.insert(materUser);
	}

	@Override
	public void updateMaterUser(MaterUser MaterUser) {
		// TODO Auto-generated method stub
		addCommMsg(MaterUser);
		materUserDao.update(MaterUser);
	}

	@Override
	public void delete(MaterUser MaterUser) {
		// TODO Auto-generated method stub
		addCommMsg(MaterUser);
		materUserDao.delete(MaterUser);
	}

	@Override
	public Boolean checkNameExists(MaterUser materUser) {
		addCommMsg(materUser);
		materUser =materUserDao.getMaterUser(materUser);
		if(materUser==null)return true;
		return false;
	}
	
	private void addCommMsg(MaterUser MaterUser){
		MaterUser.setCreateBy(Securitys.getUserName());//创建人
		MaterUser.setCreateDate(new Date());//创建日期
		MaterUser.setHelpCode(PinyingUtils.getJM(MaterUser.getUserName()));//简拼
		boolean isAdmin=Securitys.isAdmin();
		MaterUser.setProjectId(Securitys.getUser().getCurrent_projectId());//项目id
		//管理员能添加库房
		if(!isAdmin){
			MaterUser.setLccCode(Securitys.getUser().getLccCode());//lcccode
		}
	}

}
