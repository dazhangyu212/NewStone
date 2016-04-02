package com.stone.ordering.dao;

import java.sql.SQLException;

import com.j256.ormlite.dao.Dao;
import com.stone.ordering.model.User;
import com.stone.ordering.model.IDao;

/**
 * 类名:UserDao
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月10日
 */
public class UserDao extends BaseDao implements IDao {

	private Dao<User, Integer> dao;
	public UserDao() {
		try {
			dao = getDatabaseHelper(BaseDao.COLLECT_DATA_DB).getDao(User.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String insert(Object obj) {
		String id = null;
		User user = (User) obj;
		try {
			if (query(user.getID()) != null) {
				dao.update(user);
			} else {
				dao.createIfNotExists(user);
			}
			id = user.getID();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	
	/**
	 * 根据用户名查询
	 * @param uName
	 * @return
	 */
	public User queryByName(String uName){
		User user = null;
		try {
			user = (User) dao.queryBuilder().where().eq("UserName", uName).queryForFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public Object queryById(String id) {
		User user = null;
		try {
			user = dao.queryBuilder().where().eq("ID", id).queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public int update(Object obj) {
		int rows = 0;
		User user = (User) obj;
		try {
			rows = dao.update(user);
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
				rows = dao.delete((User) obj);
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
				rows = dao.delete((User) query(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rows;
	}

}
