/**
 * 
 */
package com.zhcl.web.controller.api;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhcl.utils.log.L;

/**
 * 提供简单功能
 * @author vision.chenli
 * @date 2015年12月12日 下午4:46:48
 */
@Controller
@RequestMapping("/api-json")		//根连接，方便分类避免重复
public class carpi {
	private static final String tag = "carpi";
	public carpi(){}
	
	
	
	@RequestMapping("/requesttime") //精简后的写法,直接返回字符串
	public void requesttime(HttpServletRequest request, HttpServletResponse response) {
		String result = "{\"time\":\" " + System.currentTimeMillis() + "}";
		final int MAXCOUNT = 10;
		if(requestCount(request, MAXCOUNT) >= MAXCOUNT){
			result = "使用次数已到";
		}
		L.i(tag, "result = " + result);
		PrintWriter out = null;
		response.setContentType("application/json"); 
		try{
			out = response.getWriter();
			out.write(result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 限制用户一天只能访问10次
	 */
	private int requestCount(HttpServletRequest request, int maxCount){
		String key = request.getHeader("key");
		L.e(tag, "key = " + key);
		HttpSession session = request.getSession(true);
		// 获取 session 创建时间
		Date createTime = new Date(session.getCreationTime());
		// 获取该网页的最后一次访问时间
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		String title = "欢迎回到我的网站";
		int visitCount = 0;
		String visitCountKey = "visitCount";
		String userIDKey = "userID";
		String userID = "ABCD";
		L.i(tag, "requestCount");
		// 检查网页上是否有新的访问者
		if (session.getAttribute(visitCountKey) == null){
			session.setAttribute(userIDKey, userID);
			session.setMaxInactiveInterval(30 * 60);	//设置session有效时间，已在web.xml中配置
			L.i(tag, "第一次创建");
		} else {
			visitCount = (Integer) session.getAttribute(visitCountKey);
			visitCount = visitCount + 1;
			userID = (String) session.getAttribute(userIDKey);
		}
		session.setAttribute(visitCountKey, visitCount);
		return visitCount;
	}
	
}
 