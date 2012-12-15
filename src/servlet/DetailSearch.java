package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import unti.LinkedinInfo;

import entity.User;

public class DetailSearch extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * 个人信息查询
	 * 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String key = request.getParameter("key");//搜索关键字
		String type = (String) request.getParameter("type");
		User u = (User) session.getAttribute("user");//用户信息
		JSONObject callback = new JSONObject();
		System.out.println("key="+key);
		//检查是否登录
		if(u==null){
			callback.put("code", "nonuser");
			out.print(callback);
			System.out.println("用户没有登录");
			return;
		}
		if(type.equals("linkedin")){
			if(u.getLinkedinaccess()!=null){
				System.out.println(u.getLinkedinaccess());
				try {
					LinkedinInfo li = new LinkedinInfo(u.getLinkedinaccess());
					JSONArray ja = li.searchFriend(key);
					session.setAttribute("linkedinsearch", ja);
					callback.put("result",ja);
					
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				callback.put("code", "linkedin");
				System.out.println("linkedin授权");
			}else{
				callback.put("code", "nonlinkedin");
				System.out.println("linkedin没有授权");
			}
		}else if(type.equals("qq")){
			if(u.getQqaccess()!=null){
				callback.put("code", "qq");
				System.out.println("qq授权");
			}else{
				callback.put("code", "nonqq");
				System.out.println("qq没有授权");
			}
		}else if(type.equals("weibo")){
			if(u.getWeiboaccess()!=null){
				callback.put("code", "weibo");
				System.out.println("weibo授权");
			}else{
				callback.put("code", "nonweibo");
				System.out.println("weibo没有授权");
			}
		}else if(type.equals("douban")){
			if(u.getDoubanaccess()!=null){
				callback.put("code", "douban");
				System.out.println("douban授权");
			}else{
				callback.put("code", "nonbandou");
				System.out.println("douban没有授权");
			}
		}else if(type.equals("renren")){
			if(u.getRenrenaccess()!=null){
				callback.put("code", "renren");
				System.out.println("renren授权");
			}else{
				callback.put("code", "nonrenren");
				System.out.println("renren没有授权");
			}
		}else{
			callback.put("code", "unknown");
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
	}

}
