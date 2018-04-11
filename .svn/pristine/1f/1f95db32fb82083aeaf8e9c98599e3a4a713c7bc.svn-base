package com.bdcor.pip.client.vo.paper;

import java.util.ArrayList;
import java.util.List;

import com.bdcor.pip.client.xml.model.TempletDoctypeDocumentBean.Doctype;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean.Questionnaire;

/**
 * 试卷
 * @author Administrator
 *
 */
public class Paper {

	private String projectId;
	private String projectName;
	  
	private String paperId;
	private String paperName;
	private String paperVersion;
	
	private String createDate;
	
	private List<QuestionGroup> QGroup = new ArrayList<QuestionGroup>();
	
	public Paper(){}
	
	public Paper(Questionnaire qaire){
		Doctype docType = qaire.getDoctype();
		this.projectId = docType.getProjectid();
		this.projectName = docType.getProjectname();
		this.paperId = docType.getUQSid();
		this.paperName = docType.getUQSName();
		this.paperVersion = docType.getUQSVersion();
		this.createDate = docType.getVersionCreateDate();
	}
	
	public String getPaperId() {
		return paperId;
	}

	public void setPaperId(String paperId) {
		this.paperId = paperId;
	}

	public List<QuestionGroup> getQGroup() {
		return QGroup;
	}

	public void setQGroup(List<QuestionGroup> qGroup) {
		QGroup = qGroup;
	}
	
	
	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getPaperName() {
		return paperName;
	}

	public void setPaperName(String paperName) {
		this.paperName = paperName;
	}

	public String getPaperVersion() {
		return paperVersion;
	}

	public void setPaperVersion(String paperVersion) {
		this.paperVersion = paperVersion;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * 
	 * @author Administrator
	 *
	 */
	public static class OrderProperty{
		

		/** 无效时，值 **/
		public static String VOID_VALUE = "0";
		
		public enum ShowOrHidder{
			SH_HIDDEN,SH_SHOW
		};
		/** 隐藏 **/
		public static String SH_HIDDEN = "1";
		/** 显示 **/
		public static String SH_SHOW = "0";
		
		public enum NullYesNo{
			NULL_NO,NULL_YES
		};
		/** 不允许为空，必填 **/
		public static String NULL_NO = "1";
		/** 允许为空 **/
		public static String NULL_YES = "0";
		
		public enum QuesionType{
			Q_TYPE_SINGLE,Q_TYPE_SINGLE_FILL,Q_TYPE_MULTI,Q_TYPE_MULTI_FILL,
			Q_TYPE_FILL_BLANK,Q_TYPE_TABLE,Q_TYPE_TABLE_SINGLE,Q_TYPE_TABLE_MULTI,
			Q_TYPE_TABLE_BLANK,Q_TYPE_TABLE_SELECT,Q_TYPE_SHOWTEXT,Q_TYPE_TEXTAREA
		};
		
		/** 单选 **/
		public static String Q_TYPE_SINGLE = "Single".toUpperCase();
		/** 单选填空 **/
		public static String Q_TYPE_SINGLE_FILL = "SingleFill".toUpperCase();
		/** 多选 **/
		public static String Q_TYPE_MULTI = "Multi".toUpperCase();
		/** 多选填空 **/
		public static String Q_TYPE_MULTI_FILL = "MultiFill".toUpperCase();
		/** 填空题 **/
		public static String Q_TYPE_FILL_BLANK = "FillBlank".toUpperCase();
		/** 矩阵 **/
		public static String Q_TYPE_TABLE = "Table".toUpperCase();
		/** 单选矩阵 **/
		public static String Q_TYPE_TABLE_SINGLE = "TSingle".toUpperCase();
		/** 多选矩阵 **/
		public static String Q_TYPE_TABLE_MULTI = "TMulti".toUpperCase();
		/**矩阵填空  **/
		public static String Q_TYPE_TABLE_BLANK = "TFillBlank".toUpperCase();
		/** 矩阵下拉框 **/
		public static String Q_TYPE_TABLE_SELECT = "TSele".toUpperCase();
		/** 只为显示一行红字 **/
		public static String Q_TYPE_SHOWTEXT = "showtext".toUpperCase();
		/** 文本域 **/
		public static String Q_TYPE_TEXTAREA = "Textarea".toUpperCase();
		
		
		
		public enum DataType{
			VARCHER,DATETIME,DATE,TIME,NUMBER
		}
		public static String DATA_TYPE_VARCHER = "VARCHAR".toUpperCase();
		public static String DATA_TYPE_NUMBER = "NUMBER".toUpperCase();
		public static String DATA_TYPE_DATETIME = "datetime".toUpperCase();
		public static String DATA_TYPE_DATE = "date".toUpperCase();
		public static String DATA_TYPE_TIME = "time".toUpperCase();
		
		
		private String id;
		private int order;
		public OrderProperty(){}
		public OrderProperty(String id,int order){
			this.id = id;
			this.order = order;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public int getOrder() {
			return order;
		}
		public void setOrder(int order) {
			this.order = order;
		}
		
	}
	
}
