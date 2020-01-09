package com.iss.po;

public class t_sys_user {
	private  String  t_user_id;
	private  String  t_user_name;
	private  String  t_user_pwd;
	private  String  t_role;
	private  String  t_status;
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
	@Override
	public String toString() {
		return "t_sys_user [t_user_id=" + t_user_id + ", t_user_name=" + t_user_name + ", t_user_pwd=" + t_user_pwd
				+ ", t_role=" + t_role + ", t_status=" + t_status + "]";
	}

}
