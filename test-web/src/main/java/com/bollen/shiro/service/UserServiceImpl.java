package com.bollen.shiro.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.bollen.shiro.dao.UserDao;
import com.bollen.shiro.entity.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordHelper passwordHelper;
	
	@Autowired
	private RoleService roleService;

	public User createUser(User user) {
		passwordHelper.encryptPassword(user);
		return userDao.createUser(user);
	}

	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	public void deleteUser(Long userId) {
		// TODO Auto-generated method stub
		userDao.deleteUser(userId);
	}

	public void changePassword(Long userId, String newPassword) {
		User user = userDao.findOne(userId);
		user.setPassword(newPassword);
		passwordHelper.encryptPassword(user);
		userDao.updateUser(user);
	}

	public User findOne(Long userId) {
		// TODO Auto-generated method stub
		return userDao.findOne(userId);
	}

	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.findByUsername(username);
	}

	public Set<String> findRoles(String username) {
		// TODO Auto-generated method stub
		User user = findByUsername(username);
		if(user == null){
			return Collections.EMPTY_SET;
		}
		return roleService.findRoles(user.getRoleIds().toArray(new Long[0]));
	}

	public Set<String> findPermission(String username) {
		User user = findByUsername(username);
		if(user == null){
			return Collections.EMPTY_SET;
		}
		
		return roleService.findPermissions(user.getRoleIds().toArray(new Long[0]));
	}

}
