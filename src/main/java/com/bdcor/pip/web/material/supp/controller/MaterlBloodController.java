package com.bdcor.pip.web.material.supp.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.client.tools.DateUtils;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.PipMmsScmarchivesMapper;
import com.bdcor.pip.web.material.supp.dao.PipMmsStorehouseMapper;
import com.bdcor.pip.web.material.supp.domain.BooldExcelVo;
import com.bdcor.pip.web.material.supp.domain.MaterExport;
import com.bdcor.pip.web.material.supp.domain.MaterExportDetail;
import com.bdcor.pip.web.material.supp.domain.OrderDetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouse;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmmaster;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmmaster;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchives;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmstock;
import com.bdcor.pip.web.material.supp.domain.PipMmsStorehouse;
import com.bdcor.pip.web.material.supp.filter.OrderDetalFilter;
import com.bdcor.pip.web.material.supp.filter.PipMmsExscmmasterFilter;
import com.bdcor.pip.web.material.supp.filter.PipMmsImscmdetalFilter;
import com.bdcor.pip.web.material.supp.filter.PipMmsImscmmasterFilter;
import com.bdcor.pip.web.material.supp.filter.PipMmsScmarchivesFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsDefstorehouseService;
import com.bdcor.pip.web.material.supp.service.PipMmsImscmdetalService;
import com.bdcor.pip.web.material.supp.service.PipMmsImscmmasterService;
import com.bdcor.pip.web.material.supp.service.PipMmsScmarchivesService;
import com.bdcor.pip.web.material.supp.service.impl.PipMmsExscmmasterServiceImpl;
import com.bdcor.pip.web.material.supp.service.impl.PipMmsScmstockServiceImpl;
import com.bdcor.pip.web.pro.promgt.service.LccService;

/**
 * 物资库存(冻存管管理)
 * 
 * @author rp
 * 
 */
@Controller
@RequestMapping("material/blood")
public class MaterlBloodController {

	private POIFSFileSystem fs = null;
	private HSSFWorkbook wb = null;
	private HSSFSheet sheet = null;
	private HSSFRow row = null;
	@Autowired
	PipMmsScmarchivesMapper pipMmsScmarchivesMapper;
	@Autowired
	PipMmsStorehouseMapper pipMmsStorehouseMapper;
	@Autowired
	PipMmsImscmmasterService pipMmsImscmmasterServiceImpl;
	@Autowired
	private PipMmsScmarchivesService scmarService;
	@Autowired
	PipMmsDefstorehouseService pipMmsDefstorehouseService;
	@Autowired
	PipMmsExscmmasterServiceImpl pipMmsExscmmasterServiceImpl;
	@Autowired
	private PipMmsImscmdetalService detalService;
	@Autowired
	PipMmsScmstockServiceImpl pipMmsScmstockServiceImpl;
	// @Autowired
	// PipMmsScmstockServiceImpl pipMmsScmstockServiceImpl;
	@Autowired
	private LccService lccService;

	@RequestMapping(value = "openmodalScmarchives", method = RequestMethod.GET)
	public String openmodalScmarchives(Model model, String archivesNo) {
		model.addAttribute("archivesNo", archivesNo);
		return "material/blood/gridScmarchives";
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
			PipMmsExscmmaster pipMmsExscmmaster = pipMmsExscmmasterServiceImpl.selectModelByExorderNo(exorderNo); 
			if (null != pipMmsExscmmaster ) {
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
		ModelAndView mav = new ModelAndView("material/blood/look");
		List<PipMmsExscmmaster> pipMmsExscmmasters;
		try {
			pipMmsExscmmasters = pipMmsExscmmasterServiceImpl.listByIds(ids);
			for (PipMmsExscmmaster pipMmsExscmmaster : pipMmsExscmmasters) {
				List<PipMmsExscmdetal> lists = pipMmsExscmmasterServiceImpl
						.getExportDetail(pipMmsExscmmaster.getExorderNo());
				pipMmsExscmmaster.setPipMmsExscmdetals(lists);
			}
			mav.addObject("exscmmasters", pipMmsExscmmasters);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	// 出库展示
	@RequestMapping(value = "outorderlist", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipMmsExscmmaster> outorderlist(PipMmsExscmmasterFilter pmemf) {
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			pmemf.setLccCode(null);
		} else {
			pmemf.setLccCode(Securitys.getUser().getLccCode());
		}
		List<PipMmsExscmmaster> data = pipMmsExscmmasterServiceImpl
				.selectPipMmsExscmmasterByLccCode(pmemf);
		JqgridResponse<PipMmsExscmmaster> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(data);
		return response;

	}
	/*// 出库展示
		@RequestMapping(value = "outorderlist", produces = MediaType.APPLICATION_JSON_VALUE)
		public @ResponseBody
		JqgridResponse<PipMmsExscmmaster> outorderlist(PipMmsExscmmasterFilter pmemf) {
			PipMmsExscmmaster pmem = new PipMmsExscmmaster();
			if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
				pmem.setLccCode(null);
			} else {
				pmem.setLccCode(Securitys.getUser().getLccCode());
			}
			pmem.setStockCode(pmemf.getStockCode());
			pmem.setExorderNo(pmemf.getExorderNo());
			List<PipMmsExscmmaster> data = pipMmsExscmmasterServiceImpl
					.selectPipMmsExscmmasterByLccCode(pmem);
			JqgridResponse<PipMmsExscmmaster> response = JqgridResponseContext
					.getJqgridResponse();
			response.setRows(data);
			return response;

		}*/
	/**
	 * 
	 * description:查看箱内具体物品
	 * 
	 * @author yangfeng
	 * @param filter
	 * @return pipMmsScmarchives
	 * @update 2015-10-29
	 */
	@RequestMapping(value = "listScmarchives", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipMmsScmarchives> listScmarchives(
			PipMmsScmarchivesFilter filter) {
		List<PipMmsScmarchives> list = scmarService.list(filter);
		JqgridResponse<PipMmsScmarchives> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping(value = "listDetal", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipMmsImscmdetal> getListDetal(
			PipMmsImscmdetalFilter filter) {
		List<PipMmsImscmdetal> list = detalService.list(filter);
		JqgridResponse<PipMmsImscmdetal> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipMmsImscmmaster> getList(
			PipMmsImscmmasterFilter filter) {
		List<PipMmsImscmmaster> list = pipMmsImscmmasterServiceImpl
				.list(filter);
		JqgridResponse<PipMmsImscmmaster> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping
	public String init() {
		return "material/blood/list";
	}

	@RequestMapping(value = "openmodaladdjunctinput", method = RequestMethod.GET)
	public String addjunctInput(Model model) {
		// model.addAttribute("typeList", fileService.getAdjunctType());
		return "material/blood/uploadform";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "uploadDocument", method = RequestMethod.POST)
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();

		String stockCode = request.getParameter("stockCode") == null ? ""
				: request.getParameter("stockCode");
		String stockName = request.getParameter("stockName") == null ? ""
				: request.getParameter("stockName");
		long fileSize = file.getSize();
		if (fileSize > 10240000) {
			result.put("msg", "文件大小不能超过10M!");
			result.put("success", false);
			return new ResponseEntity(result, HttpStatus.OK);
		}

		String imorderNo = DateUtils.getOrderNoByNowDay();

		// String projectId = Securitys.getUser().getCurrent_projectId();
		// String ctxPath =
		// request.getSession().getServletContext().getRealPath("") +
		// "/uploadfiles/";
		String ctxPath = CustomizedPropertyPlaceholderConfigurer
				.getContextProperty("project_document_upload_dir");

		File dirPath = new File(ctxPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		String fileName = file.getOriginalFilename();// linux 服务器上是gbk的编码
		File filePath = new File(ctxPath + fileName);

		try {
			if (!filePath.exists()) {
				filePath.createNewFile();
			}
			// 文件上传
			file.transferTo(filePath);

			// 保存订单记录
			// PipMmsImscmmaster pmi = getPipMmsImscmmaster(imorderNo);
			// pipMmsImscmmasterServiceImpl.insert(pmi );

			// 文件解析
			List excleList = readExcelContent(filePath, imorderNo);
			// 比较 list 里面的数据
			Set setBox = new HashSet();
			String str = compileList(excleList, setBox);
			if (str != null && !"".equals(str)) {
				result.put("msg", str + "  请整理完整excle后重新全部导入");
				result.put("success", false);
				return new ResponseEntity(result, HttpStatus.OK);
			}

			// 保存excle数据
			excelContentToOracle(setBox, excleList, imorderNo, stockCode,
					stockName);

			result.put("success", true);
		} catch (Exception e) {
			e.printStackTrace();
			if (filePath.isFile() && filePath.exists()) {
				filePath.delete();
			}
			result.put("msg", "上传失败，请联系管理员！");
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	// 出库展示
	@RequestMapping(value = "outorderpage")
	public String outorderpage(
			@RequestParam(value = "exorderNo", required = false) String exorderNo,
			Model model) {
		PipMmsDefstorehouse pmdf = new PipMmsDefstorehouse();
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			pmdf.setLccCode(null);
		} else {
			pmdf.setLccCode(Securitys.getUser().getLccCode());
		}
		pmdf.setExorderNo(exorderNo);
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			model.addAttribute("lccDictList", lccService.getDataLimitLcc());
		} else {
			model.addAttribute("lccDictList",
					lccService.getDataLimitLccForLccCode());

		}
		model.addAttribute("defstorehouseList",
				pipMmsDefstorehouseService.getAllHouseListByLccCode(pmdf));
		return "material/blood/outorderlist";

	}

	@RequestMapping(value = "saveExscmmaster")
	@ResponseBody
	public ResponseEntity<?> saveExscmmaster(OrderDetalFilter pcdf) {

		// 判断是否 订单已经存在过 如果订单存在过 就提示客户

		Map<String, Object> res = new HashMap<String, Object>();
		String ids[] = null;
		if (pcdf.getStr() != null && !"".equals(pcdf.getStr())) {
			ids = pcdf.getStr().split(",");
		}

		try {
			pipMmsExscmmasterServiceImpl
					.saveExscmmasterAndDetalUpdateStorck(pcdf);
		} catch (Exception e) {
			res.put("success", false);
			e.printStackTrace();
			return new ResponseEntity(res, HttpStatus.OK);
		}
		res.put("success", true);
		return new ResponseEntity(res, HttpStatus.OK);

	}
	@RequestMapping(value = "archivesNoAllByLccCode", produces = "application/json")
	@ResponseBody
	public List<PipMmsScmstock> archivesNoAllByLccCode(Model model) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<PipMmsScmstock> data = pipMmsScmstockServiceImpl
					.archivesNoAllByLccCode();
			// res.put("data", data);
			return data;
		} catch (Exception e) {
			return null;

		}

	}
	@RequestMapping(value = "archivesNoAllNoUse", produces = "application/json")
	@ResponseBody
	public List<PipMmsScmstock> archivesNoAllNoUse(Model model) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<PipMmsScmstock> data = pipMmsScmstockServiceImpl
					.archivesNoAllNoUse();
			// res.put("data", data);
			return data;
		} catch (Exception e) {
			return null;

		}

	}

	// 添加订单页面
	@RequestMapping(value = "openmodaladdneworder")
	public String openmodaladdneworder(Model model) {
		PipMmsDefstorehouse pmdf = new PipMmsDefstorehouse();
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			pmdf.setLccCode(null);
		} else {
			pmdf.setLccCode(Securitys.getUser().getLccCode());
		}
//		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
//			model.addAttribute("lccDictList", lccService.getDataLimitLcc());
//		} else {
//			model.addAttribute("lccDictList",
//					lccService.getDataLimitLccForLccCode());
//
//		}
		model.addAttribute("lccDictList",
				lccService.getLccList());
		// 库房编号
		PipMmsStorehouse pms2 = new PipMmsStorehouse();
		pms2.setLccCode(Securitys.getUser().getLccCode());
		List<PipMmsStorehouse> pmslist = pipMmsStorehouseMapper
				.getAllPipMmsStorehouse(pms2);
		if (pmslist != null && pmslist.size() > 0) {
			model.addAttribute("pmslist", pmslist.get(0));
		}
		// 先准备需要保存的订单编号
		String exorderNo = DateUtils.getOrderNoByNowDay();
		model.addAttribute("exorderNo", exorderNo);

		return "material/blood/addneworder";

	}

	

	// //查询所有库房
	// @RequestMapping(value = "getExportStockCode", produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public @ResponseBody JqgridResponse<PipMmsDefstorehouse>
	// getExportStockCode(@RequestParam(value="exportLccCode",required=true)
	// String exportLccCode) {
	//
	// // List<PipMmsDefstorehouse> data
	// =pipMmsDefstorehouseService.getExportStockCode(exportLccCode);
	// List<PipMmsDefstorehouse> data
	// =pipMmsStorehouseMapper.getExportStockCode(exportLccCode);
	// JqgridResponse<PipMmsDefstorehouse> response = JqgridResponseContext
	// .getJqgridResponse();
	// response.setRows(data);
	// return response;
	//
	// }
	// 查询所有库房
	@RequestMapping(value = "getExportStockCode", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity getExportStockCode(
			@RequestParam(value = "exportLccCode", required = true) String exportLccCode) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<PipMmsStorehouse> data = pipMmsStorehouseMapper
					.getExportStockCode(exportLccCode);
			if (data != null && data.size() > 0) {
				res.put("data", data);
				res.put("success", true);
				return new ResponseEntity(res, HttpStatus.OK);
			} else {
				res.put("data", null);
				res.put("success", false);
				return new ResponseEntity(res, HttpStatus.OK);
			}

		} catch (Exception e) {
			res.put("success", false);
			return new ResponseEntity(res, HttpStatus.OK);
		}
	}

	// 添加列表
	@RequestMapping(value = "prelist", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity prelist(
			@RequestParam(value = "archivesNo", required = false) String archivesNo) {
		Map<String, Object> res = new HashMap<String, Object>();
		OrderDetal od = new OrderDetal();
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			od.setLccCode(null);
		} else {
			od.setLccCode(Securitys.getUser().getLccCode());
		}
		if (archivesNo != null && !"".equals(archivesNo)) {
			od.setArchivesNo(archivesNo);
			List<OrderDetal> data = pipMmsExscmmasterServiceImpl
					.selectPipMmsExscmmasterByArchivesNo(od);
			// JqgridResponse<OrderDetal> response = JqgridResponseContext
			// .getJqgridResponse();
			// response.setRows(data);
			// return response;
			res.put("data", data);
			res.put("success", true);
			return new ResponseEntity(res, HttpStatus.OK);

		}
		res.put("success", false);
		return new ResponseEntity(res, HttpStatus.OK);
	}

	public PipMmsImscmmaster getPipMmsImscmmaster(String imorderNo) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		PipMmsImscmmaster pmi = new PipMmsImscmmaster();
		pmi.setProjectId(projectId);
		pmi.setImorderNo(imorderNo);
		String loginName = Securitys.getUser().getLoginName();
		String userName = Securitys.getUser().getName();
		String lccCode = Securitys.getUser().getLccCode();
		pmi.setLccCode(lccCode);
		pmi.setUserCode(loginName);
		pmi.setUserName(userName);
		pmi.setImportState(new Short((short) 1));
		pmi.setCreateDate(new Date());
		pmi.setActiveclassCode("采购入库");
		return pmi;
	}

	public void excelContentToOracle(Set setBox, List excleList,
			String imorderNo, String stockCode, String stockName) {

		pipMmsImscmmasterServiceImpl
				.insertImscmdetalAndStockAndScmarchivesAndImscmmaster(setBox,
						excleList, imorderNo, stockCode, stockName);

	}

	// 比较excle数据 是否有重复的 或者是否有 数据与数据库内重复
	public String compileList(List<BooldExcelVo> list, Set<String> setBox) {
		String str = "";
		String projectId = Securitys.getUser().getCurrent_projectId();
		// 自个excle 内部数据比较是否有空的 是否有重复的
		for (int i = 0; i < list.size(); i++) {
			BooldExcelVo bev1 = list.get(i);
			if (bev1.getArchivesNo() == null || "".equals(bev1.getArchivesNo())) {
				str += bev1.getRow() + "行【箱号】不能为空     ";
			}
			if (bev1.getBooldpackageCode() == null
					|| "".equals(bev1.getBooldpackageCode())) {
				str += bev1.getRow() + "行【采血包编号】不能为空     ";
			}
			if (bev1.getMaterlBatch() == null
					|| "".equals(bev1.getMaterlBatch())) {
				str += bev1.getRow() + "行【批次号】不能为空       ";
			}
			// 前面导入的3个字段不为空 才可以去执行下面的对比功能
			if (bev1.getArchivesNo() != null
					&& !"".equals(bev1.getArchivesNo())
					&& bev1.getBooldpackageCode() != null
					&& !"".equals(bev1.getBooldpackageCode())
					&& bev1.getMaterlBatch() != null
					&& !"".equals(bev1.getMaterlBatch())) {
				for (int j = i + 1; j < list.size(); j++) {
					if (i == j) {
						continue;
					}
					BooldExcelVo bev2 = list.get(j);
					// 比较有问题的数据
					if (bev1.getArchivesNo().equals(bev2.getArchivesNo())
							&& bev1.getBooldpackageCode().equals(
									bev2.getBooldpackageCode())
							&& bev1.getMaterlBatch().equals(
									bev2.getMaterlBatch())) {
						str += bev1.getRow() + "行 和 " + bev2.getRow()
								+ "行 有箱号、包号、批次号数据重复     ";
					}
					bev2 = null;
				}

				// 获得所有的箱号 然后看看是否导入过此箱号 和奇超沟通过 库内导入过此箱 就不能再导入
				setBox.add(bev1.getArchivesNo());

			} else {
				continue;
			}

			bev1 = null;

		}
		// 先看看这次导入的箱号 之前数据库内导入过么
		PipMmsImscmdetal pmi = null;
		for (String str2 : setBox) {
			pmi = new PipMmsImscmdetal();
			pmi.setProjectId(projectId);
			pmi.setArchivesNo(str2);
			int boxCount = detalService.selectArchivesNo(pmi);
			if (boxCount > 0) {
				str += "【" + str2 + "】箱号已经存在数据库内  ";
			}
			pmi = null;

		}
		if (str != null && !"".equals(str)) {
			return str;
		}

		// 数据库内比较是否有过次采血包
		for (BooldExcelVo bev : list) {
			int counts = pipMmsScmarchivesMapper
					.selectByPjIdAndArchNoAndPagCode(bev, projectId);
			if (counts > 0) {
				str += bev.getRow() + "行数据 数据库内已经存在此记录   ";
			}
		}
		return str;
	}

	public List readExcelContent(File file, String imorderNo)
			throws FileNotFoundException {
		InputStream is = new FileInputStream(file.toString());
		Map<Integer, String> content = new HashMap<Integer, String>();
		String str = "";
		List list = new ArrayList();
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		// 得到总行数
		int rowNum = sheet.getLastRowNum();
		row = sheet.getRow(0);
		// map.put("imorderNo", imorderNo);
		int colNum = row.getPhysicalNumberOfCells();
		// 正文内容应该从第二行开始,第一行为表头的标题
		BooldExcelVo be = null;
		for (int i = 1; i <= rowNum; i++) {
			row = sheet.getRow(i);
			if (row == null)
				continue;
			int j = 0;
			if (row.getCell((short) j) == null) {
				++j;
				break;
			}
			// 取所有数据如内存 比较处理
			be = new BooldExcelVo();
			be.setRow(String.valueOf(i));
			while (j < colNum) {

				// 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
				// str += getStringCellValue(row.getCell((short) j)).trim();

				// str += getCellFormatValue(row.getCell((short) j)).trim();
				// map.put(j+"", getCellFormatValue(row.getCell((short)
				// j))!=null &&
				// !"".equals(getCellFormatValue(row.getCell((short) j)) )
				// ?getCellFormatValue(row.getCell((short) j)).trim():null );

				if (j == 0) {// 采血包箱编号
					be.setArchivesNo(getCellFormatValue(row.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("archivesNo",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 1) {// 采血包编号
					be.setBooldpackageCode(getCellFormatValue(row
							.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("bolldpackageCode",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 2) {// 批次
					be.setMaterlBatch(getCellFormatValue(row.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("materlBatch",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 3) {// 采购价格
					be.setWholesalePrice(getCellFormatValue(row
							.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("wholesalePrice",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 4) {// 出库价格
					be.setMaterlPrice(getCellFormatValue(row.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("materlPrice",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 5) {// 规格
					be.setSpecifications(getCellFormatValue(row
							.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("specifications",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 6) { // 截止日期
					be.setPeriodValidity(getCellFormatValue(row
							.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("periodValidity",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 7) { // 供货厂商
					be.setSupplierCode(getCellFormatValue(row
							.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("supplierCode",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 8) { // 生产厂商 Manufacturer_code
					be.setManufacturerCode(getCellFormatValue(row
							.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("manufacturerCode",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				} else if (j == 9) { // 库房 STOCK_CODE
					be.setStockCode(getCellFormatValue(row.getCell((short) j)) != null
							&& !"".equals(getCellFormatValue(row
									.getCell((short) j))) ? getCellFormatValue(
							row.getCell((short) j)).trim() : null);
					// map.put("stockCode",
					// getCellFormatValue(row.getCell((short) j))!=null &&
					// !"".equals(getCellFormatValue(row.getCell((short) j)) )
					// ?getCellFormatValue(row.getCell((short) j)).trim():null
					// );
				}
				j++;
			}

			list.add(be);
			be = null;
			// 保存入库表和库存表
			// pipMmsImscmmasterServiceImpl.insertImscmdetalAndStock(map);

			// str = "";
		}
		return list;
	}

	/**
	 * 获取单元格数据内容为日期类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getDateCellValue(HSSFCell cell) {
		String result = "";
		try {
			int cellType = cell.getCellType();
			if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
				Date date = cell.getDateCellValue();
				result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
						+ "-" + date.getDate();
			} else if (cellType == HSSFCell.CELL_TYPE_STRING) {
				String date = getStringCellValue(cell);
				result = date.replaceAll("[年月]", "-").replace("日", "").trim();
			} else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
				result = "";
			}
		} catch (Exception e) {
			System.out.println("日期格式不正确!");
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取单元格数据内容为字符串类型的数据
	 * 
	 * @param cell
	 *            Excel单元格
	 * @return String 单元格数据内容
	 */
	private String getStringCellValue(HSSFCell cell) {
		String strCell = "";
		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_STRING:
			strCell = cell.getStringCellValue();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:
			strCell = String.valueOf(cell.getNumericCellValue());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:
			strCell = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			strCell = "";
			break;
		default:
			strCell = "";
			break;
		}
		if (strCell.equals("") || strCell == null) {
			return "";
		}
		if (cell == null) {
			return "";
		}
		return strCell;
	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(HSSFCell cell) {
		String cellvalue = null;
		if (cell != null) {
			// 判断当前Cell的Type
			switch (cell.getCellType()) {
			// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_NUMERIC:
			case HSSFCell.CELL_TYPE_FORMULA: {
				// 判断当前的cell是否为Date
				if (HSSFDateUtil.isCellDateFormatted(cell)) {
					// 如果是Date类型则，转化为Data格式

					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();

					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				}
				// 如果是纯数字
				else {
					// 取得当前Cell的数值
					cellvalue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			}
			// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = null;
			}
		} else {
			cellvalue = null;
		}
		return cellvalue;

	}

	// 下载文件
	@RequestMapping(value = "download")
	public void download(String path, HttpServletResponse response,
			HttpServletRequest request) {
		try {
			// path是指欲下载的文件的路径。

			path = request
					.getSession()
					.getServletContext()
					.getRealPath(
							File.separator + "WEB-INF" + File.separator
									+ "static" + File.separator + "templete"
									+ File.separator + "caixuexiang.xls");
			File file = new File(path);
			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			String ext = filename.substring(filename.lastIndexOf(".") + 1)
					.toUpperCase();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String("采血箱导入模板.xls".getBytes("utf-8"), "ISO8859-1"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		// return response;
	}

}
