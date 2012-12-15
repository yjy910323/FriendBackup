package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;

import net.sf.json.JSONObject;

import tool.MD5;

import entity.User;

public class UserAccess extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * 提取码设置
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		JSONObject callback = new JSONObject();
		if(u.getAccesscode()!=null&u.getAccesscode()!=""){
			callback.put("code","0");
			System.out.println("有提取码！");
		}else{
			callback.put("code","1");
			System.out.println("无提取码！");
		}
		out.print(callback);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * 设置提取码
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String pd = request.getParameter("pd");
		String ac = request.getParameter("ac");
		User u = (User) session.getAttribute("user");
		JSONObject callback = new JSONObject();
		if(u.getPassword().equals(new MD5(pd).compute())){
			u.setAccesscode(ac);
			try {
				new UserDao().updateUser(u);
				callback.put("code","0");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			callback.put("code","2");
			System.out.println("登录密码错误！");
		}
		
		
		
		
		out.print(callback);
		out.flush();
		out.close();
	}

}
