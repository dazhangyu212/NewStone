package com.stone.ordering.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.stone.ordering.model.Dish;
import com.stone.ordering.model.IDao;

/**
 * 类名:DishDao
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月10日
 */
public class DishDao extends BaseDao implements IDao {

	private Dao<Dish, Integer> dao;
	public DishDao() {
		try {
			dao = getDatabaseHelper(BaseDao.COLLECT_DATA_DB).getDao(Dish.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insert(Object obj) {
		String id = null;
		Dish dish = (Dish) obj;
		try {
			if (query(dish.getID()) != null) {
				dao.update(dish);
			} else {
				dao.createIfNotExists(dish);
			}
			id = dish.getID();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Object queryById(String id) {
		Dish dish = null;
		try {
			dish = dao.queryBuilder().where().eq("ID", id).queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dish;
	}

	@Override
	public int update(Object obj) {
		int rows = 0;
		Dish dish = (Dish) obj;
		try {
			rows = dao.update(dish);
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
				rows = dao.delete((Dish) obj);
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
				rows = dao.delete((Dish) query(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rows;
	}
	
	public List<Dish> queryAll(){
		List<Dish> list = new ArrayList<Dish>();
		try {
			list = dao.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
				
	}

}
