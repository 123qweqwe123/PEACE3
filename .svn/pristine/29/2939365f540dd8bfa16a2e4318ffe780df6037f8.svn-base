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
import com.bdcor.pip.web.material.supp.domain.MaterUser;
import com.bdcor.pip.web.material.supp.filter.MaterUserFilter;
import com.bdcor.pip.web.material.supp.service.MaterUserService;

@Controller
@RequestMapping("material/user")
public class MaterUserController {

	@Autowired
	private MaterUserService materUserService;
	
	@RequestMapping
	public String init(){
		return "material/user/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterUser> getAllMaterNames(MaterUserFilter filter){
		List<MaterUser> list = materUserService.getAllMaterUser(filter);
		for(MaterUser ms:list){
			String lccName=DictUtils.getValueByCode("LCC", ms.getLccCode());
			ms.setLccName(lccName);
		}
		JqgridResponse<MaterUser> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	

    @RequestMapping(value = "openmodaladdmaternameinput", method = RequestMethod.GET)
    public ModelAndView openInput(MaterUser materUser) {
    	ModelAndView mav = new ModelAndView("material/user/form");
    	String stockCode=materUser.getUserCode();
    	if(stockCode!=null&&!"".equals(stockCode)){
    		materUser = materUserService.getMaterUser(materUser);
    		mav.addObject("materUser", materUser);
    	}
    	return mav;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addOrUpdateMaterUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMaterUser(MaterUser materUser){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(materUser.getUserCode())){
				materUserService.addMaterUser(materUser);
			}else{
				materUserService.updateMaterUser(materUser);
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
	public ResponseEntity<?> delete(MaterUser materUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materUserService.delete(materUser);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value="checkNameExists",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> checkNameExists(MaterUser materUser){
        Boolean result = materUserService.checkNameExists(materUser);
        Map<String,Object> res = new HashMap<String,Object>();
        res.put("result", result);
        return new ResponseEntity(res,HttpStatus.OK);
    }
}
