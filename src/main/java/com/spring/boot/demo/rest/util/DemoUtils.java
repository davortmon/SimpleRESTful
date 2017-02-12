package com.spring.boot.demo.rest.util;

/**
 * Class Utilities.
 * @author David
 *
 */
public class DemoUtils {

	/**
	 * Valid if a string is empty or null. 
	 * @param str String to validate.
	 * @return true if string is empty or null.
	 */
	public static final boolean isEmpty(String str) {
		return (null == str || str.trim().isEmpty());
	}
}
