package com.bdcor.pip.web.data.controller;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.util.ReflectionUtils;
import com.bdcor.pip.web.common.dao.PipCommRemarkMapper;
import com.bdcor.pip.web.common.domain.PipCommRemark;
import com.bdcor.pip.web.data.dao.*;
import com.bdcor.pip.web.data.domain.*;
import com.bdcor.pip.web.data.filter.PatientDropLogFilter;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.data.service.PatientService;
import com.bdcor.pip.web.pro.promgt.service.LccService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "sys/drop")
public class PatientIdDropController {
	@Autowired
	private PipCommRemarkMapper remarkMapper;
	@Autowired
	private LccService lccService;
	@Autowired
	private PipCommPatientMapper pipCommPatientMapper;

	@Autowired
	private PipCommPatientInputMapper pipCommPatientInputMapper;

	@Autowired
	private PipCommPatientQcMapper pipCommPatientQcMapper;

	@Autowired
	private PipCommPatientQcDropMapper pipCommPatientQcDropMapper;

	@Autowired
	private UqsAnswerMapper uqsAnswerMapper;

	@Autowired
	private UqsAnswerDropMapper uqsAnswerDropMapper;

	@Autowired
	private PipCommPatientDropLogMapper pipCommPatientDropLogMapper;

	@Autowired
	private PipCommPatientQcLogMapper pipCommPatientQcLogMapper;

	@Autowired
	private PipCommPatientUpdateLogMapper pipCommPatientUpdateLogMapper;

	@Autowired
	private PatientService patientService;

	@RequestMapping
	public String init(Model model) {
		return "drop/index";
	}

	@RequestMapping(value = "list")
	public String list(Model model) {
		return "drop/list";
	}

	@RequestMapping(value = "checkIsExist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Object> checkIsExist(
			@RequestParam("patientId") String patientId) {
		Map<String, Object> map = new HashMap<String, Object>();
		PipCommPatient selectByKey = patientService.selectByKey(patientId);
		if (null != selectByKey) {
			map.put("result", true);
		} else {
			map.put("result", false);
		}
		return map;
	}

	@RequestMapping(value = "query")
	@ResponseBody
	public Map<String, String> query(HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		String patientId = request.getParameter("id");
		ret.put("patientId", patientId);
		if (patientId.substring(0, 1).equals("G") && patientId.length() == 10) {
			// 高危
			PipCommPatientExample example = new PipCommPatientExample();
			example.createCriteria().andRiskCodeEqualTo(patientId);
			List<PipCommPatient> list = this.pipCommPatientMapper
					.selectByExample(example);
			if (list.size() == 1) {
				this.formatPatitent(ret, list.get(0));
				ret.put("patientId", list.get(0).getPatientId());
			} else if (list.size() > 1) {
				ret.put("msg", "无效的高危条码，请联系管理员解决！");
			} else {
				ret.put("msg", "该条码不存在！");
			}
		} else if (!patientId.substring(0, 1).equals("G")
				&& patientId.length() == 11) {
			// 初筛
			PipCommPatientKey key = new PipCommPatientKey();
			key.setProjectId(Securitys.getUser().getCurrent_projectId());
			key.setPatientId(patientId);
			PipCommPatient pat = this.pipCommPatientMapper
					.selectByPrimaryKey(key);
			if (pat != null) {
				this.formatPatitent(ret, pat);
			} else {
				ret.put("msg", "该条码不存在！");
			}
		}
		return ret;
	}

	private void formatPatitent(Map<String, String> ret, PipCommPatient pat) {
		Field[] fields = pat.getClass().getDeclaredFields();
		for (Field f : fields) {
			if (ReflectionUtils.getFieldValue(pat, f.getName()) != null)
				ret.put(f.getName(),
						ReflectionUtils.getFieldValue(pat, f.getName())
								.toString());
		}
		if (pat.getSex().equals("1"))
			ret.put("sexTxt", "男");
		if (pat.getSex().equals("2"))
			ret.put("sexTxt", "女");
		if (pat.getBirthday() != null) {
			ret.put("birthdayTxt", DateUtil.formatDate(pat.getBirthday()));
		}
	}

	private void formatPatitentInput(Map<String, String> ret,
			PipCommPatientInput pat) {
		Field[] fields = pat.getClass().getDeclaredFields();
		for (Field f : fields) {
			if (ReflectionUtils.getFieldValue(pat, f.getName()) != null)
				ret.put(f.getName(),
						ReflectionUtils.getFieldValue(pat, f.getName())
								.toString());
		}
		if (pat.getSex().equals("1"))
			ret.put("sexTxt", "男");
		if (pat.getSex().equals("2"))
			ret.put("sexTxt", "女");
		if (pat.getBirthday() != null) {
			ret.put("birthdayTxt", DateUtil.formatDate(pat.getBirthday()));
		}
	}

	/**
	 * 问卷列表
	 * */
	@RequestMapping(value = "listUqs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipCommPatientQc> listUqs(HttpServletRequest request) {
		String patientId = request.getParameter("patientId");
		if (patientId == null)
			patientId = "";
		String itemCode = request.getParameter("itemCode");

		PipCommPatientQcExample example = new PipCommPatientQcExample();

		if (StringUtils.isNoneBlank(itemCode)) {
			example.createCriteria().andPatientIdEqualTo(patientId)
					.andItemCodeEqualTo(itemCode);
		} else {
			example.createCriteria().andPatientIdEqualTo(patientId);
		}

		List<PipCommPatientQc> newsList = this.pipCommPatientQcMapper
				.selectByExample(example);
		JqgridResponse<PipCommPatientQc> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(newsList);
		return response;
	}

	/**
	 * 作废问卷列表
	 * */
	@RequestMapping(value = "listUqsDrop", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipCommPatientQcDrop> listUqsDrop(HttpServletRequest request) {
		String patientId = request.getParameter("patientId");
		if (patientId == null)
			patientId = "";
		String itemCode = request.getParameter("itemCode");

		PipCommPatientQcDropExample example = new PipCommPatientQcDropExample();
		if (StringUtils.isNoneBlank(itemCode)) {
			example.createCriteria().andPatientIdEqualTo(patientId)
					.andItemCodeEqualTo(itemCode);
		} else {
			example.createCriteria().andPatientIdEqualTo(patientId);
		}
		List<PipCommPatientQcDrop> newsList = this.pipCommPatientQcDropMapper
				.selectByExample(example);
		JqgridResponse<PipCommPatientQcDrop> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(newsList);
		return response;
	}

	@RequestMapping(value = "listLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipCommPatientDropLog> listLog(PatientDropLogFilter filter) {

		List<PipCommPatientDropLog> newsList = this.pipCommPatientDropLogMapper
				.query(filter);
		JqgridResponse<PipCommPatientDropLog> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(newsList);
		return response;
	}

	@RequestMapping(value = "dropId")
	@ResponseBody
	public Map<String, String> dropId(HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		String patientId = request.getParameter("patientId");
		try {
			this.patientService.dropPatient("001", patientId);

		} catch (Exception ex) {
			ret.put("msg", ex.getMessage());
		}
		return ret;
	}

	@RequestMapping(value = "exchangeId")
	@ResponseBody
	public Map<String, String> exchangeId(HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		String patientId = request.getParameter("patientId");
		String targetId = request.getParameter("targetId");
		String sourceId = request.getParameter("sourceId");
		String items = request.getParameter("items");
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			this.patientService.updatePatient("001", patientId, targetId,
					sourceId, items, params);
		} catch (Exception ex) {
			ret.put("msg", ex.getMessage());
		}
		return ret;
	}

	// -------------------------------

	@RequestMapping(value = "updatePatId")
	public String updatePatId(Model model) {
		return "drop/updatePatId";
	}

	@RequestMapping(value = "movePatId")
	public String movePatId(Model model) {
		return "drop/movePatId";
	}

	@RequestMapping(value = "openmodalMove")
	public String formMove(Model model) {
		return "drop/form";
	}

	@RequestMapping(value = "listUpdate")
	public String listUpdate(Model model) {
		return "drop/listUpdate";
	}

	@RequestMapping(value = "listMove")
	public String listMove(Model model) {
		return "drop/listMove";
	}

	@RequestMapping(value = "queryIdCard")
	@ResponseBody
	public Map<String, String> queryIdCard(HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		String idCard = request.getParameter("idCard");

		PipCommPatientInputExample example = new PipCommPatientInputExample();
		example.createCriteria().andUpdateDateIsNotNull()
				.andIdNumberEqualTo(idCard);
		List<PipCommPatientInput> list = this.pipCommPatientInputMapper
				.selectByExample(example);
		if (list.size() == 1) {
			this.formatPatitentInput(ret, list.get(0));
			ret.put("patientId", list.get(0).getPatientId());

			PipCommPatientExample exa = new PipCommPatientExample();
			exa.createCriteria().andUpdateDateIsNotNull()
					.andIdNumberEqualTo(idCard);
			List<PipCommPatient> list2 = this.pipCommPatientMapper
					.selectByExample(exa);
			if (list2 != null && list2.size() > 1) {
				for (PipCommPatient pp : list2) {
					if (ret.get("patientId") != null
							&& ret.get("patientId").toString()
									.indexOf(pp.getPatientId()) < 0) {
						ret.put("patientId",
								ret.get("patientId") + "|" + pp.getPatientId());

					}
					if (StringUtils.isNoneBlank(pp.getRiskCode())
							&& ret.get("riskCode") != null
							&& ret.get("riskCode").toString()
									.indexOf(pp.getRiskCode()) < 0) {
						ret.put("riskCode",
								ret.get("riskCode") + "|" + pp.getRiskCode());
					}
				}
			}
		} else if (list.size() > 1) {
			String name = list.get(0).getPatientName();
			this.formatPatitentInput(ret, list.get(0));
			ret.put("patientId", list.get(0).getPatientId());
			for (int i = 1; i < list.size(); i++) {
				PipCommPatientInput p = list.get(i);
				if (!p.getPatientName().equals(name)) {
					ret.put("msg", "该身份证号重复，需要手工处理！");
				} else {
					ret.put("patientId",
							ret.get("patientId") + "|" + p.getPatientId());
					if (StringUtils.isNoneBlank(p.getRiskCode()))
						ret.put("riskCode",
								ret.get("riskCode") + "|" + p.getRiskCode());
				}
			}

			PipCommPatientExample exa = new PipCommPatientExample();
			exa.createCriteria().andUpdateDateIsNotNull()
					.andIdNumberEqualTo(idCard);
			List<PipCommPatient> list2 = this.pipCommPatientMapper
					.selectByExample(exa);
			if (list2 != null && list2.size() > 0) {
				for (PipCommPatient pp : list2) {
					if (ret.get("patientId") != null
							&& ret.get("patientId").toString()
									.indexOf(pp.getPatientId()) < 0) {
						ret.put("patientId",
								ret.get("patientId") + "|" + pp.getPatientId());

					}
					if (StringUtils.isNoneBlank(pp.getRiskCode())
							&& ret.get("riskCode") != null
							&& ret.get("riskCode").toString()
									.indexOf(pp.getRiskCode()) < 0) {
						ret.put("riskCode",
								ret.get("riskCode") + "|" + pp.getRiskCode());
					}
				}
			}
		} else {
			ret.put("msg", "该条码不存在！");
		}

		return ret;
	}

	@RequestMapping(value = "listUqs2", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipCommPatientQc> listUqs2(HttpServletRequest request) {
		String patientId = request.getParameter("patientId");
		if (StringUtils.isBlank(patientId) || patientId.equals("null"))
			return null;
		PipCommPatientQcExample example = new PipCommPatientQcExample();
		example.createCriteria().andPatientIdEqualTo(patientId);
		example.setOrderByClause(" ITEM_CODE ");
		List<PipCommPatientQc> newsList = this.pipCommPatientQcMapper
				.selectByExample(example);
		List<PipCommPatientQc> ret = new ArrayList<PipCommPatientQc>();
		String[] as = null;
		if (patientId.length() == 11) {
			as = new String[] { "001001", "001002" };
		} else {
			as = new String[] { "001003", "001004", "001005", "001006" };
		}
		for (String item : as) {
			boolean match = true;
			for (PipCommPatientQc q : newsList) {
				if (q.getItemCode().equals(item)) {
					ret.add(q);
					match = false;
					break;
				}
			}
			if (match) {
				PipCommPatientQc qq = new PipCommPatientQc();
				qq.setPatientId(patientId);
				qq.setItemCode(item);
				ret.add(qq);
			}
		}

		JqgridResponse<PipCommPatientQc> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(ret);
		return response;
	}

	@RequestMapping(value = "dropPatientUqs")
	@ResponseBody
	public Map<String, String> dropPatientUqs(HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		String patientId = request.getParameter("patientId");
		String itemCode = request.getParameter("itemCode");
		try {
			this.patientService.dropPatientUqs("001", patientId, itemCode);

		} catch (Exception ex) {
			ret.put("msg", ex.getMessage());
		}
		return ret;
	}

	@RequestMapping(value = "copyPatientUqs")
	@ResponseBody
	public Map<String, String> copyPatientUqs(HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		String patientId = request.getParameter("patientId");
		String itemCode = request.getParameter("itemCode");
		String sourceId = request.getParameter("sourceId");
		String dropDate = request.getParameter("dropDate");
		try {
			this.patientService.copyPatientUqs("001", patientId, sourceId,
					itemCode, dropDate);

		} catch (Exception ex) {
			ret.put("msg", ex.getMessage());
		}
		return ret;
	}

	@RequestMapping(value = "listUqsLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipCommPatientQcLog> listUqsLog(PatientDropLogFilter filter) {

		List<PipCommPatientQcLog> newsList = this.pipCommPatientQcLogMapper
				.query(filter);
		JqgridResponse<PipCommPatientQcLog> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(newsList);
		return response;
	}

	@RequestMapping(value = "listPatLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipCommPatientUpdateLog> listPatLog(
			PatientDropLogFilter filter) {

		List<PipCommPatientUpdateLog> newsList = this.pipCommPatientUpdateLogMapper
				.query(filter);
		JqgridResponse<PipCommPatientUpdateLog> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(newsList);
		return response;
	}

	@RequestMapping(value = "updatePatient")
	@ResponseBody
	public Map<String, String> updatePatient(HttpServletRequest request) {
		Map<String, String> ret = new HashMap<String, String>();
		String idNumber = request.getParameter("idNumber");
		String patientId = request.getParameter("patientId");
		String riskCode = request.getParameter("riskCode");
		String delPat = request.getParameter("delPat");
		String delRisk = request.getParameter("delRisk");
		try {
			this.patientService.updatePatientCode(Securitys.getUser()
					.getCurrent_projectId(), idNumber, patientId, riskCode,
					delPat, delRisk);

		} catch (Exception ex) {
			ret.put("msg", ex.getMessage());
		}
		return ret;
	}

	// 查询所有患者信息
	@RequestMapping(value = "queryPatient")
	public String queryPatient(PatientFilter pf, Model model) {
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			model.addAttribute("lccDictList", lccService.getDataLimitLcc());
		} else {
			model.addAttribute("lccDictList",
					lccService.getDataLimitLccForLccCode());

		}

		return "pro/patient/patientlist";

	}
	
	@RequestMapping(value = "openmodalChange")// , method = RequestMethod.GET
	public ModelAndView openmodalChange(@RequestParam("patientId") String patientId,
			String type , String lccCode,
			Model model) {
		ModelAndView mav = new ModelAndView("pro/patient/changeform");
		String projectId = Securitys.getUser().getCurrent_projectId();
		
		model.addAttribute("lccCode", lccCode);
		model.addAttribute("type", type);
		model.addAttribute("patientId", patientId);
		return mav;
	}

	@RequestMapping(value = "savechange")
	public @ResponseBody
	ResponseEntity saveChange(
			String patientId , String type , String lccCode , 
			String val , String person , String changedate , String bz
			) {
		Map<String, Object> res = new HashMap<String, Object>();
		String projectName = Securitys.getUser().getCurrent_projectName();
		
		try {
			if (patientId != null && !"".equals(patientId) 
					&& type != null && !"".equals(type)
					&& val != null && !"".equals(val)
					) {
				PipCommPatient po = new PipCommPatient();
				if( "1".equals(type) ){
					po.setIsJoinMsg(val);
				}else if("2".equals(type)){
					po.setIs6State(Integer.parseInt(val));
				}
				PipCommPatientExample pe = new PipCommPatientExample();
				pe.createCriteria().andPatientIdEqualTo(patientId);
				pipCommPatientMapper.updateByExampleSelective(po, pe);
				res.put("success", true);

			} else {
				res.put("success", false);
			}
		} catch (Exception e) {
			res.put("success", false);
		}
		
		if( "1".equals(val) ){
			PipCommRemark pr = new PipCommRemark();
			pr.setApplyBy(person);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				pr.setApplyDate(sdf.parse(changedate));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pr.setModifyBy(Securitys.getUser().getId());
			pr.setModifyDate(new Date());
			pr.setBz(bz == null ? "" : bz);
			pr.setPk1(patientId);
			pr.setId(getPrimaryId());
			pr.setModuleId((short)1);
			if( "1".equals(type) ){
				pr.setRemark1("修改为参与短信项目");
			}else if("2".equals(type) ){
				pr.setRemark1("修改为可答六月问卷");
			}
			remarkMapper.insert(pr);
		}
		return new ResponseEntity(res, HttpStatus.OK);
	}
	
	@RequestMapping(value = "queryPatientList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse<PipCommPatient> queryPatientList(PatientFilter pf) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		pf.setProjectId(projectId);
		pf.setUserId(Securitys.getUserId());
		List<PipCommPatient> data = patientService.queryPatientList(pf);
		JqgridResponse<PipCommPatient> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(data);
		return response;

	}

	@RequestMapping(value = "openmodalUpdatePage", method = RequestMethod.GET)
	public ModelAndView openInput(@RequestParam("patientId") String patientId,
			Model model) {
		ModelAndView mav = new ModelAndView("pro/patient/form");
		String projectId = Securitys.getUser().getCurrent_projectId();
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			model.addAttribute("lccDictList", lccService.getDataLimitLcc());
		} else {
			model.addAttribute("lccDictList",
					lccService.getDataLimitLccForLccCode());

		}
		// patientId 不为空 就查询出对应的患者数据 填充到患者form页面
		if (patientId != null && !"".equals(patientId)) {
			PatientFilter pf = new PatientFilter();
			pf.setPatientId(patientId);
			pf.setProjectId(projectId); // --解决患者页面修改界面数据不显示 add by yangfeng
			List<PipCommPatient> data = patientService.queryPatientList(pf);
			if (data != null && data.size() > 0) {
				model.addAttribute("patient", data.get(0));
			}

		} else {
			mav = new ModelAndView("pro/sys/queryPatientList");
		}

		return mav;
	}

	@RequestMapping(value = "openmodalUpdate", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity openmodalUpdate(PatientFilter pf) {

		Map<String, Object> res = new HashMap<String, Object>();
		String projectName = Securitys.getUser().getCurrent_projectName();
		// String projectId=Securitys.getUser().getCurrent_projectId();
		// patientId 不为空 就查询出对应的患者数据 填充到患者form页面 【更新】
		try {
			// pf.setProjectId(Constant.projectId); --注释掉 add by yangfeng
			pf.setProjectId(Securitys.getUser().getCurrent_projectId()); // --获取当前用户项目id
																			// add
																			// by
																			// yangfeng
			PipCommPatient pcp = new PipCommPatient();
			pcp.setPatientName(pf.getPatientName());
			pcp.setPatientId(pf.getPatientId());
			pcp.setProjectId(pf.getProjectId());
			pcp.setPhone(pf.getPhone());
			pcp.setMobile(pf.getMobile());
			pcp.setSex(pf.getSex());
			pcp.setEmail(pf.getEmail());
			pcp.setLccCode(pf.getLccCode());
			pcp.setPatientCode(pf.getPatientCode());
			pcp.setIdNumber(pf.getIdNumber());
			pcp.setLinkMan1(pf.getLinkMan1());
			pcp.setLinkMan1Mobile(pf.getLinkMan1Mobile());
			pcp.setLinkMan1Relation(pf.getLinkMan1Relation());
			pcp.setIs6State(pf.getIs6State());
			if (pf.getPatientId() != null && !"".equals(pf.getPatientId())) {
				// 修改
				// patientService.updateByPatientList(pcp);

                PipCommPatientKey pk = new PipCommPatientKey();
                pk.setProjectId(pf.getProjectId());
                pk.setPatientId(pf.getPatientId());
                PipCommPatient po = pipCommPatientMapper.selectByPrimaryKey(pk);
            // 性别变更时记录日志信息
                if(!pf.getSex().equals(po.getSex()) ){
                    PipCommRemark pr = new PipCommRemark();
                    pr.setId(getPrimaryId());
                    pr.setModifyBy(Securitys.getUser().getId());
                    pr.setModifyDate(new Date());
                    pr.setRemark1("记录性别信息变更为："+pf.getSex());
                    pr.setPk1(pcp.getPatientId());
                    remarkMapper.insert(pr);
                }

                patientService.updateByPrimaryKey(pcp);
                res.put("success", true);

			} else {
				res.put("success", false);
			}
		} catch (Exception e) {
			res.put("success", false);

		}
		return new ResponseEntity(res, HttpStatus.OK);
	}

	@RequestMapping(value = "openmodalAddPage")
	public ModelAndView openmodalAddPage(Model model) {
		if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
			model.addAttribute("lccDictList", lccService.getDataLimitLcc());
		} else {
			model.addAttribute("lccDictList",
					lccService.getDataLimitLccForLccCode());
		}
		ModelAndView mav = new ModelAndView("pro/patient/addpage");
		return mav;
	}

	@RequestMapping(value = "openmodalAdd", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseEntity openmodalAdd(PatientFilter pf) {

		Map<String, Object> res = new HashMap<String, Object>();
		String projectId = Securitys.getUser().getCurrent_projectId();
		try {
			pf.setProjectId(projectId);
			PipCommPatient pcp = new PipCommPatient();
			pcp.setPatientName(pf.getPatientName());
			pcp.setPatientId(pf.getPatientId());
			pcp.setProjectId(pf.getProjectId());
			pcp.setPhone(pf.getPhone());
			pcp.setMobile(pf.getMobile());
			pcp.setSex(pf.getSex());
			pcp.setEmail(pf.getEmail());
			pcp.setLccCode(pf.getLccCode());
			pcp.setPatientCode(pf.getPatientCode());
			pcp.setIdNumber(pf.getIdNumber());
			pcp.setCreateBy(Securitys.getUserId());
			pcp.setCreateDate(new Date());
			pcp.setLinkMan1(pf.getLinkMan1());
			pcp.setLinkMan1Mobile(pf.getLinkMan1Mobile());
			pcp.setLinkMan1Relation(pf.getLinkMan1Relation());
			if (pf.getPatientId() != null && !"".equals(pf.getPatientId())) {
				// 新增
				PipCommPatient ppt = patientService.selectByKey(pf
						.getPatientId());
				if (null != ppt) {
					res.put("msg", "患者ID重复");
					res.put("success", false);
					return new ResponseEntity(res, HttpStatus.OK);
				}

				patientService.insertSelective(pcp);
				res.put("success", true);

			} else {
				res.put("success", false);
			}
		} catch (Exception e) {
			res.put("success", false);
			e.printStackTrace();
		}

		return new ResponseEntity(res, HttpStatus.OK);
	}

	// 主键生成策略
	public String getPrimaryId(){
		Long now = System.currentTimeMillis();
		int random = new Random().nextInt(900)+100; // 随机数 100-999范围
		return now.toString() + random;
	}
}
