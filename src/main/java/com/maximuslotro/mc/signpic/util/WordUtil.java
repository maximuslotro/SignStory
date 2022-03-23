package com.maximuslotro.mc.signpic.util;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.math.IntMath;

public class WordUtil {
	
	public static List<String> Splitter(String text, int line_length, int row_n_combine) {
		List<String> Lines_unformatted = splitString(text, line_length);
		List<String> Lines_formatted = addspaces(Lines_unformatted, line_length);
		List<String> Lines_combined = combinelines(Lines_formatted, row_n_combine, line_length);
		return Lines_combined;
		}
	private static List<String> combinelines(List<String> f_lines, int n_row, int linesize){
		List<String> combined = new ArrayList<>();
		int line_e = 0;
		int line_n = 0;
		for (int i = 0; i < f_lines.size(); i++) {
			if (line_e<=n_row) {
				if(line_e==0) {
					combined.add(line_n, f_lines.get(i)); 
					line_e=line_e+1;
				}else {
					combined.set(line_n, combined.get(line_n)+f_lines.get(i)); 
					line_e=line_e+1;
					}
				if(line_e>=(n_row)) {
					line_n=line_n+1;
					line_e=0;
				}

			}
			
		}
		StringBuilder builder = new StringBuilder(combined.get(line_n));
		while (builder.length() < linesize*n_row) {
		    builder.append(" ");
		}
		builder.setLength(linesize*n_row);
		String result = builder.toString();
		combined.set(line_n, result);
		return combined;
	}
	
	private static List<String> addspaces(List<String> uf_lines, int linesize) {
		for (int i = 0; i < uf_lines.size(); i++) {
			StringBuilder builder = new StringBuilder(uf_lines.get(i).toString());
			while (builder.length() < linesize) {
			    builder.append(" ");
			}
			builder.setLength(linesize);
			String result = builder.toString();
			uf_lines.set(i, result);
        }
		return uf_lines;
	}
	
	
	
	private static List<String> splitString(String msg, int lineSize) {
        List<String> res = new ArrayList<>();

        Pattern p = Pattern.compile("\\b.{1," + (lineSize-1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);
        while(m.find()) {
        	res.add(m.group());
        }
        return res;
    }

}
