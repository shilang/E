package com.cloud.erp.servicefacade;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.activiti.IdentityManager;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.service.RoleService;
import com.cloud.erp.utils.PageUtil;

@Service("roleService")
public class RoleServiceFacade implements RoleService {

	@Resource
	private RoleService roleServiceImpl;
	
	@Autowired
	private IdentityManager identityManager;
	
	@Override
	public List<Role> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return roleServiceImpl.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return roleServiceImpl.getCount(params);
	}

	@Override
	public Role get(Integer id) {
		return roleServiceImpl.get(id);
	}

	@Override
	public void update(Role master) {
		roleServiceImpl.update(master);
	}

	@Override
	public boolean persistence(Role role) throws Exception {
		identityManager.saveActivitiGroup(role);
		return roleServiceImpl.persistence(role);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		identityManager.delGroup(pid.toString());
		return roleServiceImpl.deleteToUpdate(pid);
	}

	@Override
	public List<Role> findAllRoleList() {
		return roleServiceImpl.findAllRoleList();
	}

}
