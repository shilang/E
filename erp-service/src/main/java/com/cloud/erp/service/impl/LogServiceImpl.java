/**
 * @Title:  LogsServiceImpl.java
 * @Package:  com.cloud.erp.service.impl
 * @Description:  
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

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cloud.erp.dao.LogDao;
import com.cloud.erp.entities.table.Log;
import com.cloud.erp.service.LogService;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName  LogsServiceImpl
 * @Description  
 * @author  bollen bollen@live.cn
 * @date  2015年3月20日 上午10:25:51
 *
 */
@Service("logService")
public class LogServiceImpl implements LogService{
	
	@Resource
	private LogDao logDao;

	@Override
	public List<Log> findAll(Map<String, Object> params, PageUtil pageUtil) {
		return logDao.findAll(params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return logDao.getCount(params);
	}

	@Override
	public Log get(Integer id) {
		return logDao.get(id);
	}

	@Override
	public void update(Log master) {
		logDao.update(master);
	}

	@Override
	public boolean persistence(Log master)
			throws Exception {
		return logDao.persistence(master);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return logDao.deleteToUpdate(pid);
	}
	
}
