package com.stone.ordering.data;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.stone.ordering.app.MyApplication;
import com.stone.ordering.model.DiningTable;
import com.stone.ordering.model.DinnerOrder;
import com.stone.ordering.model.Dish;
import com.stone.ordering.model.DishType;
import com.stone.ordering.model.OrderDetail;
import com.stone.ordering.model.User;
import com.stone.ordering.util.Constants;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;

/**
 * 类名：DatabaseHelper 
 * 描述：数据库操作帮助类 
 * 公司:北京海鑫科金高科技股份有限公司 
 * 作者：zhangyu 
 * 版本：V1.0
 * 创建时间：2015年9月10日 
 * 最后修改时间：2015年9月10日
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static DatabaseHelper instance;

	private Map<String, Dao> daos = new HashMap<String, Dao>();
	
	private static final int DB_VERSION = 1;
	
	public DatabaseHelper(Context context) throws NameNotFoundException {
		super(context, Constants.COLLECT_DB_PATH, null, context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode);
	}

	@Override
	public synchronized SQLiteDatabase getReadableDatabase() {
		return SQLiteDatabase.openDatabase(Constants.COLLECT_DB_PATH, null, SQLiteDatabase.OPEN_READONLY);
	}

	@Override
	public synchronized SQLiteDatabase getWritableDatabase() {
		return SQLiteDatabase.openDatabase(Constants.COLLECT_DB_PATH, null, SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, DiningTable.class);
			TableUtils.createTable(connectionSource, DinnerOrder.class);
			TableUtils.createTable(connectionSource, Dish.class);
			TableUtils.createTable(connectionSource, DishType.class);
			TableUtils.createTable(connectionSource, OrderDetail.class);
			TableUtils.createTable(connectionSource, User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			TableUtils.createTable(connectionSource, DiningTable.class);
			TableUtils.createTable(connectionSource, DinnerOrder.class);
			TableUtils.createTable(connectionSource, Dish.class);
			TableUtils.createTable(connectionSource, DishType.class);
			TableUtils.createTable(connectionSource, OrderDetail.class);
			TableUtils.createTable(connectionSource, User.class);
			onCreate(database, connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static synchronized DatabaseHelper getHelper() {
		Context context = MyApplication.getMyApplication().getApplicationContext();
		if (instance == null) {
			synchronized (DatabaseHelper.class) {
				if (instance == null)
					try {
						instance = new DatabaseHelper(context);
					} catch (NameNotFoundException e) {
						e.printStackTrace();
					}
			}
		}
		
		return instance;
	}

	public synchronized Dao getDao(Class clazz) throws SQLException {
		Dao dao = null;
		String className = clazz.getSimpleName();

		if (daos.containsKey(className)) {
			dao = daos.get(className);
		}
		if (dao == null) {
			dao = super.getDao(clazz);
			daos.put(className, dao);
		}
		return dao;
	}

	@Override
	public void close() {
		super.close();

		for (String key : daos.keySet()) {
			Dao dao = daos.get(key);
			dao = null;
		}
	}

}
