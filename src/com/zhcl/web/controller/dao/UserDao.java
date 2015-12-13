/**
 * 
 */
package com.zhcl.web.controller.dao;

import org.hibernate.SessionFactory;

import com.zhcl.web.controller.entity.User;

/** 
 * @author vision.chenli
 * @date 2015年12月13日 下午4:57:38 
*/
public class UserDao implements IUserDao{
	
	/**
	 * 使用此对象可以操作数据库了
	 */
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}


	@Override
	public boolean addUser(User user) {
		sessionFactory.getCurrentSession().save(user);
		return true;
	}

}
