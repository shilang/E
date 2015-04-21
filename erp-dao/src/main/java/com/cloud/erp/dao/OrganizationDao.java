/**
 * @Title:  OrganizationDao.java
 * @Package:  com.cloud.erp.dao
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:47:40
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.entities.Organization;
import com.cloud.erp.entities.viewmodel.TreeModel;

/**
 * @ClassName OrganizationDao
 * @Description TODO
 * @author bollen bollen@live.cn
 * @date 2015年3月18日 上午10:47:40
 *
 */
public interface OrganizationDao {

	List<TreeModel> findOrganizationList();

	List<Organization> findOrganizationList(Integer id);

	boolean persistenceOrganization(Organization o);

	boolean delOrganization(Integer id);
}
