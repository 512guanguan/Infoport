package com.llb.activity;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.Toast;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.llb.adapter.LeftViewAdapter;
import com.llb.adapter.MyViewPagerAdapter;
import com.llb.entity.LeftViewItemBean;
import com.llb.fragment.ActivityFragment;
import com.llb.fragment.FriendFragment;
import com.llb.fragment.JobFragment;

public class MainActivity extends SlidingFragmentActivity implements OnClickListener,
		OnPageChangeListener {//, OnNavigationListener不需要ActionBar导航列表
	private ViewPager pager;
	private PagerAdapter mAdapter;
	private float screenWidth;// 屏幕宽度
	private SlidingMenu sm;
	private ListView leftListView;// 左边页面的listview
	private LeftViewAdapter adapter;
	private ArrayList<LeftViewItemBean> list;
	// ActionBar
	private ImageButton action_add,action_list;//添加和列表按钮
	private Button popupButton;//顶部菜单项的弹出窗口上面的按钮
	private View popView;//顶部需要弹出的view
//	private float xMove;//用来表示popupwindow显示时需要左移多少
	private LayoutInflater inflater;
//	private String[] mLocations;// 导航下拉列表项

	private ArrayList<Fragment> fragments;
	private ArrayList<RadioButton> title = new ArrayList<RadioButton>();// 三个标题
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_main);///slidingmenu里面重写了
		initSlidingMenu();// 初始化右边滑屏，这里面有setContentView函数，必须最前面
		initHead();//初始化头部
		initView();// 初始化控件
		initLeftView();// 左页面
		initTitle();
		initViewPager();
//		initActionBar();
	}

	/**
	 * 初始化视图
	 */
	private void initView() {
		// TODO Auto-generated method stub
		pager = (ViewPager) findViewById(R.id.pager);// 初始化控件
		fragments = new ArrayList<Fragment>();// 初始化数据
		fragments.add(new ActivityFragment());
		fragments.add(new JobFragment());
		fragments.add(new FriendFragment());

	}

	/**
	 * 初始化ViewPager
	 */
	private void initViewPager() {
		// TODO Auto-generated method stub
		mAdapter = new MyViewPagerAdapter(getSupportFragmentManager(),
				fragments);
		pager.setAdapter(mAdapter);
		pager.setOnPageChangeListener(this);
		pager.setCurrentItem(0);// 设置成当前第一个
	}

	/**
	 * 初始化几个用来显示title的RadioButton
	 */
	private void initTitle() {
		title.add((RadioButton) findViewById(R.id.title1));// 三个title标签
		title.add((RadioButton) findViewById(R.id.title2));
		title.add((RadioButton) findViewById(R.id.title3));
		title.get(0).setOnClickListener(new MyOnClickListener(0));// 设置响应
		title.get(1).setOnClickListener(new MyOnClickListener(1));
		title.get(2).setOnClickListener(new MyOnClickListener(2));
	}

	/**
	 * 初始化开源组件SlidingMenu
	 */
	private void initSlidingMenu() {
		// 实例化滑动菜单对象
		sm = getSlidingMenu();
		setContentView(R.layout.activity_main);// 设置当前的视图
		setBehindContentView(R.layout.left);// 设置左页视图

		// ActionBar actionBar=getActionBar();

		sm.setMode(SlidingMenu.LEFT);
		// 设置滑动阴影的宽度
		// sm.setShadowWidthRes(R.dimen.shadow_width);
		// 设置滑动阴影的图像资源
		// sm.setShadowDrawable(R.drawable.shadow);
		// 设置滑动菜单视图的宽度
		// sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		sm.setFadeDegree(0.5f);
		// 设置触摸屏幕的模式
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// .TOUCHMODE_MARGIN);
		// sm.setTouchModeBehind(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置左页的响应范围
		// sm.setTouchmodeMarginThreshold(60);//这个设置的是隔屏幕边缘多远开始响应
		// sm.setBehindOffset(50);//设置左页距离屏幕右边缘的距离，右页会补上

		WindowManager wManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wManager.getDefaultDisplay().getWidth();// 获取屏幕的宽度
		sm.setBehindWidth((int) (screenWidth * 0.8));// 设置左页的宽度
	}

	private void initLeftView() {
		// TODO Auto-generated method stub
		// 左边视图
		leftListView = (ListView) sm.getMenu().findViewById(R.id.left_listView);
		list = new ArrayList<LeftViewItemBean>();
		list.add(new LeftViewItemBean(R.drawable.left_item1_hot, "热门帖子"));
		list.add(new LeftViewItemBean(R.drawable.left_item2_mypage, "我的帖子"));
		list.add(new LeftViewItemBean(R.drawable.left_item3_mycol, "我的收藏"));
		list.add(new LeftViewItemBean(R.drawable.left_item4_setting, "设置"));
		list.add(new LeftViewItemBean(R.drawable.left_item5_exit, "注销"));
		adapter = new LeftViewAdapter(sm.getContext(), list);
		leftListView.setAdapter(adapter);
	}

	/**
	 * 重写OnClickListener的响应函数，主要目的就是实现点击title时，pager会跟着响应切换
	 * @author llb
	 */
	private class MyOnClickListener implements OnClickListener {
		private int index;
		public MyOnClickListener(int index) {
			this.index = index;
		}
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			pager.setCurrentItem(index);// 把viewpager的视图切过去，实现捏造title跟pager的联动
			title.get(index).setChecked(true);// 设置被选中，否则布局里面的背景不会切换
		}

	}

	/**
	 * 下面三个是OnPageChangeListener的接口函数
	 */
	@Override
	public void onPageScrollStateChanged(int arg0) {
	}
	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}
	@Override
	public void onPageSelected(int arg0) {
		Log.i("slide", "onPageSelected+agr0=" + arg0);
		title.get(arg0).setChecked(true);// 保持页面跟按钮的联动
		if (arg0 == 0) {
			// 如果当前是第一页，那么设置触摸屏幕的模式为全屏模式
			sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置成全屏响应
		} else {
			// 如果不是第一页，设置触摸屏幕的模式为边缘60px的地方
			sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
			sm.setTouchmodeMarginThreshold(60);
		}
	}
	/**
	 *初始化页面的头部
	 */
	private void initHead(){// onCreateOptionsMenu(Menu menu) {
		//初始化头部控件
		action_add=(ImageButton) findViewById(R.id.action_add);
		action_list=(ImageButton) findViewById(R.id.action_list);
		action_add.setOnClickListener(this);
		action_list.setOnClickListener(this);
		///初始化popupwindow
		
//		//计算popupwindow在x方向的左移量xMove
//		DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//		Log.i("llb", "屏幕宽度="+metric.widthPixels);
//		Log.i("llb", "popview的右边距="+popView.getRight());
//		int width=(int) (metric.widthPixels/1.5);
//		LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);

		inflater=LayoutInflater.from(this);//this.getLayoutInflater();
		popView=inflater.inflate(R.layout.actionbar_popup, null);
		popupButton=(Button) popView.findViewById(R.id.popup_button);//popupwindow上面的按钮
		popupButton.setOnClickListener(this);//设置popupwindow上面的按钮监听
	}
	//实现OnClickListener接口函数
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.popup_button:
			Log.i("llb","我是popupwindow上的Button");
			break;
		case R.id.action_add://顶部编辑按钮响应
			Toast.makeText(this, "你需要添加信息了",0).show();
			break;
		case R.id.action_list://顶部菜单按钮响应
			Toast.makeText(this, "来看看列表信息",0).show();
			//弹出popupwindow
			PopupWindow popupWindow=new PopupWindow(popView,LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			popupWindow.setOutsideTouchable(true);//默认点击外围没反应
			popupWindow.setFocusable(true);//默认不可点击
//			pop.setAnimationStyle(R.style.PopupAnimation);//设置动画
			popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			popupWindow.showAsDropDown(findViewById(R.id.action_list),-10,0);//显示位置还需要改
			break;
		}
	}

}
