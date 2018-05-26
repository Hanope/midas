package com.midas.cafe.common;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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

	public static List<String> splitToList(String str, String delim)
	{
		List<String> list = new ArrayList<>();
		StringTokenizer st = new StringTokenizer(str, delim);

		while (st.hasMoreTokens())
		{
			String s = st.nextToken().trim();
			if (s.length() > 0)
			{
				list.add(s);
			}
		}

		return list;
	}
}
