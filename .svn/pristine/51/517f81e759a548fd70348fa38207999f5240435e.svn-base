package com.bdcor.pip.data.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.w3c.dom.Document;

import com.bdcor.pip.core.utils.SpringContextHolder;
import com.bdcor.pip.web.data.domain.PipSysNews;
import com.bdcor.pip.web.data.domain.PipSysNewsMarquee;
import com.bdcor.pip.web.data.service.NewsService;
import com.bdcor.pip.web.data.service.impl.PipSysNewsServiceImpl;

public class PoiUtil {
	
	private static PipSysNewsServiceImpl pipSysNewsServiceImpl;
	private static NewsService newsServiceImpl;
	static{
		pipSysNewsServiceImpl = SpringContextHolder.getBean("pipSysNewsServiceImpl");
		newsServiceImpl= SpringContextHolder.getBean("newsServiceImpl");
	}
	
	
	public static PipSysNews queryNewsById(String id){
		try{
			return pipSysNewsServiceImpl.queryNewsById(id);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return null;
	}
	
	public static List<PipSysNews> queryNews(String channel){
		
		try{
			return pipSysNewsServiceImpl.queryNews(channel);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ArrayList<PipSysNews>();
	}
	
	public static List<PipSysNewsMarquee> queryMarqueeNews(){
		
		try{
			return newsServiceImpl.getAllNewsMarquee();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ArrayList<PipSysNewsMarquee>();
	}
	
	public static String word2Html(String path ,String file){
		
		InputStream input = null;
		try {
			input = new FileInputStream(path + file);
			
			HWPFDocument wordDocument = new HWPFDocument(input);
	        WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument());
	        wordToHtmlConverter.setPicturesManager(new PicturesManager() {
	            public String savePicture(byte[] content, PictureType pictureType, String suggestedName, float widthInches, float heightInches) {
	                return suggestedName;
	            }
	        });
	        wordToHtmlConverter.processDocument(wordDocument);
	        
	        String perfix = EncodeUtils.MD5Encode(file);
	        perfix = perfix.replaceAll("=", "");
	        perfix = perfix.replaceAll("/", "");
	        if ( perfix.length() > 10 )
	        	perfix = perfix.substring(0 , 10);
	        List pics = wordDocument.getPicturesTable().getAllPictures();
	        if (pics != null) {
	            for (int i = 0; i < pics.size(); i++) {
	                Picture pic = (Picture) pics.get(i);
	                try {
	                    pic.writeImageContent(new FileOutputStream(path + perfix + pic.suggestFullFileName()));
	                } catch (FileNotFoundException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	        Document htmlDocument = wordToHtmlConverter.getDocument();
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
	        DOMSource domSource = new DOMSource(htmlDocument);
	        StreamResult streamResult = new StreamResult(outStream);
	 
	        TransformerFactory tf = TransformerFactory.newInstance();
	        Transformer serializer = tf.newTransformer();
	        serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
	        serializer.setOutputProperty(OutputKeys.INDENT, "yes");
	        serializer.setOutputProperty(OutputKeys.METHOD, "html");
	        serializer.transform(domSource, streamResult);
	        outStream.close();
	 
	        String content = new String(outStream.toByteArray());
	        
	        //格式化图片
	        content = content.replaceAll("<img src=\"", "<img src=\"./news/"+perfix);
			//去掉背景css
	        if ( content.indexOf(".b2{") > 0 ){
	        	StringBuffer sb = new StringBuffer();
	        	sb.append(content.substring(0 , content.indexOf(".b2{")));
	        	content = content.substring(content.indexOf(".b2{") + 4 , content.length() );
	        	sb.append(content.substring(content.indexOf("}") + 1 , content.length()));
	        	content = sb.toString();
	        }
	        
	        return content;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				if ( input != null)
					input.close();
			}catch(Exception ex){}
			
		}
		return "";
	}

}
