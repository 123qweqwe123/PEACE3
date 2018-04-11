package com.bdcor.pip.web.sys.tablecreate.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bdcor.pip.web.sys.tablecreate.dao.PaperTableCreateDao;
import com.bdcor.pip.web.sys.tablecreate.domain.Answer;
import com.bdcor.pip.web.sys.tablecreate.domain.Option;
import com.bdcor.pip.web.sys.tablecreate.domain.Question;
import com.bdcor.pip.web.sys.tablecreate.domain.QuestionNaire;
import com.bdcor.pip.web.sys.tablecreate.domain.QuestionSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReadDataRunnable implements Runnable {

	private static Logger log = LoggerFactory.getLogger(ReadDataRunnable.class);

	private PaperTableCreateDao paperTableCreateDao;
	
	private String PATIENT_ID;
	
	private QuestionNaire qn;
	
	public ReadDataRunnable(PaperTableCreateDao paperTableCreateDao,String PATIENT_ID,QuestionNaire qn){
		this.paperTableCreateDao = paperTableCreateDao;
		this.PATIENT_ID = PATIENT_ID;
		this.qn = qn;
	}

	@Override
	public void run() {
		List<Answer> aList = paperTableCreateDao.getAnswerList(qn.getId(),PATIENT_ID);
		if(aList==null || aList.size()==0)return;
		StringBuilder inSql = new StringBuilder("INSERT into G"+qn.getId()+"_DATA@TO_122DW(PROJECT_ID,VERSION,PATIENT_ID");
		StringBuilder cycleSql = new StringBuilder("INSERT into G"+qn.getId()+"_CYCLE(PROJECT_ID,VERSION,PATIENT_ID");
		
		StringBuilder vSql = new StringBuilder("VALUES('"+qn.getProjectId()+"','"+aList.get(0).getVersion()+"','"+PATIENT_ID+"'");
		StringBuilder cvSql = new StringBuilder("VALUES('"+qn.getProjectId()+"','"+aList.get(0).getVersion()+"','"+PATIENT_ID+"'");
		
		Map<String,String> answerMap = getAnswerMap(aList);
		boolean hasCycle=false;
		for(QuestionSet s : qn.getSets()){
			for(Question q : s.getQuestions()){

				if( (  qn.getId().contains("004011") && s.getId().equals("3") && !(q.getId().equals("1")|| q.getId().equals("2")) )
						|| ( qn.getId().contains("004011") && s.getId().equals("4"))
						){
					continue;
				}

				int cycleNum=0;
//				if(s.getCycle()==1 && !"1".equals(q.getId())){
//					cycleNum=20;
//					hasCycle=true;
//				}
				for(int i=0;i<=cycleNum;i++){
					//单选
					if(q.getType().equals("SINGLE")){
						String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i);
						if(i==0){
							inSql.append(","+colN);
							vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":getV(answerMap,colN,null)));
						}else{
							cycleSql.append(","+colN);
							cvSql.append(","+(getV(answerMap,colN,null)==null?"NULL":getV(answerMap,colN,null)));
						}
					}
					
					//单选填空
					else if(q.getType().equals("SINGLEFILL")){
						String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i);
						if(i==0){
							inSql.append(","+colN);
							vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":getV(answerMap,colN,null)));
						}else{
							cycleSql.append(","+colN);
							cvSql.append(","+(getV(answerMap,colN,null)==null?"NULL":getV(answerMap,colN,null)));
						}
						
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							if(o.getStyle().equals("FILLBLANK") || o.getStyle().equals("TEXTAREA")){
								String colN_1 = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
								if(i==0){
									inSql.append(","+colN_1+"_TEXT");
									vSql.append(","+(getV(answerMap,colN_1,o.getRealType())==null?"NULL":getV(answerMap,colN_1,o.getRealType())));
								}else{
									cycleSql.append(","+colN_1+"_TEXT");
									cvSql.append(","+(getV(answerMap,colN_1,o.getRealType())==null?"NULL":getV(answerMap,colN_1,o.getRealType())));
								}
								
							}
						}
					}
					
					//多选
					else if(q.getType().equals("MULTI")){
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
							if(i==0){
								inSql.append(","+colN);
								vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
							}else{
								cycleSql.append(","+colN);
								cvSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
							}
						}
					}
					
					//多选填空
					else if(q.getType().equals("MULTIFILL")){
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							if(o.getStyle().equals("MULTI")){
								String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
								if(i==0){
									inSql.append(","+colN);
									vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
								}else{
									cycleSql.append(","+colN);
									cvSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
								}
							}else if(o.getStyle().equals("FILLBLANK")  || o.getStyle().equals("TEXTAREA")){
								String colN_1 = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
								if(i==0){
									inSql.append(","+colN_1+"_TEXT");
									vSql.append(","+(getV(answerMap,colN_1,o.getRealType())==null?"NULL":getV(answerMap,colN_1,o.getRealType())));
								}else{
									cycleSql.append(","+colN_1+"_TEXT");
									cvSql.append(","+(getV(answerMap,colN_1,o.getRealType())==null?"NULL":getV(answerMap,colN_1,o.getRealType())));
								}
							}
						}
					}
					
					//填空
					else if(q.getType().equals("FILLBLANK")){
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId();
							if(i==0){
								inSql.append(","+colN);
								vSql.append(","+(getV(answerMap,colN,o.getRealType())==null?"NULL":getV(answerMap,colN,o.getRealType())));
							}else{
								cycleSql.append(","+colN);
								cvSql.append(","+(getV(answerMap,colN,o.getRealType())==null?"NULL":getV(answerMap,colN,o.getRealType())));
							}
						}
					}
					
					//表格
					else if(q.getType().equals("TABLE")){
						if(q.getOptions()==null || q.getOptions().size()==0)continue;
						for(Option o : q.getOptions()){
							String colN = "G"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId().replace("-", "_T_");
							if(i==0){
								inSql.append(","+colN);
							}else{
								cycleSql.append(","+colN);
							}
							
							if("TSINGLE".equals(o.gettRemark().toUpperCase())){
								if(i==0){
									vSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
								}else{
									cvSql.append(","+(getV(answerMap,colN,null)==null?"NULL":"'CHECKED'"));
								}
							}else{
								if(i==0){
									vSql.append(","+(getV(answerMap,colN,o.getRealType())==null?"NULL":getV(answerMap,colN,o.getRealType())));
								}else{
									cycleSql.append(","+(getV(answerMap,colN,o.getRealType())==null?"NULL":getV(answerMap,colN,o.getRealType())));
								}
							}
						}
					}
				}
			}
		}
		
		try {
			paperTableCreateDao.execInsertSql(inSql.toString()+")"+vSql+")");
			log.info("打印插入sql："+inSql.toString()+")"+vSql+")");
//			if(hasCycle){
//				paperTableCreateDao.execInsertSql(cycleSql.toString()+")"+cvSql+")");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getRQId(String qId, int i) {
		if(i==0)return qId;
		if(i<10)return qId+"00"+i;
		if(i<100)return qId+"0"+i;
		return qId+""+i;
	}
		
	private Map<String, String> getAnswerMap(List<Answer> aList) {
		Map<String, String> map = new HashMap<String, String>();
		if(aList==null || aList.size()==0)return map;
		
		for(Answer a : aList){
			map.put(a.getId(),a.getOptionId());
			if(a.getText()==null||a.getText().trim().length()==0){
				map.put(a.getId()+"_"+a.getOptionId().replace("-", "_T_"),"CHECKED");
			}else{
				map.put(a.getId()+"_"+a.getOptionId().replace("-", "_T_"),a.getText().trim());
			}
		}
		return map;
	}
	
	private String getV(Map<String, String> map,String key,String realType){
		if(map.get(key)==null){
			return null;
		}
		if("DATE".equals(realType)){
			String d = map.get(key).trim();
			try{
				new SimpleDateFormat("yyyy-MM-dd").parse(d);
			}catch (Exception e) {
				d = "1900-01-01";
			}
			return "TO_DATE('"+d+"','yyyy-MM-dd')";
		}else if("DATETIME".equals(realType)){
			String d = map.get(key).trim();
			try{
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(d);
			}catch (Exception e) {
				d = "1900-01-01 00:00:00";
			}
			return "TO_DATE('"+d+"','yyyy-MM-dd hh24:mi:ss')";
		}else{
			return "'"+map.get(key).trim()+"'";
		}
	}


}
