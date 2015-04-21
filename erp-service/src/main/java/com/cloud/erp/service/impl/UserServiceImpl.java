/**
 * @Title:  UserServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年2月3日  上午9:29:31
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.UserDao;
import com.cloud.erp.entities.User;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.service.UserService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  UserServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  上午9:29:31
 *
 */

@Service("userService")
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	/**
	 * @param userDao the userDao to set
	 */
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.UserService#persistenceUsers(java.util.Map)
	 */
	@Override
	public boolean persistenceUsers(Map<String, List<User>> map) {
		// TODO Auto-generated method stub
		return userDao.persistenceUsers(map);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.UserService#findAllUserList(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<User> findAllUsersList(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return userDao.findAllUsersList(map, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.UserService#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return userDao.getCount(map, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.UserService#findUsersRolesList(java.lang.Integer)
	 */
	@Override
	public List<UserRoleModel> findUserRolesList(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.findUserRolesList(userId);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.UserService#saveUserRoles(java.lang.Integer, java.lang.String)
	 */
	@Override
	public boolean saveUserRoles(Integer userId, String isCheckedIds) {
		// TODO Auto-generated method stub
		return userDao.saveUserRoles(userId, isCheckedIds);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.UserService#persistenceUsers(com.cloud.erp.entities.User)
	 */
	@Override
	public boolean persistenceUser(User u) {
		// TODO Auto-generated method stub
		return userDao.persistenceUser(u);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.UserService#delUsers(java.lang.Integer)
	 */
	@Override
	public boolean delUser(Integer userId) {
		// TODO Auto-generated method stub
		return userDao.delUser(userId);
	}
	
}
