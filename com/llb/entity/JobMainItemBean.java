package com.llb.entity;

/**
 * 这个是Job主页的
 * @author llb
 *
 */
public class JobMainItemBean {
	private String job_item_name;
	private String job_item_content;
	private String job_item_time;
	public JobMainItemBean(String job_item_name, String job_item_content,
			String job_item_time) {
		super();
		this.job_item_name = job_item_name;
		this.job_item_content = job_item_content;
		this.job_item_time = job_item_time;
	}
	public String getJob_item_name() {
		return job_item_name;
	}
	public void setJob_item_name(String job_item_name) {
		this.job_item_name = job_item_name;
	}
	public String getJob_item_content() {
		return job_item_content;
	}
	public void setJob_item_content(String job_item_content) {
		this.job_item_content = job_item_content;
	}
	public String getJob_item_time() {
		return job_item_time;
	}
	public void setJob_item_time(String job_item_time) {
		this.job_item_time = job_item_time;
	}
	@Override
	public String toString() {
		return "JobMainItemBean [job_item_name=" + job_item_name
				+ ", job_item_content=" + job_item_content + ", job_item_time="
				+ job_item_time + "]";
	}
	
}
