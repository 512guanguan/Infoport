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
import com.llb.adapter.JobMainAdapter;
import com.llb.entity.JobMainItemBean;
import com.llb.util.PullToRefreshListView;
import com.llb.util.PullToRefreshListView.OnRefreshListener;


public class JobFragment extends Fragment implements OnRefreshListener,OnItemClickListener{
	private View view;//用来缓存之前的页面，防止它重复Inflate页面
	private PullToRefreshListView listView=null;
	private ArrayList<JobMainItemBean> list;//用来存储要显示的一栏信息
	private JobMainAdapter adapter;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.i("slide","JobFragment--onCreate");
        list=new ArrayList<JobMainItemBean>();
    }
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (view == null) {
			view = inflater.inflate(R.layout.job_fragment, container, false);
			Log.i("slide","JobFragment-rootView=null");
			listView=(PullToRefreshListView)view.findViewById(R.id.lv_job);
			listView.ChangeScrollBarIcon(R.drawable.scrollbar);//改变快速滚动条的图标
			initArrayList();
		}
		//缓存的rootView需要判断是否已经被加过parent，如果有parent需要从parent删除，要不然会发生IllegalStateException。
		ViewGroup parent = (ViewGroup) view.getParent();
		if (parent != null) {
			parent.removeView(view);
			Log.i("slide","JobFragment-removeView");
		}
		
		adapter=new JobMainAdapter(view.getContext(), list);
		listView.setAdapter(adapter);
		listView.setOnRefreshListener(this);//正在刷新数据监听
		listView.setOnItemClickListener(this);//设置选项点击监听
		return view; 
		//下面这种没有缓存页面，每次都会重新加载数据，数据多会闪烁
//		View view=inflater.inflate(R.layout.job_fragment,container, false);
//		return view;
	}
	/**
	 * 这里应该是把网上取到的数据放进来
	 */
	private void initArrayList() {
		list.add(new JobMainItemBean("百度网络在线实习生招聘", "工作内容：关注并分析互联网用户行为，能从用户行为中发现所负责产品的潜在用户需求。"+
	"针对用户需求进行研究，并设计出相应的产品功能,与各其他团队密切配合一起工作，推动产品功能实现并取得预期的效果;对产品进行数据分析", "2013-11-20"));
		list.add(new JobMainItemBean("腾讯手机管家产品实习生招聘", "工作内容：推动产品功能实现并取得预期的效果;对产品进行数据分析，关注并分析互联网用户行为，能从用户行为中发现所负责产品的潜在用户需求。"+
				"针对用户需求进行研究，并设计出相应的产品功能,与各其他团队密切配合一起工作", "2013-11-19"));
		list.add(new JobMainItemBean("4399游戏特效校园招聘", "岗位职责：熟练运用3DMAX及 Photoshop 配合Illusion创作3d特效独立的绘制特效贴图及序列特效图片制作，能对特效动画时间进行合理的掌控和配合角色动画进行特效制作"+
				"熟练掌握particl特效，并在实现效果的同时控制面数，降低特效对整体效能带来的影响。 根据游戏设计要求，设计并制作游戏中的粒子特效、动态贴图特效及其他特效。", "2013-11-19"));
		
	}
	/**
	 * PullToRefreshListView里面接口
	 */
	@Override
	public void onRefresh() {
		//下拉刷新在这里请求网络
		Log.i("llb", "顶部下拉请求网络");
		list.add(new JobMainItemBean("腾讯手机管家产品实习生招聘", "工作内容：推动产品功能实现并取得预期的效果;对产品进行数据分析，关注并分析互联网用户行为，能从用户行为中发现所负责产品的潜在用户需求。"+
				"针对用户需求进行研究，并设计出相应的产品功能,与各其他团队密切配合一起工作", "2013-11-19"));
		list.add(new JobMainItemBean("4399游戏特效校园招聘", "岗位职责：熟练运用3DMAX及 Photoshop 配合Illusion创作3d特效独立的绘制特效贴图及序列特效图片制作，能对特效动画时间进行合理的掌控和配合角色动画进行特效制作"+
				"熟练掌握particl特效，并在实现效果的同时控制面数，降低特效对整体效能带来的影响。 根据游戏设计要求，设计并制作游戏中的粒子特效、动态贴图特效及其他特效。", "2013-11-19"));
		adapter.notifyDataSetChanged();
		listView.onRefreshComplete();
		
	}
	@Override
	public void onLoadMore() {
		//在这里请求网络
		Log.i("llb", "底部上拉请求网络");
		list.add(new JobMainItemBean("腾讯手机管家产品实习生招聘", "工作内容：推动产品功能实现并取得预期的效果;对产品进行数据分析，关注并分析互联网用户行为，能从用户行为中发现所负责产品的潜在用户需求。"+
				"针对用户需求进行研究，并设计出相应的产品功能,与各其他团队密切配合一起工作", "2013-11-19"));
		list.add(new JobMainItemBean("4399游戏特效校园招聘", "岗位职责：熟练运用3DMAX及 Photoshop 配合Illusion创作3d特效独立的绘制特效贴图及序列特效图片制作，能对特效动画时间进行合理的掌控和配合角色动画进行特效制作"+
				"熟练掌握particl特效，并在实现效果的同时控制面数，降低特效对整体效能带来的影响。 根据游戏设计要求，设计并制作游戏中的粒子特效、动态贴图特效及其他特效。", "2013-11-19"));
		adapter.notifyDataSetChanged();
		listView.onRefreshComplete();
	}
	
	@Override
    public void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i("slide","JobFragment--onPause");
    }
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("slide","JobFragment--onStop");
	}
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
