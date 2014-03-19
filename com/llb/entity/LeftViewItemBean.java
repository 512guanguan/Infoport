package com.llb.entity;

public class LeftViewItemBean {
	private int itemImage;//listview item的图片
	private String itemName;//栏目名称
	public LeftViewItemBean(int itemImage,String itemName){
		this.itemImage=itemImage;
		this.itemName=itemName;
	}
	public int getItemImage() {
		return itemImage;
	}
	public void setItemImage(int itemImage) {
		this.itemImage = itemImage;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
}
