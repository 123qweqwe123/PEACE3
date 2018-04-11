package com.bdcor.pip.web.material.supp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchives;
import com.bdcor.pip.web.material.supp.filter.PipMmsScmarchivesFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsDefstorehouseService;
import com.bdcor.pip.web.material.supp.service.PipMmsScmarchivesService;

/**
 * 单位报损
 * 
 * @author rp
 * 
 */
@Controller
@RequestMapping("material/scmarchives")
public class MaterialScmarchivesController {

	@Autowired
	private PipMmsScmarchivesService pipMmsScmarchivesService;

	@Autowired
	private PipMmsDefstorehouseService mmsDefstorehouseService;

	@RequestMapping
	public String init(Model model) {
		model.addAttribute("storehouse", mmsDefstorehouseService.listAndLimit());
		return "material/scmarchives/list";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipMmsScmarchives> getAllSuppliers(
			PipMmsScmarchivesFilter filter) {
		List<PipMmsScmarchives> list = pipMmsScmarchivesService
				.listSimple(filter);
		JqgridResponse<PipMmsScmarchives> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "changeState", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changeState(@RequestParam("ids") String ids,
			@RequestParam("state") String state) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipMmsScmarchivesService.changeState(ids, state);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

}
