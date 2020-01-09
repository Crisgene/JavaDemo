package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;

import com.google.gson.Gson;
import com.iss.po.packinfo;
import com.iss.util.DBUtil;

/**
 * Servlet implementation class PackServlet
 */
@WebServlet("/PackServlet")
public class PackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PackServlet() {
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
		String t_flid="";
		String t_pan_code="";
		String t_pan_area="";
		String t_name="";
		String t_dic_code="";
		int total=0;
		try {
			int page=Integer.parseInt(request.getParameter("page"));
			int pageSize=Integer.parseInt(request.getParameter("pageSize"));
			
			
			if(request.getParameter("t_flid")!=null) {
				t_flid=request.getParameter("t_flid");
			}
			if(request.getParameter("t_pan_code")!=null) {
				t_pan_code=request.getParameter("t_pan_code");
			}
			if(request.getParameter("t_pan_area")!=null) {
				t_pan_area=request.getParameter("t_pan_area");
			}
			
		
			String sql="select * from t_pack_info JOIN t_dic_code where t_pan_area = t_dic_code and t_flid like '%"+t_flid+"%' and t_pan_code like '%"+t_pan_code+"%' and t_name like '%"+t_pan_area+"%' limit "+(page*pageSize)+","+pageSize;                                
			
			Statement cmd=DBUtil.getConnection().createStatement();
			ResultSet rs=cmd.executeQuery(sql);
			
			packinfo p=null;
			
			while(rs.next()) {
				
				p=new packinfo();
				p.setT_pack_id(rs.getString(1));
				p.setT_flid(rs.getString(2));
				p.setT_pan_code(rs.getString(3));
				p.setT_pan_area(rs.getString(12));
				p.setT_plan_start_time(rs.getString(5));
				p.setT_plan_end_time(rs.getString(6));
				p.setT_hzl(rs.getString(9));
				
				list.add(p);
				
			}
			
			String sql1="select count(*) from t_pack_info JOIN t_dic_code where t_pan_area = t_dic_code and t_flid like '%"+t_flid+"%' and t_pan_code like '%"+t_pan_code+"%' and t_name like '%"+t_pan_area+"%' ";                                
			Statement cmd1=DBUtil.getConnection().createStatement();
			ResultSet rs1=cmd.executeQuery(sql1);
			while(rs1.next()) {
				total=rs1.getInt(1);
				}
		}catch(Exception e) {
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
