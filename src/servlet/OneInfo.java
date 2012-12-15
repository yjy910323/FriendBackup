package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import unti.LinkedinInfo;

import net.sf.json.JSONObject;

import entity.User;

public class OneInfo extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * 得到一个人信息。
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String type = request.getParameter("type");
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		JSONObject callback = new JSONObject();
		//检查是否登录
		if(u==null){
			callback.put("code", "nonuser");
			out.print(callback);
			System.out.println("用户没有登录");
			return;
		}
		
		if(type.equals("linkedin")){
			if(u.getLinkedinaccess()!=null){
				try {
					LinkedinInfo li = new LinkedinInfo(u.getLinkedinaccess());
					callback.put("info", li.getOneFriend(id));
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				callback.put("code", "linkedin");
				System.out.println("已发送"+id+"个人信息");
			}else{
				callback.put("code", "nonlinkedin");
				System.out.println("linkedin没有授权");
			}
		}
		
		
		
		
		
		out.print(callback);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
