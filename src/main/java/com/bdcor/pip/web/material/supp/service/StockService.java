package com.bdcor.pip.web.material.supp.service;

import java.util.List;

import com.bdcor.pip.web.material.supp.domain.MaterStore;
import com.bdcor.pip.web.material.supp.filter.StockFilter;

public interface StockService {

	List<MaterStore> getAllStocks(StockFilter filter);

}
