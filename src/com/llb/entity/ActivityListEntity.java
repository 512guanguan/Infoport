package com.llb.entity;

import java.util.ArrayList;

/**
 * 活动能够主页列表刷新返回的json数据实体
 * @author llb
 *
 */
public class ActivityListEntity {
	private String code;//功能号
	private ArrayList<ActivityMainItemBean> message;//装返回的列表数据
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public ArrayList<ActivityMainItemBean> getMessage() {
		return message;
	}
	public void setMessage(ArrayList<ActivityMainItemBean> message) {
		this.message = message;
	}
}
