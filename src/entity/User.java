package entity;

import java.util.Date;


//用户实体
public class User {
	private int id;//用户id，非linkedin提供，
	private String email;//用户邮箱
	private String password;//密码
	private String linkedinaccess;//linkedin授权文件，为二进制流文件，编码为"ISO-8859-1"
	private String renrenaccess;
	private String qqaccess;
	private String doubanaccess;
	private String weiboaccess;
	private String regtime;//注册时间
	private String accesscode;//提取码
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLinkedinaccess() {
		return linkedinaccess;
	}
	public void setLinkedinaccess(String linkedinaccess) {
		this.linkedinaccess = linkedinaccess;
	}
	public String getRenrenaccess() {
		return renrenaccess;
	}
	public void setRenrenaccess(String renrenaccess) {
		this.renrenaccess = renrenaccess;
	}
	public String getQqaccess() {
		return qqaccess;
	}
	public void setQqaccess(String qqaccess) {
		this.qqaccess = qqaccess;
	}
	public String getDoubanaccess() {
		return doubanaccess;
	}
	public void setDoubanaccess(String doubanaccess) {
		this.doubanaccess = doubanaccess;
	}
	public String getWeiboaccess() {
		return weiboaccess;
	}
	public void setWeiboaccess(String weiboaccess) {
		this.weiboaccess = weiboaccess;
	}
	public String getRegtime() {
		return regtime;
	}
	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccesscode() {
		return accesscode;
	}
	public void setAccesscode(String accesscode) {
		this.accesscode = accesscode;
	}
}
