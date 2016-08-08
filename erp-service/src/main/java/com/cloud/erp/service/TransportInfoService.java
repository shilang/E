package com.cloud.erp.service;

import java.util.List;

import com.cloud.erp.entities.table.SalesOutStock;
import com.cloud.erp.entities.table.TransportInfo;
import com.cloud.erp.service.common.GeneralService;

public interface TransportInfoService extends GeneralService<TransportInfo>{
	
	List<TransportInfo> findByPid(Integer pid);
	
	boolean persistence(SalesOutStock master, TransportInfo transportInfo, String taskId) throws Exception;
	
}
