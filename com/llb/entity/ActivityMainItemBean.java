package com.llb.entity;

	/**
	 * 这个是活动主页的
	 * @author llb
	 *
	 */
public class ActivityMainItemBean {
		private String postId;//帖子的id
		private String activity_item_authorname;//发帖人
		private String activity_item_authortime;//发帖时间
		private String activity_item_activityname;//活动名称
		private String activity_item_activitytime;//活动时间
		private String activity_item_activityaddr;//活动地址
		
		public ActivityMainItemBean(String postId,String activity_item_authorname, String activity_item_authortime,String activity_item_activityname,
				String activity_item_activitytime,String activity_item_activityaddr) {
			super();
			this.postId=postId;
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
		public void setActivity_item_activityaddr(
				String activity_item_activityaddr) {
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
			return "ActivityMainItemBean [activity_item_authorname=" + activity_item_authorname
					+ "activity_item_authortime"+activity_item_authortime+",  activity_item_activityname=" + activity_item_activityname + ", activity_item_activitytime="
					+ activity_item_activitytime + ",activity_item_activityaddr="+ activity_item_activityaddr +"]";
		}
	}

