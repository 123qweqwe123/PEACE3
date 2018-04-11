package com.bdcor.pip.web.quality.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.quality.domain.InWorkStatisticsVo;
import com.bdcor.pip.web.quality.filter.InWorkStatisticsFilter;
import com.bdcor.pip.web.quality.service.InWorkStatisticsService;

@Controller
@RequestMapping("quality/inWorkStatistics")
public class InWorkStatisticsController {
	
	@Autowired
	private InWorkStatisticsService inWorkStatisticsService;
	
	@RequestMapping
	public String init(){
		return "quality/inworkstatistics/list";
	}
	
	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody JqgridResponse<InWorkStatisticsVo> getPatientQnList(InWorkStatisticsFilter filter){
		List<InWorkStatisticsVo> inWorkStatisticsList = inWorkStatisticsService.list(filter);
		if(inWorkStatisticsList != null && inWorkStatisticsList.size()>0){
			for(int i=0;i<inWorkStatisticsList.size();i++){//前台jqgrid会自动将自动将json串中的id给设置为主键
				inWorkStatisticsList.get(i).setId(i+"");
			}
		}
		JqgridResponse<InWorkStatisticsVo> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(inWorkStatisticsList);
        return response;
	}
	
}
