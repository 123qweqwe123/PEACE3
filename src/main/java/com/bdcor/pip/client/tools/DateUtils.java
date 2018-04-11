package com.bdcor.pip.client.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	/**
	 * 
	 * description:通过当前时间获取订单号生成订单的格式是：年月日时分秒
	 * 
	 * @author yangfeng
	 * @return
	 * @update 2015-12-18
	 */
	public static synchronized String getOrderNoByNowDay() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * description: 在日期上面添加 N天以后的日期 d是目标日期， day是天数 返回一天新的日期
	 * 
	 * @author yangfeng
	 * @param d
	 * @param day
	 * @return
	 * @throws ParseException
	 * @update 2015-12-18
	 */
	public static Date addDate(Date d, long day) throws ParseException {

		long time = d.getTime();
		day = day * 24 * 60 * 60 * 1000;
		time += day;
		return new Date(time);

	}

	/**
	 * 指定日期到今天的年份
	 * 
	 * @param d
	 * @return
	 */
	public static int yearsToToday(Date d) {
		if (d == null) {
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 得到当前的年份
		String cYear = sdf.format(new Date()).substring(0, 4);
		// 得到生日年份
		String birthYear = sdf.format(d).substring(0, 4);

		int age = Integer.parseInt(cYear) - Integer.parseInt(birthYear);
		return age;
	}

	/**
	 * 字符创转换为日期
	 * 
	 * @param strFormat
	 *            为 null时，使用格式：yyyy-MM-dd
	 * @param dateValue
	 * @return
	 */
	public static Date parseDate(String strFormat, String dateValue) {
		if (dateValue == null)
			return null;

		if (strFormat == null)
			strFormat = "yyyy-MM-dd";

		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date newDate = null;

		try {
			newDate = dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			newDate = null;
		}

		return newDate;
	}

	/**
	 * 字符创转换为日期时间
	 * 
	 * @param strFormat
	 * @param dateValue
	 * @return
	 */
	public static Date parseDateTime(String strFormat, String dateValue) {
		if (dateValue == null)
			return null;

		if (strFormat == null)
			strFormat = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		Date newDate = null;

		try {
			newDate = dateFormat.parse(dateValue);
		} catch (ParseException pe) {
			newDate = null;
		}

		return newDate;
	}

	/**
	 * 字符创转换为日期时间
	 * 
	 * @param strFormat
	 * @param dateValue
	 * @return
	 */
	public static String fromatDateTime(String strFormat, Date date) {
		if (date == null)
			return null;

		if (strFormat == null)
			strFormat = "yyyy-MM-dd HH:mm:ss";

		SimpleDateFormat dateFormat = new SimpleDateFormat(strFormat);
		return dateFormat.format(date);
	}

	/**
	 * 当前日期， 格式 ：yyyy-MM-dd
	 * 
	 * @return
	 */
	public static String currentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date());
	}

	/**
	 * 获取 服务器端 时间
	 * 
	 * @return
	 */
	// public static Date currentServerDat(){
	//
	//
	// URL url = null;
	// try {
	// url = new
	// URL("http://"+Constants.getPropertyValue("time_server","123.124.148.46"));
	// } catch (MalformedURLException e) {
	// e.printStackTrace();
	// }
	// Date date = HttpClientUtils.getServerTimeByProtocol(url);
	//
	//
	// return date;
	// }

	/**
	 * 日期相差天数
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int diffOfDay(Date d1, Date d2) {
		long l1 = d1.getTime();
		long l2 = d2.getTime();
		return (int) ((l1 - l2) / (1000 * 60 * 60 * 24));
	}

}
