package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.SelectableChannel;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.group.interceptors.FragmentationInterceptorMBean;

import com.google.gson.Gson;
import com.iss.util.DBUtil;

/**
 * Servlet implementation class NewPwdServlet
 */
@WebServlet("/NewPwdServlet")
public class NewPwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPwdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		String username="";
		String password="";
		String m="";
		String newpwd="";
		if(request.getParameter("username")!=null&&request.getParameter("password")!=null){
		
			username=request.getParameter("username");
			password=request.getParameter("password");
			newpwd=request.getParameter("newpwd");
		}
		System.out.println(username);
		System.out.println(password);
		System.out.println(newpwd);
			try {
				    String sql="Select * from  t_sys_user where t_user_name='"+username+"' and t_user_pwd='"+password+"'  ";
					String sql1="UPDATE t_sys_user  SET  t_user_pwd='"+newpwd+"'  WHERE t_user_name='"+username+"' ";
					Statement statement=DBUtil.getConnection().createStatement();
					ResultSet rs=statement.executeQuery(sql);
					
					if(rs.next()) {
						m=rs.getString(3);
						Statement s=DBUtil.getConnection().createStatement();
						s.execute(sql1);

					}
			} catch (Exception e) {
			// TODO: handle exception
				e.printStackTrace();
			}
			out.print(m);
			out.flush();
			out.close();
		
	}

}
