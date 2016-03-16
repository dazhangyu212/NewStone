package com.stone.ordering.activity;

import com.stone.ordering.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements OnClickListener{
	private EditText etUsername;
	private EditText etPassword;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		initView();
	}

	private void initView() {
		findViewById(R.id.btn_login).setOnClickListener(this);
		etUsername = (EditText) findViewById(R.id.et_username);
		etPassword = (EditText) findViewById(R.id.et_password);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			finish();
			break;
		default:
			break;
		}
	}
	
}
