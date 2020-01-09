package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.iss.po.PersonUser;
import com.iss.po.t_person;
import com.iss.po.t_sys_user;
import com.iss.util.DBUtil;

/**
 * Servlet implementation class UpdateInfo
 */
@WebServlet("/UpdateInfo")
public class UpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String useridentity="";
		String userstate="";
		String username="";
		String usercname="";
		String email="";
		String userphone="";
		String sex="";
		String userid="";
		if(request.getParameter("usercname")!=null){
			usercname=request.getParameter("usercname");
		}
		
		if(request.getParameter("email")!=null){
			email=request.getParameter("email");
		}
		
		if(request.getParameter("userphone")!=null){
			userphone=request.getParameter("userphone");
		}
		
		if(request.getParameter("sex")!=null){
			sex=request.getParameter("sex");
		}
		
		
		if(request.getParameter("username")!=null){
			username=request.getParameter("username");
		}
		
		
		if(request.getParameter("useridentity")!=null){
			useridentity=request.getParameter("useridentity");
		}
		
		if(request.getParameter("userstate")!=null){
			userstate=request.getParameter("userstate");
		}
			System.out.println(useridentity);
			System.out.println(username);
			System.out.println(userstate);
			String sql="UPDATE t_sys_user SET t_role='"+useridentity+"',t_status='"+userstate+"' WHERE t_user_name='"+username+"' ";
			String sql1="UPDATE t_person SET t_gender='"+sex+"',t_mobile='"+userphone+"',t_cname='"+usercname+"',t_email='"+email+"' WHERE t_user_id='"+userid+"' ";
			
			try {
				Statement statement=DBUtil.getConnection().createStatement();
				statement.execute(sql);			

				System.out.println(sql);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
