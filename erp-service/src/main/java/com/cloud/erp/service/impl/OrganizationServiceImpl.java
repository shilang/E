/**
 * @Title:  OrganizationServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:45:33
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.OrganizationDao;
import com.cloud.erp.entities.Organization;
import com.cloud.erp.entities.viewmodel.TreeModel;
import com.cloud.erp.service.OrganizationService;

/**
 * @ClassName  OrganizationServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月18日 上午10:45:33
 *
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {

	private OrganizationDao organizationDao;
	
	/**
	 * @param organizationDao the organizationDao to set
	 */
	@Autowired
	public void setOrganizationDao(OrganizationDao organizationDao) {
		this.organizationDao = organizationDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.OrganizationService#findOrganizationList()
	 */
	@Override
	public List<TreeModel> findOrganizationList() {
		// TODO Auto-generated method stub
		return organizationDao.findOrganizationList();
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.OrganizationService#findOrganizationList(java.lang.Integer)
	 */
	@Override
	public List<Organization> findOrganizationList(Integer id) {
		// TODO Auto-generated method stub
		return organizationDao.findOrganizationList(id);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.OrganizationService#persistenceOrganization(com.cloud.erp.entities.Organization)
	 */
	@Override
	public boolean persistenceOrganization(Organization o) {
		// TODO Auto-generated method stub
		return organizationDao.persistenceOrganization(o);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.OrganizationService#delOrganization(java.lang.Integer)
	 */
	@Override
	public boolean delOrganization(Integer id) {
		// TODO Auto-generated method stub
		return organizationDao.delOrganization(id);
	}

}
