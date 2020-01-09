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
import com.iss.po.Code;
import com.iss.po.Luggageinfo;
import com.iss.util.DBUtil;


/**
 * Servlet implementation class Luggage
 */
@WebServlet("/Luggage")
public class Luggage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Luggage() {
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
		//doGet(request, response);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		Gson gson=new Gson();
		PrintWriter out=response.getWriter();
		String t_fnum="";
		String t_znum="";
		String t_area="" ;
		String t_start_time="";
		String t_end_time="" ;
		String t_plane_hjl="";
		
		
		List list=new ArrayList<>();
		
		int total=0;
		try {
			
			if(request.getParameter("page")!=null&&request.getParameter("pageSize")!=null)
			{
				
				int page=Integer.parseInt(request.getParameter("page"));
				int pageSize=Integer.parseInt(request.getParameter("pageSize"));	
				System.out.println(request.getParameter("t_fnum"));
				System.out.println(request.getParameter("t_znum"));
				System.out.println(request.getParameter("t_area"));
				if(request.getParameter("t_fnum") !=""&&request.getParameter("t_fnum") !=null)
				{
					t_fnum=request.getParameter("t_fnum");
					if(request.getParameter("t_znum")!=""&&request.getParameter("t_znum")!=null)
					{
						t_znum=request.getParameter("t_znum");
						if(request.getParameter("t_area")!=""&&request.getParameter("t_area")!=null)
						{
							t_area=request.getParameter("t_area");
							String sql1="SELECT * FROM t_dic_code WHERE t_name = '"+t_area+"' and t_type = '航班属性'";
							Statement stmt1=DBUtil.getConnection().createStatement();
							ResultSet rs1=stmt1.executeQuery(sql1);
							if(rs1.next())
							{
								t_area=rs1.getString(2);
								if(t_area.equals("01cf697c-b388-464a-86ea-4f82d7480746"))
								{
									t_area="2401";
								}
								else if (t_area.equals("5708d46f-d974-46c6-917d-64a9b6e79817")) 
								{
									t_area="2403";
								}
								
							}
							String sql="SELECT * FROM t_pack_info WHERE t_flid like '%"+t_fnum+"%' AND t_pan_code = "+t_znum+" AND t_pan_area = "+t_area+" limit "+(page*pageSize)+","+pageSize+"";
							Statement stmt=DBUtil.getConnection().createStatement();
							ResultSet rs=stmt.executeQuery(sql);
							Luggageinfo f=null;			
									
							while(rs.next()) {
								String sql2="SELECT * FROM t_dic_code WHERE t_dic_code = "+rs.getString(4)+"";
							    Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								if(rs2.next())
								{	
								//System.out.println(rs2.getString(3));
								f=new Luggageinfo();
								f.setT_flid(rs.getString(2));
								f.setT_pan_code(rs.getString(3));
								f.setT_pan_area(rs2.getString(3));
								f.setT_hz1(rs.getString(9));
								f.setT_real_start_time(rs.getString(7));
								f.setT_real_end_time(rs.getString(8));
								list.add(f);	
								}
							
							}
							rs.close();
							
							
							sql1="SELECT COUNT(*) FROM t_pack_info WHERE t_flid like '%"+t_fnum+"%' AND t_pan_code = "+t_znum+" AND t_pan_area = "+t_area+"";
							stmt1=DBUtil.getConnection().createStatement();					
							rs1=stmt1.executeQuery(sql1);
							
							if(rs1.next()) {
								total=rs1.getInt(1);
							}
							
							rs1.close();	
							
						}
						else
						{
							String sql="SELECT * FROM t_pack_info where t_flid like '%"+t_fnum+"%' and t_pan_code="+t_znum+" limit "+(page*pageSize)+","+pageSize;
							Statement stmt=DBUtil.getConnection().createStatement();
							ResultSet rs=stmt.executeQuery(sql);
							Luggageinfo f=null;			
									
							while(rs.next()) {
								String sql2="SELECT * FROM t_dic_code WHERE t_dic_code = "+rs.getString(4)+"";
							    Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								if(rs2.next())
								{	
								//System.out.println(rs2.getString(3));
								f=new Luggageinfo();
								f.setT_flid(rs.getString(2));
								f.setT_pan_code(rs.getString(3));
								f.setT_pan_area(rs2.getString(3));
								f.setT_hz1(rs.getString(9));
								f.setT_real_start_time(rs.getString(7));
								f.setT_real_end_time(rs.getString(8));
								list.add(f);	
								}				
							}
							rs.close();
							
							
							String sql1="SELECT COUNT(*) FROM t_pack_info where t_flid like '%"+t_fnum+"%' and t_pan_code="+t_znum+"";
							Statement stmt1=DBUtil.getConnection().createStatement();					
					     	ResultSet rs1=stmt1.executeQuery(sql1);
							
							if(rs1.next()) {
								total=rs1.getInt(1);
							}
							
							rs1.close();	
						}
					}
					else
					{
						if(request.getParameter("t_area")!=""&&request.getParameter("t_area")!=null)
						{
							t_area=request.getParameter("t_area");
							String sql1="SELECT * FROM t_dic_code WHERE t_name = '"+t_area+"' and t_type = '航班属性'";
							Statement stmt1=DBUtil.getConnection().createStatement();
							ResultSet rs1=stmt1.executeQuery(sql1);
							if(rs1.next())
							{
								t_area=rs1.getString(2);
							}
							String sql="SELECT * FROM t_pack_info where t_flid like '%"+t_fnum+"%' and t_pan_area="+t_area+" limit "+(page*pageSize)+","+pageSize+"";
							Statement stmt=DBUtil.getConnection().createStatement();
							ResultSet rs=stmt.executeQuery(sql);
							Luggageinfo f=null;			
									
							while(rs.next()) {
								String sql2="SELECT * FROM t_dic_code WHERE t_dic_code = "+rs.getString(4)+"";
							    Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								if(rs2.next())
								{	
								//System.out.println(rs2.getString(3));
								f=new Luggageinfo();
								f.setT_flid(rs.getString(2));
								f.setT_pan_code(rs.getString(3));
								f.setT_pan_area(rs2.getString(3));
								f.setT_hz1(rs.getString(9));
								f.setT_real_start_time(rs.getString(7));
								f.setT_real_end_time(rs.getString(8));
								list.add(f);	
								}				
							}
							rs.close();
							
							
							sql1="SELECT COUNT(*) FROM t_pack_info where t_flid like '%"+t_fnum+"%' and t_pan_area = "+t_area+"";
							stmt1=DBUtil.getConnection().createStatement();					
							rs1=stmt1.executeQuery(sql1);
							
							if(rs1.next()) {
								total=rs1.getInt(1);
							}
							
							rs1.close();	
							
						}
						else
						{
							String sql="SELECT * FROM t_pack_info where t_flid like '%"+t_fnum+"%' limit "+(page*pageSize)+","+pageSize;
							Statement stmt=DBUtil.getConnection().createStatement();
							ResultSet rs=stmt.executeQuery(sql);
							Luggageinfo f=null;			
									
							while(rs.next()) {
								String sql2="SELECT * FROM t_dic_code WHERE t_dic_code = "+rs.getString(4)+"";
							    Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								if(rs2.next())
								{	
								//System.out.println(rs2.getString(3));
								f=new Luggageinfo();
								f.setT_flid(rs.getString(2));
								f.setT_pan_code(rs.getString(3));
								f.setT_pan_area(rs2.getString(3));
								f.setT_hz1(rs.getString(9));
								f.setT_real_start_time(rs.getString(7));
								f.setT_real_end_time(rs.getString(8));
								list.add(f);	
								}				
							}
							rs.close();
							
							
							String sql1="SELECT COUNT(*) FROM t_pack_info where t_flidlike '%"+t_fnum+"%'";
							Statement stmt1=DBUtil.getConnection().createStatement();					
							ResultSet rs1=stmt1.executeQuery(sql1);
							
							if(rs1.next()) {
								total=rs1.getInt(1);
							}
							
							rs1.close();	
							
						}
						
					}
				}
				else
				{
					//System.out.println("aaa");
					if(request.getParameter("t_znum")!=""&&request.getParameter("t_znum")!=null)
					{
						//System.out.println("bbb");
						t_znum=request.getParameter("t_znum");
						if(request.getParameter("t_area")!=""&&request.getParameter("t_area")!=null)
						{
							//System.out.println("ccc");
							t_area=request.getParameter("t_area");
							String sql1="SELECT * FROM t_dic_code WHERE t_name = '"+t_area+"' and t_type = '航班属性'";
							Statement stmt1=DBUtil.getConnection().createStatement();
							ResultSet rs1=stmt1.executeQuery(sql1);
							if(rs1.next())
							{
								if(rs1.getString(2).equals("5708d46f-d974-46c6-917d-64a9b6e79817"))
								{
									t_area="2403";
								}
								else if (rs1.getString(2).equals("01cf697c-b388-464a-86ea-4f82d7480746"))
								{
									t_area="2401";
								}
								else 
								{
									t_area=rs1.getString(2);
								}
							}
							t_area=rs1.getString(2);
							String sql="SELECT * FROM t_pack_info where t_pan_code = "+t_znum+" and t_pan_area = "+t_area+" limit "+(page*pageSize)+","+pageSize+"";
						    Statement stmt=DBUtil.getConnection().createStatement();
							ResultSet rs=stmt.executeQuery(sql);
							Luggageinfo f=null;			
												
							while(rs.next()) 
							{
								String sql2="SELECT * FROM t_dic_code WHERE t_dic_code = "+rs.getString(4)+"";
								Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								if(rs2.next())
								{	
											//System.out.println(rs2.getString(3));
									f=new Luggageinfo();
									f.setT_flid(rs.getString(2));
									f.setT_pan_code(rs.getString(3));
									f.setT_pan_area(rs2.getString(3));
									f.setT_hz1(rs.getString(9));
									f.setT_real_start_time(rs.getString(7));
									f.setT_real_end_time(rs.getString(8));
									list.add(f);	
								}				
							 }
							rs.close();					
							sql1="SELECT COUNT(*) FROM t_pack_info where t_pan_code = "+t_znum+" and t_pan_area = "+t_area+"";
							stmt1=DBUtil.getConnection().createStatement();					
							rs1=stmt1.executeQuery(sql1);
								
						    if(rs1.next()) 
							{
									 total=rs1.getInt(1);
							}
								
								 rs1.close();	
					    }
						else 
						{
							String sql="SELECT * FROM t_pack_info where t_pan_code ="+t_znum+" limit "+(page*pageSize)+","+pageSize;
							Statement stmt=DBUtil.getConnection().createStatement();
							ResultSet rs=stmt.executeQuery(sql);
							Luggageinfo f=null;			
									
							while(rs.next()) {
								String sql2="SELECT * FROM t_dic_code WHERE t_dic_code = "+rs.getString(4)+"";
							    Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								if(rs2.next())
								{	
								//System.out.println(rs2.getString(3));
								f=new Luggageinfo();
								f.setT_flid(rs.getString(2));
								f.setT_pan_code(rs.getString(3));
								f.setT_pan_area(rs2.getString(3));
								f.setT_hz1(rs.getString(9));
								f.setT_real_start_time(rs.getString(7));
								f.setT_real_end_time(rs.getString(8));
								list.add(f);	
								}			
							}
							rs.close();
							
							
							String sql1="SELECT COUNT(*) FROM t_pack_info where t_pan_code ="+t_znum+"";
							Statement stmt1=DBUtil.getConnection().createStatement();					
							
							//System.out.println(sql1);
							
							ResultSet rs1=stmt1.executeQuery(sql1);
							
							if(rs1.next()) {
								total=rs1.getInt(1);
							}
							
							rs1.close();	
						}
					}
					else 
					{
				
						if(request.getParameter("t_area")!=""&&request.getParameter("t_area")!=null)
						{
							//System.out.println("eee");
							t_area=request.getParameter("t_area");
							String sql1="SELECT * FROM t_dic_code WHERE t_name = '"+t_area+"' and t_type = '航班属性'";
							Statement stmt1=DBUtil.getConnection().createStatement();
							ResultSet rs1=stmt1.executeQuery(sql1);
							if(rs1.next())
							{
								if(rs1.getString(2).equals("5708d46f-d974-46c6-917d-64a9b6e79817"))
								{
									t_area="2403";
								}
								else if (rs1.getString(2).equals("01cf697c-b388-464a-86ea-4f82d7480746"))
								{
									t_area="2401";
								}
								else 
								{
									t_area=rs1.getString(2);
								}
							}
							//System.out.println(t_area);
							String sql="SELECT * FROM t_pack_info where t_pan_area = "+t_area+" limit "+(page*pageSize)+","+pageSize+"";
							Statement stmt=DBUtil.getConnection().createStatement();
							ResultSet rs=stmt.executeQuery(sql);
							Luggageinfo f=null;					
							while(rs.next()) {
								//System.out.println(rs.getString(3));
								String sql2="SELECT * FROM t_dic_code WHERE t_dic_code = "+rs.getString(4)+"";
							    Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								if(rs2.next())
								{	
								//System.out.println(rs2.getString(3));
								f=new Luggageinfo();
								f.setT_flid(rs.getString(2));
								f.setT_pan_code(rs.getString(3));
								f.setT_pan_area(rs2.getString(3));
								f.setT_hz1(rs.getString(9));
								f.setT_real_start_time(rs.getString(7));
								f.setT_real_end_time(rs.getString(8));
								list.add(f);	
								}				
							}
							rs.close();
							
							
							sql1="SELECT COUNT(*) FROM t_pack_info where t_pan_area = "+t_area+"";
							stmt1=DBUtil.getConnection().createStatement();					
							rs1=stmt1.executeQuery(sql1);
							
							if(rs1.next()) {
								total=rs1.getInt(1);
							}
							
							rs1.close();	
						}
						else 
						{
							System.out.println("fff");
							String sql="SELECT * FROM t_pack_info limit "+(page*pageSize)+","+pageSize;
							Statement stmt=DBUtil.getConnection().createStatement();
							ResultSet rs=stmt.executeQuery(sql);
							Luggageinfo f=null;			
									
							while(rs.next()) {
								String sql2="SELECT * FROM t_dic_code WHERE t_dic_code = "+rs.getString(4)+"";
							    Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								if(rs2.next())
								{	
								//System.out.println(rs2.getString(3));
								f=new Luggageinfo();
								f.setT_flid(rs.getString(2));
								f.setT_pan_code(rs.getString(3));
								f.setT_pan_area(rs2.getString(3));
								f.setT_hz1(rs.getString(9));
								f.setT_real_start_time(rs.getString(7));
								f.setT_real_end_time(rs.getString(8));
								list.add(f);	
								}			
							}
							rs.close();
							
							
							String sql1="SELECT COUNT(*) FROM t_pack_info";
							Statement stmt1=DBUtil.getConnection().createStatement();					
							
							//System.out.println(sql1);
							
							ResultSet rs1=stmt1.executeQuery(sql1);
							
							if(rs1.next()) {
								total=rs1.getInt(1);
								System.out.println(total);
							}
							
							rs1.close();	
						}
						
					}
					
				}
	
			}			
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
