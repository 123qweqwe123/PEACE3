package com.bdcor.pip.web.pro.promgt.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
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

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.data.dao.PipCommPatientHistoryMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.PipCommPatientDate;
import com.bdcor.pip.web.data.domain.PipCommPatientHistory;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.data.service.PatientService;
import com.bdcor.pip.web.data.service.PipCommPatientDateService;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.ExcelDataTransToObjWithPOI;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.dataChecking.ExcelDataChecking;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.exception.ExcelDataException;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.obj.ExcelDataObj;
import com.bdcor.pip.web.pro.promgt.service.patientExcelTemplate.GenerateExcelTemplate;
import com.bdcor.pip.web.pro.promgt.service.patientExcelTemplate.GenerateExcelTemplateOfIdCord;
import com.bdcor.pip.web.pro.promgt.service.patientExcelTemplate.GenerateExcelTemplateOfOther;
import com.bdcor.pip.web.pro.promgt.service.patientExcelTemplate.obj.AreaObj;

@Controller
@RequestMapping("pro/pat")
public class PatientController {
	@Autowired
	PipCommPatientHistoryMapper pipCommPatientHistoryMapper;
	@Autowired
	private LccService lccService;
	@Autowired
	PatientService patientService;
	@Autowired
	private PipCommPatientDateService patientDateService;
	public static ThreadLocal<Object> errorWorkBook = new ThreadLocal<Object>();

	public static Map<String, Object> errorMap = new HashMap<String, Object>();

	@RequestMapping("checkAppointmentDate")
	@ResponseBody
	public ResponseEntity<?> checkAppointmentDate(
			@RequestParam("patientId") String patientId) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PipCommPatientDate patientDate = patientDateService
					.selectPatientDate(patientId);
			if (null == patientDate) {
				result.put("success", false);
				result.put("msg", "该患者没有做过首次随访！");
			} else {
				Date firstDate = patientDate.getFirstDate();
				if (null == firstDate) {
					result.put("success", false);
					result.put("msg", "该患者没有做过首次随访！");
				}
				Date sixRealDate = patientDate.getSixRealDate();
				if (null != sixRealDate) {
					result.put("success", false);
					result.put("msg", "该患者已经完成了6月份的随访，不用再预约随访时间！");
				}
				if (firstDate != null && null == sixRealDate) {
					result.put("success", true);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			result.put("success", false);
			result.put("msg", "系统异常");
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@RequestMapping
	public String init(Model model) {
		// model.addAttribute("lccDictList", lccService.getAllLccs(new
		// LccFilter()));
		return "pro/patient/list";
	}

	@RequestMapping("appointmentlist")
	public String appointmentlist(Model model) {
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			model.addAttribute("lccDictList", lccService.getDataLimitLcc());
		} else {
			model.addAttribute("lccDictList",
					lccService.getDataLimitLccForLccCode());

		}
		return "pro/patient/appointmentlist";
	}
	@RequestMapping("updateAppointmentDateFirst")
	@ResponseBody
	public ResponseEntity<?> updateAppointmentDateFirst(PipCommPatientDate patient) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			patientDateService.updateAppointmentDateFirst(patient);
			result.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", false);
			result.put("msg", "系统异常");
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	@RequestMapping("updateAppointmentDate")
	@ResponseBody
	public ResponseEntity<?> updateAppointmentDate(PipCommPatientDate patient) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			patientDateService.updateByPatientId(patient);
			result.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", false);
			result.put("msg", "系统异常");
		}
		return new ResponseEntity(result, HttpStatus.OK);
	}
	@RequestMapping("openmodalAppointmentFirst")
	public ModelAndView openmodalAppointmentFirst(
			@RequestParam("patientId") String patientId) {
		ModelAndView modelAndView = new ModelAndView(
				"pro/patient/appointmentDateFormFirst");
		PipCommPatientDate patientDate = patientDateService
				.selectPatientDate(patientId);
		modelAndView.addObject("patientId", patientId);
		// 查的是pip_comm_patient_date 这张表的数据
		modelAndView.addObject("patientDate", patientDate);
		return modelAndView;
	}

	@RequestMapping("openmodalAppointment")
	public ModelAndView openmodalAppointment(
			@RequestParam("patientId") String patientId) {
		ModelAndView modelAndView = new ModelAndView(
				"pro/patient/appointmentDateForm");
		PipCommPatient pipCommPatient = patientService.selectByKey(patientId);
		PipCommPatientDate patientDate = patientDateService
				.selectPatientDate(patientId);
		// 查的是pip_comm_patient_date 这张表的数据
		modelAndView.addObject("patientDate", patientDate);

		// 查的是pip_comm_patient 这张表的数据
		modelAndView.addObject("patient", pipCommPatient);
		return modelAndView;
	}

	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String download(Model model) {
		// model.addAttribute("lccDictList", lccService.getAllLccs(new
		// LccFilter()));
		return "pro/patient/download";
	}

	@RequestMapping(value = "openmodal/toupload", method = RequestMethod.GET)
	public String toupload(Model model) {
		// model.addAttribute("lccDictList", lccService.getAllLccs(new
		// LccFilter()));
		return "pro/patient/upload";
	}

	@RequestMapping("/downloadHistory")
	public ModelAndView downloadHistory(HttpServletRequest request,
			HttpServletResponse response) {

		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		try {

			String id = request.getParameter("id");
			PipCommPatientHistory ph = this.pipCommPatientHistoryMapper
					.selectByPrimaryKey(Long.parseLong(id));

			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(ph.getFileName().getBytes("utf-8"),
							"ISO8859-1"));

			bis = new BufferedInputStream(new FileInputStream(ph.getFilePath()));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

	@RequestMapping("/downloadError")
	public ModelAndView downloadError(HttpServletRequest request,
			HttpServletResponse response) {

		java.io.BufferedOutputStream bos = null;
		FileInputStream fileInputStream = null;
		try {

			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("CheckResult.xls".getBytes("utf-8"),
							"ISO8859-1"));
			// Workbook wb = (Workbook) errorWorkBook.get();

			String ctxPath = CustomizedPropertyPlaceholderConfigurer
					.getContextProperty("project_document_upload_dir");
			String fileName = request.getParameter("fn");
			String logid = request.getParameter("logid");

			PipCommPatientHistory ph = this.pipCommPatientHistoryMapper
					.selectByPrimaryKey(Long.parseLong(logid));

			fileInputStream = new FileInputStream(new File(ph.getFilePath(),
					fileName));
			bos = new BufferedOutputStream(response.getOutputStream());

			IOUtils.copy(fileInputStream, bos);

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

	@RequestMapping("/downloadTemplate")
	public ModelAndView downloadTemplate(HttpServletRequest request,
			HttpServletResponse response) {

		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		String templateName = request.getParameter("templateName");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String district = request.getParameter("district");

		String provinceValue = request.getParameter("provinceValue");
		String cityValue = request.getParameter("cityValue");
		String districtValue = request.getParameter("districtValue");

		String townId = request.getParameter("town");
		String townValue = request.getParameter("townValue");
		String viliId = request.getParameter("vili");
		String viliValue = request.getParameter("viliValue");

		if (StringUtils.isBlank(templateName)) {
			templateName = "patientTemplate.xls";
		}
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(templateName.getBytes("utf-8"), "ISO8859-1"));

			String ctxPath = request.getSession().getServletContext()
					.getRealPath("/")
					+ File.separator
					+ "WEB-INF"
					+ File.separator
					+ "views"
					+ File.separator
					+ "pro"
					+ File.separator
					+ "patient"
					+ File.separator;
			String datafile = ctxPath + templateName;

			/*
			 * Workbook wb = this.patientService.getExcelData(new
			 * File(datafile));
			 * 
			 * 
			 * 
			 * 
			 * wb.getSheetAt(1).getRow(0).getCell(0).setCellValue(province);
			 * wb.getSheetAt(1).getRow(0).getCell(1).setCellValue(city);
			 * wb.getSheetAt(1).getRow(0).getCell(2).setCellValue(district);
			 * 
			 * //bis = new BufferedInputStream(new FileInputStream(datafile));
			 * bos = new BufferedOutputStream(response.getOutputStream());
			 * wb.write(bos);
			 */
			/*
			 * byte[] buff = new byte[2048]; int bytesRead; while (-1 !=
			 * (bytesRead = bis.read(buff, 0, buff.length))) { bos.write(buff,
			 * 0, bytesRead); }
			 */
			AreaObj area = new AreaObj();
			area.setProvinceId(province);
			area.setProvinceName(provinceValue);
			area.setCityId(city);
			area.setCityName(cityValue);
			area.setDistrictId(district);
			area.setDistrictName(districtValue);
			area.setTownId(townId);
			area.setTownName(townValue);
			area.setVillageId(viliId);
			area.setVillageName(viliValue);

			GenerateExcelTemplate template = null;
			if ("idcard.xls".equals(templateName)) {
				template = (GenerateExcelTemplate) new GenerateExcelTemplateOfIdCord(
						new File(datafile), area);

			} else {
				template = (GenerateExcelTemplate) new GenerateExcelTemplateOfOther(
						new File(datafile), area);

			}

			template.outTemplet(response.getOutputStream());

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (bis != null)
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (bos != null)
				try {
					bos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {

		Map<String, Object> result = new HashMap<String, Object>();
		long fileSize = file.getSize();
		if (fileSize > 10240000) {
			result.put("msg", "文件大小不能超过10M!");
			result.put("success", false);
			return new ResponseEntity(result, HttpStatus.OK);
		}
		/*
		 * PipCommPatientHistoryExample example = new
		 * PipCommPatientHistoryExample();
		 * example.createCriteria().andFileNameEqualTo
		 * (file.getOriginalFilename()); List ls
		 * =this.pipCommPatientHistoryMapper.selectByExample(example); if ( ls
		 * != null && ls.size() > 0 ){ result.put("msg", "上传文件名重复");
		 * result.put("success", false); return new ResponseEntity(result,
		 * HttpStatus.OK); }
		 */
		String projectId = Securitys.getUser().getCurrent_projectId();
		// String ctxPath =
		// request.getSession().getServletContext().getRealPath("") +
		// "/uploadfiles/";
		// 上传文件保存的根目录。
		String ctxPath = CustomizedPropertyPlaceholderConfigurer
				.getContextProperty("project_document_upload_dir");

		File dirPath = new File(ctxPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
		String fileName = System.currentTimeMillis() + "_"
				+ file.getOriginalFilename();// linux 服务器上是gbk的编码
		File uploadFile = new File(ctxPath + fileName);

		try {
			if (!uploadFile.exists()) {
				uploadFile.createNewFile();
			}
			file.transferTo(uploadFile);

			// String templatePath =
			// request.getSession().getServletContext().getRealPath("/")
			// + File.separator + "WEB-INF"
			// + File.separator + "views"
			// + File.separator + "pro"
			// + File.separator + "patient" + File.separator;

			// excel数据读取
			ExcelDataTransToObjWithPOI transftoobj = new ExcelDataTransToObjWithPOI(
					uploadFile);
			ExcelDataObj dataObj = transftoobj.getExcelData();

			if (dataObj.getObjs().size() == 0) {
				throw new ExcelDataException("excel模板没有填写数据");
			} else if (dataObj.getObjs().size() > 20000) {
				throw new ExcelDataException("数据表格需要限制在20000行以内");
			}

			String lccCode = dataObj.getObjs().get(0).getLccCode();
			if (StringUtils.isBlank(lccCode)) {
				lccCode = "noLcc";
			}

			String objectivePath = ctxPath
					+ DateUtil.getDefindedFormatCurrentDate("yyyy-MM-dd")
					+ File.separator + lccCode + File.separator;
			//
			// if (!objectiveDir.exists()) {
			// objectiveDir.mkdirs();
			// }

			File objectiveDir = new File(objectivePath);
			FileUtils.moveFileToDirectory(uploadFile, objectiveDir, true);

			uploadFile = new File(objectivePath + fileName);

			// excel数据验证
			ExcelDataChecking checking = new ExcelDataChecking(uploadFile,
					dataObj);
			checking.dataChecking();

			// 输出验证文件
			String outfileName = "checked_" + fileName;
			File outFile = new File(objectivePath, outfileName);
			if (!outFile.exists()) {
				outFile.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(outFile);
			checking.writeCheckingExcel(out);
			out.close();

			Long newId = System.currentTimeMillis();
			short isCheckSuc = 0;
			String outCheckTextFileName = outfileName + ".txt";
			// 结果处理
			if (checking.hasError()) {
				result.put("msg", checking.getMsg());
				result.put("fileName", outfileName);
				result.put("logId", newId + "");
				result.put("success", false);

				// 验证结果保存到txt文件中
				File resoutFile = new File(objectivePath, outCheckTextFileName);
				FileUtils.writeStringToFile(resoutFile, checking.getMsg()
						.replace("<br>", ","));
			} else {
				isCheckSuc = 1;
				// 数据入库
				result.put("success", true);
			}

			PipCommPatientHistory his = new PipCommPatientHistory();
			his.setId(newId);
			his.setCreateDate(new Date());
			his.setCreateBy(Securitys.getUser().getLoginName());
			his.setCreaterName(Securitys.getUser().getName());
			his.setFileName(uploadFile.getName());
			his.setInsertToDbSuc((short) 0);
			his.setCheckedSuc(isCheckSuc);
			his.setFilePath(uploadFile.getParent());
			his.setLccCode(Securitys.getUser().getLccCode());
			his.setProjectId(Securitys.getUser().getCurrent_projectId());

			if (checking.hasError()) {
				his.setFileNameChecked(outfileName);
				his.setFileNameOut(outCheckTextFileName);
				his.setRowNum(0);
			} else {
				// his.setRowNum(dataObj.getObjs().size());
				his.setRowNum(0);
			}

			this.pipCommPatientHistoryMapper.insert(his);

		} catch (Exception e) {

			if (e instanceof ExcelDataException) {
				result.put("msg", e.getMessage());
			} else {
				e.printStackTrace();
				result.put("msg", "上传失败，请联系管理员！");
			}

			result.put("success", false);
		} finally {

		}

		return new ResponseEntity(result, HttpStatus.OK);
	}

	/*
	 * @SuppressWarnings({ "unchecked", "rawtypes" })
	 * 
	 * @RequestMapping(value = "upload", method = RequestMethod.POST) public
	 * ResponseEntity<?> upload(@RequestParam("file") MultipartFile file,
	 * HttpServletRequest request) {
	 * 
	 * Map<String, Object> result = new HashMap<String, Object>(); long fileSize
	 * = file.getSize(); if(fileSize > 10240000){ result.put("msg",
	 * "文件大小不能超过10M!"); result.put("success", false); return new
	 * ResponseEntity(result, HttpStatus.OK); } PipCommPatientHistoryExample
	 * example = new PipCommPatientHistoryExample();
	 * example.createCriteria().andFileNameEqualTo(file.getOriginalFilename());
	 * List ls =this.pipCommPatientHistoryMapper.selectByExample(example); if (
	 * ls != null && ls.size() > 0 ){ result.put("msg", "上传文件名重复");
	 * result.put("success", false); return new ResponseEntity(result,
	 * HttpStatus.OK); } String projectId =
	 * Securitys.getUser().getCurrent_projectId(); //String ctxPath =
	 * request.getSession().getServletContext().getRealPath("") +
	 * "/uploadfiles/"; String ctxPath =
	 * CustomizedPropertyPlaceholderConfigurer.
	 * getContextProperty("project_document_upload_dir");
	 * 
	 * File dirPath = new File(ctxPath); if (!dirPath.exists()) {
	 * dirPath.mkdirs(); } String fileName =file.getOriginalFilename();//linux
	 * 服务器上是gbk的编码 File filePath = new File(ctxPath + fileName);
	 * 
	 * try { if(!filePath.exists()){ filePath.createNewFile(); }
	 * file.transferTo(filePath);
	 * 
	 * String templatePath =
	 * request.getSession().getServletContext().getRealPath("/") +
	 * File.separator + "WEB-INF" + File.separator + "views" + File.separator +
	 * "pro" + File.separator + "patient" + File.separator; Map<String,Object>
	 * map = this.patientService.checkData(filePath, new File(templatePath +
	 * "patTemplate.xls"), new File(templatePath + "pat.xml")); List<String> msg
	 * = (List<String>) map.get("msg"); if ( msg.size() > 0 ){
	 * result.put("success", false); //errorWorkBook.set(map.get("book"));
	 * errorMap.put(fileName, map.get("book")); StringBuffer sb = new
	 * StringBuffer(); int ii = 1 ; for ( String m : msg ){ sb.append(m);
	 * sb.append("<br>"); ii ++; if ( ii > 20 ){ break; } }
	 * 
	 * if ( msg.size() >= 1 && msg.get(0).indexOf("第") == 0 ){
	 * 
	 * // if ( msg.size() >= 100 ){ // sb.append(
	 * "<span style='color:red;'>注：错误数据过多，请点击“下载校验结果”查看备注列，无法通过校验的数据用红色背景标记。为了提高效率超过100条的错误提示不再标记到“下载校验结果”中，</span>"
	 * ); // }else{ sb.append("<span style='color:red;'>注：共有"+msg.size()+
	 * "条数据无法通过数据校验，请点击“下载校验结果”查看备注列，无法通过校验的数据用红色背景标记。</span>"); // }
	 * result.put("num", msg.size()); result.put("msg", sb.toString()); }else{
	 * result.put("msg", sb.toString().replaceAll("<br>", "")); }
	 * 
	 * }else{ result.put("success", true); } } catch (Exception e) {
	 * e.printStackTrace(); if(filePath.isFile()&&filePath.exists()){
	 * filePath.delete(); }
	 * 
	 * result.put("msg", "上传失败，请联系管理员！"); result.put("success", false); } return
	 * new ResponseEntity(result, HttpStatus.OK); }
	 */

	@RequestMapping(value = "historylist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipCommPatientHistory> historylist(
			PatientFilter filter) {
		filter.setLccCode(Securitys.getUser().getLccCode());
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setInsertToDbSuc("1");
		List<PipCommPatientHistory> list = this.pipCommPatientHistoryMapper
				.selectForPager(filter);
		JqgridResponse<PipCommPatientHistory> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}
}
