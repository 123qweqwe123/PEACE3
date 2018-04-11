package com.bdcor.pip.web.qn.controller;

import com.alibaba.fastjson.JSON;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.core.utils.StringUtils;
import com.bdcor.pip.data.util.ExcelExportConfig;
import com.bdcor.pip.data.util.ExcelExportUtil;
import com.bdcor.pip.web.common.dao.PipSysExpexcelInfoMapper;
import com.bdcor.pip.web.common.domain.PipSysExpexcelInfo;
import com.bdcor.pip.web.pro.promgt.domain.Lcc;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.qn.dao.PipQnReportDao;
import com.bdcor.pip.web.qn.filter.ReportFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by root on 16-11-21.
 */
@Controller
@RequestMapping("/qn/report")
public class PipQnReportController {

    @Autowired
    private PipSysExpexcelInfoMapper excelinfpDao;

    @Autowired
    private PipQnReportDao reportDao;

    @Autowired
    private LccService lccService;

    @RequestMapping("blood")
    public String bloodreport(){
        return "qn/bloodreport/list";
    }

    @RequestMapping("bloodlist")
    @ResponseBody
    public List<Map<String,Object>> getData
            (@RequestParam(value = "lccCode",required = false) String lccCode ,
             @RequestParam(value = "type",required = false)String type){
        return reportDao.bloodlist(lccCode);
    }

    @RequestMapping("bloodexport")
    @ResponseBody
    public ModelAndView export(@RequestParam(required = false , value = "lccCode")String lccCode,
                               @RequestParam(value = "type",required = false)String type,
                               HttpServletRequest request, HttpServletResponse response)
    throws Exception
    {
        request.setCharacterEncoding("utf-8");
        java.io.BufferedOutputStream bos = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String datestr = sdf.format(new Date());
            String filename = "中心血样本采集报表_"+datestr + "_" + Securitys.getUser().getName()+".xls";
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(filename.getBytes("utf-8"), "ISO8859-1"));
            List<Map<String,Object>> list = reportDao.bloodlist(lccCode);
            bos = new BufferedOutputStream(response.getOutputStream());
            HSSFWorkbook workbook = new HSSFWorkbook();
            String[] titleArr ={"LCCID","医院名称","门诊类型","应采集数","实际采集数","采血率","未采集数"};
            String[] keyArr = {"LCC_CODE","LCC_NAME","TYPE","YCJS","SJCJS","CXL","WCJS"};
            ExcelExportConfig config = new ExcelExportConfig();
            config.setSheetName("中心血样本采集报表");
            ExcelExportUtil.fillExcel(list,titleArr,keyArr, workbook,config);
            workbook.write(bos);

            PipSysExpexcelInfo po = new PipSysExpexcelInfo();
            po.setId(UUID.randomUUID().toString());
            po.setFileName(filename);
            po.setLccCode(Securitys.getUser().getLccCode());
            po.setOperateDate(new Date());
            po.setOperatorId(Securitys.getUser().getId());
            po.setOperatorName(Securitys.getUser().getName());
            po.setPageId("");
            po.setPageName("中心血样本采集报表");
            excelinfpDao.insert(po);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null)
                bos.close();
        }
            return null;
    }

    @RequestMapping("blooddetail")
    public String bloodInfo(HttpServletRequest request){
        if(request.getParameter("lccCode")!=null &&
                !"".equals(request.getParameter("lccCode").trim())){
            request.setAttribute("lcc", lccService.getLcc(request.getParameter("lccCode")));
        }else{
            Lcc lcc = new Lcc();
            lcc.setLccCode("");
            lcc.setLccName("");
            request.setAttribute("lcc", lcc);
        }
        return "qn/bloodreport/detail";
    }

    @RequestMapping("noCenterBloodlist")
    public @ResponseBody
    JqgridResponse<Map<String,Object>> getNoCenterBloodlist(
            ReportFilter filter
    ){
        List l = reportDao.getNoCenterBloodlist(filter);
        JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(l);
        return response;
    }


    @RequestMapping("blooddetailexport")
    @ResponseBody
    public ModelAndView bloodDetailExport(ReportFilter filter,
                               HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        request.setCharacterEncoding("utf-8");
        filter.setRows(999999999);
        if(org.apache.commons.lang3.StringUtils.isNotBlank(filter.getPatientName())){
            filter.setPatientName(java.net.URLDecoder.decode(filter.getPatientName(), "UTF-8"));
        }
        java.io.BufferedOutputStream bos = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String datestr = sdf.format(new Date());
            String filename = "未采集中心血患者名单_"+datestr + "_" + Securitys.getUser().getName()+".xls";
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(filename.getBytes("utf-8"), "ISO8859-1"));
            List<Map<String,Object>> list = reportDao.getNoCenterBloodlist(filter);
            bos = new BufferedOutputStream(response.getOutputStream());
            HSSFWorkbook workbook = new HSSFWorkbook();
            String[] titleArr = {"LCCID","医院名称","PID","患者姓名","门诊类型"};
            String[] keyArr = {"LCC_CODE","LCC_NAME","PATIENT_ID","PATIENT_NAME","TYPE"};
            ExcelExportConfig config = new ExcelExportConfig();
            config.setSheetName("未采集中心血患者名单");
            ExcelExportUtil.fillExcel(list,titleArr,keyArr, workbook,config);
            workbook.write(bos);

            PipSysExpexcelInfo po = new PipSysExpexcelInfo();
            po.setId(UUID.randomUUID().toString());
            po.setFileName(filename);
            po.setLccCode(Securitys.getUser().getLccCode());
            po.setOperateDate(new Date());
            po.setOperatorId(Securitys.getUser().getId());
            po.setOperatorName(Securitys.getUser().getName());
            po.setPageId("");
            po.setPageName("未采集中心血患者名单");
            excelinfpDao.insert(po);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null)
                bos.close();
        }
        return null;
    }


    @RequestMapping("dbpindex")
    public String initDbp(@RequestParam(value = "lccCode",required = false)String lccCode,
						  @RequestParam(value = "type",required = false)String type,
                          HttpServletRequest request){

		if( StringUtils.isNotBlank(type) ){
			request.setAttribute("type", type);
		} else {
			request.setAttribute("type", "1");
		}
        if( StringUtils.isNotBlank(lccCode) ){
            request.setAttribute("lcc", lccService.getLcc(request.getParameter("lccCode")));
        }else{
            Lcc lcc = new Lcc();
            lcc.setLccCode("");
            lcc.setLccName("");
            request.setAttribute("lcc", lcc);
        }

        return "qn/dbp/detail";
    }

    @RequestMapping("dbplistdata")
    public @ResponseBody
    JqgridResponse<Map<String,Object>> getdbpListData(
            ReportFilter filter ){
		List l = new ArrayList();
		if ("2".equals(filter.getType())) {
			l = reportDao.getDbpListDataByLast(filter);
		} else {
			l = reportDao.getDbpListData(filter);
		}
		JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
        response.setRows(l);
        return response;
    }


    @RequestMapping("dbpdetailexport")
    @ResponseBody
    public ModelAndView dbpDetailExport(ReportFilter filter,
                                          HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        request.setCharacterEncoding("utf-8");
        filter.setRows(999999999);
        if(org.apache.commons.lang3.StringUtils.isNotBlank(filter.getPatientName())){
            filter.setPatientName(java.net.URLDecoder.decode(filter.getPatientName(), "UTF-8"));
        }
        java.io.BufferedOutputStream bos = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String datestr = sdf.format(new Date());
            String filename = "动态血压回收记录_"+datestr + "_" + Securitys.getUser().getName()+".xls";
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(filename.getBytes("utf-8"), "ISO8859-1"));
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			if ("2".equals(filter.getType())) {
				list = reportDao.getDbpDataForExcelByLast(filter);
			} else {
				list = reportDao.getDbpDataForExcel(filter);
			}

            bos = new BufferedOutputStream(response.getOutputStream());
            HSSFWorkbook workbook = new HSSFWorkbook();
            String[] titleArr = {"LCCID","PID","患者姓名","门诊类型","随访完成时间","是否预约","是否已回收结果",//"医院名称",
                    "完成时间","相距天数","用户名","录入时间"};
            String[] keyArr = {"LCC_CODE","PATIENT_ID", "PATIENT_NAME","TYPE", "END_DATE", "IS_YY",//"LCC_NAME",
                    "IS_GETRESULT", "DONE_DATE","MINUSDAYS", "CREATE_NAME", "CREATE_DATE"};
            ExcelExportConfig config = new ExcelExportConfig();
            config.setSheetName("动态血压回收记录");
            ExcelExportUtil.fillExcel(list,titleArr,keyArr, workbook,config);
            workbook.write(bos);

            PipSysExpexcelInfo po = new PipSysExpexcelInfo();
            po.setId(UUID.randomUUID().toString());
            po.setFileName(filename);
            po.setLccCode(Securitys.getUser().getLccCode());
            po.setOperateDate(new Date());
            po.setOperatorId(Securitys.getUser().getId());
            po.setOperatorName(Securitys.getUser().getName());
            po.setPageId("");
            po.setPageName("动态血压回收记录");
            excelinfpDao.insert(po);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null)
                bos.close();
        }
        return null;
    }


    @RequestMapping("saveDbpData")
    @Transactional
    public ResponseEntity<?> saveDbpData(String jsonArr,
                                         HttpServletRequest request){
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String,ArrayList<Map<String,String>>> dataMap =  (Map<String,ArrayList<Map<String,String>>>)JSON.parse(jsonArr);

        try {
            List<Map<String,String>> list_insert =  dataMap.get("i");
            if( list_insert != null && list_insert.size() > 0 ){
                for(int i = 0 ; i < list_insert.size() ; i++ ){
                    Map<String,String>  m = list_insert.get(i);
                    m.put("create_id",Securitys.getUser().getId());
                    m.put("create_name",Securitys.getUser().getName());
                    reportDao.insertDbpInfo(m);
                }
            }

            List<Map<String,String>> list_update =  dataMap.get("u");
            if( list_update != null && list_update.size() > 0 ){
                for(int i = 0 ; i < list_update.size() ; i++ ){
                    Map<String,String> m = list_update.get(i);
                    m.put("update_id",Securitys.getUser().getId());
                    m.put("update_name",Securitys.getUser().getName());
                    reportDao.updateDbpInfo(m);
                }
            }
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",true);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping("resetDbp")
    public ResponseEntity<?> resetDbp(String jsonArr){
        Map<String, Object> result = new HashMap<String, Object>();
        Map<String,String> dataMap =  (Map<String,String>)JSON.parse(jsonArr);
        try {
            String isGetResult = dataMap.get("IS_GETRESULT");
            if (StringUtils.isBlank(isGetResult) || "0".equals(isGetResult)) {
                reportDao.deleteDbpInfo(dataMap);
            }
            result.put("success",true);
        }catch (Exception e){
            e.printStackTrace();
            result.put("success",true);
        }
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @RequestMapping("dbpgroup")
    public String dbpgroup(){
        return "qn/dbp/group";
    }

    @RequestMapping("dbpgroupdata")
    @ResponseBody
    public JqgridResponse<?> getDbpGroupData(ReportFilter filter){
        JqgridResponse<Map<String,Object>> response = JqgridResponseContext.getJqgridResponse();
		// type: 1.chat研究干预随机门诊 2.CHAT干预末次门诊
		if ("2".equals(filter.getType())) {
			response.setRows(reportDao.getDbpGroupDataByLast(filter));
		} else {
			response.setRows(reportDao.getDbpGroupData(filter));
		}

        return response;
    }

    @RequestMapping("exportDbpgroupdata")
    @ResponseBody
    public ModelAndView exportDbpGroupData(ReportFilter filter,
                HttpServletRequest request, HttpServletResponse response)
            throws Exception
        {
            request.setCharacterEncoding("utf-8");
            filter.setRows(999999999);
            if(org.apache.commons.lang3.StringUtils.isNotBlank(filter.getPatientName())){
                filter.setPatientName(java.net.URLDecoder.decode(filter.getPatientName(), "UTF-8"));
            }
            java.io.BufferedOutputStream bos = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String datestr = sdf.format(new Date());
                String filename = "动态血压回收情况报表_"+datestr + "_" + Securitys.getUser().getName()+".xls";
                response.setContentType("application/x-msdownload;");
                response.setHeader("Content-disposition", "attachment; filename="
                        + new String(filename.getBytes("utf-8"), "ISO8859-1"));
                // type: 1.chat研究干预随机门诊 2.CHAT干预末次门诊
				List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
				if ("2".equals(filter.getType())) {
					list = reportDao.getDbpGroupDataByLast(filter);
				} else {
					list = reportDao.getDbpGroupData(filter);
				}
                // List<Map<String,Object>> list = reportDao.getDbpGroupData(filter);
                bos = new BufferedOutputStream(response.getOutputStream());
                HSSFWorkbook workbook = new HSSFWorkbook();
                String[] titleArr = {"LCCID","医院名称","已随机数","门诊类型","已预约数","实际回收数","回收率"};
                String[] keyArr = { "LCC_CODE","LCC_NAME","YSJS","TYPE","YYYS","SJHSS","HSL"};
                ExcelExportConfig config = new ExcelExportConfig();
                config.setSheetName("动态血压回收情况报表");
                ExcelExportUtil.fillExcel(list,titleArr,keyArr, workbook,config);
                workbook.write(bos);

                PipSysExpexcelInfo po = new PipSysExpexcelInfo();
                po.setId(UUID.randomUUID().toString());
                po.setFileName(filename);
                po.setLccCode(Securitys.getUser().getLccCode());
                po.setOperateDate(new Date());
                po.setOperatorId(Securitys.getUser().getId());
                po.setOperatorName(Securitys.getUser().getName());
                po.setPageId("");
                po.setPageName("动态血压回收情况报表");
                excelinfpDao.insert(po);

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null)
                    bos.close();
            }
            return null;
        }


    }
