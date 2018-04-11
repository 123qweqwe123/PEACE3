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
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.material.supp.dao.PipMmsExscmdetalMapper;
import com.bdcor.pip.web.material.supp.dao.PipMmsExscmmasterMapper;
import com.bdcor.pip.web.material.supp.dao.PipMmsScmstockMapper;
import com.bdcor.pip.web.material.supp.domain.MaterExportDetail;
import com.bdcor.pip.web.material.supp.domain.OrderDetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetal;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetalExample;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmdetalExample.Criteria;
import com.bdcor.pip.web.material.supp.domain.PipMmsExscmmaster;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmstock;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmstockExample;
import com.bdcor.pip.web.material.supp.filter.OrderDetalFilter;
import com.bdcor.pip.web.material.supp.filter.PipMmsExscmmasterFilter;
import com.bdcor.pip.web.material.supp.service.PipMmsExscmmasterService;

@Service
@Transactional
public class PipMmsExscmmasterServiceImpl implements PipMmsExscmmasterService
{
	@Autowired
	PipMmsExscmdetalMapper pipMmsExscmdetalMapper;
	@Autowired
	PipMmsScmstockMapper pipMmsScmstockMapper;
	@Autowired
	private PipMmsExscmmasterMapper pipMmsExscmmasterMapper;

	public List<PipMmsExscmmaster> selectPipMmsExscmmasterByLccCode(PipMmsExscmmaster pmem)
	{
		pmem.setUserId(Securitys.getUserId());
		return pipMmsExscmmasterMapper.selectPipMmsExscmmasterByLccCode(pmem);
	}

	public List<OrderDetal> selectPipMmsExscmmasterByArchivesNo(OrderDetal od)
	{

		return pipMmsExscmmasterMapper.selectPipMmsExscmmasterByArchivesNo(od);

	}

	public void saveExscmmasterAndDetalUpdateStorck(OrderDetalFilter odf)
	{
		String exorderNo = odf.getExorderNo();
		String ids[] = odf.getStr().split(",");
		List<OrderDetal> data = null;
		for (int j = 0; j < ids.length; j++)
		{
			OrderDetal od = new OrderDetal();
			if (Securitys.isAdmin() || Securitys.isOrganAdmin())
			{
				od.setLccCode(null);
			} else
			{
				od.setLccCode(Securitys.getUser().getLccCode());
			}
			if (ids[j] != null && !"".equals(ids[j]))
			{
				od.setArchivesNo(ids[j]);
				data = selectPipMmsExscmmasterByArchivesNo(od);
			}
			if (data != null && data.size() > 0)
			{
				PipMmsExscmdetal pme = new PipMmsExscmdetal();
				pme.setArchivesNo(ids[j]);
				pme.setExorderNo(exorderNo);
				pme.setProjectId(Securitys.getUser().getCurrent_projectId());
				pme.setMaterlBatch(data.get(0).getMaterlBatch());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String periodValidity = data.get(0).getPeriodValidity();
				if(!StringUtils.isEmpty(periodValidity)){
					try
					{
						pme.setPeriodValidity(sdf.parse(data.get(0).getPeriodValidity()));
					} catch (ParseException e)
					{
						e.printStackTrace();
					}
				}
				pme.setManufacturerCode(data.get(0).getManufacturerCode());
				pme.setCreateBy(Securitys.getUser().getLoginName());
				pme.setCreateDate(new Date());
				pme.setStoreUnit("箱");
				pme.setMaterlPrice(new BigDecimal(data.get(0).getMaterlPrice()));
				pme.setWholesalePrice(new BigDecimal(data.get(0).getWholesalePrice()));
				pipMmsExscmdetalMapper.insert(pme);
				// 到库存表内更新一条记录
				pipMmsScmstockMapper.updateByArchivesNoAndProjectId(pme.getArchivesNo(), pme.getProjectId());

			}
		}

		// 保存订单记录
		PipMmsExscmmaster pmes = new PipMmsExscmmaster();
		pmes.setProjectId(Securitys.getUser().getCurrent_projectId());
		pmes.setExorderNo(exorderNo);
		pmes.setLccCode(Securitys.getUser().getLccCode());
		pmes.setExportDate(new Date());
		pmes.setCreateBy(Securitys.getUser().getLoginName());
		pmes.setCreateDate(new Date());
		pmes.setIsRemoved(new Short("0"));
		pmes.setExportLccCode(odf.getExportLccCode());
		pmes.setActiveclassCode(odf.getActiveclassCode());
		pmes.setExportStockCode(odf.getExportStockCode());
		pmes.setImportState(new Short("1")); // 1表示已经出库 2表示已接受
		pmes.setStockCode(odf.getStockCode());
		pmes.setUserName(odf.getLoginName()); //--出库员名称
		pipMmsExscmmasterMapper.insert(pmes);

	}

	/**
	 */
	@Override
	public List<PipMmsExscmmaster> list(PipMmsExscmmasterFilter filter)
	{
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUserId());
		filter.setLccCode(Securitys.getUser().getLccCode());
		if (Securitys.getUser().isAdmin())
		{
			return pipMmsExscmmasterMapper.selectByFilterForAdmin(filter);
		}
		return pipMmsExscmmasterMapper.selectByFilter(filter);
	}

	/**
	 * @see com.bdcor.pip.web.material.supp.service.PipMmsExscmmasterService#changeState(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void receive(String ids, String state) throws Exception
	{
		// 修改状态
		pipMmsExscmmasterMapper.changeState(ids, state);
		// 设置库存为0pipMmsScmstock
		pipMmsScmstockMapper.changeNum(ids);
		// 当乙方接受以后，乙方库存里面处理逻辑
		String[] idArray = ids.split(",");
		for (String id : idArray)
		{
			// 出库单
			PipMmsExscmmaster exscmmaster = pipMmsExscmmasterMapper.selectByPrimaryKey(id);
			// 出库单获取目标库房 和目标单位
			String exportLccCode = exscmmaster.getExportLccCode();
			String exportLccName = exscmmaster.getExportLccName();
			String exportStockCode = exscmmaster.getExportStockCode();
			String exportStockName = exscmmaster.getExportStockName();
			String lccCode = exscmmaster.getLccCode();
			String stockCode = exscmmaster.getStockCode();
			String exorderNo = exscmmaster.getExorderNo();
			// 通过出库单查询 出库箱号
			PipMmsExscmdetalExample example = new PipMmsExscmdetalExample();
			Criteria criteria = example.createCriteria();
			criteria.andExorderNoEqualTo(exorderNo);
			List<PipMmsExscmdetal> pipMmsExscmdetals = pipMmsExscmdetalMapper.selectByExample(example);
			// 遍历出库单里面箱的数据
			for (PipMmsExscmdetal dExscmdetal : pipMmsExscmdetals)
			{
				// 找到库存记录
				String archivesNo = dExscmdetal.getArchivesNo();
				String manufacturerCode = dExscmdetal.getManufacturerCode();
				String projectId = dExscmdetal.getProjectId();
				PipMmsScmstockExample example2 = new PipMmsScmstockExample();
				com.bdcor.pip.web.material.supp.domain.PipMmsScmstockExample.Criteria createCriteria = example2
						.createCriteria();
				createCriteria.andArchivesNoEqualTo(archivesNo);
				if (!StringUtils.isEmpty(manufacturerCode))
				{
					createCriteria.andManufacturerCodeEqualTo(manufacturerCode);
				}
				createCriteria.andProjectIdEqualTo(projectId);
				createCriteria.andStockCodeEqualTo(stockCode);
				createCriteria.andLccCodeEqualTo(lccCode);
				List<PipMmsScmstock> selectByExample = pipMmsScmstockMapper.selectByExample(example2);
				if (selectByExample.size() == 1)
				{
					PipMmsScmstock pipMmsScmstock = selectByExample.get(0);
					pipMmsScmstock.setLccCode(exportLccCode);
					pipMmsScmstock.setLccName(exportLccName);
					pipMmsScmstock.setStockCode(exportStockCode);
					pipMmsScmstock.setStockName(exportStockName);
					PipMmsScmstock newPipMmsScmstock = pipMmsScmstockMapper.selectByModel(pipMmsScmstock);
					if (null == newPipMmsScmstock)
					{
						// 新增
						pipMmsScmstock.setStockNum(new BigDecimal(1));
						pipMmsScmstock.setStockState((short) 0);
						pipMmsScmstockMapper.insert(pipMmsScmstock);
					} else
					{
						pipMmsScmstock.setStockNum(new BigDecimal(1));
						pipMmsScmstock.setStockState((short) 0);
						pipMmsScmstockMapper.updateStockNumSelective(pipMmsScmstock);
					}
				}
			}
		}
	}

	public List<PipMmsExscmmaster> listByIds(String ids)
	{
		String isArray[] = ids.split(",");
		return pipMmsExscmmasterMapper.listByIds(isArray);
	}

	public List<PipMmsExscmdetal> getExportDetail(String exorderNo)
	{
		PipMmsExscmdetalExample example = new PipMmsExscmdetalExample();
		Criteria criteria = example.createCriteria();
		criteria.andExorderNoEqualTo(exorderNo);
		return pipMmsExscmdetalMapper.selectByExample(example);
	}

	public List<PipMmsExscmmaster> selectPipMmsExscmmasterByLccCode(PipMmsExscmmasterFilter pmemf)
	{
		pmemf.setUserId(Securitys.getUserId());
		return pipMmsExscmmasterMapper.selectPipMmsExscmmasterByFilter(pmemf);
	}

	/**
	 * 
	 * description:通过出库单 查找出库记录
	 * 
	 * @author yangfeng
	 * @param exorderNo
	 * @return
	 * @update 2016年1月7日
	 */
	public PipMmsExscmmaster selectModelByExorderNo(String exorderNo)
	{
		return pipMmsExscmmasterMapper.selectModelByExorderNo(exorderNo);
	}
}
