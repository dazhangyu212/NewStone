package com.stone.ordering.adapter;

import java.util.List;

import com.stone.ordering.R;
import com.stone.ordering.adapter.DishesListAdapter.ViewHolder;
import com.stone.ordering.dao.DiningTableDao;
import com.stone.ordering.dao.OrderDetailDao;
import com.stone.ordering.model.DiningTable;
import com.stone.ordering.model.DinnerOrder;

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
public class OrdersAdapter extends BaseAdapter {
	private List<DinnerOrder> mOrders;
	private Context mContext;
	private DiningTableDao tableDao;
	private OrderDetailDao detailDao;
	
	public OrdersAdapter(Context context,List<DinnerOrder> orders) {
		this.mContext = context;
		this.mOrders = orders;
		tableDao = new DiningTableDao();
		detailDao = new OrderDetailDao();
	}

	@Override
	public int getCount() {
		return mOrders.size();
	}

	@Override
	public Object getItem(int position) {
		return mOrders.get(position);
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_item_orders, root);
			holder.tvStatus = (TextView) convertView.findViewById(R.id.tv_status);
			holder.tvTable = (TextView) convertView.findViewById(R.id.tv_table);
			holder.tvTotal = (TextView) convertView.findViewById(R.id.tv_total);
			holder.tvIsCharged = (TextView) convertView.findViewById(R.id.tv_is_charged);
			convertView.setTag(holder);
		}else {
			holder = (ViewHolder) convertView.getTag();
		}
		DiningTable table = (DiningTable) tableDao.queryById(mOrders.get(position).getDiningTableID());
		holder.tvTable.setText("桌号:"+table.getRemarks());
		holder.tvTotal.setText("共计:￥"+mOrders.get(position).getTotal());
		switch (mOrders.get(position).getCharge()) {
		case DinnerOrder.Charge.UNPAID:
			holder.tvStatus.setText(R.string.str_unpaid);
			break;
		case DinnerOrder.Charge.CHARGED:
			holder.tvStatus.setText(R.string.str_charged);
			break;
		default:
			break;
		}
		return convertView;
	}
	
	class ViewHolder{
		TextView tvStatus;
		TextView tvTable;
		TextView tvTotal;
		TextView tvIsCharged;
	}


}
