package com.midas.cafe.common;

/**
 * User: kimkm
 * Date: 2018-05-26
 * Time: 오후 7:07
 */
public class StrUtil
{
	public static String getDefault(String str, String defaultValue)
	{
		return isNotEmpty(str) ? str : defaultValue;
	}

	public static boolean isNotEmpty(String str)
	{
		return str != null && str.length() > 0;
	}

}
