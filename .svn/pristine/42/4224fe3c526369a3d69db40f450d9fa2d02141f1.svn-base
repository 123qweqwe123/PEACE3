package com.bdcor.pip.web.material.supp.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.core.common.BdcorCodeStringDefinition;
import com.bdcor.pip.core.excelExport.lang.collection.Beans;
import com.bdcor.pip.core.exception.ServiceException;
import com.bdcor.pip.core.mapper.JsonMapper;
import com.bdcor.pip.core.security.shiro.ShiroUser;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.material.supp.dao.DeviceDao;
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
import com.bdcor.pip.web.material.supp.service.DeviceService;

/**
 * 物质管理
 * 
 * @author rp
 * 
 */
@Service
@Transactional
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	DeviceDao deviceDao;

	@Cacheable(value = "dictCache", key = "#state+#projectId+'activeTypeCache'")
	public List<MaterialActiveType> selectActiveTypeByState(String state,
			String projectId) {
		return deviceDao.selectActiveTypeByState(state, projectId);
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
		if (!BdcorCodeStringDefinition.ACTIVECLASS_CODE.equals(masterImport
				.getActiveclassCode())) {
			masterImport.setImportState((short) 1);// 入库成功
		} else if (masterImport.getImportState() == null) {// 登记入库非登记入库通过
			masterImport.setImportState((short) 0);// 未审核状态
		}
		if ("2".equals(type)
				&& masterImport.getImportState() != null
				&& BdcorCodeStringDefinition.ACTIVECLASS_CODE
						.equals(masterImport.getActiveclassCode())) {
			batchDeleteImportDetailFromStore(masterImport.getImorderNo());
		}
		if ("1".equals(type)) {
			deviceDao.saveImportMaster(masterImport);
		} else if ("2".equals(type)) {
			deviceDao.updateImportMaster(masterImport);
		}
	}

	/**
	 * 其他状态置为登记入库状态时,将入库单号下面对应的库存信息清空
	 */
	public void batchDeleteImportDetailFromStore(String imorderNo) {
		// TODO Auto-generated method stub
		MaterImportDetailFilter fliter = new MaterImportDetailFilter();
		fliter.setRows(1000);
		fliter.setImorderNo(imorderNo);
		fliter.setProjectId(Securitys.getUser().getCurrent_projectId());
		List<MasterImportDetail> importDetailList = deviceDao
				.getAllMasterDetail(fliter);
		for (MasterImportDetail detail : importDetailList) {
			compareMasterDetailAndMasterStore(detail, null, 5);
		}
	}

	/**
	 * 查询出符合条件的入库记录
	 * 
	 * @param masterImport
	 * @return
	 */
	public MasterImport selectImportMasterByPrimaryKey(MasterImport masterImport) {
		return deviceDao.selectImportMasterByPrimaryKey(masterImport);
	}

	public List<MasterImport> getAllMaterImports(MaterImportFilter filter) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		filter.setProjectId(projectId);
		filter.setLccCode(Securitys.getUser().getLccCode());
		filter.setUserId(Securitys.getUserId());
		return deviceDao.getAllMasterImports(filter);
	}

	/**
	 * 同时删除入库单下面对应的资产明细
	 * 
	 */
	@Override
	public void delete(MasterImport masterImport) {
		deviceDao.deleteImportMaster(masterImport);
		// 删除明细之前删除库存
		// 由入库单和项目id查询该入库单下面的所有入库明细id,执行删除方法
		MaterImportDetailFilter fliter = new MaterImportDetailFilter();
		fliter.setRows(1000);
		fliter.setImorderNo(masterImport.getImorderNo());
		List<MasterImportDetail> details = deviceDao.getAllMasterDetail(fliter);
		if (details != null && details.size() > 0) {
			for (MasterImportDetail d : details) {
				d.setImportAmount(0l);// 删除时,将当前入库数量更新为0
				compareMasterDetailAndMasterStore(d, null, 2);
			}
		}
		deviceDao.deleteImportMasterDetailWithImorderId(masterImport);
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
		// 判断该入库单是否审核通过
		MasterImport msIn = new MasterImport();
		msIn.setImorderNo(masterInDetail.getImorderNo());
		msIn.setProjectId(projectId);
		msIn = deviceDao.selectImportMasterByPrimaryKey(msIn);// 得到物质基本信息
		short state = msIn.getImportState();// 入库单入库状态
		if ("1".equals(type)) {
			// 设备档案号需要自动生成 物质
			// String archivesNo = lccId.concat(GenerateKey.getKey("MAD"));
			// masterInDetail.setArchivesNo(archivesNo);
			// 设备档案号不需要自动生成
			deviceDao.saveImportMasterDetail(masterInDetail);
		} else if ("2".equals(type)) {
			deviceDao.updateImportMasterDetail(masterInDetail);
		}
		if (state == (short) 1) {// 只有为1的时候才能进行入库操作
			compareMasterDetailAndMasterStore(masterInDetail, null,
					Integer.parseInt(type));
		}
	}

	/**
	 * 
	 * description:flag主要是为了区别不同出库的逻辑 ， 如果是报损出库的话是不需要往对方库里面记录的
	 * 
	 * @author yangfeng
	 * @param inDetail
	 * @param exDetail
	 * @param type
	 * @param flag
	 * @throws ServiceException
	 * @update 2015-12-17
	 */
	public void compareMasterDetailAndMasterStore(MasterImportDetail inDetail,
			MaterExportDetail exDetail, int type, boolean flag)
			throws ServiceException {
		// 物质,批次,采购价,有效期 项目id lcccode 库房code标识库存唯一
		MaterStore ms = new MaterStore();
		ShiroUser user = Securitys.getUser();
		ms.setLccCode(user.getLccCode());// lccCode
		ms.setProjectId(user.getCurrent_projectId());// projectId
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", user.getCurrent_projectId());
		List<MaterStore> mss = null;
		// 得到本次的入库/出库数量
		if (inDetail != null) {
			if (inDetail.getId() != null && inDetail.getImorderNo() == null) {// 删除
				inDetail = deviceDao.selectImportMasterDetail(inDetail);
				inDetail.setImportAmount(0l);// 删除时将库存置0
			}
			Beans.copyProperties(inDetail, ms, false);// 将相同属性的值转化
														// 新增/修改保证了其它五个字段的唯一
			params.put("imorderNo", inDetail.getImorderNo());
			params.put("id", inDetail.getId());
			String stockCode = deviceDao.getStockCodeByImorderNo(params);// 得到库房编码
			ms.setStockCode(stockCode);// 得到库房编码
			mss = deviceDao.selectMasterStore(ms);// 由设备编号得到唯一的库房记录
		}
		if (exDetail != null) {// 出库明细用的ID作为主键
			if (exDetail.getId() != null && exDetail.getExorderNo() == null) {// 删除
				exDetail = deviceDao
						.selectExportMaterDetailByPrimaryKey(exDetail);
			}
			Beans.copyProperties(exDetail, ms, false);// 将相同属性的值转化
														// 新增/修改保证了其它五个字段的唯一
			mss = deviceDao.selectMasterStore(ms);// 得到唯一的库房记录
		}

		if (type == 1 && mss != null && mss.size() == 0) {// 新增在库存中不存在
			// 执行入库插入操作
			ms.setStoreAmount(1l);
			deviceDao.insertMaterStore(ms);
		} else if (type == 2 && mss != null && mss.size() != 0) {// 修改/删除操作
			MaterStore m = mss.get(0);
			long corrCount = m.getStoreAmount();
			if (corrCount == 0) {
				throw new ServiceException("该明细对应物质已出库,不能操作");// 修改删除操作时,如果该入库明细单上物质已经被部分或全部出库,则会出现当前库存为负数的情况
			}
			Beans.copyProperties(inDetail, m, false);
			m.setStoreAmount(inDetail.getImportAmount());
			deviceDao.updateMaterStore(m);
		} else if (type == 3 && mss != null && mss.size() != 0) {// 出库新增
			MaterStore m = mss.get(0);
			String exportStockCode = "";
			String exportLccCode = "";
			// 默认库存数量为1
			Long exportAccount = 1l;
			if (exDetail != null) {
				exportStockCode = exDetail.getExportStockCode();
				exportLccCode = exDetail.getExportLccCode();
			}

			long corrCount = 0l;
			m.setStoreAmount(corrCount);
			deviceDao.updateMaterStore(m);
			// 反向在对方库中生成库存数量
			if (flag) {
				ReverseMasterStore(m, exportLccCode, exportStockCode,
						exportAccount);
			}
		} else if (type == 4 && mss != null && mss.size() != 0) {// 出库删除
			MaterStore m = mss.get(0);
			m.setStoreAmount(1l);
			deviceDao.updateMaterStore(m);
		} else if (type == 5 && mss != null && mss.size() != 0) {// 将库存信息删除
			MaterStore m = mss.get(0);
			deviceDao.deleteMaterStore(m);
		}
	}

	/*
	 * 对比设备入库明细和库存明细 从而进行增删改 type 1:入库新增 2:入库修改/删除 3:出库新增 4:出库修改/删除
	 * 设备编号能保证设备在库房中的唯一性
	 */

	public void compareMasterDetailAndMasterStore(MasterImportDetail inDetail,
			MaterExportDetail exDetail, int type) throws ServiceException {
		// 物质,批次,采购价,有效期 项目id lcccode 库房code标识库存唯一
		MaterStore ms = new MaterStore();
		ShiroUser user = Securitys.getUser();
		ms.setLccCode(user.getLccCode());// lccCode
		ms.setProjectId(user.getCurrent_projectId());// projectId
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("projectId", user.getCurrent_projectId());
		List<MaterStore> mss = null;
		// 得到本次的入库/出库数量
		if (inDetail != null) {
			if (inDetail.getId() != null && inDetail.getImorderNo() == null) {// 删除
				inDetail = deviceDao.selectImportMasterDetail(inDetail);
				inDetail.setImportAmount(0l);// 删除时将库存置0
			}
			Beans.copyProperties(inDetail, ms, false);// 将相同属性的值转化
														// 新增/修改保证了其它五个字段的唯一
			params.put("imorderNo", inDetail.getImorderNo());
			params.put("id", inDetail.getId());
			String stockCode = deviceDao.getStockCodeByImorderNo(params);// 得到库房编码
			ms.setStockCode(stockCode);// 得到库房编码
			mss = deviceDao.selectMasterStore(ms);// 由设备编号得到唯一的库房记录
		}
		if (exDetail != null) {// 出库明细用的ID作为主键
			if (exDetail.getId() != null && exDetail.getExorderNo() == null) {// 删除
				exDetail = deviceDao
						.selectExportMaterDetailByPrimaryKey(exDetail);
			}
			Beans.copyProperties(exDetail, ms, false);// 将相同属性的值转化
														// 新增/修改保证了其它五个字段的唯一
			mss = deviceDao.selectMasterStore(ms);// 得到唯一的库房记录
		}

		if (type == 1 && mss != null && mss.size() == 0) {// 新增在库存中不存在
			// 执行入库插入操作
			ms.setStoreAmount(1l);
			deviceDao.insertMaterStore(ms);
		} else if (type == 2 && mss != null && mss.size() != 0) {// 修改/删除操作
			MaterStore m = mss.get(0);
			long corrCount = m.getStoreAmount();
			if (corrCount == 0) {
				throw new ServiceException("该明细对应物质已出库,不能操作");// 修改删除操作时,如果该入库明细单上物质已经被部分或全部出库,则会出现当前库存为负数的情况
			}
			Beans.copyProperties(inDetail, m, false);
			m.setStoreAmount(inDetail.getImportAmount());
			deviceDao.updateMaterStore(m);
		} else if (type == 3 && mss != null && mss.size() != 0) {// 出库新增
			MaterStore m = mss.get(0);
			String exportStockCode = "";
			String exportLccCode = "";
			// 默认库存数量为1
			Long exportAccount = 1l;
			if (exDetail != null) {
				exportStockCode = exDetail.getExportStockCode();
				exportLccCode = exDetail.getExportLccCode();
			}

			long corrCount = 0l;
			m.setStoreAmount(corrCount);
			deviceDao.updateMaterStore(m);
			// 反向在对方库中生成库存数量
			ReverseMasterStore(m, exportLccCode, exportStockCode, exportAccount);
		} else if (type == 4 && mss != null && mss.size() != 0) {// 出库删除
			MaterStore m = mss.get(0);
			m.setStoreAmount(1l);
			deviceDao.updateMaterStore(m);
		} else if (type == 5 && mss != null && mss.size() != 0) {// 将库存信息删除
			MaterStore m = mss.get(0);
			deviceDao.deleteMaterStore(m);
		}
	}

	/**
	 * 登记入库,审批成功,将入库单下对应的入库明细全部入库
	 */
	@Override
	public void batchPutImportDetailToStore(String imorderNo) {
		// TODO Auto-generated method stub
		MaterImportDetailFilter fliter = new MaterImportDetailFilter();
		fliter.setRows(1000);
		fliter.setImorderNo(imorderNo);
		fliter.setProjectId(Securitys.getUser().getCurrent_projectId());
		List<MasterImportDetail> importDetailList = deviceDao
				.getAllMasterDetail(fliter);
		for (MasterImportDetail detail : importDetailList) {
			compareMasterDetailAndMasterStore(detail, null, 1);
		}
	}

	/**
	 * 查询物质入库明细信息
	 */
	@Override
	public MasterImportDetail selectImportMasterDetail(
			MasterImportDetail masterInDetail) {
		return deviceDao.selectImportMasterDetail(masterInDetail);
	}

	/**
	 * 删除物质入库明细信息
	 */
	@Override
	public void deleteImportMasterDetail(MasterImportDetail masterInDetail) {
		compareMasterDetailAndMasterStore(masterInDetail, null, 2);
		deviceDao.deleteImportMasterDetail(masterInDetail);
	}

	/**
	 * 根据查询条件查询物质入库明细信息
	 */
	@Override
	public List<MasterImportDetail> getAllMaterDetail(
			MaterImportDetailFilter masterInDetail) {
		return deviceDao.getAllMasterDetail(masterInDetail);
	}
	/**
	 * 根据查询条件查询物质入库明细信息
	 */
	@Override
	public List<MasterImportDetail> to_detaillist(
			MaterImportDetailFilter masterInDetail) {
		masterInDetail.setUserId(Securitys.getUserId());
		return deviceDao.to_detaillist(masterInDetail);
	}
	
	/**
	 * 查看该入库单下面是否对应有入库明细信息
	 */
	@Override
	public int countImportMasterDetail(String imorderNo) {
		return deviceDao.countImportMasterDetail(imorderNo);
	}

	/**
	 * 显示出库列表
	 */
	@Override
	public List<MaterExport> getAllMaterExports(MaterExportFilter filter) {
		String projectId = Securitys.getUser().getCurrent_projectId();
		filter.setProjectId(projectId);
		filter.setUserId(Securitys.getUserId());
		return deviceDao.getAllMasterExports(filter);
	}
	@Override
	public List<MaterExport> getAllMaterExportsByExportNo(String exportNos) {
		return deviceDao.getAllMaterExportsByExportNo(exportNos);
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
			deviceDao.saveExportMaster(materEx);
		} else if ("2".equals(type)) {
			Short isRemoved = 1;
			if (isRemoved == materEx.getIsRemoved()) {// 删除出库单,同时删除该出库单下的出库明细,同时更改当前库存信息
				// 查找出库单下所有的出库明细
				MaterExportDetailFilter filter = new MaterExportDetailFilter();
				filter.setExorderNo(materEx.getExorderNo());// 出库单号虽不是主键,但在录入校验已经保证了唯一性
				filter.setRows(10000);// 显示条数
				List<MaterExportDetail> exDetails = deviceDao
						.getAllMasterExportDetail(filter);
				if (exDetails != null && exDetails.size() > 0) {
					for (MaterExportDetail detail : exDetails) {
						detail.setExorderNo(null);
						detail.setExportAmount(null);
						deleteExportMaterDetail(detail);
					}
				}
			}
			deviceDao.updateExportMaster(materEx);
		}
	}

	/**
	 * 根据查询条件查询单个出库信息
	 */
	@Override
	public MaterExport selectExportMasterByPrimaryKey(MaterExport materEx) {
		return deviceDao.selectExportMasterByPrimaryKey(materEx);
	}

	/**
	 * 查询出库单下对应多少条出库明细信息
	 */
	@Override
	public int countExportMasterDetail(String exorderNo) {
		// TODO Auto-generated method stub
		return deviceDao.countExportMasterDetail(exorderNo);
	}

	/**
	 * 查询出库单明细信息
	 */
	@Override
	public MaterExportDetail selectExportMasterDetail(MaterExportDetail mat) {
		// TODO Auto-generated method stub
		return deviceDao.selectExportMaterDetailByPrimaryKey(mat);
	}

	/**
	 * 查看所有的出库明细信息
	 */
	@Override
	public List<MaterExportDetail> getAllMaterExportDetail(
			MaterExportDetailFilter filter) {
		return deviceDao.getAllMasterExportDetail(filter);
	}
	/**
	 * 查看所有的出库明细信息
	 */
	@Override
	public List<MaterExportDetail> to_detaillist(
			MaterExportDetailFilter filter) {
		filter.setUserId(Securitys.getUserId());
		return deviceDao.getAllMasterExportDetail(filter);
	}
	
	/**
	 * 新增/修改 出库明细信息
	 */
	@Override
	public void saveOrUpdateExportMaterDetail(
			MaterExportDetail materExportDetail) {
		String type = materExportDetail.getType();
		String projectId = Securitys.getUser().getCurrent_projectId();
		materExportDetail.setProjectId(projectId);
		materExportDetail.setCreateBy(Securitys.getUserName());
		materExportDetail.setCreateDate(new Date());

		if ("1".equals(type)) {
			// 设备档案号需要自动生成
			compareMasterDetailAndMasterStore(null, materExportDetail, 3);
			deviceDao.saveMaterExportDetail(materExportDetail);
		} else if ("2".equals(type)) {
			deviceDao.updateMaterExportDetail(materExportDetail);
		}
	}

	public void saveOrUpdateExportMaterDetail(
			MaterExportDetail materExportDetail, boolean flag) {
		String type = materExportDetail.getType();
		String projectId = Securitys.getUser().getCurrent_projectId();
		materExportDetail.setProjectId(projectId);
		materExportDetail.setCreateBy(Securitys.getUserName());
		materExportDetail.setCreateDate(new Date());

		if ("1".equals(type)) {
			// 设备档案号需要自动生成
			compareMasterDetailAndMasterStore(null, materExportDetail, 3, flag);
			try {
				deviceDao.saveMaterExportDetail(materExportDetail);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if ("2".equals(type)) {
			deviceDao.updateMaterExportDetail(materExportDetail);
		}
	}

	/**
	 * description: 反向在目标单位目标库房生成库存 该方法包含新增和修改库存的逻辑
	 * 
	 * @author yangfeng
	 * @param exportAccount
	 * @param exportLccCode2
	 * @param exportLccCode
	 * @param materExportDetail
	 * @update 2015-12-10
	 */
	private void ReverseMasterStore(MaterStore store, String exportLccCode,
			String exportStockCode, Long exportAccount) {
		MaterStore ms = new MaterStore();
		Beans.copyProperties(store, ms, false);
		ms.setLccCode(exportLccCode);
		ms.setStockCode(exportStockCode);
		ms.setStoreAmount(exportAccount);
		List<MaterStore> selectMasterStore = deviceDao.selectMasterStore2(ms);

		if (selectMasterStore.size() == 1) {
			// 已经存在要更新
			MaterStore materStore = selectMasterStore.get(0);
			materStore.setStoreAmount(materStore.getStoreAmount()
					+ exportAccount);
			deviceDao.updateMaterStore(materStore);
		} else {
			// 插入
			deviceDao.insertMaterStore1(ms);
		}
	}

	/**
	 * 删除出库明细信息
	 */
	@Override
	public void deleteExportMaterDetail(MaterExportDetail masterEx) {
		// TODO Auto-generated method stub
		compareMasterDetailAndMasterStore(null, masterEx, 4);
		deviceDao.deleteExportMaterDetail(masterEx);
	}

	@Override
	public List<MaterStore> selectJsonForAutocomplete(MaterStore ms) {
		return deviceDao.selectMasterStore(ms);
	}

	/**
	 * 检查入库单号是否存在
	 */
	@Override
	public MaterExport checkExportMasterIsExists(String exorderNo) {
		// TODO Auto-generated method stub
		return deviceDao.checkExportMasterIsExists(exorderNo);
	}

	/**
	 * 查询库房明细信息
	 */
	@Override
	public MaterStore selectMaterStore(MaterStore ms) {
		// TODO Auto-generated method stub
		return deviceDao.selectMasterStore(ms).get(0);
	}

	@Override
	public List<MaterStore> getAllStocks(StockFilter filter) {
		return deviceDao.selectAllMaterStore(filter);
	}

	@Override
	public List<MaterStore> selectMaterStoreLimit(StockFilter filter) {
		filter.setUserId(Securitys.getUserId());
		return deviceDao.selectMaterStoreLimit(filter);
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.material.supp.service.DeviceService#saveImportAndDetail(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void saveImportAndDetail(String m, String list) throws Exception {
		// {"imorderNo":"999999","activeclassCode":"0001002","lccCode":"99","userName":"京东","userCode":"601001","stockName":"国家心血管病中心库房","stockCode":"99001","createDate":"","materlName":"采样器具包","materlCode":"MA0000000000003","materlinfoCode":"MA0000000000800","materlBatch":"1","wholesalePrice":"2","materlPrice":"12","importAmount":"1","storeUnit":"12","periodValidity":"2015-12-02","supplierName":"浙江黄岩埃迪","supplierCode":"MA0000000000002","assetsStatus":"1","isMeasure":"1","nextMeasureDate":"2015-12-03","measurePeriod":"21","archivesNo":"","classCode":"","type":1}
		// [{"id":"","materlName":"采样器具包","materlBatch":"1","materlPrice":"12","wholesalePrice":"2","importAmount":"1","storeUnit":"12","periodValidity":"2015-12-02","supplierName":"浙江黄岩埃迪","assetsStatus":"<span class=\"label label-success\">可用</span>","isMeasure":"<span class=\"label label-success\">需要计量</span>","nextMeasureDate":"2015-12-03","measurePeriod":"21","imorderNo":"999999","type":1}]
		JsonMapper jm = new JsonMapper();
		MasterImport masterImport = jm.fromJson(m, MasterImport.class);
		saveOrUpdateImportMaster(masterImport);
		// MATERLINFO_CODE
		list = list.replaceAll("不可用", "2");
		list = list.replaceAll("可用", "1");
		list = list.replaceAll("不需要计量", "2");
		list = list.replaceAll("需要计量", "1");
		List<MasterImportDetail> l = jm.fromJson(list,
				jm.createCollectionType(List.class, MasterImportDetail.class));
		for (MasterImportDetail masterImportDetail : l) {
			masterImportDetail.setSupplierCode(masterImport.getSupplierCode());
			masterImportDetail.setSupplierName(masterImport.getSupplierName());
			String archivesNo = masterImportDetail.getArchivesNo();
			MasterImportDetail checkArchivesNo = checkArchivesNo(archivesNo);
			if (null != checkArchivesNo) {
				throw new Exception("设备编号重复");
			}
			saveOrUpdateImportMasterDetail(masterImportDetail);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.material.supp.service.DeviceService#saveOrUpdateExportMaterAndDetail(java.lang.String,
	 *      java.lang.String)
	 */
	@Override
	public void saveOrUpdateExportMaterAndDetail(String m, String list) {
		JsonMapper jm = new JsonMapper();
		//m:{"exorderNo":"20160111105701","activeclassCode":"0001011","exportDate":"2016-01-06","userName":"淘宝","userCode":"601002","lccName":"成都市郫县人民医院","lccCode":"601","stockName":"成都市郫县人民医院库房","stockCode":"601001","exportLccName":"国家心血管病中心","exportLccCode":"99","exportStockName":"国家心血管并中心库房二","exportStockCode":"99002","supplierName":"test1厂家"}
		//[{"archivesNo":"DDDXXX004","supplierCode":"MA0000000001200","materlinfoCode":"MA0000000000800","materlName":"采样器具包","materlBatch":"1","materlPrice":"1","wholesalePrice":"1","importAmount":"","storeUnit":"NCCD","periodValidity":"2016-01-15","supplierName":"浙江黄岩埃迪","assetsStatus":"可用","isMeasure":"需要计量","nextMeasureDate":"2016-01-07","measurePeriod":"","exorderNo":"20160111105701","type":1}]
		MaterExport materExport = jm.fromJson(m, MaterExport.class);
		materExport.setType("1");
		saveOrUpdateExportMater(materExport);
		list = list.replaceAll("不可用", "2");
		list = list.replaceAll("可用", "1");
		list = list.replaceAll("不需要计量", "2");
		list = list.replaceAll("需要计量", "1");
		// 这个是用来判断是否需要进行 反向在对方库中生成库存数量
		boolean isFlag = false;
		// 如果是 出库类别是 发放出库的话
		if (!StringUtils.isEmpty(materExport.getActiveclassCode())
				&& materExport.getActiveclassCode().equals("0001011")) {
			isFlag = true;
		}
		//[{"archivesNo":"DX005","supplierCode":"MA0000000000002","materlinfoCode":"MA0000000000800","materlName":"采样器具包","materlBatch":"1","materlPrice":"1","wholesalePrice":"1","importAmount":"","storeUnit":"NCCD","periodValidity":"2016-01-15","supplierName":"浙江黄岩埃迪","assetsStatus":"1","isMeasure":"1","nextMeasureDate":"2016-01-14","measurePeriod":"","exorderNo":"20160111112830","type":1}]
		List<MaterExportDetail> j = jm.fromJson(list,
				jm.createCollectionType(List.class, MaterExportDetail.class));
		for (MaterExportDetail materExportDetail : j) {
			materExportDetail.setExportLccCode(materExport.getExportLccCode());
			materExportDetail.setExportStockCode(materExport
					.getExportStockCode());
			materExportDetail.setStockCode(materExport.getStockCode());
			saveOrUpdateExportMaterDetail(materExportDetail, isFlag);
		}
	}

	/**
	 * TODO 简单描述该方法的实现功能（可选）.
	 * 
	 * @see com.bdcor.pip.web.material.supp.service.DeviceService#checkArchivesNo(java.lang.String)
	 */
	@Override
	public MasterImportDetail checkArchivesNo(String archivesNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("archivesNo", archivesNo);
		map.put("projectId", Securitys.getUser().getCurrent_projectId());
		return deviceDao.selectImportMasterDetailByArchivesNoProjectId(map);
	}

	@Override
	public List<MaterExportDetail> getAllMaterExportDetail(String exorderNo) {
		MaterExportDetailFilter filter =new MaterExportDetailFilter();
		filter.setExorderNo(exorderNo);
		return getAllMaterExportDetail(filter);
	}

	@Override
	public MasterImport selectMasterImportByImorderNo(String imorderNo) {
		MaterImportFilter filter = new MaterImportFilter();
		filter.setImorderNo(imorderNo);
		filter.setProjectId(Securitys.getUser().getCurrent_projectId());
		return deviceDao.selectMasterImportByImorderNo(filter);
	}

	@Override
	public MaterExport checkExorderNo(String exorderNo)
	{
		return deviceDao.selectMaterExportByExorderNo(exorderNo);
	}
}
