package com.itheima.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.CustomerDao;
import com.itheima.domain.Customer;
import com.itheima.domain.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	private CustomerDao customerdao;
	public void setCustomerdao(CustomerDao customerdao) {
		this.customerdao = customerdao;
	}


	@Override
	public void add(Customer customer) {
		System.out.println("业务层...");
		customerdao.save(customer);
	}


	/**
	 *  分页查询
	 */
	public PageBean<Customer> findByPage(Integer pageCode, Integer pageSize, DetachedCriteria criteria) {
		
		return customerdao.findByPage(pageCode,pageSize,criteria);
	}


	/**
	 * 	通过用户Id查找用户
	 */
	public Customer findById(Long cust_id) {
		return customerdao.findById(cust_id);
	}


	@Override
	public void delete(Customer customer) {
		customerdao.delete(customer);
	}


	@Override
	public void update(Customer customer) {
		// TODO 自动生成的方法存根
		customerdao.update(customer);
	}


	@Override
	public List<Customer> findAll() {
		return customerdao.findAll();
	}

}
