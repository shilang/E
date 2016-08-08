package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.Role;
import com.cloud.erp.service.common.GeneralService;

public interface RoleService extends GeneralService<Role>{

	List<Role> findAllRoleList();

}
