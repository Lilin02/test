package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;

/**
 * 以后所有的Dao接口都需要继承BaseDao接口
 * @author pc
 *
 */
public interface BaseDao<T> {

	/**
	 * 保存
	 * @param t
	 */
	public void save(T t);
	
	/**
	 * 删除
	 * @param t
	 */
	public void delete(T t);
	
	/**
	 * 修改
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public T findById(Long id);
	
	/**
	 * 查找全部
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 分页
	 * @param pageCode
	 * @param pageSize
	 * @param criteria
	 * @return
	 */
	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);
		
}
