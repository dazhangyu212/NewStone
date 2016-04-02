package com.stone.ordering.activity;

import com.stone.ordering.R;
import com.stone.ordering.dao.DiningTableDao;
import com.stone.ordering.dao.DishDao;
import com.stone.ordering.dao.DishTypeDao;
import com.stone.ordering.dao.UserDao;
import com.stone.ordering.model.DiningTable;
import com.stone.ordering.model.Dish;
import com.stone.ordering.model.DishType;
import com.stone.ordering.model.User;
import com.stone.ordering.util.SysUtilManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class LoginActivity extends BaseActivity implements OnClickListener{
	private EditText etUsername;
	private EditText etPassword;
	//preferrence不能再卸载后记录数据库是否被写入
//	private SharedPreferences preference;
	UserDao udao;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
//		preference = getSharedPreferences("FirstRecord", MODE_PRIVATE);
		initView();
//		if (preference.getBoolean("isFirstRunning", true)) {
//		}
		udao = new UserDao();
		User user = udao.queryByName("admin");
		if (user == null) {
			insertData();
		}
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
	
	/**
	 * 在数据库中插入初始化的数据库内容
	 */
	private void insertData(){
		DiningTableDao tableDao = new DiningTableDao();
		for (int i = 0; i < 10; i++) {
			DiningTable table = new DiningTable();
			table.setID(SysUtilManager.getNextId());
			table.setTableNum("A"+(i+1));
			table.setStatus(DiningTable.Status.AVAILIABLE);
			tableDao.insert(table);
		}
		DishDao dishDao = new DishDao();
		String[] dishes = getResources().getStringArray(R.array.str_dishes);
		for (int i = 0; i < dishes.length; i++) {
			Dish dish = new Dish();
			dish.setDishName(dishes[i]);
			dish.setID(SysUtilManager.getNextId());
			dish.setPrice(23);
			dish.setTypeID("");
			dishDao.insert(dish);
			dishDao.insert(dish);
		}
		DishTypeDao typeDao = new DishTypeDao();
		String[] types = getResources().getStringArray(R.array.str_dishtypes);
		for (int i = 0; i < types.length; i++) {
			DishType type = new DishType();
			type.setID(SysUtilManager.getNextId());
			type.setTypeName(types[i]);
			typeDao.insert(type);
		}
		User user = new User();
		user.setID(SysUtilManager.getNextId());
		user.setUserName("admin");
		user.setPassWord("123");
		user.setStatus(User.Status.Adminstrator);
		
		udao.insert(user);
//		Editor editor = preference.edit();
//		editor.putBoolean("isFirstRunning", false);
//		editor.commit();		
		
	}
	
}
