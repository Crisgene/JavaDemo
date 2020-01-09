package com.iss.po;

public class t_person {
	private  String  t_preson_id;
	private  String  t_user_id;
	private  String  t_dic_id;
	private  String  t_gender;
	private  String  t_mobile;
	private  String  t_email;
	private  String  t_cname;
	public String getT_preson_id() {
		return t_preson_id;
	}
	@Override
	public String toString() {
		return "t_person [t_preson_id=" + t_preson_id + ", t_user_id=" + t_user_id + ", t_dic_id=" + t_dic_id
				+ ", t_gender=" + t_gender + ", t_mobile=" + t_mobile + ", t_email=" + t_email + ", t_cname=" + t_cname
				+ "]";
	}
	public void setT_preson_id(String t_preson_id) {
		this.t_preson_id = t_preson_id;
	}
	public String getT_user_id() {
		return t_user_id;
	}
	public void setT_user_id(String t_user_id) {
		this.t_user_id = t_user_id;
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
}
