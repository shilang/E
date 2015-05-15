/**
 * @Title:  LogsServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  TODO
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月20日 上午10:25:51
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloud.erp.dao.LogDao;
import com.cloud.erp.entities.table.Log;
import com.cloud.erp.service.LogService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  LogsServiceImpl
 * @Description  TODO
 * @author  bollen bollen@live.cn
 * @date  2015年3月20日 上午10:25:51
 *
 */
@Service("logService")
public class LogServiceImpl implements LogService{

	private LogDao logsDao;
	
	/**
	 * @param logsDao the logsDao to set
	 */
	@Autowired
	public void setLogsDao(LogDao logsDao) {
		this.logsDao = logsDao;
	}
	/* (non-Javadoc)
	 * @see com.cloud.erp.service.LogsService#findLogsAllList(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public List<Log> findLogAllList(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return logsDao.findLogAllList(map, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.LogsService#getCount(java.util.Map, com.cloud.erp.utils.PageUtil)
	 */
	@Override
	public Long getCount(Map<String, Object> map, PageUtil pageUtil) {
		// TODO Auto-generated method stub
		return logsDao.getCount(map, pageUtil);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.LogsService#persistenceLogs(com.cloud.erp.entities.Log)
	 */
	@Override
	public boolean persistenceLog(Log l) {
		// TODO Auto-generated method stub
		return logsDao.persistenceLog(l);
	}

	/* (non-Javadoc)
	 * @see com.cloud.erp.service.LogsService#delLogs(java.lang.Integer)
	 */
	@Override
	public boolean delLog(Integer logId) {
		// TODO Auto-generated method stub
		return logsDao.delLog(logId);
	}

}
