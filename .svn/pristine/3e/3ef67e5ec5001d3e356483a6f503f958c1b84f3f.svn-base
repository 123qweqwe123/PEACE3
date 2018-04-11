package com.bdcor.pip.web.qn.controller;

import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.data.util.ExcelExportConfig;
import com.bdcor.pip.data.util.ExcelExportUtil;
import com.bdcor.pip.web.common.dao.PipSysExpexcelInfoMapper;
import com.bdcor.pip.web.common.domain.PipSysExpexcelInfo;
import com.bdcor.pip.web.qn.dao.PatientReportDao;
import com.bdcor.pip.web.qn.filter.TimeListFilter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
 * Created by root on 16-11-9.
 */
@Controller
@RequestMapping("/qn/pgroup")
public class PatientGroupInfoController {

    @Autowired
    private PatientReportDao groupDao;

    @Autowired
    private PipSysExpexcelInfoMapper excelinfpDao;

    @RequestMapping
    public String init(){
        return "qn/groupInfo/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public List<Map<String , Object>> ListData(@RequestParam(required = false , value = "lccCode")String lccCode){
        return groupDao.getGroupState(lccCode);
    }


    @RequestMapping("export")
    @ResponseBody
    public ModelAndView export(@RequestParam(required = false , value = "lccCode")String lccCode, HttpServletRequest request, HttpServletResponse response)throws Exception {
        request.setCharacterEncoding("utf-8");
        java.io.BufferedOutputStream bos = null;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String datestr = sdf.format(new Date());
            String filename = "患者入组情况"+datestr + "_" +Securitys.getUser().getName()+".xls";
            response.setContentType("application/x-msdownload;");
            response.setHeader("Content-disposition", "attachment; filename="
                    + new String(filename.getBytes("utf-8"), "ISO8859-1"));
            List<Map<String,Object>> list = groupDao.getGroupState(lccCode);
            bos = new BufferedOutputStream(response.getOutputStream());
            HSSFWorkbook workbook = new HSSFWorkbook();
            String[] titleArr = {"LCCID","医院名称","首次面访人数","应随机人数","死亡人数","签署知情同意数","随机率","糖尿病研究人数","非糖尿病研究人数","非干预面访数"};
            String[] keyArr = {"LCC_CODE","LCC_NAME","FIRSTVIEW","RANDOM","DEAD","ISKNOW","RANDOMRATE","IS_DIABETES","NO_DIABETES","TELFVIEW"};
            ExcelExportConfig config = new ExcelExportConfig();
            config.setSheetName("患者入组情况");
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
            po.setPageName("患者入组情况");
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
