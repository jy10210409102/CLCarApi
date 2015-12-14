/**
 * 
 */
package com.zhcl.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;

import javax.mail.Address;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import com.zhcl.utils.log.L;
import com.zhcl.web.controller.entity.User;

/** 
 * 邮件工具类
 * @author vision.chenli
 * @date 2015年12月13日 下午9:32:09 
*/
public class MailUtils {
	private static final String tag = "MailUtils";
	
	private MailUtils(){}
	private static MailUtils mMailUtils;
	public static MailUtils getInstance(){
		if(mMailUtils == null){
			mMailUtils = new MailUtils();
		}
		return mMailUtils;
	}
	
	/**邮件配置对象*/
	private EMailBuilder mEMailBuilder;
	
	/**
	 * 设置邮件builder
	 */
	public void setEMailBuilder(EMailBuilder mEMailBuilder){
		props = null;
		this.mEMailBuilder = mEMailBuilder;
	}
	
	 private Properties props = null; 
	 
	 /**
	  * 获得属性
	  * @return
	  */
	private Properties getProperties() {
		if(this.mEMailBuilder == null){
			L.w(tag, "mEMailBuilder = null !!!");
			return null;
		}
		if (props != null) {
			return props;
		}
		props = new Properties(); 
		props.setProperty("mail.transport.protocol", this.mEMailBuilder.getProtocol());
		props.setProperty("mail.smtp.host", this.mEMailBuilder.getHost());
		props.setProperty("mail.smtp.port", this.mEMailBuilder.getPort());
		props.setProperty("mail.smtp.auth", this.mEMailBuilder.isAuth() + "");
		props.setProperty("mail.debug", this.mEMailBuilder.isDug() + "");
		return props;
	}

	 /**
     * 发送简单的文本邮件
     */ 
    public boolean sendTextEmail(final EMail email) throws Exception { 
    	new Thread(){
    		public void run(){
    			try {
					sendTextEmailDo(email);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}.start();
    	return true;
    } 
    
    
    public boolean sendTextEmailDo(final EMail email) throws Exception { 
     	
    	if(email == null){
    		L.w(tag, "email = null !!!");
    		return false;
    	}
    	Properties props = getProperties();
    	if(props == null){
    		L.w(tag, "props = null !!!");
    		return false;
    	}
    	String charset = "utf-8";   // 指定中文编码格式 
        // 创建Session实例对象 
        Session session = Session.getDefaultInstance(props); 
        // 创建MimeMessage实例对象 
        MimeMessage message = new MimeMessage(session); 
		// 设置发件人
		message.setFrom(new InternetAddress(this.mEMailBuilder.getUserName()));
		// 设置邮件主题
		message.setSubject(email.theme);
		int toSize = email.to.size();
		Address[] toAddrList = new Address[toSize];
		Set<String> keySet = email.to.keySet();
		int count = 0;
		for (String key : keySet) {
			 // 参数1：邮箱地址，参数2：姓名（在客户端收件只显示姓名，而不显示邮件地址），参数3：姓名中文字符串编码 
			toAddrList[count] = new InternetAddress(key, email.to.get(key), charset);
			count++;
		}
		Address[] ccAddrList = null;
		if(email.cc != null){
			int ccSize = email.cc.size();
			ccAddrList = new Address[ccSize];
			keySet = email.cc.keySet();
			count = 0;
			for (String key : keySet) {
				 // 参数1：邮箱地址，参数2：姓名（在客户端收件只显示姓名，而不显示邮件地址），参数3：姓名中文字符串编码 
				ccAddrList[count] = new InternetAddress(key, email.cc.get(key), charset);
				count++;
			}
		}
        // 设置收件人 
        message.setRecipients(RecipientType.TO, toAddrList); 
        if(ccAddrList != null){
        	  // 设置抄送 
            message.setRecipients(RecipientType.CC, ccAddrList); 
        }
        // 密送 (不会在邮件收件人名单中显示出来) 
        message.setRecipient(RecipientType.BCC, new InternetAddress(this.mEMailBuilder.getUserName())); 
        // 设置发送时间 
        message.setSentDate(new Date()); 
        // 设置纯文本内容为邮件正文 
        message.setText(email.body); 
        // 保存并生成最终的邮件内容 
        message.saveChanges(); 
        // 获得Transport实例对象 
        Transport transport = session.getTransport(); 
        // 打开连接 
        transport.connect(this.mEMailBuilder.getUserName(), this.mEMailBuilder.getPassWord()); 
        // 将message对象传递给transport对象，将邮件发送出去 
        transport.sendMessage(message, message.getAllRecipients()); 
        // 关闭连接 
        transport.close(); 
        return true;
    }
    
    
    /**
     * 发送注册完成邮件
     */
    public void sendRegistEMail(User user){
    	EMail email = new EMail();
    	email = new EMail();
		email.to = new HashMap<String, String>();
		email.to.put(user.getEmail(), user.getUsername());
		email.theme = "CarApi系统邮件";
		email.body = "你好 " + user.getUsername() + " 欢迎使用 CarApi,请妥善保管系统分配的KEY:" + user.getId();
		try {
			MailUtils.getInstance().sendTextEmail(email);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
