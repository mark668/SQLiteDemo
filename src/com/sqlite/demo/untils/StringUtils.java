package com.sqlite.demo.untils;

public class StringUtils {

	
	public static String cutString(String content, int cutNum) {
		
		if(content == null || content.length()==0) {
			return "";
		}
		if(content.length()>=cutNum) {
			
			return content.substring(0, cutNum)+"...";
  		}else {
			return content;
		}
		
	}
	public static String cutString2(String content, int cutNum) {
		
		if(content == null || content.length()==0) {
			return "";
		}
		if(content.length()>=cutNum) {
			
			return content.substring(0, cutNum)+"";
		}else {
			return content;
		}
		
	}
}
