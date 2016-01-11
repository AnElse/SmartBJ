package com.itanelse.smartbj.controller;

import android.content.Context;
import android.view.View;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.controller
 * @类名: MenuController
 * @时间: 下午12:01:54
 * @作者: AnElse
 * 
 * @描述: 菜单基类Controller
 * 
 * @当前版本号: $Rev$
 * @更新人: $Author$
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public abstract class MenuController
{
	protected Context	mContext;
	protected View		mRootView;

	public MenuController(Context context) {
		this.mContext = context;
		mRootView = initView(context);
	}

	/**
	 * 让子类去实现UI
	 * 
	 * @param context
	 * @return
	 */
	protected abstract View initView(Context context);

	/**
	 * 初始化数据
	 */
	public void initData()
	{

	}

	public View getRootView()
	{
		return mRootView;
	}
}
