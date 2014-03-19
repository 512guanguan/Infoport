package com.llb.activity;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import com.llb.adapter.MyViewPagerAdapter;
import com.llb.fragment.PostCommentFragment;
import com.llb.fragment.PostContentFragment;

public class PostContentActivity extends FragmentActivity implements OnPageChangeListener{
	private ArrayList<Fragment> fragments;
	private ViewPager pager;
	private PagerAdapter mAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.postcontent);
		initView();
	}
	/**
	 * 初始化两个Fragment的view
	 */
	private void initView() {
		Log.i("zgr","在PostContentActivity的initView()里面");
		pager = (ViewPager) findViewById(R.id.post_pager);// 初始化控件
		fragments=new ArrayList<Fragment>();
		fragments.add(new PostContentFragment());
		fragments.add(new PostCommentFragment());
		mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
				fragments);
		pager.setAdapter(mAdapter);
		pager.setOnPageChangeListener(this);//设置viewpager切换等动作监听
		pager.setCurrentItem(0);// 设置成当前第一个
	}
	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
	}
	@Override
	public void onPageSelected(int arg0) {
		//可以在这里设置一些响应事件
	}
}
