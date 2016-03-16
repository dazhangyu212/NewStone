package com.stone.ordering.adapter;

import com.stone.ordering.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

/**
 * 类名:OperationsAdapter
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月16日
 */
public class OperationsAdapter extends BaseAdapter {
	private Context mContext;
	private int[] ids;

	public OperationsAdapter(Context context,int[] operations) {
		ids = operations;
		this.mContext = context;
	}

	@Override
	public int getCount() {
		return ids.length;
	}

	@Override
	public Object getItem(int position) {
		return ids[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewGroup root = null;
		ViewHolder holder = new ViewHolder();
		if (convertView != null) {
			holder = (ViewHolder) convertView.getTag();
		}else {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item, root);
			holder.ivOperation = (ImageView) convertView.findViewById(R.id.iv_operation);
			convertView.setTag(holder);
		}
		holder.ivOperation.setImageResource(ids[position]);
		return convertView;
	}
	
	class ViewHolder{
		ImageView ivOperation;
	}

}
