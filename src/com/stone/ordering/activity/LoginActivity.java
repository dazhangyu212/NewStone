package com.stone.ordering.activity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.stone.ordering.R;
import com.stone.ordering.dao.DiningTableDao;
import com.stone.ordering.dao.DishDao;
import com.stone.ordering.dao.DishTypeDao;
import com.stone.ordering.dao.UserDao;
import com.stone.ordering.model.DiningTable;
import com.stone.ordering.model.Dish;
import com.stone.ordering.model.DishType;
import com.stone.ordering.model.User;
import com.stone.ordering.util.Constants;
import com.stone.ordering.util.SysUtilManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.Toast;

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
		etPassword.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_UP) {
					switch (keyCode) {
					case KeyEvent.KEYCODE_ENTER://修改回车键功能
						login();
						break;
					default:
						break;
					}
				}
				return false;
			}

			
		});
	}

	private void login() {
		String name = etUsername.getText().toString();
		String pw = etPassword.getText().toString();
		if ("".equals(name)) {
			Toast.makeText(this, R.string.str_name_null, Toast.LENGTH_LONG).show();
			return;
		}
		if ("".equals(pw)) {
			Toast.makeText(this, R.string.str_pw_null, Toast.LENGTH_LONG).show();
			return;
		}
		User user = udao.queryByName(name);
		if (user != null) {
			if (pw.equals(user.getPassWord())) {
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
				finish();
			}else {
				Toast.makeText(this, R.string.str_pw_unmatch, Toast.LENGTH_LONG).show();
			}
		}else {
			Toast.makeText(this, R.string.str_no_user, Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			login();
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
			table.setTableNum("10");
			table.setRemarks("A"+(i+1));;
			table.setStatus(DiningTable.Status.AVAILIABLE);
			tableDao.insert(table);
		}
		insertDishes();
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
	
	private void insertDishes(){
		DishDao dishDao = new DishDao();
		
		String[] dishPin = getResources().getStringArray(R.array.str_dishes_pin);
		String[] dishes = getResources().getStringArray(R.array.str_dishes);
		for (int i = 0; i < dishes.length; i++) {
			Dish dish = new Dish();
			dish.setDishName(dishes[i]);
			dish.setID(SysUtilManager.getNextId());
			String picPath = outputPics(dishPin[i]);
			dish.setPicPath(picPath);
			dish.setPrice(23);
			dish.setTypeID("");
			dishDao.insert(dish);
			dishDao.insert(dish);
		}
	}
	
	/**
	 * 将assets中的图片输出
	 * @param picName
	 * @return
	 */
	private String outputPics(String picName){
		
		try {
			File file = new File(Constants.DISHES_PATH+picName);
			if (!file.exists()) {
				InputStream is = getAssets().open("dishes/"+picName);
				FileOutputStream fos = new FileOutputStream(file);
				byte[] buffer = new byte[1024];
				int byteCount=0;               
				while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节        
					fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
				}
				fos.flush();//刷新缓冲区
				is.close();
				fos.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Constants.DISHES_PATH+picName;
	}
	
}
