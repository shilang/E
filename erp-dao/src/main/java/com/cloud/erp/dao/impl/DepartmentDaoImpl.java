/**
 * @Title:  DepartmentDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  TODO
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

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.DepartmentDao;
import com.cloud.erp.entities.table.Department;
import com.cloud.erp.utils.Constants;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  DepartmentDaoImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月18日 上午10:48:39
 *
 */
@Repository("departmentDao")
public class DepartmentDaoImpl implements DepartmentDao {

	private BaseDao<Department> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Department> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.OrganizationDao#delOrganization(java.lang.Integer)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean delDepartment(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Department t where t.status='A' and t.pid="+id;
		List<Department> list = baseDao.find(hql);
		if (list.size()!=0)
		{
			return false;
		}else {
			String hql2="from Employee t where t.departmentId="+id;
			List list2 = baseDao.find(hql2);
			if (list2.size()!=0)
			{
				return false;
			}else {
				Department o = baseDao.get(Department.class, id);
				o.setLastmod(new Date());
				o.setModifier(Constants.getCurrentUser().getUserId());
				o.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				baseDao.deleteToUpdate(o);
			}
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.OrganizationDao#findOrganizationList()
	 */
	@Override
	public List<Department> findDepartments(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql="from Department t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		
		return baseDao.find(hql, params, pageUtil.getPage(), pageUtil.getRows());
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.OrganizationDao#findOrganizationList(java.lang.Integer)
	 */
	@Override
	public List<Department> findDepartments(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Department t where t.status='A' ";
		if (null==id||"".equals(id))
		{
			hql+=" and t.pid is null";
		}else {
			hql+=" and t.pid="+id;
		}
		
		return baseDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.OrganizationDao#persistenceOrganization(com.cloud.erp.dao.Organization)
	 */
	@Override
	public boolean persistenceDepartment(Department department) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if (null==department.getDepartmentId()||"".equals(department.getDepartmentId()))
		{
			department.setCreated(new Date());
			department.setLastmod(new Date());
			department.setCreater(userId);
			department.setModifier(userId);
			department.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(department);
		}else {
			department.setLastmod(new Date());
			department.setModifier(userId);
			baseDao.update(department);
		}
		
		return true;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.DepartmentDao#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public long getCount(Map<String, Object> params, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Department t where t.status='A' ";
		hql += Constants.getSearchConditionsHQL("t", params);
		hql += Constants.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDao.count(hql, params);
	}

}
