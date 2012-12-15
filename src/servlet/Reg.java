package servlet;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scribe.model.Token;

import tool.MD5;
import unti.LinkedinInfo;

import dao.UserDao;

import entity.User;

public class Reg extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
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
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		User u = new User();
		UserDao ud = new UserDao();
		String email = request.getParameter("name");
		String password = request.getParameter("pd");
		String nowdate = new Date().toLocaleString();
		Token linkedinaccess = null;
		linkedinaccess = (Token) session.getAttribute("linkedinaccess");
		
		Token qqaccess = null;
		qqaccess = (Token) session.getAttribute("qqaccess");
		
		Token weiboaccess = null;
		weiboaccess = (Token) session.getAttribute("weiboaccess");
		
		Token doubanaccess = null;
		doubanaccess = (Token) session.getAttribute("doubanaccess");
		
		Token renrenaccess = null;
		renrenaccess = (Token) session.getAttribute("renrenaccess");
		
		
		//将linkein token转化为流文件
		ByteArrayOutputStream linkedinbs = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(linkedinbs);
        os.writeObject(linkedinaccess);
        byte[] linkedinbytes = linkedinbs.toByteArray();
        String linkedinstraccess = new String(linkedinbytes,"ISO-8859-1");
        if(linkedinstraccess.length()<20){
        	linkedinstraccess = null;
        }
		
		u.setEmail(email);
		u.setPassword(new MD5(password).compute());
		u.setRegtime(nowdate);
		u.setLinkedinaccess(linkedinstraccess);
		
		
		
		//清空seesion access
		
		
		try {
			ud.saveUser(u);
			
			
			
			
//			try {
//				LinkedinInfo li = new LinkedinInfo(u.getLinkedinaccess());
//				li.accessToken = linkedinaccess;
//				System.out.println(linkedinaccess.getRawResponse());
//				System.out.println(li.accessToken.getRawResponse());
//				System.out.println(li.accessToken.equals(linkedinaccess));
//				
//				
//				System.out.println(li.getMyself());
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			
			
			session.removeAttribute("linkedinaccess");
			session.removeAttribute("qqaccess");
			session.removeAttribute("renrenaccess");
			session.removeAttribute("doubanaccess");
			session.removeAttribute("weiboaccess");
			
			
			
			
			
			
			out.print("0");
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			out.print("1");
			out.flush();
			out.close();
			e.printStackTrace();
		}
		
	}

}
