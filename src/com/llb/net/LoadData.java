package com.llb.net;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.llb.entity.ActivityMainItemBean;
import com.llb.fragment.ActivityFragment;
import com.llb.fragment.PostContentFragment;
import com.llb.util.JsonDecodeUtil;

public class LoadData {
	private RequestQueue queue;
	public String result=null;
	public ArrayList<ActivityMainItemBean> activityMainItemBeans;//用来返回取得的列表数据
	public LoadData(Context context){
		queue=Volley.newRequestQueue(context);//请求队列
	}
	/**
	 * 请求帖子列表信息，感觉还需要传入当前的最新或者最后面帖子的id，避免取重复数据
	 * @param url请求url
	 * @param page 请求的页码
	 * @param tag 请求发起页标记： 活动-100、工作-200、圈子-300
	 * @throws JSONException
	 */
	public void getPostList(String url,int page,int tag){
		JSONObject jsonObject=new JSONObject();
		switch (tag) {
		case 100://请求活动页的列表信息
			try {
				jsonObject.put("page", page);//请求页码
				jsonObject.put("code", 100);
				Log.i("zgr","上传的数据为："+jsonObject.toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}//id=100表示这个请求是请求Activity列表数据
			break;
		}
		JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Method.GET, url, jsonObject, new Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response, boolean hasChanged) {
				Log.i("zgr","得到的json="+response.toString());
				try {
					String c=(String)response.get("code");
					int code=Integer.parseInt(c);//先取出功能号来检查请求类型
					switch (code) {
					case 101://活动列表信息请求成功
						JsonDecodeUtil jsonDecodeUtil=new JsonDecodeUtil();
						activityMainItemBeans=jsonDecodeUtil.decodeListJson(response);//解析里面的result数据
						ActivityFragment.handler.sendEmptyMessage(101);
						break;
					case 10101://返回的数据为null
						ActivityFragment.handler.sendEmptyMessage(10101);//请求失败
						break;
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("zgr", "请求失败:"+error.getMessage());
				ActivityFragment.handler.sendEmptyMessage(10100);//请求失败
			}
		});
		queue.add(jsonObjectRequest);
		Log.i("zgr", "请求提交");
	}
	/**
	 * 获取帖子的内容详情
	 * @param url 请求地址
	 */
	public void getActivityContent(String url,final int tag){
		StringRequest stringRequest=new StringRequest(Method.GET, url, new Listener<String>() {
			@Override
			public void onResponse(String response, boolean hasChanged) {
				result=response;
				switch (tag) {
				case 1://表明是在活动Activity里面请求详情信息
					result=response.toString();//返回数据
					PostContentFragment.handler.sendEmptyMessage(1);
					break;
				case 11://请求Activity活动里面的信息列表
					
					break;
				case 2://工作的具体信息
					break;
				case 3://圈子的具体信息
					break;
				}
//				ActivityFragment.handler.sendEmptyMessage(1);
			}
		}, new ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				Log.i("llb","请求出错了");
				result=null;
			}
		});
		queue.add(stringRequest);
	}
}

