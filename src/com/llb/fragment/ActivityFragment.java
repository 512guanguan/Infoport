package com.llb.fragment;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.llb.activity.AContentActivity;
import com.llb.activity.R;
import com.llb.adapter.ActivityMainAdapter;
import com.llb.app.MyHttpClient;
import com.llb.entity.ActivityMainItemBean;
import com.llb.net.LoadData;
import com.llb.util.AppUtil;
import com.llb.util.JsonDecodeUtil;
import com.llb.util.PullToRefreshListView;
import com.llb.util.PullToRefreshListView.OnRefreshListener;




public class ActivityFragment extends Fragment implements OnItemClickListener,OnRefreshListener{
	
	private PullToRefreshListView listView=null;
	private View view=null;//缓存页面
	private ArrayList<ActivityMainItemBean> list=null;//用来存储当前显示的信息
	private ArrayList<ActivityMainItemBean> freshData=new ArrayList<ActivityMainItemBean>();//下拉刷新得到的新数据
	private ArrayList<ActivityMainItemBean> oldData=new ArrayList<ActivityMainItemBean>();//上拉刷新得到的新数据
	private ActivityMainAdapter adapter;
	//网络请求
	private LoadData loadData;
	public static  Handler handler=null;
	private String baseURL="http://192.168.1.104/collegepy/index.php";
	private String url=AppUtil.BASEURL_STRING+"/Home/PostList/postlist";//请求刷新的接口地址

	private int pageSize=1;//每页显示条数
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.i("slide","ActivityFragment--onCreate");
        list=new ArrayList<ActivityMainItemBean>();
        //网络请求类
        loadData=new LoadData(this.getActivity());
//        handler=new Handler(){
//        	@Override
//        	public void handleMessage(Message msg) {
//        		super.handleMessage(msg);
//        		//Activity跳转到详情页
//        		switch (msg.what) {
//				case 101://请求到列表刷新数据后响应
//					Log.i("zgr","msg==101");
//					freshData=loadData.activityMainItemBeans;//把得到的数据传进来
//        			loadData.activityMainItemBeans=null;//清空数据
//        			refreshListView(freshData);//刷新页面
//					break;
//				case 10100://出错了
//					Log.i("zgr","msg==10100");
//					refreshListView(new ArrayList<ActivityMainItemBean>());//刷新页面
//					break;
//				}
//        	}
//        };
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
		list.add(new ActivityMainItemBean("3","zhuang","2013-11-10,17:00" ,"第十六届世纪木棉系列讲座","2013-11-20,19:00~21:00", "广州市华南理工大学34号楼102"));
		list.add(new ActivityMainItemBean("2","zhuang1","2013-11-10,17:00", "2013年中国移动广东公司“领先之星”职业发展俱乐部招新","2013-11-20, 19:00~20:30", "广州市华南理工大学34号楼102"));
		list.add(new ActivityMainItemBean("1","zhuang2", "2013-11-10,17:00","研究生科技文化节专题系列讲座之“信息漫谈”","2013-11-20", "广州市华南理工大学逸夫科学馆107"));
		
	}
	/**
	 * OnItemClickListener里面接口
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		Log.i("zgr","点击一下,开始请求数据");
		String url=AppUtil.BASEURL_STRING+"/Home/Index/activity";
		
		Intent intent=new Intent(this.getActivity(), AContentActivity.class);
		intent.putExtra("postId", list.get(position).getpostId());//获取当前点击的帖子id
		Bundle bundle=new Bundle();
		bundle.putParcelable("item", list.get(position));//要求实现Parcelable接口
		intent.putExtras(bundle);
		startActivity(intent);
		
//		//下面这段是之前考虑的用webview来显示整个详情页的方法
//		Intent intent=new Intent(this.getActivity(), PostContentActivity.class);
//		//intent.putExtra("baseURL",baseURL);//把请求网址发过去
//		//这里需要把整个item现有的内容传过去
//		intent.putExtra("postId", list.get(position).getpostId());//获取当前点击的帖子id
//		Bundle bundle=new Bundle();
//		bundle.putParcelable("item", list.get(position));//要求实现Parcelable接口
//		bundle.putSerializable("bean", list.get(position));//要求实现Serializable接口
//		intent.putExtras(bundle);
//		startActivity(intent);//先跳转页面
		
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
		String idNow=list.get(0).getpostId();//当前边界id
		Log.i("zgr",list.get(0).toString());
		ArrayList<BasicNameValuePair> params=getBasicNameValuePairs(url, idNow, 1);
		new ActivityAsynctask().execute(params.get(0),params.get(1),params.get(2));
	}
	@Override
	public void onLoadMore() {
		//在这里请求网络
		Log.i("llb", "底部上拉请求网络");
//		refreshListView(new ArrayList<ActivityMainItemBean>());//刷新数据
		String page=list.get(list.size()-1).getpostId();
		Log.i("zgr",list.get(list.size()-1).toString());
		ArrayList<BasicNameValuePair> params=getBasicNameValuePairs(url, page, 2);
		new ActivityAsynctask().execute(params.get(0),params.get(1),params.get(2));
		
	}
	private void refreshListView(ArrayList<ActivityMainItemBean> newData,int code){
		switch (code) {
		case 100://下拉刷新，把数据挂在最前面
			list.addAll(0, newData);
			break;
		case 001://上拉刷新，把数据挂在最后
			list.addAll(list.size(), newData);
			break;
		}
		adapter.notifyDataSetChanged();
		listView.onRefreshComplete();
	}
	/**
	 * AsyncTask的代码简洁，但是相较于Handler+Thread的方式，更耗资源，灵活度稍差。一般在数据量不大的情况下使用
	 * 这个程序中，所有关于列表刷新的部分都采用AsyncTask，而内容详情相关的操作采用Handler+Thread
	 * @author llb
	 *
	 */
	class ActivityAsynctask extends AsyncTask<BasicNameValuePair, Void, String>{
		public ArrayList<ActivityMainItemBean> activityMainItemBeans;//用来返回取得的列表数据
		@Override
		protected String doInBackground(BasicNameValuePair... params) {
			//postByHttpClient(String url,NameValuePair...pairs)
			HttpEntity entity=null;
			try {
				entity = MyHttpClient.getByHttpClient(params[0].getValue(),params[1],params[2]);
			} catch (ClientProtocolException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return null;
			}
			//get方法请求数据
			String result="";
			try {
				result = AppUtil.entityToJsonString(entity);
			} catch (Exception e) {
				e.printStackTrace();
				Log.i("zgr","doInBackgroup wrong!");
				return null;
			}
			return result;
		}
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			int code=0;//返回的操作功能号
			if(null!=result || result==""){
				try {
					JSONObject jsonObject=new JSONObject(result);//先把返回的数据转成一个json对象，方便解析
					JsonDecodeUtil jsonDecodeUtil=new JsonDecodeUtil();
					code=Integer.parseInt(jsonObject.getString("code"));
					switch (code) {
					case 100://下拉活动列表信息请求成功
						activityMainItemBeans=jsonDecodeUtil.decodeListJson(jsonObject);//解析里面的result数据
						Log.i("zgr","onPostExecute="+result);
						freshData=activityMainItemBeans;//把得到的数据传进来
						break;
					case 001://上拉活动列表信息请求成功
						activityMainItemBeans=jsonDecodeUtil.decodeListJson(jsonObject);//解析里面的result数据
						Log.i("zgr","onPostExecute="+result);
						freshData=activityMainItemBeans;//把得到的数据传进来
						break;
					case 10101://表明没有新数据
//						Toast.makeText(ActivityFragment, "没有新数据", 0).show();//???????
						freshData.clear();//清空，表面没有新数据
						break;
					}
	    			refreshListView(freshData,code);//刷新页面
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else {
				Log.i("zgr","应该是网络之类的问题，待处理");
				refreshListView(freshData,code);//刷新页面
				//应该是网络异常之类的，不然至少有功能好，不会为空
			}
		}
	}
	/**
	 * 生成httpclient网络请求所需要的参数信息
	 * @param url请求url
	 * @param id 当前的边界item的id号
	 * @param tag 请求标记： 1-下拉刷新  2-上拉刷新
	 * @return ArrayList<BasicNameValuePair>
	 */
	private ArrayList<BasicNameValuePair> getBasicNameValuePairs(String url,String id,int tag){
		ArrayList<BasicNameValuePair> pairs;
		pairs=new ArrayList<BasicNameValuePair>();
		switch (tag) {
		case 1://下拉请求列表信息
			pairs.add(new BasicNameValuePair("url", url));
			pairs.add(new BasicNameValuePair("id", id));
			pairs.add(new BasicNameValuePair("code", "100"));//id=100表示这个请求是请求Activity下拉列表数据
			break;
		case 2://上拉请求列表信息
			pairs.add(new BasicNameValuePair("url", url));
			pairs.add(new BasicNameValuePair("id", id));
			pairs.add(new BasicNameValuePair("code", "001"));//id=001表示这个请求是请求Activity上拉刷新列表数据
			break;
		}
		return pairs;
	}
	
}
