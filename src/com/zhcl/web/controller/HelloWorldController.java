/**
 * 
 */
package com.zhcl.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.zhcl.log.L;

/** 
 * spring测试 ，主要使用注解开发
 * @author vision.chenli
 * @date 2015年12月12日 上午1:33:09 
*/
public class HelloWorldController implements Controller{

	private static final String tag = "HelloWorldController";
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		// TODO Auto-generated method stub
		L.i(tag, "ModelAndView");
		String hello = "spring hello";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("map1", "你好");
		map.put("map2", "spring");
//		return new ModelAndView("upload", "result", hello); //回到那个视图
		return new ModelAndView("upload", "map", map);
	}
	
}
