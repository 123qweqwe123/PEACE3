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
import com.bdcor.pip.web.material.supp.domain.PipMmsScmstock;
import com.bdcor.pip.web.material.supp.filter.PipMmsScmstockFilter;
import com.bdcor.pip.web.material.supp.filter.StockFilter;
import com.bdcor.pip.web.material.supp.service.MaterialService;
import com.bdcor.pip.web.material.supp.service.PipMmsScmstockService;

/**
 * 库存查询
 * 
 * @author rp
 * 
 */
@Controller
@RequestMapping("material/matstock")
public class MaterialStockController {
	@Autowired
	private PipMmsScmstockService scmstockService;
	@Autowired
	private MaterialService materialService;

	@RequestMapping(value = "query")
	public String init() {
		return "material/matstock/list4cxb";
	}

	@RequestMapping
	public String defaultInit() {
		return "material/matstock/list";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterStore> getAllSuppliers(StockFilter filter) {
		List<MaterStore> list = materialService.getAllStocksLimit(filter);
		JqgridResponse<MaterStore> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping(value = "list4cxb", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipMmsScmstock> getAllSuppliers(
			PipMmsScmstockFilter filter) {
		List<PipMmsScmstock> list = scmstockService.list(filter);
		JqgridResponse<PipMmsScmstock> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

}
