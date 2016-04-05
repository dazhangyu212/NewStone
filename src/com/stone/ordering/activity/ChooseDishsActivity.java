package com.stone.ordering.activity;

import com.stone.ordering.R;
import com.stone.ordering.fragment.DishesFragment;
import com.stone.ordering.fragment.DishesFragment.UpdateDishInfo;
import com.stone.ordering.fragment.TablesFragment;
import com.stone.ordering.fragment.TablesFragment.UpdateInfo;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

/**
 * 类名:ChooseDishsActivity
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月16日
 */
public class ChooseDishsActivity extends BaseActivity implements OnClickListener{
	
	private TablesFragment tablesFrag ;
	private DishesFragment dishesFrag;
	private TextView tvSelectedInfo;
	
	private String selectInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dishs);
		initView();
	}

	private void initView() {
		findViewById(R.id.btn_select_dishes).setOnClickListener(this);
		findViewById(R.id.btn_select_table).setOnClickListener(this);
		tvSelectedInfo = (TextView) findViewById(R.id.tv_selected_info);
		selectInfo = getResources().getString(R.string.str_selected_info);
		setDefaultFragment();
		
	}

	private void setDefaultFragment() {
	    FragmentManager fManager = getFragmentManager();
		FragmentTransaction transaction = fManager.beginTransaction();
		tablesFrag = new TablesFragment();
		tablesFrag.setInter(new UpdateInfo() {

			@Override
			public void updateSelectInfo(String str) {
				String txt = tvSelectedInfo.getText().toString();
				tvSelectedInfo.setText(txt.replace("X", str));
			}
			
		});
        transaction.replace(R.id.flt_fragment, tablesFrag);  
        transaction.commit();  
	}

	@Override
	public void onClick(View v) {
		FragmentManager fManager = getFragmentManager();
		FragmentTransaction transaction = fManager.beginTransaction();
		switch (v.getId()) {
		case R.id.btn_select_dishes:
			if (dishesFrag == null) {
				dishesFrag = new DishesFragment();
			}
			dishesFrag.setInter(new UpdateDishInfo() {
				
				@Override
				public void updateSelectInfo(String str) {
					String txt = tvSelectedInfo.getText().toString();
					tvSelectedInfo.setText(txt.replace("Y", str));
				}
			});
			transaction.replace(R.id.flt_fragment, dishesFrag);  
			break;
		case R.id.btn_select_table:
			if (tablesFrag == null) {
				tablesFrag = new TablesFragment();
			}
			tablesFrag.setInter(new UpdateInfo() {

				@Override
				public void updateSelectInfo(String str) {
					String txt = tvSelectedInfo.getText().toString();
					tvSelectedInfo.setText(txt.replace("X", str));
				}
				
			});
			transaction.replace(R.id.flt_fragment, tablesFrag);  
			break;
		default:
			break;
		}
		// transaction.addToBackStack();  
        // 事务提交  
        transaction.commit();  
	}
	
}
