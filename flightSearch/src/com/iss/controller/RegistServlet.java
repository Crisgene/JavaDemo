package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.iss.po.t_sys_user;
import com.iss.util.DBUtil;
import java.util.*;
import java.util.UUID;
import java.sql.*;

/**
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistServlet() {
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
		List list=new ArrayList<>();
		String username="";
		String password="";
		String phone="";
		String role="∆’Õ®”√ªß";
		String uid = UUID.randomUUID().toString().replaceAll("-", "");		
		String pid = UUID.randomUUID().toString().replaceAll("-", "");	
		String did="";
		String gender="";
		String mail="";
		String cname="";
		
		if(request.getParameter("t_user_name")!=null&&request.getParameter("t_user_pwd")!=null
				){
	
			username=request.getParameter("t_user_name");	
			password=request.getParameter("t_user_pwd");
			phone=request.getParameter("phone");
			//role=request.getParameter("t_role");		
		try {
				String sql1="INSERT  INTO `t_sys_user`  VALUES ('"+uid+"','"+username+"','"+password+"','"+role+"','Œ¥…Û∫À')";
				String sql2="INSERT  INTO `t_person`   VALUES ('"+pid+"','"+uid+"','"+did+"','"+gender+"','"+phone+"','"+mail+"','"+cname+"')";
				//System.out.println(sql1);
				Statement statement=DBUtil.getConnection().createStatement();
			
				statement.execute(sql1);
				statement.execute(sql2);
				
//			 statement.close();
//			DBUtil.getConnection().close();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		
		
		
	}

}
