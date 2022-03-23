package com.maximuslotro.mc.signpic.util;

public class MathUtil {
	public static boolean isInt(String str) {
		try {
	        Integer.valueOf(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
}
