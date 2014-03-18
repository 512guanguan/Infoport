package com.llb.adapter;


import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.llb.activity.R;
import com.llb.entity.FriendMainItemBean;

public class FriendMainAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<FriendMainItemBean> list;//保存要显示的item内容
	private FriendMainItemBean itemBean;
	private TextView item_title;
	private TextView item_content;
	private TextView item_content_comment;
	private TextView item_author;
	private TextView item_time;
	public FriendMainAdapter(Context context, ArrayList<FriendMainItemBean> list) {
		super();
		this.context = context;
		this.list = list;
	}
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView==null) {
			convertView=LayoutInflater.from(context).inflate(R.layout.friend_listview_item, null);
		}
		item_title=(TextView) convertView.findViewById(R.id.friend_item_title);
		item_content=(TextView) convertView.findViewById(R.id.friend_item_content);
		item_content_comment=(TextView) convertView.findViewById(R.id.friend_item_content_comment);
		item_author=(TextView) convertView.findViewById(R.id.friend_item_author);
		item_time=(TextView) convertView.findViewById(R.id.friend_item_time);
		itemBean=list.get(position);//取得一个item对象
		
		item_title.setText(itemBean.getFriend_item_title());
		item_content.setText(itemBean.getFriend_item_content());
		item_content_comment.setText(itemBean.getFriend_item_content_comment());
		item_author.setText(itemBean.getFriend_item_author());
		item_time.setText(itemBean.getFriend_item_time());
		return convertView;
	}

}
