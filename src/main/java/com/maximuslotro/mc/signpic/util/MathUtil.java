package com.maximuslotro.mc.signpic.util;

import java.text.DecimalFormat;

public class MathUtil {
	public static boolean isInt(String str) {
		try {
	        Integer.valueOf(str);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	public static int calculatePercentageToInt(int obtained, int total) {
        return obtained * 100 / total;
    }
	public static float calculatePercentageToFloat(int obtained, int total) {
		float result = 0.0f;
        float upper = 100.0f;
		result = (float)obtained / (float)total;
		float product = result * upper;
	    return product;
    }
	public static double round1(float x) {
		double roundOff = Math.round(x * 10.0) / 10.0;
	    return roundOff;
	}
}
