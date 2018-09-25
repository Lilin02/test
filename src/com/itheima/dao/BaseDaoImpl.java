package com.itheima.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;
//import com.sun.xml.internal.ws.wsdl.writer.document.Types;

/**
 * 以后所有的Dao实现类都可以继承BaseDaoImpl，增删改查分页就不用写了
 * @author pc
 *
 * @param <T>
 */

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;
	public BaseDaoImpl(){
		//启动服务器时，配置文件加载， CustomerDaoImpl类创建对象，然后加载父类，父类才会创建
		//this表示子类，c表示就是 CustomerDaoImpl类对象
		Class c = this.getClass();
		//获取到BaseDaoImpl<Customer>
		Type type = c.getGenericSuperclass();
		
		//把type接口转换成子接口
		if(type instanceof ParameterizedType){
			ParameterizedType pType = (ParameterizedType) type;
			
			//获取Customer
			Type[] types = pType.getActualTypeArguments();
			this.clazz = (Class) types[0];
		}
	}
	
	public void save(T t) {
		this.getHibernateTemplate().save(t);
	}

	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	public T findById(Long id) {
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	public List<T> findAll() {		
		return (List<T>) this.getHibernateTemplate().find("from "+clazz.getSimpleName());
	}

	public PageBean<T> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		//创建分页对象
		PageBean<T> page = new PageBean<T>();
		//设置属性
		page.setPageCode(pageCode);
		page.setPageSize(pageSize);
		
		//设置查询聚合函数：sql相当于select count(*) from
		criteria.setProjection(Projections.rowCount());
		List<Number> list = (List<Number>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() > 0){
			int totalCount = list.get(0).intValue();
			//总记录数
			System.out.println(totalCount);
			page.setTotalCount(totalCount);
		}
		
		//清除sql select * from
		criteria.setProjection(null);
		
		List<T> beanList = (List<T>) this.getHibernateTemplate().findByCriteria(criteria, (pageCode - 1)*pageSize, pageSize);
		//每页显示的数据
		page.setBeanList(beanList);
		return page;
	}

}
