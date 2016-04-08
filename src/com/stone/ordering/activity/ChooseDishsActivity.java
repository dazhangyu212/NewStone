package com.stone.ordering.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.stone.ordering.R;
import com.stone.ordering.dao.DinnerOrderDao;
import com.stone.ordering.dao.DishDao;
import com.stone.ordering.dao.OrderDetailDao;
import com.stone.ordering.fragment.DishesFragment;
import com.stone.ordering.fragment.DishesFragment.UpdateDishInfo;
import com.stone.ordering.fragment.TablesFragment;
import com.stone.ordering.fragment.TablesFragment.UpdateInfo;
import com.stone.ordering.model.DiningTable;
import com.stone.ordering.model.DinnerOrder;
import com.stone.ordering.model.Dish;
import com.stone.ordering.model.OrderDetail;
import com.stone.ordering.util.DateUtil;
import com.stone.ordering.widget.CustomDialog;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
	/**
	 * 桌号
	 */
	private TextView tvSelectedTableInfo;
	/**
	 * 餐点数
	 */
	private TextView tvSelectedDishesInfo;
	/**
	 * 总价
	 */
	private TextView tvTotal;
	/**
	 * 是否为新订单
	 */
	private boolean isNewOrder = true;
	/**
	 * 当前订单
	 */
	private DinnerOrder currOrder = null;
	/**
	 * 没有新订单
	 */
	public static final int NO_ORDER = 0;
	/**
	 * 有新订单
	 */
	public static final int HAS_ORDER = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choose_dishs);
		initView();
	}

	private void initView() {
		findViewById(R.id.btn_select_dishes).setOnClickListener(this);
		findViewById(R.id.btn_select_table).setOnClickListener(this);
		findViewById(R.id.ib_comfirm).setOnClickListener(this);
		findViewById(R.id.ib_back).setOnClickListener(this);
		ImageButton btn_refresh = (ImageButton) findViewById(R.id.ib_refresh);
		btn_refresh.setOnClickListener(this);
		tvSelectedTableInfo = (TextView) findViewById(R.id.tv_selected_table_info);
		tvSelectedDishesInfo = (TextView) findViewById(R.id.tv_selected_dishes_info);
		tvTotal = (TextView) findViewById(R.id.tv_total);
		setDefaultFragment();
		
	}

	private void setDefaultFragment() {
	    FragmentManager fManager = getFragmentManager();
		FragmentTransaction transaction = fManager.beginTransaction();
		tablesFrag = new TablesFragment();
		tablesFrag.setInter(new UpdateInfo() {

			@Override
			public void updateSelectInfo(String str) {
				String txt = getResources().getString(R.string.str_selected_table_info);
				tvSelectedTableInfo.setText(txt.replace("0", str));
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
					String txt = getResources().getString(R.string.str_selected_dishes_info);
					tvSelectedDishesInfo.setText(txt.replace("0", str));
				}

				@Override
				public void updateTotalInfo(String total) {
					String txt = getResources().getString(R.string.str_total);
					tvTotal.setText(txt.replace("0.0", total));
					
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
					String txt = getResources().getString(R.string.str_selected_table_info);
					tvSelectedTableInfo.setText(txt.replace("0", str));
				}
				
			});
			transaction.replace(R.id.flt_fragment, tablesFrag);  
			break;
		case R.id.ib_comfirm:
			if (isNewOrder) {
				saveNewOrder();
			}else {
				updateOrder();
			}
			break;
		case R.id.ib_refresh:
			tvTotal.setText(getResources().getString(R.string.str_total));
			tvSelectedDishesInfo.setText(getResources().getString(R.string.str_selected_dishes_info));
			tvSelectedTableInfo.setText(getResources().getString(R.string.str_selected_table_info));
			isNewOrder = true;
			break;
		case R.id.ib_back:
			if (currOrder.getID()!= null) {
				this.setResult(HAS_ORDER);
			}else {
				this.setResult(NO_ORDER);
			}
			this.finish();
			break;
		default:
			break;
		}
		// transaction.addToBackStack();  
        // 事务提交  
        transaction.commit();  
	}
	
	/**
	 * 更新当前订单
	 */
	private void updateOrder() {
		if (currOrder != null && checkOrder()) {
			int i = new DinnerOrderDao().update(currOrder);
			if (i >0) {
				Toast.makeText(this, getResources().getString(R.string.str_update_success), Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this, getResources().getString(R.string.str_update_failed), Toast.LENGTH_SHORT).show();
			}
		}
	}

	/**
	 * 保存订单
	 */
	private void saveNewOrder(){
		currOrder = new DinnerOrder();
		if(checkOrder()){
			String id = new DinnerOrderDao().insert(currOrder);
			if (id != null && !"".equals(id)) {
				Toast.makeText(this, getResources().getString(R.string.str_save_success), Toast.LENGTH_SHORT).show();
				isNewOrder = false;
			}else {
				Toast.makeText(this, getResources().getString(R.string.str_save_failure), Toast.LENGTH_SHORT).show();
			}
		}
	}
	
	/**
	 * 检查订单详情
	 */
	private boolean checkOrder(){
		int count = 0;
		float total = 0;
		
		if (tablesFrag != null) {
			DiningTable table = tablesFrag.getCurrTable();
			if (table != null && table.getID() != null && !"".equals(table.getID())) {
				currOrder.setDiningTableID(table.getID());
			}else {
				CustomDialog.showEditDialog(this, 
						getResources().getString(R.string.str_tips), 
						getResources().getString(R.string.str_no_table));
				return false;
			}
			currOrder.setOrderingTime(DateUtil.formatCurrentDate(DateUtil.Catagory.third));
		}else {
			CustomDialog.showEditDialog(this, 
					getResources().getString(R.string.str_tips), 
					getResources().getString(R.string.str_no_table));
			return false;
		}
		if (dishesFrag != null) {
			OrderDetailDao dao = new OrderDetailDao();
			HashMap<String, OrderDetail> ordersMap = dishesFrag.getOrderMap();
			Iterator<Map.Entry<String, OrderDetail>> iter = ordersMap.entrySet().iterator();
			
			if (ordersMap != null && ordersMap.size() > 0) {
				while (iter.hasNext()) {
					Entry<String, OrderDetail> entry = iter.next();
					OrderDetail detail = entry.getValue();
					detail.setOrderID(currOrder.getID());
					count +=detail.getCount();
					Float price = Float.parseFloat(detail.getReserved1());
					total += price*detail.getCount();
					dao.insert(detail);
				}
			}else {
				CustomDialog.showEditDialog(this, 
						getResources().getString(R.string.str_tips), 
						getResources().getString(R.string.str_no_dishes));
				return false;
			}
		}else {
			CustomDialog.showEditDialog(this, 
					getResources().getString(R.string.str_tips), 
					getResources().getString(R.string.str_no_dishes));
			return false;
		}
		currOrder.setCount(count);
		currOrder.setTotal(total);
		currOrder.setCharge(DinnerOrder.Charge.UNPAID);
		return true;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	public void onBackPressed() {
		if (currOrder.getID()!= null) {
			this.setResult(HAS_ORDER);
		}else {
			this.setResult(NO_ORDER);
		}
		super.onBackPressed();
	}
}
