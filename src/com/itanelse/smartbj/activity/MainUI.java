package com.itanelse.smartbj.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

import com.itanelse.smartbj.R;
import com.itanelse.smartbj.fragment.ContentFragment;
import com.itanelse.smartbj.fragment.MenuFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.activity
 * @类名: Main
 * @时间: 上午12:50:18
 * @作者: AnElse
 * 
 * @描述: 主界面的开发
 * 
 * @当前版本号: $Rev: 5 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class MainUI extends SlidingFragmentActivity
{
	private static final String	TAG_LEFT	= "left";
	private static final String	TAG_CONTENT	= "content";

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		// 理解above和behind,above指右侧界面,behind指左侧界面
		// 设置右侧界面
		setContentView(R.layout.main_ui);
		// 设置左侧界面
		setBehindContentView(R.layout.main_left_ui);
		// 获得slidingmenu
		SlidingMenu slidingMenu = getSlidingMenu();

		// 设置左右两侧界面的距离
		// slidingMenu.setBehindWidth(150);
		slidingMenu.setBehindOffset(300);

		// 设置slidingmenu的抽屉模式:左边,右边或者左右都可以
		slidingMenu.setMode(SlidingMenu.LEFT);

		// 设置TouthModeAbove
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		// 设置scroll
		slidingMenu.setBehindScrollScale(1.0f);
		// slidingMenu.setShadowDrawable(resId);
		// slidingMenu.setFadeDegree(f);

		initFragment();// 用fragment加载到两边界面(FrameLayout布局)
	}

	private void initFragment()
	{
		// fragmentmanager
		FragmentManager fm = getSupportFragmentManager();

		FragmentTransaction transaction = fm.beginTransaction();
		// 加载左侧
		transaction.add(R.id.main_left_container, new MenuFragment(), TAG_LEFT);
		// 加载内容
		transaction.add(R.id.main_content_container, new ContentFragment(), TAG_CONTENT);

		transaction.commit();
	}
}
