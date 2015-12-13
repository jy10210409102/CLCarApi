/**
 * 
 */
package com.zhcl.web.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhcl.utils.EMail;
import com.zhcl.utils.EMailBuilder;
import com.zhcl.utils.MailUtils;
import com.zhcl.utils.log.L;
import com.zhcl.web.controller.entity.User;
import com.zhcl.web.controller.service.IUserManager;

/**
 * @author vision.chenli
 * @date 2015年12月12日 下午4:46:48
 */
@Controller
@RequestMapping("/user")		//根连接，方便分类避免重复
public class UserController {
	private static final String tag = "UserController";
	EMail email;
	public UserController(){
		MailUtils mMailUtils = MailUtils.getInstance();
		EMailBuilder mEMailBuilder = new EMailBuilder();
		mEMailBuilder.setUserInfo("wearefamily.link@aliyun.com", "chenli0925")
		.setProtocol("smtp", "smtp.aliyun.com", "25")
		.setDebug(true);
		mMailUtils.setEMailBuilder(mEMailBuilder);
	}
	
	@Resource(name="userManager")
	private IUserManager userManager;
	
	@RequestMapping("/toAddUser") //精简后的写法,直接返回字符串
	public String toAddUser(User user) {
		L.e(tag, "toAddUser");
		return "addUser";
	}
	
	@RequestMapping("/addUser") //精简后的写法,直接返回字符串
	public String addUser(User user) {
		L.e(tag, "addUser");  
		L.i(tag, "name : " + user.getUsername());
		L.i(tag, "address : " + user.getAddress()); 
		L.i(tag, "email:" + user.getEmail());
		userManager.addUser(user);
		email = new EMail();
		email.to = new HashMap<String, String>();
		email.to.put(user.getEmail(), "chenli");
		email.theme = "系统邮件";
		email.body = "欢迎使用 CarApi";
		try {
			MailUtils.getInstance().sendTextEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "spring";
	}
	
	
	@RequestMapping(value = "/delUser", method = RequestMethod.GET)	//完整写法
	public ModelAndView delUser(HttpServletRequest arg0, HttpServletResponse arg1) {
		L.e(tag, "delUser");
		return new ModelAndView("spring");
	}
}
 