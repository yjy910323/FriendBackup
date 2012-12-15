package servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import dao.UserDao;
import entity.User;

public class SNSAccess extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * 用户授权
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		User u = (User) session.getAttribute("user");
		UserDao ud = new UserDao();
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
		
		u.setLinkedinaccess(linkedinstraccess);

		//清空seesion access
		session.removeAttribute("linkedinaccess");
		session.removeAttribute("qqaccess");
		session.removeAttribute("renrenaccess");
		session.removeAttribute("doubanaccess");
		session.removeAttribute("weiboaccess");
		
		
		
		try {
			ud.updateUser(u);
			out.print("0");
			out.flush();
			out.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("1");
			out.flush();
			out.close();
		}
	}

}
