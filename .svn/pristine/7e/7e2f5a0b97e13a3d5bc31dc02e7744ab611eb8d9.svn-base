package com.bdcor.pip.web.qn.controller;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.qn.domain.*;
import com.bdcor.pip.web.qn.filter.EventCheckFilter;
import com.bdcor.pip.web.qn.service.EventCheckService;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * <pre>
 * </pre>
 * Author: huangrupeng
 * Create: 17/5/11 下午3:43
 */
@Controller
@RequestMapping("qn/eventMgt/checkEvents1")
public class EventCheckController {

    Logger logger = LoggerFactory.getLogger(EventCheckService.class);

    @Autowired
    EventCheckService eventCheckService;

    @RequestMapping
    public String checkEvents() {
        return "qn/eventMgt/checkEvents1";
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
    JqgridResponse<PipCommEventVO1> getEventList(EventCheckFilter filter) {
        List<PipCommEventVO1> data = eventCheckService.getEventCheckList(filter);
        JqgridResponse<PipCommEventVO1> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(data);
        return response;
    }

    @RequestMapping("/check")
    public String checkEvent(String eventCode, Model model) {
        eventCheckService.getEventInfo(eventCode, model);
        return "qn/eventMgt/checkEvent1";
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
            model.addAttribute("er", eventCheckService.getErByEventCode(eventCode));
        }
        return "qn/eventMgt/checkEvent_er1";
    }


    @RequestMapping("/saveEvent")
    @ResponseBody
    public String saveEventCheck(PipCommEventCheck1 check, PipCommEventCheckEr1 er) {
        try {
            eventCheckService.saveOrUpdate(check, er);
            return "success";
        } catch (Exception e) {
            logger.error("事件保存失败", e);
            return "fail";
        }
    }


    @RequestMapping("/addAuditUser")
    @ResponseBody
    public boolean addAuditUser() {
        return eventCheckService.addAuditUser();
    }

    @RequestMapping(value = "addEvent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addEvent(String eventCode, Model model) {
        Map<String, Object> res = new HashMap<String, Object>();
        try {
            String newEventCode = eventCheckService.addEvent(eventCode, model);
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
            List<PipCommEventExportVO> dList = eventCheckService.getEvent2exportList(filter);
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
        return eventCheckService.updateEventCheckStatus(eventCodeList);
    }
}
