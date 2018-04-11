package com.bdcor.pip.web.material.supp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.material.supp.dao.PipMmsExscmmasterMapper;
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
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmmaster;
import com.bdcor.pip.web.material.supp.filter.PipMmsExscmdetalFilter;
import com.bdcor.pip.web.material.supp.filter.PipMmsExscmmasterFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsDefstorehouseService;
import com.bdcor.pip.web.material.supp.service.PipMmsExscmdetalService;
import com.bdcor.pip.web.material.supp.service.PipMmsExscmmasterService;

/**
 * 单位接受
 * 
 * @author rp
 * 
 */
@Controller
@RequestMapping("material/receive")
public class MaterialReceiveController {
	@Autowired
	private PipMmsExscmmasterService exscmmasterService;
	@Autowired
	private PipMmsDefstorehouseService mmsDefstorehouseService;
	@Autowired
	private PipMmsExscmdetalService exscmdetalService;

	@Autowired
	private PipMmsExscmmasterMapper scmmasterDao;

	@RequestMapping
	public String init(Model model) {
		model.addAttribute("storehouse", mmsDefstorehouseService.listAndLimit());
		return "material/receive/list";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipMmsExscmmaster> list(PipMmsExscmmasterFilter filter) {
		List<PipMmsExscmmaster> list = exscmmasterService.list(filter);
		JqgridResponse<PipMmsExscmmaster> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping(value = "listDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipMmsExscmdetal> listDetail(
			PipMmsExscmdetalFilter filter) {
		List<PipMmsExscmdetal> list = exscmdetalService.list(filter);
		JqgridResponse<PipMmsExscmdetal> response = JqgridResponseContext
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

		int count = scmmasterDao.checkIsExpired(ids);
		if(count > 0){
			result.put("success", false);
			result.put("message", "存在过期采血包,无法接收");
			return new ResponseEntity(result, HttpStatus.OK);
		}

		try {
			exscmmasterService.receive(ids, state);
			result.put("success", true);
			result.put("message", "接收成功");
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", "接收失败");
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

}
