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
import com.iss.po.Code;
import com.iss.util.DBUtil;

/**
 * Servlet implementation class sCodeServlet
 */
@WebServlet("/sCodeServlet")
public class sCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sCodeServlet() {
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
		
		
		Gson gson=new Gson();
		PrintWriter out=response.getWriter();
		List list=new ArrayList<>();
		Code c=null;
		try {
			String sql="SELECT DISTINCT(t_type) FROM  t_dic_code\r\n" + 
					"";
			Statement stmt=DBUtil.getConnection().createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				c=new Code();
				c.setT_dic_code(rs.getString(2));
				c.setT_name(rs.getString(3));
				c.setT_type(rs.getString(4));
				list.add(c);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(gson.toJson(list));
		out.flush();
		out.close();
		
	}

}
