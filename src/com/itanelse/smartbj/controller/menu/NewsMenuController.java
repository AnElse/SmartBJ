package com.itanelse.smartbj.controller.menu;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.itanelse.smartbj.controller.MenuController;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.controller.menu
 * @类名: NewsMenuController
 * @时间: 下午12:11:52
 * @作者: AnElse
 * 
 * @描述: 新闻菜单controller
 * 
 * @当前版本号: $Rev$
 * @更新人: $Author$
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class NewsMenuController extends MenuController
{

	public NewsMenuController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initView(Context context)
	{
		TextView tv = new TextView(context);
		tv.setText("新闻页面");
		tv.setTextSize(24);
		tv.setTextColor(Color.RED);
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

}
