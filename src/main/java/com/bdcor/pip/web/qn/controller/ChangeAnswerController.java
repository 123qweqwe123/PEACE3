package com.bdcor.pip.web.qn.controller;

import com.bdcor.pip.client.tools.JsonMapper;
import com.bdcor.pip.client.vo.paper.*;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.persistence.domain.JqgridResponse;
import com.bdcor.pip.core.persistence.domain.JqgridResponseContext;
import com.bdcor.pip.web.common.service.ComboxService;
import com.bdcor.pip.web.material.supp.dao.PipMmsScmarchivesMapper;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchives;
import com.bdcor.pip.web.material.supp.domain.PipMmsScmarchivesExample;
import com.bdcor.pip.web.qn.dao.PipUqsAnswerDropMapper;
import com.bdcor.pip.web.qn.dao.PipUqsAnswerMapper;
import com.bdcor.pip.web.qn.dao.PipUqsAnswerqnLogMapper;
import com.bdcor.pip.web.qn.domain.*;
import com.bdcor.pip.web.qn.filter.AnswerQnLogFilter;
import com.bdcor.pip.web.qn.service.AnswerQnService;
import com.bdcor.pip.web.qn.service.impl.AnswerQnlogServiceImpl;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

/**
 * Created by zhang.rw on 16-7-6.
 */
@Controller
@RequestMapping("/changeans")
public class ChangeAnswerController {

    @Autowired
    private ComboxService comboxService;

    @Autowired
    private AnswerQnService answerQnService;

    @Autowired
    private AnswerQnlogServiceImpl qnlogservice;

    @Autowired
    private PipUqsAnswerqnLogMapper answerlogDao;

    @Autowired
    private PipUqsAnswerMapper ansDao;

    @Autowired
    private PipUqsAnswerDropMapper ansDropDao;
    @Autowired
    private PipMmsScmarchivesMapper bloodDao;

    @RequestMapping
    public String indexPage(){
        return "qn/changeans/list";
    }

    @RequestMapping("index")
    public String index(){
         return "qn/changeans/list";
    }

    @RequestMapping("getpinfo")
    public @ResponseBody
    Map<String , Object> getPatientInfo(String pid){

        return qnlogservice.getPatientInfo(pid);
    }

    @RequestMapping("list")
    @ResponseBody
    public JqgridResponse<Map<String , Object>> ListData(String pid){
        AnswerQnLogFilter filter = new AnswerQnLogFilter();
        filter.setState(1);
        filter.setPid(pid);
        JqgridResponse<Map<String , Object>> response = JqgridResponseContext.getJqgridResponse();
        if(StringUtils.isBlank(pid)){
            response.setRows(new ArrayList<Map<String,Object
                    >>());
        }else{
            response.setRows(qnlogservice.getData(filter));
        }
        return response;
    }

    /**
     * 读取问卷内容以及答案，跳转至问卷修改界面
     * @param pid
     * @param qnid
     * @return
     */
    @RequestMapping("tochange")
    public String toChange(String pid , String qnid , HttpServletRequest req , String uqsfile , Model model){

        if(StringUtils.isBlank(pid)){ // 未传递PID 没得看 直接返回
            return "qn/changeans/list";
        }

        if( StringUtils.isBlank(uqsfile) ){
            return "qn/changeans/list";
        }

        if( StringUtils.isBlank(qnid) ){
            qnid = "004.001.001";
        }


        String path = req.getSession().getServletContext().getRealPath("/")+"WEB-INF"+ File.separator+"classes"+File.separator+"paper"+File.separator+"UQS_"+qnid.replaceAll("\\.","_")+".xml";
        Paper qn = answerQnService.getPaperByPath(path);
        if(qn == null){
            model.addAttribute("errorMsg","问卷模版不存在");
            return "qn/changeans/list";
        }

        Map<String , String> m = qnlogservice.getUqsData(pid , qnid , uqsfile);
        String UQSBeginTime = m.get("START_TIME");
        String resultPath = m.get("UQSFILE");
        Result r = null;
        Map<String,String> map = new HashMap<String,String>();

        try{
            r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(new FileInputStream(uqsfile)).getQuestionnaire());
        }catch (Exception e) {
        }

        if( "004.001.001".equals(qnid) ){
            //自      年   月    日  ，您是否住过院(至少在医院住过一晚)？
            String date6_1=qnlogservice.getStartHosDate(pid, "001", "1");
            for(QuestionGroup qg : qn.getQGroup()){
                if(qg.getQs()!=null && qg.getId().equals("6")){
                    for(QuestionC q:qg.getQs()){
                        if(q.getOptions()!=null && q.getId().equals("1")){
                            q.setDisplayname("自"+date6_1.substring(0,4)+"年"+date6_1.substring(4,6)+"月"+date6_1.substring(6,8)+"日  ，您是否住过院(至少在医院住过一晚)？");
                        }
                    }
                }
            }


        if(r != null && r.getOptions() != null){
            if(r.getOptions() != null){
                for(ResultOption ro:r.getOptions()){
                    String dictName = comboxService.getDictName(qn,ro.getQuestionSetId(),ro.getQuestionId(),ro.getResultId(),ro.getResutlStr());
                    if(dictName == null){
                        map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
                    }else{
                        map.put(ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),dictName);
                        map.put("dict"+ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_"+ro.getResultId(),ro.getResutlStr());
                    }
                }
            }
        }
        //对查询到的结果数据进行一次地区的过滤  只有是问卷完成查看时才进行地区代码的替换
        if("1".equals(m.get("STATE"))){
            if(!StringUtils.isEmpty(map.get("2_1_1"))){
                map.put("2_1_1",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_1"))) ? map.get("2_1_1") : comboxService.getAreaName(map.get("2_1_1")));
            }
            if(!StringUtils.isEmpty(map.get("2_1_2"))){
                map.put("2_1_2",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_2"))) ? map.get("2_1_2") : comboxService.getAreaName(map.get("2_1_2")));
            }
            if(!StringUtils.isEmpty(map.get("2_1_3"))){
                map.put("2_1_3",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_3"))) ? map.get("2_1_3") : comboxService.getAreaName(map.get("2_1_3")));
            }
            if(!StringUtils.isEmpty(map.get("2_1_4"))){
                map.put("2_1_4",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_4"))) ? map.get("2_1_4") : comboxService.getAreaName(map.get("2_1_4")));
            }
            if(!StringUtils.isEmpty(map.get("2_1_5"))){
                map.put("2_1_5",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_5"))) ? map.get("2_1_5") : comboxService.getAreaName(map.get("2_1_5")));
            }
            if(!StringUtils.isEmpty(map.get("2_1_6"))){
                map.put("2_1_6",StringUtils.isEmpty(comboxService.getAreaName(map.get("2_1_6"))) ? map.get("2_1_6") : comboxService.getAreaName(map.get("2_1_6")));
            }
        }
        }
        model.addAttribute("paper",qn);
        model.addAttribute("resultOptionMap",map);
        model.addAttribute("resultOptionMapJson", JsonMapper.objectToJson(map));

        model.addAttribute("UQSBeginTime",UQSBeginTime);
        model.addAttribute("uqsVersion",qnid);
        model.addAttribute("patientId",pid);

        model.addAttribute("uqsfile",uqsfile);

        return "qn/changeans/answer";
    }


    @RequestMapping("saveChanges")
    public String saveChanges(HttpServletRequest request,RedirectAttributes attr){
        String uqsVersion = request.getParameter("uqsVersion");
        String patientId = request.getParameter("patientId");

        String uqsfile = request.getParameter("uqsfile");

        if(uqsVersion==null || uqsVersion.length()<10 || patientId==null){
            attr.addAttribute("result",2);
            return "redirect:index";
        }

        uqsVersion = uqsVersion.replaceAll("_",".");
//        //  查询现存问卷备份
        PipUqsAnswerqnLogExample pe = new PipUqsAnswerqnLogExample();
        ArrayList l = new ArrayList<Integer>();
        l.add(1);
        l.add(4);
        pe.createCriteria().andStateIn(l)
                           .andPatientIdEqualTo(patientId)
                           .andUqsVersionEqualTo(uqsVersion)
                           .andUqsFileNotLike("%change%");
        List<PipUqsAnswerqnLog> list =  answerlogDao.selectByExample(pe);
        PipUqsAnswerqnLog po;
        Result r = null;

        if( list.size() > 0 ){
            po = list.get(0);
            File f = new File(po.getUqsFile());
            try{
                r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(new FileInputStream(po.getUqsFile())).getQuestionnaire());
            }catch (Exception e) {
            }
            File bakFile = new File(f.getParentFile().getPath()+File.separator+"drop"+File.separator+f.getName());
            if( bakFile.exists() ){ // 文件存在说明已经备份
                try{
                    r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(new FileInputStream(bakFile)).getQuestionnaire());
                }catch (Exception e) {
                }
            }else{
                if(!bakFile.getParentFile().exists()){
                    bakFile.getParentFile().mkdirs();
                }
                try {
                    FileUtils.copyFile(f,bakFile); // 备份文件
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // 更新采血包状态
        PipUqsAnswerExample blood = new PipUqsAnswerExample();
        blood.createCriteria().andPatientIdEqualTo(patientId)
                .andQuestionnaireIdEqualTo(uqsVersion)
                .andQuestionsetIdEqualTo("4")
                .andQuestionIdEqualTo("3")
                .andOptionIdEqualTo("1");
        List<PipUqsAnswer> list_blood = ansDao.selectByExample(blood); // 查询采血包包
        if( list_blood.size() > 0 ){
            PipUqsAnswer p_blood = list_blood.get(0);
            String bloodNo = p_blood.getAnswer();// 获取采血包号

            PipMmsScmarchives pmc = new PipMmsScmarchives();
            Short state = 2 , state2 = 3;
            pmc.setPackageState(state);
            PipMmsScmarchivesExample pscme = new PipMmsScmarchivesExample();
            pscme.createCriteria().andBloodpackageCodeEqualTo(bloodNo).andPackageStateLessThan(state2); // 报损的不更新
            bloodDao.updateByExampleSelective(pmc , pscme); // 更新采血包号为未使用状态
        }

        PipUqsAnswerExample ae = new PipUqsAnswerExample();
        ae.createCriteria().andPatientIdEqualTo(patientId).andQuestionnaireIdEqualTo(uqsVersion);
        List<PipUqsAnswer> list_ans = ansDao.selectByExample(ae);
        PipUqsAnswerDrop drop = new PipUqsAnswerDrop();
        // 备份数据库数据
        if( uqsfile.toUpperCase().indexOf("CHANGE") == -1 ){
            for( PipUqsAnswer p : list_ans ){
                ansDao.deleteByPrimaryKey(p); // 删除原有记录
                // drop表中新增删除的记录
                drop.setAnswer(p.getAnswer());
                drop.setCreateDate(p.getCreateDate());
                drop.setCreateOrganization(p.getCreateOrganization());
                drop.setDictValueCode(p.getDictValueCode());
                drop.setLccCode(p.getLccCode());
                drop.setOptionId(p.getOptionId());
                drop.setPatientId(p.getPatientId());
                drop.setProjectId(p.getProjectId());
                drop.setQuestionId(p.getQuestionId());
                drop.setQuestionsetId(p.getQuestionsetId());
                drop.setQuestionnaireId(p.getQuestionnaireId());
                ansDropDao.insert(drop);
            }
        }else{
            for( PipUqsAnswer p : list_ans ){
                ansDao.deleteByPrimaryKey(p); // 删除原有记录
            }
        }
        String path = request.getSession().getServletContext().getRealPath("/")+"WEB-INF/classes/paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";
        Paper qn = answerQnService.getPaperByPath(path);
        if(qn == null){
            attr.addAttribute("result",2);
            return "redirect:index";
        }
        try{
            qnlogservice.saveQn(request,qn , r);
//            attr.addAttribute("result",1);
        }catch (Exception e) {
            e.printStackTrace();
            attr.addAttribute("result",2);
        }
        return "redirect:index";
    }


    /**
     * 校验采血号
     * @param bloodNo
     * @return
     */
    @RequestMapping(value = "checkBloodNo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ResponseEntity checkBloodNo(@RequestParam("bloodNo")String bloodNo , String pid ,String uqsver){
        Map<String,Object> res = new HashMap<String, Object>();
        res.put("success", true);
        Map<String, Object> map  = qnlogservice.checkBloodNo(bloodNo,pid , uqsver);
        if( map == null ){
            res.put("success", false);
            res.put("msg", "采血包无效！");
        }
        else if("1".equals(map.get("PACKAGE_STATE").toString())){
            res.put("success", false);
            res.put("msg", "采血包已经使用！");
        }else if("3".equals(map.get("PACKAGE_STATE").toString())){
            res.put("success", false);
            res.put("msg", "采血包已经报损！");
        }else if("4".equals(map.get("PACKAGE_STATE").toString())){
            res.put("success", false);
            res.put("msg", "采血包已经过期！");
        }
        return new ResponseEntity(res, HttpStatus.OK);
    }
}
