package com.itheima.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.domain.User;

/**
 * 持久层	都可以继承hibernateDaoSupport类，自带模板属性
 * @author 我的
 *
 */
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	/**
	 * 登录名进行验证
	 */
	public User checkCode(String user_code) {
		List<User> list = (List<User>) this.getHibernateTemplate().find("from User where user_code=?", user_code);
		if(list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 保存用户
	 */
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	/**
	 * 登录功能
	 * 通过用户名和密码和用户状态
	 */
	public User login(User user) {
		//	QBC的查询，按条件进行查询
		DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		
		//拼接查询条件
		criteria.add(Restrictions.eq("user_code", user.getUser_code()));
		criteria.add(Restrictions.eq("user_password", user.getUser_password()));
		criteria.add(Restrictions.eq("user_state", "1"));
		
		// 查询
		List<User> list = (List<User>) this.getHibernateTemplate().findByCriteria(criteria);
		if(list != null && list.size() >0) {
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO 自动生成的方法存根
		return (List<User>) this.getHibernateTemplate().find("from User");
	}

	@Override
	public void update(User user) {
		// TODO 自动生成的方法存根
		this.getHibernateTemplate().update(user);
	}

	
}
