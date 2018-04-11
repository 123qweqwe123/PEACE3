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
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.pro.promgt.domain.Evaluation;
import com.bdcor.pip.web.pro.promgt.filter.LccFilter;
import com.bdcor.pip.web.pro.promgt.service.EvaluationService;
import com.bdcor.pip.web.pro.promgt.service.LccService;

@Controller
@RequestMapping("pro/eval")
public class EvaluationController {

	@Autowired
	private EvaluationService evaluationService;
	@Autowired
	private LccService lccService;
	
	@RequestMapping
    public String init(){
        return "pro/eval/list";
    }
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<Evaluation> getAllEvaluations(LccFilter filter){
		List<Evaluation> lccList = evaluationService.getAllEvaluations(filter);
		JqgridResponse<Evaluation> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(lccList);
        return response;
	}
	
	@RequestMapping(value = "openmodalevalinput", method = RequestMethod.GET)
	public String evalInput(Model model, String id){
		if(!StringUtils.isEmpty(id)){
			Evaluation eval = evaluationService.getEvaluationById(id);
			model.addAttribute("eval", eval);
		}
		
		model.addAttribute("lccDictList", lccService.getAllLccs(new LccFilter()));
		model.addAttribute("evaluationLevelList", new String[]{"1","2","3","4","5","6","7","8","9","10"});
		return "pro/eval/form";
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addEvaluation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addEvaluation(Evaluation eval){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(eval.getId())){
				evaluationService.addEvaluation(eval);
			}else{
				evaluationService.updateEvaluation(eval);
			}
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			evaluationService.delete(id);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="checkLccNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkLccNameExists(@RequestParam(value="lccCode",required=true)String lccCode){
        Boolean result = evaluationService.checkLccNameExists(lccCode);
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("result", result);
        return new ResponseEntity(res,HttpStatus.OK);
    }
	
}
