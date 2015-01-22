package com.cloud.erp.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cloud.erp.dao.DepartmentDao;
import com.cloud.erp.entities.Department;

public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

	@Override
	public Department getDepartment(Integer deptId) {
		String hql = "from Department d where did=:did";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("did", deptId);
		return get(hql, params);
	}

	@Override
	public List<Department> getAllDepartments() {
		String hql = "from Department";
		return getForList(hql, null);
	}

}
