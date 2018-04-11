/**
 * 
 */
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
import com.bdcor.pip.web.material.supp.domain.MasterImportDetail;
import com.bdcor.pip.web.material.supp.domain.MaterExport;
import com.bdcor.pip.web.material.supp.domain.MaterExportDetail;
import com.bdcor.pip.web.material.supp.domain.MaterStore;
import com.bdcor.pip.web.material.supp.domain.MaterialActiveType;
import com.bdcor.pip.web.material.supp.filter.MaterExportDetailFilter;
import com.bdcor.pip.web.material.supp.filter.MaterExportFilter;
import com.bdcor.pip.web.material.supp.service.MaterialService;

/**
 * 耗材出库管理
 * 
 * @author rp
 * 
 */
@Controller
@RequestMapping("material/matdelivery")
public class MaterialExportController {
	
	@Autowired
	MaterialService materialService;
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
		ModelAndView mav = new ModelAndView("material/matdelivery/look");
		List<MaterExport> materExports;
		try {
			materExports = materialService.getAllMaterExportsByExportNo(ids);
			for (MaterExport materExport : materExports) {
				List<MaterExportDetail> lists = materialService
						.getAllMaterExportDetail(materExport.getExorderNo());
				materExport.setMaterExportDetails(lists);
			}
			mav.addObject("materExports", materExports);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	/**
	 * 进入物质出库界面
	 * 
	 * @return
	 */
	@RequestMapping()
	public String init() {
		return "material/matdelivery/list";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MaterExport> getAllMaterNames(MaterExportFilter filter) {
		List<MaterExport> list = materialService.getAllMaterExports(filter);
		JqgridResponse<MaterExport> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	/**
	 * 物质出库新增/修改界面
	 * 
	 * @param state
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "openmodalAddOrUpdate", method = RequestMethod.GET)
	public ModelAndView openInput(
			@RequestParam(value = "state") String state,
			@RequestParam(value = "exorderNo", required = false) String exorderNo) {
		ModelAndView mav = new ModelAndView("material/matdelivery/form1");
		// String projectName=Securitys.getUser().getCurrent_projectName();
		String projectId = Securitys.getUser().getCurrent_projectId();
		// mav.addObject("projectName", projectName);
		// if(exorderNo!=null){
		// MaterExport mat=new MaterExport();
		// mat.setProjectId(projectId);
		// mat.setExorderNo(exorderNo);
		// mat=materialService.selectExportMasterByPrimaryKey(mat);
		// mav.addObject("mat", mat);
		// }
		mav.addObject("orderNo", DateUtils.getOrderNoByNowDay());
		List<MaterialActiveType> matList = materialService
				.selectActiveTypeByState(state, projectId);
		mav.addObject("matList", matList);// 出库类别 state=2
		return mav;
	}

	/**
	 * 新增/修改入库单信息
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
			materialService.saveOrUpdateExportMater(materExport);
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
			MaterExport obj = materialService
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
		return materialService.countExportMasterDetail(exorderNo);
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
			materialService.saveOrUpdateExportMater(materEx);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	/**
	 * 进入物质明细 新增/修改界面
	 * 
	 * @param state
	 * @param imorderNo
	 * @return
	 */
	@RequestMapping(value = "openmodalAddOrUpdateDetail", method = RequestMethod.GET)
	public ModelAndView openInputDetail(
			@RequestParam(value = "state") String state,
			@RequestParam(value = "exorderNo", required = false) String exorderNo,
			@RequestParam(value = "id", required = false) Long id) {
		ModelAndView mav = new ModelAndView("material/matdelivery/detailform");
		String projectName = Securitys.getUser().getCurrent_projectName();
		mav.addObject("projectName", projectName);
		mav.addObject("exorderNo", exorderNo);
		// 由出库单号查询出出库的来源库房
		MaterExport me = new MaterExport();
		me.setExorderNo(exorderNo);
		me = materialService.selectExportMasterByPrimaryKey(me);
		mav.addObject("stockCode", me.getStockCode());
		if (id != null) {
			MaterExportDetail mat = new MaterExportDetail();
			mat.setId(id);
			// id能确定唯一
			mat = materialService.selectExportMasterDetail(mat);
			// 得到库存单位和库存数量
			MaterStore ms = new MaterStore();
			Beans.copyProperties(mat, ms, false);
			ms = materialService.selectMaterStore(ms);
			mav.addObject("ms", ms);
			mav.addObject("mat", mat);
			mav.addObject("exorderNo", mat.getExorderNo());
		}
		return mav;
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
		List<MaterExportDetail> list = materialService
				.getAllMaterExportDetail(filter);
		JqgridResponse<MaterExportDetail> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	/**
	 * 添加或者修改物质明细信息
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
			materialService.saveOrUpdateExportMaterDetail(materExportDetail);
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
			materialService.deleteExportMaterDetail(masterEx);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping(value = "comboxData", produces = "application/json")
	@ResponseBody
	public List<MaterStore> comboxData(MaterStore ms) {
		return materialService.selectJsonForAutocomplete(ms);
	}

	/**
	 * 添加出库单和出库单明细
	 * 
	 * @param masterImport
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addMasterExport", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addMasterExport(MaterExport materExport) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			materialService.addMasterExport(materExport);
			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msg", e.getMessage());
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
}
