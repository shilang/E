/**
 * @Title:  LoginAction.java
 * @Package:  com.cloud.erp.actions
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月4日  下午4:55:28
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.actions;

import javax.naming.AuthenticationException;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.common.BaseAction;
import com.cloud.erp.entities.shiro.ShiroUser;
import com.cloud.erp.entities.viewmodel.Account;
import com.cloud.erp.service.LoginService;
import com.cloud.erp.service.UserService;
import com.cloud.erp.utils.Commons;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName  LoginAction
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月4日  下午4:55:28
 *
 */
@Namespace("/")
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private UserService userService;
	
	private Integer menuId;
	
	private boolean success;
	
	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public boolean getSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Action(value = "login", results = {@Result(name="input", location="/login.jsp")})
	public String load() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		String shiroLoginFailure = (String) request.getAttribute("shiroLoginFailure");
		String loginErr = null;
		setSuccess(true);
		if (null != shiroLoginFailure){
			if(UnknownAccountException.class.getName().equals(shiroLoginFailure)) {
				loginErr = "未知用户";
			}else if(IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)) {
				loginErr = "密码错误";
			}else if(LockedAccountException.class.getName().equals(shiroLoginFailure)){
				loginErr = "账户锁定";
			}else if(Constants.CAPTCHA_ERR.equals(shiroLoginFailure)){
				loginErr = "验证码错误";
			}else if(AuthenticationException.class.getName().equals(shiroLoginFailure)) {
				loginErr = "认证错误";
			}else{
				loginErr = "未知错误";
			}
			setSuccess(false);
			request.setAttribute("loginMsg", loginErr);
		}
		return INPUT;
	}
	
	@Action(value = "getAccount")
	public String getAccount() throws Exception{
		OutputJson(getCurrUser());
		return null;
	}
	
	@Action(value = "getAccounts")
	public Account getAccounts() throws Exception{
		ShiroUser user = getCurrUser();
		Account account = new Account();
		account.setAccId(user.getUserId());
		account.setAccName(user.getAccount());
		account.setUsers(userService.findUsers());
		OutputJson(account);
		return null;
	}
	
	/**
	 * 
	 * function: search all menu for the user
	 *
	 * @Title:  findAllFunctionList
	 * @param: 
	 * @return:  String
	 * @throws:
	 */
	@Action(value = "findMenus")
	public String findAllFunctionList() throws Exception{
		OutputJson(loginService.findMenuList(getMenuId()));
		return null;
	}
	
	private ShiroUser getCurrUser(){
		ShiroUser user = new ShiroUser();
		user.setUserId(Commons.getCurrentUser().getUserId());
		user.setAccount(Commons.getCurrentUser().getAccount());
		return user;
	}

}
