package com.cloud.erp.dao;

import java.util.List;

import com.cloud.erp.dao.common.GeneralDao;
import com.cloud.erp.entities.table.TransportInfo;

public interface TransportInfoDao extends GeneralDao<TransportInfo>{
	
	List<TransportInfo> findByPid(Integer pid);
	
}
