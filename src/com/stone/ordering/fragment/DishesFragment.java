package com.stone.ordering.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.stone.ordering.R;
import com.stone.ordering.adapter.DishesListAdapter;
import com.stone.ordering.dao.DishDao;
import com.stone.ordering.dao.OrderDetailDao;
import com.stone.ordering.model.DinnerOrder;
import com.stone.ordering.model.Dish;
import com.stone.ordering.model.OrderDetail;
import com.stone.ordering.widget.CustomDialog;
import com.stone.ordering.widget.CustomDialog.ICallback;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

/**
 * 类名:DishesFragment
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月19日
 */
public class DishesFragment extends Fragment {
	private GridView gv_dishes;
	private DishDao dishDao;
	private UpdateDishInfo mInterface;
	private HashMap<String, OrderDetail> orderMap = new HashMap<String, OrderDetail>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dishes, container, false);
		dishDao = new DishDao();
		initView(view);
		return view;
	}

	private void initView(View view) {
		gv_dishes = (GridView) view.findViewById(R.id.gv_dishes);
		final ArrayList<Dish> dishes = (ArrayList<Dish>) dishDao.queryAll();
		DishesListAdapter adapter = new DishesListAdapter(getActivity(), dishes);
		gv_dishes.setAdapter(adapter);
		gv_dishes.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
				int count = 0;
				if (orderMap.containsKey(dishes.get(position).getID())) {
					count += orderMap.get(dishes.get(position).getID()).getCount();
				}
				CustomDialog.showDialog(getActivity(), null, null, count,
						new ICallback() {
					
					@Override
					public void onOKButtonClick(String str) {
						int nowCount = Integer.parseInt(str);
						Dish dish = dishes.get(position);
						if (nowCount > 0) {
							OrderDetail detail = null;
							if (orderMap.containsKey(dish.getID())) {
								detail = orderMap.get(dish.getID());
								detail.setCount(nowCount);
							}else {
								detail = new OrderDetail();
								detail.setCount(nowCount);
								detail.setDishID(dish.getID());
								detail.setRemarks(dish.getRemarks());
								detail.setReserved1(dish.getPrice()+"");
								orderMap.put(detail.getDishID(), detail);
							}
							int amount = 0;
							float total = 0;
							Iterator<Map.Entry<String, OrderDetail>> iter = orderMap.entrySet().iterator();
							while (iter.hasNext()) {
								Map.Entry<String, OrderDetail> entry = (Map.Entry<String, OrderDetail>) iter.next();
								OrderDetail val = entry.getValue();
								amount += val.getCount();
								total += Float.parseFloat(val.getReserved1())*val.getCount();
								mInterface.updateSelectInfo(amount+"");
								mInterface.updateTotalInfo(total+"");
							}
						}else {
							if (orderMap.containsKey(dish.getID())) {
								orderMap.remove(dish.getID());
								mInterface.updateSelectInfo("0");
								mInterface.updateTotalInfo("0.0");
							}
						}
					}
					
					@Override
					public void onCancelButtonClick() {
						
					}
				});
			}
		});
	}
	
	public void setInter(UpdateDishInfo mInterface){
		this.mInterface = mInterface;
	}
	
	public interface UpdateDishInfo{
		public void updateSelectInfo(String str);
		
		public void updateTotalInfo(String total);
	}

	public HashMap<String, OrderDetail> getOrderMap() {
		return orderMap;
	}
	
	public void saveOrderDetail(String orderID){
		Iterator<Map.Entry<String, OrderDetail>> iter = orderMap.entrySet().iterator();
		OrderDetailDao dao = new OrderDetailDao();
		while (iter.hasNext()) {
			Entry<String, OrderDetail> entry = iter.next();
			OrderDetail detail = entry.getValue();
			detail.setOrderID(orderID);
			dao.insert(detail);
		}
	}
	
}
