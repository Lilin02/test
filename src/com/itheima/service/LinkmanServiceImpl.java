package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.LinkmanDao;
import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;

@Transactional
public class LinkmanServiceImpl implements LinkmanService {

	private LinkmanDao linkmanDao;

	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}

	@Override
	public void save(Linkman linkman) {
		// TODO 自动生成的方法存根
		System.out.println("service");
		linkmanDao.save(linkman);
	}

	@Override
	public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		// TODO 自动生成的方法存根
		return linkmanDao.findByPage(pageCode, pageSize, criteria);
	}

	@Override
	public void delete(Linkman linkman) {
		// TODO 自动生成的方法存根
		linkmanDao.delete(linkman);
	}

	@Override
	public void update(Linkman linkman) {
		// TODO 自动生成的方法存根
		linkmanDao.update(linkman);
	}

	@Override
	public Linkman findById(long lkm_id) {
		// TODO 自动生成的方法存根
		return linkmanDao.findById(lkm_id);
	}

	@Override
	public List<Linkman> findByCustid(Long id) {
		// TODO 自动生成的方法存根
		System.out.println(id);
		return linkmanDao.findByCustid(id);
	}
	
}
