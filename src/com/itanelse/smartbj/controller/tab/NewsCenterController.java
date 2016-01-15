package com.itanelse.smartbj.controller.tab;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.itanelse.smartbj.activity.MainUI;
import com.itanelse.smartbj.bean.NewsCenterBean;
import com.itanelse.smartbj.bean.NewsCenterBean.NewsCenterMenuBean;
import com.itanelse.smartbj.controller.MenuController;
import com.itanelse.smartbj.controller.TabController;
import com.itanelse.smartbj.controller.menu.InteractMenuController;
import com.itanelse.smartbj.controller.menu.NewsMenuController;
import com.itanelse.smartbj.controller.menu.PicMenuController;
import com.itanelse.smartbj.controller.menu.TopicMenuController;
import com.itanelse.smartbj.fragment.MenuFragment;
import com.itanelse.smartbj.utils.CacheUtils;
import com.itanelse.smartbj.utils.Constans;
import com.itanelse.smartbj.utils.LogUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.controller
 * @类名: HomeController
 * @时间: 下午6:00:20
 * @作者: AnElse
 * 
 * @描述: 新闻中心Controller,用来管理Menuontroller显示切换
 * 
 * @当前版本号: $Rev: 7 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class NewsCenterController extends TabController
{
	List<NewsCenterMenuBean>		mMenuDatas;					// 获取到bean中的数据

	protected static final String	TAG	= "NewsCenterController";

	private List<MenuController>	mMenuControllers;				// 菜单对应的控制器
	private FrameLayout				mContainer;					// 负责切换controller对应的view;

	public NewsCenterController(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected View initContentView(Context context)
	{
		mIvMenu.setVisibility(View.VISIBLE);

		// TextView tv = new TextView(context);
		// tv.setText("新闻中心");
		// tv.setTextSize(24);
		// tv.setGravity(Gravity.CENTER);
		// tv.setTextColor(Color.RED);
		mContainer = new FrameLayout(context);
		return mContainer;
	}

	@Override
	public void initDate()
	{
		final String url = Constans.NEWSCENTER_URL;// url

		// 即使是有网的情况下,我们也是先去读取的.为了提高应用程序的响应时间,数据缓存是一种比较好的方式,我们可以预处理服务器返回的数据,对数据进行缓存刷新.
		// 将读取到的缓存数据展示出来
		String json = CacheUtils.getString(mContext, url);
		if (!TextUtils.isEmpty(json))
		{
			LogUtils.d(TAG, "读取缓存");
			performData(json);// 将获取到的服务器数据展示出来
		}

		// 加载数据(基本上为三个步骤)

		// 1,进行网络访问获取网络数据
		// 请求网络,关注点:请求方法,URL,请求头,请求内容(参数:GET->请求消息行URL后面,POST->请求内容里)
		HttpUtils utils = new HttpUtils();// 是一个异步方法
		// RequestParams params = new RequestParams();
		// params.addHeader(name, value);//添加头
		// params.addQueryStringParameter(name, value);//get方法的请求内容
		// params.addBodyParameter(name, value);// post方法的请求内容
		// utils.send(HttpMethod.GET, url, params, callBack);

		// 现在我们的需求不需要用到上面这些参数.
		utils.send(HttpMethod.GET, url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo)
			{
				// 成功,请求成功---->指的是连接上服务器(返回200,302,400都是成功)
				// int statusCode = responseInfo.statusCode;
				// 200,指的接口访问成功
				// if (200 == statusCode)
				// {
				// // 执行成功的逻辑
				// }
				// else
				// {
				// // 执行失败的逻辑,是由产品指定的,比如弹出警告或者跳转到某个页面,不是自己决定的
				// }

				// 由于我们用的是静态服务器,所以只有成功,没有失败,实际开发中必须考虑
				// 获取结果
				String result = responseInfo.result;
				LogUtils.d(TAG, "成功" + result);

				CacheUtils.setString(mContext, url, result);

				// 将获取到的服务器数据展示出来
				performData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg)
			{
				// 失败,请求失败
				Toast.makeText(mContext, "联网失败", 0).show();
				LogUtils.d(TAG, "失败" + msg);
			}
		});
	}

	private void performData(String json)
	{
		// 2,进行json解析,把json数据解析成object对象
		Gson gson = new Gson();
		NewsCenterBean bean = gson.fromJson(json, NewsCenterBean.class);
		// LogUtils.d(TAG, "校验:"+bean.data.get(0).children.get(0).title);
		mMenuDatas = bean.data;// bean中的数据

		// 3,把object对象的数据显示到view上.
		// 3-1,将数据添加到菜单上
		MenuFragment menuFragment = ((MainUI) mContext).getMenuFragment();
		// 给menuFragment设置数据-->展示:TODO
		menuFragment.setData(mMenuDatas);
		// 3-2,中间内容区域加载数据
		// MVC:
		// NewsCenterController-->controller
		// mContainer-->view
		// mMenuControllers-->model
		mMenuControllers = new ArrayList<MenuController>();
		for (int i = 0; i < mMenuDatas.size(); i++)
		{
			NewsCenterMenuBean menuBean = mMenuDatas.get(i);
			int type = menuBean.type;// 获取到menuitem的type
			MenuController controller = null;
			switch (type)
			{
				case 1:
					// 新闻
					controller = new NewsMenuController(mContext, menuBean.children);
					break;
				case 10:
					// 专题
					controller = new TopicMenuController(mContext);
					break;
				case 2:
					// 组图
					controller = new PicMenuController(mContext);
					break;
				case 3:
					// 互动
					controller = new InteractMenuController(mContext);
				default:
					break;
			}
			mMenuControllers.add(controller);
		}
		switchMenuItem(0);// 选中第一个
	}

	/**
	 * 新闻中心tab去实现自己的菜单controller页面的切换
	 */
	@Override
	public void switchMenuItem(int menuItem)
	{
		// 显示对应的menucontroller

		// 点击切换前清空view
		mContainer.removeAllViews();

		// 设置标题显示
		NewsCenterMenuBean bean = mMenuDatas.get(menuItem);
		mTvTitle.setText(bean.title);

		MenuController controller = mMenuControllers.get(menuItem);// 被选中的controller
		// 需要显示的view
		View rootView = controller.getRootView();

		// 加载数据(上面完成了界面的切换,这里暴露出让MenuController实现数据加载的方法)
		controller.initData();

		mContainer.addView(rootView);
	}
}
