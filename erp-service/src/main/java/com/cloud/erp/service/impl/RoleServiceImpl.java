package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.RoleDao;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.service.RoleService;
import com.cloud.erp.utils.PageUtil;

@Service("roleServiceImpl")
public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleDao roleDao;

	@Override
	public List<Role> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return roleDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return roleDao.getCount(params);
	}

	@Override
	public Role get(Integer id) {
		return roleDao.get(id);
	}

	@Override
	public void update(Role master) {
		roleDao.update(master);
	}

	@Override
	public boolean persistence(Role master) throws Exception {
		return roleDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return roleDao.deleteToUpdate(pid);
	}

	@Override
	public List<Role> findAllRoleList() {
		return roleDao.findAll(null, null);
	}

}
