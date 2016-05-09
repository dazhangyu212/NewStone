package com.stone.ordering.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.j256.ormlite.dao.Dao;
import com.stone.ordering.model.DiningTable;
import com.stone.ordering.model.IDao;

/**
 * 类名:DiningTableDao
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月10日
 */
public class DiningTableDao extends BaseDao implements IDao{
	
	private Dao<DiningTable, Integer> dao ;
	
	
	public DiningTableDao() {
		try {
			dao = getDatabaseHelper(BaseDao.COLLECT_DATA_DB).getDao(DiningTable.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 插入多条记录
	 * @param tables
	 * @return 返回成功数量
	 */
	public int insert(ArrayList<DiningTable> tables) {
		int record = 0;
		 if(tables!=null && tables.size()>0){
			 for (DiningTable table: tables) {
				 if(insert(table)!=null){
					 ++record;
				 }
			}
		 }
		 return record;
	}
	
	@Override
	public String insert(Object obj) {
		String id = null;
		DiningTable diningTable = (DiningTable) obj;
		try {
			if (query(diningTable.getID()) != null) {
				dao.update(diningTable);
			} else {
				dao.createIfNotExists(diningTable);
			}
			id = diningTable.getID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public Object queryById(String id) {
		DiningTable table = null;
		try {
			table = dao.queryBuilder().where().eq("ID", id).queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}

	@Override
	public int update(Object obj) {
		int rows = 0;
		DiningTable table = (DiningTable) obj;
		try {
			rows = dao.update(table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int deleteById(String id) {
		int rows = 0;
		if (id != null) {
			try {
				rows = dao.delete((DiningTable) query(id));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rows;
		
	}

	@Override
	public int delete(Object obj) {
		int rows = 0;
		if (obj != null) {
			try {
				rows = dao.delete((DiningTable) obj);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rows;
	}
	
	public ArrayList<DiningTable> queryAll() {
		ArrayList<DiningTable> list = new ArrayList<DiningTable>();
		try {
			list = (ArrayList<DiningTable>) dao.queryForAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
