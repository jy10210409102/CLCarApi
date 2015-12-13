/**
 * 
 */
package com.zhcl.web.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhcl.utils.EMail;
import com.zhcl.utils.EMailBuilder;
import com.zhcl.utils.MailUtils;

/**
 * @author vision.chenli
 * @date 2015年12月12日 下午4:46:48
 */
@Controller
@RequestMapping("/notify")		//根连接，方便分类避免重复
public class NotifyController {
	private static final String tag = "UserController";
	EMail email;
	public NotifyController() {
		MailUtils mMailUtils = MailUtils.getInstance();
		EMailBuilder mEMailBuilder = new EMailBuilder();
		mEMailBuilder.setUserInfo("wearefamily.link@aliyun.com", "chenli0925")
		.setProtocol("smtp", "smtp.aliyun.com", "25")
		.setDebug(true);
		mMailUtils.setEMailBuilder(mEMailBuilder);
	}
	
	@RequestMapping("/sendemail") //精简后的写法,直接返回字符串
	public String send() {
		email = new EMail();
		email.to = new HashMap<String, String>();
		email.to.put("chenli.cl@aliyun.com", "chenli");
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
}
 