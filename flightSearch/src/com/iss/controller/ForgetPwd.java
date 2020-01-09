package com.iss.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.iss.po.t_person;
import com.iss.util.DBUtil;
import com.iss.util.MailUtil;

/**
 * Servlet implementation class ForgetPwd
 */
@WebServlet("/ForgetPwd")
public class ForgetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgetPwd() {
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
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json");
		response.setHeader("Access-Control-Allow-Origin", "*");
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		List list=new ArrayList<>();
		String username="";
		String id="";
		String password="";
		String ccode="";
		String receiveMailAccount="";
		//System.out.println(request.getParameter("t_user_pwd"));
		try {
			if (request.getParameter("email")!=null) {
				receiveMailAccount=request.getParameter("email");
				Random random = new Random();
				String result="";
				for (int i=0;i<6;i++)
				{
					result+=random.nextInt(10);
				}
				String string="亲爱的用户，您好！您现在正在通过邮箱找回密码，您的验证码是"+result+",如果不是本人请无视~";
				
				ccode=result;
				MailUtil.sendMail(receiveMailAccount,string);
			}
			if(request.getParameter("ForgetPwd")!=null&&request.getParameter("ForgetPwdUsername")!=null){
				username=request.getParameter("ForgetPwdUsername");
			Statement statement=DBUtil.getConnection().createStatement();
			String sql2="SELECT * FROM `t_sys_user` where t_user_name='"+username+"'  " ;
			System.out.println(sql2);
			
			ResultSet resultSet2=statement.executeQuery(sql2);
			if(resultSet2.next())
				id=resultSet2.getString(1);
			System.out.println(id);	
			System.out.println(request.getParameter("ForgetPwd"));
			
				password=request.getParameter("ForgetPwd");		
				//Statement statement1=DBUtil.getConnection().createStatement();
			
				String sql="UPDATE `t_sys_user`  SET  t_user_pwd='"+password+"'  WHERE t_user_id='"+id+"' ";
								
				System.out.println(sql);
				statement.execute(sql);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		out.print(gson.toJson(ccode));
		out.flush();
		out.close();
		
	}

}
