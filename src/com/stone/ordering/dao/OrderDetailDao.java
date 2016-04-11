package com.stone.ordering.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.stone.ordering.model.OrderDetail;
import com.stone.ordering.model.IDao;

/**
 * 类名:OrderDetailDao
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月10日
 */
public class OrderDetailDao extends BaseDao implements IDao {

	private Dao<OrderDetail, Integer> dao;
	public OrderDetailDao() {
		try {
			dao = getDatabaseHelper(BaseDao.COLLECT_DATA_DB).getDao(OrderDetail.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insert(Object obj) {
		String id = null;
		OrderDetail OrderDetail = (OrderDetail) obj;
		try {
			if (query(OrderDetail.getID()) != null) {
				dao.update(OrderDetail);
			} else {
				dao.createIfNotExists(OrderDetail);
			}
			id = OrderDetail.getID();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Object queryById(String id) {
		OrderDetail orderDetail = null;
		try {
			orderDetail = dao.queryBuilder().where().eq("ID", id).queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetail;
	}

	@Override
	public int update(Object obj) {
		int rows = 0;
		OrderDetail orderDetail = (OrderDetail) obj;
		try {
			rows = dao.update(orderDetail);
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
				rows = dao.delete((OrderDetail) obj);
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
				rows = dao.delete((OrderDetail) query(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rows;
	}

	/**
	 * 根据订单号查询该订单的所有详情
	 * @param orderID
	 * @return
	 */
	public List<OrderDetail> queryByOrderID(String orderID){
		List<OrderDetail> list = new ArrayList<>();
			try {
				list = dao.queryBuilder().where().eq("OrderID", orderID).query();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		return list;
		
	}
	
}
