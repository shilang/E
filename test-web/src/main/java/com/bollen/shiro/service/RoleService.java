package com.bollen.shiro.service;

import java.util.List;
import java.util.Set;

import com.bollen.shiro.entity.Role;

public interface RoleService {

	public Role createRole(Role role);
	public Role updateRole(Role role);
	public Role deleteRole(Long roleId);
	
	public Role findOne(Long roleId);
	public List<Role> findAll();
	
	Set<String> findRoles(Long... roleIds);
	
	Set<String> findPermissions(Long[] roleIds);
}
