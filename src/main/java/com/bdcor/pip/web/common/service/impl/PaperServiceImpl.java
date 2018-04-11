package com.bdcor.pip.web.common.service.impl;

import com.bdcor.pip.client.tools.FileUtils;
import com.bdcor.pip.client.tools.StringUtils;
import com.bdcor.pip.core.mapper.JaxbMapper;
import com.bdcor.pip.data.xml.vo.*;
import com.bdcor.pip.web.common.service.PaperService;
import com.bdcor.pip.web.data.dao.*;
import com.bdcor.pip.web.data.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Description:
 * Author: huangrupeng
 * Create: 17/3/6 下午2:45
 */

@Service
@Transactional
public class PaperServiceImpl implements PaperService {

    public void transXmlTodb() throws IOException {

        String path = this.getClass().getResource("/").getPath()+"paper/";
        String[] uqsArr = {"UQS_004_014_001.xml","UQS_004_015_001.xml"};
        for( String uqsVersion : uqsArr ){
            String xmlStr = FileUtils.readFileToString(new File(path+uqsVersion), "UTF-8");
            xmlStr = xmlStr.replace("xmlns=\"http://www.gener-health.com/uqsPaper\"" ,"");
            if (xmlStr.indexOf("<") != 0)
                xmlStr = xmlStr.substring(xmlStr.indexOf("<"));
            Questionnaire qn = JaxbMapper.fromXml(xmlStr, Questionnaire.class);

            System.out.println(qn.getDoctype().getProjectname());
            save(qn);
        }

    }



    @Override
    public void doPapers(HttpServletRequest request) throws IOException {
        String qId = "014";
        String path = request.getSession().getServletContext().getRealPath("/") + "WEB-INF/classes/paper/UQS_004_" + qId + "_001.xml";
        String xmlStr = FileUtils.readFileToString(new File(path), "utf-8");
        if (xmlStr.indexOf("<") != 0)
            xmlStr = xmlStr.substring(xmlStr.indexOf("<"));
        Questionnaire qn = JaxbMapper.fromXml(xmlStr, Questionnaire.class);
        save(qn);

    }

    @Autowired
    PipUqsQuestionnaireMapper pipUqsQuestionnaire;
    @Autowired
    PipUqsQuestionsetMapper pipUqsQuestionsetMapper;
    @Autowired
    PipUqsQuestionMapper pipUqsQuestionMapper;
    @Autowired
    PipUqsAttributeMapper pipUqsAttributeMapper;
    @Autowired
    PipUqsOptionMapper pipUqsOptionMapper;


    public void save(Questionnaire qn) {

        PipUqsQuestionnaire uqn = new PipUqsQuestionnaire();

        uqn.setCreateDate(new Date());
        uqn.setQuestionnaireId(qn.getDoctype().getUqsId());
        uqn.setDisplayName(qn.getTitle());
        uqn.setIsRemoved(new Short("1"));
        uqn.setOrderInProject(1L);
        uqn.setProjectId(
                StringUtils.isBlank(qn.getDoctype().getProjectid())?
                "004":qn.getDoctype().getProjectid()
        );
        uqn.setQuestionnaireId(qn.getDoctype().getUqsId());
        uqn.setVersion(qn.getDoctype().getUqsVersion());

        this.pipUqsQuestionnaire.insert(uqn);

        if (qn.getQuestionSetList() != null) {
            for (QuestionSet qs : qn.getQuestionSetList()) {
                PipUqsQuestionset uqs = new PipUqsQuestionset();
                uqs.setDisplayName(qs.getDisplayname());
                uqs.setOrderInQuestionnaire(qs.getOrder());
                uqs.setProjectId(qn.getDoctype().getProjectid());
                uqs.setQuestionnaireId(qn.getDoctype().getUqsId());
                uqs.setQuestionsetId(qs.getId());
                uqs.setIsRemoved(new Short("0"));
                uqs.setVersion(uqn.getVersion());
                this.pipUqsQuestionsetMapper.insert(uqs);

                if (qs.getQuestionList() != null) {
                    for (Question q : qs.getQuestionList()) {
                        PipUqsQuestion usq = new PipUqsQuestion();

                        usq.setDisplayName(q.getDisplayname());
                        //usq.setHelpText(helpText);
                        usq.setOrderInQuestionset(q.getOrder());
                        usq.setProjectId(qn.getDoctype().getProjectid());
                        usq.setQuestionId(q.getId());
                        usq.setQuestionnaireId(qn.getDoctype().getUqsId());
                        usq.setQuestionsetId(qs.getId());
                        usq.setQuestiontypeId(q.getTypeId());
                        usq.setQuestionCode(q.getCode());
                        usq.setQuestionType(q.getType());
                        usq.setIsRemoved(new Short("0"));
                        uqs.setVersion(uqn.getVersion());
                        this.pipUqsQuestionMapper.insert(usq);

                        System.out.println("===============" + usq.getProjectId() + "\t" + usq.getQuestionnaireId() + "\t" + usq.getQuestionsetId() + "\t" + usq.getQuestionId());

                        if (q.getAttributeList() != null) {
                            for (Attribute a : q.getAttributeList()) {
                                PipUqsAttribute qa = new PipUqsAttribute();
                                qa.setAttributeId(a.getId());
                                qa.setOptionId("0");
                                qa.setProjectId(qn.getDoctype().getProjectid());
                                qa.setQuestionId(q.getId());
                                qa.setQuestionnaireId(qn.getDoctype().getUqsId());
                                qa.setQuestionsetId(qs.getId());
                                qa.setType(a.getType());
                                qa.setValidateobjecttype(a.getValidateobjecttype());
                                qa.setValue(a.getValue());
                                qa.setValidatetype(a.getValidatetype());
                                qa.setVersion(uqn.getVersion());
                                this.pipUqsAttributeMapper.insert(qa);
                            }
                        }

                        if (q.getTableList() != null) {
                            for (Table t : q.getTableList()) {
                                if (t.getTrList() != null) {
                                    for (Tr tr : t.getTrList()) {
                                        if (tr.getThList() != null) {
                                            for (Th h : tr.getThList()) {
                                                PipUqsOption uo = new PipUqsOption();

                                                uo.setDatatypeId(q.getTypeId());
                                                if (h.getDisplayName() == null)
                                                    uo.setDisplayName(tr.getDisplayname());
                                                else
                                                    uo.setDisplayName(tr.getDisplayname() + ";" + h.getDisplayName());
                                                uo.setOptionId(tr.getRownum() + "-" + h.getId());

                                                uo.setProjectId(qn.getDoctype().getProjectid());
                                                uo.setQuestionId(q.getId());
                                                uo.setQuestionnaireId(qn.getDoctype().getUqsId());
                                                uo.setQuestionsetId(qs.getId());
                                                uo.setIsChooseable(new Short("1"));
                                                uo.setIsRemoved(new Short("0"));
                                                uo.setIsEvent(new Short("0"));
                                                uo.setRemark(h.getColstyle());
                                                uo.setDictValue(h.getCode());
                                                uo.setVersion(uqn.getVersion());

                                                uo.setOptionType(h.getDatatype());
                                                uo.setOptionStyle(h.getDatastyle());

                                                this.pipUqsOptionMapper.insert(uo);

                                                if (h.getAttributeList() != null) {
                                                    for (Attribute a : h.getAttributeList()) {
                                                        PipUqsAttribute ua = new PipUqsAttribute();

                                                        ua.setAttributeId(a.getId());
                                                        ua.setOptionId(uo.getOptionId());
                                                        ua.setProjectId(qn.getDoctype().getProjectid());
                                                        ua.setQuestionId(q.getId());
                                                        ua.setQuestionnaireId(qn.getDoctype().getUqsId());
                                                        ua.setQuestionsetId(qs.getId());
                                                        ua.setValidateobjecttype(a.getValidateobjecttype());
                                                        ua.setValue(a.getValue());
                                                        ua.setValidatetype(a.getValidatetype());
                                                        ua.setType(a.getType());
                                                        ua.setVersion(uqn.getVersion());
                                                        this.pipUqsAttributeMapper.insert(ua);
                                                    }

                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        if (q.getOptionList() != null) {
                            for (Option o : q.getOptionList()) {
                                PipUqsOption uo = new PipUqsOption();

                                uo.setDatatypeId(q.getTypeId());
                                uo.setDisplayName(o.getDisplayName());
                                uo.setOptionId(o.getId());

                                uo.setProjectId(qn.getDoctype().getProjectid());
                                uo.setQuestionId(q.getId());
                                uo.setQuestionnaireId(qn.getDoctype().getUqsId());
                                uo.setQuestionsetId(qs.getId());
                                uo.setIsChooseable(new Short("1"));
                                uo.setIsRemoved(new Short("0"));
                                uo.setIsEvent(new Short("0"));
                                uo.setDictValue(o.getCode());
                                uo.setVersion(uqn.getVersion());
                                uo.setOptionType(o.getDatatype());
                                uo.setOptionStyle(o.getDatastyle());
                                this.pipUqsOptionMapper.insert(uo);

                                if (o.getAttributeList() != null) {
                                    for (Attribute a : o.getAttributeList()) {
                                        PipUqsAttribute ua = new PipUqsAttribute();

                                        ua.setAttributeId(a.getId());
                                        ua.setOptionId(o.getId());
                                        ua.setProjectId(qn.getDoctype().getProjectid());
                                        ua.setQuestionId(q.getId());
                                        ua.setQuestionnaireId(qn.getDoctype().getUqsId());
                                        ua.setQuestionsetId(qs.getId());
                                        ua.setValidateobjecttype(a.getValidateobjecttype());
                                        ua.setValue(a.getValue());
                                        ua.setValidatetype(a.getValidatetype());
                                        ua.setType(a.getType());
                                        ua.setVersion(uqn.getVersion());
                                        this.pipUqsAttributeMapper.insert(ua);
                                    }

                                }
                            }
                        }

                    }

                }
            }

        }
    }


}
