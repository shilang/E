/**
 * @Title:  OrganizationDoaImpl.java
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.BaseDao;
import com.cloud.erp.dao.OrganizationDao;
import com.cloud.erp.entities.Organization;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.utils.Constants;

/**
 * @ClassName  OrganizationDoaImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月18日 上午10:48:39
 *
 */
@Repository("organizationDao")
public class OrganizationDaoImpl implements OrganizationDao {

	private BaseDao<Organization> baseDao;
	
	/**
	 * @param baseDao the baseDao to set
	 */
	@Autowired
	public void setBaseDao(BaseDao<Organization> baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.OrganizationDao#delOrganization(java.lang.Integer)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean delOrganization(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Organization o where o.status='A' and o.pid="+id;
		List<Organization> list = baseDao.find(hql);
		if (list.size()!=0)
		{
			return false;
		}else {
			String hql2="from Users t where t.organizeId="+id;
			List list2 = baseDao.find(hql2);
			if (list2.size()!=0)
			{
				return false;
			}else {
				Organization o = (Organization)baseDao.get(Organization.class, id);
				o.setStatus(Constants.PERSISTENCE_DELETE_STATUS);
				o.setLastmod(new Date());
				o.setModifier(Constants.getCurrentUser().getUserId());
				baseDao.deleteToUpdate(o);
			}
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.OrganizationDao#findOrganizationList()
	 */
	@Override
	public List<TreeModel> findOrganizationList() {
		// TODO Auto-generated method stub
		String hql="from Organization o where o.status='A'";
		List<Organization> tempList = baseDao.find(hql);
		List<TreeModel> list=new ArrayList<TreeModel>();
		for (Organization o : tempList)
		{
			TreeModel treeModel=new TreeModel();
			treeModel.setId(o.getOrganizationId()+Constants.NULL_STRING);
			treeModel.setPid(o.getPid()==null?null:o.getPid().toString());
			treeModel.setName(o.getFullName());
			treeModel.setState(Constants.TREE_STATUS_OPEN);
			treeModel.setIconCls(o.getIconCls());
			list.add(treeModel);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.OrganizationDao#findOrganizationList(java.lang.Integer)
	 */
	@Override
	public List<Organization> findOrganizationList(Integer id) {
		// TODO Auto-generated method stub
		String hql="from Organization o where o.status='A' ";
		if (null==id||"".equals(id))
		{
			hql+=" and o.pid is null";
		}else {
			hql+=" and o.pid="+id;
		}
		return baseDao.find(hql);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.dao.OrganizationDao#persistenceOrganization(com.cloud.erp.dao.Organization)
	 */
	@Override
	public boolean persistenceOrganization(Organization o) {
		// TODO Auto-generated method stub
		Integer userId = Constants.getCurrentUser().getUserId();
		if (null==o.getOrganizationId()||"".equals(o.getOrganizationId()))
		{
			o.setCreated(new Date());
			o.setLastmod(new Date());
			o.setCreater(userId);
			o.setModifier(userId);
			o.setStatus(Constants.PERSISTENCE_STATUS);
			baseDao.save(o);
		}else {
			o.setLastmod(new Date());
			o.setModifier(userId);
			baseDao.update(o);
		}
		return true;
	}

}
