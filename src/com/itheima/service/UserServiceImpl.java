package com.itheima.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.itheima.dao.UserDao;
import com.itheima.domain.User;
import com.itheima.utils.MD5Utils;

/**
 * 用户的业务层
 * @author 我的
 *
 */
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User checkCode(String user_code) {
		User user = userDao.checkCode(user_code);
		return user;
	}

	/**
	 * 保存用户，密码需要加密
	 */
	public void save(User user) {
		String pwd = user.getUser_password();
		//给密码加密
		user.setUser_password(MD5Utils.md5(pwd));
		//用户的状态默认是1状态
		user.setUser_state("1");
		
		userDao.save(user);
	}

	/**
	 * 登录通过登录名和密码做校验
	 * 先给密码加密，再查询
	 */
	public User login(User user) {
		String pwd = user.getUser_password();
		user.setUser_password(MD5Utils.md5(pwd));
		
		return userDao.login(user);
	}

	@Override
	public List<User> findAll() {
		// TODO 自动生成的方法存根
		return userDao.findAll();
	}

	@Override
	public void update(User user) {
		// TODO 自动生成的方法存根
		userDao.update(user);
	}
	
}
