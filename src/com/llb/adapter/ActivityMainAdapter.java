package com.llb.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.llb.activity.R;
import com.llb.entity.ActivityMainItemBean;

public class ActivityMainAdapter extends BaseAdapter{
	private Context context;
	private ArrayList<ActivityMainItemBean> list;//保存要显示的item内容
	private ActivityMainItemBean itemBean;
	private TextView item_authorname;
	private TextView item_authortime;
	private TextView item_activityname;
	private TextView item_activitytime;
	private TextView item_activityaddr;

	public ActivityMainAdapter(Context context, ArrayList<ActivityMainItemBean> list) {
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
			convertView=LayoutInflater.from(context).inflate(R.layout.activity_listview_item, null);
		}
		item_authorname=(TextView) convertView.findViewById(R.id.activity_item_authorname);
		item_authortime=(TextView) convertView.findViewById(R.id.activity_item_authortime);
		item_activityname=(TextView) convertView.findViewById(R.id.activity_item_activityname);
		item_activitytime=(TextView) convertView.findViewById(R.id.activity_item_activitytime);
		item_activityaddr=(TextView) convertView.findViewById(R.id.activity_item_activityaddr);
		itemBean=list.get(position);//取得一个item对象
		
		item_authorname.setText(itemBean.getActivity_item_authorname());
		item_authortime.setText(itemBean.getActivity_item_authortime());
		item_activityname.setText(itemBean.getActivity_item_activityname());
		item_activitytime.setText(itemBean.getActivity_item_activitytime());
		item_activityaddr.setText(itemBean.getActivity_item_activityaddr());
		return convertView;
	}
}