package com.itanelse.smartbj.bean;

import java.util.List;

/**
 * @项目名称: SmartBJ
 * @包名: com.itanelse.smartbj.bean
 * @类名: NewsCenterBean
 * @时间: 下午1:53:07
 * @作者: AnElse
 * 
 * @描述: 新闻中心对应的数据bean
 * 
 * @当前版本号: $Rev$
 * @更新人: $Author$
 * @更新的时间: $date$
 * @更新的描述: TODO
 * 
 */
public class NewsCenterBean
{
	public List<NewsCenterMenuBean>	data;
	public List<Long>				extend;
	public int						retcode;
	
	//菜单对应的数据
	public class NewsCenterMenuBean
	{
		public List<NewsCenterNewsBean>	children;
		public Long						id;
		public String					title;
		public int						type;

		public String					url;
		public String					url1;

		public String					dayurl;
		public String					excurl;
		public String					weekurl;
	}

	public class NewsCenterNewsBean
	{
		public Long		id;
		public String	title;
		public int		type;
		public String	url;
	}
}
