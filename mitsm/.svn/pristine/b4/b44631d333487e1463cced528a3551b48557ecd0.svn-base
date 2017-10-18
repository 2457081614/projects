package com.meession.market.common.dateutil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 把java.util.Date类型的日期转化为String类型的日期
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		String s = df.format(date);
		return s;
	}

	/**
	 * 把String类型的日期转化为java.util.Date类型的日期
	 * @param s
	 * @return
	 */
	public static Date stringToDate(String s) {
		Date d = null;
		try {
			d = df.parse(s);
		} catch (ParseException e) {
			System.out.println("日期格式不正确！");
		}
		return d;
	}

	



	/**
	 * 获取随机日期
	 * 
	 * @param begindate
	 *            起始日期，格式为：yyyy-mm-dd
	 * @param enddate
	 *            结束日期，格式为：yyyy-mm-dd
	 * @return
	 */

	public static Date randomDate(String beginDate, String endDate) {
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date start = format.parse(beginDate);// 开始日期
			Date end = format.parse(endDate);// 结束日期
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = randomDate(start.getTime(), end.getTime());

			return new Date(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 得到指定范围的随机日期
	 * @param begin - 最早时间
	 * @param end - 最晚时间
	 * @return long类型的时间毫秒数
	 */
	private static long randomDate(long begin, long end) {
		long rtnn = begin + (long) (Math.random() * (end - begin));
		if (rtnn == begin || rtnn == end) {
			return randomDate(begin, end);
		}
		return rtnn;
	}
	/**
	 * 判断日期是否相等
	 */
	public static boolean datesAreEqual(java.sql.Date date1,java.sql.Date date2){
		if(!date1.after(date2)&&!date2.after(date1)){
			return true;
		}
		return false;
	}
	
	/**
	 * 得到系统当前时间
	 */
	public static java.util.Date getCurrentTime(){
		long longTime = System.currentTimeMillis();
		java.util.Date date = new Date(longTime);
		return date;
	}
}
