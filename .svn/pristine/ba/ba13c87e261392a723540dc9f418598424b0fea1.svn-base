package com.bdcor.pip.web.spem.service;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
* 生成excel视图，可用excel工具打开或者保存
* 由ViewController的return new ModelAndView(viewExcel, model)生成
*/
public class ViewExcel extends AbstractExcelView {   
   
    public void buildExcelDocument(Map model, HSSFWorkbook workbook,   
            HttpServletRequest request, HttpServletResponse response)   
            throws Exception {  
    	
    	String excelName = "生物样本导出信息表.xls";
		// 设置response方式,使执行此controller时候自动出现下载页面,而非直接使用excel打开
		response.setContentType("APPLICATION/OCTET-STREAM");
		response.setHeader("Content-Disposition", "attachment; filename="+ URLEncoder.encode(excelName, "UTF-8"));
		HSSFSheet sheet = workbook.createSheet("生物样本信息");
		//设置样式
		// 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
		// 产生Excel表头
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLACK.index);
        
		//合并单元格，参数依次为起始行，结束行，起始列，结束列
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0,6));//1,1 5,5
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 7,8));//2,4 6,7
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 9,19));//2,4 6,7
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 20,21));//2,4 6,7
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 22,26));//2,4 6,7
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 27,28));//2,4 6,7
        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 29,31));//2,4 6,7
        HSSFRow header = sheet.createRow(0); // 第0行
        HSSFCell cell1 = header.createCell(0);
        cell1.setCellStyle(style);
        cell1.setCellValue("Patient");
        HSSFCell cell2 = header.createCell(7);
        cell2.setCellStyle(style);
        cell2.setCellValue("Visit");
        HSSFCell cell3 = header.createCell(9);
        cell3.setCellStyle(style);
        cell3.setCellValue("Sample");
        HSSFCell cell4 = header.createCell(20);
        cell4.setCellStyle(style);
        cell4.setCellValue("Client");
        HSSFCell cell5 = header.createCell(22);
        cell5.setCellStyle(style);
        cell5.setCellValue("Project");
        HSSFCell cell6 = header.createCell(24);
        cell6.setCellStyle(style);
        cell6.setCellValue("盒内位置");
        HSSFCell cell7 = header.createCell(26);
        cell7.setCellStyle(style);
        cell7.setCellValue("冻存管状态");
        HSSFRow header1 = sheet.createRow(1); // 第1行
        Map<String,String>title=new LinkedHashMap<String,String>();
        title.put("LCC_CODE", "LCC ID");
        title.put("LCC_NAME", "LCC Name");
        title.put("BLOOD_CODE", "patient ID");//高危条码
        title.put("HELP_CODE", "Init");
        title.put("BIRTHDAY", "DOB");
        title.put("DT", "disease type");
        title.put("SEX", "gender");
        title.put("VISIT_TYPE", "Visit type");
        title.put("CREATE_DATE", "Visit date");
        title.put("TUBE_CODE", "Blood Kit ID");
        title.put("SI", "Sample ID");
        title.put("TUBE_TYPE", "Sample Type");
        title.put("AD", "Additive");
        title.put("CR", "consent Received");
        title.put("RS", "received status");
        title.put("PSE", "previous stored environment");
        title.put("FMC", "freeze-melted cycle");
        title.put("TT", "tube type");
        title.put("UNIT", "unit");
        title.put("ZYZX_BOX_ID", "BOX ID");
        title.put("CN", "Client Name");
        title.put("CC", "Client's company");
        title.put("PJ", "Project Name");
        title.put("ST", "Study Name");
        title.put("ADS", "Aliquot.Description");
        title.put("PD", "Patient.Description");
        title.put("PB", "项目盒号");
        title.put("BOX_ROWNO", "行位置");
        title.put("BOX_COLNO", "列位置");
        title.put("IS_HEMOLYSIS", "是否溶血");
        title.put("IS_LIPID", "是否乳糜血");
        title.put("IS_EMPTY", "是否空管");
        
        
        Set<Entry<String, String>> titles=title.entrySet();
        int i=0;
        for(Entry<String, String> entry:titles){
        	String key=entry.getKey();
        	String value=entry.getValue();
        	Cell cell=header1.createCell(i);
        	cell.setCellStyle(style);
        	cell.setCellValue(value);
        	i=i+1;
        }
        List<Map> lines = (List<Map>) model.get("list");   
        int j=2;//从第2行开始创建行
        for(Map line:lines){
        	Row row= sheet.createRow(j); // 第1行
        	j=j+1;
        	i=0;
        	 for(Entry<String, String> entry:titles){
             	String key=entry.getKey();
             	
             	if("DT".equals(key)){
             		line.put("DT", "GaoWei");
             	}
             	if("VISIT_TYPE".equals(key)){
             		line.put("VISIT_TYPE", "initial");
             	}
             	if("SI".equals(key)){//顺序
             		line.put("SI", j-2);
             	}
             	if("PJ".equals(key)){//顺序
             		line.put("PJ","Programme of Screening and Intervention Subjects with High Risk Cardiovascular Diseases");
             	}
             	if("CR".equals(key)){
             		line.put("CR", "Y");
             	}
             	if("RS".equals(key)){
             		line.put("RS", "frozen");
             	}
             	if("PSE".equals(key)){
             		line.put("PSE", "-40 freezer");
             	}
             	if("FMC".equals(key)){
             		line.put("FMC", "0");
             	}
             	if("CN".equals(key)){
             		line.put("CN", "lujiapeng");
             	}
             	if("CC".equals(key)){
             		line.put("CC", "NCCD");
             	}
             	if("PJ".equals(key)){
             		line.put("CC", "Programme of Screening and Intervention Subjects with High Risk Cardiovascular Diseases");
             	}
             	if("ST".equals(key)){
             		line.put("ST", "GW Project");
             	}
             	if("PB".equals(key)){
             		Object boxCode=line.get("BOX_CODE");
             		Object boxType=line.get("BOX_TYPE");
             		if(boxCode!=null&&boxType!=null){
             			line.put("PB", (String)boxCode+(String)boxType);
             		}
             		line.put("ST", "GW Project");
             	}
             	Object value=line.get(key);
             	if(value!=null){
             		if("TUBE_CODE".equals(key)){
             		}
             		if("SEX".equals(key)){
                 		String v=value.toString();
                 		if("1".equals(v)){
                 			value="male";
                 		}else if("2".equals(v)){
                 			value="female";
                 		}
                 	}
                 	if("IS_HEMOLYSIS".equals(key)){
                 		String v=value.toString();
                 		if("1".equals(v)){
                 			value="是";
                 		}else if("2".equals(v)){
                 			value="否";
                 		}
                 	}
                 	if("IS_LIPID".equals(key)){
                 		String v=value.toString();
                 		if("1".equals(v)){
                 			value="是";
                 		}else if("2".equals(v)){
                 			value="否";
                 		}
                 	}
                 	if("IS_EMPTY".equals(key)){
                 		String v=value.toString();
                 		if("1".equals(v)){
                 			value="是";
                 		}else if("2".equals(v)){
                 			value="否";
                 		}
                 	}
                 	if("TUBE_TYPE".equals(key)){
                 		String v=value.toString();
                 		if("A".equals(v)){
                 			line.put("AD", "EDTA");
                 			value="plasma";
                 		}else if("B".equals(v)){
                 			value="plasma";
                 			line.put("AD", "EDTA");
                 		}else if("C".equals(v)){
                 			value="serum";
                 		}else if("D".equals(v)){
                 			value="serum";
                 		}else if("E".equals(v)){
                 			value="urine";
                 		}else if("F".equals(v)){
                 			value="urine";
                 		}else if("G".equals(v)){
                 			value="blood cell";
                 			line.put("AD", "EDTA");
                 		}
                 	}
             	}
             	
             	String v=entry.getValue();
             	Cell cell=row.createCell(i);
             	cell.setCellStyle(style2);
             	 // 判断值的类型后进行强制类型转换
                String textValue = null;
                
                if(value==null||"".equals(value)){
                	
                } else if (value instanceof Date) {
                    Date date = (Date) value;
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    textValue = sdf.format(date);
                } else if (value instanceof byte[]) {} else {
                    // 其它数据类型都当作字符串简单处理
                    textValue = value.toString();
                }
                // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                if (textValue != null) {
                    Pattern p = Pattern.compile("^//d+(//.//d+)?{1}quot");
                    Matcher matcher = p.matcher(textValue);
                    if (matcher.matches()) {
                        // 是数字当作double处理
                        cell.setCellValue(Double.parseDouble(textValue));
                    } else {
                        HSSFRichTextString richString = new HSSFRichTextString(
                                textValue);
                        richString.applyFont(font3);
                        cell.setCellValue(richString);
                    }
                }
                i=i+1;
             }
        }
    }   
    
    
    
    
    public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern,String [] fileds) {
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(15);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        HSSFFont font3 = workbook.createFont();
        font3.setColor(HSSFColor.BLACK.index);
        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = (T) it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            for (short i = 0; i < fileds.length; i++) {
                HSSFCell cell = row.createCell(i);
                cell.setCellStyle(style2);
                String getMethodName = fileds[i];
                try {
                    Class tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    // 判断值的类型后进行强制类型转换
                    String textValue = null;
                    if(value==null||"".equals(value)){
                    	
                    } else if (value instanceof Date) {
                        Date date = (Date) value;
                        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                        textValue = sdf.format(date);
                    } else if (value instanceof byte[]) {
                        // 有图片时，设置行高为60px;
                        row.setHeightInPoints(60);
                        // 设置图片所在列宽度为80px,注意这里单位的一个换算
                        sheet.setColumnWidth(i, (short) (35.7 * 80));
                        // sheet.autoSizeColumn(i);
                        byte[] bsValue = (byte[]) value;
                        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0,
                                1023, 255, (short) 6, index, (short) 6, index);
                        anchor.setAnchorType(2);
                        patriarch.createPicture(anchor, workbook.addPicture(
                                bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                    } else {
                        // 其它数据类型都当作字符串简单处理
                        textValue = value.toString();
                    }
                    // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                    if (textValue != null) {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?{1}quot");
                        Matcher matcher = p.matcher(textValue);
                        if (matcher.matches()) {
                            // 是数字当作double处理
                            cell.setCellValue(Double.parseDouble(textValue));
                        } else {
                            HSSFRichTextString richString = new HSSFRichTextString(
                                    textValue);
                            richString.applyFont(font3);
                            cell.setCellValue(richString);
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }

        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
