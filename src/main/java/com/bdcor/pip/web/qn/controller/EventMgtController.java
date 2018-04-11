package com.bdcor.pip.web.qn.controller;

import com.bdcor.pip.client.tools.JsonMapper;
import com.bdcor.pip.client.vo.paper.*;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.constant.ExcelDataConstant;
import com.bdcor.pip.data.util.ExcelExportBatchUtil;
import com.bdcor.pip.web.common.dao.PipSysExpexcelInfoMapper;
import com.bdcor.pip.web.common.domain.PipSysExpexcelInfo;
import com.bdcor.pip.web.common.domain.PipSysParameter;
import com.bdcor.pip.web.common.service.ComboxService;
import com.bdcor.pip.web.qn.domain.*;
import com.bdcor.pip.web.qn.filter.EventCheckFilter;
import com.bdcor.pip.web.qn.filter.EventFilter;
import com.bdcor.pip.web.qn.service.AnswerQnService;
import com.bdcor.pip.web.qn.service.EventMgtService;
import net.sf.jxls.transformer.XLSTransformer;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("qn/eventMgt")
public class EventMgtController {

    @Autowired
    private EventMgtService eventMgtService;
    @Autowired
    private ComboxService comboxService;
    @Autowired
    private AnswerQnService answerQnService;
    @Autowired
    private PipSysExpexcelInfoMapper excelinfpDao;

    /**
     * 事件管理首页
     *
     * @return
     */
    @RequestMapping
    public String init(HttpServletRequest request) {
        String result = request.getParameter("reportAfterresult");
        request.setAttribute("reportAfterresult", result);
        return "qn/eventMgt/list";
    }

    /**
     * 事件管理_人员列表
     */
    @RequestMapping(value = "getEventPatientList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    JqgridResponse<EventVo> getEventPatientList(EventFilter filter) {
        List<EventVo> data = eventMgtService.getEventPatientList(filter);
        JqgridResponse<EventVo> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(data);
        return response;
    }


    /**
     * 查看事件
     */
    @RequestMapping(value = "openmodalShowEvent")
    public String openmodalShowEvent(HttpServletRequest request) {
        request.setAttribute("patientId", request.getParameter("patientId"));
        request.setAttribute("hisList", eventMgtService.getHisList());
        return "qn/eventMgt/showEvent";
    }

    @RequestMapping(value = "showEventList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    JqgridResponse<Map<String, Object>> showEventList(EventFilter filter) {
        List<Map<String, Object>> data = eventMgtService.showEventList(filter);
        JqgridResponse<Map<String, Object>> response = JqgridResponseContext.getJqgridResponse();
        if (data != null && data.size() > 0d) {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).put("id", i + "");
            }
        }
        response.setRows(data);
        return response;
    }

    /**
     * 查看支持性材料
     */
    @RequestMapping(value = "openmodalShowUsrFile")
    public String openmodalShowUsrFile(HttpServletRequest request) {
        request.setAttribute("patientId", request.getParameter("patientId"));
        request.setAttribute("hisList", eventMgtService.getHisList());
        return "qn/eventMgt/showUsrFile";
    }

    /**
     * 查询所有收集材料
     */
    @RequestMapping(value = "showAllUsrFileList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    JqgridResponse<Map<String, Object>> showAllUsrFileList(EventFilter filter) {
        List<Map<String, Object>> data = eventMgtService.showAllUsrFileList(filter);
        JqgridResponse<Map<String, Object>> response = JqgridResponseContext.getJqgridResponse();
        if (data != null && data.size() > 0d) {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).put("id", i + "");
            }
        }
        response.setRows(data);
        return response;
    }

    @RequestMapping(value = "showUsrFileList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    JqgridResponse<Map<String, Object>> showUsrFileList(EventFilter filter) {
        List<Map<String, Object>> data = eventMgtService.showUsrFileList(filter);
        JqgridResponse<Map<String, Object>> response = JqgridResponseContext.getJqgridResponse();
        if (data != null && data.size() > 0d) {
            for (int i = 0; i < data.size(); i++) {
                data.get(i).put("id", i + "");
            }
        }
        response.setRows(data);
        return response;
    }

    /**
     * 查看上报列表
     */
    @RequestMapping(value = "openmodalShowReportList")
    public String openmodalShowReportList(HttpServletRequest request) {
        request.setAttribute("patientId", request.getParameter("patientId"));
        request.setAttribute("uqsCode", request.getParameter("uqsCode"));
        return "qn/eventMgt/showReportList";
    }


    @RequestMapping(value = "showReportList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    JqgridResponse<Map<String, Object>> showReportList(EventFilter filter) {
        List<Map<String, Object>> data = eventMgtService.showReportList(filter);
        JqgridResponse<Map<String, Object>> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(data);
        return response;
    }

    /**
     * 完成支持性文件收集
     *
     * @return
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "collectDictFile", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    ResponseEntity collectDictFile(@RequestParam("patientId") String patientId, @RequestParam("fileInfo") String fileInfo, @RequestParam("logMinId") String logMinId, @RequestParam("inHosDate") String inHosDate, @RequestParam("outHosDate") String outHosDate, @RequestParam("hosName") String hosName) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("success", true);
        try {
            eventMgtService.collectDictFile(logMinId, patientId, fileInfo, inHosDate, outHosDate, hosName);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("success", false);
            res.put("msg", "操作失败");
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }

    /**
     * 补报事件(弹出页面)
     */
    @RequestMapping(value = "openmodalReportAfter")
    public String openmodalReportAfter(HttpServletRequest request, Model model, @RequestParam("uqsCode") String uqsCode, @RequestParam("patientId") String patientId, @RequestParam("logId") String logId) {
        Map<String, String> answerLogMap = answerQnService.getAnswerLogMap_MIN(uqsCode, patientId, logId);
        if (answerLogMap == null) {
            model.addAttribute("errorMsg", "用户ID不存在");
            return "qn/eventMgt/reportAfter";
        }

        String uqsVersion = null;
        if (answerLogMap.get("UQSVERSION") == null || answerLogMap.get("UQSVERSION").trim().length() == 0) {
            uqsVersion = Securitys.getCurrentProject() + "." + uqsCode + ".001";
        } else {
            uqsVersion = answerLogMap.get("UQSVERSION");
        }


        String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/paper/UQS_" + uqsVersion.replaceAll("\\.", "_") + ".xml";
        //String path = "D:\\gener-dev\\workspace\\CHINAPEACE3-web\\src\\main\\resources"+"/paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
        Paper qn = answerQnService.getPaperByPath(path);
        if (qn == null) {
            model.addAttribute("errorMsg", "问卷模版不存在");
            return "qn/eventMgt/reportAfter";
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String UQSBeginTime = sf.format(new Date());

        String resultPath = answerLogMap.get("UQSFILE");
        Result r = null;
        if (resultPath != null && resultPath.trim().length() > 0) {
            try {
                r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(new FileInputStream(resultPath)).getQuestionnaire());
            } catch (Exception e) {
            }
        }

        Map<String, String> map = new HashMap<String, String>();
        if (r != null && r.getOptions() != null) {
            if (r.getOptions() != null) {
                for (ResultOption ro : r.getOptions()) {
                    String dictName = comboxService.getDictName(qn, ro.getQuestionSetId(), ro.getQuestionId(), ro.getResultId(), ro.getResutlStr());
                    if (dictName == null) {
                        map.put(ro.getQuestionSetId() + "_" + ro.getQuestionId() + "_" + ro.getResultId(), ro.getResutlStr());
                    } else {
                        map.put(ro.getQuestionSetId() + "_" + ro.getQuestionId() + "_" + ro.getResultId(), dictName);
                        map.put("dict" + ro.getQuestionSetId() + "_" + ro.getQuestionId() + "_" + ro.getResultId(), ro.getResutlStr());
                    }
                }
            }
        }

        if (uqsCode.equals("006")) {
            //自      年   月    日  ，您是否住过院(至少在医院住过一晚)？
            String date2_1 = new SimpleDateFormat("yyyyMMdd").format(new Date());
            if (answerLogMap.get("LAST_FOLLOWVIEW_DATE") != null && answerLogMap.get("LAST_FOLLOWVIEW_DATE").length() == 8) {
                date2_1 = answerLogMap.get("LAST_FOLLOWVIEW_DATE");
            }

            for (QuestionGroup qg : qn.getQGroup()) {
                if (qg.getQs() != null && qg.getId().equals("2")) {
                    for (QuestionC q : qg.getQs()) {
                        if (q.getOptions() != null && q.getId().equals("1")) {
                            q.setDisplayname("自" + date2_1.substring(0, 4) + "年" + date2_1.substring(4, 6) + "月" + date2_1.substring(6) + "日  ，您是否住过院(至少在医院住过一晚)？");
                        }
                    }
                }
            }
        }

        model.addAttribute("paper", qn);
        model.addAttribute("resultOptionMap", map);
        model.addAttribute("resultOptionMapJson", JsonMapper.objectToJson(map));

        model.addAttribute("UQSBeginTime", UQSBeginTime);
        model.addAttribute("uqsVersion", uqsVersion);
        model.addAttribute("patientId", patientId);
        model.addAttribute("logId", logId);
        model.addAttribute("uqsTypeName", "事件");
        if (logId.trim().length() > 0) {
            model.addAttribute("isModify", true);
        }


        if (request.getParameter("onlyShow") != null) {
            return "qn/eventMgt/showReport";
        }

        return "qn/eventMgt/reportAfter";
    }

    /**
     * 上报(提交小问卷)
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "saveQn")
    public String saveQn(HttpServletRequest request, RedirectAttributes attr) {
        String uqsVersion = request.getParameter("uqsVersion");
        String patientId = request.getParameter("patientId");
        String logId = request.getParameter("logId");
        if (uqsVersion == null || uqsVersion.length() < 10 || patientId == null) {
            attr.addAttribute("reportAfterresult", 2);
            return "redirect:/qn/eventMgt";
        }

        uqsVersion = uqsVersion.replaceAll("_", ".");

        String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/paper/UQS_" + uqsVersion.replaceAll("\\.", "_") + ".xml";
        //String path = "D:\\gener-dev\\workspace\\CHINAPEACE3-web\\src\\main\\resources"+"/paper/UQS_"+qnVersion.replaceAll("\\.","_")+".xml";
        Paper qn = answerQnService.getPaperByPath(path);
        if (qn == null) {
            attr.addAttribute("reportAfterresult", 2);
            return "redirect:/qn/eventMgt";
        }

        Map<String, String> answerLogMap = answerQnService.getAnswerLogMap_MIN(uqsVersion.substring(4, 7), patientId, logId);
        if (answerLogMap == null) {
            attr.addAttribute("reportAfterresult", 2);
            return "redirect:/qn/eventMgt";
        }

        try {
            answerQnService.saveQn_MIN(request, qn, logId);
            attr.addAttribute("reportAfterresult", 1);
        } catch (Exception e) {
            e.printStackTrace();
            attr.addAttribute("reportAfterresult", 2);
        }
        return "redirect:/qn/eventMgt";
    }

    /**
     * 得到支持性文件字典
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "dictFiles", produces = "application/json")
    @ResponseBody
    public List<Map<String, String>> getDictFiles(HttpServletRequest request) {
        return eventMgtService.getDictFiles();
    }


    /**
     * 事件列表首页
     */
    @RequestMapping(value = "eventList")
    public String eventList(HttpServletRequest request) {
        request.setAttribute("hisList", eventMgtService.getHisList());
        return "qn/eventMgt/eventList";
    }

    /**
     * 事件列表数据
     */
    @RequestMapping(value = "getEventList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    JqgridResponse<Map<String, Object>> getEventList(EventFilter filter) {
        List<Map<String, Object>> data = eventMgtService.getEventList(filter);
        JqgridResponse<Map<String, Object>> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(data);
        return response;
    }

    @RequestMapping(value = "/exportexcel")
    public ModelAndView exportexcel(HttpServletRequest request,
                                    HttpServletResponse response, EventFilter filter)
        throws Exception {
        request.setCharacterEncoding("utf-8");
        java.io.BufferedOutputStream bos = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String datestr = sdf.format(new Date());
            String filename = "事件列表" + datestr + "_" + Securitys.getUser().getName() + ".xls";
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                + new String(filename.getBytes("utf-8"), "ISO8859-1"));
//			List<Map<String,Object>> list = eventMgtService.getEventListExcel(filter);
            filter.setPage(1);
            filter.setRows(99999999);
            List<Map<String, Object>> list = eventMgtService.getEventList(filter);
            bos = new BufferedOutputStream(response.getOutputStream());
            SXSSFWorkbook workbook = new SXSSFWorkbook(ExcelExportBatchUtil.SXSSWORKBOOK_MEMORY);
            ExcelExportBatchUtil.createAndFillingExcel(list, ExcelDataConstant.HEADERARRAY_EVENT, ExcelDataConstant.DATANAMEARRAY_EVENT, workbook);
            workbook.write(bos);

            PipSysExpexcelInfo po = new PipSysExpexcelInfo();
            po.setId(UUID.randomUUID().toString());
            po.setFileName(filename);
            po.setLccCode(Securitys.getUser().getLccCode());
            po.setOperateDate(new Date());
            po.setOperatorId(Securitys.getUser().getId());
            po.setOperatorName(Securitys.getUser().getName());
            po.setPageId("ME0000000000093");
            po.setPageName("事件列表");
            excelinfpDao.insert(po);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null)
                bos.close();
        }
        return null;
    }

    Logger logger = LoggerFactory.getLogger(EventMgtController.class);

    @RequestMapping("/checkEvents")
    public String checkEvents() {
        return "qn/eventMgt/checkEvents";
    }

    /**
     * 事件审定列表
     *
     * @param filter
     * @return
     */
    @RequestMapping(value = "getCheckEvents", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public
    @ResponseBody
    JqgridResponse<PipCommEventVO> getEventList(EventCheckFilter filter) {
        List<PipCommEventVO> data = eventMgtService.getEventCheckList(filter);
        JqgridResponse<PipCommEventVO> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(data);
        return response;
    }

    @RequestMapping("/checkEvents/check")
    public String checkEvent(String eventCode, Model model) {
        eventMgtService.getEventInfo(eventCode, model);
        return "qn/eventMgt/checkEvent";
    }

    /**
     * 获取原始事件描述树
     */
    @RequestMapping("/currdesctree")
    @ResponseBody
    public List<PipSysParameter> currdesctree() {
        return eventMgtService.getCurrdesctree();
    }

    /**
     * 当前事件描述额外信息
     *
     * @param model
     * @return
     */
    @RequestMapping("/openmodalcurrdescer")
    public String currdesctree(Model model, String code, String eventCode) {
        model.addAttribute("code", code);
        if (StringUtils.isNotEmpty(eventCode)) {
            model.addAttribute("er", eventMgtService.getErByEventCode(eventCode));
        }
        return "qn/eventMgt/checkEvent_er";
    }


    @RequestMapping("checkEvents/saveEvent")
    @ResponseBody
    public String saveEventCheck(PipCommEventCheck check, PipCommEventCheckEr er) {
        try {
            eventMgtService.saveOrUpdate(check, er);
            return "success";
        } catch (Exception e) {
            logger.error("事件保存失败", e);
            return "fail";
        }
    }


    @RequestMapping("checkEvents/addAuditUser")
    @ResponseBody
    public boolean addAuditUser() {
        return eventMgtService.addAuditUser();
    }

    @RequestMapping(value = "checkEvents/addEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addEvent(String eventCode, Model model) {
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            String newEventCode = eventMgtService.addEvent(eventCode, model);
            res.put("success", true);
            res.put("eventCode", newEventCode);
        } catch (Exception e) {
            logger.error("事件保存失败", e);
            res.put("success", false);
        }

        return new ResponseEntity(res, HttpStatus.OK);
    }


    @RequestMapping(value = "download", method = RequestMethod.GET)
    public void download(EventCheckFilter filter, HttpServletResponse response) {
        String ctxPath = this.getClass().getResource("/").getPath()
            + "exceltemplate"
            + File.separator;
        String tempName = "eventcheck.xls";
        String templatePath = ctxPath + tempName;

        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;

        String datafile = ctxPath + "temp" + File.separator + "eventcheck-temp.xls";
        XLSTransformer transformer = new XLSTransformer();
        try {
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                + new String("事件审定列表.xls".getBytes("utf-8"), "ISO8859-1"));
            filter.setRows(Integer.MAX_VALUE - 1);
            filter.setPage(1);
            List<PipCommEventExportVO> dList = eventMgtService.getEvent2exportList(filter);
            Map beans = new HashMap();

            beans.put("resultList", dList);
            transformer.transformXLS(templatePath, beans, datafile);

            bis = new BufferedInputStream(new FileInputStream(datafile));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null)
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @RequestMapping("/updateEventCheckStatus")
    @ResponseBody
    public String updateEventCheckStatus(String eventCodeList) {
        return eventMgtService.updateEventCheckStatus(eventCodeList);
    }
}
