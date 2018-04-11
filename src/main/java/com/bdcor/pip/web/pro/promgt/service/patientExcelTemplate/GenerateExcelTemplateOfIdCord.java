package com.bdcor.pip.web.pro.promgt.service.patientExcelTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import com.bdcor.pip.web.pro.promgt.service.patientExcelTemplate.obj.AreaObj;
import com.bdcor.pip.web.pro.promgt.service.patientExcelTemplate.util.ExcelHelper;

/**
 * 生成 身份证 导入的excel 模板
 * @author Administrator
 *
 */
public class GenerateExcelTemplateOfIdCord extends GenerateExcelTemplate{
	
	public static int areaColBegin = 5;
	/* 可编辑的列数   */
	public static int editColCount = 5;
	/*  模板标记    */
	public static String templateFlag = "idCord";
	
	private static String[] headColNames = new String[]{"所属项目LCCID*","姓名*","身份证号*","电话","手机"};

	
	public GenerateExcelTemplateOfIdCord(File templetFile,AreaObj area) throws FileNotFoundException, IOException{
		super(templetFile,area,headColNames);
	}


	@Override
	protected void otherSet() {
		// TODO Auto-generated method stub
		
	}


	@Override
	protected String templateFlag() {
		return templateFlag;
	}
	
	

	
}
