package com.bdcor.pip.web.material.supp.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.bdcor.pip.core.persistence.MyBatisRepository;
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

@MyBatisRepository
public interface MaterialDao {
	/**
	 * 查询所有的入库/出库类别
	 * 
	 * @return
	 */
	public List<MaterialActiveType> selectActiveTypeByState(
			@Param(value = "state") String state,
			@Param(value = "projectId") String projectId);

	/**
	 * 查询入库信息
	 * 
	 * @param masterIn
	 * @return
	 */
	public MasterImport selectImportMasterByPrimaryKey(MasterImport masterIn);

	public List<MasterImport> getAllMasterImports(MaterImportFilter fliter);

	/**
	 * 添加入库信息
	 * 
	 * @param masterIn
	 */
	public void saveImportMaster(MasterImport masterIn);

	/**
	 * 更新入库信息
	 * 
	 * @param masterIn
	 */
	public void updateImportMaster(MasterImport masterIn);

	/**
	 * 删除入库信息
	 * 
	 * @param masterIn
	 */
	public void deleteImportMaster(MasterImport masterIn);

	/**
	 * 添加入库明细信息
	 * 
	 * @param masterIn
	 */
	public void saveImportMasterDetail(MasterImportDetail masterInDetail);

	/**
	 * 更新入库明细信息
	 * 
	 * @param masterIn
	 */
	public void updateImportMasterDetail(MasterImportDetail masterInDetail);

	/**
	 * 删除入库明细信息
	 * 
	 * @param masterIn
	 */
	public void deleteImportMasterDetail(MasterImportDetail masterInDetail);

	/**
	 * 查询入库明细信息
	 * 
	 * @param masterIn
	 * @return
	 */
	public MasterImportDetail selectImportMasterDetail(
			MasterImportDetail masterIn);

	public List<MasterImportDetail> getAllMasterDetail(
			MaterImportDetailFilter fliter);

	public int countImportMasterDetail(
			@Param(value = "imorderNo") String imorderNo);

	/**
	 * 删除入库单下面对应的资产明细信息
	 * 
	 * @param masterIn
	 */
	public void deleteImportMasterDetailWithImorderId(MasterImport masterIn);

	/**
	 * 查询库存信息
	 * 
	 * @return
	 */
	public List<MaterStore> selectMasterStore(MaterStore ms);

	/**
	 * 
	 * description: 查询
	 * 
	 * @author yangfeng
	 * @param ms
	 * @return
	 * @update 2015-12-10
	 */
	public List<MaterStore> selectMasterStore2(MaterStore ms);

	public void insertMaterStore(MaterStore ms);

	public void updateMaterStore(MaterStore ms);

	public String getStockCodeByImorderNo(Map<String, Object> params);

	/**
	 * 查询出库信息
	 * 
	 * @param masterIn
	 * @return
	 */
	public MaterExport selectExportMasterByPrimaryKey(MaterExport masterEx);

	public MaterExport checkExportMasterIsExists(
			@Param(value = "exorderNo") String exorderNo);

	public List<MaterExport> getAllMasterExports(MaterExportFilter fliter);
	
	public List<MaterExport> getAllMaterExportsByExportNo(@Param("exportNos")String [] exportNos);
	
	
	/**
	 * 添加出库信息
	 * 
	 * @param masterIn
	 */
	public void saveExportMaster(MaterExport masterEx);

	/**
	 * 更新出库信息
	 * 
	 * @param masterIn
	 */
	public void updateExportMaster(MaterExport masterEx);

	/**
	 * 添加出库明细信息
	 * 
	 * @param meDetail
	 */
	public void saveMaterExportDetail(MaterExportDetail meDetail);

	public void updateMaterExportDetail(MaterExportDetail meDetail);

	/**
	 * @param meDetail
	 * @return
	 */
	public MaterExportDetail selectExportMaterDetailByPrimaryKey(
			MaterExportDetail meDetail);

	/**
	 * 查询出库明细分页信息
	 * 
	 * @param filter
	 * @return
	 */
	public List<MaterExportDetail> getAllMasterExportDetail(
			MaterExportDetailFilter filter);

	public int countExportMasterDetail(
			@Param(value = "exorderNo") String exorderNo);

	/**
	 * 删除出库明细信息
	 * 
	 * @param masterEx
	 */
	public void deleteExportMaterDetail(MaterExportDetail masterEx);

	public String getStockCodeByExorderNo(Map<String, Object> params);

	public List<MaterStore> selectAllMaterStore(StockFilter filter);

	public List<MaterStore> selectMaterStoreLimit(StockFilter filter);

	public String getStoreCodeByExorderNo(Map<String, Object> params);

	/**
	 * description: list
	 * 
	 * @author yangfeng
	 * @param ids
	 * @return
	 * @update 2015-12-22
	 */
	public List<MasterImport> listByIds(@Param("ids") String [] ids);
}
