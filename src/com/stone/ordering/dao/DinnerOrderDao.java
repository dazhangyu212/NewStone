package com.stone.ordering.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.stone.ordering.model.DinnerOrder;
import com.stone.ordering.model.IDao;
import com.stone.ordering.util.SysUtilManager;

/**
 * 类名:DinnerOrderDao
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月10日
 */
public class DinnerOrderDao extends BaseDao implements IDao {
	
	private Dao<DinnerOrder, Integer> dao;
	public DinnerOrderDao() {
		try {
			dao = getDatabaseHelper(BaseDao.COLLECT_DATA_DB).getDao(DinnerOrder.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insert(Object obj) {
		String id = null;
		DinnerOrder dinnerOrder = (DinnerOrder) obj;
		dinnerOrder.setID(SysUtilManager.getNextId());
		try {
			if (query(dinnerOrder.getID()) != null) {
				dao.update(dinnerOrder);
			} else {
				dao.createIfNotExists(dinnerOrder);
			}
			id = dinnerOrder.getID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Object queryById(String id) {
		DinnerOrder order = null;
		try {
			order = dao.queryBuilder().where().eq("ID", id).queryForFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return order;
	}

	@Override
	public int update(Object obj) {
		int rows = 0;
		DinnerOrder order = (DinnerOrder) obj;
		try {
			rows = dao.update(order);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int delete(Object obj) {
		int rows = 0;
		if (obj != null) {
			try {
				rows = dao.delete((DinnerOrder) obj);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
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
				rows = dao.delete((DinnerOrder) query(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rows;
	}

}
