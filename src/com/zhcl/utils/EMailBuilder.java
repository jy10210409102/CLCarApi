/**
 * 
 */
package com.zhcl.utils;

import com.zhcl.utils.log.L;

/**
 * @author vision.chenli
 * @date 2015年12月13日 下午10:25:47
 */
public class EMailBuilder {
	private static final String tag = "MailBuilder";
	/** 用户名 */
	private String userName;
	/** 密码 */
	private String passWord;
	/** 邮件发送协议 如:"smtp" */
	private String protocol;
	/** host 如: "smtp.aliyun.com" */
	private String host;
	/** port 如:"25" */
	private String port;
	/** 是否身份认证 */
	private boolean isAuth;
	/** 否启用调试模式（启用调试模式可打印客户端与服务器交互过程时一问一答的响应消息） */
	private boolean isDug;

	/**
	 * 配置用户信息，即发送端的邮箱地址
	 * 
	 * @param userName
	 * @return
	 */
	public EMailBuilder setUserInfo(String userName, String passWord) {
		if (isEmpty(userName) || isEmpty(passWord)) {
			L.w(tag, "userName is empty or passWord is empty");
			return this;
		}
		this.userName = userName;
		this.passWord = passWord;
		return this;
	}

	private boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	/**
	 * 配置邮件发送协议
	 * 
	 * @param protocol
	 *            协议
	 * @param host
	 *            SMTP邮件服务器
	 * @param port
	 *            SMTP邮件服务器默认端口
	 * @return
	 */
	public EMailBuilder setProtocol(String protocol, String host, String port) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
		return this;
	}

	/**
	 * 设置是否要求身份认证
	 */
	public EMailBuilder setDebug(boolean isDebug) {
		this.isDug = isDebug;
		return this;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getProtocol() {
		return protocol;
	}

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public boolean isAuth() {
		return isAuth;
	}

	public boolean isDug() {
		return isDug;
	}
}
