package com.llb.entity;
/**
 * 这个是Job主页的
 * @author llb
 *
 */
public class FriendMainItemBean {
	private String friend_item_title;
	private String friend_item_content;
	private String friend_item_content_comment;
	private String friend_item_author;
	private String friend_item_time;
	
	public FriendMainItemBean(String friend_item_title,String friend_item_content,
			String friend_item_content_comment,String friend_item_author,String friend_item_time) {
		super();
		this.friend_item_title = friend_item_title;
		this.friend_item_content = friend_item_content;
		this.friend_item_content_comment = friend_item_content_comment;
		this.friend_item_author = friend_item_author;
		this.friend_item_time = friend_item_time;
	}
	public String getFriend_item_title() {
		return friend_item_title;
	}
	public void setFriend_item_title(String friend_item_title) {
		this.friend_item_title = friend_item_title;
	}
	public String getFriend_item_content() {
		return friend_item_content;
	}
	public void setFriend_item_content(String friend_item_content) {
		this.friend_item_content = friend_item_content;
	}
	public String getFriend_item_content_comment() {
		return friend_item_content_comment;
	}
	public void setFriend_item_content_comment(
			String friend_item_content_comment) {
		this.friend_item_content_comment = friend_item_content_comment;
	}
	public String getFriend_item_author() {
		return friend_item_author;
	}
	public void setFriend_item_author(String friend_item_author) {
		this.friend_item_author = friend_item_author;
	}
	public String getFriend_item_time() {
		return friend_item_time;
	}
	public void setFriend_item_time(String friend_item_time) {
		this.friend_item_time = friend_item_time;
	}
	public String toString() {
		return "FriendMainItemBean [friend_item_title=" + friend_item_title
				+ "friend_item_content"+friend_item_content+",  friend_item_content_comment=" + friend_item_content_comment + ", friend_item_author="
				+ friend_item_author + ",friend_item_time="+ friend_item_time +"]";
	}
}
