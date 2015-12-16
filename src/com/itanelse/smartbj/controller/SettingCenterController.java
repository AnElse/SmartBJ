package com.itanelse.smartbj.controller;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.controller
 * @类名: HomeController
 * @时间: 下午6:00:20
 * @作者: AnElse
 * 
 * @描述: 设置中心controller
 *
 * @当前版本号: $Rev: 7 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 *
 */
public class SettingCenterController extends TabController
{

	public SettingCenterController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initContentView(Context context)
	{
		mIvMenu.setVisibility(View.GONE);
		
		TextView tv = new TextView(context);
		tv.setText("设置中心");
		tv.setTextSize(24);
		tv.setGravity(Gravity.CENTER);
		tv.setTextColor(Color.RED);
		return tv;
	}
}
