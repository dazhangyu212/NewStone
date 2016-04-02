package com.stone.ordering.fragment;

import java.util.ArrayList;

import com.stone.ordering.R;
import com.stone.ordering.adapter.DishesListAdapter;
import com.stone.ordering.dao.DishDao;
import com.stone.ordering.model.Dish;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dishes, container, false);
		dishDao = new DishDao();
		initView(view);
		return view;
	}

	private void initView(View view) {
		gv_dishes = (GridView) view.findViewById(R.id.gv_dishes);
		ArrayList<Dish> dishes = (ArrayList<Dish>) dishDao.queryAll();
		DishesListAdapter adapter = new DishesListAdapter(getActivity(), dishes);
		gv_dishes.setAdapter(adapter);
	}
	

}
