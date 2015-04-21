/**
 * @Title:  ShiroUser.java
 * @Package:  com.cloud.erp.shiro.entities
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  下午3:47:43
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.entities.shiro;

import java.io.Serializable;

/**
 * @ClassName  ShiroUser
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  下午3:47:43
 *
 */
public class ShiroUser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private String account;
	
	/**
	 * @param userId
	 * @param account
	 */
	public ShiroUser(Integer userId, String account) {
		super();
		this.userId = userId;
		this.account = account;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return account;
	}
	
	
}
