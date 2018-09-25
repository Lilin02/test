package com.itheima.service;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.VisitDao;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;
import com.itheima.domain.Visit;

@Transactional
public class VisitServiceImpl implements VisitService {

	private VisitDao visitDao;

	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}

	@Override
	public void save(Visit visit) {
		// TODO 自动生成的方法存根
		visitDao.save(visit);
	}

	@Override
	public PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		// TODO 自动生成的方法存根
		return visitDao.findByPage(pageCode, pageSize, criteria);
	}

	@Override
	public void delete(Visit visit) {
		// TODO 自动生成的方法存根
		visitDao.delete(visit);
	}

	@Override
	public void update(Visit visit) {
		// TODO 自动生成的方法存根
		visitDao.update(visit);
	}

	@Override
	public Visit findById(Long visit_id) {
		// TODO 自动生成的方法存根
		return visitDao.findById(visit_id);
	}
	
}
