/**
 * 
 */
package com.zhcl.web.controller.service;

import com.zhcl.web.controller.dao.IUserDao;
import com.zhcl.web.controller.entity.User;

/**
 * @author vision.chenli
 * @date 2015年12月13日 下午5:06:04
 */

public class UserManager implements IUserManager {
	
	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}

}
