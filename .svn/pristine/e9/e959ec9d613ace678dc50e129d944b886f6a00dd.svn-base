package com.bdcor.pip.web.sys.tablecreate.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.web.sys.tablecreate.dao.PaperTableCreateDao;
import com.bdcor.pip.web.sys.tablecreate.domain.Option;
import com.bdcor.pip.web.sys.tablecreate.domain.Question;
import com.bdcor.pip.web.sys.tablecreate.domain.QuestionNaire;
import com.bdcor.pip.web.sys.tablecreate.domain.QuestionSet;
import com.bdcor.pip.web.sys.tablecreate.service.PaperTableCreateService;

@Service
@Transactional
public class PaperTableCreateServiceImpl implements PaperTableCreateService {

	Logger log = LoggerFactory.getLogger(PaperTableCreateServiceImpl.class);

	@Autowired
	private PaperTableCreateDao paperTableCreateDao;
	
	public static ArrayBlockingQueue<String> sqlQueue = new ArrayBlockingQueue<String>(10000);
	
	public static boolean isRunning = false;
	
	
	@Override
	public void createTable() {
		List<QuestionNaire> qnList = paperTableCreateDao.getQnList();
		
		for(QuestionNaire qn : qnList){
            if( !( qn.getId().contains("004011") )  ){
                continue;
            }
			String tableName = "G"+qn.getId();
			StringBuilder cSql = new StringBuilder("CREATE TABLE "+tableName+"(\nPROJECT_ID VARCHAR2(32),\nPATIENT_ID VARCHAR2(32),VERSION  VARCHAR2(32)");
			StringBuilder cycleSql = new StringBuilder("CREATE TABLE "+tableName+"_CYCLE(\nPROJECT_ID VARCHAR2(32),\nPATIENT_ID VARCHAR2(32),VERSION  VARCHAR2(32)");
			boolean hasCycle=false;
			for(QuestionSet s : qn.getSets()){
				for(Question q : s.getQuestions()){
					int cycleNum=0;
					if(s.getCycle()==1 && !"1".equals(q.getId())){
						cycleNum=20;
						hasCycle = true;
					}
					for(int i=0;i<=cycleNum;i++){
						//单选
						if(q.getType().equals("SINGLE")){
							if(i==0){
								cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+" VARCHAR2(8)");
							}else{
								cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+" VARCHAR2(8)");
							}
						}
						
						//单选填空
						else if(q.getType().equals("SINGLEFILL")){
							cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+" VARCHAR2(8)");
							
							if(q.getOptions()==null || q.getOptions().size()==0)continue;
							for(Option o : q.getOptions()){
								if(o.getStyle().equals("FILLBLANK") || o.getStyle().equals("TEXTAREA")){
									if(i==0){
										cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+"_TEXT "+o.getType());
									}else{
										cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+"_TEXT "+o.getType());
									}
								}
							}
						}
						
						//多选
						else if(q.getType().equals("MULTI")){
							if(q.getOptions()==null || q.getOptions().size()==0)continue;
							for(Option o : q.getOptions()){
								if(i==0){
									cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" VARCHAR2(8)");
								}else{
									cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" VARCHAR2(8)");
								}
							}
						}
						
						//多选填空
						else if(q.getType().equals("MULTIFILL")){
							if(q.getOptions()==null || q.getOptions().size()==0)continue;
							for(Option o : q.getOptions()){
								if(o.getStyle().equals("MULTI")){
									if(i==0){
										cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" VARCHAR2(8)");
									}else{
										cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" VARCHAR2(8)");
									}
								}else if(o.getStyle().equals("FILLBLANK")  || o.getStyle().equals("TEXTAREA")){
									if(i==0){
										cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+"_TEXT "+o.getType());
									}else{
										cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+"_TEXT "+o.getType());
									}
									
								}
							}
						}
						
						//填空
						else if(q.getType().equals("FILLBLANK")){
							if(q.getOptions()==null || q.getOptions().size()==0)continue;
							for(Option o : q.getOptions()){
								if(i==0){
									cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" "+o.getType());
								}else{
									cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId()+" "+o.getType());
								}
							}
						}
						
						//表格
						else if(q.getType().equals("TABLE")){
							if(q.getOptions()==null || q.getOptions().size()==0)continue;
							for(Option o : q.getOptions()){
								if(i==0){
									cSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId().replace("-", "_T_")+" "+o.getType());
								}else{
									cycleSql.append(",\nG"+qn.getId()+"_"+s.getId()+"_"+getRQId(q.getId(),i)+"_"+o.getId().replace("-", "_T_")+" "+o.getType());
								}
							}
						}
					}
				}
			}
			cSql.append(")");
			paperTableCreateDao.execUpdateSql(cSql.toString());
			if(hasCycle){
				cycleSql.append(")");
				paperTableCreateDao.execUpdateSql(cycleSql.toString());
			}
		}
		
	}
	
	private String getRQId(String qId, int i) {
		if(i==0)return qId;
		if(i<10)return qId+"00"+i;
		if(i<100)return qId+"0"+i;
		return qId+""+i;
	}

	@Override
	public void insertData() {
		if(isRunning){
			return;
		}
		isRunning = true;
		List<QuestionNaire> qnList = paperTableCreateDao.getQnList();
		Map<String,QuestionNaire> qnM= new HashMap<String,QuestionNaire>();
		for(QuestionNaire qn : qnList){
			qnM.put(qn.getId(), qn);
		}
		
		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(25,25,0L, TimeUnit.MILLISECONDS,new LinkedBlockingQueue<Runnable>());
		String[] qnIdArr = {"004010","004011"}; // "004010",""004011""
		SimpleDateFormat sf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		for(QuestionNaire qn : qnList){
//			String qnId = qn.getId();
		for(String qnId : qnIdArr){
			int i=0;
			while(true){
				System.out.println(sf.format(new Date())+"  队列线程数"+poolExecutor.getQueue().size());
				if(poolExecutor.getQueue().size()>10000){
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					continue;
				}
				i++;
				System.out.println(sf.format(new Date())+"  执行查询开始");
				List<String> pIdList = paperTableCreateDao.getPIdList(qnId,(i-1)*50000+1,i*50000,"G"+qnId);
				System.out.println(sf.format(new Date())+"  执行查询结束");
				if(pIdList==null || pIdList.size()==0){
					break;
				}
				for(String pId : pIdList){
					poolExecutor.execute(new ReadDataRunnable(paperTableCreateDao,pId,qnM.get(qnId)));
				}
			}
		}
		while(poolExecutor.getActiveCount()>0){
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isRunning = false;
	}


}
