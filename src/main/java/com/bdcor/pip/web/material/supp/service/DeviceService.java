/**
 * 
 */
package com.bdcor.pip.web.material.supp.service;

import java.util.List;

import com.bdcor.pip.core.exception.ServiceException;
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

/**
 * 设备管理
 * 
 * @author rp
 * 
 */
public interface DeviceService
{

	public List<MaterialActiveType> selectActiveTypeByState(String state, String projectId);

	public void saveOrUpdateImportMaster(MasterImport masterIn);

	public MasterImport selectImportMasterByPrimaryKey(MasterImport masterImport);

	public void delete(MasterImport masterImport);

	public int countImportMasterDetail(String imorderNo);

	public List<MasterImport> getAllMaterImports(MaterImportFilter fliter);

	public void saveOrUpdateImportMasterDetail(MasterImportDetail masterInDetail);

	public MasterImportDetail selectImportMasterDetail(MasterImportDetail masterInDetail);

	public MasterImportDetail checkArchivesNo(String archivesNo);

	void deleteImportMasterDetail(MasterImportDetail masterInDetail);

	List<MasterImportDetail> getAllMaterDetail(MaterImportDetailFilter masterInDetail);
	List<MasterImportDetail> to_detaillist(MaterImportDetailFilter masterInDetail);

	public MaterExport selectExportMasterByPrimaryKey(MaterExport materEx);

	public List<MaterExport> getAllMaterExports(MaterExportFilter fliter);

	public void saveOrUpdateExportMater(MaterExport materEx);

	public int countExportMasterDetail(String exorderNo);

	public MaterExportDetail selectExportMasterDetail(MaterExportDetail mat);

	public List<MaterExportDetail> getAllMaterExportDetail(MaterExportDetailFilter filter);
	public List<MaterExportDetail> to_detaillist(MaterExportDetailFilter filter);
	
	public void saveOrUpdateExportMaterDetail(MaterExportDetail materExportDetail) throws ServiceException;

	public void deleteExportMaterDetail(MaterExportDetail masterEx);

	public List<MaterStore> selectJsonForAutocomplete(MaterStore ms);

	public MaterExport checkExportMasterIsExists(String exorderNo);

	public void batchPutImportDetailToStore(String imorderNo);

	public MaterStore selectMaterStore(MaterStore ms);

	List<MaterStore> getAllStocks(StockFilter filter);

	List<MaterStore> selectMaterStoreLimit(StockFilter filter);

	/**
	 * description: m 和list都是要转成字符串的用来一次性生成订单和订单内容
	 * 
	 * @author yangfeng
	 * @param m
	 * @param list
	 * @update 2015-12-14
	 */
	public void saveImportAndDetail(String m, String list) throws Exception;

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param m
	 * @param list
	 * @update 2015-12-15
	 */
	public void saveOrUpdateExportMaterAndDetail(String m, String list);

	/**
	 * 根据订单号查找信息 exportNo 用 ,分开
	 * 
	 * @param ids
	 * @return
	 */
	public List<MaterExport> getAllMaterExportsByExportNo(String exportNos);

	public List<MaterExportDetail> getAllMaterExportDetail(String exorderNo);

	public MasterImport selectMasterImportByImorderNo(String imorderNo);

	/**
	 * 确定出库单号的唯一
	 * @param exorderNo
	 * @return
	 */
	public MaterExport checkExorderNo(String exorderNo);

}
