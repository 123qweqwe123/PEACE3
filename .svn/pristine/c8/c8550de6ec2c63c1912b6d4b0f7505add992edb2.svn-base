package com.bdcor.pip.web.quality.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.bdcor.pip.client.vo.paper.QuestionOption;
import com.bdcor.pip.web.common.service.ComboxService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.QuestionC;
import com.bdcor.pip.client.vo.paper.QuestionGroup;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.dict.domain.DictCommDTO;
import com.bdcor.pip.dict.utils.DictUtils;
import com.bdcor.pip.web.qn.service.AnswerQnService;
import com.bdcor.pip.web.qn.service.impl.AnswerQnlogServiceImpl;
import com.bdcor.pip.web.quality.domain.PatientQn;
import com.bdcor.pip.web.quality.filter.PatientQnFilter;
import com.bdcor.pip.web.quality.service.PreviewQuestionnaireService;

@Controller
@RequestMapping("quality/previewqn")
public class PreviewQuestionnaireController {

	@Autowired
	private PreviewQuestionnaireService previewQuestionnaireService;

	@Autowired
	private AnswerQnlogServiceImpl ansqnService;

	@Autowired
	private ComboxService comboxService;

	@RequestMapping
	public String init() {
		return "quality/previewqn/list";
	}

	@RequestMapping(value = "list", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PatientQn> getPatientQnList(PatientQnFilter filter) {
		List<PatientQn> patientQnList = previewQuestionnaireService
				.getPatientQnList(filter);
		JqgridResponse<PatientQn> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(patientQnList);
		return response;
	}

	@RequestMapping(value = "openmodalshowQnInfo", method = RequestMethod.GET)
	public String showQnInfo(Model model,
			@RequestParam("patientId") String patientId) {
		PatientQnFilter filter = new PatientQnFilter();
		filter.setpIdEq(patientId);
		List<PatientQn> patientQnList = previewQuestionnaireService
				.getPatientQnList(filter);
		if (patientQnList != null && patientQnList.size() > 0)
			model.addAttribute("pq", patientQnList.get(0));

		Map<String, String> map = new HashMap<String, String>();
		map.put("projectId", Securitys.getUser().getCurrent_projectId());
		map.put("patientId", patientId);
		List<Map<String, Object>> list = previewQuestionnaireService
				.getQnList(map);
		model.addAttribute("qnList", list);
		return "quality/previewqn/qnInfo";
	}

	/**
	 * 预览问卷
	 */
	@RequestMapping(value = "showQnDetail", method = RequestMethod.POST)
	public String showQnDetail(HttpServletRequest request, Model model,
			@RequestParam("qnVersion") String qnVersion,
			@RequestParam("patientId") String patientId,
			@RequestParam("logId") String logId) {
		String basePath = this.getClass().getResource("/").getPath();
		if (basePath.indexOf(":") > -1) {// windows
			basePath = basePath.substring(1);
		}
		String path = basePath + "paper/UQS_"
				+ qnVersion.replaceAll("\\.", "_") + ".xml";
		Paper qn = previewQuestionnaireService.getPaperByPath(path);
		if (qnVersion.trim().substring(4, 7).equals("001")) {
			String date6_1 = ansqnService
					.getStartHosDate(patientId, "001", "1");
			for (QuestionGroup qg : qn.getQGroup()) {
				if (qg.getQs() != null && qg.getId().equals("6")) {
					for (QuestionC q : qg.getQs()) {
						if (q.getOptions() != null && q.getId().equals("1")) {
							q.setDisplayname("自" + date6_1.substring(0, 4)
									+ "年" + date6_1.substring(4, 6) + "月"
									+ date6_1.substring(6)
									+ "日  ，您是否住过院(至少在医院住过一晚)？");
						}
					}
				}
			}
		}

		if (qnVersion.trim().substring(4, 7).equals("011")) {
			String date6_1 = ansqnService
					.getStartHosDate(patientId, "011", "1");
			for (QuestionGroup qg : qn.getQGroup()) {
				if (qg.getQs() != null && qg.getId().equals("3")) {
					for (QuestionC q : qg.getQs()) {
						if (q.getOptions() != null && q.getId().equals("1")) {
							q.setDisplayname("自" + date6_1.substring(0, 4)
									+ "年" + date6_1.substring(4, 6) + "月"
									+ date6_1.substring(6)
									+ "日  ，您是否住过院(至少在医院住过一晚)？");
						}
					}
				}
			}
		}
		Map<String, String> resultOptionMap = previewQuestionnaireService
				.getAnswerMap(qnVersion, patientId, logId);
		resultOptionMap = previewQuestionnaireService.getAnswerWithDict(qn,resultOptionMap);
//		qn  resultOptionMap
//		comboxService.getDictName(qn)

//		Iterator it =  resultOptionMap.entrySet().iterator();
//		while( it.hasNext() ){
//			Map.Entry me  = (Map.Entry)it.next();
//			String key = me.getKey().toString();
//			String[] ids = key.split("_");
//			String dictNmae = comboxService.getDictName(qn,ids[0],ids[1],ids[2],me.getValue().toString());
//			if(StringUtils.isNotBlank(dictNmae)){
//				resultOptionMap.put(key,dictNmae);
//			}
//		}


		model.addAttribute("questionnaire", qn);
		model.addAttribute("resultOptionMap", resultOptionMap);
		model.addAttribute("resultOptionMapJson",
				JSONUtils.toJSONString(resultOptionMap));
		model.addAttribute("uqsVersion",qnVersion);
		return "quality/previewqn/qnInfoDetail";
	}

}
