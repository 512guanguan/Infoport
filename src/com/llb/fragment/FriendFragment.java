package com.llb.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.llb.activity.PostContentActivity;
import com.llb.activity.R;
import com.llb.adapter.FriendMainAdapter;
import com.llb.entity.FriendMainItemBean;
import com.llb.util.PullToRefreshListView;
import com.llb.util.PullToRefreshListView.OnRefreshListener;


public class FriendFragment extends Fragment implements OnRefreshListener,OnItemClickListener{
	private View view;//缓存页面
	private ArrayList<FriendMainItemBean> list;//用来存储要显示的一栏信息
	private PullToRefreshListView listView;
	private FriendMainAdapter adapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		list=new ArrayList<FriendMainItemBean>();
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("slide","FriendFragment-onCreateView");
		if(view==null){
			view=inflater.inflate(R.layout.friend_fragment,container, false);
			
			listView=(PullToRefreshListView)view.findViewById(R.id.lv_friend);
			listView.ChangeScrollBarIcon(R.drawable.scrollbar);//改变快速滚动条的图标
			initArrayList();
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);//先移除
		}
		
		adapter=new FriendMainAdapter(view.getContext(), list);
		listView.setAdapter(adapter);
		listView.setOnRefreshListener(this);//正在刷新数据监听
		listView.setOnItemClickListener(this);//设置选项点击监听事件
		return view;
	}
	/**
	 * 这里应该是把网上取到的数据放进来
	 */
	private void initArrayList() {
		list.add(new FriendMainItemBean("android相对布局详解","这个帖子的内容是讲述android的相对布局方面的知识","0","zhuang","10-23,23:00"));
		list.add(new FriendMainItemBean("友善之臂之Tiny4412","友善之臂Tiny4412是由广州友善之臂计算机科技有限公司设计生产的","0","zhuang","10-23,23:00"));
		list.add(new FriendMainItemBean("华南理工世纪木棉讲座","华工世纪木棉系列讲座一般是有校学生会组织承办的","0","zhuang","10-23,23:00"));
	}
	
	@Override
    public void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i("slide","FriendFragment--onPause");
    }
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("slide","FriendFragment--onStop");
	}
	/**
	 * PullToRefreshListView里面接口
	 */
	@Override
	public void onRefresh() {
		//下拉刷新在这里请求网络
		Log.i("llb", "顶部下拉请求网络");
		list.add(new FriendMainItemBean("android相对布局详解","这个帖子的内容是讲述android的相对布局方面的知识","0","zhuang","10-23,23:00"));
		list.add(new FriendMainItemBean("友善之臂之Tiny4412","友善之臂Tiny4412是由广州友善之臂计算机科技有限公司设计生产的","0","zhuang","10-23,23:00"));
		adapter.notifyDataSetChanged();
		listView.onRefreshComplete();
		
	}
	@Override
	public void onLoadMore() {
		//在这里请求网络
				Log.i("llb", "底部上拉请求网络");
				list.add(new FriendMainItemBean("android相对布局详解","这个帖子的内容是讲述android的相对布局方面的知识","0","zhuang","10-23,23:00"));
				list.add(new FriendMainItemBean("友善之臂之Tiny4412","友善之臂Tiny4412是由广州友善之臂计算机科技有限公司设计生产的","0","zhuang","10-23,23:00"));
				adapter.notifyDataSetChanged();
				listView.onRefreshComplete();
	}
	/**
	 * onitemclicklistener接口函数
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.i("zgr","点击一下,开始请求数据");
		String url="http://192.168.1.102/collegepy/index.php/Home/Index/activity";
		Intent intent=new Intent(this.getActivity(), PostContentActivity.class);
		intent.putExtra("url",url);//把请求网址发过去
		startActivity(intent);//先跳转页面
	}
}
