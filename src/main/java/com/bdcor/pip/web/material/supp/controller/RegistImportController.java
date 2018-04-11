package com.bdcor.pip.web.material.supp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.common.BdcorCodeStringDefinition;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.material.supp.domain.MasterImport;
import com.bdcor.pip.web.material.supp.filter.MaterImportFilter;
import com.bdcor.pip.web.material.supp.service.DeviceService;
/**
 * 登记入库
 * @author rp
 *
 */
@Controller
@RequestMapping("material/regist")
public class RegistImportController {

	@Autowired
	DeviceService deviceService;
	
	@RequestMapping
	public String init(){
		return "material/regist/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MasterImport> getAllMaterNames(MaterImportFilter filter){
		filter.setActiveclassCode(BdcorCodeStringDefinition.ACTIVECLASS_CODE);//入库状态需审核,针对登记入库
		List<MasterImport> list = deviceService.getAllMaterImports(filter);
		JqgridResponse<MasterImport> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
	/**
	 * 修改入库状态  0:未审核 1:审核通过 2:退回
	 * @param imorderNo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "updateState",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> updateState(MasterImport materIn){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materIn.setType("2");//更新
			deviceService.saveOrUpdateImportMaster(materIn);
			if(materIn.getImportState()==1){//审核通过,将该入库单下面的资产全部入库
				deviceService.batchPutImportDetailToStore(materIn.getImorderNo());
			}
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
}
