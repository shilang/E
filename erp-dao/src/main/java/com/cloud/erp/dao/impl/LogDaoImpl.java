/**
 * @Title:  LogsDaoImpl.java
 * @Package:  com.cloud.erp.dao.impl
 * @Description:  
 * Copyright:  Copyright(C) 2015
 * @author:  bollen bollen@live.cn
 * @date:  2015年3月19日 下午3:57:31
 * @version:  v1.0
 *
 * History:
 * Date		Author		Version
 * ---------------------------------------------
 * <reasons>
 */
package com.cloud.erp.dao.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.cloud.erp.dao.LogDao;
import com.cloud.erp.dao.common.GeneralDaoSupport;
import com.cloud.erp.dao.common.StatusFields;
import com.cloud.erp.entities.table.Log;
import com.cloud.erp.utils.PageUtil;

/**
 * @ClassName LogsDaoImpl
 * @Description 
 * @author bollen bollen@live.cn
 * @date 2015年3月19日 下午3:57:31
 *
 */
@Repository("logDao")
public class LogDaoImpl implements LogDao {
	
	private final StatusFields statusFields = new StatusFields();
	
	{
		statusFields.setInterId("logId");
	}
	
	@Resource
	private GeneralDaoSupport<Log> generalDao;

	@Override
	public List<Log> findAll(Map<String, Object> params, PageUtil pageUtil) {
		
		return generalDao.findAll(Log.class, params, pageUtil);
	}

	@Override
	public long getCount(Map<String, Object> params) {
		return generalDao.getCount(Log.class, params);
	}

	@Override
	public Log get(Integer id) {
		return generalDao.get(Log.class, id);
	}

	@Override
	public void update(Log master) {
		generalDao.update(master);
	}

	@Override
	public boolean persistence(Log master) throws Exception {
		return generalDao.persistence(master, statusFields);
	}

	@Override
	public boolean deleteToUpdate(Integer pid) {
		return generalDao.deleteToUpdate(Log.class, pid, statusFields);
	}

}
