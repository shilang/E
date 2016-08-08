/**
 * @Title:  ShiroServcie.java
 * @Package:  com.cloud.erp.service
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月6日  上午11:23:52
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.User;

/**
 * @ClassName ShiroServcie
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年2月6日 上午11:23:52
 *
 */
public interface ShiroService {

	public User getUser(String username);

	public List<Object> getPermissions(boolean isAdmin, String username);

}
