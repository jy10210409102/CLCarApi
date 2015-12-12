/**
 * 
 */
package com.zhcl.web.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/** 
 * spring测试 ，多个请求在一个类中处理
 * @author vision.chenli
 * @date 2015年12月12日 上午1:33:09 
*/
public class HelloWorldController2 extends MultiActionController{

	private static final String tag = "HelloWorldController";
	
	public ModelAndView add(){
		return null;
	}
	
	public ModelAndView update(){
		return null;
	}

}
