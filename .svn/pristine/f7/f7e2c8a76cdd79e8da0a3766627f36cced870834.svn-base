package com.bdcor.pip.web.pro.promgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.dao.LinkManDao;
import com.bdcor.pip.web.pro.promgt.domain.LinkMan;
import com.bdcor.pip.web.pro.promgt.filter.LinkManFilter;
import com.bdcor.pip.web.pro.promgt.service.LinkManService;

@Service
@Transactional
public class LinkManServiceImpl implements LinkManService {

	@Autowired
	private LinkManDao linkManDao;

	@Override
	public List<LinkMan> getAllLinkMans(LinkManFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return linkManDao.getAllLinkMans(filter);
	}

	@Override
	public LinkMan getLinkManByCode(String linkManCode) {
		return linkManDao.getLinkManByCode(linkManCode);
	}

	@Override
	public void addLinkMan(LinkMan linkMan) {
		try{
			linkMan.setLinkManCode(GenerateKey.getKey(GenerateKey.PREFIX_PROJECT));
			linkMan.setProjectId(Securitys.getUser().getCurrent_projectId());
			linkManDao.addLinkMan(linkMan);
		}catch(Exception e){
			throw new ServiceException("联系人添加失败！", e);
		}
	}

	@Override
	public void updateLinkMan(LinkMan linkMan) {
		try{
			linkManDao.updateLinkMan(linkMan);
		}catch(Exception e){
			throw new ServiceException("联系人更新失败！", e);
		}
	}

	@Override
	public void delete(String linkManCode) {
		try{
			linkManDao.delete(linkManCode);
		}catch(Exception e){
			throw new ServiceException("联系人删除失败！", e);
		}
	}
}
