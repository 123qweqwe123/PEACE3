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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.client.tools.DateUtils;
import com.bdcor.pip.core.excelExport.lang.reflect.Beans;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.domain.MasterImport;
import com.bdcor.pip.web.material.supp.domain.MaterExport;
import com.bdcor.pip.web.material.supp.domain.MaterExportDetail;
import com.bdcor.pip.web.material.supp.domain.MaterStore;
import com.bdcor.pip.web.material.supp.domain.MaterialActiveType;
import com.bdcor.pip.web.material.supp.filter.MaterExportDetailFilter;
import com.bdcor.pip.web.material.supp.filter.MaterExportFilter;
import com.bdcor.pip.web.material.supp.service.DeviceService;
import com.bdcor.pip.web.material.supp.service.MaterialService;

/**
 * 设备出库
 * 
 * @author rp
 * 
 */
@Controller
@RequestMapping("material/devdelivery")
public class DeviceExportController {

	@Autowired
	DeviceService deviceService;
	@Autowired
	MaterialService materialService;
	/**
	 * 进入设备出库界面
	 * 
	 * @return
	 */
	@RequestMapping()
	public String init() {
		return "material/devdelivery/list";
	}
	/**
	 * 进入设备出库明细界面
	 * 
	 * @return
	 */
	@RequestMapping("detail")
	public String initDetail() {
		return "material/devdelivery/detail/list";
	}
	/** 
	 * 确认出库单号是否唯一
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "checkExorderNo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkExorderNo(
			@RequestParam("exorderNo") String exorderNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MaterExport materExport = deviceService.checkExorderNo(exorderNo);
			if (null !=materExport ) {
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
	/**
	 * 
	 * description: 打印订单格式
	 * 
	 * @author yangfeng
	 * @param ids
	 * @return
	 * @update 2015-12-22
	 */
	@RequestMapping(value = "look", method = RequestMethod.GET)
	public ModelAndView look(@RequestParam("ids") String ids) {
		ModelAndView mav = new ModelAndView("material/devdelivery/look");
		List<MaterExport> materExports;
		try {
			materExports = deviceService.getAllMaterExportsByExportNo(ids);
			for (MaterExport materExport : materExports) {
				List<MaterExportDetail> lists = deviceService
						.getAllMaterExportDetail(materExport.getExorderNo());
				materExport.setMaterExportDetails(lists);
			}
			mav.addObject("materExports", materExports);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterExport> getAllMaterNames(MaterExportFilter filter) {
		List<MaterExport> list = deviceService.getAllMaterExports(filter);
		JqgridResponse<MaterExport> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	/**
	 * 设备出库新增/修改界面
	 * 
	 * @param state
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "openmodalAddOrUpdate", method = RequestMethod.GET)
	public ModelAndView openInput(
			@RequestParam(value = "state") String state,
			@RequestParam(value = "exorderNo", required = false) String exorderNo) {
		ModelAndView mav = new ModelAndView("material/devdelivery/form");
		String projectName = Securitys.getUser().getCurrent_projectName();
		String projectId = Securitys.getUser().getCurrent_projectId();
		mav.addObject("projectName", projectName);
		mav.addObject("orderNo", DateUtils.getOrderNoByNowDay());
		if (exorderNo != null) {
			MaterExport mat = new MaterExport();
			mat.setProjectId(projectId);
			mat.setExorderNo(exorderNo);
			mat = deviceService.selectExportMasterByPrimaryKey(mat);
			mav.addObject("mat", mat);
		}
		List<MaterialActiveType> matList = deviceService
				.selectActiveTypeByState(state, projectId);
		mav.addObject("matList", matList);// 出库类别 state=2
		return mav;
	}

	/**
	 * 新增/修改出库单信息
	 * 
	 * @param masterImport
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "saveOrUpdateMasterExport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMasterImport(MaterExport materExport) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			deviceService.saveOrUpdateExportMater(materExport);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	/**
	 * 
	 * description:修改出库单的方法 将生成订单和订单明细结合在一起
	 * 
	 * @author yangfeng
	 * @param materExport
	 * @return
	 * @update 2015-12-15
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "saveOrUpdateMasterExport2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMasterImport2(
			@RequestParam("m") String m, @RequestParam("list") String list) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			deviceService.saveOrUpdateExportMaterAndDetail(m, list);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	/**
	 * 检查出库单号是否存在
	 * 
	 * @param masterImport
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "checkexorderNoExists", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> checkimorderNoExists(String exorderNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			MaterExport obj = deviceService
					.checkExportMasterIsExists(exorderNo);
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
	 * 查询出库单下面对应多少条出库明细信息
	 * 
	 * 
	 */
	@RequestMapping(value = "countMasterExportDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public int countMasterImportDetail(
			@RequestParam(value = "exorderNo") String exorderNo) {
		return deviceService.countExportMasterDetail(exorderNo);
	}

	/**
	 * 删除出库基本信息 将出库单的
	 * 
	 * @param imorderNo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> delete(MaterExport materEx) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		String lccCode = Securitys.getUser().getLccCode();
		materEx.setProjectId(projectId);
		materEx.setLccCode(lccCode);
		materEx.setIsRemoved((short) 1);// 已删除
		materEx.setType("2");// 更新
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			deviceService.saveOrUpdateExportMater(materEx);
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
			@RequestParam(value = "exorderNo", required = false) String exorderNo,
			@RequestParam(value = "id", required = false) Long id,
			@RequestParam(value = "exportLccCode", required = false) String exportLccCode,
			@RequestParam(value = "exportStockCode", required = false) String exportStockCode) {
		ModelAndView mav = new ModelAndView("material/devdelivery/detailform");
		String projectName = Securitys.getUser().getCurrent_projectName();
		mav.addObject("projectName", projectName);
		mav.addObject("exorderNo", exorderNo);
		mav.addObject("exportStockCode", exportStockCode);
		mav.addObject("exportLccCode", exportLccCode);
		// 由出库单号查询出出库的来源库房
		MaterExport me = new MaterExport();
		me.setExorderNo(exorderNo);
		me = deviceService.selectExportMasterByPrimaryKey(me);
		mav.addObject("stockCode", me.getStockCode());
		if (id != null) {
			MaterExportDetail mat = new MaterExportDetail();
			mat.setId(id);
			// id能确定唯一
			mat = deviceService.selectExportMasterDetail(mat);
			// 得到库存单位和库存数量
			MaterStore ms = new MaterStore();
			Beans.copyProperties(mat, ms, false);
			ms = deviceService.selectMaterStore(ms);
			mav.addObject("ms", ms);
			mav.addObject("mat", mat);
			mav.addObject("exorderNo", mat.getExorderNo());
		}
		return mav;
	}
	/**
	 * 出库明细信息列表 给detail使用的
	 * 
	 * @param filter
	 * @return
	 */
	@RequestMapping(value = "to_detaillist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterExportDetail> to_detaillist(
			MaterExportDetailFilter filter) {
		List<MaterExportDetail> list = deviceService
				.to_detaillist(filter);
		JqgridResponse<MaterExportDetail> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}
	/**
	 * 出库明细信息列表
	 * 
	 * @param filter
	 * @return
	 */
	@RequestMapping(value = "detaillist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterExportDetail> getAllMaterExportDetails(
			MaterExportDetailFilter filter) {
		List<MaterExportDetail> list = deviceService
				.getAllMaterExportDetail(filter);
		JqgridResponse<MaterExportDetail> response = JqgridResponseContext
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
	@RequestMapping(value = "saveOrUpdateMasterExportDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addOrUpdateMasterImportDetail(
			MaterExportDetail materExportDetail) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			deviceService.saveOrUpdateExportMaterDetail(materExportDetail);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	/**
	 * 删除出库明细信息
	 * 
	 * @param imorderNo
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "deleteDetail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> deleteDetail(MaterExportDetail masterEx) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			deviceService.deleteExportMaterDetail(masterEx);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = "comboxData", produces = "application/json")
	@ResponseBody
	public List<MaterStore> comboxData(MaterStore ms) {
		return deviceService.selectJsonForAutocomplete(ms);
	}

}
