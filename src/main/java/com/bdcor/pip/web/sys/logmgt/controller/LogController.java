
package com.bdcor.pip.web.sys.logmgt.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.sys.logmgt.domain.LogDTO;
import com.bdcor.pip.web.sys.logmgt.filter.LogFilter;
import com.bdcor.pip.web.sys.logmgt.service.LogService; 

/**
 * 
 * <pre>
 * 功能说明：日志controller
 * </pre>
 * 
 * @author <a href="mailto:wang.g@gener-tech.com">WangGang</a>
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/sys/log")
public class LogController{

    @Autowired
    private LogService logService;
    
    /**
     * 初始化日志查询界面
     * @return
     */
    @RequiresPermissions("sysmgt:log")
    @RequestMapping
    public String init(){
        
        return "sys/log/list";
    }

    /**
     * 查询日志
     * @param filter
     * @return
     */
    @RequiresPermissions("sysmgt:log")
    @RequestMapping(value = "list",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public JqgridResponse<LogDTO> list(LogFilter filter) {
        List<LogDTO> userList = logService.list(filter);
        JqgridResponse<LogDTO> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(userList);
        return response; 
    }

}
