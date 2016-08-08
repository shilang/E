package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.table.Permission;

public interface PermissionAssignmentDao {

	List<Permission> findAllFunctionsList(Integer pid);

	boolean savePermission(Integer roldId, String checkedIds);

	List<Permission> getRolePermission(Integer roleId);

}
