package com.itanelse.smartbj.base;

import android.app.Activity;
import android.os.Bundle;

import com.itanelse.smartbj.utils.ActivityCollectorManager;
import com.itanelse.smartbj.utils.LogUtils;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.base
 * @类名: BaseActivity
 * @时间: 上午9:13:05
 * @作者: AnElse
 * 
 * @描述: 所有UI的父类,用来知晓当前是在哪一个活动
 * 
 * @当前版本号: $Rev: 5 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class BaseActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtils.d("BaseActivity", getClass().getSimpleName());
		ActivityCollectorManager.addActivity(this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityCollectorManager.removeActivity(this);
	}
}
