package com.iss.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.apache.jasper.tagplugins.jstl.core.Out;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * Servlet implementation class VCservlet
 */
@WebServlet("/VCservlet")
public class VCservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VCservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		request.getParameter("Code");
		
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setIntHeader("expires", 0);
		//PrintWriter out=response.getWriter();
		
		int width=120;
		int heigh=30;
		
		BufferedImage img=new BufferedImage(width, heigh, BufferedImage.TYPE_INT_BGR);
		Graphics g=img.getGraphics();
		
		g.setColor(Color.PINK);
		g.fillRect(1, 1, width-2, heigh-2);
		
		g.setColor(Color.RED);
		g.drawRect(0, 0, width-1, heigh-1);
		
		g.setColor(Color.BLUE);
		g.setFont(new Font("Vladimir Script", Font.BOLD|Font.ITALIC,26));
		
		Random random=new Random();
		int position=20;
		
		StringBuffer sb=new StringBuffer();
		
		for(int i=0;i<4;i++)
		{
			int s=random.nextInt(10);
			g.drawString(s+"", position, 22);
			position+=20;
			sb.append(s);
		}
		
		for(int i=0;i<10;i++) 
		{
			g.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
			g.drawLine(random.nextInt(width), random.nextInt(heigh), random.nextInt(width), random.nextInt(heigh));
			
			
			
		}
		ImageIO.write(img, "png",response.getOutputStream());
		//request.setAttribute("picCode", sb.toString());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
