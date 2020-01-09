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
 * Servlet implementation class routeSearch
 */
@WebServlet("/routeSearch")
public class routeSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public routeSearch() {
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
		Gson gson=new Gson();
		String sql="";
		String type=request.getParameter("T");
		List list=new ArrayList<>();
		try {
			if(type.equals("a")) {
				sql="SELECT DISTINCT(t_plane_hjl) FROM t_flightinfo";
			}
			else if (type.equals("b")) {
				sql="SELECT DISTINCT(t_plane_hjl) FROM t_flightinfo";
			}			
			//String sql="SELECT DISTINCT(t_plane_hjl) FROM t_flightinfo";
			Statement statement=DBUtil.getConnection().createStatement();
			ResultSet rSet=statement.executeQuery(sql);
			while(rSet.next()) {
				list.add(rSet.getString(1));
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
