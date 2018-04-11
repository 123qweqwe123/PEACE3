package com.bdcor.pip.web.spem.service;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
* 生成excel视图，可用excel工具打开或者保存
*/
public class PlanExcel extends AbstractExcelView {   
   
    public void buildExcelDocument(Map model, HSSFWorkbook workbook,   
            HttpServletRequest request, HttpServletResponse response)   
            throws Exception {  
    	
    	String excelName = "样本转运计划.xls";
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(excelName, "UTF-8"));
		HSSFSheet sheet = workbook.createSheet("样本转运计划");
		
		//合并单元格，参数依次为起始行，结束行，起始列，结束列
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0,1));//1,1 1,2
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 2,15));//1,1 3,16
        sheet.addMergedRegion(new CellRangeAddress(1, (short) 1, 0,15));//2,2 1,16
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 0,0));
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 1,1));
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 2,2));
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 3,3));
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 4,4));
        
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 12,12));
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 13,13));
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 14,14));
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 3, 15,15));
        
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 5,7));
        sheet.addMergedRegion(new CellRangeAddress(2, (short) 2, 8,10));
        
        sheet.setColumnWidth( 0, 1500);
        sheet.setColumnWidth( 1, 1800);
        sheet.setColumnWidth( 2, 1800);
        sheet.setColumnWidth( 3, 4800);
        sheet.setColumnWidth( 4, 2800);
        sheet.setColumnWidth( 5, 2200);
        sheet.setColumnWidth( 6, 2200);
        sheet.setColumnWidth( 7, 2200);
        sheet.setColumnWidth( 8, 2200);
        sheet.setColumnWidth( 9, 2200);
        sheet.setColumnWidth( 10,2200);
        sheet.setColumnWidth( 11,2200);
        sheet.setColumnWidth( 12,2200);
        sheet.setColumnWidth( 13,4800);
        sheet.setColumnWidth( 14,4800);
        sheet.setColumnWidth( 15,6400);
        
        HSSFCellStyle style1 = getStyle1(workbook);
        HSSFCellStyle style2 = getStyle2(workbook);
        HSSFCellStyle style3 = getStyle3(workbook);
        HSSFCellStyle style4 = getStyle4(workbook);
        HSSFCellStyle style5 = getStyle5(workbook);
        HSSFCellStyle style6 = getStyle6(workbook);
        HSSFCellStyle style7 = getStyle7(workbook);
        HSSFCellStyle style8 = getStyle8(workbook);
        
        HSSFCellStyle dataStyle1_1 = getDataStyle1_1(workbook);
        HSSFCellStyle dataStyle1_2 = getDataStyle1_2(workbook);
        HSSFCellStyle dataStyle2_1 = getDataStyle2_1(workbook);
        HSSFCellStyle dataStyle2_2 = getDataStyle2_2(workbook);
        HSSFCellStyle dataStyle3_1 = getDataStyle3_1(workbook);
        HSSFCellStyle dataStyle3_2 = getDataStyle3_2(workbook);
        
        HSSFRow row0 = sheet.createRow(0); // 第0行
        row0.setHeight((short)915);
        HSSFCell row0_cell2 = row0.createCell(2);
        row0_cell2.setCellStyle(style1);
        row0_cell2.setCellValue("高危筛查项目生物样本转运计划_【"+new SimpleDateFormat("yyyy-MM-dd").format(new Date())+"】");
        
        HSSFRow row1 = sheet.createRow(1); // 第1行
        row1.setHeight((short)1470);
        HSSFCell row1_cell0 = row1.createCell(0);
        row1_cell0.setCellStyle(style2);
        row1_cell0.setCellValue("注：\n"+
        "1.每人份7支样本：紫、紫、红、红、黄、黄帽冻存管、EDTA采血管，分别存于A、B、C、D、E、F、G冻存盒中，每个冻存盒100人份样本\n"+
        "2.相同盒号的A与B\\C与D\\E与F盒需要分开包装箱运输\n"+
        "3.因EDTA采血管为各中心自己采购，管帽及外观可能不同，均存于大橘盒中\n"+
        "4.原则上每次只回收满100人份样本的冻存盒且同号码的7盒样本应该一起取回。末次样本回收时无论冻存盒是否放满，全部取回。");
        
        HSSFRow row2 = sheet.createRow(2); // 第2行
        row2.setHeight((short)345);
        HSSFCell row2_cell0 = row2.createCell(0);
        row2_cell0.setCellStyle(style3);
        row2_cell0.setCellValue("序号");
        
        HSSFCell row2_cell1 = row2.createCell(1);
        row2_cell1.setCellStyle(style3);
        row2_cell1.setCellValue("地区");
        
        HSSFCell row2_cell2 = row2.createCell(2);
        row2_cell2.setCellStyle(style3);
        row2_cell2.setCellValue("项目点ID");
        
        HSSFCell row2_cell3 = row2.createCell(3);
        row2_cell3.setCellStyle(style3);
        row2_cell3.setCellValue("单位名称");
        
        HSSFCell row2_cell4 = row2.createCell(4);
        row2_cell4.setCellStyle(style3);
        row2_cell4.setCellValue("样本回收日期");
        
        HSSFCell row2_cell5 = row2.createCell(5);
        row2_cell5.setCellStyle(style4);
        row2_cell5.setCellValue("运输箱1");
        
        HSSFCell row2_cell8 = row2.createCell(8);
        row2_cell8.setCellStyle(style4);
        row2_cell8.setCellValue("运输箱2");
        
        row2.createCell(6).setCellStyle(style4);
        row2.createCell(7).setCellStyle(style4);
        row2.createCell(9).setCellStyle(style4);
        row2.createCell(10).setCellStyle(style4);
        
        HSSFCell row2_cell11 = row2.createCell(11);
        row2_cell11.setCellStyle(style4);
        row2_cell11.setCellValue("运输箱1&2");
        
        HSSFCell row2_cell12 = row2.createCell(12);
        row2_cell12.setCellStyle(style3);
        row2_cell12.setCellValue("样本\n人份");
        
        HSSFCell row2_cell13 = row2.createCell(13);
        row2_cell13.setCellStyle(style3);
        row2_cell13.setCellValue("起始冻存盒编码");
        
        HSSFCell row2_cell14 = row2.createCell(14);
        row2_cell14.setCellStyle(style3);
        row2_cell14.setCellValue("结束冻存盒编码");
        
        HSSFCell row2_cell15 = row2.createCell(15);
        row2_cell15.setCellStyle(style3);
        row2_cell15.setCellValue("备注");
        
        HSSFRow row3 = sheet.createRow(3); // 第3行
        row3.setHeight((short)510);
        row3.createCell(0).setCellStyle(style3);
        row3.createCell(1).setCellStyle(style3);
        row3.createCell(2).setCellStyle(style3);
        row3.createCell(3).setCellStyle(style3);
        row3.createCell(4).setCellStyle(style3);
        row3.createCell(12).setCellStyle(style3);
        row3.createCell(13).setCellStyle(style3);
        row3.createCell(14).setCellStyle(style3);
        row3.createCell(15).setCellStyle(style3);
        
        HSSFCell row3_cell5 = row3.createCell(5);
        row3_cell5.setCellStyle(style5);
        row3_cell5.setCellValue("紫色小盒\nA/个");
        
        HSSFCell row3_cell6 = row3.createCell(6);
        row3_cell6.setCellStyle(style6);
        row3_cell6.setCellValue("白色小盒\nC/个");
        
        HSSFCell row3_cell7 = row3.createCell(7);
        row3_cell7.setCellStyle(style7);
        row3_cell7.setCellValue("黄色小盒\nE/个");
        
        HSSFCell row3_cell8 = row3.createCell(8);
        row3_cell8.setCellStyle(style5);
        row3_cell8.setCellValue("紫色小盒\nB/个");
        
        HSSFCell row3_cell9 = row3.createCell(9);
        row3_cell9.setCellStyle(style6);
        row3_cell9.setCellValue("白色小盒\nD/个");
        
        HSSFCell row3_cell10 = row3.createCell(10);
        row3_cell10.setCellStyle(style7);
        row3_cell10.setCellValue("黄色小盒\nF/个");
        
        HSSFCell row3_cell11 = row3.createCell(11);
        row3_cell11.setCellStyle(style8);
        row3_cell11.setCellValue("橘色小盒\nG/个");
        
        List<Map> list = (List<Map>) model.get("list"); 
        if(list == null || list.size()==0)return;
        
        int rowNum=3;
        String province = "-100";//当前行的省，换背景颜色的标记
        HSSFCellStyle dataStyle1 = dataStyle1_2;
        HSSFCellStyle dataStyle2 = dataStyle2_2;
        HSSFCellStyle dataStyle3 = dataStyle3_2;
        for(Map<String,Object> m: list){
        	
        	if(m.get("lccCode") == null || m.get("lccCode").toString().length()<2){
        		continue;
        	}
        	rowNum++;
        	HSSFRow dataRow = sheet.createRow(rowNum);
        	dataRow.setHeight((short)720);
        	if(!m.get("lccCode").toString().trim().substring(0, 2).equals(province)){
        		province = m.get("lccCode").toString().trim().substring(0, 2);
        		if(dataStyle1 == dataStyle1_1){
        			dataStyle1 = dataStyle1_2;
        		}else{
        			dataStyle1 = dataStyle1_1; //居中 无背景 
        		}
        		
        		if(dataStyle2 == dataStyle2_1){
        			dataStyle2 = dataStyle2_2;
        		}else{
        			dataStyle2 = dataStyle2_1; //居中 无背景 
        		}
        		
        		if(dataStyle3 == dataStyle3_1){
        			dataStyle3 = dataStyle3_2;
        		}else{
        			dataStyle3 = dataStyle3_1; //居中 无背景 
        		}
        	}
        	
        	HSSFCell data_cell0 = dataRow.createCell(0);
        	data_cell0.setCellStyle(dataStyle1);
        	data_cell0.setCellValue(String.valueOf(m.get("id")));
        	
        	HSSFCell data_cell1 = dataRow.createCell(1);
        	data_cell1.setCellStyle(dataStyle1);
        	data_cell1.setCellValue(String.valueOf(m.get("provinceName")));
        	
        	HSSFCell data_cell2 = dataRow.createCell(2);
        	data_cell2.setCellStyle(dataStyle1);
        	data_cell2.setCellValue(String.valueOf(m.get("lccCode")));
        	
        	HSSFCell data_cell3 = dataRow.createCell(3);
        	data_cell3.setCellStyle(dataStyle2);
        	data_cell3.setCellValue(String.valueOf(m.get("lccName")));
        	
        	HSSFCell data_cell4 = dataRow.createCell(4);
        	data_cell4.setCellStyle(dataStyle3);
        	
        	HSSFCell data_cell5 = dataRow.createCell(5);
        	data_cell5.setCellStyle(dataStyle3);
        	data_cell5.setCellValue(String.valueOf(m.get("aCount")));
        	
        	HSSFCell data_cell6 = dataRow.createCell(6);
        	data_cell6.setCellStyle(dataStyle3);
        	data_cell6.setCellValue(String.valueOf(m.get("cCount")));
        	
        	HSSFCell data_cell7 = dataRow.createCell(7);
        	data_cell7.setCellStyle(dataStyle3);
        	data_cell7.setCellValue(String.valueOf(m.get("eCount")));
        	
        	HSSFCell data_cell8 = dataRow.createCell(8);
        	data_cell8.setCellStyle(dataStyle3);
        	data_cell8.setCellValue(String.valueOf(m.get("bCount")));
        	
        	HSSFCell data_cell9 = dataRow.createCell(9);
        	data_cell9.setCellStyle(dataStyle3);
        	data_cell9.setCellValue(String.valueOf(m.get("dCount")));
        	
        	HSSFCell data_cell10 = dataRow.createCell(10);
        	data_cell10.setCellStyle(dataStyle3);
        	data_cell10.setCellValue(String.valueOf(m.get("fCount")));
        	
        	HSSFCell data_cell11 = dataRow.createCell(11);
        	data_cell11.setCellStyle(dataStyle3);
        	data_cell11.setCellValue(String.valueOf(m.get("gCount")));
        	
        	HSSFCell data_cell12 = dataRow.createCell(12);
        	data_cell12.setCellStyle(dataStyle3);
        	data_cell12.setCellValue(String.valueOf(m.get("pCount")));
        	
        	HSSFCell data_cell13 = dataRow.createCell(13);
        	data_cell13.setCellStyle(dataStyle3);
        	data_cell13.setCellValue(m.get("minBoxCode") == null?"" : String.valueOf(m.get("minBoxCode")));
        	
        	HSSFCell data_cell14 = dataRow.createCell(14);
        	data_cell14.setCellStyle(dataStyle3);
        	data_cell14.setCellValue(m.get("maxBoxCode") == null?"" : String.valueOf(m.get("maxBoxCode")));
        	
        	HSSFCell data_cell15 = dataRow.createCell(15);
        	data_cell15.setCellStyle(dataStyle3);
        	
        }
        
        
    }
    
    //宋体、加粗、20、居中、背景白色
    private HSSFCellStyle getStyle1(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 20);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        return style;
    }
    
    //宋体、加粗、11、居左、背景白色
    private HSSFCellStyle getStyle2(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 11);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、10、居中、背景灰色
    private HSSFCellStyle getStyle3(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、11、居中、背景灰色
    private HSSFCellStyle getStyle4(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 11);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        
        return style;
    }
    
    //宋体、加粗、9、居中、背景紫色
    private HSSFCellStyle getStyle5(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 9);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.VIOLET.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、9、居中、背景白色
    private HSSFCellStyle getStyle6(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 9);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、9、居中、背景黄色
    private HSSFCellStyle getStyle7(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 9);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、9、居中、背景橘色
    private HSSFCellStyle getStyle8(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 9);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、10、居中、无背景
    private HSSFCellStyle getDataStyle1_1(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、10、居中、背景灰
    private HSSFCellStyle getDataStyle1_2(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    
    //宋体、不加粗、10、居左、无背景
    private HSSFCellStyle getDataStyle2_1(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、不加粗、10、居左、背景灰
    private HSSFCellStyle getDataStyle2_2(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    
    //宋体、不加粗、10、居中、无背景
    private HSSFCellStyle getDataStyle3_1(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、不加粗、10、居中、背景灰
    private HSSFCellStyle getDataStyle3_2(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、10、居左、无背景
    private HSSFCellStyle getStyle10(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    //宋体、加粗、10、居左、背景灰色
    private HSSFCellStyle getStyle11(HSSFWorkbook workbook){
    	HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 10);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setFontName("宋体");
        
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setWrapText(true);//允许换行
        
        return style;
    }
    
    
}
