package com.itanelse.smartbj.fragment;

import java.util.List;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.itanelse.smartbj.R;
import com.itanelse.smartbj.activity.MainUI;
import com.itanelse.smartbj.bean.NewsCenterBean.NewsCenterMenuBean;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.fragment
 * @类名: ContentFragment
 * @时间: 下午9:06:50
 * @作者: AnElse
 * 
 * @描述: 主界面的左侧抽屉界面
 * 
 * @当前版本号: $Rev: 6 $
 * @更新人: $Author: vaio $
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class MenuFragment extends BaseFragment implements OnItemClickListener
{
	private ListView					mListView		= null;
	private List<NewsCenterMenuBean>	mMenuDatas;
	private int							mCurrentItem	= -1;	// 用来标记选中的menuitem
	private MenuAdapter					menuAdapter;

	@Override
	public View initView()
	{
		// TextView tv = new TextView(mActivity);
		// tv.setText("左侧抽屉fragment");
		mListView = new ListView(mActivity);
		// 设置listview的样式
		// mListView.setBackgroundResource(R.drawable.menu_bg);
		mListView.setBackgroundColor(Color.BLACK);
		mListView.setCacheColorHint(Color.TRANSPARENT);// 设置该属性防止拖拽出现白色一大片
		mListView.setDividerHeight(0);// 去掉分割线
		mListView.setFadingEdgeLength(0);// 去掉雾状机构
		mListView.setScrollbarFadingEnabled(false);// 解决设置图片背景拖拽出现的白色一大片
		mListView.setSelector(android.R.color.transparent);// 设置去掉menuitem点击的背景
		mListView.setPadding(0, 40, 0, 40);

		// 设置item的点击事件
		mListView.setOnItemClickListener(this);
		return mListView;
	}

	/**
	 * 给菜单设置数据
	 * 
	 * @param mMenuDatas
	 *            服务器返回的数据
	 */
	public void setData(List<NewsCenterMenuBean> datas)
	{
		this.mMenuDatas = datas;
		// 给listview设置数据
		menuAdapter = new MenuAdapter();
		mListView.setAdapter(menuAdapter);

		mCurrentItem = 0;// 设置默认的选中第一个:新闻菜单
	}

	private class MenuAdapter extends BaseAdapter
	{

		@Override
		public int getCount()
		{
			// 模板写法
			if (mMenuDatas != null) { return mMenuDatas.size(); }
			return 0;
		}

		@Override
		public Object getItem(int position)
		{
			// 模板写法
			if (mMenuDatas != null) { return mMenuDatas.get(position); }
			return null;
		}

		@Override
		public long getItemId(int position)
		{
			// 模板写法
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			ViewHolder holder = null;
			if (convertView == null)
			{
				// 没有复用
				holder = new ViewHolder();
				convertView = View.inflate(mActivity, R.layout.item_menu, null);

				// 设置标记
				convertView.setTag(holder);

				// 初始化view
				holder.tv = (TextView) convertView.findViewById(R.id.item_menu_tv);
			}
			else
			{
				// 有复用
				holder = (ViewHolder) convertView.getTag();
			}
			// 设置数据
			NewsCenterMenuBean bean = mMenuDatas.get(position);
			// 给view 设置数据
			holder.tv.setText(bean.title);

			// if (mCurrentItem == position)
			// {
			// holder.tv.setEnabled(true);
			// }else{
			// holder.tv.setEnabled(false);
			// }

			// 简化写法
			holder.tv.setEnabled(mCurrentItem == position);
			return convertView;
		}
	}

	class ViewHolder
	{
		TextView	tv;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		// 如果点击的是同一个,那么就不切换界面
		if (position == mCurrentItem) { return; }
		// 切换选中的item
		mCurrentItem = position;
		menuAdapter.notifyDataSetChanged();// 让适配器去更新选中的菜单界面
		
		//让对应的tab显示对应的menu页面(因为新闻中心,智慧服务,政务都有菜单)
		ContentFragment contentFragment = ((MainUI) mActivity).getContentFragment();
		//让contentFragemnt中当前的tab去选中对应的menuitem
		contentFragment.switchMenuItem(mCurrentItem);
	}
}
