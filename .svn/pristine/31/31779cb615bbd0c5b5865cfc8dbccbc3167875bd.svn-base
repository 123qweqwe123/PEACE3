package com.bdcor.pip.web.scm.service.impl;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.scm.dao.ScmMapper;
import com.bdcor.pip.web.scm.filter.ScmFilter;
import com.bdcor.pip.web.scm.service.ScmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
@Transactional
public class ScmServiceImpl implements ScmService {

	@Autowired
	private ScmMapper scmDao;

	@Override
	public Map<String, String> getBoxMap(String boxCode) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("PROJECT_ID", Securitys.getCurrentProject());
		paramMap.put("LCC_CODE", Securitys.getUser().getLccCode());
		paramMap.put("BOX_CODE", boxCode);
		return scmDao.getBoxMap(paramMap);
	}

	@Override
	public List<Map<String, String>> getTubeList(String boxCode) {
		Map<String,String> paramMap = new HashMap<String, String>();
		paramMap.put("PROJECT_ID", Securitys.getCurrentProject());
		paramMap.put("LCC_CODE", Securitys.getUser().getLccCode());
		paramMap.put("BOX_CODE", boxCode);
		return scmDao.getTubeList(paramMap);
	}

	@Override
	@Transactional(
			propagation = Propagation.REQUIRED ,
			isolation = Isolation.DEFAULT,
			rollbackFor = Exception.class
		)
	public void save(HttpServletRequest request) throws Exception {
		String boxCode = request.getParameter("boxCode").trim();
		String boxRemark = request.getParameter("boxRemark").trim();
		if(boxRemark.length()==0){
			boxRemark = null;
		}
		Date createDate = new Date();
		Map<String,Object> boxMap = new HashMap<String,Object>();
		boxMap.put("PROJECT_ID", Securitys.getCurrentProject());
		boxMap.put("LCC_CODE", Securitys.getUser().getLccCode());
		boxMap.put("BOX_CODE", boxCode);
		if(getBoxMap(boxCode)==null){
			boxMap.put("CREATE_DATE", createDate);
			boxMap.put("CREATE_BY", Securitys.getUserId());
			scmDao.insertBox(boxMap);
		}else{
			boxMap.put("REMARK", boxRemark);
			scmDao.updateBox(boxMap);
			scmDao.clearTube(boxMap);
		}
		
		
		@SuppressWarnings("rawtypes")
		Enumeration enumer = request.getParameterNames();
		while(enumer.hasMoreElements()){
			String rKey = enumer.nextElement().toString();
			if(rKey.startsWith("tube_")){
				String tubeCode = request.getParameter(rKey);
				String rowNo = rKey.substring(5);
				for(int j=1;j<=10;j++){
					Map<String,Object> tubeMap = new HashMap<String, Object>();
					tubeMap.put("PROJECT_ID", Securitys.getCurrentProject());
					tubeMap.put("LCC_CODE", Securitys.getUser().getLccCode());
					tubeMap.put("BOX_CODE", boxCode);
					tubeMap.put("CREATE_DATE", createDate);
					tubeMap.put("CREATE_BY", Securitys.getUserId());
					
					tubeMap.put("TUBE_CODE", tubeCode);
					tubeMap.put("BOX_ROWNO", Integer.parseInt(rowNo));
					tubeMap.put("BOX_COLNO", j);
					
					String remark = request.getParameter("tubeRemark"+rowNo+"_"+j);
					if(remark != null && remark.trim().length()==0){
						remark = null;
					}
					tubeMap.put("REMARK",remark);
					scmDao.insertTube(tubeMap);
				}
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> boxList(ScmFilter filter) {
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setUserId(Securitys.getUserId());
		List<Map<String,Object>> boxListSum = scmDao.boxListSum(filter);
		List<Map<String,Object>> boxList = scmDao.boxList(filter);
		boxList.addAll(0,boxListSum);
		return boxList;
	}

    // 校验前台输入的采血管号是否有效可用
	public boolean checkTubeCode(String tubecode , String boxCode){
        int i = scmDao.checkTubeCode(Securitys.getUser().getLccCode().trim() , tubecode , boxCode);
		if( i == 0 ){
			return false;
		}else{
			return true;
		}
	}
}
