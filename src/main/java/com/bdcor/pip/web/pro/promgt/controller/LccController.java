package com.bdcor.pip.web.pro.promgt.controller;

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
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.dict.domain.DictCommDTO;
import com.bdcor.pip.dict.utils.DictUtils;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.filter.EthicalFilter;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.pro.promgt.service.PipCommLccEthicalService;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;

@Controller
@RequestMapping(value = "pro/lccmgt")
public class LccController {
	@Autowired
	private PipCommLccEthicalService pipCommLccEthicalService;
	@Autowired
	private LccService lccService;
	@Autowired
	private ProjectMgtService projectMgtService;

	@RequestMapping
	public String init(Model model) {
		return "pro/lccmgt/list";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<LccFilter> getAllProjects(LccFilter filter) {
		List<LccFilter> lccList = lccService.getAllLccs(filter);
		JqgridResponse<LccFilter> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(lccList);
		return response;
	}

	/**
	 * 
	 * description:查看伦理列表
	 * 
	 * @author yangfeng
	 * @param lccCode
	 * @return
	 * @update 2015-10-23
	 */
	@RequestMapping(value = "LunliList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<EthicalFilter> getLunLiByLccCode(String lccCode) {
		List<EthicalFilter> list = pipCommLccEthicalService
				.listByLccCode(lccCode);
		JqgridResponse<EthicalFilter> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping(value = "openmodallccinput", method = RequestMethod.GET)
	public String lccInput(Model model, String lccCode, String type) {
		Project pro = projectMgtService.getProjectById(Securitys.getUser()
				.getCurrent_projectId());
		Lcc lcc = null;
		if ("edit".equals(type)) {
			lcc = lccService.getLcc(lccCode);
			model.addAttribute("isEdit", true);
		}
		if (lcc == null) {
			lcc = new Lcc();
		}
		model.addAttribute("pro", pro);
		lcc.setProjectName(pro == null ? "" : pro.getProjectName());
		model.addAttribute("lcc", lcc);
		return "pro/lccmgt/form";
	}

	/**
	 * description: 用来展示伦理列表数据的
	 * 
	 * @author yangfeng
	 * @param model
	 * @param lccCode
	 * @return
	 * @update 2015-10-23
	 */
	@RequestMapping(value = "openmodalLunLiInput", method = RequestMethod.GET)
	public String searchLunLiInput(Model model, String lccCode) {
		model.addAttribute("lccCode", lccCode);
		return "pro/lccmgt/gridLunLi";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "getLccCode", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getLccCode(String areaCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String lccCode = lccService.getLccCode(areaCode);
			if (!StringUtils.isEmpty(lccCode)) {
				result.put("code", lccCode.substring(4));
			} else {
				result.put("code", "01");
			}
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addLcc", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addLcc(Lcc lcc) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (lcc == null)
			return null;
		lcc.setStatus(2);
		try {
			if (StringUtils.isEmpty(lcc.getProjectId())) {
				lccService.addLcc(lcc);
			} else {
				lccService.updateLcc(lcc);
			}
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(String lccCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			lccService.delete(lccCode);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = "updateStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateStatus(
			@RequestParam(value = "lccCode", required = true) String lccCode,
			@RequestParam(value = "status", required = true) String status) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String msg = lccService.updateStatus(lccCode, status);
			if (msg != null && !"".equals(msg) && "修改成功".equals(msg)) {
				result.put("success", true);
				result.put("msg", msg);
			} else {
				result.put("success", false);
				result.put("msg", "修改失败");
			}

		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "checkLccCodeExists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkLccCodeExists(
			@RequestParam(value = "lccCode", required = true) String lccCode) {
		Boolean result = lccService.checkLccCodeExists(lccCode);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("result", result);
		return new ResponseEntity(res, HttpStatus.OK);
	}

	@RequestMapping(value = "getProvince", produces = "application/json")
	@ResponseBody
	public List<DictCommDTO> getProvince() {
		return DictUtils.getProvince();
	}
}
