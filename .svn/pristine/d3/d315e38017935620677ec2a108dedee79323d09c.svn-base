package com.bdcor.pip.client.vo.paper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.bdcor.pip.client.tools.PagerData;
import com.bdcor.pip.client.xml.model.TempletQuestionDocumentBean.Question;
import com.bdcor.pip.client.xml.model.TempletQuestionSetDocumentBean.QuestionSet;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean.Questionnaire;


public class PaperService {

	public static List<Paper> papers = new ArrayList<Paper>();
	
	private static PaperService instance = null;
	public static PaperService getInstance(){
		if(instance==null){
			instance = new PaperService();
		}
		return instance;
	}
	
	
	/**
	 * 加载试卷
	 */
	public static void initPaper(String ctxPath){
		PaperService.papers.clear();
		
		//==========================================================   xml文件到java对象
		PagerData datas = new PaperDataImpl(ctxPath);
		List qaires = datas.getPagerObjs();
		for(Object obj:qaires){
			Questionnaire qaire = (Questionnaire)obj;
			Paper paper = new Paper(qaire);
			//循环 试题组
			for(QuestionSet qset:qaire.getQuestionSetArray()){
				QuestionGroup qg = new QuestionGroup(qset);
				//循环试题
				for(Question q:qset.getQuestionArray()){
					qg.getQs().add(new QuestionC(q));
				}
				paper.getQGroup().add(qg);
				Collections.sort(qg.getQs(),new Comparator<QuestionC>() {
					@Override
					public int compare(QuestionC o1, QuestionC o2) {
						if(o1.getOrder()<=o2.getOrder())return -1;
						return 1;
					}
				});
			}
			Collections.sort(paper.getQGroup(),new Comparator<QuestionGroup>() {
				@Override
				public int compare(QuestionGroup o1, QuestionGroup o2) {
					if(o1.getOrder()<=o2.getOrder())return -1;
					return 1;
				}
			});
			PaperService.papers.add(paper);
			
		//	Rule r = matchRuleWithPaper(paper);
		//	if(r == null)continue;
		//	paper.setRule(r);
			//==========================================================
		}
	}
	
	/**
	 * 查找某患者试卷列表信息
	 */
	/**
	public List<Map<String,Object>> getIntervPaperMap(String intervId,List<Paper> paperList){
		if(intervId == null || paperList == null || paperList.size() == 0){
			return null;
		}
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		for(Paper p : paperList){
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("id", p.getPaperId());
			map.put("name", p.getPaperName());
			
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT  FILEPATH as \"filePath\", ISUPLOAD  as \"isUpload\"  FROM LOG_FILE_CREATE where intervid = '"+intervId.trim()+"' and PAGEID ='"+p.getPaperId()+"' order by CREATETIME  desc");
			
			Object o = DbService.getOperate().getOne(sql.toString(),new DbOperate.dbOperatorCallBack() {
				@Override
				public Object toObj(ResultSet rs) {
					Map<String,Object> map = new HashMap<String,Object>();
					try {
						map.put("filePath", rs.getString("filePath"));
						map.put("isUpload", rs.getInt("isUpload"));
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return map;
				}
			});
			
			if(o == null){
				map.put("completed", false);
			}else{
				map.put("completed", true);
				map.put("filePath", ((Map)o).get("filePath"));
				if((Boolean)((Map)o).get("isUpload") == true){
					map.put("hasUpload", true);
				}else{
					map.put("hasUpload", false);
				}
			}
			
			resultList.add(map);
		}
		
		return resultList;
	}
	**/
	public Paper getPage(String paperId){
		for(Paper paper:papers){
			String cpaperid = paper.getPaperId();
			if(cpaperid.equals(paperId)){
				return paper;
			}
		}
		return null;
	}
	/**
	public Map<String,Integer> getPersonCountMap(){
		String selectSql = "select count(*) as c,PAPERID,SAVETYPE from PIP_EXAM_TEMPSAVE where PROJECT_ID='"+ClientInfo.PROJ_NO+"' and "+
				"LCC_CODE = '"+	ClientInfo.LCC_NO+"' group by PAPERID,SAVETYPE";
		List<Object> oList = DbService.getOperate().getList(selectSql, new DbOperate.dbOperatorCallBack() {
			@Override
			public Object toObj(ResultSet rs) {
				Map<String,Object> map = new HashMap<String,Object>();
				try{
					map.put("paperId", rs.getString("PAPERID"));
					map.put("type", rs.getInt("SAVETYPE"));
					map.put("count",rs.getInt("c"));
				}catch (Exception e) {
					
				}
				return map;
			}
		});
		Map<String,Integer> returnMap = new HashMap<String,Integer>();
		if(oList == null || oList.size() == 0){
			return returnMap;
		}
		
		for(Object o : oList){
			Map<String,Object> map = (Map<String,Object>)o;
			if(map.get("type").toString().equals("1")){
				returnMap.put(map.get("paperId")+"saved", (Integer)map.get("count"));
			}else{
				returnMap.put(map.get("paperId")+"tempSaved", (Integer)map.get("count"));
			}
			
			
		}
		selectSql = "select count(*) as c,PAPERID from PIP_EXAM_TEMPSAVE where PROJECT_ID='"+ClientInfo.PROJ_NO+"' and "+
				"LCC_CODE = '"+	ClientInfo.LCC_NO+"' and SAVETYPE = '1' and CREATE_DATE like '"+
				DateUtils.currentDate() +"%' group by PAPERID";
		List<Object> todayList = DbService.getOperate().getList(selectSql, new DbOperate.dbOperatorCallBack() {
			@Override
			public Object toObj(ResultSet rs) {
				Map<String,Object> map = new HashMap<String,Object>();
				try{
					map.put("paperId", rs.getString("PAPERID"));
					map.put("count",rs.getInt("c"));
				}catch (Exception e) {
				}
				return map;
			}
		});
		for(Object o : todayList){
			Map<String,Object> map = (Map<String,Object>)o;
			returnMap.put(map.get("paperId")+"todaySaved", (Integer)map.get("count"));
		}
		
		return returnMap;
	}
	
	public Page getTempSavedInte(int currentPage,String paperId){
		String countSql = "select count(*) as \"c\" from PIP_EXAM_TEMPSAVE where PROJECT_ID='"+ClientInfo.PROJ_NO+"' and "+
				"LCC_CODE = '"+	ClientInfo.LCC_NO+"' and SAVETYPE=2 and PAPERID='"+paperId+"'";
		Object o = DbService.getOperate().getOne(countSql.toString(), new DbOperate.dbOperatorCallBack() {
			@Override
			public Object toObj(ResultSet rs) {
				try {
					return rs.getInt("c");
				} catch (SQLException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		
		Page page = new Page();
		if(o == null){
			page.setTotalRowCount(0);
		}else{
			page.setTotalRowCount((Integer)o);
		}
		page.setCurrentPage(currentPage);
		
		if(page.getTotalRowCount() > 0){
			String selectSql = "select * from (select *,rownum as \"r\" from(select * from PIP_EXAM_TEMPSAVE where PROJECT_ID='"+ClientInfo.PROJ_NO+"' and "+
				"LCC_CODE = '"+	ClientInfo.LCC_NO+"' and SAVETYPE=2 and PAPERID='"+paperId+"' order by CREATE_DATE desc)m)n where " +
					"n.\"r\" >="+page.getStartRow()+" and n.\"r\" <="+page.getEndRow();
			List<Object> list = DbService.getOperate().getList(selectSql,new DbOperate.dbOperatorCallBack() {
				@Override
				public Object toObj(ResultSet rs) {
					Map<String,Object> map = new HashMap<String,Object>();
					try {
						map.put("INTERVCODE",rs.getString("INTERVCODE"));
						map.put("RESULTPATH",rs.getString("RESULTPATH"));
						map.put("CREATE_DATE",rs.getString("CREATE_DATE"));
					} catch (Exception e) {
					}
					return map;
				}
			});
			page.setResultList(list);
		}
		 
		return page;
	}
	
	**/
}
