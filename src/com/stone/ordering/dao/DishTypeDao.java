package com.stone.ordering.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.stone.ordering.model.DishType;
import com.stone.ordering.model.IDao;

/**
 * 类名:DishTypeDao
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月10日
 */
public class DishTypeDao extends BaseDao implements IDao {

	private Dao<DishType, Integer> dao;
	public DishTypeDao() {
		try {
			dao = getDatabaseHelper(BaseDao.COLLECT_DATA_DB).getDao(DishType.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insert(Object obj) {
		String id = null;
		DishType dishType = (DishType) obj;
		try {
			if (query(dishType.getID()) != null) {
				dao.update(dishType);
			} else {
				dao.createIfNotExists(dishType);
			}
			id = dishType.getID();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Object queryById(String id) {
		DishType dishType = null;
		try {
			dishType = dao.queryBuilder().where().eq("ID", id).queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dishType;
	}

	@Override
	public int update(Object obj) {
		int rows = 0;
		DishType dishType = (DishType) obj;
		try {
			rows = dao.update(dishType);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int delete(Object obj) {
		int rows = 0;
		if (obj != null) {
			try {
				rows = dao.delete((DishType) obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}

	@Override
	public int deleteById(String id) {
		int rows = 0;
		if (id != null) {
			try {
				rows = dao.delete((DishType) query(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rows;
	}

}
