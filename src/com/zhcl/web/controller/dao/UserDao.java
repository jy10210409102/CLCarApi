/**
 * 
 */
package com.zhcl.web.controller.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.zhcl.web.controller.entity.User;

/** 
 * 如果有函数要使用currentSession,需要在spring-hibernate.xml 定义
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
	
	@Override
	public boolean exists(String name) {
		Session session = sessionFactory.getCurrentSession();
	    String hql="select t.username from user t where t.username = '" + name + "'";//使用命名参数，推荐使用，易读。
	    Query query=session.createSQLQuery(hql);
	    Object result = query.uniqueResult(); 
		return result != null;
	}
}
