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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.material.supp.domain.Supplier;
import com.bdcor.pip.web.material.supp.filter.SupplierFilter;
import com.bdcor.pip.web.material.supp.service.SupplierService;

@Controller
@RequestMapping("material/supp")
public class SupplierController {

	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping
	public String init(){
		return "material/supp/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Supplier> getAllSuppliers(SupplierFilter filter){
		List<Supplier> list = supplierService.getAllSuppliers(filter);
		JqgridResponse<Supplier> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
    @RequestMapping(value = "openmodaladdsupplierinput", method = RequestMethod.GET)
    public ModelAndView addProjectInput(@RequestParam(value = "supplierCode", required = false) String supplierCode) {
    	ModelAndView mav = new ModelAndView("material/supp/form");
    	if(supplierCode!=null&&!"".equals(supplierCode)){
    		Supplier supplier = supplierService.getSupplierByCode(supplierCode);
    		mav.addObject("supplier", supplier);
    		mav.addObject("edit", true);
    	}else{
    		mav.addObject("edit", false);
    	}
    	return mav;
    }
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addSupplier", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addSupplier(Supplier supplier){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			if(StringUtils.isEmpty(supplier.getSupplierCode1())){
				supplierService.addSupplier(supplier);
			}else{
				supplierService.updateSupplier(supplier);
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
	public ResponseEntity<?> delete(String supplierCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			supplierService.delete(supplierCode);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "checkNameExists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkNameExists(Supplier supplier) {
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			Supplier supp = supplierService.checkNameExists(supplier.getSupplierName());
			if(supp != null){
				result.put("success", true);
			}else{
				result.put("success", false);
			}
			
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
