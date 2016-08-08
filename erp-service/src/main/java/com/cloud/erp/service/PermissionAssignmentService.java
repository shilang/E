package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.Permission;


public interface PermissionAssignmentService {

	List<Permission> findAllFunctionsList(Integer pid);
	
	boolean savePermission(Integer roldId, String checkedIds);
	
	List<Permission> getRolePermission(Integer roleId);
	
}
