package com.bdcor.pip.web.data.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.web.data.domain.PipCommEventDictfile;
import com.bdcor.pip.web.data.filter.PipCommEventDictfileFilter;
import com.bdcor.pip.web.data.service.PipCommEventDictfileService;
import com.bdcor.pip.web.material.supp.domain.MasterImport;
import com.bdcor.pip.web.material.supp.filter.MaterImportFilter;

@Controller
@RequestMapping(value = "file/management")
public class PipCommEventDictFileController {
	@Autowired
	private PipCommEventDictfileService pipCommEventFileService;

	@RequestMapping
	public String init() {
		return "pro/file/list";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipCommEventFileService.deletById(id);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> add(PipCommEventDictfile pipCommEventDictfile) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 设置fileInfo字段的简拼首字母
			pipCommEventDictfile.setHelpCode(PinyingUtils
					.getJM(pipCommEventDictfile.getFileInfo()));
			if (StringUtils.isEmpty(pipCommEventDictfile.getId())) {
				pipCommEventFileService.add(pipCommEventDictfile);
			} else {
				pipCommEventFileService.update(pipCommEventDictfile);
			}
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = "openmodaladdinput", method = RequestMethod.GET)
	public ModelAndView openInput(
			@RequestParam(value = "id", required = false) String id) {
		PipCommEventDictfile pipCommEventDictfile = null;
		ModelAndView mav = new ModelAndView("pro/file/form");
		if (!StringUtils.isEmpty(id)) {
			pipCommEventDictfile = pipCommEventFileService.getEventFileById(id);
		}
		if (pipCommEventDictfile == null) {
			pipCommEventDictfile = new PipCommEventDictfile();
		}
		mav.addObject("pipCommEventDictfile", pipCommEventDictfile);
		return mav;
	}
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipCommEventDictfile> getAllMaterInfos(
			PipCommEventDictfileFilter filter) {
		List<PipCommEventDictfile> list = pipCommEventFileService.listByFilter(filter);
		JqgridResponse<PipCommEventDictfile> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}
}
