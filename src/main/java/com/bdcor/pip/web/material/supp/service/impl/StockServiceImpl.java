package com.bdcor.pip.web.material.supp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bdcor.pip.web.material.supp.dao.MaterialDao;
import com.bdcor.pip.web.material.supp.domain.MaterStore;
import com.bdcor.pip.web.material.supp.filter.StockFilter;
import com.bdcor.pip.web.material.supp.service.StockService;

@Service
public class StockServiceImpl implements StockService {
	
	@Autowired
	MaterialDao materialDao;
	@Override
	public List<MaterStore> getAllStocks(StockFilter filter) {
		// TODO Auto-generated method stub
		return materialDao.selectAllMaterStore(filter);
	}

}
