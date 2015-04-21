/**
 * @Title:  OrganizationService.java
 * @Package:  com.cloud.erp.service
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月18日 上午10:40:54
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.Organization;
import com.cloud.erp.entities.viewmodel.TreeModel;

/**
 * @ClassName  OrganizationService
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月18日 上午10:40:54
 *
 */
public interface OrganizationService {
	
	List<TreeModel> findOrganizationList();
	
	List<Organization> findOrganizationList(Integer id);
	
	boolean persistenceOrganization(Organization o);
	
	boolean delOrganization(Integer id);
}
