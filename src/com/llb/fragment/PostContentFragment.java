package com.llb.fragment;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.llb.activity.R;
import com.llb.app.MyHttpClient;
import com.llb.entity.ActivityMainItemBean;
import com.llb.net.LoadData;
import com.llb.util.AppUtil;
import com.llb.util.JsonDecodeUtil;

/**
 * 这个帖子内容详情页
 * @author llb
 *
 */

public class PostContentFragment extends Fragment {
	private WebView webView;
	private View view=null;
	
	//网络请求
	static final int SUCCESS=1;//标记请求结果
	static final int FAILED=0;
	private LoadData loadData;
	public static Handler handler;
	private String postId;//帖子id
	//private String baseURL;
	private String url=AppUtil.BASEURL_STRING+"/Home/PostContent/postcontent";
	private String result;//请求的json数据
	private String content;//帖子详情
	private ActivityMainItemBean item;//当前点击item已有的信息
	
	private Handler mHandler=new Handler(){
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SUCCESS:
				result=(String)msg.obj;//请求得到的json数据
				Log.i("llb","得到的result="+result);
				try {
					//开始解析json数据
					JSONObject jsonObject = new JSONObject(result);
					JsonDecodeUtil jsonDecodeUtil=new JsonDecodeUtil();
					int code=Integer.parseInt(jsonObject.getString("code"));
					JSONArray jsonArray=jsonObject.getJSONArray("result");
					content="<html><body>"+jsonArray.getJSONObject(0).getString("content")+"</body></html>";
					//先把返回的数据转成一个json对象，方便解析
					webView.loadData(createHtml(content), "text/html;charset=UTF-8", null);//Html.toHtml(result);
					//webView.loadData(content, "text/html", "utf-8");//这样会出现乱码
					//上述loadData是不支持中文的，但不值为何在模拟器上又可以支持，换用loadDataWithBaseURL一点事情都没有了
					//webView.loadDataWithBaseURL(null, content, "text/html",HTTP.UTF_8, null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case FAILED:
				Log.i("llb","请求失败");
				break;
			default:
				break;
			}
		};
	};
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent=this.getActivity().getIntent();
		//url=intent.getExtras().getString("url");//获得传进来的请求url
		postId=intent.getExtras().getString("postId");//帖子id
		//获取之前塞进去的Parcelable
		item=(ActivityMainItemBean)intent.getParcelableExtra("item");
		//、ActivityMainItemBean bean=(ActivityMainItemBean) intent.getSerializableExtra("bean");
		//Log.i("llb",item.toString());
		//Log.i("llb",bean.toString());
		Log.i("zgr","url="+url+" "+"postId="+postId);
		
		//请求网络
		Thread thread=new Thread(new Runnable() {
			@Override
			public void run() {
				NameValuePair pair0=new BasicNameValuePair("id", postId);
				NameValuePair pair1=new BasicNameValuePair("code", "102");
				String result=null;//返回的json数据
				try {
					HttpEntity entity=MyHttpClient.getByHttpClient(url, pair0,pair1);
					result=AppUtil.entityToJsonString(entity);
				}catch (Exception e) {
					e.printStackTrace();
					Log.i("zgr","Exception!");
					mHandler.obtainMessage(FAILED).sendToTarget();//请求失败，发送信息给UI线程
				}
				mHandler.obtainMessage(SUCCESS, result).sendToTarget();//请求成功，把数据发回给UI线程
			}
		});
		thread.start();//开始线程
		
		//volley时代的产物
//		loadData=new LoadData(this.getActivity());
//		loadData.getActivityContent(url,1);//请求详情信息
//		handler=new Handler(){//响应请求
//			@Override
//			public void handleMessage(Message msg) {
//				// TODO Auto-generated method stub
//				super.handleMessage(msg);
//				switch (msg.what) {
//				case 1://请求详情信息返回  为缓存页面
//					webView.loadDataWithBaseURL(null, loadData.result, "text/html", "utf-8", null);
//					break;
//				}
//			}
//		};
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.i("llb","oncreateView里面");
		if(view==null){
			view=inflater.inflate(R.layout.post_content, container,false);
			webView=(WebView) view.findViewById(R.id.webview);
			initWebView();
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);//先移除
			Log.i("slide","ActivityFragment-removeView");
		}
		return view;
	}
	
	private void initWebView() {
//		webView.setBackgroundColor(0);
		//webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true); 
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setDefaultTextEncodingName("utf-8");
	}
	private String createHtml(String content){
		StringBuilder builder=new StringBuilder();
		builder.append("<html><body>");
		builder.append("<center><H3>"+item.getActivity_item_activityname()+"</H3></center><br/>");
		builder.append(item.getActivity_item_authorname()+" 发表于 "+item.getActivity_item_authortime()+"<hr width=\"100%\"/>");
		builder.append("时间："+item.getActivity_item_activitytime()+"<hr width=\"100%\"/>");
		builder.append("地点："+item.getActivity_item_activityaddr()+"<hr width=\"100%\"/>");
		builder.append("活动详情："+"<br/>"+"<p>"+content+"</p><hr width=\"100%\"/>");
		builder.append("主办单位：<hr width=\"100%\"/>");
		builder.append("协办单位：<hr width=\"100%\"/>");
		return builder.toString();
	}
	
	
}
