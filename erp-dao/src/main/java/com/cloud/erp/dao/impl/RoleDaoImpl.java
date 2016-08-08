package com.cloud.erp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.RoleDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Role;
import com.cloud.erp.utils.PageUtil;

@Repository("roleDao")
public class RoleDaoImpl implements RoleDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("roleId");
	}
	
	@Resource
	private GeneralDaoSupport<Role> generalDao;
	
	@Autowired
	private BaseDao<Role> baseDao;

	@Override
	public List<Role> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(Role.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Role.class, params);
	}

	@Override
	public Role get(Integer id) {
		return generalDao.get(Role.class, id);
	}

	@Override
	public void update(Role master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(Role master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Role.class, pid, statusFields);
	}

}
