package com.bdcor.pip.client.tools;



import java.util.List;

public interface IDbManager {
	public static String errorValue = "1";
	public static String normalValue = "0"; 
	
	/**
	 * 数据库备份
	 * @return
	 */
	public abstract String dbBack();
	
	/**
	 * 数据库还原
	 * @return
	 */
	public abstract String dbRestores();
	
	/**
	 * 备份     错误数据库
	 */
	public abstract void backupErrorDB();

	/**
	 * 数据库是否损坏
	 * @return
	 */
	public abstract boolean dbIsError();
	
	/**
	 * 获取数据库损坏次数
	 * @return
	 */
	public abstract int getDbErrorCount();
	

	/**
	 * 设置数据库错误
	 */
	public abstract void setDBError();

	/**
	 * 设置数据库正常
	 */
	public abstract void setDBNormal();


	/**
	 * 记录查询日志
	 * @param sql
	 */
	public abstract void queryLog(String sql);

	/**
	 * 记录更新日志
	 * @param sql
	 */
	public abstract void updateLog(String sql);


	/**
	 * 关闭数据库
	 */
	public abstract void closeDb();



	/**
	 * 数据库名称
	 * @return
	 */
	public abstract String dbName();

	/**
	 * 数据库路径
	 * @return
	 */
	public abstract String dbPath();
	
	/**
	 * <p>设置上次还原包名称
	 * <li>在开始还原时，保存还原文件的名称
	 * <li>启动成功后，设置还原包名称为”0“,
	 * 
	 * @return
	 */
	public abstract void savePriRebackDbName(String filename);

	public boolean isBackupIng();
}