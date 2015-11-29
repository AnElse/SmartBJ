package com.itanelse.smartbj.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.utils
 * @类名: CacheUtils
 * @时间: 下午12:51:24
 * @作者: AnElse
 * 
 * @描述: 是否第一次进入主界面的缓存记录
 * 
 * @当前版本号: $Rev: 5 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class CacheUtils
{
	private static final String			SP_NAME	= "smartbj";
	private static SharedPreferences	sp;

	private static SharedPreferences getSp(Context context)
	{
		if (sp == null)
		{
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
		return sp;
	}

	public static Boolean isFirstTime(Context context, String key)
	{
		SharedPreferences sp = getSp(context);
		return sp.getBoolean(key, false);
	}
	/**
	 * 获取记录第一次进入界面的缓存
	 * @param context 上下文
	 * @param key 键
	 * @param defValue 默认值
	 * @return
	 */
	public static Boolean isFirstTime(Context context, String key, boolean defValue)
	{
		SharedPreferences sp = getSp(context);
		return sp.getBoolean(key, defValue);
	}
	
	/**
	 * 保存是否第一次进入主界面的缓存值
	 * @param context 上下文
	 * @param key 保存的键
	 * @param value 保存的值
	 */
	public static void setBoolean(Context context,String key,boolean value){
		SharedPreferences sp = getSp(context);
		sp.edit().putBoolean(key, value).commit();
	}
}
