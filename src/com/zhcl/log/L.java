/**
 * 
 */
package com.zhcl.log;


/**
 * 日志输出控制
 * @author zhonghong.chenli
 * date:2015-8-19下午7:31:47	<br/>
 */
public class L {
	static boolean e = true;
	static boolean i = true;
	static boolean w = true;
	
	public static void e(String tag, String str){
		if(e){
			//System.out.println("tag:" + tag + " str:" + str);
			System.out.println("e: " + tag + "\t" + str + "\r\n");
		}
	}
	
	public static void i(String tag, String str){
		if(i){
			//System.out.println("tag:" + tag + " str:" + str);
//			Log.i(tag, str);
			System.out.println("i: " + tag + "\t" + str + "\r\n");
		}
	}
	
	public static void w(String tag, String str){
		if(w){
			//System.out.println("tag:" + tag + " str:" + str);
//			Log.w(tag, str);
			System.out.println("w: " + tag + "\t" + str + "\r\n");
		}
	}
}
