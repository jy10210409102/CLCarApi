/**
 * 
 */
package com.zhcl.utils.log;


/**
 * 日志输出控制
 * @author zhonghong.chenli
 * date:2015-8-19下午7:31:47	<br/>
 */
public class L {
	static boolean e = true;
	static boolean i = true;
	static boolean w = true;
	private static final int SIZE = 30;
	
	private static String getSizeStr(String tag){
		int emptyCount = 0;
		if(tag == null || "".equals(tag)){
			emptyCount = SIZE;
		}else{
			emptyCount = SIZE - tag.length();
			if(emptyCount < 0){
				return tag.substring(0, SIZE);
			}
		}
		return String.format("%s%" + (emptyCount + 1) + "s", tag, "");
	}
	
	public static void e(String tag, String str){
		if(e){
			System.out.println("<e:> " + getSizeStr(tag) + "\t|" + str);
		}
	}
	
	public static void i(String tag, String str){
		if(i){
//			Log.i(tag, str);
			System.out.println("<i:> " + getSizeStr(tag) + "\t|" + str);
		}
	}
	
	public static void w(String tag, String str){
		if(w){
//			Log.w(tag, str);
			System.out.println("<w:> " + getSizeStr(tag) + "\t|" + str);
		}
	}
}
