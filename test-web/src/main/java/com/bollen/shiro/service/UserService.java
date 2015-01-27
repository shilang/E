package com.bollen.shiro.service;

import java.util.List;
import java.util.Set;

import com.bollen.shiro.entity.User;

public interface UserService {
	
	public User createUser(User user);
	
	public User updateUser(User user);
	
	public void deleteUser(Long userId);
	
	public void changePassword(Long userId, String newPassword);
	
	User findOne(Long userId);
	
	List<User> findAll();
	
	public User findByUsername(String username);
	
	public Set<String> findRoles(String username);
	
	public Set<String> findPermission(String username);
	
}
