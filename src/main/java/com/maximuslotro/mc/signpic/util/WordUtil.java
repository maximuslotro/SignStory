package com.maximuslotro.mc.signpic.util;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.math.IntMath;
//import com.maximuslotro.mc.signpic.Log;

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
				if(line_e>=(n_row)&&i<f_lines.size()-1) {
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
			//Log.logDefault(i+"["+result+"]");
        }
		return uf_lines;
	}
	
	
	
	private static List<String> splitString(String msg, int lineSize) {
        List<String> res = new ArrayList<>();
/*
        Pattern p = Pattern.compile("\\b.{1," + (lineSize-1) + "}\\b\\W?");
        Matcher m = p.matcher(msg);
        while(m.find()) {
        	res.add(m.group());
        }*/
        String words[] = msg.split("\\s");//splits based on white space
        String line = "";
        Boolean done = false;
        for(String w:words) {
        	done=false;
        	//Log.logDefault("["+w+"]"+"b1");
        	if(w.charAt(w.length()-1)=='&') {
        		String wr =w.replaceAll("&", "");
        		if(line!="") {
        			String tmp= line+" "+wr;
            		if(tmp.length()>lineSize) {
            			res.add(line);
            			//Log.logDefault("["+line+"]"+"a1");
            			res.add(wr);
            			//Log.logDefault("["+wr+"]"+"a2");
            			line="";
            		}
            		if(tmp.length()==lineSize){
            			res.add(tmp);
            			//Log.logDefault("["+tmp+"]"+"a3");
            			line="";
            		}
            		if(tmp.length()<lineSize){
            			res.add(tmp);
            			//Log.logDefault("["+tmp+"]"+"a4");
            			line="";
            		}
        		}else {
        			res.add(wr);
            		//Log.logDefault("["+wr+"]"+"a5");
            	}
            	done=true;
        	}
            if(w.length() > lineSize&&done==false) {
            	if(line!= "") {res.add(line);line="";}
            	String[] results = w.split("(?<=\\G.{"+lineSize+"})");
            	for(String s:results){
            		if (s.length()==lineSize) {
            			res.add(s);
            			//Log.logDefault("["+s+"]"+"1");
            		}else {
            			line = s;
                		//Log.logDefault("["+line+"]"+"2");
            		}
            	}
            	done=true;
            }
            if(w.length() == lineSize&&done==false){
            	if(line!="") {
            		res.add(line);
        			//Log.logDefault("["+line+"]"+"3");
            		line="";
        			res.add(w);
        			//Log.logDefault("["+w+"]"+"4");
            	}else {
            		res.add(w);
        			//Log.logDefault("["+w+"]"+"5");
            	}
            	done=true;
            }
            if(w.length() < lineSize&&done==false){
            	if(line!="") {
            		String tmp= line+" "+w;
            		if(tmp.length()>lineSize) {
            			res.add(line);
            			//Log.logDefault("["+line+"]"+"6");
            			line="";
            			line=w;
            			//Log.logDefault("["+line+"]"+"7");
            		}
            		if(tmp.length()==lineSize){
            			res.add(tmp);
            			//Log.logDefault("["+tmp+"]"+"8");
            			line="";
            		}
            		if(tmp.length()<lineSize){
            			line= tmp;
            			//Log.logDefault("["+line+"]"+"9");
            		}
            	}else {
            		line=w;
            		//Log.logDefault("["+w+"]"+"10");
            	}
            	done=true;
            }
        }
        if(line!="") {res.add(line);
		//Log.logDefault("["+line+"]"+"11");
        line="";}
        return res;
    }

}
