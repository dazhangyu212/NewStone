package com.stone.ordering.fragment;

import java.util.ArrayList;

import com.stone.ordering.R;
import com.stone.ordering.adapter.TablesListAdapter;
import com.stone.ordering.dao.DiningTableDao;
import com.stone.ordering.model.DiningTable;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 类名:TablesFragment
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月19日
 */
public class TablesFragment extends Fragment {
	private ListView lv_tables;
	private DiningTable currTable;
	private DiningTableDao tableDao;
	private UpdateInfo mInterface;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_tables, container, false);
		tableDao = new DiningTableDao();
		initView(view);
		return view;
	}

	private void initView(View view) {
		lv_tables = (ListView) view.findViewById(R.id.lv_tables);
		final ArrayList<DiningTable> tables = tableDao.queryAll();
		TablesListAdapter adapter = new TablesListAdapter(getActivity(),tables);
		lv_tables.setAdapter(adapter);
		lv_tables.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				currTable = tables.get(position);
				mInterface.updateSelectInfo(currTable.getRemarks());
			}
		});
	}
	
	public void setInter(UpdateInfo mInterface){
		this.mInterface = mInterface;
	}
	
	public interface UpdateInfo{
		public void updateSelectInfo(String str);
	}

	public DiningTable getCurrTable() {
		return currTable;
	}
	
	
}
