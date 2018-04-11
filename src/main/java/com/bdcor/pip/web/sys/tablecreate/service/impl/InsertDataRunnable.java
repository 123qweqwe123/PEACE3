package com.bdcor.pip.web.sys.tablecreate.service.impl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bdcor.pip.web.sys.tablecreate.dao.PaperTableCreateDao;

public class InsertDataRunnable implements Runnable {
	
	private PaperTableCreateDao paperTableCreateDao;
	
	private String id;
	
	public InsertDataRunnable(PaperTableCreateDao paperTableCreateDao,String id){
		this.paperTableCreateDao = paperTableCreateDao;
		this.id=id;
	}

	@Override
	public void run() {
		try{
			Thread.sleep(50000);
			while(true){
				List<String> sqlL = new ArrayList<String>();
				int m=0;
				for(int i=0;i<50;i++){
					try{
						sqlL.add(PaperTableCreateServiceImpl.sqlQueue.poll(30, TimeUnit.SECONDS));
						m=0;
					}catch (Exception e) {
						m++;
						break;
					}
				}
				sqlSubmit(sqlL);
				if(m>30){
					break;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Transactional(
			propagation = Propagation.REQUIRED,
			isolation = Isolation.READ_COMMITTED,
			rollbackFor = Exception.class
		)
	private void sqlSubmit(List<String> sqlL){
		for(String s : sqlL){
			try{
				paperTableCreateDao.execInsertSql(s);
			}catch (Exception e) {
				File muf = new File("D:/insertError/");
				if(!muf.exists()){
					muf.mkdirs();
				}
				
				File f = new File("D:/insertError/"+id+".txt");
				if(!f.exists()){
					try {
						f.createNewFile();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				FileWriter fw = null;
				try {
					fw = new FileWriter(f,true);
					fw.write(s+";\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				finally{
					if(fw != null){
						try {
							fw.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
				
			}
		}
	}
	
}
