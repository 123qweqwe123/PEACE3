package com.bdcor.pip.web.qn.service.impl;

import com.bdcor.pip.core.utils.GenerateKey;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.domain.PipSysParameter;
import com.bdcor.pip.web.common.utils.CacheUtils;
import com.bdcor.pip.web.data.dao.PipCommDictIcdMapper;
import com.bdcor.pip.web.data.dao.PipUqsAnswerMinMapper;
import com.bdcor.pip.web.data.dao.PipUqsOptionMapper;
import com.bdcor.pip.web.data.domain.*;
import com.bdcor.pip.web.pro.promgt.dao.LccDao;
import com.bdcor.pip.web.qn.dao.*;
import com.bdcor.pip.web.qn.domain.*;
import com.bdcor.pip.web.qn.filter.EventCheckFilter;
import com.bdcor.pip.web.qn.filter.EventExportFilter;
import com.bdcor.pip.web.qn.filter.EventFilter;
import com.bdcor.pip.web.qn.service.EventMgtService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EventMgtServiceImpl implements EventMgtService {
    @Autowired
    private UqsEventDao uqsEventDao;

    @Override
    public List<EventVo> getEventPatientList(EventFilter filter) {
        filter.setProjectId(Securitys.getCurrentProject());
        filter.setUserId(Securitys.getUserId());
        return uqsEventDao.getEventPatientList(filter);
    }

    @Override
    public List<Map<String, Object>> showEventList(EventFilter filter) {
        filter.setProjectId(Securitys.getCurrentProject());
        filter.setUserId(Securitys.getUserId());
        return uqsEventDao.showEventList(filter);
    }

    @Override
    public List<Map<String, Object>> showUsrFileList(EventFilter filter) {
        filter.setProjectId(Securitys.getCurrentProject());
        filter.setUserId(Securitys.getUserId());
        return uqsEventDao.showUsrFileList(filter);
    }

    @Override
    public List<Map<String, Object>> showAllUsrFileList(EventFilter filter) {
        filter.setProjectId(Securitys.getCurrentProject());
        filter.setUserId(Securitys.getUserId());
        return uqsEventDao.showAllUsrFileList(filter);
    }

    @Override
    public List<Map<String, Object>> showReportList(EventFilter filter) {
        filter.setProjectId(Securitys.getCurrentProject());
        filter.setUserId(Securitys.getUserId());
        return uqsEventDao.showReportList(filter);
    }

    @Override
    public void collectDictFile(String logMinId, String patientId, String fileInfo, String inHosDate, String outHosDate, String hosName) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("PATIENT_ID", patientId);
        map.put("LOG_MIN_ID", logMinId);
        map.put("FILE_INFO", fileInfo);
        map.put("HOS_NAME", hosName);
        map.put("CREATE_BY", Securitys.getUserId());
        map.put("IN_HOS_DATE", inHosDate);
        map.put("OUT_HOS_DATE", outHosDate);
        /*try{
            map.put("IN_HOS_DATE",new SimpleDateFormat("yyyy-MM-dd").parse(inHosDate));
			map.put("OUT_HOS_DATE",new SimpleDateFormat("yyyy-MM-dd").parse(outHosDate));
			
		}catch (Exception e) {
			
		}*/
        uqsEventDao.updateEventUsrFileState(map);

    }

    @Override
    public List<Map<String, String>> getDictFiles() {
        return uqsEventDao.getDictFiles();
    }

    @Override
    public List<Map<String, String>> getHisList() {
        return uqsEventDao.getHisList();
    }

    @Override
    public List<Map<String, Object>> getEventList(EventFilter filter) {
        filter.setProjectId(Securitys.getCurrentProject());
        filter.setUserId(Securitys.getUserId());
        return uqsEventDao.getEventList(filter);
    }

    @Override
    public List<Map<String, Object>> getEventListExcel(EventExportFilter filter) {
        filter.setProjectId(Securitys.getCurrentProject());
        filter.setUserId(Securitys.getUserId());
        return uqsEventDao.getEventListExcel(filter);
    }


    @Autowired
    PipCommEventMapper pipCommEventMapper;
    @Autowired
    PipUqsAnswerMinMapper pipUqsAnswerMinMapper;
    @Autowired
    PipCommDictIcdMapper pipCommDictIcdMapper;
    @Autowired
    PipUqsAnswerMapper pipUqsAnswerMapper;
    @Autowired
    PipUqsOptionMapper pipUqsOptionMapper;
    @Autowired
    PipCommEventCheckMapper pipCommEventCheckMapper;
    @Autowired
    PipCommEventCheckErMapper pipCommEventCheckErMapper;
    @Autowired
    PipCommEventCheckPersonMapper pipCommEventCheckPersonMapper;
    @Autowired
    LccDao lccDao;


    /**
     * 获取事件审定列表
     *
     * @param filter
     * @return
     */
    @Override
    public List<PipCommEventVO> getEventCheckList(EventCheckFilter filter) {
        return pipCommEventMapper.selectByFilter(filter);
    }

    /**
     * 获取事件明细信息
     *
     * @param eventCode
     * @param model
     */
    @Override
    public void getEventInfo(String eventCode, Model model) {
        PipCommEventExample exp = new PipCommEventExample();
        exp.createCriteria().andEventCodeEqualTo(eventCode);
        PipCommEvent event = pipCommEventMapper.selectByExample(exp).get(0);
        model.addAttribute("event", event);
        model.addAttribute("check", getEventCheckByEventCode(eventCode));
        model.addAttribute("er", getEventCheckErByEventCode(eventCode));

        // 是否死亡事件， 死亡（医院） 死亡（家中）
        String eventName = event.getEventName();
        if ("死亡（医院）".equals(eventName)) {
            model.addAttribute("eventType", 1);
        } else if ("死亡（家中）".equals(eventName)) {
            model.addAttribute("eventType", 2);
        }

        model.addAttribute("eventending", getEventEnding(event));//出院转归
        model.addAttribute("deathDate", getDeathDate(event));//死亡日期 从问卷抓取
        model.addAttribute("originDesc", getOriginalDesc(event));//审定前事件描述 从问卷抓取
        model.addAttribute("lccCode", event.getLccCode());
        model.addAttribute("lccName", lccDao.getLcc(event.getLccCode(), Securitys.getCurrentProject()).getLccName());

        // 获取该PID 对应的所有事件
        EventCheckFilter filter = new EventCheckFilter();
        filter.setPatientId(event.getPatientId());
        filter.setShowAddEvents(1);

        filter.setSidx("e.event_code");

        List<PipCommEventVO> commEvents = pipCommEventMapper.selectByFilter(filter);
        for (PipCommEventVO vo : commEvents) {
            String srcDesc = getOriginalDesc(vo);
            vo.setSrcdescStr(srcDesc);
        }
        model.addAttribute("eventList", commEvents);
        model.addAttribute("isAuditUser", checkAuditUser());
    }

    // 获取出院转归
    private String getEventEnding(PipCommEvent event) {
        String eventEnding = "";
        String questionnaireId = event.getQuestionnaireId();
        if ("004008".equals(questionnaireId)) { // 1-7
            PipUqsAnswerMinExample exp1 = new PipUqsAnswerMinExample();
            // 出院转归
            exp1.createCriteria().andQuestionnaireIdEqualTo("004.008.001")
                .andQuestionsetIdEqualTo("1")
                .andQuestionIdEqualTo("7")
                .andPatientIdEqualTo(event.getPatientId())
                .andLogMinIdEqualTo(event.getLogMinId());
            List<PipUqsAnswerMin> ansList = pipUqsAnswerMinMapper.selectByExample(exp1);
            if (ansList.size() > 0) {
                PipUqsAnswerMin ans = ansList.get(0);
                String displayName = getOptionDisplayName(ans.getQuestionnaireId(), ans.getQuestionsetId(),
                    ans.getQuestionId(), ans.getOptionId());
                return displayName;
            }
        }

        String uqsNo = event.getUqsNo();
        // 循环题只有其中的一道题会触发事件
        // 获取questionset_id  6_8_10 6_9_1
        String questionsetId = StringUtils.substringBefore(uqsNo, "_");
        String questionId = StringUtils.substringBetween(uqsNo, "_");
        String optionId = StringUtils.substringAfterLast(uqsNo, "_");
        String outHospitalQuestion = "";// 出院转归题号

        // 判断是住院原因还是治疗措施
        int a = Integer.parseInt(questionId) % 8;
        if (a == 0) {
            outHospitalQuestion = Integer.parseInt(questionId) + 2 + "";
        } else {
            outHospitalQuestion = Integer.parseInt(questionId) + 1 + "";
        }
        String newQuestionnaireId = "004." + questionnaireId.substring(3, 6) + ".001";

        PipUqsAnswerExample answerExample6 = new PipUqsAnswerExample();
        answerExample6.createCriteria().andQuestionnaireIdEqualTo(newQuestionnaireId)
            .andQuestionsetIdEqualTo(questionsetId)
            .andQuestionIdEqualTo(outHospitalQuestion)
            .andPatientIdEqualTo(event.getPatientId());
        List<PipUqsAnswer> ansReason = pipUqsAnswerMapper.selectByExample(answerExample6);
        if (ansReason.size() > 0) {
            return getOptionDisplayName(newQuestionnaireId, questionsetId, outHospitalQuestion, ansReason.get(0).getOptionId());
        }
        return "";
    }

    private PipCommEventCheck getEventCheckByEventCode(String eventCode) {
        PipCommEventCheckExample exp = new PipCommEventCheckExample();
        exp.createCriteria().andEventCodeEqualTo(eventCode);
        List<PipCommEventCheck> eventChecks = pipCommEventCheckMapper.selectByExample(exp);
        if (eventChecks.size() > 0) {
            return eventChecks.get(0);
        }
        return null;
    }

    private PipCommEventCheckEr getEventCheckErByEventCode(String eventCode) {
        PipCommEventCheckErExample exp = new PipCommEventCheckErExample();
        exp.createCriteria().andEventCodeEqualTo(eventCode);
        List<PipCommEventCheckEr> eventChecks = pipCommEventCheckErMapper.selectByExample(exp);
        if (eventChecks.size() > 0) {
            return eventChecks.get(0);
        }
        return null;
    }




    /*
     * 事件对应 004006 004008 004001 004011 004014 五张问卷
     * 004006 004008 答案保存在 pip_uqs_answer_min中，问卷可以重复做，通过 log_min_id 来识别
     * 004001 004011 004014 答案保存在 pip_uqs_answer 中，问卷不能重复
     */

    // 获取审定前事件描述
    private String getOriginalDesc(PipCommEvent event) {
        StringBuilder inHospitalReason = new StringBuilder(); // 住院主要原因
        StringBuilder inHospitalTreat = new StringBuilder();    // 住院治疗措施
        StringBuilder deathReason = new StringBuilder(); // 死亡原因

        String questionnaireId = event.getQuestionnaireId();
        String uqsNo = event.getUqsNo();
        // 循环题只有其中的一道题会触发事件
        // 获取questionset_id  6_8_10 6_9_1
        String questionsetId = StringUtils.substringBefore(uqsNo, "_");
        String questionId = StringUtils.substringBetween(uqsNo, "_");
        String optionId = StringUtils.substringAfterLast(uqsNo, "_");

        // 该问卷只有是否死亡和住院原因会触发事件，即 UQS_NO 只有 1_4_x 1_8_x
        if ("004006".equals(questionnaireId)) {  // 死亡事件上报问卷
            PipUqsAnswerMinExample exp1 = new PipUqsAnswerMinExample();

            // 死亡原因
            if("4".equals(questionId)) {
                exp1.createCriteria().andQuestionnaireIdEqualTo("004.006.001")
                    .andQuestionsetIdEqualTo("1")
                    .andQuestionIdEqualTo("3")
                    .andOptionIdEqualTo("1")
                    .andPatientIdEqualTo(event.getPatientId())
                    .andLogMinIdEqualTo(event.getLogMinId());

                List<PipUqsAnswerMin> pipUqsAnswerMinList = pipUqsAnswerMinMapper.selectByExample(exp1);
                if (pipUqsAnswerMinList.size() > 0) {
                    deathReason.append(pipUqsAnswerMinList.get(0).getAnswer());
                }
            }

            // 住院主要原因
            if("8".equals(questionId)) {
                PipUqsAnswerMinExample exp2 = new PipUqsAnswerMinExample();
                exp2.createCriteria().andQuestionnaireIdEqualTo("004.006.001")
                    .andQuestionsetIdEqualTo("1")
                    .andQuestionIdEqualTo("8")
                    .andOptionIdEqualTo(optionId)
                    .andPatientIdEqualTo(event.getPatientId())
                    .andLogMinIdEqualTo(event.getLogMinId());
                List<PipUqsAnswerMin> inReasons = pipUqsAnswerMinMapper.selectByExample(exp2);
                convertAnswerMinListToStringBuffer(inHospitalReason, inReasons);
            }

        }

        // 该问卷只有住院原因和治疗措施会触发事件，即 UQS_NO 只有 1_5_x 1_6_x
        if ("004008".equals(questionnaireId)) {

            // 住院主要原因
            if("5".equals(questionId)) {
                PipUqsAnswerMinExample exp4 = new PipUqsAnswerMinExample();

                exp4.createCriteria().andQuestionnaireIdEqualTo("004.008.001")
                    .andQuestionsetIdEqualTo("1")
                    .andQuestionIdEqualTo("5")
                    .andOptionIdEqualTo(optionId)
                    .andPatientIdEqualTo(event.getPatientId())
                    .andLogMinIdEqualTo(event.getLogMinId());
                List<PipUqsAnswerMin> inReasons = pipUqsAnswerMinMapper.selectByExample(exp4);
                convertAnswerMinListToStringBuffer(inHospitalReason, inReasons);
            }
            // 住院接受的治疗措施
            if("6".equals(questionId)) {
                PipUqsAnswerMinExample exp5 = new PipUqsAnswerMinExample();
                exp5.createCriteria().andQuestionnaireIdEqualTo("004.008.001")
                    .andQuestionsetIdEqualTo("1")
                    .andQuestionIdEqualTo("6")
                    .andOptionIdEqualTo(optionId)
                    .andPatientIdEqualTo(event.getPatientId())
                    .andLogMinIdEqualTo(event.getLogMinId());
                List<PipUqsAnswerMin> inTreats = pipUqsAnswerMinMapper.selectByExample(exp5);
                convertAnswerMinListToStringBuffer(inHospitalTreat, inTreats);
            }

        } else {

            String inHospitalReasonQuestion = "";// 住院原因题号
            String inHospitalTreatQuestion = ""; // 治疗措施题号

            // 判断是住院原因还是治疗措施
            int a = Integer.parseInt(questionId) % 8;
            if (a == 0) {
                inHospitalReasonQuestion = questionId;
              //  inHospitalTreatQuestion = Integer.parseInt(questionId) + 1 + "";
            } else {
            //    inHospitalReasonQuestion = Integer.parseInt(questionId) - 1 + "";
                inHospitalTreatQuestion = questionId;
            }
            String newQuestionnaireId = "004." + questionnaireId.substring(3, 6) + ".001";
            // 修改评审前事件描述为只根据触发事件的问题
            if(StringUtils.isNotBlank(inHospitalReasonQuestion)) {
                PipUqsAnswerExample answerExample6 = new PipUqsAnswerExample();
                answerExample6.createCriteria().andQuestionnaireIdEqualTo(newQuestionnaireId)
                    .andQuestionsetIdEqualTo(questionsetId)
                    .andAnswerEqualTo(optionId)
                    .andQuestionIdEqualTo(inHospitalReasonQuestion)
                    .andPatientIdEqualTo(event.getPatientId());
                List<PipUqsAnswer> ansReason = pipUqsAnswerMapper.selectByExample(answerExample6);
                convertAnswerListToStringBuffer(inHospitalReason, ansReason);
            }

            if (StringUtils.isNotBlank(inHospitalTreatQuestion)) {
                PipUqsAnswerExample answerExample7 = new PipUqsAnswerExample();
                answerExample7.createCriteria().andQuestionnaireIdEqualTo(newQuestionnaireId)
                    .andQuestionsetIdEqualTo(questionsetId)
                    .andOptionIdEqualTo(optionId)
                    .andQuestionIdEqualTo(inHospitalTreatQuestion)
                    .andPatientIdEqualTo(event.getPatientId());
                List<PipUqsAnswer> ansTreat = pipUqsAnswerMapper.selectByExample(answerExample7);
                convertAnswerListToStringBuffer(inHospitalTreat, ansTreat);
            }

        }
        StringBuffer result = new StringBuffer();
        if(StringUtils.isNotBlank(inHospitalReason.toString())) {
            result.append("住院原因:");
            result.append(inHospitalReason.toString());
            result.append("<br/>");
        }
        if(StringUtils.isNotBlank(inHospitalTreat.toString())) {
            result.append("住院治疗措施:");
            result.append(inHospitalTreat.toString());
            result.append("<br/>");
        }
        if ("1".equals(event.getIsDeath()) && StringUtils.isNotBlank(deathReason.toString())) {    // 死亡事件
            result.append("死亡原因:");
            result.append(deathReason.toString());
        }
        return result.toString();
    }

    private void convertAnswerListToStringBuffer(StringBuilder sBuffer, List<PipUqsAnswer> answerMinList) {
        for (PipUqsAnswer ans : answerMinList) {
            String answer = ans.getAnswer();
            if (StringUtils.isNotEmpty(answer) && answer.toUpperCase().contains("_")) {    // 从ICD获取
                sBuffer.append(convrtIcd(answer)).append(";");
            } else if (StringUtils.isNotEmpty(answer)) {  // 直接获取答案
                sBuffer.append(answer).append(";");
            } else {
                sBuffer.append(getOptionDisplayName(ans.getQuestionnaireId(), ans.getQuestionsetId(), ans.getQuestionId(), ans.getOptionId())).append(";");
            }
        }
    }

    private void convertAnswerMinListToStringBuffer(StringBuilder sBuffer, List<PipUqsAnswerMin> answerMinList) {
        for (PipUqsAnswerMin ans : answerMinList) {
            String answer = ans.getAnswer();
            if (StringUtils.isNotEmpty(answer) && answer.toUpperCase().startsWith("I")) {    // 从ICD获取
                sBuffer.append(convrtIcd(answer)).append(";");
            } else if (StringUtils.isEmpty(answer)) {   // 获取选项的
                String displayName = getOptionDisplayName(ans.getQuestionnaireId(), ans.getQuestionsetId(),
                    ans.getQuestionId(), ans.getOptionId());
                if (StringUtils.isNotEmpty(displayName)) {
                    sBuffer.append(displayName).append(";");
                }
            } else {
                sBuffer.append(answer).append(";");
            }
        }
    }

    private String getOptionDisplayName(String qnId, String qsId, String qId, String oId) {
        PipUqsOptionExample exp = new PipUqsOptionExample();
        // 转化 questionnaireId，如 004.008.001 转化为 004008
        qnId = qnId.substring(0, 3) + qnId.substring(4, 7);
        exp.createCriteria().andQuestionnaireIdEqualTo(qnId)
            .andQuestionsetIdEqualTo(qsId)
            .andQuestionIdEqualTo(qId)
            .andOptionIdEqualTo(oId);

        List<PipUqsOption> options = pipUqsOptionMapper.selectByExample(exp);
        if (options.size() > 0) {
            return options.get(0).getDisplayName();
        }
        return "";
    }


    /**
     * 获取 ICD 对应的字典值
     *
     * @param answer
     * @return
     */
    private String convrtIcd(String answer) {
        // 原始答案表保存值为 I25.210_7376 转化为id7376，然后从icd表通过id获取具体的字典值
        String icdId = StringUtils.substringAfterLast(answer, "_");
        PipCommDictIcdExample exp = new PipCommDictIcdExample();
        exp.createCriteria().andIdEqualTo(icdId);
        List<PipCommDictIcd> icds = pipCommDictIcdMapper.selectByExample(exp);
        if (icds.size() > 0) {
            return icds.get(0).getVname();
        }
        return "";
    }

    // 获取死亡日期
    private String getDeathDate(PipCommEvent event) {
        PipUqsAnswerMinExample exp = new PipUqsAnswerMinExample();
        PipUqsAnswerMinExample.Criteria criteria = exp.createCriteria();
        criteria.andQuestionnaireIdEqualTo("004.006.001")
            .andQuestionsetIdEqualTo("1")
            .andQuestionIdEqualTo("2")
            .andOptionIdEqualTo("1")
            .andPatientIdEqualTo(event.getPatientId());

        if (StringUtils.isNoneEmpty(event.getLogMinId())) {
            criteria.andLogMinIdEqualTo(event.getLogMinId());
        }
        List<PipUqsAnswerMin> pipUqsAnswerMinList = pipUqsAnswerMinMapper.selectByExample(exp);
        if (pipUqsAnswerMinList.size() > 0) {
            return pipUqsAnswerMinList.get(0).getAnswer();
        }
        return "N/A";
    }


    @Override
    public List<PipSysParameter> getCurrdesctree() {
        List<PipSysParameter> sysParameters = (List<PipSysParameter>) CacheUtils.getCacheValue(CacheUtils.CACHE_SYS_PARAMETER, "S001");
        return sysParameters;
    }


    @Override
    public void saveOrUpdate(PipCommEventCheck check, PipCommEventCheckEr er) {
        String eventCode = check.getEventCode();
        PipCommEventCheckExample exp1 = new PipCommEventCheckExample();
        exp1.createCriteria().andEventCodeEqualTo(eventCode);
        List<PipCommEventCheck> eventChecks = pipCommEventCheckMapper.selectByExample(exp1);
        if (eventChecks.size() > 0) {// 修改
            PipCommEventCheck check1 = eventChecks.get(0);
            check.setId(check1.getId());
            check.setCreateBy(check1.getCreateBy());
            check.setCreateTime(check1.getCreateTime());
            check.setUpdateBy(Securitys.getUserId());
            check.setUpdateTime(new Date());
            pipCommEventCheckMapper.updateByPrimaryKey(check);

            PipCommEventCheckErExample exp2 = new PipCommEventCheckErExample();
            exp2.createCriteria().andEventCodeEqualTo(eventCode);
            PipCommEventCheckEr er1 = pipCommEventCheckErMapper.selectByExample(exp2).get(0);
            Short erId = er1.getId();
            er.setId(erId);
            er.setUpdateBy(Securitys.getUserId());
            er.setUpdateTime(new Date());
            er.setCreateTime(er1.getCreateTime());
            er.setCreateBy(er1.getCreateBy());
            pipCommEventCheckErMapper.updateByPrimaryKey(er);   // 更新全部字段

        } else { // 新增
            String checkId = GenerateKey.getKey(GenerateKey.PREFIX_CHK);
            String checkErId = GenerateKey.getKey(GenerateKey.PREFIX_CHKER);
            check.setId(Short.parseShort(checkId));
            er.setId(Short.parseShort(checkErId));
            check.setCreateBy(Securitys.getUserId());
            check.setCreateTime(new Date());
            er.setCreateTime(new Date());
            er.setCreateBy(Securitys.getUserId());
            pipCommEventCheckMapper.insert(check);
            pipCommEventCheckErMapper.insert(er);

        }

        PipCommEvent event = new PipCommEvent();
        event.setCheckStatus(check.getReportStatus());
        PipCommEventExample eventExample = new PipCommEventExample();
        eventExample.createCriteria().andEventCodeEqualTo(eventCode);
        pipCommEventMapper.updateByExampleSelective(event, eventExample);
    }

    @Override
    public PipCommEventCheckEr getErByEventCode(String eventCode) {
        return getEventCheckErByEventCode(eventCode);
    }

    /**
     * 判断当前用户是否有审核员角色
     *
     * @return
     */
    @Override
    public boolean checkAuditUser() {
        PipCommEventCheckPersonExample exp = new PipCommEventCheckPersonExample();
        exp.createCriteria().andUserIdEqualTo(Securitys.getUserId());
        List<PipCommEventCheckPerson> personList = pipCommEventCheckPersonMapper.selectByExample(exp);
        if (personList.size() > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addAuditUser() {
        PipCommEventCheckPersonExample exp = new PipCommEventCheckPersonExample();
        exp.createCriteria().andUserIdEqualTo(Securitys.getUserId());
        if (pipCommEventCheckPersonMapper.selectByExample(exp).size() > 0) {
            return true;
        }
        String id = GenerateKey.getKey(GenerateKey.PREFIX_CHKP);
        PipCommEventCheckPerson person = new PipCommEventCheckPerson();
        person.setId(Short.parseShort(id));
        person.setSequence((short) 0);
        person.setUserId(Securitys.getUserId());
        person.setUserName(Securitys.getUserName());
        person.setType(getType());
        pipCommEventCheckPersonMapper.insert(person);
        return true;
    }

    private String getType() {
        List<PipCommEventCheckPerson> list = pipCommEventCheckPersonMapper.selectByExample(null);
        int size = list.size() + 65;
        return Character.toString((char) (size));
    }

    private String getSequence(String eventCode) {
        PipCommEventExample exp = new PipCommEventExample();
        exp.createCriteria().andEventCodeLike(eventCode + "%");
        List<PipCommEvent> events = pipCommEventMapper.selectByExample(exp);

        PipCommEventCheckPersonExample exp1 = new PipCommEventCheckPersonExample();
        exp1.createCriteria().andUserIdEqualTo(Securitys.getUserId());
        PipCommEventCheckPerson person = pipCommEventCheckPersonMapper.selectByExample(exp1).get(0);
        return person.getType() + (String.format("%03d", events.size()));
    }

    @Override
    public String addEvent(String eventCode, Model model) {
        PipCommEventExample exp = new PipCommEventExample();
        exp.createCriteria().andEventCodeEqualTo(eventCode);
        PipCommEvent srcEvent = pipCommEventMapper.selectByExample(exp).get(0);
        srcEvent.setEventDate(new Date());
        srcEvent.setCreateBy(Securitys.getUserId());
        srcEvent.setCreateDate(new Date());

        // 事件ID 有7位或者8位

        Pattern p = Pattern.compile("(\\d+)(\\D\\d+)*");
        Matcher m = p.matcher(eventCode);
        String srcEventCode = "";
        while (m.find()) {
            srcEventCode = m.group(1);
        }

        String newEventCode = srcEventCode + getSequence(eventCode);
        srcEvent.setEventCode(newEventCode);
        srcEvent.setCheckStatus((short) 1);
        pipCommEventMapper.insertSelective(srcEvent);

        return newEventCode;
    }

    @Override
    public List<PipCommEventExportVO> getEvent2exportList(EventCheckFilter filter) {

        return pipCommEventMapper.select4export(filter);
    }

    // /Users/huangrupeng/work/java/idea/BDCOR/CHINAPEACE3-web/target/CHINAPEACE3-web/WEB-INF/classes/exceltemplate/eventcheck.xls
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String srcFilePath = "/Users/huangrupeng/work/java/idea/BDCOR/CHINAPEACE3-web/target/CHINAPEACE3-web/WEB-INF/classes/exceltemplate/eventcheck.xls";
        InputStream is = new BufferedInputStream(new FileInputStream(srcFilePath));
        org.apache.poi.ss.usermodel.Workbook hssfWorkbook = null;
        hssfWorkbook = WorkbookFactory.create(is);

    }

    @Override
    public String updateEventCheckStatus(String eventCodeList) {
        String[] eventCodes = eventCodeList.split(",|，|\\n");
        int sucCount = 0;
        StringBuilder msg = new StringBuilder();
        for (String eventCode : eventCodes) {
            if (StringUtils.isNoneBlank(eventCode)) {
                eventCode = eventCode.trim();
                PipCommEventExample exp = new PipCommEventExample();
                exp.createCriteria().andEventCodeEqualTo(eventCode);
                List<PipCommEvent> events = pipCommEventMapper.selectByExample(exp);

                if (events.size() > 0) {
                    PipCommEvent event = events.get(0);
                    if (event.getCheckStatus() != null) {
                        short status = event.getCheckStatus();
                        msg.append(eventCode + "状态已为");
                        if (status == 1) {
                            msg.append("待审核");
                        } else if (status == 2) {
                            msg.append("需再审核");
                        } else if (status == 3) {
                            msg.append("有效");
                        } else if (status == 4) {
                            msg.append("评审后需补充支持性文件");
                        } else if (status == 5) {
                            msg.append("现有资料不足无法补充");
                        } else if (status == 6) {
                            msg.append("无效-非结局事件");
                        } else if (status == 7) {
                            msg.append("无效-重复");
                        } else if (status == 8) {
                            msg.append("需讨论");
                        } else if (status == 10) {
                            msg.append("无任何资料");
                        }
                        msg.append("不能修改<br/>");
                    } else {
                        event.setCheckStatus((short) 1);
                        pipCommEventMapper.updateByExampleSelective(event, exp);
                        sucCount++;
                    }
                } else {
                    msg.append(eventCode + "不存在;<br/>");
                }
            }
        }
        if (sucCount > 0) {
            msg.append("更新成功" + sucCount + "条<br/>");
        }
        return msg.toString();
    }
}
