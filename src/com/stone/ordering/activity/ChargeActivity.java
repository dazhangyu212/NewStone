package com.stone.ordering.activity;

import java.util.List;

import com.stone.ordering.R;
import com.stone.ordering.adapter.OrderDetailAdapter;
import com.stone.ordering.dao.DiningTableDao;
import com.stone.ordering.dao.DinnerOrderDao;
import com.stone.ordering.dao.OrderDetailDao;
import com.stone.ordering.model.DiningTable;
import com.stone.ordering.model.DinnerOrder;
import com.stone.ordering.model.OrderDetail;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class ChargeActivity extends BaseActivity {

	private ListView lvDetails;
	private Button btnCheckOut;
	private DinnerOrderDao orderDao;
	private OrderDetailDao detailDao;
	private EditText etReceived;
	private TextView tvChange;
	private TextView tvTotal;
	private TextView tvTable;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_charge);
		initData();
		initView();
	}

	private void initData() {
		orderDao = new DinnerOrderDao();
		detailDao = new OrderDetailDao();
	}

	private void initView() {
		String orderId = getIntent().getStringExtra("ID");
		DinnerOrder order = (DinnerOrder) new DinnerOrderDao().queryById(orderId);
		List<OrderDetail> list = new OrderDetailDao().queryByOrderID(orderId);
				
		lvDetails = (ListView) findViewById(R.id.lv_details);
		OrderDetailAdapter adapter = new OrderDetailAdapter(this, list);
		lvDetails.setAdapter(adapter);
		btnCheckOut = (Button) findViewById(R.id.btn_check_out);
		etReceived = (EditText) findViewById(R.id.et_received);
		tvChange = (TextView) findViewById(R.id.tv_change); 
		tvTotal = (TextView) findViewById(R.id.tv_total);
		tvTotal.setText(getResources().getString(R.string.str_total).replace("0.0", order.getTotal()+""));
		tvTable = (TextView) findViewById(R.id.tv_table);
		DiningTable table = (DiningTable) new DiningTableDao().queryById(order.getDiningTableID());
		tvTable.setText(getString(R.string.str_selected_table_info).replace("0", table.getRemarks()));
		btnCheckOut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ChargeActivity.this.setResult(23);
			}
		});
		
	}
	
}
