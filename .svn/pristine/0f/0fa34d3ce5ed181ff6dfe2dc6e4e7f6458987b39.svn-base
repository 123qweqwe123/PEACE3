package com.bdcor.pip.client.tools;


import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.bdcor.pip.core.utils.CustomizedPropertyPlaceholderConfigurer;




public class CFileInfo extends LogHandler{

		//客户端文件夹路径
		/** 字典目录 **/
		public static String DICT_DIR = null;
		/** 模板目录 **/
		public static String MODEL_DIR = null;
		/** 结果目录 **/
		public static String RESULT_DIR = null;
		
		/** 答卷目录 **/
		public static String RESULT_UQS_DIR = null;
		/** 样本目录 **/
		public static String RESULT_SCM_DIR = null;
		/** 心电图目录 **/
		public static String RESULT_ECG_DIR = null;
		/** 超声图目录 **/
		public static String RESULT_ECH_DIR = null;
		/** 指血文件目录 **/
		public static String RESULT_FGB_DIR = null;
		/** 颈动脉超声目录 **/
		public static String RESULT_CDU_DIR = null;
		/** 骨密度目录 **/
		public static String RESULT_BND_DIR = null;
		/** 受访者目录 **/
		public static String RESULT_PAT_DIR = null;
		/** 错误文件 **/
		public static String RESULT_ERROR_DIR = null;
		
		/** 身份证xml文件保存目录 **/
		public static String RESULT_IDCORDS_DIR = null;
		
		/** 导入导出数据时，压缩包临时文件夹 **/
		public static String UPLOAD_XML_DIR = null;
		/** 数据备份目录，在数据导出或上传时，备份 **/
		public static String DATABACKUP_DIR = null;
		
		/** 日志文件名称 **/
		public static String VERSION_FILE_NAME = "sVersion.txt";

		
		/**
		 * 客户端结果文件保存路径
		 * @param fileType 文件类型
		 * @return
		 */
		public static File busFile(String fileType){
			
			String filePath = busFilePath(fileType);
			File f = null;
			if(filePath!=null){
				f = new File(filePath);
				if(!f.exists()){
					boolean createSuc = f.mkdirs();
					if(!createSuc){
						throw new DatException("客户端创建文件夹失败："+filePath);
					}
				}
			}
			return f;
		}
		
		
		/**
		 * 客户端结果文件保存路径
		 * @param fileType 文件类型
		 * @return
		 */
		public static String busFilePath(String fileType){
			String filePath = null;
			try {
				Field field = CFileInfo.class.getField("RESULT_"+fileType.toUpperCase()+"_DIR");
				filePath = field.get(null).toString();
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return filePath;
		}
		
		/**
		 * 问卷模板列表
		 * @param type
		 * @return
		 */
		public static List<File> templateModelFiles(String ctxPath){
			//List<File> flist = FileUtils.fileInCDir(new File(DirectoryInfo.instance().DAT_TEMPLATE_Dir()));
			//先用本机的 地址做测试 
			String path ="";
//			String  osName = System.getProperties().getProperty("os.name");
//			if(osName.toUpperCase().indexOf("WIN")!=-1){
//				path = CustomizedPropertyPlaceholderConfigurer.getContextProperty("win.question.page.xml.path");
//			}else{
//				path = CustomizedPropertyPlaceholderConfigurer.getContextProperty("linux.question.page.xml.path");
//			}
			//path = CustomizedPropertyPlaceholderConfigurer.getContextProperty(ctxPath);
			List<File> flist = FileUtils.fileInCDir(new File(ctxPath));
			List<File> rList = new ArrayList<File>();
			for(File f:flist){
				if(f.getName().contains("_rule")){
					continue;
				}
				rList.add(f);
			}
			return rList;
		}
		
		/**
		 * 规则文件列表
		 * @param type
		 * @return
		 */
		public static List<File> rulerModelFiles(){
			List<File> flist = FileUtils.fileInCDir(new File(DirectoryInfo.instance().DAT_TEMPLATE_Dir()));
			List<File> rList = new ArrayList<File>();
			for(File f:flist){
				if(f.getName().contains("_rule")){
					rList.add(f);
				}
			}
			return rList;
		}
		
		
		/**
		 * 某类业务文件列表
		 * @param type
		 * @return
		 */
		public static List<File> busFiles(String type){
			File f = busFile(type);
			return FileUtils.fileInCDir(f);
		}
		
		/**
		 * 某类业务文件名称列表
		 * @param type
		 * @return
		 */
		public static List<String> busFileNames(String type){
			List<String> fNames = new ArrayList<String>();
			for(File f:busFiles(type)){
				fNames.add(f.getPath());
			}
			return fNames;
		}
		
		

}
