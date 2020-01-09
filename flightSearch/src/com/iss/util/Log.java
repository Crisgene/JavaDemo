package com.iss.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {
	
	public static void Logs(String methodName,String ClassName,
			String currentUser,String sql,String exceptionMsg) {
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time=sdf.format(new Date());
			
			BufferedWriter bw=new BufferedWriter(new FileWriter(new File("D:/logs.txt")));
			bw.write("CurrentTime:"+time+"\tErrorMsg:"+exceptionMsg);
			bw.flush();
			bw.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
