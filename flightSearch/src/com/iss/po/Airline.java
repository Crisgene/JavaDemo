package com.iss.po;

public class Airline {
	private String t_flid;
	private String t_time;
	private String t_splace;
	private String t_zplace;
	private String t_mplace;
	public String getT_flid() {
		return t_flid;
	}
	public void setT_flid(String t_flid) {
		this.t_flid = t_flid;
	}
	public String getT_time() {
		return t_time;
	}
	public void setT_time(String t_time) {
		this.t_time = t_time;
	}
	public String getT_splace() {
		return t_splace;
	}
	public void setT_splace(String t_splace) {
		this.t_splace = t_splace;
	}
	public String getT_zplace() {
		return t_zplace;
	}
	public void setT_zplace(String t_zplace) {
		this.t_zplace = t_zplace;
	}
	public String getT_mplace() {
		return t_mplace;
	}
	public void setT_mplace(String t_mplace) {
		this.t_mplace = t_mplace;
	}
	@Override
	public String toString() {
		return "Airline [t_flid=" + t_flid + ", t_time=" + t_time + ", t_splace=" + t_splace + ", t_zplace=" + t_zplace
				+ ", t_mplace=" + t_mplace + "]";
	}
	
}
