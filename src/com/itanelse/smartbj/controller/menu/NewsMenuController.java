package com.itanelse.smartbj.controller.menu;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.itanelse.smartbj.R;
import com.itanelse.smartbj.activity.MainUI;
import com.itanelse.smartbj.bean.NewsCenterBean.NewsCenterNewsBean;
import com.itanelse.smartbj.controller.MenuController;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.controller.menu
 * @类名: NewsMenuController
 * @时间: 下午12:11:52
 * @作者: AnElse
 * 
 * @描述: 新闻中心页面中,新闻菜单对应的页面
 * 
 * @当前版本号: $Rev$
 * @更新人: $Author$
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class NewsMenuController extends MenuController implements OnPageChangeListener
{
	@ViewInject(R.id.newscenter_news_pager)
	private ViewPager					mPager;

	@ViewInject(R.id.newscenter_news_indicator)
	private TabPageIndicator			indicator;

	private List<NewsCenterNewsBean>	mDatas;	// 新闻中心中的新闻页面的viewpagerindicator标题

	public NewsMenuController(Context context, List<NewsCenterNewsBean> datas) {
		super(context);
		this.mDatas = datas;
	}

	@Override
	protected View initView(Context context)
	{
		// TextView tv = new TextView(context);
		// tv.setText("新闻页面");
		// tv.setTextSize(24);
		// tv.setTextColor(Color.RED);
		// tv.setGravity(Gravity.CENTER);
		View view = View.inflate(mContext, R.layout.newscenter_news, null);
		// 注入
		ViewUtils.inject(this, view);

		return view;
	}

	@Override
	public void initData()
	{
		// 给viewpager加载数据
		mPager.setAdapter(new NewsMenuPagerAdapter());

		// 给指针设置viewpager
		indicator.setViewPager(mPager);

		// 设置viewpager的监听
		indicator.setOnPageChangeListener(this);
	}

	@OnClick(R.id.newscenter_news_iv_arrow)
	 public void clickArrow(View view)
	 {
	 // 点击了箭头,viewpager选中下一个页面
	 int currentItem = mPager.getCurrentItem();
	 mPager.setCurrentItem(++currentItem);//查看源码,发现已经做了做一个的逻辑判断,当为最后一个时还是显示最后一个
	 }

	private class NewsMenuPagerAdapter extends PagerAdapter
	{

		@Override
		public int getCount()
		{
			if (mDatas != null) { return mDatas.size(); }
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
			NewsCenterNewsBean bean = mDatas.get(position);
			// 模拟UI显示
			TextView tv = new TextView(mContext);
			tv.setText(bean.title);
			tv.setTextSize(24);
			tv.setTextColor(Color.RED);
			tv.setGravity(Gravity.CENTER);

			// 添加view
			container.addView(tv);

			return tv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object)
		{
			container.removeView((View) object);
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			if (mDatas != null)
			{
				NewsCenterNewsBean bean = mDatas.get(position);
				return bean.title;
			}
			return super.getPageTitle(position);
		}
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int position)
	{
		// 如果选中了第0个菜单可以拖拽出来,否则不可以
		// if (0 == position)
		// {
		// ((MainUI)mContext).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		// }else{
		// ((MainUI)mContext).getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		// }
		((MainUI) mContext).getSlidingMenu().setTouchModeAbove(position == 0 ? SlidingMenu.TOUCHMODE_FULLSCREEN : SlidingMenu.TOUCHMODE_NONE);
	}

	@Override
	public void onPageScrollStateChanged(int state)
	{
		// TODO Auto-generated method stub

	}
}
