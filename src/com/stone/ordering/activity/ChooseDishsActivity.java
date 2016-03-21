package com.stone.ordering.activity;

import com.j256.ormlite.misc.TransactionManager;
import com.stone.ordering.R;
import com.stone.ordering.fragment.DishesFragment;
import com.stone.ordering.fragment.TablesFragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dishs);
		initView();
	}

	private void initView() {
		findViewById(R.id.btn_select_dishes).setOnClickListener(this);;
		findViewById(R.id.btn_select_table).setOnClickListener(this);;
		setDefaultFragment();
		
	}

	private void setDefaultFragment() {
	    FragmentManager fManager = getFragmentManager();
		FragmentTransaction transaction = fManager.beginTransaction();
		tablesFrag = new TablesFragment();
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
			transaction.replace(R.id.flt_fragment, dishesFrag);  
			break;
		case R.id.btn_select_table:
			if (tablesFrag == null) {
				tablesFrag = new TablesFragment();
			}
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
