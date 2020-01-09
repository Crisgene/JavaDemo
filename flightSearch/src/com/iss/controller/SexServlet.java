package com.iss.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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

import java.sql.*;

/**
 * Servlet implementation class SexServlet
 */
@WebServlet("/SexServlet")
public class SexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SexServlet() {
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
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		List list=new ArrayList<>();
		String id=""; //用来存储user_id
		String username="";
		String email="";
		String phone="";
		String sex="";
		if (request.getParameter("t_email")!=null) {
			email=request.getParameter("t_email");
		}
		if (request.getParameter("phone")!=null) {
			phone=request.getParameter("phone");
		}
		if (request.getParameter("sex")!=null) {
			sex=request.getParameter("sex");
		}
		
		try {
			String sql="SELECT DISTINCT(t_gender) FROM `t_person`";
			Statement statement=DBUtil.getConnection().createStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			while(resultSet.next()){
				list.add(resultSet.getString(1));
			}
			if(request.getParameter("t_user_name")!=null){
				username=request.getParameter("t_user_name");
			String sql2="SELECT * FROM `t_sys_user` where t_user_name='"+username+"'  " ;
			
			ResultSet resultSet2=statement.executeQuery(sql2);
			if(resultSet2.next())
				id=resultSet2.getString(1);//得到用户名后，通过t_sys_user表获取t_user_id
			String sql3="UPDATE `t_person` SET  t_mobile='"+phone+"' WHERE t_user_id='"+id+"'";
			String sql4="UPDATE `t_person` SET  t_email='"+email+"' WHERE t_user_id='"+id+"'";
			String sql5="UPDATE `t_person` SET  t_gender='"+sex+"' WHERE t_user_id='"+id+"'";
			statement.execute(sql3);
			statement.execute(sql4);
			statement.execute(sql5);
			
				
			}
		
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		out.print(gson.toJson(list));
		out.flush();
		out.close();
	}

}
