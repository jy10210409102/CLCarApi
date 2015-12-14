/**
 * 
 */
package com.zhcl.web.controller.service;

import org.springframework.stereotype.Service;

import com.zhcl.web.controller.dao.IUserDao;
import com.zhcl.web.controller.entity.User;

/**
 * @author vision.chenli
 * @date 2015年12月13日 下午5:06:04
 */
@Service
public class UserManager implements IUserManager{
	
	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public boolean exists(String name) {
		return userDao.exists(name);
	}

}
