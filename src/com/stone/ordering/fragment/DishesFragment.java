package com.stone.ordering.fragment;

import java.util.ArrayList;

import com.stone.ordering.R;
import com.stone.ordering.adapter.DishesListAdapter;
import com.stone.ordering.dao.DishDao;
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
	private int count=0;
	private ArrayList<OrderDetail> listDetail = new ArrayList<OrderDetail>();
	
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
				CustomDialog.showDialog(getActivity(), null, null, 
						new ICallback() {
					
					@Override
					public void onOKButtonClick(String str) {
						OrderDetail detail = new OrderDetail();
						detail.setCount(Integer.parseInt(str));
						detail.setDishID(dishes.get(position).getID());
						detail.setRemarks(dishes.get(position).getRemarks());
						listDetail.add(detail);
						int amount = 0;
						float total = 0;
						for (int i = 0; i < listDetail.size(); i++) {
							amount +=listDetail.get(i).getCount();
							total += dishes.get(i).getPrice()*listDetail.get(i).getCount();
							mInterface.updateSelectInfo(amount+"");
							mInterface.updateTotalInfo(total+"");
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

	
	public ArrayList<OrderDetail> getListDetail() {
		return listDetail;
	}
}
