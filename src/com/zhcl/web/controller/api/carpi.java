/**
 * 
 */
package com.zhcl.web.controller.api;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zhcl.utils.log.L;

/**
 * @author vision.chenli
 * @date 2015年12月12日 下午4:46:48
 */
@Controller
@RequestMapping("/api-json")		//根连接，方便分类避免重复
public class carpi {
	private static final String tag = "carpi";
	public carpi(){ }
	
	@RequestMapping("/requesttime") //精简后的写法,直接返回字符串
	public void requesttime(HttpServletRequest request, HttpServletResponse response) {
		String result = "{\"time\":\" " + System.currentTimeMillis() + "}";
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
}
 