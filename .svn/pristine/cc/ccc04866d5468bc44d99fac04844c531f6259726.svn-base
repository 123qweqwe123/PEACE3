package com.bdcor.pip.client.vo.paper;



import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bdcor.pip.client.ClientInfo;
import com.bdcor.pip.client.file.filter.XmlFileFilter;
import com.bdcor.pip.client.tools.CFileInfo;
import com.bdcor.pip.client.tools.PagerData;
import com.bdcor.pip.client.xml.model.TempletQuestionnaireDocumentBean;


public class PaperDataImpl implements PagerData {

	private List<File> files = null;

	public PaperDataImpl(String ctxPath){
		loadFile(ctxPath);
		
	}
	
	/**
	 *  加载问卷模板文件
	 */
	private void loadFile(String ctxPath){
		files = CFileInfo.templateModelFiles(ctxPath);
	}


	@Override
	public List getPagerObjs() {
		List reList = new ArrayList();
		//DatLogger.logSysStartDown(getClass(),"开始加载问卷文件");
		
		long start = System.currentTimeMillis();
		int loadCount = 0;
		for(File f:files){
			TempletQuestionnaireDocumentBean questionnaire = null;
			try {
				questionnaire = (TempletQuestionnaireDocumentBean)TempletQuestionnaireDocumentBean.Factory.parse(
						new XmlFileFilter(ClientInfo.PROJ_NO).decryptionFile(f));
			} catch (Exception e) {
			//	DatLogger.logSysCommun(getClass(), "问卷密文方式解析错误，按照明文方式解析");
			//	DatLogger.loggError(e);
				try {
					questionnaire = (TempletQuestionnaireDocumentBean)TempletQuestionnaireDocumentBean.Factory.parse(
					new FileInputStream(f));
				} catch (Exception e1) {
			//		DatLogger.loggError(e1);
			//		DatLogger.logSysCommun(getClass(), "问卷明文方式解析错误");
			//		DatLogger.logSysCommun(getClass(), f.getParent()+"加载失败");
				}
			}
			
			if(questionnaire!=null){
				reList.add(questionnaire.getQuestionnaire());
			//	DatLogger.logSysStartDown(getClass(),"成功加载："+f.getName());
				loadCount++;
			}
		}
		
		long end = System.currentTimeMillis();
		
//		System.out.println(end-start); 
	//	DatLogger.logSysStartDown(getClass(),"完成问卷文件的加载,共加载："+loadCount+"个问卷；耗时："+(end-start)+"毫秒");
		return reList;
	}

}
