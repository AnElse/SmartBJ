package com.itanelse.smartbj.utils;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.utils
 * @类名: ActivityCollector
 * @时间: 上午9:26:30
 * @作者: AnElse
 * 
 * @描述: 活动管理类,可以用来随时随地退出程序
 * 
 * @当前版本号: $Rev: 5 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class ActivityCollectorManager {
	public static List<Activity>	activities	= new ArrayList<Activity>();
	
	/**
	 * 向活动管理器List添加一个活动
	 * @param activity 添加的活动
	 */
	public static void addActivity(Activity activity) {
		activities.add(activity);
	}
	
	/**
	 * 把活动管理器List中的一个活动移除
	 * @param activity 移除的活动
	 */
	public static void removeActivity(Activity activity) {
		activities.remove(activity);
	}
	
	/**
	 * 把活动管理器List中的所有活动销毁
	 */
	public static void finishAll() {
		for (Activity activity : activities) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}
}
