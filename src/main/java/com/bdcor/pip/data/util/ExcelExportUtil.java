
package com.bdcor.pip.data.util;

import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.Workbook;

/**  
 * description:  导出excel 普通通用导出
 * @author xu.sh 创建时间：2016年10月9日         
 */
public class ExcelExportUtil{
	
	private static List<Map<String,Object>> list;
	
	private static String[] headerArray;
	
	private static Object[] dataNameArray;
	
	private static Workbook workbook;
	
	private static ExcelExportConfig config;
	

	/**
	 * description:填充 excel
	 * @author xu.sh  
	 * @param list  数据
	 * @param headerArray  表头
	 * @param dataNameArray 数据对应的name
	 * @update 2016年10月9日
	 */
	public static void fillExcel(List<Map<String,Object>> list, String[] headerArray, 
			Object[] dataNameArray,Workbook workbook,ExcelExportConfig config){
		
		if(list==null)list = new ArrayList<Map<String,Object>>();
		ExcelExportUtil.list = list;
		ExcelExportUtil.headerArray = headerArray;
		ExcelExportUtil.dataNameArray = dataNameArray;
		ExcelExportUtil.workbook = workbook;
		if(config == null)config=new ExcelExportConfig();
		ExcelExportUtil.config = config;
		
		if(list.size()==0){
			createAndFillSheet(0);
			return;
		}
		
		int page =0;
		
		//分sheet页打印
		for(;page<(list.size()+config.getSheetRowCount()-1)/config.getSheetRowCount(); page++){
			createAndFillSheet(page);
		}
	}
	
	
	private static void createAndFillSheet(int page){
		String sheetName  = null;
		if(config.getSheetName()==null){
			sheetName = "第"+(page+1)+"页";
		}else{
			sheetName = config.getSheetName();
			if((list.size()+config.getSheetRowCount()-1)/config.getSheetRowCount()>1){
				sheetName += "_"+(page+1);
			}
		}
		
		Sheet sheet = workbook.createSheet(sheetName);
		createSheetHeader(sheet);
		
		CellStyle defaultStyle = createDefaultDataStyle();
		for(int j = 0;j<config.getSheetRowCount();j++){
			Row row = sheet.createRow(j+1);
			row.setHeight((short) config.getRowHeight());
			if(list.size()<=page*config.getSheetRowCount()+j)break;
			Map map = list.get(page*config.getSheetRowCount()+j);
			for(int h = 0; h<dataNameArray.length;h++){
				if(map.get(dataNameArray[h])==null || map.get(dataNameArray[h])==""){
					continue;
				}
				Cell cell = row.createCell(h);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				if(config.getDataCellStyle().get(h)==null){
					cell.setCellStyle(defaultStyle);
				}else{
					cell.setCellStyle(config.getTitleCellStyle().get(h));
				}
				cell.setCellValue(map.get(dataNameArray[h]).toString());
			}
		}
		
		//列宽
		for(int k = 0; k<dataNameArray.length;k++){
			if(config.getColWidth().get(k)==null){
				sheet.autoSizeColumn(k);
				int w = sheet.getColumnWidth(k);
				sheet.setColumnWidth(k, (int) Math.round(w*1.6));
			}else{
				sheet.setColumnWidth(k, config.getColWidth().get(k));
			}
		}
	}
	
	private static void createSheetHeader(Sheet sheet){
		Row row = sheet.createRow(0);
		row.setHeight((short) config.getTitleRowHeight());
		CellStyle defaultStyle = createDefaultTitleStyle();
		for(int i =0;i<headerArray.length; i++){
			Cell cell = row.createCell(i);
			if(config.getTitleCellStyle().get(i)==null){
				cell.setCellStyle(defaultStyle);
			}else{
				cell.setCellStyle(config.getTitleCellStyle().get(i));
			}
			cell.setCellType(Cell.CELL_TYPE_STRING);
			cell.setCellValue(headerArray[i]);
		}
	}
	
	
	
	
	/**
	 * 标题默认样式 : 居中,背景灰,16,加粗
	 * @param workbook
	 * @return
	 */
	private static CellStyle createDefaultTitleStyle(){
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
	
	/**
	 * 数据行默认样式 居左,12
	 * @param workbook
	 * @return
	 */
	private static CellStyle createDefaultDataStyle(){
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		
		// 生成一个字体
		Font font = workbook.createFont();
		font.setFontHeightInPoints((short) 12);
		
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}
}

