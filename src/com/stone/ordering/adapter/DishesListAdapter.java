package com.stone.ordering.adapter;

import java.util.ArrayList;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.stone.ordering.R;
import com.stone.ordering.model.Dish;

import android.content.Context;
import android.graphics.Bitmap;
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
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.img_loading)
				.showImageOnFail(R.drawable.img_loaded_failure)
				.cacheInMemory(false)
				.cacheOnDisk(false)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		ImageLoader.getInstance().displayImage(Scheme.FILE.wrap(mList.get(position).getPicPath()), holder.iv_dish, options);
		holder.tv_name.setText(mList.get(position).getDishName());
		holder.tv_price.setText(mList.get(position).getPrice()+"￥");
		holder.tv_brief.setText(mList.get(position).getRemarks());
		return convertView;
	}

	class ViewHolder{
		ImageView iv_dish;
		TextView tv_price;
		TextView tv_brief;
		TextView tv_name;
	}
	
}
