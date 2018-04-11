package com.bdcor.pip.web.common.controller;

import com.bdcor.pip.core.mapper.JsonMapper;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.dict.domain.DictCommDTO;
import com.bdcor.pip.web.common.service.ComboxService;
import com.bdcor.pip.web.common.service.CommonJdbcService;
import com.bdcor.pip.web.data.service.PipCommEventDictfileService;
import com.bdcor.pip.web.material.supp.domain.PipMmsDefstorehouse;
import com.bdcor.pip.web.material.supp.service.MaterlInfoService;
import com.bdcor.pip.web.material.supp.service.PipMmsDefstorehouseService;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.pro.promgt.service.LccUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("combox")
public class ComboxController {
	private static final Logger log = LoggerFactory.getLogger(ComboxController.class);
	@Autowired
	private PipCommEventDictfileService pipCommEventDictfileService;
	@Autowired
	private CommonJdbcService commonJdbcService;
	@Autowired
	private LccService lccService;
	@Autowired
	private LccUserService lccUserService;
	@Autowired
	private ComboxService comboxService;
	@Autowired
	private PipMmsDefstorehouseService pipMmsDefstorehouseService;
	@Autowired
	private MaterlInfoService materlInfoService;
	@RequestMapping(value = "comboxData", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> comboxData(
			@RequestParam("table") String table,
			@RequestParam("cols") String cols,
			@RequestParam("where") String where) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("table", table);
		params.put("columns", cols.split("\\|"));
		if (!StringUtils.isEmpty(where)) {
			JsonMapper jm = JsonMapper.nonDefaultMapper();
			params.put("where", jm.fromJson(where, List.class));
			// ehcache的key应该使用table+where条件
			params.put("wherekey", where);
		}
		return this.commonJdbcService.selectJsonForAutocomplete(params);
	}

	@RequestMapping(value = "province", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> province(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String all = request.getParameter("all");
		if (all != null && all.trim().length() > 0) {
			map.put("all", "true");
		}
		return lccService.getProvinceList(map);
	}

	@RequestMapping(value = "rcc", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> rcc(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String provinceCode = request.getParameter("provinceCode");
		if (provinceCode != null && provinceCode.trim().length() > 0) {
			map.put("provinceCode", provinceCode);
		}
		String noDataLimit = request.getParameter("noDataLimit");
		if (provinceCode != null && provinceCode.trim().length() > 0) {
			map.put("noDataLimit", "true");
		}
		return lccService.getRccList(map);
	}

	/**
	 * 
	 * description:查找事件信息传递一个dictCode 参数
	 * 
	 * @author yangfeng
	 * @param request
	 * @return
	 * @update 2015-11-27
	 */
	@RequestMapping(value = "eventInfo", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> eventInfo(HttpServletRequest request) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String dicCode = request.getParameter("dictCode") == null ? null
				: request.getParameter("dictCode");
		List<DictCommDTO> dictHisList = pipCommEventDictfileService
				.getDictHisList(dicCode);

		for (DictCommDTO dto : dictHisList) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("VCODE", dto.getvCode());
			map.put("VNAME", dto.getvName());
			map.put("HELPCODE", dto.getHelpCode());
			list.add(map);
		}
		return list;
	}

	/**
	 * 
	 * description:查找库房信息
	 * 
	 * @author yangfeng
	 * @param request
	 * @return
	 * @update 2015-11-27
	 */
	@RequestMapping(value = "stockInfo", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> stockInfo(HttpServletRequest request) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		String limitLcc = StringUtils.isEmpty(request.getParameter("limitLcc")) ? null
				: request.getParameter("limitLcc").trim();
		List<PipMmsDefstorehouse> listAndLimit = null;
		if (StringUtils.isEmpty(limitLcc)) {
			listAndLimit = pipMmsDefstorehouseService.listAndLimit();
		} else {
			listAndLimit = pipMmsDefstorehouseService.listAndLimitLcc();
		}
		for (PipMmsDefstorehouse pdh : listAndLimit) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("STOCKCODE", pdh.getStockCode());
			map.put("STOCKNAME", pdh.getStockName());
			list.add(map);
		}
		return list;
	}

	@RequestMapping(value = "dataLimitLcc", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> dataLimitLcc(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		String provinceCode = request.getParameter("provinceCode");
		if (provinceCode != null && provinceCode.trim().length() > 0) {
			map.put("provinceCode", provinceCode);
		}
		String rccCode = request.getParameter("rccCode");
		if (rccCode != null && rccCode.trim().length() > 0) {
			map.put("rccCode", rccCode);
		}
		return lccService.getDataLimitLccList(map);
	}
	/**
	 * 
	 * description:  根据省份查询医院单位信息，不根据权限
	 * @author yangfeng  
	 * @param request
	 * @return   
	 * @update 2016年4月11日
	 */
	@RequestMapping(value = "dataNoLimitLcc", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> dataNoLimitLcc(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();

		String provinceCode = request.getParameter("provinceCode");
		if (provinceCode != null && provinceCode.trim().length() > 0) {
			map.put("provinceCode", provinceCode);
		}
		String rccCode = request.getParameter("rccCode");
		if (rccCode != null && rccCode.trim().length() > 0) {
			map.put("rccCode", rccCode);
		}
		return lccService.getLccListByProvinceCode(map);
	}
	/**
	 * 
	 * description: 根据LCCID lccCode，项目ID projectID，获取用户信息 没有加权限设置
	 * 
	 * @author yangfeng
	 * @param request
	 * @return
	 * @update 2015-11-10
	 */
	@RequestMapping(value = "user", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> user(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		String lccCode = request.getParameter("lccCode");
		map.put("projectId", Securitys.getUser().getCurrent_projectId());

		if (lccCode != null && lccCode.trim().length() > 0) {
			map.put("lccCode", lccCode);
		}
		return lccUserService.getLccUserList(map);
	}
	
	/**
	 * 
	 * description: 根据LCCID lccCode，项目ID projectID，获取用户信息 没有加权限设置
	 * 
	 * @author yangfeng
	 * @param request
	 * @return
	 * @update 2015-11-10
	 */
	@RequestMapping(value = "materlInfoListByLimit", produces = "application/json")
	@ResponseBody
	public List<Map<String, String>> materlInfoListByLccCode(HttpServletRequest request) {
		return materlInfoService.materlInfoListByLimit();
	}

	/**
	 * 问卷字典
	 */
	@RequestMapping(value = "getDict", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getDict(HttpServletRequest request,
						  @RequestParam("q")String q) {
		String code = request.getParameter("code");
		String prevCode = request.getParameter("prevTrueCode");
		String limitStr = request.getParameter("limit");

		if (code == null || prevCode == null || q == null) {
			return "";
		}
		int limit = 10;
		try {
			limit = Integer.parseInt(limitStr);
		} catch (Exception e) {
		}
		return comboxService.getDict(code, prevCode, q, limit);
	}
	
	@RequestMapping(value = "keepAlive", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity keepAlive(){
		Map<String,Object> res = new HashMap<String, Object>();
		res.put("success", true);
		return new ResponseEntity(res, HttpStatus.OK);
	}

	@RequestMapping("getautodata")
    public @ResponseBody String getAutoData( HttpServletRequest request,
											 @RequestParam("q") String q ){
		log.info("autocomplete查询，查看编码转换："+q);
		String type = request.getParameter("type");
        String limitStr = request.getParameter("limit");
        if("person".equals(type)){
        	String lccCode = request.getParameter("lccCode");
        	if(!StringUtils.isBlank(lccCode)){
                return comboxService.getAutoDoctor(q,limitStr,lccCode);
            }else{
                log.info("传入LCC_CODE为空,无法查询返回空");
        		return "";
        	}
        }else if("patient".equals(type)){
        	return comboxService.getAutoData(q,limitStr,type);
        }else{
        	return "";
        }
    }

}
