package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.PermissionAssignmentDao;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.viewmodel.TreeGrid;
import com.cloud.erp.service.PermissionAssignmentService;

@Service("permissionAssignmentService")
public class PermissionAssignmentServiceImpl implements
		PermissionAssignmentService {
	
	private PermissionAssignmentDao permissionAssignmentDao;
	
	@Autowired
	public void setPermissionAssignmentDao(
			PermissionAssignmentDao permissionAssignmentDao) {
		this.permissionAssignmentDao = permissionAssignmentDao;
	}
	
	@Override
	public List<TreeGrid> findAllFunctionsList(Integer pid) {
		// TODO Auto-generated method stub
		return permissionAssignmentDao.findAllFunctionsList(pid);
	}

	@Override
	public Long getCount(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return permissionAssignmentDao.getCount(param);
	}

	@Override
	public boolean savePermission(Integer roldId, String checkedIds) {
		// TODO Auto-generated method stub
		return permissionAssignmentDao.savePermission(roldId, checkedIds);
	}

	@Override
	public List<Permission> getRolePermission(Integer roleId) {
		// TODO Auto-generated method stub
		return permissionAssignmentDao.getRolePermission(roleId);
	}

	@Override
	public boolean persistenceRole(Map<String, List<Role>> map) {
		// TODO Auto-generated method stub
		return permissionAssignmentDao.persistenceRole(map);
	}

	@Override
	public List<Role> findAllRoleList(Map<String, Object> param, Integer page,
			Integer rows, boolean isPage) {
		// TODO Auto-generated method stub
		return permissionAssignmentDao.findAllRoleList(param, page, rows, isPage);
	}

	@Override
	public boolean persistenceRole(Role r) {
		// TODO Auto-generated method stub
		return permissionAssignmentDao.persistenceRole(r);
	}

	@Override
	public boolean persistenceRole(Integer roleId) {
		// TODO Auto-generated method stub
		return permissionAssignmentDao.persistenceRole(roleId);
	}

}
