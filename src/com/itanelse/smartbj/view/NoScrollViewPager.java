package com.itanelse.smartbj.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.view
 * @类名: NoScrollViewPager
 * @时间: 下午4:27:51
 * @作者: AnElse
 * 
 * @描述: 不可以滚动的,懒加载的viewpager
 * 
 * @当前版本号: $Rev: 8 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class NoScrollViewPager extends LazyViewPager
{
	public NoScrollViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev)
	{
		// 不拦截
		return false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev)
	{
		// 不消费
		return false;
	}

}
