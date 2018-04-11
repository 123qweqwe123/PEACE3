/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.material.supp.service.impl 
 */
/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.web.material.supp.service.impl  
 */

package com.bdcor.pip.web.material.supp.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.PipMmsImscmdetalMapper;
import com.bdcor.pip.web.material.supp.dao.PipMmsImscmmasterMapper;
import com.bdcor.pip.web.material.supp.dao.PipMmsScmarchivesMapper;
import com.bdcor.pip.web.material.supp.dao.PipMmsScmstockMapper;
import com.bdcor.pip.web.material.supp.domain.BooldExcelVo;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsImscmmaster;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchives;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmstock;
import com.bdcor.pip.web.material.supp.filter.PipMmsImscmmasterFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsImscmmasterService;

/**
 * description:编号物资入库单表
 * 
 */
@Service
@Transactional
public class PipMmsImscmmasterServiceImpl implements PipMmsImscmmasterService {

	@Autowired
	PipMmsImscmmasterMapper mapper;

	@Autowired
	PipMmsImscmdetalMapper pipMmsImscmdetalMapper;

	@Autowired
	PipMmsScmstockMapper pipMmsScmstockMapper;

	@Autowired
	PipMmsScmarchivesMapper pipMmsScmarchivesMapper;

	/**
	 * 根据条件查询查询.
	 * 
	 * @see com.bdcor.pip.web.material.supp.service.PipMmsImscmmasterService#list(com.bdcor.pip.web.material.supp.filter.PipMmsImscmmasterFilter)
	 */
	@Override
	public List<PipMmsImscmmaster> list(PipMmsImscmmasterFilter filter) {
		filter.setUserId(Securitys.getUserId());
		return mapper.selectByFilter(filter);
	}

	@Override
	public List<PipMmsImscmmaster> list() {
		List<PipMmsImscmmaster> list = new ArrayList<PipMmsImscmmaster>();
		PipMmsImscmmaster selectByPrimaryKey = mapper
				.selectByPrimaryKey((short) 1);
		list.add(selectByPrimaryKey);
		return list;
	}

	@Override
	public void insert(PipMmsImscmmaster pmi) {

		// 查询箱是否已经保存过此订单 存储
		int count = mapper.insertIfExit(pmi);
		if (count < 1) {
			mapper.insert(pmi);
		}

	}

	public void insertImscmdetalAndStockAndScmarchivesAndImscmmaster(
			Set<String> setBox, List<BooldExcelVo> excleList, String imorderNo,
			String stockCode, String stockName) {

		// 第一步 保存每条商品包 PIP_MMS_SCMARCHIVES
		PipMmsScmarchives pms = null;
		for (BooldExcelVo bev : excleList) {
			pms = getPipMmsScmarchives(bev, stockCode);
			pipMmsScmarchivesMapper.insert(pms);
		}

		// 第一步保存入库 PIP_MMS_IMSCMDETAL
		PipMmsImscmdetal pmi = null;
		for (String box : setBox) {
			pmi = getPipMmsImscmdetals(box, excleList, imorderNo);
			pipMmsImscmdetalMapper.insert(pmi);
		}

		// 第二步保存 库存 PIP_MMS_SCM STOCK
		PipMmsScmstock ps = null;
		for (String box : setBox) {
			ps = getPipMmsScmstocks(box, excleList, imorderNo, stockCode,
					stockName);
			pipMmsScmstockMapper.insert(ps);
		}
		// 保存入库订单表
		PipMmsImscmmaster pmic = null;
		pmic = getPipMmsImscmmaster(imorderNo, excleList, stockCode);
		mapper.insert(pmic);
	}

	/**
	 * 
	 * description: ev : excel里面的数据，只取第一条
	 * 
	 * @author yangfeng
	 * @param imorderNo
	 * @param ev
	 * @return
	 * @update 2015-11-24
	 */
	public PipMmsImscmmaster getPipMmsImscmmaster(String imorderNo,
			List<BooldExcelVo> ev, String stockCode) {
		PipMmsImscmmaster pmic = new PipMmsImscmmaster();
		String projectId = Securitys.getUser().getCurrent_projectId();
		pmic.setProjectId(projectId);
		pmic.setImorderNo(imorderNo);
		pmic.setLccCode(Securitys.getUser().getLccCode());
		pmic.setUserName(Securitys.getUser().getName());
		pmic.setUserCode(Securitys.getUser().getId()); // 待定
		pmic.setCreateBy(Securitys.getUser().getLccName());
		pmic.setCreateDate(new Date());
		pmic.setIsRemoved((short) 0);
		pmic.setImportState((short) 1);
		pmic.setStockCode(stockCode);
		if (ev.size() > 0) {
			if (!StringUtils.isEmpty(ev.get(0).getSupplierCode())) {
				pmic.setSupplierCode(ev.get(0).getSupplierCode());
			}
		}
		return pmic;
	}

	/**
	 * 
	 * description:将excelvo 里面的数据整理成PipMmsScmarchives数据
	 * 
	 * @author yangfeng
	 * @param bev
	 * @return
	 * @update 2015-11-24
	 */
	public PipMmsScmarchives getPipMmsScmarchives(BooldExcelVo bev,
			String stockCode) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		PipMmsScmarchives pms = new PipMmsScmarchives();
		pms.setArchivesNo(bev.getArchivesNo());
		pms.setBloodpackageCode(bev.getBooldpackageCode());
		pms.setCreateDate(new Date());
		pms.setMaterlBatch(bev.getMaterlBatch());
		pms.setPackageState((short) 2);
		pms.setProjectId(projectId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			pms.setPeriodValidity(sdf.parse(bev.getPeriodValidity()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		pms.setStockCode(stockCode);
		pms.setCreateBy(Securitys.getUser().getName());
		pms.setCreateDate(new Date());
		return pms;
	}

	/**
	 * 
	 * description: 将excel数据重新整理成PipMmsImscmdetal 数据
	 * 
	 * @author yangfeng
	 * @param box
	 * @param excleList
	 * @param imorderNo
	 * @return
	 * @update 2015-11-24
	 */
	public PipMmsImscmdetal getPipMmsImscmdetals(String box,
			List<BooldExcelVo> excleList, String imorderNo) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		PipMmsImscmdetal pmi = new PipMmsImscmdetal();
		for (BooldExcelVo bev : excleList) {
			if (box.equals(bev.getArchivesNo())) {
				pmi.setProjectId(projectId);
				pmi.setArchivesNo(bev.getArchivesNo());
				pmi.setImorderNo(imorderNo);
				pmi.setMaterlBatch(bev.getMaterlBatch());
				pmi.setMaterlPrice(new BigDecimal(bev.getMaterlPrice()));
				pmi.setWholesalePrice(new BigDecimal(bev.getWholesalePrice()));
				pmi.setManufacturerCode(bev.getManufacturerCode());
				pmi.setCreateBy(Securitys.getUser().getLccName());
				pmi.setCreateDate(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					pmi.setPeriodValidity(sdf.parse(bev.getPeriodValidity()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				pmi.setImportUnit(Securitys.getUser().getLccName());
				pmi.setStoreUnit("箱");

			}
		}
		return pmi;
	}

	public PipMmsScmstock getPipMmsScmstocks(String box,
			List<BooldExcelVo> excleList, String imorderNo, String stockCode,
			String stockName) {

		String projectId = Securitys.getUser().getCurrent_projectId();
		PipMmsScmstock ps = new PipMmsScmstock();
		for (BooldExcelVo bev : excleList) {
			if (box.equals(bev.getArchivesNo())) {
				ps.setLccCode(Securitys.getUser().getLccCode());
				ps.setProjectId(projectId);
				ps.setArchivesNo(bev.getArchivesNo());
				ps.setImorderNo(imorderNo);
				ps.setMaterlBatch(bev.getMaterlBatch());
				ps.setMaterlPrice(new BigDecimal(bev.getMaterlPrice()));
				ps.setWholesalePrice(new BigDecimal(bev.getWholesalePrice()));
				ps.setManufacturerCode(bev.getManufacturerCode());
				ps.setStockCode(stockCode);
				ps.setStockName(stockName);
				ps.setCreateBy(Securitys.getUser().getLccName());
				// 库存数量
				ps.setStockNum(new BigDecimal("1"));
				// 出库状态 1为正在出库0为可用状态 默认就是0
				ps.setStockState((short) 0);
				ps.setCreateDate(new Date());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					ps.setPeriodValidity(sdf.parse(bev.getPeriodValidity()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				ps.setStockNum(new BigDecimal("1"));

			}
		}
		return ps;
	}

}
