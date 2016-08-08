/**
 * @Title:  MyShiroRealm.java
 * @Package:  com.cloud.erp.shiro.realm
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  下午2:40:30
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.shiro.realm;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cloud.erp.entities.shiro.ShiroUser;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.service.ShiroService;
import com.cloud.erp.utils.Constants;


/**
 * @ClassName  MyShiroRealm
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  下午2:40:30
 *
 */
public class MyShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private ShiroService shiroService;
	
//	private static final String REALM_CAPTCHA_ERROR = "验证码错误！";

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		//ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName()).iterator().next();
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		String username = shiroUser.getAccount();
		if(username != null){
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			List<Object> permissions = null;
			if(Constants.SYSTEM_ADMINISTRATOR.equals(username)){
				permissions = shiroService.getPermissions(true, "");
			}else{
				permissions = shiroService.getPermissions(false, username);
			}
			
			if(permissions != null && permissions.size() > 0){
				for(Object permission : permissions){
					Object[] perm = (Object[])permission;
					info.addStringPermission(perm[1].toString());
				}
				return info;
			}
		}
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		//CaptchaUsernamePasswordToken token = (CaptchaUsernamePasswordToken) authcToken;

		//get username via form
//		String username = token.getUsername();
		String username = (String) authcToken.getPrincipal();
		
//		if(StringUtils.hasText(username) && doCaptchaValidate(token)){
		if(StringUtils.hasText(username)){
			User user = shiroService.getUser(username);
			if(user != null){
				Subject subject = SecurityUtils.getSubject();
				subject.getSession().setAttribute(Constants.SHIRO_USER, new ShiroUser(user.getUserId(), user.getName()));
				//return new SimpleAuthenticationInfo(new ShiroUser(user.getUserId(), user.getName()),user.getPassword(),getName());
				return new SimpleAuthenticationInfo(new ShiroUser(user.getUserId(), user.getName()), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), getName());
			}
		}
		
		return null;
	}

	/**
	 * 更新用户授权信息缓存.
	 */

	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */

	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
		
//	/**
//	 * @param token
//	 * @return
//	 */
//	private boolean doCaptchaValidate(CaptchaUsernamePasswordToken token) {
//		
//		String captcha = (String) ServletActionContext.getRequest().getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
//		
//		if(captcha != null && !captcha.equalsIgnoreCase(token.getCaptcha())){
//			throw new IncorrectCaptchaException(REALM_CAPTCHA_ERROR);
//		}
//		
//		return true;
//	}
}
