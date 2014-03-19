package com.llb.fragment;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.llb.activity.PostContentActivity;
import com.llb.activity.R;
import com.llb.adapter.ActivityMainAdapter;
import com.llb.entity.ActivityMainItemBean;
import com.llb.net.LoadData;
import com.llb.util.PullToRefreshListView;
import com.llb.util.PullToRefreshListView.OnRefreshListener;



public class ActivityFragment extends Fragment implements OnItemClickListener,OnRefreshListener{
	
	private PullToRefreshListView listView=null;
	private View view;//缓存页面
	private ArrayList<ActivityMainItemBean> list=null;//用来存储当前显示的信息
	private ArrayList<ActivityMainItemBean> newData=null;//刷新得到的新数据
	private ActivityMainAdapter adapter;
	//网络请求
	private LoadData loadData;
	public static  Handler handler=null;
	private String baseURL="http://192.168.1.109/collegepy/index.php";
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.i("slide","ActivityFragment--onCreate");
        list=new ArrayList<ActivityMainItemBean>();
        //网络请求类
        loadData=new LoadData(this.getActivity());
        handler=new Handler(){
        	@Override
        	public void handleMessage(Message msg) {
        		super.handleMessage(msg);
        		//Activity跳转到详情页
        		switch (msg.what) {
				case 101://请求到列表刷新数据后响应
					Log.i("zgr","msg==101");
        			newData=loadData.activityMainItemBeans;//把得到的数据传进来
        			loadData.activityMainItemBeans=null;//清空数据
        			refreshListView(newData);//刷新页面
					break;
				case 10100://出错了
					Log.i("zgr","msg==10100");
					refreshListView(new ArrayList<ActivityMainItemBean>());//刷新页面
					break;
				}
        	}
        };
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("slide","ActivityFragment-onCreateView");
		if(view==null){
			view=inflater.inflate(R.layout.activity_fragment,container, false);
			listView=(PullToRefreshListView)view.findViewById(R.id.lv_activity);
			listView.ChangeScrollBarIcon(R.drawable.scrollbar);//改变快速滚动条的图标
			initArrayList();
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);//先移除
			Log.i("slide","ActivityFragment-removeView");
		}
		adapter=new ActivityMainAdapter(view.getContext(), list);
		listView.setAdapter(adapter);
		listView.setOnRefreshListener(this);//正在刷新数据监听
		listView.setOnItemClickListener(this);//设置点击监听
		return view;
	}
	private void initArrayList() {
		list.add(new ActivityMainItemBean("2","zhuang","2013-11-10,17:00" ,"第十六届世纪木棉系列讲座","2013-11-20,19:00~21:00", "广州市华南理工大学34号楼102"));
		list.add(new ActivityMainItemBean("1","zhuang1","2013-11-10,17:00", "2013年中国移动广东公司“领先之星”职业发展俱乐部招新","2013-11-20, 19:00~20:30", "广州市华南理工大学34号楼102"));
		list.add(new ActivityMainItemBean("2","zhuang2", "2013-11-10,17:00","研究生科技文化节专题系列讲座之“信息漫谈”","2013-11-20", "广州市华南理工大学逸夫科学馆107"));
		
	}
	/**
	 * OnItemClickListener里面接口
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.i("zgr","点击一下,开始请求数据");
		String url=baseURL+"/Home/Index/activity";
		Intent intent=new Intent(this.getActivity(), PostContentActivity.class);
		intent.putExtra("url",url);//把请求网址发过去
		startActivity(intent);//先跳转页面
//		请求json数据示例
//		try {
//			String jsonString=loadData.getActivityPost("http://10.0.2.2/collegepy/index.php/Home/Index/activity");
//			Log.i("zgr","得到的数据："+jsonString);
//			loadData.result=null;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			Log.i("zgr","请求失败");
//			e.printStackTrace();
//		}
	}
	/**
	 * PullToRefreshListView里面接口
	 */
	@Override
	public void onRefresh() {
		//下拉刷新在这里请求网络
		Log.i("llb", "顶部下拉请求网络");
		String url=baseURL+"/Home/PostList/postlist";//请求接口地址
		loadData.getPostList(url,1,100);//请求第一页数据
	}
	@Override
	public void onLoadMore() {
		//在这里请求网络
		Log.i("llb", "底部上拉请求网络");
		refreshListView(new ArrayList<ActivityMainItemBean>());//刷新数据
	}
	private void refreshListView(ArrayList<ActivityMainItemBean> newData){
//		for(int index=0;index<newData.size();index++){
//			list.
//			list.add(newData.get(index));//逐个添加新数据
//		}
		list.addAll(0, newData);
//		list.add(new ActivityMainItemBean("2","zhuang2", "2013-11-10,17:00","研究生科技文化节专题系列讲座之“信息漫谈”","2013-11-20", "广州市华南理工大学逸夫科学馆107"));
		adapter.notifyDataSetChanged();
		listView.onRefreshComplete();
	}
	
	@Override
    public void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i("slide","ActivityFragment--onPause");
    }
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("slide","ActivityFragment--onStop");
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("slide","ActivityFragment--onDestroy");
	}
	
}
