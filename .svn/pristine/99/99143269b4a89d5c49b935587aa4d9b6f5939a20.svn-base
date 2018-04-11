package com.bdcor.pip.web.material.supp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.material.supp.domain.MaterStore;
import com.bdcor.pip.web.material.supp.filter.StockFilter;
import com.bdcor.pip.web.material.supp.service.StockService;
/**
 * 库存查询
 * @author rp
 *
 */
@Controller
@RequestMapping("material/stock")
public class StockInfoController {

	@Autowired
	private StockService stockService;
	
	@RequestMapping
	public String init(){
		return "material/stock/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterStore> getAllSuppliers(StockFilter filter){
		List<MaterStore> list = stockService.getAllStocks(filter);
		JqgridResponse<MaterStore> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
}
