package com.meession.education.common.util;

import java.util.Calendar;
import java.util.Date;

/**
 * <p>
 * A suite of utilities surrounding the use of the {@link java.util.Calendar}
 * and {@link java.util.Date} object.
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

}
