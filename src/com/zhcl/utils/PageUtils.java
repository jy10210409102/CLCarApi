/**
 * 
 */
package com.zhcl.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** 
 * @ClassName: PageUtils 
 * @author zhonghong.chenli
 * @date 2015年12月14日 下午3:41:25  
 */
public class PageUtils {
	private static final String tag = "PageUtils";
	private PageUtils(){}
	private static PageUtils mPageUtils;
	public static PageUtils getInstance(){
		if(mPageUtils == null){
			mPageUtils = new PageUtils();
		}
		return mPageUtils;
	}
	
	/**
	 * spring 跳转至错误界面
	 */
	public String errorToPage(HttpServletRequest request, HttpServletResponse response, String tip){
		request.setAttribute("error", tip);
		return "error";
	}
	
}
