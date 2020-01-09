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
import com.iss.po.Airline;
import com.iss.po.Luggageinfo;
import com.iss.util.DBUtil;

/**
 * Servlet implementation class Airlinesearch
 */
@WebServlet("/Airlinesearch")
public class Airlinesearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Airlinesearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		Gson gson=new Gson();
		PrintWriter out=response.getWriter();
		String splace="";
		String mplace="";
		
        List list=new ArrayList<>();
		
		int total=0;
		try
		{
			if(request.getParameter("page")!=null&&request.getParameter("pageSize")!=null)
			{
				int page=Integer.parseInt(request.getParameter("page"));
				int pageSize=Integer.parseInt(request.getParameter("pageSize"));	
				if(request.getParameter("splace")!=""&&request.getParameter("splace")!=null)
				{
					splace=request.getParameter("splace");
					if(request.getParameter("mplace")!=""&&request.getParameter("mplace")!=null)
					{
						mplace=request.getParameter("mplace");
						String sql="SELECT * FROM t_dic_code WHERE t_name = '"+splace+"'";
						Statement stmt=DBUtil.getConnection().createStatement();
						ResultSet rs=stmt.executeQuery(sql);
						if(rs.next())
						{
							String sql1="SELECT * FROM t_dic_code WHERE t_name = '"+mplace+"'";
							Statement stmt1=DBUtil.getConnection().createStatement();
							ResultSet rs1=stmt1.executeQuery(sql1);
							if(rs1.next())
							{
								splace=rs.getString(2);
								mplace=rs1.getString(2);
								String sql2="SELECT * FROM t_flightinfo WHERE t_airline like '"+splace+"%"+mplace+"%' limit "+(page*pageSize)+","+pageSize+"";
								Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								Airline f=null;		
								while(rs2.next())
								{
									//String sql3="SELECT * FROM t_flightinfo WHERE t_airline like '%"+mplace+"%' limit "+(page*pageSize)+","+pageSize+"";
									//Statement stmt3=DBUtil.getConnection().createStatement();
									//ResultSet rs3=stmt1.executeQuery(sql3);
									//Luggageinfo f=null;		
									//while(rs3.next())
									//{
										String fnum=rs2.getString(10);
										String time=rs2.getString(7);
										String s=rs2.getString(14);
										int b=s.length();
										if(b==6)
										{
											String a=s.substring(0, 3);
											String c=s.substring(3, 6);
											String zplace1="";
											String splace1="";
											String mplace1="";
											String sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+a+"'";
											Statement stmt3=DBUtil.getConnection().createStatement();
											ResultSet rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												splace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+c+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												mplace1=rs3.getString(3);
											}
											f=new Airline();
											f.setT_flid(fnum);
											f.setT_time(time);
											f.setT_splace(splace1);
											f.setT_zplace(zplace1);
											f.setT_mplace(mplace1);
											//f.setT_time(time);
											list.add(f);	
											System.out.println(fnum);
											System.out.println(splace1);
											System.out.println(zplace1);
											System.out.println(mplace1);
											System.out.println(time);
										}
										else if(b==9)
										{
											String a=s.substring(0, 3);
											String c=s.substring(3, 6);
											String d=s.substring(6, 9);
											String zplace1="";
											String splace1="";
											String mplace1="";
											String sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+a+"'";
											Statement stmt3=DBUtil.getConnection().createStatement();
											ResultSet rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												splace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+c+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												zplace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+d+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												mplace1=rs3.getString(3);
											}
											f=new Airline();
											f.setT_flid(fnum);
											f.setT_time(time);
											f.setT_splace(splace1);
											f.setT_zplace(zplace1);
											f.setT_mplace(mplace1);
											list.add(f);	
											System.out.println(fnum);
											System.out.println(splace1);
											System.out.println(zplace1);
											System.out.println(mplace1);
											System.out.println(time);
										}
								}
								rs2.close();
						        sql1="SELECT COUNT(*) FROM t_flightinfo WHERE t_airline like '"+splace+"%"+mplace+"%'";
								stmt1=DBUtil.getConnection().createStatement();					
						     	rs1=stmt1.executeQuery(sql1);
								
								if(rs1.next()) {
									total=rs1.getInt(1);
								}
								
								rs1.close();	
							}
							
						}
						
					}
					else
					{
						String sql="SELECT * FROM t_dic_code WHERE t_name = '"+splace+"'";
						Statement stmt=DBUtil.getConnection().createStatement();
						ResultSet rs=stmt.executeQuery(sql);
						if(rs.next())
						{
								splace=rs.getString(2);
								String sql2="SELECT * FROM t_flightinfo WHERE t_airline like '"+splace+"%' limit "+(page*pageSize)+","+pageSize+"";
								Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								Airline f=null;		
								while(rs2.next())
								{
										String fnum=rs2.getString(10);
										String time=rs2.getString(7);
										String s=rs2.getString(14);
										int b=s.length();
										if(b==6)
										{
											String a=s.substring(0, 3);
											String c=s.substring(3, 6);
											String zplace1="";
											String splace1="";
											String mplace1="";
											String sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+a+"'";
											Statement stmt3=DBUtil.getConnection().createStatement();
											ResultSet rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												splace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+c+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												mplace1=rs3.getString(3);
											}
											f=new Airline();
											f.setT_flid(fnum);
											f.setT_time(time);
											f.setT_splace(splace1);
											f.setT_zplace(zplace1);
											f.setT_mplace(mplace1);
											//f.setT_time(time);
											list.add(f);	
											System.out.println(fnum);
											System.out.println(splace1);
											System.out.println(zplace1);
											System.out.println(mplace1);
											System.out.println(time);
										}
										else if(b==9)
										{
											String a=s.substring(0, 3);
											String c=s.substring(3, 6);
											String d=s.substring(6, 9);
											String zplace1="";
											String splace1="";
											String mplace1="";
											String sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+a+"'";
											Statement stmt3=DBUtil.getConnection().createStatement();
											ResultSet rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												splace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+c+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												zplace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+d+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												mplace1=rs3.getString(3);
											}
											f=new Airline();
											f.setT_flid(fnum);
											f.setT_time(time);
											f.setT_splace(splace1);
											f.setT_zplace(zplace1);
											f.setT_mplace(mplace1);
											list.add(f);	
											System.out.println(fnum);
											System.out.println(splace1);
											System.out.println(zplace1);
											System.out.println(mplace1);
											System.out.println(time);
										}
								}
								rs2.close();
						        sql="SELECT COUNT(*) FROM t_flightinfo WHERE t_airline like '"+splace+"%'";
								stmt=DBUtil.getConnection().createStatement();					
						     	rs=stmt.executeQuery(sql);
								
								if(rs.next()) {
									total=rs.getInt(1);
								}
								
								rs.close();	
							}
							
						}
				}
				else
				{
					if(request.getParameter("mplace")!=""&&request.getParameter("mplace")!=null)
					{
						mplace=request.getParameter("mplace");
							String sql1="SELECT * FROM t_dic_code WHERE t_name = '"+mplace+"'";
							Statement stmt1=DBUtil.getConnection().createStatement();
							ResultSet rs1=stmt1.executeQuery(sql1);
							if(rs1.next())
							{
								mplace=rs1.getString(2);
								String sql2="SELECT * FROM t_flightinfo WHERE t_airline like '___%"+mplace+"%' limit "+(page*pageSize)+","+pageSize+"";
								Statement stmt2=DBUtil.getConnection().createStatement();
								ResultSet rs2=stmt2.executeQuery(sql2);
								Airline f=null;		
								while(rs2.next())
								{
										String fnum=rs2.getString(10);
										String time=rs2.getString(7);
										String s=rs2.getString(14);
										int b=s.length();
										if(b==6)
										{
											String a=s.substring(0, 3);
											String c=s.substring(3, 6);
											String zplace1="";
											String splace1="";
											String mplace1="";
											String sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+a+"'";
											Statement stmt3=DBUtil.getConnection().createStatement();
											ResultSet rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												splace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+c+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												mplace1=rs3.getString(3);
											}
											f=new Airline();
											f.setT_flid(fnum);
											f.setT_time(time);
											f.setT_splace(splace1);
											f.setT_zplace(zplace1);
											f.setT_mplace(mplace1);
											list.add(f);	
											System.out.println(fnum);
											System.out.println(splace1);
											System.out.println(zplace1);
											System.out.println(mplace1);
											System.out.println(time);
										}
										else if(b==9)
										{
											String a=s.substring(0, 3);
											String c=s.substring(3, 6);
											String d=s.substring(6, 9);
											String zplace1="";
											String splace1="";
											String mplace1="";
											String sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+a+"'";
											Statement stmt3=DBUtil.getConnection().createStatement();
											ResultSet rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												splace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+c+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												zplace1=rs3.getString(3);
											}
											sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+d+"'";
											stmt3=DBUtil.getConnection().createStatement();
											rs3=stmt3.executeQuery(sql3);
											if(rs3.next())
											{
												mplace1=rs3.getString(3);
											}
											f=new Airline();
											f.setT_flid(fnum);
											f.setT_time(time);
											f.setT_splace(splace1);
											f.setT_zplace(zplace1);
											f.setT_mplace(mplace1);
											list.add(f);	
											System.out.println(fnum);
											System.out.println(splace1);
											System.out.println(zplace1);
											System.out.println(mplace1);
											System.out.println(time);
										}
								}
								rs2.close();
						        sql1="SELECT COUNT(*) FROM t_flightinfo WHERE t_airline like '%"+mplace+"%'";
								stmt1=DBUtil.getConnection().createStatement();					
						     	rs1=stmt1.executeQuery(sql1);
								
								if(rs1.next()) {
									total=rs1.getInt(1);
								}
								
								rs1.close();	
							}
				    }
					else 
					{
						String sql2="SELECT * FROM t_flightinfo limit "+(page*pageSize)+","+pageSize+"";
						Statement stmt2=DBUtil.getConnection().createStatement();
						ResultSet rs2=stmt2.executeQuery(sql2);
						Airline f=null;		
						while(rs2.next())
						{
								String fnum=rs2.getString(10);
								String time=rs2.getString(7);
								String s=rs2.getString(14);
								int b=s.length();
								if(b==6)
								{
									String a=s.substring(0, 3);
									String c=s.substring(3, 6);
									String zplace1="";
									String splace1="";
									String mplace1="";
									String sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+a+"'";
									Statement stmt3=DBUtil.getConnection().createStatement();
									ResultSet rs3=stmt3.executeQuery(sql3);
									if(rs3.next())
									{
										splace1=rs3.getString(3);
									}
									sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+c+"'";
									stmt3=DBUtil.getConnection().createStatement();
									rs3=stmt3.executeQuery(sql3);
									if(rs3.next())
									{
										mplace1=rs3.getString(3);
									}
									f=new Airline();
									f.setT_flid(fnum);
									f.setT_time(time);
									f.setT_splace(splace1);
									f.setT_zplace(zplace1);
									f.setT_mplace(mplace1);
									list.add(f);	
									System.out.println(fnum);
									System.out.println(splace1);
									System.out.println(zplace1);
									System.out.println(mplace1);
									System.out.println(time);
								}
								else if(b==9)
								{
									String a=s.substring(0, 3);
									String c=s.substring(3, 6);
									String d=s.substring(6, 9);
									String zplace1="";
									String splace1="";
									String mplace1="";
									String sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+a+"'";
									Statement stmt3=DBUtil.getConnection().createStatement();
									ResultSet rs3=stmt3.executeQuery(sql3);
									if(rs3.next())
									{
										splace1=rs3.getString(3);
									}
									sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+c+"'";
									stmt3=DBUtil.getConnection().createStatement();
									rs3=stmt3.executeQuery(sql3);
									if(rs3.next())
									{
										zplace1=rs3.getString(3);
									}
									sql3="SELECT * FROM t_dic_code WHERE t_dic_code = '"+d+"'";
									stmt3=DBUtil.getConnection().createStatement();
									rs3=stmt3.executeQuery(sql3);
									if(rs3.next())
									{
										mplace1=rs3.getString(3);
									}
									f=new Airline();
									f.setT_flid(fnum);
									f.setT_time(time);
									f.setT_splace(splace1);
									f.setT_zplace(zplace1);
									f.setT_mplace(mplace1);
									list.add(f);	
									System.out.println(fnum);
									System.out.println(splace1);
									System.out.println(zplace1);
									System.out.println(mplace1);
									System.out.println(time);
								}
						}
					    rs2.close();
				        String sql1="SELECT COUNT(*) FROM t_flightinfo";
				        Statement stmt1=DBUtil.getConnection().createStatement();					
				        ResultSet rs1=stmt1.executeQuery(sql1);
						
						if(rs1.next()) {
							total=rs1.getInt(1);
						}
						
						rs1.close();	
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
