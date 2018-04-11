package com.bdcor.pip.web.material.supp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.common.BdcorCodeStringDefinition;
import com.bdcor.pip.core.excelExport.lang.collection.Beans;
import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.security.shiro.ShiroUser;
import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.material.supp.dao.MaterialDao;
import com.bdcor.pip.web.material.supp.domain.MasterImport;
import com.bdcor.pip.web.material.supp.domain.MasterImportDetail;
import com.bdcor.pip.web.material.supp.domain.MaterExport;
import com.bdcor.pip.web.material.supp.domain.MaterExportDetail;
import com.bdcor.pip.web.material.supp.domain.MaterStore;
import com.bdcor.pip.web.material.supp.domain.MaterialActiveType;
import com.bdcor.pip.web.material.supp.filter.MaterExportDetailFilter;
import com.bdcor.pip.web.material.supp.filter.MaterExportFilter;
import com.bdcor.pip.web.material.supp.filter.MaterImportDetailFilter;
import com.bdcor.pip.web.material.supp.filter.MaterImportFilter;
import com.bdcor.pip.web.material.supp.filter.StockFilter;
import com.bdcor.pip.web.material.supp.service.MaterialService;

/**
 * 物质管理
 * 
 * @author rp
 * 
 */
@Service
@Transactional
public class MaterialServiceImpl implements MaterialService {

	@Autowired
	MaterialDao materialDao;

	// @Cacheable(value = "dictCache", key =
	// "#state+#projectId+'activeTypeCache'")
	public List<MaterialActiveType> selectActiveTypeByState(String state,
			String projectId) {
		return materialDao.selectActiveTypeByState(state, projectId);
	}

	/**
	 * 新增/修改入库信息
	 */
	public void saveOrUpdateImportMaster(MasterImport masterImport) {
		String type = masterImport.getType();
		String projectId = Securitys.getUser().getCurrent_projectId();
		masterImport.setProjectId(projectId);
		masterImport.setCreateBy(Securitys.getUserName());
		masterImport.setCreateDate(new Date());
		// 入库类别 登记入库需要审核
		masterImport.setImportState((short) 1);// 入库成功
		if ("1".equals(type)) {
			materialDao.saveImportMaster(masterImport);
		} else if ("2".equals(type)) {
			materialDao.updateImportMaster(masterImport);
		}
	}

	/**
	 * 查询出符合条件的入库记录
	 * 
	 * @param masterImport
	 * @return
	 */
	public MasterImport selectImportMasterByPrimaryKey(MasterImport masterImport) {
		return materialDao.selectImportMasterByPrimaryKey(masterImport);
	}

	public List<MasterImport> getAllMaterImports(MaterImportFilter filter) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		filter.setProjectId(projectId);
		filter.setUserId(Securitys.getUserId());
		return materialDao.getAllMasterImports(filter);
	}

	/**
	 * 同时删除入库单下面对应的资产明细
	 * 
	 */
	@Override
	public void delete(MasterImport masterImport) {
		materialDao.deleteImportMaster(masterImport);
		// 删除明细之前删除库存
		// 由入库单和项目id查询该入库单下面的所有入库明细id,执行删除方法
		MaterImportDetailFilter fliter = new MaterImportDetailFilter();
		fliter.setRows(1000);
		fliter.setImorderNo(masterImport.getImorderNo());
		List<MasterImportDetail> details = materialDao
				.getAllMasterDetail(fliter);
		if (details != null && details.size() > 0) {
			for (MasterImportDetail d : details) {
				d.setImportAmount(0l);// 删除时,将当前入库数量更新为0
				compareMasterDetailAndMasterStore(d, null, 2);
			}
		}
		materialDao.deleteImportMasterDetailWithImorderId(masterImport);
	}

	/**
	 * 新增/修改物质入库明细信息
	 */
	@Override
	public void saveOrUpdateImportMasterDetail(MasterImportDetail masterInDetail) {

		String type = masterInDetail.getType();
		String projectId = Securitys.getUser().getCurrent_projectId();
		String lccId = Securitys.getUser().getLccCode();
		masterInDetail.setProjectId(projectId);
		masterInDetail.setCreateBy(Securitys.getUserName());
		masterInDetail.setCreateDate(new Date());
		// 设备入库 默认拆零系数为1
		if (masterInDetail.getCoefficientChange() == null) {
			masterInDetail.setCoefficientChange(new BigDecimal(1));
		}
		if ("1".equals(type)) {
			// 设备档案号需要自动生成 物质
			if (BdcorCodeStringDefinition.CLASS_CODE_B.equals(masterInDetail
					.getClassCode())) {
				String archivesNo = lccId.concat(GenerateKey.getKey("MAD"));
				masterInDetail.setArchivesNo(archivesNo);
			}
			compareMasterDetailAndMasterStore(masterInDetail, null,
					Integer.parseInt(type));
			materialDao.saveImportMasterDetail(masterInDetail);
		} else if ("2".equals(type)) {
			compareMasterDetailAndMasterStore(masterInDetail, null,
					Integer.parseInt(type));
			materialDao.updateImportMasterDetail(masterInDetail);
		}
	}

	/*
	 * 对比入库明细和库存明细 从而进行增删改 type 1:入库新增 2:入库修改/删除 3:出库新增 4:出库修改/删除
	 */

	public void compareMasterDetailAndMasterStore(MasterImportDetail inDetail,
			MaterExportDetail exDetail, int type) throws ServiceException {
		String exportLccCode = "";
		String exportStockCode = "";
		if (null != exDetail) {
			exportLccCode = StringUtils.isEmpty(exDetail.getExportLccCode()) ? ""
					: exDetail.getExportLccCode();
			exportStockCode = StringUtils
					.isEmpty(exDetail.getExportStockCode()) ? "" : exDetail
					.getExportStockCode();
		}
		// 物质,批次,采购价,有效期 项目id lcccode 库房code标识库存唯一
		MaterStore ms = new MaterStore();
		ShiroUser user = Securitys.getUser();
		ms.setLccCode(user.getLccCode());// lccCode
		ms.setProjectId(user.getCurrent_projectId());// projectId
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", user.getCurrent_projectId());

		List<MaterStore> mss = null;
		// 得到本次的入库/出库数量
		long count = 0l;
		if (inDetail != null) {
			try {// 新增/修改
				count = inDetail.getCoefficientChange()
						.multiply(new BigDecimal(inDetail.getImportAmount()))
						.longValue();
			} catch (Exception e) {
				// 删除
				e.printStackTrace();
			}

			params.put("imorderNo", inDetail.getImorderNo());
			if (inDetail.getId() != null && inDetail.getImorderNo() == null) {// 删除
				inDetail = materialDao.selectImportMasterDetail(inDetail);
			}
			Beans.copyProperties(inDetail, ms, false);// 将相同属性的值转化
														// 新增/修改保证了其它五个字段的唯一
			params.put("id", inDetail.getId());
			String stockCode = materialDao.getStockCodeByImorderNo(params);// 得到库房编码
			ms.setStockCode(stockCode);
			mss = materialDao.selectMasterStore(ms);// 得到唯一的库房记录
		}
		if (exDetail != null) {// 出库明细用的ID作为主键
			try {
				count = exDetail.getExportAmount();
			} catch (Exception e) {
				count = 0l;
			}
			//
			// if(exDetail.getId()!=null&&exDetail.getExorderNo()==null){//删除
			// exDetail=materialDao.selectExportMaterDetailByPrimaryKey(exDetail);
			// }
			// params.put("exorderNo", exDetail.getExorderNo());
			// params.put("id", exDetail.getId());
			// String
			// stockCode=materialDao.getStoreCodeByExorderNo(params);//由id得到完整库房明细记录
			// Beans.copyProperties(exDetail, ms, false);//将相同属性的值转化
			// 新增/修改保证了其它五个字段的唯一
			// ms.setStockCode(stockCode);
			// 由库存ID来获取库存明细信息
			ms.setId(exDetail.getId());
			mss = materialDao.selectMasterStore(ms);// 得到唯一的库房记录
			MaterStore s = mss.get(0);
			// 物质,批次,采购价,有效期 项目id lcccode 库房code标识库存唯一
			exDetail.setMaterlinfoCode(s.getMaterlinfoCode());
			exDetail.setMaterlBatch(s.getMaterlBatch());
			exDetail.setMaterlPrice(s.getMaterlPrice());
			exDetail.setWholesalePrice(s.getWholesalePrice());
			exDetail.setExportUnit(s.getStoreUnit());
			exDetail.setPeriodValidity(s.getPeriodValidity());
			exDetail.setSupplierCode(s.getSupplierCode());
		}

		if (type == 1 && mss != null && mss.size() == 0) {// 新增在库存中不存在
			// 执行入库插入操作
			ms.setStoreAmount(count);
			materialDao.insertMaterStore(ms);
		} else if (type == 1 && mss != null && mss.size() != 0) {// 新增在库存中已存在
																	// 更新数量
			MaterStore m = mss.get(0);
			m.setStoreAmount(m.getStoreAmount() + count);
			materialDao.updateMaterStore(m);
		} else if (type == 2 && mss != null && mss.size() != 0) {
			// 修改/删除操作
			MaterStore m = mss.get(0);
			MasterImportDetail mid = materialDao
					.selectImportMasterDetail(inDetail);// 由入库id找到入库明细
			long corrCount = m.getStoreAmount()
					- mid.getCoefficientChange()
							.multiply(new BigDecimal(mid.getImportAmount()))
							.longValue() + count;// 得到当前的实际库存数量 库存数量-明细数量+当前数量
			if (corrCount < 0) {
				throw new ServiceException("该明细对应物质已出库,不能操作");// 修改删除操作时,如果该入库明细单上物质已经被部分或全部出库,则会出现当前库存为负数的情况
			}
			m.setAssetsStatus(inDetail.getAssetsStatus());
			m.setIsMeasure(inDetail.getIsMeasure());
			m.setNextMeasureDate(inDetail.getNextMeasureDate());
			m.setMeasurePeriod(inDetail.getMeasurePeriod());
			m.setStoreAmount(corrCount);
			materialDao.updateMaterStore(m);
		} else if (type == 3 && mss != null && mss.size() != 0) {// 出库新增
			MaterStore m = mss.get(0);

			long corrCount = m.getStoreAmount() - exDetail.getExportAmount();// 得到当前的实际库存数量
			// 库存数量-明细数量+当前数量
			if (corrCount < 0) {
				throw new ServiceException(m.getMaterlName() + "出库数量大于库存数量");
			}
			m.setStoreAmount(corrCount);
			// 将甲方的库存数量变更到乙方
			if (!StringUtils.isEmpty(exportLccCode)
					&& !StringUtils.isEmpty(exportStockCode)) {
				ReverseStore(m, exportLccCode, exportStockCode,
						exDetail.getExportAmount());
			}
			materialDao.updateMaterStore(m);
		} else if (type == 4 && mss != null && mss.size() != 0) {// 出库修改 删除
			MaterStore m = mss.get(0);

			MaterExportDetail mid = materialDao
					.selectExportMaterDetailByPrimaryKey(exDetail);// 由入库id找到出库明细
			long corrCount = m.getStoreAmount() + mid.getExportAmount() - count;// 得到当前的实际库存数量
																				// 库存数量-明细数量+当前数量
			m.setStoreAmount(corrCount);
			materialDao.updateMaterStore(m);
		}
	}

	/**
	 * 查询物质入库明细信息
	 */
	@Override
	public MasterImportDetail selectImportMasterDetail(
			MasterImportDetail masterInDetail) {
		return materialDao.selectImportMasterDetail(masterInDetail);
	}

	/**
	 * 删除物质入库明细信息
	 */
	@Override
	public void deleteImportMasterDetail(MasterImportDetail masterInDetail) {
		compareMasterDetailAndMasterStore(masterInDetail, null, 2);
		materialDao.deleteImportMasterDetail(masterInDetail);
	}

	/**
	 * 根据查询条件查询物质入库明细信息
	 */
	@Override
	public List<MasterImportDetail> getAllMaterDetail(
			MaterImportDetailFilter masterInDetail) {
		return materialDao.getAllMasterDetail(masterInDetail);
	}

	/**
	 * 查看该入库单下面是否对应有入库明细信息
	 */
	@Override
	public int countImportMasterDetail(String imorderNo) {
		// TODO Auto-generated method stub
		return materialDao.countImportMasterDetail(imorderNo);
	}

	/**
	 * 显示出库列表
	 */
	@Override
	public List<MaterExport> getAllMaterExports(MaterExportFilter filter) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		filter.setProjectId(projectId);
		filter.setUserId(Securitys.getUserId());
		return materialDao.getAllMasterExports(filter);
	}
	/**
	 * 显示出库列表
	 */
	@Override
	public List<MaterExport> getAllMaterExportsByExportNo(String exportNos) {
		String [] exportArr = exportNos.split(","); 
		return materialDao.getAllMaterExportsByExportNo(exportArr);
	}
	

	/**
	 * 添加/修改出库信息
	 */
	@Override
	public void saveOrUpdateExportMater(MaterExport materEx) {
		String type = materEx.getType();
		String projectId = Securitys.getUser().getCurrent_projectId();
		materEx.setProjectId(projectId);
		materEx.setCreateBy(Securitys.getUserName());
		materEx.setCreateDate(new Date());
		if ("1".equals(type)) {
			materialDao.saveExportMaster(materEx);
		} else if ("2".equals(type)) {
			if (materEx.getIsRemoved() == (short) 1) {// 删除出库单,同时删除该出库单下的出库明细,同时更改当前库存信息
				// 查找出库单下所有的出库明细
				MaterExportDetailFilter filter = new MaterExportDetailFilter();
				filter.setExorderNo(materEx.getExorderNo());// 出库单号虽不是主键,但在录入校验已经保证了唯一性
				filter.setRows(10000);// 显示条数
				List<MaterExportDetail> exDetails = materialDao
						.getAllMasterExportDetail(filter);
				if (exDetails != null && exDetails.size() > 0) {
					for (MaterExportDetail detail : exDetails) {
						detail.setExorderNo(null);
						detail.setExportAmount(null);
						deleteExportMaterDetail(detail);
					}
				}
			}
			materialDao.updateExportMaster(materEx);
		}
	}

	/**
	 * 根据查询条件查询单个出库信息
	 */
	@Override
	public MaterExport selectExportMasterByPrimaryKey(MaterExport materEx) {
		return materialDao.selectExportMasterByPrimaryKey(materEx);
	}

	/**
	 * 查询出库单下对应多少条出库明细信息
	 */
	@Override
	public int countExportMasterDetail(String exorderNo) {
		// TODO Auto-generated method stub
		return materialDao.countExportMasterDetail(exorderNo);
	}

	/**
	 * 查询出库单明细信息
	 */
	@Override
	public MaterExportDetail selectExportMasterDetail(MaterExportDetail mat) {
		// TODO Auto-generated method stub
		return materialDao.selectExportMaterDetailByPrimaryKey(mat);
	}

	/**
	 * 查看所有的出库明细信息
	 */
	@Override
	public List<MaterExportDetail> getAllMaterExportDetail(
			MaterExportDetailFilter filter) {
		return materialDao.getAllMasterExportDetail(filter);
	}
	/**
	 * 查看所有的出库明细信息
	 */
	@Override
	public List<MaterExportDetail> getAllMaterExportDetail(
			String exportNo) {
		MaterExportDetailFilter filter= new MaterExportDetailFilter();
		filter.setExorderNo(exportNo);
		return getAllMaterExportDetail(filter);
	}

	/**
	 * 新增/修改 出库明细信息
	 */
	@Override
	public void saveOrUpdateExportMaterDetail(
			MaterExportDetail materExportDetail) {
		// TODO Auto-generated method stub

		String type = materExportDetail.getType();
		String projectId = Securitys.getUser().getCurrent_projectId();
		materExportDetail.setProjectId(projectId);
		materExportDetail.setCreateBy(Securitys.getUserName());
		materExportDetail.setCreateDate(new Date());

		if ("1".equals(type)) {
			// 设备档案号需要自动生成
			compareMasterDetailAndMasterStore(null, materExportDetail, 3);
			materialDao.saveMaterExportDetail(materExportDetail);
		} else if ("2".equals(type)) {
			compareMasterDetailAndMasterStore(null, materExportDetail, 4);
			materialDao.updateMaterExportDetail(materExportDetail);
		}

	}

	/**
	 * 删除出库明细信息
	 */
	@Override
	public void deleteExportMaterDetail(MaterExportDetail masterEx) {
		// TODO Auto-generated method stub
		compareMasterDetailAndMasterStore(null, masterEx, 4);
		materialDao.deleteExportMaterDetail(masterEx);
	}

	@Override
	public List<MaterStore> selectJsonForAutocomplete(MaterStore ms) {
		// TODO Auto-generated method stub
		ms.setLccCode(Securitys.getUser().getLccCode());
		return materialDao.selectMasterStore(ms);
	}

	/**
	 * 检查入库单号是否存在
	 */
	@Override
	public MaterExport checkExportMasterIsExists(String exorderNo) {
		// TODO Auto-generated method stub
		return materialDao.checkExportMasterIsExists(exorderNo);
	}

	/**
	 * 查询库房明细信息
	 */
	@Override
	public MaterStore selectMaterStore(MaterStore ms) {
		return materialDao.selectMasterStore(ms).get(0);
	}

	@Override
	public List<MaterStore> getAllStocks(StockFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return materialDao.selectAllMaterStore(filter);
	}

	@Override
	public List<MaterStore> getAllStocksLimit(StockFilter filter) {
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		filter.setUserId(Securitys.getUserId());
		return materialDao.selectMaterStoreLimit(filter);
	}

	/**
	 * description:甲方出库到乙方以后，乙方的库存的业务逻辑 里面包含的新增和更新库存数量的逻辑
	 * 
	 * @author yangfeng
	 * @param store
	 * @update 2015-12-9
	 */
	public void ReverseStore(MaterStore store, String lccCode,
			String stockCode, Long exportAccount) {
		MaterStore materStore = new MaterStore();
		materStore.setProjectId(store.getProjectId());
		materStore.setLccCode(lccCode);
		materStore.setStockCode(stockCode);
		materStore.setMaterlBatch(store.getMaterlBatch());
		materStore.setMaterlinfoCode(store.getMaterlinfoCode());
		materStore.setMaterlPrice(store.getMaterlPrice());
		materStore.setWholesalePrice(store.getWholesalePrice());
		materStore.setStoreAmount(exportAccount);
		materStore.setStoreUnit(store.getStoreUnit());
		materStore.setPeriodValidity(store.getPeriodValidity());
		materStore.setSupplierCode(store.getSupplierCode());
		materStore.setCreateDate(store.getCreateDate());
		materStore.setCreateBy(store.getCreateBy());
		materStore.setProduceDate(store.getProduceDate());
		materStore.setMaterlSpec(store.getMaterlSpec());
		List<MaterStore> selectMasterStore = materialDao
				.selectMasterStore2(materStore);
		if (selectMasterStore.size() == 1) {
			MaterStore materStore2 = selectMasterStore.get(0);
			// 如果查询仓库里面有一条数据的话 ，将原来的库存数量+接收的库存数量
			materStore2.setStoreAmount(materStore2.getStoreAmount()
					+ exportAccount);
			materialDao.updateMaterStore(materStore2);
		} else {
			materialDao.insertMaterStore(materStore);
		}
	}

	@Override
	public void addMasterExport(MaterExport materExport) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		materExport.setProjectId(projectId);
		materExport.setCreateBy(Securitys.getUserName());
		materExport.setCreateDate(new Date());
		// 添加出库单信息
		materialDao.saveExportMaster(materExport);
		// 添加出库明细信息
		String detail = materExport.getDetail();
		if (StringUtils.isNotBlank(detail)) {
			for (String record : detail.split(";")) {
				String[] detailInfo = record.split(":");
				Long storeId = Long.parseLong(detailInfo[0]);// 库存ID
				Long storeCount = Long.parseLong(detailInfo[1]);// 出库数量
				MaterExportDetail d = new MaterExportDetail();
				d.setId(storeId);
				d.setExportAmount(storeCount);
				d.setProjectId(projectId);
				d.setCreateBy(Securitys.getUserName());
				d.setCreateDate(new Date());
				d.setExportStockCode(materExport.getExportStockCode());
				d.setExportLccCode(materExport.getExportLccCode());
				compareMasterDetailAndMasterStore(null, d, 3);
				d.setExorderNo(materExport.getExorderNo());
				materialDao.saveMaterExportDetail(d);
			}
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.material.supp.service.MaterialService#listByIds(java.lang.String)
	 */
	@Override
	public List<MasterImport> listByIds(String ids) throws Exception {
		String [] idArray = ids.split(",");
		return materialDao.listByIds(idArray);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.material.supp.service.MaterialService#getAllMaterDetail(java.lang.String)
	 */
	@Override
	public List<MasterImportDetail> getAllMaterDetail(String imorderNo) {
		MaterImportDetailFilter masterInDetail = new MaterImportDetailFilter();
		masterInDetail.setImorderNo(imorderNo);
		return getAllMaterDetail(masterInDetail);
	}
}
