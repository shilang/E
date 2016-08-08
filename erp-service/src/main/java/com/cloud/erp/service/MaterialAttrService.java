package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.ICItemAttr;
import com.cloud.erp.service.common.GeneralService;

public interface MaterialAttrService extends GeneralService<ICItemAttr>{

	List<ICItemAttr> findMaterialAttrsByPId(Integer pid);
	
	List<ICItemAttr> findMaterialAttrs();
	
}
