package com.bootdo.system.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2018-03-31 11:22:34
 */
public class TestDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer uid;
	//
	private String uname;
	//
	private Date birth;

	/**
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：
	 */
	public void setUname(String uname) {
		this.uname = uname;
	}
	/**
	 * 获取：
	 */
	public String getUname() {
		return uname;
	}
	/**
	 * 设置：
	 */
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	/**
	 * 获取：
	 */
	public Date getBirth() {
		return birth;
	}
}
