package entity;

import java.util.Date;


//�û�ʵ��
public class User {
	private int id;//�û�id����linkedin�ṩ��
	private String email;//�û�����
	private String password;//����
	private String linkedinaccess;//linkedin��Ȩ�ļ���Ϊ���������ļ�������Ϊ"ISO-8859-1"
	private String renrenaccess;
	private String qqaccess;
	private String doubanaccess;
	private String weiboaccess;
	private String regtime;//ע��ʱ��
	private String accesscode;//��ȡ��
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
