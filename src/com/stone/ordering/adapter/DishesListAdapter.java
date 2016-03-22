package com.stone.ordering.adapter;

import java.util.ArrayList;

import com.stone.ordering.R;
import com.stone.ordering.model.Dish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 类名:DishesListAdapter
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月22日
 */
public class DishesListAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<Dish> mList;

	public DishesListAdapter(Context context,ArrayList<Dish> dishes) {
		this.mContext = context;
		this.mList = dishes;
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		ViewGroup root = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_dishes, root);
			holder.iv_dish = (ImageView) convertView.findViewById(R.id.iv_dishes);
			holder.tv_name = (TextView) convertView.findViewById(R.id.tv_dish_name);
			holder.tv_price = (TextView) convertView.findViewById(R.id.tv_dish_price);
			holder.tv_brief = (TextView) convertView.findViewById(R.id.tv_dish_brief);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		
		return convertView;
	}

	class ViewHolder{
		ImageView iv_dish;
		TextView tv_price;
		TextView tv_brief;
		TextView tv_name;
	}
	
}
