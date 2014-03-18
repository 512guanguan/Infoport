package com.llb.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.llb.activity.R;
import com.llb.entity.JobMainItemBean;

public class JobMainAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<JobMainItemBean> list;//保存要显示的item内容
	private JobMainItemBean itemBean;
	private TextView item_name;
	private TextView item_content;
	private TextView item_time;
	
	public JobMainAdapter(Context context, ArrayList<JobMainItemBean> list) {
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
			convertView=LayoutInflater.from(context).inflate(R.layout.job_listview_item, null);
		}
		item_name=(TextView) convertView.findViewById(R.id.job_item_name);
		item_content=(TextView) convertView.findViewById(R.id.job_item_content);
		item_time=(TextView) convertView.findViewById(R.id.job_item_time);
		itemBean=list.get(position);//取得一个item对象
		
		item_name.setText(itemBean.getJob_item_name());
		item_content.setText(itemBean.getJob_item_content());
		item_time.setText(itemBean.getJob_item_time());
		return convertView;
	}

}
