/**
 * @Title:  DepartmentDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:48:39
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.DepartmentDao;
import com.cloud.erp.dao.common.BaseDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Department;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  DepartmentDaoImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月18日 上午10:48:39
 *
 */
@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("departmentId");
	}
	
	@Resource
	private GeneralDaoSupport<Department> generalDao;

	@Autowired
	private BaseDao<Department> baseDao;

	@Override
	public List<Department> findDepartments(Integer id) {
		String hql="from Department t where t.status='A' ";
		if (null==id||"".equals(id))
		{
			hql+=" and t.pid is null";
		}else {
			hql+=" and t.pid="+id;
		}
		
		return baseDao.find(hql);
	}

	@Override
	public List<Department> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return generalDao.findAll(Department.class, params, pageUtil);
	}
	
	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Department.class, params);
	}
	
	@Override
	public Department get(Integer id) {
		return generalDao.get(Department.class, id);
	}
	
	@Override
	public void update(Department master) {
		generalDao.update(master);
	}
	
	@Override
	public boolean persistence(Department master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public boolean deleteToUpdate(Integer pid) {
		String hql="from Department t where t.status='A' and t.pid=" + pid;
		List<Department> list = baseDao.find(hql);
		if (list.size()!=0)
		{
			return false;
		}else {
			String hql2="from Employee t where t.departmentId=" + pid;
			List list2 = baseDao.find(hql2);
			if (list2.size()!=0)
			{
				return false;
			}else {
				
				return generalDao.deleteToUpdate(Department.class, pid, statusFields);
			}
		}
	}

}
