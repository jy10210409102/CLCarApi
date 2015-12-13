/**
 * 
 */
package com.zhcl.web.controller.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author vision.chenli
 * @date 2015年12月13日 下午4:19:25
 */
@Entity
@Table(name = "user") // 表名
public class User {
	// 20
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 32)
	private String id;
	// 32
	@Column(length = 32, nullable = true)
	private String username;
	// 16
	@Column(length = 16, nullable = true)
	private String password;
	// 50
	@Column(length = 50, nullable = true)
	private String email;
	// 100
	@Column(length = 100)
	private String address;
	// 32
	@Column(length = 32)
	private String phonenum;
	// 20
	@Column(length = 20)
	private String type;
	@Column(nullable = true)
	private long registtime = 0;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		setRegisttime(System.currentTimeMillis());
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public long getRegisttime() {
		return registtime;
	}
	public void setRegisttime(long registtime) {
		this.registtime = registtime;
	}
	
	
}
