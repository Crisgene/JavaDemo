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
import com.iss.po.zhijiInfo;
import com.iss.util.DBUtil;


/**
 * Servlet implementation class ZhiJiServlet
 */
@WebServlet("/ZhiJiServlet")
public class ZhiJiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZhiJiServlet() {
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
		List list =new ArrayList<>();
		String  t_flid="";	
		String t_plane_num=""; 
	    String t_startDate="";
		
		int total=0;
		
		try {
			int page=Integer.parseInt(request.getParameter("page"));
			int pageSize=Integer.parseInt(request.getParameter("pageSize"));
						
			if(request.getParameter("t_flid")!=null) {
				t_flid=request.getParameter("t_flid");
			}
			if(request.getParameter("t_plane_num")!=null) {
				t_plane_num=request.getParameter("t_plane_num");
			}
			
			if(request.getParameter("t_startDate")!=null) {
				t_startDate=request.getParameter("t_startDate");
			}
			
			
			String sql="SELECT * FROM t_zhiji_start JOIN t_dic_code  where t_company=t_dic_code and t_flid like '%"+t_flid+"%' and t_plane_num like '%"+t_plane_num+"%'  and t_startDate like '%"+t_startDate +"%' limit "+((page*pageSize))+","+pageSize;
			
			
			Statement stmt=DBUtil.getConnection().createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			
			
			zhijiInfo z=null;
			
			while (rs.next()) {
				z=new zhijiInfo();
				z.setT_flid(rs.getString(2));
				z.setT_plane_num(rs.getString(3));
				z.setT_startDate(rs.getString(4));
				if(rs.getString(6).equals("2401")) {
					z.setT_area("国际");
				}
				else if(rs.getString(6).equals("2402")) {
					z.setT_area("地区");
				}
				else if(rs.getString(6).equals("2403")) {
					z.setT_area("国内");
				}
				else if(rs.getString(6).equals("2404")) {
					z.setT_area("混合");
				}
				else {
					z.setT_area("未知");
				}
				//z.setT_area(rs.getString(6));
				if(rs.getString(7).equals("D"))
				{
					z.setT_ad("出港");
				}
				z.setT_company(rs.getString(11));					
				list.add(z);
			}
		
			//rs.close();
			
			String sql1="SELECT COUNT(*) FROM t_zhiji_start JOIN t_dic_code where  t_company=t_dic_code and t_flid like '%"+t_flid+"%' and t_plane_num like '%"+t_plane_num+"%' and t_startDate like '%"+t_startDate+"%'  ";
			
			Statement stmt1=DBUtil.getConnection().createStatement();
			ResultSet rs1=stmt1.executeQuery(sql1);
			
			while(rs1.next()) {
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
		
		/*out.print(gson.toJson(list));
		out.flush();
		out.close();	*/
	}

}
