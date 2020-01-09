package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iss.util.DBUtil;

/**
 * Servlet implementation class UpdateInfo1
 */
@WebServlet("/UpdateInfo1")
public class UpdateInfo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfo1() {
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
		
		
		if(request.getParameter("userid")!=null){
			userid=request.getParameter("userid");
		}
			System.out.println(usercname);
			System.out.println(email);
			System.out.println(userphone);
			System.out.println(sex);
			System.out.println(userid);
			//String sql="UPDATE t_sys_user SET t_role='"+useridentity+"',t_status='"+userstate+"' WHERE t_user_name='"+username+"' ";
			String sql1="UPDATE t_person SET t_gender='"+sex+"',t_mobile='"+userphone+"',t_cname='"+usercname+"',t_email='"+email+"' WHERE t_user_id='"+userid+"' ";
			
			try {
				Statement statement=DBUtil.getConnection().createStatement();
				statement.execute(sql1);			

				System.out.println(sql1);
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
