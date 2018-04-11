package com.bdcor.pip.web.data.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.filter.PatientFilter;

public interface PatientService {

	public Map<String, Object> checkData(File file, File template, File xml);

	public void dropPatient(String projectId, String patientId)
			throws Exception;

	public void updatePatient(String projectId, String patientId,
			String targetPatId, String sourceId, String items,
			Map<String, Object> params) throws Exception;

	public void exchangePatient(String projectId, String patientId,
			String patientId2) throws Exception;

	public void dropPatientUqs(String projectId, String patientId,
			String itemCode) throws Exception;

	public void copyPatientUqs(String projectId, String patientId,
			String targetId, String itemCode, String dropDate) throws Exception;

	public void updatePatientCode(String projectId, String idNumber,
			String patientId, String riskCode, String delPat, String delRisk)
			throws Exception;

	public Workbook getExcelData(File file);

	public boolean checkTemplate(Workbook wb, Workbook tb, int headerRow);

	public List<PipCommPatient> queryPatientList(PatientFilter pf);

	public void updateByPatientList(PatientFilter pf);

	public int updateByPrimaryKey(PipCommPatient record);

	public int updateByPatientId(PipCommPatient record);

	public int insertSelective(PipCommPatient record);
	public int updateBySelevePrimaryKey(PipCommPatient record);
	/**
	 * description:查找一条记录
	 * 
	 * @author yangfeng
	 * @param patientId
	 * @return
	 * @update 2015-12-2
	 */
	public PipCommPatient selectByKey(String patientId);
}
