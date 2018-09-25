package com.itheima.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.domain.PageBean;
import com.itheima.domain.Visit;

public interface VisitService {

	void save(Visit visit);

	PageBean<Visit> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria);

	void delete(Visit visit);

	void update(Visit visit);

	Visit findById(Long visit_id);

}
