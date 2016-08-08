/**
 * @Title:  ShiroServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月6日  上午11:26:13
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.ShiroDao;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.service.ShiroService;

/**
 * @ClassName  ShiroServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月6日  上午11:26:13
 *
 */

@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

	@Autowired
	private ShiroDao shiroDao;

	@Override
	public User getUser(String username) {
		
		return shiroDao.getUser(username);
	}

	@Override
	public List<Object> getPermissions(boolean isAdmin, String username) {
		
		return shiroDao.getPermissions(isAdmin, username);
	}

}
