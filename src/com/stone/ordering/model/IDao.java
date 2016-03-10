package com.stone.ordering.model;
/**
 * 类名:Dao
 * 描述:
 * 公司:北京海鑫科技高科技股份有限公司
 * 作者:zhangyu
 * 创建时间:2016年3月10日
 */
public interface IDao {
	/**
	 * 插入数据
	 * @param obj 传入的数据需对应当期的IDao所需类
	 * @return
	 */
	public String insert(Object obj);
	/**
	 * 查询
	 * @param id
	 * @return
	 */
	public Object queryById(String id);
	/**
	 * 修改餐桌数据
	 * @param 
	 * @return
	 */
	public int update(Object obj);
	/**
	 * 删除数据
	 * @param obj
	 * @return
	 */
	public int delete(Object obj);
	/**
	 * 删除数据
	 * @param id
	 * @return
	 */
	public int deleteById(String id);
}
