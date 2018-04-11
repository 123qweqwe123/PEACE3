package com.bdcor.pip.web.reserve.controller;

import com.alibaba.fastjson.JSON;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.constant.ExcelDataConstant;
import com.bdcor.pip.data.util.ExcelExportBatchUtil;
import com.bdcor.pip.web.reserve.dao.PipCommReserveMapper;
import com.bdcor.pip.web.reserve.domain.PipCommReserveDetail;
import com.bdcor.pip.web.reserve.filter.PipCommReserveDetailFilter;
import com.bdcor.pip.web.reserve.filter.PipCommReserveFilter;
import com.bdcor.pip.web.reserve.service.PipCommReserveDetailService;
import com.bdcor.pip.web.reserve.service.PipCommReserveService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("reserve")
public class ReserveController {

    private static final Logger log = LoggerFactory.getLogger(ReserveController.class);

	@Autowired
	PipCommReserveService pipCommReserveService;

	@Autowired
	PipCommReserveDetailService pipCommReserveDetailService;

	@Autowired
	private PipCommReserveMapper reserveDao;
    /**
     * @param model
     * @param ismsg 0 非短信项目   1 短信项目的人
     * @return
     */
	@RequestMapping(value = "datelist")
	public String init(Model model , String ismsg) {
        Calendar cal = Calendar.getInstance();
        model.addAttribute("day", cal.get(Calendar.DAY_OF_MONTH));
        model.addAttribute("month", cal.get(Calendar.MONTH)+1);
        model.addAttribute("year", cal.get(Calendar.YEAR));
        model.addAttribute("maxDayOfMonth", cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        if(StringUtils.isBlank(ismsg) ){
            model.addAttribute("ismsg","1"); // 参与短信项目
        }else{
            model.addAttribute("ismsg",ismsg);
        }
        String value = JSON.toJSONString(pipCommReserveService.getGroupData(cal.get(Calendar.YEAR),(cal.get(Calendar.MONTH)+1),ismsg));
        model.addAttribute("datas", value.toLowerCase());
        log.info(value.toLowerCase());
		return "reserve/datelist";
	}

    @RequestMapping("getdateList")
    @ResponseBody
    public String getDateList( String date , String ismsg){
        Calendar cal = Calendar.getInstance();
        String[] dateArr = {cal.get(Calendar.YEAR)+"",(cal.get(Calendar.MONTH)+1)+""};
        if(StringUtils.isNotBlank(date)){
            dateArr = date.split("-");
        }
        List<Map<String,String>> list  = pipCommReserveService.getGroupData(dateArr[0],dateArr[1],ismsg);
        String returnVal = JSON.toJSONString(list).toLowerCase();
        log.info("日历选择月份:"+date+",查询数据输出："+returnVal);
        return returnVal;
    }


	@RequestMapping(value = "reservelist")
	public String resverlist(
//			@RequestParam(value = "rq") String rq,
//			@RequestParam(value = "status") String status,  // 0 当天预约   1  当天红色  2 当天黄色
            @RequestParam(value = "ismsg") String ismsg,
            Model model) {


//		model.addAttribute("rq", rq);
//		model.addAttribute("status", status);
        model.addAttribute("ismsg", ismsg);
		return "reserve/reservelist";

	}

    /**
     * @param filter
     * @return
     */
    //method = RequestMethod.POST,
        @RequestMapping(value = "findList",  produces = MediaType.APPLICATION_JSON_VALUE)
        public @ResponseBody
        JqgridResponse findlist(PipCommReserveFilter filter) {
//            filter.setUserid(Securitys.getUserId());
//            String[] rqArr = filter.getRq().split("-");
//            String[] strArr = pipCommReserveService.getDateArr(rqArr[0],rqArr[1]);
//            filter.setStartdate(strArr[0]);
//            filter.setEnddate(strArr[1]);
//            filter.setRq(filter.getRq().replace("-",""));
//            List<Map<String,String>> list = pipCommReserveService.getDataInfo(filter);
			List<Map<String,Object>> list = reserveDao.getPlanViewInfo(filter);
            JqgridResponse<Map<String,Object>> response = JqgridResponseContext
                    .getJqgridResponse();
            response.setRows(list);
            return response;
        }


	@RequestMapping(value = "/expoertExcel")
	public ModelAndView watchExport(PipCommReserveFilter filter, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		java.io.BufferedOutputStream bos = null;
		try {
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition", "attachment; filename="
					+ new String("患者预约信息.xls".getBytes("utf-8"), "ISO8859-1"));
			filter.setRows(66537);
			List<Map<String, Object>> list = reserveDao.getPlanViewInfo(filter);
			bos = new BufferedOutputStream(response.getOutputStream());
			SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
			if("1".equals(filter.getIsmsg())) {
				ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_CHAT_PLANDATE_INFO, ExcelDataConstant.DATA_CHAT_PLANDATE_INFO, workbook);
			} else {
				ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEAD_PLANDATE_INFO, ExcelDataConstant.DATA_PLANDATE_INFO, workbook);
			}
			workbook.write(bos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null)
				bos.close();
		}
		return null;
	}

	/**
	 * 
	 * @param lccCode
	 *            医院编码
	 * @param patientId
	 *            病人id
	 * @return
	 */
	@RequestMapping(value = "openmodalAddReserveInputorUpdate", method = RequestMethod.GET)
	public ModelAndView openmodalAddReserveInputorUpdate(
			@RequestParam(value = "lccCode") String lccCode, Model model,
			@RequestParam(value = "patientId") String patientId) {
		ModelAndView mav = new ModelAndView("reserve/dateform");
		return mav;
	}

    @RequestMapping("updateviewdate")
    @ResponseBody
    public ResponseEntity updateViewDate(String pid, String newdate ){
        Map<String, Object> res = new HashMap<String, Object>();

        int i = pipCommReserveService.updateViewDate(pid,newdate);
        if( i == 0 ){
            res.put("success", false);
            res.put("message", "更新失败");
        }else{
            res.put("success", true);
            res.put("message", "更新成功");
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }


	/**
	 * 保存医生修改病人的来院检查记录
	 * 
	 *            医院编码
	 *            病人id
	 * @return
	 */
	@RequestMapping(value = "saveReserveLog", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity saveReserveLog(PipCommReserveDetailFilter pcdf) {
		Map<String, Object> res = new HashMap<String, Object>();
		if (pcdf.getPatientId() == null || "".equals(pcdf.getPatientId())) {
			return null;
		}
		if (pcdf.getMaxVersion() == null || "".equals(pcdf.getMaxVersion())) {
			return null;
		}
		String docName = Securitys.getUser().getName();
		String docLoginName = Securitys.getUser().getLoginName();
		pcdf.setDoc(docName);
		pcdf.setDocNo(docLoginName);

		Boolean bl = pipCommReserveService.saveReserveDetailUpdateReserve(pcdf);
		res.put("success", bl);

		return new ResponseEntity(res, HttpStatus.OK);

	}

	@RequestMapping(value = "findPatientVersionList", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity findPatientVersionList(PipCommReserveDetailFilter pcdf) {
		Map<String, Object> res = new HashMap<String, Object>();
		if (pcdf.getPatientId() == null || "".equals(pcdf.getPatientId())) {
			return null;
		}
		if (pcdf.getVersion() == null || "".equals(pcdf.getVersion())) {
			return null;
		}
		PipCommReserveDetail pcrd = new PipCommReserveDetail();
		pcrd.setPatientId(pcdf.getPatientId());
		pcrd.setVersion(pcdf.getVersion());
		List<PipCommReserveDetail> list = pipCommReserveDetailService
				.selectPipCommReserveDetail(pcrd);
		// JqgridResponse<PipCommReserveDetail> response =
		// JqgridResponseContext.getJqgridResponse();
		// response.setRows(list);
		// return response;

		res.put("data", list);

		return new ResponseEntity(res, HttpStatus.OK);
	}

	public static String getResult(int number) {
		switch (number) {
		case 1:
			return "更改来院时间";
		case 2:
			return "可按计划日来院";
		case 3:
			return "在外地";
		case 4:
			return "行动不便";
		case 5:
			return "拒绝来访";
		case 6:
			return "无法取得联系";
		case 7:
			return "其他情况";

		default:
			return "其他";
		}

	}

}
