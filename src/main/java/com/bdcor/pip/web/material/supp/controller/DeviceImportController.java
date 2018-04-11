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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.client.tools.DateUtils;
import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.domain.MasterImport;
import com.bdcor.pip.web.material.supp.domain.MasterImportDetail;
import com.bdcor.pip.web.material.supp.domain.MaterialActiveType;
import com.bdcor.pip.web.material.supp.filter.MaterImportDetailFilter;
import com.bdcor.pip.web.material.supp.filter.MaterImportFilter;
import com.bdcor.pip.web.material.supp.service.DeviceService;

/**
 * @author rp
 * 
 */
@Controller
@RequestMapping("material/devwarehouse")
public class DeviceImportController {

	@Autowired
	DeviceService deviceService;

	/**
	 * 进入设备入库界面
	 * 
	 * @return
	 */
	@RequestMapping()
	public String init() {
		return "material/devwarehouse/list";
	}
	
	/**
	 * 进入设备入库明细界面
	 * 
	 * @return
	 */
	@RequestMapping("detail")
	public String initDetail() {
		return "material/devwarehouse/detail/list";
	}
	/**
	 * 确认订单号是否唯一
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "checkImportNo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkImportNo(
			@RequestParam("imorderNo") String imorderNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MasterImport masterImport = deviceService
					.selectMasterImportByImorderNo(imorderNo);
			if (null != masterImport) {
				result.put("result", true);
			} else {
				result.put("result", false);
			}

		} catch (Exception e) {
			result.put("result", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "checkArchivesNo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkArchivesNo(
			@RequestParam("archivesNo") String archivesNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// pipMmsDevimportdetail
			MasterImportDetail checkArchivesNo = deviceService
					.checkArchivesNo(archivesNo);
			if (null == checkArchivesNo) {
				result.put("success", true);
			} else {
				result.put("success", false);
				result.put("msg", "设备编号重复！请重新输入");
			}

		} catch (Exception e) {
			result.put("success", false);
			e.printStackTrace();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MasterImport> getAllMaterNames(
			MaterImportFilter filter) {
		List<MasterImport> list = deviceService.getAllMaterImports(filter);
		JqgridResponse<MasterImport> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	/**
	 * 设备入库新增/修改界面
	 * 
	 * @param state
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "openmodalAddOrUpdate", method = RequestMethod.GET)
	public ModelAndView openInput(
			@RequestParam(value = "state") String state,
			@RequestParam(value = "imorderNo", required = false) String imorderNo) {
		ModelAndView mav = new ModelAndView("material/devwarehouse/form");
		String projectName = Securitys.getUser().getCurrent_projectName();
		String projectId = Securitys.getUser().getCurrent_projectId();
		mav.addObject("orderNo", DateUtils.getOrderNoByNowDay());
		mav.addObject("projectName", projectName);
		if (imorderNo != null) {
			MasterImport mat = new MasterImport();
			mat.setProjectId(projectId);
			mat.setImorderNo(imorderNo);
			mat = deviceService.selectImportMasterByPrimaryKey(mat);
			mav.addObject("mat", mat);
		}

		List<MaterialActiveType> matList = deviceService
				.selectActiveTypeByState(state, projectId);
		mav.addObject("matList", matList);// 入库类别
		return mav;
	}

	/**
	 * 新增/修改入库单信息
	 * 
	 * @param masterImport
	 * @return
	 */
	@Transactional
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "saveOrUpdateMasterImport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMasterImport(
			@RequestParam("m") String m, HttpServletRequest req, String list) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			// 原来的逻辑
			// deviceService.saveOrUpdateImportMaster(masterImport);
			deviceService.saveImportAndDetail(m, list);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("msg", "设备编号重复或者系统异常");
			e.printStackTrace();
			// 事务回滚
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	/*
	 * //原来的逻辑
	 * 
	 * @ResponseBody public ResponseEntity<?>
	 * addOrUpdateMasterImport(MasterImport masterImport){ Map<String, Object>
	 * result = new HashMap<String, Object>(); try{
	 * deviceService.saveOrUpdateImportMaster(masterImport);
	 * result.put("success", true); }catch(Exception e){ result.put("success",
	 * false); } return new ResponseEntity(result, HttpStatus.OK); }
	 */
	/**
	 * 检查入库单号是否存在
	 * 
	 * @param masterImport
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "checkimorderNoExists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkimorderNoExists(MasterImport masterImport) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			masterImport.setProjectId(Securitys.getUser()
					.getCurrent_projectId());
			MasterImport obj = deviceService
					.selectImportMasterByPrimaryKey(masterImport);// 查询该入库单号下是否存在记录
			if (obj != null) {
				result.put("result", true);// 该入库单号已存在
			} else {
				result.put("result", false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	/**
	 * 查询入库单下面对应多少条入库明细信息
	 */
	@RequestMapping(value = "countMasterImportDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int countMasterImportDetail(
			@RequestParam(value = "imorderNo") String imorderNo) {
		return deviceService.countImportMasterDetail(imorderNo);
	}

	/**
	 * 删除入库基本信息
	 * 
	 * @param imorderNo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(MasterImport masterIn) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		masterIn.setProjectId(projectId);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			deviceService.delete(masterIn);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	/**
	 * 进入设备明细 新增/修改界面
	 * 
	 * @param state
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "openmodalAddOrUpdateDetail", method = RequestMethod.GET)
	public ModelAndView openInputDetail(
			@RequestParam(value = "state") String state,
			@RequestParam(value = "imorderNo", required = false) String imorderNo,
			@RequestParam(value = "id", required = false) Long id) {
		ModelAndView mav = new ModelAndView("material/devwarehouse/detailform");
		String projectName = Securitys.getUser().getCurrent_projectName();
		mav.addObject("projectName", projectName);
		mav.addObject("imorderNo", imorderNo);
		if (id != null) {
			MasterImportDetail mat = new MasterImportDetail();
			mat.setId(id);
			// id能确定唯一
			mat = deviceService.selectImportMasterDetail(mat);
			mav.addObject("mat", mat);
			mav.addObject("imorderNo", mat.getImorderNo());
		}
		return mav;
	}
	/**
	 * 
	 * description:  入库明细查询给detail页面使用的
	 * @author yangfeng  
	 * @param filter
	 * @return   
	 * @update 2016年1月27日
	 */
	@RequestMapping(value = "to_detaillist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MasterImportDetail> to_detaillist(
			MaterImportDetailFilter filter) {
		List<MasterImportDetail> list = deviceService.to_detaillist(filter);
		JqgridResponse<MasterImportDetail> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	@RequestMapping(value = "detaillist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MasterImportDetail> getAllMaterDetails(
			MaterImportDetailFilter filter) {
		List<MasterImportDetail> list = deviceService.getAllMaterDetail(filter);
		JqgridResponse<MasterImportDetail> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	/**
	 * 添加或者修改设备明细信息
	 * 
	 * @param masterImport
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "saveOrUpdateMasterImportDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMasterImportDetail(
			MasterImportDetail masterImportDetail) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			deviceService.saveOrUpdateImportMasterDetail(masterImportDetail);
			result.put("success", true);
		} catch (ServiceException se) {
			result.put("success", false);
			result.put("msg", se.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	/**
	 * 删除入库明细信息
	 * 
	 * @param imorderNo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "deleteDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteDetail(MasterImportDetail masterIn) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			deviceService.deleteImportMasterDetail(masterIn);
			result.put("success", true);
		} catch (ServiceException se) {
			result.put("success", false);
			result.put("msg", se.getMessage());
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
