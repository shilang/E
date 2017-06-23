package com.cloud.erp.service;

import com.cloud.erp.entities.table.QualityComplain;
import com.cloud.erp.service.common.GeneralService;

public interface ComplainService extends GeneralService<QualityComplain>{

	String getCurrTaskDefKey(String userId, String processInstanceId);
	
	boolean updateSegment(String segment, QualityComplain qualityComplain) throws Exception;
	
}
