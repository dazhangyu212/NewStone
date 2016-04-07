package com.stone.ordering.activity;

import java.util.List;

import com.stone.ordering.R;
import com.stone.ordering.adapter.OperationsAdapter;
import com.stone.ordering.adapter.OrdersAdapter;
import com.stone.ordering.dao.DinnerOrderDao;
import com.stone.ordering.model.DinnerOrder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
	private long exitTime = 0; 
	private List<DinnerOrder> orders;
	private OrdersAdapter orderAdapter;
	private DinnerOrderDao orderDao;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		orderDao = new DinnerOrderDao();
		initView();
	}
	
	private void initView() {
		
		GridView gvOperations = (GridView) findViewById(R.id.gv_operators);
		int[] operations = {R.drawable.greet,R.drawable.merge,
				R.drawable.move,R.drawable.inspect,R.drawable.ordering,
				R.drawable.update,R.drawable.setting,R.drawable.logout};
		OperationsAdapter adapter = new OperationsAdapter(this, operations);
		gvOperations.setAdapter(adapter);
		gvOperations.setOnItemClickListener(itemListener);
		
		ListView lvOrders = (ListView) findViewById(R.id.lv_orders);
		orders = orderDao.queryOrders(DinnerOrder.Charge.UNPAID);
		orderAdapter = new OrdersAdapter(this, orders);
		lvOrders.setAdapter(orderAdapter);
	}

	OnItemClickListener itemListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			Intent intent;
			switch (position) {
			case 0:
				
				break;
			case 1:
				
				break;
			case 2:
				
				break;
			case 3:
				
				break;
			case 4:
				intent = new Intent(MainActivity.this, ChooseDishsActivity.class);
				startActivityForResult(intent, Activity.RESULT_OK);
				break;
			case 5:
				orders.clear();
				orders.addAll(orderDao.queryAll());
				orderAdapter.notifyDataSetChanged();
				break;
			case 6:
				
				break;
			case 7:
				
				break;
			default:
				break;
			}
		}
	};
	
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event) {  
        if (KeyEvent.KEYCODE_BACK == keyCode) {  
            // 判断是否在两秒之内连续点击返回键，是则退出，否则不退出  
            if (System.currentTimeMillis() - exitTime > 2000) {  
                Toast.makeText(getApplicationContext(), "再按一次退出程序",  
                        Toast.LENGTH_SHORT).show();  
                // 将系统当前的时间赋值给exitTime  
                exitTime = System.currentTimeMillis();  
            } else {  
                exitApp();  
            }  
            return true;  
        }  
        return super.onKeyDown(keyCode, event);  
    }  
	
	/** 
     * 退出应用程序的方法，发送退出程序的广播 
     */  
    private void exitApp() {  
        Intent intent = new Intent();  
        intent.setAction("com.stone.ordering");  
        this.sendBroadcast(intent);  
    }  
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	switch (resultCode) {
		case ChooseDishsActivity.HAS_ORDER:
			orders.clear();
			orders.addAll(orderDao.queryOrders(DinnerOrder.Charge.UNPAID));
			orderAdapter.notifyDataSetChanged();
			break;
		case ChooseDishsActivity.NO_ORDER:
			
			break;
		default:
			break;
		}
    	super.onActivityResult(requestCode, resultCode, data);
    }
    
}
