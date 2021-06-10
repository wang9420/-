package com.tom.util;

/**
 * 判断字符串工具类
 * @author Administrator
 *
 */
public class StringUtil {

	/**
	 * 判读是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if (str == null || "".equals(str.trim())){
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * 判断不为空?
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if (str != null && !"".equals(str.trim())){
			return true;
		}
		else {
			return false;
		}
	}
}
