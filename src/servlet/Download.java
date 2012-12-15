package servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import tool.LinkedinXlsTool;
import unti.LinkedinInfo;

import entity.User;

public class Download extends HttpServlet {

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
		response.setContentType("application/octet-stream");  
		OutputStream out = response.getOutputStream();
		String as = request.getParameter("as");
		String type = request.getParameter("type");
		String attstr = request.getParameter("tmp");
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		System.out.println(type+" "+as);
		if(u==null||u.getAccesscode()==null){
			return;
		}
		if(type.equals("linkedin")){
			if(as.equals("allasxls")){
				//全部下载
				try {
					LinkedinInfo li = new LinkedinInfo(u.getLinkedinaccess());
					//创建xls实例
					HSSFWorkbook workbook =  new  HSSFWorkbook();
					//创建XLS样式
					HSSFCellStyle style = workbook.createCellStyle();
					HSSFCellStyle leftstyle = workbook.createCellStyle();
					HSSFCellStyle rightstyle = workbook.createCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					leftstyle.setBorderLeft((short)2); 
					rightstyle.setBorderRight((short)2);
					HSSFSheet sheet = workbook.createSheet();
					
					//输出header
					response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("linkedinAll.xls", "UTF-8"));
					//样式
					for(int i=0;i<66;i++){
						if(i==6)continue;
						if(i==7)continue;
						sheet.setDefaultColumnStyle(i, style);
					}
					
					//导入到xls样式
					LinkedinXlsTool.createXLSTitle(sheet,leftstyle,rightstyle);
					//全部结果
		    	    JSONArray j2 = JSONArray.fromObject(li.getAllFriend().get("values"));
		    	    //将结果信息写入XLS
		    	    Iterator<JSONObject> ij = j2.iterator();
		    	    int iid=1;
		    	    while(ij.hasNext()){
		    	    	JSONObject result = ij.next();
		    	    	String[] s = LinkedinXlsTool.loadData(result, sheet, iid);
		    		    HSSFRow row = sheet.createRow(iid);	
		    	    	LinkedinXlsTool.insertOneData(row,s,leftstyle,rightstyle);
		    	    	iid++;
		    	    	
		    	    }
		    	    workbook.writeProtectWorkbook(u.getAccesscode(),u.getEmail());
		    	    workbook.write(out);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(as.equals("allaspdf")){
				
			}else if(as.equals("searchasxls")){
				//搜索下载
				JSONArray ja = (JSONArray) session.getAttribute("linkedinsearch");
				HSSFWorkbook workbook =  new  HSSFWorkbook();
				HSSFCellStyle style = workbook.createCellStyle();
				HSSFCellStyle leftstyle = workbook.createCellStyle();
				HSSFCellStyle rightstyle = workbook.createCellStyle();
				style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				leftstyle.setBorderLeft((short)2); 
				rightstyle.setBorderRight((short)2);
				HSSFSheet sheet = workbook.createSheet();
				response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("linkedinSearch.xls", "UTF-8"));
				for(int i=0;i<66;i++){
					if(i==6)continue;
					if(i==7)continue;
					sheet.setDefaultColumnStyle(i, style);
				}
				LinkedinXlsTool.createXLSTitle(sheet,leftstyle,rightstyle);
	    	    Iterator<JSONObject> ij = ja.iterator();
	    	    int iid=1;
	    	    while(ij.hasNext()){
	    	    	JSONObject result = ij.next();
	    	    	String[] s = LinkedinXlsTool.loadData(result, sheet, iid);
	    		    HSSFRow row = sheet.createRow(iid);	
	    	    	LinkedinXlsTool.insertOneData(row,s,leftstyle,rightstyle);
	    	    	iid++;
	    	    	
	    	    }
	    	    //设置XLS密码
	    	    workbook.writeProtectWorkbook(u.getAccesscode(),u.getEmail());
	    	    workbook.write(out);
				
				
				
				
			}else if(as.equals("searchaspdf")){
				
			}else if(as.equals("attasxls")){
				//关注 下载
				try {
					LinkedinInfo li = new LinkedinInfo(u.getLinkedinaccess());
					HSSFWorkbook workbook =  new  HSSFWorkbook();
					HSSFCellStyle style = workbook.createCellStyle();
					HSSFCellStyle leftstyle = workbook.createCellStyle();
					HSSFCellStyle rightstyle = workbook.createCellStyle();
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
					leftstyle.setBorderLeft((short)2); 
					rightstyle.setBorderRight((short)2);
					HSSFSheet sheet = workbook.createSheet();
					response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("linkedinAttention.xls", "UTF-8"));
					for(int i=0;i<66;i++){
						if(i==6)continue;
						if(i==7)continue;
						sheet.setDefaultColumnStyle(i, style);
					}
					LinkedinXlsTool.createXLSTitle(sheet,leftstyle,rightstyle);
					
					System.out.println(attstr);
					String[] lists = attstr.split(" ");
					JSONArray ja = new JSONArray();
					for(int i = 0;i<lists.length;i++){
						if(lists[i]!=""){
							ja.add(li.getOneFriend(lists[i]));
						}
						
					}   
		    	    
		    	    Iterator<JSONObject> ij = ja.iterator();
		    	    int iid=1;
		    	    while(ij.hasNext()){
		    	    	JSONObject result = ij.next();
		    	    	String[] s = LinkedinXlsTool.loadData(result, sheet, iid);
		    		    HSSFRow row = sheet.createRow(iid);	
		    	    	LinkedinXlsTool.insertOneData(row,s,leftstyle,rightstyle);
		    	    	iid++;
		    	    	
		    	    }
		    	    //设置XLS密码
		    	    workbook.writeProtectWorkbook(u.getAccesscode(),u.getEmail());
		    	    //写出xls
		    	    workbook.write(out);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}else if(as.equals("attaspdf")){
				
				
			}
			
			
		}

		
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
		
		
		out.flush();
		out.close();
	}

}
