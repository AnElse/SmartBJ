package com.itanelse.smartbj.fragment;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.itanelse.smartbj.R;
import com.itanelse.smartbj.activity.MainUI;
import com.itanelse.smartbj.controller.TabController;
import com.itanelse.smartbj.controller.tab.GovController;
import com.itanelse.smartbj.controller.tab.HomeController;
import com.itanelse.smartbj.controller.tab.NewsCenterController;
import com.itanelse.smartbj.controller.tab.SettingCenterController;
import com.itanelse.smartbj.controller.tab.SmartServiceController;
import com.itanelse.smartbj.view.LazyViewPager;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.fragment
 * @类名: ContentFragment
 * @时间: 下午9:06:50
 * @作者: AnElse
 * 
 * @描述: 主界面的右侧内容界面
 * 
 * @当前版本号: $Rev: 8 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class ContentFragment extends BaseFragment implements OnCheckedChangeListener
{
	private static final String	TAG	= "ContentFragment";

	private List<TabController>	mPagerDatas;

	@ViewInject(R.id.content_pager)
	private LazyViewPager		mPager;
	@ViewInject(R.id.fragmentcontent_rg)
	private RadioGroup			mRgTabs;

	private int					mCurrentTab;				// 当前是哪个tab选中了

	@Override
	public View initView()
	{
		View view = View.inflate(mActivity, R.layout.fragment_content, null);

		// XUtils-->ViewUtils:解决View的注入

		// 分析view，找到View中所有的孩子的id,-->view
		// 反射handler找到对应的注解, @ViewInject-->field--->instance=view
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	public void initData()
	{
		// 数据的加载
		mPagerDatas = new ArrayList<TabController>();
		mPagerDatas.add(new HomeController(mActivity));
		mPagerDatas.add(new NewsCenterController(mActivity));
		mPagerDatas.add(new SmartServiceController(mActivity));
		mPagerDatas.add(new GovController(mActivity));
		mPagerDatas.add(new SettingCenterController(mActivity));

		// ViewPager加载数据
		mPager.setAdapter(new ContentPagerAdapter());// --->List

		// 设置radioGroup的监听
		// mRgTabs.setOnCheckedChangeListener(this);

		// 设置默认选中项
		mRgTabs.check(R.id.content_rb_home);
	}

	@Override
	public void initEvent()
	{
		// 设置radioGroup的监听
		mRgTabs.setOnCheckedChangeListener(this);
		super.initEvent();
	}

	class ContentPagerAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mPagerDatas != null) { return mPagerDatas.size(); }

			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object object)
		{
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position)
		{
			Log.d(TAG, "加载了第" + position + "页面");

			TabController controller = mPagerDatas.get(position);
			View rootView = controller.getRootView();

			controller.initDate();// 让controller自己去加载自己的数据,所以TabController需要加上initData()方法

			// 加载
			container.addView(rootView);

			return rootView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			Log.d(TAG, "销毁了第" + position + "页面");
			container.removeView((View) object);
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId)
	{
		// 当radioButton选中时的回调
		// @group: 变化的RadioGroup
		// @checkedId: 选中的radioButton的id

		switch (checkedId)
		{
			case R.id.content_rb_home:
				mCurrentTab = 0;
				// 不可以打开menu
				setSlidingMenuTouchEnable(false);
				break;
			case R.id.content_rb_newscenter:
				mCurrentTab = 1;
				// 以打开menu
				setSlidingMenuTouchEnable(true);
				break;
			case R.id.content_rb_smartsevice:
				mCurrentTab = 2;
				// 以打开menu
				setSlidingMenuTouchEnable(true);
				break;
			case R.id.content_rb_gov:
				mCurrentTab = 3;
				// 以打开menu
				setSlidingMenuTouchEnable(true);
				break;
			case R.id.content_rb_setting:
				mCurrentTab = 4;
				// 不可以打开menu
				setSlidingMenuTouchEnable(false);
				break;
			default:
				break;
		}
		// 设置ViewPager的选中
		mPager.setCurrentItem(mCurrentTab);
	}

	private void setSlidingMenuTouchEnable(boolean enable)
	{
		SlidingMenu slidingMenu = ((MainUI) mActivity).getSlidingMenu();
		slidingMenu.setTouchModeAbove(enable ? SlidingMenu.TOUCHMODE_FULLSCREEN : SlidingMenu.TOUCHMODE_NONE);
	}

	/**
	 * 让contentFragemnt中当前的tab去选中对应的menuitem,即切换对应menuitem
	 * 
	 * @param menuItem
	 *            :menu的position
	 */
	public void switchMenuItem(int menuItem)
	{
		//让当前的tab去选中对应的menuitem
		TabController controller = mPagerDatas.get(mCurrentTab);
		//但不是所有的tab都有菜单,所以让tab自己去实现
		controller.switchMenuItem(menuItem);
	}

}