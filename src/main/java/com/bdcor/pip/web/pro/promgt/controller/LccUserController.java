package com.bdcor.pip.web.pro.promgt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.PinyingUtils;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.pro.promgt.dao.LccUserDao;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.domain.LccUser;
import com.bdcor.pip.web.pro.promgt.domain.Project;
import com.bdcor.pip.web.pro.promgt.filter.LccUserFilter;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.pro.promgt.service.LccUserService;
import com.bdcor.pip.web.pro.promgt.service.ProjectMgtService;
import com.bdcor.pip.web.sys.rbac.domain.Organization;
import com.bdcor.pip.web.sys.rbac.service.OrganizationService;
import com.bdcor.pip.web.sys.rbac.service.RoleService;
import com.bdcor.pip.web.sys.rbac.service.UserService;

@Controller
@RequestMapping(value = "pro/lccuser")
public class LccUserController {

	@Autowired
	private LccUserService lccUserService;
	@Autowired
	private ProjectMgtService projectMgtService;
	@Autowired
	private LccService lccService;
	@Autowired
	private UserService userService;

	@Autowired
	private OrganizationService orgService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private LccUserDao lccUserDao;

	// 1:NCC 2:RCC 3:LCC
	@RequestMapping
	public String init(Model model) {
		// model.addAttribute("lccDictList", lccService.getAllLccs(new
		// LccFilter()));
		model.addAttribute("lccDictList", lccService.getDataLimitLcc("3"));
		return "pro/lccuser/list";
	}

	@RequestMapping(value = "ncc")
	public String nccInit(Model model) {
		model.addAttribute("lccDictList", lccService.getDataLimitLcc("1"));
		return "pro/lccuser/ncclist";
	}

	@RequestMapping(value = "rcc")
	public String rccInit(Model model) {
		model.addAttribute("lccDictList", lccService.getDataLimitLcc("2"));
		return "pro/lccuser/rcclist";
	}

	@RequestMapping(value = "getAllActiveLcc", produces = "application/json")
	@ResponseBody
	public List<Lcc> getAllActiveLcc(Model model) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<Lcc> data = lccService.getAllActiveLcc();
			return data;
		} catch (Exception e) {
			return null;
		}

	}
	/**
	 * 
	 * description:  根据登陆用户名的权限来获取单位
	 * @author yangfeng  
	 * @param model
	 * @return   
	 * @update 2016年3月14日
	 */
	@RequestMapping(value = "getAllActiveLccByAuthority", produces = "application/json")
	@ResponseBody
	public List<Lcc> getAllActiveLccByAuthority(Model model) {
		Map<String, Object> res = new HashMap<String, Object>();
		try {
			List<Lcc> data = lccService.getAllActiveLccByAuthority();
			return data;
		} catch (Exception e) {
			return null;
		}

	}

	@RequestMapping(value = "lcclist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<LccUser> getlccProjects(LccUserFilter filter) {
		filter.setLccRoleType("3");
		List<LccUser> pList = lccUserService.getAllLccUsers(filter);
		JqgridResponse<LccUser> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(pList);
		return response;
	}

	@RequestMapping(value = "rcclist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<LccUser> getrccProjects(LccUserFilter filter) {
		filter.setLccRoleType("2");
		List<LccUser> pList = lccUserService.getAllLccUsers(filter);
		JqgridResponse<LccUser> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(pList);
		return response;
	}

	@RequestMapping(value = "ncclist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<LccUser> getnccProjects(LccUserFilter filter) {
		filter.setLccRoleType("1");
		List<LccUser> pList = lccUserService.getAllLccUsers(filter);
		JqgridResponse<LccUser> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(pList);
		return response;
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<LccUser> getAllProjects(LccUserFilter filter) {
		List<LccUser> pList = lccUserService.getAllLccUsers(filter);
		JqgridResponse<LccUser> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(pList);
		return response;
	}

	// @RequestMapping(value = "list", method = RequestMethod.POST, produces =
	// MediaType.APPLICATION_JSON_VALUE)
	// public @ResponseBody JqgridResponse<LccUserFilter>
	// getAllProjects(LccUserFilter filter){
	// List<LccUserFilter> pList = lccUserService.getAllLccUsers(filter);
	// JqgridResponse<LccUserFilter> response =
	// JqgridResponseContext.getJqgridResponse();
	// response.setRows(pList);
	// return response;
	// }
	@RequestMapping(value = "openmodallccuserinput", method = RequestMethod.GET)
	public String lccInput(
			Model model,
			@RequestParam(value = "userCode", required = false) String userCode,
			@RequestParam(value = "lccRoleType", required = false) String lccRoleType) {
		Project pro = projectMgtService.getProjectById(Securitys.getUser()
				.getCurrent_projectId());
		LccUser lccUser = new LccUser();
		if (!StringUtils.isEmpty(userCode)) {
			lccUser = lccUserService.getLccUserById(userCode, lccRoleType);
			model.addAttribute("isEdit", true);
		}
		if (lccUser == null) {
			lccUser = new LccUser();
		}
		lccUser.setProjectName(pro == null ? "" : pro.getProjectName());
		model.addAttribute("lccUser", lccUser);
		// model.addAttribute("lccDictList", lccService.getAllLccs(new
		// LccFilter()));
		model.addAttribute("lccDictList",
				lccService.getDataLimitLcc(lccRoleType));
		model.addAttribute("lccRoleType", lccRoleType);
		return "pro/lccuser/form";
	}
	@RequestMapping(value="getLccByType")
	@ResponseBody
	public List<Lcc> getLccByType(@RequestParam("lccRoleType")String lccRoleType){
		return lccService.getDataLimitLcc(lccRoleType);
	}
	
	/***
	 * 初始化编辑页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws UnsupportedEncodingException
	 */

	@RequestMapping(value = "openmodaluserinputfromlccuser", method = RequestMethod.GET)
	public ModelAndView openmodaluserinputfromlccuser(
			@RequestParam(value = "lccCode") String lccCode,
			@RequestParam(value = "userCode") String userCode,
			@RequestParam(value = "lccName") String lccName,
			@RequestParam(value = "name") String name,
			HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException {
		ModelAndView mav = new ModelAndView("pro/lccuser/formUser");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		List<Organization> list = orgService.list(new HashMap());

		lccName = new String(lccName.getBytes("iso8859-1"), "utf-8");
		lccName = java.net.URLDecoder.decode(lccName, "UTF-8");

		mav.addObject("organizations", list);
		mav.addObject("hiddenLccName", lccName);
		mav.addObject("hiddenLccCode", lccCode);
		mav.addObject("userCode", userCode);
		name = new String(name.getBytes("iso8859-1"), "utf-8");
		name = java.net.URLDecoder.decode(name, "UTF-8");
		mav.addObject("hiddenname", name);

		String pyName = PinyingUtils.hanziToPinyin(name);
		if (pyName == null)
			return null;
		pyName = pyName.replaceAll(" ", "");
		pyName = pyName.trim();

		pyName = userService.getListForLoginName(pyName);

		// User user2 = new User();
		// user2.setLccCode(lccCode);
		// user2.setName(name);
		// user2.setLoginName(pyName );
		// mav.addObject("user2", user2);
		mav.addObject("roles",
				roleService.getAllByOrganId(Securitys.getOrganId()));
		mav.addObject("pyName", pyName);
		// mav.addObject("lccUserList",
		// lccUserService.getLccUsersByProjectId());
		mav.addObject("projectId", Securitys.getUser().getCurrent_projectId());

		return mav;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "addLccUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> addLccUser(LccUser lccUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (StringUtils.isEmpty(lccUser.getUserCode())) {
				lccUser.setStatus("2");
				String lccSequence = getLccSequence(lccUser.getLccCode());
				// 单位Code+四位流水
				lccUser.setUserCode(lccUser.getLccCode() + lccSequence);
				lccUserService.addLccUser(lccUser);
				result.put("userCode", lccUser.getUserCode());
			} else {
				lccUserService.updateLccUser(lccUser);
			}
			result.put("success", true);

		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "changeStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> changeStatus(LccUser lccUser) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Lcc lcc = lccService.getLcc(lccUser.getLccCode());

			// 查询一次lccUser
			LccUser lccUser2 = new LccUser();
			List<LccUser> list = lccUserService
					.getOneForLccCodeAndProjectIdAndUserCode(
							lccUser.getLccCode(), lccUser.getProjectId(),
							lccUser.getUserCode());
			if (list != null && list.size() > 0) {
				lccUser2 = list.get(0);
			}
			// if(lcc!=null&&lcc.getStatus()==2&&"2".equals(lccUser.getStatus())){
			if (lcc == null || lcc.getStatus() == 2) {
				result.put("isLccActive", false);
				return new ResponseEntity(result, HttpStatus.OK);
			} else if (lccUser2.getIsSignSigna() == null
					|| "".equals(lccUser2.getIsSignSigna())
					|| "2".equals(lccUser2.getIsSignSigna())) {
				result.put("msg", "没有签署电子签名表");
				return new ResponseEntity(result, HttpStatus.OK);
			} else if (lccUser2.getIsGetResume() == null
					|| "".equals(lccUser2.getIsGetResume())
					|| "2".equals(lccUser2.getIsGetResume())) {
				result.put("msg", "未收到简历");
				return new ResponseEntity(result, HttpStatus.OK);
			} else if (lccUser2.getIsJoinTraining() == null
					|| "".equals(lccUser2.getIsJoinTraining())
					|| "2".equals(lccUser2.getIsJoinTraining())) {
				result.put("msg", "未参加培训");
				return new ResponseEntity(result, HttpStatus.OK);
			} else {
				// 如果是用户激活 就要激活地下所有的账户
				lccUserService.changeStatus(lccUser);
				result.put("success", true);
				result.put("isLccActive", true);
			}
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "getLccStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> getLccStatus(String lccCode) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Lcc lcc = lccService.getLcc(lccCode);
			if (lcc.getStatus() == 1) {
				result.put("success", true);
			} else {
				result.put("success", false);
			}
		} catch (Exception e) {
			result.put("success", false);
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	private String getLccSequence(String lccCode) {
		String lccSequence = "0001";
		String maxLccCode = lccUserDao.getMaxLccCode(Securitys.getUser()
				.getCurrent_projectId(), lccCode);
		if (StringUtils.isEmpty(maxLccCode)) {
			return lccSequence;
		} else {
			String lccSeq = maxLccCode.substring(maxLccCode.length() - 4);
			return formatKey(4, Long.parseLong(lccSeq) + 1);
		}

	}

	private String formatKey(int length, Long keyValue) {
		String pattern = "";
		for (int i = 0; i < length; i++) {
			pattern += "0";
		}
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(keyValue);
	}

	@RequestMapping(value = "outExcelLccUser")
	public void outExcelLccUser(HttpServletRequest request,
			HttpServletResponse response, LccUserFilter filter)
			throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		OutputStream ouputStream = null;

		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ File.separator
				+ "WEB-INF"
				+ File.separator
				+ "static"
				+ File.separator + "templete" + File.separator;

		// String template = ctxPath + "LccUser导出.xls";
		// String dfName = System.currentTimeMillis()+"LccUser导出.xls";
		String dfName = "";
		if ("1".equals(filter.getLccRoleType())) {
			dfName = "NCC研究人员.xls";

		}
		if ("2".equals(filter.getLccRoleType())) {
			dfName = "RCC研究人员.xls";

		}
		if ("3".equals(filter.getLccRoleType())) {
			dfName = "LCC研究人员.xls";

		}
		// String datafile = ctxPath + dfName;
		try {
			// response.setContentType("application/x-msdownload;");
			filter.setRows(10000);
			List<LccUser> list = lccUserService.getAllLccUsers(filter);

			HSSFWorkbook wb = exportToExl(list);
			response.setContentType("application/vnd.ms-excel");
			String name = new String(dfName.getBytes("gb2312"), "ISO8859-1");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ name);
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ouputStream != null)
					ouputStream.close();
			} catch (Exception ex) {

			}
			try {
				if (bos != null)
					bos.close();
			} catch (Exception ex) {

			}
			try {
				if (bis != null)
					bis.close();
			} catch (Exception ex) {

			}
		}

	}

	public HSSFWorkbook exportToExl(List<LccUser> list) {

		String[] excelHeader = { "用户姓名", "所属单位", "联系电话", "手机", "Email",
				"电子签名表", "简历", "培训", "激活", "角色", "参严" };
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("lccUser");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
		}
		if (list == null) {
			return wb;
		}
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 1);
			LccUser lccUser = list.get(i);
			row.createCell(0).setCellValue(lccUser.getName());
			sheet.setColumnWidth(0, 20 * 256);
			row.createCell(1).setCellValue(lccUser.getLccName());
			sheet.setColumnWidth(1, 20 * 256);
			row.createCell(2).setCellValue(lccUser.getPhone());
			sheet.setColumnWidth(2, 20 * 256);
			row.createCell(3).setCellValue(lccUser.getMobile());
			sheet.setColumnWidth(3, 20 * 256);
			row.createCell(4).setCellValue(lccUser.getEmail());
			sheet.setColumnWidth(4, 20 * 256);
			if (lccUser.getIsSignSigna() != null) {
				row.createCell(5).setCellValue(
						"1".equals(lccUser.getIsSignSigna()) ? "是" : "否");
			} else {
				row.createCell(5).setCellValue("");
			}
			sheet.setColumnWidth(5, 10 * 256);
			if (lccUser.getIsGetResume() != null) {
				row.createCell(6).setCellValue(
						"1".equals(lccUser.getIsGetResume()) ? "是" : "否");
			} else {
				row.createCell(6).setCellValue("");
			}
			sheet.setColumnWidth(6, 10 * 256);
			if (lccUser.getIsJoinTraining() != null) {
				row.createCell(7).setCellValue(
						"1".equals(lccUser.getIsJoinTraining()) ? "是" : "否");
			} else {
				row.createCell(7).setCellValue("");
			}
			sheet.setColumnWidth(7, 10 * 256);
			if (lccUser.getStatus() != null) {
				row.createCell(8).setCellValue(
						"1".equals(lccUser.getStatus()) ? "是" : "否");
			} else {
				row.createCell(8).setCellValue("");
			}
			sheet.setColumnWidth(8, 10 * 256);
			row.createCell(9).setCellValue(lccUser.getLccRole());
			sheet.setColumnWidth(9, 20 * 256);
			if (lccUser.getResearchStatus() != null) {
				row.createCell(10).setCellValue(
						"1".equals(lccUser.getResearchStatus()) ? "参严" : "离严");
			} else {
				row.createCell(10).setCellValue("");
			}
			sheet.setColumnWidth(10, 10 * 256);
		}
		return wb;

	}

}
