package com.stone.ordering.activity;

import com.stone.ordering.R;
import com.stone.ordering.dao.DinnerOrderDao;
import com.stone.ordering.dao.OrderDetailDao;
import com.stone.ordering.model.DinnerOrder;

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
		lvDetails = (ListView) findViewById(R.id.lv_details);
		btnCheckOut = (Button) findViewById(R.id.btn_check_out);
		etReceived = (EditText) findViewById(R.id.et_received);
		tvChange = (TextView) findViewById(R.id.tv_change); 
		tvTotal = (TextView) findViewById(R.id.tv_total);
		btnCheckOut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ChargeActivity.this.setResult(23);
			}
		});
	}
	
}
