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
 * Servlet implementation class PersonServlet
 */
@WebServlet("/PersonServlet")
public class PersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PersonServlet() {
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
		List list1=new ArrayList<>();
		String username="";
		if(request.getParameter("username")!=null){
			username=request.getParameter("username");
		}
		String id="";
			System.out.println(username);
			try {
			Statement statement=DBUtil.getConnection().createStatement();
			String sql2="SELECT * FROM `t_sys_user` where t_user_name='"+username+"'  " ;
			
			ResultSet resultSet2=statement.executeQuery(sql2);
			if(resultSet2.next())
				id=resultSet2.getString(1);
			//System.out.println(id);
			
			String sql1="SELECT `t_sys_user`.* , `t_person`.*  FROM `t_sys_user`,`t_person` WHERE `t_sys_user`.`t_user_id`=`t_person`.`t_user_id` AND `t_sys_user`.t_user_id='"+id+"' ";
				
			ResultSet resultSet1=statement.executeQuery(sql1);
			
			t_sys_user user=null;
			t_person person=null;
			PersonUser personUser=null;
			if(resultSet1.next()){
				personUser=new PersonUser();
				personUser.setT_user_id(resultSet1.getString(1));
				personUser.setT_user_name(resultSet1.getString(2));
				personUser.setT_role(resultSet1.getString(4));		
				personUser.setT_status(resultSet1.getString(5));
				
				//personUser.setT_preson_id(resultSet1.getString(6));
				//personUser.setT_dic_id(resultSet1.getString(8));
				personUser.setT_gender(resultSet1.getString(9));
				personUser.setT_mobile(resultSet1.getString(10));
				personUser.setT_email(resultSet1.getString(11));
				personUser.setT_cname(resultSet1.getString(12));	
			}
			list1.add(personUser);
		} catch (Exception e) {
			// TODO: handle exception
		}
		//System.out.println(list1);
			out.print(gson.toJson(list1));
			out.flush();
			out.close();
	}

}
