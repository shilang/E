/**
 * @Title:  UserServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
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
import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.UserDao;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.table.UserInfo;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.service.UserService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  UserServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年2月3日  上午9:29:31
 *
 */

@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findUsers() {
		
		return userDao.findAll(null, null);
	}

	@Override
	public List<UserRoleModel> findUserRolesList(Integer userId) {
		
		return userDao.findUserRolesList(userId);
	}

	@Override
	public boolean saveUserRoles(Integer userId, String isCheckedIds) {
		
		return userDao.saveUserRoles(userId, isCheckedIds);
	}
	
	private String md5Hash(String source, String salt){
		return new Md5Hash(source, salt, 1).toString();
	}

	@Override
	public boolean updatePasswd(String username, String oldPasswd,
			String newPasswd) throws Exception {
		
		User user = userDao.getUser(username);
		String salt = user.getSalt();
		String oldPwd = md5Hash(oldPasswd, salt);
		if(user.getPassword().equalsIgnoreCase(oldPwd)){
			user.setPassword(md5Hash(newPasswd, salt));
			userDao.persistence(user);
			return true;
		}
		
		return false;
	}

	@Override
	public Role getRole(Integer roleId) {
		
		return userDao.getRole(roleId);
	}

	@Override
	public UserInfo getUserById(Integer id) {
		
		return userDao.getUserById(id);
	}

	@Override
	public List<User> findAll(Map<String, Object> params, PageUtil pageUtil) {
		
		return userDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return userDao.getCount(params);
	}

	@Override
	public User get(Integer id) {
		
		return userDao.get(id);
	}

	@Override
	public void update(User master) {
		
		userDao.update(master);
	}

	@Override
	public boolean persistence(User user) throws Exception {
		String salt = UUID.randomUUID().toString();
		user.setSalt(salt);
		user.setPassword(md5Hash(user.getPassword(), salt));
		
		return userDao.persistence(user);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		
		return userDao.deleteToUpdate(pid);
	}
	
}
