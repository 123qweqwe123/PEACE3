package com.bdcor.pip.web.qn.service.impl;

import com.bdcor.pip.client.vo.paper.Paper;
import com.bdcor.pip.client.vo.paper.Result;
import com.bdcor.pip.client.vo.paper.ResultOption;
import com.bdcor.pip.client.xml.model.result.ResultDoctypeDocumentBean;
import com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean;
import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;
import com.bdcor.pip.core.utils.Securitys;
import com.bdcor.pip.web.common.dao.ComboxMapper;
import com.bdcor.pip.web.material.supp.dao.PipMmsScmarchivesMapper;
import com.bdcor.pip.web.qn.dao.AnswerQnDao;
import com.bdcor.pip.web.qn.dao.PipUqsAnswerMapper;
import com.bdcor.pip.web.qn.dao.PipUqsAnswerqnLogMapper;
import com.bdcor.pip.web.qn.dao.UqsEventDao;
import com.bdcor.pip.web.qn.domain.PipUqsAnswer;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerExample;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerqnLog;
import com.bdcor.pip.web.qn.domain.PipUqsAnswerqnLogExample;
import com.bdcor.pip.web.qn.filter.AnswerQnLogFilter;
import org.apache.xmlbeans.XmlOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhang.rw on 16-7-6.
 */
@Service
public class AnswerQnlogServiceImpl {

    @Autowired
    private PipUqsAnswerqnLogMapper qnlogDao;

    @Autowired
    private AnswerQnDao answerQnDao;

    @Autowired
    private UqsEventDao uqsEventDao;

    @Autowired
    private ComboxMapper comboxDao;

    @Autowired
    private PipMmsScmarchivesMapper pipMmsScmarchivesMapper;

    @Autowired
    private PipUqsAnswerMapper ansDao;

    public List<PipUqsAnswerqnLog> listData(AnswerQnLogFilter filter) {
        return qnlogDao.listData(filter);
    }

    /**
     * 获取问卷列表数据
     *
     * @param filter
     * @return
     */
    public List<Map<String, Object>> getData(AnswerQnLogFilter filter) {
        return qnlogDao.getData(filter);
    }

    /**
     * 获取病人详细信息
     *
     * @param pid
     * @return
     */
    public Map<String, Object> getPatientInfo(String pid) {
        return qnlogDao.getPatientInfoById(pid);
    }

    /**
     * 获取病人基本信息 完成问卷信息
     *
     * @param pid
     * @param qnid
     * @return
     */
    public Map<String, String> getUqsData(String pid, String qnid ,String uqsfile) {
        List<Map<String,String>> list = qnlogDao.getUqsData(pid, qnid,uqsfile);
        if( list.size() > 0 ){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Transactional(
            propagation = Propagation.REQUIRED,
            isolation = Isolation.DEFAULT,
            rollbackFor = Exception.class
    )
    public void saveQn(HttpServletRequest request, Paper paper ,Result src_r) throws Exception {

        Result r = new Result(request, paper);

        src_r.setOptions(r.getOptions());
        r = src_r; //保留表头信息

        //保存问卷文件
        String filePath = CustomizedPropertyPlaceholderConfigurer.getContextProperty("uqs_result_path") + "/"
                + new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/";
        if (!new File(filePath).exists()) {
            new File(filePath).mkdirs();
        }

        PipUqsAnswerqnLog qnlog = new PipUqsAnswerqnLog();
        qnlog.setState(4);
        PipUqsAnswerqnLogExample pe = new PipUqsAnswerqnLogExample();
            pe.createCriteria().andPatientIdEqualTo(r.getPatientID()).andUqsVersionEqualTo(r.getUQSVersion());
        List<PipUqsAnswerqnLog> l = qnlogDao.selectByExample(pe);
        String lcc_code = l.get(0).getLccCode();
        qnlogDao.updateByExampleSelective(qnlog , pe);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date endTime = new Date();
        filePath += r.getPatientID() + "_" + r.getUQSVersion() + new Date().getTime() + "_change.xml";
        Map<String, Object> AnswerQnLog = new HashMap<String, Object>();
        AnswerQnLog.put("projectId", Securitys.getCurrentProject());
        AnswerQnLog.put("lccCode", lcc_code);
        AnswerQnLog.put("patientId", r.getPatientID());
        AnswerQnLog.put("uqsVersion", r.getUQSVersion());
        AnswerQnLog.put("startTime", sf.parse(r.getUQSBeginTime()));
        AnswerQnLog.put("endTime", sf.parse(r.getUQSEndDate()));
        AnswerQnLog.put("createBy", r.getOperaterID());
        AnswerQnLog.put("uqsFile", filePath);
        AnswerQnLog.put("state", 1);
        answerQnDao.insertAnswerQnLog(AnswerQnLog);

        saveFile(r, filePath);

        if (r.getOptions() == null || r.getOptions().size() == 0 || request.getParameter("tempSave") != null) {
            return;
        }

        String paperLabel = paper.getPaperVersion().substring(4, 7);
        String col_BLOOD_TEST = "";
        String o_BLOOD_TEST = "";
        if (paperLabel.equals("001")) {
            col_BLOOD_TEST = "BLOOD_TEST";
            o_BLOOD_TEST = "4_1_1,4_1_3";
        } else if (paperLabel.equals("002")) {
            col_BLOOD_TEST = "BLOOD_TEST6";
            o_BLOOD_TEST = "3_3_1";
        } else if (paperLabel.equals("003")) {
            col_BLOOD_TEST = "BLOOD_TEST12";
            o_BLOOD_TEST = "3_3_1";
        } else if (paperLabel.equals("004")) {
            col_BLOOD_TEST = "BLOOD_TEST18";
            o_BLOOD_TEST = "3_3_1";
        } else if (paperLabel.equals("005")) {
            col_BLOOD_TEST = "BLOOD_TEST24";
            o_BLOOD_TEST = "3_3_1";
        }

        Map<String, Object> patientMap = new HashMap<String, Object>();
        patientMap.put("PATIENT_ID", r.getPatientID());
        for (ResultOption o : r.getOptions()) {
            Map<String, Object> aMap = new HashMap<String, Object>();
            aMap.put("projectId", Securitys.getCurrentProject());
            aMap.put("patientId", r.getPatientID());
            aMap.put("uqsVersion", r.getUQSVersion());
            aMap.put("qnSetId", o.getQuestionSetId());
            aMap.put("questionId", o.getQuestionId());
            aMap.put("optionId", o.getResultId());
            aMap.put("answer", o.getResutlStr());
            aMap.put("createDate", endTime);
//            aMap.put("lccCode", Securitys.getUser().getLccCode()); // lcc_code
            aMap.put("lccCode", lcc_code);
            answerQnDao.insertAnswer(aMap);
            //判断采血包是否被使用，如果使用的话，更新采血包的信息
            if ("1".equals(o.getResultId()) && "3".equals(o.getQuestionId()) && "4".equals(o.getQuestionSetId())) {
                if (!org.apache.commons.lang.StringUtils.isEmpty(o.getResutlStr())) {
                    pipMmsScmarchivesMapper.updateStateByBloodCode(Securitys.getUser().getCurrent_projectId(), o.getResutlStr(), "1");
                }
            }
            String oFullId = o.getQuestionSetId() + "_" + o.getQuestionId() + "_" + o.getResultId();
            if (paperLabel.equals("001")) {
                if ("2_3_1".equals(oFullId) && o.getResutlStr().trim().length() > 0) {//手机
                    patientMap.put("MOBILE", o.getResutlStr());
                } else if ("2_2_1".equals(oFullId) && o.getResutlStr().trim().length() > 0) {//固定电话
                    patientMap.put("PHONE", o.getResutlStr());
                } else if ("1_4_1".equals(oFullId) && o.getResutlStr().trim().length() > 0) {//身份证号
                    patientMap.put("ID_NUMBER", o.getResutlStr());
                } else if ("1_2_1".equals(oFullId)) {//性别男
                    patientMap.put("SEX", 1);
                } else if ("1_2_2".equals(oFullId)) {//性别女
                    patientMap.put("SEX", 2);
                } else if ("2_4_1".equals(oFullId)) {//第一联系人
                    patientMap.put("LINK_MAN1", o.getResutlStr());
                } else if ("2_5_1".equals(oFullId)) {//第一联系人与本人关系
                    patientMap.put("LINK_MAN1_RELATION", "父母");
                } else if ("2_5_2".equals(oFullId)) {//第一联系人与本人关系
                    patientMap.put("LINK_MAN1_RELATION", "配偶");
                } else if ("2_5_3".equals(oFullId)) {//第一联系人与本人关系
                    patientMap.put("LINK_MAN1_RELATION", "子女");
                } else if ("2_5_4".equals(oFullId)) {//第一联系人与本人关系
                    patientMap.put("LINK_MAN1_RELATION", "兄弟姐妹");
                } else if ("2_5_5".equals(oFullId)) {//第一联系人与本人关系
                    patientMap.put("LINK_MAN1_RELATION", o.getResutlStr());
                } else if ("2_6_1".equals(oFullId)) {//第一联系人手机
                    patientMap.put("LINK_MAN1_MOBILE", o.getResutlStr());
                }
            }

            //问卷只能答一次，不能修改,如果问卷能修改，此处需要改动
            if (o_BLOOD_TEST.indexOf(o.getQuestionSetId() + "_" + o.getQuestionId() + "_" + o.getResultId()) > -1) {
                Map<String, String> updateBloodMap = new HashMap<String, String>();
                updateBloodMap.put("col", col_BLOOD_TEST);
                updateBloodMap.put("value", "1");
                updateBloodMap.put("patientId", r.getPatientID());
                answerQnDao.updatePatientBlood(updateBloodMap);
            }
        }

        answerQnDao.updatePatientInfo(patientMap);//更新性别、手机、电话、身份证
    }

    private void saveFile(Result r, String filePath) throws Exception {
        ResultQuestionnaireDocumentBean questionD = ResultQuestionnaireDocumentBean.Factory.newInstance();
        com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean.Questionnaire q3 = com.bdcor.pip.client.xml.model.result.ResultQuestionnaireDocumentBean.Questionnaire.Factory.newInstance();
        ResultDoctypeDocumentBean.Doctype doctype = q3.addNewDoctype();

        doctype.setProjectID(r.getProjectID());
        doctype.setProjectName(r.getProjectName());
        doctype.setUQSID(r.getUQSID());
        doctype.setUQSName(r.getUQSName());
        doctype.setUQSVersion(r.getUQSVersion());
        doctype.setVersionCreateDate(r.getVersionCreateDate());
        doctype.setUQSBeginTime(r.getUQSBeginTime());
        doctype.setUQSEndDate(r.getUQSEndDate());
        doctype.setUQSTimeZone(r.getUQSTimeZone());
        doctype.setOperaterID(r.getOperaterID());
        doctype.setOperaterName(r.getOperaterName());
        doctype.setHospitalCode(r.getHospitalCode());
        doctype.setHospitalName(r.getHospitalName());
        doctype.setPatientID(r.getPatientID());
        doctype.setPersonID(r.getPersonID());
        doctype.setPatientName(r.getPatientName());
        doctype.setPatientCode(r.getPatientCode());
        doctype.setUQSIsHold(r.getUQSIsHold());
        doctype.setUQSRemark(r.getUQSRemark());

        List<com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean.Result> resultsList = new ArrayList<com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean.Result>();
        for (ResultOption ro1 : r.getOptions()) {
            com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean.Result ro = q3.addNewResult();
            ro.setQuestionset(ro1.getQuestionSetId());
            ro.setQuestionId(ro1.getQuestionId());
            ro.setOptionid(ro1.getResultId());
            ro.setAnswer(ro1.getResutlStr());
            resultsList.add(ro);
        }
        q3.setResultArray(resultsList.toArray(new com.bdcor.pip.client.xml.model.result.ResultResultDocumentBean.Result[0]));
        questionD.setQuestionnaire(q3);

        XmlOptions options = new XmlOptions();
        options.setSavePrettyPrint();
        // 这里使用默认名空间
        options.setUseDefaultNamespace();

        //生成xml文件
        questionD.save(new File(filePath), options);
        //加密
        //	new XmlFileFilter().encryptFile(filePath);
    }


    public Map<String, Object> checkBloodNo(String bloodNo,String pid,String uqsver) {
        PipUqsAnswerqnLogExample pe = new PipUqsAnswerqnLogExample();
        pe.createCriteria().andPatientIdEqualTo(pid);
        List<PipUqsAnswerqnLog> list =   qnlogDao.selectByExample(pe);

        PipUqsAnswerExample ae = new PipUqsAnswerExample();
            ae.createCriteria().andPatientIdEqualTo(pid)
                               .andQuestionsetIdEqualTo("4")
                               .andQuestionIdEqualTo("3")
                               .andOptionIdEqualTo("1")
                               .andQuestionnaireIdEqualTo(uqsver);
        List<PipUqsAnswer> l = ansDao.selectByExample(ae);
        Map<String,Object> paramMap = new HashMap<String, Object>();
        if( l != null && l.size() > 0 ){
            if( l.get(0).getAnswer().equals(bloodNo) ){
                paramMap.put("PACKAGE_STATE" , "2");
                return paramMap;
            }
        }
        paramMap.put("projectId", Securitys.getCurrentProject());
        paramMap.put("bloodNo", bloodNo);
        if( list != null && list.size() > 0 ){
            paramMap.put("lccCode",list.get(0).getLccCode());
            return answerQnDao.checkBloodNo(paramMap);
        }else{
            return null;
        }
    }


	public String getStartHosDate(String patientId, String uqsCode, String state) {
		if(!"1".equals(state))state="2";
		Map<String,Object> pMap = new HashMap<String, Object>();
		pMap.put("patientId", patientId);
		pMap.put("uqsCode", uqsCode);
		pMap.put("state", state);
		Date date = qnlogDao.getStartHosDate(pMap);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(date == null)return sdf.format(new Date());
		return sdf.format(date);
	}
}