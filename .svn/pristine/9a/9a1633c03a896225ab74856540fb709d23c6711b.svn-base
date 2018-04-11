package com.bdcor.pip.web.pro.promgt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.pro.promgt.dao.FileDao;
import com.bdcor.pip.web.pro.promgt.domain.Adjunct;
import com.bdcor.pip.web.pro.promgt.domain.AdjunctType;
import com.bdcor.pip.web.pro.promgt.filter.AdjunctFilter;
import com.bdcor.pip.web.pro.promgt.service.FileService;


@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDao fileDao;
	
	@Override
	public List<Adjunct> listAdjunct(AdjunctFilter filter) {
		if(Securitys.getSubject().isAuthenticated()){
			filter.setCreator(Securitys.getUserId());
			String userId = Securitys.getUserId();
			filter.setCreator(userId==null?"":userId);
			filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		}
		return fileDao.listAdjunct(filter);
	}
	
	@Override
	public void saveAdjunct(String projectId, String fileName, String ctxPath,
			String fileType, String fileDesc) {
		Adjunct adjunct = new Adjunct();
		//adjunct.setAdjunctId(Identities.uuid());
		adjunct.setAdjunctId(GenerateKey.getKey(GenerateKey.PREFIX_PROJECT));
		adjunct.setProjectId(projectId);
		adjunct.setAdjunctName(fileName);
		adjunct.setPath(ctxPath);
		adjunct.setType(fileType);
		adjunct.setFileDesc(fileDesc);
		adjunct.setCreator(Securitys.getUserId());
		try{
			fileDao.saveAdjunct(adjunct);
		}catch(Exception e){
			throw new ServiceException("保存附件信息失败！", e);
		}
	}

	@Override
	public void updateDownCount(Adjunct adjunct) {
		try{
			fileDao.updateDownCount(adjunct);
		}catch(Exception e){
			throw new ServiceException("更新下载次数失败！", e);
		}
	}

	@Override
	public void deleteAdjunct(String adjunctId) {
		try{
			fileDao.deleteAdjunct(adjunctId);
		}catch(Exception e){
			throw new ServiceException("删除附件失败！",e);
		}
	}

	@Override
	public List<AdjunctType> getAdjunctType() {
		return fileDao.getAdjunctType();
	}

}
