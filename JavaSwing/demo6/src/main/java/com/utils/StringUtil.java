package com.utils;

public class StringUtil {
	public static boolean isEmpty(String str){
		if("".equals(str)|| str == null){
			return true;
		}
		return false;
	}
}
