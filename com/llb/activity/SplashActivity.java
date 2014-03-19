package com.llb.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;

public class SplashActivity extends Activity{
	private LinearLayout splash_layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		splash_layout=(LinearLayout) findViewById(R.id.splash_layout);
		
		AlphaAnimation aa=new AlphaAnimation(0f, 1.0f);
        aa.setDuration(2000);//动画持续2s钟
        splash_layout.startAnimation(aa);//给布局设置淡入的动画效果
        
		//延迟2s跳转到主页去，以后这部分得跟服务器进行通信等等才对
        new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent=new Intent(SplashActivity.this,MainActivity.class);
				startActivity(intent);
				finish();
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);//淡入淡出动画
			}
		}, 2*1000);
	}
}
