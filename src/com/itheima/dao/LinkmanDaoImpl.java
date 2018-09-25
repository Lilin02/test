package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Linkman;

public class LinkmanDaoImpl extends BaseDaoImpl<Linkman> implements LinkmanDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<Linkman> findByCustid(Long id) {
		// TODO 自动生成的方法存根
		System.out.println(id);
		return (List<Linkman>) this.getHibernateTemplate().find("from Linkman where lkm_cust_id =?", id);
	}

}
