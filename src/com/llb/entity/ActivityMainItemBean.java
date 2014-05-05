package com.llb.entity;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 这个是活动主页的
 * 
 * @author llb
 * 
 */
public class ActivityMainItemBean implements Parcelable,Serializable {
	private String postId;// 帖子的id
	private String activity_item_authorname;// 发帖人
	private String activity_item_authortime;// 发帖时间
	private String activity_item_activityname;// 活动名称
	private String activity_item_activitytime;// 活动时间
	private String activity_item_activityaddr;// 活动地址

	public ActivityMainItemBean() {
	}

	public ActivityMainItemBean(String postId, String activity_item_authorname,
			String activity_item_authortime, String activity_item_activityname,
			String activity_item_activitytime, String activity_item_activityaddr) {
		super();
		this.postId = postId;
		this.activity_item_authorname = activity_item_authorname;
		this.activity_item_authortime = activity_item_authortime;
		this.activity_item_activityname = activity_item_activityname;
		this.activity_item_activitytime = activity_item_activitytime;
		this.activity_item_activityaddr = activity_item_activityaddr;
	}

	public String getpostId() {
		return postId;
	}

	public void setId(String postId) {
		this.postId = postId;
	}

	public String getActivity_item_authorname() {
		return activity_item_authorname;
	}

	public void setActivity_item_authorname(String activity_item_authorname) {
		this.activity_item_authorname = activity_item_authorname;
	}

	public String getActivity_item_activityname() {
		return activity_item_activityname;
	}

	public void setActivity_item_activityname(String activity_item_activityname) {
		this.activity_item_activityname = activity_item_activityname;
	}

	public String getActivity_item_activitytime() {
		return activity_item_activitytime;
	}

	public void setActivity_item_activitytime(String activity_item_activitytime) {
		this.activity_item_activitytime = activity_item_activitytime;
	}

	public String getActivity_item_activityaddr() {
		return activity_item_activityaddr;
	}

	public void setActivity_item_activityaddr(String activity_item_activityaddr) {
		this.activity_item_activityaddr = activity_item_activityaddr;
	}

	public String getActivity_item_authortime() {
		return activity_item_authortime;
	}

	public void setActivity_item_authortime(String activity_item_authortime) {
		this.activity_item_authortime = activity_item_authortime;
	}

	@Override
	public String toString() {
		return "ActivityMainItemBean [postId=" + postId
				+ "activity_item_authorname=" + activity_item_authorname
				+ "activity_item_authortime" + activity_item_authortime
				+ ",  activity_item_activityname=" + activity_item_activityname
				+ ", activity_item_activitytime=" + activity_item_activitytime
				+ ",activity_item_activityaddr=" + activity_item_activityaddr
				+ "]";
	}
	
	/**
	 * 以下三个函数都是为了实现Parcelable接口必须实现的函数
	 * 实现了Parcelable接口后
	 */
	public static final Parcelable.Creator<ActivityMainItemBean> CREATOR = new Creator<ActivityMainItemBean>() {
		@Override
		public ActivityMainItemBean createFromParcel(Parcel source) {
			ActivityMainItemBean itemBean = new ActivityMainItemBean();
			itemBean.postId = source.readString();
			itemBean.activity_item_activityaddr = source.readString();
			itemBean.activity_item_activityname = source.readString();
			itemBean.activity_item_activitytime = source.readString();
			itemBean.activity_item_authorname = source.readString();
			itemBean.activity_item_authortime = source.readString();
			return itemBean;
		}
		@Override
		public ActivityMainItemBean[] newArray(int size) {
			// TODO Auto-generated method stub
			return new ActivityMainItemBean[size];
		};
	};
	@Override
	public int describeContents() {
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(postId);
		dest.writeString(activity_item_activityaddr);
		dest.writeString(activity_item_activityname);
		dest.writeString(activity_item_activitytime);
		dest.writeString(activity_item_authorname);
		dest.writeString(activity_item_authortime);
	}
}

