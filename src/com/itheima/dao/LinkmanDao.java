package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Linkman;

public interface LinkmanDao extends BaseDao<Linkman>{

	List<Linkman> findByCustid(Long id);


}
