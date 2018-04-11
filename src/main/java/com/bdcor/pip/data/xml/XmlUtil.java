package com.bdcor.pip.data.xml;

import java.util.ArrayList;
import java.util.List;

import com.bdcor.pip.core.mapper.JaxbMapper;
import com.bdcor.pip.data.xml.vo.*;

public class XmlUtil {

	public static void main(String[] args) {
		
		
		/*Questionnaire qr = new Questionnaire();
		qr.setTitle("你好");
		
		Doctype t = new Doctype();
		t.setProjectid("234");
		t.setProjectname("adfadsfads");
		t.setUqsId("2");
		t.setUqsVersion("234324");
		t.setVersionCreateDate("2014-08-14 09:18:01");
		
		qr.setDoctype(t);
		
		List<QuestionSet> qsList = new ArrayList<QuestionSet>();
		QuestionSet qs = new QuestionSet();
		qs.setDisplayname("qs");
		qs.setId("1");
		qs.setOrder(1L);
		
		List<Question> ql = new ArrayList<Question>();
		Question q = new Question();
		q.setCode("2");
		q.setDisplayname("qq");
		q.setId("1");
		q.setOrder(1L);
		q.setType("Varchar");
		
		List<Option> ol = new ArrayList<Option>();
		Option o = new Option();
		o.setCode("oo");
		o.setDatastyle("adfadsf");
		o.setDatatype("adfa");
		o.setId("1");
		o.setOrder(1L);
		ol.add(o);
		q.setOptionList(ol);
		
		ql.add(q);
		qs.setQuestionList(ql);
		
		qsList.add(qs);
		qr.setQuestionSetList(qsList);
		
		
		String xml = JaxbMapper.toXml(qr);
		System.out.println(xml);
		
		Questionnaire qqq = JaxbMapper.fromXml(xml, Questionnaire.class);
		System.out.println(qqq.getDoctype().getProjectname());*/
		
		
		QuestionaireRule rule = new QuestionaireRule();
		
		Doctype t1 = new Doctype();
		t1.setProjectid("234");
		t1.setProjectname("adfadsfads");
		t1.setUqsId("2");
		t1.setUqsVersion("234324");
		t1.setVersionCreateDate("2014-08-14 09:18:01");
		
		rule.setDoctype(t1);
		rule.setTitle("rule");
					
		List<QuestionsetRule> qlist = new ArrayList<QuestionsetRule>();
		
		QuestionsetRule qsr = new QuestionsetRule();
		qsr.setId("1");
		
		List<Rule> rlist = new ArrayList<Rule>();
		Rule r = new Rule();
		
		r.setExpression("ST1");
		r.setRuleid("1");
		
		List<Operation> olist = new ArrayList<Operation>();
		
		Operation oo = new Operation();
		
		oo.setOptionid("1");
		oo.setQuestionid("2");
		oo.setQuestionsetid("3");
		oo.setState("4");
		
		olist.add(oo);
		
		r.setOperationList(olist);
		rlist.add(r);
		
		qsr.setRuleList(rlist);
		
		qlist.add(qsr);
		rule.setQuestionsetList(qlist);
		
		String xml = JaxbMapper.toXml(rule);
		System.out.println(xml);
		
		
		QuestionaireRule rrr = JaxbMapper.fromXml(xml, QuestionaireRule.class);
		System.out.println(rrr.getDoctype().getProjectname());
	}

}
