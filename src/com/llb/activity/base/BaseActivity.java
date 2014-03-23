package com.llb.activity.base;

import android.app.Activity;
import android.widget.Toast;

public class BaseActivity extends Activity{
	/**
	 * 显示短时间的toast提示
	 * @param message 提示信息
	 */
	public void showShortToast(String message){
		Toast.makeText(this, message, 0).show();
	}
}
