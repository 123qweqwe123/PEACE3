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
 * 物质管理
 * 
 * @author rp
 * 
 */
public interface MaterialService {

	public List<MaterialActiveType> selectActiveTypeByState(String state,
			String projectId);

	public void saveOrUpdateImportMaster(MasterImport masterIn);

	public MasterImport selectImportMasterByPrimaryKey(MasterImport masterImport);

	public void delete(MasterImport masterImport);

	public int countImportMasterDetail(String imorderNo);

	public List<MasterImport> getAllMaterImports(MaterImportFilter fliter);

	public void saveOrUpdateImportMasterDetail(MasterImportDetail masterInDetail);

	public MasterImportDetail selectImportMasterDetail(
			MasterImportDetail masterInDetail);

	void deleteImportMasterDetail(MasterImportDetail masterInDetail);

	List<MasterImportDetail> getAllMaterDetail(
			MaterImportDetailFilter masterInDetail);

	public MaterExport selectExportMasterByPrimaryKey(MaterExport materEx);

	public List<MaterExport> getAllMaterExports(MaterExportFilter fliter);
	public List<MaterExport> getAllMaterExportsByExportNo(String exportNos);

	public void saveOrUpdateExportMater(MaterExport materEx);

	public int countExportMasterDetail(String exorderNo);

	public MaterExportDetail selectExportMasterDetail(MaterExportDetail mat);

	public List<MaterExportDetail> getAllMaterExportDetail(
			MaterExportDetailFilter filter);

	public void saveOrUpdateExportMaterDetail(
			MaterExportDetail materExportDetail) throws ServiceException;

	public void deleteExportMaterDetail(MaterExportDetail masterEx);

	public List<MaterStore> selectJsonForAutocomplete(MaterStore ms);

	public MaterExport checkExportMasterIsExists(String exorderNo);

	public MaterStore selectMaterStore(MaterStore ms);

	List<MaterStore> getAllStocks(StockFilter filter);

	List<MaterStore> getAllStocksLimit(StockFilter filter);

	public void addMasterExport(MaterExport materExport);

	/**
	 * description:通过ids查询 订单
	 * 
	 * @author yangfeng
	 * @param ids
	 * @return
	 * @update 2015-12-22
	 */
	public List<MasterImport> listByIds(String ids) throws Exception;

	/**
	 * description:
	 * 
	 * @author yangfeng
	 * @param imorderNo
	 * @return
	 * @update 2015-12-22
	 */
	public List<MasterImportDetail> getAllMaterDetail(String imorderNo);

	public List<MaterExportDetail> getAllMaterExportDetail(String exorderNo);

}
