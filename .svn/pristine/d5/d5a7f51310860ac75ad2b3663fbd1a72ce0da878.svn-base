/**
 * 
 */
package com.bdcor.pip.web.material.supp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.bdcor.pip.core.excelExport.lang.reflect.Beans;
import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.security.shiro.ShiroUser;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.domain.MasterImport;
import com.bdcor.pip.web.material.supp.domain.MasterImportDetail;
import com.bdcor.pip.web.material.supp.domain.MaterStore;
import com.bdcor.pip.web.material.supp.domain.MaterialActiveType;
import com.bdcor.pip.web.material.supp.filter.MaterImportDetailFilter;
import com.bdcor.pip.web.material.supp.filter.MaterImportFilter;
import com.bdcor.pip.web.material.supp.service.MaterialService;

/**
 * 物质入库
 * @author rp
 *
 */
@Controller
@RequestMapping("material/warehouse")
public class MaterialController {
	
	@Autowired
	MaterialService materialService;
	/**
	 * 进入物质入库界面
	 * @return
	 */
	@RequestMapping()
	public String init(){
		return "material/warehouse/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MasterImport> getAllMaterNames(MaterImportFilter filter){
		List<MasterImport> list = materialService.getAllMaterImports(filter);
		JqgridResponse<MasterImport> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
	/**
	 * 物质入库新增/修改界面
	 * @param state
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "openmodalAddOrUpdate", method = RequestMethod.GET)
    public ModelAndView openInput(@RequestParam(value = "state") String state,
    		@RequestParam(value="imorderNo",required=false)String imorderNo) {
    	ModelAndView mav = new ModelAndView("material/warehouse/form");
    	String projectName=Securitys.getUser().getCurrent_projectName();
    	String projectId=Securitys.getUser().getCurrent_projectId();
    	mav.addObject("projectName", projectName);
    	if(imorderNo!=null){
    		MasterImport mat=new MasterImport();
    		mat.setProjectId(projectId);
    		mat.setImorderNo(imorderNo);
    		mat=materialService.selectImportMasterByPrimaryKey(mat);
    		mav.addObject("mat", mat);
    	}
    	
    	List<MaterialActiveType> matList=materialService.selectActiveTypeByState(state,projectId);
    	mav.addObject("matList",matList);//入库类别
    	return mav;
    }
	/**
	 * 新增/修改入库单信息
	 * @param masterImport
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "saveOrUpdateMasterImport",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMasterImport(MasterImport masterImport){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materialService.saveOrUpdateImportMaster(masterImport);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	/**
	 * 检查入库单号是否存在
	 * @param masterImport
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "checkimorderNoExists",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkimorderNoExists(MasterImport masterImport){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			masterImport.setProjectId(Securitys.getUser().getCurrent_projectId());
			MasterImport obj=materialService.selectImportMasterByPrimaryKey(masterImport);//查询该入库单号下是否存在记录
			if(obj!=null){
				result.put("result", true);//该入库单号已存在
			}else{
				result.put("result", false);
			}
		}catch(Exception e){
			e.printStackTrace();
			result.put("result", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	/**
	 * 查询入库单下面对应多少条入库明细信息
	 * 
	 * 
	 */
	@RequestMapping(value = "countMasterImportDetail",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int countMasterImportDetail(@RequestParam(value="imorderNo")String imorderNo){
		return materialService.countImportMasterDetail(imorderNo);
	}
	
	/**
	 * 删除入库基本信息
	 * @param imorderNo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "delete",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(MasterImport masterIn){
		String projectId=Securitys.getUser().getCurrent_projectId();
		masterIn.setProjectId(projectId);
		
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materialService.delete(masterIn);
			result.put("success", true);
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	
	/**
	 * 进入物质明细 新增/修改界面
	 * @param state
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "openmodalAddOrUpdateDetail", method = RequestMethod.GET)
    public ModelAndView openInputDetail(@RequestParam(value = "state") String state,
    		@RequestParam(value="imorderNo",required=false)String imorderNo,
    		@RequestParam(value="id",required=false)Long id) {
    	ModelAndView mav = new ModelAndView("material/warehouse/detailform");
    	String projectName=Securitys.getUser().getCurrent_projectName();
    	mav.addObject("projectName", projectName);
    	mav.addObject("imorderNo", imorderNo);
    	if(id!=null){
    		MasterImportDetail mat=new MasterImportDetail();
    		mat.setId(id);
    		//id能确定唯一
    		mat=materialService.selectImportMasterDetail(mat);
    		mav.addObject("mat", mat);
    		mav.addObject("imorderNo", mat.getImorderNo());
    	}
    	return mav;
    }
	@RequestMapping(value = "detaillist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MasterImportDetail> getAllMaterDetails(MaterImportDetailFilter filter){
		List<MasterImportDetail> list = materialService.getAllMaterDetail(filter);
		JqgridResponse<MasterImportDetail> response = JqgridResponseContext.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	
	/**
	 * 添加或者修改物质明细信息
	 * @param masterImport
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "saveOrUpdateMasterImportDetail",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMasterImportDetail(MasterImportDetail masterImportDetail){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materialService.saveOrUpdateImportMasterDetail(masterImportDetail);
			result.put("success", true);
		}catch(ServiceException se){
			result.put("success",false);
			result.put("msg",se.getMessage());
		}catch(Exception e){
			e.printStackTrace();
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
	/**
	 * 删除入库明细信息
	 * @param imorderNo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "deleteDetail",method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteDetail(MasterImportDetail masterIn){
		Map<String, Object> result = new HashMap<String, Object>();
		try{
			materialService.deleteImportMasterDetail(masterIn);
			result.put("success", true);
		}catch(ServiceException se){
			result.put("success",false);
			result.put("msg",se.getMessage());
		}catch(Exception e){
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	
}
