package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {
	public static String getDateString(Date d,String format){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}
}
