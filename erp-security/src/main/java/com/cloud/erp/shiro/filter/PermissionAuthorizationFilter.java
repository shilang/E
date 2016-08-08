/**
 * @Title:  PermissionAuthorizationFilter.java
 * @Package:  com.cloud.erp.shiro.filter
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  下午6:05:13
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;

/**
 * @ClassName  PermissionAuthorizationFilter
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  下午6:05:13
 *
 */
public class PermissionAuthorizationFilter extends AuthorizationFilter {

	private static final Logger log = Logger.getLogger(PermissionAuthorizationFilter.class);

	@Override
	protected boolean isAccessAllowed(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		
		if(log.isDebugEnabled()){
			log.debug("RequestURI: " + ((ShiroHttpServletRequest)request).getRequestURI());
		}
			
		if(SecurityUtils.getSubject().isAuthenticated()){
			System.out.println("获取访问URL所需要的角色,然后看当前用户是否有此角色");
		}else {
			return false;
		}
		
		return true;
	}

}
