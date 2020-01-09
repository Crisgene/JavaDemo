package com.iss.po;

public class PersonUser {
	private  String uID;
	private  String pwd;
	private  String nickname;
	private  String name;
	private  String sex;
	private  String phonenum;
	private  String email;
	
	public String getuID() {
		return uID;
	}
	public void setuID(String uID) {
		this.uID = uID;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private  String  state;
	private  String  t_user_id;
	private  String  t_user_name;
	private  String  t_user_pwd;
	private  String  t_role;
	private  String  t_status;
	private  String  t_preson_id;
	//private  String  t_user_id;
	private  String  t_dic_id;
	private  String  t_gender;
	private  String  t_mobile;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	private  String  t_email;
	@Override
	public String toString() {
		return "PersonUser [t_user_id=" + t_user_id + ", t_user_name=" + t_user_name + ", t_user_pwd=" + t_user_pwd
				+ ", t_role=" + t_role + ", t_status=" + t_status + ", t_preson_id=" + t_preson_id + ", t_dic_id="
				+ t_dic_id + ", t_gender=" + t_gender + ", t_mobile=" + t_mobile + ", t_email=" + t_email + ", t_cname="
				+ t_cname + "]";
	}
	public String getT_user_id() {
		return t_user_id;
	}
	public void setT_user_id(String t_user_id) {
		this.t_user_id = t_user_id;
	}
	public String getT_user_name() {
		return t_user_name;
	}
	public void setT_user_name(String t_user_name) {
		this.t_user_name = t_user_name;
	}
	public String getT_user_pwd() {
		return t_user_pwd;
	}
	public void setT_user_pwd(String t_user_pwd) {
		this.t_user_pwd = t_user_pwd;
	}
	public String getT_role() {
		return t_role;
	}
	public void setT_role(String t_role) {
		this.t_role = t_role;
	}
	public String getT_status() {
		return t_status;
	}
	public void setT_status(String t_status) {
		this.t_status = t_status;
	}
	public String getT_preson_id() {
		return t_preson_id;
	}
	public void setT_preson_id(String t_preson_id) {
		this.t_preson_id = t_preson_id;
	}
	public String getT_dic_id() {
		return t_dic_id;
	}
	public void setT_dic_id(String t_dic_id) {
		this.t_dic_id = t_dic_id;
	}
	public String getT_gender() {
		return t_gender;
	}
	public void setT_gender(String t_gender) {
		this.t_gender = t_gender;
	}
	public String getT_mobile() {
		return t_mobile;
	}
	public void setT_mobile(String t_mobile) {
		this.t_mobile = t_mobile;
	}
	public String getT_email() {
		return t_email;
	}
	public void setT_email(String t_email) {
		this.t_email = t_email;
	}
	public String getT_cname() {
		return t_cname;
	}
	public void setT_cname(String t_cname) {
		this.t_cname = t_cname;
	}
	private  String  t_cname;

}

