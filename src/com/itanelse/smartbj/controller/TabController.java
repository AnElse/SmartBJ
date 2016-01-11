package com.itanelse.smartbj.controller;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.itanelse.smartbj.R;
import com.itanelse.smartbj.activity.MainUI;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.controller
 * @类名: TabController
 * @时间: 上午11:57:38
 * @作者: AnElse
 * 
 * @描述: 所有controller界面的基类
 * 
 * @当前版本号: $Rev: 7 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public abstract class TabController implements OnClickListener
{
	protected View			mRootView;
	protected Context		mContext;
	protected ImageView		mIvMenu;
	protected TextView		mTvTitle;
	protected FrameLayout	mContentContainer;

	public TabController(Context context) {
		this.mContext = context;
		mRootView = initView(context);
	}

	/**
	 * 让子类去实现自己的布局---共同的top title
	 * 
	 * @param context
	 * @return
	 */
	public View initView(Context context)
	{
		View view = View.inflate(mContext, R.layout.base_tab, null);
		// 孩子有不同的区内容
		mIvMenu = (ImageView) view.findViewById(R.id.base_tab_iv_menu);
		mTvTitle = (TextView) view.findViewById(R.id.base_tab_tv_title);
		mContentContainer = (FrameLayout) view.findViewById(R.id.base_tab_content_container);

		View contentView = initContentView(context);
		// 添加到布局中
		mContentContainer.addView(contentView);

		// 设置点击事件
		mIvMenu.setOnClickListener(this);
		return view;
	}

	protected abstract View initContentView(Context context);

	public View getRootView()
	{
		return mRootView;
	}

	/**
	 * 数据加载的方法,如果子类有数据加载,那么就去复写
	 */
	public void initDate()
	{

	}

	/**
	 * 让对应的tab去选中menuitem
	 */
	public void switchMenuItem(int menuItem)
	{

	}

	@Override
	public void onClick(View v)
	{
		if (v == mIvMenu)
		{
			toggleMenu();
		}
	}

	private void toggleMenu()
	{
		((MainUI)mContext).getSlidingMenu().toggle();
	}
}
