package com.iss.util;
import java.util.Properties;
import java.io.*;


public class PropertiesUtil {
	public static String getValue(String key) {
		String str="";
		try {
			Properties prop=new Properties();
			InputStream in=new PropertiesUtil().getClass()
					.getResourceAsStream("myconfig.properties");
			prop.load(in);
			str=(String)prop.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
