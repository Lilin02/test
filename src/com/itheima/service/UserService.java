package com.itheima.service;

import java.util.List;

import com.itheima.domain.User;

public interface UserService {

	public User checkCode(String user_code);

	public void save(User user);

	public User login(User user);

	public List<User> findAll();

	public void update(User user);

	
}
