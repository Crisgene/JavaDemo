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
import com.iss.po.t_sys_user;
import com.iss.util.DBUtil;

/**
 * Servlet implementation class SysuserServlet
 */
@WebServlet("/SysuserServlet")
public class SysuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SysuserServlet() {
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
		List list2=new ArrayList<>();
		String username="";
		if(request.getParameter("t_user_name")!=null){
			username=request.getParameter("t_user_name");
			//System.out.println(username);
		
			t_sys_user user=null;
			try {
				String sql2="SELECT * FROM `t_sys_user`  where t_user_name= '"+username+"' " ;
				//System.out.println(sql2);
				Statement statement=DBUtil.getConnection().createStatement();
				ResultSet resultSet2=statement.executeQuery(sql2);
				while(resultSet2.next()){
					//System.out.println(resultSet2.getString(1));
					user=new t_sys_user();
					user.setT_user_id(resultSet2.getString(1));
					user.setT_user_name(resultSet2.getString(2));
					//user.setT_user_pwd(resultSet2.getString(3));
					user.setT_role(resultSet2.getString(4));		
					user.setT_status(resultSet2.getString(5));
					list2.add(user);
				}
				
				//while(resultSet2.next()){}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		}
		out.print(gson.toJson(list2));
		out.flush();
		out.close();
	}

}
