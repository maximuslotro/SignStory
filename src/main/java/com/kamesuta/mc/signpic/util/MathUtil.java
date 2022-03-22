package com.kamesuta.mc.signpic.util;

public class MathUtil {
	public static boolean isInt(String str) {
		try {
	        Integer.parseInt(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
