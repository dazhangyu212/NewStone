package com.stone.ordering.adapter;

import java.util.List;

import com.stone.ordering.R;
import com.stone.ordering.dao.DiningTableDao;
import com.stone.ordering.dao.DishDao;
import com.stone.ordering.model.Dish;
import com.stone.ordering.model.OrderDetail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 类名:OrdersAdapter
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年4月7日
 */
public class OrderDetailAdapter extends BaseAdapter {
	private List<OrderDetail> mDetails;
	private Context mContext;
	
	public OrderDetailAdapter(Context context,List<OrderDetail> details) {
		this.mContext = context;
		this.mDetails = details;
	}

	@Override
	public int getCount() {
		return mDetails.size();
	}

	@Override
	public Object getItem(int position) {
		return mDetails.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		ViewGroup root = null;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_order_details, root);
			holder.tvDishName = (TextView) convertView.findViewById(R.id.tv_dish_name);
			holder.tvPrice = (TextView) convertView.findViewById(R.id.tv_price);
			holder.tvCount = (TextView) convertView.findViewById(R.id.tv_count);
			holder.ivDish = (ImageView) convertView.findViewById(R.id.iv_dishes);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		OrderDetail detail = mDetails.get(position);
		holder.tvCount.setText(mContext.getResources().getString(R.string.str_count).replace("0", detail.getCount()+""));
		Dish dish = (Dish) new DishDao().queryById(detail.getDishID());
		holder.tvDishName.setText(dish.getDishName());
		holder.tvPrice.setText(mContext.getResources().getString(R.string.str_price).replace("0.0", dish.getPrice()+""));
		return convertView;
	}
	
	class ViewHolder{
		TextView tvDishName;
		TextView tvPrice;
		TextView tvCount;
		ImageView ivDish;
	}


}
