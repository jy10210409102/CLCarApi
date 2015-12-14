/**
 * 
 */
package com.zhcl.init;

import com.zhcl.utils.EMailBuilder;
import com.zhcl.utils.MailUtils;
import com.zhcl.utils.log.L;

/** 
 * @ClassName: InitMnager 
 * @author zhonghong.chenli
 * @date 2015年12月14日 下午4:00:01  
 */
public class InitMnager {
	private static final String tag = "InitMnager";
	private InitMnager(){}
	private static InitMnager mInitMnager;
	public static InitMnager getInstance(){
		if(mInitMnager == null){
			mInitMnager = new InitMnager();
		}
		return mInitMnager;
	}
	
	/**
	 * 初始化
	 */
	public void init(){
		L.i(tag, "-------------初始化----------");
		initSystemEmail();
	}
	
	/**
	 * 销毁
	 */
	public void destroy(){
		L.i(tag, "-------------销毁----------");
	}
	
	/**
	 * 初始化系统邮件配置
	 */
	public void initSystemEmail(){
		L.i(tag, "初始化系统邮件");
		MailUtils mMailUtils = MailUtils.getInstance();
		EMailBuilder mEMailBuilder = new EMailBuilder();
		mEMailBuilder.setUserInfo("wearefamily.link@aliyun.com", "chenli0925")
				.setProtocol("smtp", "smtp.aliyun.com", "25").setDebug(true);
		mMailUtils.setEMailBuilder(mEMailBuilder);
	}
	
}	
