package com.bdcor.pip.web.progress.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jxls.transformer.XLSTransformer;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.DateUtil;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.util.ReflectionUtils;
import com.bdcor.pip.web.data.dao.PipCommDictDistrictMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.dao.PipFeeInfoMapper;
import com.bdcor.pip.web.data.dao.ReportFactoryViewMapper;
import com.bdcor.pip.web.data.dao.UqsHandleViewMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.ReportFactoryView;
import com.bdcor.pip.web.data.domain.ReportFactoryViewExample;
import com.bdcor.pip.web.data.filter.FeeInfoFilter;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.pro.promgt.dao.LccDao;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.progress.dao.MacStatDao;
import com.bdcor.pip.web.progress.domain.MacStat;
import com.bdcor.pip.web.progress.filter.ProgressFilter;
import com.bdcor.pip.web.sys.rbac.dao.UserDao;

@Controller
@RequestMapping("progress")
public class ProgressController {

	@Autowired
	private PipCommDictDistrictMapper pipCommDictDistrictDao;

	@Autowired
	private PipFeeInfoMapper pipFeeInfoDao;

	@Autowired
	private LccDao lccDao;

	@Autowired
	private LccService lccService;

	@Autowired
	private PipCommPatientMapper pipCommPatientMapper;

	@Autowired
	private ReportFactoryViewMapper reportFactoryViewMapper;

	@Autowired
	private UqsHandleViewMapper uqsHandleViewMapper;

	@Autowired
	private MacStatDao macStatDao;;

	@Autowired
	private UserDao userDao;

	@Autowired
	private EhCacheCacheManager cacheManager;

	private Date getWeekStart(String str) {
		try {

			Calendar cal = Calendar.getInstance();
			int year = Integer.parseInt(str.substring(0, 4));
			cal.set(year, Calendar.JANUARY, 1, 0, 0, 0);
			int w = cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DAY_OF_YEAR, (w + 8) % 7);
			int week = Integer.parseInt(str.substring(5, str.length()));
			cal.add(Calendar.DAY_OF_YEAR, week * 7);
			return cal.getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private Date getWeekEnd(String str) {
		try {

			Calendar cal = Calendar.getInstance();
			int year = Integer.parseInt(str.substring(0, 4));
			cal.set(year, Calendar.JANUARY, 1, 0, 0, 0);
			int w = cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DAY_OF_YEAR, (w + 8) % 7);
			int week = Integer.parseInt(str.substring(5, str.length()));
			cal.add(Calendar.DAY_OF_YEAR, (week + 1) * 7);
			return cal.getTime();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@RequestMapping(value = "getProgressList", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	JqgridResponse getProgressList(@RequestParam("type") String type,
			@RequestParam("start") String start,
			@RequestParam("end") String end, @RequestParam("lcc") String lcc) {

		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();

		Map<String, Map<String, Object>> ret = new HashMap<String, Map<String, Object>>();

		String projectId = Securitys.getCurrentProject();
		FeeInfoFilter filter = new FeeInfoFilter();
		filter.setProjectId(projectId);
		try {
			filter.setStart(DateUtils.parseDate(start + " 00:00:00",
					"yyyy-MM-dd HH:mm:ss"));
		} catch (Exception ex) {
		}
		try {
			filter.setEnd(DateUtils.parseDate(end + " 23:59:59",
					"yyyy-MM-dd HH:mm:ss"));
		} catch (Exception ex) {
		}
		if (StringUtils.isNotBlank(lcc))
			filter.setLcc(lcc);
		if (type.equals("day")) {
			List<Map<String, Object>> list = this.pipFeeInfoDao
					.getLccDetailByDay(filter);
			for (Map<String, Object> map : list) {
				if (map.get("NAME") != null && map.get("ST_TYPE") != null
						&& map.get("DATESTR") != null) {
					String lccName = map.get("NAME").toString()
							+ map.get("DATESTR").toString();

					if (ret.get(lccName) == null) {
						ret.put(lccName, new HashMap<String, Object>());
						ret.get(lccName)
								.put("name", map.get("NAME").toString());
						ret.get(lccName).put("code", map.get("LCC_CODE"));
						ret.get(lccName).put("workload", map.get("WORKLOAD"));
						ret.get(lccName).put("date", map.get("DATESTR"));
					}
					ret.get(lccName).put(map.get("ST_TYPE").toString(),
							map.get("VALUE"));
				}
			}

		} else if (type.equals("week")) {
			List<Map<String, Object>> list = this.pipFeeInfoDao
					.getLccDetailByWeek(filter);
			for (Map<String, Object> map : list) {
				if (map.get("NAME") != null && map.get("ST_TYPE") != null
						&& map.get("DATESTR") != null) {
					String lccName = map.get("NAME").toString()
							+ map.get("DATESTR").toString();
					if (ret.get(lccName) == null) {
						ret.put(lccName, new HashMap<String, Object>());
						ret.get(lccName)
								.put("name", map.get("NAME").toString());
						ret.get(lccName).put("code", map.get("LCC_CODE"));
						ret.get(lccName).put("workload", map.get("WORKLOAD"));
						ret.get(lccName).put("date", map.get("DATESTR"));
					}
					ret.get(lccName).put(map.get("ST_TYPE").toString(),
							map.get("VALUE"));
				}
			}
		} else if (type.equals("month")) {
			List<Map<String, Object>> list = this.pipFeeInfoDao
					.getLccDetailByMonth(filter);
			for (Map<String, Object> map : list) {
				if (map.get("NAME") != null && map.get("ST_TYPE") != null
						&& map.get("DATESTR") != null) {
					String lccName = map.get("NAME").toString()
							+ map.get("DATESTR").toString();
					if (ret.get(lccName) == null) {
						ret.put(lccName, new HashMap<String, Object>());
						ret.get(lccName)
								.put("name", map.get("NAME").toString());
						ret.get(lccName).put("code", map.get("LCC_CODE"));
						ret.get(lccName).put("workload", map.get("WORKLOAD"));
						ret.get(lccName).put("date", map.get("DATESTR"));
					}
					ret.get(lccName).put(map.get("ST_TYPE").toString(),
							map.get("VALUE"));
				}
			}
		}
		Iterator<String> it = ret.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();

			PatientFilter pf = new PatientFilter();
			pf.setProjectId(projectId);
			try {
				if (type.equals("day")) {
					pf.setStart(DateUtils.parseDate(ret.get(key).get("date")
							+ " 00:00:00", "yyyy-MM-dd HH:mm:ss"));
				} else if (type.equals("week")) {
					pf.setStart(getWeekStart(ret.get(key).get("date")
							.toString()));
				} else if (type.equals("month")) {
					pf.setStart(DateUtils.parseDate(ret.get(key).get("date")
							+ "-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			try {
				if (type.equals("day")) {
					pf.setEnd(DateUtils.parseDate(ret.get(key).get("date")
							+ " 23:59:59", "yyyy-MM-dd HH:mm:ss"));
				} else if (type.equals("week")) {
					pf.setEnd(getWeekEnd(ret.get(key).get("date").toString()));
				} else if (type.equals("month")) {
					pf.setEnd(DateUtils.parseDate(
							nextMonth(ret.get(key).get("date").toString())
									+ "-01 00:00:00", "yyyy-MM-dd HH:mm:ss"));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			pf.setLccCode(ret.get(key).get("code").toString());

			List<Map<String, Object>> rList = this.pipCommPatientMapper
					.getLccRiskNumByTime(pf);
			if (rList != null && rList.size() > 0 && rList.get(0) != null) {
				ret.get(key).put("risk", rList.get(0).get("VALUE"));
				// ret.get(key).put("order", rList.get(0).get("VALUE")) ;
			} else {
				// ret.get(key).put("risk", new BigDecimal("0")) ;
				// ret.get(key).put("order", new BigDecimal("0")) ;
			}
			List<Map<String, Object>> vList = this.pipCommPatientMapper
					.getLccViewNumByTime(pf);
			if (vList != null && vList.size() > 0 && vList.get(0) != null) {
				// ret.get(key).put("risk", rList.get(0).get("VALUE")) ;
				ret.get(key).put("order", vList.get(0).get("VALUE"));
			}

			ret.get(key).put("csp", divide(ret, key, "2", "workload", 100));
			// 高危对象检出率
			ret.get(key).put("gwp", divide(ret, key, "risk", "2", 100));
			// 高危对象检出完成率
			ret.get(key).put("gwfp", divide(ret, key, "3", "workload", 500));
			// 随访成功率
			ret.get(key).put("sfp", divide(ret, key, "5", "order", 100));

			data.add(ret.get(key));
		}

		JqgridResponse<Map<String, Object>> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(data);
		return response;
	}

	@RequestMapping(value = "getLccDetails2", produces = "application/json")
	public @ResponseBody
	Map getLccDetails2() {
		Map<String, Map> ret = new HashMap<String, Map>();
		Cache cache = this.cacheManager.getCache("dictCache");
		List<Map> list = null;
		if (cache.get("mapForLcc") != null)
			list = (List<Map>) cache.get("mapForLcc").get();
		if (list == null) {
			ProgressFilter filter = new ProgressFilter();
			filter.setRows(100);
			list = this.uqsHandleViewMapper.mapForLcc(filter);
			cache.put("mapForLcc", list);
		}
		if (list != null) {
			for (Map m : list) {
				m.put("name", m.get("LCC_NAME"));
				m.put("value", m.get("UQS2"));
				ret.put("" + m.get("name"), m);
			}
		}
		return ret;
	}

	/**
	 * 初筛完成人数 2 初筛完成率 csp 高危对象检出人数 risk 高危对象检出率 gwp 高危调查完成人数 3 高危对象检出完成率 gwfp
	 * 随访预约人数 order 随访完成人数 4 // 后调整为 5 随访成功率 sfp
	 * */
	@RequestMapping(value = "getLccDetails", produces = "application/json")
	public @ResponseBody
	Map getLccDetails() {
		Map<String, Map<String, Object>> ret = new HashMap<String, Map<String, Object>>();
		String projectId = Securitys.getCurrentProject();

		// lcc FEE信息 st_type 2 : 初筛人数 3：高危完成人数 4 ： 随访完成人数
		List<Map<String, Object>> list = this.pipFeeInfoDao
				.getLccDetail(projectId);
		if (list != null) {
			for (Map<String, Object> map : list) {
				if (map.get("NAME") != null && map.get("ST_TYPE") != null) {
					String lccName = map.get("NAME").toString();
					if (ret.get(lccName) == null) {
						ret.put(lccName, new HashMap<String, Object>());
						ret.get(lccName).put("name", lccName);
						ret.get(lccName).put("code", map.get("LCC_CODE"));
						ret.get(lccName).put("workload", map.get("WORKLOAD"));
					}
					ret.get(lccName).put(map.get("ST_TYPE").toString(),
							map.get("VALUE"));
				}
			}
		}

		// 高危检出人数
		List<Map<String, Object>> rlist = this.pipCommPatientMapper
				.getLccRiskNum(projectId);
		if (rlist != null) {
			for (Map<String, Object> map : rlist) {
				if (map.get("NAME") != null) {
					String lccName = map.get("NAME").toString();
					if (ret.get(lccName) == null) {
						// ret.put(lccName, new HashMap<String,Object>());
						// ret.get(lccName).put("name", lccName);
						// ret.get(lccName).put("code", map.get("LCC_CODE"));
						// ret.get(lccName).put("workload",
						// map.get("WORKLOAD"));
					} else {
						ret.get(lccName).put("risk", map.get("VALUE"));
						// ret.get(lccName).put("order", map.get("VALUE"));
					}
				}
			}
		}
		// 随访预约
		List<Map<String, Object>> vlist = this.pipCommPatientMapper
				.getLccViewNum(projectId);
		if (rlist != null) {
			for (Map<String, Object> map : vlist) {
				if (map.get("NAME") != null) {
					String lccName = map.get("NAME").toString();
					if (ret.get(lccName) == null) {
						// ret.put(lccName, new HashMap<String,Object>());
						// ret.get(lccName).put("name", lccName);
						// ret.get(lccName).put("code", map.get("LCC_CODE"));
						// ret.get(lccName).put("workload",
						// map.get("WORKLOAD"));
					} else {
						// ret.get(lccName).put("risk", map.get("VALUE"));
						ret.get(lccName).put("order", map.get("VALUE"));
					}
				}
			}
		}

		if (ret.keySet().size() > 0) {
			Iterator<String> keys = ret.keySet().iterator();
			while (keys.hasNext()) {
				String lccName = keys.next();
				// 初筛完成率
				ret.get(lccName).put("csp",
						divide(ret, lccName, "2", "workload", 100));
				// 高危对象检出率
				ret.get(lccName).put("gwp",
						divide(ret, lccName, "risk", "2", 100));
				// 高危对象检出完成率
				ret.get(lccName).put("gwfp",
						divide(ret, lccName, "3", "workload", 500));
				// 随访成功率
				ret.get(lccName).put("sfp",
						divide(ret, lccName, "5", "order", 100));

				// 默认初筛
				ret.get(lccName).put("value", ret.get(lccName).get("2"));
			}
		}
		return ret;
	}

	private String nextMonth(String str) {
		StringBuffer sb = new StringBuffer();
		if (str != null && str.length() == 7 && str.indexOf("-") == 4) {
			if (str.substring(5).equals("12")) {
				sb.append(Integer.parseInt(str.substring(0, 4) + 1));
			} else {
				sb.append(str.substring(0, 4));
			}
			sb.append("-");
			if (str.substring(5).equals("12")) {
				sb.append("01");
			} else {
				int m = Integer.parseInt(str.substring(5));
				m++;
				String ms = "00" + m;
				ms = ms.substring(ms.length() - 2, ms.length());
				sb.append(ms);
			}
		}
		return sb.toString();
	}

	private int divide(Map<String, Map<String, Object>> ret, String key,
			String n1, String n2, int n) {
		MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
		if (ret != null && ret.get(key) != null && ret.get(key).get(n1) != null
				&& ret.get(key).get(n2) != null) {
			try {
				return ((BigDecimal) ret.get(key).get(n1))
						.multiply(new BigDecimal("" + n), mc)
						.divide((BigDecimal) ret.get(key).get(n2), mc)
						.intValue();
			} catch (Exception ex) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	private int divide(Map<String, Object> map, String n1, String n2, int n) {
		if (map.get(n2) != null && ((BigDecimal) map.get(n2)).intValue() == 0)
			return 0;

		MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
		if (map != null && map.get(n1) != null && map.get(n2) != null) {
			try {
				return ((BigDecimal) map.get(n1))
						.multiply(new BigDecimal("" + n), mc)
						.divide((BigDecimal) map.get(n2), mc).intValue();
			} catch (Exception ex) {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * update by dinglin 采用统一的数据视图作为数据来源
	 * */
	@RequestMapping(value = "getLccData2", produces = "application/json")
	public @ResponseBody
	Map getLccData2(@RequestParam("mt") String mt) {
		Map<String, Object> ret = new HashMap<String, Object>();
		Cache cache = this.cacheManager.getCache("dictCache");
		if (mt.equals("china")) {
			List<Map> list = null;
			if (cache.get("mapForChina") != null)
				list = (List<Map>) cache.get("mapForChina").get();
			if (list == null) {
				list = this.uqsHandleViewMapper
						.mapForChina(new ProgressFilter());
				cache.put("mapForChina", list);
			}
			if (list != null && list.size() == 1)
				ret = list.get(0);
		} else {
			List<Map> list = null;
			if (cache.get("mapForArea") != null)
				list = (List<Map>) cache.get("mapForArea").get();
			if (list == null) {
				ProgressFilter filter = new ProgressFilter();
				filter.setRows(100);
				list = this.uqsHandleViewMapper.mapForArea(filter);
				cache.put("mapForArea", list);
			}
			for (Map m : list) {
				if (m.get("AREA_NAME").equals(mt)) {
					ret = m;
					break;
				}
			}
		}
		return ret;
	}

	@RequestMapping(value = "getLccData", produces = "application/json")
	public @ResponseBody
	Map getLccData(@RequestParam("mt") String mt) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String projectId = Securitys.getCurrentProject();
		MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
		// 工作量
		BigDecimal w = new BigDecimal("0");
		List<Map<String, Object>> wl;

		// 高危检出人数
		List<Map<String, Object>> rlist = this.pipCommPatientMapper
				.getAreaRiskNum(projectId);
		// 高危预约人数
		List<Map<String, Object>> olist = this.pipCommPatientMapper
				.getAreaRiskOrderNum(projectId);
		// 随访预约人数
		List<Map<String, Object>> vlist = this.pipCommPatientMapper
				.getAreaFollowViewNum(projectId);

		BigDecimal riskNum = new BigDecimal("0");
		BigDecimal orderNum = new BigDecimal("0");
		BigDecimal viewNum = new BigDecimal("0");

		if (mt.equals("china")) {
			List<Map<String, Object>> list = this.pipFeeInfoDao
					.getChinaData(projectId);
			if (list != null) {
				for (Map<String, Object> map : list) {
					ret.put(map.get("ST_TYPE").toString(), map.get("VALUE"));
				}
			}
			wl = this.lccDao.getWorkload(projectId, null);
			if (rlist != null) {
				for (Map<String, Object> map : rlist) {
					if (map.get("VALUE") != null) {
						riskNum = riskNum
								.add((BigDecimal) map.get("VALUE"), mc);
					}
				}
			}
			if (olist != null) {
				for (Map<String, Object> map : olist) {
					if (map.get("VALUE") != null) {
						orderNum = orderNum.add((BigDecimal) map.get("VALUE"),
								mc);
					}
				}
			}
			if (vlist != null) {
				for (Map<String, Object> map : vlist) {
					if (map.get("VALUE") != null) {
						viewNum = viewNum
								.add((BigDecimal) map.get("VALUE"), mc);
					}
				}
			}

		} else {
			List<Map<String, Object>> list = this.pipFeeInfoDao.getMapData(
					projectId, null);
			if (list != null) {
				for (Map<String, Object> map : list) {
					if (formatAreaName(map.get("NAME")).equals(mt)) {
						ret.put(map.get("ST_TYPE").toString(), map.get("VALUE"));
					}
				}
			}
			wl = this.lccDao.getWorkload(projectId, mt);
			if (rlist != null) {
				for (Map<String, Object> map : rlist) {
					if (formatAreaName(map.get("NAME")).equals(mt)) {
						riskNum = (BigDecimal) map.get("VALUE");
					}
				}
			}
			if (olist != null) {
				for (Map<String, Object> map : olist) {
					if (formatAreaName(map.get("NAME")).equals(mt)) {
						orderNum = (BigDecimal) map.get("VALUE");
					}
				}
			}
			if (vlist != null) {
				for (Map<String, Object> map : vlist) {
					if (formatAreaName(map.get("NAME")).equals(mt)) {
						viewNum = (BigDecimal) map.get("VALUE");
					}
				}
			}
		}
		if (wl != null && wl.get(0) != null) {
			w = (BigDecimal) wl.get(0).get("WORKLOAD");
			ret.put("workload", w);
		}

		ret.put("risk", riskNum);

		ret.put("order", viewNum);

		// 初筛完成率
		ret.put("csp", this.divide(ret, "2", "workload", 100));

		// 高危完成率
		ret.put("gwfp", this.divide(ret, "3", "workload", 500));

		// 高危随访率
		ret.put("per4", this.divide(ret, "5", "3", 100));

		// 随访成功率
		ret.put("sfp", this.divide(ret, "5", "order", 100));

		// 高危对象检出率
		ret.put("gwp", this.divide(ret, "risk", "2", 100));

		return ret;
	}

	/**
	 * update by dinglin 采用统一的数据视图作为数据来源
	 * */
	@RequestMapping(value = "getProgressData2", produces = "application/json")
	public @ResponseBody
	List getProgressData2(@RequestParam("type") String type) {
		List<Map> ret = new ArrayList<Map>();
		Cache cache = this.cacheManager.getCache("dictCache");
		List<Map> list = null;
		if (cache.get("mapForArea") != null)
			list = (List<Map>) cache.get("mapForArea").get();
		if (list == null) {
			ProgressFilter filter = new ProgressFilter();
			filter.setRows(100);
			list = this.uqsHandleViewMapper.mapForArea(filter);
			cache.put("mapForArea", list);
		}
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).put("name", list.get(i).get("AREA_NAME"));
				list.get(i).put("value", list.get(i).get("UQS2"));
			}
			ret = list;
		}
		return ret;
	}

	@RequestMapping(value = "getProgressData", produces = "application/json")
	public @ResponseBody
	List getProgressData(@RequestParam("type") String type) {

		List<Map<String, Object>> ret = new ArrayList<Map<String, Object>>();
		/*
		 * PipCommDictDistrictExample example = new
		 * PipCommDictDistrictExample(); List<PipCommDictDistrict> dlist =
		 * this.pipCommDictDistrictDao.selectByExample(example);
		 * Map<String,PipCommDictDistrict> dmap = new
		 * HashMap<String,PipCommDictDistrict>(); if ( dlist != null ){ for (
		 * PipCommDictDistrict d : dlist ){ dmap.put(d.getDictCode(), d); } }
		 */
		MathContext mc = new MathContext(4, RoundingMode.HALF_DOWN);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String projectId = Securitys.getCurrentProject();

		if (type.equals("1")) {
			// 初筛
			list.addAll(this.pipFeeInfoDao.getMapData(projectId, null));
			for (Map<String, Object> m : list) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// list.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, null));
			// 初筛人数 stType = 2 高危人数 stType = 3 高危随访人数 stType = 4
			for (Map<String, Object> map : list) {
				if (map.get("ST_TYPE").equals("2")) {
					map.put("name", map.get("NAME"));
					map.put("value", map.get("VALUE"));
					ret.add(map);
				}
			}
			for (Map<String, Object> map : list) {
				if (!map.get("ST_TYPE").equals("2")) {
					for (Map<String, Object> m : ret) {
						if (m.get("NAME").equals(map.get("NAME"))) {
							m.put(map.get("ST_TYPE").toString(),
									map.get("VALUE"));
						}
					}
				}
			}
		} else if (type.equals("2")) {
			// 高危
			list.addAll(this.pipFeeInfoDao.getMapData(projectId, "3"));
			for (Map<String, Object> m : list) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// list.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, "3"));
			// 初筛人数 stType = 2 高危人数 stType = 3 高危随访人数 stType = 4
			for (Map<String, Object> map : list) {
				map.put("name", map.get("NAME"));
				map.put("value", map.get("VALUE"));
				ret.add(map);
			}
		} else if (type.equals("3")) {
			// 随访
			list.addAll(this.pipFeeInfoDao.getMapData(projectId, "5"));
			for (Map<String, Object> m : list) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// list.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, "4"));
			// 初筛人数 stType = 2 高危人数 stType = 3 高危随访人数 stType = 4
			for (Map<String, Object> map : list) {
				map.put("name", map.get("NAME"));
				map.put("value", map.get("VALUE"));
				ret.add(map);
			}
		} else if (type.equals("4")) {
			// 初筛百分比
			list.addAll(this.pipFeeInfoDao.getMapData(projectId, "2"));
			for (Map<String, Object> m : list) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// list.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, "2"));
			for (Map<String, Object> map : list) {
				map.put("name", map.get("NAME"));
				map.put("value", ((BigDecimal) map.get("PERCENT")).intValue());
				ret.add(map);
			}
		} else if (type.equals("5")) {
			// 高危对象检出完成率
			list.addAll(this.pipFeeInfoDao.getMapData(projectId, "3"));
			for (Map<String, Object> m : list) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// list.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, "3"));
			for (Map<String, Object> map : list) {
				map.put("name", map.get("NAME"));
				map.put("value", this.divide(map, "VALUE", "WORKLOAD", 500));
				ret.add(map);
			}
		} else if (type.equals("6")) {
			// 高危随访百分比 stType = 3;
			list.addAll(this.pipFeeInfoDao.getMapData(projectId, "3"));
			for (Map<String, Object> m : list) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// list.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, "3"));
			List<Map<String, Object>> sfs = new ArrayList<Map<String, Object>>();
			sfs.addAll(this.pipFeeInfoDao.getMapData(projectId, "5"));
			for (Map<String, Object> m : sfs) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// sfs.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, "4"));
			for (Map<String, Object> map : list) {
				for (Map<String, Object> mf : sfs) {
					if (map.get("NAME").equals(mf.get("NAME"))) {
						map.put("name", map.get("NAME"));
						Double num = 0.0;
						if (map.get("VALUE") != null
								&& ((BigDecimal) map.get("VALUE")).intValue() != 0)
							num = ((BigDecimal) mf.get("VALUE")).divide(
									((BigDecimal) map.get("VALUE")), mc)
									.doubleValue() * 100;
						map.put("value", num.intValue());
						ret.add(map);
					}
				}
			}
		} else if (type.equals("7")) {
			// 高危对象检出人数 / 随访预约人数
			List<Map<String, Object>> rlist = this.pipCommPatientMapper
					.getAreaRiskNum(projectId);
			if (rlist != null) {
				for (Map<String, Object> map : rlist) {
					map.put("name", formatAreaName(map.get("NAME")));
					map.put("value", ((BigDecimal) map.get("VALUE")).intValue());
					ret.add(map);
				}
			}

		} else if (type.equals("8")) {
			// 随访成功率
			List<Map<String, Object>> sfs = new ArrayList<Map<String, Object>>();
			sfs.addAll(this.pipFeeInfoDao.getMapData(projectId, "5"));
			for (Map<String, Object> m : sfs) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// sfs.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, "4"));
			List<Map<String, Object>> rlist = this.pipCommPatientMapper
					.getAreaRiskNum(projectId);
			if (rlist != null) {
				for (Map<String, Object> map : rlist) {
					for (Map<String, Object> m : sfs) {
						if (m.get("NAME").equals(
								formatAreaName(map.get("NAME")))) {
							map.put("name", map.get("NAME"));
							Double num = 0.0;
							if (map.get("VALUE") != null
									&& ((BigDecimal) map.get("VALUE"))
											.intValue() != 0)
								num = ((BigDecimal) m.get("VALUE")).divide(
										((BigDecimal) map.get("VALUE")), mc)
										.doubleValue() * 100;
							map.put("value", num.intValue());
							ret.add(map);
						}
					}
				}
			}

		} else if (type.equals("9")) {
			// 高危对象检出率
			List<Map<String, Object>> sfs = new ArrayList<Map<String, Object>>();
			sfs.addAll(this.pipFeeInfoDao.getMapData(projectId, "2"));
			for (Map<String, Object> m : sfs) {
				m.put("NAME", formatAreaName(m.get("NAME")));
			}
			// sfs.addAll(this.pipFeeInfoDao.getMapDataCity(projectId, "4"));
			List<Map<String, Object>> rlist = this.pipCommPatientMapper
					.getAreaRiskNum(projectId);
			if (rlist != null) {
				for (Map<String, Object> map : rlist) {
					for (Map<String, Object> m : sfs) {
						if (m.get("NAME").equals(
								formatAreaName(map.get("NAME")))) {
							map.put("name", map.get("NAME"));
							Double num = 0.0;
							if (map.get("VALUE") != null
									&& ((BigDecimal) map.get("VALUE"))
											.intValue() != 0)
								num = ((BigDecimal) map.get("VALUE")).divide(
										((BigDecimal) m.get("VALUE")), mc)
										.doubleValue() * 100;
							map.put("value", num.intValue());
							ret.add(map);
						}
					}
				}
			}

		}
		return ret;
	}

	private String formatAreaName(Object name) {
		if (name == null)
			return "";
		return name.toString().replace("省", "").replace("市", "")
				.replace("自治区", "").replace("壮族", "").replace("藏族", "")
				.replace("回族", "").replace("维吾尔", "");
	}

	@RequestMapping(value = "map", method = RequestMethod.GET)
	public String map(Model model) {
		return "progress/map";
	}

	@RequestMapping(value = "map2", method = RequestMethod.GET)
	public String map2(Model model) {
		return "progress/map2";
	}

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("lccDictList", lccService.getAllActiveLcc());
		return "progress/list2";
	}

	@RequestMapping(value = "tube", method = RequestMethod.GET)
	public String tube(Model model) {
		model.addAttribute("lccDictList", lccService.getAllActiveLcc());
		return "progress/tubelist";
	}

	@RequestMapping(value = "trend", method = RequestMethod.GET)
	public String trend(Model model) {
		model.addAttribute("lccDictList", lccService.getAllActiveLcc());
		return "progress/list2";
	}

	@RequestMapping(value = "topatientlistpage", method = RequestMethod.GET)
	public ModelAndView init(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("progress/patientlist");
		mav.addObject("lccName", request.getParameter("lccName"));
		mav.addObject("type", request.getParameter("type"));
		return mav;
	}

	@RequestMapping(value = "getpatientlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<PipCommPatient> getAllMaterNames(PatientFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		if ("全国".equals(filter.getLccName())) {
			filter.setLccName(null);
		}
		List<PipCommPatient> list = pipCommPatientMapper
				.getLccPatientList(filter);
		JqgridResponse<PipCommPatient> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}

	@RequestMapping(value = "getProgressByCondit", produces = "application/json")
	public @ResponseBody
	Map getProgressByCondit(ProgressFilter filter) {

		if (filter.getLccCode() != null && filter.getLccCode().equals(""))
			filter.setLccCode(null);
		if (filter.getArea_code() != null && filter.getArea_code().equals(""))
			filter.setArea_code(null);
		List days = filter.getDays_();
		List valus = new ArrayList();
		List num = new ArrayList();
		filter.setProjectId(Securitys.getCurrentProject());
		filter.setRows(500);

		if (filter.getType() != null) {
			List<Map> result = null;
			if (filter.getType().equals("001001")) {
				result = this.uqsHandleViewMapper.uqs1Trend(filter);
			} else if (filter.getType().equals("001002")) {
				result = this.uqsHandleViewMapper.uqs2Trend(filter);
			} else if (filter.getType().equals("001003,001004")) {
				result = this.uqsHandleViewMapper.uqs3Trend(filter);
			} else if (filter.getType().equals("001005")) {
				result = this.uqsHandleViewMapper.uqs5Trend(filter);
			} else if (filter.getType().equals("001006")) {
				result = this.uqsHandleViewMapper.uqs6Trend(filter);
			} else if (filter.getType().equals("num5")) {
				result = this.uqsHandleViewMapper.riskTrend(filter);
			} else if (filter.getType().equals("num6")) {
				result = this.uqsHandleViewMapper.sampleTrend(filter);
			} else if (filter.getType().equals("num7")) {
				result = this.uqsHandleViewMapper.ecgTrend(filter);
			} else if (filter.getType().equals("num8")) {
				result = this.uqsHandleViewMapper.cchTrend(filter);
			} else if (filter.getType().equals("num9")) {
				result = this.uqsHandleViewMapper.ecgTrend(filter);
			} else if (filter.getType().equals("tube")) {
				result = this.uqsHandleViewMapper.tubeTrend(filter);
			}

			if (result != null && result.size() > 0) {

				// 补齐
				List<Map> list = new ArrayList<Map>();
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				try {
					Date dd0 = df.parse(result.get(0).get("ITEM_DATE")
							.toString());
					Date dd1 = df.parse(days.get(0).toString());

					if (dd1.getTime() < dd0.getTime()) {
						long n = (dd0.getTime() - dd1.getTime())
								/ (24L * 3600 * 1000);
						for (int i = 0; i < n; i++) {
							Map tm = new HashMap();
							Date td = new Date();
							td.setTime(dd1.getTime() + 24L * 3600 * 1000 * i);
							tm.put("ITEM_DATE", df.format(td));
							tm.put("NUM", new BigDecimal("0"));
							list.add(tm);
						}
					}

				} catch (Exception ex) {

				}

				for (int i = 0; i < result.size(); i++) {
					list.add(result.get(i));
					if (i < (result.size() - 1)) {
						try {
							Date d0 = df.parse(result.get(i).get("ITEM_DATE")
									.toString());
							Date d1 = df.parse(result.get(i + 1)
									.get("ITEM_DATE").toString());
							long n = (d1.getTime() - d0.getTime())
									/ (24L * 3600 * 1000);
							if (n > 1) {
								for (int j = 1; j < n; j++) {
									Map tm = new HashMap();
									Date td = new Date();
									td.setTime(d0.getTime() + 24L * 3600 * 1000
											* j);
									tm.put("ITEM_DATE", df.format(td));
									tm.put("NUM", new BigDecimal("0"));
									list.add(tm);
								}
							}
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}

				int s = 0;
				for (Map map : list) {
					s += ((BigDecimal) map.get("NUM")).intValue();
					for (Object day : days) {
						if (day.equals(map.get("ITEM_DATE"))) {
							num.add(((BigDecimal) map.get("NUM")).toString());
							valus.add(s);
						}
					}
				}
				// 补齐
				if (valus.size() < days.size()) {
					int nn = days.size() - valus.size();
					Object nc = valus.get(valus.size() - 1);
					for (int i = 0; i < nn; i++) {
						num.add("0");
						valus.add(nc);
					}
				}
			} else {
				for (Object day : days) {
					num.add("0");
					valus.add("0");
				}
			}
		}
		Map map = new HashMap();
		map.put("days", days);
		map.put("valus", valus);
		map.put("nums", num);
		return map;
	}

	/**
	 * @deprecated
	 * */
	@RequestMapping(value = "getProgressByCondit2", produces = "application/json")
	public @ResponseBody
	Map getProgressByCondit2(ProgressFilter filter) {
		Date dd = new Date();
		dd.setTime(filter.getStart().getTime() - 24L * 3600 * 1000);
		filter.setStart(dd);
		List days = filter.getDays_();
		List valus = new ArrayList();
		List num = new ArrayList();
		filter.setProjectId(Securitys.getCurrentProject());

		processCondition(filter);

		Map data = new HashMap();
		if (filter.getType().indexOf("num") >= 0) {
			// 按照杨总的设计 通用的进度管理 稳定后再全部替换
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
			ReportFactoryViewExample example = new ReportFactoryViewExample();
			if (filter.getArea_code() != null
					&& !filter.getArea_code().equals("")) {
				example.createCriteria()
						.andProjectIdEqualTo(filter.getProjectId())
						.andLccCodeLike(filter.getArea_code() + "%");
			} else if (filter.getLccCode() != null
					&& !filter.getLccCode().equals("")) {
				example.createCriteria()
						.andProjectIdEqualTo(filter.getProjectId())
						.andLccCodeEqualTo(filter.getLccCode());
			} else {
				example.createCriteria().andProjectIdEqualTo(
						filter.getProjectId());
			}
			/*
			 * if ( filter.getStart() != null ){ example.createCriteria()
			 * .andItemDateGreaterThanOrEqualTo(sf.format(filter.getStart())); }
			 * if ( filter.getEnd() != null ){ example.createCriteria()
			 * .andItemDateLessThanOrEqualTo(sf.format(filter.getEnd())); }
			 */
			List<Lcc> lccs = lccService.getAllActiveLcc();

			example.setOrderByClause(" ITEM_DATE ASC ");
			List<ReportFactoryView> ls = this.reportFactoryViewMapper
					.selectByExample(example);
			if (ls != null) {
				Map<String, String> m = new HashMap<String, String>();
				Map<String, String> mv = new HashMap<String, String>();
				Object tmp = "0";
				for (ReportFactoryView v : ls) {
					Object o = ReflectionUtils.getFieldValue(v,
							filter.getType());
					if (o == null)
						o = "0";
					m.put(v.getLccCode() + "|" + v.getItemDate(), o.toString());
				}

				for (ReportFactoryView v : ls) {
					Object vo = ReflectionUtils.getFieldValue(
							v,
							"var"
									+ filter.getType().substring(3,
											filter.getType().length()));
					if (vo == null)
						vo = "0";
					if (mv.get(v.getItemDate()) == null) {
						mv.put(v.getItemDate(), "0");
					}
					mv.put(v.getItemDate(),
							""
									+ (Integer.parseInt(vo.toString()) + Integer
											.parseInt(mv.get(v.getItemDate()))));
				}
				/*
				 * for ( ReportFactoryView v : ls ){ Object o =
				 * ReflectionUtils.getFieldValue(v, filter.getType()); if ( o ==
				 * null ) o = "0"; if ( Integer.parseInt(v.getItemDate()) <=
				 * Integer.parseInt( days.get(0).toString().replaceAll("-", ""))
				 * ) tmp = o; if ( m.get(v.getItemDate()) == null )
				 * m.put(v.getItemDate(), "0"); m.put(v.getItemDate(),
				 * ""+(Integer.parseInt(m.get(v.getItemDate())) +
				 * Integer.parseInt(o.toString())) ); }
				 */
				// 补齐缺失行
				Object td = null;
				for (Object d : days) {
					for (Lcc lcc : lccs) {
						if (m.get(lcc.getLccCode() + "|"
								+ d.toString().replaceAll("-", "")) == null) {
							if (td == null) {
								m.put(lcc.getLccCode() + "|"
										+ d.toString().replaceAll("-", ""), "0");
							} else {
								m.put(lcc.getLccCode() + "|"
										+ d.toString().replaceAll("-", ""),
										m.get(lcc.getLccCode()
												+ "|"
												+ td.toString().replaceAll("-",
														"")));
							}
						}
					}
					td = d;
				}
				// 汇总
				Map<String, String> mm = new HashMap<String, String>();
				for (Object d : days) {
					if (mm.get(d.toString().replaceAll("-", "")) == null) {
						mm.put(d.toString().replaceAll("-", ""), "0");
					}
					for (String mk : m.keySet()) {
						if (mk.indexOf(d.toString().replaceAll("-", "")) >= 0) {
							mm.put(d.toString().replaceAll("-", ""),
									""
											+ (Integer.parseInt(m.get(mk)) + Integer.parseInt(mm
													.get(d.toString()
															.replaceAll("-", "")))));
						}
					}
				}

				for (Object d : days) {
					if (mm.get(d.toString().replaceAll("-", "")) != null) {
						tmp = mm.get(d.toString().replaceAll("-", ""));
					}
					valus.add(tmp);
					if (mv.get(d.toString().replaceAll("-", "")) != null) {
						num.add(mv.get(d.toString().replaceAll("-", "")));
					} else {
						num.add("0");
					}
				}

			}
			days.remove(0);
			valus.remove(0);
			num.remove(0);
		} else {
			// 之前春雨的计算 算法暂时不动

			data = this.pipCommPatientMapper.getProgressByCondit(filter);
			if (data == null)
				data = new HashMap();

			// String beforeValue = "0";
			for (int i = 0; i < days.size(); i++) {
				String value = (data.get("X" + i) == null) ? "0" : data.get(
						"X" + i).toString();
				/*
				 * if(! "0".equals(value)) { beforeValue = value; }else{ value =
				 * beforeValue; }
				 */
				valus.add(value);
				if (i > 0)
					num.add(Integer.parseInt(value)
							- Integer.parseInt("" + valus.get(i - 1)));
			}

			days.remove(0);
			valus.remove(0);
		}

		// 返回的数据结构 很奇怪 为了不修改前端代码 暂时沿用

		Map map = new HashMap();
		map.put("days", days);
		map.put("valus", valus);
		map.put("nums", num);

		return map;
	}

	private void processCondition(ProgressFilter filter) {
		if (StringUtils.isBlank(filter.getType()))
			return;
		// String types[] = filter.getType().split(",");

		/*
		 * StringBuffer sb = new StringBuffer();
		 * 
		 * int i=0; for(String type:types){ if(StringUtils.isBlank(type))
		 * continue; if(i==0 ) { sb.append("(   )"); }else{
		 * sb.insert(sb.length() - 1, " , "); } sb.insert(sb.length() - 1,
		 * type); i++; } filter.setType(sb.toString() );
		 */
	}

	// -----------------------------------

	@RequestMapping(value = "reportUqs", method = RequestMethod.GET)
	public String reportUqs(Model model) {
		return "progress/reportUqsForLcc";
	}

	@RequestMapping(value = "reportUqslist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map> reportUqslist(ProgressFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		List<Map<String, String>> dlist = this.userDao
				.getDataLimitByUserId(Securitys.getUser().getId());

		if (dlist != null) {
			for (Map m : dlist) {
				filter.getDatalimits().add("" + m.get("LCC_CODE"));
			}
		}

		List<Map> list = this.uqsHandleViewMapper.reportUqsForLcc(filter);
		JqgridResponse<Map> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping(value = "reportProgress", method = RequestMethod.GET)
	public String reportProgress(Model model) {
		return "progress/reportProgressForLcc";
	}

	@RequestMapping(value = "reportProgresslist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map> reportProgresslist(ProgressFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		List<Map<String, String>> dlist = this.userDao
				.getDataLimitByUserId(Securitys.getUser().getId());

		if (dlist != null) {
			for (Map m : dlist) {
				filter.getDatalimits().add("" + m.get("LCC_CODE"));
			}
		}

		List<Map> list = this.uqsHandleViewMapper.reportProgressForLcc(filter);
		JqgridResponse<Map> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping(value = "reportCompleteness", method = RequestMethod.GET)
	public String reportCompleteness(Model model) {
		return "progress/reportCompletenessForLcc";
	}

	@RequestMapping(value = "reportCompletenesslist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map> reportCompletenesslist(ProgressFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		List<Map<String, String>> dlist = this.userDao
				.getDataLimitByUserId(Securitys.getUser().getId());

		if (dlist != null) {
			for (Map m : dlist) {
				filter.getDatalimits().add("" + m.get("LCC_CODE"));
			}
		}

		List<Map> list = this.uqsHandleViewMapper
				.reportCompletenessForLcc(filter);
		JqgridResponse<Map> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping(value = "reportList", method = RequestMethod.GET)
	public String reportList(Model model) {
		return "progress/patientReportList";
	}

	@RequestMapping(value = "patientList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<Map> patientList(ProgressFilter filter) {

		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		List<Map> list = null;
		if (filter.getType() != null) {
			if (filter.getType().equals("UQS0")) {
				list = this.uqsHandleViewMapper.listUqs0(filter);
			} else if (filter.getType().equals("UQS1")) {
				list = this.uqsHandleViewMapper.listUqs1(filter);
			} else if (filter.getType().equals("UQS2")) {
				list = this.uqsHandleViewMapper.listUqs2(filter);
			} else if (filter.getType().equals("UQS3")) {
				list = this.uqsHandleViewMapper.listUqs3(filter);
			} else if (filter.getType().equals("UQS4")) {
				list = this.uqsHandleViewMapper.listUqs4(filter);
			} else if (filter.getType().equals("UQS34")) {
				list = this.uqsHandleViewMapper.listUqs4(filter);
			} else if (filter.getType().equals("UQS5")) {
				list = this.uqsHandleViewMapper.listUqs5(filter);
			} else if (filter.getType().equals("UQS6")) {
				list = this.uqsHandleViewMapper.listUqs6(filter);
			} else if (filter.getType().equals("RISK")) {
				list = this.uqsHandleViewMapper.listRisk(filter);
			}
		}
		JqgridResponse<Map> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}

	@RequestMapping("/exportexcel")
	public ModelAndView exportexcel(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;

		String ctxPath = request.getSession().getServletContext()
				.getRealPath("/")
				+ File.separator
				+ "WEB-INF"
				+ File.separator
				+ "views"
				+ File.separator
				+ "progress"
				+ File.separator
				+ "assets"
				+ File.separator;
		String tempName = request.getParameter("template");
		ProgressFilter filter = new ProgressFilter();
		filter.setRows(100);
		String st = request.getParameter("start");
		if (StringUtils.isNotBlank(st)) {
			st += " 00:00:00";
			filter.setStart(DateUtil.parse(st));
		}
		String et = request.getParameter("end");
		if (StringUtils.isNotBlank(st)) {
			et += " 23:59:59";
			filter.setEnd(DateUtil.parse(et));
		}
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		if (StringUtils.isNotBlank(request.getParameter("areacode")))
			filter.setArea_code(request.getParameter("areacode"));
		if (tempName == null)
			tempName = "completeness";
		String template = ctxPath + tempName + ".xls";
		String datafile = ctxPath + "DataReport" + System.currentTimeMillis()
				+ ".xls";
		String dfName = "";
		XLSTransformer transformer = new XLSTransformer();
		try {
			response.setContentType("application/x-msdownload;");

			// response.setHeader("Content-Length", String.valueOf(fileLength));

			List<Map<String, String>> dlist = this.userDao
					.getDataLimitByUserId(Securitys.getUser().getId());

			if (dlist != null) {
				for (Map m : dlist) {
					filter.getDatalimits().add("" + m.get("LCC_CODE"));
				}
			}

			List<Map> list = null;
			if (tempName.equals("completeness")) {
				dfName = "CompletenessReport_" + System.currentTimeMillis()
						+ ".xls";
				list = this.uqsHandleViewMapper
						.reportCompletenessForLcc(filter);
			} else if (tempName.equals("uqs")) {
				// dfName = "UQSReport_"+System.currentTimeMillis() + ".xls";
				// 统计模板
				dfName = "上传文件统计.xls";
				list = this.uqsHandleViewMapper.reportUqsForLcc(filter);
			} else if (tempName.equals("progress")) {
				dfName = "ProgressReport_" + System.currentTimeMillis()
						+ ".xls";
				list = this.uqsHandleViewMapper.reportProgressForLcc(filter);
			} else if (tempName.equals("export")) {
				String lccCode = request.getParameter("lcc");
				dfName = lccCode + "_" + System.currentTimeMillis() + ".xls";
				list = this.uqsHandleViewMapper.exportForLcc(lccCode);
			}

			response.setHeader("Content-disposition", "attachment; filename="
					+ new String(dfName.getBytes("utf-8"), "ISO8859-1"));
			/*
			 * if ( list != null && list.size() > 0 ){ for ( int i = 0 ; i <
			 * list.size() ; i ++ ){ Set ks = list.get(i).keySet(); List keys =
			 * new ArrayList(); for ( Object key : ks ){ keys.add(key); } for (
			 * Object key : keys ){ if ( list.get(i).get(key) instanceof Date){
			 * list.get(i).put(key + "_DATE",
			 * DateUtil.formatDateLong((Date)list.get(i).get(key))); }else if (
			 * list.get(i).get(key) instanceof Timestamp){ Timestamp tt =
			 * (Timestamp)list.get(i).get(key); Date d = new Date();
			 * d.setTime(tt.getTime()); list.get(i).put(key + "_DATE",
			 * DateUtil.formatDateLong(d)); } } } }
			 */

			Map beans = new HashMap();
			beans.put("resultList", list);
			transformer.transformXLS(template, beans, datafile);

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
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}

	/**
	 * 上传信息统计
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "uploadMacStat", method = RequestMethod.GET)
	public String uploadMacStat(Model model) {
		return "progress/uploadMacStat";
	}

	@RequestMapping(value = "getMacStatList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JqgridResponse<MacStat> getMacStatList(ProgressFilter filter) {
		List<MacStat> list = this.macStatDao.getMacStats(filter);
		JqgridResponse<MacStat> response = JqgridResponseContext
				.getJqgridResponse();
		response.setRows(list);
		return response;
	}
}
