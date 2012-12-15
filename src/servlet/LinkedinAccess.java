package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import linkedinAbout.AuthHandler;
import linkedinAbout.LKSConstants;

import net.sf.json.JSONObject;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

public class LinkedinAccess extends HttpServlet {

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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		OAuthService service = (OAuthService) session.getAttribute("linkedinservice");//
		Token requestToken = (Token) session.getAttribute("linkedinrequestToken");
		String vp = request.getParameter("vp");//验证码
		Verifier verifier = new Verifier(vp);
		
		
		try {
			//获取accessToken
			Token accessToken = service.getAccessToken(requestToken,verifier);
			AuthHandler authHandler = new AuthHandler(accessToken);
			accessToken = authHandler.getAccessToken();
			//请求地址
			String url = "http://api.linkedin.com/v1/people/~:(email-address,id,first-name,last-name,headline,industry,num-connections,honors,positions,publications,languages,skills,certifications,educations,phone-numbers,date-of-birth,main-address,picture-url,public-profile-url)";
			//String url1 = "http://api.linkedin.com/v1/people/~/email-address";
			OAuthRequest request1 = new OAuthRequest(Verb.GET, url);
	       	request1.addHeader("x-li-format", "json");
	       	service.signRequest(accessToken, request1);
	       	Response response1 = request1.send();
	       	JSONObject info = JSONObject.fromObject(response1.getBody());
	     	System.out.println(response1.getBody());
	       	
	       	
	       	session.removeAttribute("linkedinservice");
	       	session.removeAttribute("linkedinrequestToken");
	       	
	       	session.setAttribute("linkedinaccess", accessToken);
			
			out.println(info.get("firstName")+" "+info.get("lastName"));
			out.flush();
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
			

		
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
		HttpSession session = request.getSession();
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		OAuthService service = LKSConstants.service;//创建service
		
		//生成 requestToken
		Token requestToken = service.getRequestToken();
		System.out.println(service.getAuthorizationUrl(requestToken));

		session.setAttribute("linkedinrequestToken", requestToken);
		session.setAttribute("linkedinservice", service);
		
		
		//通过requestToken得到授权地址
		out.println(service.getAuthorizationUrl(requestToken));
		System.out.println(service);
	}

	
	
	

}
