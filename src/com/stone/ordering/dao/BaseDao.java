package com.stone.ordering.dao;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.stone.ordering.data.DatabaseHelper;

/**
 * 类名：BaseDao 描述：Dao基类 公司:北京海鑫科金高科技股份有限公司 作者：yuchengkuan 版本：V1.0 创建时间：2015年9月10日
 * 最后修改时间：2015年9月10日
 */
public class BaseDao {
	
	public static final int COLLECT_DATA_DB = 0;

	public OrmLiteSqliteOpenHelper getDatabaseHelper(int type) {
		OrmLiteSqliteOpenHelper databaseHelper = null;
		switch (type) {
		case COLLECT_DATA_DB:
			databaseHelper = DatabaseHelper.getHelper();
			break;
		default:
			break;
		}
		return databaseHelper;
	}
	
	public Object query(String id){
		return null;
	};
	/**
	 * 提供可根据外键查询的接口
	 * @param field 外键属性名
	 * @param foreignKey 外键值
	 * @return
	 */
	public Object query(String field,String foreignKey){
		return null;
	}
	
//	public QueryBuilder getQueryBuilder(){
//		return null;
//	}
}
