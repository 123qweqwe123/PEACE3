package com.bdcor.pip.client.tools;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.IOUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;



public class FileUtils extends org.apache.commons.io.FileUtils {

	/**
	 * class 类路径
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String classPath(){
		String returnPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
//		DatLogger.logSysStartDown(FileUtils.class, returnPath);
		return URLDecoder.decode(returnPath);
	}
	
	public static boolean createFoldes(String foldeDir){
		File f = new File(foldeDir);
		if(!f.exists()){
			return f.mkdirs();
		}
		return true;
	}
	
	/**
	 * 文件是否存在
	 * @param filePName
	 * @return
	 */
	public static boolean isExist(String filePName){
		File f = new File(filePName);
		return f.isFile()&&f.exists();
	}
	/**
	 * 移动文件到指定的文件夹
	 * @param files
	 * @param dir
	 * @return
	 */
	public static boolean moveFilesToDir(List<File> files,String dir){
		if(!new File(dir).exists()){
			new File(dir).mkdirs();
		}
		for(File f:files){
			try {
				FileUtils.moveToDirectory(f, new File(dir), false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	/**
	 * 返回文件的数据行
	 * @param file
	 * @return
	 */
	public static List<String> readLines(File file){
		try {
			return org.apache.commons.io.FileUtils.readLines(file);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DatException("文件不可读取",e);
		}
	}
	

	/**
	 * 向文件写入数据
	 * @param file
	 * @param data
	 * @param append
	 */
	public static void write(File file,String data,boolean append){
		try {
			org.apache.commons.io.FileUtils.write(file, data, append);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DatException("不可写入文件",e);
		}
	}
	
	public static void write(File file,String data,boolean append,String dcode){
		try {
			org.apache.commons.io.FileUtils.write(file, data, dcode,append);
		} catch (IOException e) {
			e.printStackTrace();
			throw new DatException("不可写入文件",e);
		}
	}
	
	/**
	 * 生成文件；<br>
	 * 
	 * <li>存在返回文件；</li>
	 * <li>不存在生成文件并返回文件；</li>
	 * @param filePName
	 */
	public static File createFile(String filePName){
		File f = new File(filePName);
		if(!f.exists()){
			try {
				if(!f.getParentFile().exists()){
					f.getParentFile().mkdirs();
				}
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return f;
	}
	
	/**
	 * 当前文件中的所有文件，
	 * 无文件时，返回空list
	 * @param f
	 * @return
	 */
	public static List<File> fileInCDir(File f){
		File[] files = f.listFiles(new FileFilter(){
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory()){
					return false;
				}
				return true;
			}
		});
		List<File> reList = new ArrayList<File>();
		for(File ff:files){
			reList.add(ff);
		}
		return reList;
	}
	
	/**
	 * 当前文件中的所有文件，
	 * 无文件时，返回空list
	 * @param f
	 * @return
	 */
	public static List<File> fileInCDir(File f,final String fileNameFilter){
		File[] files = f.listFiles(new FileFilter(){
			@Override
			public boolean accept(File pathname) {
				if(pathname.isDirectory()){
					return false;
				}
				if(pathname.getName().contains(fileNameFilter)){
					return true;
				}
				return false;
			}
		});
		List<File> reList = new ArrayList<File>();
		for(File ff:files){
			reList.add(ff);
		}
		return reList;
	}
	
	/**
	 * 文件压缩
	 * 
	 * @param files  待压缩的文件列表
	 * @param outdir 压缩文件保存文件夹。文件路径+文件名称（.zip）。
	 * @return 压缩后的文件名称  xxx.zip
	 */
	public static String zipFiles(Collection files,String outdir){
		 int      bufSize = 512;
	    byte[]          buf = new byte[bufSize]; 
	    int             readedBytes; 

	    String filename = System.currentTimeMillis()+".zip";
	    String zipFileName = outdir+filename;
	    if(outdir.endsWith("zip")){
	    	zipFileName = outdir;
	    }
		
		ZipOutputStream zipOut;
		try{ 
            zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName))); 
            FileInputStream fileIn; 
            for(Object ff:files){
            	File f = (File)ff;
            	fileIn = new FileInputStream(f); 
            	
        		
            	
                zipOut.putNextEntry(new ZipEntry(f.getName())); 

                while((readedBytes = fileIn.read(buf))>0){ 
                    zipOut.write(buf , 0 , readedBytes); 
                } 
                fileIn.close();
                zipOut.closeEntry(); 
            }
            zipOut.close(); 
        }catch(IOException ioe){ 
           //	DatLogger.loggError(ioe);
        } 
		return filename;
	}
	
	/**
	 * 文件压缩
	 * 
	 * @param files  待压缩的文件列表
	 * @param outdir 压缩文件保存文件夹。文件路径+文件名称（.zip）。
	 * @return 压缩后的文件名称  xxx.zip
	 */
	public static String zipFilesInpustStream(Map<String,InputStream> files,String outdir){
		 int      bufSize = 512;
	    byte[]          buf = new byte[bufSize]; 
	    int             readedBytes; 
		
	    String filename = System.currentTimeMillis()+".zip";
	    String zipFileName = outdir+filename;
	    if(outdir.endsWith("zip")){
	    	zipFileName = outdir;
	    }
	    
		
		ZipOutputStream zipOut;
		try{ 
            zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFileName))); 
            InputStream fileIn; 
            for(String fileName:files.keySet()){
            	fileIn = files.get(fileName); 
            	
                zipOut.putNextEntry(new ZipEntry(fileName)); 

                while((readedBytes = fileIn.read(buf))>0){ 
                    zipOut.write(buf , 0 , readedBytes); 
                } 
                zipOut.closeEntry(); 
            }
            zipOut.close(); 
        }catch(IOException ioe){ 
            ioe.printStackTrace(); 
        } 
		return filename;
	}
	
	/**
	 * 压缩包的根目录下是否包括制定的文件夹
	 * @param zipPFileName
	 * @param rootFolderName
	 * @return
	 */
	public static boolean isContainFolderNameOfRoot(String zipPFileName,
			String rootFolderName) {
		boolean returnV = false;
		try {
			// 根据ZIP文件创建ZipFile对象
			ZipFile zipFile = new ZipFile(zipPFileName);
			ZipEntry entry = null;
			String entryName = null;
			// 获取ZIP文件里所有的entry
			Enumeration entrys = zipFile.getEntries();
			// 遍历所有entry
			while (entrys.hasMoreElements()) {
				entry = (ZipEntry) entrys.nextElement();
				// 获得entry的名字
				entryName = entry.getName();
				if(entryName.contains(rootFolderName)){
					returnV = true;
					break;
				}
			}
			zipFile.close();
		} catch (IOException err) {
			//DatLogger.logSysCommun(FileUtils.class, "解压缩文件失败: " + err);
			//DatLogger.loggError(err);
		}
		return returnV;
	}
	
	/**
	 * 解压文件，支持压缩包中包括文件夹。<br>
	 * 有些压缩包不能解压成功，请调用unzipByJava()
	 * @param zipFileName
	 * @param targetBaseDirName
	 * @throws IOException 
	 */
	public static void unzip2(String zipFileName, String targetBaseDirName)
			throws IOException {

		if (!targetBaseDirName.endsWith(File.separator)) {
			targetBaseDirName += File.separator;
		}
		try {
			// 根据ZIP文件创建ZipFile对象
			ZipFile zipFile = new ZipFile(zipFileName);
			ZipEntry entry = null;
			String entryName = null;
			String targetFileName = null;
			byte[] buffer = new byte[4096];
			int bytes_read;
			// 获取ZIP文件里所有的entry
			Enumeration entrys = zipFile.getEntries();
			// 遍历所有entry
			while (entrys.hasMoreElements()) {
				entry = (ZipEntry) entrys.nextElement();
				// 获得entry的名字
				entryName = entry.getName();
				targetFileName = targetBaseDirName + entryName;
				if (entry.isDirectory()) {
					// 如果entry是一个目录，则创建目录
					new File(targetFileName).mkdirs();
					continue;
				} else {
					// 如果entry是一个文件，则创建父目录
					new File(targetFileName).getParentFile().mkdirs();
				}

				// 否则创建文件
				File targetFile = new File(targetFileName);
				// System.out.println("创建文件：" + targetFile.getAbsolutePath());
				// DatLogger.logSysCommun(FileUtils.class, "创建文件：" +
				// targetFile.getAbsolutePath());
				// 打开文件输出流
				FileOutputStream os = new FileOutputStream(targetFile);
				// 从ZipFile对象中打开entry的输入流
				InputStream is = zipFile.getInputStream(entry);
				while ((bytes_read = is.read(buffer)) != -1) {
					os.write(buffer, 0, bytes_read);
				}
				// 关闭流
				os.close();
				is.close();
			}
			zipFile.close();
			System.out.println("解压缩文件成功！  ");
		} catch (IOException err) {
			throw new IOException("解压缩文件失败: " + zipFileName, err);
		}
	}
	
	

	/**
	 * 使用jre自带zip包解压类。不支持压缩包中文件夹解压
	 * @param zipFileName
	 * @param targetBaseDirName
	 * @throws IOException 
	 */
	public static void unzipByJava(String zipFileName, String targetBaseDirName)
			throws IOException {
		String Parent=targetBaseDirName; //输出路径（文件夹目录）
		try {
			ZipInputStream Zin=new ZipInputStream(new FileInputStream(zipFileName));//输入源zip路径
			BufferedInputStream Bin=new BufferedInputStream(Zin);
			
			File entityFile=null;
			java.util.zip.ZipEntry entry;
			try {
				while((entry = Zin.getNextEntry())!=null && !entry.isDirectory()){
					entityFile=new File(Parent,entry.getName());
					if(!entityFile.exists()){
						(new File(entityFile.getParent())).mkdirs();
					}
					FileOutputStream out=new FileOutputStream(entityFile);
					BufferedOutputStream Bout=new BufferedOutputStream(out);
					
					IOUtils.copy(Bin, out);
					Bout.close();
					out.close();
				}
				Bin.close();
				Zin.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] a) throws IOException{
		long start = System.currentTimeMillis();
		String zipFile = "D:\\PIPEclipse\\001\\DATA\\DICT\\201_20150730151412.zip";
		String toDir = "D:\\PIPEclipse\\001\\DATA\\DICT";
		
		unzip(zipFile,toDir);
		
		long end = System.currentTimeMillis();
		
		System.out.println((end-start)+"ms");
		
	}
	
	
	/**
	 * 文件解压  ，压缩包中保留文件夹时，不能解压
	 * @param zipFilePath
	 * @param targetPath
	 * @return
	 * @throws IOException
	 */
	public static List<String> fileNameInZipFile(String zipFilePath)
	           throws IOException {
		List<String> outZipFiles = new ArrayList<String>();
	       InputStream is = null;
	       ZipFile zipFile = null;
	       try {
	           zipFile = new ZipFile(zipFilePath);
	           String directoryPath = "";
	           Enumeration entryEnum = zipFile.getEntries();
	           if (null != entryEnum) {
	               ZipEntry zipEntry = null;
	               while (entryEnum.hasMoreElements()) {
	                   zipEntry = (ZipEntry) entryEnum.nextElement();
	                   outZipFiles.add(zipEntry.getName());
	                   
	               }
	           }
	       } catch (IOException ex) {
	          ex.printStackTrace();
	          System.out.println(zipFilePath);
	       } catch (Exception ex) {
	    	   ex.printStackTrace();
	    	   System.out.println(zipFilePath);
	       } finally {
	           if(null != zipFile){
	        	   zipFile.close();
	               zipFile = null;
	           }
	           if (null != is) {
	               is.close();
	           }
	       }
	       return outZipFiles;
	   }
	
	
	
	/**
	 * 文件解压  ，压缩包中保留文件夹时，不能解压
	 * @param zipFilePath
	 * @param targetPath
	 * @return
	 * @throws IOException
	 */
	public static List<File> unzip(String zipFilePath, String targetPath)
	           throws IOException {
		List<File> outZipFiles = new ArrayList<File>();
	       OutputStream os = null;
	       InputStream is = null;
	       ZipFile zipFile = null;
	       try {
	           zipFile = new ZipFile(zipFilePath);
	           String directoryPath = "";
	           if (null == targetPath || "".equals(targetPath)) {
	               directoryPath = zipFilePath.substring(0, zipFilePath
	                       .lastIndexOf("."));
	           } else {
	               directoryPath = targetPath;
	           }
	           
	           if(!new File(targetPath).exists()){
	        	   new File(targetPath).mkdirs();
	           }
	           
	           Enumeration entryEnum = zipFile.getEntries();
	           if (null != entryEnum) {
	               ZipEntry zipEntry = null;
	               while (entryEnum.hasMoreElements()) {
	                   zipEntry = (ZipEntry) entryEnum.nextElement();
	                   if (zipEntry.isDirectory()) {
	                       directoryPath = directoryPath + File.separator
	                               + zipEntry.getName();
	                       System.out.println(directoryPath);
	                       continue;
	                   }
	                   if (zipEntry.getSize() > 0) {
	                       // 文件
	                       File targetFile = buildFile(directoryPath
	                               + File.separator + zipEntry.getName(), false);
	                       os = new BufferedOutputStream(new FileOutputStream(
	                               targetFile));
	                       is = zipFile.getInputStream(zipEntry);
	                       byte[] buffer = new byte[4096];
	                       int readLen = 0;
	                       while ((readLen = is.read(buffer, 0, 4096)) >= 0) {
	                           os.write(buffer, 0, readLen);
	                       }
	                       os.flush();
	                       os.close();
	                       outZipFiles.add(targetFile);
	                   } else {
	                       // 空目录
	                       buildFile(directoryPath + File.separator
	                               + zipEntry.getName(), true);
	                   }
	               }
	           }
	       } catch (IOException ex) {
	           throw ex;
	       } finally {
	           if(null != zipFile){
	        	   zipFile.close();
	               zipFile = null;
	           }
	           if (null != is) {
	               is.close();
	           }
	           if (null != os) {
	               os.close();
	           }
	       }
	       return outZipFiles;
	   }
	
	public static String readFileToString(String afilePath,String afileName) throws IOException{
		
		return org.apache.commons.io.FileUtils.readFileToString(new File(afilePath+afileName));
	}
	
	
	public static String readFileToString(String pName){
		try {
			return org.apache.commons.io.FileUtils.readFileToString(new File(pName));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static String readFileToString(File pName){
		try {
			return org.apache.commons.io.FileUtils.readFileToString(pName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
    public static File buildFile(String fileName, boolean isDirectory) {

        File target = new File(fileName);

        if (isDirectory) {

            target.mkdirs();

        } else {

            if (!target.getParentFile().exists()) {

                target.getParentFile().mkdirs();

                target = new File(target.getAbsolutePath());

            }

        }

        return target;

    }
    
    /**
     * 流转换为字符串
     * @param in
     * @return
     */
    public  static String   inputStream2String(InputStream in){ 
        StringBuffer out = new StringBuffer(); 
        byte[] b = new byte[4096]; 
        try {
			for(int n;   (n   =   in.read(b))   !=   -1;)   { 
				out.append(new   String(b,   0,   n)); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
        return   out.toString(); 
    } 
}
