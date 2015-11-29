package com.itanelse.smartbj.activity;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.itanelse.smartbj.R;
import com.itanelse.smartbj.base.BaseActivity;
import com.itanelse.smartbj.utils.CacheUtils;
import com.itanelse.smartbj.utils.MyConstants;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.activity
 * @类名: GuideUI
 * @时间: 下午2:03:19
 * @作者: AnElse
 * 
 * @描述: 引导界面
 * 
 * @当前版本号: $Rev: 5 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class GuideUI extends BaseActivity implements OnClickListener
{
	private ViewPager		mPager;
	private int[]			imgRes	= new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3 };
	private List<ImageView>	mImgDatas;
	private LinearLayout	mPointContainer;
	private View			mSeletedPoint;
	private int				mPointSpace;																		// 两点间滑动的距离
	private Button			mBtnStart;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.guide_ui);
		initView();// 初始化组件
		initData();// 初始化数据
		initEvent();// 初始化事件
	}

	/**
	 * 初始化事件
	 */
	private void initEvent()
	{
		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			// 当页面被选中时调用
			@Override
			public void onPageSelected(int position)
			{
				// if (position == (imgRes.length-1))
				// {
				// mBtnStart.setVisibility(View.VISIBLE);
				// }
				// else
				// {
				// mBtnStart.setVisibility(View.INVISIBLE);
				// }
				mBtnStart.setVisibility(position == (imgRes.length - 1) ? View.VISIBLE : View.INVISIBLE);
			}

			// 当page滑动时
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
			{
				// 滚动时
				// @positionOffset :滚动的比例
				// @positionOffsetPixels: 滚动的像素
				// 1. 计算需要移动距离
				// 通过两个点间的距离计算 和 positionOffset，移动的距离
				int left = (int) (mPointSpace * positionOffset + 0.5f);

				// 2. 改变选中点的marginleft
				RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) mSeletedPoint.getLayoutParams();
				params.leftMargin = left + mPointSpace * position;

				mSeletedPoint.setLayoutParams(params);
			}

			@Override
			public void onPageScrollStateChanged(int state)
			{

			}
		});

		// 设置view的树的观察者来获取到全局监听者
		mSeletedPoint.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout()
			{
				mPointSpace = mPointContainer.getChildAt(1).getLeft() - mPointContainer.getChildAt(0).getLeft();
				mSeletedPoint.getViewTreeObserver().removeGlobalOnLayoutListener(this);
			}
		});

		// 设置点击开始体验跳转到主界面
		mBtnStart.setOnClickListener(this);
	}

	/**
	 * 初始化数据
	 */
	private void initData()
	{
		// 对数据进行初始化
		mImgDatas = new ArrayList<ImageView>();
		for (int i = 0; i < imgRes.length; i++)
		{
			ImageView iv = new ImageView(this);
			iv.setImageResource(imgRes[i]);
			iv.setScaleType(ScaleType.FIT_XY);
			mImgDatas.add(iv);

			// 动态的添加点
			View view = new View(this);
			view.setBackgroundResource(R.drawable.point_normal);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(10, 10);
			if (i != 0)
			{
				params.leftMargin = 10;

			}
			mPointContainer.addView(view, params);
		}

		// 给viewpager添加数据
		mPager.setAdapter(new GuidePagerAdapter());// --->List
	}

	class GuidePagerAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mImgDatas != null) { return mImgDatas.size(); }
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			// 判断是否有缓存(固定写法)
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			ImageView iv = mImgDatas.get(position);// 拿到当前的实例
			// 用pager添加
			// mPager.addView(iv);
			container.addView(iv);// container相当于mPager
			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}
	}

	/**
	 * 初始化组件
	 */
	private void initView()
	{
		mPager = (ViewPager) findViewById(R.id.guide_pager);
		mPointContainer = (LinearLayout) findViewById(R.id.gudie_point_container);
		mSeletedPoint = findViewById(R.id.guide_point_selected);
		mBtnStart = (Button) findViewById(R.id.guide_btn_start);
	}

	@Override
	public void onClick(View v)
	{
		if (v == mBtnStart)
		{
			doJump();// 跳转页面的方法
		}
	}

	private void doJump()
	{
		// 1,页面跳转到主界面
		Intent intent = new Intent(GuideUI.this, MainUI.class);
		startActivity(intent);
		finish();
		// 2,记录住cache的状态,不是第一次进入主界面
		CacheUtils.setBoolean(getApplicationContext(), MyConstants.KEY_IS_FIRST, false);
	}
}
