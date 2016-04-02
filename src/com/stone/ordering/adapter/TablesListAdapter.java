package com.stone.ordering.adapter;

import java.util.List;

import com.stone.ordering.R;
import com.stone.ordering.model.DiningTable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 类名:TablesListAdapter
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月22日
 */
public class TablesListAdapter extends BaseAdapter {
	
	private Context mContext;
	private List<DiningTable> mTables;

	public TablesListAdapter(Context context,List<DiningTable> tables) {
		mContext = context;
		mTables = tables;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTables.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mTables.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		ViewGroup root = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_tables, root);
			holder = new ViewHolder();
			holder.iv_table = (ImageView) convertView.findViewById(R.id.iv_table);
			holder.tv_table_status = (TextView) convertView.findViewById(R.id.tv_table_status);
			holder.tv_table_type = (TextView) convertView.findViewById(R.id.tv_table_type);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.iv_table.setImageResource(R.drawable.table_thumbnail);
		holder.tv_table_type.setText(mTables.get(position).getRemarks()+mTables.get(position).getTableNum());
		holder.tv_table_status.setText(mTables.get(position).getStatus()+"");
		return convertView;
	}
	
	class ViewHolder{
		ImageView iv_table;
		TextView tv_table_type;
		TextView tv_table_status;
	}
}
