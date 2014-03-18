package com.llb.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.llb.activity.R;
import com.llb.entity.LeftViewItemBean;

public class LeftViewAdapter extends BaseAdapter{
	private ArrayList<LeftViewItemBean> list;//存储的是几个item需要的信息
	private Context mContext;
	private ImageView imageView;//item上面的
	private TextView textView;
	public LeftViewAdapter( Context mContext,ArrayList<LeftViewItemBean> list){
		this.mContext=mContext;
		this.list=list;
	}
	public LeftViewAdapter(Context mContext){
		this.mContext=mContext;
		LeftViewItemBean item=null;//=new LeftViewItemBean(R.drawable.leftview, "热门帖子");
		list.add(item);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}
	@Override
	public Object getItem(int index) {
		// TODO Auto-generated method stub
		return list.get(index);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view=LayoutInflater.from(mContext).inflate(R.layout.left_listview_item, null);
		imageView=(ImageView) view.findViewById(R.id.iv_item_head);
		textView=(TextView) view.findViewById(R.id.tv_item_name);
		imageView.setImageResource(list.get(position).getItemImage());
		textView.setText(list.get(position).getItemName());
		return view;
	}
}
