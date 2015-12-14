/**
 * 
 */
package com.zhcl.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.zhcl.utils.EMail;
import com.zhcl.utils.MailUtils;
import com.zhcl.utils.PageUtils;
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
	@Resource(name="userManager")
	private IUserManager userManager;
	
	EMail email;
	public UserController(){ }
	
	@RequestMapping("/toAddUser") //精简后的写法,直接返回字符串
	public String toAddUser(User user) {
		L.e(tag, "toAddUser");
		return "addUser";
	}
	
	@RequestMapping("/addUser") //精简后的写法,直接返回字符串
	public String addUser(User user, HttpServletRequest request, HttpServletResponse response) {
		if(userManager.exists(user.getUsername())){
			L.e(tag, user.getUsername() + "已经存在！");
			return PageUtils.getInstance().errorToPage(request, response, "异常：用户已被注册");
		}
		userManager.addUser(user);
		MailUtils.getInstance().sendRegistEMail(user);
		request.setAttribute("result", "已发送邮件至邮箱：" + user.getEmail());
		return "spring";
	}
	
	
	@RequestMapping(value = "/delUser", method = RequestMethod.GET)	//完整写法
	public ModelAndView delUser(HttpServletRequest arg0, HttpServletResponse arg1) {
		L.e(tag, "delUser");
		return new ModelAndView("spring");
	}
}
 