package com.bdcor.pip.web.data.controller;

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
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.data.domain.PipCommRcc;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.data.filter.PipCommRccFilter;
import com.bdcor.pip.web.data.service.PipCommRccService;
import com.bdcor.pip.web.pro.promgt.service.LccService;

@Controller
@RequestMapping(value = "rcc")
public class PipCommRccController {

	@Autowired
	private LccService lccService;
	@Autowired
	private PipCommRccService pipCommRccService;

	/**
	 * 进入新增页面
	 */
	@RequestMapping(value = "openmodalAreainput")
	public ModelAndView openmodalAreainput(
			@RequestParam(value = "rccCode", required = false) String rccCode) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("pro/rcc/form");
		if (!StringUtils.isEmpty(rccCode)) {
			PipCommRcc pipCommRcc = null;
			try {
				pipCommRcc = pipCommRccService
						.selectPipCommRccByRccCode(rccCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			mav.addObject("pipCommRcc", pipCommRcc);
		}
		return mav;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "checkRccCodeExists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkRccCodeExists(
			@RequestParam(value = "rccCode", required = true) String rccCode) {
		Boolean result;
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			result = pipCommRccService.checkRccCodeExists(rccCode);
			res.put("result", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity(res, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addPipCommRcc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addPipCommRcc(PipCommRcc pipCommRcc) {
		Map<String, Object> result = new HashMap<String, Object>();
		// Boolean flag = lccService.checkLccCodeExists(lcc.getLccCode());
		if (pipCommRcc == null)
			return null;
		try {
			if (StringUtils.isEmpty(pipCommRcc.getProjectId())) {
				pipCommRccService.addPipCommRcc(pipCommRcc);
			} else {
				pipCommRccService.updatePipCommRcc(pipCommRcc);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@ResponseBody
	@RequestMapping(value = "deleteByRccCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteByRccCode(
			@RequestParam(value = "rccCode", required = true) String rccCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (StringUtils.isEmpty(rccCode)) {
			result.put("success", false);
			return new ResponseEntity(result, HttpStatus.OK);
		}
		try {
			pipCommRccService.deleteByRccCode(rccCode);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	// 查询所有患者信息
	@RequestMapping(value = "rccpage")
	public String queryPatient(PatientFilter pf, Model model) {
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			model.addAttribute("lccDictList", lccService.getDataLimitLcc());
		} else {
			model.addAttribute("lccDictList",
					lccService.getDataLimitLccForLccCode());

		}

		return "pro/rcc/rccpage";

	}

	@RequestMapping(value = "queryRccList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse queryRccList(PipCommRccFilter pcrf) {
		String projectName = Securitys.getUser().getCurrent_projectName();
		String projectId = Securitys.getUser().getCurrent_projectId();
		String lccCode =  Securitys.getUser().getLccCode();
		pcrf.setProjectId(projectId);
//		pcrf.setDepartMent(departMent);
//		pcrf.setUnit(unit);
		List<PipCommRcc> data = pipCommRccService.queryRccList(pcrf);
		JqgridResponse<PipCommRcc> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(data);
		return response;

	}
}
