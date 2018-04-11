package com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.dataChecking;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.bdcor.pip.core.utils.SpringContextHolder;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.web.data.dao.PipCommPatientInputMapper;
import com.bdcor.pip.web.data.domain.PipCommPatientInputExample;
import com.bdcor.pip.web.pro.promgt.service.patientExcelResolving.obj.ExcelPatientOfIdCordObj;

/**
 * 身份证数据导入数据验证
 * @author Administrator
 *
 */
public class DataCheckingOfIDCord extends DataChecking{

	
	
	
	
	ExcelPatientOfIdCordObj obj = null;
	
	public List<ExcelErrorMsg> dataChecking(ExcelPatientOfIdCordObj obj){
		List<ExcelErrorMsg> l = super.dataChecking(obj);
		return l;
	}

	@Override
	protected int lccColNum() {
		return ExcelPatientOfIdCordObj.COL_NUM_LCCCODE;
	}

	@Override
	protected int nameColNum() {
		return ExcelPatientOfIdCordObj.COL_NUM_PATIENTNAME;
	}

	@Override
	protected int idnumberColNum() {
		return ExcelPatientOfIdCordObj.COL_NUM_IDNUMBER;
	}

	@Override
	protected int phoneColNum() {
		return ExcelPatientOfIdCordObj.COL_NUM_PHONE;
	}

	@Override
	protected int mobileColNum() {
		return ExcelPatientOfIdCordObj.COL_NUM_MOBILE;
	}
	
	
	
}
