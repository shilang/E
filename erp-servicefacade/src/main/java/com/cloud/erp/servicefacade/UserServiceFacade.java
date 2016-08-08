package com.cloud.erp.servicefacade;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.IdentityManager;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.table.User;
import com.cloud.erp.entities.table.UserInfo;
import com.cloud.erp.entities.viewmodel.UserRoleModel;
import com.cloud.erp.service.UserService;
import com.cloud.erp.utils.PageUtil;

@Service("userService")
public class UserServiceFacade implements UserService{
	
	@Resource
	private UserService userServiceImpl;
	
	@Autowired
	private IdentityManager identityManager;

	@Override
	public boolean persistence(User user) throws Exception {
		
		userServiceImpl.persistence(user);
		identityManager.saveActivitiUser(user);
		return true;
	}
	
	@Override
	public List<User> findUsers() {
		
		return userServiceImpl.findUsers();
	}

	@Override
	public List<User> findAll(Map<String, Object> params, PageUtil pageUtil) {
		
		return userServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		
		return userServiceImpl.getCount(params);
	}

	@Override
	public List<UserRoleModel> findUserRolesList(Integer userId) {
		
		return userServiceImpl.findUserRolesList(userId);
	}

	@Override
	public boolean saveUserRoles(Integer userId, String isCheckedIds) {
		
		User user = get(userId);
		String[] roleIds  = isCheckedIds.split(",");
		List<String> roles = new ArrayList<String>();
		for(String roleId : roleIds){
			roles.add(getRole(Integer.valueOf(roleId)).getName());
		}
		identityManager.addMembershipToActivitiGroups(user.getName(), roles);
		return userServiceImpl.saveUserRoles(userId, isCheckedIds);
	}

	@Override
	public boolean updatePasswd(String username, String oldPasswd,
			String newPasswd) throws Exception {
		
		return userServiceImpl.updatePasswd(username, oldPasswd, newPasswd);
	}

	@Override
	public boolean deleteToUpdate(Integer userId) {
		
		return userServiceImpl.deleteToUpdate(userId);
	}

	@Override
	public User get(Integer userId) {
		
		return userServiceImpl.get(userId);
	}

	@Override
	public Role getRole(Integer roleId) {
		
		return userServiceImpl.getRole(roleId);
	}

	@Override
	public UserInfo getUserById(Integer id) {
		
		return userServiceImpl.getUserById(id);
	}

	@Override
	public void update(User master) {
		
		userServiceImpl.update(master);
	}

}
