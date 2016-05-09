package com.stone.ordering.activity;

import com.stone.ordering.R;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * 类名:SettingActivity
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年4月18日
 */
public class SettingActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		initView();
	}

	private void initView() {
		findViewById(R.id.ib_back).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SettingActivity.this.finish();
			}
		});;
	}
}
