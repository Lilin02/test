package com.itheima.service;

import java.util.List;

import com.itheima.domain.Dict;

public interface DictService {

	public List<Dict> findByCode(String dict_type_code);

}
