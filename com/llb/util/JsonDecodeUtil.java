package com.llb.util;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.llb.entity.ActivityMainItemBean;

/**
 * 主要用来解析服务器接收的json数据
 * @author llb
 *
 */
public class JsonDecodeUtil {
	/**
	 * 取出json里面的result部分内容，封装在一个ArrayList里面
	 * @param response 待解析的json数据
	 * @return ArrayList<ActivityMainItemBean>
	 * @throws JSONException
	 */
	public ArrayList<ActivityMainItemBean> decodeListJson(JSONObject response) throws JSONException{
		JSONArray messageJsonArray=response.getJSONArray("result");//返回的列表信息
		JSONObject itemJsonObject;
		ArrayList<ActivityMainItemBean> message = new ArrayList<ActivityMainItemBean>();//用来存储result信息
		ActivityMainItemBean itemBean;//存储每一个item信息
		for(int index=0;index<messageJsonArray.length();index++){//逐个解析jsonarray里面的item对象
			itemJsonObject=messageJsonArray.getJSONObject(index);
			itemBean=new ActivityMainItemBean(itemJsonObject.getString("id"),itemJsonObject.getString("nickname"), 
					itemJsonObject.getString("createtime"), itemJsonObject.getString("title"), 
					itemJsonObject.getString("time"), itemJsonObject.getString("location"));
			message.add(itemBean);//
		}
		return message;
	}
//	public ActivityListEntity decodeListJson(JSONObject response) throws JSONException{
//		String code=response.getString("code");//功能号
//		JSONArray messageJsonArray=response.getJSONArray("result");//返回的列表信息
//		JSONObject itemJsonObject;
//		ArrayList<ActivityMainItemBean> message = null;//用来存储result信息
//		ActivityMainItemBean itemBean;//存储每一个item信息
//		for(int index=0;index<messageJsonArray.length();index++){//逐个解析jsonarray里面的item对象
//			itemJsonObject=messageJsonArray.getJSONObject(index);
//			itemBean=new ActivityMainItemBean(itemJsonObject.getString("id"),itemJsonObject.getString("nickname"), 
//					itemJsonObject.getString("createtime"), itemJsonObject.getString("title"), 
//					itemJsonObject.getString("time"), itemJsonObject.getString("location"));
//			message.add(itemBean);//
//		}
//		ActivityListEntity activityListEntity=new ActivityListEntity();
//		activityListEntity.setCode(code);
//		activityListEntity.setMessage(message);
//		return activityListEntity;
//	}

}
