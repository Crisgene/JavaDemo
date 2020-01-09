package com.iss.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.iss.util.PropertiesUtil;
import com.mysql.jdbc.interceptors.ServerStatusDiffInterceptor;

public class DBUtil {
	
	private static Connection con=null;
	private static String driverName=PropertiesUtil.getValue("driverName");
	private static String url=PropertiesUtil.getValue("url");
	private static String user=PropertiesUtil.getValue("username");	
	private static String password=PropertiesUtil.getValue("userpassword");
	
	
	public static Connection getConnection() {
		try {
			if (con==null) {
				Class.forName(driverName);
				con=DriverManager.getConnection(url, user, password);
			}
		} catch (Exception e) {
			con=null;
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection() throws SQLException {
		try {
			if (con!=null) {
				con.close();
			}
		} catch (Exception e) {
			con.close();
		}
	}
}
