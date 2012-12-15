package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import unti.LinkedinInfo;

import net.sf.json.JSONObject;

import entity.User;

public class DetailInfo extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * ��ȡ������Ϣ
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		User u = (User) session.getAttribute("user");
		String type = (String) request.getParameter("type");//����
		int currentPageNum = Integer.valueOf(request.getParameter("currentPageNum"));//��ǰҳ��
		System.out.println(type);
		JSONObject callback = new JSONObject();
		
		//����û��Ƿ��¼
		if(u==null){
			callback.put("code", "nonuser");
			callback.put("time", String.valueOf(new Date().toString()));
			out.print(callback);
			System.out.println("�û�û�е�¼");
			return;
		}
		
		if(type.equals("linkedin")){
			if(u.getLinkedinaccess()!=null){
				System.out.println(u.getLinkedinaccess());
				try {
					//����linkedinInfo
					LinkedinInfo li = new LinkedinInfo(u.getLinkedinaccess());
					callback.put("info", li.getFriend(currentPageNum,10));
				} catch (ClassNotFoundException e) {
					//δ֪����
					callback.put("code", "unknwon");
					e.printStackTrace();
					return;
				}
				callback.put("code", "linkedin");
				System.out.println("linkedin��Ȩ");
			}else{
				callback.put("code", "nonlinkedin");
				System.out.println("linkedinû����Ȩ");
			}
		}else if(type.equals("qq")){
			if(u.getQqaccess()!=null){
				callback.put("code", "qq");
				System.out.println("qq��Ȩ");
			}else{
				callback.put("code", "nonqq");
				System.out.println("qqû����Ȩ");
			}
		}else if(type.equals("weibo")){
			if(u.getWeiboaccess()!=null){
				callback.put("code", "weibo");
				System.out.println("weibo��Ȩ");
			}else{
				callback.put("code", "nonweibo");
				System.out.println("weiboû����Ȩ");
			}
		}else if(type.equals("douban")){
			if(u.getDoubanaccess()!=null){
				callback.put("code", "douban");
				System.out.println("douban��Ȩ");
			}else{
				callback.put("code", "nonbandou");
				System.out.println("doubanû����Ȩ");
			}
		}else if(type.equals("renren")){
			if(u.getRenrenaccess()!=null){
				callback.put("code", "renren");
				System.out.println("renren��Ȩ");
			}else{
				callback.put("code", "nonrenren");
				System.out.println("renrenû����Ȩ");
			}
		}else{
			callback.put("code", "unknown");
		}
		
		out.print(callback);
		
		
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
	}

}
