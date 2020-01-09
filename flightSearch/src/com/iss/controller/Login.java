package com.iss.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.colorchooser.ColorSelectionModel;
import javax.websocket.Session;

import com.iss.po.User;

import com.google.gson.Gson;
import com.iss.util.DBUtil;
import com.iss.po.Img;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    String  msg=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int h=30;
		int w=120;
		Img img=new Img();
		String code=img.generateVerifyCode(4);
		msg=code;
		img.outputImage(w, h, response.getOutputStream(), code);
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
	
		String usercode=request.getParameter("Code");
		
		String name="";
		String pwd="";
	
		try {
	            name=request.getParameter("name");
				pwd=request.getParameter("pwd");
				
			   System.out.println(usercode);
			   System.out.println(msg);
		
			    String sql="select * from t_sys_user where t_user_name = '"+name+"'";
			    Statement cmd=DBUtil.getConnection().createStatement();
			    ResultSet rs=cmd.executeQuery(sql);
			    
			    if(rs.next())
			    {
			    
			    	sql="select * from t_sys_user where t_user_name = '"+name+"' and t_user_pwd = "+pwd+"";
				    cmd=DBUtil.getConnection().createStatement();
				    rs=cmd.executeQuery(sql);
				
				    if(rs.next())
				    {
				    
				    	
				    	if(usercode.equals(msg))
						{
				    		if(rs.getString(5).equals("δ���"))
					    	{
					    		out.print("{\"s\":\"ok\",\"msg\":\"�û�δ��ˣ��޷���¼��\"}");
					    	}
					    	else
					    	{
					    		System.out.println(rs.getString(4));
					    		if(rs.getString(4).equals("����Ա"))
					    		{
					    			out.print("{\"s\":\"ok\",\"msg\":\"����Ա\"}");
					    		}
					    		else if(rs.getString(4).equals("����"))
					    		{
					    			out.print("{\"s\":\"ok\",\"msg\":\"����\"}");
					    		}
					    		else if(rs.getString(4).equals("��ͨ�û�"))
					    		{
					    			out.print("{\"s\":\"ok\",\"msg\":\"��ͨ�û�\"}");
					    		}
					    		//out.print("{\"s\":\"ok\",\"msg\":\"��¼�ɹ���\"}");
					    		//System.out.println("aaa");
					    		
					    		//out.print("rs.getString(4)");
					    		
					    	}
				    	
						}
						else
						{
							
						
							out.print("{\"s\":\"ok\",\"msg\":\"��֤��������������룡\"}");
					
						}
				    }
				    else
				    {
				    
				     	out.print("{\"s\":\"ok\",\"msg\":\"�������\"}");
				     
				    }
			    }
			    else 
			    {
			    	//System.out.println("d");
			    	
			    	//PrintWriter out=response.getWriter();
			    	out.print("{\"s\":\"ok\",\"msg\":\"�û�δע��!\"}");
			    	//out.flush();
					//out.close();
			    }
		    	rs.close();
		     } catch (Exception e)
		    {
			   e.printStackTrace();
		    }
		
		out.flush();
		out.close();
	}

}
