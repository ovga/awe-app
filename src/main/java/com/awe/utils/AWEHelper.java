package com.awe.utils;

public class AWEHelper {

	private AWEHelper() {}
	
	public static String convertToPrettyName(String string) {
		return string.toLowerCase()
				.replaceAll("[^a-zA-Z0-9 ]", "")
				.replaceAll("\\s+", "-");
		
	}
}
