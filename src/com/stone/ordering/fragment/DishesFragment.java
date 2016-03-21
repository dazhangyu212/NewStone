package com.stone.ordering.fragment;

import com.stone.ordering.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 类名:DishesFragment
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月19日
 */
public class DishesFragment extends Fragment {

	public DishesFragment() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_dishes, container, false);
		initView(view);
		return view;
	}

	private void initView(View view) {
		
	}
	

}
