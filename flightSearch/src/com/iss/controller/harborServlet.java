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
 * Servlet implementation class harborServlet
 */
@WebServlet("/harborServlet")
public class harborServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public harborServlet() {
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
		
		Gson gson=new Gson();
		PrintWriter out=response.getWriter();
		List list=new ArrayList<>();
		try {
			
			String sql="SELECT DISTINCT(t_ad) FROM t_flightinfo";
			Statement stmt=DBUtil.getConnection().createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while(rs.next()) {
				list.add(rs.getString(1));
			}
			rs.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.print(gson.toJson(list));
		out.flush();
		out.close();	
	}

	}

