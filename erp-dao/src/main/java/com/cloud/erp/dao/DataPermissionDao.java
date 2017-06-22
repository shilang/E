package com.cloud.erp.dao;

import java.util.List;
import java.util.Map;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.entities.table.DataPermission;

/**
 * 
 * @author Bollen
 *
 */
public interface DataPermissionDao extends GeneralDao<DataPermission>{
	
	boolean persistenceDataPermission(Map<String, List<DataPermission>> entries);
	
}
