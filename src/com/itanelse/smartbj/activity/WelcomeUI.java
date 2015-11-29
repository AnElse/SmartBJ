package com.itanelse.smartbj.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

import com.itanelse.smartbj.R;
import com.itanelse.smartbj.base.BaseActivity;
import com.itanelse.smartbj.utils.CacheUtils;
import com.itanelse.smartbj.utils.LogUtils;
import com.itanelse.smartbj.utils.MyConstants;

/**
 * 
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj
 * @类名: SplashActivity
 * @时间: 上午8:53:44
 * @作者: AnElse
 * 
 * @描述: 欢迎界面的实现
 * 
 * @当前版本号: $Rev: 5 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class WelcomeUI extends BaseActivity
{

	private View					mRootView;
	private static final long		DURATION	= 2000;						// crtl+shift+X/Y大小写切换
	protected static final String	TAG			= WelcomeUI.class.getName();
	private AnimationSet			set;										// 动画集

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		// 去掉标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.welcome_ui);

		initView();// 初始化组件
		initAnim();// 初始化动画
		initEvent();// 初始化事件
	}

	/**
	 * 初始化事件
	 */
	private void initEvent()
	{
		// 动画完成后要进行页面的跳转,我们进行动画的监听
		set.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation)
			{
				// TODO Auto-generated method stub

			}

			@Override
			public void onAnimationRepeat(Animation animation)
			{
				// TODO Auto-generated method stub

			}

			// 当动画结束时
			@Override
			public void onAnimationEnd(Animation animation)
			{
				// 页面跳转
				// 记录是否是第一次打开应用

				// 内存 ---》快 ，不能持久化
				// 持久化的存储 --》sp,sdcard,sqlite,net
				if (CacheUtils.isFirstTime(WelcomeUI.this, MyConstants.KEY_IS_FIRST, true))
				{
					// 进入引导界面
					LogUtils.d(TAG, "进入引导界面");
					Intent intent = new Intent(WelcomeUI.this, GuideUI.class);
					startActivity(intent);
					finish();
				}
				else
				{
					// 进入主界面
					LogUtils.d(TAG, "进入主界面");
					Intent intent = new Intent(WelcomeUI.this, MainUI.class);
					startActivity(intent);
					finish();
				}
			}
		});
	}

	/**
	 * 初始化动画
	 */
	private void initAnim()
	{
		// 旋转动画
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(DURATION);
		ra.setFillAfter(true);

		// 渐变动画
		AlphaAnimation aa = new AlphaAnimation(0.0f, 1.0f);
		aa.setDuration(DURATION);
		aa.setFillAfter(true);

		// 缩放(比例进行缩放)
		ScaleAnimation sa = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(DURATION);
		sa.setFillAfter(true);

		// 动画集
		// AnimationSet的构造函数有带一个参,Boolean值,true即分享插入器
		set = new AnimationSet(true);
		set.setInterpolator(new BounceInterpolator());
		set.addAnimation(ra);
		set.addAnimation(aa);
		set.addAnimation(sa);

		mRootView.startAnimation(set);

	}

	/**
	 * 初始化组件
	 */
	private void initView()
	{
		mRootView = findViewById(R.id.welcome_root);
	}

}
