package com.cloud.erp.dao;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.table.Permission;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.entities.viewmodel.TreeGrid;

public interface PermissionAssignmentDao {

	List<TreeGrid> findAllFunctionsList(Integer pid);

	Long getCount(Map<String, Object> param);

	boolean savePermission(Integer roldId, String checkedIds);

	List<Permission> getRolePermission(Integer roleId);

	boolean persistenceRole(Map<String, List<Role>> map);

	List<Role> findAllRoleList(Map<String, Object> param, Integer page,
			Integer rows, boolean isPage);

	boolean persistenceRole(Role r);

	boolean persistenceRole(Integer roleId);
}
