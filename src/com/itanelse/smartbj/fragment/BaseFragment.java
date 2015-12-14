package com.itanelse.smartbj.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.fragment
 * @类名: BaseFragment
 * @时间: 下午9:14:37
 * @作者: AnElse
 * 
 * @描述: 所有fragment的基类
 * 
 * @当前版本号: $Rev$
 * @更新人: $Author$
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public abstract class BaseFragment extends Fragment
{
	protected FragmentActivity	mActivity;	// 获取宿主

	/**
	 * 说明挂载(onAttach())已经完成,可以获得宿主
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
	}

	/**
	 * 当碎片加载视图到宿主activity上时调用
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return initView();
	}

	/**
	 * 抽象出初始化组件方法,让每个fragment子类去实现自己的界面布局
	 */
	public abstract View initView();

	/**
	 * 做一些数据的加载,当宿主activity的oncreate()方法执行完毕后调用
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);
		initData();
		initEvent();
	}

	/**
	 * 如果子类需要加载数据，就复写此方法
	 */
	public void initData()
	{

	}
	
	/**
	 * 如果子类需要加载数据，就复写此方法
	 */
	public void initEvent()
	{

	}
}
