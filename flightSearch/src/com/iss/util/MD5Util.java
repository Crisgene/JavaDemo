package com.iss.util;

import java.security.MessageDigest;
import java.util.Base64;
public class MD5Util {

	public static String getKillCode(String str) {
		String res="";
		try {
			MessageDigest md5=MessageDigest.getInstance("MD5");
			res = Base64.getEncoder().encodeToString(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
