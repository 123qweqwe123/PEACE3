package com.bdcor.pip.web.common.controller;

import com.bdcor.pip.web.common.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Author: huangrupeng
 * Create: 17/3/6 下午2:44
 */
@Controller
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    PaperService paperService;

    @RequestMapping(value = "uqsTemplate")
    @ResponseBody
    public Object uqsTemplate(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            paperService.doPapers(request);
            map.put("msg", "问卷保存成功");
        } catch (Exception ex) {
            map.put("msg", ex.getMessage());
            ex.printStackTrace();
        }
        return map;
    }

    @RequestMapping("transxmltodb")
    public Object uqsTemplate2(HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            paperService.transXmlTodb();
            map.put("msg", "问卷保存成功");
        } catch (Exception ex) {
            map.put("msg", ex.getMessage());
            ex.printStackTrace();
        }
        return map;
    }
}
