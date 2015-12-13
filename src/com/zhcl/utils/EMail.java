/**
 * 
 */
package com.zhcl.utils;

import java.util.HashMap;

/** 
 * @author vision.chenli
 * @date 2015年12月13日 下午10:34:09 
*/
public class EMail {
	/** 发送至 */
	public HashMap<String, String> to;
	/** 抄送*/
	public HashMap<String, String> cc;
	/** 主题 */
	public String theme;
	/** 内容 */
	public String body;
}
