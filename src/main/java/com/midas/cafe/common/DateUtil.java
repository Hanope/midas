package com.midas.cafe.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 7:08
 */
public class DateUtil
{
	public static String format(Date date)
	{
		return format("yyyy-MM-dd HH:mm:ss.SSS", date);
	}

	public static String format(String format, Date date)
	{
		return date != null ? (new SimpleDateFormat(format)).format(date) : "";
	}

	public static Timestamp currentTimestamp()
	{
		return new Timestamp(System.currentTimeMillis());
	}

}
