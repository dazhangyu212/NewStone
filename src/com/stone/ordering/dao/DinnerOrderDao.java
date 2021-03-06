package com.stone.ordering.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
			e.printStackTrace();
		}
		return id;
	}

	public String insert(DinnerOrder order,String id){
		if (id != null && !"".equals(order)) {
			try {
				if (query(id) != null) {
					dao.update(order);
				} else {
					dao.createIfNotExists(order);
				}
				id = order.getID();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}
	
	@Override
	public Object queryById(String id) {
		DinnerOrder order = null;
		try {
			order = dao.queryBuilder().where().eq("ID", id).queryForFirst();
		} catch (SQLException e) {
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
	
	/**
	 * 查询全部
	 * @return
	 */
	public List<DinnerOrder> queryAll(){
		List<DinnerOrder> list = new ArrayList<DinnerOrder>();
		try {
			list = dao.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 按是否付款查询
	 * @return
	 */
	public List<DinnerOrder> queryOrders(int isCharged){
		List<DinnerOrder> list = new ArrayList<DinnerOrder>();
		try {
			list = dao.queryBuilder().where().eq("Charge", isCharged).query();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
