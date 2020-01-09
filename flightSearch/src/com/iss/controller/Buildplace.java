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
import com.iss.util.DBUtil;

/**
 * Servlet implementation class Buildplace
 */
@WebServlet("/Buildplace")
public class Buildplace extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Buildplace() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
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
		try {
			String sql="SELECT DISTINCT(t_pan_area) FROM t_pack_info WHERE t_pan_area!=''";
			Statement statement=DBUtil.getConnection().createStatement();
			ResultSet rSet=statement.executeQuery(sql);
			while(rSet.next())
			{
			    System.out.println(rSet.getString(1));
				String sql1="SELECT * FROM t_dic_code WHERE t_dic_code = "+rSet.getString(1)+"";
				Statement stmt1=DBUtil.getConnection().createStatement();
				ResultSet rs1=stmt1.executeQuery(sql1);
				if(rs1.next())
				{
					System.out.println(rSet.getString(1));
					System.out.println(rs1.getString(3));
				    list.add(rs1.getString(3));
				}
			}
			rSet.close();		
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		
		
	}

}
