package com.bdcor.pip.web.material.supp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.dict.utils.DictUtils;
import com.bdcor.pip.web.material.supp.domain.MaterStorehouse;
import com.bdcor.pip.web.material.supp.filter.MaterStorehouseFilter;
import com.bdcor.pip.web.material.supp.service.MaterStorehouseService;

@Controller
@RequestMapping("material/storehouse")
public class MaterStorehouseController {

	@Autowired
	private MaterStorehouseService materStorehouseService;
	
	@RequestMapping
	public String init(){
		return "material/storehouse/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterStorehouse> getAllMaterNames(MaterStorehouseFilter filter){
		List<MaterStorehouse> list = materStorehouseService.getAllMaterStorehouses(filter);
		for(MaterStorehouse ms:list){
			String lccName=DictUtils.getValueByCode("LCC", ms.getLccCode());
			ms.setLccName(lccName);
		}
		JqgridResponse<MaterStorehouse> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	

    @RequestMapping(value = "openmodaladdmaternameinput", method = RequestMethod.GET)
    public ModelAndView openInput(MaterStorehouse materStorehouse) {
    	ModelAndView mav = new ModelAndView("material/storehouse/form");
    	String stockCode=materStorehouse.getStockCode();
    	if(stockCode!=null&&!"".equals(stockCode)){
    		materStorehouse = materStorehouseService.getMaterStorehouse(materStorehouse);
    		mav.addObject("materStorehouse", materStorehouse);
    	}
    	return mav;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addOrUpdateMaterStorehouse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMaterStorehouse(MaterStorehouse materStorehouse){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(materStorehouse.getStockCode())){
				materStorehouseService.addMaterStorehouse(materStorehouse);
			}else{
				materStorehouseService.updateMaterStorehouse(materStorehouse);
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
	public ResponseEntity<?> delete(MaterStorehouse materStorehouse) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materStorehouseService.delete(materStorehouse);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="checkNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkNameExists(MaterStorehouse materStorehouse){
        Boolean result = materStorehouseService.checkNameExists(materStorehouse);
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("result", result);
        return new ResponseEntity(res,HttpStatus.OK);
    }
}
