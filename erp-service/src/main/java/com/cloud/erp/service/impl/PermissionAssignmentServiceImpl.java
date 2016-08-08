package com.cloud.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.PermissionAssignmentDao;
import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.service.PermissionAssignmentService;

@Service("permissionAssignmentService")
public class PermissionAssignmentServiceImpl implements
		PermissionAssignmentService {
	
	@Autowired
	private PermissionAssignmentDao permissionAssignmentDao;
	
	@Override
	public List<Permission> findAllFunctionsList(Integer pid) {
		return permissionAssignmentDao.findAllFunctionsList(pid);
	}

	@Override
	public boolean savePermission(Integer roldId, String checkedIds) {
		return permissionAssignmentDao.savePermission(roldId, checkedIds);
	}

	@Override
	public List<Permission> getRolePermission(Integer roleId) {
		return permissionAssignmentDao.getRolePermission(roleId);
	}

}
