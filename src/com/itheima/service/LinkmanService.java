package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.Linkman;
import com.itheima.domain.PageBean;

public interface LinkmanService {

	public void save(Linkman linkman);

	public PageBean<Linkman> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	public void delete(Linkman linkman);

	public void update(Linkman linkman);

	public Linkman findById(long lkm_id);

	public List<Linkman> findByCustid(Long id);

}
