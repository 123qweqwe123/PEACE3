/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.controller 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.quality.controller  
 */

package com.bdcor.pip.web.quality.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.s;

import org.apache.commons.lang.StringUtils;
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
import com.bdcor.pip.web.quality.domain.PipExpImplement;
import com.bdcor.pip.web.quality.domain.PipExpImplementPerson;
import com.bdcor.pip.web.quality.domain.PipExpImplementProblem;
import com.bdcor.pip.web.quality.domain.PipExpPlan;
import com.bdcor.pip.web.quality.filter.PipExpImplementFilter;
import com.bdcor.pip.web.quality.filter.PipExpPlanFilter;
import com.bdcor.pip.web.quality.service.PipExpImplementPersonService;
import com.bdcor.pip.web.quality.service.PipExpImplementProblemService;
import com.bdcor.pip.web.quality.service.PipExpImplementService;

/**
 * description:考核实施 控制层
 * 
 * @author yangfeng 创建时间：2015-11-5
 */
@Controller
@RequestMapping("quality/examineImplement")
public class ExamineImplementController {
	@Autowired
	private PipExpImplementService pipExpImplementService;
	@Autowired
	private PipExpImplementPersonService personService;
	@Autowired
	private PipExpImplementProblemService problemService;

	@RequestMapping
	public String init() {
		return "quality/examineImplement/list";
	}

	@RequestMapping(value = "openmodaladdinput", method = RequestMethod.GET)
	public String openmodaladdInput(Model model,
			@RequestParam(value = "id", required = false) String id) {
		if (!StringUtils.isEmpty(id)) {
			PipExpImplement pipExpImplement = null;
			try {
				pipExpImplement = pipExpImplementService.selectByExample(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("pipExpImplement", pipExpImplement);
		}
		return "quality/examineImplement/form";
	}
	@RequestMapping(value = "look", method = RequestMethod.GET)
	public String look(Model model,@RequestParam(value = "id", required = false) String id){
		PipExpImplement pipExpImplement= null;
		try {
			pipExpImplement = pipExpImplementService.selectByExample(id);
			model.addAttribute("pipExpImplement", pipExpImplement);
			List<PipExpImplementPerson> persons=personService.selectByFk(id);
			String khStrings = 	"";
			String yjStrings =	"";
			String otherStrings = "";
			if(persons.size()>0){
				for (PipExpImplementPerson person:persons) {
					if(("1").equals(person.getPersonType())){
						khStrings +=person.getLccUserName()+"、";
					}
					if(("2").equals(person.getPersonType())){
						yjStrings +=person.getLccUserName()+"、";
					}
					if(("3").equals(person.getPersonType())){
						otherStrings+=person.getLccUserName()+"、";
					}
				}
				if(khStrings.indexOf("、") != -1){
					khStrings=khStrings.substring(0, khStrings.length()-1);
				}
				if(yjStrings.indexOf("、") != -1){
					yjStrings=yjStrings.substring(0, yjStrings.length()-1);
				}
				if(otherStrings.indexOf("、") != -1){
					otherStrings=otherStrings.substring(0, otherStrings.length()-1);
				}
			}
			model.addAttribute("khStrings", khStrings);
			model.addAttribute("yjStrings", yjStrings);
			model.addAttribute("otherStrings", otherStrings);
			List<PipExpImplementProblem> problems = problemService.selectByFk(id);
			model.addAttribute("problems", problems);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "quality/examineImplement/look";
	}
	
	@RequestMapping(value = "openmodalpatientinput", method = RequestMethod.GET)
	public String openmodalpatientinput(Model model,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "lccCode", required = true) String lccCode) {
		model.addAttribute("lccCode", lccCode);
		model.addAttribute("implementId", id);
		return "quality/examineImplement/patientForm";
	}
	@RequestMapping(value = "openmodalProbleminput", method = RequestMethod.GET)
	public String openmodalProbleminput(Model model,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "lccCode", required = true) String lccCode) {
		model.addAttribute("lccCode", lccCode);
		model.addAttribute("implementId", id);
		return "quality/examineImplement/problemForm";
	}
	@RequestMapping(value = "openmodalsituationinput", method = RequestMethod.GET)
	public String openmodalsituationinput(Model model,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "lccCode", required = true) String lccCode) {
		model.addAttribute("lccCode", lccCode);
		model.addAttribute("implementId", id);
		if (!StringUtils.isEmpty(id)) {
			PipExpImplement pipExpImplement = null;
			try {
				pipExpImplement = pipExpImplementService.selectByExample(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("pipExpImplement", pipExpImplement);
		}
		return "quality/examineImplement/situationForm";
	}
	
	@RequestMapping(value = "openmodalResearchWJinput", method = RequestMethod.GET)
	public String openmodalResearchWJinput(Model model,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "lccCode", required = true) String lccCode) {
		model.addAttribute("lccCode", lccCode);
		model.addAttribute("implementId", id);
		if (!StringUtils.isEmpty(id)) {
			PipExpImplement pipExpImplement = null;
			try {
				pipExpImplement = pipExpImplementService.selectByExample(id);
			} catch (Exception e) {
				e.printStackTrace();
			}
			model.addAttribute("pipExpImplement", pipExpImplement);
		}
		return "quality/examineImplement/checkForm";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> add(PipExpImplement implement) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpImplementService.save(implement);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipExpImplement> list(PipExpImplementFilter filter) throws Exception {
		List<PipExpImplement> list = pipExpImplementService.listByFilter(filter);
		JqgridResponse<PipExpImplement> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> update(PipExpImplement pipExpImplement) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpImplementService.update(pipExpImplement);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "updateZtqk", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateZtqk(PipExpImplement pipExpImplement) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpImplementService.update(pipExpImplement);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(@RequestParam("id") String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			pipExpImplementService.delete(id);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "deleteBatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteBatch(@RequestParam("ids") String ids) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
