package com.cloud.erp.service;

import java.util.List;
import java.util.Map;

import com.cloud.erp.entities.table.DataPermission;
import com.cloud.erp.service.common.GeneralService;

public interface DataPermissionService extends GeneralService<DataPermission>{
	
	boolean persistenceDataPermission(Map<String, List<DataPermission>> entries);
	
}
