package com.bdcor.pip.web.msg.controller;


import com.alibaba.fastjson.JSON;
import com.bdcor.pip.client.tools.JsonMapper;
import com.bdcor.pip.client.vo.paper.*;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.dao.PipCommRemarkMapper;
import com.bdcor.pip.web.common.domain.PipCommRemark;
import com.bdcor.pip.web.common.service.ComboxService;
import com.bdcor.pip.web.data.dao.PipCommPatientDateMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientMapper;
import com.bdcor.pip.web.data.dao.PipCommPatientNewjoinMapper;
import com.bdcor.pip.web.data.domain.PipCommPatient;
import com.bdcor.pip.web.data.domain.PipCommPatientDate;
import com.bdcor.pip.web.data.domain.PipCommPatientNewjoin;
import com.bdcor.pip.web.data.filter.PatientFilter;
import com.bdcor.pip.web.data.service.PatientService;
import com.bdcor.pip.web.msg.dao.PatientGroupDao;
import com.bdcor.pip.web.msg.service.MsgJoinService;
import com.bdcor.pip.web.pro.promgt.service.LccService;
import com.bdcor.pip.web.qn.service.impl.AnswerQnlogServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("msg/newjoin")
public class MsgNewJoinController {

    private static  final Logger log = LoggerFactory.getLogger(MsgNewJoinController.class);

    @Autowired
    private LccService lccService;

    @Autowired
    private PipCommPatientMapper patientDao;

    @Autowired
    private PipCommRemarkMapper remarkDao;

    @Autowired
    private AnswerQnlogServiceImpl ansqnService;

    @Autowired
    private MsgJoinService msgJoinService;

    @Autowired
    private ComboxService comboxService;

    @Autowired
    private PipCommPatientDateMapper patientDateDao;

    @Autowired
    private PipCommPatientNewjoinMapper newpatientDao;

    @RequestMapping
    public String index(HttpServletRequest req){
        req.setAttribute("patientId",req.getParameter("patientId"));
        return "msg/newjoin/index";
    }

    @RequestMapping("openmodalAddPage")
    public String formIndex(Model model){

        if (Securitys.isAdmin() || Securitys.isOrganAdmin()) {
            model.addAttribute("lccDictList", lccService.getDataLimitLcc());
        } else {
            model.addAttribute("lccDictList",
                    lccService.getDataLimitLccForLccCode());
        }

        return "msg/newjoin/form";
    }

    @RequestMapping("getautodata")
    public @ResponseBody
    String getAutoData(HttpServletRequest request,
                       @RequestParam("q") String q ){
        log.info("autocomplete查询，查看编码转换："+q);
        String limitStr = request.getParameter("limit");
        List<Map<String,Object>> list = patientDao.getPatientForAuto(q,limitStr);
        if(StringUtils.isNotBlank(q)){
            String returnStr="";
            for(Map<String,Object> map:list){
                if(returnStr.length()>0){
                    returnStr += "\n";
                }
//                    returnStr += map.get("PATIENT_NAME")+"|"+map.get("PATIENT_ID");

                returnStr += JSON.toJSONString(map);

            }
            return returnStr;
        }else{
            return "";
        }
    }

    @RequestMapping("add")
    @ResponseBody
    public ResponseEntity<?> add(String patientId){
        Map<String,Object> m = new HashMap<String,Object>();
        if( StringUtils.isNotBlank(patientId) ){
            patientDao.updateProcessType(patientId,"2");
            m.put("success",true);
            m.put("msg","添加成功");

            PipCommRemark pr = new PipCommRemark();
            pr.setApplyBy(Securitys.getUserName());
            pr.setModifyBy(Securitys.getUser().getId());
            pr.setModifyDate(new Date());
            pr.setPk1(patientId);
            pr.setId(getPrimaryId());
            pr.setModuleId((short)3);
            pr.setRemark1("修改添加为补录人员");
            remarkDao.insert(pr);
        }else{
            m.put("success",false);
            m.put("msg","添加失败");
        }
        return new ResponseEntity(m, HttpStatus.OK);
    }

    // 主键生成策略
    public String getPrimaryId(){
        Long now = System.currentTimeMillis();
        int random = new Random().nextInt(900)+100; // 随机数 100-999范围
        return now.toString() + random;
    }


    @ResponseBody
    @RequestMapping(value="update")
    public Map<String, Boolean> update(PipCommPatient patient){
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        try
        {
            patient.setProjectId(Securitys.getUser().getCurrent_projectId());
            patientDao.updateByPrimaryKeySelective(patient);
            PipCommRemark pr = new PipCommRemark();
            pr.setApplyBy(Securitys.getUserName());
            pr.setApplyDate(new Date());
            pr.setModifyBy(Securitys.getUser().getId());
            pr.setModifyDate(new Date());
            pr.setPk1(patient.getPatientId());
            pr.setId(getPrimaryId());
            pr.setModuleId((short)3);
            pr.setRemark1("修改手机号,新号码为："+patient.getMobile());
            remarkDao.insert(pr);
            result.put("success", true);
        } catch (Exception e)
        {
            e.printStackTrace();
            result.put("success", false);
        }
        return result;
    }


    @RequestMapping(value="joinUqs")
    public String joinUqs(HttpServletRequest request,Model model,@RequestParam("uqsCode")String uqsCode,@RequestParam("patientId")String patientId){
        Map<String,String> answerLogMap = msgJoinService.getAnswerLogMap(uqsCode,patientId);
        if(answerLogMap == null){
            model.addAttribute("errorMsg","用户ID不存在");
            return "/WEB-INF/views/msg/join/list.jsp.bak";
        }

        String uqsVersion = answerLogMap.get("UQSVERSION");
        if(uqsVersion==null || uqsVersion.trim().length()==0){
            uqsVersion = msgJoinService.getCurrentVersion(Securitys.getCurrentProject()+"."+uqsCode);
        }

        String basePath = this.getClass().getResource("/").getPath();
        if(basePath.indexOf(":")>-1){//windows
            basePath = basePath.substring(1);
        }
        String path = basePath+"paper/UQS_"+uqsVersion.replaceAll("\\.","_")+".xml";

        Paper qn = msgJoinService.getPaperByPath(path);
        if (qn == null) {
            model.addAttribute("errorMsg", "问卷模版不存在");
            return "/WEB-INF/views/msg/join/list.jsp.bak";
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String UQSBeginTime = sf.format(new Date());

        String resultPath = answerLogMap.get("UQSFILE");
        Result r = null;
        String paperLabel = uqsVersion.substring(4, 7);
        Map<String, String> map = new HashMap<String, String>();
        if (resultPath != null && resultPath.trim().length() > 0) {
            try {
                r = new Result(ResultQuestionnaireDocumentBean.Factory.parse(
                        new FileInputStream(resultPath)).getQuestionnaire());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //初始化默认值
        if (paperLabel.equals("010")) {
            List<Map<String, String>> answer001 = msgJoinService.getUqsAnswer(patientId,"001");
            if( answer001 == null || answer001.size() == 0 ){
                answer001 = msgJoinService.getUqsAnswer(patientId,"013");
            }
            if (answer001 != null && answer001.size() > 0) {
                for (Map<String, String> m : answer001) {
                    if(m.get("QUESTIONSET_ID").equals("1")||m.get("QUESTIONSET_ID").equals("2")){
                        map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),
                                m.get("ANSWER") == null ? "" : m.get("ANSWER"));

                        if(m.get("QUESTIONSET_ID").equals("2") && m.get("QUESTION_ID").equals("1") && Integer.parseInt(m.get("OPTION_ID").trim())<6){
                            map.put(m.get("QUESTIONSET_ID") + "_"+ m.get("QUESTION_ID") + "_"+ m.get("OPTION_ID"),StringUtils.isEmpty(comboxService.getAreaName(m.get("ANSWER"))) ? m.get("ANSWER") : comboxService.getAreaName(m.get("ANSWER")));
                        }
                    }
                }
            }else{
                map.put("1_1_1",answerLogMap.get("PATIENT_NAME"));
                map.put("1_4_1",answerLogMap.get("ID_NUMBER"));
                if( StringUtils.isNotBlank(answerLogMap.get("SEX"))){
                    map.put("1_2_"+answerLogMap.get("SEX"),"");
                }
                map.put("1_3_1",answerLogMap.get("BIRTHDAY") == null ? "" : answerLogMap.get("BIRTHDAY"));
                if( StringUtils.isNotBlank(answerLogMap.get("PHONE"))){
                    map.put("2_2_1",answerLogMap.get("PHONE"));
                }else{
                    map.put("2_2_2","");
                }
                if( StringUtils.isNotBlank(answerLogMap.get("MOBILE"))){
                    map.put("2_3_1",answerLogMap.get("MOBILE"));
                }else{
                    map.put("2_3_2","");
                }
                if( StringUtils.isNotBlank(answerLogMap.get("ADDRESS"))){
                    map.put("2_1_7",answerLogMap.get("ADDRESS"));
                }
                if( StringUtils.isNotBlank(answerLogMap.get("LINK_MAN1"))){
                    map.put("2_4_1",answerLogMap.get("LINK_MAN1"));
                }
                if( StringUtils.isNotBlank(answerLogMap.get("LINK_MAN1_MOBILE"))){
                    map.put("2_6_1",answerLogMap.get("LINK_MAN1_MOBILE"));
                }
                if( StringUtils.isNotBlank(answerLogMap.get("LINK_MAN2"))){
                    map.put("2_7_1",answerLogMap.get("LINK_MAN2"));
                }
                if( StringUtils.isNotBlank(answerLogMap.get("LINK_MAN2_MOBILE"))){
                    map.put("2_9_1",answerLogMap.get("LINK_MAN2_MOBILE"));
                }
                String relation1 = answerLogMap.get("LINK_MAN1_RELATION");
                if( StringUtils.isNotBlank(relation1) ){
                    if( "父母".equals(relation1) ){
                        map.put("2_5_1","");
                    }else if("配偶".equals(relation1) ){
                        map.put("2_5_2","");
                    }else if("子女".equals(relation1) ){
                        map.put("2_5_3","");
                    }else if("兄弟姐妹".equals(relation1) ){
                        map.put("2_5_4","");
                    }else{
                        map.put("2_5_5",relation1);
                    }

                }
                String relation2 = answerLogMap.get("LINK_MAN2_RELATION");
                if( StringUtils.isNotBlank(relation2) ){
                    if( "父母".equals(relation2) ){
                        map.put("2_8_1","");
                    }else if("配偶".equals(relation2) ){
                        map.put("2_8_2","");
                    }else if("子女".equals(relation2) ){
                        map.put("2_8_3","");
                    }else if("兄弟姐妹".equals(relation2) ){
                        map.put("2_8_4","");
                    }else{
                        map.put("2_8_5",relation2);
                    }

                }

            }

            if("1".equals(answerLogMap.get("IS_DIABETES"))){
                map.put("5_1_1","");
            }else if("2".equals(answerLogMap.get("IS_DIABETES"))){
                map.put("5_1_2","");
            }

            if("1".equals(answerLogMap.get("IS_XJGSBS"))){
                map.put("5_10_1","");
            }else if("2".equals(answerLogMap.get("IS_XJGSBS"))){
                map.put("5_10_2","");
            }

            map.put("5_7_1",answerLogMap.get("GLU"));
            map.put("5_7_2",answerLogMap.get("HBA1C"));
            map.put("5_7_3",answerLogMap.get("GLU_DATE"));
        }

        if (r != null && r.getOptions() != null) {

            String oldValue = "";
            if (r.getOptions() != null) {

                String id;
                for( ResultOption ro : r.getOptions() ){
                    id = ro.getQuestionSetId()+"_"+ro.getQuestionId()+"_";
                    if( !oldValue.equals(id) ){
                        removeMapByLike(map,id);
                        oldValue = id;
                    }
                }

                for (ResultOption ro : r.getOptions()) {
                    String dictName = comboxService.getDictName(qn,ro.getQuestionSetId(), ro.getQuestionId(),ro.getResultId(), ro.getResutlStr());
                    if (dictName == null) {
                        map.put(ro.getQuestionSetId() + "_"+ ro.getQuestionId() + "_" + ro.getResultId(),ro.getResutlStr());
                    } else {
                        map.put(ro.getQuestionSetId() + "_"+ ro.getQuestionId() + "_" + ro.getResultId(),dictName);
                        map.put("dict" + ro.getQuestionSetId() + "_"+ ro.getQuestionId() + "_" + ro.getResultId(),ro.getResutlStr());
                    }
                }
            }
        }
        model.addAttribute("paper", qn);
        model.addAttribute("resultOptionMap", map);
        model.addAttribute("resultOptionMapJson", JsonMapper.objectToJson(map));

        model.addAttribute("UQSBeginTime", UQSBeginTime);
        model.addAttribute("uqsVersion", uqsVersion);
        model.addAttribute("patientName", answerLogMap.get("PATIENT_NAME"));
        model.addAttribute("patientId", patientId);
        model.addAttribute("lccCode", answerLogMap.get("LCC_CODE"));

        model.addAttribute("isDiabetes", answerLogMap.get("IS_DIABETES"));
        model.addAttribute("state", answerLogMap.get("STATE") == null ? "" : answerLogMap.get("STATE") );

        if ("1".equals(answerLogMap.get("STATE"))) {
            return "msg/join/showQn";
        }
        return "msg/join/joinUqs";
    }

    //   刨除Map里 模糊查询到的key然后删除key-value
    public void removeMapByLike(Map map , String id){
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if( entry.getKey().toString().contains(id) ){
                map.remove(entry.getKey());
                iter = map.entrySet().iterator();
            }
        }
    }


    @RequestMapping("openmodaladdPatient")
    public String addPatient(@RequestParam(value = "patientId",required = false) String patientId,
                             Model model){
        if( StringUtils.isBlank(patientId) ){
            return "msg/newjoin/addPatient";
        }else{
            PipCommPatientNewjoin po = newpatientDao.selectByPrimaryKey(patientId);
            model.addAttribute("po",po);
            return "msg/newjoin/addPatient";
        }
    }

    @RequestMapping("isHandAdd")
    @ResponseBody
    public ResponseEntity<?> isHandAdd(String patientId){
        Map<String,Object> m = new HashMap<String,Object>();
        if( newpatientDao.selectByPrimaryKey(patientId) == null ){
            m.put("isHandAdd",false);
        }else{
            m.put("isHandAdd",true);
        }
        return new ResponseEntity(m, HttpStatus.OK);
    }

    /**
     * 入选补录 患者新增保存
     * @param
     * @return
     */
    @RequestMapping("patientSave")
    @ResponseBody
    public ResponseEntity<?> addPatientSave(HttpServletRequest req, HttpServletResponse resp,
                                            PipCommPatientNewjoin newpo){
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("success",true);
        String newPatientId = newpatientDao.getNewPatientid(Securitys.getUser().getLccCode());
        newpo.setPatientId(newPatientId);

        PipCommPatient po = new PipCommPatient();
            po.setPatientId(newpo.getPatientId());
            po.setPatientName(newpo.getPatientName());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            po.setLastFollowviewDate(sdf.format(newpo.getHospitaldate()));
        try {
            po.setBirthday(sdf.parse(newpo.getBirthday()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
            po.setCreateDate(new Date());
            po.setCreateBy(Securitys.getUserName());
            po.setIdNumber(newpo.getIdnumber());
            po.setProjectId("004");
            po.setSex(newpo.getSex());
            po.setIsDiabetes(Integer.parseInt(newpo.getIsdiabetes()));
            po.setMobile(newpo.getMobile());
            po.setIsJoinMsg("1");
            po.setIsSubscribe("1");
            po.setLccCode(Securitys.getUser().getLccCode());

        Map<String,String> m_insert = new HashMap<String,String>();
        m_insert.put("patientId",newpo.getPatientId());
        m_insert.put("sex",newpo.getSex());
        m_insert.put("ageGrade",newpo.getAgegrade());
        m_insert.put("eduLevel",newpo.getEduLevel());
        m.put("pid",newPatientId);

        PipCommPatientDate pDatepo = new PipCommPatientDate();
        pDatepo.setPatientId(newPatientId);
        pDatepo.setProjectId("004");
        pDatepo.setPatientName(newpo.getPatientName());
        try {
            newpatientDao.insert(newpo); //插入新增patient表
            patientDao.insertSelective(po); // 插入patient表
            patientDao.updateProcessType(po.getPatientId(),"2");
            newpatientDao.insertGroup(m_insert); // 更新分组数据表 避免分组异常
            patientDateDao.insertSelective(pDatepo);
            return new ResponseEntity(m, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            m.put("success",false);
            return new ResponseEntity(m, HttpStatus.OK);
        }


    }

}
