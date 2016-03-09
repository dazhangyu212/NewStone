package com.stone.ordering.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;
import com.stone.ordering.model.SysUserModel;
import com.stone.ordering.util.SysUtilManager;

/**
*类名：SysUserDao
*描述：Dao基类
*公司:北京海鑫科金高科技股份有限公司
*作者：tianbin
*版本：V1.0
*创建时间：2015年10月16日
*最后修改时间：2015年10月16日
*/
public class SysUserDao extends BaseDao{

	private Dao<SysUserModel, Integer> dao;
	
	public SysUserDao() {
		try {
			dao = getDatabaseHelper(BaseDao.COLLECT_DATA_DB).getDao(SysUserModel.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 插入一条记录
	 * 
	 * @return 返回id
	 */
	public String insert(SysUserModel sysUserModel) {
		String id = null;
		try {
			if (query(sysUserModel.getId()) != null) {
				dao.update(sysUserModel);
			} else {
				dao.createIfNotExists(sysUserModel);
			}
			id = sysUserModel.getId();
		} catch (SQLException e) {
			SysUtilManager.saveCrashInfo2File(e);
			return null;
		}
		return id;
	}
	/**
	 * 插入多条记录
	 * 
	 * @return 返回成功数量
	 */
	public int insert(ArrayList<SysUserModel> userLists) {
		int record = 0;
		 if(userLists!=null && userLists.size()>0){
			 for (SysUserModel sysUserModel : userLists) {
				 if(insert(sysUserModel)!=null){
					 ++record;
				 }
			}
		 }
		 return record;
	}
	
	
	/**
	 * 删除一条记录
	 * 
	 * @return 返回影响的条数
	 */
	public int delete(SysUserModel sysUserModel) {
		int num = 0;
		try {
			num = dao.delete(sysUserModel);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 删除一条记录
	 * 
	 * @return 返回影响的条数
	 */
	public int deleteByID(String id) throws SQLException {
		int num = 0;
		SysUserModel sysUserModel = query(id);
		if (sysUserModel != null) {
			num = delete(sysUserModel);
		}
		return num;
	}

	/**
	 * 更新一条记录
	 * 
	 */
	public int update(SysUserModel sysUserModel) {
		int num = 0;
		try {
			num = dao.update(sysUserModel);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	/**
	 * 根据ID查询数据
	 * 
	 */
	public SysUserModel query(String id) {
		SysUserModel sysUserModel = null;
		try {

			sysUserModel = dao.queryBuilder().where().eq("ID", id).queryForFirst();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sysUserModel;
	}
	
	/**
	 * 根据用户名查询密码
	 * @param userName
	 * @return
	 */
	public SysUserModel queryByUserName(String userName){
		SysUserModel sysUserModel = null;
		try {
			Where<SysUserModel, Integer> eq = dao.queryBuilder().where().eq("USERNAME", userName);
			sysUserModel = eq.queryForFirst();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sysUserModel;
	}
	
	/**
	 * 获取所有的身份证信息
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<SysUserModel> getAllInfo() throws SQLException{
		ArrayList<SysUserModel> cardInfoList = new ArrayList<SysUserModel>();
		cardInfoList = (ArrayList<SysUserModel>) dao.queryForAll();
		
		return cardInfoList;
	}
}
