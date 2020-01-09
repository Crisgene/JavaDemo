package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.iss.po.flightInfo;
import com.iss.util.DBUtil;

/**
 * Servlet implementation class flightPlanServlet
 */
@WebServlet("/flightPlanServlet")
public class flightPlanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public flightPlanServlet() {
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
		String t_ad="";
		String t_area="" ;
		String t_takeoff_date="";
		String t_dflight_num="" ;
		String t_plane_num="";
		String t_plane_type="" ;
		String t_plane_hjl="";
		String t_airline="";
		
		
		
		List list=new ArrayList<>();
		
		int total=0;
		try {
			int page=Integer.parseInt(request.getParameter("page"));
			int pageSize=Integer.parseInt(request.getParameter("pageSize"));		
			
			
			
			
			if(request.getParameter("t_ad")!=null) {
				t_ad=request.getParameter("t_ad");
			}
			if(request.getParameter("t_area")!=null) {
				t_area=request.getParameter("t_area");
			}
			if (request.getParameter("t_takeoff_date")!= null) {
				t_takeoff_date=request.getParameter("t_takeoff_date");
			}
			if (request.getParameter("t_dflight_num")!= null) {
				t_dflight_num=request.getParameter("t_dflight_num");
			}
			if (request.getParameter("t_plane_num")!= null) {
				t_plane_num=request.getParameter("t_plane_num");
			}
			if(request.getParameter("t_plane_type")!=null) {
				t_plane_type=request.getParameter("t_plane_type");
			}
			if (request.getParameter("t_plane_hjl")!= null) {
				t_plane_hjl=request.getParameter("t_plane_hjl");
			}
			if (request.getParameter("t_airline")!= null) {
				t_airline=request.getParameter("t_airline");
			}
			
			
			
			
			
			
			
			
			
			String sql="SELECT * FROM t_flightinfo where t_area like '%"+t_area+"%' and t_ad like '%"+t_ad+"%' and t_plane_hjl like '%"+t_plane_hjl+"%' and t_plane_num like '%"+t_plane_num+"%' and t_dflight_num like '%"+t_dflight_num+"%' and t_plane_type like '%"+t_plane_type+"%' and t_airline like'%"+t_airline+"%' and t_takeoff_date like '%"+t_takeoff_date+"%' limit "+((page*pageSize))+","+pageSize;
			Statement stmt=DBUtil.getConnection().createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			System.out.println(sql);
			flightInfo f=null;			
					
			while(rs.next()) {
				f=new flightInfo();
				f.setT_task(rs.getString(4));
				f.setT_ad(rs.getString(5));
				f.setT_area(rs.getString(6));
				f.setT_takeoff_date(rs.getString(7));
				f.setT_dflight_num(rs.getString(9));
				f.setT_plane_num(rs.getString(10));
				f.setT_plane_type(rs.getString(11));
				f.setT_plane_jw(rs.getString(12));
				f.setT_plane_hjl(rs.getString(13));
				f.setT_airline(rs.getString(14));
				
				list.add(f);						
			}
			rs.close();
			
			
			String sql1="SELECT count(*) FROM t_flightinfo where t_area like '%"+t_area+"%' and t_ad like '%"+t_ad+"%' and t_plane_hjl like '%"+t_plane_hjl+"%' and t_plane_num like '%"+t_plane_num+"%' and t_dflight_num like '%"+t_dflight_num+"%' and t_plane_type like '%"+t_plane_type+"%' and t_airline like'%"+t_airline+"%' and t_takeoff_date like '%"+t_takeoff_date+"%'";
			Statement stmt1=DBUtil.getConnection().createStatement();					
			
			System.out.println(sql1);
			
			ResultSet rs1=stmt1.executeQuery(sql1);
			
			if(rs1.next()) {
				total=rs1.getInt(1);
			}
			
			rs1.close();	
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map map=new HashMap<>();
		
		
		map.put("total", total);
		map.put("rows", list);	
		
		
		out.print(gson.toJson(map));
		out.flush();
		out.close();	
	}

}
