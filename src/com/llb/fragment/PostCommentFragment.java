package com.llb.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.llb.activity.R;
import com.llb.net.LoadData;
public class PostCommentFragment extends Fragment {
	private View view=null;
	
	//网络请求
	private LoadData loadData;
	public static Handler handler;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		Intent intent=this.getActivity().getIntent();
//		String url=intent.getExtras().getString("url");//获得请求的url
//		Log.i("zgr","url="+url);
//		
//		loadData=new LoadData(this.getActivity());
//		loadData.getActivityContent(url,1);//请求详情信息
//		handler=new Handler(){//响应请求
//			@Override
//			public void handleMessage(Message msg) {
//				// TODO Auto-generated method stub
//				super.handleMessage(msg);
//				switch (msg.what) {
//				case 1://请求详情信息返回
//					webView.loadDataWithBaseURL(null, loadData.result, "text/html", "utf-8", null);
//					break;
//				}
//			}
//		};
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.i("llb","PostCommentFragment oncreateView里面");
		if(view==null){
			view=inflater.inflate(R.layout.post_comment, container,false);
		}
		ViewGroup parent = (ViewGroup) view.getParent();
		if(parent!=null){
			parent.removeView(view);//先移除
			Log.i("slide","PostCommentFragment-removeView");
		}
		return view;
	}
	
	@Override
    public void onPause()
    {
        // TODO Auto-generated method stub
        super.onPause();
        Log.i("slide","PostCommentFragment--onPause");
    }
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.i("slide","PostCommentFragment--onStop");
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("slide","PostCommentFragment--onDestroy");
	}
	
}
