package com.meession.market.common.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * A suite of utilities surrounding the use of the {@link java.util.Calendar}
 * and {@link java.util.Date}{@link java.sql.Date} object.
 * </p>
 * 
 * @author sam
 */
public abstract class DateUtils {

	/**
	 * if day=1, return 1900,1,1<br>
	 * if day=2, return 1900,1,2
	 * 
	 * @param day
	 * @return
	 */
	public static Date getDateSince1900(int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(1900, 0, 0, 0, 0, 0);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}
	
	/**
	 * return current year
	 * 
	 * @return
	 */
	public static int getCurrentYear() {
		return Calendar.getInstance().get(Calendar.YEAR);
	}

	/**
	 * return current month (0-11)
	 * 
	 * @return
	 */
	public static int getCurrentMoth() {
		return Calendar.getInstance().get(Calendar.MONTH);
	}

	/**
	 * return current year (0-31)
	 * 
	 * @return
	 */
	public static int getCurrentDayOfMonth() {
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * return java.util.Date object of now
	 * 
	 * @return
	 */
	public static java.util.Date getUtilNowDate() {
		Calendar cal = Calendar.getInstance();
		java.util.Date now = new java.util.Date(cal.getTimeInMillis());
		return now;
	}

	/**
	 * return java.sql.Date object of now
	 * 
	 * @return
	 */
	public static java.sql.Date getSqlNowDate() {

		Calendar cal = Calendar.getInstance();
		java.sql.Date now = new java.sql.Date(cal.getTimeInMillis());
		return now;
	}

	/**
	 * convert java.util.Date object to java.sql.Date object
	 * 
	 * @param java
	 *            .util.Date
	 * @return
	 */
	public static java.sql.Date convertToSqlDate(java.util.Date date) {
		return new java.sql.Date(date.getTime());
	}

	/**
	 * convert java.sql.Date object to java.util.Date object
	 * 
	 * @param java
	 *            .sql.Date
	 * @return
	 */
	public static java.util.Date convertToUtilDate(java.sql.Date date) {
		return new java.util.Date(date.getTime());
	}

	/**
	 * format the date to string
	 * 
	 * @param time
	 * @return
	 */
	public static String formart(Date time) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String ctime = formatter.format(time);
		return ctime;
	}

	/**
	 * format the date to string with given pattern
	 * 
	 * @param time
	 * @param pattern
	 * @return
	 */
	public static String formart(Date time, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String ctime = formatter.format(time);
		return ctime;
	}

	/**
	 * parse string to date
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String str) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.util.Date d = sdf.parse(str);
		
		return DateFormat.getDateInstance().parse(str);
	}

	/**
	 * prarse String to date with given pattern
	 * 
	 * @param str
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Date parse(String str, String pattern) throws ParseException {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(pattern);
		java.util.Date d = sdf.parse(str);
		return d;
	}

	/**
	 * if the first date is between the second date and the third date return
	 * true, else return false
	 * 
	 * @param today
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean isBetweenStartAndEnd(Date today, Date start, Date end) {
		boolean rs = false;
		if (today.after(start) && today.before(end)) {
			rs = true;
		}
		return rs;
	}

	/**
	 * count date number between startDate and endDate
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(Date startDate, Date endDate)throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		startDate = sdf.parse(sdf.format(startDate));
		endDate = sdf.parse(sdf.format(endDate));

		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		long timeStart = cal.getTimeInMillis();
		cal.setTime(endDate);
		long timeEnd = cal.getTimeInMillis();
		long between_days = (timeEnd - timeStart) / (1000 * 60 * 60 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * count date number between startDate and endDate
	 * 
	 * @param startdate
	 * @param enddate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(String startDate, String endDate)throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		cal.setTime(sdf.parse(startDate));
		long timeStart = cal.getTimeInMillis();

		cal.setTime(sdf.parse(endDate));
		long timeEnd = cal.getTimeInMillis();
		long between_days = (timeEnd - timeStart) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}
	
	/**
	 * 如果double有小数点就保留两位小数 如果没有小数部分就显示整数
	 * 
	 * @param a
	 * @return
	 */
	public static double changeDouble(double a) {
		DecimalFormat df = new DecimalFormat("##0.00");
		String str = "0.00";
		double resulet =(int)a;
		if ((a - resulet) >= 1e-3) {
			str = df.format(a);
			resulet = Double.parseDouble(str);
		}
		return resulet;
	}
	
}
