/**
 * 
 */
package com.zhcl.web.controller.dao;

import com.zhcl.web.controller.entity.User;

/** 
 * spring 框架 要求使用接口
 * @author vision.chenli
 * @date 2015年12月13日 下午4:56:13 
*/
public interface IUserDao {
	
	public boolean addUser(User user);
}
