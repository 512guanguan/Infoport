package com.llb.fragment;

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
import com.llb.net.LoadData;

/**
 * 这个帖子内容详情页
 * @author llb
 *
 */

public class PostContentFragment extends Fragment {
	private WebView webView;
	private View view=null;
	
	//网络请求
	private LoadData loadData;
	public static Handler handler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Intent intent=this.getActivity().getIntent();
		String url=intent.getExtras().getString("url");//获得请求的url
		Log.i("zgr","url="+url);
		
		loadData=new LoadData(this.getActivity());
		loadData.getActivityContent(url,1);//请求详情信息
		handler=new Handler(){//响应请求
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				switch (msg.what) {
				case 1://请求详情信息返回  为缓存页面
					webView.loadDataWithBaseURL(null, loadData.result, "text/html", "utf-8", null);
					break;
				}
			}
		};
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("llb","oncreateView里面");
		if(view==null){
			view=inflater.inflate(R.layout.post_content, container,false);
			webView=(WebView) view.findViewById(R.id.webview);
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);//先移除
			Log.i("slide","ActivityFragment-removeView");
		}
		initWebView();
		return view;
	}
	
	
	
	private void initWebView() {
//		webView.setBackgroundColor(0);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true); 
		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setDefaultTextEncodingName("utf-8");
	}
	
	
	@Override
    public void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i("slide","PostContentFragment--onPause");
    }
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("slide","PostContentFragment--onStop");
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("slide","PostContentFragment--onDestroy");
	}
	
}
