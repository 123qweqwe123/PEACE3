/**
 * JAVACC DEMO 1.0
 * @copy right zbxsoft company All rights reserved. 
 * @Package com.bdcor.pip.data.util 
 */

package com.bdcor.pip.data.util;

import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

/**  
 * description:  导出excel数据超过2W条数据的话建议使用这个
 * @author yangfeng 创建时间：2016年1月12日         
 */
public class ExcelExportBatchUtil
{
	//设置单个sheet存放多少条记录
	public final static int SHEET_COUNT = 20000;
	//设置初始化SXSSFWorkbook所在窗口的row的行数 类似于预先开启一定量的row的行数  麻痹没有看懂
	public final static int SXSSWORKBOOK_MEMORY =3000;
	/**
	 * 
	 * description:  创建并填充 excel
	 * @author yangfeng  
	 * @param list  数据
	 * @param headerArray  表头
	 * @param dataNameArray 数据对应的name
	 * @update 2016年1月12日
	 */
	public static void createAndFillingExcel(List<Map<String,Object>> list, String[] headerArray, Object[] dataNameArray)
	{	
		SXSSFWorkbook workbook = new SXSSFWorkbook(SXSSWORKBOOK_MEMORY); 
		int page =0;
		//分sheet页打印
		for(;page<list.size()/SHEET_COUNT; page++){
			createAndFillingSheet(list, headerArray, dataNameArray, workbook, page);
		}
		int mod = list.size() % SHEET_COUNT;
		//余数页打印
		if(mod>0){
			createAndFillingSheetMod(list, headerArray, dataNameArray, workbook, page, mod);
		}
	}
	/**
	 * 
	 * description:  创建并填充 excel
	 * @author yangfeng  
	 * @param list  数据
	 * @param headerArray  表头
	 * @param dataNameArray 数据对应的name
	 * @param workbook  SXSSFWorkbook对象
	 * @update 2016年1月12日
	 */
	public static void createAndFillingExcel(List<Map<String,Object>> list, String[] headerArray, Object[] dataNameArray,SXSSFWorkbook workbook)
	{	
		int page =0;
		
		if(null == list || list.isEmpty() || list.size()<=0){
			Sheet sheet = workbook.createSheet("第"+(page+1)+"页");
			createSheetHeader(workbook, sheet, headerArray);
			return ;
		}
		
		//分sheet页打印
		for(;page<list.size()/SHEET_COUNT; page++){
			createAndFillingSheet(list, headerArray, dataNameArray, workbook, page);
		}
		int mod = list.size() % SHEET_COUNT;
		//余数页打印
		if(mod>0){
			createAndFillingSheetMod(list, headerArray, dataNameArray, workbook, page, mod);
		}
	}

	/**
	 * 
	 * description:  整段处理   SHEET_COUNT页  处理方法   不处理余数页
	 * @author yangfeng  
	 * @param list
	 * @param headerArray
	 * @param dataNameArray
	 * @param workbook
	 * @param page   
	 * @update 2016年1月12日
	 */
	public static void createAndFillingSheet(List<Map<String,Object>> list, String[] headerArray, Object[] dataNameArray, SXSSFWorkbook workbook,
			int page)
	{
		Sheet sheet = workbook.createSheet("第"+(page+1)+"页");
		createSheetHeader(workbook, sheet, headerArray);
		for(int j =page*SHEET_COUNT; j<(page+1)*SHEET_COUNT;j++){
			Row row = sheet.createRow(j+1-page*SHEET_COUNT);
			Map map = list.get(j);
			for(int h = 0; h<dataNameArray.length;h++){
				if(map.get(dataNameArray[h])==null || map.get(dataNameArray[h])==""){
					continue;
				}
				Cell cell = row.createCell(h);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(map.get(dataNameArray[h]).toString());
			}
		}
	}
	/**
	 * 
	 * description:   对于到 SHEET_COUNT 余数的处理
	 * @author yangfeng  
	 * @param list
	 * @param headerArray
	 * @param dataNameArray
	 * @param workbook
	 * @param page
	 * @param mod   
	 * @update 2016年1月12日
	 */
	public static void createAndFillingSheetMod(List<Map<String,Object>> list, String[] headerArray, Object[] dataNameArray, SXSSFWorkbook workbook,
			int page, int mod)
	{
		Sheet sheet = workbook.createSheet("第"+(page+1)+"页");
		createSheetHeader(workbook, sheet, headerArray);
		for(int j =page*SHEET_COUNT; j<(page)*SHEET_COUNT+mod;j++){
			Row row = sheet.createRow(j+1-page*SHEET_COUNT);
			Map map = list.get(j);
			for(int h = 0; h<dataNameArray.length;h++){
				if(map.get(dataNameArray[h])==null || map.get(dataNameArray[h])==""){
					continue;
				}
				Cell cell = row.createCell(h);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				cell.setCellValue(map.get(dataNameArray[h]).toString());
			}
		}
		
		// 列宽
		for(int k = 0; k<dataNameArray.length;k++){
			sheet.autoSizeColumn(k);
//			int w = sheet.getColumnWidth(k);
            // dataNameArray 与 headNameArray的长度是完全一致的
            // int nameWidth = (int)(headerArray[k].getBytes().length + 0.72)*256;
            // 单元格Cell最大长度不大于255字节宽带  每个字节宽度是 1*256
			// 所以做大宽带是 255*256 = 65280 超出会抛异常
			sheet.setColumnWidth(k,
                 sheet.getColumnWidth(k)*1.7 >= 65280 ? 65280 : (int)(sheet.getColumnWidth(k)*1.7)
            );
		}
	}
	/**
	 * 
	 * description:  设置表头
	 * @author yangfeng  
	 * @param workbook
	 * @param
	 * @param headerArray   
	 * @update 2016年1月12日
	 */
	public static void createSheetHeader(SXSSFWorkbook workbook, Sheet sheet, String[] headerArray)
	{
		Row createRow = sheet.createRow(0);
		createRow.setHeight((short) 345);
		for(int i =0;i<headerArray.length; i++){
			Cell cell = createRow.createCell(i);
			cell.setCellStyle(createCellStyle(workbook));
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(headerArray[i]);
		}
	}
	/**
	 * 
	 * description: 设置单元格的样式 
	 * @author yangfeng  
	 * @param workbook
	 * @return   
	 * @update 2016年1月12日
	 */
	public static  CellStyle createCellStyle(SXSSFWorkbook workbook){
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
		
		// 生成一个字体
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 16);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}
}

