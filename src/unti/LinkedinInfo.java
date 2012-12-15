package unti;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Iterator;

import linkedinAbout.LKSConstants;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

import dao.UserDao;

import entity.User;


public class LinkedinInfo {
	private String strAccess;
	public Token accessToken;
	
	public LinkedinInfo(String strAccess) throws IOException, ClassNotFoundException{
		this.strAccess = strAccess;
		accessToken = getToken();
	}
	
	private Token getToken() throws IOException, ClassNotFoundException{
		
		
		byte[] bytes = strAccess.getBytes("ISO-8859-1");//按照ISO-8859-1编码  貌似非此编码不可。
        ByteArrayInputStream bs = new ByteArrayInputStream(bytes);
        ObjectInputStream ois = new ObjectInputStream(bs);
        Token o =  (Token) ois.readObject();
		return o;
	}
	
	public JSONObject getMyself(){
		String url = "http://api.linkedin.com/v1/people/~:(educations)";
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		request.addHeader("x-li-format", "json");
		LKSConstants.service.signRequest(accessToken, request);
		Response response = request.send();
		return JSONObject.fromObject(response.getBody());
	}
	
	public JSONObject getAllFriend(){
		String url = "http://api.linkedin.com/v1/people/~/connections:(educations,distance,member-url-resources,location,id,first-name,last-name,summary,headline,industry,num-connections,honors,positions,publications,languages,skills,certifications,phone-numbers,date-of-birth,main-address,picture-url,public-profile-url)";
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		request.addHeader("x-li-format", "json");
		LKSConstants.service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println(response.getBody());
		return JSONObject.fromObject(response.getBody());
	}

	public JSONObject getFriend(int start,int count){
		String url = "http://api.linkedin.com/v1/people/~/connections:(educations,distance,member-url-resources,location,id,first-name,last-name,summary,headline,industry,num-connections,honors,positions,publications,languages,skills,certifications,phone-numbers,date-of-birth,main-address,picture-url,public-profile-url)?start="+start+"&count="+count;
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		request.addHeader("x-li-format", "json");
		LKSConstants.service.signRequest(accessToken, request);
		Response response = request.send();
		System.out.println(response.getBody());
		return JSONObject.fromObject(response.getBody());
	}
	
	public JSONArray searchFriend(String key){
		JSONObject friendAllInfo = getAllFriend();
		JSONArray answer = new JSONArray();
		JSONObject j1 = JSONObject.fromObject(friendAllInfo);
	    JSONArray j2 = JSONArray.fromObject(j1.get("values"));
	    Iterator<JSONObject> ij = j2.iterator();
	    while(ij.hasNext()){
	    	JSONObject result = ij.next();
	    	JSONObject tmp = result;
	    	tmp.put("name", result.get("firstName")+" "+result.get("lastName"));
	    	System.out.println(tmp.toString());
	    	if(tmp.toString().toLowerCase().contains(key.trim().toLowerCase())){
	    		answer.add(result);
	    		System.out.println(result);
	    	}
	    }  
		return answer;
	}
	
	public JSONObject getOneFriend(String id){
		String url = "http://api.linkedin.com/v1/people/id="+id+":(email-address,distance,member-url-resources,summary,location,id,first-name,last-name,headline,industry,num-connections,honors,positions,publications,languages,skills,certifications,educations,phone-numbers,date-of-birth,main-address,picture-url,public-profile-url)";
		OAuthRequest request = new OAuthRequest(Verb.GET, url);
		request.addHeader("x-li-format", "json");
		LKSConstants.service.signRequest(accessToken, request);
		Response response = request.send();
		JSONObject result = JSONObject.fromObject(response.getBody());
    	JSONObject tmp = result;
		tmp.put("name", result.get("firstName")+" "+result.get("lastName"));
		System.out.println(tmp);
		return tmp;
	}
	
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		User u = new UserDao().login("s@s.com", "123123123");
		LinkedinInfo li = new LinkedinInfo(u.getLinkedinaccess());
		System.out.println(li.getFriend(1, 1));
		
		
	}

		
	
	
}
